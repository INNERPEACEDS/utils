package com.wgb.utils.util.algorithm.sort;

/**
 * 插入算法
 * 时间复杂度O(N^2)
 * @author INNERPEACE
 * @date 2019/8/20
 */
public class InsertionSort {
	public static void insertionSort(int[] array) {
		sort(array, array.length);
	}

	public static void sort(int[] array , int number_of_elements) {
		int iter, jter;
		for (iter = 1; iter < number_of_elements; iter++) {
			int current_element = array[iter];
			jter = iter - 1;
			while (jter >= 0 && array[jter] > current_element) {
				array[jter + 1] = array[jter];
				jter--;
			}
			array[jter + 1] = current_element;
		}
	}

	public static void insertionDescendingSort(int[] array) {
		descendingSort(array, array.length);
	}

	/**
	 * 插入降序
	 * @param array
	 * @param number_of_elements
	 */
	public static void descendingSort(int[] array , int number_of_elements) {
		int iter, jter;
		for (iter = 1; iter < number_of_elements; iter++) {
			int current_element = array[iter];
			jter = iter - 1;
			while (jter >= 0 && array[jter] < current_element) {
				array[jter + 1] = array[jter];
				jter--;
			}
			array[jter + 1] = current_element;
		}
	}


}
