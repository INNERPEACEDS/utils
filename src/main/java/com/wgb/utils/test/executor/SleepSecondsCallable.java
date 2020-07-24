package com.wgb.utils.test.executor;

/**
 * 睡seconds秒，输出成功执行，返回名字+固定串
 * @author INNERPEACE
 * @date 2020/6/4
 */

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class SleepSecondsCallable implements Callable<String> {
	private String name;

	private int seconds;

	public SleepSecondsCallable(String name, int seconds) {
		this.name = name;
		this.seconds = seconds;
	}

	@Override
	public String call() throws Exception {
		System.out.println(name + ",begin to execute");

		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
			System.out.println(name + " was disturbed during sleeping.");
			return name + "_SleepSecondsCallable_failed";
		}
		/*int j = 1;
		for (int i = 0; i < seconds * 100000000; i++) {
			j += i;
		}*/

		System.out.println(name + ",success to execute");

		return name + "_SleepSecondsCallable_succes";
	}

}
