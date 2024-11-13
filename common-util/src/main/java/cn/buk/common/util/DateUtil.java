/*
* Distributable under LGPL v3 license.
* See terms of license at https://github.com/Yunfeng/schotel/blob/master/LICENSE
*/

package cn.buk.common.util;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static cn.buk.common.Constant.DATE_YYYY_MM_DD;

public class DateUtil {

  private DateUtil() {
  }

  public static Date createDate(LocalDate localDate) {
    return createDate(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
  }

  /**
   * 按照指定的年月日数字生产Date对象
   */
  public static Date createDate(int year, int month, int date) {
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.clear();
    calendar.set(Calendar.YEAR, year);
    calendar.set(Calendar.MONTH, month - 1);
    calendar.set(Calendar.DAY_OF_MONTH, date);
    return calendar.getTime();
  }

  public static Date createDate(int year, int month, int day, int hourOfDay, int minute, int second) {
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.clear();
    calendar.set(Calendar.YEAR, year);
    calendar.set(Calendar.MONTH, month - 1);
    calendar.set(Calendar.DAY_OF_MONTH, day);
    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
    calendar.set(Calendar.MINUTE, minute);
    calendar.set(Calendar.SECOND, second);

    return calendar.getTime();
  }

  /**
   * 调整时间
   */
  public static Date setTimeOnDate(Date date, int hourOfDay, int minute, int second) {
    Calendar c = Calendar.getInstance();
    c.clear();
    c.setTime(date);
    c.set(Calendar.HOUR_OF_DAY, hourOfDay);
    c.set(Calendar.MINUTE, minute);
    c.set(Calendar.SECOND, second);
    return c.getTime();
  }

  /**
   * 获取当前日期时间
   */
  public static Date getCurDateTime() {
    return Calendar.getInstance().getTime();
  }

  public static Date getCurTime() {
    Calendar c = Calendar.getInstance();
    c.clear(Calendar.YEAR);
    c.clear(Calendar.MONTH);
    c.clear(Calendar.DAY_OF_MONTH);

    return c.getTime();
  }

  public static int getCurrentHour() {
    return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
  }

  public static int getCurrentMinuteOfHour() {
    return Calendar.getInstance().get(Calendar.MINUTE);
  }

  public static String getCurDateTimeString() {
    return getCurDateTimeString(null);
  }

  public static String getCurDateTimeString(String format) {
    if (format == null) {
      format = "yyyy年 MM月 dd日 HH时 mm分 ss秒";
    }
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    return sdf.format(getCurDateTime());
  }


  /**
   * 默认格式：yyyy-MM-dd HH:mm:ss
   */
  public static String formatDate(Date date) {
    return formatDate(date, null);
  }

  public static String formatDate(Date date, String format, Locale locale) {
    SimpleDateFormat sdf = new SimpleDateFormat(format, locale);
    return sdf.format(date);
  }

  public static String formatDate(Date date, String format) {
    if (date == null) {
      return null;
    }
    if (format == null) {
      format = "yyyy-MM-dd HH:mm:ss";
    }
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
    return sdf.format(date);
  }

  public static Date getCurDate() {
    Calendar c = Calendar.getInstance();
    c.set(Calendar.HOUR_OF_DAY, 0);
    c.clear(Calendar.MINUTE);
    c.clear(Calendar.SECOND);
    c.clear(Calendar.MILLISECOND);
    return c.getTime();
  }

  public static Date getSomedayAfterToday(int x) {
    Calendar c = Calendar.getInstance();
    c.set(Calendar.HOUR_OF_DAY, 0);
    c.clear(Calendar.MINUTE);
    c.clear(Calendar.SECOND);
    c.clear(Calendar.MILLISECOND);
    c.add(Calendar.DAY_OF_MONTH, x);
    return c.getTime();
  }

  public static Date addDays(Date date, int x) {
    Calendar c = Calendar.getInstance();
    c.clear();
    c.setTime(date);
    c.add(Calendar.DATE, x);
    return c.getTime();
  }

  public static Date addMonth(Date date, int x) {
    Calendar c = Calendar.getInstance();
    c.clear();
    c.setTime(date);
    c.add(Calendar.MONTH, x);
    return c.getTime();
  }

  /**
   * 获取完整的起飞时间
   */
  public static Date getFullDDate(Date dDate, String dTime) {
    Calendar c = new GregorianCalendar();
    c.clear();
    c.setTime(dDate);

    int dHour = 0;
    int dMinute = 0;

    try {
      dHour = Integer.parseInt(dTime.substring(0, 2));
      dMinute = Integer.parseInt(dTime.substring(3));
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    c.set(Calendar.HOUR_OF_DAY, dHour);
    c.set(Calendar.MINUTE, dMinute);

    return c.getTime();
  }

  public static Date getOnlyDate(Date date) {
    Calendar c = Calendar.getInstance();
    c.clear();

    c.setTime(date);
    c.set(Calendar.HOUR_OF_DAY, 0);
    c.set(Calendar.MINUTE, 0);
    c.set(Calendar.SECOND, 0);
    c.set(Calendar.MILLISECOND, 0);

    return c.getTime();
  }


  public static boolean isGreaterThan0230() {
    Calendar c = Calendar.getInstance();
    return c.get(Calendar.HOUR_OF_DAY) >= 2 && c.get(Calendar.MINUTE) >= 30;
  }

  public static int getPastDays(Date thenDate) {
    return getPastDays(DateUtil.getCurDateTime(), thenDate);
  }

  public static int getPastDays(Date nowDate, Date thenDate) {
    long ms = nowDate.getTime() - thenDate.getTime();
    long seconds = ms / 1000;
    long minutes = seconds / 60;
    long hours = minutes / 60;

    return (int) (hours / 24);
  }

  public static long getPastHours(Date pastDate) {
    long seconds = getPastTime(pastDate) / 1000;

    return seconds / 3600;
  }

  public static int getPastMinutes(Date pastDate) {
    long seconds = getPastTime(pastDate) / 1000;

    return (int) seconds / 60;
  }

  public static int getPastSeconds(Date pastDate) {
    long seconds = getPastTime(pastDate) / 1000;

    return (int) seconds;
  }

  /**
   * 计算指定时刻距离当前的时间长短，单位为毫秒ms
   */
  public static long getPastTime(Date pastDate) {
    if (pastDate == null) {
      return 0;
    }

    return (getCurDateTime().getTime() - pastDate.getTime());
  }

  public static Date getDate(String dateString, String format) throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat(format);

    return sdf.parse(dateString);
  }

  public static int getDayOfWeek(Date aDate) {
    Calendar c = new GregorianCalendar();
    c.clear();
    c.setTime(aDate);

    return c.get(Calendar.DAY_OF_WEEK);

  }

  public static String getDayOfWeekDesc(Date aDate) {
    Calendar c = new GregorianCalendar();
    c.clear();
    c.setTime(aDate);

    int x = c.get(Calendar.DAY_OF_WEEK);

    switch (x) {
      case Calendar.SUNDAY:
        return "日";
      case Calendar.MONDAY:
        return "一";
      case Calendar.TUESDAY:
        return "二";
      case Calendar.WEDNESDAY:
        return "三";
      case Calendar.THURSDAY:
        return "四";
      case Calendar.FRIDAY:
        return "五";
      case Calendar.SATURDAY:
        return "六";
      default:
        return "";
    }

  }

  /**
   * 比较dateNow和dateThen的天数差, dateNow - dateThen
   */
  public static int getDaySpan(Date dateNow, Date dateThen) {
    long seconds = (dateNow.getTime() - dateThen.getTime()) / 1000;
    return (int) seconds / (60 * 60 * 24);
  }

  public static String getUpdateTimeDesc(Date updateTime) {
    long seconds = (getCurDateTime().getTime() - updateTime.getTime()) / 1000;
    int minutes = (int) seconds / 60;

    if (minutes < 0) {
      return "时间还没到呢";
    } else if (minutes < 5) {
      return "刚刚";
    } else if (minutes >= 60) {
      int hours = minutes / 60;
      return hours + "小时前";
    } else {
      return minutes + "分钟前";
    }
  }

  /**
   * 是否小于等于当前日期时间
   */
  public static boolean isLowerEqualDate(Date date) {
    long ms = date.getTime() - getCurDateTime().getTime();
    return ms <= 0;
  }

  /**
   * 是否大于等于当前时间
   */
  public static boolean isGreaterEqualOnlyTime(Date date) {
    long ms = date.getTime() - getCurTime().getTime();
    return ms >= 0;
  }

  /**
   * 是否小于等于当前时间
   */
  public static boolean isLowerEqualOnlyTime(Date date) {
    long ms = date.getTime() - getCurTime().getTime();
    return ms <= 0;
  }

  /**
   * 是否大于等于当前日期时间
   */
  public static boolean isGreaterEqualDate(Date date) {
    long ms = date.getTime() - getCurDateTime().getTime();
    return ms >= 0;
  }

  /*
   * yyyy-MM-dd HH:mm:ss
   */
  public static Date convertToDate(String val, String format) throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
    return sdf.parse(val);
  }

