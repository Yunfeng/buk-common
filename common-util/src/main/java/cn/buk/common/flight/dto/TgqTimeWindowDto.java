package cn.buk.common.flight.dto;


/**
 * 航班退改使用条件中的时间点信息
 * flight condition time window
 */
public class TgqTimeWindowDto {

  /**
   * 航司代码
   */
  private String carrier;

  /**
   * 时间窗口顺序：目前国内基本为4个时间窗口
   */
  private int windowOrder;

  /**
   * 以航班起飞时间为0，正数表示起飞前，负数表示起飞后
   * 区间上限（小时）
   * 0 表示不限
   */
  private int maxHours;

  /**
   * 是否包含区间上限
   * 0-不包含
   * 1-包含
   */
  private int maxIncluded;

  /**
   * 区间下限（小时）
   * 0 表示不限
   */
  private int minHours;

  /**
   * 是否包含区间下限
   */
  private int minIncluded;


  public String getCarrier() {
    return carrier;
  }

  public void setCarrier(String carrier) {
    this.carrier = carrier;
  }

  public int getWindowOrder() {
    return windowOrder;
  }

  public void setWindowOrder(int windowOrder) {
    this.windowOrder = windowOrder;
  }

  public int getMaxHours() {
    return maxHours;
  }

  public void setMaxHours(int maxHours) {
    this.maxHours = maxHours;
  }

  public int getMaxIncluded() {
    return maxIncluded;
  }

  public void setMaxIncluded(int maxIncluded) {
    this.maxIncluded = maxIncluded;
  }

  public int getMinHours() {
    return minHours;
  }

  public void setMinHours(int minHours) {
    this.minHours = minHours;
  }

  public int getMinIncluded() {
    return minIncluded;
  }

  public void setMinIncluded(int minIncluded) {
    this.minIncluded = minIncluded;
  }
}
