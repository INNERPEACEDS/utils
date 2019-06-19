package com.wgb.utils.util.algorithm.hanoi;

import lombok.extern.slf4j.Slf4j;

/**
 * =================================================================#
 * 汉诺塔是由Edouard Lucas提出的数学谜题 ,
 * 他是19世纪的法国数学家.
 *
 * 有三个直立的柱子竖在地面上.
 * 第一个柱子有一组的盘子套在上面.
 * 这些盘子是平整的，中间带着孔，
 * 因此它们才能套在柱子上面.
 * 这组盘子有不同的直径，它们是依照直径从小到大来从高到低放置.
 *
 * 最小的盘在最高，最大的盘在最底部.
 *
 * 现在的任务是要把这一组的盘子从一个柱子全部地搬到另一个柱子上.
 *
 * 你只能一次从一个柱子上移动一个盘子到另一个柱子.
 * 允许把盘子重新移回到它原来的最初位置.
 * 你可以把一个小的盘子放在大的盘子上面,
 * 但不能把大的盘子放在小的盘子上面.
 * 请注意这一点.
 *
 * 对于这一组盘子，数量少时，只需要移动很少的次数就能达到要求.
 * 但随着这组盘子的数量的增加,
 * 移动的次数几乎成倍增长的,
 * 而移动的策略变得愈加复杂.
 *
 * 想了解更多的信息, 请访问 http://hanoi.kernelthread.com.
 *
 *
 *          ...                   ...                    ...
 *          | |                   | |                    | |
 *         _|_|_                  | |                    | |
 *        |_____|                 | |                    | |
 *       |_______|                | |                    | |
 *      |_________|               | |                    | |
 *     |___________|              | |                    | |
 *    |             |             | |                    | |
 *  .--------------------------------------------------------------.
 *  |**************************************************************|
 *           #1                   #2                      #3
 *
 * =================================================================#
 * @author INNERPEACE
 * @date 2019/5/16 16:15
 **/
@Slf4j
public class Hanoi {
	private static int moveCount = 0;

	public static void main (String[] args) {
		if (args.length != 1) {
			log.error("error:a single integer argument needed");
			System.exit(1);
		}
		Integer n = new Integer(args[0]);
		hDoHanoi(n, 3, 1, 2);
		log.info("移动次数：{}", moveCount);
		System.exit(0);
	}

	/**
	 * 汉诺塔递归
	 * @param n 汉诺塔层数
	 * @param c 最终承受盘子的柱子
	 * @param a 最初承受盘子的柱子
	 * @param b 中转承受盘子的柱子
	 */
	public static void hDoHanoi(int n, int c, int a, int b) {
		if (n > 0) {
			// 假设有64个盘子，把前63个看成整体，现在需要把63个放到b上
			hDoHanoi(n-1, b, a, c);
			// 再把a最后一个放到c上
			hMoveIt(a, c, n);
			// 再把b上的63个放到c上
			hDoHanoi(n-1, c, b, a);
		}
	}

	/**
	 * 移动
	 * @param from
	 * @param to
	 * @param n
	 */
	public static void hMoveIt(int from, int to, int n) {
		log.info("盘子[{}]移动: 柱子[{}] --> 柱子[{}]", n, from, to);
		moveCount++;
	}
}
