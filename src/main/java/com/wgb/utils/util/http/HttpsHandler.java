package com.wgb.utils.util.http;

/**
 * @author INNERPEACE
 * @date 2018/12/25 18:59
 **/
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
@Slf4j
class HttpsHandler extends HttpFacade {

    HttpsHandler() {
    }
    public static void main(String[] args) {
       /* Map map = new HashMap();
        map.put("张三", "18");
        map.put("李四", "19");
        map.put("王五", "20");
        map.put("赵六", "21");
        String s = paramsKeyValueSerial(map);
        log.info("结果1:{}", s);*/
    }

    /**
     * 处理POST方式
     * @param url    请求地址
     * @param params 请求参数
     * @return 处理POST后的结果
     * @throws Exception
     */
    @Override
    public String post(String url, Map<Object, Object> params) throws Exception {
        return this.handler(url, this.buildRequest(params).getBytes(this.defaultCharset), this.DEFAULT_CONNECT_TIMEOUT, this.DEFAULT_READ_TIMEOUT, METHOD_POST);
    }

    /**
     * 获取POST请求状态
     * @param url    请求地址
     * @param params 请求参数
     * @return 处理POST状态后的结果
     * @throws Exception
     */
    @Override
    public String postStatus(String url, Map<Object, Object> params) throws Exception {
        return this.handlerStatus(url, this.buildRequest(params).getBytes(this.defaultCharset), this.DEFAULT_CONNECT_TIMEOUT, this.DEFAULT_READ_TIMEOUT, METHOD_POST);
    }

    @Override
    public Response postResponse(String url, Map<Object, Object> params) throws Exception {
        return this.handlerResponse(url, this.buildRequest(params).getBytes(this.defaultCharset), this.DEFAULT_CONNECT_TIMEOUT, this.DEFAULT_READ_TIMEOUT, "POST");
    }

    /**
     * 处理GET请求
     * @param url 请求地址
     * @return 处理get请求后的结果
     * @throws Exception
     */
    @Override
    public String get(String url) throws Exception {
        return this.handler(url, null, this.DEFAULT_CONNECT_TIMEOUT, this.DEFAULT_READ_TIMEOUT, METHOD_GET);
    }

    /**
     * 获取GET请求状态
     * @param url 请求地址
     * @return 处理
     * @throws Exception
     */
    @Override
    public String getStatus(String url) throws Exception {
        return this.handlerStatus(url, null, this.DEFAULT_CONNECT_TIMEOUT, this.DEFAULT_READ_TIMEOUT, METHOD_GET);
    }

    /**
     * 获取连接
     * @param url
     * @param method
     * @return
     * @throws IOException
     */
    HttpsURLConnection getConnection(URL url, String method) throws IOException {
        HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
        conn.setRequestMethod(method);
        conn.setDoInput(true);
        if (METHOD_POST.equals(method)) {
            conn.setDoOutput(true);
        }
        conn.setRequestProperty("Accept", "*/*");
        return conn;
    }

    /**
     * 处理请求连接
     * @param url
     * @param content
     * @param connectTimeout
     * @param readTimeout
     * @param method
     * @return
     * @throws Exception
     */
    private String handler(String url, byte[] content, int connectTimeout, int readTimeout, String method) throws Exception {
        String type = "0";
        return handleProcess(url, content, connectTimeout, readTimeout, method, type);
    }

    /**
     * 处理请求状态
     * @param url
     * @param content
     * @param connectTimeout
     * @param readTimeout
     * @param method
     * @return
     * @throws Exception
     */
    private String handlerStatus(String url, byte[] content, int connectTimeout, int readTimeout, String method) throws Exception {
        String type = "1";
        return handleProcess(url, content, connectTimeout, readTimeout, method, type);
    }

    /**
     * 处理后的响应（包含连接和状态）
     * @param url
     * @param content
     * @param connectTimeout
     * @param readTimeout
     * @param method
     * @return
     * @throws Exception
     */
    private Response handlerResponse(String url, byte[] content, int connectTimeout, int readTimeout, String method) throws Exception {
        String type = "2";
        return handleProcess(url, content, connectTimeout, readTimeout, method, type);
    }

    /**
     * 请求连接处理过程
     * @param url
     * @param content
     * @param connectTimeout
     * @param readTimeout
     * @param method
     * @param type
     * @param <T>
     * @return
     * @throws Exception
     */
    private <T extends Object> T handleProcess(String url, byte[] content, int connectTimeout, int readTimeout, String method, String type) throws Exception {
        HttpsURLConnection conn = null;
        OutputStream out = null;
        try {
            try {
                SSLContext ctx = SSLContext.getInstance("TLS");
                ctx.init(new KeyManager[0], new TrustManager[]{new HttpsHandler.DefaultTrustManager((HttpsHandler.DefaultTrustManager)null)}, new SecureRandom());
                SSLContext.setDefault(ctx);
                conn = this.getConnection(new URL(url), method);
                conn.setHostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
                conn.setConnectTimeout(connectTimeout);
                conn.setReadTimeout(readTimeout);
            } catch (Exception e) {
                log.info("连接异常{}", e);
                throw e;
            }
            return handlerType(content, method, conn, out, type);
        } finally {
            if (out != null) {
                out.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    private static class DefaultTrustManager implements X509TrustManager {
        private DefaultTrustManager() {
        }

        public DefaultTrustManager(DefaultTrustManager defaultTrustManager) {
        }

        @Override
        public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }
}