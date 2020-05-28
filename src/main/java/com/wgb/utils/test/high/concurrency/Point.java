package com.wgb.utils.test.high.concurrency;

/**
 * 清单4.6 DelegatingVehicleTracker使用不可变的Point类
 * @author INNERPEACE
 * @date 2020/4/27 9:24
 */
public class Point {
	private final int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "x:" + x + ",y:" + y;
	}
}
