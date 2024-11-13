package cn.buk.util;

import cn.buk.common.util.CheckUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Author: yfdai@buk.cn
 * Url: http://www.buk.cn
 * License: LGPL
 */
public class CheckUtilTest {

    @Test
    public void testIsMobileNo_RightMobileNo_ReturnTrue() {
        String mobileNo = "10088888888";
        assertTrue(CheckUtil.isMobileNo(mobileNo));
        mobileNo = "11088888888";
        assertTrue(CheckUtil.isMobileNo(mobileNo));
        mobileNo = "12088888888";
        assertTrue(CheckUtil.isMobileNo(mobileNo));
        mobileNo = "13088888888";
        assertTrue(CheckUtil.isMobileNo(mobileNo));
        mobileNo = "14088888888";
        assertTrue(CheckUtil.isMobileNo(mobileNo));
        mobileNo = "15088888888";
        assertTrue(CheckUtil.isMobileNo(mobileNo));
        mobileNo = "16088888888";
        assertTrue(CheckUtil.isMobileNo(mobileNo));
        mobileNo = "17088888888";
        assertTrue(CheckUtil.isMobileNo(mobileNo));
        mobileNo = "18088888888";
        assertTrue(CheckUtil.isMobileNo(mobileNo));
        mobileNo = "19088888888";
        assertTrue(CheckUtil.isMobileNo(mobileNo));
    }

    @Test
    public void testIsMobileNo_MobileNoLengthNotEleven_ReturnFalse() {
        String mobileNo = "130617388766";

        assertFalse(CheckUtil.isMobileNo(mobileNo));
    }

    @Test
    public void testIsMobileNo_MobileNoLengthShort_ReturnFalse() {
        String mobileNo = "1306173837";

        assertFalse(CheckUtil.isMobileNo(mobileNo));
    }

    @Test
    public void testIsMobileNo_MobileNoIncludeIllegelChar_ReturnFalse() {
        String mobileNo = "130a1738b76";

        assertFalse(CheckUtil.isMobileNo(mobileNo));
    }

    @Test
    public void testDateFormat_ReturnTrue() {
        String temp = "2017-12-31";

        assertTrue(CheckUtil.isDate(temp));
    }

    @Test
    public void testDateFormat_ReturnFalse() {
        String temp = "2017-12-AA";

        assertFalse(CheckUtil.isDate(temp));
    }

    @Test
    void test_isValidTime_return_true() {
        String temp = "0000";
        assertTrue(CheckUtil.isValidTime(temp));

        temp = "00:00";
        assertTrue(CheckUtil.isValidTime(temp));

        temp = "2359";
        assertTrue(CheckUtil.isValidTime(temp));

        temp = "23:59";
        assertTrue(CheckUtil.isValidTime(temp));

        temp = "0930";
        assertTrue(CheckUtil.isValidTime(temp));

        temp = "1930";
        assertTrue(CheckUtil.isValidTime(temp));


        temp = "33:59";
        assertFalse(CheckUtil.isValidTime(temp));
    }

    @Test
    public void testStringFormat() {
        double x = 1020.71;
        String val = String.format("%9.2f", x);
        System.out.println(val);
    }

    @Test
    public void testCommonOperation() {
        int i = 1;
        float j = 100.00f;
        float x = i / j;
        float y = i % j;
        System.out.println("x: " + x + " , y: " + y);
    }
}
