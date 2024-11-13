package cn.buk.common.flight.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 航空公司仓位信息及相关自愿退改费率
 * @author yfdai
 */
public class TgqSubclassInfo {

    private int id;

    private String carrier;

    /**
     * 舱位性质
     * 10-头等舱
     * 20-商务舱
     * 30-经济舱
     * 40-超级经济舱
     */
    private int cabinType;

    private String subclass;

    /**
     * 乘机人类型
     */
    private int psgType;

    /**
     * 仓位描述
     */
    private String subclassDesc;

    /**
     * 相对于Y的折扣金额
     */
    private int discount = 100;

    /**
     * 是否允许OPEN
     * 0-不允许
     * 1-允许
     */
    private int openAllowed;

    /**
     * 是否可以自愿签转
     * 0-不可以
     * 1- 可以
     */
    private int endorsable;

    /**
     * 航司给的定额代理费
     */
    private Integer commission;

    /**
     * 改签手续费率, 百分数
     */
    private int changeFeeRate1;

    private int changeFeeRate2;

    private int changeFeeRate3;

    private int changeFeeRate4;

    /**
     * 退票手续费率，百分数
     */
    private int refundFeeRate1;

    private int refundFeeRate2;

    private int refundFeeRate3;

    private int refundFeeRate4;

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

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        if (carrier == null) {
          carrier = "";
        } else {
          carrier = carrier.trim().toUpperCase();
        }

        this.carrier = carrier;
    }

    public String getSubclass() {
        return subclass.trim();
    }

    public void setSubclass(String subclass) {
        if (subclass == null) {
          subclass = "";
        } else {
          subclass = subclass.trim().toUpperCase();
        }

        this.subclass = subclass;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getCabinType() {
        return cabinType;
    }

    public void setCabinType(int cabinType) {
        this.cabinType = cabinType;
    }

    public String getSubclassDesc() {
        return subclassDesc;
    }

    public void setSubclassDesc(String subclassDesc) {
        this.subclassDesc = subclassDesc;
    }

    public Integer getCommission() {
        return commission;
    }

    public void setCommission(Integer commission) {
        this.commission = commission;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getChangeFeeRate4() {
        return changeFeeRate4;
    }

    public void setChangeFeeRate4(int changeFeeRate4) {
        this.changeFeeRate4 = changeFeeRate4;
    }

    public int getChangeFeeRate1() {
        return changeFeeRate1;
    }

    public void setChangeFeeRate1(int changeFeeRate1) {
        this.changeFeeRate1 = changeFeeRate1;
    }

    public int getChangeFeeRate2() {
        return changeFeeRate2;
    }

    public void setChangeFeeRate2(int changeFeeRate2) {
        this.changeFeeRate2 = changeFeeRate2;
    }

    public int getChangeFeeRate3() {
        return changeFeeRate3;
    }

    public void setChangeFeeRate3(int changeFeeRate3) {
        this.changeFeeRate3 = changeFeeRate3;
    }

    public int getRefundFeeRate1() {
        return refundFeeRate1;
    }

    public void setRefundFeeRate1(int refundFeeRate1) {
        this.refundFeeRate1 = refundFeeRate1;
    }

    public int getRefundFeeRate2() {
        return refundFeeRate2;
    }

    public void setRefundFeeRate2(int refundFeeRate2) {
        this.refundFeeRate2 = refundFeeRate2;
    }

    public int getRefundFeeRate3() {
        return refundFeeRate3;
    }

    public void setRefundFeeRate3(int refundFeeRate3) {
        this.refundFeeRate3 = refundFeeRate3;
    }

    public int getRefundFeeRate4() {
        return refundFeeRate4;
    }

    public void setRefundFeeRate4(int refundFeeRate4) {
        this.refundFeeRate4 = refundFeeRate4;
    }

    public int getPsgType() {
        return psgType;
    }

    public void setPsgType(int psgType) {
        this.psgType = psgType;
    }

    public int getEndorsable() {
        return endorsable;
    }

    public void setEndorsable(int endorsable) {
        this.endorsable = endorsable;
    }

    public int getOpenAllowed() {
        return openAllowed;
    }

    public void setOpenAllowed(int openAllowed) {
        this.openAllowed = openAllowed;
    }
}
