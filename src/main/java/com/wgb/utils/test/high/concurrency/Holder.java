package com.wgb.utils.test.high.concurrency;

/**
 * 清单3.15 如果Holder没有被正确发布，它将处于失败的风险中
 * @author INNERPEACE
 * @date 2020/4/24 13:26
 */
public class Holder {
	private int n;
	public Holder(int n) {
		this.n = n;
	}

	public void assertSanity() {
		if (n != n) {
			throw new AssertionError("This statement is false.");
		}
	}
}
