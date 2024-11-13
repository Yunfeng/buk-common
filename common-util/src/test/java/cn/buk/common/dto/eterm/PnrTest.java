package cn.buk.common.dto.eterm;

import cn.buk.common.Constant;
import cn.buk.common.util.DateUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: william
 * Date: 14-8-30
 * Time: 下午11:12
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("SpellCheckingInspection")
class PnrTest {

    private PnrDto pnr;
    private Date opTime;

    @BeforeEach
    public void setUp() throws Exception {
        pnr = new PnrDto();
        opTime = DateUtil.convertToDateTime("2014-06-10 10:08:04");
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

        pnr.setPnrDetail(pnrDetail);
        assertEquals("JV4WMT", pnr.getPnrNo());
        assertEquals("ME4KMH", pnr.getBigPnrNo());
        assertEquals(1, pnr.getSegCount());
        assertEquals("杨金鹤", pnr.getPassengers().get(0).getPsgName());
        assertEquals("32010719610222135X", pnr.getPassengers().get(0).getIdNo());
        assertEquals(1, (long)pnr.getPassengers().get(0).getIdType());

        assertEquals("HO1218", pnr.getFlights().get(0).getFlight().getFlightNo());
        assertEquals("XIY", pnr.getFlights().get(0).getFlight().getDport());
        assertEquals("SHA", pnr.getFlights().get(0).getFlight().getAport());
        assertEquals("V", pnr.getFlights().get(0).getFlight().getSubclass());
        assertEquals("1130", pnr.getFlights().get(0).getFlight().getDtime());
        assertEquals("1340", pnr.getFlights().get(0).getFlight().getAtime());
//        assertEquals("2018-06-30", pnr.getFlights().get(0).getFlight().getDdate());
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

        pnr.setPnrDetail(pnrDetail);
        assertEquals("HMLD5R", pnr.getPnrNo());
        assertEquals("PBM031", pnr.getBigPnrNo());
        assertEquals(1, pnr.getSegCount());
        assertEquals("杨金彪", pnr.getPassengers().get(0).getPsgName());
        assertEquals(null, pnr.getPassengers().get(0).getIdNo());
        assertEquals(0, (long)pnr.getPassengers().get(0).getIdType());

        assertEquals("ZH1787", pnr.getFlights().get(0).getFlight().getFlightNo());
        assertEquals("WEH", pnr.getFlights().get(0).getFlight().getDport());
        assertEquals("HRB", pnr.getFlights().get(0).getFlight().getAport());
        assertEquals("Y", pnr.getFlights().get(0).getFlight().getSubclass());
        assertEquals("1035", pnr.getFlights().get(0).getFlight().getDtime());
        assertEquals("1225", pnr.getFlights().get(0).getFlight().getAtime());
        assertEquals("2022-07-05", pnr.getFlights().get(0).getFlight().getDdateFormatted());
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

        pnr.setPnrDetail(pnrDetail);
        assertEquals("JMJXYT", pnr.getPnrNo());
        assertEquals(1, pnr.getSegCount());
        assertEquals("谷雪松", pnr.getPassengers().get(0).getPsgName());
        assertEquals("侯德萍", pnr.getPassengers().get(1).getPsgName());
        assertEquals("孙月霞", pnr.getPassengers().get(2).getPsgName());
        assertEquals(null, pnr.getPassengers().get(0).getIdNo());
        assertEquals(0, (long)pnr.getPassengers().get(0).getIdType());

        assertEquals("ZH1605", pnr.getFlights().get(0).getFlight().getFlightNo());
        assertEquals("PEK", pnr.getFlights().get(0).getFlight().getDport());
        assertEquals("DLC", pnr.getFlights().get(0).getFlight().getAport());
        assertEquals("Y", pnr.getFlights().get(0).getFlight().getSubclass());
        assertEquals("1025", pnr.getFlights().get(0).getFlight().getDtime());
        assertEquals("1155", pnr.getFlights().get(0).getFlight().getAtime());
        assertEquals("2022-07-06", pnr.getFlights().get(0).getFlight().getDdateFormatted());
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

        pnr.setPnrDetail(pnrDetail);
        assertEquals("KNWD5R", pnr.getPnrNo());
        assertEquals(3, pnr.getSegCount());
        assertEquals("CZWS", pnr.getEtermUsername());


        assertEquals("SHA205", pnr.getBookOfficeNo());
        assertEquals("SHA518,SHA255", pnr.getAuthOfficeNo());

        assertEquals("PU/LINLIN", pnr.getPassengers().get(0).getPsgName());
        assertEquals("ZHANG/ZHIHAN", pnr.getPassengers().get(1).getPsgName());

        assertEquals(2, pnr.getPsgCount());
        assertEquals(0, pnr.getCtcmCount());

        assertEquals(2, pnr.getTicketCount());

        assertEquals(3, pnr.getFlights().size());

        PnrFlightDto flightDto = pnr.getFlights().get(0);
        assertEquals("2016-08-23", flightDto.getFlight().getDdateFormatted());
        assertEquals("0055", flightDto.getFlight().getDtime());
        assertEquals("2016-08-24", flightDto.getFlight().getAdateFormatted());
        assertEquals("0645", flightDto.getFlight().getAtime());
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

        pnr.setPnrDetail(pnrDetail);
        assertEquals("JFM4T5", pnr.getPnrNo());
        assertEquals(9, pnr.getPsgCount());
        assertEquals("HU/YUNJIE", pnr.getPassengers().get(0).getPsgName());
        assertEquals("LI/HAIXIA", pnr.getPassengers().get(1).getPsgName());
        assertEquals("LIU/JINYI", pnr.getPassengers().get(2).getPsgName());
        assertEquals("RONG/YANE", pnr.getPassengers().get(3).getPsgName());
        assertEquals("WANG/CHIHOU", pnr.getPassengers().get(4).getPsgName());
        assertEquals("WANG/CHINGHSIUNG", pnr.getPassengers().get(5).getPsgName());
        assertEquals("WANG/CHINGKUANG", pnr.getPassengers().get(6).getPsgName());
        assertEquals("YU/WENTAO", pnr.getPassengers().get(7).getPsgName());
        assertEquals("ZHU/QIANQIAN", pnr.getPassengers().get(8).getPsgName());
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

        pnr.setPnrDetail(pnrDetail);
        assertEquals("JZNR6Y", pnr.getPnrNo());
        assertEquals("PG203R", pnr.getBigPnrNo());
        assertEquals(1, pnr.getPsgCount());
        assertEquals("青威兴", pnr.getPassengers().get(0).getPsgName());

        assertEquals(2600, pnr.getPrice());
        assertEquals(50, pnr.getTax());
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

        pnr.setPnrDetail(pnrDetail);
        assertEquals("JY9YP3", pnr.getPnrNo());
        assertEquals("MHLD07", pnr.getBigPnrNo());
        assertEquals(2, pnr.getPsgCount());
        assertEquals("INTERSTAR", pnr.getEtermUsername());
        assertEquals(2, pnr.getPsgCount());
        assertEquals(2, pnr.getCtcmCount());

        assertEquals(990, pnr.getPrice());
        assertEquals(990, pnr.getParValue());
        assertEquals(310, pnr.getTax());

        PnrFlightDto flt = pnr.getFlights().get(0);
        assertEquals("T1", flt.getFlight().getDterm());
        assertEquals("", flt.getFlight().getAterm());

        flt = pnr.getFlights().get(2);
        assertEquals("", flt.getFlight().getDterm());
        assertEquals("T1", flt.getFlight().getAterm());
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

        pnr.setPnrDetail(pnrDetail);
        assertEquals("KVFHQ2", pnr.getPnrNo());
        assertEquals("", pnr.getBigPnrNo());
        assertEquals(1, pnr.getPsgCount());
        assertEquals(9, pnr.getSegCount());
//        assertEquals("INTERSTAR", pnr.getEtermUsername());

        assertEquals(1, pnr.getPsgCount());
        assertEquals(1, pnr.getCtcmCount());

        assertEquals("13585991046", pnr.getPassengers().get(0).getMobile());

//        assertEquals();
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

        pnr.setPnrDetail(pnrDetail);
        assertEquals("HY0F9W", pnr.getPnrNo());
        assertEquals("MFTB55", pnr.getBigPnrNo());
        assertEquals(1, pnr.getPsgCount());
        assertEquals(0, pnr.getSegCount());
        assertEquals("", pnr.getBookOfficeNo());
    }


    @Test
    void testOfficeNo_True() {
        final String officeNo = "SHA205";

        boolean result = PnrDto.testOfficeNoFormat(officeNo);

        assertEquals(true, result);
    }

    @Test
     void testOfficeNo_False() {
        final String officeNo = "SHASHA";

        boolean result = PnrDto.testOfficeNoFormat(officeNo);

        assertEquals(false, result);
    }

    @Test
    void testPnrDetail_FixError_20171003() {
        String pnrDetail = "**ELECTRONIC TICKET PNR** \n" +
                " 1.孔华鹏 KNJW76\n" +
                " 2.  ZH9811 W   MO02OCT  SZXWUX HX1   2100 2320          E T3-- \n" +
                " 3.  ZH9811 G   MO02OCT  SZXWUX KK1                      E T3-- \n" +
                " 4.SHA/T SHA/T 0510-82722200/CHINA COMFORT WUXI TRAVEL SERVICE/CHEN RU ABCDEFG   5.BOOK JIANGXU 0907 1144   \n" +
                " 6.T\n" +
                " 7.SSR FOID ZH HK1 NI320204197212112315/P1  \n" +
                " 8.SSR OTHS 1E CANCELED DUE TO INVOL AT AIRPORT \n" +
                " 9.SSR FQTV ZH HK1 WUXSZX 9804 W22SEP CA005327439033/P1 \n" +
                "10.SSR ADTK 1E BY SHA07SEP17/1343 OR CXL ZH9804 W22SEP  \n" +
                "11.SSR TKNE ZH HK1 WUXSZX 9804 W22SEP INF4795783509315/1/P1                    +\n" +
                "12.SSR TKNE ZH HK1 WUXSZX 9804 W22SEP 4795783509314/1/P1                       -13.SSR INFT ZH  KK1 WUXSZX 9804 W22SEP ZOU/LINXIAO 21SEP16/P1   \n" +
                "14.OSI ZH CTCT13003333977   \n" +
                "15.OSI ZH CTCM13301511157/P1\n" +
                "16.OSI YY 1INF ZOULINXIAOINF/P1 \n" +
                "17.OSI YY RLOC PEKCA PW6YHE \n" +
                "18.RMK MP 13301511157   \n" +
                "19.RMK TJ SHA205\n" +
                "20.RMK AUTOMATIC FARE QUOTE \n" +
                "21.RMK AUTOMATIC FARE QUOTE \n" +
                "22.RMK CA/NFNMLD\n" +
                "23.TN/479-5783509314/P1                                                        +\n" +
                "24.TN/IN/479-5783509315/P1                                                     -25.FP/IN/CASH,CNY   \n" +
                "26.XN/IN/邹霖霄INF(SEP16)/P1\n" +
                "27.SHA205";

        pnr.setPnrDetail(pnrDetail);

        assertEquals(1, pnr.getPsgCount());

        assertEquals("KNJW76", pnr.getPnrNo());
        assertEquals("邹霖霄INF(SEP16)", pnr.getPassengers().get(0).getInfName());
        assertEquals("4795783509315", pnr.getPassengers().get(0).getInfTicketNo());
        assertEquals("孔华鹏", pnr.getPassengers().get(0).getPsgName());
        assertEquals(1, (long)pnr.getPassengers().get(0).getIdType());
        assertEquals("320204197212112315", pnr.getPassengers().get(0).getIdNo());
        assertEquals("479-5783509314", pnr.getPassengers().get(0).getTicketNo());

//        "15.OSI ZH CTCM13301511157/P1\n" +
        assertEquals("13301511157", pnr.getPassengers().get(0).getMobile());



        assertEquals("ZH9811", pnr.getFlights().get(0).getFlight().getFlightNo());
        assertEquals("SZX", pnr.getFlights().get(0).getFlight().getDport());
        assertEquals("WUX", pnr.getFlights().get(0).getFlight().getAport());
        assertEquals("W", pnr.getFlights().get(0).getFlight().getSubclass());
        assertEquals("2100", pnr.getFlights().get(0).getFlight().getDtime());
        assertEquals("2320", pnr.getFlights().get(0).getFlight().getAtime());
        assertEquals("2019-10-02", pnr.getFlights().get(0).getFlight().getDdateFormatted());

        PnrFlightDto flt = pnr.getFlights().get(0);
        assertEquals("T3", flt.getFlight().getDterm());
        assertEquals("", flt.getFlight().getAterm());
    }


