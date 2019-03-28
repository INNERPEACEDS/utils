package com.wgb.utils.mode.singleton;

/**
 * @author : innerpeace
 * @date : 2018/11/22
 */
public class Singleton6 {
	/**
	 * 标记位
	 */
	private volatile static boolean flag;

	/**
	 * 私有构造方法，防止被实例化
	 */
	private Singleton6() {
		if(!flag){
			flag = true;
		}else{
			throw new RuntimeException("不能多次创建单例对象");
		}
	}

	/**
	 * 此处使用一个内部类来维护单例
	 */
	private static class SingletonFactory {
		private static Singleton6 instance = new Singleton6();
	}

	/**
	 * 获取实例
	 * @return
	 */
	public static Singleton6 getInstance() {
		return SingletonFactory.instance;
	}

	/**
	 * 如果该对象被用于序列化，可以保证对象在序列化前后保持一致
	 * @return
	 */
	private Object readResolve() {
		return getInstance();
	}
}
