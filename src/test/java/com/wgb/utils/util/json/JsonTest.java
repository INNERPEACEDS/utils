package com.wgb.utils.util.json;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class JsonTest {
    @Test
    public void jsonTest() {
        log.info("键值对中value值为JSONObject开始：......");
        // 数据类型为json，key：value（value类型为JSONArray）,例如：{"login":[{"ip":"192.168.69.25","remark":"测试"},{"ip":"78.24.34.67","remark":"测试"}],"cash_operator":[{"ip":"123.34.56.66","remark":"测试"},{"ip":"192.168.0.1","remark":"测试"}]}
        String allownIp1 = "{\"login\":[{\"ip\":\"192.168.69.25\",\"remark\":\"测试\"},{\"ip\":\"78.24.34.67\",\"remark\":\"测试\"}],\"cash_operator\":[{\"ip\":\"123.34.56.66\",\"remark\":\"测试\"},{\"ip\":\"192.168.0.1\",\"remark\":\"测试\"}]}";
        String allownIp = null;
        JSONObject ips = null;
        JSONArray ipType = null;
        try {
            ips = new JSONObject(allownIp1);
            ipType = (JSONArray) ips.get("login");
        } catch (JSONException e) {
            log.info("初始化Json数据异常");
        }
        log.info("login[长度为{}]对应的值为：{}", ipType.length(), ipType);
        log.info("ipType.remove(0)", ipType.remove(0));
        int length = ipType.length();
        log.info("长度：{}", length);
        for(int i = length-1; i >= 0; i--){
            ipType.remove(i);
        }
        log.info("原json串更改后[ips]为：{}；login[长度为{}]对应的值为{}", ips, ipType.length(), ipType);
        log.info("键值对中value值为JSONObject结束");

        log.info("键值对中value为字符数据测试开始：......");
        // 数据类型为json，key：value({})，例如：{"login":"本地地址测试", "cash_operator":"安全地址测试"}
        String ips1 = "{\"login\":\"本地地址测试\", \"cash_operator\":\"安全地址测试\"}";
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(ips1);
        } catch (JSONException e) {
            log.info("初始化JSONObject异常", e);
        }
        if (jsonObject != null && jsonObject.length() > 0) {
            log.info("jsonObject[长度为：{}]的数据为：{}", jsonObject.length(), jsonObject);
            String login = (String) jsonObject.remove("login");

            log.info("移除[{}]后数据为：{}", login, jsonObject);
            /*for (int i = jsonObject.length()-1; i >=0; i--) {
                jsonObject.remove(i);
            }*/
        }
        log.info("键值对中value为字符数据测试结束");

        /*System.out.println("开始：" + allownIp);
		Map<String, String> allownIpMap = JsonUtil.jsonToMap(allownIp);
		System.out.println("allownIpMap为:" + allownIpMap);
		JSONArray jsonArray = new JSONArray();
	*/

		/*String ipStr = allownIp;//已存在的ip（String）
		JSONObject ips = null;
		String result = "";
		if(ipStr != null && !"".equals(ipStr.trim())){//ip存在
			ips = new JSONObject(ipStr);
			JSONArray ipType = null;
			try{
				ipType = (JSONArray) ips.get("login");//根据ip类型，获取ip，如果为空则抛出异常
				System.out.println("ipType:" + ipType);
				//ips.put("login", null);
				ips.put("cash_operator", allownIpMap.get("cash_operator"));
			}catch(JSONException e){
				ipType = new JSONArray();
				ips.put("login", ipType);
				ips.put("cash_operator", allownIpMap.get("cash_operator"));
			}
		}else{//ip不存在
			ips = new JSONObject();
			ips.put("login", new JSONArray());
			ips.put("cash_operator", allownIpMap.get("cash_operator"));
		}
		System.out.println("ips:" + ips);*/
		/*allownIpMap.put("login", jsonArray.toString());
		System.out.println("allownIpMap为:" + allownIpMap);
		String allownIpJson = JsonUtil.map2Json(allownIpMap);
		System.out.println("测试结果：" + allownIpJson);*/


    }
}
