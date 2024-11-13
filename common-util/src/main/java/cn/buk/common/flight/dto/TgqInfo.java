package cn.buk.common.flight.dto;

import java.util.List;

public class TgqInfo {

  /**
   * 乘机人类型
   */
  private int passengerType;

  /**
   * 阶梯退票金额
   */
  private List<TgqItem> tamountList;
  /**
   * 阶梯改签金额
   */
  private List<TgqItem> gamountList;

  public List<TgqItem> getGamountList() {
    return gamountList;
  }

  public void setGamountList(List<TgqItem> gamountList) {
    this.gamountList = gamountList;
  }

  public List<TgqItem> getTamountList() {
    return tamountList;
  }

  public void setTamountList(List<TgqItem> tamountList) {
    this.tamountList = tamountList;
  }

  public int getPassengerType() {
    return passengerType;
  }

  public void setPassengerType(int passengerType) {
    this.passengerType = passengerType;
  }
}
