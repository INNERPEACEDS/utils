package com.wgb.utils.util.algorithm;

import com.wgb.utils.util.algorithm.queen.NQueen;
import com.wgb.utils.util.algorithm.sort.*;
import com.wgb.utils.util.print.PrintUtil;
import com.wgb.utils.util.random.RandomUtil;

/**
 * @author INNERPEACE
 * @date 2019/5/16
 **/
public class AlgorithmTest {
	public static void main(String[] args) {
		// NQueenTest();
		// mergeSortTest();
		sortTest();
	}

	public static void sortTest() {
		// 选择排序、快速排序、希尔排序、堆排序不是稳定的排序算法，
		// 冒泡排序、插入排序、归并排序和基数排序是稳定的排序算法
		int[] randomData = RandomUtil.getRandomData(10, 142, 243);
		System.out.println("排序前数据：");
		PrintUtil.print(randomData);
		// MergeSort.mergeSort(randomData);
		// MergeSort.mergeDescendingSort(randomData);
		// BubbleSort.bubbleSort(randomData);
		// BubbleSort.sort(randomData, 4, 8);
		// BubbleSort.bubbleDescendingSort(randomData);
		// SelectionSort.SelectionSort(randomData);
		// SelectionSort.sort(randomData, 4, 2);
		// QuickSort.quickSort(randomData);
		// QuickSort.quickDescendingSort(randomData);
		// InsertionSort.insertionSort(randomData);
		// InsertionSort.insertionDescendingSort(randomData);
		// ShellSort.shellSort(randomData);
		// RadixSort.sort(randomData, 3);
		// HeapSort.minHeapSort(randomData);
		// HeapSort.maxHeapSort(randomData);
		System.out.println("排序后数据：");
		PrintUtil.print(randomData);
	}
	public static void NQueenTest() {
		NQueen nQueen = new NQueen(20);
		nQueen.solve();
	}
}
/**
 * 1=1
 * 2=0
 * 3=0
 * 4=2
 * 5=10
 * 6=4
 * 7=40
 * 8=92
 * 9=352
 * 10=724
 */
