package com.wgb.utils.test;

import lombok.extern.slf4j.Slf4j;

/**
 * a.当数值范围为-128~127时：如果两个new出来Integer对象，即使值相同，通过“==”比较结果为false，但两个对象直接赋值，则通过“==”比较结果为“true，这一点与String非常相似。
 *
 * b.当数值不在-128~127时，无论通过哪种方式，即使两个对象的值相等，通过“==”比较，其结果为false；
 *
 * c.当一个Integer对象直接与一个int基本数据类型通过“==”比较，其结果与第一点相同；
 *
 * d.Integer对象的hash值为数值本身；
 * @author INNERPEACE
 * @date 2020/7/6
 */
@Slf4j
public class IntegerTest {
	public static void main(String[] args) {
		/*int i = 10;
		for (; ; ) {
			if (i > 20) {
				return;
			} else{
				log.info("i={}", ++i);
			}
		}*/
		Integer i0 = new Integer("127");
		Integer i1 = new Integer("127");
		Integer i2 = new Integer("128");
		Integer i3 = new Integer("128");
		int int0 = 127;
		int int1 = 128;
		int int2 = 128;
		// 1.两个对象相比，对象肯定不一样，false
		log.info("i0=i1:{},i2=i3:{}", i0 == i1, i2 == i3);
		// 2.Integer类型和int类型 == 比较，自动解装箱，比较值的大小，true
		log.info("i0=int0:{}，i2=int1:{}", i0 == int0, i2 == int1);
		// 3.范围在-128~127的数字直接自动装箱，拿的对象都是同一个
		Integer i11 = 127;
		Integer i22 = 127;
		log.info("i11 == i22:{}", i11 == i22);
		i11 = 128;
		i22 = 128;
		log.info("i11 == i22:{}", i11 == i22);
	}
}
