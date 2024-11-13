package cn.buk.common.flight.intl.dto;

import cn.buk.common.flight.dto.FlightInfoDto;

/**
 * 配合SearchOne接口的航班信息
 * 包括解决方案所对应的RBD信息等
 * @author yfdai
 */
public class IntlFlightInfoDto extends FlightInfoDto {

  /**
   * 记录对应的航班信息对应于原始航班信息中的ID
   */
//  @JsonIgnore
  private int flightId;

  /**
   *  订位舱等 RBD (Reservation Booking Designator )
   */
  private String rbd;

  private String cabin;

  /**
   * 座位数
   */
  private int seatCount;

  /**
   * 是否可以变更
   */
  private boolean changeable;
  /**
   * 是否可以i退票
   */
  private boolean refundable;
  /**
   * 是否可以升舱
   */
  private boolean upgradable;

  /**
   * 去程运价/回程运
   * 价标识
   * 0：outbound,1:inbound
   */
  private int io;

  public String getRbd() {
    return rbd;
  }

  public void setRbd(String rbd) {
    this.rbd = rbd;
  }

  public void setFlightId(int flightId) {
    this.flightId = flightId;
  }

  public int getFlightId() {
    return flightId;
  }

  public boolean isUpgradable() {
    return upgradable;
  }

  public void setUpgradable(boolean upgradable) {
    this.upgradable = upgradable;
  }

  public boolean isRefundable() {
    return refundable;
  }

  public void setRefundable(boolean refundable) {
    this.refundable = refundable;
  }

  public boolean isChangeable() {
    return changeable;
  }

  public void setChangeable(boolean changeable) {
    this.changeable = changeable;
  }

  public void setIo(int io) {

    this.io = io;
  }

  public int getIo() {
    return io;
  }

  public void setCabin(String cabin) {
    this.cabin = cabin;
  }

  public String getCabin() {
    return cabin;
  }

  public void setSeatCount(int seatCount) {
    this.seatCount = seatCount;
  }

  public int getSeatCount() {
    return seatCount;
  }
}
