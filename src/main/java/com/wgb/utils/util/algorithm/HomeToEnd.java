package com.wgb.utils.util.algorithm;

import lombok.extern.slf4j.Slf4j;

/**
 * [2,1,3,0,1]目标在起点，该目标可以跳到的位置范围是加目标位置对应的数值
 *
 * break和continue都是对该层循环起作用，对外层循环无影响。
 *
 * @author INNERPEACE
 * @date 2020/7/3
 */

@Slf4j
public class HomeToEnd {
	public static void main(String[] args) {


//		int[] data = {2, 3, 0, 1, 0, 2};
		int[] data = {2, 3, 0, 1, 1, 2};
		HomeToEnd homeToEnd = new HomeToEnd();
		boolean canHomeToEndMethodOne = homeToEnd.isCanHomeToEndMethodOne(data);
		log.info("canHomeToEndMethodOne:{}", canHomeToEndMethodOne);
		boolean canHomeToEndMethodTwo = homeToEnd.isCanHomeToEndMethodTwo(data);
		log.info("canHomeToEndMethodTwo:{}", canHomeToEndMethodTwo);
	}

	public boolean isCanHomeToEndMethodOne(int[] data) {
		if (data == null || data.length == 0) {
			return false;
		}
		boolean[] can = new boolean[data.length];
		can[0] = true;
		for (int i = 1; i < data.length; i++) {
			for (int j = 0; j < i; j++) {
				if (can[j] && (i - j) <= data[j]) {
					can[i] = true;
					break;
				}
			}
		}
		return can[data.length - 1];
	}



	public boolean isCanHomeToEndMethodTwo(int[] data) {
		if (data == null || data.length == 0) {
			return false;
		}
		for (int i = 1; i < data.length; i++) {
			boolean can = false;
			for (int j = 0; j < i; j++) {
				if ((i - j) <= data[j]) {
					can = true;
					break;
				}
			}
			if (!can) {
				return false;
			}
		}
		return true;
	}
}
