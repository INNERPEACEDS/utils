package com.wgb.utils.util.print;

/**
 * @author INNERPEACE
 * @date 2019/8/19
 */
public class PrintUtil {

	public static void print(int[] arr) {
		if (arr == null || arr.length <= 0) {
			System.out.println("数据不存在，无法输出");
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "\t");
		}
		System.out.println();

	}

}
