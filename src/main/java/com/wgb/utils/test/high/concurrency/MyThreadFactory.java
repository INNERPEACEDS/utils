package com.wgb.utils.test.high.concurrency;

import java.util.concurrent.ThreadFactory;

/**
 * @author INNERPEACE
 * @date 2020/6/28
 */
public class MyThreadFactory implements ThreadFactory {
	private final String poolName;

	public MyThreadFactory(String poolName) {
		this.poolName = poolName;
	}

	@Override
	public Thread newThread(Runnable r) {
		return new MyAppThread(r, poolName);
	}
}
