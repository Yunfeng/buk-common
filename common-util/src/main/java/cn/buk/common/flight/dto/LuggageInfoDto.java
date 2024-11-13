package cn.buk.common.flight.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 航空公司的行李额信息
 * @author yfdai
 */
public class LuggageInfoDto {

    private int id;

    private String carrier;

    /**
     * 行李类型:
     * 0-托运行李
     * 1-非托运行李
     */
    private int luggageType;

    /**
     * 舱位性质
     * 10-头等舱
     * 20-商务舱
     * 30-经济舱
     * 40-超级经济舱
     */
    private int cabinType;


    /**
     * 免费行李额
     */
    private String freeLimit;

    /**
     * 体积限定
     */
    private String volumeLimit;

    /**
     * 重量限定
     */
    private String weightLimit;

    /**
     * 标题：主要用于页面展示
     */
    private String title;

    /**
     * 备注
     */
    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date lastUpdate;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        if (carrier == null) {
          carrier = "";
        } else {
          carrier = carrier.trim().toUpperCase();
        }

        this.carrier = carrier;
    }



    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getCabinType() {
        return cabinType;
    }

    public void setCabinType(int cabinType) {
        this.cabinType = cabinType;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getLuggageType() {
        return luggageType;
    }

    public void setLuggageType(int luggageType) {
        this.luggageType = luggageType;
    }

    public String getFreeLimit() {
        return freeLimit;
    }

    public void setFreeLimit(String freeLimit) {
        this.freeLimit = freeLimit;
    }

    public String getVolumeLimit() {
        return volumeLimit;
    }

    public void setVolumeLimit(String volumeLimit) {
        this.volumeLimit = volumeLimit;
    }

    public String getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(String weightLimit) {
        this.weightLimit = weightLimit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
