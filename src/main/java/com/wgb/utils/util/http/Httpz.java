package com.wgb.utils.util.http;

import java.util.Map;

/**
 * @author INNERPEACE
 * @date 2018/12/25 17:15
 **/

public class Httpz extends HttpFacade {
    private String encoding = "utf-8";
    private int readTimeout = 18000;
    private int sendTimeout = 18000;

    public Httpz(String encoding, int readTimeout, int sendTimeout) {
        this.encoding = encoding;
        this.readTimeout = readTimeout;
        this.sendTimeout = sendTimeout;
    }

    public Httpz() {
    }

    public String post(String url, Map<Object, Object> params) throws Exception {
        HttpFacade hf = null;
        if (this.isHttps(url)) {
            hf = new HttpsHandler();
        } else {
            hf = new HttpHandler();
        }

        ((HttpFacade)hf).setDefaultCharset(this.encoding);
        ((HttpFacade)hf).setDEFAULT_CONNECT_TIMEOUT(this.sendTimeout);
        ((HttpFacade)hf).setDEFAULT_READ_TIMEOUT(this.readTimeout);
        return ((HttpFacade)hf).post(url, params);
    }

    public String get(String url) throws Exception {
        HttpFacade hf = null;
        if (this.isHttps(url)) {
            hf = new HttpsHandler();
        } else {
            hf = new HttpHandler();
        }

        ((HttpFacade)hf).setDefaultCharset(this.encoding);
        ((HttpFacade)hf).setDEFAULT_CONNECT_TIMEOUT(this.sendTimeout);
        ((HttpFacade)hf).setDEFAULT_READ_TIMEOUT(this.readTimeout);
        return ((HttpFacade)hf).get(url);
    }

    private boolean isHttps(String url) {
        return url.startsWith("https") || url.startsWith("HTTPS");
    }

    public String postStatus(String url, Map<Object, Object> params) throws Exception {
        HttpFacade hf = null;
        if (this.isHttps(url)) {
            hf = new HttpsHandler();
        } else {
            hf = new HttpHandler();
        }

        ((HttpFacade)hf).setDefaultCharset(this.encoding);
        ((HttpFacade)hf).setDEFAULT_CONNECT_TIMEOUT(this.sendTimeout);
        ((HttpFacade)hf).setDEFAULT_READ_TIMEOUT(this.readTimeout);
        return ((HttpFacade)hf).postStatus(url, params);
    }

    public String getStatus(String url) throws Exception {
        HttpFacade hf = null;
        if (this.isHttps(url)) {
            hf = new HttpsHandler();
        } else {
            hf = new HttpHandler();
        }

        ((HttpFacade)hf).setDefaultCharset(this.encoding);
        ((HttpFacade)hf).setDEFAULT_CONNECT_TIMEOUT(this.sendTimeout);
        ((HttpFacade)hf).setDEFAULT_READ_TIMEOUT(this.readTimeout);
        return ((HttpFacade)hf).getStatus(url);
    }

    public Response postResponse(String url, Map<Object, Object> params) throws Exception {
        HttpFacade hf = null;
        if (this.isHttps(url)) {
            hf = new HttpsHandler();
        } else {
            hf = new HttpHandler();
        }

        ((HttpFacade)hf).setDefaultCharset(this.encoding);
        ((HttpFacade)hf).setDEFAULT_CONNECT_TIMEOUT(this.sendTimeout);
        ((HttpFacade)hf).setDEFAULT_READ_TIMEOUT(this.readTimeout);
        return ((HttpFacade)hf).postResponse(url, params);
    }
}