    @Test
     void testPnrDetail_IncludePat() {
        String pnrDetail = " 1.季茂勇 2.毛禹岑 HYQNQW                                                       \n" +
                " 3.  MU2976 B   SA04NOV  CKGWUX HK2   1715 1930          E T3T2                 \n" +
                " 4.SHA/T SHA/T 0510-82722200/CHINA COMFORT WUXI TRAVEL SERVICE/CHEN RU ABCDEFG  \n" +
                " 5.LIZHEN                                                                       \n" +
                " 6.BOOK BSPET03D 1101 1901                                                      \n" +
                " 7.TL/1515/04NOV/SHA205                                                         \n" +
                " 8.SSR FOID MU HK1 NI321027198201031818/P1                                      \n" +
                " 9.SSR FOID MU HK1 NI320684198605242936/P2                                      \n" +
                "10.SSR FQTV MU HK1 CKGWUX 2976 B04NOV MU650500848183/P2                         \n" +
                "11.SSR ADTK 1E BY SHA01NOV17/2200 OR CXL MU2976 B04NOV                          \n" +
                "12.OSI MU CTCT15861699523                                                       \n" +
                "13.OSI MU CTCM13861896978/P1/2                                                  \n" +
                "14.RMK CA/NTP6P0                                                                \n" +
                "15.SHA205                                                                       \n" +
                "\u0010                                                                               \n" +
                "                                                                                \n" +
                "                                                                                \n" +
                "\u0010pat:a                                                                          \n" +
                ">PAT:A                                                                          \n" +
                "01 B FARE:CNY1400.00 TAX:CNY50.00 YQ:TEXEMPTYQ  TOTAL:1450.00                   \n" +
                "\u0010SFC:01   \u0010SFN:01                                                               \n" +
                "\u0010                           ";

        pnr.setPnrDetail(pnrDetail);
        assertEquals("HYQNQW", pnr.getPnrNo());
        assertEquals("NTP6P0", pnr.getBigPnrNo());
        assertEquals(2, pnr.getPsgCount());
        assertEquals(1, pnr.getSegCount());

        assertEquals("季茂勇", pnr.getPassengers().get(0).getPsgName());
        assertEquals("毛禹岑", pnr.getPassengers().get(1).getPsgName());
        assertEquals(1, (long)pnr.getPassengers().get(1).getIdType());
        assertEquals("320684198605242936", pnr.getPassengers().get(1).getIdNo());

//        "13.OSI MU CTCM13861896978/P1/2                                                  \n" +
        assertEquals("13861896978", pnr.getPassengers().get(0).getMobile());
        assertEquals("13861896978", pnr.getPassengers().get(1).getMobile());

        PnrFlightDto flt = pnr.getFlights().get(0);
        assertEquals("MU2976", pnr.getFlights().get(0).getFlight().getFlightNo());
        assertEquals("CKG", pnr.getFlights().get(0).getFlight().getDport());
        assertEquals("WUX", pnr.getFlights().get(0).getFlight().getAport());
        assertEquals("B", pnr.getFlights().get(0).getFlight().getSubclass());
        assertEquals("1715", pnr.getFlights().get(0).getFlight().getDtime());
        assertEquals("1930", pnr.getFlights().get(0).getFlight().getAtime());
        assertEquals("2019-11-04", pnr.getFlights().get(0).getFlight().getDdateFormatted());

        assertEquals("T3", flt.getFlight().getDterm());
        assertEquals("T2", flt.getFlight().getAterm());

        assertEquals("SHA205", pnr.getBookOfficeNo());

        assertEquals(1400, pnr.getParValue());
        assertEquals(50, pnr.getTax());

    }

    @Test
     void testPnrDetail_DDateIncludeYear_IncludePat() {
        String pnrDetail = " 1.季茂勇 2.毛禹岑 HYQNQW                                                       \n" +
                " 3.  MU2976 B   SU08APR18CKGWUX HK2   1715 1930          E T3T2                 \n" +
                " 4.SHA/T SHA/T 0510-82722200/CHINA COMFORT WUXI TRAVEL SERVICE/CHEN RU ABCDEFG  \n" +
                " 5.LIZHEN                                                                       \n" +
                " 6.BOOK BSPET03D 1101 1901                                                      \n" +
                " 7.TL/1515/04NOV/SHA205                                                         \n" +
                " 8.SSR FOID MU HK1 NI321027198201031818/P1                                      \n" +
                " 9.SSR FOID MU HK1 NI320684198605242936/P2                                      \n" +
                "10.SSR FQTV MU HK1 CKGWUX 2976 B04NOV MU650500848183/P2                         \n" +
                "11.SSR ADTK 1E BY SHA01NOV17/2200 OR CXL MU2976 B04NOV                          \n" +
                "12.OSI MU CTCT15861699523                                                       \n" +
                "13.OSI MU CTCM13861896978/P1/2                                                  \n" +
                "14.RMK CA/NTP6P0                                                                \n" +
                "15.SHA205                                                                       \n" +
                "\u0010                                                                               \n" +
                "                                                                                \n" +
                "                                                                                \n" +
                "\u0010pat:a                                                                          \n" +
                ">PAT:A                                                                          \n" +
                "01 B FARE:CNY1400.00 TAX:CNY50.00 YQ:TEXEMPTYQ  TOTAL:1450.00                   \n" +
                "\u0010SFC:01   \u0010SFN:01                                                               \n" +
                "\u0010                           ";

        pnr.setPnrDetail(pnrDetail);
        assertEquals("HYQNQW", pnr.getPnrNo());
        assertEquals("NTP6P0", pnr.getBigPnrNo());
        assertEquals(2, pnr.getPsgCount());
        assertEquals(1, pnr.getSegCount());

        assertEquals("季茂勇", pnr.getPassengers().get(0).getPsgName());
        assertEquals("毛禹岑", pnr.getPassengers().get(1).getPsgName());
        assertEquals(1, (long)pnr.getPassengers().get(1).getIdType());
        assertEquals("320684198605242936", pnr.getPassengers().get(1).getIdNo());

        PnrFlightDto flt = pnr.getFlights().get(0);
        assertEquals("MU2976", pnr.getFlights().get(0).getFlight().getFlightNo());
        assertEquals("CKG", pnr.getFlights().get(0).getFlight().getDport());
        assertEquals("WUX", pnr.getFlights().get(0).getFlight().getAport());
        assertEquals("B", pnr.getFlights().get(0).getFlight().getSubclass());
        assertEquals("1715", pnr.getFlights().get(0).getFlight().getDtime());
        assertEquals("1930", pnr.getFlights().get(0).getFlight().getAtime());
        assertEquals("2018-04-08", pnr.getFlights().get(0).getFlight().getDdateFormatted());

        assertEquals("T3", flt.getFlight().getDterm());
        assertEquals("T2", flt.getFlight().getAterm());

        assertEquals("SHA205", pnr.getBookOfficeNo());

        assertEquals(1400, pnr.getParValue());
        assertEquals(50, pnr.getTax());

    }

    @Test
     void testPnrFlight_CreateByContent() {
        String content = " 3.  MU2976 B   SA04NOV  CKGWUX HK2   1715 1930          E T3T2   ";

        PnrFlightDto flt = PnrFlightDto.createPnrFlight(content);
        assertEquals("MU2976", flt.getFlight().getFlightNo());
        assertEquals("CKG", flt.getFlight().getDport());
        assertEquals("WUX", flt.getFlight().getAport());
        assertEquals("B", flt.getFlight().getSubclass());
        assertEquals("1715", flt.getFlight().getDtime());
        assertEquals("1930", flt.getFlight().getAtime());
        assertEquals("2019-11-04", flt.getFlight().getDdateFormatted());
        assertEquals("T3", flt.getFlight().getDterm());
        assertEquals("T2", flt.getFlight().getAterm());
    }

    @Test
     void testPnrFlight_CreateByContent1() {
        String content = "3.  MU2976 B   SA04NOV  CKGWUX HK2   1715 1930          E T3T2   ";

        PnrFlightDto flt = PnrFlightDto.createPnrFlight(content);
        assertEquals("MU2976", flt.getFlight().getFlightNo());
        assertEquals("CKG", flt.getFlight().getDport());
        assertEquals("WUX", flt.getFlight().getAport());
        assertEquals("B", flt.getFlight().getSubclass());
        assertEquals("1715", flt.getFlight().getDtime());
        assertEquals("1930", flt.getFlight().getAtime());
        assertEquals("2019-11-04", flt.getFlight().getDdateFormatted());
        assertEquals("T3", flt.getFlight().getDterm());
        assertEquals("T2", flt.getFlight().getAterm());
    }

