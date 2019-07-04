package com.wgb.utils.test;

/**
 * @author INNERPEACE
 * @date 2019/7/2 18:41
 **/
public class Calculate1 {
	public int count1 = 300;

	public void count(Integer maxCount) {
		for (int i = 0; i < maxCount; i++) {
			count1 = count1 + i;
		}
	}

	public int getCount1() {
		return count1;
	}
}
