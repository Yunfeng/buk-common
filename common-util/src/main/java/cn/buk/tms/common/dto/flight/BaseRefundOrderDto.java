package cn.buk.tms.common.dto.flight;

import cn.buk.common.flight.dto.FlightBasicInfo;
import cn.buk.tms.common.dto.CustomerDto;
import cn.buk.tms.common.dto.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 机票退票订单DTO
 *
 * @author yfdai
 */
public class BaseRefundOrderDto extends BaseDto {

    private int id;

    /**
     * 结算代码
     */
    private String balanceCode;

    /**
     * 要退的机票的票号(10位）
     */
    private String ticketNo;

    /**
     * 退票单号
     */
    private String refundOrderNo;

    /**
     * 机票订单号
     * 订单号(该退票来自哪个订单）
     * 原始单号
     */
    private String orderNo;

    private Integer orderId;


    /**
     * 客户类型
     * 0 - 代表散客
     * 1 - 代表企业客户
     */
    private Integer customerType;

    /**
     * 冗余字段，应该和Order.customer值保持一致
     */
    private CustomerDto customer;

    /**
     * 成本中心
     */
    private String costCenter;

    private String projectName;

    /**
     * 旅客姓名
     */
    private String psgName;

    private String idNo;


    /**
     * 退票理由代码
     * 0-自愿退票
     * 1-非自愿退票
     * 4-废票
     */
    private int reasonCode;

    /**
     * 退票理由
     */
    private String reasonDesc;

    private Double parValue;

    private Double tax;

    /**
     * 原付票款额
     * 收客时客户支付的实际金额
     */
    private double ticketAmount;

    /**
     * 企业收的退票服务费
     */
    private double serviceCharge;

    /**
     * 实际退给客人的金额
     */
    private Double passengerRefundAmount;

    /**
     * 0 - 提交退票，等待处理
     * 1 - 我来处理
     * 2 - 已向航司提交退票
     * 状态2之后可以变为状态8 或 16
     * 8 - 航司已退，待退客人
     * 16 - 已退客人
     * 状态8 和状态16都达到了，就可以到32状态了
     * 32 - 已完成
     */
    private int orderStatus;

    /**
     * 航司退款状态
     * 0、null 未退
     * 1 已退
     */
    private Integer airRefundStatus;

    /**
     * 2 - 已销账
     */
    private Integer payStatus;

    /**
     * 操作员：下订单的操作员
     */
    private String operator;

    private String remark;

    private Set<FlightBasicInfo> flights;

    /**
     * 是否国际票退票
     * 1-是
     */
    private Integer intlTicket;

    /**
     * 提交退票的日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date submitDate;

    /**
     * 航司退票日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date airlineRefundDate;

    /**
     * 实退客户日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date psgRefundDate;

    /**
     * 该账单客户的付款方式
     * 1-现金；
     * 2-信用卡；
     * 4-支票；
     * 8-记账(默认）
     */
    private Integer payType;

    /**
     * 客户付款备注(来自机票订单）
     */
    private String payRemark;

    private String pnrNo;


    /**
     * 联系人（代订人）(从机票订单过来）冗余
     */
    private String linkman;

    /**
     * 联系电话（代订人）(从机票订单过来）冗余
     */
    private String linkPhone;



    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date lastUpdate;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public String getPsgName() {
        return psgName;
    }

    public void setPsgName(String psgName) {
        this.psgName = psgName;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public String getBalanceCode() {
        return balanceCode;
    }

    public void setBalanceCode(String balanceCode) {
        this.balanceCode = balanceCode;
    }

    public double getTicketAmount() {
        return ticketAmount;
    }

    public void setTicketAmount(double ticketAmount) {
        this.ticketAmount = ticketAmount;
    }

    public double getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    public Double getPassengerRefundAmount() {
        return passengerRefundAmount;
    }

    public void setPassengerRefundAmount(Double passengerRefundAmount) {
        this.passengerRefundAmount = passengerRefundAmount;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getReasonDesc() {
        return reasonDesc;
    }

    public void setReasonDesc(String reasonDesc) {
        this.reasonDesc = reasonDesc;
    }

    public int getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(int reasonCode) {
        this.reasonCode = reasonCode;
    }

    public Integer getOrderId() {
        return orderId == null ? 0 : orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Set<FlightBasicInfo> getFlights() {
        if (flights == null) {
            flights = new HashSet<>();
        }
        return flights;
    }

    public void setFlights(Set<FlightBasicInfo> flights) {
        this.flights = flights;
    }


    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getRefundOrderNo() {
        return refundOrderNo;
    }

    public void setRefundOrderNo(String refundOrderNo) {
        this.refundOrderNo = refundOrderNo;
    }

    public Integer getCustomerType() {
        return customerType == null ? 1: customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    public void setIntlTicket(int intlTicket) {
        this.intlTicket = intlTicket;
    }

    public int getIntlTicket() {
        return intlTicket == null ? 0 : intlTicket;
    }

    public Double getTax() {
        return tax == null ? 0: tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public Date getAirlineRefundDate() {
        return airlineRefundDate;
    }

    public void setAirlineRefundDate(Date airlineRefundDate) {
        this.airlineRefundDate = airlineRefundDate;
    }

    public Date getPsgRefundDate() {
        return psgRefundDate;
    }

    public void setPsgRefundDate(Date psgRefundDate) {
        this.psgRefundDate = psgRefundDate;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public Integer getPayType() {
        return payType == null ? 8 : payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPayRemark() {
        return payRemark;
    }

    public void setPayRemark(String payRemark) {
        this.payRemark = payRemark;
    }

    public Integer getPayStatus() {
        return payStatus == null ? 0 : payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public int getAirRefundStatus() {
        return airRefundStatus == null ? 0 : airRefundStatus;
    }

    public void setAirRefundStatus(Integer airRefundStatus) {
        this.airRefundStatus = airRefundStatus;
    }

    public String getPnrNo() {
        return pnrNo;
    }

    public void setPnrNo(String pnrNo) {
        this.pnrNo = pnrNo;
    }

    public Double getParValue() {
        return parValue;
    }

    public void setParValue(Double parValue) {
        this.parValue = parValue;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }
}
