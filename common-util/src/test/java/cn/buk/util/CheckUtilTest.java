package cn.buk.util;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Author: yfdai@buk.cn
 * Url: http://www.buk.cn
 * License: LGPL
 */
public class CheckUtilTest {

    @Test
    public void testIsMobileNo_RightMobileNo_ReturnTrue() {
        String mobileNo = "10088888888";
        assertTrue("mobileNo is correct.", CheckUtil.isMobileNo(mobileNo));
        mobileNo = "11088888888";
        assertTrue("mobileNo is correct.", CheckUtil.isMobileNo(mobileNo));
        mobileNo = "12088888888";
        assertTrue("mobileNo is correct.", CheckUtil.isMobileNo(mobileNo));
        mobileNo = "13088888888";
        assertTrue("mobileNo is correct.", CheckUtil.isMobileNo(mobileNo));
        mobileNo = "14088888888";
        assertTrue("mobileNo is correct.", CheckUtil.isMobileNo(mobileNo));
        mobileNo = "15088888888";
        assertTrue("mobileNo is correct.", CheckUtil.isMobileNo(mobileNo));
        mobileNo = "16088888888";
        assertTrue("mobileNo is correct.", CheckUtil.isMobileNo(mobileNo));
        mobileNo = "17088888888";
        assertTrue("mobileNo is correct.", CheckUtil.isMobileNo(mobileNo));
        mobileNo = "18088888888";
        assertTrue("mobileNo is correct.", CheckUtil.isMobileNo(mobileNo));
        mobileNo = "19088888888";
        assertTrue("mobileNo is correct.", CheckUtil.isMobileNo(mobileNo));
    }

    @Test
    public void testIsMobileNo_MobileNoLengthNotEleven_ReturnFalse() {
        String mobileNo = "130617388766";

        assertFalse("mobileNo's length is not correct.", CheckUtil.isMobileNo(mobileNo));
    }

    @Test
    public void testIsMobileNo_MobileNoLengthShort_ReturnFalse() {
        String mobileNo = "1306173837";

        assertFalse("mobileNo's length is not correct.", CheckUtil.isMobileNo(mobileNo));
    }

    @Test
    public void testIsMobileNo_MobileNoIncludeIllegelChar_ReturnFalse() {
        String mobileNo = "130a1738b76";

        assertFalse("mobileNo include illegal character.", CheckUtil.isMobileNo(mobileNo));
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
