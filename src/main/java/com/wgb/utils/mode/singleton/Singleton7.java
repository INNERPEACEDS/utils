package com.wgb.utils.mode.singleton;

import java.io.*;

/**
 * @author : innerpeace
 * @date : 2018/11/22
 */
public class Singleton7 implements Serializable {
	private volatile static boolean flag;

	/**
	 * 私有构造方法，防止被实例化
	 */
	private Singleton7() {
		if(!flag){
			flag = false;
		}else{
			throw new RuntimeException("不能多次创建单例对象");
		}
	}

	/**
	 * 测试方式,把单例对象序列化后再反序列化从而获得一个新的对象 就相当于复制了一个单例对象
	 * @return
	 * @throws Exception
	 */
	public Singleton7 copy() throws Exception{
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(Singleton7.getInstance());

		InputStream is = new ByteArrayInputStream(os.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(is);
		Singleton7 obj = (Singleton7) ois.readObject();
		return obj;
	}

	/**
	 * 此处使用一个内部类来维护单例
	 */
	private static class SingletonFactory {
		private static Singleton7 instance = new Singleton7();
	}

	/**
	 * 获取实例
	 * @return
	 */
	public static Singleton7 getInstance() {
		return SingletonFactory.instance;
	}

	/**
	 * 如果该对象被用于序列化，可以保证对象在序列化前后保持一致
	 * 把这个方法注释前和注释后来运行测试代码观察结果
	 * @return
	 */
	/*private Object readResolve() {
		return getInstance();
	}*/
}
