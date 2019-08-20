package com.wgb.utils.util.algorithm.sort;

/**
 * 归并排序
 * 时间复杂度O(n log n)
 * @author INNERPEACE
 * @date 2019/8/19
 */
public class MergeSort {

	public static void mergeSort(int[] arr) {
		if (arr == null || arr.length <= 0) {
			System.out.println("使用归并排序异常，数据为空，不能排序");
			return;
		}
		sort(arr, 0, arr.length - 1);
	}

	/**
	 * 排序
	 * @param arr 数组
	 * @param l 数组第一个数据下标
	 * @param r 数组最后一个数据下标
	 */
	public static void sort(int[] arr, int l, int r) {
		if(l == r) {
			return;
		}
		int mid = l + ((r - l) >> 1);
		sort(arr, l, mid);
		sort(arr, mid + 1, r);
		merge(arr, l, mid, r);
	}

	/**
	 * 合并
	 * @param arr 数组
	 * @param l 数组第一个数据下标
	 * @param mid 数据中间数据下标
	 * @param r 数据最后一个数据下标
	 */
	private static void merge(int[] arr, int l, int mid, int r) {
		int[] temp = new int[r - l + 1];
		int i = 0;
		int p1 = l;
		int p2 = mid + 1;
		// 比较左右两部分的元素，哪个小，把那个元素填入temp中
		while (p1 <= mid && p2 <= r) {
			temp[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
		}
		// 上面的循环退出后，把剩余的元素依次填入到temp中
		// 以下两个while只有一个会执行
		while(p1 <= mid) {
			temp[i++] = arr[p1++];
		}
		while(p2 <= r) {
			temp[i++] = arr[p2++];
		}
		// 把最终的排序的结果复制给原数组
		for(i = 0; i < temp.length; i++) {
			arr[l + i] = temp[i];
		}
	}




	public static void mergeDescendingSort(int[] arr) {
		if (arr == null || arr.length <= 0) {
			System.out.println("使用归并排序异常，数据为空，不能排序");
			return;
		}
		descendingSort(arr, 0, arr.length - 1);
	}

	/**
	 * 排序
	 * @param arr 数组
	 * @param l 数组第一个数据下标
	 * @param r 数组最后一个数据下标
	 */
	public static void descendingSort(int[] arr, int l, int r) {
		if(l == r) {
			return;
		}
		int mid = l + ((r - l) >> 1);
		descendingSort(arr, l, mid);
		descendingSort(arr, mid + 1, r);
		descendingMerge(arr, l, mid, r);
	}

	/**
	 * 合并
	 * @param arr 数组
	 * @param l 数组第一个数据下标
	 * @param mid 数据中间数据下标
	 * @param r 数据最后一个数据下标
	 */
	private static void descendingMerge(int[] arr, int l, int mid, int r) {
		int[] temp = new int[r - l + 1];
		int i = 0;
		int p1 = l;
		int p2 = mid + 1;
		// 比较左右两部分的元素，哪个小，把那个元素填入temp中
		while (p1 <= mid && p2 <= r) {
			temp[i++] = arr[p1] > arr[p2] ? arr[p1++] : arr[p2++];
		}
		// 上面的循环退出后，把剩余的元素依次填入到temp中
		// 以下两个while只有一个会执行
		while(p1 <= mid) {
			temp[i++] = arr[p1++];
		}
		while(p2 <= r) {
			temp[i++] = arr[p2++];
		}
		// 把最终的排序的结果复制给原数组
		for(i = 0; i < temp.length; i++) {
			arr[l + i] = temp[i];
		}
	}
}
