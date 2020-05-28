package com.wgb.utils.test.high.concurrency;

/**
 * @author INNERPEACE
 * @date 2020/5/7 10:28
 */
public interface Computable<A, V> {

	/**
	 * 大量计算接口
	 * @param arg
	 * @return
	 * @throws InterruptedException
	 */
	V compute(A arg) throws InterruptedException;
}
