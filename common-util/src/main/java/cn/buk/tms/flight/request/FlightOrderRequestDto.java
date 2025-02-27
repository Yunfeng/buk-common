package cn.buk.tms.flight.request;

import cn.buk.common.Constant;
import cn.buk.common.dto.eterm.PnrFlightDto;
import cn.buk.common.dto.eterm.PnrPassengerDto;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yfdai
 */
public class FlightOrderRequestDto extends BaseFlightPriceDto2 {

    private int id;

    private int enterpriseId;

    private String orderNo;

    private int customerId;

    private String costCenter;

    private String projectName;

    private int supplierId;

    private int paymentMethodId;

    private int payType;

    /**
     * 客户付款备注：用来选择具体哪一种付款方式
     */
    private String payRemark;

    private int payStatus;

    private String etdzMemo;

    @Deprecated
    private String payMemo;



    /**
     * 是否需要发票或行程单
     * 0-不需要
     * 1-行程单
     * 2-发票
     */
    private int itineraryType;

    private String itineraryRecipient;

    /**
     * 收件人联系电话
     */
    private String itineraryRecipientMobile;

    /**
     * 邮寄地址
     */
    private String address;

    /**
     * 是否邮寄
     */
    private int itineraryMailingStatus;

    /**
     * 邮寄备注
     */
    private String itineraryMailingMemo;

    /**
     * 派送日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date deliveryDate;


    private String pnrNo;

    /**
     * 大客户代码，PAT:A时需要用概知
     */
    private String keyCustomerNo;

    private String pnrDetail;

    /**
     * 出票时限的日期部分
     */
    private Date tktlDate;

    /**
     * 出票时限的时间部分
     */
    private String tktlTime;

    private String linkman;

    private String linkPhone;

    /**
     * 是否国际票 1-是
     */
    private int intlTicket;

    /**
     * 监控类型：用于追位订单
     */
    private int monitoring;

    /**
     * 追位时间：0-工作时间，1-日夜兼程
     */
    private int dayAndNight;

    /**
     * 新编码需要授权的office: 用于追位订单
     */
    private String authOfficeNo;


    @NotNull
    private String op1;

    private String remark;

    @NotNull
    @Size(min = 1, max = 100)
    private List<@NotNull PnrFlightDto> flights;

    @NotNull
    @Size(min = 1, max = 100)
    private List<@NotNull PnrPassengerDto> passengers;

    private int orderStatus;

    /**
     * 预订渠道
     * 0：PNR导入及手工录入
     * 1: 白屏预订
     */
    private int bookingChannel;

    public void calcCost() {
        if (psgCount == 0 && this.passengers != null) {
            for (PnrPassengerDto psg : this.passengers) {
                if (psg.isSelected()) {
                    psgCount++;
                }
            }
        }

        if (this.getAdultCount() == 0 && this.getChildCount() == 0 && this.passengers != null) {
            int adultCount = 0;
            int childCount = 0;
            int infantCount = 0;
            for (PnrPassengerDto psg : this.passengers) {
                if (!psg.isSelected()) {
                    continue;
                }
                if (psg.getPsgType() == Constant.PSG_TYPE_ADU) {
                    adultCount++;
                } else if (psg.getPsgType() == Constant.PSG_TYPE_CHD) {
                    childCount++;
                } else if (psg.getPsgType() == Constant.PSG_TYPE_INF) {
                    infantCount++;
                }
            }

            setAdultCount(adultCount);
            setChildCount(childCount);
            setInfantCount(infantCount);
        }

        super.calc();
    }


    /**
     * 总占座人数（婴儿包括嘛？)
     */
    private int psgCount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;


    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getEtdzMemo() {
        return etdzMemo == null ? "" : etdzMemo;
    }

    public void setEtdzMemo(String etdzMemo) {
        this.etdzMemo = etdzMemo;
    }


    public String getPayMemo() {
        return payMemo;
    }

    public void setPayMemo(String payMemo) {
        this.payMemo = payMemo;
    }

