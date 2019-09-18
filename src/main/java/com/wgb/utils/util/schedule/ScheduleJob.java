package com.wgb.utils.util.schedule;

import com.wgb.utils.util.aop.IAopTargetInterface;
import com.wgb.utils.util.http.Httpz;
import com.wgb.utils.util.http.client.HttpClientConfig;
import com.wgb.utils.util.http.client.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sun.net.www.http.HttpClient;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * 定时测试
 * @Configurable：加上此注解的类相当于XML配置文件，可以被SpringBoot扫描初始化。
 *
 * @EnableScheduling：通过在配直类注解＠EnableScheduling来开启对计划任务的支持，然后在要执行计划任务的方法上注解＠Scheduled，声明这是一个计划任务。
 *
 * @Scheduled：注解为定时任务，在cron表达式里写执行的时机。
 * @author INNERPEACE
 * @date 2019/7/18
 **/
@Component
//@Configurable
@EnableScheduling
@Slf4j
public class ScheduleJob {
	@Autowired
	private RestTemplate restTemplate;

	@Resource
	IAopTargetInterface aopTargetInterfaceImpl;

	public void reportCurrent(){
		log.info("http发送");
		String url = "http://localhost:8088/lesheAlbumSpider/startSpider";
		try {
			String respResult = new Httpz().post(url, null);
			log.info("响应结果：{}", respResult);
		} catch (Exception e) {
			log.error("请求乐摄爬虫异常", e);
		}
	}

	public void httpClientPost() {
		log.info("http发送");
		String url = "http://localhost:8088/lesheAlbumSpider/startSpider";
		try {
			String respResult = HttpClientUtil.doPost(url, new HashMap(16), "UTF-8");
			log.info("响应结果：{}", respResult);
		} catch (Exception e) {
			log.error("请求乐摄爬虫异常", e);
		}
	}

	/**
	 * 每5秒执行一次
	 */
	// @Scheduled(cron="*/5 * * * * *")
	public void httpClientRestTemplatePost() {
		log.info("http发送");
		String url = "http://localhost:8088/lesheAlbumSpider/startSpider";
		try {
			String respResult = restTemplate.postForObject(url, null, String.class);
			// log.info("响应结果：{}", respResult);
		} catch (Exception e) {
			log.error("请求乐摄爬虫异常", e);
		}
	}

	/**
	 * 每5秒执行一次
	 */
	// @Scheduled(cron="*/5 * * * * *")
	public void aopTest() {
		log.info("springAop执行开始");
		aopTargetInterfaceImpl.save();
	}
}