  public static Date convertToDate(String val) throws ParseException {
    return convertToDate(val, DATE_YYYY_MM_DD);
  }

  public static Date convertToDateTime(String val) throws ParseException {
    return convertToDate(val, "yyyy-MM-dd hh:mm:ss");
  }


  public static boolean isValidateData(String val) {
    try {
      convertToDate(val, "yyyy-MM-dd");
      return true;
    } catch (Exception ex) {
      return false;
    }
  }

  /**
   * 将eterm中的日期格致转化为yyyy-MM-dd格式
   *
   * @param etermDateStr 识别三种格式: 1. 11DEC18 ; 2. 11DEC(TUE) ; 3. 11DEC
   */
  public static String convertEtermDate(final String etermDateStr) {
    if (etermDateStr == null) {
      return "";
    }

    final String regexp0 = "[0-9]{2}[A-Z]{3}[0-9]{2}"; // 格式1
    final String regexp1 = "[0-9]{2}[A-Z]{3}[(][A-Z]{3}[)]"; // 格式2
    final String regexp2 = "[0-9]{2}[A-Z]{3}"; // 格式3

    // 先测试格式1
    Pattern p = Pattern.compile(regexp0);
    Matcher m = p.matcher(etermDateStr);
    if (m.find()) {
      // 格式1
      final String val = etermDateStr.substring(m.start(), m.end());

      String day = val.substring(0, 2);
      String month = convertMonthFormat(val.substring(2, 5));
      String year = "20" + val.substring(5);

      return year + "-" + month + "-" + day;
    }

    // 格式二
    p = Pattern.compile(regexp1);
    m = p.matcher(etermDateStr);
    if (m.find()) {
      final String val = etermDateStr.substring(m.start());

      return convertEtermDate(val.substring(0, 5), val.substring(6, 9));
    }

    // 格式三
    p = Pattern.compile(regexp2);
    m = p.matcher(etermDateStr);
    if (m.find()) {
      final String val = etermDateStr.substring(m.start(), m.end());

      String day = val.substring(0, 2);
      String month = convertMonthFormat(val.substring(2, 5));

      String year = LocalDate.now().getYear() + "";

      return year + "-" + month + "-" + day;
    }

    return "";
  }

