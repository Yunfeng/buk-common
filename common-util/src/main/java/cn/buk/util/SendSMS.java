package cn.buk.util;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


public class SendSMS {
	// HTTP connection timeout
	private static int CONNECTION_TIMEOUT = 100000;
	// HTTP scoket connection timeout
	private static int SO_TIMEOUT = 120000;

    private static Logger logger = Logger.getLogger(SendSMS.class);

    private static CloseableHttpClient getHttpClient(String proxyHost, String proxyPort) {
        int port = 0;
        try {
            port = Integer.parseInt(proxyPort);
        } catch (Exception ex) {
        }

        CloseableHttpClient httpClient;
        if (proxyHost == null || proxyHost.trim().length() == 0 || port <= 0) {
            httpClient = HttpClients.createDefault();
        } else {
            HttpHost proxy = new HttpHost(proxyHost, port);
            DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
            httpClient = HttpClients.custom()
                    .setRoutePlanner(routePlanner)
                    .build();
        }

        return httpClient;
    }

    public static int send(String smsUrl, String username, String password, String mobileNo, String smsContent) {
        return send(smsUrl, username, password, mobileNo, smsContent, "", "");
    }

	public static int send(String smsUrl, String username, String password, String mobileNo, String smsContent, String proxyHost, String proxyPort) {
        CloseableHttpClient httpclient = getHttpClient(proxyHost, proxyPort);

        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(SO_TIMEOUT).setConnectTimeout(CONNECTION_TIMEOUT).build();

        HttpPost httpPost = new HttpPost(smsUrl);
        httpPost.setConfig(requestConfig);

		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("reststyle", "1"));
        formparams.add(new BasicNameValuePair("username", username));
        formparams.add(new BasicNameValuePair("password", password));
		formparams.add(new BasicNameValuePair("phoneno", mobileNo));
		formparams.add(new BasicNameValuePair("smsdetail", smsContent));
		
		try{
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httpPost.setEntity(entity);
			
			HttpResponse response = httpclient.execute(httpPost);
		
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String charsetName = ContentType.getOrDefault(response.getEntity()).getMimeType();
				logger.debug(charsetName + "<<<<<<<<<<<<<<<<<");
 
				String rs = EntityUtils.toString(response.getEntity());
				logger.debug(">>>>>>" + rs);

				return  1;
			}else{
				logger.warn("Error occurs");
			}
		}catch (Exception e) {
			e.printStackTrace();

		}
				
		return 0;
	}
}
