package com.wgb.utils.test.high.concurrency;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * 清单3.12 在不可变的容器中缓存数字和它的因数
 * @author INNERPEACE
 * @date 2020/4/24 11:13
 */
public class OneValueCache {
	private final BigInteger lastNumber;
	private final BigInteger[] lastFactors;

	OneValueCache(BigInteger i, BigInteger[] factors) {
		lastNumber = i;
		lastFactors = Arrays.copyOf(factors, factors.length);
	}

	public BigInteger[] getFactors(BigInteger i) {
		if (lastNumber == null || !lastNumber.equals(i)) {
			return null;
		} else {
			return Arrays.copyOf(lastFactors, lastFactors.length);
		}
	}
}
