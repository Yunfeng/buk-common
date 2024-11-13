package cn.buk.util;

import cn.buk.common.util.DateUtil;
import org.junit.jupiter.api.Test;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import static cn.buk.common.util.DateUtil.convertEtermDate;
import static org.junit.jupiter.api.Assertions.*;

public class DateUtilTest {

	@Test
	public void getPastHours() throws Exception {
		Date now = DateUtil.getCurDateTime();
		Date then = DateUtil.addDays(now, -50);

		System.out.println(then);
		long hours = DateUtil.getPastHours(then);

		assertEquals(1200, hours);
	}


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

		LocalDate localDate = LocalDate.now().plusMonths(1);
		Date date0 = DateUtil.createDate(localDate);
		System.out.println(DateUtil.formatDate(date0, "yyyy-MM-dd"));
		assertEquals(localDate.toString(), DateUtil.formatDate(date0, "yyyy-MM-dd"));

		Date date1 = DateUtil.createDate(2013, 12, 30);
		
		Format sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String strTestDate1 = sdf1.format(date1.getTime());

		assertTrue(strTestDate1.compareTo("2013-12-30")==0, "yyyy-MM-dd is expected!");
		

		date1 = DateUtil.createDate(2013, 2, 30);
		
		sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		strTestDate1 = sdf1.format(date1.getTime());
		
		String msg = "2013-02-30 is expected, but " + strTestDate1 + " is found!";

		assertTrue(strTestDate1.compareTo("2013-03-02")==0, msg);
	}

	@Test
	public void testAddDays() {
		Date testedDate = DateUtil.createDate(2015, 12, 10);
		Date expectedDate = DateUtil.createDate(2015, 12, 15);		
		testedDate = DateUtil.addDays(testedDate, 5);
		assertTrue(DateUtil.getDaySpan(testedDate, expectedDate) == 0, "addDays failed.");

		testedDate = DateUtil.addDays(testedDate, -5);
		testedDate = DateUtil.addDays(testedDate, 6);
		assertFalse(DateUtil.getDaySpan(testedDate, expectedDate) == 0, "addDays failed.");
	}

    @Test
    public void testConvertEtermDate_NextYear_ReturnOk() {
        String etermDate = "12AUG";
        String dayOfWeek = "WE";
        String correctDate = convertEtermDate(etermDate, dayOfWeek, null);
//        System.out.println(correctDate);

        assertTrue(correctDate.equalsIgnoreCase("2020-08-12"));
    }

	@Test
	public void testConvertEtermDate_PastYear_ReturnOk() {
		String etermDate = "27DEC";
		String dayOfWeek = "THU";
		String correctDate = convertEtermDate(etermDate, dayOfWeek, null);

		assertFalse(correctDate.equalsIgnoreCase("2018-12-27"));
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
					e.printStackTrace();
				}
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
		assertTrue(DateUtil.getDaySpan(currentDate, comparedDate) == 18, "getDaySpan failed");
		
		Date comparedDate2 = DateUtil.createDate(2015, 11, 10);
		assertTrue(DateUtil.getDaySpan(currentDate, comparedDate2) == -13, "getDaySpan failed");

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
	public void testConvertToDate() throws Exception {
		String val = "160320";
		Date adate = DateUtil.convertToDate(val, "yyMMdd");
		System.out.println(DateUtil.formatDate(adate, "yyyy-MM-dd"));
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

	@Test
	public void test_convertEtermDate() {
		assertEquals("2019-12-11", DateUtil.convertEtermDate("11DEC(MON)"));
		assertEquals("2019-12-11", DateUtil.convertEtermDate("11DEC(TUE)"));
		assertEquals("2019-12-11", DateUtil.convertEtermDate("11DEC(WED)"));
	}

	@Test
	public void test_localDate() {
		LocalDateTime localDateTime = LocalDateTime.now();

		System.out.println(localDateTime.toString());

		System.out.println(localDateTime.getYear());
		System.out.println(localDateTime.getMonth().getValue());
		System.out.println(localDateTime.getDayOfMonth());
		System.out.println(localDateTime.toString());

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

		System.out.println(localDateTime.format(formatter));

		System.out.println(localDateTime.getSecond());


		System.out.println(Instant.now().getEpochSecond());
		System.out.println(DateUtil.getCurDateTime().getTime());
	}

	@Test
	public void testCalcAge() {
		final Date birthday = DateUtil.createDate(1975, 1, 23);
		System.out.println(DateUtil.calcAge(birthday));
	}

	@Test
	public void testGetBirthday() throws ParseException {
		final String idNo = "31010819890903054X";
		Date bd = DateUtil.convertToDate(idNo.substring(6, 14), "yyyyMMdd");
		System.out.println(DateUtil.formatDate(bd, "yyyy-MM-dd"));
	}
}
