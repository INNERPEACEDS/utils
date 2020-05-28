package com.wgb.utils.test.thead;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * @author INNERPEACE
 * @date 2019/12/26 17:02
 */
@Slf4j
public class DataToFile implements Callable<List<String>> {

	private BlockingDeque<List<String>> queue;

	private CountDownLatch countDownLatch;

	public DataToFile(BlockingDeque<List<String>> queue, CountDownLatch countDownLatch) {
		this.queue = queue;
		this.countDownLatch = countDownLatch;
	}


	public void run() {
		try {
			StringBuffer buffer = new StringBuffer();
			List<String> take = queue.take();
			for (String data : take) {
				buffer.append(data).append(" ");
			}
			log.info("写入文件数据中run：{}", buffer.toString());
			Thread.sleep(2000);
		} catch (Exception e) {
			log.error("写入文件异常", e);
		} finally {
			countDownLatch.countDown();
		}
	}

	@Override
	public List<String> call() {
		try {
			StringBuffer buffer = new StringBuffer();
			List<String> take = queue.take();
			for (String data : take) {
				buffer.append(data).append(" ");
			}
			Thread.sleep(4000);
			log.info("写入文件数据中call：{}完成", buffer.toString());
		} catch (Exception e) {
			log.error("写入文件异常", e);
		} finally {
			countDownLatch.countDown();
		}
		return null;
	}
}
