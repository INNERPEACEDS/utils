package com.wgb.utils.util.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author INNERPEACE
 * @date 2019/11/9 10:43
 */
public class TestThreadPool {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//1. 创建线程池
		ExecutorService pool = Executors.newFixedThreadPool(5);

		List<Future<Integer>> list=new ArrayList<Future<Integer>>();

		for (int i = 0; i < 10; i++) {
			Future<Integer>  future=pool.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					int sum=0;
					for (int i = 0; i <=100; i++) {
						sum+=i;
					}
					return sum;
				}
			});
			list.add(future);
		}

		pool.shutdown();

		for (Future<Integer> future : list) {
			System.out.println(future.get());
		}




//		Future<Integer>  future=pool.submit(new Callable<Integer>() {
//			public Integer call() throws Exception {
//				int sum=0;
//				for (int i = 0; i <=100; i++) {
//					sum+=i;
//				}
//				return sum;
//			}
//		});
//		System.out.println(future.get());
//		pool.shutdown();




//		ThreadPoolDemo threadPoolDemo=new ThreadPoolDemo();
//
//		//2. 为线程池中的线程分配任务
//		for (int i = 0; i < 10; i++) {
//			pool.submit(threadPoolDemo);
//		}
//
//		//3. 关闭线程池
//		pool.shutdown();
//


//		new Thread(threadPoolDemo).start();
//		new Thread(threadPoolDemo).start();

	}
}

class ThreadPoolDemo implements Runnable{

	private int i=0;

	@Override
	public void run() {
		while(i<=100){
			System.out.println(Thread.currentThread().getName()+" "+ i++);
		}
	}

}