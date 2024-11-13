package cn.buk.common.dto.eterm;


/**
 * 航班经停信息
 * @author yfdai
 */
public class FfFlightInfo {

  /**
   * 经停机场代码
   */
  private String airport;

  /**
   * 经停机场名称
   */
  private String airportName;

  /**
   * 到达时间
   */
  private String atime;

  /**
   * 离开时间
   */
  private String dtime;

  public String getAirport() {
    return airport;
  }

  public void setAirport(String airport) {
    this.airport = airport;
  }

  public String getAirportName() {
    return airportName;
  }

  public void setAirportName(String airportName) {
    this.airportName = airportName;
  }

  public String getAtime() {
    return atime;
  }

  public void setAtime(String atime) {
    this.atime = atime;
  }

  public String getDtime() {
    return dtime;
  }

  public void setDtime(String dtime) {
    this.dtime = dtime;
  }
}
