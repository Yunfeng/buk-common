package cn.buk.tms.common.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 客户信息DTO
 */
public class CustomerDto {

    public static CustomerDto create(int id, String name, String customerCode) {
        CustomerDto o = new CustomerDto();
        o.setId(id);
        if (id > 0) {
            o.setName(name);
            o.setCustomerCode(customerCode);
        } else {
            o.setName("散客");
        }

        return o;
    }

    /**
     * 客户ID
     */
    private int id;
    /**
     * 客户名称
     */
    private String name;
    /**
     * 客户代码
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String customerCode;

    /**
     * 账单地址
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String address;

    /**
     * 企业差旅负责人（审核人）(账单）
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String linkman;
    /**
     * 联系电话
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String phone;

    /**
     * 行程单需求类型
     * 0 - 不需要
     * 1 - 行程单
     * 2 - 发票
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer itineraryType;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer enterpriseId;

    /**
     * 企业类型
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer enterpriseType;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String pinyin;

    /**
     * 启用状态
     * 0 - 未启用
     * 1 - 启用中
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer status;


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

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getItineraryType() {
        return itineraryType;
    }

    public void setItineraryType(Integer itineraryType) {
        this.itineraryType = itineraryType;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Integer getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(Integer enterpriseType) {
        this.enterpriseType = enterpriseType;
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
}
