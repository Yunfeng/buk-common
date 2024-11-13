package cn.buk.tms.common.dto.flight;

import cn.buk.common.flight.dto.FlightInfoDto;

/**
 * @author yfdai
 */
public class BaseFlightOrderFlightDto extends FlightInfoDto {

  /**
   * 航段序号
   */
  private Integer segOrder;

  /**
   * 税的总数
   * 国内的税=airportTax+fuelSurcharge
   */
  private Integer tax;

  public Integer getSegOrder() {
    return segOrder;
  }

  public void setSegOrder(Integer segOrder) {
    this.segOrder = segOrder;
  }

  public Integer getTax() {
    return tax;
  }

  public void setTax(Integer tax) {
    this.tax = tax;
  }
}
