package cn.buk.tms.exception;

/**
 * 基础意外类
 * @author yfdai
 */
public class BaseException extends Exception {

  /**
   * 错误代码
   */
  private int errcode;

  /**
   * 错误子代码
   */
  private String subCode;

  /**
   * 错误信息
   */
  private String errmsg;

  public BaseException(String message) {
    super(message);
  }

  public BaseException(int errcode, String message) {
    super(message);
    this.errcode = errcode;
  }

  public BaseException(int errcode, String subCode, String message) {
    super(message);
    this.errcode = errcode;
    this.subCode = subCode;
  }


  public int getErrcode() {
    return errcode;
  }

  public void setErrcode(int errcode) {
    this.errcode = errcode;
  }

  public String getSubCode() {
    return subCode;
  }

  public void setSubCode(String subCode) {
    this.subCode = subCode;
  }

  public String getErrmsg() {
    return errmsg;
  }

  public void setErrmsg(String errmsg) {
    this.errmsg = errmsg;
  }
}
