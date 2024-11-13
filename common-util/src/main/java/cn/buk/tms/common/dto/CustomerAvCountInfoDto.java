package cn.buk.tms.common.dto;


import cn.buk.tms.common.dto.base.BaseDto;

/**
 * 从tms系统中抽取出来，供微服务gds使用
 * 客户的航班查询次数信息
 * @author yfdai
 */
public class CustomerAvCountInfoDto extends BaseDto {

  /**
   * 最大允许次数
   */
  private int totalMax;

  /**
   * 已使用的总数
   */
  private int totalUsed;

  /**
   * 每月最大允许数
   */
  private int monthlyMax;

  /**
   * 当前月份已使用的数量
   */
  private int monthlyUsed;

  public int getTotalMax() {
    return totalMax;
  }

  public void setTotalMax(int totalMax) {
    this.totalMax = totalMax;
  }

  public int getTotalUsed() {
    return totalUsed;
  }

  public void setTotalUsed(int totalUsed) {
    this.totalUsed = totalUsed;
  }

  public int getMonthlyMax() {
    return monthlyMax;
  }

  public void setMonthlyMax(int monthlyMax) {
    this.monthlyMax = monthlyMax;
  }

  public int getMonthlyUsed() {
    return monthlyUsed;
  }

  public void setMonthlyUsed(int monthlyUsed) {
    this.monthlyUsed = monthlyUsed;
  }
}
