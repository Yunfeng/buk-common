package cn.buk.common.flight.dto;

public class TgqItem {
  /**
   * 标题：一般用于显示适用的退改的时间点
   */
  private String title;
  /**
   * 退改时的收费金额
   */
  private int amount;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }
}