    @Test
     void testPnrDetail_IncludePassport() {
        String pnrDetail = "  **ELECTRONIC TICKET PNR** \n" +
                " 1.FANG/ZHIXING 2.HUANG/HAILIANG 3.REN/WEI 4.WANG/SHU HWT13K\n" +
                " 5.  CZ8309 E   WE10JAN  CGOHKT RR4   2220 0220+1        E T2I  \n" +
                " 6.  CZ8310 Z   MO15JAN  HKTCGO HK4   0320 0910          E I T2 \n" +
                " 7.SHA/T SHA/T 0510-82722200/CHINA COMFORT WUXI TRAVEL SERVICE/CHEN RU ABCDEFG   8.13643319594  \n" +
                " 9.T\n" +
                "10.SSR ADTK 1E BY SHA11DEC17/1114 OR CXL CZ BOOKING \n" +
                "11.SSR TKNE CZ HK1 CGOHKT 8309 E10JAN 7845929142919/1/P4\n" +
                "12.SSR TKNE CZ HK1 CGOHKT 8309 E10JAN 7845929142904/1/P3\n" +
                "13.SSR TKNE CZ HK1 CGOHKT 8309 E10JAN 7845929142903/1/P2\n" +
                "14.SSR TKNE CZ HK1 CGOHKT 8309 E10JAN 7845929142902/1/P1                       +\n" +
                "15.SSR TKNE CZ HK1 HKTCGO 8310 Z15JAN 7845929142902/2/P1                       -16.SSR TKNE CZ HK1 HKTCGO 8310 Z15JAN 7845929142903/2/P2\n" +
                "17.SSR TKNE CZ HK1 HKTCGO 8310 Z15JAN 7845929142904/2/P3\n" +
                "18.SSR TKNE CZ HK1 HKTCGO 8310 Z15JAN 7845929142919/2/P4\n" +
                "19.SSR DOCS CZ HK1 P/CN/G31567598/CN/28FEB79/M/07DEC18/FANG/ZHIXING/P1  \n" +
                "20.SSR DOCS CZ HK1 P/CN/G32217985/CN/17FEB82/F/01DEC18/WANG/SHU/P4  \n" +
                "21.SSR DOCS CZ HK1 P/CN/G32787246/CN/12FEB83/M/21JAN19/HUANG/HAILIANG/P2\n" +
                "22.SSR DOCS CZ HK1 P/CN/G45692675/CN/04MAR63/M/12SEP20/REN/WEI/P3   \n" +
                "23.OSI CZ CTCM13904094049/P3\n" +
                "24.OSI CZ CTCM18132163373/P2\n" +
                "25.OSI CZ CTCM13897916549/P4\n" +
                "26.OSI CZ CTCM13931843705/P1                                                   +\n" +
                "27.OSI CZ CTCT13643319594                                                      -28.RMK  \n" +
                "29.RMK INTERSTAR-HB-SJZ-SJZ-004-13643319594 \n" +
                "30.RMK CA/NLLP7V\n" +
                "31.RMK TJ AUTH SHA697   \n" +
                "32.FN/A/\n" +
                "33.TN/784-5929142902/P1 \n" +
                "34.TN/784-5929142903/P2 \n" +
                "35.TN/784-5929142904/P3 \n" +
                "36.TN/784-5929142919/P4 \n" +
                "37.FP/  \n" +
                "38.SHA205";

//        SSR DOCS表示输入旅客的护照等信息内容。
//        SSR DOCS CZ HK1 P/CN/G31567598/CN/28FEB79/M/07DEC18/FANG/ZHIXING/P1
//        MU  航空公司代码
//        HK1 行动代码，表示座位已预订完成。
//        P     证件类型
//        SG  发证国家
//        EO109323N  证件号码
//        SG  国籍
//        18AUG73  出生日期
//        M  性别
//        04AUG12  有效期
//        TSENG/KUNTSAN   姓名
//        P1  表示本段护照信息对应PNR中的第一个旅客

//        https://www.iso.org/obp/ui/#search 国家代码查询地址

        pnr.setPnrDetail(pnrDetail);
        assertEquals("HWT13K", pnr.getPnrNo());
        assertEquals("NLLP7V", pnr.getBigPnrNo());
        assertEquals(4, pnr.getPsgCount());
        assertEquals(2, pnr.getSegCount());

//        "19.SSR DOCS CZ HK1 P/CN/G31567598/CN/28FEB79/M/07DEC18/FANG/ZHIXING/P1  \n" +
        assertEquals("FANG/ZHIXING", pnr.getPassengers().get(0).getPsgName());
        assertEquals(2, (long)pnr.getPassengers().get(0).getIdType());
        assertEquals("G31567598", pnr.getPassengers().get(0).getIdNo());
        assertEquals("CN", pnr.getPassengers().get(0).getNationality());
        assertEquals("1979-02-28", DateUtil.formatDate(pnr.getPassengers().get(0).getBirthday(), "yyyy-MM-dd"));
        assertEquals(1, pnr.getPassengers().get(0).getGender());
        assertEquals("2018-12-07", DateUtil.formatDate(pnr.getPassengers().get(0).getIdExpiredDate(), "yyyy-MM-dd"));

        assertEquals("HUANG/HAILIANG", pnr.getPassengers().get(1).getPsgName());
        assertEquals(2, (long)pnr.getPassengers().get(1).getIdType());
        assertEquals("G32787246", pnr.getPassengers().get(1).getIdNo());

        assertEquals("REN/WEI", pnr.getPassengers().get(2).getPsgName());
        assertEquals(2, (long)pnr.getPassengers().get(2).getIdType());
        assertEquals("G45692675", pnr.getPassengers().get(2).getIdNo());

        assertEquals("WANG/SHU", pnr.getPassengers().get(3).getPsgName());
        assertEquals(2, (long)pnr.getPassengers().get(3).getIdType());
        assertEquals("G32217985", pnr.getPassengers().get(3).getIdNo());
    }


    @Test
     void testPnrDetail_IncludeTwoPassengersTowPrices() {
        //"编码中包括2个人，2个人价格不同")
        //儿童 2-12岁
//        男童  MSTR
//        女童  MISS
//        其他一般统一加CHD
        String pnrDetail = " 1.SU/DONG MR 2.SU/RAEKAMOXIN MISS JXT4G8   \n" +
                " 3.  AC030  K   WE31JAN  PEKYVR HK2   1740 1215      SEAME  3 M \n" +
                " 4.  AC029  T   WE28FEB  YVRPEK HK2   1240 1600+1    SEAME  M 3 \n" +
                " 5.SHA/T SHA/T 0510-82722200/CHINA COMFORT WUXI TRAVEL SERVICE/CHEN RU ABCDEFG   6.13651757240-SYSTEM   \n" +
                " 7.021-51069999 \n" +
                " 8.95010\n" +
                " 9.T\n" +
                "10.SSR FQTV AC HK/ CA738198753/P2   \n" +
                "11.SSR FQTV AC HK/ CA158046221/P1   \n" +
                "12.SSR TKNE AC HK1 PEKYVR 030 K31JAN 0145134713305/1/P2                        +\n" +
                "13.SSR TKNE AC HK1 PEKYVR 030 K31JAN 0145134713304/1/P1                        -14.SSR TKNE AC HK1 YVRPEK 029 T28FEB 0145134713304/2/P1 \n" +
                "15.SSR TKNE AC HK1 YVRPEK 029 T28FEB 0145134713305/2/P2 \n" +
                "16.SSR DOCS AC HK1 P/CHN/E04125976/CHN/22DEC89/M/04SEP22/SU/DONG/P1 \n" +
                "17.SSR DOCS AC HK1 P/CAN/GL978402/CAN/06NOV12/F/25JUN20/SU/RAEKAMOXIN/P2\n" +
                "18.SSR CHLD AC HK1 06NOV12/P2   \n" +
                "19.SSR CTCE AC HK1  GPYYXGXPZ//CTRIP.COM/P1 \n" +
                "20.SSR CTCM AC HK1 13764482350/P1   \n" +
                "21.OSI AC CHD FREE TEXT 1SU/RAEKAMOXIN/P2   \n" +
                "22.OSI AC CTCT13651757240   \n" +
                "23.RMK TJ SHA205\n" +
                "24.RMK TJ AUTH SHA717                                                          +\n" +
                "25.RMK AC/MA4EE5                                                               -26.FN/A/FCNY1500.00/SCNY1500.00/C4.00/XCNY2333.00/TCNY90.00CN/TCNY134.00CA/ \n" +
                "    TCNY2109.00XT/ACNY3833.00   \n" +
                "27.FN/A/FCNY1170.00/SCNY1170.00/C4.00/XCNY2243.00/TEXEMPTCN/TCNY134.00CA/   \n" +
                "    TCNY2109.00XT/ACNY3413.00/P2\n" +
                "28.TN/014-5134713304/P1 \n" +
                "29.TN/014-5134713305/P2 \n" +
                "30.FP/CASH,CNY  \n" +
                "31.SHA205";
        pnr.setPnrDetail(pnrDetail);
        assertEquals("JXT4G8", pnr.getPnrNo());

        assertEquals(2, pnr.getPsgCount());

        PnrPassengerDto psg = pnr.getPassengers().get(0);
        assertEquals("SU/DONG", psg.getPsgName());
        assertEquals(Constant.PSG_TYPE_ADU, psg.getPsgType());
        assertEquals(Constant.GENDER_MALE, psg.getGender());
        assertEquals("E04125976", psg.getIdNo());
        assertEquals("CHN", psg.getNationality());
        assertEquals(1, psg.getGender());
        assertEquals("CHN", psg.getNationality());
        assertEquals("1989-12-22", DateUtil.formatDate(psg.getBirthday(), "yyyy-MM-dd"));
        assertEquals("2022-09-04", DateUtil.formatDate(psg.getIdExpiredDate(), "yyyy-MM-dd"));

        psg = pnr.getPassengers().get(1);
        assertEquals("SU/RAEKAMOXIN", psg.getPsgName());
        assertEquals(Constant.PSG_TYPE_CHD, psg.getPsgType());
        assertEquals("GL978402", psg.getIdNo());
        assertEquals("CAN", psg.getNationality());
        assertEquals(Constant.GENDER_FEMALE, psg.getGender());

        assertEquals(1500, pnr.getPrice(), 0.001);
        assertEquals(1500, pnr.getParValue(), 0.001);
        assertEquals(2333, pnr.getTax(), 0.001);
        assertEquals(4, pnr.getCommRate(), 0.001);

        assertEquals(1170, pnr.getChdPrice(), 0.001);
        assertEquals(1170, pnr.getChdParValue(), 0.001);
        assertEquals(2243, pnr.getChdTax(), 0.001);
        assertEquals(4, pnr.getChdCommRate(), 0.001);
    }

