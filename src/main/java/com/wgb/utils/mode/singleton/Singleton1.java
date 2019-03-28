package com.wgb.utils.mode.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * 饿汉模式
 * @author : innerpeace
 * @date : 2018/11/22
 */
@Slf4j
public class Singleton1 {
	/**
	 * 持有私有静态实例，防止被引用
	 */
	private static Singleton1 instance = new Singleton1();

	/**
	 * 私有构造方法，防止被实例化
	 */
	private Singleton1() {}

	/**
	 * 静态工程方法，返回Singleton实例
	 * @return
	 */
	public static Singleton1 getInstance() {
		return instance;
	}

	/**
	 * 如果该对象被用于序列化，可以保证对象在序列化前后保持一致
	 *
	 * @return
	 */
	private Object readResolve() {
		return instance;
	}

	public static void say() {
		log.info("你好，say！");
	}

	public  void  say1(){
		log.info("你好say1");
	}

	public static void main(String[] args) {
		Singleton1 sing = Singleton1.getInstance();
		Singleton1 sing1 = Singleton1.getInstance();
		Singleton1 sing2 = Singleton1.getInstance();
		Singleton1 sing3 = Singleton1.getInstance();
		log.info("sing:{}",sing);
		log.info("sing1:{}",sing1);
		log.info("sing2:{}",sing2);
		log.info("sing3:{}",sing3);
		Singleton1.say();
	}
}
