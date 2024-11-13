package cn.buk.tms.common.dto;

import cn.buk.tms.common.dto.base.BaseDto;

/**
 * 从tms系统中抽取出来，供微服务gds使用
 * 全局加价设置
 * @author yfdai
 */
public class MarkupConfigDto extends BaseDto {

  /**
   * 酒店全局加价
   */
  private int hotelMarkup;

  /**
   * 用车全局加价
   */
  private int carMarkup;

  /**
   * 登机牌全局加价
   */
  private int boardingCardMarkup;

  /**
   * 国内机票全局加价
   */
  private int domFlightMarkup;
  /**
   * 国际机票全局加价
   */
  private int intlFlightMarkup;

  public int getHotelMarkup() {
    return hotelMarkup;
  }

  public void setHotelMarkup(int hotelMarkup) {
    this.hotelMarkup = hotelMarkup;
  }

  public int getCarMarkup() {
    return carMarkup;
  }

  public void setCarMarkup(int carMarkup) {
    this.carMarkup = carMarkup;
  }

  public int getBoardingCardMarkup() {
    return boardingCardMarkup;
  }

  public void setBoardingCardMarkup(int boardingCardMarkup) {
    this.boardingCardMarkup = boardingCardMarkup;
  }

  public int getDomFlightMarkup() {
    return domFlightMarkup;
  }

  public void setDomFlightMarkup(int domFlightMarkup) {
    this.domFlightMarkup = domFlightMarkup;
  }

  public int getIntlFlightMarkup() {
    return intlFlightMarkup;
  }

  public void setIntlFlightMarkup(int intlFlightMarkup) {
    this.intlFlightMarkup = intlFlightMarkup;
  }
}