    @Test
     void testPnrDetail_IncludeTwoPassengersThreeChilds() {
        //"编码中包括2个人，2个人价格不同")
        //儿童 2-12岁
//        男童  MSTR
//        女童  MISS
//        其他一般统一加CHD
        String pnrDetail = "1.DONG/FANG 2.HO/SHIH HAO 3.HO/TZUMIAO CHD 4.HO/YILE CHD 5.HO/YITING CHD       \n" +
                "   JRKTVE                                                                       \n" +
                " 6.  CI584  Q   SU21JAN  PVGKHH HK5   2035 2250      SEAME  1 I                 \n" +
                " 7.  CI583  Q   SU18FEB  KHHPVG HK5   1710 1920      SEAME  I 1                 \n" +
                " 8.SHA/T SHA/T021-31087705/BU YE CHENG BOOKING OFFICE/JINHUA ABCDEFG            \n" +
                " 9.REM 1218 1628 SY02                                                           \n" +
                "10.T                                                                            \n" +
                "11.SSR SEAT CI HK1 PVGKHH 584 Q21JAN 20GN/P5                                    \n" +
                "12.SSR SEAT CI HK1 PVGKHH 584 Q21JAN 20FN/P4                                    \n" +
                "13.SSR SEAT CI HK1 PVGKHH 584 Q21JAN 20EN/P3                                    \n" +
                "14.SSR SEAT CI HK1 PVGKHH 584 Q21JAN 20DN/P2                                    \n" +
                "15.SSR SEAT CI HK1 PVGKHH 584 Q21JAN 20JN/P1                                    \n" +
                "16.SSR SEAT CI HK1 KHHPVG 583 Q18FEB 22JN/P1                                    \n" +
                "17.SSR SEAT CI HK1 KHHPVG 583 Q18FEB 22DN/P2                                    \n" +
                "18.SSR SEAT CI HK1 KHHPVG 583 Q18FEB 22EN/P3                                    \n" +
                "19.SSR SEAT CI HK1 KHHPVG 583 Q18FEB 22FN/P4                                    \n" +
                "20.SSR SEAT CI HK1 KHHPVG 583 Q18FEB 22GN/P5                                    \n" +
                "21.SSR ADTK 1E TO CI BY 21DEC 1400 SHA TIME ZONE OTHERWISE WILL BE XLD         +\n" +
                "\u0010                                                                               \n" +
                "                                                                                \n" +
                "                                                                                \n" +
                "\u0010pn                                                                             \n" +
                "22.SSR TKNE CI HK1 PVGKHH 584 Q21JAN 2975136081213/1/P5                        -\n" +
                "23.SSR TKNE CI HK1 PVGKHH 584 Q21JAN 2975136081212/1/P4                         \n" +
                "24.SSR TKNE CI HK1 PVGKHH 584 Q21JAN 2975136081211/1/P3                         \n" +
                "25.SSR TKNE CI HK1 PVGKHH 584 Q21JAN 2975136081210/1/P2                         \n" +
                "26.SSR TKNE CI HK1 PVGKHH 584 Q21JAN 2975136081209/1/P1                         \n" +
                "27.SSR TKNE CI HK1 KHHPVG 583 Q18FEB 2975136081209/2/P1                         \n" +
                "28.SSR TKNE CI HK1 KHHPVG 583 Q18FEB 2975136081210/2/P2                         \n" +
                "29.SSR TKNE CI HK1 KHHPVG 583 Q18FEB 2975136081211/2/P3                         \n" +
                "30.SSR TKNE CI HK1 KHHPVG 583 Q18FEB 2975136081212/2/P4                         \n" +
                "31.SSR TKNE CI HK1 KHHPVG 583 Q18FEB 2975136081213/2/P5                         \n" +
                "32.SSR DOCS CI HK1 P/CHN/T23242752/CHN/04APR82/F/11JAN21/DONG/FANG/P1           \n" +
                "33.SSR DOCS CI HK1 P/TW/311602706/TW/18AUG14/M/29JUL20/HO/YILE/P4              +\n" +
                "\u0010                                                                               \n" +
                "                                                                                \n" +
                "                                                                                \n" +
                "\u0010pn                                                                             \n" +
                "34.SSR DOCS CI HK1 P/TW/309622893/TW/10DEC08/M/11JUL19/HO/YITING/P5            -\n" +
                "35.SSR DOCS CI HK1 P/TW/306932862/TW/12JAN07/F/20FEB18/HO/TZUMIAO/P3            \n" +
                "36.SSR DOCS CI HK1 P/TW/02295955/TW/06JUN70/M/30OCT16/HO/SHIHHAO/P2             \n" +
                "37.SSR CHLD CI HK1 18AUG14/P4                                                   \n" +
                "38.SSR CHLD CI HK1 12JAN07/P3                                                   \n" +
                "39.SSR CHLD CI HK1 10DEC08/P5                                                   \n" +
                "40.SSR CTCM CI HK1 15802175978/P5                                               \n" +
                "41.SSR CTCM CI HK1 13817732569/P4                                               \n" +
                "42.SSR CTCM CI HK1 13661832356/P3                                               \n" +
                "43.SSR CTCM CI HK1 18917093601/P2                                               \n" +
                "44.SSR CTCM CI HK1 15301883082/P1                                               \n" +
                "45.OSI CI CTCT18917007892                                                      +\n" +
                "\u0010                                                                               \n" +
                "                                                                                \n" +
                "                                                                                \n" +
                "\u0010pn                                                                             \n" +
                "46.RMK TJ SHA255                                                               -\n" +
                "47.RMK 1A/Q4XT6K                                                                \n" +
                "48.FN/A/FCNY1590.00/SCNY1590.00/C7.00/XCNY771.00/TCNY90.00CN/TCNY111.00TW/      \n" +
                "    TCNY570.00YQ/ACNY2361.00                                                    \n" +
                "49.FN/A/FCNY1200.00/SCNY1200.00/C7.00/XCNY681.00/TEXEMPTCN/TCNY111.00TW/        \n" +
                "    TCNY570.00YQ/ACNY1881.00/P3/4/5                                             \n" +
                "50.TN/297-5136081209/P1                                                         \n" +
                "51.TN/297-5136081210/P2                                                         \n" +
                "52.TN/297-5136081211/P3                                                         \n" +
                "53.TN/297-5136081212/P4                                                         \n" +
                "54.TN/297-5136081213/P5                                                         \n" +
                "55.FP/CASH,CNY                                                                 +\n" +
                "\u0010                                                     ";


        pnr.setPnrDetail(pnrDetail);
        assertEquals("JRKTVE", pnr.getPnrNo());

        assertEquals(5, pnr.getPsgCount());

        PnrPassengerDto psg = pnr.getPassengers().get(0);
        assertEquals("DONG/FANG", psg.getPsgName());
        assertEquals(0, psg.getPsgType());
//        assertEquals("E04125976", psg.getIdNo());
//        assertEquals("CHN", psg.getNationality());
        assertEquals(2, psg.getGender());

        psg = pnr.getPassengers().get(4);
        assertEquals("HO/YITING", psg.getPsgName());
        assertEquals(Constant.PSG_TYPE_CHD, psg.getPsgType());
        assertEquals(Constant.GENDER_MALE, psg.getGender());

        assertEquals(1590, pnr.getPrice(), 0.001);
        assertEquals(1590, pnr.getParValue(), 0.001);
        assertEquals(771, pnr.getTax(), 0.001);
        assertEquals(7, pnr.getCommRate(), 0.001);

        assertEquals(1200, pnr.getChdPrice(), 0.001);
        assertEquals(1200, pnr.getChdParValue(), 0.001);
        assertEquals(681, pnr.getChdTax(), 0.001);
        assertEquals(7, pnr.getChdCommRate(), 0.001);
    }

    @Test
     void testPnrDetail_GroupNameList() {
        //"编码中包括2个人，2个人价格不同")
        //儿童 2-12岁
//        男童  MSTR
//        女童  MISS
//        其他一般统一加CHD
        String pnrDetail = "   **ELECTRONIC TICKET PNR** \n" +
                " 0.21SYTUAN NM21 HNH102 \n" +
                " 1.HU/YIJIAN 2.HUANG/XUETING 3.LI/DONG 4.QIAN/FANG 5.SHEN/YANG 6.SHI/YAN\n" +
                " 7.SUN/JING 8.WU/YAN 9.XIAO/ZHIYING 10.XU/YUN 11.YAN/BEIBEI 12.YAN/CUI  \n" +
                "13.YANG/BUCHENG 14.YANG/SHILEI 15.YANG/WENJUAN 16.YANG/YANG 17.YU/JUNLAN\n" +
                "18.ZHANG/TIAN 19.ZHONG/LILI 20.ZHOU/JUXIANG 21.ZHOU/LI  \n" +
                "22.  HO1387 E   FR05JAN  PVGNGO RR21  1250 1625          E T2-- \n" +
                "23.  HO1388 E   WE10JAN  NGOPVG RR21  1715 1915          E --T2 \n" +
                "24.SHA/T SHA/T021-6276220418917099670/SHANGHAI SHANGYOU INTERNATIONAL TRAVEL\n" +
                "     SERVICE CO., ABCDEFG   \n" +
                "25.TL/1700/01JAN/SHA274 \n" +
                "26.SSR OTHS 1E 1 PNR RR AND PRINTED                                            +\n" +
                "27.SSR OTHS 1E 1 HOAIRLINES ET PNR                                             -\n" +
                "28.SSR GRPS 1E TCP21 SYTUAN \n" +
                "29.SSR TKNE HO HK1 PVGNGO 1387 E05JAN 0182101590791/1/P1\n" +
                "30.SSR TKNE HO HK1 PVGNGO 1387 E05JAN 0182101590792/1/P2\n" +
                "31.SSR TKNE HO HK1 PVGNGO 1387 E05JAN 0182101590793/1/P3\n" +
                "32.SSR TKNE HO HK1 PVGNGO 1387 E05JAN 0182101590794/1/P4\n" +
                "33.SSR TKNE HO HK1 PVGNGO 1387 E05JAN 0182101590795/1/P5\n" +
                "34.SSR TKNE HO HK1 PVGNGO 1387 E05JAN 0182101590796/1/P6\n" +
                "35.SSR TKNE HO HK1 PVGNGO 1387 E05JAN 0182101590797/1/P7\n" +
                "36.SSR TKNE HO HK1 PVGNGO 1387 E05JAN 0182101590798/1/P8\n" +
                "37.SSR TKNE HO HK1 PVGNGO 1387 E05JAN 0182101590799/1/P9\n" +
                "38.SSR TKNE HO HK1 PVGNGO 1387 E05JAN 0182101590800/1/P10                      +\n" +
                "39.SSR TKNE HO HK1 PVGNGO 1387 E05JAN 0182101590801/1/P11                      -\n" +
                "40.SSR TKNE HO HK1 PVGNGO 1387 E05JAN 0182101590802/1/P12   \n" +
                "41.SSR TKNE HO HK1 PVGNGO 1387 E05JAN 0182101590803/1/P13   \n" +
                "42.SSR TKNE HO HK1 PVGNGO 1387 E05JAN 0182101590804/1/P14   \n" +
                "43.SSR TKNE HO HK1 PVGNGO 1387 E05JAN 0182101590805/1/P15   \n" +
                "44.SSR TKNE HO HK1 PVGNGO 1387 E05JAN 0182101590806/1/P16   \n" +
                "45.SSR TKNE HO HK1 PVGNGO 1387 E05JAN 0182101590807/1/P17   \n" +
                "46.SSR TKNE HO HK1 PVGNGO 1387 E05JAN 0182101590808/1/P18   \n" +
                "47.SSR TKNE HO HK1 PVGNGO 1387 E05JAN 0182101590809/1/P19   \n" +
                "48.SSR TKNE HO HK1 PVGNGO 1387 E05JAN 0182101590810/1/P20   \n" +
                "49.SSR TKNE HO HK1 PVGNGO 1387 E05JAN 0182101590811/1/P21   \n" +
                "50.SSR TKNE HO HK1 NGOPVG 1388 E10JAN 0182101590791/2/P1                       +\n" +
                "51.SSR TKNE HO HK1 NGOPVG 1388 E10JAN 0182101590792/2/P2                       -\n" +
                "52.SSR TKNE HO HK1 NGOPVG 1388 E10JAN 0182101590793/2/P3\n" +
                "53.SSR TKNE HO HK1 NGOPVG 1388 E10JAN 0182101590794/2/P4\n" +
                "54.SSR TKNE HO HK1 NGOPVG 1388 E10JAN 0182101590795/2/P5\n" +
                "55.SSR TKNE HO HK1 NGOPVG 1388 E10JAN 0182101590796/2/P6\n" +
                "56.SSR TKNE HO HK1 NGOPVG 1388 E10JAN 0182101590797/2/P7\n" +
                "57.SSR TKNE HO HK1 NGOPVG 1388 E10JAN 0182101590798/2/P8\n" +
                "58.SSR TKNE HO HK1 NGOPVG 1388 E10JAN 0182101590799/2/P9\n" +
                "59.SSR TKNE HO HK1 NGOPVG 1388 E10JAN 0182101590800/2/P10   \n" +
                "60.SSR TKNE HO HK1 NGOPVG 1388 E10JAN 0182101590801/2/P11   \n" +
                "61.SSR TKNE HO HK1 NGOPVG 1388 E10JAN 0182101590802/2/P12   \n" +
                "62.SSR TKNE HO HK1 NGOPVG 1388 E10JAN 0182101590803/2/P13                      +\n" +
                "63.SSR TKNE HO HK1 NGOPVG 1388 E10JAN 0182101590804/2/P14                      -\n" +
                "64.SSR TKNE HO HK1 NGOPVG 1388 E10JAN 0182101590805/2/P15   \n" +
                "65.SSR TKNE HO HK1 NGOPVG 1388 E10JAN 0182101590806/2/P16   \n" +
                "66.SSR TKNE HO HK1 NGOPVG 1388 E10JAN 0182101590807/2/P17   \n" +
                "67.SSR TKNE HO HK1 NGOPVG 1388 E10JAN 0182101590808/2/P18   \n" +
                "68.SSR TKNE HO HK1 NGOPVG 1388 E10JAN 0182101590809/2/P19   \n" +
                "69.SSR TKNE HO HK1 NGOPVG 1388 E10JAN 0182101590810/2/P20   \n" +
                "70.SSR TKNE HO HK1 NGOPVG 1388 E10JAN 0182101590811/2/P21   \n" +
                "71.SSR DOCS HO HK1 P/CN/E94877720/CN/18SEP86/M/21JAN27/YANG/BUCHENG/P13 \n" +
                "72.SSR DOCS HO HK1 P/CN/E93820089/CN/01JUL93/F/18JAN27/SHI/YAN/P6   \n" +
                "73.SSR DOCS HO HK1 P/CN/E19814199/CN/12JAN92/M/26JUN24/ZHANG/TIAN/P18   \n" +
                "74.SSR DOCS HO HK1 P/CN/E94876917/CN/12JUL92/M/21JAN27/YANG/YANG/P16           +\n" +
                "75.SSR DOCS HO HK1 P/CN/E93556781/CN/11OCT92/F/23JAN27/XIAO/ZHIYING/P9         -\n" +
                "76.SSR DOCS HO HK1 P/CN/E16796495/CN/30OCT88/F/03MAY24/XU/YUN/P10   \n" +
                "77.SSR DOCS HO HK1 P/CN/E90902857/CN/09OCT96/M/04DEC26/HU/YIJIAN/P1 \n" +
                "78.SSR DOCS HO HK1 P/CN/E80004084/CN/09NOV92/F/28JUN26/YANG/WENJUAN/P15 \n" +
                "79.SSR DOCS HO HK1 P/CN/E96562423/CN/11DEC85/M/14FEB27/LI/DONG/P3   \n" +
                "80.SSR DOCS HO HK1 P/CN/EA3759697/CN/18NOV82/M/26MAY27/HUANG/XUETING/P2 \n" +
                "81.SSR DOCS HO HK1 P/CN/G61785622/CN/04JUN88/F/09MAY22/QIAN/FANG/P4 \n" +
                "82.SSR DOCS HO HK1 P/CN/G58004089/CN/28DEC83/F/08JAN22/SUN/JING/P7  \n" +
                "83.SSR DOCS HO HK1 P/CN/E14181335/CN/29JUN87/F/14MAR23/ZHOU/LI/P21  \n" +
                "84.SSR DOCS HO HK1 P/CN/G58444037/CN/14NOV88/F/17JAN22/YANG/SHILEI/P14  \n" +
                "85.SSR DOCS HO HK1 P/CN/E90212966/CN/15SEP87/F/14NOV26/YAN/BEIBEI/P11   \n" +
                "86.SSR DOCS HO HK1 P/CN/E94885916/CN/17MAR91/M/22JAN27/SHEN/YANG/P5            +\n" +
                "87.SSR DOCS HO HK1 P/CN/E45788909/CN/24MAR91/M/09MAR25/YU/JUNLAN/P17           -\n" +
                "88.SSR DOCS HO HK1 P/CN/E41855105/CN/15NOV88/F/20JAN25/ZHOU/JUXIANG/P20 \n" +
                "89.SSR DOCS HO HK1 P/CN/E94513534/CN/26SEP90/F/24JAN27/YAN/CUI/P12  \n" +
                "90.SSR DOCS HO HK1 P/CN/G58451731/CN/12JAN88/F/20JAN22/ZHONG/LILI/P19   \n" +
                "91.SSR DOCS HO HK1 P/CN/EA7891937/CN/31DEC83/F/21AUG27/WU/YAN/P8\n" +
                "92.OSI HO CTCT13585881661   \n" +
                "93.OSI 1E HOET TN/0182101590791-0182101590811   \n" +
                "94.RMK CA/NH06XB\n" +
                "95.SHA27\n";


        pnr.setPnrDetail(pnrDetail);
        assertEquals("HNH102", pnr.getPnrNo());

        assertEquals(21, pnr.getPsgCount());
        assertEquals(2, pnr.getFlights().size());

        PnrPassengerDto psg = pnr.getPassengers().get(20);
        assertEquals("E14181335", psg.getIdNo());
        assertEquals("018-2101590811", psg.getTicketNo());
    }

