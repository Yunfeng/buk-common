package cn.buk.tms.common.dto.flight;

import cn.buk.tms.common.dto.CustomerDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 * 差旅系统（前台、后台）机票订单DTO的通用部分
 * @author yfdai
 */
public class BaseFlightOrderResponseDto extends BaseFlightOrderDto {

  /**
   * 创建此账单的用户名
   */
  private String operator;
  /**
   * 客户类型
   * 0 - 代表散客
   * 1 - 代表企业客户
   */
  private int customerType;
  /**
   * 客户信息
   */
  private CustomerDto customer;
  /**
   * 记录编号：前台有部分客户是需要可以看到的
   */
  private String pnrNo;
  /**
   * 记录编号内容
   */
  private String pnrDetail;
  /**
   * 航段数
   */
  private int segCount;

  /**
   * 客户付款备注：用来选择具体哪一种付款方式
   */
  private String payRemark;


  /**
   * 行程类型
   * 1-单程
   * 2－来回
   * 3－多程
   */
  private int routeType;
  /**
   * 配送单号, 以后配送的相关信息都保存到配送单中
   */
  private String deliveryNoteNo;
  /**
   * 对应的总订单id
   */
  @JsonIgnore
  private Integer blanketOrderId;
  /**
   * 如果是总订单，则用subTotal保存机票订单的总价
   */
  private Double subTotal;
  /**
   * 订单创建日期
   */
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date createDate;

  /**
   * 订单金额是否已校验
   * 0 - 未校验
   * 1 - 校验通过
   * 4 - 校验失败, 需要人工干预
   */
  private int priceCheckedStatus;

  /**
   * 订单金额校验时间
   */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date priceCheckedTime;


  public int getCustomerType() {
    return customerType;
  }

  public void setCustomerType(int customerType) {
    this.customerType = customerType;
  }


  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  public CustomerDto getCustomer() {
    return customer;
  }

  public void setCustomer(CustomerDto customer) {
    this.customer = customer;
  }

  public String getPnrNo() {
    return pnrNo;
  }

  public void setPnrNo(String pnrNo) {
    this.pnrNo = pnrNo;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public int getSegCount() {
    return segCount;
  }

  public void setSegCount(int segCount) {
    this.segCount = segCount;
  }

  public String getPayRemark() {
    return payRemark;
  }

  public void setPayRemark(String payRemark) {
    this.payRemark = payRemark;
  }

  public int getRouteType() {
    return routeType;
  }

  public void setRouteType(int routeType) {
    this.routeType = routeType;
  }

  public String getPnrDetail() {
    return pnrDetail;
  }

  public void setPnrDetail(String pnrDetail) {
    this.pnrDetail = pnrDetail;
  }

  public String getDeliveryNoteNo() {
    return deliveryNoteNo;
  }

  public void setDeliveryNoteNo(String deliveryNoteNo) {
    this.deliveryNoteNo = deliveryNoteNo;
  }

  public Integer getBlanketOrderId() {
    return blanketOrderId;
  }

  public void setBlanketOrderId(Integer blanketOrderId) {
    this.blanketOrderId = blanketOrderId;
  }

  public Double getSubTotal() {
    return subTotal;
  }

  public void setSubTotal(Double subTotal) {
    this.subTotal = subTotal;
  }

  public int getPriceCheckedStatus() {
    return priceCheckedStatus;
  }

  public void setPriceCheckedStatus(int priceCheckedStatus) {
    this.priceCheckedStatus = priceCheckedStatus;
  }

  public Date getPriceCheckedTime() {
    return priceCheckedTime;
  }

  public void setPriceCheckedTime(Date priceCheckedTime) {
    this.priceCheckedTime = priceCheckedTime;
  }

}
