package cn.buk.tms.common.dto.base;

import cn.buk.tms.api.flight.ApiPriceDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 机票订单的运价信息
 * @author yfdai
 */
public class BasePriceDto extends ApiPriceDto {



    /**
     * 保险（无利润） ???
     */
    @JsonIgnore
    private double insurance;



    /**
     * 单张实收客户 = parValue + tax + serviceCharge + insurance - discount
     */
    private Double amount;

    /**
     * 代理费(默认为航司给的代理费）
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double commission;

    /**
     * 代理费（代理人自己给自己的，也就是在航司票价基础上加价部分，但不计算在服务费里）
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double commission2;

    /**
     * 成本（单张）
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double cost;

    /**
     * 单张利润
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double profit;

    /**
     * 客票数量（人数）
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer ticketCount;

    public void calc() {
        this.amount = this.getParValue() + this.getTax() + this.insurance + this.getServiceCharge() - this.getDiscount();
        if (this.commission == null) {
            this.commission = 0d;
        }
        if (this.commission2 == null) {
            this.commission2 = 0d;
        }
        this.cost = this.getParValue() + this.getTax() + this.insurance - this.commission - this.commission2;
        this.profit = this.amount - this.cost;
    }



    public double getInsurance() {
        return insurance;
    }

    public void setInsurance(double insurance) {
        this.insurance = insurance;
    }



    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }



    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getCommission2() {
        return commission2;
    }

    public void setCommission2(Double commission2) {
        this.commission2 = commission2;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public int getTicketCount() {
        return ticketCount == null ? 0 : ticketCount;
    }

    public void setTicketCount(Integer ticketCount) {
        this.ticketCount = ticketCount;
    }
}
