package com.wgb.utils.test;

/**
 * @author INNERPEACE
 * @date 2019/7/2 18:44
 **/
public class Calculate2 {
	public int count2 = -300;

	public void count(Integer maxCount) {
		for (int i = maxCount; i > 0; i++) {
			count2 = count2 - i;
		}
	}

	public int getCount2() {
		return count2;
	}
}
