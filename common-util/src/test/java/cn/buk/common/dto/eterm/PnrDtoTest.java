package cn.buk.common.dto.eterm;

import cn.buk.common.Constant;
import cn.buk.common.flight.dto.FlightInfoDto;
import cn.buk.common.util.DateUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static cn.buk.common.Constant.DATE_YYYY_MM_DD;
import static cn.buk.tms.common.constant.TmsConstant.GENDER_MALE;
import static cn.buk.tms.common.constant.TmsConstant.ID_TYPE_NI;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * 原来保存在实体PNR中的代码全部移到此处
 */
@SuppressWarnings("ALL")
class PnrDtoTest {

    private PnrDto pnrDto;
    private Date opTime;

    @BeforeEach
    void setUp() throws Exception {
        pnrDto = new PnrDto();
        opTime = DateUtil.convertToDateTime("2014-06-10 10:08:04");
    }

    @Test
    void test() {
        String aa = "zyxwvutsrqponmlkjihgfedcba0";
        StringBuffer sb = new StringBuffer(aa);
        System.out.println(sb.reverse().toString());
    }

    @Test
    void testPnrCommitResult1() {
        String pnrDetail = "JTD1KW -EOT SUCCESSFUL, BUT ASR UNUSED FOR 1 OR MORE SEGMENTS   \n" +
                "  CZ3901  Z TH04SEP  PEKKMG DK1   0910 1230 \n" +
                "  航空公司使用自动出票时限, 请检查PNR   \n" +
                " ";

        pnrDto.setOpTime(opTime);
        pnrDto.setPnrCommitResult(pnrDetail);
        assertEquals("JTD1KW", pnrDto.getPnrNo());
        assertEquals(1, pnrDto.getSegCount());
    }

    @Test
    void testPnrCommitResult2() {
        String pnrDetail = "  CZ6210  V SA13SEP  PVGHRB HK3   1400 1705 \n" +
                "  CZ6251  Z FR19SEP  HRBPVG HK3   1510 1805 \n" +
                "JN38QH -   航空公司使用自动出票时限, 请检查PNR  \n" +
                "  ";

        pnrDto.setOpTime(opTime);
        pnrDto.setPnrCommitResult(pnrDetail);
        assertEquals("JN38QH", pnrDto.getPnrNo());
        assertEquals(2, pnrDto.getSegCount());
    }

    @Test
    void testPnrCommitResult3() {
        String pnrDetail = "  CA 967  H MO07JUL  PVGMXP RR1   0130 0805 \n" +
                "  CA 968  V TH10JUL  MXPPVG HK1   1230 0550+1   \n" +
                "HPETK1  \n" +
                "  *** 预订酒店指令HC, 详情   HC:HELP   ***   ";

        pnrDto.setOpTime(opTime);
        pnrDto.setPnrCommitResult(pnrDetail);
        assertEquals("HPETK1", pnrDto.getPnrNo());
        assertEquals(2, pnrDto.getSegCount());
    }


    @Test
    void testPnrCommitResult4() {
        String pnrDetail = "  NX 110  L FR27JUN  MFMSHA DK1   1040 1255 \n" +
                "HMC4SH  \n" +
                "  *** 预订酒店指令HC, 详情   HC:HELP   ***  ";

        pnrDto.setOpTime(opTime);
        pnrDto.setPnrCommitResult(pnrDetail);
        assertEquals("HMC4SH", pnrDto.getPnrNo());
        assertEquals(1, pnrDto.getSegCount());
    }

    @Test
    void testPnrCommitResult5() {
        String pnrDetail = " *MU8984  N TU01JUL  PVGHKG DK1   0755 1020 \n" +
                "HT4B46 -EOT SUCCESSFUL, BUT ASR UNUSED FOR 1 OR MORE SEGMENTS   \n" +
                "  *** 预订酒店指令HC, 详情   HC:HELP   ***";

        pnrDto.setOpTime(opTime);
        pnrDto.setPnrCommitResult(pnrDetail);
        assertEquals("HT4B46", pnrDto.getPnrNo());
        assertEquals(1, pnrDto.getSegCount());
    }

    @Test
    void testPnrCommitResult6() {
        String pnrDetail = "  CZ 313  W FR11JUL  PVGICN HK1 \n" +
                "  CZ 314  L TU15JUL  ICNPVG HK1   1200 1300 \n" +
                "HRF03W  \n" +
                "  *** 预订酒店指令HC, 详情   HC:HELP   ***";

        pnrDto.setOpTime(opTime);
        pnrDto.setPnrCommitResult(pnrDetail);
        assertEquals("HRF03W", pnrDto.getPnrNo());
        assertEquals(2, pnrDto.getSegCount());
    }

