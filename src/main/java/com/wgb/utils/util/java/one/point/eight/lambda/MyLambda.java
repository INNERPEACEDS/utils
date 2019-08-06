package com.wgb.utils.util.java.one.point.eight.lambda;

/**
 * @author INNERPEACE
 * @date 2019/8/5
 **/
@FunctionalInterface
public interface MyLambda {
	void test1(String y);

	// public void test2();

	default String test3() {
		return "3";
	}

	default String test4() {
		return "4";
	}

}
