package cn.buk.tms.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * 供应商信息DTO
 * @author yfdai
 */
public class SupplierDto {

    public static SupplierDto create(int id, String name) {
        SupplierDto dto = new SupplierDto();
        dto.setId(id);
        dto.setName(name);
        return dto;
    }

    private int id;

    private String name;

    /**
     * 是否为BSP供应商, BSP供应商可以自动开票
     * FLIGHT_AUTO_TICKETING_NONE  非BSP供应商，手工处理开票
     * FLIGHT_AUTO_TICKETING_BSP - BSP自动开票供应商
     * FLIGHT_AUTO_TICKETING_GP - GP自动开票供应商
     */
    private int isBsp;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer supplierType;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String pinyin;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer status;

    /**
     * 支出付款方式id
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer paymentMethodId;

    /**
     * 支付付款方式的名称
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String paymentMethodName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String remark;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastUpdate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Integer getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(Integer supplierType) {
        this.supplierType = supplierType;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(Integer paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public void setPaymentMethodName(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }

    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    public int getIsBsp() {
        return isBsp;
    }

    public void setIsBsp(int isBsp) {
        this.isBsp = isBsp;
    }
}
