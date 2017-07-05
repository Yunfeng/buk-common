package cn.buk.test.xml;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by yfdai on 16/1/19.
 */
@XmlRootElement(name = "RtXmlRequest")
public class RtRequest {

    private IntlRtFareInfo fareInfo;

    public IntlRtFareInfo getFareInfo() {
        return fareInfo;
    }

    public void setFareInfo(IntlRtFareInfo fareInfo) {
        this.fareInfo = fareInfo;
    }
}
