package cn.buk.common.util;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.net.URIBuilder;
import org.apache.hc.core5.util.Timeout;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static java.nio.charset.StandardCharsets.UTF_8;


/**
 * @author yfdai
 */
public class HttpUtil extends BaseHttpClient {

  private static final Logger logger = LogManager.getLogger(HttpUtil.class);


  /**
   * @deprecated 请使用postUrl
   */
  @Deprecated(since = "2.5.8", forRemoval = true)
  public static String sendPost(String url) {
    PrintWriter out = null;
    BufferedReader in = null;
    String result = "";
    try {
      URL realUrl = new URL(url);
      // 打开和URL之间的连接
      URLConnection conn = realUrl.openConnection();
      // 设置通用的请求属性
      conn.setRequestProperty("accept", "*/*");
      conn.setRequestProperty("connection", "Keep-Alive");
      conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
      // 发送POST请求必须设置如下两行
      conn.setDoOutput(true);
      conn.setDoInput(true);
      // 获取URLConnection对象对应的输出流

      // 定义BufferedReader输入流来读取URL的响应
      in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      String line;
      while ((line = in.readLine()) != null) {
        result += line;
      }
    } catch (Exception e) {
      logger.error("Exception: {}", e.getMessage());
      e.printStackTrace();
    }
    //使用finally块来关闭输出流、输入流
    finally {
      try {
        if (out != null) {
          out.close();
        }
        if (in != null) {
          in.close();
        }
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
    return result;
  }

  public static CloseableHttpClient createHttpClient() {
    return createHttpClient(null, null);
  }

  public static String getUrl(String url) {
    return getUrl(url, null);
  }

  public static String getUrl(String url, List<NameValuePair> params) {
    String uri = url;
    if (params != null) {
      try {
        var uriBuilder = new URIBuilder(url);
        uriBuilder.addParameters(params);
        uri = uriBuilder.toString();
      } catch (URISyntaxException e) {
        e.printStackTrace();
      }
    }

    logger.debug(uri);

    CloseableHttpClient httpClient = createHttpClient();
    HttpGet httpGet = new HttpGet(uri);
    httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63");

    return executeGet(httpClient, httpGet);
  }

  public static String executeGet(CloseableHttpClient httpClient, HttpGet httpGet) {
    String rs = "";

    try {
      CloseableHttpResponse response = httpClient.execute(httpGet);

      if (response.getCode() == HttpStatus.SC_OK) {
        rs = EntityUtils.toString(response.getEntity(), UTF_8);

        logger.debug("response: {}", rs);
      } else {
        logger.debug(response.getCode());

        rs = EntityUtils.toString(response.getEntity(), UTF_8);

        logger.debug("response: {}", rs);
      }

      response.close();
    } catch (Exception e) {
      logger.error(e.getMessage());
    }

    return rs;
  }

  public static String postUrl(final String url, final String body) {
    CloseableHttpClient httpClient = createHttpClient();

    RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(Timeout.ofMilliseconds(CONNECTION_TIMEOUT)).build();

    HttpPost httpPost = new HttpPost(url);
    httpPost.setConfig(requestConfig);

    String rs = "";

    try {
      StringEntity entity = new StringEntity(body, StandardCharsets.UTF_8);
      httpPost.setEntity(entity);

      CloseableHttpResponse response = httpClient.execute(httpPost);

      logger.debug(response);

      if (response.getCode() == HttpStatus.SC_OK) {
        rs = EntityUtils.toString(response.getEntity(), UTF_8);

        logger.debug(rs);
      }

      response.close();
    } catch (Exception e) {
      logger.error(e.getMessage());
    }

    return rs;
  }

  public static String postUrl(String url, List<NameValuePair> params) {
    CloseableHttpClient httpClient = createHttpClient();
    RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(Timeout.ofMilliseconds(CONNECTION_TIMEOUT)).build();

    HttpPost httpPost = new HttpPost(url);
    httpPost.setConfig(requestConfig);

    String rs = "";

    CloseableHttpResponse response = null;

    try {
      if (!params.isEmpty()) {
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, StandardCharsets.UTF_8);
        httpPost.setEntity(entity);
      }

      response = httpClient.execute(httpPost);

      if (response.getCode() == HttpStatus.SC_OK) {
        rs = EntityUtils.toString(response.getEntity(), UTF_8);
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
    } finally {
      if (response != null) {
        try {
          response.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }


    return rs;
  }

  public static String postUrl(final String url, String body, List<NameValuePair> headers) {
    logger.info(url);
    CloseableHttpClient httpClient = createHttpClient();
    RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(Timeout.ofMilliseconds(CONNECTION_TIMEOUT)).build();

    HttpPost httpPost = new HttpPost(url);
    httpPost.setConfig(requestConfig);

    String rs = "";

    try {
      StringEntity entity = new StringEntity(body, UTF_8);
      httpPost.setEntity(entity);

      if (headers != null) {
        for (var key : headers) {
          httpPost.addHeader(key.getName(), key.getValue().toUpperCase());
        }
      }

      httpPost.addHeader("Content-Type", "application/json");

      CloseableHttpResponse response = httpClient.execute(httpPost);


      if (response.getCode() == HttpStatus.SC_OK) {
        rs = EntityUtils.toString(response.getEntity(), UTF_8);
      }

      response.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return rs;
  }

  public static String postUrl(final String url, final String body, final boolean isJson) {
    CloseableHttpClient httpClient = createHttpClient();

    RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(Timeout.ofMilliseconds(CONNECTION_TIMEOUT)).build();

    HttpPost httpPost = new HttpPost(url);
    httpPost.setConfig(requestConfig);

    if (isJson) {
      httpPost.setHeader("Content-type", "application/json");
    }

    String rs = "";

    try {
      StringEntity entity = new StringEntity(body, StandardCharsets.UTF_8);
      httpPost.setEntity(entity);

      CloseableHttpResponse response = httpClient.execute(httpPost);

      logger.debug(response);

      if (response.getCode() == HttpStatus.SC_OK) {
        rs = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);

        logger.debug(rs);
      }

      response.close();
    } catch (Exception e) {
      logger.error(e.getMessage());
    }

    return rs;
  }

  public static void saveZipFile(CloseableHttpResponse response) throws IOException {
    //                assertStatus(response);//判断返回的code是否为200
    //从response里获取数据实体
    HttpEntity entity = response.getEntity();
    //获取数据流
    InputStream in = entity.getContent();
    Charset c = Charset.forName(UTF_8.name());
    //封装成zip输入流
    ZipInputStream zin = new ZipInputStream(in, c);
    BufferedOutputStream bos = null;
    ZipEntry ze;

    //文件存放地址
    String path = System.getProperty("java.io.tmpdir");
    File file = null;

    try {
      //循环zip输入流，获取每一个文件实体
      while ((ze = zin.getNextEntry()) != null) {
        //生成file（如果文件路径不存在，先创建）
        file = new File(path + ze.getName());
        logger.debug(file.getAbsolutePath());

        FileOutputStream fos = new FileOutputStream(file);
        int len;
        byte[] bytes = new byte[2048];
        bos = new BufferedOutputStream(fos, 2048);
        while ((len = zin.read(bytes, 0, 2048)) != -1) {
          bos.write(bytes, 0, len);
        }
        bos.flush();
        bos.close();
      }
      zin.close();//关闭输入流
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
  }

  public static String unZipIt(InputStream in) {
    byte[] buffer = new byte[2048];
    try {
      ZipInputStream zis = new ZipInputStream(in);
      ZipEntry entry = zis.getNextEntry();
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      while (entry != null) {
        logger.debug("Extracting: {}", entry);
        int len;
        while ((len = zis.read(buffer)) > 0) {
          baos.write(buffer, 0, len);
        }
        entry = zis.getNextEntry();
        baos.flush();
        baos.close();
      }
      zis.close();

      if (baos.toByteArray().length > 0) {
        return new String(baos.toByteArray(), UTF_8);
      } else {
        return null;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

}
