package cn.buk.tms.common.dto;


import cn.buk.tms.common.dto.base.BaseDto;

/**
 * 从tms系统中抽取出来，供微服务gds使用
 * 客户的折扣信息
 * @author yfdai
 */
public class CustomerDiscountInfoDto extends BaseDto {

  /**
   * 酒店的直减金额
   */
  private int hotelDiscount;

  /**
   * 用车的直减金额
   */
  private int carDiscount;

  /**
   * 登机牌办理的直减金额
   */
  private int boardingCardDiscount;

  /**
   * 国内机票公布运价的直减金额
   */
  private int domFlightDiscount;
  /**
   * 国际机票公布运价的直减金额
   */
  private int intlFlightDiscount;

  public int getHotelDiscount() {
    return hotelDiscount;
  }

  public void setHotelDiscount(int hotelDiscount) {
    this.hotelDiscount = hotelDiscount;
  }

  public int getCarDiscount() {
    return carDiscount;
  }

  public void setCarDiscount(int carDiscount) {
    this.carDiscount = carDiscount;
  }

  public int getBoardingCardDiscount() {
    return boardingCardDiscount;
  }

  public void setBoardingCardDiscount(int boardingCardDiscount) {
    this.boardingCardDiscount = boardingCardDiscount;
  }

  public int getDomFlightDiscount() {
    return domFlightDiscount;
  }

  public void setDomFlightDiscount(int domFlightDiscount) {
    this.domFlightDiscount = domFlightDiscount;
  }

  public int getIntlFlightDiscount() {
    return intlFlightDiscount;
  }

  public void setIntlFlightDiscount(int intlFlightDiscount) {
    this.intlFlightDiscount = intlFlightDiscount;
  }
}
