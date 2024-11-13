package cn.buk.tms.common.dto.flight;

import cn.buk.common.util.FlightTicketUtil;
import cn.buk.tms.common.dto.base.BasePassengerDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yfdai
 */
public class BaseFlightOrderPassengerDto extends BasePassengerDto {

  /**
   * id
   */
  private int id;

  /**
   * 乘机人票号
   */
  private String ticketNo;

  /**
   * 票号数量（联程客票）
   */
  private int ticketCount;

  /**
   * 截止票号
   */
  private String ticketNoEnd;

  /**
   * 暂时代码
   * 如果ticketNo保存的是多个票号的内容时，则将多个票号分开，保存到ticketNos中
   */
  private List<String> ticketNos;

  /**
   * 客票所对应的航段状态, 该乘机人对应的所有的票号和航段状态（都一致的状态, 不一致则用-1）
   * -1 - 未知
   * 0 - 未使用
   * 1 - 已使用
   * 2 - 已改签
   * 3 - 已退票
   * 4 - 已作废
   */
  private Integer ticketStatus;

  public String getShowTicketNo() {
    return FlightTicketUtil.processShowTicketNo(this.ticketNo, this.ticketCount, this.ticketNoEnd);
  }


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTicketNo() {
    return ticketNo;
  }

  public void setTicketNo(String ticketNo) {
    this.ticketNo = ticketNo;
  }

  public int getTicketCount() {
    return ticketCount;
  }

  public void setTicketCount(int ticketCount) {
    this.ticketCount = ticketCount;
  }

  public String getTicketNoEnd() {
    return ticketNoEnd;
  }

  public void setTicketNoEnd(String ticketNoEnd) {
    this.ticketNoEnd = ticketNoEnd;
  }

  public List<String> getTicketNos() {
    if (ticketNos == null) {
      ticketNos = new ArrayList<>();
    }
    return ticketNos;
  }

  public void setTicketNos(List<String> ticketNos) {
    this.ticketNos = ticketNos;
  }

  public Integer getTicketStatus() {
    return ticketStatus == null ? -1: ticketStatus;
  }

  public void setTicketStatus(Integer ticketStatus) {
    this.ticketStatus = ticketStatus;
  }


}
