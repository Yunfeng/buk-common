package cn.buk.common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yfdai
 */
public class FlightTicketUtil {

    private FlightTicketUtil() {
        throw new IllegalArgumentException("Utilities class.");
    }

    /**
     * 校验单张机票票号的正确性
     * 校验票号是否为14位或13位
     */
    public static boolean verifyTicketNo(String ticketNo) {
        final String regEx = "[0-9]{3}-?[0-9]{10}";

        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(ticketNo);

        return m.matches();
    }

    /**
     * 校验联程机票
     */
    public static boolean verifyConnectionTicketNo(String ticketNo) {
        final String regEx = "[0-9]{3}-?[0-9]{10}-[0-9]{1,2}";

        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(ticketNo);

        return m.matches();
    }

    /**
     * 处理票号：将单张票号或联程票号的字符串分析处理，并返回票号列表
     * @param ticketNo 票号的字符串表达式
     * @return 按顺序包含每张票号的列表, 票号格式为：781-01234567890
     */
    public static List<String> processTicketNo(final String ticketNo) {

        List<String> tickets = new ArrayList<>();

        if (!verifyTicketNo(ticketNo) && !verifyConnectionTicketNo(ticketNo)) {
            //不是单张票，也不是联程客票的票号
            return tickets;
        }

        if (verifyTicketNo(ticketNo)) {
            if (ticketNo.length() == 13) {
                tickets.add(ticketNo.substring(0, 3) + "-" + ticketNo.substring(3));
            } else {
                tickets.add(ticketNo);
            }
        } else {
            // 联程票号
            final String balCode = ticketNo.substring(0, 3);
            String ticketNo1 = ticketNo.substring(3);

            if ("-".equalsIgnoreCase(ticketNo1.substring(0, 1))) {
                ticketNo1 = ticketNo1.substring(1);
            }

            final String ticket1 = ticketNo1.substring(0, 10);
            tickets.add(balCode + "-" + ticket1);


            final String ticketNo2 = ticketNo1.substring(11);

            //联程票号理论上不会超过10个票号连续
            for(int i = 1; i < 10; i++) {
                long ticketNum = Long.parseLong(ticket1) + i;
                String ticket2 = String.format("%010d", ticketNum);

                tickets.add(balCode + "-" + ticket2);
                if (ticketNo2.equalsIgnoreCase(ticket2.substring(10 - ticketNo2.length()))) {
                    break;
                }
            }
        }

        return tickets;
    }

    public static List<String> processTicketNo(final String firstTicketNo, final int ticketCount) {
        List<String> tickets = new ArrayList<>();

        if (!verifyTicketNo(firstTicketNo)) {
            //不是单张票号
            return tickets;
        }

        final String balCode = firstTicketNo.substring(0, 3);
        String ticket1;
        if (firstTicketNo.length() == 13) {
            tickets.add(balCode + "-" + firstTicketNo.substring(3));
            ticket1 = firstTicketNo.substring(3);
        } else {
            tickets.add(firstTicketNo);
            ticket1 = firstTicketNo.substring(4);
        }

        for (int i = 1; i < ticketCount; i++) {
            long ticketNum = Long.parseLong(ticket1) + i;
            String ticket2 = String.format("%010d", ticketNum);

            tickets.add(balCode + "-" + ticket2);
        }


        return tickets;
    }

    /**
     * 将多张联程票号处理为联程票号格式
     */
    public static String processShowTicketNo(final String ticketNo, final int ticketCount, final String ticketNoEnd) {
        if (ticketCount > 1) {
            final String temp = StringUtils.trimToEmpty(ticketNoEnd);
            return ticketNo + (temp.length() == 10 ? "-" + temp.substring(8) : "");
        }
        return ticketNo;
    }
}
