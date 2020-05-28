package com.wgb.utils.test.high.concurrency;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * 清单7.3 不可靠的取消把生产者置于阻塞的操作中（不要这样做）
 * @author INNERPEACE
 * @date 2020/5/19 17:04
 */
@Slf4j
public class BrokenPrimeProducer extends Thread {
	private final BlockingQueue<BigInteger> queue;
	private volatile boolean cancelled = false;
	private volatile boolean needMorePrimes = true;

	BrokenPrimeProducer(BlockingQueue queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			BigInteger p = BigInteger.ONE;
			while (!cancelled) {
				queue.put(p = p.nextProbablePrime());
			}
		} catch (InterruptedException e) {
			log.info("异常", e);
		}
	}

	public void cancel() {
		cancelled = true;
	}
	public void setNeedMorePrimes(boolean flag) {
		log.info("修改needMorePrimes成：{}", flag);
		needMorePrimes = flag;
	}

	public void consumePrime(BrokenPrimeProducer brokenPrimeProducer) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.submit(brokenPrimeProducer);
		brokenPrimeProducer.start();
		executorService.submit(new Runnable() {
			@Override
			@SneakyThrows
			public void run() {
				SECONDS.sleep(5);
				brokenPrimeProducer.setNeedMorePrimes(false);
			}
		});
		executorService.shutdown();
		try {
			while (needMorePrimes()) {
				consume(brokenPrimeProducer.queue.take());
			}
		} catch (Exception e) {
			log.error("客户端异常", e);
		} finally {
			brokenPrimeProducer.cancel();
		}
	}

	public boolean needMorePrimes() {
		/*Random random = new Random();
		int i = random.nextInt(10000);
		return i % 2 == 0;*/
		return needMorePrimes;
	}

	public static void consume(BigInteger data) {
		log.info("拿取数据：{}", data);
	}

	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<BigInteger> queue = new LinkedBlockingQueue<>();
		BrokenPrimeProducer brokenPrimeProducer = new BrokenPrimeProducer(queue);
		brokenPrimeProducer.consumePrime(brokenPrimeProducer);
	}
}
