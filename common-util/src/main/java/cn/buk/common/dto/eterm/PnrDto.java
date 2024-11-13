package cn.buk.common.dto.eterm;

import cn.buk.common.Constant;
import cn.buk.common.flight.dto.FlightInfoDto;
import cn.buk.common.util.DateUtil;
import cn.buk.common.util.VerifyCodeUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static cn.buk.common.Constant.DATE_YYYY_MM_DD;
import static cn.buk.common.Constant.PSG_TYPE_INF;
import static cn.buk.tms.common.constant.TmsConstant.*;
import static java.util.regex.Pattern.compile;

/**
 * Created by yfdai on 2017/1/13.
 * copy from
 */
public class PnrDto {

    // 电子客票标识
    public static final String ELECTRONIC_TICKET_PNR  = "**ELECTRONIC TICKET PNR**";

    public static final String MARRIED_SEGMENT_EXIST_IN_PNR  = "MARRIED SEGMENT EXIST IN THE PNR";

    /**
     * 表示旅客索引号的正则表达式
     */
    public static final String REG_EX_PSG_INDEX = "/P[0-9]{1,3}";

    public static final String REG_EX_RTKT_DETAIL = "[rRtTkK]{4}:[0-9]{3}-*[0-9]{10}";

    private String tktl;
    private String tktlDate;
    private String tktlTime;

    /**
     * 国内国际属性
     * -1 未知
     * 0 国内
     * 1 国际
     */
    private int type3 = -1;

    /**
     * 大客户代码
     */
    private String keyCustomerNo;

    public static int convertIdType(String val) {
        if ("NI".equalsIgnoreCase(val)) {
            return ID_TYPE_NI;
        } else if ("PP".equalsIgnoreCase(val)) {
            return ID_TYPE_PASSPORT;
        } else if ("ID".equalsIgnoreCase(val)) {
            return ID_TYPE_OTHER;
        } else {
            logger.info("convertIdType: {}", val);
            return 0;
        }
    }

    private static final Logger logger = LogManager.getLogger(PnrDto.class);


    private int id;

    private String pnrNo;

    private int enterpriseId;

    /**
     * 大编码，和pnrno配合，确定是否为同一个编码
     */
    @SuppressWarnings("SpellCheckingInspection")
    private String bigPnrNo;


    private String dport;

    private String aport;

    /**
     * 第一程或单程的航班号
     */
    private String flightNo;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date ddate;

    private String dtime;

    /**
     * 第一程或单程的预定的舱位
     */
    private String subClass;


    /**
     * 预定该PNR的用户所使用的eterm用户名
     */
    private String etermUsername;

    /**
     * 出票时限
     */
    private Date dzTime;

    /**
     * 出票状态
     * 0-未出票
     * 1-已出票
     */
    private int dzStatus=0;

    // 是否电子客票
    private boolean et = false;

    /**
     * 出票日期
     * 从t233中找到对应的编码，并将他的出票日期写入进来
     */
    private Date etdzDate;

    /**
     * 出票的office
     */
    private String etdzOffice;

    /**
     * 0 - 此编码正常状态
     * 1 - 此编码已取消
     * 4 - NO PNR
     */
    private int xeStatus=0;

    /**
     * 航段状态
     * --用于显示PNR中需要提醒业务员注意的航段，比如HX,NO等航段
     */
    private String segStatus;

    /**
     * 编码的处理状态
     * 0-不需要处理
     * 1-需要处理，取出pnrDetail，重新分析出票时限
     * 2-需要处理，取出pnrDetail, 重新分析pnrNo
     */
    private int status;


    private List<PnrFlightDto> flights;

    private List<PnrPassengerDto> passengers;

    private List<PnrTicketDto> tickets;

    private int price;

    /**
     * 实际销售运价
     */
    private int parValue;


    private int tax;

    /**
     * 佣金
     */
    private int commission;

    /**
     * 成人的总价
     * total = parValue + tax
     */
    private int total;

    private int segCount;

    /**
     * 乘机人数（不包含婴儿)
     */
    private int psgCount;

    /**
     * 乘机人数（包含成人、儿童、婴儿）
     */
    private int totalPsgCount;

    private int ticketCount;

    /**
     * pnr中包含的ctcm数量
     *
     * -3: 不在需要自动补录手机号的航司清单中
     */
    @SuppressWarnings("SpellCheckingInspection")
    private int ctcmCount;

    private int ctctCount;

    private String pnrDetail;

    private String rtktDetail;

    private boolean pnrContentProcessed;

    private String remark;

    /**
     * PNR的清Q日期
     */
    private Date issueDate;

    /**
     * 0/null - 未监控
     * 1 - 监控中, 检查NO/HX状态提醒
     * 2 - 追位，编码中的位子被NO，或HN，则查航班，并定位
     */
    private int monitoring = 0;



    /**
     * pnr状态是否已检查
     * 以下三个状态主要针对于状态为HX NO状态的PNR的处理
     */
    private String checkDone;

    /**
     * pnr状态检查时间
     */

    private Date checkTime;

    /**
     * pnr状态检查用户名
     */

    private String checker;

    /**
     * 该记录保存到数据库的时间, PNR创建时间，仅供参考
     */
    private Date createTime;

    /**
     * 最后更新时间
     */
    private Date lastUpdate;

    /**
     * 冗余字段,保存最大的航班起飞日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date maxDepartureDate;

    /**
     * 冗余字段,保存最小的航班起飞日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date minDepartureDate;


    private String pnrCommitResult;

    /**
     * pnrCommitResult生成的时间
     */
    private Date opTime;

    @SuppressWarnings("SpellCheckingInspection")
    private String linkphone;

    /**
     * 预订office
     */
    private String bookOfficeNo;

    /**
     * 授权office
     */
    private String authOfficeNo;

    /**
     * 精简的编码内容
     */
    private String compactPnrDetail;

    /**
     * RT结果的状态
     * 1 - 有效
     * 0 - 无效
     */
    private int rtResultStatus;

    /**
     * 代理费率
     */
    private double commRate;

    /**
     * 儿童公布运价
     */
    private int chdPrice;

    /**
     * 实际销售运价
     */
    private int chdParValue;

    /**
     * 税总额
     */
    private int chdTax;

    private double chdCommRate;

    /**
     * 公布运价
     */
    private int infPrice;

    /**
     * 实际销售运价
     */
    private int infParValue;

    /**
     * 税总额
     */
    private int infTax;

    private double infCommRate;

    private int adultCount;

    private int childCount;

    private int infantCount;


    @SuppressWarnings("SpellCheckingInspection")
    private void processPsgDetail(final String psgDetail0) {
        String regEx0=" 0\\.";
        Matcher m = compile(regEx0).matcher(psgDetail0);
        if (m.find()) {
            // 团队编码
            String psgDetail = psgDetail0.substring(m.end());

            String regEx1="([0-9]{1,3}\\.)";
            Matcher m1 = compile(regEx1).matcher(psgDetail);
            boolean b = m1.find();
            if (!b) {
                // 团队编码，没有名单
                //0.26WUXICCTS/AT NM0 HEHWTM
                String[] items = psgDetail.split(" ");
                this.pnrNo = items[items.length-1];
            }
            int count = 0;
            while (b) {
                if (count == 0) {
                    String temp = psgDetail.substring(0, m1.start()).trim();

                    String[] items = temp.split(" ");
                    String pnrNo0 = items[items.length - 1];

                    if (this.pnrNo == null || this.pnrNo.length() == 0) {
                        this.pnrNo = pnrNo0;
                    } else if (!this.pnrNo.trim().equalsIgnoreCase(pnrNo0.trim())) {
                        logger.info(this.pnrNo + " != " + pnrNo0);
                        return;
                    }
                }
                count++;
                int start1 =  m1.start();
                int end1 = m1.end();
                int psgNo = Integer.parseInt(psgDetail.substring(m1.start(), m1.end()-1));

                b = m1.find(m1.end());
                if (b) {
                    int start2 = m1.start();
                    String psgName = psgDetail.substring(end1, start2);
                    getPassengers().add(new PnrPassengerDto(psgNo, psgName));
                } else {
                    String psgName = psgDetail.substring(end1);
                    getPassengers().add(new PnrPassengerDto(psgNo, psgName));
                }
            }
        } else {
            // 9人以下的散客编码
            String[] items = psgDetail0.trim().split("[.]");

            String lastItem = items[items.length - 1];
            String[] lastItems = lastItem.split(" ");

            String pnrNo0 = lastItems[lastItems.length - 1];
            if (this.pnrNo == null || this.pnrNo.length() == 0) {
                this.pnrNo = pnrNo0;
            } else if (!this.pnrNo.trim().equalsIgnoreCase(pnrNo0.trim())) {
                logger.info(this.pnrNo + " != " + pnrNo0);
                return;
            }


            String psgName = "";
            int psgNo = 0;

            for (int x = 0; x < items.length; x++) {
                if (x == 0) {
                    try {
                        psgNo = Integer.parseInt(items[x]);
                    } catch (Exception ex) {
                        logger.error(items[x] + ", " + ex.getMessage() + ", " + this.pnrNo);
                        if (this.pnrDetail.length() > 50) {
                            logger.info(this.pnrDetail.substring(0, 50));
                        } else {
                            logger.info(this.pnrDetail);
                        }
                    }
                    continue;
                }

                String nameLine = items[x].trim();
                int idx = nameLine.lastIndexOf(" ");
                psgName = nameLine.substring(0, idx).trim();
                getPassengers().add(new PnrPassengerDto(psgNo, psgName));

                if (x < items.length - 1) {
                    psgNo = Integer.parseInt(nameLine.substring(idx).trim());
                }
            }
        }

    }

