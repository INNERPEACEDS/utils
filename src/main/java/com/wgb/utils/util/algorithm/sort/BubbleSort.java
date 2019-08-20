package com.wgb.utils.util.algorithm.sort;

/**
 * 冒泡排序
 * 时间复杂度是O(N^2)
 * @author INNERPEACE
 * @date 2019/8/20
 */
public class BubbleSort {
	public static void bubbleSort(int[] array) {
		sort(array, 0, array.length -1);
	}

	public static void sort(int[] array, int start, int end) {
		if (array == null) {
			System.out.println("冒泡排序异常，排序数组为空!");
			return;
		}
		if (start < 0 || start > end) {
			System.out.println("请输入合法的数组下标!");
			return;
		}
		int i;
		boolean swapped;
		do {
			swapped = false;
			for (i = start + 1; i <= end; i++) {
				if (array[i - 1] > array[i]) {
					int temp;
					temp = array[i-1];
					array[i-1] = array[i];
					array[i] = temp;
					swapped = true;
				}
			}
		} while (swapped);
	}

	public static void bubbleDescendingSort(int[] array) {
		descendingSort(array, 0, array.length-1);
	}

	/**
	 * 冒泡降序
	 * @param array
	 * @param start
	 * @param end
	 */
	public static void descendingSort(int[] array, int start, int end) {
		if (array == null) {
			System.out.println("冒泡排序异常，排序数组为空!");
			return;
		}
		if (start < 0 || start > end) {
			System.out.println("请输入合法的数组下标!");
		}
		int i;
		boolean swapped;
		do {
			swapped = false;
			for (i = start + 1; i <= end; i++) {
				if (array[i - 1] < array[i]) {
					int temp;
					temp = array[i-1];
					array[i-1] = array[i];
					array[i] = temp;
					swapped = true;
				}
			}
		} while (swapped);
	}

}
