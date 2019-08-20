package com.wgb.utils.util.algorithm.sort;

/**
 * 基数排序
 * @author INNERPEACE
 * @date 2019/8/20
 */
public class RadixSort {
	/**
	 * @param number
	 * @param d      d表示最大的数有多少位
	 */
	public static void sort(int[] number, int d) {
		int k = 0;
		int n = 1;
		// 控制键值排序依据在哪一位
		int m = 1;
		// 数组的第一维表示可能的余数0-9
		int[][] temp = new int[10][number.length];
		// 数组orderp[i]用来表示该位是i的数的个数
		int[] order = new int[10];
		while (m <= d) {
			for (int i = 0; i < number.length; i++) {
				int lsd = ((number[i] / n) % 10);
				temp[lsd][order[lsd]] = number[i];
				order[lsd]++;
			}
			for (int i = 0; i < 10; i++) {
				if (order[i] != 0) {
					for (int j = 0; j < order[i]; j++) {
						number[k] = temp[i][j];
						k++;
					}
				}
				order[i] = 0;
			}
			n *= 10;
			k = 0;
			m++;
		}
	}
}
