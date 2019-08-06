package com.wgb.utils.util.http.client;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 〈HTTP接口工具〉
 * @author INNERPEACE
 * @date 2019/7/18 16:58
 **/
@Slf4j
public class HttpClientUtil {

	/**
	 * get请求
	 *
	 * @return
	 */
	public static String doGet(String url) {
		try {
			HttpClient client = new DefaultHttpClient();
			//发送get请求
			HttpGet request = new HttpGet(url);
			HttpResponse response = client.execute(request);

			/**请求发送成功，并得到响应**/
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				/**读取服务器返回过来的json字符串数据**/
				String strResult = EntityUtils.toString(response.getEntity());

				return strResult;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}


	/**
	 * post请求(用于key-value格式的参数)
	 *
	 * @param url
	 * @param params
	 * @return
	 */
	public static String doPost(String url, Map params, String charset) {

		BufferedReader in = null;
		try {
			// 定义HttpClient
			HttpClient client = new DefaultHttpClient();
			// 实例化HTTP方法
			HttpPost request = new HttpPost();
			request.setURI(new URI(url));

			//设置参数
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for (Iterator iter = params.keySet().iterator(); iter.hasNext(); ) {
				String name = (String) iter.next();
				String value = String.valueOf(params.get(name));
				nvps.add(new BasicNameValuePair(name, value));
				log.info("name:{},value:{}", name, value);
			}
			request.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

			HttpResponse response = client.execute(request);
			int code = response.getStatusLine().getStatusCode();
			int successedCode = 200;
			// 请求成功
			if (code == successedCode) {
				in = new BufferedReader(new InputStreamReader(response.getEntity()
						.getContent(), charset));
				StringBuffer sb = new StringBuffer();
				String line = "";
				String NL = System.getProperty("line.separator");
				while ((line = in.readLine()) != null) {
					sb.append(line + NL);
				}

				in.close();

				return sb.toString();
			} else {    //
				System.out.println("状态码：" + code);
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		}
	}

	/**
	 * post请求（用于请求json格式的参数）
	 *
	 * @param url
	 * @param params
	 * @return
	 */
	public static String doPost(String url, String params,String charset) throws Exception {

		// 创建httpPost
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		StringEntity entity = new StringEntity(params, charset);
		httpPost.setEntity(entity);
		try (
				CloseableHttpClient httpclient = HttpClients.createDefault();
				CloseableHttpResponse response = httpclient.execute(httpPost)) {
			StatusLine status = response.getStatusLine();
			int state = status.getStatusCode();
			if (state == HttpStatus.SC_OK) {
				HttpEntity responseEntity = response.getEntity();
				String jsonString = EntityUtils.toString(responseEntity);
				return jsonString;
			} else {
				log.error("请求返回:" + state + "(" + url + ")");
			}
		}
		return null;
	}

	/**
	 * 带SSL证书的请求
	 *
	 * @param url
	 * @param str
	 * @param charset
	 * @return
	 */
	public String doSslPost(String url, String str, String charset) {
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		try {
			httpClient = new SSLClient();
			httpPost = new HttpPost(url);
			httpPost.setHeader("Content-type", "application/xml; charset=utf-8");
			StringEntity entity = new StringEntity(str, "UTF-8");
			// 发送Json格式的数据请求
			entity.setContentType("application/xml;charset=UTF-8");
			entity.setContentEncoding("utf-8");
			httpPost.setEntity(entity);
			HttpResponse response = httpClient.execute(httpPost);
			int status = response.getStatusLine().getStatusCode();
			if (status == 200) {
				if (response != null) {
					HttpEntity resEntity = response.getEntity();
					if (resEntity != null) {
						result = EntityUtils.toString(resEntity, charset);
					}
				}
			} else {
				result = null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	/**
	 * 带SSL证书但是忽略此证书的请求
	 *
	 * @param url
	 * @param map
	 * @param charset
	 * @return
	 */
	public String doIgnorePost(String url, Map<String, String> map, String charset) {
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		try {
			httpClient = new SSLClient();
			httpPost = new HttpPost(url);
			//设置参数
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			Iterator iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, String> elem = (Map.Entry<String, String>) iterator.next();
				list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
			}
			if (list.size() > 0) {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
				httpPost.setEntity(entity);
			}
			HttpResponse response = httpClient.execute(httpPost);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, charset);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}


}