package cn.buk.common.dto.eterm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TprInfoDtoTest {

    private TprInfoDto info;

    @BeforeEach
    public void setUp() {
        info = new TprInfoDto();
    }

    @Test
    public void test_TprResponse_1() {
        String detail = "********************************************************************************" +
                "*                CAAC  MIS  PASSTED-DAILY-SALES-REPORT                         *" +
                "*                                                                              *" +
                "*   OFFICE : SHA205    IATA NUMBER : 08301812    DEVICE : 3/  1389             *" +
                "*   DATE   : 25MAR17                             AIRLINE:   ALL                *" +
                "--------------------------------------------------------------------------------" +
                "  TKT-NUMBER      ORIG-DEST  COLLECTION   TAXS   COMM       PNR   AGENT \n" +
                "---------------   ---------  ----------- -----  ------    -----   ----- \n" +
                "784-1361662284    SHA SHA     1050.00    100.00   20.00    HE2MQK 72468 \n" +
                "131-5736616608    ET-REFUND   8023.00    373.00   0.00%            6127 \n" +
                "784-1361662286    CTU CAN      860.00     50.00   10.00    KZN9LV 72467 \n" +
                "876-1361662287    CAN CTU     1040.00     50.00   18.00    HQM9TQ 72467 \n" +
                "479-1361662288    WUX WUX     2020.00    100.00   43.00    HEXHND  6127        -" +
                "479-1361662289    WUX WUX     2020.00    100.00   43.00    HEXHND  6127 \n" +
                "731-1361662290    XMN TYN     1130.00     50.00   18.00    KZQ54P  6127 \n" +
                "731-1361662291    XMN TYN     1130.00     50.00   18.00    KZQ54P  6127 \n" +
                "781-5900916787 C1 CTU CTU     2050.00    100.00   20.00    HRNJ0T  6127 \n" +
                "781-5900916788 C2 CTU CTU                                  HRNJ0T  6127 \n" +
                "784-1361662294    WUH HGH     1090.00     50.00   20.00    HFWWW4  6127 \n" +
                "784-1361662295    WUH HGH     1520.00     50.00   20.00    HFWWWT  6127 \n" +
                "324-1361662296    HFE TAO      320.00     50.00   18.00    JEQ8K7  6127 \n" +
                "781-1361662297    CKG WUX     2310.00     50.00   40.00    KR5J5F  6127 \n" +
                "784-1361662298    SZX WUH     1180.00     50.00   10.00    KWZR0Q  6127 \n" +
                "999-1361662299    WUH SZX      520.00     50.00   5.00     JXHM2P  6127        +\n" +
                "999-1361662300    BJS TAO     1380.00     50.00   15.00    HDX0F1  6127        -" +
                "479-1361662301    WUX WUX     2090.00    100.00   43.00    HFS46N  6127 \n" +
                "*==============================================================================*" +
                "      TOTAL TICKETS:       18( TICKETS VOID     0    TICKETS REFUND   0  )  \n" +
                " -----------------------------NORMAL TICKETS----------------------------------- " +
                "   NORMAL FARE -- AMOUNT :      24340.00               CNY  \n" +
                "      CARRIERS -- AMOUNT :      23909.00               CNY  \n" +
                "    NORMAL TAX -- AMOUNT :       1100.00               CNY  \n" +
                " NORMAL COMMIT -- AMOUNT :        431.00               CNY  \n" +
                "*------------------------------REFUND TICKETS--------------------------------*  " +
                "    NET REFUND -- AMOUNT :          0.00               CNY  \n" +
                "     DEDUCTION -- AMOUNT :          0.00               CNY                     +\n" +
                "    REFUND TAX -- AMOUNT :          0.00               CNY                     -" +
                " REFUND COMMIT -- AMOUNT :          0.00               CNY  \n" +
                "*==============================================================================**";

        detail = detail.replaceAll("\n", "\r");

        info.setCmdResult(detail);

        assertEquals(18, info.getTicketCount());
        assertEquals(18, info.getTotalTicket());

        TprItemDto item = info.getItems().get(0);
        assertEquals("784-1361662284", item.getTicketNo());
        assertEquals("HE2MQK", item.getPnrNo());
        assertEquals(1050, item.getPrice(), 0.001);
        assertEquals(100, item.getTax(), 0.001);
        assertEquals(20, item.getCommission(), 0.001);

        item = info.getItems().get(1);
        assertEquals("131-5736616608", item.getTicketNo());
        assertEquals("", item.getPnrNo());
        assertEquals(8023, item.getPrice(), 0.001);
        assertEquals(373, item.getTax(), 0.001);
        assertEquals(0, item.getCommission(), 0.001);
        assertEquals(TprItemDto.STATUS_REFUND, item.getStatus());
        assertNull(item.getOrig());
        assertNull(item.getDest());


        item = info.getItems().get(8);
        assertEquals("781-5900916787", item.getTicketNo());
        assertEquals("HRNJ0T", item.getPnrNo());
        assertEquals(2050, item.getPrice(), 0.001);
        assertEquals(100, item.getTax(), 0.001);
        assertEquals(20, item.getCommission(), 0.001);
        assertEquals(0, item.getStatus());
        assertEquals("CTU", item.getOrig());
        assertEquals("CTU", item.getDest());
        assertEquals("C1", item.getConjTag());
        item = info.getItems().get(9);
        assertEquals("781-5900916788", item.getTicketNo());
        assertEquals("HRNJ0T", item.getPnrNo());
        assertEquals(0, item.getPrice(), 0.001);
        assertEquals(0, item.getTax(), 0.001);
        assertEquals(0, item.getCommission(), 0.001);
        assertEquals(0, item.getStatus());
        assertEquals("CTU", item.getOrig());
        assertEquals("CTU", item.getDest());
        assertEquals("C2", item.getConjTag());

        assertEquals("479-1361662301", info.getItems().get(17).getTicketNo());
        assertEquals("HFS46N", info.getItems().get(17).getPnrNo());
        assertEquals(2090, info.getItems().get(17).getPrice(), 0.001);
        assertEquals(100, info.getItems().get(17).getTax(), 0.001);
        assertEquals(43, info.getItems().get(17).getCommission(), 0.001);

        assertEquals("3", info.getDeviceId());
        assertEquals("2017-03-25", info.getSellDate());
    }

    @Test
    public void test_TprResponse_2() {
        String detail = "********************************************************************************\n" +
                "*                CAAC  MIS  PASSTED-DAILY-SALES-REPORT                         *\n" +
                "*                                                                              *\n" +
                "*   OFFICE : SHA360    IATA NUMBER : 08329985    DEVICE : 1                    *\n" +
                "*   DATE   : 17DEC18                             AIRLINE:   ALL                *\n" +
                "--------------------------------------------------------------------------------\n" +
                "  TKT-NUMBER      ORIG-DEST  COLLECTION   TAXS   COMM       PNR   AGENT         \n" +
                "----------------  ---------  ----------- -----  ------    -----   -----         \n" +
                "836-3169868852    WNZ NTG      470.00     60.00  18.00     JNQDDR 27472         \n" +
                "836-3169868820    ET-REFUND    659.00     60.00  25.00            27472         \n" +
                "836-3169868871    HAK SJW     1120.00     10.00  0.00%     JV5M5L 27468         \n" +
                "836-3169868875    HAK SJW-EX     0.00            0.00%     HRZ0F6 27472         \n" +
                "836-3169868876    HAK SJW-EX     0.00            0.00%     KFTM0M 27472         \n" +
                "836-3169868877    HAK SJW-EX     0.00            0.00%     HWTRP8 27472         \n" +
                "836-3169868889    SJW CKG      660.00     10.00  0.00%     JN8NJS 27468         \n" +
                "866-3169868891    TSN SYX-EX   210.00    648.00  0.00%     JPXQPP 27472         \n" +
                "836-3169868892    ET-REFUND    937.00     80.00  25.00            27472         \n" +
                "*==============================================================================*\n" +
                "      TOTAL TICKETS:      114( TICKETS VOID     0    TICKETS REFUND   4  )      \n" +
                "*------------------------------NORMAL TICKETS----------------------------------*\n" +
                "   NORMAL FARE -- AMOUNT :     135020.00               CNY                      \n" +
                "      CARRIERS -- AMOUNT :     132836.00               CNY                      \n" +
                "    NORMAL TAX -- AMOUNT :       8818.00               CNY                      \n" +
                " NORMAL COMMIT -- AMOUNT :       2184.00               CNY                      \n" +
                "*------------------------------REFUND TICKETS----------------------------------*\n" +
                "    NET REFUND -- AMOUNT :       1780.00               CNY                      \n" +
                "     DEDUCTION -- AMOUNT :        614.00               CNY                      \n" +
                "    REFUND TAX -- AMOUNT :        340.00               CNY                      \n" +
                " REFUND COMMIT -- AMOUNT :         66.00               CNY                      \n" +
                "*==============================================================================*\n";

        detail = detail.replaceAll("\n", "\r");

        info.setCmdResult(detail);

        assertEquals(114, info.getTotalTicket());
        assertEquals(9, info.getTicketCount());

        TprItemDto item = info.getItems().get(8);
        assertEquals("836-3169868892", item.getTicketNo());
        assertEquals("", item.getPnrNo());
        assertEquals(937, item.getPrice(), 0.001);
        assertEquals(80, item.getTax(), 0.001);
        assertEquals(25, item.getCommission(), 0.001);
        assertEquals(TprItemDto.STATUS_REFUND, item.getStatus());

        //836-3169868852    WNZ NTG      470.00     60.00  18.00     JNQDDR 27472
        item = info.getItems().get(0);
        assertEquals("836-3169868852", item.getTicketNo());
        assertEquals("JNQDDR", item.getPnrNo());
        assertEquals(470, item.getPrice(), 0.001);
        assertEquals(60, item.getTax(), 0.001);
        assertEquals(18, item.getCommission(), 0.001);

        assertEquals("1", info.getDeviceId());
        assertEquals("2018-12-17", info.getSellDate());
    }

    @Test
    public void test_TprResponse_3() {
        String detail = "\u0010tpr:1/18jan19                                                                  \n" +
                "********************************************************************************\n" +
                "*                CAAC  MIS  PASSTED-DAILY-SALES-REPORT                         *\n" +
                "*                                                                              *\n" +
                "*   OFFICE : SHA360    IATA NUMBER : 08329985    DEVICE : 1                    *\n" +
                "*   DATE   : 18JAN19                             AIRLINE:   ALL                *\n" +
                "--------------------------------------------------------------------------------\n" +
                "  TKT-NUMBER      ORIG-DEST  COLLECTION   TAXS   COMM       PNR   AGENT         \n" +
                "----------------  ---------  ----------- -----  ------    -----   -----         \n" +
                "479-3405989825    NNG WXN      670.00     50.00  18.00     KNK9CR 27472         \n" +
                "836-3401430941    ET-REFUND    629.00     80.00  18.00            27468         \n" +
                "*==============================================================================*\n" +
                "      TOTAL TICKETS:      119( TICKETS VOID     0    TICKETS REFUND   3  )      \n" +
                "*------------------------------NORMAL TICKETS----------------------------------*\n" +
                "   NORMAL FARE -- AMOUNT :     104040.00               CNY                      \n" +
                "      CARRIERS -- AMOUNT :     101835.00               CNY                      \n" +
                "    NORMAL TAX -- AMOUNT :       5800.00               CNY                      \n" +
                " NORMAL COMMIT -- AMOUNT :       2205.00               CNY                      \n" +
                "*------------------------------REFUND TICKETS----------------------------------*\n" +
                "    NET REFUND -- AMOUNT :       2533.00               CNY                      \n" +
                "     DEDUCTION -- AMOUNT :        243.00               CNY                      \n" +
                "    REFUND TAX -- AMOUNT :        240.00               CNY                     \n" +
                " REFUND COMMIT -- AMOUNT :         54.00               CNY                      \n" +
                "*==============================================================================*\n";

        detail = detail.replaceAll("\n", "\r");

        info.setCmdResult(detail);

        assertEquals(119, info.getTotalTicket());

        TprItemDto item = info.getItems().get(1);
        assertEquals("836-3401430941", item.getTicketNo());
        assertEquals("", item.getPnrNo());
        assertEquals(629, item.getPrice(), 0.001);
        assertEquals(80, item.getTax(), 0.001);
        assertEquals(18, item.getCommission(), 0.001);

        //"479-3405989825    NNG WXN      670.00     50.00  18.00     KNK9CR 27472         \n" +
        item = info.getItems().get(0);
        assertEquals("479-3405989825", item.getTicketNo());
        assertEquals("KNK9CR", item.getPnrNo());
        assertEquals(670, item.getPrice(), 0.001);
        assertEquals(50, item.getTax(), 0.001);
        assertEquals(18, item.getCommission(), 0.001);

        assertEquals("1", info.getDeviceId());
        assertEquals("2019-01-18", info.getSellDate());
    }

    @Test
    public void test_TslResponse_1() {
        String detail = "********************************************************************************\n" +
                "*                  CAAC  MIS  OPTAT  DAILY-SALES-REPORT                        *\n" +
                "*                                                                              *\n" +
                "*   OFFICE : SHA205    IATA NUMBER : 08301812    DEVICE : 1/  69321            *\n" +
                "*   DATE   : 19APR                               AIRLINE:   ALL                *\n" +
                "--------------------------------------------------------------------------------\n" +
                "  TKT-NUMBER      ORIG-DEST  COLLECTION    TAXS    COMM     PNR   AGENT         \n" +
                "--------------------------------------------------------------------------------\n" +
                "781-3680452142    SHA SHA     1400.00     799.00  0.00%    JS6YV6 6127          \n" +
                "781-3680452144     VOID       1102  19APR                  JFWEN2 6127         -\n" +
                "999-3680452147    SFO BJS     2350.00    1393.00  0.00%    HMS4EM 6127          \n" +
                "999-3678352781    ET-REFUND   3620.00     900.00  0.00%           6127          \n" +
                "999-3680661316    SHA SHA     1250.00     799.00  0.00%    HV2V5T 6127          \n" +
                "781-3680661364    SHA SHA     2050.00     413.00  0.00%    HR09S0 6127          \n" +
                "781-5900916787 C1 CTU CTU     2050.00    100.00   20.00    HRNJ0T  6127 \n" +
                "781-5900916788 C2 CTU CTU                                  HRNJ0T  6127 \n" +
                "836-3169868875    HAK SJW-EX   900.00            0.00%     HRZ0F6 27472         \n" +
                "836-3169868876    HAK SJW-EX   800.00            0.00%     KFTM0M 27472         \n" +
                "*==============================================================================*\n" +
                "      TOTAL TICKETS:      68 (      1 TICKETS VOID /      1 TICKETS REFUND )    \n" +
                " ---------------NORMAL TICKETS--------------------------------------------------\n" +
                "   NORMAL  FARE-- AMOUNT :     137060.00               CNY                      \n" +
                "      CARRIERS -- AMOUNT :     137014.00               CNY                     +\n" +
                "        COMMIT -- AMOUNT :         46.00               CNY                     -\n" +
                "   NORMAL  TAX -- AMOUNT :      58964.00               CNY                      \n" +
                " ---------------REFUND TICKETS------------------------------------------------- \n" +
                "    NET REFUND -- AMOUNT :       3620.00               CNY                      \n" +
                "     DEDUCTION -- AMOUNT :       1210.00               CNY                      \n" +
                " REFUND    TAX -- AMOUNT :        900.00               CNY                      \n" +
                " REFUND COMMIT -- AMOUNT :          0.00               CNY                      \n" +
                "*==============================================================================*\n" +
                "*                                                                               \n";

        detail = detail.replaceAll("\n", "\r");

        info.setCmdResult(detail);

        assertEquals(68, info.getTotalTicket());

        TprItemDto item = info.getItems().get(5);
        assertEquals("781-3680661364", item.getTicketNo());
        assertEquals("HR09S0", item.getPnrNo());
        assertEquals(2050, item.getPrice(), 0.001);
        assertEquals(413, item.getTax(), 0.001);
        assertEquals(0, item.getCommission(), 0.001);

        item = info.getItems().get(0);
        assertEquals("781-3680452142", item.getTicketNo());
        assertEquals("JS6YV6", item.getPnrNo());
        assertEquals(1400, item.getPrice(), 0.001);
        assertEquals(799, item.getTax(), 0.001);
        assertEquals(0, item.getCommission(), 0.001);

        item = info.getItems().get(1);
        assertEquals("781-3680452144", item.getTicketNo());
        assertEquals("JFWEN2", item.getPnrNo());
        assertEquals(16, item.getStatus());
        assertEquals(0, item.getPrice(), 0.001);
        assertEquals(0, item.getTax(), 0.001);
        assertEquals(0, item.getCommission(), 0.001);

        item = info.getItems().get(3);
        assertEquals("999-3678352781", item.getTicketNo());
        assertEquals("", item.getPnrNo());
        assertEquals(4, item.getStatus());
        assertEquals(3620, item.getPrice(), 0.001);
        assertEquals(900, item.getTax(), 0.001);
        assertEquals(0, item.getCommission(), 0.001);

//        "781-3680452142    SHA SHA     1400.00     799.00  0.00%    JS6YV6 6127          \n" +
//                "781-3680452143    MFM SHA     2130.00     159.00  0.00%    HNE4PE 6127          \n" +
//                "781-3680452145    SHA SHA    24400.00    4176.00  0.00%    KD94XP 6127          \n" +
//                "105-3680452146    SHA SHA     4600.00    3170.00  1.00%    HS01NB 6127         +\n" +
//                "781-3680452144     VOID       1102  19APR                  JFWEN2 6127         -\n" +
//                "999-3678352781    ET-REFUND   3620.00     900.00  0.00%           6127          \n" +
//                "999-3680661316    SHA SHA     1250.00     799.00  0.00%    HV2V5T 6127          \n" +


                assertEquals("1", info.getDeviceId());
//        assertEquals("2021-04-19", info.getSellDate());
    }

    @Test
    public void test_TslResponse_2() {
        String detail = "********************************************************************************\n"
            + "*                  CAAC  MIS  OPTAT  DAILY-SALES-REPORT                        *\n"
            + "*                                                                              *\n"
            + "*   OFFICE : SHA205    IATA NUMBER : 08301812    DEVICE : 40  37134            *\n"
            + "*   DATE   : 25MAY                               AIRLINE:   ALL                *\n"
            + "--------------------------------------------------------------------------------\n"
            + "  TKT-NUMBER      ORIG-DEST  COLLECTION    TAXS    COMM     PNR   AGENT         \n"
            + "--------------------------------------------------------------------------------\n"
            + "220-9577737401    SHA SHA     4400.00    4022.00  7.00%    JT75F6 6127          \n"
            + "014-9577737402    SHA SHA     8970.00    2682.00  0.00%    HYDDE7 6127          \n"
            + "014-9577737403    SHA SHA     8970.00    2682.00  0.00%    HYDDE7 6127          \n"
            + "014-9577737404    SHA SHA     8970.00    2682.00  0.00%    HYDDE7 6127         +\n"
            + "724-9577737405    SHA SHA     5700.00    3031.00  7.00%    JZ698L 6127         -\n"
            + "724-9577737406    SHA SHA     5700.00    3031.00  7.00%    JZ698L 6127          \n"
            + "724-9577737407    SHA SHA     5700.00    3031.00  7.00%    JZ698L 6127          \n"
            + "*==============================================================================*\n"
            + "      TOTAL TICKETS:       7 (      0 TICKETS VOID /      0 TICKETS REFUND )    \n"
            + " ---------------NORMAL TICKETS--------------------------------------------------\n"
            + "   NORMAL  FARE-- AMOUNT :      48410.00               CNY                      \n"
            + "      CARRIERS -- AMOUNT :      46905.00               CNY                      \n"
            + "        COMMIT -- AMOUNT :       1505.00               CNY                      \n"
            + "   NORMAL  TAX -- AMOUNT :      21161.00               CNY                      \n"
            + " ---------------REFUND TICKETS------------------------------------------------- \n"
            + "    NET REFUND -- AMOUNT :          0.00               CNY                      \n"
            + "     DEDUCTION -- AMOUNT :          0.00               CNY                      \n"
            + " REFUND    TAX -- AMOUNT :          0.00               CNY                      \n"
            + " REFUND COMMIT -- AMOUNT :          0.00               CNY                      \n"
            + "*==============================================================================*\n"
            + "*                                                 ";

        detail = detail.replaceAll("\n", "\r");

        info.setCmdResult(detail);

        assertEquals(7, info.getTotalTicket());

        TprItemDto item = info.getItems().get(0);
        assertEquals("220-9577737401", item.getTicketNo());
        assertEquals("JT75F6", item.getPnrNo());
        assertEquals(4400, item.getPrice(), 0.001);
        assertEquals(4022, item.getTax(), 0.001);
        assertEquals(0, item.getCommission(), 0.001);
        assertEquals(7, item.getCommissionRate(), 0.001);
    }


    @Test
    public void test_TslResponse_3() {
        String detail = "*********************************************************************************                  CAAC  MIS  OPTAT  DAILY-SALES-REPORT                        **                                                                              **   OFFICE : SHA274    IATA NUMBER : 08320222    DEVICE : 1/  16536            **   DATE   : 26MAR                               AIRLINE:   ALL                *--------------------------------------------------------------------------------  TKT-NUMBER      ORIG-DEST  COLLECTION    TAXS    COMM     PNR   FOP   AGENT   --------------------------------------------------------------------------------781-1939886903    CKG SHA     1430.00      50.00  15.00    JPXJXB CAS  14601\n" +
                "784-1939886902    CTU BJS     1630.00      50.00  15.00    HDWENV CAS  14601\n" +
                "784-1939886901    BJS CTU     1440.00      50.00  10.00    HDJJM5 CAS  14601\n" +
                "781-1939886900    SYX SHA     2130.00      50.00  15.00    KWPTBH CAS  14601   +\n" +
                "784-1939886899    KMG SHA     1100.00      50.00  10.00    HT3K4Y CAS  14582   -784-1939886898    CAN HGH      680.00      50.00  10.00    KZ99ML CAS  14582\n" +
                "784-1939886897    NNG CKG      690.00      50.00  15.00    JYJ16P CAS  14582\n" +
                "781-1939886896    TAO SHA      930.00      50.00  10.00    HV74YN CAS  14582\n" +
                "*==============================================================================*      TOTAL TICKETS:       8 (      0 TICKETS VOID /      0 TICKETS REFUND )\n" +
                " ---------------NORMAL TICKETS--------------------------------------------------   NORMAL  FARE-- AMOUNT :      10030.00               CNY  \n" +
                "      CARRIERS -- AMOUNT :       9930.00               CNY  \n" +
                "        COMMIT -- AMOUNT :        100.00               CNY  \n" +
                "   NORMAL  TAX -- AMOUNT :        400.00               CNY  \n" +
                " ---------------REFUND TICKETS-------------------------------------------------+\n" +
                "    NET REFUND -- AMOUNT :          0.00               CNY                     -     DEDUCTION -- AMOUNT :          0.00               CNY  \n" +
                " REFUND    TAX -- AMOUNT :          0.00               CNY  \n" +
                " REFUND COMMIT -- AMOUNT :          0.00               CNY  \n" +
                "*==============================================================================**\n";

        detail = detail.replaceAll("\n", "\r");

        info.setCmdResult(detail);

        assertEquals(8, info.getTotalTicket());

        TprItemDto item = info.getItems().get(0);
        assertEquals("781-1939886903", item.getTicketNo());
        assertEquals("JPXJXB", item.getPnrNo());
        assertEquals(1430, item.getPrice(), 0.001);
        assertEquals(50, item.getTax(), 0.001);
        assertEquals(15, item.getCommission(), 0.001);
        assertEquals(0, item.getCommissionRate(), 0.001);
    }

}