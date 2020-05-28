package com.wgb.utils.util.java.one.point.eight.lambda;

/**
 * 1.8新特性：
 * 1.Lambda 表达式： Lambda 允许把函数作为一个方法的参数（函数作为参数传递到方法中）。
 * @author INNERPEACE
 * @date 2019/8/5
 **/
public class MyLambdaImpl implements MyLambda {

	@Override
	public void test1(String y) {
//		System.out.println(y + "a" + y);
	}

	public static void main(String[] args) {
		MyLambda myLambda = new MyLambdaImpl();
		System.out.print(myLambda.test3());
		MyLambda m = y -> System.out.println(y);
//		myLambda.test1("好的呀");
		m.test1("好的呀");
	}
}
