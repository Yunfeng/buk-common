package cn.buk.common.dto.eterm;

import java.util.ArrayList;
import java.util.List;

/**
 * FF接口反馈dto
 * @author yfdai
 */
public class FfDto {

  /**
   * 错误代码
   * 0-无错误
   */
  private int errcode;

  /**
   * 错误信息
   */
  private String errmsg;

  /**
   * FF查出来的航班信息
   */
  private List<FfFlightInfo> flights;



  public List<FfFlightInfo> getFlights() {
    if (flights == null) {
      flights = new ArrayList<>();
    }
    return flights;
  }

  public void setFlights(List<FfFlightInfo> flights) {
    this.flights = flights;
  }

  public int getErrcode() {
    return errcode;
  }

  public void setErrcode(int errcode) {
    this.errcode = errcode;
  }

  public String getErrmsg() {
    return errmsg;
  }

  public void setErrmsg(String errmsg) {
    this.errmsg = errmsg;
  }
}
