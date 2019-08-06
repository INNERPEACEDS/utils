package com.wgb.utils.util.concurrency.thread.pool;

import lombok.extern.slf4j.Slf4j;
import java.lang.reflect.Method;
import java.util.Calendar;


/**
 * @author INNERPEACE
 * @date 2019/7/2
 **/
@Slf4j
public class ThreadPoolTool<T,V> implements Runnable {

	/**
	 * 使用线程执行类
	 */
	private  T t;

	/**
	 * 执行类方法
	 */
	private String methodName;

	/**
	 * 执行类方法参数
	 */
	private Object[] args;

	/**
	 * 执行返回结果
	 */
	private V v;

	public ThreadPoolTool(T t, String methodName, Object... args) {
		this.t = t;
		this.methodName = methodName;
		this.args = args;
	}

	@Override
	public void run() {
		try {
			Class<?> classType = t.getClass();
			// 获取Method对象
			Method method = classType.getDeclaredMethod(methodName, (Class<?>[]) getClassTypes(args));
			// 抑制Java的访问控制检查
			method.setAccessible(true);
			// 如果不加上上面这句，将会Error: TestPrivate can not access a member of class PrivateClass with modifiers "private"
			v = (V) method.invoke(t, args);
		} catch (Exception e) {
			log.error("线程执行异常", e);
		}
	}

	/**
	 * 获取参数的类类型
	 * @param args 参数
	 * @return
	 */
	public static Class[] getClassTypes(Object... args) {
		Class[] classTypes = new Class[args.length];
		for (int i = 0; i < args.length; i++) {
			classTypes[i] = args.getClass();
		}
		return classTypes;
	}

	public V getV() {
		return v;
	}
}
