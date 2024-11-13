package cn.buk.tms.common.dto.flight;

import cn.buk.tms.common.dto.base.BaseOrderDto;
import cn.buk.tms.common.dto.base.BasePriceDto;

import java.util.ArrayList;
import java.util.List;

/**
 * 机票订单DTO基础
 * 包含了response, request的通用部分
 * @author yfdai
 */
//public class BaseFlightOrderDto extends BaseFlightPriceDto {
public class BaseFlightOrderDto extends BaseOrderDto {


  //订单生成接口请求参数 开始
  /**
   * 接口参数
   * 价格
   */
  private List<BasePriceDto> prices;

  /**
   * 成本中心
   * 接口参数
   */
  private String costCenter;

  /**
   * 项目名称
   * 接口参数
   */
  private String projectName;

  /**
   * 接口参数: 联系人
   */
  private String linkman;

  /**
   * 联系电话
   */
  private String linkPhone;

  /**
   * 是否国际/港澳台票
   * 0-否
   * 1-是
   */
  private int intlTicket;

  /**
   * 接口参数
   * 付款方式
   * 0 - 现付
   * 8 - 记账
   */
  private int payType;

  /**
   * 是否需要发票或行程单
   *  0-不需要
   *  1-行程单
   *  2-发票
   *  3-信息单
   *  4-行程单 + 捆绑产品（比如30元航意险/人/航段）
   */
  private int itineraryType;

  /**
   * 接口参数
   * 邮寄地址
   */
  private String address;

  /**
   * 接口参数
   * 备注
   */
  private String remark;

  /**
   * 接口参数
   * 政策代码：机票私有政策，大客户政策的代码
   */
  private String policyCode;

  /**
   * 接口参数：
   * 0 - 否，不是开GP机票
   * 1 - 是，该订单开GP机票
   */
  private int gpTicket;

  /**
   * 接口参数
   * 总收客金额(成人+儿童+婴儿）
   */
  private double totalAmount;

  //订单生成接口请求参数 截止



  /**
   * 成人人数
   */
  private int adultCount;

  /**
   * 儿童人数
   */
  private int childCount;

  /**
   * 婴儿人数
   */
  private int infantCount;



  protected void calc() {
    this.totalAmount = 0;
    for (BasePriceDto priceDto : prices) {
      priceDto.calc();
      switch (priceDto.getPriceType()) {
        case 0:
          this.totalAmount += this.adultCount * priceDto.getAmount();
          break;
        case 1:
          this.totalAmount += this.childCount * priceDto.getAmount();
          break;
        case 2:
          this.totalAmount += this.infantCount * priceDto.getAmount();
          break;
        default:
          break;
      }
    }
  }

  /**
   * 冗余字段，根据乘机人类型计算得到
   * 总占座人数（婴儿包括嘛？)
   */
  protected int psgCount;


  public String getCostCenter() {
    return costCenter;
  }

  public void setCostCenter(String costCenter) {
    this.costCenter = costCenter;
  }

  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public String getLinkman() {
    return linkman;
  }

  public void setLinkman(String linkman) {
    this.linkman = linkman;
  }

  public String getLinkPhone() {
    return linkPhone;
  }

  public void setLinkPhone(String linkPhone) {
    this.linkPhone = linkPhone;
  }

  public int getIntlTicket() {
    return intlTicket;
  }

  public void setIntlTicket(int intlTicket) {
    this.intlTicket = intlTicket;
  }

  public int getPayType() {
    return payType;
  }

  public void setPayType(int payType) {
    this.payType = payType;
  }

  public int getItineraryType() {
    return itineraryType;
  }

  public void setItineraryType(int itineraryType) {
    this.itineraryType = itineraryType;
  }

  public String getPolicyCode() {
    return policyCode;
  }

  public void setPolicyCode(String policyCode) {
    this.policyCode = policyCode;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public int getGpTicket() {
    return gpTicket;
  }

  public void setGpTicket(int gpTicket) {
    this.gpTicket = gpTicket;
  }

  public double getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(double totalAmount) {
    this.totalAmount = totalAmount;
  }

  public List<BasePriceDto> getPrices() {
    if (prices == null) {
      prices = new ArrayList<>();
    }
    return prices;
  }

  public void setPrices(List<BasePriceDto> prices) {
    this.prices = prices;
  }

  public int getAdultCount() {
    return adultCount;
  }

  public void setAdultCount(int adultCount) {
    this.adultCount = adultCount;
  }

  public int getChildCount() {
    return childCount;
  }

  public void setChildCount(int childCount) {
    this.childCount = childCount;
  }

  public int getInfantCount() {
    return infantCount;
  }

  public void setInfantCount(int infantCount) {
    this.infantCount = infantCount;
  }
}
