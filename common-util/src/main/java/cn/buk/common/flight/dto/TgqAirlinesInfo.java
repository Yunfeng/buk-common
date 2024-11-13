package cn.buk.common.flight.dto;


import java.util.List;

/**
 * 航空公司的退改签信息
 */
public class TgqAirlinesInfo {

  private List<TgqSubclassInfo> subclassInfos;

  private List<TgqTimeWindowDto> timeWindows;

  public List<TgqSubclassInfo> getSubclassInfos() {
    return subclassInfos;
  }

  public void setSubclassInfos(List<TgqSubclassInfo> subclassInfos) {
    this.subclassInfos = subclassInfos;
  }

  public List<TgqTimeWindowDto> getTimeWindows() {
    return timeWindows;
  }

  public void setTimeWindows(List<TgqTimeWindowDto> timeWindows) {
    this.timeWindows = timeWindows;
  }
}
