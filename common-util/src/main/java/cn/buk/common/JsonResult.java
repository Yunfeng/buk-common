package cn.buk.common;

import cn.buk.tms.common.dto.base.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author yfdai
 */
public class JsonResult extends BaseDto {

  public static final String STATUS_OK = "OK";
  public static final String STATUS_ER = "ER";
  public static final String STATUS_NA = "NA";


  public static JsonResult createJsonResult(int retCode) {
    return createJsonResult(retCode, null);
  }

  public static JsonResult createJsonResult(int retCode, String errmsg) {
    JsonResult jsonResult = new JsonResult();

    if (retCode > 0) {
      jsonResult.setErrcode(0);
      jsonResult.setStatus(STATUS_OK);
      jsonResult.setReturnCode(retCode);
      jsonResult.setDesc(errmsg);
    } else {
      jsonResult.setErrcode(Math.abs(retCode));
      jsonResult.setStatus(STATUS_ER);
      jsonResult.setErrmsg(errmsg);
    }

    return jsonResult;
  }

  /**
   * 状态
   */
  private String status;

  /**
   * 说明
   */
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String desc;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String url;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String attach;

  /**
   * @return the status
   */
  public String getStatus() {
    if (status == null) {
      return STATUS_NA;
    }
    return status;
  }

  /**
   * @param status the status to set
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * @return the desc
   */
  public String getDesc() {
    return desc;
  }

  /**
   * @param desc the desc to set
   */
  public void setDesc(String desc) {
    this.desc = desc;
  }


  public void setUrl(String url) {
    this.url = url;
  }

  public String getUrl() {
    return url;
  }

  public void setAttach(String attach) {
    this.attach = attach;
  }

  public String getAttach() {
    return attach;
  }
}
