package com.wgb.utils.test;

/**
 * @author INNERPEACE
 * @date 2019/12/25 13:59
 */
public class VolatileTest {
	private static volatile int race = 0;

	public static int threadNumber = 20;

	public static void increase() {
		race++;
	}

	public static void main(String[] args) throws InterruptedException {
		Thread[] threads = new Thread[threadNumber];
		for (int i = 0; i < threadNumber; i++) {
			int finalI = i;
			threads[i] = new Thread(() ->{
				for (int j = 0; j < 100; j++) {
					increase();
					/*try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}*/
					/*System.out.println("开始线程" + finalI + ":");
					findAllThread();*/
				}
			}
			);
			threads[i].start();
		}
		while (Thread.activeCount() > 2) {
			System.out.println(Thread.activeCount());
			Thread.yield();
		}
		System.out.println("race=" + race);
	}

	public static Thread[] findAllThread(){
		ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();
		while (currentGroup.getParent() != null) {
			// 返回此线程组的父线程组
			currentGroup = currentGroup.getParent();
		}
		//此线程组中活动线程的估计数
		int noThreads = currentGroup.activeCount();

		Thread[] lstThreads = new Thread[noThreads];
		//把对此线程组中的所有活动子组的引用复制到指定数组中。
		currentGroup.enumerate(lstThreads);
		for (Thread thread : lstThreads) {
			System.out.println("线程数量：" + noThreads + " 线程id：" + thread.getId() + " 线程名称：" + thread.getName() + " 线程状态：" + thread.getState());
		}
		return lstThreads;
	}
}
