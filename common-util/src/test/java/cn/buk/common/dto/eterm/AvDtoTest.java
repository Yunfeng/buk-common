package cn.buk.common.dto.eterm;

import cn.buk.common.flight.dto.FlightInfoDto;
import cn.buk.common.util.DateUtil;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("ALL")
public class AvDtoTest {

  @Test
  public void setAvResult_1() {
    final String avResult3 = " 27DEC(THU) SHACAN DIRECT ONLY  \n" +
            "1- *MU9327  DS# UQ FA PA JA C6 D2 QS IS WC YA  SHACAN 1730   2005   333 0^D  E  >   FM9327      BA MQ EQ HQ KQ LQ NQ RQ SQ VQ TQ GQ ZQ              T2 T1  2:35\n" +
            "2   FM9327  DS# UQ FA PA JA C6 D2 QS IS WC YA  SHACAN 1730   2005   333 0^D  E  >               BA MQ EQ HQ KQ LQ NQ RQ SQ VQ TQ GQ ZQ              T2 T1  2:35\n" +
            "3  *MU9313  DS# UC F2 P2 J1 CS DS QS IS WA YA  SHACAN 1830   2105   73L 0^D  E  >   FM9313      BA MA EA HS K1 LQ NQ RQ SQ VQ TQ GQ ZQ              T2 T1  2:35\n" +
            "4   FM9313  DS# UC F2 P2 J1 CS DS QS IS WA YA  SHACAN 1830   2105   73L 0^D  E  >               BA MA EA HS K1 LQ NQ RQ SQ VQ TQ GQ ZQ              T2 T1  2:35\n" +
            "5   CZ3572  DS# J8 C6 D4 IQ OC W4 SQ YA PA BQ  SHACAN 1850   2115   789 0^D  E  >               MQ HQ KQ UQ AQ LQ QQ EQ VQ ZQ TQ NQ RQ G5 XC        T2 T2  2:25\n" +
            "6  *MU4939  DS# YA MQ EQ KQ LQ RQ              SHACAN 1850   2115   789 0^D  E  >   CZ3572                                                          T2 T2  2:25\n" +
            "7  *MF1446  DS# YA BQ MQ LQ KQ NQ QQ VQ SQ     SHACAN 1850   2115   789 0^D  E  >   CZ3572                                                          T2 T2  2:25\n" +
            "8+ *CZ9366  DS# YA BA HS AS LS                 SHACAN 1915   2200   73L 0^D  E  >   MU5315                                                          T2 T1  2:45\n" +
            " **  ALL SCHEDULED MU OR FM FLIGHTS OPERATED BY MU OR FM ARE \"EASTERN EXPRESS\" \n" +
            " **  ALL SCHEDULED MU OR FM FLIGHTS OPERATED BY MU OR FM\n";

    AvDto avResponse = new AvDto();
    avResponse.setAvResult(avResult3);

    assertEquals("SHA", avResponse.getDcity());
    assertEquals("CAN", avResponse.getAcity());
    assertEquals("2019-12-27", DateUtil.formatDate(avResponse.getDdate(), "yyyy-MM-dd"));

    assertEquals(8, avResponse.getFlightCount());

    //1- *MU9327  DS# UQ FA PA JA C6 D2 QS IS WC YA  SHACAN 1730   2005   333 0^D  E
    //>   FM9327      BA MQ EQ HQ KQ LQ NQ RQ SQ VQ TQ GQ ZQ              T2 T1  2:35
    FlightInfoDto segInfo =  avResponse.getFlights().get(0);
    assertNotNull(segInfo);
    assertEquals("SHA", segInfo.getDport());
    assertEquals("CAN", segInfo.getAport());
    assertEquals("MU9327", segInfo.getFlightNo());
    assertEquals("FM9327", segInfo.getOpFlightNo());
    assertEquals(1, segInfo.getCodeShare());
    assertEquals("1730", segInfo.getDtime());
    assertEquals("2005", segInfo.getAtime());
    assertEquals("333", segInfo.getAircraft());
    assertEquals(0, segInfo.getStopover());
    assertEquals("T2", segInfo.getDterm());
    assertEquals("T1", segInfo.getAterm());
    assertEquals("2:35", segInfo.getFlyTime());
    assertEquals(155, segInfo.getDuration());
    assertEquals("UQ FA PA JA C6 D2 QS IS WC YA BA MQ EQ HQ KQ LQ NQ RQ SQ VQ TQ GQ ZQ", segInfo.getSubClassDesc());
    assertEquals(10, segInfo.getSubClassSeatCount("F"));
    assertEquals(2, segInfo.getSubClassSeatCount("D"));
    assertEquals(-1, segInfo.getSubClassSeatCount("U"));

    //5   CZ3572  DS# J8 C6 D4 IQ OC W4 SQ YA PA BQ  SHACAN 1850   2115   789 0^D  E
    //>               MQ HQ KQ UQ AQ LQ QQ EQ VQ ZQ TQ NQ RQ G5 XC        T2 T2  2:25\n" +
    segInfo =  avResponse.getFlights().get(4);
    assertNotNull(segInfo);
    assertEquals("SHA", segInfo.getDport());
    assertEquals("CAN", segInfo.getAport());
    assertEquals("CZ3572", segInfo.getFlightNo());
    assertEquals("", segInfo.getOpFlightNo());
    assertEquals(0, segInfo.getCodeShare());
    assertEquals("1850", segInfo.getDtime());
    assertEquals("2115", segInfo.getAtime());
    assertEquals("789", segInfo.getAircraft());
    assertEquals(0, segInfo.getStopover());
    assertEquals("T2", segInfo.getDterm());
    assertEquals("T2", segInfo.getAterm());
    assertEquals("2:25", segInfo.getFlyTime());
//    assertEquals("J8 C6 D4 IQ OC W4 SQ YA PA BQ MQ HQ KQ UQ AQ LQ QQ EQ VQ ZQ TQ NQ RQ G5 XC", segInfo.getSubclasses());
  }

