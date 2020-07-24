package com.wgb.utils.test.high.concurrency;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author INNERPEACE
 * @date 2020/6/5 11:16
 */
@Slf4j
public class WebCrawlerImpl extends WebCrawler {
	@Override
	protected List<String> processPage(String url) {

		List<String> urls = new ArrayList<>();
		try {
			log.info("{}开始创建子地址", url);
			for (int i = 0; i < 100000000; i++) {
				urls.add(new String(url + "www.qq.com" + (i + 1)));
				try {
					if (i == 1000) {
						Thread.sleep(20000);
					}
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					return urls;
				}

			}
		} catch (Exception e) {
			log.error("爬虫地址异常", e);
//			e.printStackTrace();
			return null;
		}
		return urls;
	}

	@Test
	public void webCrawlerImplTest() throws InterruptedException {
		WebCrawlerImpl webCrawler = new WebCrawlerImpl();
		webCrawler.start();
		webCrawler.stop();

	}


}
