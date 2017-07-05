package cn.buk.test.xml;

import javax.xml.bind.annotation.*;

/**
 * Created by yfdai on 16/1/16.
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name="RtFareInfo", propOrder = {"policyId", "isOther"})
public class IntlRtFareInfo extends IntlOwFareInfo {

    private Integer isOther = 1;

    public Integer getIsOther() {
        return isOther;
    }

    public void setIsOther(Integer isOther) {
        this.isOther = isOther;
    }

    @XmlAttribute(name = "PolicyId")
    public String getPolicyId() {
        return super.getPolicyId();
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }
}
