package cn.buk.common.flight.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

/**
 * 子舱位价格
 *
 * @author yfdai
 */
public class SubClassDto {

    private String cabinClass;
    private String subClass;
    private int seatCount;
    private String seatStatus;

    /**
     * 票价
     */
    private int price;

    /**
     * CZ NDC的offer项编码
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String offerItemId;

    /**
     * 服务费
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer serviceFee;

    /**
     * 机场税
     * 1.航班查询结果中，不包含此值；
     * 2.航班查询结果中，此值包含在航班信息中。
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer airportTax;

    /**
     * 燃油附加费
     * 1.航班查询结果中，不包含此值；
     * 2.航班查询结果中，此值包含在航班信息中。
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer fuelSurcharge;

    /**
     * 税总额 = airportTax + fuelSurcharge
     * 1.航班查询结果中，不包含此值；
     * 2.航班查询结果中，此值包含在航班信息中。
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer tax;

    /**
     * total = price + tax + serviceFee
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer total;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Float returnPoint;
    /**
     * 返钱：让利，立减那种
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer returnPrice;

    /**
     * 别称：9折，超值头等 等
     */
    private String nickname;

    /**
     * 折扣率：基于Y舱的价格计算出来的百分比
     */
    private int discountRate;

    /**
     * 退票规则说明
     */
    private String refundRule;

    /**
     * 更改规则说明
     */
    private String changeRule;

    /**
     * 退改签规则详情
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String ruleDetail;

    /**
     * 政策代码
     */
    private String policyCode;

    /**
     * 政策备注：用于向客户简介政策信息
     */
    private String policyMemo;

    /**
     * 行李信息
     */
    private List<LuggageInfoDto> luggageInfos;

    /**
     * 退改签信息
     */
    private TgqInfo tgqInfo;



    public String getCabinClass() {
        return cabinClass;
    }

    public void setCabinClass(String cabinClass) {
        this.cabinClass = cabinClass;
    }

    public String getSubClass() {
        return subClass;
    }

    public void setSubClass(String subClass) {
        this.subClass = subClass;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }

    public String getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(String seatStatus) {
        this.seatStatus = seatStatus;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Integer getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(Integer serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Integer getAirportTax() {
        return airportTax;
    }

    public void setAirportTax(Integer airportTax) {
        this.airportTax = airportTax;
    }

    public Integer getFuelSurcharge() {
        return fuelSurcharge;
    }

    public void setFuelSurcharge(Integer fuelSurcharge) {
        this.fuelSurcharge = fuelSurcharge;
    }

    public Integer getTax() {
        return tax;
    }

    public void setTax(Integer tax) {
        this.tax = tax;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Float getReturnPoint() {
        return returnPoint;
    }

    public void setReturnPoint(Float returnPoint) {
        this.returnPoint = returnPoint;
    }

    public Integer getReturnPrice() {
        return returnPrice;
    }

    public void setReturnPrice(Integer returnPrice) {
        this.returnPrice = returnPrice;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(int discountRate) {
        this.discountRate = discountRate;
    }

    public String getRefundRule() {
        return refundRule;
    }

    public void setRefundRule(String refundRule) {
        this.refundRule = refundRule;
    }

    public String getChangeRule() {
        return changeRule;
    }

    public void setChangeRule(String changeRule) {
        this.changeRule = changeRule;
    }

    public String getRuleDetail() {
        return ruleDetail;
    }

    public void setRuleDetail(String ruleDetail) {
        this.ruleDetail = ruleDetail;
    }

    public String getPolicyCode() {
        return policyCode;
    }

    public void setPolicyCode(String policyCode) {
        this.policyCode = policyCode;
    }

    public String getPolicyMemo() {
        return policyMemo;
    }

    public void setPolicyMemo(String policyMemo) {
        this.policyMemo = policyMemo;
    }

    public List<LuggageInfoDto> getLuggageInfos() {
        if(luggageInfos == null) {
            luggageInfos = new ArrayList<>();
        }
        return luggageInfos;
    }

    public void setLuggageInfos(List<LuggageInfoDto> luggageInfos) {
        this.luggageInfos = luggageInfos;
    }

    public TgqInfo getTgqInfo() {
        return tgqInfo;
    }

    public void setTgqInfo(TgqInfo tgqInfo) {
        this.tgqInfo = tgqInfo;
    }

    public String getOfferItemId() {
        return offerItemId;
    }

    public void setOfferItemId(String offerItemId) {
        this.offerItemId = offerItemId;
    }
}
