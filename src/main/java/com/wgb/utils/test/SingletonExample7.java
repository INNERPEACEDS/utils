package com.wgb.utils.test;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.concurrent.ThreadSafe;

/**
 * @author INNERPEACE
 * @date 2020/4/23 10:21
 */
@ThreadSafe
//@Recommend
@Slf4j
public class SingletonExample7 {

	// 私有构造函数
	private SingletonExample7() {
		log.info("SingletonExample7");
	}

	public static SingletonExample7 getInstance(int i) {
		log.info("第{}次", i);
		return Singleton.INSTANCE.getInstance();
	}

	private enum Singleton {
		INSTANCE;

		private SingletonExample7 singleton;

		// JVM保证这个方法绝对只调用一次
		Singleton() {
			log.info("Singleton");
			singleton = new SingletonExample7();
		}

		public SingletonExample7 getInstance() {
			return singleton;
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			int count = i;
			new Runnable(){
				@Override
				public void run() {
					SingletonExample7.getInstance(count);
				}
			}.run();
		}


	}
}