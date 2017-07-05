package cn.buk.common.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yfdai on 16/6/2.
 * 出票统计
 */
public class TicketStatDto implements Serializable {

    /**
     * 出票日期
     */
    private Date ticketDate;

    private int totalTicket;
    private float totalAmount;

    /**
     * 机票数量
     */
    private int ticketCount;

    /**
     * 出票金额
     */
    private float amount;

    public Date getTicketDate() {
        return ticketDate;
    }

    public void setTicketDate(Date ticketDate) {
        this.ticketDate = ticketDate;
    }

    public int getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(int ticketCount) {
        this.ticketCount = ticketCount;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getTotalTicket() {
        return totalTicket;
    }

    public void setTotalTicket(int totalTicket) {
        this.totalTicket = totalTicket;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }
}
