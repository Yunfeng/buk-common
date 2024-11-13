package cn.buk.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author yfdai
 */
public class DecimalUtil {

  private DecimalUtil() {

  }

  /**
   * 保留2位小数
   */
  public static double keepScale2(double f) {
    return BigDecimal.valueOf(f).setScale(2, RoundingMode.HALF_UP).doubleValue();
  }
}
