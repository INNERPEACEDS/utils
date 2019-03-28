package com.wgb.utils.mode.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * 懒汉模式
 * @author : innerpeace
 * @date : 2018/11/22
 */
@Slf4j
public class Singleton2 {
	/**
	 * 持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载
	 */
	private static Singleton2 instance = null;

	/**
	 * 私有构造方法，防止被实例化
	 */
	private Singleton2() {
	}

	/**
	 * 静态工程方法，创建实例
	 * @return
	 */
	public static Singleton2 getInstance() {
		if (instance == null) {
			instance = new Singleton2();
		}
		return instance;
	}

	/**
	 * 如果该对象被用于序列化，可以保证对象在序列化前后保持一致
	 * @return
	 */
	public Object readResolve() {
		return instance;
	}

	public static void main(String[] args) {
		Singleton2 sing  =	Singleton2.getInstance();
		Singleton2 sing1  =	Singleton2.getInstance();
		Singleton2 sing2  =	Singleton2.getInstance();
		log.info("sing:{}",sing);
		log.info("sing1:{}",sing1);
		log.info("sing2:{}",sing2);
	}
}
