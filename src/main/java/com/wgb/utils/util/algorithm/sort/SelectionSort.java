package com.wgb.utils.util.algorithm.sort;

/**
 * 选择排序
 * 时间复杂度O(N^2)
 * @author INNERPEACE
 * @date 2019/8/20
 */
public class SelectionSort {
	public static void SelectionSort(int[] array) {
		sort(array, 0, array.length - 1);
	}

	public static void sort(int[] array, int start, int end) {
		int i,j;
		int iMin;
		if (start < 0 || start > end) {
			System.out.println("请输入有效的数组下标!");
		}
		for(j = start; j <= end-1; j++) {
			iMin = j;
			for(i = j+1; i <= end; i++) {
				if (array[i] < array[iMin]) {
					iMin = i;
				}
			}
			if (iMin != j) {
				int temp;
				temp = array[j];
				array[j] = array[iMin];
				array[iMin] = temp;
			}
		}
	}
}
