package cn.buk.common.dto;

/**
 * Created by yfdai on 16/7/8.
 * tmc 订单状态统计类
 */
public class TmcOrderStatusStat {

    private int enterpriseId;
    /**
     * 订单类型:
     * 0-订单,
     * 1-退票,
     * 2-改签
     */
    private int type;

    private int status;

    private int count;

    public int getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(int enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
