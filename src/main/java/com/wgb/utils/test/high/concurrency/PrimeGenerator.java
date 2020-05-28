package com.wgb.utils.test.high.concurrency;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * 清单7.1 使用volatile域保存取消状态
 * @author INNERPEACE
 * @date 2020/5/19 14:37
 */
@Slf4j
@ThreadSafe
public class PrimeGenerator implements Runnable {
	@GuardedBy("this")
	private final List<BigInteger> primes = new ArrayList<>();

	private volatile boolean cancelled;

	@SneakyThrows
	@Override
	public void run() {
		BigInteger p = BigInteger.ONE;
		while (!cancelled) {
			p = p.nextProbablePrime();
			synchronized (this) {
				primes.add(p);
				log.info("开始等待");
//				Thread.sleep(10000);
				throw new RuntimeException("运行异常");
			}
		}
	}

	public void cancel() {
		cancelled = true;
	}

	public List<BigInteger> get() {
		return new ArrayList<>(primes);
	}

	public static List<BigInteger> aSecondOfPrime() throws InterruptedException {
		PrimeGenerator primeGenerator = new PrimeGenerator();
		new Thread(primeGenerator).start();
		try {
			SECONDS.sleep(10);
		} catch (Exception e) {
			log.error("运行出错", e);
			primeGenerator.cancel();
		}
		return primeGenerator.get();
	}

	public static void main(String[] args) throws InterruptedException {
		List<BigInteger> bigIntegers = aSecondOfPrime();
		log.info("生产素数的最大值结果：{}", bigIntegers);

	}
}