  @Test
  public void setAvResult_2() {
    final String avResult3 = " 31JAN19(THU) SHAHRB DIRECT ONLY  \n" +
            "1-  CZ6210  DS# J8 CQ DQ IQ OC WA SQ YA P2 BQ  PVGHRB 0635   0950   320 0^C  E  >               MQ HQ K2 UQ AQ LQ QQ EQ VQ ZQ TQ NQ RQ GS XC        T2 --  3:15\n" +
            "2  *NZ3851  DS! C0 D0 Z0 J0 Y0 B0 M0 H0 Q0 V0  PVGHRB 0700   1010   332 0 B  E  >   CA1971      W0 T0 L0 S0 G0 K0                                   T2 T2  3:10\n" +
            "3   CA1971  DS# FA AQ JC CQ DQ ZQ RQ YL BQ MQ  PVGHRB 0700   1010   332 0^B  E  >               UQ HQ QQ VQ WQ SQ TQ LQ NQ KQ                       T2 T2  3:10\n" +
            "               ** M1Q S1Q \n" +
            "4  *ZH1971  DS# FA YL BQ MQ UQ QQ VQ WQ TQ LQ  PVGHRB 0700   1010   332 0^B  E  >   CA1971      KQ                                                  T2 T2  3:10\n" +
            "5   HO1350  DS# CS JS D8 RS IS YA BS MS US HS  PVGHRB 0705   1010   320 0^B  E  >               QS VS WS SS TS ZS ES KS GS LS NS XS                 T2 --  3:05\n" +
            "6  *MU9171  DS# UQ FA PQ JC CQ DQ QQ IQ WL     PVGHRB 0730   1050   33L 0^B  E  >   FM9171                                                          T1 --  3:20\n" +
            "7+  FM9171  DS# UQ FA PQ JC CQ DQ QQ IQ WL     PVGHRB 0730   1050   33L 0^B  E  >                                                                   T1 --  3:20\n" +
            " **  ALL SCHEDULED MU OR FM FLIGHTS OPERATED BY MU OR FM ARE \"EASTERN EXPRESS\" \n" +
            " **  ALL SCHEDULED MU OR FM FLIGHTS OPERATED BY MU OR FM \n" +
            " **  HKG-HX-PEK-*HX-HRB AND V.V., NO STOPOVER AT PEK IS PERMITTED\n";

    AvDto avResponse = new AvDto();
    avResponse.setAvResult(avResult3);

    assertEquals("SHA", avResponse.getDcity());
    assertEquals("HRB", avResponse.getAcity());
    assertEquals("2019-01-31", DateUtil.formatDate(avResponse.getDdate(), "yyyy-MM-dd"));

    assertEquals(7, avResponse.getFlightCount());

    FlightInfoDto segInfo =  avResponse.getFlights().get(0);
    assertNotNull(segInfo);
    assertEquals("PVG", segInfo.getDport());
    assertEquals("HRB", segInfo.getAport());
    assertEquals("CZ6210", segInfo.getFlightNo());
    assertEquals("", segInfo.getOpFlightNo());
    assertEquals(0, segInfo.getCodeShare());
    assertEquals("0635", segInfo.getDtime());
    assertEquals("0950", segInfo.getAtime());
    assertEquals("320", segInfo.getAircraft());
    assertEquals(0, segInfo.getStopover());
    assertEquals("T2", segInfo.getDterm());
    assertEquals("", segInfo.getAterm());
    assertEquals("3:15", segInfo.getFlyTime());
//    assertEquals("J8 CQ DQ IQ OC WA SQ YA P2 BQ MQ HQ K2 UQ AQ LQ QQ EQ VQ ZQ TQ NQ RQ GS XC", segInfo.getSubclasses());

    segInfo =  avResponse.getFlights().get(5);
    assertNotNull(segInfo);
    //"6  *MU9171  DS# UQ FA PQ JC CQ DQ QQ IQ WL     PVGHRB 0730   1050   33L 0^B  E  >   FM9171                                                          T1 --  3:20\n" +
    assertEquals("PVG", segInfo.getDport());
    assertEquals("HRB", segInfo.getAport());
    assertEquals("MU9171", segInfo.getFlightNo());
    assertEquals("FM9171", segInfo.getOpFlightNo());
    assertEquals(1, segInfo.getCodeShare());
    assertEquals("0730", segInfo.getDtime());
    assertEquals("1050", segInfo.getAtime());
    assertEquals("33L", segInfo.getAircraft());
    assertEquals(0, segInfo.getStopover());
    assertEquals("T1", segInfo.getDterm());
    assertEquals("", segInfo.getAterm());
    assertEquals("3:20", segInfo.getFlyTime());
//    assertEquals("UQ FA PQ JC CQ DQ QQ IQ WL", segInfo.getSubclasses());
  }

