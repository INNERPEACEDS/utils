package com.wgb.utils.util.algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 398.随机数索引 蓄水池
 * @author INNERPEACE
 * @date 2020/7/17
 */
@Slf4j
public class Solution {
	private int[] nums;

	public Solution(int[] nums) {
		this.nums = nums;
	}

	public int pick(int target) {
		Random random = new Random();
		int index = -1;
		int n = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == target) {
				if (random.nextInt() % ++n == 0) {
					index = i;
				}
			}
		}
		return index;
	}

	public static void main(String[] args) {
		// 测试概率:四个2，两个三，四个4，四个7，两个8，两个9，
		int[] nums = {2, 4, 7, 3, 9, 8, 4, 0, 1, 9, 4, 4, 8, 0, 1, 2, 2, 3, 6, 2, 6, 7, 7, 7};
		Solution solution = new Solution(nums);
		// 测试2的位置概率
		Map<Integer, Integer> twoProbability = new HashMap<>();
		int sum = 100000;
		for (int i = 0; i < sum; i++) {
			int index = solution.pick(2);
			Integer integer = twoProbability.get(index);
			if (integer == null) {
				integer = 0;
			}
			twoProbability.put(index, ++integer);
		}
		for (Map.Entry<Integer, Integer> entry : twoProbability.entrySet()) {
			Integer key = entry.getKey();
			Integer value = entry.getValue();
			log.info("下标【{}】出现【{}】次，概率为【{}】", key, value, new BigDecimal(value).divide(new BigDecimal(sum)).doubleValue());
		}
	}
}