    public int getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public List<PnrFlightDto> getFlights() {
        if (flights == null) {
            flights = new ArrayList<>();
        }
        return flights;
    }

    public void setFlights(List<PnrFlightDto> flights) {
        this.flights = flights;
    }

    public List<PnrPassengerDto> getPassengers() {
        if (passengers == null) {
            passengers = new ArrayList<>();
        }
        return passengers;
    }

    public void setPassengers(List<PnrPassengerDto> passengers) {
        this.passengers = passengers;
    }

    public String getPnrNo() {
        return pnrNo;
    }

    public void setPnrNo(String pnrNo) {
        this.pnrNo = pnrNo;
    }

    public String getPnrDetail() {
        return pnrDetail;
    }

    public void setPnrDetail(String pnrDetail) {
        this.pnrDetail = pnrDetail;
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

    public int getItineraryType() {
        return itineraryType;
    }

    public void setItineraryType(int itineraryType) {
        this.itineraryType = itineraryType;
    }

    public String getItineraryMailingAddress() {
        return address == null ? "" : address.trim();
    }

    public void setItineraryMailingAddress(String itineraryMailingAddress) {
        this.address = itineraryMailingAddress;
    }

    public int getItineraryMailingStatus() {
        return itineraryMailingStatus;
    }

    public void setItineraryMailingStatus(int itineraryMailingStatus) {
        this.itineraryMailingStatus = itineraryMailingStatus;
    }

    public String getItineraryMailingMemo() {
        return itineraryMailingMemo;
    }

    public void setItineraryMailingMemo(String itineraryMailingMemo) {
        this.itineraryMailingMemo = itineraryMailingMemo;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getTktlDate() {
        return tktlDate;
    }

    public void setTktlDate(Date tktlDate) {
        this.tktlDate = tktlDate;
    }

    public String getTktlTime() {
        return tktlTime == null ? "" : tktlTime.trim();
    }

    public void setTktlTime(String tktlTime) {
        this.tktlTime = tktlTime;
    }

    public String getItineraryRecipient() {
        return itineraryRecipient;
    }

    public void setItineraryRecipient(String itineraryRecipient) {
        this.itineraryRecipient = itineraryRecipient;
    }

    public String getItineraryRecipientMobile() {
        return itineraryRecipientMobile;
    }

    public void setItineraryRecipientMobile(String itineraryRecipientMobile) {
        this.itineraryRecipientMobile = itineraryRecipientMobile;
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public int getIntlTicket() {
        return intlTicket;
    }

    public void setIntlTicket(int intlTicket) {
        this.intlTicket = intlTicket;
    }

    public int getPsgCount() {
        return psgCount;
    }

    public void setPsgCount(int psgCount) {
        this.psgCount = psgCount;
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

    public String getPayRemark() {
        return payRemark;
    }

    public void setPayRemark(String payRemark) {
        this.payRemark = payRemark;
    }

    public String getOp1() {
        return op1 == null ? "" : op1.trim();
    }

    public void setOp1(String op1) {
        this.op1 = op1;
    }

    public int getMonitoring() {
        return monitoring;
    }

    public void setMonitoring(int monitoring) {
        this.monitoring = monitoring;
    }

    public String getAuthOfficeNo() {
        return authOfficeNo;
    }

    public void setAuthOfficeNo(String authOfficeNo) {
        this.authOfficeNo = authOfficeNo.trim().toUpperCase();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getDayAndNight() {
        return dayAndNight;
    }

    public void setDayAndNight(int dayAndNight) {
        this.dayAndNight = dayAndNight;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(int enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getKeyCustomerNo() {
        return keyCustomerNo;
    }

    public void setKeyCustomerNo(String keyCustomerNo) {
        this.keyCustomerNo = keyCustomerNo;
    }

    public int getBookingChannel() {
        return bookingChannel;
    }

    public void setBookingChannel(int bookingChannel) {
        this.bookingChannel = bookingChannel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
