package cn.buk.tms.common.dto.flight;

import cn.buk.common.flight.dto.FlightBasicInfo;

/**
 * @author yfdai
 */
public class BaseChangeOrderFlightDto extends FlightBasicInfo {

    /**
     * 改签航班信息的所在数据库表的id，在修改改签航班信息时需要用到
     */
    private int id;

    /**
     * 改签航班信息类型：0-原航班， 1-新航班
     */
    private int fltType;

    public int getFltType() {
        return fltType;
    }

    public void setFltType(int fltType) {
        this.fltType = fltType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
