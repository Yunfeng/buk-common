package cn.buk.common.dto.eterm;

import cn.buk.common.util.DateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author yfdai
 *  2017/3/26
 */
public class TprInfoDto {
    private String cmdResult;

    private String officeNo;
    private String sellDate;
    private String iata;
    private String deviceId;

    /**
     * TPR指令中最下方显示的TOTAL TICKET中的数字，用于校验用
     */
    private int totalTicket;

    private List<TprItemDto> items;

    public void setCmdResult(final String cmdResult) {
        this.cmdResult = cmdResult;

        //office
        String regEx = "[A-Z]{3}[0-9]{3}";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(cmdResult);
        if (m.find()) {
            int start = m.start();
            this.officeNo = cmdResult.substring(start, start + 6);
        }

        //date
        regEx = "DATE   : [0-9]{2}[A-Z]{3}[0-9]{0,2}";
        p = Pattern.compile(regEx);
        m = p.matcher(cmdResult);
        if (m.find()) {
            int start = m.start();
            int end = m.end();

            String printDate = cmdResult.substring(start + 9, end);

            this.sellDate = DateUtil.convertEtermDate(printDate);
        }

        //iata
        regEx = " [0-9]{8} ";
        p = Pattern.compile(regEx);
        m = p.matcher(cmdResult);
        if (m.find()) {
            int start = m.start();
            int end = m.end();
            this.iata = cmdResult.substring(start, end);
        }

        //device id
        //DEVICE : 40
        regEx = "DEVICE : [0-9]{1,2}";
        p = Pattern.compile(regEx);
        m = p.matcher(cmdResult);
        if (m.find()) {
            int start = m.start();
            int end = m.end();
            this.deviceId = cmdResult.substring(start+8, end).trim();
        }

        //total ticket
        regEx = "TOTAL TICKETS:? *[0-9]+";
        p = Pattern.compile(regEx);
        m = p.matcher(cmdResult);
        if (m.find()) {
            int start = m.start();
            int end = m.end();
            String line = cmdResult.substring(start, end);
            line = line.substring(14).trim();
            this.totalTicket = Integer.parseInt(line);
        }

        //ticket
        regEx = "[0-9]{3}-[0-9]{10}";
        p = Pattern.compile(regEx);
        m = p.matcher(cmdResult);
        while (m.find()) {
            int start = m.start();
            String line = cmdResult.substring(start, start + 71);
            TprItemDto item = new TprItemDto();
            this.getItems().add(item);
            item.setDetail(line);
        }
    }

    public String getCmdResult() {
        return cmdResult;
    }

    public int getTicketCount() {
        return this.getItems().size();
    }

    public String getOfficeNo() {
        return officeNo;
    }

    public void setOfficeNo(String officeNo) {
        this.officeNo = officeNo;
    }

    public List<TprItemDto> getItems() {
        if (items == null) {
          items = new ArrayList<>();
        }
        return items;
    }

    public void setItems(List<TprItemDto> items) {
        this.items = items;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getSellDate() {
        return sellDate;
    }

    public void setSellDate(String sellDate) {
        this.sellDate = sellDate;
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public int getTotalTicket() {
        return totalTicket;
    }
}
