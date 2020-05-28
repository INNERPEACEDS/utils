package com.wgb.utils.test.high.concurrency;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 清单4.10 NumberRange类没有完整的保护它的不变约束（不要这样做）
 * @author INNERPEACE
 * @date 2020/4/28 11:42
 */
@NotThreadSafe
public class NumberRange {
	// 不变约束 lower <= upper

	private final AtomicInteger lower = new AtomicInteger(0);
	private final AtomicInteger upper = new AtomicInteger(0);

	public void setLower(int i) {
		// 警告 -- 不安全的“检查再运行”
		if (i > upper.get()) {
			throw new IllegalArgumentException("can't set lower to " + i + " > upper");
		}
		lower.set(i);
	}

	public void setUpper(int i) {
		// 警告 -- 不安全的“检查再运行”
		if (i < lower.get()) {
			throw new IllegalArgumentException("" +
					"can't set upper to " + i + " < lower");
		}
		upper.set(i);
	}

	public boolean isInRange(int i) {
		return (i >= lower.get() && i <= upper.get());
	}
}
