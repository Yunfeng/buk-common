package cn.buk.common.flight.intl.response;


import cn.buk.common.flight.intl.dto.IntlFlightSolutionInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 按照自己的方式组织国际机票的查询结果
 * @author yfdai
 */
public class IntlFlightResponseDto {

  /**
   * 错误代码
   * 0-无错误
   */
  private int errcode;
  /**
   * 错误信息
   */
  private String errmsg;

  /**
   * 具体的行程方案
   */
  private List<IntlFlightSolutionInfo> solutions;

  public List<IntlFlightSolutionInfo> getSolutions() {
    if (solutions == null) {
      solutions = new ArrayList<>();
    }
    return solutions;
  }

  public void setSolutions(List<IntlFlightSolutionInfo> solutions2) {
    this.solutions = solutions2;
  }

  public int getErrcode() {
    return errcode;
  }

  public void setErrcode(int errcode) {
    this.errcode = errcode;
  }

  public String getErrmsg() {
    return errmsg;
  }

  public void setErrmsg(String errmsg) {
    this.errmsg = errmsg;
  }
}
