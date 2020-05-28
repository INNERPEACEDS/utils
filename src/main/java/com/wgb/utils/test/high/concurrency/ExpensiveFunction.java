package com.wgb.utils.test.high.concurrency;

import java.math.BigInteger;

/**
 * @author INNERPEACE
 * @date 2020/5/7 10:30
 */
public class ExpensiveFunction implements Computable<String, BigInteger> {
	@Override
	public BigInteger compute(String arg) {
		return new BigInteger(arg);
	}
}