    @Test
     void testPnrDetail_IncludeInfant() {
        //包括婴儿
        String pnrDetail = "  **ELECTRONIC TICKET PNR** \n" +
                " 1.刘杰 KN05M7  \n" +
                " 2.  HU7407 N   WE27DEC  NKGDLC RR1   1045 1215          E  \n" +
                " 3.SHA/T SHA/T 0510-82722200/CHINA COMFORT WUXI TRAVEL SERVICE/CHEN RU ABCDEFG   4.BOOK YUANYE 1222 1046\n" +
                " 5.BOOK YUANYE 1222 1047\n" +
                " 6.T\n" +
                " 7.SSR FOID HU HK1 NI210281198011184402/P1  \n" +
                " 8.SSR ADTK 1E BY SHA22DEC17/1101 OR CXL HU7407 N27DEC  \n" +
                " 9.SSR TKNE HU HK1 NKGDLC 7407 N27DEC INF8805159875787/1/P1 \n" +
                "10.SSR TKNE HU HK1 NKGDLC 7407 N27DEC 8805159875786/1/P1\n" +
                "11.SSR INFT HU HK1 NKGDLC 7407 N27DEC LI/JIAGUO 03JAN17/P1                     +\n" +
                "12.OSI HU CTCT13585086564                                                      -13.OSI YY 1INF LIJIAGUOINF/P1   \n" +
                "14.OSI HU CTCM13771526988/P1\n" +
                "15.RMK CMS/A/** \n" +
                "16.RMK MP 13771526988   \n" +
                "17.RMK TJ SHA205\n" +
                "18.RMK CA/MKF7H5\n" +
                "19.RMK AUTOMATIC FARE QUOTE \n" +
                "20.RMK AUTOMATIC FARE QUOTE \n" +
                "21.FN/A/FCNY200.00/SCNY200.00/C0.00/XCNY50.00/TCNY50.00CN/TEXEMPTYQ/ACNY250.00  " +
                "22.FN/A/IN/FCNY100.00/SCNY100.00/C0.00/TEXEMPTCN/TEXEMPTYQ/ACNY100.00/P1\n" +
                "23.TN/880-5159875786/P1                                                        +\n" +
                "24.TN/IN/880-5159875787/P1                                                     -25.FP/CASH,CNY  \n" +
                "26.FP/IN/CASH,CNY/P1\n" +
                "27.XN/IN/LI/YUANZE (OCT16)/P1\n" +
                "28.SHA205";


        pnr.setPnrDetail(pnrDetail);
        assertEquals("KN05M7", pnr.getPnrNo());

        assertEquals(2, pnr.getTotalPsgCount());
        assertEquals(1, pnr.getPsgCount());
        assertEquals(1, pnr.getAdultCount());
        assertEquals(1, pnr.getInfantCount());
        assertEquals(1, pnr.getFlights().size());

        PnrPassengerDto psg = pnr.getPassengers().get(0);
        assertEquals("刘杰", psg.getPsgName());
        assertEquals(1, psg.getPsgNo());
        assertEquals("210281198011184402", psg.getIdNo());
        assertEquals("880-5159875786", psg.getTicketNo());
        assertEquals(0, psg.getPsgType());

        psg = pnr.getPassengers().get(1);
        assertEquals("LI/YUANZE (OCT16)", psg.getPsgName());
        assertEquals(1, psg.getPsgNo());
        assertEquals("8805159875787", psg.getTicketNo());
        assertEquals(2, psg.getPsgType());


        assertEquals(200, pnr.getPrice(), 0.001);
        assertEquals(200, pnr.getParValue(), 0.001);
        assertEquals(50, pnr.getTax(), 0.001);
        assertEquals(0, pnr.getCommRate(), 0.001);

        assertEquals(100, pnr.getInfPrice(), 0.001);
        assertEquals(100, pnr.getInfParValue(), 0.001);
        assertEquals(0, pnr.getInfTax(), 0.001);
        assertEquals(0, pnr.getInfCommRate(), 0.001);
    }

    @Test
     void testPnrDetail_IncludeInfant_IncludeInfantIdNo() {
        //包括婴儿
        String pnrDetail = "  **ELECTRONIC TICKET PNR** \n" +
                " 1.LI/FULI JR0YTP   \n" +
                " 2.  AF1519 S   SU24DEC  FRACDG HK1   1025 1155          E  \n" +
                " 3.  AF1618 E   TU13MAR  CDGFRA HK1   0950 1115          E  \n" +
                " 4.SHA/T SHA/T 0510-82722200/CHINA COMFORT WUXI TRAVEL SERVICE/CHEN RU ABCDEFG   5.BOOK MYD 1222 1000   \n" +
                " 6.T\n" +
                " 7.SSR OTHS 1E AF 1519 24DEC OPERATED BY WX \n" +
                " 8.SSR OTHS 1E AF 1618 13MAR OPERATED BY A5 \n" +
                " 9.SSR ADTK 1E TO AF BY 23DEC 1000 OTHERWISE WILL BE XLD\n" +
                "10.SSR TKNE AF HK1 FRACDG 1519 S24DEC INF0575135898098/1/P1 \n" +
                "11.SSR TKNE AF HK1 FRACDG 1519 S24DEC 0575135898097/1/P1                       +\n" +
                "12.SSR TKNE AF HK1 CDGFRA 1618 E13MAR 0575135898097/2/P1                       -13.SSR TKNE AF HK1 CDGFRA 1618 E13MAR INF0575135898098/2/P1 \n" +
                "14.SSR DOCS AF HK1 P/CN/E33103800/CN/13AUG78/F/27NOV23/LI/FULI/P1   \n" +
                "15.SSR DOCS AF HK1 P/CN/EB7237447/CN/23OCT16/MI/13NOV22/LI/YUANZE/P1\n" +
                "16.SSR INFT AF HK1 FRACDG 1519 S24DEC LI/YUANZE 23OCT16/P1  \n" +
                "17.SSR INFT AF HK1 CDGFRA 1618 E13MAR LI/YUANZE 23OCT16/P1  \n" +
                "18.SSR CTCM AF HK1 13906183870/P1   \n" +
                "19.OSI AF CTCT13906183870   \n" +
                "20.OSI YY 1INF LI/YUANZE/P1 \n" +
                "21.RMK TJ SHA205\n" +
                "22.RMK 1A/L8E297\n" +
                "                                                                               +\n" +
                "23.FN/A/FEUR177.00/ECNY1390.00/SCNY1390.00/C0.00/XCNY577.00/TCNY75.00DE/       -    TCNY59.00OY/TCNY443.00XT/ACNY1967.00\n" +
                "24.FN/A/IN/FEUR18.00/ECNY150.00/SCNY150.00/C0.00/ACNY150.00 \n" +
                "25.TN/057-5135898097/P1 \n" +
                "26.TN/IN/057-5135898098/P1  \n" +
                "27.FP/CASH,CNY  \n" +
                "28.FP/IN/CASH,CNY   \n" +
                "29.XN/IN/LI/YUANZE (OCT16)/P1   \n" +
                "30.SHA205";


        pnr.setPnrDetail(pnrDetail);
        assertEquals("JR0YTP", pnr.getPnrNo());

        assertEquals(2, pnr.getTotalPsgCount());
        assertEquals(1, pnr.getPsgCount());
        assertEquals(1, pnr.getAdultCount());
        assertEquals(1, pnr.getInfantCount());
        assertEquals(2, pnr.getFlights().size());

        PnrPassengerDto psg = pnr.getPassengers().get(0);
        assertEquals("LI/FULI", psg.getPsgName());
        assertEquals(1, psg.getPsgNo());
        assertEquals("E33103800", psg.getIdNo());
        assertEquals("057-5135898097", psg.getTicketNo());
        assertEquals(0, psg.getPsgType());

        psg = pnr.getPassengers().get(1);
        assertEquals("LI/YUANZE (OCT16)", psg.getPsgName());
        assertEquals(1, psg.getPsgNo());
        assertEquals("EB7237447", psg.getIdNo());
        assertEquals("0575135898098", psg.getTicketNo());
        assertEquals(2, psg.getPsgType());


        assertEquals(1390, pnr.getPrice(), 0.001);
        assertEquals(1390, pnr.getParValue(), 0.001);
        assertEquals(577, pnr.getTax(), 0.001);
        assertEquals(0, pnr.getCommRate(), 0.001);

        assertEquals(150, pnr.getInfPrice(), 0.001);
        assertEquals(150, pnr.getInfParValue(), 0.001);
        assertEquals(0, pnr.getInfTax(), 0.001);
        assertEquals(0, pnr.getInfCommRate(), 0.001);
    }

