package cn.buk.tms.common.dto.base;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 用于订单输入时的名单保存，
 * 也可用于订单输出时的乘机人信息
 * @author yfdai
 */
public class BasePassengerDto extends BaseDto {

  /**
   * 接口参数
   * 姓名 和 姓与名 必填一个
   */
  private String name;

  /**
   * Given Name 名
   */
  private String firstName;

  /**
   * Sur name 名
   */
  private String lastName;

  /**
   * 接口参数
   * 证件类型：1-身份证，2-护照
   */
  private Integer idType;

  /**
   * 接口参数
   * 证件号
   */
  @NotNull
  @Size(min = 1)
  private String idNo;

  /**
   * 国籍代码
   */
  private String countryCode;

  /**
   * 证件签发国家/地区
   */
  private String idIssueNation;

  /**
   * 证件有效期
   */
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date idValidDate;

  /**
   * 生日
   */
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date birthday;

  /**
   * 接口参数
   * 乘机人类型
   */
  private Integer psgType;

  /**
   * 性别
   */
  private Integer gender;

  /**
   * 接口参数
   * 乘机人移动号码
   */
  private String mobile;

  /**
   * 电子邮件
   */
  private String email;

  /**
   * 接口参数
   * 常旅客卡号（对应当前预订航司的）
   */
  private String ffpNo;

  /**
   * 接口参数
   * 是否选中保存
   */
  @JsonIgnore
  private boolean selected = true;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getIdType() {
    return idType;
  }

  public void setIdType(Integer idType) {
    this.idType = idType;
  }

  public String getIdNo() {
    return idNo;
  }

  public void setIdNo(String idNo) {
    this.idNo = idNo;
  }

  public Integer getPsgType() {
    return psgType;
  }

  public void setPsgType(Integer psgType) {
    this.psgType = psgType;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFfpNo() {
    return ffpNo;
  }

  public void setFfpNo(String ffpNo) {
    this.ffpNo = ffpNo;
  }

  public boolean isSelected() {
    return selected;
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
  }

  public Integer getGender() {
    return gender;
  }

  public void setGender(Integer gender) {
    this.gender = gender;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getIdIssueNation() {
    return idIssueNation;
  }

  public void setIdIssueNation(String idIssueNation) {
    this.idIssueNation = idIssueNation;
  }

  public Date getIdValidDate() {
    return idValidDate;
  }

  public void setIdValidDate(Date idValidDate) {
    this.idValidDate = idValidDate;
  }
}
