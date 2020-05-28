package com.wgb.utils.util.http;

/**
 * @author INNERPEACE
 * @date 2020/1/6 13:45
 */

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class ClientApp {
	public static String httpPostWithJSON(String url, String encoderJson){
		try {
			HttpClient httpClient = wrapClient(new DefaultHttpClient());
			HttpConnectionParams.setConnectionTimeout(httpClient.getParams(), 100000);
			HttpPost httpPost = new HttpPost(url);
			httpPost.addHeader(HTTP.CONTENT_TYPE,"application/x-www-form-urlencoded");
			httpPost.addHeader("CONNECTION", HTTP.CONN_CLOSE);
			StringEntity se = new StringEntity(encoderJson);
			httpPost.setEntity(se);
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			InputStream content = entity.getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(content, "utf-8"));
			StringBuilder sb = new StringBuilder();
			String line = null;
			try {
				while((line = reader.readLine()) != null) {
					sb.append(line);
				}
			} catch(IOException e) {}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static HttpClient wrapClient(HttpClient base)  {
		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
			X509TrustManager tm = new X509TrustManager(){
				@Override
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
				@Override
				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
				@Override
				public X509Certificate[] getAcceptedIssuers() {return null;}
				public boolean isClientTrusted(X509Certificate[] arg0) {return false;}
				public boolean isServerTrusted(X509Certificate[] arg0) {
					// TODO Auto-generated method stub
					return true;
				}
			};
			ctx.init(null, new TrustManager[] { tm }, null);
			SSLSocketFactory ssf = new SSLSocketFactory(ctx);
			ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			ClientConnectionManager ccm = base.getConnectionManager();
			SchemeRegistry sr = ccm.getSchemeRegistry();
			//设置要使用的端口，默认是 443
			sr.register(new Scheme("https", ssf, 443));
			return new DefaultHttpClient(ccm, base.getParams());
		} catch (Exception ex) {
			return null;
		}
	}
	public static void main(String[] args) throws Exception{
		JSONObject json = new JSONObject();
		JSONObject data = new JSONObject();
		json.put("id", "00001");
		json.put("appKey", "sdf235safsaf23fsd");
		json.put("data", data);
		data.put("mchntCodeTp", "02");
		data.put("mchntCode", "110011000000786864");
		data.put("queryTp","01");
		data.put("queryAcqCaseMchnt", "1");
		data.put("queryLoseTrustMchnt", "1");
		data.put("querySusMchnt", "1");
		data.put("queryRiskMchnt", "1");
		data.put("queryUpRiskMchnt", "1");
		data.put("queryManageAbnormalEnterprise", "1");
		data.put("queryIllegalEnterprise", "1");
		data.put("queryChangeFrequentInformation", "1");
		json.put("data", data);  //企业代码风险评估查询如上
		String result = httpPostWithJSON("https://esb.unionpay.com/ares/ARESVerificationService/ARESVerificationServiceProxy/EnterpriseRiskEval/getMerchantRiskEval", json.toString());
		System.out.println("result:" + result);
	}
}