  public static String convertEtermDate(final String val, final String dayOfWeek) {
    return convertEtermDate(val, dayOfWeek, null);
  }

  /**
   * 将三个字母的月份转换为2个数字表示的月份
   */
  private static String convertMonthFormatThree2Two(String month) {
    if (month.compareToIgnoreCase("JAN") == 0) {
      month = "01";
    } else if (month.compareToIgnoreCase("FEB") == 0) {
      month = "02";
    } else if (month.compareToIgnoreCase("MAR") == 0) {
      month = "03";
    } else if (month.compareToIgnoreCase("APR") == 0) {
      month = "04";
    } else if (month.compareToIgnoreCase("MAY") == 0) {
      month = "05";
    } else if (month.compareToIgnoreCase("JUN") == 0) {
      month = "06";
    } else if (month.compareToIgnoreCase("JUL") == 0) {
      month = "07";
    } else if (month.compareToIgnoreCase("AUG") == 0) {
      month = "08";
    } else if (month.compareToIgnoreCase("SEP") == 0) {
      month = "09";
    } else if (month.compareToIgnoreCase("OCT") == 0) {
      month = "10";
    } else if (month.compareToIgnoreCase("NOV") == 0) {
      month = "11";
    } else if (month.compareToIgnoreCase("DEC") == 0) {
      month = "12";
    } else {
      month = "00";
    }

    return month;
  }

