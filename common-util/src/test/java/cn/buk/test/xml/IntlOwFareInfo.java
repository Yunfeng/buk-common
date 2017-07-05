package cn.buk.test.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * 国际私有单程运价
 * Created by yfdai on 16/1/8.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="OwFareInfo")
public class IntlOwFareInfo {

    /**
     *文件编号	PolicyId	varchar(30)	自由录入格式，如SHA5700FF500
     */
    @XmlAttribute(name = "PolicyID")
    public String policyId = "PolicyID";


    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }
}
