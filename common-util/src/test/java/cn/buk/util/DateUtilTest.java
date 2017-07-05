package cn.buk.util;

import org.junit.Ignore;
import org.junit.Test;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class DateUtilTest {

	@Test
	public void testDivide() {
		int x = 59;
		int y = x / 2;
		int z = x % 3;
		assertEquals(29, y);
		assertEquals(2, z);
	}

	@Test
	public void testCreateDate() {
		
		Date date1 = DateUtil.createDate(2013, 12, 30);
		
		Format sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String strTestDate1 = sdf1.format(date1.getTime());

		assertTrue("yyyy-MM-dd is expected!",strTestDate1.compareTo("2013-12-30")==0);
		

		date1 = DateUtil.createDate(2013, 2, 30);
		
		sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		strTestDate1 = sdf1.format(date1.getTime());
		
		String msg = "2013-02-30 is expected, but " + strTestDate1 + " is found!";

		assertTrue(msg, strTestDate1.compareTo("2013-03-02")==0);
	}

	@Test
	@Ignore
	public void testGetSomedayAfterToday() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddDays() {
		Date testedDate = DateUtil.createDate(2015, 12, 10);
		Date expectedDate = DateUtil.createDate(2015, 12, 15);		
		testedDate = DateUtil.addDays(testedDate, 5);
		assertTrue("addDays failed.", DateUtil.getDaySpan(testedDate, expectedDate) == 0);

		testedDate = DateUtil.addDays(testedDate, -5);
		testedDate = DateUtil.addDays(testedDate, 6);
		assertFalse("addDays failed.", DateUtil.getDaySpan(testedDate, expectedDate) == 0);
	}

	@Test
	@Ignore
	public void testConvertEtermDate_CurYear_ReturnOk() {
		String etermDate = "12AUG";
        String dayOfWeek = "TU";
        String correctDate = DateUtil.convertEtermDate(etermDate, dayOfWeek, null);

        assertTrue(correctDate.equalsIgnoreCase("2014-08-12"));
	}
    @Test

    public void testConvertEtermDate_NextYear_ReturnOk() {
        String etermDate = "12AUG";
        String dayOfWeek = "WE";
        String correctDate = DateUtil.convertEtermDate(etermDate, dayOfWeek, null);

        assertTrue(correctDate.equalsIgnoreCase("2017-08-12"));
    }

    @Test
	public void testGetPastHours() {
		//2014-08-21T12:00:00 ->Thu Aug 21 00:00:00 CST 2014
        String originalString = "2014-08-21T15:00:00";
        try {
            Date date = DateUtil.convertToDate(originalString, "yyyy-MM-dd'T'HH:mm:ss");
            System.out.println(date);
            String newSting = DateUtil.formatDate(date, "yyyy-MM-dd'T'HH:mm:ss");
            String newSting2 = DateUtil.formatDate(date, "yyyy-MM-dd HH:mm:ss");
            System.out.println(date);
            //System.out.println(originalString);
            System.out.println(newSting);
            System.out.println(newSting2);
            System.out.print(DateUtil.getCurDateTime());
//            System.out.println()
            assertTrue(originalString.equalsIgnoreCase(newSting));
        } catch (ParseException e) {

        }
    }

	@Test
	@Ignore
	public void testGetPastMinutes() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testGetDayOfWeek() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDayOfWeekDesc() {
		Date newDate = DateUtil.createDate(2016, 7, 15);
		int dayOfWeek = DateUtil.getDayOfWeek(newDate);
		assertEquals(Calendar.FRIDAY, dayOfWeek);
	}

	@Test
	public void testGetDaySpan() {
		Date currentDate = DateUtil.createDate(2015, 10, 28);
		Date comparedDate = DateUtil.createDate(2015, 10, 10);		
		assertTrue("getDaySpan failed", DateUtil.getDaySpan(currentDate, comparedDate) == 18);
		
		Date comparedDate2 = DateUtil.createDate(2015, 11, 10);
		assertTrue("getDaySpan failed", DateUtil.getDaySpan(currentDate, comparedDate2) == -13);

		int hourOfDay = DateUtil.getCurrentHour();
		System.out.println(hourOfDay);

		int minuteOfHour = DateUtil.getCurrentMinuteOfHour();
		System.out.println(minuteOfHour);
	}

	@Test
	public void testStringFormat() {
		//final String key = "flight:search:%s-%s-%ty%tM%td";
//		String val = String.format(key, "SHA", "KHN", DateUtil.getCurDate());
//		System.out.println(val);
//
//		Date date=new Date();                               // 创建日期对象
//		System.out.printf("2位数字24时制的小时(不足2位前面补0):%tH%n",date);
//		System.out.printf("2位数字12时制的小时(不足2位前面补0):%tI%n",date);
//		System.out.printf("2位数字24时制的小时(前面不补0):%tk%n",date);
//		System.out.printf("2位数字12时制的小时(前面不补0):%tl%n",date);
//		System.out.printf("2位数字的分钟(不足2位前面补0):%tM%n",date);
//		System.out.printf("2位数字的秒(不足2位前面补0):%tS%n",date);
//		System.out.printf("3位数字的毫秒(不足3位前面补0):%tL%n",date);
//		System.out.printf("9位数字的毫秒数(不足9位前面补0):%tN%n",date);
//		String str=String.format(Locale.US,"小写字母的上午或下午标记(英)：%tp",date);
//		System.out.println(str);                          // 输出字符串变量str的内容
//		System.out.printf ("小写字母的上午或下午标记(中)：%tp%n",date);
//		System.out.printf("相对于GMT的RFC822时区的偏移量:%tz%n",date);
//		System.out.printf("时区缩写字符串:%tZ%n",date);
//		System.out.printf("1970-1-1 00:00:00 到现在所经过的秒数：%ts%n",date);
//		System.out.printf("1970-1-1 00:00:00 到现在所经过的毫秒数：%tQ%n",date);
	}

	@Test
	public void testCreateDateTime() throws Exception {

	}

	@Test
	public void testSetTimeOnDate() throws Exception {

	}

	@Test
	public void testGetCurDateTime() throws Exception {

	}

	@Test
	public void testGetCurTime() throws Exception {

	}

	@Test
	public void testGetCurrentHour() throws Exception {

	}

	@Test
	public void testGetCurrentMinuteOfHour() throws Exception {

	}

	@Test
	public void testGetCurDateTimeString() throws Exception {

	}

	@Test
	public void testGetCurDateTimeString1() throws Exception {

	}

	@Test
	public void testFormatDate() throws Exception {

	}

	@Test
	public void testFormatDate1() throws Exception {

	}

	@Test
	public void testGetCurDate() throws Exception {

	}

	@Test
	public void testAdd() throws Exception {

	}

	@Test
	public void testAddMonth() throws Exception {

	}

	@Test
	public void testGetFullDDate() throws Exception {

	}

	@Test
	public void testGetOnlyDate() throws Exception {

	}

	@Test
	public void testIsGreaterThan0230() throws Exception {

	}

	@Test
	public void testGetPastDays() throws Exception {

	}

	@Test
	public void testGetPastDays1() throws Exception {

	}

	@Test
	public void testGetPastTime() throws Exception {

	}

	@Test
	public void testGetDate() throws Exception {

	}

	@Test
	public void testGetDaysSpan() throws Exception {

	}

	@Test
	public void testGetUpdateTimeDesc() throws Exception {

	}

	@Test
	public void testIsLowerEqualDate() throws Exception {

	}

	@Test
	public void testIsGreaterEqualOnlyTime() throws Exception {

	}

	@Test
	public void testIsLowerEqualOnlyTime() throws Exception {

	}

	@Test
	public void testIsGreaterEqualDate() throws Exception {

	}

	@Test
	public void testConvertToDate() throws Exception {
		String val = "160320";
		Date adate = DateUtil.convertToDate(val, "yyMMdd");
		System.out.println(DateUtil.formatDate(adate, "yyyy-MM-dd"));
	}

	@Test
	public void testConvertToDate1() throws Exception {

	}

	@Test
	public void testConvertToDateTime() throws Exception {

	}

	@Test
	public void testFormateDate() throws Exception {

	}

	@Test
	public void testIsValidateData() throws Exception {

	}

	@Test
	public void testConvertEtermDate() throws Exception {

	}

	@Test
	public void testAddMinutes() throws Exception {

	}

	@Test
	public void testGetDateOnMinute() throws Exception {

	}

	@Test
	public void testGetDateOnTheHour() throws Exception {

	}

	@Test
	public void testGetTomorrowStr() throws Exception {

	}

	@Test
	public void testGetTomorrowDate() throws Exception {

	}

	@Test
	public void testConvertDateToXMLGregorianCalendar() throws Exception {

	}

	@Test
	public void testIsInWorkTime() throws Exception {
		String beginTime = "0900";
		String endTime = "1700";
		System.out.println(beginTime + "-" + endTime + ": " + DateUtil.isInWorkTime(beginTime, endTime));

		beginTime = "0900";
		endTime = "2155";
		System.out.println(beginTime + "-" + endTime + ": " + DateUtil.isInWorkTime(beginTime, endTime));

		beginTime = "0900";
		endTime = "2230";
		System.out.println(beginTime + "-" + endTime + ": " + DateUtil.isInWorkTime(beginTime, endTime));

		beginTime = "2205";
		endTime = "2230";
		System.out.println(beginTime + "-" + endTime + ": " + DateUtil.isInWorkTime(beginTime, endTime));
	}
}
