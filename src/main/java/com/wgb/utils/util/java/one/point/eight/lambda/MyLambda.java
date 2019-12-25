package com.wgb.utils.util.java.one.point.eight.lambda;

/**
 * 1.8新特性：
 * 3.默认方法：默认方法就是一个在接口里面有了一个实现的方法。
 * 注意点：a.函数式接口里允许定义默认方法；b.函数式接口里允许定义静态方法;c.函数式接口里允许定义 java.lang.Object 里的 public 方法
 * @author INNERPEACE
 * @date 2019/8/5
 **/
@FunctionalInterface
public interface MyLambda {

	void test1(String y);

	/**
	 * 重写Object中的equals方法
	 * @param obj
	 * @return
	 */
	@Override
	boolean equals(Object obj);

	// public void test2();

	default String test3() {
		return "3";
	}

	default String test4() {
		return "4";
	}

	static String staticMethod() {
		return "staticMethod";
	}

}