  @Test
  void setAvResult_3() {
    final String avResult3 = " 09APR(FRI) LXANGQ DIRECT ONLY                                                 \n" +
            "1-  TV6008  DS# CL DL AL IL YL BQ MQ HQ KQ LQ  LXANGQ 0750   0945   319 0 B  E \n" +
            ">               JQ QQ GQ VQ RQ EQ TQ OQ ZL PL SQ XL                        1:55\n" +
            "2+  TV9807  DS# CL DL AQ IQ YL BQ MQ HQ KQ LQ  LXANGQ 0900   1100   319 0 B  E \n" +
            ">               JQ QQ GQ VQ RQ EQ TQ OQ ZL PL SQ XL                        2:00\n";

    AvDto avResponse = new AvDto();
    avResponse.setAvResult(avResult3);

    assertEquals("LXA", avResponse.getDcity());
    assertEquals("NGQ", avResponse.getAcity());
    assertEquals("2021-04-09", DateUtil.formatDate(avResponse.getDdate(), "yyyy-MM-dd"));

    assertEquals(2, avResponse.getFlightCount());

    FlightInfoDto segInfo =  avResponse.getFlights().get(0);
    assertNotNull(segInfo);
//    "1-  TV6008  DS# CL DL AL IL YL BQ MQ HQ KQ LQ  LXANGQ 0750   0945   319 0 B  E \n" +
//            ">               JQ QQ GQ VQ RQ EQ TQ OQ ZL PL SQ XL                        1:55\n" +

    assertEquals("LXA", segInfo.getDport());
    assertEquals("NGQ", segInfo.getAport());
    assertEquals("TV6008", segInfo.getFlightNo());
    assertEquals("", segInfo.getOpFlightNo());
    assertEquals(0, segInfo.getCodeShare());
    assertEquals("0750", segInfo.getDtime());
    assertEquals("0945", segInfo.getAtime());
    assertEquals("319", segInfo.getAircraft());
    assertEquals(0, segInfo.getStopover());
    assertEquals("", segInfo.getDterm());
    assertEquals("", segInfo.getAterm());
    assertEquals("1:55", segInfo.getFlyTime());
    assertEquals("CL DL AL IL YL BQ MQ HQ KQ LQ JQ QQ GQ VQ RQ EQ TQ OQ ZL PL SQ XL", segInfo.getSubClassDesc());

    segInfo =  avResponse.getFlights().get(1);
    assertNotNull(segInfo);
//    "2+  TV9807  DS# CL DL AQ IQ YL BQ MQ HQ KQ LQ  LXANGQ 0900   1100   319 0 B  E \n" +
//            ">               JQ QQ GQ VQ RQ EQ TQ OQ ZL PL SQ XL                        2:00\n";                                                         T1 --  3:20\n" +
    assertEquals("LXA", segInfo.getDport());
    assertEquals("NGQ", segInfo.getAport());
    assertEquals("TV9807", segInfo.getFlightNo());
    assertEquals("", segInfo.getOpFlightNo());
    assertEquals(0, segInfo.getCodeShare());
    assertEquals("0900", segInfo.getDtime());
    assertEquals("1100", segInfo.getAtime());
    assertEquals("319", segInfo.getAircraft());
    assertEquals(0, segInfo.getStopover());
    assertEquals("", segInfo.getDterm());
    assertEquals("", segInfo.getAterm());
    assertEquals("2:00", segInfo.getFlyTime());
    assertEquals("CL DL AQ IQ YL BQ MQ HQ KQ LQ JQ QQ GQ VQ RQ EQ TQ OQ ZL PL SQ XL", segInfo.getSubClassDesc());
  }


  @Test
  public void testFormatDate() {
    final Date yesterday = DateUtil.getYesterday();
    System.out.println(DateUtil.formatDate(yesterday, "ddMMMyy", Locale.ENGLISH));
  }


}