package com.wgb.utils.util.java.one.point.eight.lambda;

/**
 * @author INNERPEACE
 * @date 2019/8/5
 **/
public class MyLambdaImpl implements MyLambda {

	@Override
	public void test1(String y) {

	}

	public static void main(String[] args) {
		MyLambda myLambda = new MyLambdaImpl();
		System.out.print(myLambda.test3());

		MyLambda m = y -> System.out.println(y);
		m.test1("你好");
	}
}
