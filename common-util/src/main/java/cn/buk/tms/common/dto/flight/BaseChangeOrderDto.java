package cn.buk.tms.common.dto.flight;

import cn.buk.tms.common.dto.CustomerDto;
import cn.buk.tms.common.dto.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.*;

/**
 * 机票改签订单DTO
 * @author yfdai
 */
public class BaseChangeOrderDto extends BaseDto {

    private int id;

    private String balanceCode;

    /**
     * 改签的机票的票号
     */
    private String ticketNo;

    /**
     * 该票的改签次数
     * 有些票号改签后不会产生新票号，再次改签时就会用原票号改签
     */
    private Integer changeCount;

    /**
     * 改签单单号
     */
    private String changeOrderNo;

    /**
     * 订单号(该改签单来自哪个订单）
     */
    private String orderNo;

    private Integer orderId;

    private String parentChangeOrderNo;


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

    private Double parValue;

    private Double tax;


    /**
     * 企业收的改签服务费
     */
    private double serviceCharge;

    /**
     * 航司收的改签费
     */
    private double airChangeCharge;

    /**
     * 0 - 提交改签，等待处理
     * 1 - 我来处理
     * 2 - 改签完成，回填新编码和票号（如果有)
     */
    private int orderStatus;

    /**
     * 2 - 已销账
     */
    private Integer payStatus;

    /**
     * 改签类型
     * 0-升舱
     * 1-更改
     * 2-签转
     */
    private int reasonCode;

    /**
     * 操作员：下订单的操作员
     */
    private String operator;

    /**
     * 新票号(如果有）
     */
    private String newTicketNo;

    private String remark;

    private List<BaseChangeOrderFlightDto> flights;

    /**
     * 是否国际票改签
     * 0-否
     * 1-是
     */
    private int intlTicket;

    /**
     * 该账单客户的付款方式
     * 1-现金；
     * 2-信用卡；
     * 4-支票；
     * 8-记账(默认）
     */
    private Integer payType;

    /**
     * 付款备注
     */
    private String payRemark;

    /**
     * 联系人（代订人）从机票订单中来，冗余数据
     */
    private String linkman;
    /**
     * 联系人（代订人）从机票订单中来，冗余数据
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

    public Integer getOrderId() {
        return orderId == null ? 0 : orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public Double getParValue() {
        return parValue == null ? 0: parValue;
    }

    public void setParValue(Double parvalue) {
        this.parValue = parvalue;
    }

    public Double getTax() {
        return tax == null ? 0: tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public String getChangeOrderNo() {
        return changeOrderNo;
    }

    public void setChangeOrderNo(String changeOrderNo) {
        this.changeOrderNo = changeOrderNo;
    }

    public double getAirChangeCharge() {
        return airChangeCharge;
    }

    public void setAirChangeCharge(double airChangeCharge) {
        this.airChangeCharge = airChangeCharge;
    }


    public List<BaseChangeOrderFlightDto> getFlights() {
        if (flights == null) {
            this.flights = new ArrayList<>();
        }
        return flights;
    }

    public void setFlights(List<BaseChangeOrderFlightDto> flights) {
        this.flights = flights;
    }

    public int getIntlTicket() {
        return intlTicket;
    }

    public void setIntlTicket(int intlTicket) {
        this.intlTicket = intlTicket;
    }


    public String getNewTicketNo() {
        return newTicketNo;
    }

    public void setNewTicketNo(String newTicketNo) {
        this.newTicketNo = newTicketNo;
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

    public void setPayRemark(String payRemark) {
        this.payRemark = payRemark;
    }

    public String getPayRemark() {
        return payRemark;
    }

    public String getParentChangeOrderNo() {
        return parentChangeOrderNo;
    }

    public void setParentChangeOrderNo(String parentChangeOrderNo) {
        this.parentChangeOrderNo = parentChangeOrderNo;
    }

    public Integer getPayStatus() {
        return payStatus == null ? 0 : payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public int getChangeCount() {
        return changeCount == null ? 0 : changeCount;
    }

    public void setChangeCount(Integer changeCount) {
        this.changeCount = changeCount;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(int reasonCode) {
        this.reasonCode = reasonCode;
    }
}
