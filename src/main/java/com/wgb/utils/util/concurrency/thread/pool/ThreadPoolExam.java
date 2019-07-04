package com.wgb.utils.util.concurrency.thread.pool;

/**
 * @author INNERPEACE
 * @date 2019/7/2
 **/

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExam {
	public static void main(String[] args) {
		// first test for singleThreadPool
//		ExecutorService pool = Executors.newSingleThreadExecutor();
		// second test for fixedThreadPool
//        ExecutorService pool = Executors.newFixedThreadPool(5);
		// third test for cachedThreadPool
		ExecutorService pool = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			pool.execute(new TaskInPool(i));
		}
		pool.shutdown();
	}
}

class TaskInPool implements Runnable {
	private final int id;

	TaskInPool(int id) {
		this.id = id;
	}

    @Override
	public void run() {
		try {
			for (int i = 0; i < 5; i++) {
				System.out.println("TaskInPool-["+id+"] is running phase-"+i);
				TimeUnit.SECONDS.sleep(1);
			}
			System.out.println("TaskInPool-["+id+"] is over");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}