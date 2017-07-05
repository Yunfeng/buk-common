package cn.buk.util;

import cn.buk.test.xml.IntlOwFareInfo;
import cn.buk.test.xml.IntlRtFareInfo;
import cn.buk.test.xml.OwRequest;
import cn.buk.test.xml.RtRequest;
import org.junit.Test;

/**
 * Created by gezhi on 16/1/19.
 */
public class XmlUtilTest {

    @Test
    public void testConvertToXml() throws Exception {

    }

    @Test
    public void testConvertXmlTo() throws Exception {

        OwRequest request = new OwRequest();
        IntlOwFareInfo fareInfo = new IntlOwFareInfo();
        request.setFareInfo(fareInfo);


        String xml = XmlUtil.convertToXml(request, OwRequest.class, IntlOwFareInfo.class);

        System.out.println(xml);
    }

    @Test
    public void testConvertXmlTo2() throws Exception {

        RtRequest request = new RtRequest();
        IntlRtFareInfo fareInfo = new IntlRtFareInfo();
        request.setFareInfo(fareInfo);


        String xml = XmlUtil.convertToXml(request, RtRequest.class, IntlRtFareInfo.class);

        System.out.println(xml);
    }

}