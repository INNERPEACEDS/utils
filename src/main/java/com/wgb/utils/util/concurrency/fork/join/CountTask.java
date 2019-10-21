package com.wgb.utils.util.concurrency.fork.join;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 *  Fork／Join 框架
 * @author INNERPEACE
 * @date 2019/7/2
 **/
@Slf4j
public class CountTask extends RecursiveTask<Integer> {
	/**
	 * 阈值
	 */
	private static final int THRESHOLD = 2;
	private int start;
	private int end;
	public CountTask(int start, int end) {
		this.start = start;
		this.end = end;
	}
	@Override
	protected Integer compute() {
		int product = 1;
		// 如果任务足够小就计算任务
		boolean canCompute = (end - start) <= THRESHOLD;
		if (canCompute) {
			for (int i = start; i <= end; i++) {
				product *= i;
			}
		} else {
			// 如果任务大于阀值，就分裂成两个子任务计算
			int middle = (start + end) / 2;
			CountTask leftTask = new CountTask(start, middle);
			CountTask rightTask = new CountTask(middle + 1, end);
			// 执行子任务
			leftTask.fork();
			rightTask.fork();
			// 等待子任务执行完，并得到其结果
			int leftResult=leftTask.join();
			int rightResult=rightTask.join();
			// 合并子任务
			product = leftResult * rightResult;
		}
		return product;
	}
	public static void main(String[] args) {
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		// 生成一个计算任务，负责计算 3*4*5*...15
		CountTask task = new CountTask(3, 15);
		// 执行一个任务
		Future<Integer> result = forkJoinPool.submit(task);
		try {
			log.info("执行结果：{}", result.get());
			// System.out.println(result.get());
		} catch (InterruptedException | ExecutionException e) {
		}
	}
}
