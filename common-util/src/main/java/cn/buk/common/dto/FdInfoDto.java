package cn.buk.common.dto;

/**
 * Created by yfdai on 16/11/9.
 */
public class FdInfoDto {

    private String dport;
    private String aport;
    private String carrier;
    private String subclass;

    public String getDport() {
        return dport;
    }

    public void setDport(String dport) {
        this.dport = dport;
    }

    public String getAport() {
        return aport;
    }

    public void setAport(String aport) {
        this.aport = aport;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getSubclass() {
        return subclass;
    }

    public void setSubclass(String subclass) {
        this.subclass = subclass;
    }
}
