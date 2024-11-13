package cn.buk.tms.common.dto.hotel;

import cn.buk.tms.common.dto.base.BaseOrderDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * tms和eotms中的酒店订单response和request公用部分
 *
 * @author yfdai
 */
public class BaseHotelOrderDto extends BaseOrderDto {

  /**
   * 酒店订单状态
   *
   * @see cn.buk.tms.common.dto.base.BaseOrderDto
   * orderStatus
   */
  private int status;

  /**
   * 酒店订单类型
   * 0-预订单
   * 4-退单
   */
  private int hotelOrderType;

  /**
   * 客户ID：
   * 0 - 散客
   * >1 - 客户（企业散客，企业客户）
   */
  private Integer customerId;

  /**
   * 订单金额是否已校验
   * 0 - 未校验
   * 1 - 校验通过
   * 4 - 校验失败, 需要人工干预
   */
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Integer priceCheckedStatus;

  /**
   * 订单金额校验时间
   */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date priceCheckedTime;

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
    this.setOrderStatus(status);
  }

  public Integer getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Integer customerId) {
    this.customerId = customerId;
  }

  public int getPriceCheckedStatus() {
    return priceCheckedStatus == null ? 0 : priceCheckedStatus;
  }

  public void setPriceCheckedStatus(int priceCheckedStatus) {
    this.priceCheckedStatus = priceCheckedStatus;
  }

  public Date getPriceCheckedTime() {
    return priceCheckedTime;
  }

  public void setPriceCheckedTime(Date priceCheckedTime) {
    this.priceCheckedTime = priceCheckedTime;
  }

  public int getHotelOrderType() {
    return hotelOrderType;
  }

  public void setHotelOrderType(int hotelOrderType) {
    this.hotelOrderType = hotelOrderType;
  }
}