    @Test
     void testPnrDetail_OnlyChild() {
        //包括婴儿
        String pnrDetail = "  **ELECTRONIC TICKET PNR** \n" +
                " 1.袁乐怡CHD JSTJQL \n" +
                " 2.  HO1177 Y   SU28JAN  SHASYX RR1   0955 1310          E T2-- \n" +
                " 3.SHA/T SHA/T 0510-82722200/CHINA COMFORT WUXI TRAVEL SERVICE/CHEN RU ABCDEFG   4.BOOK SZCY2 1226 1547 \n" +
                " 5.BOOK SZCY2 1226 1648 \n" +
                " 6.BOOK SZCY2 1226 1649 \n" +
                " 7.T\n" +
                " 8.SSR FOID HO HK1 NI310115201003191169/P1  \n" +
                " 9.SSR ADTK 1E BY SHA26DEC17/1747 OR CXL HO1177 Y28JAN  \n" +
                "10.SSR TKNE HO HK1 SHASYX 1177 Y28JAN 0182037126371/1/P1\n" +
                "11.SSR CHLD HO HK1 19MAR10/P1                                                  +\n" +
                "12.OSI HO CTCT18964878487                                                      -13.OSI HO CTCM15961801058/P1\n" +
                "14.OSI HO CTCT13328003672   \n" +
                "15.RMK  \n" +
                "16.RMK CA/PDSLMV\n" +
                "17.RMK TJ AUTH SHA255   \n" +
                "18.RMK AUTOMATIC FARE QUOTE \n" +
                "19.FN/A/\n" +
                "20.TN/018-2037126371/P1 \n" +
                "21.FP/  \n" +
                "22.SHA205";


        pnr.setPnrDetail(pnrDetail);
        assertEquals("JSTJQL", pnr.getPnrNo());

        assertEquals(1, pnr.getPsgCount());
        assertEquals(0, pnr.getAdultCount());
        assertEquals(0, pnr.getInfantCount());
        assertEquals(1, pnr.getFlights().size());

        PnrPassengerDto psg = pnr.getPassengers().get(0);
        assertEquals("袁乐怡", psg.getPsgName());
        assertEquals(1, psg.getPsgNo());
        assertEquals("310115201003191169", psg.getIdNo());
        assertEquals("018-2037126371", psg.getTicketNo());
        assertEquals(1, psg.getPsgType());
    }

    @Test
     void testPnrDetail_9Passengers() {
        //包括婴儿
        String pnrDetail = "MARRIED SEGMENT EXIST IN THE PNR\n" +
                "  **ELECTRONIC TICKET PNR** \n" +
                " 1.CAI/MENGSHI 2.LI/JIANPING 3.LIANG/XIA 4.MO/ZHENYONG 5.MO/ZIYAN CHD 6.PAN/JIE  7.WANG/FENG 8.WANG/YILIANG 9.ZHANG/XIAOFEI JF9RMX  \n" +
                "10.  KL896  N1  TU20FEB  PVGAMS HK9   1240 1745      SEAME  1-- \n" +
                "11.  KL1697 L1  TU20FEB  AMSLIS HK9   2110 2310      SEAME -- 1 \n" +
                "12.    ARNK              LISBCN \n" +
                "13.  AF1649 L   SA03MAR  BCNCDG HK9   1505 1700      SEAME  12F \n" +
                "14.  AF5202 Q   SA03MAR  CDGPVG HK9   2005 1435+1    SEAME  \n" +
                "15.SHA/T SHA/T 0510-82722200/CHINA COMFORT WUXI TRAVEL SERVICE/CHEN RU ABCDEFG  16.BOOK SHZQL 1227 1706 \n" +
                "17.T                                                                           +\n" +
                "18.SSR OTHS 1E CZ FLT 769 OP BY KL -CKIN WITH KL                               -19.SSR OTHS 1E AF 1649 03MAR OPERATED BY JN \n" +
                "20.SSR OTHS 1E AF 5202 03MAR APIS DEST PAX DATA REQUIRED SSR DOCS   \n" +
                "21.SSR ADTK 1E TO KL BY 30DEC17/1600Z OTHERWISE WILL BE XXLD\n" +
                "22.SSR ADTK 1E BY SHA08JAN18/1706 OR CXL CZ BOOKING \n" +
                "23.SSR ADTK 1E TO AF BY 30DEC 1800 OTHERWISE WILL BE XLD\n" +
                "24.SSR TKNE KL HK1 PVGAMS 896 N20FEB 0745136763559/1/P9 \n" +
                "25.SSR TKNE KL HK1 PVGAMS 896 N20FEB 0745136763557/1/P8 \n" +
                "26.SSR TKNE KL HK1 PVGAMS 896 N20FEB 0745136763555/1/P7 \n" +
                "27.SSR TKNE KL HK1 PVGAMS 896 N20FEB 0745136763553/1/P6 \n" +
                "28.SSR TKNE KL HK1 PVGAMS 896 N20FEB 0745136763551/1/P5 \n" +
                "29.SSR TKNE KL HK1 PVGAMS 896 N20FEB 0745136763549/1/P4                        +\n" +
                "30.SSR TKNE KL HK1 PVGAMS 896 N20FEB 0745136763547/1/P3                        -31.SSR TKNE KL HK1 PVGAMS 896 N20FEB 0745136763545/1/P2 \n" +
                "32.SSR TKNE KL HK1 PVGAMS 896 N20FEB 0745136763543/1/P1 \n" +
                "33.SSR TKNE KL HK1 AMSLIS 1697 L20FEB 0745136763543/2/P1\n" +
                "34.SSR TKNE AF HK1 BCNCDG 1649 L03MAR 0745136763543/4/P1\n" +
                "35.SSR TKNE AF HK1 CDGPVG 5202 Q03MAR 0745136763544/1/P1\n" +
                "36.SSR TKNE KL HK1 AMSLIS 1697 L20FEB 0745136763545/2/P2\n" +
                "37.SSR TKNE AF HK1 BCNCDG 1649 L03MAR 0745136763545/4/P2\n" +
                "38.SSR TKNE AF HK1 CDGPVG 5202 Q03MAR 0745136763546/1/P2\n" +
                "39.SSR TKNE KL HK1 AMSLIS 1697 L20FEB 0745136763547/2/P3\n" +
                "40.SSR TKNE AF HK1 BCNCDG 1649 L03MAR 0745136763547/4/P3\n" +
                "41.SSR TKNE AF HK1 CDGPVG 5202 Q03MAR 0745136763548/1/P3                       +\n" +
                "42.SSR TKNE KL HK1 AMSLIS 1697 L20FEB 0745136763549/2/P4                       -43.SSR TKNE AF HK1 BCNCDG 1649 L03MAR 0745136763549/4/P4\n" +
                "44.SSR TKNE AF HK1 CDGPVG 5202 Q03MAR 0745136763550/1/P4\n" +
                "45.SSR TKNE KL HK1 AMSLIS 1697 L20FEB 0745136763551/2/P5\n" +
                "46.SSR TKNE AF HK1 BCNCDG 1649 L03MAR 0745136763551/4/P5\n" +
                "47.SSR TKNE AF HK1 CDGPVG 5202 Q03MAR 0745136763552/1/P5\n" +
                "48.SSR TKNE KL HK1 AMSLIS 1697 L20FEB 0745136763553/2/P6\n" +
                "49.SSR TKNE AF HK1 BCNCDG 1649 L03MAR 0745136763553/4/P6\n" +
                "50.SSR TKNE AF HK1 CDGPVG 5202 Q03MAR 0745136763554/1/P6\n" +
                "51.SSR TKNE KL HK1 AMSLIS 1697 L20FEB 0745136763555/2/P7\n" +
                "52.SSR TKNE AF HK1 BCNCDG 1649 L03MAR 0745136763555/4/P7\n" +
                "53.SSR TKNE AF HK1 CDGPVG 5202 Q03MAR 0745136763556/1/P7                       +\n" +
                "54.SSR TKNE KL HK1 AMSLIS 1697 L20FEB 0745136763557/2/P8                       -55.SSR TKNE AF HK1 BCNCDG 1649 L03MAR 0745136763557/4/P8\n" +
                "56.SSR TKNE AF HK1 CDGPVG 5202 Q03MAR 0745136763558/1/P8\n" +
                "57.SSR TKNE KL HK1 AMSLIS 1697 L20FEB 0745136763559/2/P9\n" +
                "58.SSR TKNE AF HK1 BCNCDG 1649 L03MAR 0745136763559/4/P9\n" +
                "59.SSR TKNE AF HK1 CDGPVG 5202 Q03MAR 0745136763560/1/P9\n" +
                "60.SSR DOCS KL HK1 P/CN/G61064101/CN/30OCT78/F/15APR22/ZHANG/XIAOFEI/P9 \n" +
                "61.SSR DOCS AF HK1 P/CN/G61064101/CN/30OCT78/F/15APR22/ZHANG/XIAOFEI/P9 \n" +
                "62.SSR DOCS KL HK1 P/CN/E16432745/CN/18JAN01/M/04MAY19/WANG/YILIANG/P8  \n" +
                "63.SSR DOCS AF HK1 P/CN/E16432745/CN/18JAN01/M/04MAY19/WANG/YILIANG/P8  \n" +
                "64.SSR DOCS KL HK1 P/CN/G55056178/CN/05MAR71/M/15SEP21/WANG/FENG/P7 \n" +
                "65.SSR DOCS AF HK1 P/CN/G55056178/CN/05MAR71/M/15SEP21/WANG/FENG/P7            +\n" +
                "66.SSR DOCS KL HK1 P/CN/G37736155/CN/17SEP09/M/16SEP19/PAN/JIE/P6              -67.SSR DOCS AF HK1 P/CN/G37736155/CN/17SEP09/M/16SEP19/PAN/JIE/P6   \n" +
                "68.SSR DOCS KL HK1 P/CN/E96372655/CN/09APR07/F/20APR22/MO/ZIYAN/P5  \n" +
                "69.SSR DOCS AF HK1 P/CN/E96372655/CN/09APR07/F/20APR22/MO/ZIYAN/P5  \n" +
                "70.SSR DOCS KL HK1 P/CN/G61076820/CN/08AUG78/M/17APR22/MO/ZHENYONG/P4   \n" +
                "71.SSR DOCS AF HK1 P/CN/G61076820/CN/08AUG78/M/17APR22/MO/ZHENYONG/P4   \n" +
                "72.SSR DOCS KL HK1 P/CN/E772024125/CN/15FEB88/F/29JUL28/LIANG/XIA/P3\n" +
                "73.SSR DOCS AF HK1 P/CN/E772024125/CN/15FEB88/F/29JUL28/LIANG/XIA/P3\n" +
                "74.SSR DOCS KL HK1 P/CN/E02202247/CN/15FEB86/F/29JUL22/LI/JIANPING/P2   \n" +
                "75.SSR DOCS AF HK1 P/CN/E02202247/CN/15FEB86/F/29JUL22/LI/JIANPING/P2   \n" +
                "76.SSR DOCS KL HK1 P/CN/G37736887/CN/03NOV82/F/17SEP19/CAI/MENGSHI/P1   \n" +
                "77.SSR DOCS AF HK1 P/CN/G37736887/CN/03NOV82/F/17SEP19/CAI/MENGSHI/P1          +\n" +
                "78.SSR CTCM KL HK1 13761691912/P1                                              -79.SSR CTCM AF HK1 13761691912/P1   \n" +
                "80.RMK  \n" +
                "81.RMK 1A/K63PG8\n" +
                "82.RMK TJ AUTH SHA771   \n" +
                "83.RMK TJ AUTH HGH888   \n" +
                "84.RMK  TP1920XXXXXXX9940 1119 021942.00CNY 6313\n" +
                "85.RMK  TP1920XXXXXXX9940 1119 020802.00CNY 6316\n" +
                "86.RMK  TP1920XXXXXXX9940 1119 021942.00CNY 6319\n" +
                "87.FN/A//P1/2/3/4/6/7/8/9   \n" +
                "88.FN/A//P5 \n" +
                "89.TN/074-5136763543-44/P1                                                     +\n" +
                "90.TN/074-5136763545-46/P2                                                     -91.TN/074-5136763547-48/P3  \n" +
                "92.TN/074-5136763549-50/P4  \n" +
                "93.TN/074-5136763551-52/P5  \n" +
                "94.TN/074-5136763553-54/P6  \n" +
                "95.TN/074-5136763555-56/P7  \n" +
                "96.TN/074-5136763557-58/P8  \n" +
                "97.TN/074-5136763559-60/P9  \n" +
                "98.FP/  \n" +
                "99.SHA205";


        pnr.setPnrDetail(pnrDetail);
        assertEquals("JF9RMX", pnr.getPnrNo());

        assertEquals(9, pnr.getPsgCount());
        assertEquals(8, pnr.getAdultCount());
        assertEquals(0, pnr.getInfantCount());
        assertEquals(4, pnr.getFlights().size());

        PnrPassengerDto psg = pnr.getPassengers().get(0);
        assertEquals("CAI/MENGSHI", psg.getPsgName());
        assertEquals(0, psg.getPsgType());
        assertEquals(1, psg.getPsgNo());
        assertEquals("074-5136763543-44", psg.getTicketNo());
        assertEquals("G37736887", psg.getIdNo());

        psg = pnr.getPassengers().get(4);
        assertEquals("MO/ZIYAN", psg.getPsgName());
        assertEquals(Constant.PSG_TYPE_CHD, psg.getPsgType());
        assertEquals(5, psg.getPsgNo());
        assertEquals("074-5136763551-52", psg.getTicketNo());
        assertEquals("E96372655", psg.getIdNo());
    }


