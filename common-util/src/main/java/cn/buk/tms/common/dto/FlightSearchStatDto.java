package cn.buk.tms.common.dto;

/**
 * 航班查询次数统计
 * @author yfdai
 */
public class FlightSearchStatDto {

  /**
   * 所有者id
   */
  private int ownerId;

  /**
   * 企业客户对应的企业id
   */
  private int enterpriseId;

  /**
   * 统计的年份
   */
  private int year;

  /**
   * 统计的月份
   */
  private int month;

  /**
   * 已使用的次数
   */
  private int usedCount;

  public int getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(int ownerId) {
    this.ownerId = ownerId;
  }

  public int getEnterpriseId() {
    return enterpriseId;
  }

  public void setEnterpriseId(int enterpriseId) {
    this.enterpriseId = enterpriseId;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public int getMonth() {
    return month;
  }

  public void setMonth(int month) {
    this.month = month;
  }

  public int getUsedCount() {
    return usedCount;
  }

  public void setUsedCount(int usedCount) {
    this.usedCount = usedCount;
  }
}
