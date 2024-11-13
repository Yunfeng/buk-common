package cn.buk.tms.common.dto;

/**
 * 从tms系统中抽取出来，供微服务gds使用
 * 客户的机票折扣信息
 * @author yfdai
 */
public class CustomerFlightDiscountDto extends KeyCustomerPolicyDto {

  /**
   * 留扣：留扣：后台为月结客户设置可使用的机票政策时可以设置留扣，数值为0-100之间。
   * 月结客户优惠价的计算公式为：私有运价底价 + 公布运价 * 留扣 * 1%。
   */
  private int commissionRateRetained;

  public int getCommissionRateRetained() {
    return commissionRateRetained;
  }

  public void setCommissionRateRetained(int commissionRateRetained) {
    this.commissionRateRetained = commissionRateRetained;
  }

}
