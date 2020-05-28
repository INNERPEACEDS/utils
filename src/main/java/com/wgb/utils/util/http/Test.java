package com.wgb.utils.util.http;

/**
 * B.4 JAVA接口调用代码示例（企业法人身份证号接口）
 * @author INNERPEACE
 * @date 2020/1/6 13:55
 */

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import java.io.*; import java.nio.charset.Charset;

public class Test {


	public static void main(String[] args) {
		JSONObject json = new JSONObject();
		JSONObject data = new JSONObject();
		json.put("id","00001");
		json.put("appKey"," sdf235safsaf23fsd");
		data.put("certId", "452226198211300000");
		data.put("certName", "李明");
		data.put("queryTp", "01");
		data.put("querySusMchnt", "1");
		data.put("queryRiskMchnt", "1");
		data.put("queryUpRiskMchnt", "1");
		data.put("queryManageAbnormalEnterprise", "1");
		data.put("queryIllegalEnterprise", "1");
		data.put("queryChangeFrequentInformation", "1");
		json.put("data", data);
		try {
			System.out.println(json);
			String str = httpPostWithJSON("https://esb.unionpay.com/ares/ARESVerificationService/ARESVerificationServiceProxy/PerRiskEval /getCertifIdRiskEval",json.toString());
			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String httpPostWithJSON(String url, String encoderJson) throws Exception {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader(HTTP.CONTENT_TYPE,"application/x-www-form-urlencoded");
		httpPost.addHeader("CONNECTION", HTTP.CONN_CLOSE);
		StringEntity se = new StringEntity(encoderJson,Charset.forName("GB18030"));
		httpPost.setEntity(se);
		HttpResponse response = httpClient.execute(httpPost);
		HttpEntity entity = response.getEntity();
		InputStream content = entity.getContent();
		JSONObject json = toJson(content);
		return json.toString();
	}
	/**
	 * 将输入转换成 JSON 数据
	 * */
	public static JSONObject toJson(InputStream in) throws UnsupportedEncodingException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF8"));
		StringBuilder sb = new StringBuilder();
		String line;

		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String string = sb.toString();
		if (string != null && !string.equals("")) {
			return JSONObject.fromObject(string);
		} else {
			return new JSONObject();
		}
	}
}
