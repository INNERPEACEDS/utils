package com.wgb.utils.util.algorithm.sort;

/**
 * 希尔排序
 * 时间复杂度O(n^(1.3—2))
 * @author INNERPEACE
 * @date 2019/8/20
 */
public class ShellSort {
	public static void shellSort(int[] array) {
		sort(array, array.length);
	}

	public static void sort(int[] array, int len) {
		int h = 1;
		while (h < len / 3) {
			h = 3 * h + 1;
		}
		while (h >= 1) {
			for (int i = h; i < len; i++) {
				int cur = array[i];
				int j = i - h;
				while (j >= 0 && array[j] > cur) {
					array[j + h] = array[j];
					j = j - h;
				}
				array[j + h] = cur;
			}
			h = h / 3;
		}
	}
}
