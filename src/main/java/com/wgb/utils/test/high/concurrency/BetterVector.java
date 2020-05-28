package com.wgb.utils.test.high.concurrency;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Vector;

/**
 * 清单4.13 扩展的Vector包含一个“缺少即加入”方法
 * @author INNERPEACE
 * @date 2020/4/28 17:09
 */
@ThreadSafe
public class BetterVector<E> extends Vector<E> {
	public synchronized boolean putIfAbsent(E x) {
		boolean absent = !contains(x);
		if (absent) {
			add(x);
		}
		return absent;
	}
}