    private List<String> convertPnrDetail2Array() {
        List<String> lines = new ArrayList<>();

        //标示PNR项目的序号已经开始 0-未开始，1-开始，2-已结束
        int itemNoBegan = 0;
        int patMode = 0;

        final String[] lines0 = pnrDetail.split("\n");

        //PNR中的项目序号的正则表达式: 行开始，空格和0-9的数字（1-4个，有时会有2个空格和3位标号），再加一个点
        final String regStr = "^([ 0-9]{1,4}\\.)";
        final Pattern pattern = compile(regStr);

        for(String line: lines0) {
            if (line.contains(MARRIED_SEGMENT_EXIST_IN_PNR) && line.length() <= 80) {
                continue;
            }
            if (line.trim().length() == 0) {
                continue;
            }

            final String upperCaseLine = line.trim().toUpperCase();
            if (upperCaseLine.contains("PAT:")
                    || upperCaseLine.contains("PAT A")
                    || upperCaseLine.contains("PAT:A")) {
                patMode = 1;
            }

            if (patMode ==  1) {
                lines.add(line);
                continue;
            }

            int start = -1;
            int end = -1;
            Matcher m = pattern.matcher(line);
            if (m.find()) {
                start = m.start();
                end = m.end();
            }

            if (itemNoBegan == 0 && start >= 0) {
                itemNoBegan = 1;
            } else if (itemNoBegan == 1) {
                // 判断是否到OFFICE NO
                if (start >= 0) {
                    final String temp = line.substring(end).trim();
                    if (testOfficeNoFormat(temp) ) {
                        itemNoBegan = 2;
                    }
                }
            }

            if (line.length() <= 80) {
                if (itemNoBegan == 0) {
                    continue;
                }
                if (itemNoBegan != 1 || start >= 0) {
                    lines.add(line);
                    continue;
                }

                if (itemNoBegan == 1 && start == -1 && lines.size() > 0) {
                    // 合并到上一行
                    final int idx = lines.size() - 1;
                    String temp = line.trim();
                    if ("+".equalsIgnoreCase(temp) || "-".equalsIgnoreCase(temp)) {
                        //空白行，忽略
                    } else {
                        temp = lines.get(idx) + " " + temp;
                        lines.set(idx, temp);
                    }
                } else {
                    lines.add(line);
                }

                continue;
            }

            //用正则判断是否有数字表示的开头，有则认为应该分行
            while(line.length() > 80) {
                Matcher m1 = compile("[- ]?[0-9]{2,3}\\.[A-Z]").matcher(line);
                if (m1.find(end + 1)) {
                    lines.add(line.substring(0, m1.start()));
                    line = line.substring(m1.start()+1);
                } else {
                    break;
                }
            }
            if (line.length() <= 80) {
                lines.add(line);
                continue;
            }


            do {

                String line1 = null;
                if (line.length() > 80) {
                    line1 = line.substring(0, 80);
                } else {
                    line1 = line;
                    lines.add(line1);
                    continue;
                }



                if (start >= 0) {
                    lines.add(line1);
                } else {
                    // 合并到上一行
                    final int idx = lines.size() - 1;
                    if (idx >= 0) {
                        lines.set(idx, lines.get(idx) + line1);
                    } else if (line1.contains(MARRIED_SEGMENT_EXIST_IN_PNR)) {
                        //IGNORE
                    } else {
                        logger.error(this.pnrNo + ":" + line1 + ":" + line1.length());
                        if (this.pnrDetail.length() > 200) {
                            logger.error(this.pnrDetail.substring(0, 160).replace("\n", "\r\n"));
                        }
                    }
                }

                if (line.length() > 80) {
                    line = line.substring(80);
                }
            } while (line.length() > 80);


            m = compile(regStr).matcher(line);
            if (m.find()) {
                lines.add(line);
            } else {
                final int idx = lines.size() - 1;
                if (idx >= 0) {
                    String temp = lines.get(idx) + line.trim();
                    lines.set(idx, temp);
                } else if (line.contains(ELECTRONIC_TICKET_PNR)) {
                    //IGNORE
                } else {
                    logger.error(this.pnrNo + ":" + line + ":" + line.length());
                }
            }
        }

        return lines;
    }

