package cn.buk.common.util;

import cn.buk.common.Constant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Date;

/**
 * 与BukPM通讯的接口类
 *
 * @author yfdai
 * 2017/1/14
 */
public class BukEtermClient {

    private static final Logger logger = LogManager.getLogger(BukEtermClient.class);

    private BukEtermClient() {
    }

    /**
     * 构造函数
     * @param host ip
     * @param port port
     * @param username username
     * @param password password
     * @param timeout 指令超时等待时间 ms
     */
    public BukEtermClient(String host, int port, String username, String password, int timeout) {
        this.remoteHost = host;
        this.remotePort = port;
        this.username = username;
        this.password = password;
        this.timeout = timeout;

        renewRequestTime();
        renewResponseTime();
    }

    @Override
    protected void finalize(){
        this.running = false;
        logger.debug(this.hashCode() + " is destroying");
    }

    private String remoteHost;
    private int remotePort;
    private String username;
    private String password;

    /**
     * 等待指令超时,ms
     */
    private int timeout = 10000;

    private boolean running = true;

    /**
     * socket timeout, ms
     */
    private int soTimeout = 3000;


    private Socket socket;

    private boolean logined;

    /**
     * 最后一次指令发送的时间
     */
    private Date requestTime;

    /**
     * 响应时间
     */
    private Date responseTime;

    /**
     * 2条指令间的最小时间间隔, ms
     */
    private int minInterval = 500;

    /**
     * 指令发送超时次数：连续超时次数
     */
    private int timeoutCount;

    /**
     * 最近一次执行指令的错误代码
     */
    private int errorCode;

    private String errorMsg;

    private String generatePmDataPackage(final String cmd) throws UnsupportedEncodingException {
        return doGenerateDataPackage("PM" + cmd);
    }

    private String generateOne1DataPackage(final String cmd) throws UnsupportedEncodingException {
        return doGenerateDataPackage("1E" + cmd);
    }

    private String doGenerateDataPackage(String cmd) throws UnsupportedEncodingException {
        byte[] bytes = cmd.getBytes("GBK");

        final int len = bytes.length + 5;
        final int intH = len / 256;
        final int intL = len % 256;

        return "BUK" + (char) intH + (char) intL + cmd;
    }

    private OutputStream out;
    private InputStream input;
    private final byte[] buffer = new byte[2000];


