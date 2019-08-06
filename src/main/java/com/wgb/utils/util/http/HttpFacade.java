package com.wgb.utils.util.http;

import lombok.extern.slf4j.Slf4j;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

/**
 * @author INNERPEACE
 * @date 2018/12/25 17:18
 **/
@Slf4j
public abstract class HttpFacade {
    /**
     * 定义处理的请求类型（"0"代表只有conn方式；"1"表示只有statusCode方式；"2"代表response方式，包含conn和statusCode）
     */
    static String[] handlerType = {"0", "1", "2"};

    static final String METHOD_POST = "POST";

    static final String METHOD_GET = "GET";

    int DEFAULT_CONNECT_TIMEOUT = 18000;

    int DEFAULT_READ_TIMEOUT = 18000;

    protected String defaultCharset = "UTF-8";

    public abstract String post(String var1, Map<Object, Object> var2) throws Exception;

    public abstract String postStatus(String var1, Map<Object, Object> var2) throws Exception;

    public abstract Response postResponse(String var1, Map<Object, Object> var2) throws Exception;

    public abstract String get(String var1) throws Exception;

    public abstract String getStatus(String var1) throws Exception;

    /**
     * 获取以字符串格式的响应
     * @param conn
     * @return
     * @throws IOException
     */
    String getResponseAsString(HttpURLConnection conn) throws IOException {
        String charset = this.getResponseCharset(conn.getContentType());
        InputStream es = conn.getErrorStream();
        if (es == null) {
            return this.getStreamAsString(conn.getInputStream(), charset);
        } else {
            String msg = this.getStreamAsString(es, charset);
            if (msg == null) {
                throw new IOException(conn.getResponseCode() + ":" + conn.getResponseMessage());
            } else {
                throw new IOException(msg);
            }
        }
    }

    String getStreamAsString(InputStream stream, String charset) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset));
            StringWriter writer = new StringWriter();
            char[] chars = new char[256];
            boolean var6 = false;

            int count;
            while((count = reader.read(chars)) > 0) {
                writer.write(chars, 0, count);
            }
            String var8 = writer.toString();
            return var8;
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }

    String getResponseCharset(String ctype) {
        String charset = this.defaultCharset;
        if (this.isNotNullOrEmpty(ctype)) {
            String[] params = ctype.split(";");
            String[] var7 = params;
            int var6 = params.length;

            for(int var5 = 0; var5 < var6; ++var5) {
                String param = var7[var5];
                param = param.trim();
                if (param.startsWith("charset")) {
                    String[] pair = param.split("=", 2);
                    if (pair.length == 2 && this.isNotNullOrEmpty(pair[1])) {
                        charset = pair[1].trim();
                    }
                    break;
                }
            }
        }
        return charset;
    }

    /**
     * 参数Map键值对序列化（例如：Map{"name":"张三", "age":"18"}转换成字符串name=张三&age=18）
     * @return 构建成字符串后的请求参数
     */
    String buildRequest(Map<Object, Object> params) {
        StringBuilder sb = new StringBuilder();
        if (params != null && !params.isEmpty()) {
            Iterator it = params.keySet().iterator();

            while(it.hasNext()) {
                String temp = it.next().toString();
                sb.append(temp).append("=").append(params.get(temp)).append("&");
            }

            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * 写入连接或者请求状态
     * @param content
     * @param method
     * @param conn
     * @param out
     * @param response
     * @return
     * @throws Exception
     */
    String handlerResultOfConnection(byte[] content, String method, HttpURLConnection conn, OutputStream out, String response) throws Exception {
        try {
            if (METHOD_POST.equals(method)) {
                out = conn.getOutputStream();
                out.write(content);
            }
            response = String.valueOf(conn.getResponseCode());
        } catch (IOException e) {
            log.error("连接写入异常", e);
            throw e;
        }
        return response;
    }

    /**
     * 写入请求结果（包含连接和状态）
     * @param content
     * @param method
     * @param conn
     * @param out
     * @param response
     * @return
     */
    Response handlerResultOfResponse(byte[] content, String method, HttpURLConnection conn, OutputStream out, Response response) {
        try {
            if (METHOD_POST.equals(method)) {
                out = conn.getOutputStream();
                out.write(content);
            }
            response.setHttpStatus(conn.getResponseCode());
            response.setMessage(this.getResponseAsString(conn));
        } catch (IOException e) {
            log.error("处理响应数据异常：{}", e);
            response.setMessage(e.getMessage());
        }
        return response;
    }
    public <T extends Object> T handlerType(byte[] content, String method, HttpURLConnection conn, OutputStream out, String type) throws Exception {
        if (handlerType[0].equals(type) || handlerType[1].equals(type)) {
            String response = null;
            return (T) handlerResultOfConnection(content, method, conn, out, response);
        }
        if (handlerType[2].equals(type)) {
            Response response = new Response();
            return (T) handlerResultOfResponse(content, method, conn, out, response);
        }
        return null;
    }

    public void setDefaultCharset(String defaultCharset) {
        this.defaultCharset = defaultCharset;
    }

    private boolean isNullOrEmpty(String s) {
        return s == null || "".equals(s);
    }

    private boolean isNotNullOrEmpty(String s) {
        return !this.isNullOrEmpty(s);
    }

    public int getDEFAULT_CONNECT_TIMEOUT() {
        return this.DEFAULT_CONNECT_TIMEOUT;
    }

    public void setDEFAULT_CONNECT_TIMEOUT(int dEFAULT_CONNECT_TIMEOUT) {
        this.DEFAULT_CONNECT_TIMEOUT = dEFAULT_CONNECT_TIMEOUT;
    }

    public int getDEFAULT_READ_TIMEOUT() {
        return this.DEFAULT_READ_TIMEOUT;
    }

    public void setDEFAULT_READ_TIMEOUT(int dEFAULT_READ_TIMEOUT) {
        this.DEFAULT_READ_TIMEOUT = dEFAULT_READ_TIMEOUT;
    }


}
