package cn.buk.common.flight.intl.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 行程信息，航段信息
 * @author yfdai
 */
public class IntlSegmentInfo {

  /**
   * 始发
   */
  private String origin;
  /**
   * 始发地代码（在返给前台页面数据时，origin保存名称，此处保存代码）
   */
  private String originCode;

  /**
   * 目的
   */
  private String destination;
  /**
   * 目的地代码（在返给前台页面数据时，origin保存名称，此处保存代码）
   */
  private String destinationCode;

  /**
   * 航班信息
   */
  private List<IntlFlightInfoDto> flights;


  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }

  public List<IntlFlightInfoDto> getFlights() {
    if (flights == null) {
      flights = new ArrayList<>();
    }
    return flights;
  }

  public void setFlights(List<IntlFlightInfoDto> flights) {
    this.flights = flights;
  }

  public String getDestinationCode() {
    return destinationCode;
  }

  public void setDestinationCode(String destinationCode) {
    this.destinationCode = destinationCode;
  }

  public String getOriginCode() {
    return originCode;
  }

  public void setOriginCode(String originCode) {
    this.originCode = originCode;
  }
}
