package com.wgb.utils.test.high.concurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author INNERPEACE
 * @date 2020/6/4
 */
@Slf4j
public class CheckMail {

	public static void main(String[] args) throws InterruptedException {
		Set<String> set = new HashSet<>();
		set.add("01@qq.com");
		set.add("02@qq.com.");
		set.add("03@qq.com.");
		set.add("04@qq.com.");
		set.add("05@qq.com.");
		set.add("06@qq.com.");
		set.add("07@qq.com.");
		set.add("08@qq.com.");
		set.add("09@qq.com.");
		set.add("010@qq.com.");
		boolean b = new CheckMail().checkMail(set, 6, TimeUnit.SECONDS);
		log.info("结果：{}", b);
	}

	public boolean checkMail(Set<String> hosts, long timeout, TimeUnit unit) throws InterruptedException {
		ExecutorService exec = Executors.newCachedThreadPool();
		final AtomicBoolean hasNewMail = new AtomicBoolean(false);
		try {
			for (final String host : hosts) {
				exec.execute(new Runnable() {
					@Override
					public void run() {
						if (checkMail(host)) {
							log.info("{}设置为true", host);
							hasNewMail.set(true);
							return;
						}
						log.info("{}设置为false", host);
					}
				});
			}
		} finally {
			log.info("开始关闭exec");
			exec.shutdown();
			exec.awaitTermination(timeout, unit);
		}
		return hasNewMail.get();
	}

	public boolean checkMail(String email) {
		log.info("{}开始等待", email);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			log.error("{}发生中断", email);
		}
		String emailRegular = "^[a-z0-9A-Z]+[- |a-z0-9A-Z._]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$";
		if (email == null) {
			return false;
		}
		return email.matches(emailRegular);
	}
}
