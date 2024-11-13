package cn.buk.common.dto.eterm;


import cn.buk.common.flight.dto.FlightInfoDto;
import cn.buk.common.util.DateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.util.Date;

/**
 * @author yfdai
 */
public class PnrFlightDto {

  private static final Logger logger = LogManager.getLogger(PnrFlightDto.class);

  private int id;

  /**
   * 航段序号
   */
  private int sn;

  private FlightInfoDto flight = new FlightInfoDto();

  private String text;

  public static PnrFlightDto createPnrFlight(final String strLine0) {
    PnrFlightDto pnrFlight = new PnrFlightDto();
    //  1.  MU5151 Y   SA25NOV  SHAPEK DK1   0830 1050          333 B 0  R E T2T2  sd后的格式，没有封口
    //   01234567890123456789012345678901234567890123456789012345678901234567890123456789
    //" 2.  HO1218 V   TH30JUN  XIYSHA HK1   1130 1340          E T3T2 \n" +
    //  3.  MU2976 B   SA04NOV  CKGWUX HK2   1715 1930          E T3T2
    //  2.  KL2110 R1  FR20SEP  PVGCDG HK1   2320 0540+1    SEAME      OP-AF111
    //  2.  W5081 Y   SA22MAR  IKACAN RR3   0520 1600          E T2T1
    //  2.  ZH8606 W   SA15MAY  YBPHFE RR1   1615 1825          E  \n" +
    //   01234567890123456789012345678901234567890123456789012345678901234567890123456789
    //  3.  CX881  S2  TU23AUG16LAXHKG HK2   0055 0645+1        E  B 1
    //      CX881  S2  TU23AUG16LAXHKG HK2   0055 0645+1        E  B 1
    //  2. *ZH1787 Y   TU05JUL  WEHHRB HK1   1035 1225          E      OP-CA1787
    //   01234567890123456789012345678901234567890123456789012345678901234567890123456789
    //             1         2         3         4         5         6         7         1
    //   Y   SA22MAR  IKACAN RR3   0520 1600          E T2T1
    //   R1  FR20SEP  PVGCDG HK1   2320 0540+1    SEAME      OP-AF111
    //   V   TH30JUN  XIYSHA HK1   1130 1340          E T3T2
    //   S2  TU23AUG16LAXHKG HK2   0055 0645+1        E  B 1
    //   W   SA15MAY  YBPHFE RR1   1615 1825          E  \n" +
    //   01234567890123456789012345678901234567890123456789012345678901234567890123456789
    //             1         2         3         4         5         6         7         1

    final int offset0 = strLine0.indexOf('.');
    String strLine = strLine0.substring(offset0 + 1).trim();

    final int offset1 = strLine.indexOf(' ');
    final String tempFlightNo = strLine.substring(0, offset1).trim();
    final String flightNo = "*".equals(tempFlightNo.substring(0, 1)) ? tempFlightNo.substring(1) : tempFlightNo;

    strLine = strLine.substring(offset1).trim();

    String subClass = strLine.substring(0, 2).trim();
    String departureDayOfWeek = strLine.substring(4, 6);
    String departureDate = strLine.substring(6, 13).trim();
    String departurePort = strLine.substring(13, 16);
    String arrivalPort = strLine.substring(16, 19);
    String segStatus0 = "";
    try {
      segStatus0 = strLine.substring(20, 22);
    } catch (Exception ex) {
      logger.error(strLine);
      logger.error(ex.getMessage());
    }

    String departureTime = "";
    String arrivalTime = "";
    if (strLine.length() > 37) {
      departureTime = strLine.substring(26, 30);
      arrivalTime = strLine.substring(31, 35);
    }

    String dterm = "", aterm = "", opFlightNo = null;
    try {
      final int realLength = strLine.trim().length();
      if (realLength > 50) {
        dterm = strLine.substring(47, 49).trim();
        aterm = strLine.substring(49, 51).trim();
      }

      if (realLength >= 60) {
        //判断是否有OP-
        if (strLine.contains("OP-")) {
          int idx = strLine.indexOf("OP-");
          String opFlightNo0 = strLine.substring(idx + 3).trim();
          if (opFlightNo0.length() >= 4 && opFlightNo0.length() <= 7) {
            opFlightNo = opFlightNo0;
          }
        }
      }

      dterm = "--".equalsIgnoreCase(dterm) ? "" : dterm;
      aterm = "--".equalsIgnoreCase(aterm) ? "" : aterm;
    } catch (Exception ex) {
      logger.error(ex.getMessage());
      logger.error(strLine);
    }

    pnrFlight.setText(strLine);
    pnrFlight.getFlight().setFlightNo(flightNo);
    pnrFlight.getFlight().setOpFlightNo(opFlightNo);
    pnrFlight.getFlight().setDport(departurePort);
    pnrFlight.getFlight().setAport(arrivalPort);
    pnrFlight.getFlight().setDtime(departureTime);
    pnrFlight.getFlight().setAtime(arrivalTime);
    pnrFlight.getFlight().setSegmentStatus(segStatus0);
    pnrFlight.getFlight().setSubclass(subClass);
    pnrFlight.getFlight().setDterm(dterm);
    pnrFlight.getFlight().setAterm(aterm);

    departureDate = DateUtil.convertEtermDate(departureDate, departureDayOfWeek, null);
    try {
      pnrFlight.getFlight().setDdate(DateUtil.convertToDate(departureDate));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    String arrivalDate = departureDate;

    if (strLine.length() > 36 && strLine.substring(35, 37).compareToIgnoreCase("+1") == 0) {
      try {
        Date date = DateUtil.convertToDate(departureDate);
        date = DateUtil.addDays(date, 1);
        arrivalDate = DateUtil.formatDate(date, "yyyy-MM-dd");
      } catch (ParseException e) {
        logger.error(e.getMessage());
      }
    }
    try {
      pnrFlight.getFlight().setAdate(DateUtil.convertToDate(arrivalDate));
    } catch (ParseException e) {
      e.printStackTrace();
    }

    return pnrFlight;
  }

  @Override
  public String toString() {
    return flight.getDport() + flight.getAport() + flight.getDdate() + flight.getFlightNo() + flight.getSegmentStatus();
  }

  /**
   * @return the sn
   */
  public int getSn() {
    return sn;
  }

  /**
   * @param sn the sn to set
   */
  public void setSn(int sn) {
    this.sn = sn;
  }

  /**
   * @return the flight
   */
  public FlightInfoDto getFlight() {
    return flight;
  }

  /**
   * @param flight the flight to set
   */
  public void setFlight(FlightInfoDto flight) {
    this.flight = flight;
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

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
