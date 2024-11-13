package cn.buk.common.util;

import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class HttpUtilTest {


    @Test
    public void testGetShortUrl() {
        final String url = "http://www.baidu.com?";

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("s", "ID.4"));


        final String jsonStr = HttpUtil.getUrl(url, params);

        System.out.println(Math.abs(-10000));

//        System.out.println(jsonStr);
    }
}