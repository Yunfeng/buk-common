package cn.buk.test.xml;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by yfdai on 16/1/19.
 */
@XmlRootElement(name = "OwXmlRequest")
public class OwRequest {

    private IntlOwFareInfo fareInfo;


    public IntlOwFareInfo getFareInfo() {
        return fareInfo;
    }

    public void setFareInfo(IntlOwFareInfo fareInfo) {
        this.fareInfo = fareInfo;
    }
}
