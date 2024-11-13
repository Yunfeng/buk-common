package cn.buk.util;

import cn.buk.common.Constant;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Date;

/**
 * Created by yfdai on 2017/1/14.
 */
public class BukEtermClient {

//    private static final Logger logger = Logger.getLogger(BukEtermClient.class);
    private static final Logger logger = LogManager.getLogger(BukEtermClient.class);

    private String remoteHost;
    private int remotePort;
    private String username;
    private String password;

    /**
     * socket timeout, ms
     */
    private int soTimeout = 3000;

    /**
     * 等待指令超时,ms
     */
    private int timeout = 10000;

    private Socket socket;

    private boolean logined;

    /**
     * 最后一次指令发送的时间
     */
    private Date requestTime;

    private String generatePmDataPackage(final String cmd) {
        return doGenerateDataPackage("PM" + cmd);
    }

    private String generateOne1DataPackage(final String cmd) {
        return doGenerateDataPackage("1E" + cmd);
    }

    private String doGenerateDataPackage(String cmd) {
        final int len = cmd.length() + 5;
        final int intH = len / 256;
        final int intL = len % 256;

        return "BUK" + (char)intH + (char)intL + cmd;
    }

    private BufferedWriter out;
    private InputStream input;
    private byte[] buffer = new byte[2000];

    private String doExecuteCmd(final String cmd) throws IOException {

        if (!this.isConnected()) {
            logger.warn("socket is not connected.");
            return "";
        }

        this.requestTime = DateUtil.getCurDateTime();

        out.write(cmd, 0, cmd.length());
        out.flush();

        boolean done = false;

        String rs = null;
        int readCount = 0;
        Date then = DateUtil.getCurDateTime();

        while (!done) {
            while (input.available() > 0) {
                readCount += input.read(buffer, 0, 2000);
                break;
            }
            done = readCount > 0 || ((DateUtil.getCurDateTime().getTime() - then.getTime()) > this.timeout);

            try {
                if (!done)
                    Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
                done = true;
            }
        }

        if (readCount > 0) {
            rs = readStrFromBuffer(readCount);
        }

        return rs;
    }

    private String readStrFromBuffer(int readCount) throws UnsupportedEncodingException {
        String rs = new String(buffer, 0, readCount, "GB2312");

        if ("BUK".equalsIgnoreCase(rs.substring(0, 3))) {
            int intH =  buffer[3];
            int intL =  buffer[4];
            if (intL < 0) intL = 256 + intL;
            final int len = intH * 256 + intL; //这个计算是基于GB编码的，JAVA字符串中的汉字是UTF-8编码，一个汉字可以包含在一个字符里

            if (len == readCount) {
            } else if (len < readCount) {
                logger.warn(len + " < " + readCount);
            } else {
                //数据不全
                logger.warn(len + " > " + readCount);
            }
        } else {
            logger.error("Data packet is wrong.");
        }

        return rs;
    }


    public void connect() {
        socket = new Socket();
        SocketAddress socketAddress = new InetSocketAddress(this.remoteHost, this.remotePort);

        try {
            socket.connect(socketAddress, this.soTimeout);
            if (this.socket.isConnected()) {
                out = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
                input = socket.getInputStream();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isConnected() {
        return this.socket != null ? this.socket.isConnected() : false;
    }

    public boolean login() throws IOException, InterruptedException {
        if (!this.isConnected()) return false;
        if (this.logined) return this.logined;

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
        BufferedReader input = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), "GB2312"));

        String cmd = "USERNAME=" + this.username + ";PASSWORD=" + this.password + ";VCODE=;";

        cmd = generatePmDataPackage(cmd);

        out.write(cmd, 0, cmd.length());
        out.flush();

        char[] buffer = new char[1024];

//        /this.socket.getInputStream().read()

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
            rs = String.valueOf(buffer, 0, readCount);

            if ("BUK".equalsIgnoreCase(rs.substring(0, 3))) {
                int intH = (int)rs.charAt(3);
                int intL = (int)rs.charAt(4);
                int len = intH * 256 + intL;
//                System.out.println("len: " + len);

                if (len == 9) {
                    String rs0 = rs.substring(0, 9);
                    rs0 = rs0.substring(5);
                    System.out.println(rs0);
                    this.logined = "PMOK".equalsIgnoreCase(rs0);
                }
            }
        }

        return this.logined;
    }


    public String readAvailableData() throws IOException {
        if (!this.isConnected()) {
            logger.warn("socket is not connected.");
            return "";
        }

        String rs = null;
        int readCount = 0;
        Date then = DateUtil.getCurDateTime();

        if (input.available() > 0) {
            readCount += input.read(buffer, 0, 2000);
        }

        if (readCount > 0) {
            rs = readStrFromBuffer(readCount);
        }

        return rs;
    }


    public String execCmd(final String cmd0) throws IOException {
        String cmd = generateOne1DataPackage(cmd0);

        return doExecuteCmd(cmd);
    }

    public String rt(final String pnrNo) throws IOException {
        return rt(pnrNo, true);
    }

    public String rt(final String pnrNo, final boolean needPn) throws IOException {

        //计算上次执行指令到现在的时间差
        if (this.requestTime != null && DateUtil.getPastTime(this.requestTime) < 1500) {
            // logger.info("wait for 1500ms.");
            try {
                Thread.sleep(1500-DateUtil.getPastTime(this.requestTime));

                String rs = this.readAvailableData();
                if (rs != null) logger.warn(rs);
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
                rtDone = rs0.indexOf(Constant.PNR_ENTIRELY_CANCELLED) >= 0;
            }

            if (needPn == false) break;

            try {
                if (!rtDone)
                    Thread.sleep(500);
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

    public void setRemoteHost(String remoteHost) {
        this.remoteHost = remoteHost;
    }

    public int getRemotePort() {
        return remotePort;
    }

    public void setRemotePort(int remotePort) {
        this.remotePort = remotePort;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username.toUpperCase();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setTimeout(int timeout) {
        this.timeout = timeout;
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
}