    @Test
     void testPnrDetail_SsrDocsIsTooLong() {
        //包括婴儿
        String pnrDetail = "  **ELECTRONIC TICKET PNR**                                                     \n" +
                " 1.MCDERMOTT/IRELAND E MS 2.MCDERMOTT/JAMES RILEY MR 3.MCDERMOTT/JAMIE LEE MS   \n" +
                " 4.MCDERMOTT/RYAN PATRICK MR KR8NVX                                             \n" +
                " 5.  CA979  U   WE14FEB  PEKBKK RR4   1945 2345          E T3--                 \n" +
                " 6.  CA980  U   MO26FEB  BKKPEK HK4   0105 0630          E --T3                 \n" +
                " 7.PEK/T BJS/T-51283260/ZHI YANG SHANG LV(BEIJING)AVIATION SERVICE LTD.,CO/CUI  \n" +
                "    JIAN ABCDEFG                                                                \n" +
                " 8.HHS FOR BV                                                                   \n" +
                " 9.T                                                                            \n" +
                "10.SSR ADTK 1E BY PEK11JAN18/2056 OR CXL CA ALL SEGS                            \n" +
                "11.SSR TKNE CA HK1 PEKBKK 979 U14FEB 9995137015751/1/P4                         \n" +
                "12.SSR TKNE CA HK1 PEKBKK 979 U14FEB 9995137015750/1/P3                        +\n" +
                "\u0010                                                                               \n" +
                "                                                                                \n" +
                "                                                                                \n" +
                "13.SSR TKNE CA HK1 PEKBKK 979 U14FEB 9995137015749/1/P2                        -\n" +
                "14.SSR TKNE CA HK1 PEKBKK 979 U14FEB 9995137015748/1/P1                         \n" +
                "15.SSR TKNE CA HK1 BKKPEK 980 U26FEB 9995137015748/2/P1                         \n" +
                "16.SSR TKNE CA HK1 BKKPEK 980 U26FEB 9995137015749/2/P2                         \n" +
                "17.SSR TKNE CA HK1 BKKPEK 980 U26FEB 9995137015750/2/P3                         \n" +
                "18.SSR TKNE CA HK1 BKKPEK 980 U26FEB 9995137015751/2/P4                         \n" +
                "19.SSR DOCS CA HK1 P/USA/548785735/USA/20JUN00/M/06NOV26/MCDERMOTT/RYAN         \n" +
                "     PATRICK/P4                                                                 \n" +
                "20.SSR DOCS CA HK1 P/USA/548785732/USA/07FEB02/M/06NOV21/MCDERMOTT/JAMES RILEY  \n" +
                "    /P2                                                                         \n" +
                "21.SSR DOCS CA HK1 P/USA/548785733/USA/17FEB04/F/06NOV21/MCDERMOTT/IRELAND E    \n" +
                "    /P1                                                                        +\n" +
                "\u0010                                                                               \n" +
                "                                                                                \n" +
                "                                                                                \n" +
                "22.SSR DOCS CA HK1 P/USA/548785736/USA/21OCT66/F/06NOV26/MCDERMOTT/JAMIE LEE   -\n" +
                "    /P3                                                                         \n" +
                "23.SSR CKIN CA HK4 VICO9BJ10G39                                                 \n" +
                "24.OSI YY CTCT13701266980                                                       \n" +
                "25.OSI YY CTCM13810060046/P1/2/3/4                                              \n" +
                "26.RMK TJ PEK354                                                                \n" +
                "27.RMK CA/MG4WDJ                                                                \n" +
                "28.RMK FARECODE/9BJ10G39/CA979/PEKBKK/14FEB18/CNY6340.00                        \n" +
                "29.RMK FARECODE/9BJ10G39/CA980/BKKPEK/26FEB18/CNY6340.00                        \n" +
                "30.FN/A/FCNY6340.00/SCNY6340.00/C0.00/XCNY1212.00/TCNY90.00CN/TCNY7.00E7/       \n" +
                "    TCNY1115.00XT/ACNY7552.00                                                   \n" +
                "31.TN/999-5137015748/P1 \n" +
                "\n" +
                "                                                        +\n" +
                "\u0010                                                                               \n" +
                "                                                                                \n" +
                "                                                                                \n" +
                "32.TN/999-5137015749/P2 \n" +
                "\n" +
                "                                                        -\n" +
                "33.TN/999-5137015750/P3 \n" +
                "\n" +
                "                                                         \n" +
                "34.TN/999-5137015751/P4 \n" +
                "\n" +
                "                                                         \n" +
                "35.PEK354";


        pnr.setPnrDetail(pnrDetail);
        assertEquals("KR8NVX", pnr.getPnrNo());

        assertEquals(4, pnr.getPsgCount());
        assertEquals(4, pnr.getAdultCount());
        assertEquals(0, pnr.getInfantCount());
        assertEquals(2, pnr.getFlights().size());

        assertEquals(6340, pnr.getParValue());
        assertEquals(6340, pnr.getPrice());
        assertEquals(1212, pnr.getTax());
//        assertEquals(7552, pnr.Amount());

        PnrPassengerDto psg = pnr.getPassengers().get(0);
        assertEquals("MCDERMOTT/IRELAND E", psg.getPsgName());
        assertEquals(Constant.GENDER_FEMALE, psg.getGender());
        assertEquals(1, psg.getPsgNo());
        assertEquals("548785733", psg.getIdNo());
        assertEquals("999-5137015748", psg.getTicketNo());
        assertEquals(Constant.PSG_TYPE_ADU, psg.getPsgType());
    }


    @Test
     void testPnrDetail_FixError() {
        //包括婴儿
        String pnrDetail = "  **ELECTRONIC TICKET PNR**                                                     \n" +
                " 1.张雪梅 KNY5MX                                                                \n" +
                " 2.  CA1856 H   MO22JAN  SHAPEK RR1   2100 2320          E T2T3                 \n" +
                " 3.PEK/T BJS/T-51283260/ZHI YANG SHANG LV(BEIJING)AVIATION SERVICE LTD.,CO/CUI  \n" +
                "    JIAN ABCDEFG                                                                \n" +
                " 4.ZX FOR BV                                                                    \n" +
                " 5.T                                                                            \n" +
                " 6.SSR FOID CA HK1 NI152722197808151825/P1                                      \n" +
                " 7.SSR CKIN CA HK1 VICO9BJ10G39                                                 \n" +
                " 8.SSR FQTV CA HK1 SHAPEK 1856 H22JAN CA003112953046/P1                         \n" +
                " 9.SSR ADTK 1E BY PEK22JAN18/1646 OR CXL CA ALL SEGS                            \n" +
                "10.SSR TKNE CA HK1 SHAPEK 1856 H22JAN 9995175863051/1/P1                       +\n" +
                "\u0010                                                                               \n" +
                "                                                                                \n" +
                "                                                                                \n" +
                "11.OSI YY CTCT13701266980                                                      -\n" +
                "12.OSI YY CTCM15210247397/P1                                                    \n" +
                "13.RMK MP 13910707820                                                           \n" +
                "14.RMK TJ PEK354                                                                \n" +
                "15.RMK CA/PD8GQB                                                                \n" +
                "16.RMK AUTOMATIC FARE QUOTE                                                     \n" +
                "17.RMK AUTOMATIC FARE QUOTE                                                     \n" +
                "18.FN/A/RCNY1000.00/SCNY310.00/C0.00/XCNY207.00/TCNY207.00OC/OCNY50.00CN/       \n" +
                "    OEXEMPTYQ/ACNY517.00/P1                                                     \n" +
                "19.TN/999-5175863051/P1 \n" +
                "\n" +
                "                                                         \n" +
                "20.FP/CASH,CNY/P1                                                               \n" +
                "21.PEK354       ";
    }

    @Test
     void testPnrDetail_OnlyChild_2() {
        //包括婴儿
        String pnrDetail = " **ELECTRONIC TICKET PNR**                                                     \n" +
                " 1.ZHANG/ZIYU CHD HM2KC2                                                        \n" +
                " 2.  CA821  S   FR18MAY  PEKHKT RR1   1940 0040+1        E T3--                 \n" +
                " 3.  CA822  S   SA26MAY  HKTPEK HK1   0140 0755          E --T3                 \n" +
                " 4.PEK/T BJS/T-51283260/ZHI YANG SHANG LV(BEIJING)AVIATION SERVICE LTD.,CO/CUI  \n" +
                "    JIAN ABCDEFG                                                                \n" +
                " 5.WT                                                                           \n" +
                " 6.T                                                                            \n" +
                " 7.SSR SEAT CA HK1 PEKHKT 821 S18MAY 23BN                                       \n" +
                " 8.SSR SEAT CA HK1 HKTPEK 822 S26MAY 23BN                                       \n" +
                " 9.SSR CHML CA HK1 PEKHKT 821 S18MAY/P1                                         \n" +
                "10.SSR CHML CA HK1 HKTPEK 822 S26MAY/P1                                        +\n" +
                "\u0010                                                                               \n" +
                "                                                                                \n" +
                "                                                                                \n" +
                "11.SSR OTHS 1E CA BKG CXLD DUE TO TKT TIME EXPIRED                             -\n" +
                "12.SSR ADTK 1E BY PEK04MAY18/1940 OR CXL CA ALL SEGS                            \n" +
                "13.SSR TKNE CA HK1 PEKHKT 821 S18MAY 9992082971052/1/P1                         \n" +
                "14.SSR TKNE CA HK1 HKTPEK 822 S26MAY 9992082971052/2/P1                         \n" +
                "15.SSR DOCS CA HK1 P/CHN/E29718578/CHN/16SEP12/F/04SEP19/ZHANG/ZIYU/P1          \n" +
                "16.SSR CHLD CA HK1 16SEP12/P1                                                   \n" +
                "17.OSI CA CTCM13810099919/P1                                                    \n" +
                "18.RMK MP 13910099919                                                           \n" +
                "19.RMK TJ PEK354                                                                \n" +
                "20.RMK CA/NCH1MW                                                                \n" +
                "21.FN/A/FCNY2250.00/SCNY2250.00/C0.00/XCNY1121.00/TEXEMPTCN/TCNY7.00E7/         \n" +
                "    TCNY1114.00XT/ACNY3371.00                                                  +\n" +
                "\u0010                                                                               \n" +
                "                                                                                \n" +
                "                                                                                \n" +
                "22.TN/999-2082971052/P1                                                        -\n" +
                "23.PEK354                   ";


        pnr.setPnrDetail(pnrDetail);
        assertEquals("HM2KC2", pnr.getPnrNo());

        assertEquals(1, pnr.getPsgCount());
        assertEquals(0, pnr.getAdultCount());
        assertEquals(0, pnr.getInfantCount());
        assertEquals(2, pnr.getFlights().size());

        PnrPassengerDto psg = pnr.getPassengers().get(0);
        assertEquals("ZHANG/ZIYU", psg.getPsgName());
//        assertEquals(1, psg.getPsgNo());
//        assertEquals("310115201003191169", psg.getIdNo());
//        assertEquals("0182037126371", psg.getTicketNo());
//        assertEquals(1, psg.getPsgType());

        //TODO 价格:  只有一个乘机人（儿童），价格弄到成人上去了
        assertEquals(2250, pnr.getPrice(), 0.01);
    }

