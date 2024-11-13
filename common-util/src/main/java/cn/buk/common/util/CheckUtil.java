/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/
package cn.buk.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 格式检查工具
 * @author yfdai
 */
public class CheckUtil {

  private CheckUtil() {
    throw new IllegalArgumentException("Utility class");
  }

  /**
   * 电子邮件的格式
   */
  private static final Pattern emailPattern = Pattern.compile("^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$");

  /**
   * 手机号格式
   */
  private static final Pattern mobileNoPattern = Pattern.compile("^(1)\\d{10}$");

  /**
   * 日期格式：yyyy-MM-dd
   */
  private static final Pattern datePattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");

  /**
   * 时间格式：0000, 2359, 00:00, 23:59
   */
  private static final Pattern TIME_PATTERN = Pattern.compile("^[0-2][0-9]:?[0-6][0-9]$");

  public static boolean isEmail(String email) {
    Matcher m = emailPattern.matcher(email);
    return m.matches();
  }

  public static boolean isMobileNo(String mobile) {
    if (mobile == null) {
      return false;
    }
    Matcher m = mobileNoPattern.matcher(mobile);
    return m.matches();
  }

  public static boolean isDate(String strDate) {
    Matcher m = datePattern.matcher(strDate);
    return m.matches();
  }

  /**
   * 检查是否为有效的时间格式：18:30, 1830 etc
   */
  public static boolean isValidTime(String time) {
    if (time == null) {
      return false;
    }

    return TIME_PATTERN.matcher(time).matches();
  }

}
