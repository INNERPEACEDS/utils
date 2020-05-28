package com.wgb.utils.test.high.concurrency;

import java.util.HashSet;
import java.util.Set;

/**
 * 清单3.11 构造于底层可变对象之上的不可变类
 * @author INNERPEACE
 * @date 2020/4/24 10:41
 */
public final class ThreadStooges {
	private final Set<String> stooges = new HashSet<>();

	ThreadStooges(){
		stooges.add("Moe");
		stooges.add("Larry");
		stooges.add("Curly");
	}

	public boolean isStooges(String name) {
		return stooges.contains(name);
	}
}