    @Disabled
    @Test
     void test_InstantDuration() {
        System.out.println("Begin");
        final Instant beginTime = Instant.now();
        while (true) {
            Duration duration = Duration.between(beginTime, Instant.now());

            System.out.println(Instant.now());
            System.out.println(duration.getSeconds());
            if (duration.getSeconds() >= 10) break;

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("End");
    }

    @Test
     void testPnrDetail_1() {
        String pnrDetail = "RTHM3RFJ                                                                       \n"
            + " 1.戴绪锦 2.孔伟 3.张毅 HM3RFJ  \n"
            + " 4.  SC4950 V   TU03SEP  SHACKG HK3   1030 1320          E T2T3 V1  \n"
            + " 5.SHA/T SHA/T021-6276220418917099670/SHANGHAI SHANGYOU INTERNATIONAL TRAVEL\n"
            + "     SERVICE CO., ABCDEFG   \n"
            + " 6.209 190902 1201  \n"
            + " 7.209 190902 1202  \n"
            + " 8.209 190902 1258  \n"
            + " 9.TL/1900/02SEP/SHA274 \n"
            + "10.FC/A/SHA B-03SEP19 A-03SEP19 SC CKG 980.00V1 CNY980.00END\n"
            + "11.SSR FOID SC HK1 NI320882198812073213/P3  \n"
            + "12.SSR FOID SC HK1 NI510921198910271414/P2  \n"
            + "13.SSR FOID SC HK1 NI320821198001154359/P1                                     +\n"
            + "\u007FPN                                                                             \n"
            + "14.SSR ADTK 1E BY SHA02SEP19/1328 OR CXL SC4950 V03SEP                         -\n"
            + "15.OSI SC CTCT15301883082   \n"
            + "16.OSI SC CTCM18917007892/P1\n"
            + "17.OSI SC CTCM1361832356/P2 \n"
            + "18.OSI SC CTCM18917093601/P3\n"
            + "19.RMK CMS/A/** \n"
            + "20.RMK OT/A/0/17643/3-1SC1570246N1CKG   \n"
            + "21.RMK CA/NY0WG0\n"
            + "22.RMK AUTOMATIC FARE QUOTE \n"
            + "23.FN/A/FCNY980.00/SCNY980.00/C0.00/XCNY50.00/TCNY50.00CN/TEXEMPTYQ/ACNY1030.00 \n"
            + "24.EI/BUDEQIANZHUAN不得签转/GAIQITUIPIAOSHOUFEI改期退票收费 \n"
            + "25.FP/CASH,CNY  ";

        pnr.setPnrDetail(pnrDetail);
        assertEquals("HM3RFJ", pnr.getPnrNo());
        assertEquals("NY0WG0", pnr.getBigPnrNo());
        assertEquals(3, pnr.getPsgCount());
        assertEquals(1, pnr.getSegCount());

        assertEquals("戴绪锦", pnr.getPassengers().get(0).getPsgName());
        assertEquals("孔伟", pnr.getPassengers().get(1).getPsgName());
        assertEquals("张毅", pnr.getPassengers().get(2).getPsgName());
        assertEquals(1, (long)pnr.getPassengers().get(0).getIdType());
        assertEquals(1, (long)pnr.getPassengers().get(1).getIdType());
        assertEquals(1, (long)pnr.getPassengers().get(2).getIdType());
        assertEquals("320821198001154359", pnr.getPassengers().get(0).getIdNo());
        assertEquals("510921198910271414", pnr.getPassengers().get(1).getIdNo());
        assertEquals("320882198812073213", pnr.getPassengers().get(2).getIdNo());
        assertEquals("18917007892", pnr.getPassengers().get(0).getMobile());
        assertEquals(null, pnr.getPassengers().get(1).getMobile());
        assertEquals("18917093601", pnr.getPassengers().get(2).getMobile());
    }

    @Test
     void testPnrDetail_2() {
        String pnrDetail = "**ELECTRONIC TICKET PNR** \n"
            + " 1.TIAN/XIAO MR JMWBHL  \n"
            + " 2.  KL2110 R1  FR20SEP  PVGCDG HK1   2320 0540+1    SEAME      OP-AF111\n"
            + " 3.  KL2296 R1  SA21SEP  CDGPTY HK1   1355 1745      SEAME      OP-AF474\n"
            + " 4.  AF475  R2  SA19OCT  PTYCDG HK1   2000 1320+1    SEAME --2E \n"
            + " 5.  AF112  R2  SU20OCT  CDGPVG HK1   1510 0825+1    SEAME 2E 1 \n"
            + " 6.SHA/T SHA/T021-6276220418917099670/SHANGHAI SHANGYOU INTERNATIONAL TRAVEL\n"
            + "     SERVICE CO., ABCDEFG   \n"
            + " 7.209 190918 1348  \n"
            + " 8.T\n"
            + " 9.SSR OTHS 1E AF 475 19OCT APIS DEST PAX DATA REQUIRED SSR DOCS               +\n"
            + "\u007Fpg1                                                                            \n"
            + "MARRIED SEGMENT EXIST IN THE PNR\n"
            + "  **ELECTRONIC TICKET PNR** \n"
            + " 1.TIAN/XIAO MR JMWBHL  \n"
            + " 2.  KL2110 R1  FR20SEP  PVGCDG HK1   2320 0540+1    SEAME      OP-AF111\n"
            + " 3.  KL2296 R1  SA21SEP  CDGPTY HK1   1355 1745      SEAME      OP-AF474\n"
            + " 4.  AF475  R2  SA19OCT  PTYCDG HK1   2000 1320+1    SEAME --2E \n"
            + " 5.  AF112  R2  SU20OCT  CDGPVG HK1   1510 0825+1    SEAME 2E 1 \n"
            + " 6.SHA/T SHA/T021-6276220418917099670/SHANGHAI SHANGYOU INTERNATIONAL TRAVEL\n"
            + "     SERVICE CO., ABCDEFG   \n"
            + " 7.209 190918 1348  \n"
            + " 8.T\n"
            + " 9.SSR OTHS 1E AF 475 19OCT APIS DEST PAX DATA REQUIRED SSR DOCS\n"
            + "10.SSR OTHS 1E AF 112 20OCT APIS DEST PAX DATA REQUIRED SSR DOCS\n"
            + "11.SSR ADTK 1E TO AF BY 21SEP 1400 OTHERWISE WILL BE XLD\n"
            + "12.SSR TKNE KL HK1 PVGCDG 2110 R20SEP 0746356698947/1/P1\n"
            + "13.SSR TKNE KL HK1 CDGPTY 2296 R21SEP 0746356698947/2/P1\n"
            + "14.SSR TKNE AF HK1 PTYCDG 475 R19OCT 0746356698947/3/P1 \n"
            + "15.SSR TKNE AF HK1 CDGPVG 112 R20OCT 0746356698947/4/P1 \n"
            + "16.SSR DOCS KL HK1 P/CN/PE1732995/CN/05JAN90/M/04JAN24/TIAN/XIAO/P1 \n"
            + "17.SSR DOCS AF HK1 P/CN/PE1732995/CN/05JAN90/M/04JAN24/TIAN/XIAO/P1            +\n"
            + "\u007Fpn                                                                             \n"
            + "18.SSR CTCM AF HK1 18917007892/P1                                              -\n"
            + "19.SSR CTCM KL HK1 18917007892/P1   \n"
            + "20.OSI KL CTCT15301883082   \n"
            + "21.OSI AF CTCT15301883082   \n"
            + "22.OSI YY OIN CN04654   \n"
            + "23.RMK TJ SHA274\n"
            + "24.RMK 1A/PQIOKS\n"
            + "25.RMK FARECODE/T718970/KL2110/PVGCDG/20SEP19/CNY8200.00\n"
            + "26.RMK FARECODE/T718970/KL2296/CDGPTY/21SEP19/CNY8200.00\n"
            + "27.RMK FARECODE/T718970/AF475/PTYCDG/19OCT19/CNY8200.00 \n"
            + "28.RMK FARECODE/T718970/AF112/CDGPVG/20OCT19/CNY8200.00 \n"
            + "                                                                               +\n"
            + "\u007Fpn                                                                             \n"
            + "29.FN/A/FCNY8200.00/SCNY8200.00/C0.00/XCNY4802.00/TCNY90.00CN/TCNY74.00FR/     -\n"
            + "    TCNY4638.00XT/ACNY13002.00  \n"
            + "30.TN/074-6356698947/P1 \n"
            + "31.FP/CASH,CNY  \n"
            + "32.SHA274   ";

        pnr.setPnrDetail(pnrDetail);
        assertEquals("JMWBHL", pnr.getPnrNo());
        assertEquals(1, pnr.getPsgCount());
        assertEquals(4, pnr.getSegCount());

        assertEquals("TIAN/XIAO", pnr.getPassengers().get(0).getPsgName());
        assertEquals(2, (long)pnr.getPassengers().get(0).getIdType());
        assertEquals("PE1732995", pnr.getPassengers().get(0).getIdNo());
        assertEquals("18917007892", pnr.getPassengers().get(0).getMobile());

        assertEquals("KL2110", pnr.getFlights().get(0).getFlight().getFlightNo());
        assertEquals("AF111", pnr.getFlights().get(0).getFlight().getOpFlightNo());
    }

    @Test
     void testPnrDetail_3() {
        String pnrDetail = "1.WANG/DEYUNMR HR43E2                                                          \n"
            + " 2.  TR121  C   SU22SEP  WUHSIN HK1   1455 2005      SEAME -- 2                 \n"
            + " 3.SHA/T SHA/T-021-62839061 SHA DONG LI BUSINESS LTD.,CO /TANG/ZHEN FENG        \n"
            + "     ABCDEFG                                                                    \n"
            + " 4.DL 190822 1655 BKXMXDI                                                       \n"
            + " 5.T                                                                            \n"
            + " 6.SSR OTHS 1E TR CONFO NBR TFLKNE                                              \n"
            + " 7.SSR OTHS 1E SUBJ CXL ON/BEFORE 23AUG 0855Z WITHOUT PAYMENT                   \n"
            + " 8.SSR OTHS 1E ITIN CONFIRMED - MUST PROVIDE PAYMENT                            \n"
            + " 9.SSR OTHS 1E MOST FARES EXCL GOV FARES REQUIRE TKT WITHIN 24 HRS              \n"
            + "10.SSR ADTK 1E TO TR ON/BEFORE 23AUG 0855Z OTHERWISE WILL BE XLD                \n"
            + "11.SSR TKNE TR HK1 WUHSIN 121 C22SEP 6686354329100/1/P1                         \n"
            + "12.SSR DOCS TR HK1 P/SG/K1119563K/SG/26FEB57/M/16DEC24/WANG/DEYUN/P1            \n"
            + "13.SSR CTCM TR HK1 15301883082/P1                                               \n"
            + "14.OSI TR CTCT15801780348                                                       \n"
            + "15.OSI TR CTCT15802175978                                                       \n"
            + "16.RMK TJ SHA697                                                                \n"
            + "17.RMK TJ AUTH SHA697       ";

        pnr.setPnrDetail(pnrDetail);
        assertEquals("HR43E2", pnr.getPnrNo());
        assertEquals(1, pnr.getPsgCount());
        assertEquals(1, pnr.getSegCount());

        assertEquals("WANG/DEYUN", pnr.getPassengers().get(0).getPsgName());
        assertEquals(2, (long)pnr.getPassengers().get(0).getIdType());
        assertEquals("K1119563K", pnr.getPassengers().get(0).getIdNo());
        assertEquals("15301883082", pnr.getPassengers().get(0).getMobile());
    }
}
