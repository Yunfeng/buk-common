package cn.buk.common.flight.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * 航班信息（基础信息）
 * @author yfdai
 */
public class FlightBasicInfo {

  /**
   * 出发机场三字代码
   */
  private String dport;

  /**
   * 出发机场名称：城市名+机场名
   */
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String dportName;

  /**
   * 到达机场三字代码
   */
  private String aport;

  /**
   * 到达机场名称
   */
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String aportName;

  /**
   * 航班号
   */
  private String flightNo;

  /**
   * 出发日期
   */
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date ddate;

  /**
   * 出发时间
   */
  private String dtime;

  /**
   * 到达日期
   */
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Date adate;

  /**
   * 到达时间
   */
  private String atime;

  /**
   * 出发航站楼
   */
  private String dterm;

  /**
   * 到达航站楼
   */
  private String aterm;

  /**
   * 舱位性质
   * 10-头等舱
   * 20-商务舱
   * 30-经济舱
   * 40-超级经济舱
   */
  private Integer cabinType;

  /**
   * 客票舱位（子舱位)
   */
  private String subclass;

  /**
   * 该航段的状态：一般用于DETR中的内容，比如：OPEN FOR USE等
   */
  private String ticketStatus;

  public String getDport() {
    return dport;
  }

  public void setDport(String dport) {
    this.dport = dport;
  }

  public String getDportName() {
    return dportName;
  }

  public void setDportName(String dportName) {
    this.dportName = dportName;
  }

  public String getAport() {
    return aport;
  }

  public void setAport(String aport) {
    this.aport = aport;
  }

  public String getAportName() {
    return aportName;
  }

  public void setAportName(String aportName) {
    this.aportName = aportName;
  }

  /**
   * 航班号
   */
  public String getFlightNo() {
    return flightNo;
  }

  public void setFlightNo(String flightNo) {
    this.flightNo = flightNo;
  }

  public Date getDdate() {
    return ddate;
  }

  public void setDdate(Date ddate) {
    this.ddate = ddate;
  }

  public String getDtime() {
    return dtime;
  }

  public void setDtime(String dtime) {
    this.dtime = dtime;
  }

  public Date getAdate() {
    return adate;
  }

  public void setAdate(Date adate) {
    this.adate = adate;
  }

  public String getAtime() {
    return atime;
  }

  public void setAtime(String atime) {
    this.atime = atime;
  }

  public String getDterm() {
    return dterm;
  }

  public void setDterm(String dterm) {
    this.dterm = dterm;
  }

  public String getAterm() {
    return aterm;
  }

  public void setAterm(String aterm) {
    this.aterm = aterm;
  }

  public String getSubclass() {
    return subclass;
  }

  public void setSubclass(String subclass) {
    this.subclass = subclass;
  }

  public String getTicketStatus() {
    return ticketStatus;
  }

  public void setTicketStatus(String ticketStatus) {
    this.ticketStatus = ticketStatus;
  }

  public Integer getCabinType() {
    return cabinType;
  }

  public void setCabinType(Integer cabinType) {
    this.cabinType = cabinType;
  }
}
