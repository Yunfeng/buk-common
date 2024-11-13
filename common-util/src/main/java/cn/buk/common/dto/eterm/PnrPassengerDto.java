package cn.buk.common.dto.eterm;

import cn.buk.common.Constant;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PnrPassengerDto {

  private static final  String MR_RegEx = "(MR)$";
  private static final  String MS_RegEx = "(MS)$";
  private static final  String CHD_RegEx = "(CHD)$";
  private static final  String MSTR_RegEx = "(MSTR)$";
  private static final  String MISS_RegEx = "(MISS)$";

  public PnrPassengerDto() {

  }

  public PnrPassengerDto(final int psgNo, final String psgName0) {
    final String psgName1 = psgName0.trim();

    this.psgNo = psgNo;
    this.selected = true;
    this.psgName = psgName1;
    this.psgType = Constant.PSG_TYPE_ADU;

    Pattern p = Pattern.compile(MR_RegEx);
    Matcher m = p.matcher(psgName);
    if (m.find()) {
      // 男性成人
      this.gender = Constant.GENDER_MALE;
      this.psgName = psgName.substring(0, m.start()).trim();
      return;
    }

    p = Pattern.compile(MS_RegEx);
    m = p.matcher(psgName);
    if (m.find()) {
      // 女性成人
      this.gender = Constant.GENDER_FEMALE;
      this.psgName = psgName.substring(0, m.start() - 1).trim();
      return;
    }

    p = Pattern.compile(CHD_RegEx);
    m = p.matcher(psgName);
    if (m.find()) {
      // 儿童
      this.psgType = Constant.PSG_TYPE_CHD;
      this.psgName = psgName.substring(0, m.start()).trim();
      return;
    }

    p = Pattern.compile(MSTR_RegEx);
    m = p.matcher(psgName);
    if (m.find()) {
      // 男性儿童
      this.psgType = Constant.PSG_TYPE_CHD;
      this.gender = Constant.GENDER_MALE;
      this.psgName = psgName.substring(0, m.start() - 1).trim();
      return;
    }

    p = Pattern.compile(MISS_RegEx);
    m = p.matcher(psgName);
    if (m.find()) {
      // 女性性儿童
      this.psgType = Constant.PSG_TYPE_CHD;
      this.gender = Constant.GENDER_FEMALE;
      this.psgName = psgName.substring(0, m.start() - 1).trim();
      return;
    }

//
//		//处理名字
//		int idx = psgName.indexOf(" ");
//		if (idx < 0 && psgName.contains("CHD")) {
//			this.psgType = Constant.PSG_TYPE_CHD;
//			idx = psgName.indexOf("CHD");
//			if (idx > 0) {
//				this.psgName = psgName.substring(0, idx - 1); // 儿童的名字中去掉CHD
//			}
//		} else if (psgName.length() > 6){
////		儿童 2-12岁
////        男童  MSTR
////        女童  MISS
////        其他一般统一加CHD
//			final String temp = psgName.substring(psgName.length()-4).trim();
//			if (temp.equalsIgnoreCase("CHD") || temp.equalsIgnoreCase("MSTR") || temp.equalsIgnoreCase("MISS")) {
//				this.psgType = Constant.PSG_TYPE_CHD;
//				this.psgName = psgName.substring(0, psgName.length() - 4).trim();
//			}
//
//			if(temp.equalsIgnoreCase("MSTR")) {
//				this.gender = Constant.GENDER_MALE;
//			} else if (temp.equalsIgnoreCase("MISS")) {
//				this.gender = Constant.GENDER_FEMALE;
//			}
//		}
  }


  private int id;

  /**
   * 乘机人序号
   */
  private int sn;

  private String firstName;

  private String lastName;

  private String psgName;

  /**
   * null/-1 未填写
   * 1 - 男
   * 2 - 女
   */
  private Integer gender;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date birthday;

  /**
   * 证件类型
   * /**
   * 1-身份证
   * 2-护照
   */
  private int idType;

  private String idNo;

  /**
   * 国籍：2位或三位代码
   */
  private String nationality;

  /**
   * 证件有效期
   */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date idExpiredDate;


  /**
   * 保存pnr中针对某人的fn项目
   */

  private String fn;

  private String patA;

  /**
   * 在pnr中名字对应的前面的数字
   */
  private int psgNo;

  /**
   * 乘机人类型：成人，儿童，婴儿
   * 0 - ADT - adult
   * 1 - CHD - child
   * 2 - INF - infant
   */
  private int psgType;


  /**
   * 联系手机
   */
  private String mobile;

  /**
   * 票号
   */
  private String ticketNo;

  /**
   * 随行婴儿名
   */
  private String infName;

  /**
   * 随行婴儿票号
   */
  private String infTicketNo;


  private boolean selected;

  public String getName() {
    return this.psgName;
  }


  /**
   * @return the sn
   */
  public int getSn() {
    return sn;
  }

  /**
   * @param sn the sn to set
   */
  public void setSn(int sn) {
    this.sn = sn;
  }

  /**
   * @return the firstName
   */
  public String getFirstName() {

    return firstName;
  }

  /**
   * @param firstName the firstName to set
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * @return the lastName
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * @param lastName the lastName to set
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * @return the idNo
   */
  public String getIdNo() {
    return idNo;
  }

  /**
   * @param idNo the idNo to set
   */
  public void setIdNo(String idNo) {
    this.idNo = idNo;
  }

  /**
   * @return the fn
   */
  public String getFn() {
    return fn;
  }

  /**
   * @param fn the fn to set
   */
  public void setFn(String fn) {
    this.fn = fn;
  }

  public String getPsgName() {
    return psgName;
  }

  public void setPsgName(String psgName) {
    this.psgName = psgName;
  }

  public String getPatA() {
    return patA;
  }

  public void setPatA(String patA) {
    this.patA = patA;
  }

  public int getPsgNo() {
    return psgNo;
  }

  public void setPsgNo(int psgNo) {
    this.psgNo = psgNo;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public int getPsgType() {
    return psgType;
  }

  public void setPsgType(int psgType) {
    this.psgType = psgType;
  }

  public boolean isSelected() {
    return selected;
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
  }

  public String getTicketNo() {
    return ticketNo;
  }

  public void setTicketNo(String ticketNo) {
    this.ticketNo = ticketNo;
  }

  public String getInfName() {
    return infName;
  }

  public void setInfName(String infName) {
    this.infName = infName;
  }

  public String getInfTicketNo() {
    return infTicketNo;
  }

  public void setInfTicketNo(String infTicketNo) {
    this.infTicketNo = infTicketNo;
  }

  public int getIdType() {
    return idType;
  }

  public void setIdType(int idType) {
    this.idType = idType;
  }

  public String getNationality() {
    return nationality;
  }

  public void setNationality(String nationality) {
    this.nationality = nationality;
  }

  public Date getIdExpiredDate() {
    return idExpiredDate;
  }

  public void setIdExpiredDate(Date idExpiredDate) {
    this.idExpiredDate = idExpiredDate;
  }

  public int getGender() {
    return gender == null ? -1 : gender;
  }

  public void setGender(Integer gender) {
    this.gender = gender;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }
}
