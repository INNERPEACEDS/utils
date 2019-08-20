package com.wgb.utils.util.random;

import java.util.Random;

/**
 * @author INNERPEACE
 * @date 2019/8/19
 */
public class RandomUtil {
	private static final Random RANDOM = new Random();

	/**
	 * 生成length个随机数，取值范围是[min, max]
	 * @param length 随机数个数
	 * @param min 取值范围最小值
	 * @param max 取值范围最大值
	 * @return
	 */
	public static int[] getRandomData(int length, int min, int max) {
		if (length <= 0) {
			System.out.println("请输入有效的长度");
			return null;
		}
		if ((max - min) < 0) {
			System.out.println("请确保取值范围的正确性");
			return null;
		}
		int[] data = new int[length];
		for (int i = 0; i < length; i++) {
			data[i] = random(min, max);
		}
		return data;
	}

	public static int random(int min, int max) {
		return RANDOM.nextInt(max - min + 1) + min;
	}
}
