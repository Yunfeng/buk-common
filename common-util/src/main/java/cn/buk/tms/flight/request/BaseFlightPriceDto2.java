package cn.buk.tms.flight.request;

import cn.buk.tms.common.dto.base.BaseDto;
import cn.buk.tms.common.dto.base.BasePriceDto;

/**
 * 新版本，调整机票订单录入时的DTO,将成人价格和儿童价格共用代码
 * @author yfdai
 */
public class BaseFlightPriceDto2 extends BaseDto {

    // 成人
    private BasePriceDto adultPrice = new BasePriceDto();

    //儿童
    private BasePriceDto childPrice = new BasePriceDto();

    //婴儿
    private BasePriceDto infantPrice = new BasePriceDto();

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

    /**
     * 总收客金额(成人+儿童+婴儿）
     */
    private double totalAmount;

    /**
     * 总支出成本(成人+儿童+婴儿）
     */
    private double totalCost; //总成本

    private double totalProfit; //利润，总利润

    protected void calc() {
        adultPrice.calc();
        childPrice.calc();
        infantPrice.calc();

        totalAmount = adultCount * adultPrice.getAmount() + childCount * childPrice.getAmount() + infantCount * infantPrice.getAmount();
        totalCost = adultCount * adultPrice.getCost() + childCount * childPrice.getCost() + infantCount * infantPrice.getCost();
        totalProfit = adultCount * adultPrice.getProfit() + childCount * childPrice.getProfit() + infantCount * infantPrice.getProfit();
    }

    public double getAmount() {
        return this.totalAmount;
    }


    public BasePriceDto getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(BasePriceDto adultPrice) {
        this.adultPrice = adultPrice;
    }

    public BasePriceDto getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(BasePriceDto childPrice) {
        this.childPrice = childPrice;
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

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public BasePriceDto getInfantPrice() {
        return infantPrice;
    }

    public void setInfantPrice(BasePriceDto infantPrice) {
        this.infantPrice = infantPrice;
    }

    public int getInfantCount() {
        return infantCount;
    }

    public void setInfantCount(int infantCount) {
        this.infantCount = infantCount;
    }
}
