package cn.buk.common.dto.eterm;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author yfdai
 */
public class TprItemDto {

    private static final Logger logger = LogManager.getLogger(TprItemDto.class);



    private static final String TICKET_ET_REFUND = "ET-REFUND";
    private static final String TICKET_VOID = "VOID";
    private static final String TICKET_EXCHANGE = "-EX";

    private static final String ET_ISSUE_FAILED = "BSP ET ISSUE FAILED";
    private static final String ET_REFUND_FAILED = "ET REFUND MESSAGE TO";

    public static final int STATUS_TICKETING = 0;
    public static final int STATUS_EXCHANGE = 1;
    public static final int STATUS_REFUND = 4;
    /**
     * 废票
     */
    public static final int STATUS_VOID = 16;


    private String detail;

    private String ticketNo;
    private String balCode;

    private String orig;
    private String dest;

    private String pnrNo;

    /**
     * 0 - 出票
     * 1 - 换开
     * 4 - 退票
     * 16 - 废票
     */
    private int status;

    private double price;

    private double tax;

    private double commission;

    private double commissionRate;

    /**
     * 联程标志
     */
    private String conjTag;


    public void setDetail(final String detail) {
        this.detail = detail;

        if (detail.contains(TICKET_ET_REFUND)) {
            this.status = STATUS_REFUND;
        } else if (detail.contains(TICKET_VOID)) {
            this.status = STATUS_VOID;
        } else if (detail.contains(TICKET_EXCHANGE)) {
            this.status = STATUS_EXCHANGE;
        }

        this.ticketNo = detail.substring(0, 14);
        this.balCode = this.ticketNo.substring(0, 3);

        this.conjTag = detail.substring(15, 17).trim();

        this.pnrNo = detail.substring(59, 65);

        if (this.status == STATUS_VOID) {
            // 废票
            return;
        }

        if (this.status != STATUS_REFUND) {
            // 退票
            this.orig = detail.substring(18, 21);
            this.dest = detail.substring(22, 25);
        }

        if (detail.contains(ET_ISSUE_FAILED) || detail.contains(ET_REFUND_FAILED)) {
            return;
        }

        try {
            String temp = detail.substring(28, 59).trim();

            if (temp.length() > 0) {
                int idx = temp.indexOf(" ");
                this.price = Double.parseDouble(temp.substring(0, idx-1));

                temp = temp.substring(idx).trim();

                if (temp.length() > 8) {
                    idx = temp.indexOf(" ");
                    this.tax = Double.parseDouble(temp.substring(0, idx - 1));

                    temp = temp.substring(idx).trim();
                    if (temp.contains("%")) {
                        this.commissionRate = Double.parseDouble(temp.replace("%", ""));
                    } else {
                        this.commission = Double.parseDouble(temp.replace("%", ""));
                    }
                } else {
                    //无税的情况
                    if (temp.contains("%")) {
                        this.commissionRate = Double.parseDouble(temp.replace("%", ""));
                    } else {
                        this.commission = Double.parseDouble(temp.replace("%", ""));
                    }
                }
            }
        } catch (Exception ex) {
            logger.error(detail);
            logger.error(ex.getMessage());
        }
    }

    public String getDetail() {
        return detail;
    }

    public String getBalCode() {
        return balCode;
    }

    public void setBalCode(String balCode) {
        this.balCode = balCode;
    }

    public String getOrig() {
        return orig;
    }

    public void setOrig(String orig) {
        this.orig = orig;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getPnrNo() {
        return pnrNo == null ? "" : pnrNo.trim();
    }

    public void setPnrNo(String pnrNo) {
        this.pnrNo = pnrNo;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public String getConjTag() {
        return conjTag;
    }

    public void setConjTag(String conjTag) {
        this.conjTag = conjTag;
    }

    public double getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(double commissionRate) {
        this.commissionRate = commissionRate;
    }
}
