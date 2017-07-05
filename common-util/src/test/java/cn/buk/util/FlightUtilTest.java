package cn.buk.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by gezhi on 16/4/29.
 */
public class FlightUtilTest {
    @Test
    public void testCheckSubclassNormal_allIsA_ReturnFalse() throws Exception {
        final String subclassDesc = "YA BA LA MA TA EA HA V8 KA WA RA QA ZA PA XA GA SA IA NA";
        boolean checkResult = FlightUtil.checkSubclassNormal(subclassDesc);
        assertFalse("should be false, but is " + checkResult, checkResult);
    }

    @Test
    public void testCheckSubclassNormal_lastTwoIsNotA_ReturnTrue() throws Exception {
        final String subclassDesc = "YA BA LA MA TA EA HA VA KA WA RA QA ZA PS XS GA SA IS NS";
        boolean checkResult = FlightUtil.checkSubclassNormal(subclassDesc);
        assertTrue("should be true, but is " + checkResult, checkResult);
    }

    @Test
    public void testCheckSubclassNormal_lastTwoIsA_ReturnFalse() throws Exception {
        final String subclassDesc = "YA BA LA MA TA EA HA V8 K8 W8 R8 Q8 Z8 PA XA GA SA IA NA";
        boolean checkResult = FlightUtil.checkSubclassNormal(subclassDesc);
        assertFalse("should be false, but is " + checkResult, checkResult);
    }

}