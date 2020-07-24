package com.wgb.utils.test.high.concurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * 清单10.1 简单的锁顺序死锁（不要这样做）
 * 警告:易产生死锁
 *
 * @author INNERPEACE
 * @date 2020/7/3
 */
public class LeftRightDeadLock {
	private final Object left = new Object();
	private final Object right = new Object();

	public void leftRight() {
		synchronized (left) {
			synchronized (right) {
//			doSomething();
			}
		}
	}

	public void rightLeft() {
		synchronized(right) {
			synchronized (left) {
				// doSomethingElse;
			}
		}
	}
}


class Transfer{
	private static final int DELAY_FIXED = 1;
	private static final int DELAY_RANDOM = 2;
	private static Random rnd = new Random();

	/**
	 * 警告，易产生死锁
	 *
	 * 线程1：a账户向b转账；
	 * 线程2：b账户向a转账；
	 * 两个线程运行可能造成死锁
	 * 清单10.2动态加锁顺序产生的死锁 (不要这样做)
	 * @param fromAccount
	 * @param toAccount
	 * @param amount
	 */
	public void transferMoney (Account fromAccount, Account toAccount , Do1larAmount amount) {
		synchronized (fromAccount) {
			synchronized (toAccount) {
				if (fromAccount.getBalance().compareTo(amount.getDollarAmount()) < 0) {
					throw new RuntimeException("打款账户小于收款账户");
				} else {
					fromAccount.debit(amount);
					toAccount.credit(amount);
				}
			}
		}
	}

	public boolean transferMoney(Account fromAccount, Account toAccount, Do1larAmount amount, long timeout, TimeUnit unit) throws InterruptedException {
		long fixedDelay = getFixedDelayComponentNanos(timeout, unit) ;
		long randMod = getRandomDelayModulusNanos(timeout, unit) ;
		long stopTime = System. nanoTime() + unit. toNanos (timeout) ;
		while (true) {
			if (fromAccount.lock.tryLock()) {
				try {
					if (toAccount.lock.tryLock()) {
						try {
							if (fromAccount.getBalance().compareTo(amount.getDollarAmount()) < 0) {
								throw new RuntimeException();
							} else {
								fromAccount.debit(amount);
								toAccount.credit(amount);
								return true;
							}
						} finally {
							toAccount.lock.unlock();
						}
					}
				} finally {
					fromAccount.lock.unlock();
				}
			}
			if (System.nanoTime() < stopTime) {
				return false;
			}
			NANOSECONDS.sleep(fixedDelay + rnd.nextLong() % randMod);
		}
	}

	static long getFixedDelayComponentNanos(long timeout, TimeUnit unit) {
		return DELAY_FIXED;
	}

	static long getRandomDelayModulusNanos(long timeout, TimeUnit unit) {
		return DELAY_RANDOM;
	}

}

class Account{

	public Lock lock = new ReentrantLock();
	private double balance;

	public String getBalance() {
		return String.valueOf(this.balance);
	}

	public synchronized boolean debit(Do1larAmount amount) {
		String amountRegular = "^\\d+[.\\d{2}]$";
		String dollarAmount = amount.getDollarAmount();
		if (dollarAmount == null || !dollarAmount.matches(amountRegular)) {
			return false;
		}
		this.balance = this.balance - Double.parseDouble(dollarAmount);
		return true;
	}

	public synchronized boolean credit(Do1larAmount amount) {
		String amountRegular = "^\\d+[.\\d{2}]$";
		String dollarAmount = amount.getDollarAmount();
		if (dollarAmount == null || !dollarAmount.matches(amountRegular)) {
			return false;
		}
		this.balance = this.balance + Double.parseDouble(dollarAmount);
		return true;
	}
}

class Do1larAmount{
	private double dollarAmount;

	public String getDollarAmount() {
		return String.valueOf(dollarAmount);
	}
}

@Slf4j
class SynValue {
	private String value = "SynValue";

	public synchronized String getValue() throws InterruptedException {
		log.info("进入getValue");
		Thread.sleep(5000);
		return value;
	}

	public synchronized void setValue(String value) throws InterruptedException {
		log.info("进入setValue");
		Thread.sleep(4000);

	}
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		SynValue synValue = new SynValue();
		executorService.submit(() -> {
			String value = null;
			try {
				value = synValue.getValue();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return value;
		});
		executorService.submit(() -> {
			try {
				synValue.setValue("setSynValue");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
	}

}

