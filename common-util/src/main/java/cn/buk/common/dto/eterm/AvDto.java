package cn.buk.common.dto.eterm;

import cn.buk.common.flight.dto.FlightInfoDto;
import cn.buk.common.util.DateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * AV:H/SHAPEK/+/D 指令格式的反馈结果
 * @author yfdai
 */
public class AvDto {

    private static final Logger logger = LogManager.getLogger(AvDto.class);

    /**
     * 错误代码
     * 0-无错误
     * 3-NO ROUTING
     * 5-PLEASE WAIT - TRANSACTION IN PROGRESS
     */
    private int errorCode;

    private String dcity;

    private String acity;

    private Date ddate;

    private String avResult;

    /**
     * 航班信息
     */
    private List<FlightInfoDto> flights;

    private int flightCount;

    private void processAvResult(final String avResult) {
        if (avResult.contains("NO ROUTING")) {
            this.errorCode = 3;
            return;
        }

        if (avResult.contains("PLEASE WAIT - TRANSACTION IN PROGRESS")) {
            this.errorCode = 5;
            return;
        }


        String[] strLines = avResult.replace("\r\n", "\r").replace("\n", "\r").split("\r");

        //取第一行的出发日期和城市对
        //" 27DEC(THU) SHACAN DIRECT ONLY" +
        String firstLine = strLines[0];
        //出发日期
        final String regEx0="[0-9]{2}[A-Z]{3}[0-9]{0,2}[(][A-Z]{3}[)]";
        final Pattern p0 = Pattern.compile(regEx0);
        Matcher m0 = p0.matcher(firstLine);
        if (m0.find()) {
            final String temp1 = firstLine.substring(m0.start(), m0.end());

            try {
                final int index = temp1.indexOf("(");
                String temp0 = DateUtil.convertEtermDate(temp1.substring(0, index), temp1.substring(index+1, index+4), DateUtil.getCurDate());
                this.ddate = DateUtil.convertToDate(temp0, "yyyy-MM-dd");
            } catch (ParseException ex) {
                logger.error(ex.getMessage());
            }

            final String temp3 = firstLine.substring(m0.end()).trim();
            this.dcity = temp3.substring(0, 3);
            this.acity = temp3.substring(3, 6);
        }

        //取航班信息
        //匹配行首的4个字符
        final String regEx="^[1-8][-+ ]? [ |*]?";
        final Pattern p = Pattern.compile(regEx);
        for(int i = 1; i < strLines.length; i++) {
            final String strLine = strLines[i];

            Matcher m = p.matcher(strLine);

            if (m.find()) {
                FlightInfoDto segmentInfo = new FlightInfoDto();
                segmentInfo.setDdate(this.ddate);
                getFlights().add(segmentInfo);

                final String line1 = strLine.length() <= 80 ? strLine : strLine.substring(0, 80);
                final String line2 = strLine.length() <= 80 ? strLines[i + 1] : strLine.substring(80);


                //          1         2         3         4         5         6         7
                //01234567890123456789012345678901234567890123456789012345678901234567890123456789
                //1- *MU9327  DS# UQ FA PA JA C6 D2 QS IS WC YA  SHACAN 1730   2005   333 0^D  E
                //>   FM9327      BA MQ EQ HQ KQ LQ NQ RQ SQ VQ TQ GQ ZQ              T2 T1  2:35
                //6   8L9890  DS#                                PVGKMG 2025   0015+1 32M 0^   E
                //>                                                                   T2 --  3:50


                String shareFlight = line1.substring(3, 4).trim();
                String flightNo = line1.substring(4, 11).trim();
                segmentInfo.setFlightNo(flightNo);
                String subClasses = line1.substring(16, 45).trim();
                String dport = line1.substring(47, 50);
                segmentInfo.setDport(dport);
                String aport = line1.substring(50, 53);
                segmentInfo.setAport(aport);
                String dtime = line1.substring(54, 58).trim();
                segmentInfo.setDtime(dtime);
                String atime = line1.substring(61, 65).trim();
                segmentInfo.setAtime(atime);
                //TODO 到达日期的+1处理
                String addDays = line1.substring(65, 67).trim();
                if (addDays.contains("+")) {
                    addDays = addDays.replace("+", "");
                    segmentInfo.setAddedDays(Integer.parseInt(addDays));
                }

                String aircraft = line1.substring(68, 71).trim();
                segmentInfo.setAircraft(aircraft);
                String stopover = line1.substring(72, 73).trim();
                segmentInfo.setStopover(Integer.parseInt(stopover));
                // ^ 有机上预留座位
                // D 有餐食 D-Dinner
                String meal = line1.substring(74, 75).trim();
                segmentInfo.setMeal(meal);

                String opFlightNo = line2.substring(4, 11).trim();
                segmentInfo.setOpFlightNo(opFlightNo);
                subClasses += " " + line2.substring(16, 60).trim();
                String dterm = line2.substring(68, 70).trim().replace("-", "");
                segmentInfo.setDterm(dterm);
                String aterm = line2.substring(71, 73).trim().replace("-", "");
                segmentInfo.setAterm(aterm);
                String flyTime = line2.substring(74).trim();
                segmentInfo.setFlyTime(flyTime);

                segmentInfo.setSubClassDesc(subClasses.trim());

                if ("*".equalsIgnoreCase(shareFlight)) {
                    segmentInfo.setCodeShare(1);
                }

            }
        }

        this.flightCount = getFlights().size();
    }

    public String getAvResult() {
        return avResult;
    }

    public void setAvResult(String avResult3) {
        this.avResult = avResult3;
        try {
            processAvResult(avResult);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            logger.info(avResult3.replace("\r", "\r\n"));
            ex.printStackTrace();
        }
    }

    public List<FlightInfoDto> getFlights() {
        if (flights == null) {
            flights = new ArrayList<>();
        }
        return flights;
    }

    public void setFlights(List<FlightInfoDto> flights) {
        this.flights = flights;
    }

    public int getFlightCount() {
        return flightCount == 0 ? this.getFlights().size() : flightCount;
    }

    public String getAcity() {
        return acity;
    }

    public void setAcity(String acity) {
        this.acity = acity;
    }

    public String getDcity() {
        return dcity;
    }

    public void setDcity(String dcity) {
        this.dcity = dcity;
    }

    public Date getDdate() {
        return ddate;
    }

    public void setDdate(Date ddate) {
        this.ddate = ddate;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
