package com.wgb.utils.test.high.concurrency;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.util.Random;

/**
 * 清单5.20 使用Memoizer为因式分解的servlet缓存结果
 * @author INNERPEACE
 * @date 2020/5/7 13:35
 */
@Slf4j
public class Factorizer  {

	public static void main(String[] args){
		new Factorizer().service();
	}

	private final Computable<BigInteger, BigInteger[]> c = this::factor;

	public BigInteger[] factor(BigInteger arg) {
		log.info("arg:{}", arg);
		for (BigInteger i = new BigInteger("2"); i.compareTo(arg) < 0; i = i.add(BigInteger.ONE)) {
			BigInteger[] divideAndRemainder = arg.divideAndRemainder(i);
			log.info("arg:{},divide:{}divideAndRemainder:{}", arg, i, divideAndRemainder);
			if (divideAndRemainder[1].compareTo(BigInteger.ZERO) == 0) {
				BigInteger[] bigIntegers = new BigInteger[2];
				bigIntegers[0] = i;
				bigIntegers[1] = divideAndRemainder[0];
				return bigIntegers;
			}
		}
		return null;
	}

	private final Computable<BigInteger, BigInteger[]> cache = new Memoizer<>(c);

	public void service() {
		try {
			BigInteger i = extractFromRequest();
			encodeIntoResponse(cache.compute(i));
		} catch (InterruptedException e) {
			e.printStackTrace();
			encodeError("factorization interruppted");
		}
	}

	public void encodeError(String errorMsg) {
		log.error("中断异常：{}", errorMsg);
	}

	public void encodeIntoResponse(BigInteger[] bigInteger) {
		log.info("因式分解的值：{}", (Object) bigInteger);

	}

	public BigInteger extractFromRequest() {
		String value = null;
		if (value == null) {
			value = String.valueOf(new Random().nextInt(100000000));
		}
		return new BigInteger(value);
	}

	public String getServletInfo() {
		return null;
	}

	public void destroy() {

	}
}
