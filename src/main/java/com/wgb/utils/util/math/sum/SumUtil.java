package com.wgb.utils.util.math.sum;

/**
 * @author INNERPEACE
 * @date 2019/9/25 9:28
 */
public class SumUtil {

	public static double sum(String[] strings) {
		double sum = 0;
		for (String str : strings) {
			sum += Double.parseDouble(str);
		}
		return sum;
	}

	public static void main(String[] args) {
		String[] str = {"500318", "500958", "498556", "498083", "500063", "499734", "502507", "500346", "498544", "500891"};
		double sum = sum(str);
		System.out.println(sum);
	}
}
