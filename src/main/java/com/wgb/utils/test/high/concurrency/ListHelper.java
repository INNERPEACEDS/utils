package com.wgb.utils.test.high.concurrency;

import javax.annotation.concurrent.ThreadSafe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 清单4.15 使用客户端加锁实现的“缺少即加入”
 * @author INNERPEACE
 * @date 2020/4/28 17:36
 */
@ThreadSafe
public class ListHelper<E> {
	public List<E> list = Collections.synchronizedList(new ArrayList<>());

	public boolean putIfAbsent(E x) {
		synchronized (list) {
			boolean absent = !list.contains(x);
			if (absent) {
				list.add(x);
			}
			return absent;
		}
	}
}
