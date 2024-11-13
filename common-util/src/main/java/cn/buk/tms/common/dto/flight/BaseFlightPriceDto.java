package cn.buk.tms.common.dto.flight;

import cn.buk.tms.common.dto.base.BaseDto;
import cn.buk.tms.common.dto.base.BasePriceDto;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于机票订单的生成和响应时的价格信息
 * @author yfdai
 */
public class BaseFlightPriceDto extends BaseDto {

    /**
     * 接口参数
     * 价格
     */
    private List<BasePriceDto> prices;

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
     * 接口参数
     * 总收客金额(成人+儿童+婴儿）
     */
    private double totalAmount;

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



    public int getInfantCount() {
        return infantCount;
    }

    public void setInfantCount(int infantCount) {
        this.infantCount = infantCount;
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
}
