package com.wgb.utils.util.algorithm.sort;

import com.wgb.utils.util.random.RandomUtil;

/**
 * 快速排序
 * 平均时间复杂度：O(nlogn)
 * @author INNERPEACE
 * @date 2019/8/20
 */
public class QuickSort {
	public static void quickSort(int[] array) {
		sort(array, 0, array.length - 1);
	}

	public static void sort(int[] array,int begin,int end) {
		if (array == null) {
			System.out.println("快速排序异常，排序数组为空!");
		}
		if( begin < end) {
			int pivot_idx = partition(array, begin, end);
			sort(array, begin, pivot_idx-1);
			sort(array, pivot_idx+1, end);
		}
	}

	static int partition(int[] array,int begin, int end) {
		int pivotIdx = RandomUtil.random(begin, end);
		int pivot = array[pivotIdx];
		swap(array, begin, pivotIdx);
		int i = begin + 1;
		int j = end;
		while(i <= j) {
			while ((i <= end) && (array[i] <= pivot)) {
				i++;
			}
			while ((j >= begin) && (array[j] > pivot)) {
				j--;
			}
			if (i < j) {
				swap(array, i, j);
			}
		}
		swap(array, begin, j);
		return j;
	}

	public static void swap(int[] array, int subscriptOne, int subscriptTwo) {
		int temp;
		temp = array[subscriptOne];
		array[subscriptOne] = array[subscriptTwo];
		array[subscriptTwo] = temp;
	}

	public static void quickDescendingSort(int[] array) {
		descendingSort(array, 0, array.length - 1);
	}

	public static void descendingSort(int[] array,int begin,int end) {
		if (array == null) {
			System.out.println("快速排序异常，排序数组为空!");
		}
		if( begin < end) {
			int pivot_idx = descendingPartition(array, begin, end);
			descendingSort(array, begin, pivot_idx-1);
			descendingSort(array, pivot_idx+1, end);
		}
	}

	static int descendingPartition(int[] array,int begin, int end) {
		int pivotIdx = RandomUtil.random(begin, end);
		int pivot = array[pivotIdx];
		swap(array, begin, pivotIdx);
		int i = begin + 1;
		int j = end;
		while(i <= j) {
			while ((i <= end) && (array[i] >= pivot)) {
				i++;
			}
			while ((j >= begin) && (array[j] < pivot)) {
				j--;
			}
			if (i < j) {
				swap(array, i, j);
			}
		}
		swap(array, begin, j);
		return j;
	}
}
