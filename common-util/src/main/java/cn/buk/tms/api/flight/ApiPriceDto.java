package cn.buk.tms.api.flight;

/**
 * 航班价格
 * 用于对外提供接口的
 * @author yfdai
 */
public class ApiPriceDto {

  /**
   * 接口参数
   * 0-成人价
   * 1-儿童价
   * 2-婴儿价格
   * 价格类型
   */
  private int priceType;

  /**
   * 接口参数
   * 公布运价
   */
  private double price;

  /**
   * 接口参数
   * 票面价, 航司销售价
   */
  private double parValue;

  /**
   * 接口参数
   * 税：税的总额
   */
  private double tax;

  /**
   * 接口参数
   * 服务费
   */
  private double serviceCharge;

  /**
   * 接口参数
   * 优惠: 对应政策的直减金额, returnPrice
   */
  private double discount;


  public int getPriceType() {
    return priceType;
  }

  public void setPriceType(int priceType) {
    this.priceType = priceType;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public double getParValue() {
    return parValue;
  }

  public void setParValue(double parValue) {
    this.parValue = parValue;
  }

  public double getTax() {
    return tax;
  }

  public void setTax(double tax) {
    this.tax = tax;
  }

  public double getServiceCharge() {
    return serviceCharge;
  }

  public void setServiceCharge(double serviceCharge) {
    this.serviceCharge = serviceCharge;
  }

  public double getDiscount() {
    return discount;
  }

  public void setDiscount(double discount) {
    this.discount = discount;
  }
}
