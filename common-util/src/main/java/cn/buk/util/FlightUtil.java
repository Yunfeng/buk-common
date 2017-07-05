package cn.buk.util;

import java.text.DecimalFormat;

/**
 * Created with IntelliJ IDEA.
 * User: yfdai
 * Date: 13-8-2
 * Time: 上午10:38
 * To change this template use File | Settings | File Templates.
 */
public class FlightUtil {
    public static String getCityCode(String airportCode) {
        if (airportCode.equalsIgnoreCase("NAY") || airportCode.equalsIgnoreCase("PEK")) {
            return "BJS";
        }
        else if (airportCode.equalsIgnoreCase("PVG") || airportCode.equalsIgnoreCase("SHA")) {
            return "SHA";
        }
        else {
            return airportCode;
        }
    }

    public static String getOffsetDesc(int offset) {
        if (offset == 100) return "全价";
        else if (offset < 10) return "";
        else {
            return new DecimalFormat("0.0").format(offset / 10) + "折";
        }
    }

    public static String getCabinClassDesc(String cabinClass) {
        if (cabinClass == null) return "";
        else if (cabinClass.trim().equalsIgnoreCase("Y")) return "经济舱";
		else if (cabinClass.trim().equalsIgnoreCase("F")) return "头等舱";
		else if (cabinClass.trim().equalsIgnoreCase("C")) return "公务舱";
		else return "";
    }

    public static String formatShowTime(String time) {
        String tempDepTime;
        if (time != null) {
            tempDepTime = time.trim();
        } else {
            return "";
        }

        if (tempDepTime.length() == 4) {
            return tempDepTime.substring(0, 2) + ":" + tempDepTime.substring(2, 4);
        } else {
            return tempDepTime;
        }
    }

    /**
     * 检查Y的子舱位的状态是否正常: 如果子仓位的状态全部为A或者最后2个仓位为A则认为不正常
     * @param subclassDesc
     * @return
     */
    public static boolean checkSubclassNormal(String subclassDesc) {

        int statusACount = 0, statusOtCount = 0, lastTwoStatusACount = 0;

        String[] subclsses = subclassDesc.split(" ");
        int subclassLeftCount = subclsses.length;
        for (String subclass : subclsses) {
            subclassLeftCount--;
            subclass = subclass.trim();
            if (subclass.length() != 2) continue;

            String seatStatus = subclass.substring(1);

            if (seatStatus.equalsIgnoreCase("A")) {
                statusACount++;

                if (subclassLeftCount <= 1) lastTwoStatusACount++;
            } else {
                statusOtCount++;
            }
        }

        return statusOtCount >= 2 && lastTwoStatusACount != 2;
    }

    public static String verifyAirport(String port0) {
        return verifyAirport(port0, null);
    }

    public static String verifyAirport(String port0, String airline) {
        if (port0.equalsIgnoreCase("PVG")) {
            return "SHA";
        } else if (port0.equalsIgnoreCase("NAY")) {
            if (airline != null && airline.equalsIgnoreCase("KN")) {
                return "NAY";
            } else {
                return "PEK";
            }
        }  else {
            return port0.toUpperCase();
        }
    }

    public static String verifyCityCode(String code0) {
        if (code0.equalsIgnoreCase("PVG")) return "SHA";
        else if (code0.equalsIgnoreCase("NAY")) return "BJS";
        else if (code0.equalsIgnoreCase("PEK")) return "BJS";
        else return code0.toUpperCase();
    }

}