  /**
   * 将eterm日期格式转换为yyyy-MM-dd格式
   *
   * @param val       ddMMM格式的日期
   * @param dayOfWeek 星期几
   * @param baseTime  基准时间
   */
  public static String convertEtermDate(final String val, String dayOfWeek, Date baseTime) {
    if (val == null || val.length() < 5) {
      return "";
    }

    final String day = val.substring(0, 2);
    final String month = convertMonthFormatThree2Two(val.substring(2, 5));


    if (val.length() == 7) {
      return "20" + val.substring(5) + "-" + month + "-" + day;
    }

    if (baseTime == null) {
      baseTime = getCurDateTime();
    }

    Calendar c = Calendar.getInstance();
    c.setTime(baseTime);

    final int curYear = c.get(Calendar.YEAR);
    final int curMonth = c.get(Calendar.MONTH);
    int calcYear = curYear;

    if (dayOfWeek != null) {
      if (dayOfWeek.length() == 2 || dayOfWeek.length() == 3) {
        for (int i = 1; i >= -2; i--) {
          calcYear = curYear + i;

          Date calcDate = null;
          try {
            calcDate = convertToDate(calcYear + "-" + month + "-" + day);
          } catch (ParseException e) {
            e.printStackTrace();
          }
          String calcDayOfWeek = DateUtil.formatDate(calcDate, "E", Locale.ENGLISH);
          calcDayOfWeek = calcDayOfWeek.toUpperCase();
          if (calcDayOfWeek.indexOf(dayOfWeek) == 0) {
            break;
          }
        }
      }
    }
    else {
      if (curMonth > Integer.parseInt(month)) {
        calcYear += 1;
      }
    }
    return calcYear + "-" + month + "-" + day;
  }


  public static Date addMinutes(Date date, int minutes) {
    Calendar c = Calendar.getInstance();
    c.clear();
    c.setTime(date);
    c.add(Calendar.MINUTE, minutes);
    return c.getTime();
  }

  public static Date addSeconds(Date date, int s) {
    Calendar c = Calendar.getInstance();
    c.clear();
    c.setTime(date);
    c.add(Calendar.SECOND, s);
    return c.getTime();
  }

  public static Date addMilliSeconds(Date date, int ms) {
    Calendar c = Calendar.getInstance();
    c.clear();
    c.setTime(date);
    c.add(Calendar.MILLISECOND, ms);
    return c.getTime();
  }

  public static Date getDateOnMinute(Date date) {
    Calendar c = Calendar.getInstance();
    c.clear();
    c.setTime(date);
    c.clear(Calendar.MILLISECOND);
    c.clear(Calendar.SECOND);
    return c.getTime();
  }

  public static Date getDateOnTheHour(Date date) {

    Calendar c = Calendar.getInstance();
    c.clear();
    c.setTime(date);
    c.clear(Calendar.MILLISECOND);
    c.clear(Calendar.SECOND);
    c.clear(Calendar.MINUTE);
    return c.getTime();
  }


  public static String getTomorrowStr() {
    Date tomorrow = DateUtil.addDays(DateUtil.getCurDate(), 1);
    return DateUtil.formatDate(tomorrow, "yyyy-MM-dd");
  }

  public static Date getTomorrowDate() {
    return DateUtil.addDays(DateUtil.getCurDate(), 1);
  }

  public static Date getYesterday() {
    return DateUtil.addDays(DateUtil.getCurDate(), -1);
  }

  public static XMLGregorianCalendar convertDateToXMLGregorianCalendar(Date date) {
    GregorianCalendar c = new GregorianCalendar();
    c.setTime(date);
    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH);
    int day = c.get(Calendar.DAY_OF_MONTH);

    XMLGregorianCalendar xt = null;
    try {
      xt = DatatypeFactory.newInstance().newXMLGregorianCalendar(year, month + 1, day, 0, 0, 0, 0, 0);
    } catch (DatatypeConfigurationException e) {
      e.printStackTrace();
    }