    @Test
    void testPnrCommitResult7() {
        String pnrDetail = "  CZ3524  R TH28AUG  SHACAN RR2   1145 1410 \n" +
                "  CZ 387  U TH28AUG  CANCGK RR2   1700 2050 \n" +
                "  CZ 388  M SA13SEP  CGKCAN HK2   0905 1505 \n" +
                "  CZ3547  R SA13SEP  CANSHA HK2   1900 2120 \n" +
                "HRZP98  ";

        try {
            opTime = DateUtil.convertToDateTime("2014-09-04 12:50:35");
        } catch (ParseException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        pnrDto.setOpTime(opTime);
        pnrDto.setPnrCommitResult(pnrDetail);
        assertEquals("HRZP98", pnrDto.getPnrNo());
        assertEquals(4, pnrDto.getSegCount());
        assertEquals("2014-09-13", DateUtil.formatDate(pnrDto.getMaxDepartureDate(), "yyyy-MM-dd"));
    }

    @Test
    void testPnrCommitResult8() {
        String pnrDetail = "  QF 130  M MO20OCT  PVGSYD DK2   1955 0930+1   \n" +
                "  QF 129  S SU16AUG  SYDPVG DK2   0935 1830 \n" +
                "JEBD8G  \n" +
                "  \n";

        try {
            opTime = DateUtil.convertToDateTime("2014-09-26 17:32:49");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        pnrDto.setOpTime(opTime);
        pnrDto.setPnrCommitResult(pnrDetail);
        assertEquals("JEBD8G", pnrDto.getPnrNo());
        assertEquals(2, pnrDto.getSegCount());
        assertEquals("2014-10-20", DateUtil.formatDate(pnrDto.getMinDepartureDate(), "yyyy-MM-dd"));
        assertEquals("2015-08-16", DateUtil.formatDate(pnrDto.getMaxDepartureDate(), "yyyy-MM-dd"));
    }

    @Test
    void testPnrDetail_OneManOneFlight() {
        String pnrDetail = " 1.杨金鹤 JV4WMT\n" +
                " 2.  HO1218 V   TH30JUN  XIYSHA HK1   1130 1340          E T3T2 \n" +
                " 3.SHA/T SHA/T18917808789/SHANGHAI YINGHANG TICKETING AGENT LIMITED COMPANY/LI      SHIRONG ABCDEFG \n" +
                " 4.021-51069999X454360/P1   \n" +
                " 5.0513-89186666/P1 \n" +
                " 6.BOOK YH41 0629 0852  \n" +
                " 7.TL/1800/29JUN/SHA360 \n" +
                " 8.SSR FOID HO HK1 NI32010719610222135X/P1  \n" +
                " 9.SSR ADTK 1E BY SHA29JUN16/0952 OR CXL HO1218 V30JUN  \n" +
                "10.OSI HO CTCT18917890623   \n" +
                "11.RMK CA/ME4KMH        ";

        pnrDto.setPnrDetail(pnrDetail);
        assertEquals("JV4WMT", pnrDto.getPnrNo());
        assertEquals("ME4KMH", pnrDto.getBigPnrNo());
        assertEquals(1, pnrDto.getSegCount());
        assertEquals("杨金鹤", pnrDto.getPassengers().get(0).getPsgName());
        assertEquals("32010719610222135X", pnrDto.getPassengers().get(0).getIdNo());
        assertEquals(1, pnrDto.getPassengers().get(0).getIdType());

        assertEquals("2022-06-30", DateUtil.formatDate(pnrDto.getDdate(), "yyyy-MM-dd"));

        PnrFlightDto flt = pnrDto.getFlights().get(0);
        assertEquals("HO1218", flt.getFlight().getFlightNo());
        assertEquals("XIY", flt.getFlight().getDport());
        assertEquals("SHA", flt.getFlight().getAport());
        assertEquals("V", flt.getFlight().getSubclass());
        assertEquals("1130", flt.getFlight().getDtime());
        assertEquals("1340", flt.getFlight().getAtime());
        assertEquals("2022-06-30", flt.getFlight().getDdateFormatted());
        assertEquals("HK", flt.getFlight().getSegmentStatus());
    }

    @Test
    void testPnrDetail_OneManOneCodeSharedFlight() {
        String pnrDetail = " 1.杨金彪 HMLD5R\n" +
                " 2. *ZH1787 Y   TU05JUL  WEHHRB HK1   1035 1225          E      OP-CA1787   \n" +
                " 3.SHA/T SHA/T-021-34064880-454360/SHA HUA CHENG SOUTHWEST TRAVEL/SHAO/JI HONG      ABCDEFG \n" +
                " 4.021-51069999X454360/P1   \n" +
                " 5.0513-89186666/P1 \n" +
                " 6.TL/1200/03JUL/SHA717 \n" +
                " 7.SSR FOID \n" +
                " 8.SSR ADTK 1E BY SHA29JUN16/1000 OR CXL ZH1787 Y05JUL  \n" +
                " 9.OSI ZH CTCT18321656710   \n" +
                "10.RMK CA/PBM031\n" +
                "11.RMK TJ AUTH SHA360/T ";

        pnrDto.setPnrDetail(pnrDetail);
        assertEquals("HMLD5R", pnrDto.getPnrNo());
        assertEquals("PBM031", pnrDto.getBigPnrNo());
        assertEquals(1, pnrDto.getSegCount());
        assertEquals("杨金彪", pnrDto.getPassengers().get(0).getPsgName());
        assertEquals(null, pnrDto.getPassengers().get(0).getIdNo());
        assertEquals(0, pnrDto.getPassengers().get(0).getIdType());

        assertEquals("2022-07-05", DateUtil.formatDate(pnrDto.getDdate(), "yyyy-MM-dd"));

        PnrFlightDto flt = pnrDto.getFlights().get(0);
        assertEquals("ZH1787", flt.getFlight().getFlightNo());
        assertEquals("ZH1787", pnrDto.getFlightNo());
        assertEquals("WEH", flt.getFlight().getDport());
        assertEquals("HRB", flt.getFlight().getAport());
        assertEquals("Y", flt.getFlight().getSubclass());
        assertEquals("1035", flt.getFlight().getDtime());
        assertEquals("1225", flt.getFlight().getAtime());
        assertEquals("2022-07-05", flt.getFlight().getDdateFormatted());
        assertEquals("HK", flt.getFlight().getSegmentStatus());
    }

    @Test
    void testPnrDetail_ThreeMenOneCodeSharedFlight() {
        String pnrDetail = " 1.谷雪松 2.侯德萍 3.孙月霞 JMJXYT  \n" +
                " 4. *ZH1605 Y   WE06JUL  PEKDLC HK3   1025 1155          E T3-- OP-CA1605   \n" +
                " 5.SHA/T SHA/T-021-34064880-454360/SHA HUA CHENG SOUTHWEST TRAVEL/SHAO/JI HONG      ABCDEFG \n" +
                " 6.021-51069999X454360/P1   \n" +
                " 7.0513-89186666/P1 \n" +
                " 8.021-51069999X454360/P2   \n" +
                " 9.0513-89186666/P2 \n" +
                "10.021-51069999X454360/P3   \n" +
                "11.0513-89186666/P3 \n" +
                "12.TL/1200/04JUL/SHA717 \n" +
                "13.SSR FOID     ";

        pnrDto.setPnrDetail(pnrDetail);
        assertEquals("JMJXYT", pnrDto.getPnrNo());
        assertEquals(1, pnrDto.getSegCount());
        assertEquals("谷雪松", pnrDto.getPassengers().get(0).getPsgName());
        assertEquals("侯德萍", pnrDto.getPassengers().get(1).getPsgName());
        assertEquals("孙月霞", pnrDto.getPassengers().get(2).getPsgName());
        assertEquals(null, pnrDto.getPassengers().get(0).getIdNo());
        assertEquals(0, pnrDto.getPassengers().get(0).getIdType());

        assertEquals("2022-07-06", DateUtil.formatDate(pnrDto.getDdate(), "yyyy-MM-dd"));

        PnrFlightDto flt = pnrDto.getFlights().get(0);
        assertEquals("ZH1605", flt.getFlight().getFlightNo());
        assertEquals("PEK", flt.getFlight().getDport());
        assertEquals("DLC", flt.getFlight().getAport());
        assertEquals("Y", flt.getFlight().getSubclass());
        assertEquals("1025", flt.getFlight().getDtime());
        assertEquals("1155", flt.getFlight().getAtime());
        assertEquals("2022-07-06", flt.getFlight().getDdateFormatted());
        assertEquals("HK", flt.getFlight().getSegmentStatus());
    }

    @Test
    void testPnrDetail_WithChildPsg() {
        String pnrDetail = " SHA205 SCHEDULE CHG  (  0002  )  (  0002  )\n" +
                "MARRIED SEGMENT EXIST IN THE PNR\n" +
                "  **ELECTRONIC TICKET PNR** \n" +
                " 1.PU/LINLIN 2.ZHANG/ZHIHAN CHD KNWD5R  \n" +
                " 3.  CX881  S2  TU23AUG16LAXHKG HK2   0055 0645+1        E  B 1 \n" +
                " 4.  CX368  S   WE24AUG  HKGPVG UN2   0835 1110          E  1 2 S   \n" +
                " 5.  CX368  S2  WE24AUG  HKGPVG TK2   0915 1144          E  1 2 S   \n" +
                " 6.SHA/T SHA/T 0510-82722200/CHINA COMFORT WUXI TRAVEL SERVICE/CHEN RU ABCDEFG  \n" +
                " 7.BOOK CZWS 0702 1036  \n" +
                " 8.T\n" +
                " 9.SSR SEAT KA HN1 PVGHKG 803 S10AUG 44AN/P2\n" +
                "10.SSR SEAT KA HN1 PVGHKG 803 S10AUG 44CN/P1                                   +\n" +
                "11.SSR SEAT CX HN1 HKGLAX 882 S10AUG 66BN/P1                                   -\n" +
                "12.SSR SEAT CX HN1 HKGLAX 882 S10AUG 66AN/P2\n" +
                "13.SSR SEAT CX HN1 HKGPVG 368 S24AUG 75CN/P1\n" +
                "14.SSR SEAT CX HN1 HKGPVG 368 S24AUG 75AN/P2\n" +
                "15.SSR SEAT CX HN1 LAXHKG 881 S23AUG 64KN/P2\n" +
                "16.SSR SEAT CX HN1 LAXHKG 881 S23AUG 64JN/P1\n" +
                "17.SSR OTHS 1E DUPE BKG FOUND PLS HV PAX CANX DUPE OR BKG WILL BE AUTO CANX ON  \n" +
                "    05JUL16 \n" +
                "18.SSR OTHS 1E PLS NTE PAX NOT ELIGIBLE FOR ASR X PLS MAKE SEAT REQ UPON CKIN   \n" +
                "    X TKS   \n" +
                "19.SSR OTHS 1E PAX NOT ELIGIBLE FOR ASR X PLS MAKE SEAT REQ UPON CKIN   \n" +
                "                                                                               +\n" +
                "20.SSR ADTK 1E ADV TKT NBR TO CX/KA BY 11JUL 0300 GMT OR SUBJECT TO CANCEL -   -\n" +
                "     DEADLINE REVISED DUE BOOKING ADJUSTMENT\n" +
                "21.SSR ADTK 1E ADV TKT NBR TO CX/KA BY 11JUL 1100 GMT OR SUBJECT TO CANCEL  \n" +
                "22.SSR TKNE KA HK1 PVGHKG 803 S10AUG 1609563648178/1/P2 \n" +
                "23.SSR TKNE KA HK1 PVGHKG 803 S10AUG 1609563648176/1/P1 \n" +
                "24.SSR TKNE CX HK1 HKGLAX 882 S10AUG 1609563648176/2/P1 \n" +
                "25.SSR TKNE CX HK1 LAXHKG 881 S23AUG 1609563648176/3/P1 \n" +
                "26.SSR TKNE CX HK1 HKGPVG 368 S24AUG 1609563648176/4/P1 \n" +
                "27.SSR TKNE CX HK1 HKGLAX 882 S10AUG 1609563648178/2/P2 \n" +
                "28.SSR TKNE CX HK1 LAXHKG 881 S23AUG 1609563648178/3/P2 \n" +
                "29.SSR TKNE CX HK1 HKGPVG 368 S24AUG 1609563648178/4/P2 \n" +
                "30.SSR DOCS CX HK1 P/CN/E40507515/CN/06APR07/M/08DEC19/ZHANG/ZHIHAN/P2         +\n" +
                "31.SSR DOCS KA HK1 P/CN/E40507515/CN/06APR07/M/08DEC19/ZHANG/ZHIHAN/P2         -\n" +
                "32.SSR DOCS CX HK1 P/CN/E38412012/CN/12MAR86/F/29OCT24/PU/LINLIN/P1 \n" +
                "33.SSR DOCS KA HK1 P/CN/E38412012/CN/12MAR86/F/29OCT24/PU/LINLIN/P1 \n" +
                "34.OSI CX CTCT15961219496   \n" +
                "35.OSI KA CTCT13951560989   \n" +
                "36.RMK  \n" +
                "37.RMK TJ AUTH SHA518   \n" +
                "38.RMK TJ AUTH SHA255   \n" +
                "39.RMK 1A/Y2CK7C\n" +
                "40.TN/160-9563648176/P1 \n" +
                "41.TN/160-9563648178/P2 \n" +
                "42.FP/                                                                         +\n" +
                "43.SHA205                                                                      -";

        pnrDto.setPnrDetail(pnrDetail);
        assertEquals("KNWD5R", pnrDto.getPnrNo());
        assertEquals(3, pnrDto.getSegCount());
        assertEquals("CZWS", pnrDto.getEtermUsername());

        assertEquals("3/4/5", pnrDto.getFlightItemNo());


        assertEquals("SHA205", pnrDto.getBookOfficeNo());
        assertEquals("SHA518,SHA255", pnrDto.getAuthOfficeNo());

        assertEquals("PU/LINLIN", pnrDto.getPassengers().get(0).getPsgName());
        assertEquals("ZHANG/ZHIHAN", pnrDto.getPassengers().get(1).getPsgName());
        assertEquals(1, pnrDto.getPassengers().get(1).getPsgType());

        assertEquals(2, pnrDto.getPsgCount());
        assertEquals(0, pnrDto.getCtcmCount());

        assertEquals(1, pnrDto.getDzStatus());

        assertEquals("2016-08-23", DateUtil.formatDate(pnrDto.getDdate(), "yyyy-MM-dd"));

        PnrFlightDto flt = pnrDto.getFlights().get(0);
        assertEquals("HK", flt.getFlight().getSegmentStatus());
        flt = pnrDto.getFlights().get(1);
        assertEquals("UN", flt.getFlight().getSegmentStatus());
        flt = pnrDto.getFlights().get(2);
        assertEquals("TK", flt.getFlight().getSegmentStatus());
    }

    @Test
    void testPnrDetail_MultiPassenger() {
        String pnrDetail = " SHA205 SCHEDULE CHG  (  0000  )  (  0000  )\n" +
                " 1.HU/YUNJIE MR 2.LI/HAIXIA MS 3.LIU/JINYI MR 4.RONG/YANE MS 5.WANG/CHIHOU MR   \n" +
                " 6.WANG/CHINGHSIUNG MR 7.WANG/CHINGKUANG CHD 8.YU/WENTAO MR 9.ZHU/QIANQIAN MS   \n" +
                "   JFM4T5   \n" +
                "10.  ET685  O   MO26SEP  PVGADD HX9   0015 0615      SEAME  2 2 S   \n" +
                "11.  ET809  O   MO26SEP  ADDJNB HX9   0840 1303      SEAME  2 A \n" +
                "12.  ET808  O   FR07OCT  JNBADD HX9   1409 2024          E  A 2 \n" +
                "13.  ET684  O   FR07OCT  ADDPVG HX9   2325 1540+1    SEAME  2 2 \n" +
                "14.SHA/T SHA/T 0510-82722200/CHINA COMFORT WUXI TRAVEL SERVICE/CHEN RU ABCDEFG  \n" +
                "15.BOOK SZSD3 0916 1547 \n" +
                "16.TL/2215/25SEP/SHA205 \n" +
                "17.SSR OTHS 1E PLZ UPDATE ITIN BY XXL ALL HX SEGMENTS                          +\n" +
                "18.SSR OTHS 1E PLS BOOK VIA ET LOCAL OFFICE                                    -\n" +
                "19.SSR OTHS 1E HX DELETE HX SEGS FROM PNR TO KEEP RES IN SYNCH  \n" +
                "20.SSR OTHS 1E HX CANCELED DUE TO SYSTEM OR PASSENGER ACTION\n" +
                "21.SSR OTHS 1E PLZ UPDATE ITIN BY XXL ALL HX SEGMENTS   \n" +
                "22.SSR DOCS ET HK1 P/TW/309285895/TW/05JUN70/M/20MAY24/WANG/CHI HOU/P5  \n" +
                "23.SSR DOCS ET HK1 P/CN/E79005976/CN/02DEC90/M/07APR26/RONG/YANE/P4 \n" +
                "24.SSR DOCS ET HK1 P/CN/G44034739/CN/19JUL83/M/03NOV20/LIU/JINYI/P3 \n" +
                "25.SSR DOCS ET HK1 P/CN/G22347523/CN/05MAY75/F/18APR17/LI/HAIXIA/P2 \n" +
                "26.SSR DOCS ET HK1 P/CN/E38302702/CN/16NOV88/M/29OCT24/HU/YUNJIE/P1 \n" +
                "27.OSI ET CTCT13301546619   \n" +
                "28.RMK TJ AUTH SHA697   \n" +
                "29.RMK ET/DMJUMS                                                               +\n" +
                "30.SHA205                                                                      -";

        pnrDto.setPnrDetail(pnrDetail);
        assertEquals("JFM4T5", pnrDto.getPnrNo());
        assertEquals(9, pnrDto.getPsgCount());
        assertEquals(4, pnrDto.getSegCount());
        assertEquals("10/11/12/13", pnrDto.getFlightItemNo());
        assertEquals("HU/YUNJIE", pnrDto.getPassengers().get(0).getPsgName());
        assertEquals(Constant.GENDER_MALE, pnrDto.getPassengers().get(0).getGender());
        assertEquals("LI/HAIXIA", pnrDto.getPassengers().get(1).getPsgName());
        assertEquals(Constant.GENDER_FEMALE, pnrDto.getPassengers().get(1).getGender());
        assertEquals("LIU/JINYI", pnrDto.getPassengers().get(2).getPsgName());
        assertEquals("RONG/YANE", pnrDto.getPassengers().get(3).getPsgName());
        assertEquals("WANG/CHIHOU", pnrDto.getPassengers().get(4).getPsgName());
        assertEquals("WANG/CHINGHSIUNG", pnrDto.getPassengers().get(5).getPsgName());
        assertEquals("WANG/CHINGKUANG", pnrDto.getPassengers().get(6).getPsgName());
        assertEquals("YU/WENTAO", pnrDto.getPassengers().get(7).getPsgName());
        assertEquals("ZHU/QIANQIAN", pnrDto.getPassengers().get(8).getPsgName());
        PnrFlightDto flt = pnrDto.getFlights().get(0);
        assertEquals("HX", flt.getFlight().getSegmentStatus());
        flt = pnrDto.getFlights().get(1);
        assertEquals("HX", flt.getFlight().getSegmentStatus());
        flt = pnrDto.getFlights().get(2);
        assertEquals("HX", flt.getFlight().getSegmentStatus());
    }

    @Test
    void testPnrDetail_RtAndPat() {
        String pnrDetail = "\u0010rtJZNR6Y                                                                       \n" +
                " 1.青威兴 JZNR6Y                                                                \n" +
                " 2.  CZ6609 Y   SU18SEP  YNJSZX HK1   0930 1545          E                      \n" +
                " 3.SZX/T SZX/T-4006940069/SZX TENG BANG BUSINESS SERVICES CO.,LTD/PENG YU MEI   \n" +
                "    ABCDEFG                                                                     \n" +
                " 4.0755-82150101                                                                \n" +
                " 5.BY OPT GSSHF05 2016/09/16 1431A                                              \n" +
                " 6.TL/1800/16SEP/SZX195                                                         \n" +
                " 7.SSR FOID CZ HK1 NI450121198706303917/P1                                      \n" +
                " 8.SSR ADTK 1E BY SZX16SEP16/1631 OR CXL CZ BOOKING                             \n" +
                " 9.OSI CZ CTCT13590114767                                                       \n" +
                "10.RMK CA/PG203R                                                                \n" +
                "11.RMK TJ AUTH SHA777                                                          +\n" +
                "\u0010                                                                               \n" +
                "                                                                                \n" +
                "                                                                                \n" +
                "\u0010pat a                                                                          \n" +
                ">PAT:A                                                                          \n" +
                "01 Y FARE:CNY2600.00 TAX:CNY50.00 YQ:TEXEMPTYQ  TOTAL:2650.00                   \n" +
                "\u0010SFC:01   \u0010SFN:01   ";

        pnrDto.setPnrDetail(pnrDetail);
        assertEquals("JZNR6Y", pnrDto.getPnrNo());
        assertEquals("PG203R", pnrDto.getBigPnrNo());
        assertEquals(1, pnrDto.getPsgCount());
        assertEquals(1, pnrDto.getSegCount());
        assertEquals("青威兴", pnrDto.getPassengers().get(0).getPsgName());

        assertEquals(2600, pnrDto.getPrice());
        assertEquals(50, pnrDto.getTax());
    }

    @Test
    void testPnrDetail_RtAndPat_PAT_CHD() {
        String pnrDetail = "  **ELECTRONIC TICKET PNR** \n" +
                " 1.穆雨辰CHD JTVLV4 \n" +
                " 2.  MU2798 B   SA02FEB  ZHANKG RR1   2025 2240          E --T2 \n" +
                " 3.SHA/T SHA/T021-6276220418917099670/SHANGHAI SHANGYOU INTERNATIONAL TRAVEL\n" +
                "     SERVICE CO., ABCDEFG   \n" +
                " 4.216 190118 1025  \n" +
                " 5.T\n" +
                " 6.SSR FOID MU HK1 NI340202201302024511/P1  \n" +
                " 7.SSR ADTK 1E BY SHA20JAN19/1025 OR CXL MU2798 B02FEB  \n" +
                " 8.SSR TKNE MU HK1 ZHANKG 2798 B02FEB 7813541793275/1/P1\n" +
                " 9.SSR CHLD MU HK1 02FEB13/P1   \n" +
                "10.OSI MU CTCT18917093601                                                      +\n" +
                "\u007Fpat a*ch                                                                       \n" +
                ">PAT:A*CH   \n" +
                "01 YCH FARE:CNY790.00 TAX:TEXEMPTCN YQ:TEXEMPTYQ  TOTAL:790.00  \n" +
                "\u007FSFC:01   \u007FSFN:01   \n";

        pnrDto.setPnrDetail(pnrDetail);
        assertEquals("JTVLV4", pnrDto.getPnrNo());
        assertEquals("", pnrDto.getBigPnrNo());
        assertEquals(1, pnrDto.getPsgCount());
        assertEquals(1, pnrDto.getSegCount());
        assertEquals("穆雨辰", pnrDto.getPassengers().get(0).getPsgName());

        assertEquals(0, pnrDto.getPrice());
        assertEquals(0, pnrDto.getTax());
        assertEquals(790, pnrDto.getChdPrice());
        assertEquals(0, pnrDto.getChdTax());
    }

    @Test
    void testPnrDetail_RmkInterStar() {
        String pnrDetail = " SHA205 SCHEDULE CHG  (  0025  )  (  0025  )\n" +
                "  **ELECTRONIC TICKET PNR** \n" +
                " 1.WANG/YANPING 2.ZHANG/HONG JY9YP3 \n" +
                " 3.  MU2901 Z   SU08JAN  WUXHKG UN2   0750 1040          E T1-- S   \n" +
                " 4.  MU2901 Z   SU08JAN  WUXHKG TK2   0735 1040          E T1-- S   \n" +
                " 5.  MU2902 Z   TU10JAN  HKGWUX UN2   1140 1330          E --T1 S   \n" +
                " 6.  MU2902 Z   TU10JAN  HKGWUX TK2   1140 1320          E --T1 S   \n" +
                " 7.SHA/T SHA/T 0510-82722200/CHINA COMFORT WUXI TRAVEL SERVICE/CHEN RU ABCDEFG  \n" +
                " 8.13701511021  \n" +
                " 9.T\n" +
                "10.SSR ADTK 1E BY SHA18NOV16/1200 OR CXL MU2901 Z08JAN  \n" +
                "11.SSR TKNE MU HK1 WUXHKG 2901 Z08JAN 7811081054309/1/P2                       +\n" +
                "12.SSR TKNE MU HK1 WUXHKG 2901 Z08JAN 7811081054308/1/P1                       -\n" +
                "13.SSR TKNE MU HK1 HKGWUX 2902 Z10JAN 7811081054308/2/P1\n" +
                "14.SSR TKNE MU HK1 HKGWUX 2902 Z10JAN 7811081054309/2/P2\n" +
                "15.SSR DOCS MU HK1 P/CN/C46322008/CN/21OCT70/F/08OCT26/WANG/YANPING/P1  \n" +
                "16.SSR DOCS MU HK1 P/CN/C46322009/CN/13NOV70/M/08OCT26/ZHANG/HONG/P2\n" +
                "17.OSI MU CTCM13901513036/P2\n" +
                "18.OSI MU CTCM13701511021/P1\n" +
                "19.OSI MU CTCT13701511021   \n" +
                "20.RMK TJ SHA205\n" +
                "21.RMK INTERSTAR--13706154921   \n" +
                "22.RMK CA/MHLD07\n" +
                "                                                                               +\n" +
                "23.FN/A/FCNY990.00/SCNY990.00/C0.00/XCNY310.00/TCNY90.00CN/TCNY80.00G3/        -\n" +
                "    TCNY140.00XT/ACNY1300.00\n" +
                "24.TN/781-1081054308/P1 \n" +
                "25.TN/781-1081054309/P2 \n" +
                "26.FP/CASH,CNY  \n" +
                "27.SHA205";

        pnrDto.setPnrDetail(pnrDetail);
        assertEquals("JY9YP3", pnrDto.getPnrNo());
        assertEquals("MHLD07", pnrDto.getBigPnrNo());
        assertEquals(2, pnrDto.getPsgCount());
        assertEquals(4, pnrDto.getSegCount());
        assertEquals("3/4/5/6", pnrDto.getFlightItemNo());
        assertEquals("INTERSTAR", pnrDto.getEtermUsername());
        assertEquals(2, pnrDto.getPsgCount());
        assertEquals(2, pnrDto.getCtcmCount());
        assertEquals(1, pnrDto.getDzStatus());
    }

    @Test
    void testPnrDetail_RmkInterStar1() {
        String pnrDetail = " SHA205 SCHEDULE CHG  (  0000  )  (  0000  )\n" +
                "MARRIED SEGMENT EXIST IN THE PNR\n" +
                "  **ELECTRONIC TICKET PNR** \n" +
                " 1.HU/YONG FENG MR KVFHQ2   \n" +
                " 2.  LH729  T   SA14JAN17PVGFRA HX1   1350 1845      SEAME  2 1 \n" +
                " 3.  LH136  T   SA14JAN17FRASTR HX1   2135 2215      SEAME  1 1 \n" +
                " 4.  LX189  Y   SU15JAN  PVGZRH TL1   0950 1540          E      S   \n" +
                " 5.  SK998  M   SU15JAN  PVGCPH TK1   1335 1830          E      S   \n" +
                " 6.  LX1174 Y   SU15JAN  ZRHSTR TK1   1730 1815          E      S   \n" +
                " 7.  KA897  H   SU15JAN  PVGHKG HX1   1945 2240          E      S   \n" +
                " 8.  SK667  M   SU15JAN  CPHSTR TK1   2040 2210          E      S   \n" +
                " 9.    ARNK              STRGRZ                                                +\n" +
                "10.  LH2339 T1  WE25JAN  GRZMUC HK1   1950 2045      SEAME -- 2                -\n" +
                "11.  LH726  T1  WE25JAN  MUCPVG HK1   2200 1555+1    SEAME  2 2 \n" +
                "12.SHA/T SHA/T-021-62839061 SHA DONG LI BUSINESS LTD.,CO /TANG/ZHEN FENG\n" +
                "     ABCDEFG\n" +
                "13.DL 161129 1406 BKXMVXBY  \n" +
                "14.T\n" +
                "15.SSR OTHS 1E REMOVE DUPE SPACE BY ZRH 1130/15JAN17 OR LX WILL AUTO -CANCEL\n" +
                "16.SSR OTHS 1E PLS ADV TKT NBR FOR ITIN BY 15JAN17/1210Z OR LX OPTG /MKTG FLTS  \n" +
                "    WILL BE CNLD // 14JAN171548 \n" +
                "17.SSR OTHS 1E INVOL\n" +
                "18.SSR OTHS 1E PLS ISS AUTOMATIC TKT BY 02DEC16/2359Z OR LH OPTG/MKTG SEGS  \n" +
                "     WILL BE XLD. APPLIC FARE RULE APPLIES IF IT DEMANDS EARLIER TKTG.         +\n" +
                "19.SSR ADTK 1E ADV TKT NBR TO CX/KA BY 14JAN 2100 GMT OR SUBJECT TO CANCEL     -\n" +
                "20.SSR TKNE LH HK1 GRZMUC 2339 T25JAN 2201194473038/4/P1\n" +
                "21.SSR TKNE LH HK1 MUCPVG 726 T25JAN 2201194473039/1/P1 \n" +
                "22.SSR DOCS \n" +
                "23.SSR CTCM LH HK1 13585991046/P1   \n" +
                "24.SSR OTHS 1E LH 729 14JAN FULLY FLAT BED IN BUSINESS CLASS. SEE WWW.LH.COM\n" +
                "     PLS INSERT PSGR CTC ALSO FOR RETURN FLIGHT \n" +
                "25.OSI CTC  \n" +
                "26.RMK TJ SHA205\n" +
                "27.RMK  \n" +
                "28.RMK  \n" +
                "29.RMK                                                                         +\n" +
                "30.FN/A/FCNY4180.00/SCNY4180.00/C0.00/XCNY3276.00/TCNY90.00CN/TCNY104.00AT/    -\n" +
                "    TCNY3082.00XT/ACNY7456.00   \n" +
                "31.TN/220-1194473038-39/P1  \n" +
                "32.FP/CASH,CNY  \n" +
                "33.SHA697";

        pnrDto.setPnrDetail(pnrDetail);
        assertEquals("KVFHQ2", pnrDto.getPnrNo());
        assertEquals("", pnrDto.getBigPnrNo());
        assertEquals(1, pnrDto.getPsgCount());
        assertEquals(9, pnrDto.getSegCount());
        assertEquals("2/3/4/5/6/7/8/10/11", pnrDto.getFlightItemNo());
//        assertEquals("INTERSTAR", pnrDto.getEtermUsername());

        assertEquals(1, pnrDto.getPsgCount());
        assertEquals(1, pnrDto.getCtcmCount());

        assertEquals(1, pnrDto.getDzStatus());

        assertEquals("2017-01-14", DateUtil.formatDate(pnrDto.getDdate(), "yyyy-MM-dd"));
    }

    @Test
    void testPnrDetail_NoFlight() {
        String pnrDetail = " 1.朱中群 HY0F9W                                                                \n" +
                " 2.SHA/T SHA/T 0510-82722200/CHINA COMFORT WUXI TRAVEL SERVICE/CHEN RU ABCDEFG  \n" +
                " 3.BOOK SHJ 1230 1000                                                           \n" +
                " 4.TL/1800/30DEC16/SHA205                                                       \n" +
                " 5.FC/A/TAO MU WUX 590.00H CNY590.00END                                         \n" +
                " 6.SSR FOID MU HK1 NI320201197407032017/P1                                      \n" +
                " 7.SSR OTHS 1E CNL DUE TO TL                                                    \n" +
                " 8.SSR CKIN MU                                                                  \n" +
                " 9.SSR ADTK 1E BY SHA01JAN17/0958 OR CXL MU2702 H27JAN                          \n" +
                "10.OSI MU CTCT13861896978                                                       \n" +
                "11.RMK CMS/A/**                                                                 \n" +
                "12.RMK OT/A/0/15975/0-1MU2033P1WUX                                              \n" +
                "13.RMK CA/MFTB55                                                                \n" +
                "14.RMK AUTOMATIC FARE QUOTE                                                     \n" +
                "15.FN/A/FCNY590.00/SCNY590.00/C0.00/XCNY50.00/TCNY50.00CN/TEXEMPTYQ/ACNY640.00  \n" +
                "16.EI/Q/BUDEQIANZHUAN不得签转/BIANGENGTUIPIAOSHOUFEI变更退票收费                \n" +
                "17.FP/CASH,CNY   ";

        pnrDto.setPnrDetail(pnrDetail);
        assertEquals("HY0F9W", pnrDto.getPnrNo());
        assertEquals("MFTB55", pnrDto.getBigPnrNo());
        assertEquals(1, pnrDto.getPsgCount());
        assertEquals(0, pnrDto.getSegCount());
        assertEquals("", pnrDto.getBookOfficeNo());
    }

    @Test
    void testPnrDetail_NoNames() {
        String pnrDetail = "  **ELECTRONIC TICKET PNR** \n" +
                " 0.23CZX/AT NM23 HT9X3N \n" +
                "24.  CA1825 K   WE26SEP  PEKCZX RR23  1725 1935          E T3-- \n" +
                "25.CZX  \n" +
                "26.T\n" +
                "27.SSR FOID CA HK1 NI32052019640106254X/P14 \n" +
                "28.SSR FOID CA HK1 NI320520196907140840/P21 \n" +
                "29.SSR FOID CA HK1 NI320520194601290820/P20 \n" +
                "30.SSR FOID CA HK1 NI320520197910144425/P17 \n" +
                "31.SSR FOID CA HK1 NI320520197508274440/P12 \n" +
                "32.SSR FOID CA HK1 NI320219194410267028/P15 \n" +
                "33.SSR FOID CA HK1 NI320219196912077063/P16                                    +\n" +
                "34.SSR FOID CA HK1 NI630102194009160427/P3                                     -35.SSR FOID CA HK1 NI321024196907280084/P4  \n" +
                "36.SSR FOID CA HK1 NI320582198108073053/P23 \n" +
                "37.SSR FOID CA HK1 NI320582201203180249/P22 \n" +
                "38.SSR FOID CA HK1 NI321181198507016323/P1  \n" +
                "39.SSR FOID CA HK1 NI320582201412120170/P19 \n" +
                "40.SSR FOID CA HK1 NI320521195310011167/P18 \n" +
                "41.SSR FOID CA HK1 NI320521195211151113/P10 \n" +
                "42.SSR FOID CA HK1 NI320582198910051142/P11 \n" +
                "43.SSR FOID CA HK1 NI320582201309120287/P5  \n" +
                "44.SSR FOID CA HK1 NI320582198404076435/P7  \n" +
                "45.SSR FOID CA HK1 NI320521196007256422/P8                                     +\n" +
                "46.SSR FOID CA HK1 NI320582199009174822/P9                                     -47.SSR FOID CA HK1 NI320582198811156432/P6  \n" +
                "48.SSR FOID CA HK1 NI320521196109120825/P13 \n" +
                "49.SSR FOID CA HK1 NI320521195712190815/P2  \n" +
                "50.SSR OTHS CA TKT BEF1600/20SEP OR CNL \n" +
                "51.SSR FQTV CA HK1 PEKCZX 1825 K26SEP CA313012249019/P11\n" +
                "52.SSR TKNE CA HK1 PEKCZX 1825 K26SEP 9992899128695/1/P23   \n" +
                "53.SSR TKNE CA HK1 PEKCZX 1825 K26SEP 9992899128694/1/P22   \n" +
                "54.SSR TKNE CA HK1 PEKCZX 1825 K26SEP 9992899128693/1/P21   \n" +
                "55.SSR TKNE CA HK1 PEKCZX 1825 K26SEP 9992899128692/1/P20   \n" +
                "56.SSR TKNE CA HK1 PEKCZX 1825 K26SEP 9992899128691/1/P19   \n" +
                "57.SSR TKNE CA HK1 PEKCZX 1825 K26SEP 9992899128690/1/P18                      +\n" +
                "58.SSR TKNE CA HK1 PEKCZX 1825 K26SEP 9992899128689/1/P17                      -59.SSR TKNE CA HK1 PEKCZX 1825 K26SEP 9992899128688/1/P16   \n" +
                "60.SSR TKNE CA HK1 PEKCZX 1825 K26SEP 9992899128687/1/P15   \n" +
                "61.SSR TKNE CA HK1 PEKCZX 1825 K26SEP 9992899128686/1/P14   \n" +
                "62.SSR TKNE CA HK1 PEKCZX 1825 K26SEP 9992899128685/1/P13   \n" +
                "63.SSR TKNE CA HK1 PEKCZX 1825 K26SEP 9992899128684/1/P12   \n" +
                "64.SSR TKNE CA HK1 PEKCZX 1825 K26SEP 9992899128683/1/P11   \n" +
                "65.SSR TKNE CA HK1 PEKCZX 1825 K26SEP 9992899128682/1/P10   \n" +
                "66.SSR TKNE CA HK1 PEKCZX 1825 K26SEP 9992899128681/1/P9\n" +
                "67.SSR TKNE CA HK1 PEKCZX 1825 K26SEP 9992899128680/1/P8\n" +
                "68.SSR TKNE CA HK1 PEKCZX 1825 K26SEP 9992899128679/1/P7\n" +
                "69.SSR TKNE CA HK1 PEKCZX 1825 K26SEP 9992899128678/1/P6                       +\n" +
                "70.SSR TKNE CA HK1 PEKCZX 1825 K26SEP 9992899128677/1/P5                       -71.SSR TKNE CA HK1 PEKCZX 1825 K26SEP 9992899128676/1/P4\n" +
                "72.SSR TKNE CA HK1 PEKCZX 1825 K26SEP 9992899128675/1/P3\n" +
                "73.SSR TKNE CA HK1 PEKCZX 1825 K26SEP 9992899128674/1/P2\n" +
                "74.SSR TKNE CA HK1 PEKCZX 1825 K26SEP 9992899128673/1/P1\n" +
                "75.SSR CHLD CA HK1 18MAR12/P22  \n" +
                "76.SSR CHLD CA HK1 12DEC14/P19  \n" +
                "77.SSR CHLD CA HK1 12SEP13/P5   \n" +
                "78.OSI CA CTCT0519  \n" +
                "79.OSI CA CTCT13952475268   \n" +
                "80.OSI CA CTCM18662301221/P2/13 \n" +
                "81.OSI CA CTCM13862209497/P6/9                                                 +\n" +
                "82.OSI CA CTCM15861556527/P5/7/8                                               -83.OSI CA CTCM13773236131/P10/11\n" +
                "84.OSI CA CTCM15106126811/P18/19\n" +
                "85.OSI CA CTCM18015682885/P1/22 \n" +
                "86.OSI CA CTCM13773222066/P23   \n" +
                "87.OSI CA CTCM13775763052/P3/4  \n" +
                "88.OSI CA CTCM13327917532/P15/16\n" +
                "89.OSI CA CTCM15250320810/P12/17/20 \n" +
                "90.OSI CA CTCM13585004695/P14/21\n" +
                "91.RMK CMS/A/** \n" +
                "92.RMK CMS/A/** \n" +
                "93.RMK CMS/A/**                                                                +\n" +
                "94.RMK CMS/A/**                                                                -95.RMK TJ SHA205\n" +
                "96.RMK CA XINGZONGHAI   \n" +
                "97.RMK CA/MG9PQ5\n" +
                "98.RMK CLAIM PNR ACK RECEIVED   \n" +
                "99.RMK AUTOMATIC FARE QUOTE \n" +
                "100.RMK AUTOMATIC FARE QUOTE\n" +
                "101.RMK AUTOMATIC FARE QUOTE\n" +
                "102.RMK AUTOMATIC FARE QUOTE\n" +
                "103.FN/A/FCNY400.00/SCNY400.00/C0.00/XCNY60.00/TCNY50.00CN/TCNY10.00YQ/ \n" +
                "    ACNY460.00  \n" +
                "104.FN/A/FCNY400.00/SCNY400.00/C0.00/TEXEMPTCN/TEXEMPTYQ/ACNY400.00/P5         +\n" +
                "105.FN/A/FCNY400.00/SCNY400.00/C0.00/TEXEMPTCN/TEXEMPTYQ/ACNY400.00/P19        -106.FN/A/FCNY400.00/SCNY400.00/C0.00/TEXEMPTCN/TEXEMPTYQ/ACNY400.00/P22 \n" +
                "107.TN/999-2899128673/P1\n" +
                "108.TN/999-2899128674/P2\n" +
                "109.TN/999-2899128675/P3\n" +
                "110.TN/999-2899128676/P4\n" +
                "111.TN/999-2899128677/P5\n" +
                "112.TN/999-2899128678/P6\n" +
                "113.TN/999-2899128679/P7\n" +
                "114.TN/999-2899128680/P8\n" +
                "115.TN/999-2899128681/P9\n" +
                "116.TN/999-2899128682/P10                                                      +\n" +
                "117.TN/999-2899128683/P11                                                      -118.TN/999-2899128684/P12   \n" +
                "119.TN/999-2899128685/P13   \n" +
                "120.TN/999-2899128686/P14   \n" +
                "121.TN/999-2899128687/P15   \n" +
                "122.TN/999-2899128688/P16   \n" +
                "123.TN/999-2899128689/P17   \n" +
                "124.TN/999-2899128690/P18   \n" +
                "125.TN/999-2899128691/P19   \n" +
                "126.TN/999-2899128692/P20   \n" +
                "127.TN/999-2899128693/P21   \n" +
                "128.TN/999-2899128694/P22                                                      +\n" +
                "129.TN/999-2899128695/P23                                                      -130.FP/CASH,CNY \n" +
                "131.FP/CASH,CNY/P5  \n" +
                "132.FP/CASH,CNY/P19 \n" +
                "133.FP/CASH,CNY/P22 \n" +
                "134.SHA205";

        pnrDto.setPnrDetail(pnrDetail);
        assertEquals("HT9X3N", pnrDto.getPnrNo());
//        assertEquals("MFTB55", pnrDto.getBigPnrNo());
        assertEquals(0, pnrDto.getPsgCount());
        assertEquals(1, pnrDto.getSegCount());
        assertEquals(1, pnrDto.getDzStatus());
//        assertEquals("", pnrDto.getBookOfficeNo());


    }

    @Test
    void testPnrDetail_NoNames_2() {
        String pnrDetail = " 0.26WUXICCTS/AT NM0 HEHWTM \n" +
                " 1.  CA919  K   TU30OCT  PVGNRT HK26  1430 1815          E T21  \n" +
                " 2.    ARNK              NRTNGO \n" +
                " 3.  CA406  K   SU04NOV  NGOPVG HK26  1510 1625          E --T2 \n" +
                " 4.SHA/T SHA/T 0510-82722200/CHINA COMFORT WUXI TRAVEL SERVICE/CHEN RU ABCDEFG   5.LIU  \n" +
                " 6.BOOK LIUHUI 0725 1440\n" +
                " 7.TL/1100/29OCT/SHA205 \n" +
                " 8.SSR GRPS 1E PAY 1ST DEPOSIT TO CA BY 09AUG18/1100 OR CNL \n" +
                " 9.SSR GRPS 1E CNL UNUSED SEATS BY 09AUG18/1100 OR MORE PENALTY \n" +
                "10.SSR GRPS 1E ADTK BY 27OCT18/1600 OR CNL NO TKT PAX   \n" +
                "11.OSI CA CTCT13912375556                                                      +\n" +
                "12.RMK CA/NCEXSY                                                               -13.SHA205";

        pnrDto.setPnrDetail(pnrDetail);
        assertEquals("HEHWTM", pnrDto.getPnrNo());
//        assertEquals("MFTB55", pnrDto.getBigPnrNo());
        assertEquals(0, pnrDto.getPsgCount());
        assertEquals(2, pnrDto.getSegCount());
        assertEquals("1/3", pnrDto.getFlightItemNo());
//        assertEquals("", pnrDto.getBookOfficeNo());
    }

    @Test
    void testPnrDetail_SsrDocs() {
        String pnrDetail = "  **ELECTRONIC TICKET PNR** \n" +
                " 1.CHENG/JUTING KXCEFM  \n" +
                " 2.SHA/T SHA/T 0510-82722200/CHINA COMFORT WUXI TRAVEL SERVICE/CHEN RU ABCDEFG   3.BOOK SZCY4 0703 1448 \n" +
                " 4.BOOK SZCY4 0922 1214 \n" +
                " 5.T\n" +
                " 6.SSR SEAT BR HK1 SFOTPE 007 K22DEC 26GN   \n" +
                " 7.SSR SEAT BR HK1 TPEPVG 722 K27DEC 21CN   \n" +
                " 8.SSR OTHS 1E BR/B7 HV TAKEN OVER TZ PNR X PLZ CTC BR FR FURTHER CHG ON BR/B7      FLT \n" +
                " 9.SSR OTHS 1E BR CANCELLATION DUE TO NO TICKET \n" +
                "10.SSR OTHS 1E PLEASE PROVIDE DOCS DATA OR MAY DENY ON BOARD                   +\n" +
                "11.SSR FQTV BR HK/ BR3310558790/P1                                             -12.SSR ADTK 1E TO BR BY 23SEP 1300 HKG OTHERWISE WILL BE XLD\n" +
                "13.SSR ADTK 1E TO BR BY 18SEP 1000 HKG OTHERWISE WILL BE XLD\n" +
                "14.SSR ADTK 1E TO BR BY 04JUL 1600 HKG OTHERWISE WILL BE XLD\n" +
                "15.SSR ADTK 1E TO BR BY 04JUL 1500 HKG OTHERWISE WILL BE XLD\n" +
                "16.SSR TKNE BR HK1 SFOTPE 007 K22DEC 6952877154728/1/P1 \n" +
                "17.SSR TKNE BR HK1 TPEPVG 722 K27DEC 6952877154728/2/P1 \n" +
                "18.SSR DOCS BR HK1 P/TWN/301920358/TWN/05OCT95/F/04AUG20/P1 \n" +
                "19.SSR DOCS BR HK1 SFOTPE 007 K22DEC P/TWN/301920358/TWN/05OCT95/F/04AUG20  \n" +
                "    /P1 \n" +
                "20.SSR DOCS BR HK1 ////05OCT95/F//CHENG/JUTING  \n" +
                "21.OSI BR TKNO 6955929937240                                                   +\n" +
                "22.OSI BR CTCT13641912087                                                      -23.RMK TJ SHA383\n" +
                "24.RMK 1A/T3GZI4\n" +
                "25.RMK TJ AUTH SHA383   \n" +
                "26.RMK TJ AUTH SHA463   \n" +
                "27.FN/RUSD1909.00/ECNY13080.00/SCNY00.00/C0.00/OCNY39.00AY/OCNY126.00US/\n" +
                "    OCNY479.00XT/ACNY00.00  \n" +
                "28.TN/695-2877154728/P1 \n" +
                "29.FP/CASH,CNY/*PVG0069 \n" +
                "30.SHA205";

        pnrDto.setPnrDetail(pnrDetail);
        assertEquals("KXCEFM", pnrDto.getPnrNo());
        assertEquals(1, pnrDto.getPsgCount());
        assertEquals(0, pnrDto.getSegCount());
    }

    @Test
    void test_pnr_ctcm_duplicate() {
        String pnrDetail = "  **ELECTRONIC TICKET PNR** \n" +
                " 1.黎振贵 2.任在露 3.孙乐 KRW40G\n" +
                " 4.  ZH9544 V   SA27OCT  CTUWUX RR3   1740 2030          E T2-- \n" +
                " 5.SHA/T SHA/T 0510-82722200/CHINA COMFORT WUXI TRAVEL SERVICE/CHEN RU ABCDEFG   6.BOOK SHJ 1010 0905   \n" +
                " 7.T\n" +
                " 8.SSR FOID ZH HK1 NI321283199403184026/P3  \n" +
                " 9.SSR FOID ZH HK1 NI04343837/P1\n" +
                "10.SSR FOID ZH HK1 NI320831198809300323/P2  \n" +
                "11.SSR FQTV ZH HK1 CTUWUX 9544 V27OCT CA122002893247/P1 \n" +
                "12.SSR ADTK 1E BY SHA10OCT18/1520 OR CXL ZH9544 V27OCT  \n" +
                "13.SSR TKNE ZH HK1 CTUWUX 9544 V27OCT 4792935352300/1/P3                       +\n" +
                "14.SSR TKNE ZH HK1 CTUWUX 9544 V27OCT 4792935352299/1/P2                       -15.SSR TKNE ZH HK1 CTUWUX 9544 V27OCT 4792935352298/1/P1\n" +
                "16.OSI ZH CTCT13861896978   \n" +
                "17.OSI ZH CTCM15358087520/P1\n" +
                "18.OSI ZH CTCM15358087511/P1/2/3\n" +
                "19.OSI ZH CTCM15358087508/P1/2/3\n" +
                "20.RMK CMS/A/** \n" +
                "21.RMK TJ SHA205\n" +
                "22.RMK CA/MERH0Y\n" +
                "23.RMK AUTOMATIC FARE QUOTE \n" +
                "24.FN/A/FCNY1050.00/SCNY1050.00/C0.00/XCNY80.00/TCNY50.00CN/TCNY30.00YQ/\n" +
                "    ACNY1130.00                                                                +\n" +
                "25.TN/479-2935352298/P1                                                        -26.TN/479-2935352299/P2 \n" +
                "27.TN/479-2935352300/P3 \n" +
                "28.FP/CASH,CNY  \n" +
                "29.SHA205";

        pnrDto.setPnrDetail(pnrDetail);
        assertEquals("KRW40G", pnrDto.getPnrNo());
        assertEquals("MERH0Y", pnrDto.getBigPnrNo());
        assertEquals(3, pnrDto.getPsgCount());
        assertEquals(1, pnrDto.getSegCount());
        assertEquals(3, pnrDto.getCtcmCount());
        assertEquals("479-2935352298", pnrDto.getTicketNoByName("黎振贵"));
        assertEquals("479-2935352299", pnrDto.getTicketNoByName("任在露"));
        assertEquals("479-2935352300", pnrDto.getTicketNoByName("孙乐"));
        assertEquals("479-2935352298", pnrDto.getTicketNoByIdNo("04343837"));
        assertEquals("479-2935352299", pnrDto.getTicketNoByIdNo("320831198809300323"));
        assertEquals("479-2935352300", pnrDto.getTicketNoByIdNo("321283199403184026"));

        assertEquals(1, pnrDto.getDzStatus());
    }

    @Test
    void test_pnr_tn() {
        String pnrDetail = "  **ELECTRONIC TICKET PNR** \n" +
                " 1.AA 2.BB 3.CC HY9PKH\n" +
                " 4.  NS3289 M   TU05FEB  SJWHAK RR3   1255 1810          E T2-- \n" +
                " 5.SHA/T SHA/T18917808789/SHANGHAI YINGHANG TICKETING AGENT LIMITED COMPANY/LI      SHIRONG ABCDEFG \n" +
                " 6.T\n" +
                " 7.SSR FOID NS HK1 NI130982199702070921/P3  \n" +
                " 8.SSR FOID NS HK1 NI132903197109210919/P2  \n" +
                " 9.SSR FOID NS HK1 NI132903197007250960/P1  \n" +
                "10.SSR ADTK 1E BY SHA06DEC18/2300 OR CXL NS3289 M05FEB  \n" +
                "11.SSR TKNE NS HK1 SJWHAK 3289 M05FEB 8363169222147/1/P3\n" +
                "12.SSR TKNE NS HK1 SJWHAK 3289 M05FEB 8363169222146/1/P2                       +\n" +
                "13.SSR TKNE NS HK1 SJWHAK 3289 M05FEB 8363169222145/1/P1                       -14.OSI NS CTCT15026628515   \n" +
                "15.OSI NS CTCM17701608782/P1/2/3\n" +
                "16.RMK CMS/A/** \n" +
                "17.RMK TJ SHA360\n" +
                "18.RMK TJ AUTH SHA717   \n" +
                "19.RMK CA/MXBWSM\n" +
                "20.RMK AUTOMATIC FARE QUOTE \n" +
                "21.RMK FARECODE/SHASF001/NS3289/SJWHAK/05FEB19/CNY1760.00   \n" +
                "22.FN/A/FCNY1760.00/SCNY1760.00/C0.00/XCNY80.00/TCNY50.00CN/TCNY30.00YQ/\n" +
                "    ACNY1840.00 \n" +
                "23.TN/836-3169222145/P1                                                        +\n" +
                "24.TN/836-3169222146/P2                                                        -25.TN/836-3169222147/P3 \n" +
                "26.FP/CASH,CNY/*SHASF001\n" +
                "27.SHA360";

        pnrDto.setPnrDetail(pnrDetail);
        assertEquals("HY9PKH", pnrDto.getPnrNo());
        assertEquals("MXBWSM", pnrDto.getBigPnrNo());
        assertEquals(3, pnrDto.getPsgCount());
        assertEquals(1, pnrDto.getSegCount());
        assertEquals(3, pnrDto.getCtcmCount());
        assertEquals("836-3169222145", pnrDto.getTicketNoByName("AA"));
        assertEquals("836-3169222146", pnrDto.getTicketNoByName("BB"));
        assertEquals("836-3169222147", pnrDto.getTicketNoByName("CC"));
        assertEquals("836-3169222145", pnrDto.getTicketNoByIdNo("132903197007250960"));
        assertEquals("836-3169222146", pnrDto.getTicketNoByIdNo("132903197109210919"));
        assertEquals("836-3169222147", pnrDto.getTicketNoByIdNo("130982199702070921"));

        assertEquals(1, pnrDto.getDzStatus());
        assertEquals(true, pnrDto.isEt());
    }

    @Test
    void test_pnr_dz_status() {
        String pnrDetail = "  **ELECTRONIC TICKET PNR** \n" +
                " 1.黎振贵 2.任在露 3.孙乐 KRW40G\n" +
                " 4.  ZH9544 V   SA27OCT  CTUWUX RR3   1740 2030          E T2-- \n" +
                " 5.SHA/T SHA/T 0510-82722200/CHINA COMFORT WUXI TRAVEL SERVICE/CHEN RU ABCDEFG   6.BOOK SHJ 1010 0905   \n" +
                " 7.T\n" +
                " 8.SSR FOID ZH HK1 NI321283199403184026/P3  \n" +
                " 9.SSR FOID ZH HK1 NI04343837/P1\n" +
                "10.SSR FOID ZH HK1 NI320831198809300323/P2  \n" +
                "11.SSR FQTV ZH HK1 CTUWUX 9544 V27OCT CA122002893247/P1 \n" +
                "12.SSR ADTK 1E BY SHA10OCT18/1520 OR CXL ZH9544 V27OCT  \n";

        pnrDto.setPnrDetail(pnrDetail);
        assertEquals("KRW40G", pnrDto.getPnrNo());
        assertEquals(3, pnrDto.getPsgCount());
        assertEquals(1, pnrDto.getSegCount());

        assertEquals(1, pnrDto.getDzStatus());
    }

    @Test
    void test_getItemNo_xe() {
        final String pnrDetail = " 1.贾艳丽 HR3KBH\n" +
                " 2.  ZH9504 Y   FR16NOV  SHASZX HK1   1245 1525          E T2T3 \n" +
                " 3.SHA/T SHA/T18917808789/SHANGHAI YINGHANG TICKETING AGENT LIMITED COMPANY/LI      SHIRONG ABCDEFG \n" +
                " 4.021-51069999X454360/P1   \n" +
                " 5.95010/P1 \n" +
                " 6.TL/1200/16NOV/SHA360 \n" +
                " 7.SSR FOID ZH HK1 NI130228197811150087/P1  \n" +
                " 8.SSR FQTV ZH HK1 SHASZX 9504 Y16NOV CA008184718531/P1 \n" +
                " 9.SSR ADTK 1E BY SHA15NOV18/1851 OR CXL ZH9504 Y16NOV  \n" +
                "10.OSI ZH CTCT15902115610   \n" +
                "11.OSI ZH CTCM15000727847/P1 ";

        pnrDto.setPnrDetail(pnrDetail);

        assertEquals(1, pnrDto.getSegCount());
        assertEquals("2", pnrDto.getFlightItemNo());
        assertEquals("10/11", pnrDto.getCtctCtcmItemNo());
        assertEquals("4/5", pnrDto.getCtripCtItemNo());
        assertEquals("6", pnrDto.getTktlItemNo());
        assertEquals("4/5/6/10/11", pnrDto.getAllNeedXeItemNos());
//        assertEquals(11, pnrDto.getCtcmItemNo());

        assertEquals("HK", pnrDto.getSegStatus());
        PnrFlightDto flt = pnrDto.getFlights().get(0);
        assertEquals("HK", flt.getFlight().getSegmentStatus());
    }

    @Test
    void test_getItemNo_xe_2() {
        final String pnrDetail = "  **ELECTRONIC TICKET PNR**                                                     \n" +
                " 1.方瑜 2.冯春生 HDKWBP                                                         \n" +
                " 3.  NS3240 K   FR01FEB  HAKLYG RR2   1910 2205          E\n" +
                " 4.SHA/T SHA/T18917808789/SHANGHAI YINGHANG TICKETING AGENT LIMITED COMPANY/LI  \n" +
                "    SHIRONG ABCDEFG                                                             \n" +
                " 5.T                                                                            \n" +
                " 6.SSR FOID NS HK1 NI371327198912284913/P2                                      \n" +
                " 7.SSR FOID NS HK1 NI460032198709156188/P1                                      \n" +
                " 8.SSR ADTK 1E BY SHA22DEC18/2300 OR CXL NS3240 K01FEB                          \n" +
                " 9.SSR TKNE NS HK1 HAKLYG 3240 K01FEB 8363402779388/1/P2                        \n" +
                "10.SSR TKNE NS HK1 HAKLYG 3240 K01FEB 8363402779387/1/P1                        \n" +
                "11.OSI NS CTCT15026628515 ";

        pnrDto.setPnrDetail(pnrDetail);

        assertEquals(1, pnrDto.getSegCount());
        assertEquals("3", pnrDto.getFlightItemNo());

        assertEquals("RR", pnrDto.getSegStatus());
        PnrFlightDto flt = pnrDto.getFlights().get(0);
        assertEquals("RR", flt.getFlight().getSegmentStatus());
    }

    @Test
    void test_pnr_detail() {
        final String pnrDetail = " SHA205 REPLY RCD     (  0001  )  (  0001  )\n" +
                " 1.黄乃宏 2.陆庆 3.沈金林 4.沈立新 5.王世群 6.张建刚\n" +
                "   KMVQHE   \n" +
                " 7.  FU6630 A   WE23JAN  HLDHRB NO6   1955 2115          E --T2 \n" +
                " 8.SHA/T SHA/T 0510-82722200/CHINA COMFORT WUXI TRAVEL SERVICE/CHEN RU ABCDEFG  \n" +
                " 9.BOOK SZDW3 0114 1052 \n" +
                "10.TL/1755/23JAN/SHA205 \n" +
                "11.SSR FOID FU HK1 NI320981197811066710/P5  \n" +
                "12.SSR FOID FU HK1 NI320523197109275533/P6  \n" +
                "13.SSR FOID FU HK1 NI320583197911219412/P3  \n" +
                "14.SSR FOID FU HK1 NI32052319760829101X/P2  \n" +
                "15.SSR FOID FU HK1 NI320404197111080415/P1                                     +\n" +
                "16.SSR FOID FU HK1 NI320523196405121016/P4                                     -\n" +
                "17.SSR OTHS 1E FU6630 /A/23JAN/HLDHRB CANCELED DUE TO ATTL EXPIRED  \n" +
                "18.OSI FU CTCM15335287789/P4\n" +
                "19.OSI FU CTCM18913531567/P1\n" +
                "20.OSI FU CTCM18962152300/P2\n" +
                "21.OSI FU CTCM13906133066/P3\n" +
                "22.OSI FU CTCM13063879952/P6\n" +
                "23.OSI FU CTCM15501689589/P5\n" +
                "24.OSI FU CTCT15371210397   \n" +
                "25.RMK CA/NFDPY8\n" +
                "26.SHA205";

        pnrDto.setPnrDetail(pnrDetail);

        assertEquals(6, pnrDto.getPsgCount());
        assertEquals("黄乃宏", pnrDto.getPassengers().get(0).getName());
        assertEquals(ID_TYPE_NI, pnrDto.getPassengers().get(0).getIdType());
        assertEquals("1971-11-08", DateUtil.formatDate(pnrDto.getPassengers().get(0).getBirthday(), "yyyy-MM-dd"));
        assertEquals(GENDER_MALE, pnrDto.getPassengers().get(0).getGender());


        assertEquals(1, pnrDto.getSegCount());
        assertEquals("KMVQHE", pnrDto.getPnrNo());
        assertEquals("NFDPY8", pnrDto.getBigPnrNo());

        assertEquals("NO", pnrDto.getSegStatus());
        PnrFlightDto flt = pnrDto.getFlights().get(0);
        assertEquals("NO", flt.getFlight().getSegmentStatus());
    }

    @Test
    void test_pnr_detail_2() {
        final String pnrDetail = "MARRIED SEGMENT EXIST IN THE PNR                                               - 1.LI/YAN 2.YAN/SIJI JS7BKD \n" +
                " 3.  KL896  N1  TU29JAN  PVGAMS HK2   1240 1745      SEAME  1-- \n" +
                " 4.  KL1609 L1  TU29JAN  AMSFCO HK2   2100 2310      SEAME -- 1 \n" +
                " 5.  AF112  L   FR08FEB  CDGPVG HK2   1330 0750+1    SEAME 2E 1 \n" +
                " 6.SHA/T SHA/T 0510-82722200/CHINA COMFORT WUXI TRAVEL SERVICE/CHEN RU ABCDEFG   7.BOOK BJHYGJ5 0114 1618   \n" +
                " 8.TL/1040/29JAN/SHA205 \n" +
                " 9.SSR OTHS 1E AF 112 08FEB APIS DEST PAX DATA REQUIRED SSR DOCS\n" +
                "10.SSR ADTK 1E TO KL BY 17JAN19/1300Z OTHERWISE WILL BE XXLD\n" +
                "11.SSR ADTK 1E TO AF BY 17JAN 1700 OTHERWISE WILL BE XLD\n" +
                "12.SSR CTCM KL HK1 18916014747/P1                                              +\n" +
                "13.SSR CTCM AF HK1 18916014747/P1                                              -14.OSI KL CTCT18916014747   \n" +
                "15.RMK 1A/K4E7ZC\n" +
                "16.SHA205";

        pnrDto.setPnrDetail(pnrDetail);

        assertEquals(3, pnrDto.getSegCount());
        assertEquals(2, pnrDto.getPsgCount());
        assertEquals("JS7BKD", pnrDto.getPnrNo());
        assertEquals("", pnrDto.getBigPnrNo());

        assertEquals("HK", pnrDto.getSegStatus());
        PnrFlightDto flt = pnrDto.getFlights().get(0);
        assertEquals("HK", flt.getFlight().getSegmentStatus());

        assertEquals(0, pnrDto.getDzStatus());
    }

    @Test
    void test_pnr_detail_3() {
        final String pnrDetail = "MARRIED SEGMENT EXIST IN THE PNR                                               -  **ELECTRONIC TICKET PNR** \n" +
                " 1.BAI/JUNHUI MR HRG5L0 \n" +
                " 2.SHA/T SHA/T 0510-82722200/CHINA COMFORT WUXI TRAVEL SERVICE/CHEN RU ABCDEFG   3.BOOK SZHX 0322 1651  \n" +
                " 4.BOOK MAXIAOQING 0531 1054\n" +
                " 5.T\n" +
                " 6.SSR OTHS 1E PLS ADV TKT NBR FOR ITIN BY 01JUN18/0210Z OR LX FLTS WILL BE \n" +
                "     CNLD / APPLIC FARE RULE APPLIES IF IT DEMANDS EARLIER TKTG // 31MAY180210   7.SSR OTHS 1E PLS ADV TKT NBR FOR ITIN BY 23APR18/0223Z OR LX FLTS WILL BE \n" +
                "     CNLD / APPLIC FARE RULE APPLIES IF IT DEMANDS EARLIER TKTG // 20APR180223                                                                                 +\n" +
                " 8.SSR OTHS 1E PLS ADV TKT NBR FOR ITIN BY 22MAR18/2150Z OR LX FLTS WILL BE    -     CNLD / APPLIC FARE RULE APPLIES IF IT DEMANDS EARLIER TKTG // 22MAR180849   9.SSR TKNE LX HK1 PVGZRH 189 S23MAR 7245321405398/1/P1 \n" +
                "10.SSR TKNE LX HK1 ZRHMAN 380 S23MAR 7245321405398/2/P1 \n" +
                "11.SSR TKNE LX HK1 MANZRH 391 S01JUN 7242694909270/1/P1 \n" +
                "12.SSR TKNE LX HK1 ZRHPVG 188 S01JUN 7242694909270/2/P1 \n" +
                "13.SSR DOCS LX HK1 P/CN/E00593027/CN/08APR84/M/28JUN22/BAI/JUNHUI/P1\n" +
                "14.SSR OTHS 1E LX 391 01JUN OPERATED BY 2L  \n" +
                "15.OSI LX CTCT15962205118   \n" +
                "16.RMK TJ SHA205\n" +
                "17.RMK 1A/LRHEWM\n" +
                "18.RMK TJ AUTH SHA697                                                          +\n" +
                "19.FN/RCNY4300.00/SCNY200.00/C0.00/XCNY1013.00/TCNY970.00DU/TCNY43.00UB/       -    OCNY3723.00XT/ACNY1213.00   \n" +
                "20.TN/724-2694909270/P1 \n" +
                "21.FP/CASH,CNY  \n" +
                "22.SHA205";

        pnrDto.setPnrDetail(pnrDetail);

        assertEquals(0, pnrDto.getSegCount());
        assertEquals(1, pnrDto.getPsgCount());
        assertEquals("HRG5L0", pnrDto.getPnrNo());
        assertEquals("", pnrDto.getBigPnrNo());

        assertEquals(null, pnrDto.getSegStatus());
    }

    @Test
    void test_pnr_detail_4() {
        final String pnrDetail = "  **ELECTRONIC TICKET PNR** \n" +
                " 0.10SHAZHUYUANEB NM10 HMVVRE   \n" +
                "11.  AC026  K   TU22JAN  PVGYVR HK10  1750 1210      CLAME  \n" +
                "12.  AC025  K   TU29JAN  YVRPVG HK10  1200 1600+1    CLAME  \n" +
                "13.SHA/T SHA/T 0510-82722200/CHINA COMFORT WUXI TRAVEL SERVICE/CHEN RU ABCDEFG  14.BJS/WANGYILING//QTRAVEL.CN   \n" +
                "15.BOOK TAOMAN 0114 1517\n" +
                "16.BOOK PYQ 0114 1530   \n" +
                "17.T\n" +
                "18.FQ/MANUAL FARE QUOTE-20DEC18 HS/GS   \n" +
                "19.FQ/FARE CALC-SHA AC YVR Q11.43 R0.00 LK18GPEB AC SHA Q11.43 R0.00 LL18GP \n" +
                "20.FQ/EB                                                                       +\n" +
                "21.FQ/FARE CALC- NUC22.86ROE6.918915                                           -22.FQ/FARE- IT CNY 1750 TAX YQ 2350 TAX CN 90 TAX CA 134 TAX XG 6   \n" +
                "23.FQ/FARE TAX- SQ 103  \n" +
                "24.FQ/FARE TOTAL- CNY 2683  \n" +
                "25.FQ/FARE BAG ALLOWANCE-2PC\n" +
                "26.FQ/TOUR FARE- CN18GPEB   \n" +
                "27.SSR SEAT AC HK1 PVGYVR 026 K22JAN36AN\n" +
                "28.SSR SEAT AC HK1 PVGYVR 026 K22JAN36BN\n" +
                "29.SSR SEAT AC HK1 PVGYVR 026 K22JAN36CN\n" +
                "30.SSR SEAT AC HK1 PVGYVR 026 K22JAN36DN\n" +
                "31.SSR SEAT AC HK1 PVGYVR 026 K22JAN36EN\n" +
                "32.SSR SEAT AC HK1 PVGYVR 026 K22JAN36GN                                       +\n" +
                "33.SSR SEAT AC HK1 PVGYVR 026 K22JAN37AN                                       -34.SSR SEAT AC HK1 PVGYVR 026 K22JAN37BN\n" +
                "35.SSR SEAT AC HK1 PVGYVR 026 K22JAN37CN\n" +
                "36.SSR SEAT AC HK1 PVGYVR 026 K22JAN37DN\n" +
                "37.SSR AUTH AC HK/ PVGYVR 026 K22JAN /PSGI/BJSAC/HSGS/11JAN9\n" +
                "38.SSR AUTH AC HK/ PVGYVR 026 K22JAN /PSGI/BJSAC/HSGS/11JAN9\n" +
                "39.SSR AUTH AC HK/ PVGYVR 026 K22JAN /PSGI/BJSAC/HSGS/11JAN9\n" +
                "40.SSR AUTH AC HK/ PVGYVR 026 K22JAN /PSGI/BJSAC/HSGS/11JAN9\n" +
                "41.SSR AUTH AC HK/ PVGYVR 026 K22JAN /PSGI/BJSAC/HSGS/11JAN9\n" +
                "42.SSR AUTH AC HK/ PVGYVR 026 K22JAN /PSGI/BJSAC/HSGS/11JAN9\n" +
                "43.SSR AUTH AC HK/ PVGYVR 026 K22JAN /PSGI/BJSAC/HSGS/11JAN9\n" +
                "44.SSR AUTH AC HK/ PVGYVR 026 K22JAN /PSGI/BJSAC/HSGS/11JAN9                   +\n" +
                "45.SSR AUTH AC HK/ PVGYVR 026 K22JAN /PSGI/BJSAC/HSGS/11JAN9                   -46.SSR AUTH AC HK/ PVGYVR 026 K22JAN /PSGI/BJSAC/CCGS/14JAN9\n" +
                "47.SSR SEAT AC HK1 YVRPVG 025 K29JAN29AN\n" +
                "48.SSR SEAT AC HK1 YVRPVG 025 K29JAN29BN\n" +
                "49.SSR SEAT AC HK1 YVRPVG 025 K29JAN29CN\n" +
                "50.SSR SEAT AC HK1 YVRPVG 025 K29JAN29DN\n" +
                "51.SSR SEAT AC HK1 YVRPVG 025 K29JAN29EN\n" +
                "52.SSR SEAT AC HK1 YVRPVG 025 K29JAN29GN\n" +
                "53.SSR SEAT AC HK1 YVRPVG 025 K29JAN29HN\n" +
                "54.SSR SEAT AC HK1 YVRPVG 025 K29JAN29JN\n" +
                "55.SSR SEAT AC HK1 YVRPVG 025 K29JAN29KN\n" +
                "56.SSR SEAT AC HK1 YVRPVG 025 K29JAN28HN                                       +\n" +
                "57.SSR AUTH AC HK/ YVRPVG 025 K29JAN /PSGI/BJSAC/HSGS/11JAN9                   -58.SSR AUTH AC HK/ YVRPVG 025 K29JAN /PSGI/BJSAC/HSGS/11JAN9\n" +
                "59.SSR AUTH AC HK/ YVRPVG 025 K29JAN /PSGI/BJSAC/HSGS/11JAN9\n" +
                "60.SSR AUTH AC HK/ YVRPVG 025 K29JAN /PSGI/BJSAC/HSGS/11JAN9\n" +
                "61.SSR AUTH AC HK/ YVRPVG 025 K29JAN /PSGI/BJSAC/HSGS/11JAN9\n" +
                "62.SSR AUTH AC HK/ YVRPVG 025 K29JAN /PSGI/BJSAC/HSGS/11JAN9\n" +
                "63.SSR AUTH AC HK/ YVRPVG 025 K29JAN /PSGI/BJSAC/HSGS/11JAN9\n" +
                "64.SSR AUTH AC HK/ YVRPVG 025 K29JAN /PSGI/BJSAC/HSGS/11JAN9\n" +
                "65.SSR AUTH AC HK/ YVRPVG 025 K29JAN /PSGI/BJSAC/HSGS/11JAN9\n" +
                "66.SSR AUTH AC HK/ YVRPVG 025 K29JAN /PSGI/BJSAC/CCGS/14JAN9\n" +
                "67.SSR TKNE AC HK1 PVGYVR 026 K22JAN 0143421395672/1/P10\n" +
                "68.SSR TKNE AC HK1 PVGYVR 026 K22JAN 0143421395671/1/P9                        +\n" +
                "69.SSR TKNE AC HK1 PVGYVR 026 K22JAN 0143421395670/1/P8                        -70.SSR TKNE AC HK1 PVGYVR 026 K22JAN 0143421395669/1/P7 \n" +
                "71.SSR TKNE AC HK1 PVGYVR 026 K22JAN 0143421395668/1/P6 \n" +
                "72.SSR TKNE AC HK1 PVGYVR 026 K22JAN 0143421395667/1/P5 \n" +
                "73.SSR TKNE AC HK1 PVGYVR 026 K22JAN 0143421395666/1/P4 \n" +
                "74.SSR TKNE AC HK1 PVGYVR 026 K22JAN 0143421395665/1/P3 \n" +
                "75.SSR TKNE AC HK1 PVGYVR 026 K22JAN 0143421395664/1/P2 \n" +
                "76.SSR TKNE AC HK1 PVGYVR 026 K22JAN 0143421395663/1/P1 \n" +
                "77.SSR TKNE AC HK1 YVRPVG 025 K29JAN 0143421395663/2/P1 \n" +
                "78.SSR TKNE AC HK1 YVRPVG 025 K29JAN 0143421395664/2/P2 \n" +
                "79.SSR TKNE AC HK1 YVRPVG 025 K29JAN 0143421395665/2/P3 \n" +
                "80.SSR TKNE AC HK1 YVRPVG 025 K29JAN 0143421395666/2/P4                        +\n" +
                "81.SSR TKNE AC HK1 YVRPVG 025 K29JAN 0143421395667/2/P5                        -82.SSR TKNE AC HK1 YVRPVG 025 K29JAN 0143421395668/2/P6 \n" +
                "83.SSR TKNE AC HK1 YVRPVG 025 K29JAN 0143421395669/2/P7 \n" +
                "84.SSR TKNE AC HK1 YVRPVG 025 K29JAN 0143421395670/2/P8 \n" +
                "85.SSR TKNE AC HK1 YVRPVG 025 K29JAN 0143421395671/2/P9 \n" +
                "86.SSR TKNE AC HK1 YVRPVG 025 K29JAN 0143421395672/2/P10\n" +
                "87.SSR DOCS AC HK1 P/CN/E22242014/CN/22JAN83/M/01SEP23/ZHANG/SHIJIE/P9  \n" +
                "88.SSR DOCS AC HK1 P/CN/EB2045102/CN/17DEC90/F/17OCT27/ZHOU/LAN/P10 \n" +
                "89.SSR DOCS AC HK1 P/CN/G55882237/CN/11MAY58/F/09OCT21/ZHANG/RUIJUAN/P8 \n" +
                "90.SSR DOCS AC HK1 P/CN/G41437237/CN/27OCT60/F/21MAR20/ZHANG/RUIFEN/P7  \n" +
                "91.SSR DOCS AC HK1 P/CN/G55882238/CN/11AUG64/F/09OCT20/ZHANG/RUIDI/P6   \n" +
                "92.SSR DOCS AC HK1 P/CN/G43643491/CN/28NOV88/M/22JUN20/CHEN/SHIHAO/P1          +\n" +
                "93.SSR DOCS AC HK1 P/CN/E22242014/CN/22JAN83/M/01SEP23/ZHANG/SHIJIE/P9         -94.SSR DOCS AC HK1 P/CN/G37528414/CN/25NOV77/F/15SEP19/XU/SIRONG/P5 \n" +
                "95.SSR DOCS AC HK1 P/CN/EB2045102/CN/17DEC90/F/17OCT27/ZHOU/LAN/P10 \n" +
                "96.SSR DOCS AC HK1 P/CN/G36251321/CN/26MAR84/F/09AUG19/RONG/JIAWEI/P4   \n" +
                "97.SSR DOCS AC HK1 P/CN/G55882239/CN/29AUG87/F/09OCT21/HE/YIJUN/P2  \n" +
                "98.SSR DOCS AC HK1 P/CN/G55882237/CN/11MAY58/F/09OCT21/ZHANG/RUIJUAN/P8 \n" +
                "99.SSR DOCS AC HK1 P/CN/G41437237/CN/27OCT60/F/21MAR20/ZHANG/RUIFEN/P7  \n" +
                "100.SSR DOCS AC HK1 P/CN/G55882238/CN/11AUG64/F/09OCT20/ZHANG/RUIDI/P5  \n" +
                "101.SSR DOCS AC HK1 P/CN/E76239035/CN/18OCT93/F/02MAR26/QIAN/CHENMIN/P3 \n" +
                "102.OSI YY  GRPN SHA ZHUYUAN EB \n" +
                "103.OSI AC CTCT13771561010  \n" +
                "104.OSI AC CTCT13912353278                                                     +\n" +
                "105.RMK TJ SHA205                                                              -106.RMK NT/FOP L-/08301812CLM   \n" +
                "107.RMK GRP1/GR01/MS01/PVGPVG   \n" +
                "108.RMK GRMS/MC-0000000000/000000/QHCMBM20180709/QHCMBM \n" +
                "109.RMK SC-BJS  \n" +
                "110.RMK NET FARE CNY1750 BJSHSGS09JUL   \n" +
                "111.RMK ON T/A EMAIL REQ CHG OB TO 22JAN AC 26 N IB TO 29JAN AC25 BJSCCGS24JU   112.RMK L   \n" +
                "113.RMK ALREADY FULL PAY CNY57629 FOR 13PAX..WITH LETTER BJSHSGS21DEC   \n" +
                "114.RMK ON T/A EMAIL REQ KEEP 10Y N CXL 3Y NO ADM DUE TO MEET GRP RATE BJSCCG   115.RMK S14JAN  \n" +
                "116.RMK AC/QHCMBM                                                              +\n" +
                "117.FN/IT//SCNY1750.00/C0.00/XCNY2683.00/TCNY90.00CN/TCNY134.00CA/TCNY2459.00XT-118.TN/014-3421395663/P1\n" +
                "119.TN/014-3421395664/P2\n" +
                "120.TN/014-3421395665/P3\n" +
                "121.TN/014-3421395666/P4\n" +
                "122.TN/014-3421395667/P5\n" +
                "123.TN/014-3421395668/P6\n" +
                "124.TN/014-3421395669/P7\n" +
                "125.TN/014-3421395670/P8\n" +
                "126.TN/014-3421395671/P9\n" +
                "127.TN/014-3421395672/P10   \n" +
                "128.FP/CASH,CNY                                                                +\n" +
                "129.SHA205";

        pnrDto.setPnrDetail(pnrDetail);

        assertEquals(2, pnrDto.getSegCount());
        assertEquals(0, pnrDto.getPsgCount());
        assertEquals("HMVVRE", pnrDto.getPnrNo());
        assertEquals("", pnrDto.getBigPnrNo());

        assertEquals("SHA205", pnrDto.getBookOfficeNo());
        assertEquals(1, pnrDto.getDzStatus());
    }

    @Test
    void test_pnr_detail_5() {
        final String pnrDetail = "  **ELECTRONIC TICKET PNR** \n" +
                " 1.倪无瑕 2.徐向东 HMYRGW   \n" +
                " 3.  ZH9158 E   FR18JAN  WUXPEK RR2   0935 1145          E T2T3 \n" +
                " 4.SHA/T SHA/T 0510-82722200/CHINA COMFORT WUXI TRAVEL SERVICE/CHEN RU ABCDEFG   5.BOOK SSH 0108 1731   \n" +
                " 6.BOOK SSH 0108 1734   \n" +
                " 7.BOOK SSH 0108 1748   \n" +
                " 8.T\n" +
                " 9.SSR FOID ZH HK1 NI320211196811012817/P2  \n" +
                "10.SSR FOID ZH HK1 NI320203197608081221/P1  \n" +
                "11.SSR CKIN ZH  \n" +
                "12.SSR CKIN ZH                                                                 +\n" +
                "13.SSR FQTV ZH HK1 WUXPEK 9158 E18JAN CA001357214782/P2                        -14.SSR FQTV ZH HK1 WUXPEK 9158 E18JAN CA005335307243/P1 \n" +
                "15.SSR ADTK 1E BY SHA08JAN19/1930 OR CXL ZH9158 E18JAN  \n" +
                "16.SSR TKNE ZH HK1 WUXPEK 9158 E18JAN 4793405572437/1/P2\n" +
                "17.SSR TKNE ZH HK1 WUXPEK 9158 E18JAN 4793405572436/1/P1\n" +
                "18.OSI ZH CTCM13606172772/P2\n" +
                "19.OSI ZH CTCT15852807325   \n" +
                "20.RMK CMS/A/** \n" +
                "21.RMK MP 13606172772   \n" +
                "22.RMK TJ SHA205\n" +
                "23.RMK CA/MWGH7T\n" +
                "24.RMK AUTOMATIC FARE QUOTE                                                    +\n" +
                "25.RMK 无预存款机票                                                        -26.FN/A/FCNY770.00/SCNY770.00/C0.00/XCNY50.00/TCNY50.00CN/TEXEMPTYQ/ACNY820.00  27.TN/479-3405572436/P1 \n" +
                "28.TN/479-3405572437/P2 \n" +
                "29.FP/CASH,CNY  \n" +
                "30.SHA205";

        pnrDto.setPnrDetail(pnrDetail);

        assertEquals(1, pnrDto.getSegCount());
        assertEquals(2, pnrDto.getPsgCount());
        assertEquals("HMYRGW", pnrDto.getPnrNo());
        assertEquals("MWGH7T", pnrDto.getBigPnrNo());

        assertEquals(770, pnrDto.getPrice());

        assertEquals("SHA205", pnrDto.getBookOfficeNo());
        assertEquals(1, pnrDto.getDzStatus());
    }


    @Test
    void test_pnr_detail_6() {
        final String pnrDetail = " SHA205 REPLY RCD     (  0000  )  (  0000  )\n" +
                "  **ELECTRONIC TICKET PNR** \n" +
                " 1.田启玲 JGG3Q6\n" +
                " 2.  CA4572 Y   TH17JAN  ZUHCKG HX1   1135 1355          E --T3 \n" +
                " 3.  CA4560 Y   TH17JAN  ZUHCKG RR1   1835 2105          E --T3 \n" +
                " 4.SHA/T SHA/T 0510-82722200/CHINA COMFORT WUXI TRAVEL SERVICE/CHEN RU ABCDEFG  \n" +
                " 5.BOOK SZDW 0116 1756  \n" +
                " 6.T\n" +
                " 7.SSR FOID CA HK1 NI500106199201168524/P1  \n" +
                " 8.SSR OTHS 1E CA   \n" +
                " 9.SSR OTHS 1E CA BKG CXLD DUE TO TKT TIME EXPIRED  \n" +
                "10.SSR ADTK 1E BY SHA04JAN19/1128 OR CXL CA ALL SEGS                           +\n" +
                "11.SSR TKNE CA HK1 ZUHCKG 4560 Y17JAN 9993405571558/1 /A/P1                    -\n" +
                "12.OSI CA CTCT13771845114   \n" +
                "13.OSI CA CTCM15358087508/P1\n" +
                "14.OSI CA CTCM18302355560/P1\n" +
                "15.RMK MP 18875248083   \n" +
                "16.RMK MP 18302355560   \n" +
                "17.RMK MP 13983895337   \n" +
                "18.RMK MP 18580573303   \n" +
                "19.RMK MP 15730026521   \n" +
                "20.RMK TJ SHA205\n" +
                "21.RMK AUTOMATIC FARE QUOTE \n" +
                "22.RMK CA/PV8C6R                                                               +\n" +
                "23.TN/999-3405571558/P1                                                        -\n" +
                "24.FP/CASH,CNY  \n" +
                "25.SHA205";

        pnrDto.setPnrDetail(pnrDetail);

        assertEquals(2, pnrDto.getSegCount());
        assertEquals(1, pnrDto.getPsgCount());
        assertEquals("JGG3Q6", pnrDto.getPnrNo());
        assertEquals("PV8C6R", pnrDto.getBigPnrNo());

        assertEquals(0, pnrDto.getPrice());

        assertEquals("SHA205", pnrDto.getBookOfficeNo());
        assertEquals(1, pnrDto.getDzStatus());

        assertEquals("田启玲", pnrDto.getPassengers().get(0).getPsgName());
        assertEquals("999-3405571558", pnrDto.getPassengers().get(0).getTicketNo());
    }

    @Test
    void test_pnr_detail_7() {
        final String pnrDetail = "MARRIED SEGMENT EXIST IN THE PNR\n" +
                "  **ELECTRONIC TICKET PNR** \n" +
                " 1.SHEN/JUN MR 2.SHEN/YIKANG CHD 3.SONG/LILI MS JN5S0H  \n" +
                " 4.  AY088  Z1  TU29JAN  PVGHEL HK3   1005 1420      SEAME  2 2 \n" +
                " 5.  AY915  Z1  TU29JAN  HELOSL HK3   1605 1635      SEAME  2-- \n" +
                " 6.  AY914  R2  WE06FEB  OSLHEL HK3   1315 1540      SEAME -- 2 \n" +
                " 7.  AY087  R2  WE06FEB  HELPVG HK3   1725 0815+1    SEAME  2 2 \n" +
                " 8.SHA/T SHA/T 0510-82722200/CHINA COMFORT WUXI TRAVEL SERVICE/CHEN RU ABCDEFG   9.13651757240-SYSTEM   \n" +
                "10.021-51069999 \n" +
                "11.95010\n" +
                "12.T                                                                           +\n" +
                "13.SSR TKNE AY HK1 PVGHEL 088 Z29JAN 1053115139118/1/P3                        -14.SSR TKNE AY HK1 PVGHEL 088 Z29JAN 1053115139117/1/P2 \n" +
                "15.SSR TKNE AY HK1 PVGHEL 088 Z29JAN 1053115139116/1/P1 \n" +
                "16.SSR TKNE AY HK1 HELOSL 915 Z29JAN 1053115139116/2/P1 \n" +
                "17.SSR TKNE AY HK1 OSLHEL 914 R06FEB 1053115139116/3/P1 \n" +
                "18.SSR TKNE AY HK1 HELPVG 087 R06FEB 1053115139116/4/P1 \n" +
                "19.SSR TKNE AY HK1 HELOSL 915 Z29JAN 1053115139117/2/P2 \n" +
                "20.SSR TKNE AY HK1 OSLHEL 914 R06FEB 1053115139117/3/P2 \n" +
                "21.SSR TKNE AY HK1 HELPVG 087 R06FEB 1053115139117/4/P2 \n" +
                "22.SSR TKNE AY HK1 HELOSL 915 Z29JAN 1053115139118/2/P3 \n" +
                "23.SSR TKNE AY HK1 OSLHEL 914 R06FEB 1053115139118/3/P3 \n" +
                "24.SSR TKNE AY HK1 HELPVG 087 R06FEB 1053115139118/4/P3                        +\n" +
                "25.SSR DOCS AY HK1 P/CN/E47682875/CN/20OCT82/F/25MAR25/SONG/LILI/P3            -26.SSR DOCS AY HK1 P/CN/E43151206/CN/04JAN11/M/22JAN20/SHEN/YIKANG/P2   \n" +
                "27.SSR DOCS AY HK1 P/CN/E43151207/CN/19NOV82/M/22JAN25/SHEN/JUN/P1  \n" +
                "28.SSR CHLD AY HK1 04JAN11/P2   \n" +
                "29.SSR CTCE AY HK1  GPYYXGXPZ//CTRIP.COM/P1 \n" +
                "30.SSR CTCM AY HK1 13764482350/P1   \n" +
                "31.OSI AY CHD FREE TEXT 1SHEN/YIKANG/P2 \n" +
                "32.OSI AY CTCT13651757240   \n" +
                "33.RMK TJ SHA205\n" +
                "34.RMK TJ AUTH SHA717   \n" +
                "35.RMK 1A/OVTKI9\n" +
                "                                                                               +\n" +
                "36.FN/A/IT//SCNY1570.00/C0.00/XCNY3130.00/TEXEMPTCN/TCNY40.00DQ/TCNY3090.00XT  -    /P2 \n" +
                "37.FN/A/IT//SCNY2090.00/C0.00/XCNY3220.00/TCNY90.00CN/TCNY40.00DQ/TCNY3090.00XT 38.TN/105-3115139116/P1 \n" +
                "39.TN/105-3115139117/P2 \n" +
                "40.TN/105-3115139118/P3 \n" +
                "41.FP/CASH,CNY  \n" +
                "42.SHA205";

        pnrDto.setPnrDetail(pnrDetail);

        assertEquals(4, pnrDto.getSegCount());
        assertEquals(3, pnrDto.getPsgCount());
        assertEquals("JN5S0H", pnrDto.getPnrNo());
        assertEquals("", pnrDto.getBigPnrNo());

        //FN/A/IT 表示是净价票，所以没有ACNY项（总收客户）
        assertEquals(0, pnrDto.getPrice());
        assertEquals(2090, pnrDto.getParValue());
        assertEquals(3220, pnrDto.getTax());
//        assertEquals(3130, pnrDto.ge());

        assertEquals(0, pnrDto.getChdPrice());
        assertEquals(1570, pnrDto.getChdParValue());
        assertEquals(3130, pnrDto.getChdTax());

        assertEquals("SHA205", pnrDto.getBookOfficeNo());
        assertEquals(1, pnrDto.getDzStatus());

        assertEquals("SHEN/JUN", pnrDto.getPassengers().get(0).getPsgName());
        assertEquals("105-3115139116", pnrDto.getPassengers().get(0).getTicketNo());

        assertEquals("SHEN/YIKANG", pnrDto.getPassengers().get(1).getPsgName());
        assertEquals("105-3115139117", pnrDto.getPassengers().get(1).getTicketNo());

        assertEquals("SONG/LILI", pnrDto.getPassengers().get(2).getPsgName());
        assertEquals("105-3115139118", pnrDto.getPassengers().get(2).getTicketNo());
    }

    @Test
    void test_pnr_detail_8() {
        final String pnrDetail = "MARRIED SEGMENT EXIST IN THE PNR\n" +
                "  **ELECTRONIC TICKET PNR** \n" +
                " 1.CHEN/YUE 2.GUAN/XINGPING 3.HE/ZIQING 4.LIN/LI 5.LU/JUEYI 6.MA/XINGSHUO   \n" +
                " 7.WANG/YIMENG 8.YU/ANGE 9.ZHOU/ZHENGQIAN HFPD94\n" +
                "10.    ARNK              OULHEL \n" +
                "11.  AY087  S   SU03FEB  HELPVG HK9   1725 0815+1    SEAME  2 2 \n" +
                "12.SHA/T SHA/T 0510-82722200/CHINA COMFORT WUXI TRAVEL SERVICE/CHEN RU ABCDEFG  13.BOOK SHZFL3 1211 2320\n" +
                "14.BOOK SHZFL5 1212 1002\n" +
                "15.T\n" +
                "16.SSR OTHS 1E CHANGE   \n" +
                "17.SSR OTHS 1E INVOL RR DUE TO AY DISRUPTION REROUTED FROM AY441 18JAN19       +\n" +
                "18.SSR OTHS 1E INVOL RR DUE TO AY DISRUPTION REROUTED FROM AY88 18JAN19        -19.SSR OTHS 1E INVOL RR DUE TO AY DISRUPTION REROUTED FROM AY441 18JAN19\n" +
                "20.SSR OTHS 1E INVOL RR DUE TO AY DISRUPTION REROUTED FROM AY88 18JAN19 \n" +
                "21.SSR OTHS 1E INVOL RR DUE TO AY DISRUPTION REROUTED FROM AY441 18JAN19\n" +
                "22.SSR OTHS 1E INVOL RR DUE TO AY DISRUPTION REROUTED FROM AY88 18JAN19 \n" +
                "23.SSR OTHS 1E INVOL RR DUE TO AY DISRUPTION REROUTED FROM AY441 18JAN19\n" +
                "24.SSR OTHS 1E INVOL RR DUE TO AY DISRUPTION REROUTED FROM AY88 18JAN19 \n" +
                "25.SSR OTHS 1E INVOL RR DUE TO AY DISRUPTION REROUTED FROM AY441 18JAN19\n" +
                "26.SSR OTHS 1E INVOL RR DUE TO AY DISRUPTION REROUTED FROM AY88 18JAN19 \n" +
                "27.SSR OTHS 1E INVOL RR DUE TO AY DISRUPTION REROUTED FROM AY441 18JAN19\n" +
                "28.SSR OTHS 1E INVOL RR DUE TO AY DISRUPTION REROUTED FROM AY88 18JAN19 \n" +
                "29.SSR OTHS 1E INVOL RR DUE TO AY DISRUPTION REROUTED FROM AY441 18JAN19       +\n" +
                "30.SSR OTHS 1E INVOL RR DUE TO AY DISRUPTION REROUTED FROM AY88 18JAN19        -31.SSR OTHS 1E INVOL RR DUE TO AY DISRUPTION REROUTED FROM AY441 18JAN19\n" +
                "32.SSR OTHS 1E INVOL RR DUE TO AY DISRUPTION REROUTED FROM AY88 18JAN19 \n" +
                "33.SSR OTHS 1E INVOL RR DUE TO AY DISRUPTION REROUTED FROM AY441 18JAN19\n" +
                "34.SSR OTHS 1E INVOL RR DUE TO AY DISRUPTION REROUTED FROM AY88 18JAN19 \n" +
                "35.SSR OTHS 1E CHANGE IF REBOOKED. FARE RULES STILL APPLY   \n" +
                "36.SSR OTHS 1E TIMELIMIT FOR THIS PNR WILL NOT  \n" +
                "37.SSR OTHS 1E IF NO TKT IS ISSUED BY 22DEC2018/2359 SHA TIME   \n" +
                "38.SSR OTHS 1E AY RESERVES THE RIGHT TO AUTOCANCEL OR SEND ADM  \n" +
                "39.SSR OTHS QR HN1 PVGDOH 871 B18JAN TKNO 1052464950361/P1  \n" +
                "40.SSR OTHS QR HN1 DOHHEL 307 B19JAN TKNO 1052464950361/P1  \n" +
                "41.SSR OTHS QR HN1 PVGDOH 871 B18JAN TKNO 1052464950362/P2                     +\n" +
                "42.SSR OTHS QR HN1 DOHHEL 307 B19JAN TKNO 1052464950362/P2                     -43.SSR OTHS QR HN1 PVGDOH 871 B18JAN TKNO 1052464950363/P3  \n" +
                "44.SSR OTHS QR HN1 DOHHEL 307 B19JAN TKNO 1052464950363/P3  \n" +
                "45.SSR OTHS QR HN1 PVGDOH 871 B18JAN TKNO 1052464950364/P4  \n" +
                "46.SSR OTHS QR HN1 DOHHEL 307 B19JAN TKNO 1052464950364/P4  \n" +
                "47.SSR OTHS QR HN1 PVGDOH 871 B18JAN TKNO 1052464950365/P5  \n" +
                "48.SSR OTHS QR HN1 DOHHEL 307 B19JAN TKNO 1052464950365/P5  \n" +
                "49.SSR OTHS QR HN1 PVGDOH 871 B18JAN TKNO 1052464950366/P6  \n" +
                "50.SSR OTHS QR HN1 DOHHEL 307 B19JAN TKNO 1052464950366/P6  \n" +
                "51.SSR OTHS QR HN1 PVGDOH 871 B18JAN TKNO 1052464950367/P7  \n" +
                "52.SSR OTHS QR HN1 DOHHEL 307 B19JAN TKNO 1052464950367/P7  \n" +
                "53.SSR OTHS QR HN1 PVGDOH 871 B18JAN TKNO 1052464950368/P8                     +\n" +
                "54.SSR OTHS QR HN1 DOHHEL 307 B19JAN TKNO 1052464950368/P8                     -55.SSR OTHS QR HN1 PVGDOH 871 B18JAN TKNO 1052464950369/P9  \n" +
                "56.SSR OTHS QR HN1 DOHHEL 307 B19JAN TKNO 1052464950369/P9  \n" +
                "57.SSR RQST QR HK1 DOHHEL 307 B19JAN 22JN/P9\n" +
                "58.SSR RQST QR  KK1 PVGDOH 871 B18JAN 12CN/P9   \n" +
                "59.SSR RQST QR HK1 DOHHEL 307 B19JAN 27DN/P5\n" +
                "60.SSR RQST QR  KK1 PVGDOH 871 B18JAN 17EN/P5   \n" +
                "61.SSR RQST QR HK1 DOHHEL 307 B19JAN 33DN/P6\n" +
                "62.SSR RQST QR  KK1 PVGDOH 871 B18JAN 20KN/P6   \n" +
                "63.SSR RQST QR HK1 DOHHEL 307 B19JAN 34JN/P8\n" +
                "64.SSR RQST QR  KK1 PVGDOH 871 B18JAN 15CN/P8   \n" +
                "65.SSR RQST QR HK1 DOHHEL 307 B19JAN 36FN/P7                                   +\n" +
                "66.SSR RQST QR  KK1 PVGDOH 871 B18JAN 17JN/P7                                  -67.SSR RQST QR HK1 DOHHEL 307 B19JAN 31BN/P7\n" +
                "68.SSR RQST QR  KK1 PVGDOH 871 B18JAN 31AN/P7   \n" +
                "69.SSR RQST QR HK1 DOHHEL 307 B19JAN 36FN/P1\n" +
                "70.SSR RQST QR  KK1 PVGDOH 871 B18JAN 16EN/P1   \n" +
                "71.SSR RQST QR HK1 DOHHEL 307 B19JAN 20DN/P2\n" +
                "72.SSR RQST QR  KK1 PVGDOH 871 B18JAN 16FN/P2   \n" +
                "73.SSR RQST QR  KK1 PVGDOH 871 B18JAN 16AN/P2   \n" +
                "74.SSR RQST QR  KK1 PVGDOH 871 B18JAN 31KN/P2   \n" +
                "75.SSR RQST QR  KK1 PVGDOH 871 B18JAN 11CN/P7   \n" +
                "76.SSR RQST QR HK1 DOHHEL 307 B19JAN 17FN/P3\n" +
                "77.SSR RQST QR  KK1 PVGDOH 871 B18JAN 39EN/P3                                  +\n" +
                "78.SSR RQST QR  KK1 PVGDOH 871 B18JAN 31AN/P3                                  -79.SSR RQST QR HN1 PVGDOH 871 B18JAN 15A/P3 \n" +
                "80.SSR RQST QR HK1 DOHHEL 307 B19JAN 22DN/P1\n" +
                "81.SSR RQST QR  KK1 PVGDOH 871 B18JAN 31AN/P1   \n" +
                "82.SSR RQST QR HN1 PVGDOH 871 B18JAN 15B/P1 \n" +
                "83.SSR TKNE AY HK1 HELPVG 087 S03FEB 1053115444451/4/P1 \n" +
                "84.SSR TKNE AY HK1 HELPVG 087 S03FEB 1053115444452/4/P2 \n" +
                "85.SSR TKNE AY HK1 HELPVG 087 S03FEB 1053115444453/4/P3 \n" +
                "86.SSR TKNE AY HK1 HELPVG 087 S03FEB 1053115444454/4/P4 \n" +
                "87.SSR TKNE AY HK1 HELPVG 087 S03FEB 1053115444455/4/P5 \n" +
                "88.SSR TKNE AY HK1 HELPVG 087 S03FEB 1053115444456/4/P6 \n" +
                "89.SSR TKNE AY HK1 HELPVG 087 S03FEB 1053115444457/4/P7                        +\n" +
                "90.SSR TKNE AY HK1 HELPVG 087 S03FEB 1053115444458/4/P8                        -91.SSR TKNE AY HK1 HELPVG 087 S03FEB 1053115444459/4/P9 \n" +
                "92.SSR DOCS QR HK1 P/CN/EE5870681/CN/06FEB97/M//GUAN/XINGPING/P2\n" +
                "93.SSR DOCS QR HK1 P/CN/E97559698/CN/09FEB97/F/23MAR27/ZHOU/ZHENGQIAN/P9\n" +
                "94.SSR DOCS QR HK1 P/CN/EA9734511/CN/26APR99/F/09AUG27/YU/ANGE/P8   \n" +
                "95.SSR DOCS QR HK1 P/CN/E29565866/CN/11JUN98/F/01SEP24/MA/XINGSHUO/P6   \n" +
                "96.SSR DOCS QR HK1 P/CN/ED7886941/CN/08OCT98/F/22JUL28/LU/JUEYI/P5  \n" +
                "97.SSR DOCS QR HK1 P/CN/E63778138/CN/04SEP97/F/23NOV25/LIN/LI/P4\n" +
                "98.SSR DOCS QR HK1 P/CN/E22694669/CN/31DEC96/F/01JUL23/HE/ZIQING/P3 \n" +
                "99.SSR DOCS QR HK1 P/CN/EB9970929/CN/07MAY95/F/16JAN28/CHEN/YUE/P1  \n" +
                "100.SSR DOCS QR HK1 P/CN/E48861700/CN/26FEB99/F//WANG/YIMENG/P7 \n" +
                "101.SSR DOCS AY HK1 P/CN/EA9734511/CN/26APR99/F/09AUG27/YU/ANGE/P8             +\n" +
                "102.SSR DOCS AY HK1 P/CN/E22694669/CN/31DEC96/F/01JUL23/HE/ZIQING/P3           -103.SSR DOCS AY HK1 P/CN/E63778138/CN/04SEP97/F/23NOV25/LIN/LI/P4   \n" +
                "104.SSR DOCS AY HK1 P/CN/EE5870681/CN/06FEB97/M/18NOV28/GUAN/XINGPING/P2\n" +
                "105.SSR DOCS AY HK1 P/CN/EB9970929/CN/07MAY95/F/16JAN28/CHEN/YUE/P1 \n" +
                "106.SSR DOCS AY HK1 P/CN/E48861700/CN/26FEB99/F/19APR25/WANG/YIMENG/P7  \n" +
                "107.SSR DOCS AY HK1 P/CN/E29565866/CN/11JUN98/F/01SEP24/MA/XINGSHUO/P6  \n" +
                "108.SSR DOCS AY HK1 P/CN/ED7886941/CN/08OCT98/F/22JUL28/LU/JUEYI/P5 \n" +
                "109.SSR DOCS AY HK1 P/CN/E97559698/CN/09FEB97/F/23MAR27/ZHOU/ZHENGQIAN/P9   \n" +
                "110.SSR DOCS QR HK1 PVGDOH 871 B18JAN P/CHN/E63778138/CHN/04SEP97/F/23NOV25/\n" +
                "    /LIN/LI/P4  \n" +
                "111.SSR DOCS QR HK1 DOHHEL 307 B19JAN P/CHN/E63778138/CHN/04SEP97/F/23NOV25/\n" +
                "    /LIN/LI/P4                                                                 +\n" +
                "112.SSR DOCS QR HK1 PVGDOH 871 B18JAN P/CHN/E29565866/CHN/11JUN98/F/01SEP24/MA -    /XINGSHUO/P6\n" +
                "113.SSR DOCS QR HK1 DOHHEL 307 B19JAN P/CHN/E29565866/CHN/11JUN98/F/01SEP24/MA      /XINGSHUO/P6\n" +
                "114.SSR DOCS QR HK1 PVGDOH 871 B18JAN P/CHN/E97559698/CHN/09FEB97/F/23MAR27/\n" +
                "    /ZHOU/ZHENGQIAN/P9  \n" +
                "115.SSR DOCS QR HK1 DOHHEL 307 B19JAN P/CHN/E97559698/CHN/09FEB97/F/23MAR27/\n" +
                "    /ZHOU/ZHENGQIAN/P9  \n" +
                "116.SSR DOCS QR HK1 PVGDOH 871 B18JAN P/CHN/EA9734511/CHN/26APR99/F/09AUG27/YU      /ANGE/P8\n" +
                "117.SSR DOCS QR HK1 DOHHEL 307 B19JAN P/CHN/EA9734511/CHN/26APR99/F/09AUG27/YU      /ANGE/P8                                                                   +\n" +
                "118.SSR DOCS QR HK1 PVGDOH 871 B18JAN P/CHN/EB9970929/CHN/07MAY95/F/16JAN28/   -    /CHEN/YUE/P1\n" +
                "119.SSR DOCS QR HK1 DOHHEL 307 B19JAN P/CHN/EB9970929/CHN/07MAY95/F/16JAN28/\n" +
                "    /CHEN/YUE/P1\n" +
                "120.SSR DOCS QR HK1 PVGDOH 871 B18JAN P/CHN/E22694669/CHN/31DEC96/F/01JUL23/HE      /ZIQING/P3  \n" +
                "121.SSR DOCS QR HK1 DOHHEL 307 B19JAN P/CHN/E22694669/CHN/31DEC96/F/01JUL23/HE      /ZIQING/P3  \n" +
                "122.SSR DOCS QR HK1 PVGDOH 871 B18JAN P/CHN/EE5870681/CHN/06FEB97/M/18NOV28/\n" +
                "    /GUAN/XINGPING/P2   \n" +
                "123.SSR DOCS QR HK1 DOHHEL 307 B19JAN P/CHN/EE5870681/CHN/06FEB97/M/18NOV28/\n" +
                "    /GUAN/XINGPING/P2                                                          +\n" +
                "124.SSR DOCS QR HK1 PVGDOH 871 B18JAN P/CHN/E48861700/CHN/26FEB99/F/19APR25/   -    /WANG/YIMENG/P7 \n" +
                "125.SSR DOCS QR HK1 DOHHEL 307 B19JAN P/CHN/E48861700/CHN/26FEB99/F/19APR25/\n" +
                "    /WANG/YIMENG/P7 \n" +
                "126.SSR DOCS QR HK1 PVGDOH 871 B18JAN P/CHN/ED7886941/CHN/08OCT98/F/22JUL28/LU      /JUEYI/P5   \n" +
                "127.SSR DOCS QR HK1 DOHHEL 307 B19JAN P/CHN/ED7886941/CHN/08OCT98/F/22JUL28/LU      /JUEYI/P5   \n" +
                "128.SSR CTCE QR HK1 /1010353765//QQ.COM/P2  \n" +
                "129.SSR CTCM QR HK1 /8613564560841/EN/P7\n" +
                "130.SSR CTCM AY HK1 13671867039/P1  \n" +
                "131.OSI AY CTCT13817889253                                                     +\n" +
                "132.RMK TJ SHA205                                                              -133.RMK XUEXIANG\n" +
                "134.RMK 1A/WTIAFA   \n" +
                "135.FN/A/IT//SCNY5190.00/C0.00/XCNY3102.00/TCNY90.00CN/TCNY40.00DQ/TCNY2972.00XT    \n" +
                "136.TN/105-3115444451/P1\n" +
                "137.TN/105-3115444452/P2\n" +
                "138.TN/105-3115444453/P3\n" +
                "139.TN/105-3115444454/P4\n" +
                "140.TN/105-3115444455/P5\n" +
                "141.TN/105-3115444456/P6\n" +
                "142.TN/105-3115444457/P7                                                       +\n" +
                "143.TN/105-3115444458/P8                                                       -144.TN/105-3115444459/P9\n" +
                "145.FP/CASH,CNY \n" +
                "146.SHA205";

        pnrDto.setPnrDetail(pnrDetail);

        assertEquals(1, pnrDto.getSegCount());
        assertEquals("SHA205", pnrDto.getBookOfficeNo());

    }

    @Test
    void test_pnr_detail_9() {
        final String pnrDetail = "  **ELECTRONIC TICKET PNR** \n" +
                " 1.JIN/JUNJIE MSTR 2.JIN/WENLONG MR JMLCXF  \n" +
                " 3.  OZ366  S   SA02FEB  PVGICN HK2   1625 1920      CLAME  \n" +
                " 4.  OZ363  S   TU12FEB  ICNPVG HK2   1050 1155      CLAME  \n" +
                " 5.SHA/T SHA/T 0510-82722200/CHINA COMFORT WUXI TRAVEL SERVICE/CHEN RU ABCDEFG   6.KTO SONG/HUA \n" +
                " 7.0086-186-1639-7322-H \n" +
                " 8.SONGHUA0318 GMAIL.COM-H  \n" +
                " 9.13011474 QQ.COM-H\n" +
                "10.86-177-0170-9125 \n" +
                "11.BOOK SHENCHEN 1214 1046  \n" +
                "12.BOOK SHENCHEN 1214 1047                                                     +\n" +
                "13.T                                                                           -14.SSR OTHS 1A AUTH TYPE-MP \n" +
                "15.SSR OTHS 1A AUTH ITR-PVG/ICN/PVG \n" +
                "16.SSR OTHS 1A AUTH NBR-8CCBM5MS-2 12/12\n" +
                "17.SSR OTHS 1A AUTH TTL-2 AD-2 CH-0 IN-0\n" +
                "18.SSR OTHS 1A AUTH F/B-SHCK S CLS  \n" +
                "19.SSR OTHS  OZ RSVN IS 0170-9125 \n" +
                "20.SSR FQTV OZ HK1 OZ374655644/P2   \n" +
                "21.SSR TKNE OZ HK1 PVGICN 366 S02FEB 9883115444854/1/P2 \n" +
                "22.SSR TKNE OZ HK1 PVGICN 366 S02FEB 9883115444853/1/P1 \n" +
                "23.SSR TKNE OZ HK1 ICNPVG 363 S12FEB 9883115444853/2/P1 \n" +
                "24.SSR TKNE OZ HK1 ICNPVG 363 S12FEB 9883115444854/2/P2                        +\n" +
                "25.SSR DOCS OZ HK1  P/CHN/EB6843771/CHN/06DEC80/M/28NOV27/JIN/WENLONG/P2       -26.SSR FQTV OZ HK1 OZ797487671/P1   \n" +
                "27.SSR DOCS OZ HK1  P/CHN/E40100381/CHN/25DEC08/M/15DEC19/JIN/JUNJIE/P1 \n" +
                "28.SSR TKNM OZ HK1 PVGICN 366 S02FEB 9884435982871/P2   \n" +
                "29.SSR TKNM OZ HK1 ICNPVG 363 S12FEB 9884435982871/P2   \n" +
                "30.SSR CTCM OZ HK1 15852543065/P1   \n" +
                "31.OSI OZ CTCT13961841012   \n" +
                "32.RMK TJ SHA205\n" +
                "33.RMK I-AUTH REQUESTED \n" +
                "34.RMK 1A/TAWMC8\n" +
                "35.FN/FCNY1500.00/SCNY1350.00/C0.00/XCNY1076.00/TCNY90.00CN/TCNY172.00BP/   \n" +
                "    TCNY814.00YQ/ACNY2576.00                                                   +\n" +
                "36.FN/FCNY1130.00/SCNY1020.00/C0.00/XCNY986.00/TEXEMPTCN/TCNY172.00BP/         -    TCNY814.00YQ/ACNY2116.00/P1 \n" +
                "37.TN/988-3115444853/P1 \n" +
                "38.TN/988-3115444854/P2 \n" +
                "39.FP/CASH,CNY  \n" +
                "40.SHA205";

        pnrDto.setPnrDetail(pnrDetail);

        assertEquals(2, pnrDto.getSegCount());
        assertEquals("SHA205", pnrDto.getBookOfficeNo());

    }

    @Test
    void test_pnr_detail_10() {
        final String pnrDetail = "SHA205 SCHEDULE CHG  (  0000  )  (  0000  )\n" +
                "MARRIED SEGMENT EXIST IN THE PNR\n" +
                "  **ELECTRONIC TICKET PNR** \n" +
                " 1.CHEN/YAFANG MS 2.DAI/NA MS 3.GONG/WEI MR 4.WU/YAMING MR 5.ZHOU/XINGJU MS \n" +
                "   HNCL2K   \n" +
                " 6.  UA836  G1  MO25FEB  PVGORD HK5   1700 1625      SEAME  2 5 \n" +
                " 7.  UA246  G1  MO25FEB  ORDTPA HK5   1823 2206      SEAME  1-- \n" +
                " 8.  UA2051 L2  FR28JUN  TPASFO HK5   0710 0940      SEAME -- 3 \n" +
                " 9.  UA891  L2  FR28JUN  SFOPVG HK5   1635 2030+1    SEAME  I 2 \n" +
                "10.SHA/T SHA/T 0510-82722200/CHINA COMFORT WUXI TRAVEL SERVICE/CHEN RU ABCDEFG  \n" +
                "11.BOOK BSPET04D 0125 1423  \n" +
                "12.BOOK BSPET04D 0125 1633                                                     +\n" +
                "13.T                                                                           -\n" +
                "14.SSR SEAT UA  KK1 PVGORD 836 G25FEB 32CC/P5   \n" +
                "15.SSR SEAT UA  KK1 PVGORD 836 G25FEB 32DC/P3   \n" +
                "16.SSR SEAT UA  KK1 PVGORD 836 G25FEB 37EC/P1   \n" +
                "17.SSR SEAT UA  UN1 PVGORD 836 G25FEB 21CC/P5   \n" +
                "18.SSR SEAT UA  UN1 PVGORD 836 G25FEB 21DC/P3   \n" +
                "19.SSR SEAT UA  UN1 PVGORD 836 G25FEB 38EF/P1   \n" +
                "20.SSR SEAT UA  KK1 ORDTPA 246 G25FEB 27DF/P1   \n" +
                "21.SSR SEAT UA  KK1 ORDTPA 246 G25FEB 27AF/P2   \n" +
                "22.SSR SEAT UA  KK1 ORDTPA 246 G25FEB 27EF/P3   \n" +
                "23.SSR SEAT UA  KK1 ORDTPA 246 G25FEB 26FF/P4   \n" +
                "24.SSR SEAT UA  KK1 ORDTPA 246 G25FEB 27FF/P5                                  +\n" +
                "25.SSR ADTK 1E HK5 .TICKETING MAY BE REQUIRED BY FARE RULE                     -\n" +
                "26.SSR ADTK 1E HK5 .TKT UA SEGS BY 28JAN19 TO AVOID AUTO CXL /EARLIER   \n" +
                "27.SSR TKNE UA HK1 PVGORD 836 G25FEB 0163423129689/1/P5 \n" +
                "28.SSR TKNE UA HK1 PVGORD 836 G25FEB 0163423129688/1/P4 \n" +
                "29.SSR TKNE UA HK1 PVGORD 836 G25FEB 0163423129687/1/P3 \n" +
                "30.SSR TKNE UA HK1 PVGORD 836 G25FEB 0163423129686/1/P2 \n" +
                "31.SSR TKNE UA HK1 PVGORD 836 G25FEB 0163423129685/1/P1 \n" +
                "32.SSR TKNE UA HK1 ORDTPA 246 G25FEB 0163423129685/2/P1 \n" +
                "33.SSR TKNE UA HK1 TPASFO 2051 L28JUN 0163423129685/3/P1\n" +
                "34.SSR TKNE UA HK1 SFOPVG 891 L28JUN 0163423129685/4/P1 \n" +
                "35.SSR TKNE UA HK1 ORDTPA 246 G25FEB 0163423129686/2/P2 \n" +
                "36.SSR TKNE UA HK1 TPASFO 2051 L28JUN 0163423129686/3/P2                       +\n" +
                "37.SSR TKNE UA HK1 SFOPVG 891 L28JUN 0163423129686/4/P2                        -\n" +
                "38.SSR TKNE UA HK1 ORDTPA 246 G25FEB 0163423129687/2/P3 \n" +
                "39.SSR TKNE UA HK1 TPASFO 2051 L28JUN 0163423129687/3/P3\n" +
                "40.SSR TKNE UA HK1 SFOPVG 891 L28JUN 0163423129687/4/P3 \n" +
                "41.SSR TKNE UA HK1 ORDTPA 246 G25FEB 0163423129688/2/P4 \n" +
                "42.SSR TKNE UA HK1 TPASFO 2051 L28JUN 0163423129688/3/P4\n" +
                "43.SSR TKNE UA HK1 SFOPVG 891 L28JUN 0163423129688/4/P4 \n" +
                "44.SSR TKNE UA HK1 ORDTPA 246 G25FEB 0163423129689/2/P5 \n" +
                "45.SSR TKNE UA HK1 TPASFO 2051 L28JUN 0163423129689/3/P5\n" +
                "46.SSR TKNE UA HK1 SFOPVG 891 L28JUN 0163423129689/4/P5 \n" +
                "47.SSR DOCS UA HK1 P/CN/G41100993/CN/16APR78/F/02MAR20/CHEN/YAFANG/P1   \n" +
                "48.SSR DOCS UA HK1 P/CN/E22266316/CN/31OCT79/F/05JUN23/ZHOU/XINGJU/P5          +\n" +
                "49.SSR DOCS UA HK1 P/CN/EE3219427/CN/21NOV81/M/13SEP28/WU/YAMING/P4            -\n" +
                "50.SSR DOCS UA HK1 P/CN/E56289462/CN/16APR82/M/23JUL25/GONG/WEI/P3  \n" +
                "51.SSR DOCS UA HK1 P/CN/EF0076009/CN/08AUG81/F/20DEC28/DAI/NA/P2\n" +
                "52.SSR CTCM UA HK1 13063879952/P2   \n" +
                "53.SSR OTHS 1E UA 836 25FEB ADV PAX FLT ARRIVES TERMINAL-5 ADV PAX FLT DEPARTS  \n" +
                "    TERMINAL-2  \n" +
                "54.SSR OTHS 1E UA 836 25FEB BUSINESSFIRST OFFERED THIS FLIGHT   \n" +
                "55.SSR OTHS 1E UA 246 25FEB ADV PAX FLT DEPARTS TERMINAL-1  \n" +
                "56.SSR OTHS 1E UA 2051 28JUN ADV PAX FLT ARRIVES TERMINAL-3 \n" +
                "57.SSR OTHS 1E UA 891 28JUN ADV PAX FLT ARRIVES TERMINAL-2 ADV PAX FLT DEPARTS  \n" +
                "    TERMINAL-I  \n" +
                "58.SSR OTHS 1E UA 891 28JUN BUSINESSFIRST OFFERED THIS FLIGHT                  +\n" +
                "59.OSI UA CTCT13063879952                                                      -\n" +
                "60.RMK MP 13912662133   \n" +
                "61.RMK TJ SHA202\n" +
                "62.RMK UA/NL4WYM\n" +
                "63.RMK TJ AUTH SHA202   \n" +
                "64.RMK TJ AUTH SHA255   \n" +
                "65.FN/A/IT//SCNY2570.00/C0.00/XCNY3168.00/TCNY90.00CN/TCNY76.00AY/TCNY3002.00XT \n" +
                "66.TN/016-3423129685/P1 \n" +
                "67.TN/016-3423129686/P2 \n" +
                "68.TN/016-3423129687/P3 \n" +
                "69.TN/016-3423129688/P4 \n" +
                "70.TN/016-3423129";

        pnrDto.setPnrDetail(pnrDetail);

        assertEquals(4, pnrDto.getSegCount());
        assertEquals("6/7/8/9", pnrDto.getFlightItemNo());
        assertEquals("", pnrDto.getBookOfficeNo());

    }

    @Test
    void test_pnr_detail_11() {
        final String pnrDetail = "1.赵颖 HNNLTK  \n" +
                " 2.  CZ3594 Y   SU19JAN  PVGCGO HK1   1915 2125          E T2T2 \n" +
                " 3.SHA/T SHA/T021-6276220418917099670/SHANGHAI SHANGYOU INTERNATIONAL TRAVEL\n" +
                "     SERVICE CO., ABCDEFG   \n" +
                " 4.365 200111 1154  \n" +
                " 5.TL/1900/11JAN/SHA274 \n" +
                " 6.SSR FOID CZ HK1 NIED0788164/P1   \n" +
                " 7.SSR ADTK 1E BY SHA12JAN20/1155 OR CXL CZ BOOKING \n" +
                " 8.OSI CZ CTCT15301883082   \n" +
                " 9.OSI CZ CTCM18917007892/P1\n" +
                "10.RMK IC CZ/1903833\n" +
                "11.RMK CA/MGXFXL                                                               +\n" +
                "\u007FPAT:A#CDK1903833                                                               \n" +
                ">PAT:A#CDK1903833   \n" +
                "01 Y FARE:CNY1280.00 TAX:CNY50.00 YQ:TEXEMPTYQ  TOTAL:1330.00   \n" +
                "\u007FSFC:01   \u007FSFN:01";

        pnrDto.setPnrDetail(pnrDetail);

        assertEquals(1, pnrDto.getSegCount());
        assertEquals("2", pnrDto.getFlightItemNo());
        assertEquals("", pnrDto.getBookOfficeNo());
        assertEquals("MGXFXL", pnrDto.getBigPnrNo());
        assertEquals("CZ", pnrDto.getCarrier());
        assertEquals("1903833", pnrDto.getKeyCustomerNo());
    }

    @Test
    void test_pnr_detail_12() {
        final String pnrDetail = "1.王峰 JV9M4V  \n" +
                " 2.  CZ6755 Y   TU14JAN  PVGDDG HK1   1320 1535          E T2-- \n" +
                " 3.SHA/T SHA/T021-6276220418917099670/SHANGHAI SHANGYOU INTERNATIONAL TRAVEL\n" +
                "     SERVICE CO., ABCDEFG   \n" +
                " 4.TL/1320/14JAN/SHA274 \n" +
                " 5.SSR FOID CZ HK1 NI610521198302151533/P1  \n" +
                " 6.SSR ADTK 1E BY SHA13JAN20/1230 OR CXL CZ BOOKING \n" +
                " 7.OSI CZ CTCT15301883082   \n" +
                " 8.OSI CZ CTCM17887928867/P1\n" +
                " 9.RMK IC CZ/1310165\n" +
                "10.RMK CA/PZFMD2\n" +
                "11.SHA274   \n" +
                "\u007FPAT:A#CDK1310165                                                               \n" +
                ">PAT:A#CDK1310165   \n" +
                "01 Y FARE:CNY1290.00 TAX:CNY50.00 YQ:TEXEMPTYQ  TOTAL:1340.00   \n" +
                "\u007FSFC:01   \u007FSFN:01";

        pnrDto.setPnrDetail(pnrDetail);

        assertEquals(1, pnrDto.getSegCount());
        assertEquals("2", pnrDto.getFlightItemNo());
        assertEquals("SHA274", pnrDto.getBookOfficeNo());
        assertEquals("PZFMD2", pnrDto.getBigPnrNo());
        assertEquals("CZ", pnrDto.getCarrier());
        assertEquals("1310165", pnrDto.getKeyCustomerNo());
    }

    @Test
    void test_pnr_detail_13() {
        final String pnrDetail = "1.何章明 2.朱柏华 KT1KDN                                                       \n" +
                " 3.  FM9307 R   WE26AUG  SHACAN RR2   1030 1255          E T2T1                 \n" +
                " 4.SHA/T SHA/T 021-62227779/SHA JETOUR TRAVEL CO.,LTD/WU BIN ABCDEFG            \n" +
                " 5.T                                                                            \n" +
                " 6.SSR FOID FM HK1 NI310106198310244012/P1                                      \n" +
                " 7.SSR FOID FM HK1 NI310108196306132812/P2                                      \n" +
                " 8.SSR ADTK 1E BY SHA24AUG20/1952 OR CXL FM9307 R26AUG                          \n" +
                " 9.SSR TKNE FM HK1 SHACAN 9307 R26AUG 7814837530075/1/P2                        \n" +
                "10.SSR TKNE FM HK1 SHACAN 9307 R26AUG 7814837530074/1/P1                        \n" +
                "11.OSI FM CTCM18116299207/P2                                                    \n" +
                "12.OSI FM CTCM18616577543/P1                                                   +\n" +
                "\u0010                                                                               \n" +
                "                                                                                \n" +
                "                                                                                \n" +
                "\u0010pn                                                                             \n" +
                "13.OSI FM CTCT15901952020                                                      -\n" +
                "14.RMK CMS/A/**                                                                 \n" +
                "15.RMK MP 18116299207/P2                                                        \n" +
                "16.RMK MP 18616577543/P1                                                        \n" +
                "17.RMK TJ SHA820                                                                \n" +
                "18.RMK CID 132611/JINGANFENJU                                                   \n" +
                "19.RMK TID 2716381/P2                                                           \n" +
                "20.RMK TID 2716330/P1                                                           \n" +
                "21.RMK BKFARE KDGYN GP NULLNULL R SHACAN 26AUG/CNY910.0                         \n" +
                "22.RMK BOOKING FROM OK 1ETRIP                                                   \n" +
                "23.RMK 0086761598259126404                                                      \n" +
                "24.RMK CA/MFQ0HQ                                                               +\n" +
                "\u0010                                                                               \n" +
                "                                                                                \n" +
                "                                                                                \n" +
                "\u0010pn                                                                             \n" +
                "25.RMK AUTOMATIC FARE QUOTE                                                    -\n" +
                "26.RMK FARECODE/GP/FM9307/SHACAN/26AUG20/CNY910.00                              \n" +
                "27.FN/A/FCNY910.00/SCNY910.00/RCNY910.00/BCNY50.00/C0.00/XCNY50.00/TCNY50.00CN/   \n" +
                "    TEXEMPTYQ/ACNY960.00                                                        \n" +
                "28.TN/781-4837530074/P1                                                         \n" +
                "29.TN/781-4837530075/P2                                                         \n" +
                "30.FP/CC/UP6225XXXXXXXX0158 0000 1920.00CNY 0000                                \n" +
                "31.SHA820";

        pnrDto.setPnrDetail(pnrDetail);

        assertEquals(1, pnrDto.getSegCount());
        assertEquals("3", pnrDto.getFlightItemNo());
        assertEquals("SHA820", pnrDto.getBookOfficeNo());
        assertEquals("KT1KDN", pnrDto.getPnrNo());
        assertEquals("MFQ0HQ", pnrDto.getBigPnrNo());
        assertEquals("FM", pnrDto.getCarrier());
//        assertEquals("1310165", pnrDto.getKeyCustomerNo());
    }

    @Test
    void test_pnr_detail_14() {
        final String pnrDetail = "   **ELECTRONIC TICKET PNR** \n" +
                " 1.黄月旻 2.石云松 JFDY12 \n" +
                " 3.  MU5529 L   WE16SEP  SHATAO RR2   0905 1045          E T2-- \n" +
                " 4.  MU5520 R   TH17SEP  TAOSHA RR2   1515 1655          E --T2 \n" +
                " 5.SHA/T SHA/T021-6276220418917099670/SHANGHAI SHANGYOU INTERNATIONAL TRAVEL\n" +
                "     SERVICE CO., ABCDEFG   \n" +
                " 6.209 200909 1949  \n" +
                " 7.T  \n" +
                " 8.SSR FOID MU HK1 NI310230198402191494/P2  \n" +
                " 9.SSR FOID MU HK1 NI31010819890903054X/P1  \n" +
                "10.SSR FQTV MU HK1 SHATAO 5529 L16SEP MU614014322262/P2 \n" +
                "11.SSR FQTV MU HK1 SHATAO 5529 L16SEP MU660403596708/P1                        +      12.SSR FQTV MU HK1 TAOSHA 5520 R17SEP MU614014322262/P2                        -13.SSR FQTV MU HK1 TAOSHA 5520 R17SEP MU660403596708/P1 \n" +
                "14.SSR ADTK 1E BY SHA10SEP20/1951 OR CXL MU5529 L16SEP  \n" +
                "15.SSR TKNE MU HK1 SHATAO 5529 L16SEP 7815462429763/1/P2\n" +
                "16.SSR TKNE MU HK1 SHATAO 5529 L16SEP 7815462429762/1/P1\n" +
                "17.SSR TKNE MU HK1 TAOSHA 5520 R17SEP 7815462429763/2/P2\n" +
                "18.SSR TKNE MU HK1 TAOSHA 5520 R17SEP 7815462429762/2/P1\n" +
                "19.OSI MU CTCT15301883082   \n" +
                "20.OSI MU CTCM18917006892/P1\n" +
                "21.OSI MU CTCM13661832356/P2\n" +
                "22.RMK CMS/A/** \n" +
                "23.RMK TJ SHA274                                                               +      24.RMK CA/PYELRG                                                               -25.RMK AUTOMATIC FARE QUOTE \n" +
                "26.FN/A/FCNY1490.00/SCNY1490.00/C0.00/XCNY100.00/TCNY100.00CN/TEXEMPTYQ/\n" +
                "    ACNY1590.00 \n" +
                "27.TN/781-5462429762/P1 \n" +
                "28.TN/781-5462429763/P2 \n" +
                "29.FP/CASH,CNY  \n" +
                "30.SHA274   ";

        pnrDto.setPnrDetail(pnrDetail);

        assertEquals(2, pnrDto.getSegCount());
        assertEquals("3/4", pnrDto.getFlightItemNo());
        assertEquals("SHA274", pnrDto.getBookOfficeNo());
        assertEquals("JFDY12", pnrDto.getPnrNo());
        assertEquals("PYELRG", pnrDto.getBigPnrNo());
        assertEquals("MU", pnrDto.getCarrier());

        assertEquals(1490, pnrDto.getPrice());
        assertEquals(1490, pnrDto.getParValue());
//        assertEquals(0, pnrDto.getai());
        assertEquals(100, pnrDto.getTax());
        assertEquals(1590, pnrDto.getTotal());
    }

    @Test
    void test_pnr_detail_15() {
        final String pnrDetail = "   **ELECTRONIC TICKET PNR** \n" +
                " 1.毕舒林 2.高银峰 3.张富 KEPRBZ\n" +
                " 4.  CA4516 L   MO14SEP  SHACTU RR3   1200 1520          E T2T2 \n" +
                " 5.SHA/T SHA/T021-6276220418917099670/SHANGHAI SHANGYOU INTERNATIONAL TRAVEL\n" +
                "     SERVICE CO., ABCDEFG   \n" +
                " 6.2355352540 200910 1331   \n" +
                " 7.T  \n" +
                " 8.SSR FOID CA HK1 NI340521198811214218/P3  \n" +
                " 9.SSR FOID CA HK1 NI320483198501117033/P2  \n" +
                "10.SSR FOID CA HK1 NI340825198703270214/P1  \n" +
                "11.SSR FQTV CA HK1 SHACTU 4516 L14SEP CA000759837330/P2 \n" +
                "12.SSR ADTK 1E BY SHA10SEP20/1433 OR CXL CA NON-TKT SEGS                       +      13.SSR TKNE CA HK1 SHACTU 4516 L14SEP 9995462429797/1/P3                       -14.SSR TKNE CA HK1 SHACTU 4516 L14SEP 9995462429796/1/P2\n" +
                "15.SSR TKNE CA HK1 SHACTU 4516 L14SEP 9995462429795/1/P1\n" +
                "16.OSI CA CTCT18930300224   \n" +
                "17.OSI CA CTCM18655360286/P1\n" +
                "18.OSI CA CTCM13363947439/P2\n" +
                "19.OSI CA CTCM15055313502/P3\n" +
                "20.RMK CMS/A/** \n" +
                "21.RMK TJ SHA274\n" +
                "22.RMK CA/NH6S0R\n" +
                "23.RMK AUTOMATIC FARE QUOTE \n" +
                "24.FN/A/FCNY800.00/SCNY800.00/C0.00/XCNY50.00/TCNY50.00CN/TEXEMPTYQ/ACNY850.00 +      25.TN/999-5462429795/P1                                                        -26.TN/999-5462429796/P2 \n" +
                "27.TN/999-5462429797/P3 \n" +
                "28.FP/CASH,CNY  \n" +
                "29.SHA274   ";

        pnrDto.setPnrDetail(pnrDetail);

        assertEquals(800, pnrDto.getPrice());
        assertEquals(800, pnrDto.getParValue());
        assertEquals(50, pnrDto.getTax());
        assertEquals(850, pnrDto.getTotal());
    }

    @Test
    void test_pnr_detail_16() {
        final String pnrDetail = "1.陈堃 2.倪健 KWTFWB                                                           \n" +
                " 3.  FM9192 N   WE30SEP  DLCPVG RR2   1315 1520          E --T1                 \n" +
                " 4.SHA/T SHA/T 021-62227779/SHA JETOUR TRAVEL CO.,LTD/WU BIN ABCDEFG            \n" +
                " 5.T                                                                            \n" +
                " 6.SSR FOID FM HK1 NI310107198310075737/P1                                      \n" +
                " 7.SSR FOID FM HK1 NI31010819790719401X/P2                                      \n" +
                " 8.SSR FQTV FM HK1 DLCPVG 9192 N30SEP MU664023829177/P1                         \n" +
                " 9.SSR FQTV FM HK1 DLCPVG 9192 N30SEP MU644019610443/P2                         \n" +
                "10.SSR ADTK 1E BY SHA29SEP20/1328 OR CXL FM9192 N30SEP                          \n" +
                "11.SSR TKNE FM HK1 DLCPVG 9192 N30SEP 7815468288485/1/P2                        \n" +
                "12.SSR TKNE FM HK1 DLCPVG 9192 N30SEP 7815468288484/1/P1                       +\n" +
                "\u0010                                                                               \n" +
                "                                                                                \n" +
                "                                                                                \n" +
                "\u0010pn                                                                             \n" +
                "13.OSI FM CTCM18616577543/P2                                                   -\n" +
                "14.OSI FM CTCM18116299207/P1                                                    \n" +
                "15.OSI FM CTCT15901952020                                                       \n" +
                "16.RMK CMS/A/**                                                                 \n" +
                "17.RMK MP 18616577543/P2                                                        \n" +
                "18.RMK MP 18116299207/P1                                                        \n" +
                "19.RMK TJ SHA820                                                                \n" +
                "20.RMK CID 132611/JINGANFENJU                                                   \n" +
                "21.RMK TID 2716601/P2                                                           \n" +
                "22.RMK TID 2716296/P1                                                           \n" +
                "23.RMK BKFARE KMZYN GP NULLNULL N DLCPVG 30SEP/CNY690.0                         \n" +
                "24.RMK BOOKING FROM OK 1ETRIP                                                  +\n" +
                "\u0010                                                                               \n" +
                "                                                                                \n" +
                "                                                                                \n" +
                "\u0010pn                                                                             \n" +
                "25.RMK 0346951601346501184                                                     -\n" +
                "26.RMK CA/NH0SPK                                                                \n" +
                "27.RMK AUTOMATIC FARE QUOTE                                                     \n" +
                "28.RMK FARECODE/GP/FM9192/DLCPVG/30SEP20/CNY690.00                              \n" +
                "29.FN/A/FCNY690.00/SCNY690.00/RCNY690.00/BCNY50.00/C0.00/XCNY50.00/TCNY50.00CN/   \n" +
                "    TEXEMPTYQ/ACNY740.00                                                        \n" +
                "30.TN/781-5468288484/P1                                                         \n" +
                "31.TN/781-5468288485/P2                                                         \n" +
                "32.FP/CC/UP6225XXXXXXXX0158 0000 1480.00CNY 0000                                \n" +
                "33.SHA820";

        pnrDto.setPnrDetail(pnrDetail);

        assertEquals(690, pnrDto.getPrice());
        assertEquals(690, pnrDto.getParValue());
        assertEquals(50, pnrDto.getTax());
        assertEquals(740, pnrDto.getTotal());
    }

  @Test
  void test_pnr_detail_17() {
    final String pnrDetail = "rtHEQXNM\n" +
            " **ELECTRONIC TICKET PNR** \n" +
            " 1.CAO/ZHI 2.NIU/SHUAI 3.WEI/GUICONG HEQXNM  \n" +
            " 2.  W5081 Y   SA22MAR  IKACAN RR3   0520 1600          E T2T1 \n" +
            " 3.SHA/T SHA/T 021-6276241315301883082/SHA SHANGYOU INT TRAVEL SERVICE CO., \n" +
            "     LTD/YINBO ABCDEFG";

    pnrDto.setPnrDetail(pnrDetail);

    assertEquals("HEQXNM", pnrDto.getPnrNo());
    assertEquals(1, pnrDto.getFlights().size());
    assertEquals(3, pnrDto.getPassengers().size());

    FlightInfoDto fltDto = pnrDto.getFlights().get(0).getFlight();
    assertEquals("W5081", fltDto.getFlightNo());
    assertEquals("Y", fltDto.getSubclass());
    assertEquals("IKA", fltDto.getDport());
    assertEquals("CAN", fltDto.getAport());
    assertEquals("T2", fltDto.getDterm());
    assertEquals("T1", fltDto.getAterm());
    assertEquals("0520", fltDto.getDtime());
    assertEquals("1600", fltDto.getAtime());
    assertEquals("2019-03-22", fltDto.getDdateFormatted());
  }

  @Test
  void test_pnr_detail_18() {
    final String pnrDetail = "rtJY1440                                                                       \n" +
            "  **ELECTRONIC TICKET PNR** \n" +
            " 1.LI/SHENG 2.于蕾 JY1440   \n" +
            " 3.  MU5126 Q   FR16APR  PEKSHA RR2   2000 2230          E T2T2 \n" +
            " 4.SHA/T SHA/T 021-6276241315301883082/SHA SHANGYOU INT TRAVEL SERVICE CO., \n" +
            "     LTD/YINBO ABCDEFG  \n" +
            " 5.227 210415 1730  \n" +
            " 6.T\n" +
            " 7.SSR FOID MU HK1 NI110106197806292120/P2  \n" +
            " 8.SSR FOID MU HK1 PP1347335/P1 \n" +
            " 9.SSR CKIN MU HK2 VICO4905095  \n" +
            "10.SSR FQTV MU HK1 PEKSHA 5126 Q16APR MU610284756009/P2 \n" +
            "11.SSR FQTV MU HK1 PEKSHA 5126 Q16APR MU640280988332/P1                        +\n" +
            "\u007Fpn                                                                             \n" +
            "12.SSR ADTK 1E BY SHA15APR21/2031 OR CXL MU5126 Q16APR                         -\n" +
            "13.SSR TKNE MU HK1 PEKSHA 5126 Q16APR 7816050837033/1/P2\n" +
            "14.SSR TKNE MU HK1 PEKSHA 5126 Q16APR 7816050837032/1/P1\n" +
            "15.OSI MU CTCT18602107616   \n" +
            "16.OSI MU CTCM13818791638/P2\n" +
            "17.RMK CMS/A/** \n" +
            "18.RMK TJ SHA274\n" +
            "19.RMK CA/NCDWMR\n" +
            "20.RMK AUTOMATIC FARE QUOTE \n" +
            "21.RMK FARECODE/4905095/MU5126/PEKSHA/16APR21/CNY2980.00\n" +
            "22.FN/A/FCNY2980.00/SCNY2980.00/C0.00/XCNY50.00/TCNY50.00CN/TEXEMPTYQ/  \n" +
            "    ACNY3030.00                                                                +\n" +
            "\u007Fpn                                                                             \n" +
            "23.TN/781-6050837032/P1                                                        -\n" +
            "24.TN/781-6050837033/P2 \n" +
            "25.FP/CASH,CNY/*4905095 \n" +
            "26.SHA274   ";

    pnrDto.setPnrDetail(pnrDetail);

    assertEquals("JY1440", pnrDto.getPnrNo());
    assertEquals("NCDWMR", pnrDto.getBigPnrNo());
    assertEquals(1, pnrDto.getFlights().size());
    assertEquals(2, pnrDto.getPassengers().size());


  }

  @Test
  void test_pnr_detail_19() {
    final String pnrDetail = "   **ELECTRONIC TICKET PNR** \n" +
            " 1.闫英虎 KFSSB8\n" +
            " 2.  ZH8606 W   SA15MAY  YBPHFE RR1   1615 1825          E  \n" +
            " 3.SHA/T SHA/T 021-6276241315301883082/SHA SHANGYOU INT TRAVEL SERVICE CO., \n" +
            "     LTD/YINBO ABCDEFG  \n" +
            " 4.SHQC02 210514 1007   \n" +
            " 5.T  \n" +
            " 6.SSR FOID ZH HK1 NI230602198210203215/P1  \n" +
            " 7.SSR CKIN ZH HK1 VICO1NK10FWR \n" +
            " 8.SSR CKIN ZH HK1 VICO1NK10FWR \n" +
            " 9.SSR ADTK 1E BY SHA14MAY21/1108 OR CXL ZH8606 W15MAY  \n" +
            "10.SSR TKNE ZH HK1 YBPHFE 8606 W15MAY 4796334724447/1/P1\n" +
            "11.OSI ZH CTCT18930300224                                                      \n" +
            "12.OSI ZH CTCM15055308387/P1\n" +
            "13.RMK CMS/A/** \n" +
            "14.RMK TJ SHA274\n" +
            "15.RMK CA/PG01V0\n" +
            "16.RMK AUTOMATIC FARE QUOTE \n" +
            "17.FN/A/FCNY870.00/SCNY870.00/C0.00/XCNY50.00/TCNY50.00CN/TEXEMPTYQ/ACNY920.00  18.TN/479-6334724447/P1 \n" +
            "19.FP/CASH,CNY/*1NK10FWR\n" +
            "20.SHA274   ";

    pnrDto.setPnrDetail(pnrDetail);

    assertEquals("KFSSB8", pnrDto.getPnrNo());
    assertEquals("PG01V0", pnrDto.getBigPnrNo());
    assertEquals(1, pnrDto.getFlights().size());
    assertEquals(1, pnrDto.getPassengers().size());

    var fltDto = pnrDto.getFlights().get(0);
//    " 2.  ZH8606 W   SA15MAY  YBPHFE RR1   1615 1825          E  \n" +
    assertEquals("ZH8606", fltDto.getFlight().getFlightNo());
    assertEquals("W", fltDto.getFlight().getSubclass());
    assertEquals("YBP", fltDto.getFlight().getDport());
    assertEquals("HFE", fltDto.getFlight().getAport());
    assertEquals("2021-05-15", fltDto.getFlight().getDdateFormatted());
    assertEquals("1615", fltDto.getFlight().getDtime());
    assertEquals("1825", fltDto.getFlight().getAtime());


  }


  @Test
  void test_pnr_detail_20() {
    final String pnrDetail = "rtHEXX57                                                                       \n" +
            "   **ELECTRONIC TICKET PNR**                                                     \n" +
            "  1.HUNG/CHEN CHOU HEXX57                                                        \n" +
            "  2.  BR712  Y   SA08JAN  TPEPVG HK1   1010 1205      SEAME  2 2                 \n" +
            "  3.SHA/T SHA/T-021-62839061 SHA DONG LI BUSINESS LTD.,CO /TANG/ZHEN FENG        \n" +
            "      ABCDEFG                                                                    \n" +
            "  4.T                                                                            \n" +
            "  5.SSR ADTK 1E TO BR BY 20OCT 1010 SHA TIME ZONE OTHERWISE WILL BE XLD          \n" +
            "  6.SSR TKNE BR HK1 TPEPVG 712 Y08JAN 6954857680418/1/P1                         \n" +
            "  7.SSR DOCS BR HK1 P/TW/02957300/TW/06JAN75/M/29JUN26/HUNG/CHEN CHOU/P1         \n" +
            "  8.SSR CTCM BR HK1 13661832356/P1                                               \n" +
            "  9.OSI BR CTCT15301883082                                                       \n" +
            " 10.RMK TJ SHA697                                                               +\n" +
            " \u0010                                                                               \n" +
            "                                                                                 \n" +
            "                                                                                 \n" +
            " \u0010pn                                                                             \n" +
            " 11.RMK 1A/54AJAK                                                               -\n" +
            " 12.FN/A/FTWD13800/ECNY3220.00/SCNY3220.00/C5.00/XCNY344.00/TCNY117.00TW/        \n" +
            "     TCNY97.00YQ/TCNY130.00YR/ACNY3564.00                                        \n" +
            " 13.TN/695-4857680418/P1                                                         \n" +
            " 14.FP/CASH,CNY/AGT08311306                                                      \n" +
            " 15.SHA697          ";

    pnrDto.setPnrDetail(pnrDetail);

    assertEquals("HEXX57", pnrDto.getPnrNo());
//    assertEquals("54AJAK", pnrDto.getBigPnrNo());

    assertEquals(1, pnrDto.getFlights().size());
    assertEquals(1, pnrDto.getPassengers().size());

    var fltDto = pnrDto.getFlights().get(0);
    assertEquals("BR712", fltDto.getFlight().getFlightNo());
    assertEquals("Y", fltDto.getFlight().getSubclass());
    assertEquals("TPE", fltDto.getFlight().getDport());
    assertEquals("PVG", fltDto.getFlight().getAport());
    assertEquals("2022-01-08", fltDto.getFlight().getDdateFormatted());
    assertEquals("1010", fltDto.getFlight().getDtime());
    assertEquals("1205", fltDto.getFlight().getAtime());


  }



  @Test
  void test_rtkt_detail_1() {
    final String pnrDetail = "\u0010rtkt:4794855654087                                                            \n" +
            "      SHENZHEN AIRLINES                                    08313045              \n" +
            "    不得签转,改期收费                           HFESZX       JETOUR TRAVEL       \n" +
            "                                     10DEC20    KS75LR/1ESHA820                  \n" +
            "    章进                                                     DEV-1               \n" +
            "                                                         60784      QSR M        \n" +
            "                                                                                 \n" +
            "      合肥          HFE  ZH  9900 E 12DEC 0620  OKYTJSF40                  20K   \n" +
            "                                                                                 \n" +
            "      深圳          SZX      VOID                                                \n" +
            "                                                                                 \n" +
            "      VOID                                                                       \n" +
            "    CNY  720.00A12DEC20HFE ZH SZX720.00CNY720.00END                              \n" +
            "                                                                                 \n" +
            "    CN    50.00                                                                  \n" +
            "    YQ   EXEMPTCASH(CNY)                                                         \n" +
            "    CNY  770.00                                                                  \n" +
            "                                             CNY720.00            18  50.00      \n" +
            "                      479";

    pnrDto.setPnrDetail(pnrDetail);

    assertEquals("KS75LR", pnrDto.getPnrNo());
    assertEquals("2020-12-10", DateUtil.formatDate(pnrDto.getEtdzDate(), DATE_YYYY_MM_DD));

    assertEquals(1, pnrDto.getPsgCount());
    assertEquals("章进", pnrDto.getPassengers().get(0).getPsgName());
    assertEquals("4794855654087", pnrDto.getPassengers().get(0).getTicketNo());

    assertEquals(1, pnrDto.getSegCount());
    assertEquals(1, pnrDto.getFlights().size());
    assertEquals("HFE", pnrDto.getFlights().get(0).getFlight().getDport());
    assertEquals("SZX", pnrDto.getFlights().get(0).getFlight().getAport());
    assertEquals("ZH9900", pnrDto.getFlights().get(0).getFlight().getFlightNo());
    assertEquals("E", pnrDto.getFlights().get(0).getFlight().getSubclass());
    assertEquals("2021-12-12", pnrDto.getFlights().get(0).getFlight().getDdateFormatted());
    assertEquals("0620", pnrDto.getFlights().get(0).getFlight().getDtime());


    assertEquals(720, pnrDto.getPrice());
    assertEquals(720, pnrDto.getParValue());
    assertEquals(50, pnrDto.getTax());
    assertEquals(770, pnrDto.getTotal());
    assertEquals(18, pnrDto.getCommission());
  }

    @Test
    void test_verifyTicketNo() {
        final String pnrDetail = "  **ELECTRONIC TICKET PNR**                                                     \n" +
                " 1.路平 2.孙佳璐 3.孙涛 4.田建峰 5.田崟珍 6.王建青                              \n" +
                "   HDV0RC                                                                       \n" +
                " 7.  NS3289 Y   TU05FEB  SJWHAK RR6   1255 1810          E T2--                 \n" +
                " 8.SHA/T SHA/T18917808789/SHANGHAI YINGHANG TICKETING AGENT LIMITED COMPANY/LI  \n" +
                "    SHIRONG ABCDEFG                                                             \n" +
                " 9.T                                                                            \n" +
                "10.SSR FOID NS HK1 NI132229197609274260/P6                                      \n" +
                "11.SSR FOID NS HK1 NI130528200101034824/P5                                      \n" +
                "12.SSR FOID NS HK1 NI132229197611084271/P4                                      \n" +
                "13.SSR FOID NS HK1 NI132229197902080115/P3                                      \n" +
                "14.SSR FOID NS HK1 NI130528200207170023/P2                                     +\n" +
                "15.SSR FOID NS HK1 NI132229197706050066/P1                                     -\n" +
                "16.SSR FQTV NS HK1 SJWHAK 3289 Y05FEB NS190000708/C/P1                          \n" +
                "17.SSR ADTK 1E BY SHA22DEC18/2300 OR CXL NS3289 Y05FEB                          \n" +
                "18.SSR TKNE NS HK1 SJWHAK 3289 Y05FEB 8363402779343/1/P6                        \n" +
                "19.SSR TKNE NS HK1 SJWHAK 3289 Y05FEB 8363402779342/1/P5                        \n" +
                "20.SSR TKNE NS HK1 SJWHAK 3289 Y05FEB 8363402779341/1/P4                        \n" +
                "21.SSR TKNE NS HK1 SJWHAK 3289 Y05FEB 8363402779340/1/P3                        \n" +
                "22.SSR TKNE NS HK1 SJWHAK 3289 Y05FEB 8363402779339/1/P2                        \n" +
                "23.SSR TKNE NS HK1 SJWHAK 3289 Y05FEB 8363402779338/1/P1                        \n" +
                "24.OSI NS CTCT18317021057                                                       \n" +
                "25.OSI NS CTCT15026628515                                                       \n" +
                "26.OSI NS CTCM17701608782/P1/2/3/4/5/6                                          \n" +
                "27.RMK CMS/A/**                                                                 \n" +
                "28.RMK TJ SHA360                                                                \n" +
                "29.RMK TJ AUTH SHA717                                                           \n" +
                "30.RMK CA/MVKPDZ                                                               +\n" +
                "31.RMK AUTOMATIC FARE QUOTE                                                    -\n" +
                "32.RMK FARECODE/SHASF001/NS3289/SJWHAK/05FEB19/CNY2100.00                       \n" +
                "33.FN/A/FCNY2100.00/SCNY2100.00/C0.00/XCNY80.00/TCNY50.00CN/TCNY30.00YQ/        \n" +
                "    ACNY2180.00                                                                 \n" +
                "34.TN/836-3402779338/P1                                                         \n" +
                "35.TN/836-3402779339/P2                                                         \n" +
                "36.TN/836-3402779340/P3                                                         \n" +
                "37.TN/836-3402779341/P4                                                         \n" +
                "38.TN/836-3402779342/P5                                                         \n" +
                "39.TN/836-3402779343/P6                                                         \n" +
                "40.FP/CASH,CNY/*SHASF001                                                        \n" +
                "41.SHA360           ";

        pnrDto.setPnrDetail(pnrDetail);

        assertEquals(1, pnrDto.getDzStatus());
        assertEquals(true, pnrDto.isEt());
        assertEquals(1, pnrDto.getSegCount());
        assertEquals(6, pnrDto.getPsgCount());
        assertEquals("7", pnrDto.getFlightItemNo());

        assertEquals("RR", pnrDto.getSegStatus());

        assertEquals("HDV0RC", pnrDto.getPnrNo());
        assertEquals("MVKPDZ", pnrDto.getBigPnrNo());
        assertEquals(6, pnrDto.getCtcmCount());
//        1.路平 2.孙佳璐 3.孙涛 4.田建峰 5.田崟珍 6.王建青
//        "34.TN/836-3402779338/P1                                                         \n" +
//                "35.TN/836-3402779339/P2                                                         \n" +
//                "36.TN/836-3402779340/P3                                                         \n" +
//                "37.TN/836-3402779341/P4                                                         \n" +
//                "38.TN/836-3402779342/P5                                                         \n" +
//                "39.TN/836-3402779343/P6                                                         \n" +
        assertEquals("836-3402779338", pnrDto.getTicketNoByName("路平"));
        assertEquals("836-3402779339", pnrDto.getTicketNoByName("孙佳璐"));
        assertEquals("836-3402779340", pnrDto.getTicketNoByName("孙涛"));
        assertEquals("836-3402779341", pnrDto.getTicketNoByName("田建峰"));
        assertEquals("836-3402779342", pnrDto.getTicketNoByName("田崟珍"));
        assertEquals("836-3402779343", pnrDto.getTicketNoByName("王建青"));

        assertEquals("836-3402779338", pnrDto.getTicketNoByIdNo("132229197706050066"));
        assertEquals("836-3402779339", pnrDto.getTicketNoByIdNo("130528200207170023"));
        assertEquals("836-3402779340", pnrDto.getTicketNoByIdNo("132229197902080115"));
        assertEquals("836-3402779341", pnrDto.getTicketNoByIdNo("132229197611084271"));
        assertEquals("836-3402779342", pnrDto.getTicketNoByIdNo("130528200101034824"));
        assertEquals("836-3402779343", pnrDto.getTicketNoByIdNo("132229197609274260"));


        FlightInfoDto flt = pnrDto.getFlights().get(0).getFlight();
        //NS3289 Y   TU05FEB  SJWHAK RR6   1255 1810          E T2--
        assertEquals("NS3289", flt.getFlightNo());
        assertEquals("Y", flt.getSubclass());
        assertEquals("2019-02-05", flt.getDdateFormatted());
        assertEquals("SJW", flt.getDport());
        assertEquals("HAK", flt.getAport());
        assertEquals("RR", flt.getSegmentStatus());
        assertEquals("1255", flt.getDtime());
        assertEquals("1810", flt.getAtime());
        assertEquals("T2", flt.getDterm());
        assertEquals("", flt.getAterm());

        assertEquals(1, pnrDto.getDzStatus());
    }


    @Test
    void testOfficeNo_True() {
        final String officeNo = "SHA205";

        boolean result = PnrDto.testOfficeNoFormat(officeNo);

        assertEquals(true, result);
    }

    @Test
    void testOfficeNo_False_2() {
        final String officeNo = "CTCT13";

        boolean result = PnrDto.testOfficeNoFormat(officeNo);

        assertEquals(false, result);
    }

    @Test
    void testPnr_True() {
        final String officeNo = "JES1B1";

        boolean result = PnrDto.testPnrFormat(officeNo);

        assertEquals(true, result);
    }

    @Test
    void testPnr_ReturnFalse() {
        final String officeNo = "AV:ZH";

        boolean result = PnrDto.testPnrFormat(officeNo);

        assertEquals(false, result);
    }

    @Test
    void testOfficeNo_False() {
        final String officeNo = "SHASHA";

        boolean result = PnrDto.testOfficeNoFormat(officeNo);

        assertEquals(false, result);
    }

    @Test
    void testEtermDateFormat() throws ParseException {
        final String ddate0 = "2018-04-24";
        final Date ddate = DateUtil.convertToDate(ddate0, "yyyy-MM-dd");

        final String cmdDate = DateUtil.formatDate(ddate, "ddMMM", Locale.ENGLISH).toUpperCase();
        assertEquals("24APR", cmdDate);
    }

    @Test
    void testTktlTime_0() {
        final String dtime = "1425";

        assertEquals("1325", PnrDto.calcTktlTime(dtime));
    }

    @Test
    void testTktlTime_1() {
        final String dtime = "0425";
        assertEquals("0325", PnrDto.calcTktlTime(dtime));

//        System.out.println(PnrDto.calcTktlTime(null));
    }

    @Test
    void test_regStr() {
        final String line1 = " 9.SSR TKNE NS HK1 HAKLYG 3240 K01FEB 8363402779388/1/P2";
        final String regStr = "^([ ]?[0-9]{1,3}\\.)";

        Matcher m = Pattern.compile(regStr).matcher(line1);

        final String line2 = "A29.SSR TKNE NS HK1 HAKLYG 3240 K01FEB 8363402779388/1/P2";

        Matcher m1 = Pattern.compile(regStr).matcher(line2);

    }

  @Test
  void test_rtkt_detail_2() {
    final String pnrDetail = "     CHINA EASTERN AIRLINES                               08320222              \n" +
            "   Q/NONEND RTOJ DISCT                         SHASHA   SHANGYOU                \n" +
            "                                    22DEC20    KM8M9Q/1ESHA274                  \n" +
            "   黄月旻                                                   DEV-01              \n" +
            "                                                        14601      3WP J        \n" +
            "                                                                                \n" +
            "     上海虹桥      SHA  MU  5663 L 28DEC 0855  OKRT/L                     20K   \n" +
            "                                                                                \n" +
            "     厦门          XMN  MU  5666 N 30DEC 1355  OKRT/N                     20K   \n" +
            "                                                                                \n" +
            "     上海虹桥      SHA                                                          \n" +
            "   CNY 1770.00A28DEC20SHA MU XMN960.00MU SHA810.00CNY1770.00END**4905095        \n" +
            "                                                                                \n" +
            "   CN   100.00                                                                  \n" +
            "   YQ   EXEMPTCASH(CNY) *4905095                                                \n" +
            "   CNY 1870.00                                                                  \n" +
            "                                            CNY1770.00           20  100.00     \n" +
            "                     781              ";

    pnrDto.setRtktDetail(pnrDetail);

    assertEquals("KM8M9Q", pnrDto.getPnrNo());
    assertEquals("2020-12-22", DateUtil.formatDate(pnrDto.getEtdzDate(), DATE_YYYY_MM_DD));

    assertEquals(1, pnrDto.getPsgCount());
    assertEquals("黄月旻", pnrDto.getPassengers().get(0).getPsgName());

    assertEquals(2, pnrDto.getSegCount());
    assertEquals(2, pnrDto.getFlights().size());

    PnrFlightDto flt =  pnrDto.getFlights().get(0);
    assertEquals("SHA", flt.getFlight().getDport());
    assertEquals("XMN", flt.getFlight().getAport());
    assertEquals("MU5663", flt.getFlight().getFlightNo());
    assertEquals("L", flt.getFlight().getSubclass());
    assertEquals("2021-12-28", flt.getFlight().getDdateFormatted());
    assertEquals("0855", flt.getFlight().getDtime());

    flt =  pnrDto.getFlights().get(1);
    assertEquals("XMN", flt.getFlight().getDport());
    assertEquals("SHA", flt.getFlight().getAport());
    assertEquals("MU5666", flt.getFlight().getFlightNo());
    assertEquals("N", flt.getFlight().getSubclass());
    assertEquals("2021-12-30", flt.getFlight().getDdateFormatted());
    assertEquals("1355", flt.getFlight().getDtime());


    assertEquals(1770, pnrDto.getPrice());
    assertEquals(1770, pnrDto.getParValue());
    assertEquals(100, pnrDto.getTax());
    assertEquals(1870, pnrDto.getTotal());
    assertEquals(20, pnrDto.getCommission());
  }

  @Test
  void test_rtkt_detail_3() {
    final String pnrDetail = "    JI XIANG AIRLINE                                    08320222                \n" +
            "   Q/NONEND/PENALTY APPLIES SHA20074       OSANKG       SHANGYOU                \n" +
            "                                  18DEC20  KR0WD8/1E    SHA274                  \n" +
            "   XIAO/YANG MS                                         DEV-02                  \n" +
            "                                                            14582  OMR N        \n" +
            "     KANSAI        KIXHO  1609 M 21JAN 1700  OKMOWDDA         21JAN21JAN2PC     \n" +
            "     Nan Jing      NKG    VOID                                                  \n" +
            "     VOID                 VOID                                                  \n" +
            "     VOID                 VOID                                                  \n" +
            "     VOID                                                                       \n" +
            "   JPY  128600A21JAN21OSA HO NKG1234.03NUC1234.03END ROE104.211276 XT 64.00     \n" +
            "   CNY 8130.00 TK27.00YQ                                                        \n" +
            "   OI    21.00                                                                  \n" +
            "   SW   176.00                                                                  \n" +
            "   XT    91.00CASH(CNY) *SHA20074                            SHA20074           \n" +
            "   CNY 8418.00                                                                  \n" +
            "                  3711395207                CNY8130.00           100 288.00     \n" +
            "                                                                                ";

    pnrDto.setRtktDetail(pnrDetail);

    assertEquals("KR0WD8", pnrDto.getPnrNo());
    assertEquals("2020-12-18", DateUtil.formatDate(pnrDto.getEtdzDate(), DATE_YYYY_MM_DD));

    assertEquals(1, pnrDto.getPsgCount());
    assertEquals("XIAO/YANG MS", pnrDto.getPassengers().get(0).getPsgName());

    assertEquals(1, pnrDto.getSegCount());
    assertEquals(1, pnrDto.getFlights().size());

    PnrFlightDto flt =  pnrDto.getFlights().get(0);
    assertEquals("KIX", flt.getFlight().getDport());
    assertEquals("NKG", flt.getFlight().getAport());
    assertEquals("HO1609", flt.getFlight().getFlightNo());
    assertEquals("M", flt.getFlight().getSubclass());
    assertEquals("2021-01-21", flt.getFlight().getDdateFormatted());
    assertEquals("1700", flt.getFlight().getDtime());

    assertEquals(8130, pnrDto.getPrice());
    assertEquals(8130, pnrDto.getParValue());
    assertEquals(288, pnrDto.getTax());
    assertEquals(8418, pnrDto.getTotal());
    assertEquals(100, pnrDto.getCommission());
  }

}
