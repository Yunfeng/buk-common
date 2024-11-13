package cn.buk.common.flight.intl.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yfdai
 */
public class IntlFlightSolutionInfo {

  private int id;

  private int sortScore;

  /**
   * solution 的最低票价
   */
  private AmountInfo bestPriceAcrossAgenciesBaseFare;

  /**
   * 自定义字段：返钱，直减金额
   */
  private Integer returnPrice;

  /**
   * solution 的最低税
   * 费
   */
  private AmountInfo bestPriceAcrossAgenciesTax;
  /**
   * solution 的最低总
   * 价
   */
  private AmountInfo bestPriceAcrossAgenciesTotal;

  /**
   * 行程信息
   */
  private List<IntlSegmentInfo> segments;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getSortScore() {
    return sortScore;
  }

  public void setSortScore(int sortScore) {
    this.sortScore = sortScore;
  }

  public AmountInfo getBestPriceAcrossAgenciesBaseFare() {
    return bestPriceAcrossAgenciesBaseFare;
  }

  public void setBestPriceAcrossAgenciesBaseFare(AmountInfo bestPriceAcrossAgenciesBaseFare) {
    this.bestPriceAcrossAgenciesBaseFare = bestPriceAcrossAgenciesBaseFare;
  }

  public AmountInfo getBestPriceAcrossAgenciesTax() {
    return bestPriceAcrossAgenciesTax;
  }

  public void setBestPriceAcrossAgenciesTax(AmountInfo bestPriceAcrossAgenciesTax) {
    this.bestPriceAcrossAgenciesTax = bestPriceAcrossAgenciesTax;
  }

  public AmountInfo getBestPriceAcrossAgenciesTotal() {
    return bestPriceAcrossAgenciesTotal;
  }

  public void setBestPriceAcrossAgenciesTotal(AmountInfo bestPriceAcrossAgenciesTotal) {
    this.bestPriceAcrossAgenciesTotal = bestPriceAcrossAgenciesTotal;
  }

  public List<IntlSegmentInfo> getSegments() {
    if (segments == null) {
      segments = new ArrayList<>();
    }
    return segments;
  }

  public void setSegments(List<IntlSegmentInfo> segments) {
    this.segments = segments;
  }

  public Integer getReturnPrice() {
    return returnPrice;
  }

  public void setReturnPrice(Integer returnPrice) {
    this.returnPrice = returnPrice;
  }
}