    return xt;
  }

  /**
   * 当前时间是否在工作时间范围内
   * @param workBeginTime 工作开始时间
   * @param workEndTime 工作结束时间
   * @return boolean 是否在工作时间内
   */
  public static boolean isInWorkTime(final String workBeginTime, final String workEndTime) {
    if (!CheckUtil.isValidTime(workBeginTime)
            || !CheckUtil.isValidTime(workEndTime)) {
      return false;
    }

    final int hour = getCurrentHour();
    final int minute = getCurrentMinuteOfHour();

    final int hour0 = Integer.parseInt(workBeginTime.substring(0, 2));
    final int minute0 = Integer.parseInt(workBeginTime.replace(":", "").substring(2, 4));
    final int hour1 = Integer.parseInt(workEndTime.substring(0, 2));
    final int minute1 = Integer.parseInt(workEndTime.replace(":", "").substring(2, 4));

    return hour >= hour0
            && (hour != hour0 || minute >= minute0)
            && (hour != hour1 || minute <= minute1)
            && hour <= hour1;
  }

  /**
   * 判断指定的一天是否在企业设置的工作日范围之内
   *
   * @param dayOfWeek 按照java取出的dayOfWeek计算, 星期日为1.
   */
  public static boolean isInEntWorkDay(final int dayOfWeek, final String entWorkDay) {
    int dayOfWeek0 = dayOfWeek - 1;
    if (dayOfWeek0 == 0) {
      dayOfWeek0 = 7;  //礼拜天
    }

    return entWorkDay.contains(dayOfWeek0 + "");
  }

  public static String convertMonthFormat(String abbrMonth) {
    String result = "";
    switch (abbrMonth) {
      case "JAN":
        result = "01";
        break;
      case "FEB":
        result = "02";
        break;
      case "MAR":
        result = "03";
        break;
      case "APR":
        result = "04";
        break;
      case "MAY":
        result = "05";
        break;
      case "JUN":
        result = "06";
        break;
      case "JUL":
        result = "07";
        break;
      case "AUG":
        result = "08";
        break;
      case "SEP":
        result = "09";
        break;
      case "OCT":
        result = "10";
        break;
      case "NOV":
        result = "11";
        break;
      default:
        result = "12";
    }

    return result;
  }

  /**
   * 把三个字母的星期几转为数字
   */
  public static int convertDayOfWeekFormat(String dayOfWeek) {
    int result = 0;
    switch (dayOfWeek) {
      case "SUN":
        result = 7;
        break;
      case "MON":
        result = 1;
        break;
      case "TUE":
        result = 2;
        break;
      case "WED":
        result = 3;
        break;
      case "THR":
        result = 4;
        break;
      case "FRI":
        result = 5;
        break;
      default:
        result = 6;
    }

    return result;
  }

  public static boolean containMonth(String content) {
    return content.contains("JAN") ||
            content.contains("FEB") ||
            content.contains("MAR") ||
            content.contains("APR") ||
            content.contains("MAY") ||
            content.contains("JUN") ||
            content.contains("JUL") ||
            content.contains("AUG") ||
            content.contains("SEP") ||
            content.contains("OCT") ||
            content.contains("NOV") ||
            content.contains("DEC");
  }

  public static Date timestampToDate(long subscribeTime) {
    return new Date(subscribeTime);
  }

  public static int calcAge(Date birthDay) {
    Calendar cal = Calendar.getInstance();

    if (cal.before(birthDay))
    {
      throw new IllegalArgumentException(
              "The birthDay is before Now.It's unbelievable!");
    }
    int yearNow = cal.get(Calendar.YEAR);
    int monthNow = cal.get(Calendar.MONTH);
    int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
    cal.setTime(birthDay);

    int yearBirth = cal.get(Calendar.YEAR);
    int monthBirth = cal.get(Calendar.MONTH);
    int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

    int age = yearNow - yearBirth;

    if (monthNow <= monthBirth)
    {
      if (monthNow == monthBirth)
      {
        if (dayOfMonthNow < dayOfMonthBirth)
          age--;
      }
      else
      {
        age--;
      }
    }
    return age;
  }

  /**
   * 获取指定日期中的年份
   */
  public static int getYear(Date d) {
    Calendar c = new GregorianCalendar();
    c.clear();
    c.setTime(d);

    return c.get(Calendar.YEAR);
  }

  /**
   * 获取指定日期中的月份
   */
  public static int getMonth(Date d) {
    Calendar c = new GregorianCalendar();
    c.clear();
    c.setTime(d);

    return c.get(Calendar.MONTH) + 1;
  }
}