    @SuppressWarnings("SpellCheckingInspection")
    private void processPnrDetail() {
        this.rtResultStatus = 0;
        if (this.pnrDetail == null) {
            return;
        }
        this.rtResultStatus = 1;

        if (this.pnrDetail.contains("需要授权")) {
            this.segStatus = "X1"; // 需要授权
            return;
        }
        if (this.pnrDetail.contains("NO PNR")) {
            this.segStatus = "X0"; //no pnr
            return;
        }

        if (this.pnrNo != null && this.pnrNo.length() > 0 && !this.pnrDetail.contains(this.pnrNo)) {
            logger.info("{} is not in pnr detail.", this.pnrNo);
            this.rtResultStatus = 0;
            return;
        }

        if (this.pnrDetail.contains(Constant.PNR_ENTIRELY_CANCELLED)) {
            this.segStatus = "XX";
            return;
        }

        pnrDetail = pnrDetail.replace("\r\n", "\n").replace("\r", "\n");


        if (this.pnrDetail.contains(ELECTRONIC_TICKET_PNR)) {
            this.et = true;
        }


        // 将编码内容转换到一行行的列中
        List<String> lines = convertPnrDetail2Array();

        List<String> tns = new ArrayList<>();
        List<String> tknes = new ArrayList<>();
        String fn0 = null;
        List<String> fns = new ArrayList<>();
        List<String> ssrDocs = new ArrayList<>();

        this.compactPnrDetail = null;

        // 记录老的ctcm count
        this.ctcmCount = 0;
        this.ctctCount = 0;

        int sn = 0;
        int segCount0 = 0;
        int flightLineNo = 0;
        int flightLineMode = 0; //0-还没有到航班行， 1-正在航班行，2-已过航班行
        String psgDetail = "";
        boolean contentBegined = false;

        int patMode = 0; // 0-还没有到pat区域, 1-到了
        int patType = 0; //PAT:A运价的类型，0-成人，1-表示儿童，2-表示婴儿

        //行尾分页正则表达式
        final String regEx11 = "[ ]*([-]$|[+]$)";
        final Pattern pattern = compile(regEx11);

        //PNR中的项目序号的正则表达式: 行开始，空格和0-9的数字（1-4个，有时会有2个空格和3位标号），再加一个点
        final String regStr = "^([ 0-9]{1,4}\\.[ ]{1,2}[*]?)";
        final Pattern flightPattern = compile(regStr);


        for(int i = 0; i < lines.size(); i++) {
            String strLine = lines.get(i);
            if (strLine.length() >= 80) {
                Matcher m1 = pattern.matcher(strLine);
                if (m1.find()) {
                    strLine = strLine.substring(0, m1.start());
                }
            }

            if (!contentBegined && strLine.contains(".")) {
                contentBegined = true;
            }

            if (!contentBegined) {
                continue;
            }

            if (strLine.contains("SCHEDULE CHG")) {
                continue;
            }
            if (strLine.contains(MARRIED_SEGMENT_EXIST_IN_PNR)) {
                continue;
            }
            if (strLine.contains(ELECTRONIC_TICKET_PNR)) {
                continue;
            }
            if (strLine.contains("**PNR MISMATCH,PLEASE CHECK THE PNR RMK QUEUE**")) {
                continue;
            }

            if (this.compactPnrDetail == null) {
                this.compactPnrDetail = strLine;
            } else {
                if (strLine.contains(".SHA/T SHA/T")
                        || strLine.contains(".SSR SEAT")
                        || strLine.contains(".SSR TKNE")
                        || strLine.contains(".SSR DOCS")
                        || strLine.contains(".SSR FOID")
                        || strLine.contains(".SSR FQTV")
                        || strLine.contains(".SSR ADTK")
                        || strLine.contains(".SSR OTHS")) {
                } else {
                    this.compactPnrDetail += "\r\n" + strLine;
                }
            }

            if (strLine.trim().length() <= 5) {
//                " 7.T"
                try {
                    if (strLine.length() > 3) {
                        strLine = strLine.substring(3).trim();
                        if (this.et && "T".equalsIgnoreCase(strLine)) {
                            this.dzStatus = 1; //已出票
                        }
                    }
                } catch (Exception ex) {
                    logger.error(strLine + ".");
                }
                continue;
            }

            //航班的信息是否开始了,flightLineMode = 0 表示 还没有航班信息
            // flightLineMode = 1 表示 已经正在处理航班信息了
            if (flightLineMode == 0 || flightLineMode == 1) {
                Matcher m1 = flightPattern.matcher(strLine);
                if (m1.find()) {
                    flightLineMode = 1;
                    //flight
                    // 2.  HO1218 V   TH30JUN  XIYSHA HK1   1130 1340          E T3T2 \n" +
                    //  2.  FM9392 N   TU28SEP  CSXSHA HK1   1410 1555          E T2T2
                    //01234567890123456789012345678901234567890123456789012345678901234567890123456789
                    //           1         1         1         1         1         1         1         1
                    if (flightLineNo == 0) {
                        flightLineNo = i;
                        try {
                            processPsgDetail(psgDetail);
                        } catch (Exception ex) {
                            logger.error(this.pnrNo + ": " + psgDetail);
                            this.getPassengers().clear();
                            return;
                        }
                    }
                    if (strLine.length() < 35) {
                        if (strLine.contains("ARNK")) {
                            continue;
                        }
                    }

                    if (strLine.contains("OPEN")) {
                        continue;
                    }

                    try {
                        sn++;
                        processFlightInfoLine(strLine, sn);
                        segCount0++;
                        this.segCount = segCount0;
                    } catch (Exception ex) {
                        logger.error(strLine);
                        ex.printStackTrace();
                    }
                    continue;
                }
            }

            // 6.SHA/T SHA/T 0510-82722200/CHINA COMFORT WUXI TRAVEL SERVICE/CHEN RU ABCDEFG
            // 3.SZX/T SZX/T-4006940069/SZX TENG BANG BUSINESS SERVICES CO.,LTD/PENG YU MEI
            if (strLine.contains("/T ") && flightLineNo == 0) {
                String regEx="([.A-Z]{4,}/T)+";
                boolean result= compile(regEx).matcher(strLine).find();
                if (result) {
                    //说明编码中已经没有航班信息了
                    flightLineMode = 2; //航班信息行已过
                    this.segCount = 0;
                    try {
                        //处理psgDetail
                        processPsgDetail(psgDetail);
                    } catch (Exception ex) {
                        logger.error(this.pnrNo + ": " + psgDetail);
                    }
                }
            }

            if (flightLineNo == 0) {
                psgDetail += strLine;
            }


            if (flightLineMode == 1) {
                flightLineMode = 2;
            }

            if (strLine.indexOf(".SSR FOID") > 0) {
                processSsrFoid(strLine);
            }

            if (strLine.indexOf(".SSR DOCS") > 0 && strLine.length() > 30) {
                ssrDocs.add(strLine);
            }

            //adtk
            if (strLine.indexOf(".SSR ADTK") > 0) {
                //24.SSR ADTK 1E BY SHA06SEP17/1434 OR CXL HO1135 T14SEP
                String regEx="[0-9]{2,}[A-Z]{3,}[0-9]{2,}/[0-9]{4,}";
                Matcher m = compile(regEx).matcher(strLine);
                if (m.find()) {
                    try {
                        this.tktl = strLine.substring(m.start(), m.end());
                        this.tktlDate = DateUtil.convertEtermDate(this.tktl.substring(0, 7));
                        this.tktlTime = this.tktl.substring(8);
                    } catch (Exception ex) {
                        logger.error(ex.getMessage());
                    }
                }
            }

            //" 7.BOOK CZWS 0702 1036  \n" +
            if (strLine.indexOf(".BOOK ") > 0) {
                String[] items = strLine.trim().split(" ");
                if (this.etermUsername == null || this.etermUsername.trim().length() == 0) {
                    this.etermUsername = items[1];

                    if (this.etermUsername == null || this.etermUsername.trim().length() == 0) {
                        logger.info(strLine);
                    }
                }
            }
//			34.OSI CX CTCT15961219496
//			35.OSI KA CTCT13951560989
//          18.OSI AC PAX CTCT 86-13651757240
            if (strLine.indexOf(".OSI") > 0 && strLine.indexOf("CTCT") > 0) {
                this.ctctCount++;
                try {
                    String[] items = strLine.trim().split(" ");
                    String phoneNo;
                    if (items[2].contains("PAX")) {
                        phoneNo = items[4];
                    } else {
                        phoneNo = items[2].substring(4);
                    }

                    if (this.linkphone == null) {
                        this.linkphone = phoneNo + " ";
                    } else {
                        this.linkphone += phoneNo + " ";
                    }
                } catch (Exception ex) {
                    logger.info(strLine);
                    logger.error(ex.getMessage());
                }
            }
            if (strLine.indexOf(".OSI") > 0 && strLine.indexOf("CTCM") > 0) {
                this.ctcmCount++;
                processOsiCtcm(strLine);
            }
            if (strLine.indexOf(".SSR") > 0 && strLine.indexOf("CTCM") > 0) {
                this.ctcmCount++;
                processOsiCtcm(strLine);
            }

//			"37.RMK TJ AUTH SHA518   \n" +
//					"38.RMK TJ AUTH SHA255   \n" +
            if (strLine.indexOf(".RMK TJ AUTH") > 0) {
                String[] items = strLine.trim().split(" ");
                if (this.authOfficeNo == null || this.authOfficeNo.length() == 0) {
                    this.authOfficeNo = items[3];
                } else {
                    this.authOfficeNo += "," + items[3];
                }
            }

//			21.RMK INTERSTAR--13706154921
            if (strLine.indexOf(".RMK INTERSTAR-") > 0) {
                if (this.etermUsername == null || this.etermUsername.trim().length() == 0) {
                    this.etermUsername = "INTERSTAR";
                }
            }

            //22.RMK CA/MHLD07
            if (strLine.indexOf(".RMK CA/") > 0) {
                String[] items = strLine.trim().split("/");
                if (items.length > 1) {
                    String[] items1 = items[1].split(" ");
                    this.setBigPnrNo(items1[0]);
                } else {
                    logger.warn(strLine);
                }
            }

            //.RMK IC CZ/1903833
            if (strLine.indexOf(".RMK IC ") > 0) {
                int idx = strLine.indexOf(".RMK IC ");
                String temp = strLine.substring(idx+7).trim();
//                System.out.println(temp);

                String[] items = temp.split("/");
                if (items.length > 1) {

                    this.setKeyCustomerNo(items[1]);
                } else {
                    logger.warn(strLine);
                }
            }

            //25.TN/880-1289117412/P1
            if (strLine.indexOf(".TN/") > 0) {
                if (this.et) {
                    this.dzStatus = 1;
                }
                tns.add(strLine);
            }

            if (strLine.indexOf(".SSR TKNE") > 0) {
                if (this.et) {
                    this.dzStatus = 1;
                }
                tknes.add(strLine);
            }

            if (strLine.indexOf(".FN") > 0) {
                if (fn0 != null) {
                    fns.add(fn0);
                    fn0 = null;
                }
                fn0 = strLine.trim();
                if ("-".equalsIgnoreCase(fn0.substring(fn0.length()-1))) {
                    fn0 = fn0.substring(0, fn0.length()-1).trim();
                }
            }
            if (fn0 != null && "    ".equalsIgnoreCase(strLine.substring(0, 4))) {
                fn0 += strLine.trim();
            }

            if (strLine.indexOf("XN/IN/") > 0) {
                //随行婴儿姓名
                String regEx = "XN/IN/";
                Matcher m = compile(regEx).matcher(strLine);
                if (m.find()) {
                    String temp = strLine.substring(m.end());

                    Matcher m1 = compile(REG_EX_PSG_INDEX).matcher(temp);
                    if (m1.find()) {

                        int idx = parsePassengerIndex(temp.substring(m1.start()+1, m1.end()));
                        if (idx > -1 && this.getPassengers().size() >= idx) {
                            this.getPassengers().get(idx - 1).setInfName(temp.substring(0, m1.start()));
                        }
                        PnrPassengerDto psg = new PnrPassengerDto(idx, temp.substring(0, m1.start()));
                        psg.setPsgType(Constant.PSG_TYPE_INF);
                        this.getPassengers().add(psg);
                    }
                }
            }

            if (strLine.trim().length() <= 10 || i == lines.size()-1) {
                //最后一行,预订的office
                try {
                    String regEx="[A-Z]{3,}[0-9]{3,}";
                    Matcher m = compile(regEx).matcher(strLine);
                    if (m.find()) {
                        this.bookOfficeNo = strLine.substring(m.start(), m.end());
                    }
                } catch (Exception ex) {
                    logger.error(ex.getMessage());
                    logger.info(this.pnrNo);
                    logger.info(strLine);
                }
            }

            if (patMode == 0 && strLine.contains("PAT:A")) {
                patMode = 1;
                if (strLine.contains("PAT:A*CH")) {
                    patType = 1;
                }
            } else if (patMode == 1 && strLine.contains("FARE:") && strLine.contains("01")) {
                try {
                    processPatA(strLine, patType);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                patMode = 2;
            }
        }

        try {
            if (fn0 != null) {
                fns.add(fn0);
            }

            processFltInfo();
            processTickets(tns, tknes);
            processSsrDocs(ssrDocs);
            processFn(fns);
        } catch (Exception e) {
            logger.error(this.pnrNo);
            e.printStackTrace();
        }
    }

    /**
     *       CNY为人民币国际代码。F是first、S是substantial、X是tax、T是total。
     *         FCNT：首次出票的票面价格、
     *         SCNY：客票的实际销售价格、
     *         XCNY：基建+燃油、
     *         TCNY：票价+基建+燃油的总和。
     *         FCNY机票票面价格 SCNY升舱费的差价 XCNY机建燃油费，也就是俗说的税费 TCNY是变更费TCNY后面写明变更费用后写上OB
     *         FCNY 票面价
     *         SCNY 实收价
     *         TCNY 税费
     */
    @SuppressWarnings("SpellCheckingInspection")
    private void processFn(List<String> fns) {

        if (fns.isEmpty()) {
            return;
        }
        for (String fn0 : fns) {
            try {
                boolean isChild = false;
                boolean isInfant = false;

                if (fn0.contains("/IN/")) {
                    isInfant = true;
                } else {
                    String regEx = "/P[0-9]+(/?[0-9]?)*";
                    Matcher m = compile(regEx).matcher(fn0);
                    if (m.find()) {
                        // 判断是否指定客户的运价，比如儿童
                        isChild = true;
                    }
                }

                final String fn = fn0.replace(" ", "");

                String[] items = fn.split("/");

                if (isChild) {
                    int total = 0;

                    // 儿童价格
                    for (String item : items) {
                        if (item.length() < 5) {
                            continue;
                        }
                        String f4 = item.substring(0, 4);
                        if ("FCNY".equalsIgnoreCase(f4) || "ECNY".equalsIgnoreCase(f4)) {
                            this.chdPrice = (int) Double.parseDouble(item.substring(4));
                        } else if ("SCNY".equalsIgnoreCase(f4)) {
                            this.chdParValue = (int) Double.parseDouble(item.substring(4));
                        } else if ("XCNY".equalsIgnoreCase(f4)) {
                            this.chdTax = (int) Double.parseDouble(item.substring(4));
                        } else if ("ACNY".equalsIgnoreCase(f4)) {
                            total = (int) Double.parseDouble(item.substring(4));
                        } else if ("C".equalsIgnoreCase(item.substring(0, 1))) {
                            this.chdCommRate = Double.parseDouble(item.substring(1));
                        }
                    }
                } else if (isInfant) {
                    int total = 0;

                    // 婴儿价格
                    for (String item : items) {
                        if (item.length() < 5) {
                            continue;
                        }
                        String f4 = item.substring(0, 4);
                        if ("FCNY".equalsIgnoreCase(f4) || "ECNY".equalsIgnoreCase(f4)) {
                            this.infPrice = (int) Double.parseDouble(item.substring(4));
                        } else if ("SCNY".equalsIgnoreCase(f4)) {
                            this.infParValue = (int) Double.parseDouble(item.substring(4));
                        } else if ("XCNY".equalsIgnoreCase(f4)) {
                            this.infTax = (int) Double.parseDouble(item.substring(4));
                        } else if ("ACNY".equalsIgnoreCase(f4)) {
                            total = (int) Double.parseDouble(item.substring(4));
                        } else if ("C".equalsIgnoreCase(item.substring(0, 1))) {
                            this.infCommRate = Double.parseDouble(item.substring(1));
                        }
                    }

                    if (total > 0 && total != (this.getInfParValue() + this.getInfTax())) {
                        logger.info(this.pnrNo + ": " + total + " != ");
                    }
                } else {
                    int total = 0;

                    for (String item : items) {
                        if (item.length() < 5) {
                            continue;
                        }
                        if (item.contains(".FN")) {
                            continue;
                        }
                        final String f4 = item.trim().substring(0, 1);

                        try {
                            //余下的数值部分
                            String temp = item.substring(1);
                            if (temp.contains("+")) {
                                temp = temp.substring(0, temp.indexOf("+"));
                            }
                            final String valuePart = temp.replace("CNY", "").replace("+", "");

                            if ("F".equalsIgnoreCase(f4) || "E".equalsIgnoreCase(f4)) {
                                this.price = (int) Double.parseDouble(valuePart);
                            } else if ("S".equalsIgnoreCase(f4)) {
                                this.parValue = (int) Double.parseDouble(valuePart);
                            } else if ("X".equalsIgnoreCase(f4)) {
                                this.tax = (int) Double.parseDouble(valuePart);
                            } else if ("A".equalsIgnoreCase(f4)) {
                                this.total = (int) Double.parseDouble(valuePart);
                            } else if ("C".equalsIgnoreCase(item.substring(0, 1))) {
                                this.commRate = Double.parseDouble(item.substring(1));
                            } else if ("T".equalsIgnoreCase(f4.substring(0, 1))) {
                                //TODO 此处解析各类税的金额
                            }
                        } catch (Exception ex) {
                            logger.error(ex.getMessage() + ", " + this.pnrNo);
                            System.out.println(ex.getMessage());
                            System.out.println(item);
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     *    护照号码
     *                 SSR DOCS表示输入旅客的护照等信息内容。
     *         SSR DOCS CZ HK1 P/CN/G31567598/CN/28FEB79/M/07DEC18/FANG/ZHIXING/P1
     *         MU  航空公司代码
     *         HK1 行动代码，表示座位已预订完成。
     *         P     证件类型
     *         SG  发证国家
     *         EO109323N  证件号码
     *         SG  国籍
     *         18AUG73  出生日期
     *         M  性别
     *         04AUG12  有效期
     *         TSENG/KUNTSAN   姓名
     *         P1  表示本段护照信息对应PNR中的第一个旅客
     *         "18.SSR DOCS BR HK1 P/TWN/301920358/TWN/05OCT95/F/04AUG20/P1
     *          19.SSR DOCS BR HK1 SFOTPE 007 K22DEC P/TWN/301920358/TWN/05OCT95/F/04AUG20/P1
     *          20.SSR DOCS BR HK1 ////05OCT95/F//CHENG/JUTING
     *           9.SSR DOCS CZ HK1 P/CN/G31567598/CN/28FEB79/M/07DEC18/FANG/ZHIXING/P1
     *          27.SSR DOCS OZ HK1  P/CHN/E40100381/CHN/25DEC08/M/15DEC19/JIN/JUNJIE/P1
     *          01234567890123456789012345678901234567890123456789012345678901234567890123456789
     *                    1         1         1         1         1         1         1         1
     */
    @SuppressWarnings("SpellCheckingInspection")
    private void processSsrDocs(List<String> ssrDocs) {

        final String regEx = "/P[0-9]+";
        for(final String strLine: ssrDocs) {
            try {
                //判断SSR DOCS 后面是否有 /P 的字符串

                Matcher m = compile(regEx).matcher(strLine);
                if (!m.find()) {
                    continue;
                }

                String[] items = strLine.trim().split("/");

                String temp = items[items.length - 1].substring(1);
                if (temp.indexOf(" ") > 0) {
                    temp = temp.substring(0, temp.indexOf(" "));
                }

                int psgNo = Integer.parseInt(temp);

                int gender = -1;
                int psgType = -1;
                if ("M".equalsIgnoreCase(items[5])) {
                    gender = GENDER_MALE;
                } else if ("F".equalsIgnoreCase(items[5])) {
                    gender = GENDER_FEMALE;
                } else if ("MI".equalsIgnoreCase(items[5])) {
                    gender = GENDER_MALE;
                    psgType = PSG_TYPE_INF;
                } else if ("FI".equalsIgnoreCase(items[5])) {
                    gender = GENDER_FEMALE;
                    psgType = PSG_TYPE_INF;
                }

                for (PnrPassengerDto psg : getPassengers()) {
                    if (psg.getPsgNo() == psgNo) {
                        if (psgType == PSG_TYPE_INF && psg.getPsgType() != psgType) {
                            continue;
                        }
                        // P/ 证件类型
                        int idType = ID_TYPE_PASSPORT;
                        psg.setIdType(idType);
                        // CN/ 发证国家
                        // 证件号码
                        psg.setIdNo(items[2]);
                        // 国籍
                        psg.setNationality(items[3]);
                        // 出生日期
                        psg.setBirthday(convertBirthday(items[4]));
                        // 性别
                        psg.setGender(gender);
                        //证件有效期
                        try {
                            if (items[6].length() >=7) {
                                psg.setIdExpiredDate(DateUtil.convertToDate(DateUtil.convertEtermDate(items[6])));
                            }
                        } catch (ParseException e) {
                            logger.error(this.pnrNo + ":" + strLine);
                            logger.error(e.getMessage());
                        }
                    }
                }
            } catch (Exception ex) {
                logger.error(this.pnrNo + ":" + strLine);
                logger.error(ex.getMessage());
            }
        }
    }


    private Date convertBirthday(final String birthdayInPnr) {
        // 将SSR中的生日日期转化为Date格式
        if (birthdayInPnr == null || birthdayInPnr.length() != 7) {
            return null;
        }

        String day = birthdayInPnr.substring(0, 2);
        String month = DateUtil.convertMonthFormat(birthdayInPnr.substring(2,5));
        String year1 = "19" + birthdayInPnr.substring(5);
        String year2 = "20" + birthdayInPnr.substring(5);

        Date b1;
        Date b2;
        try {
            b1 = DateUtil.convertToDate(year1 + "-" + month + "-" + day);
            b2 = DateUtil.convertToDate(year2 + "-" + month + "-" + day);
        } catch (ParseException e) {
            logger.error(e.getMessage());
            return null;
        }

        if (b2.getTime() > DateUtil.getCurDate().getTime()) {
            return b1;
        } else {
            return b2;
        }

    }



    /**
     * 处理pat:a字符串
     * @param patType 0-成人，1-儿童，2-婴儿
     */
    @SuppressWarnings("SpellCheckingInspection")
    private void processPatA(final String pata, final int patType) {
        //01 B FARE:CNY1400.00 TAX:CNY50.00 YQ:TEXEMPTYQ  TOTAL:1450.00
        String val = pata;

        int idx = val.indexOf("FARE:");
        val = val.substring(idx);

        String[] items = val.split(" ");
        Double d;
        for(String item: items) {
            if (item.contains("FARE:CNY")) {
                d = Double.parseDouble(item.substring(8));
                if (patType == 0) {
                    this.price = d.intValue();
                    this.parValue = this.price;
                } else if (patType == 1) {
                    this.chdPrice = d.intValue();
                    this.chdParValue = this.chdPrice;
                }

            } else if (item.contains("TAX:CNY")) {
                d = Double.parseDouble(item.substring(7));
                if (patType == 0) {
                    this.tax = d.intValue();
                } else if (patType == 1) {
                    this.chdTax = d.intValue();
                }
            } else if (item.contains("YQ:CNY")) {
                d = Double.parseDouble(item.substring(6));
                if (patType == 0) {
                    this.tax += d.intValue();
                } else if (patType == 1) {
                    this.chdTax += d.intValue();
                }
            }
        }
    }


    /**
     * 分析编码中的 航班信息行
     */
    @SuppressWarnings("SpellCheckingInspection")
    private void processFlightInfoLine(final String strLine, final int sn) {
        //          1         2         3         4         5         6
        //0123456789012345678901234567890123456789012345678901234567890123456789
        // 7.  NS3289 Y   TU05FEB  SJWHAK RR6   1255 1810          E T2--
        PnrFlightDto pnrFlight =  PnrFlightDto.createPnrFlight(strLine);

        pnrFlight.setSn(sn);

        getFlights().add(pnrFlight);
    }

    /**
     * 分析 ssr foid 行
     */
    @SuppressWarnings("SpellCheckingInspection")
    private void processSsrFoid(final String strLine0) {
        //" 8.SSR FOID HO HK1 NI32010719610222135X/P1  \n" +
        //" 7.SSR FOID \n" +
        //  9.SSR FOID CZ HK1 NI 22012219840817111/P2
        // 10.SSR FOID MU HK1 NIC4JHH0JVG//P1
        // 33.SSR FOID CA HK1 NI320219196912077063/P16                                    +
        // 01234567890123456789012345678901234567890123456789012345678901234567890123456789
        //           1         1         1         1         1         1         1         1

        String strLine = strLine0.length() > 80 ? strLine0.substring(0, 80) : strLine0;

        String newLine = strLine.substring(11).trim();

        // 检查行尾是否有 -  或 +  符号，有的话就去掉
        final String regExp = "[+|-]$";
        final Pattern p = compile(regExp);
        final Matcher m = p.matcher(newLine);
        if (m.find()) {
            newLine = newLine.substring(0, m.start()).trim();
        }
        if (newLine.length() == 0) {
            return;
        }

        String[] items = newLine.split(" ");
        if (items.length == 3) {
            final String item2 = items[2];
            String[] infoes = items[2].split("/");
            if (infoes.length > 1) {
                try {
                    int psgNo = Integer.parseInt(infoes[1].substring(1));
                    for (PnrPassengerDto psg : getPassengers()) {
                        if (psg.getPsgNo() == psgNo) {
                            psg.setIdType(convertIdType(infoes[0].substring(0, 2)));
                            psg.setIdNo(infoes[0].substring(2));
                        }
                    }
                } catch (Exception ex) {
                    logger.error(ex.getMessage());
                    logger.info(strLine + ", " + item2 + ", " + this.pnrNo);
                }
            } else {
                logger.info(strLine);
            }
        } else if (items.length == 4) {
            String[] infoes = items[3].split("/");
            if (infoes.length > 1) {
                int psgNo = Integer.parseInt(infoes[1].substring(1));
                for (PnrPassengerDto psg : getPassengers()) {
                    if (psg.getPsgNo() == psgNo) {
                        psg.setIdType(convertIdType(items[2]));
                        psg.setIdNo(infoes[0].substring(2));
                    }
                }
            } else {
                logger.info(strLine);
            }
        }
    }

    @SuppressWarnings("SpellCheckingInspection")
    private void processOsiCtcm(String strLine) {
        if (this.getPassengers().isEmpty()) {
            return;
        }

//		7.OSI ZH CTCM15618206320/P1
        String mobile = null;

        String regEx = "[0-9]{11}";
        Matcher m = compile(regEx).matcher(strLine);
        if (m.find()) {
            // 判断是否指定客户的运价，比如儿童
            mobile = strLine.substring(m.start(), m.end());
        }
        if (mobile == null) {
            return;
        }

        regEx = "/P[0-9]+";
        m = compile(regEx).matcher(strLine);
        if (m.find()) {
            String temp = strLine.substring(m.start() + 2).trim();

            int idx = temp.indexOf(" ");
            if (idx > 0) {
                temp = temp.substring(0, idx);
            }

            String[] items = temp.split("/");
            for(String item: items) {
                int psgIndex = Integer.parseInt(item);
                try {
                    if (psgIndex > this.getPassengers().size()) {
                        logger.error(this.pnrNo + ": " + this.getPassengers().size() + ", " + strLine);
                        break;
                    } else {
                        this.getPassengers().get(psgIndex - 1).setMobile(mobile);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

    }

    @SuppressWarnings("SpellCheckingInspection")
    private void processTickets(List<String> tns, List<String> tknes) {
        if (this.passengers == null || this.passengers.isEmpty()) {
            return;
        }

        String regEx="[0-9]{13}";
        Pattern p = compile(regEx);

        String regExInf="INF[0-9]{13}";
        Pattern pInf = compile(regExInf);

        for(String tn: tns) {
            tn = tn.trim();
            int idx = tn.indexOf(" ");
            if (idx >= 0) {
                tn = tn.substring(0, idx).trim();
            }
//			"23.TN/479-5783509314/P1                                                        +\n"
//					"24.TN/IN/479-5783509315/P1                                                 \n" +

            //是否婴儿票
            if (tn.indexOf(".TN/IN") > 0) {
                String[] items = tn.split("/");
                int psgIndex = parsePassengerIndex(items[3]);
                if (psgIndex > 0 && this.getPassengers().get(psgIndex-1).getInfTicketNo() == null) {
                    this.getPassengers().get(psgIndex-1).setInfTicketNo(items[2]);
                }

                continue;
            }


            String[] items = tn.split("/");
            if (items.length < 3) {
                continue;
            }
            int psgIndex = Integer.parseInt(items[2].substring(1).trim());
            String ticketNo = items[1];

            boolean b = false;
            for(PnrTicketDto ticket: this.getTickets()) {
                if (ticket.getPassengerIndex() == psgIndex && ticketNo.equalsIgnoreCase(ticket.getTicketNo())) {
                    b = true;
                    break;
                }
            }

            if (!b) {
                PnrTicketDto ticket = new PnrTicketDto();
                ticket.setPassengerIndex(psgIndex);
                ticket.setTicketNo(ticketNo);

                for(PnrPassengerDto psg: this.passengers) {
                    if (psg.getPsgNo() == psgIndex) {
                        ticket.setPsgName(psg.getPsgName());
                        psg.setTicketNo(ticketNo);
                        break;
                    }
                }

                this.getTickets().add(ticket);
                this.ticketCount++;
            }
        }


        for(String tkne: tknes) {
            //首先查看是否婴儿票
            Matcher mInf = pInf.matcher(tkne);
            if (mInf.find()) {
                String temp = tkne.substring(mInf.start()).trim();

                //去除空格后的多余字符
                if (temp.indexOf(" ") > 0) {
                    temp = temp.substring(0, temp.indexOf(" "));
                }

                String[] items = temp.split("/");

                int psgIndex = parsePassengerIndex(items[2]);
                if (psgIndex > 0) {
                    this.getPassengers().get(psgIndex-1).setInfTicketNo(items[0].substring(3));
                }

                for(PnrPassengerDto psg: this.getPassengers()) {
                    if (psg.getPsgNo() == psgIndex && psg.getPsgType() == 2) {
                        psg.setTicketNo(items[0].substring(3));
                    }
                }

                continue;
            }

            Matcher m = p.matcher(tkne);
            if (m.find()) {
                final String temp0 = tkne.substring(m.start());

                final String temp = temp0.contains(" ") ? temp0.substring(0, temp0.indexOf(" ")) : temp0;

                String ticketNo = temp.substring(0, 13);
                ticketNo = ticketNo.substring(0, 3) + "-" + ticketNo.substring(3);


                int psgIndex = 0;
                final int psgNoStart = findPsgNoIndex(temp);
                if (psgNoStart > -1) {
                    String tempPsgIndex = temp.substring(psgNoStart + 2)
                            .replaceAll("[+]", "")
                            .trim();
                    psgIndex = Integer.parseInt(tempPsgIndex);


                    if (this.passengers.get(psgIndex - 1).getTicketNo() == null) {
                        this.passengers.get(psgIndex - 1).setTicketNo(ticketNo);
                    }

                    boolean b = false;
                    for (PnrTicketDto ticket : this.getTickets()) {
                        if (ticket.getPassengerIndex() == psgIndex && ticketNo.equalsIgnoreCase(ticket.getTicketNo())) {
                            b = true;
                            break;
                        }
                    }

                    if (!b) {
                        PnrTicketDto ticket = new PnrTicketDto();
                        ticket.setPassengerIndex(psgIndex);
                        ticket.setTicketNo(ticketNo);

                        for (PnrPassengerDto psg : this.passengers) {
                            if (psg.getPsgNo() == psgIndex) {
                                ticket.setPsgName(psg.getPsgName());
                                break;
                            }
                        }

                        this.getTickets().add(ticket);
                        this.ticketCount++;
                    }
                }
            }
        }


    }


    private void processFltInfo() {
        if (this.flights == null || this.flights.isEmpty()) {
            return;
        }

        FlightInfoDto flt = this.flights.get(0).getFlight();
        this.dport = flt.getDport();
        this.aport = flt.getAport();
            this.ddate = flt.getDdate();

        this.flightNo = flt.getFlightNo();
        this.subClass = flt.getSubclass();
        this.segStatus = flt.getSegmentStatus();

        for(PnrFlightDto pnrFlight: this.flights) {
            FlightInfoDto info = pnrFlight.getFlight();
            try {
                Date d = info.getDdate();

                if (this.minDepartureDate == null) {
                    this.minDepartureDate = d;
                    this.maxDepartureDate = d;
                    continue;
                }

                if (this.minDepartureDate.getTime() > d.getTime()) {
                    this.minDepartureDate = d;
                }
                if (this.maxDepartureDate.getTime() < d.getTime()) {
                    this.maxDepartureDate = d;
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            if (!this.segStatus.contains("K") && info.getSegmentStatus().contains("K")) {
                this.segStatus = info.getSegmentStatus();
            }
        }
    }

    @Override
    public String toString() {
        String val = this.pnrNo + this.psgCount + this.segCount;

        for(PnrFlightDto flight: getFlights()) {
            val += flight.toString();
        }

        return val;
    }

    /**
     * 根据航班信息、记录编号、人数生成的MD5编码
     * @return hashCode
     */
    public String getHashCode() {
        return VerifyCodeUtil.MD5(this.toString());
    }

    public String getUpdateTimeDesc() {
        if (lastUpdate == null) {
            return "";
        } else {
            return DateUtil.getUpdateTimeDesc(lastUpdate);
        }
    }


    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the dzTime
     */
    public Date getDzTime() {
        return dzTime;
    }

    /**
     * @param dzTime the dzTime to set
     */
    public void setDzTime(Date dzTime) {
        this.dzTime = dzTime;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return the tax
     */
    public double getTax() {
        return tax;
    }

    /**
     * @param tax the tax to set
     */
    public void setTax(int tax) {
        this.tax = tax;
    }

    /**
     * @return the segCount
     */
    public int getSegCount() {
        return segCount;
    }

    /**
     * @param segCount the segCount to set
     */
    public void setSegCount(int segCount) {
        this.segCount = segCount;
    }

    /**
     * @return the psgCount
     */
    public int getPsgCount() {
        return psgCount;
    }

    /**
     * @param psgCount the psgCount to set
     */
    public void setPsgCount(int psgCount) {
        this.psgCount = psgCount;
    }

    /**
     * @return the pnrDetail
     */
    public String getPnrDetail() {
        return pnrDetail == null ? "" : pnrDetail;
    }

    /**
     * @param pnrDetail the pnrDetail to set
     */
    public void setPnrDetail(String pnrDetail) {
        this.setPnrDetail(pnrDetail, true);
    }

    public void setPnrDetail(String pnrDetail, boolean processing) {
        this.pnrDetail = pnrDetail;
        if (processing) {

            //判断是否RTKT结果
            if (Pattern.compile(REG_EX_RTKT_DETAIL).matcher(pnrDetail).find()) {
                this.rtktDetail = pnrDetail;
                processRtktDetail();
            } else {
                processPnrDetail();
                caclPnrCtcmCount();
            }

            this.calcPsgCount();
        }

    }

    /**
     * 处理RTKT结果
     */
    @SuppressWarnings("SpellCheckingInspection")
    private void processRtktDetail() {
        String[] lines = rtktDetail.replace("\r\n", "\n").replace("\r", "\n").split("\n");
        if (lines.length < 18) {
            logger.error("lines's length is too short(" + lines.length + ").");
            return;
        }
        PnrPassengerDto psgDto = new PnrPassengerDto(0, "");
        this.getPassengers().add(psgDto);

        //判断国内还是国际，国内的包含中文
        if (Pattern.compile("[\u4e00-\u9fa5]+").matcher(this.rtktDetail).find()) {
            //国内
            this.type3 = 0;
            processDomRtkt(lines);
        } else {
            //国际
            this.type3 = 1;
            processIntlRtkt(lines);
        }
    }

    @SuppressWarnings("SpellCheckingInspection")
    private void processDomRtkt(String[] lines) {
        int lineNo = 0;
        for(String line: lines) {
            lineNo++;
            if (lineNo == 1) {
                if (Pattern.compile(REG_EX_RTKT_DETAIL).matcher(line).find()) {
                    //RTKT指令行，含有票号
                    Matcher m = Pattern.compile("[0-9]{3}-*[0-9]{10}").matcher(line);
                    if (m.find()) {
                        this.getPassengers().get(0).setTicketNo(line.substring(m.start(),m.end()));
                    }
                    lineNo--;
                    continue;
                }
            }
            if (lineNo > 0) {
//                System.out.println(String.format("%2d", lineNo) + ":" + line);
            }
            if (lineNo == 3) {
                //          1         2         3         4         5         6
                //0123456789012345678901234567890123456789012345678901234567890123456789
                //                                     10DEC20    KS75LR/1ESHA820
                //                                    22DEC20    KM8M9Q/1ESHA274
                try {
                    Matcher m = Pattern.compile("[0-9]{2}[A-Z]{3}[0-9]{2}").matcher(line);
                    if (m.find()) {
                        String temp = line.substring(m.start(), m.end());
                        this.etdzDate = DateUtil.convertToDate(DateUtil.convertEtermDate(temp));
                    }
                    int idx = line.indexOf("/");
                    this.pnrNo = line.substring(idx-6, idx);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else if (lineNo == 4) {
                //          1         2         3         4         5         6
                //0123456789012345678901234567890123456789012345678901234567890123456789
                //    章进                                                     DEV-1
                this.passengers.get(0).setPsgName(line.substring(3, 30).trim());
            } else if (lineNo == 7) {
                //          1         2         3         4         5         6
                //0123456789012345678901234567890123456789012345678901234567890123456789
                //      合肥          HFE  ZH  9900 E 12DEC 0620  OKYTJSF40                  20K
                PnrFlightDto fltDto = new PnrFlightDto();
                this.getFlights().add(fltDto);
                this.segCount = this.getFlights().size();

                fltDto.setSn(1);

                String temp = line.trim();
                temp = temp.substring(temp.indexOf(" ")).trim();
                //          1         2         3         4         5         6
                //0123456789012345678901234567890123456789012345678901234567890123456789
                //HFE  ZH  9900 E 12DEC 0620  OKYTJSF40                  20K

                fltDto.getFlight().setDport(temp.substring(0, 3).trim());
                fltDto.getFlight().setFlightNo(temp.substring(5, 7).trim() + temp.substring(8, 13).trim());
                fltDto.getFlight().setSubclass(temp.substring(14, 15));
                String temp1 = temp.substring(16, 21);
                try {
                    fltDto.getFlight().setDdate(DateUtil.convertToDate(DateUtil.convertEtermDate(temp1)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                fltDto.getFlight().setDtime(temp.substring(22, 26).trim());
            } else if (lineNo == 9) {
                //          1         2         3         4         5         6
                //0123456789012345678901234567890123456789012345678901234567890123456789
                //HFE  ZH  9900 E 12DEC 0620  OKYTJSF40                  20K
                String temp = line.trim();
                temp = temp.substring(temp.indexOf(" ")).trim();
                this.getFlights().get(0).getFlight().setAport(temp.substring(0, 3).trim());

                if (!temp.contains("VOID")) {
                    PnrFlightDto fltDto = new PnrFlightDto();
                    this.getFlights().add(fltDto);
                    this.segCount = this.getFlights().size();

                    fltDto.setSn(2);


                    fltDto.getFlight().setDport(temp.substring(0, 3).trim());
                    fltDto.getFlight().setFlightNo(temp.substring(5, 7).trim() + temp.substring(8, 13).trim());
                    fltDto.getFlight().setSubclass(temp.substring(14, 15));
                    String temp1 = temp.substring(16, 21);
                    try {
                        fltDto.getFlight().setDdate(DateUtil.convertToDate(DateUtil.convertEtermDate(temp1), "yyyy-MM-dd"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    fltDto.getFlight().setDtime(temp.substring(22, 26).trim());

                }
            } else if (lineNo == 11) {
                //          1         2         3         4         5         6
                //0123456789012345678901234567890123456789012345678901234567890123456789
                //HFE  ZH  9900 E 12DEC 0620  OKYTJSF40                  20K
                if (this.getFlights().size() == 2) {
                    String temp = line.trim();
                    temp = temp.substring(temp.indexOf(" ")).trim();
                    this.getFlights().get(1).getFlight().setAport(temp.substring(0, 3).trim());
                }
            } else if (lineNo == 12) {
                //          1         2         3         4         5         6
                //0123456789012345678901234567890123456789012345678901234567890123456789
                //    CNY  720.00A12DEC20HFE ZH SZX720.00CNY720.00END
                this.price = (int)Float.parseFloat(line.substring(7, 14).trim());
                this.parValue = this.price;
            } else if (lineNo == 14) {
                //          1         2         3         4         5         6
                //0123456789012345678901234567890123456789012345678901234567890123456789
                //    CN    50.00
                this.tax = (int)Float.parseFloat(line.substring(7, 15).trim());
            } else if (lineNo == 16) {
                //          1         2         3         4         5         6
                //0123456789012345678901234567890123456789012345678901234567890123456789
                this.total = (int)Float.parseFloat(line.substring(7, 15).trim());
            } else if (lineNo == 17) {
                //          1         2         3         4         5         6         7
                //01234567890123456789012345678901234567890123456789012345678901234567890123456789
                //                                             CNY720.00            18  50.00
                //                                            CNY1770.00           20  100.00
                //01234567890123456789012345678901234567890123456789012345678901234567890123456789
                //CNY1770.00           20  100.00
                //CNY720.00            18  50.00
                String temp = line.trim();
//                System.out.println(temp);

                this.parValue = (int)Float.parseFloat(temp.substring(3, 13).trim());
                this.commission = (int)Float.parseFloat(temp.substring(20, 23).trim());
                this.tax = (int)Float.parseFloat(temp.substring(24).trim());
            }
        }

    }

    @SuppressWarnings("SpellCheckingInspection")
    private void processIntlRtkt(String[] lines) {
        int lineNo = 0;
        for(String line: lines) {
            lineNo++;
            if (lineNo == 1) {
                if (Pattern.compile(REG_EX_RTKT_DETAIL).matcher(line).find()) {
                    //RTKT指令行，含有票号
                    Matcher m = Pattern.compile("[0-9]{3}-*[0-9]{10}").matcher(line);
                    if (m.find()) {
                        this.getPassengers().get(0).setTicketNo(line.substring(m.start(),m.end()));
                    }
                    lineNo--;
                    continue;
                }
            }
            if (lineNo > 0) {
//                System.out.println(String.format("%2d", lineNo) + ":" + line);
            }
            if (lineNo == 3) {
                //          1         2         3         4         5         6
                //0123456789012345678901234567890123456789012345678901234567890123456789
                //                                     10DEC20    KS75LR/1ESHA820
                //                                    22DEC20    KM8M9Q/1ESHA274
                try {
                    Matcher m = Pattern.compile("[0-9]{2}[A-Z]{3}[0-9]{2}").matcher(line);
                    if (m.find()) {
                        String temp = line.substring(m.start(), m.end());
                        this.etdzDate = DateUtil.convertToDate(DateUtil.convertEtermDate(temp));
                    }
                    int idx = line.indexOf("/");
                    this.pnrNo = line.substring(idx-6, idx);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else if (lineNo == 4) {
                //          1         2         3         4         5         6
                //0123456789012345678901234567890123456789012345678901234567890123456789
                //    章进                                                     DEV-1
                this.passengers.get(0).setPsgName(line.substring(3, 30).trim());
            } else if (lineNo == 6) {
                //          1         2         3         4         5         6
                //0123456789012345678901234567890123456789012345678901234567890123456789
                //     KANSAI        KIXHO  1609 M 21JAN 1700  OKMOWDDA         21JAN21JAN2PC

                PnrFlightDto fltDto = new PnrFlightDto();
                this.getFlights().add(fltDto);
                this.segCount = this.getFlights().size();

                fltDto.setSn(1);

                String temp = line.substring(19).trim();
                //          1         2         3         4         5         6
                //0123456789012345678901234567890123456789012345678901234567890123456789
                //KIXHO  1609 M 21JAN 1700  OKMOWDDA         21JAN21JAN2PC

                fltDto.getFlight().setDport(temp.substring(0, 3).trim());
                fltDto.getFlight().setFlightNo(temp.substring(3, 5).trim() + temp.substring(6, 11).trim());
                fltDto.getFlight().setSubclass(temp.substring(12, 13));
                String temp1 = temp.substring(14, 19);
                try {
                    fltDto.getFlight().setDdate(DateUtil.convertToDate(DateUtil.convertEtermDate(temp1, null,this.etdzDate)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                fltDto.getFlight().setDtime(temp.substring(20, 24).trim());

            } else if (lineNo == 7) {
                //          1         2         3         4         5         6
                //0123456789012345678901234567890123456789012345678901234567890123456789
                //     Nan Jing      NKG    VOID
                //NKG    VOID
                String temp = line.substring(19).trim();
                this.getFlights().get(0).getFlight().setAport(temp.substring(0, 3).trim());

                if (!temp.contains("VOID")) {

                    PnrFlightDto fltDto = new PnrFlightDto();
                    this.getFlights().add(fltDto);
                    this.segCount = this.getFlights().size();

                    fltDto.setSn(2);

                    fltDto.getFlight().setDport(temp.substring(0, 3).trim());
                    fltDto.getFlight().setFlightNo(temp.substring(5, 7).trim() + temp.substring(8, 13).trim());
                    fltDto.getFlight().setSubclass(temp.substring(14, 15));
                    String temp1 = temp.substring(16, 21);
                    try {
                        fltDto.getFlight().setDdate(DateUtil.convertToDate(DateUtil.convertEtermDate(temp1)));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    fltDto.getFlight().setDtime(temp.substring(22, 26).trim());
                }
            } else if (lineNo == 9) {
                //          1         2         3         4         5         6
                //0123456789012345678901234567890123456789012345678901234567890123456789
                //     VOID                 VOID

                //HFE  ZH  9900 E 12DEC 0620  OKYTJSF40                  20K
                if (!line.substring(0, 10).trim().contains("VOID")) {
                    String temp = line.substring(19).trim();
                    this.getFlights().get(0).getFlight().setAport(temp.substring(0, 3).trim());

                    if (!temp.contains("VOID")) {
                        PnrFlightDto fltDto = new PnrFlightDto();
                        this.getFlights().add(fltDto);
                        this.segCount = this.getFlights().size();

                        fltDto.setSn(2);


                        fltDto.getFlight().setDport(temp.substring(0, 3).trim());
                        fltDto.getFlight().setFlightNo(temp.substring(5, 7).trim() + temp.substring(8, 13).trim());
                        fltDto.getFlight().setSubclass(temp.substring(14, 15));
                        String temp1 = temp.substring(16, 21);
                        try {
                            fltDto.getFlight().setDdate(DateUtil.convertToDate(DateUtil.convertEtermDate(temp1)));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        fltDto.getFlight().setDtime(temp.substring(22, 26).trim());

                    }
                }
            } else if (lineNo == 10) {
                //          1         2         3         4         5         6
                //0123456789012345678901234567890123456789012345678901234567890123456789
                //HFE  ZH  9900 E 12DEC 0620  OKYTJSF40                  20K
                if (this.getFlights().size() == 2) {
                    String temp = line.trim();
                    temp = temp.substring(temp.indexOf(" ")).trim();
                    this.getFlights().get(1).getFlight().setAport(temp.substring(0, 3).trim());
                }
            } else if (lineNo == 12) {
                //          1         2         3         4         5         6
                //0123456789012345678901234567890123456789012345678901234567890123456789
                //    CNY  720.00A12DEC20HFE ZH SZX720.00CNY720.00END
                this.price = (int)Float.parseFloat(line.substring(7, 14).trim());
                this.parValue = this.price;
            } else if (lineNo == 14) {
                //          1         2         3         4         5         6
                //0123456789012345678901234567890123456789012345678901234567890123456789
                //    CN    50.00
                this.tax = (int)Float.parseFloat(line.substring(7, 15).trim());
            } else if (lineNo == 16) {
                //          1         2         3         4         5         6
                //0123456789012345678901234567890123456789012345678901234567890123456789
                this.total = (int)Float.parseFloat(line.substring(7, 15).trim());
            } else if (lineNo == 17) {
                //          1         2         3         4         5         6         7
                //01234567890123456789012345678901234567890123456789012345678901234567890123456789
                //                                             CNY720.00            18  50.00
                //                                            CNY1770.00           20  100.00
                //                  3711395207                CNY8130.00           100 288.00

                //01234567890123456789012345678901234567890123456789012345678901234567890123456789
                //CNY1770.00           20  100.00
                //CNY720.00            18  50.00
                //CNY8130.00           100 288.00
                String temp = line.substring(40).trim();
//                System.out.println(temp);

                this.parValue = (int)Float.parseFloat(temp.substring(3, 13).trim());
                this.commission = (int)Float.parseFloat(temp.substring(20, 24).trim());
                this.tax = (int)Float.parseFloat(temp.substring(25).trim());
            }
        }

    }

    @SuppressWarnings("SpellCheckingInspection")
    private void caclPnrCtcmCount() {
        if (this.passengers == null) {
            return;
        }
        int ctcmCount0 = 0;
        for(PnrPassengerDto psg: passengers) {
            if (psg.getMobile() != null && psg.getMobile().trim().length() >= 11) {
                ctcmCount0++;
            }
        }

        this.ctcmCount = ctcmCount0;
    }

    /**
     * 计算PNR中的人数
     */
    private void calcPsgCount()  {
        this.totalPsgCount = 0;
        this.psgCount = 0;
        this.adultCount = 0;
        this.childCount = 0;
        this.infantCount = 0;

        if (this.passengers == null) {
            return;
        }

        this.totalPsgCount = getPassengers().size();
        for(PnrPassengerDto psg: getPassengers()) {
            if (psg.getPsgType() == Constant.PSG_TYPE_ADU) {
                this.adultCount++;
                this.psgCount++;
            } else if (psg.getPsgType() == Constant.PSG_TYPE_CHD) {
                this.childCount++;
                this.psgCount++;
            } else if (psg.getPsgType() == Constant.PSG_TYPE_INF) {
                this.infantCount++;
            }

            if (psg.getIdType() == ID_TYPE_NI && psg.getIdNo() != null) {
                final String idNo = psg.getIdNo().trim();
                if (idNo.length() == 18) {
                    try {
                        psg.setBirthday(DateUtil.convertToDate(idNo.substring(6, 14), "yyyyMMdd"));
                        int evenNumber = Integer.parseInt(idNo.substring(16, 17)) % 2;
                        if (evenNumber == 0) {
                            psg.setGender(GENDER_FEMALE); // 女
                        } else if (evenNumber == 1) {
                            psg.setGender(GENDER_MALE); // 男
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                }
            }
        }
    }


    /**
     * @return the pnrNo
     */
    public String getPnrNo() {
        return this.pnrNo == null ? "" : this.pnrNo.trim();
    }

    /**
     * @param pnrNo the pnrNo to set
     */
    public void setPnrNo(String pnrNo) {
        if (pnrNo != null) {
            pnrNo = pnrNo.trim();
        }
        this.pnrNo = pnrNo;
    }

    /**
     * @return the flights
     */
    public List<PnrFlightDto> getFlights() {
        if (flights == null) {
            flights = new ArrayList<>();
        }
        return flights;
    }

    /**
     * @param flights the flights to set
     */
    public void setFlights(List<PnrFlightDto> flights) {
        this.flights = flights;
    }

    /**
     * @return the passengers
     */
    public List<PnrPassengerDto> getPassengers() {
        if (this.passengers == null) {
            this.passengers = new ArrayList<>();
        }
        return passengers;
    }

    /**
     * @param passengers the passengers to set
     */
    public void setPassengers(List<PnrPassengerDto> passengers) {
        this.passengers = passengers;
    }

    public List<PnrTicketDto> getTickets() {
        if (this.tickets == null) {
            this.tickets = new ArrayList<>();
        }
        return tickets;
    }

    public void setTickets(List<PnrTicketDto> tickets) {
        this.tickets = tickets;
    }

    public int getTicketCount() {
        return this.ticketCount;
    }

    public void setTicketCount(int ticketCount) {
        this.ticketCount = ticketCount;
    }


    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return the pnrContentProcessed
     */
    public boolean isPnrContentProcessed() {
        return pnrContentProcessed;
    }

    /**
     * @param pnrContentProcessed the pnrContentProcessed to set
     */
    public void setPnrContentProcessed(boolean pnrContentProcessed) {
        this.pnrContentProcessed = pnrContentProcessed;
    }

    /**
     * @return the enterpriseId
     */
    public int getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * @param enterpriseId the enterpriseId to set
     */
    public void setEnterpriseId(int enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public int getDzStatus() {
        return dzStatus;
    }

    public void setDzStatus(Integer dzStatus) {
        this.dzStatus = dzStatus;
    }

    public String getSegStatus() {
        return segStatus;
    }

    public void setSegStatus(String segStatus) {
        this.segStatus = segStatus;
    }

    public String getCheckDone() {
        return checkDone;
    }

    public void setCheckDone(String checkDone) {
        this.checkDone = checkDone;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getEtermUsername() {
        return this.etermUsername == null ? "" : etermUsername.trim();
    }

    public void setEtermUsername(String etermUsername) {
        this.etermUsername = etermUsername;
    }

    public String getPnrCommitResult() {
        return pnrCommitResult;
    }

    @SuppressWarnings("SpellCheckingInspection")
    public void setPnrCommitResult(String pnrCommitResult) {
        if (pnrCommitResult.length() < 20) {
            return;
        }
        if (pnrCommitResult.contains("UNABLE TO SELL")) {
            return;
        }


        this.pnrCommitResult = pnrCommitResult;

        pnrCommitResult = pnrCommitResult.replace("\r", "\n");


        String[] lines = pnrCommitResult.split("\n");

        int intMode = 1;
        int sn = 0;
        int segCount0 = 0;
        //第一行
        String strLine = lines[0];
        if (strLine.substring(0, 2).compareToIgnoreCase("  ") == 0 || strLine.substring(0, 2).compareToIgnoreCase(" *") == 0) {
            //  "  CA 968  V TH10JUL  MXPPVG HK1   1230 0550+1   \n" +
            //  "  CA 967  H MO07JUL  PVGMXP RR1   0130 0805
            //  "  CZ3901  Z TH04SEP  PEKKMG DK1   0910 1230 \n" +
            //  " *MU8984  N TU01JUL  PVGHKG DK1   0755 1020 \n" +
            //  "  UA3923  E FR19SEP  EWRGSP HK1
            //  "012345678901234567890123456789012345678901234567890123456789
            //  ”        1         2         3         4         5
            //  "  航空公司使用自动出票时限, 请检查PNR   \n" +
            //  "  CZ 313  W FR11JUL  PVGICN HK1 \n" +
            //  "012345678901234567890123456789012345678901234567890123456789
            //  ”        1         2         3         4         5
            intMode = 2;
            String flightNo0 = strLine.substring(2, 4).trim() + strLine.substring(4, 9).trim();
            String subClass = strLine.substring(10, 11);
            String departureDayOfWeek = strLine.substring(12, 14);
            String departureDate = strLine.substring(14, 21).trim();
            String departurePort = strLine.substring(21, 24);
            String arrivalPort = strLine.substring(24, 27);
            String segStatus0 = strLine.substring(28, 30);

            String departureTime = "";
            String arrivalTime = "";
            if (strLine.length() > 34) {
                departureTime = strLine.substring(34, 38);
                arrivalTime = strLine.substring(39, 43);
            }
            PnrFlightDto pnrFlight = new PnrFlightDto();
            pnrFlight.getFlight().setFlightNo(flightNo0);
            pnrFlight.getFlight().setDport(departurePort);
            pnrFlight.getFlight().setAport(arrivalPort);
            pnrFlight.getFlight().setDtime(departureTime);
            pnrFlight.getFlight().setAtime(arrivalTime);
            pnrFlight.getFlight().setSegmentStatus(segStatus0);
            pnrFlight.getFlight().setSubclass(subClass);
            departureDate = DateUtil.convertEtermDate(departureDate, departureDayOfWeek, opTime);
            try {
                pnrFlight.getFlight().setDdate(DateUtil.convertToDate(departureDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String arrivalDate = departureDate;
            if (strLine.length()>45 && strLine.substring(43, 45).compareToIgnoreCase("+1") == 0) {
                try {
                    Date date = DateUtil.convertToDate(departureDate);
                    date = DateUtil.addDays(date, 1);
                    arrivalDate = DateUtil.formatDate(date, DATE_YYYY_MM_DD);
                } catch (Exception ex) {
                    ex.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
            try {
                pnrFlight.getFlight().setAdate(DateUtil.convertToDate(arrivalDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            sn++;
            pnrFlight.setSn(sn);

            getFlights().add(pnrFlight);

            segCount0++;
            this.segCount = segCount0;
        } else {
            this.pnrNo = strLine.substring(0, 6);
        }

        for(int i = 1; i < lines.length; i++) {
            strLine = lines[i];

            if (strLine.length() <= 6) {
                continue;
            }

            if (intMode == 2 && strLine.substring(0, 1).compareToIgnoreCase(" ") != 0) {
                this.pnrNo = strLine.substring(0, 6);
                continue;
            }

            if (strLine.length() < 43 ) {
                continue;
            }

            if (strLine.substring(0, 2).compareToIgnoreCase("  ") != 0) {
                continue;
            }

            if (strLine.substring(9, 10).compareToIgnoreCase(" ") != 0) {
                continue;
            }


            //  "  CA 968  V TH10JUL  MXPPVG HK1   1230 0550+1   \n" +
            //  "  CZ3901  Z TH04SEP  PEKKMG DK1   0910 1230 \n" +
            //  "012345678901234567890123456789012345678901234567890123456789
            //  ”        1         2         3         4         5
            //  "  航空公司使用自动出票时限, 请检查PNR   \n" +
            String flightNo0 = strLine.substring(2, 4).trim() + strLine.substring(4, 9).trim();
            String subClass0 = strLine.substring(10, 11);
            String departureDayOfWeek = strLine.substring(12, 14);
            String departureDate = strLine.substring(14, 21).trim();
            String departurePort = strLine.substring(21, 24);
            String arrivalPort = strLine.substring(24, 27);
            String segStatus0 = strLine.substring(28, 30);

            String departureTime = "";
            String arrivalTime = "";
            if (strLine.length() > 34) {
                departureTime = strLine.substring(34, 38);
                arrivalTime = strLine.substring(39, 43);
            }

            PnrFlightDto pnrFlight = new PnrFlightDto();
            pnrFlight.getFlight().setFlightNo(flightNo0);
            pnrFlight.getFlight().setDport(departurePort);
            pnrFlight.getFlight().setAport(arrivalPort);
            pnrFlight.getFlight().setDtime(departureTime);
            pnrFlight.getFlight().setAtime(arrivalTime);
            pnrFlight.getFlight().setSegmentStatus(segStatus0);
            pnrFlight.getFlight().setSubclass(subClass0);
            departureDate = DateUtil.convertEtermDate(departureDate, departureDayOfWeek, opTime);
            try {
                pnrFlight.getFlight().setDdate(DateUtil.convertToDate(departureDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String arrivalDate = departureDate;
            if (strLine.length()>45 && strLine.substring(43, 45).compareToIgnoreCase("+1") == 0) {
                try {
                    Date date = DateUtil.convertToDate(departureDate);
                    date = DateUtil.addDays(date, 1);
                    arrivalDate = DateUtil.formatDate(date, DATE_YYYY_MM_DD);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            try {
                pnrFlight.getFlight().setAdate(DateUtil.convertToDate(arrivalDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            sn++;
            pnrFlight.setSn(sn);

            getFlights().add(pnrFlight);
            segCount0++;
            this.segCount = segCount0;
        }
        //设置属性值
        getMaxDepartureDate();
        getMinDepartureDate();
    }

    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }



    public Date getEtdzDate() {
        return etdzDate;
    }

    public void setEtdzDate(Date etdzDate) {
        this.etdzDate = etdzDate;
    }

    public String getEtdzOffice() {
        return etdzOffice;
    }

    public void setEtdzOffice(String etdzOffice) {
        this.etdzOffice = etdzOffice;
    }

    public int getXeStatus() {
        return xeStatus;
    }

    public void setXeStatus(Integer xeStatus) {
        this.xeStatus = xeStatus;
    }

    public int getIntXeStatus() {
            return this.xeStatus;
    }

    public Date getMinDepartureDate() {
        if (minDepartureDate == null && this.flights != null && !this.flights.isEmpty()) {
            try {
                minDepartureDate = flights.get(0).getFlight().getDdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return minDepartureDate;
    }

    public void setMinDepartureDate(Date minDepartureDate) {
        this.minDepartureDate = minDepartureDate;
    }

    public Date getMaxDepartureDate() {
        if (maxDepartureDate == null && this.flights != null && !this.flights.isEmpty()) {
            int i = flights.size() - 1;
            try {
                maxDepartureDate = flights.get(i).getFlight().getDdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return maxDepartureDate;
    }

    public void setMaxDepartureDate(Date maxDepartureDate) {
        this.maxDepartureDate = maxDepartureDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getSubClass() {
        return subClass;
    }

    public void setSubClass(String subClass) {
        this.subClass = subClass;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCarrier() {
        if (flightNo == null) {
            return  "";
        } else {
            return flightNo.substring(0,2);
        }

    }

    @SuppressWarnings("SpellCheckingInspection")
    public String getLinkphone() {
        return linkphone == null ? "" : linkphone.trim();
    }

    @SuppressWarnings("SpellCheckingInspection")
    public void setLinkphone(String linkphone) {
        if (linkphone == null) {
            linkphone = "";
        } else if (linkphone.length() > 20) {
            linkphone = linkphone.substring(0, 20);
        }

        this.linkphone = linkphone;
    }

    public String getBookOfficeNo() {
        return Objects.requireNonNullElse(bookOfficeNo, "");
    }

    public String getAuthOfficeNo() {
        if (authOfficeNo == null) {
            return "";
        } else {
            return authOfficeNo;
        }
    }

    public void setAuthOfficeNo(String authOfficeNo) {
        this.authOfficeNo = authOfficeNo;
    }

    public String getCompactPnrDetail() {
        return compactPnrDetail;
    }

    public void setCompactPnrDetail(String compactPnrDetail) {
        this.compactPnrDetail = compactPnrDetail;
    }

    public String getBigPnrNo() {
        return this.bigPnrNo == null ? "" : bigPnrNo;
    }

    public void setBigPnrNo(String bigPnrNo) {
        if (bigPnrNo != null && bigPnrNo.trim().length() > 7) {
            logger.info("bigPnrNo: " + bigPnrNo);
            this.bigPnrNo = bigPnrNo.trim().substring(0, 7);
        } else {
            this.bigPnrNo = bigPnrNo;
        }
    }

    @SuppressWarnings("SpellCheckingInspection")
    public int getCtcmCount() {
        return ctcmCount;
    }

    @SuppressWarnings("SpellCheckingInspection")
    public void setCtcmCount(Integer ctcmCount) {
        this.ctcmCount = ctcmCount;
    }

    /**
     * 检查OFFICE NO的格式
     * @param officeNo office no
     * @return true or false
     */
    public static boolean testOfficeNoFormat(final String officeNo) {
        String regEx="[A-Z]{3}[0-9]{3}";
        return officeNo != null && officeNo.length() == 6 && compile(regEx).matcher(officeNo).find();
    }

    public static boolean testPnrFormat(final String pnr) {
        String regEx="[A-Z0-9]{6}";
        return compile(regEx).matcher(pnr).find();
    }

    public static String calcTktlTime(final String dtime) {
        // 航班的起飞日期为当天， dtime是它的起飞时间
        if (dtime == null || dtime.length() != 4) {
            //当前日期往后一小时
            Date curTime = DateUtil.getCurDateTime();
            curTime = DateUtil.addMinutes(curTime, 60);
            return DateUtil.formatDate(curTime, "HHmm");
        } else {

            int intDtime = Integer.parseInt(dtime) - 100;

            return String.format("%04d", intDtime);
        }
    }

    public int getRtResultStatus() {
        return rtResultStatus;
    }

    public void setRtResultStatus(int rtResultStatus) {
        this.rtResultStatus = rtResultStatus;
    }

    public Integer getCtctCount() {
        return ctctCount;
    }

    public void setCtctCount(Integer ctctCount) {
        this.ctctCount = ctctCount;
    }

    public int getParValue() {
        return parValue;
    }

    public void setParValue(int parValue) {
        this.parValue = parValue;
    }

    public Integer getMonitoring() {
        return monitoring;
    }

    public void setMonitoring(Integer monitoring) {
        this.monitoring = monitoring;
    }

    /**
     * 获取航班信息所在行的序号，多个航班的序号用斜线分隔
     * @return 返回PNR中航班号前的编号
     */
    public String getFlightItemNo() {
        String regEx="^[ 0-9]{2}.[ *]{2}[A-Z0-9]+";
        return getItemNo(regEx);
    }

    /**
     * 获取OSI CTCT CTCM项目的序号
     * @return 返回PNR中CTCT/CTCM项目前的编号
     */
    @SuppressWarnings("SpellCheckingInspection")
    public String getCtctCtcmItemNo() {
        String regEx="^[ 0-9]{2}.OSI [A-Z]{2} CTC[T|M]";
        return getItemNo(regEx);
    }

    /**
     * 获取携程预订编码的乘机人联系组序号
     * @return 获取PNR中的项目编号
     */
    public String getCtripCtItemNo() {
        String regEx = "^[ 0-9]{2}.(95010|021-51069999X)";
        return getItemNo(regEx);
    }

    public String getTktlItemNo() {
        String regEx = "^[ 0-9]{2}.TL/[0-9]{4}";
        return getItemNo(regEx);
    }

    public String getAllNeedXeItemNos() {
        // 在PNR中会按照先后顺序显示，有可能没有（乘机人或者航班太多的情况下，当前屏显示不下）
        // CT 组
        String xeItemNos = getCtripCtItemNo();

        //TK TL组
        String newItemNo = getTktlItemNo();
        if (newItemNo != null) {
            xeItemNos = xeItemNos == null ? newItemNo : (xeItemNos + "/" + newItemNo);
        }

        // OSI 组
        newItemNo = getCtctCtcmItemNo();
        if (newItemNo != null) {
            xeItemNos = xeItemNos == null ? newItemNo : (xeItemNos + "/" + newItemNo);
        }

        return xeItemNos;
    }

    /**
     * 根据传入的正则表达式查找项目序号
     *
     */
    private String getItemNo(final String regEx) {
        String itemNo = null;

        Pattern p = compile(regEx, Pattern.MULTILINE);

        Matcher m = p.matcher(this.pnrDetail);
        while (m.find()) {
            String temp = this.pnrDetail.substring(m.start(), m.end());

            temp = temp.substring(0, 2).trim();

            if (temp.length() > 0) {
                itemNo = itemNo == null ? temp : itemNo + "/" + temp;
            }
        }

        return itemNo;
    }


    /**
     * 获取该乘机人对应的票号
     * @param psgName 乘机人姓名
     * @return 获取
     */
    public String getTicketNoByName(final String psgName) {
        int psgNo = 0;
        for (PnrPassengerDto psg: this.passengers) {
            if (psg.getPsgName().equalsIgnoreCase(psgName)) {
                psgNo = psg.getPsgNo();
                break;
            }
        }

        if (psgNo > 0) {
            for(PnrTicketDto ticket: this.tickets) {
                if (ticket.getPassengerIndex() == psgNo) {
                    return ticket.getTicketNo();
                }
            }
        }

        return null;
    }

    /**
     * 根据乘机人的ID获取对应的编码
     * @param idNo 证件号dto
     * @return 获取
     */
    public String getTicketNoByIdNo(final String idNo) {
        int psgNo = 0;
        for (PnrPassengerDto psg: this.passengers) {
            if (idNo.equalsIgnoreCase(psg.getIdNo())) {
                psgNo = psg.getPsgNo();
                break;
            }
        }

        if (psgNo > 0) {
            for(PnrTicketDto ticket: this.tickets) {
                if (ticket.getPassengerIndex() == psgNo) {
                    return ticket.getTicketNo();
                }
            }
        }

        return null;
    }

    /**
     * 从字符串中获取passenger index
     * 字符串内容如下：P1,P2
     *
     */
    @SuppressWarnings("SpellCheckingInspection")
    private int parsePassengerIndex(final String pstring) {
        String regEx="[P][0-9]+[/]?[0-9]?";
        boolean b = compile(regEx).matcher(pstring).find();
        if (!b) {
            return -1;
        }

        return Integer.parseInt(pstring.substring(1));
    }

    public boolean isEt() {
        return et;
    }

    /**
     * 第一程或单程的出发机场
     */
    public String getDport() {
        return dport;
    }

    public void setDport(String dport) {
        this.dport = dport;
    }

    /**
     * 第一程或单程的到达机场
     *  @return 获取
     */
    public String getAport() {
        return aport;
    }

    public void setAport(String aport) {
        this.aport = aport;
    }

    /**
     * 第一程或单程的出发日期
     *  @return 获取
     */
    public Date getDdate() {
        return ddate;
    }

    public void setDdate(Date ddate) {
        this.ddate = ddate;
    }

    /**
     * 第一程或单程的出发时间
     */
    public String getDtime() {
        return dtime;
    }

    public void setDtime(String dtime) {
        this.dtime = dtime;
    }

    public Double getCommRate() {
        return commRate;
    }

    public void setCommRate(Double commRate) {
        this.commRate = commRate;
    }

    public Double getInfCommRate() {
        return infCommRate;
    }

    public void setInfCommRate(Double infCommRate) {
        this.infCommRate = infCommRate;
    }

    public Integer getInfTax() {
        return infTax;
    }

    public void setInfTax(Integer infTax) {
        this.infTax = infTax;
    }

    public int getInfParValue() {
        return infParValue;
    }

    public void setInfParValue(Integer infParValue) {
        this.infParValue = infParValue;
    }

    public Integer getInfPrice() {
        return infPrice;
    }

    public void setInfPrice(Integer infPrice) {
        this.infPrice = infPrice;
    }

    public Double getChdCommRate() {
        return chdCommRate;
    }

    public void setChdCommRate(Double chdCommRate) {
        this.chdCommRate = chdCommRate;
    }

    public int getChdTax() {
        return chdTax;
    }

    public void setChdTax(Integer chdTax) {
        this.chdTax = chdTax;
    }

    public int getChdParValue() {
        return chdParValue;
    }

    public void setChdParValue(Integer chdParValue) {
        this.chdParValue = chdParValue;
    }

    public int getChdPrice() {
        return chdPrice;
    }

    public void setChdPrice(int chdPrice) {
        this.chdPrice = chdPrice;
    }

    public int getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(Integer adultCount) {
        this.adultCount = adultCount;
    }

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(Integer childCount) {
        this.childCount = childCount;
    }

    public int getInfantCount() {
        return infantCount;
    }

    public void setInfantCount(Integer infantCount) {
        this.infantCount = infantCount;
    }

    public int getType3() {
        return type3;
    }

    public void setType3(int type3) {
        this.type3 = type3;
    }

    public int getTotalPsgCount() {
        return totalPsgCount;
    }

    public void setTotalPsgCount(int totalPsgCount) {
        this.totalPsgCount = totalPsgCount;
    }

    /**
     * 查找表示乘机人序号的字符串的位置
     */
    private int findPsgNoIndex(final String line) {
        final String regEx = "/P[1-9][0-9]{0,2}";
        Matcher m = compile(regEx).matcher(line);
        if (m.find()) {
            return m.start();
        } else {
            return -1;
        }
    }

    public String getKeyCustomerNo() {
        return keyCustomerNo;
    }

    public void setKeyCustomerNo(String keyCustomerNo) {
        this.keyCustomerNo = keyCustomerNo;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTktlDate() {
        return tktlDate;
    }

    public String getTktlTime() {
        return tktlTime;
    }

    public int getCommission() {
        return commission;
    }

    public void setCommission(int commission) {
        this.commission = commission;
    }

    public String getRtktDetail() {
        return rtktDetail;
    }

    public void setRtktDetail(String rtktDetail) {
        this.rtktDetail = rtktDetail;
        this.pnrDetail = rtktDetail;
        this.processRtktDetail();
        this.calcPsgCount();
    }
}

