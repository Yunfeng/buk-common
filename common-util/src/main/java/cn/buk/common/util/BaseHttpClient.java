package cn.buk.common.util;


import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.routing.DefaultProxyRoutePlanner;
import org.apache.hc.core5.http.HttpHost;

/**
 *
 * @author yfdai
 *  2017/3/30
 */
public class BaseHttpClient {

    // HTTP connection timeout
    protected static final int CONNECTION_TIMEOUT = 60000;

    protected static PoolingHttpClientConnectionManager cm = null;

    protected static CloseableHttpClient createHttpClient() {
        return createHttpClient(null, null);
    }

    protected static CloseableHttpClient createHttpClient(String proxyHost, String proxyPort) {
        if (cm == null) {
            cm = new PoolingHttpClientConnectionManager();
            cm.setMaxTotal(100);
        }

        int port = 0;
        try {
            if (proxyPort != null) {
              port = Integer.parseInt(proxyPort);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        CloseableHttpClient httpClient;
        if (proxyHost == null || proxyHost.trim().length() == 0 || port <= 0) {
//            httpClient = HttpClients.createDefault();
//            httpClient = HttpClients.custom().setConnectionManager(cm).build();

            //我们可以使用一个Builder来设置UA字段，然后再创建HttpClient对象
            HttpClientBuilder builder = HttpClients.custom();
            //对照UA字串的标准格式理解一下每部分的意思
            builder.setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:65.0) Gecko/20100101 Firefox/65.0");

//            builder.setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY)

            httpClient = builder.setConnectionManager(cm).build();


        } else {
            HttpHost proxy = new HttpHost(proxyHost, port);
            DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
            httpClient = HttpClients.custom()
                    .setConnectionManager(cm)
                    .setRoutePlanner(routePlanner)
                    .build();
        }

        return httpClient;
    }
}
