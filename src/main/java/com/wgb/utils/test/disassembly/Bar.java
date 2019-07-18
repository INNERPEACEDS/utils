package com.wgb.utils.test.disassembly;

/**
 * @author INNERPEACE
 * @date 2019/7/4
 **/
public class Bar {
	int a = 1;
	static int b = 2;

	public int sum(int c) {
		return a + b + c;
	}

	public static void main(String[] args) {
		new Bar().sum(3);

	}
}
