package cn.buk.common.flight.intl.dto;

/**
 * 金额信息
 * @author yfdai
 */
public class AmountInfo {

  /**
   * 金额
   */
  private int amount;

  /**
   * 币种
   */
  private String currency;

  private int originAddonAmount;

  private int destinationAddonAmount;

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public int getOriginAddonAmount() {
    return originAddonAmount;
  }

  public void setOriginAddonAmount(int originAddonAmount) {
    this.originAddonAmount = originAddonAmount;
  }

  public int getDestinationAddonAmount() {
    return destinationAddonAmount;
  }

  public void setDestinationAddonAmount(int destinationAddonAmount) {
    this.destinationAddonAmount = destinationAddonAmount;
  }
}