    /**
     * 按照设定的最短指令间隔，休息片刻
     */
    private void restForMinInterval() {
        //计算上次执行指令到现在的时间差
        if (this.requestTime == null) {
            return;
        }

        if (DateUtil.getPastTime(this.requestTime) < this.minInterval) {
            try {
                Thread.sleep(this.minInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void ping() {
        String rs = this.doExecuteCmd("");
        logger.info("Ping: " + rs + ". (" + this.hashCode() + ", " + this.responseTime + ")");
    }

    private void renewRequestTime() {
        this.requestTime = DateUtil.getCurDateTime();
    }

    private void renewResponseTime() {
        this.responseTime = DateUtil.getCurDateTime();
    }

    // 执行命令，向服务器发送数据
    private String doExecuteCmd(final String cmd) {
        if (!this.isConnected()) {
            logger.error("socket is not connected.");
            this.connect();
            return null;
        }

        if (!this.logined) {
            logger.error("connection is not logined.");
            try {
                this.login();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        String rs = null;

        try {
            restForMinInterval();

            // 清空缓存中可能有的数据
            this.readAvailableData();

            byte[] bytes = cmd.getBytes("GBK");
            out.write(bytes, 0, bytes.length);
            logger.info("bytes.length:" + bytes.length);
            out.write('\0');
            out.flush();

            renewRequestTime();

            boolean done = false;

            int readCount = 0;
            Date then = DateUtil.getCurDateTime();

            while (!done) {
                while (input.available() > 0) {
                    readCount += input.read(buffer, 0, 2000);
                    break;
                }
                done = readCount > 0 || ((DateUtil.getCurDateTime().getTime() - then.getTime()) > this.timeout);

                if (done) {
                    if (readCount > 0) {
                        renewResponseTime();

                        timeoutCount = 0;
                        rs = readStrFromBuffer(readCount);
                        if (rs != null && rs.length() > 8 && rs.charAt(8) == '0') {
                            // 是系统发送过来的消息，忽略 //TODO 此处会误判，需要改进 BUK 91E40.SSR TKNE KE HK1 ICNHNL 053 L06FEB 1802875844402/2/P5 刚好第9位是0
                            // 系统消息，忽略
                            //BUK  1E
                            //0123456
                            String temp = rs.substring(5, 7);
                            if (!"1E".equalsIgnoreCase(temp)) {
                                logger.error(temp);
                                logger.info(rs.replaceAll("\r", "\r'n"));
                                continue;
                            }
                        }

                        break;
                    } else {
                        // 超时
                        timeoutCount++;
                        errorCode = 40001;
                        errorMsg = "Time out";
                    }
                } else {
                    // 继续等待
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        logger.error(e.getMessage());
                        done = true;
                    }
                }
            }
        } catch (IOException ex) {
            this.connect();
            logger.error(ex.getMessage() + ".(" + this.hashCode() + ")");
            rs = null;
        }

        return rs;
    }

    /**
     * 从BUFFER中获取当前报文
     */
    private String readStrFromBuffer(int readCount) throws UnsupportedEncodingException {
        String rs = new String(buffer, 0, readCount, "GBK");

        if ("BUK".equalsIgnoreCase(rs.substring(0, 3))) {
            int intH =  buffer[3];
            int intL =  buffer[4];
            if (intL < 0) {
                intL = 256 + intL;
            }
            final int len = intH * 256 + intL; //这个计算是基于GB编码的，JAVA字符串中的汉字是UTF-8编码，一个汉字可以包含在一个字符里

            if (len == readCount) {
            } else if (len < readCount) {
                logger.debug(len + " < " + readCount);
            } else {
                //数据不全
                logger.debug(len + " > " + readCount);
            }
        } else {
            logger.debug("Data packet is wrong.");
        }

        return rs;
    }


    public void connect() {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException ex) {
                logger.error(ex.getMessage());
            }
            socket = null;
        }

        this.logined = false;

        socket = new Socket();
        SocketAddress socketAddress = new InetSocketAddress(this.remoteHost, this.remotePort);

        try {
            socket.connect(socketAddress, this.soTimeout);
            if (this.socket.isConnected()) {
                out = socket.getOutputStream();
                input = socket.getInputStream();

                this.login();
            }
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isConnected() {
        return this.socket != null ? this.socket.isConnected() : false;
    }

    public boolean login() throws IOException, InterruptedException {
        if (!this.isConnected()) {
            return false;
        }
        if (this.logined) {
            return true;
        }

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
        BufferedReader input = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), "GBK"));

        String cmd = "USERNAME=" + this.username + ";PASSWORD=" + this.password + ";VCODE=;";
//        System.out.println(cmd);
        cmd = generatePmDataPackage(cmd);

        out.write(cmd, 0, cmd.length());
        out.flush();

        renewRequestTime();

        char[] buffer = new char[1024];

        boolean done = false;
        String rs = "";
        int readCount = 0;
        Date then = DateUtil.getCurDateTime();
        while (!done) {
            Thread.sleep(100);
            while (input.ready()) {
                readCount += input.read(buffer, 0, 1024);
                break;
            }
            done = readCount > 0 || ((DateUtil.getCurDateTime().getTime() - then.getTime()) > this.timeout);
        }

        if (readCount > 0) {
            renewResponseTime();

            rs = String.valueOf(buffer, 0, readCount);

            if ("BUK".equalsIgnoreCase(rs.substring(0, 3))) {
                int intH = rs.charAt(3);
                int intL = rs.charAt(4);
                int len = intH * 256 + intL;

                if (len == 9) {
                    String rs0 = rs.substring(0, 9);
                    rs0 = rs0.substring(5);

                    this.logined = "PMOK".equalsIgnoreCase(rs0);
                }
            }
        }

        if (this.logined) {
            Thread.sleep(2000);
            this.readAvailableData();
        }

        return this.logined;
    }


    public String readAvailableData() throws IOException {
        if (!this.isConnected()) {
            logger.debug("socket is not connected.");
            return "";
        }

        String rs = null;

        if (input.available() > 0) {
            int readCount = input.read(buffer, 0, 2000);
            rs = readStrFromBuffer(readCount);

            logger.warn("eterm message: " + rs.replaceAll("\r", "\r\n"));

            if (rs.length() > 8 && rs.charAt(7) == '0') {
                rs = null;
            }
        }

        return rs;
    }

    public String execCmd(final String cmd0) throws UnsupportedEncodingException {
        String cmd1 = (char) 30 + cmd0;
        String cmd = generateOne1DataPackage(cmd1);

        return doExecuteCmd(cmd);
    }

    public String rt(final String pnrNo) throws IOException {
        return rt(pnrNo, true);
    }

    public String rt(final String pnrNo, final boolean needPn) throws IOException {
        //计算上次执行指令到现在的时间差
        if (this.requestTime != null && DateUtil.getPastTime(this.requestTime) < this.minInterval) {
            try {
                Thread.sleep(this.minInterval);

                String rs = this.readAvailableData();
                if (rs != null) {
                    logger.debug(rs);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        String hostCmd = "RT " + pnrNo;
        String pnrDetail = "";

        boolean rtDone = false;

        logger.debug(hostCmd);

        String rs0;
        while (!rtDone) {
            rs0 = this.doExecuteCmd(generateOne1DataPackage(hostCmd));
            if (rs0 == null) {
                logger.error(pnrNo + "<" + hostCmd + ">: result is null. (" + this.hashCode() + ", " + this.timeoutCount + ", " +  this.responseTime + ")");
                break;
            }

            if (pnrDetail.length() == 0) {
                pnrDetail += rs0.substring(7);
                if (!rs0.contains(pnrNo)) {
                    break;
                }
            } else {
                pnrDetail += "\r" + rs0.substring(7);
            }

            rtDone = !rs0.endsWith("+");
            hostCmd = "PN";

            //如果pNR是被取消了，则不需要PN
            if (!rtDone) {
                rtDone = rs0.contains(Constant.PNR_ENTIRELY_CANCELLED);
            }

            if (!needPn) {
                break;
            }

            try {
                if (!rtDone) {
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return pnrDetail;
    }


    //setter & getter

    public String getRemoteHost() {
        return remoteHost;
    }

    public int getRemotePort() {
        return remotePort;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getSoTimeout() {
        return soTimeout;
    }

    public void setSoTimeout(int soTimeout) {
        this.soTimeout = soTimeout;
    }

    public int getTimeout() {
        return timeout;
    }

    public boolean isLogined() {
        return logined;
    }

    public void setLogined(boolean logined) {
        this.logined = logined;
    }

    public Date getRequestTime() {
        return requestTime;
}

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public int getMinInterval() {
        return minInterval;
    }

    public void setMinInterval(int minInterval) {
        this.minInterval = minInterval;
    }

    public int getTimeoutCount() {
        return timeoutCount;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

}
