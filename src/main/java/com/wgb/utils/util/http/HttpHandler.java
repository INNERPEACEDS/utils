package com.wgb.utils.util.http;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * @author INNERPEACE
 * @date 2018/12/25 19:11
 **/
class HttpHandler extends HttpFacade {
    HttpHandler() {
    }

    @Override
    public String post(String url, Map<Object, Object> params) throws Exception {
        return this.handler(url, this.buildRequest(params).getBytes(this.defaultCharset), this.DEFAULT_CONNECT_TIMEOUT, this.DEFAULT_READ_TIMEOUT, METHOD_POST);
    }

    @Override
    public String postStatus(String url, Map<Object, Object> params) throws Exception {
        return this.handlerStatus(url, this.buildRequest(params).getBytes(this.defaultCharset), this.DEFAULT_CONNECT_TIMEOUT, this.DEFAULT_READ_TIMEOUT, METHOD_POST);
    }

    @Override
    public Response postResponse(String url, Map<Object, Object> params) throws Exception {
        return this.handlerResponse(url, this.buildRequest(params).getBytes(this.defaultCharset), this.DEFAULT_CONNECT_TIMEOUT, this.DEFAULT_READ_TIMEOUT, METHOD_POST);
    }

    @Override
    public String get(String url) throws Exception {
        return this.handler(url, null, this.DEFAULT_CONNECT_TIMEOUT, this.DEFAULT_READ_TIMEOUT, METHOD_GET);
    }

    @Override
    public String getStatus(String url) throws Exception {
        return this.handlerStatus(url, null, this.DEFAULT_CONNECT_TIMEOUT, this.DEFAULT_READ_TIMEOUT, METHOD_GET);
    }

    private String handler(String url, byte[] content, int connectTimeout, int readTimeout, String method) throws Exception {
        String type = "0";
        return handleProcess(url, content, connectTimeout, readTimeout, method, type);
    }

    private String handlerStatus(String url, byte[] content, int connectTimeout, int readTimeout, String method) throws Exception {
        String type = "1";
        return handleProcess(url, content, connectTimeout, readTimeout, method, type);
    }

    private Response handlerResponse(String url, byte[] content, int connectTimeout, int readTimeout, String method) throws Exception {
        String type = "2";
        return handleProcess(url, content, connectTimeout, readTimeout, method, type);
    }

    private <T extends Object> T handleProcess(String url, byte[] content, int connectTimeout, int readTimeout, String method, String type) throws Exception{
        HttpURLConnection conn = null;
        OutputStream out = null;
        try {
            conn = this.getConnection(new URL(url), method);
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);
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

    /**
     * 获取连接
     * @param url
     * @param method
     * @return
     * @throws IOException
     */
    HttpURLConnection getConnection(URL url, String method) throws IOException {
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod(method);
        conn.setDoInput(true);
        if (METHOD_POST.equals(method)) {
            conn.setDoOutput(true);
        }
        conn.setRequestProperty("Accept", "*/*");
        return conn;
    }
}
