package cn.buk.tms.common.dto;

import cn.buk.tms.common.dto.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Date;

/**
 * 从tms系统中抽取出来，供微服务gds使用
 * 机票私有政策（大客户政策）
 * @author yfdai
 */
@JsonPropertyOrder({"id", "policyCode", "carrier", "keyCustomerCode", "subclasses", "effectDate", "expireDate", "status"})
public class KeyCustomerPolicyDto extends BaseDto {

  private int id;

  /**
   * 政策编号
   */
  private String policyCode;

  private String carrier;

  /**
   * 政策包含的舱位代码，可以有多个舱位
   */
  private String subclasses;

  /**
   * 航司提供的大客户代码
   */
  private String keyCustomerCode;

  /**
   * 公开备注，主要用于页面上显示给客人看到的
   */
  private String publicMemo;

  /**
   * 私有备注，公司内部查看
   */
  private String privateMemo;

  /**
   * 生效日期
   */
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date effectDate;

  /**
   * 失效日期
   */
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date expireDate;

  /**
   * 状态：
   * 0：停用
   * 1：启用
   */
  private int status;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date createTime;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date lastUpdate;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPolicyCode() {
    return policyCode;
  }

  public void setPolicyCode(String policyCode) {
    this.policyCode = policyCode == null ? null : policyCode.trim().toUpperCase();
  }

  public String getCarrier() {
    return carrier;
  }

  public void setCarrier(String carrier) {
    this.carrier = carrier == null ? null : carrier.trim().toUpperCase();
  }

  public String getSubclasses() {
    return subclasses;
  }

  public void setSubclasses(String subclasses) {
    this.subclasses = subclasses == null ? null: subclasses.trim().toUpperCase();
  }

  public String getKeyCustomerCode() {
    return keyCustomerCode;
  }

  public void setKeyCustomerCode(String keyCustomerCode) {
    this.keyCustomerCode = keyCustomerCode;
  }

  public String getPublicMemo() {
    return publicMemo;
  }

  public void setPublicMemo(String publicMemo) {
    this.publicMemo = publicMemo;
  }

  public String getPrivateMemo() {
    return privateMemo;
  }

  public void setPrivateMemo(String privateMemo) {
    this.privateMemo = privateMemo;
  }

  public Date getEffectDate() {
    return effectDate;
  }

  public void setEffectDate(Date effectDate) {
    this.effectDate = effectDate;
  }

  public Date getExpireDate() {
    return expireDate;
  }

  public void setExpireDate(Date expireDate) {
    this.expireDate = expireDate;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
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
}
