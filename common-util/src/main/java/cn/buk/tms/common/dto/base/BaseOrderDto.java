package cn.buk.tms.common.dto.base;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 基础的订单信息DTO
 * @author yfdai
 */
public class BaseOrderDto extends BaseDto {

  /**
   * 订单ID
   */
  private int id;

  /**
   * 订单号
   */
  private String orderNo;

  /**
   * 订单类型
   */
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Integer orderType;

  /**
   * 订单状态（不同类型的订单，所包含的状态会不一样）
   */
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Integer orderStatus;

  /**
   * 成本中心
   */
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String costCenter;

  /**
   * 项目名称
   */
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String projectName;

  /**
   * 付款方式
   */
  private int payType;

  /**
   * 付款状态(只有付款类型非记账方式时才有用）
   * 0 or null 未支付
   * 1 已收款 已支付(通过账单设置的已支付，客服操作）
   * 2 已销账 已支付（已销账，同步财务账单的状态）
   */
  private int payStatus;

  /**
   * 订单总额（？）
   */
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Double total;

  /**
   * 客户名称（用于前台显示）
   */
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String customerName;

  /**
   * 客户代码（用于前台显示）
   */
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String customerCode;

  /**
   * 如果该订单需要审批，则系统在此设置指定审批人
   */
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String specifiedApprover;

  /**
   * 审批状态：
   * null - 未处理
   * 1 - 同意
   * 0 - 不同意
   */
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Integer approvalStatus;

  /**
   * 审批如果拒绝，拒绝的原因描述
   */
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String approvalDenyReason;

  /**
   * 是否违反差旅政策
   * null - 未处理
   * 0 - 未违反
   * 1 - 违反
   */
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Integer violationStatus;

  /**
   * 违反差旅政策的原因描述
   */
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String violationReason;



  public String getOrderNo() {
    return orderNo;
  }

  public void setOrderNo(String orderNo) {
    this.orderNo = orderNo;
  }

  public int getOrderType() {
    return orderType == null ? 0 : orderType;
  }

  public void setOrderType(int orderType) {
    this.orderType = orderType;
  }

  public int getOrderStatus() {
    return orderStatus == null ? 0 : orderStatus;
  }

  public void setOrderStatus(int orderStatus) {
    this.orderStatus = orderStatus;
  }

  public Double getTotal() {
    return total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }

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

  public int getPayType() {
    return payType;
  }

  public void setPayType(int payType) {
    this.payType = payType;
  }

  public int getPayStatus() {
    return payStatus;
  }

  public void setPayStatus(int payStatus) {
    this.payStatus = payStatus;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public String getCustomerCode() {
    return customerCode;
  }

  public void setCustomerCode(String customerCode) {
    this.customerCode = customerCode;
  }

  public String getSpecifiedApprover() {
    return specifiedApprover;
  }

  public void setSpecifiedApprover(String specifiedApprover) {
    this.specifiedApprover = specifiedApprover;
  }

  public Integer getApprovalStatus() {
    return approvalStatus;
  }

  public void setApprovalStatus(Integer approvalStatus) {
    this.approvalStatus = approvalStatus;
  }

  public String getApprovalDenyReason() {
    return approvalDenyReason;
  }

  public void setApprovalDenyReason(String approvalDenyReason) {
    this.approvalDenyReason = approvalDenyReason;
  }

  public Integer getViolationStatus() {
    return violationStatus;
  }

  public void setViolationStatus(Integer violationStatus) {
    this.violationStatus = violationStatus;
  }

  public String getViolationReason() {
    return violationReason;
  }

  public void setViolationReason(String violationReason) {
    this.violationReason = violationReason;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
