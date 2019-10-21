package com.wgb.utils.test;

import java.io.IOException;

/**
 * @author INNERPEACE
 * @date 2019/10/16 9:55
 */
public class InputTest {
	public static void main(String[] args) throws IOException {
		while (true) {
			int value = System.in.read();
			if (value == 97) {
				break;
			}
			System.out.println(value);
		}
		// System.out.println(System.in.read());
	}
}
