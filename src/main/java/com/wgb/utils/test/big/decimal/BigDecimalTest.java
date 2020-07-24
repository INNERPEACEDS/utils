package com.wgb.utils.test.big.decimal;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @author INNERPEACE
 * @date 2020/7/2
 */
@Slf4j
public class BigDecimalTest {
	public static void main(String[] args) {
		// 1.ROUND_UP 直接升位（不管正负数，不管后面跟大于4的还是小于5的数）（不管后面跟大于4的还是小于5的数）(求负数时，先求绝对值得结果，最后加负号)  （用表达式表示：a代表原数据，则
		// 先求|a|的结果为b，b直接升位后为c，若原数据为正数则结果为c，否则为-c）
		BigDecimal roundUp = new BigDecimal("2.225667").setScale(2, BigDecimal.ROUND_UP);
		// 跟上面相反，进位处理 2.23
		log.info("ROUND_UP result ：{}", roundUp);
		BigDecimal roundUp1 = new BigDecimal("2.224667").setScale(2, BigDecimal.ROUND_UP);
		// 跟上面相反，进位处理 2.23
		log.info("ROUND_UP1 result ：{}", roundUp1);
		BigDecimal roundUp2 = new BigDecimal("-2.225667").setScale(2, BigDecimal.ROUND_UP);
		// 跟上面相反，进位处理 -2.23
		log.info("ROUND_UP2 result ：{}", roundUp2);
		BigDecimal roundUp3 = new BigDecimal("-2.224667").setScale(2, BigDecimal.ROUND_UP);
		// 跟上面相反，进位处理 -2.23
		log.info("ROUND_UP3 result ：{}\n", roundUp3);



		// 2.ROUND_DOWN 直接降位（不管正负号，不管后面跟大于4的还是小于5的数）(求负数时，先求绝对值得结果，最后加负号)  （用表达式表示：a代表原数据，则
		// 先求|a|的结果为b，b直接降位后为c，若原数据为正数则结果为c，否则为-c）
		BigDecimal roundDown = new BigDecimal("2.225667").setScale(2, BigDecimal.ROUND_DOWN);
		// 直接去掉多余的位数 2.22
		log.info("ROUND_DOWN result ：{}", roundDown);
		BigDecimal roundDown1 = new BigDecimal("2.224667").setScale(2, BigDecimal.ROUND_DOWN);
		// 直接去掉多余的位数 2.22
		log.info("ROUND_DOWN1 result ：{}", roundDown1);
		BigDecimal roundDown2 = new BigDecimal("-2.225667").setScale(2, BigDecimal.ROUND_DOWN);
		// 直接去掉多余的位数 -2.22
		log.info("ROUND_DOWN2 result ：{}", roundDown2);
		BigDecimal roundDown3 = new BigDecimal("-2.224667").setScale(2, BigDecimal.ROUND_DOWN);
		// 直接去掉多余的位数 -2.22
		log.info("ROUND_DOWN3 result ：{}\n", roundDown3);



		// 3. ROUND_CEILING  天花板（向上），正数进位向上，负数舍位向上
		BigDecimal boundCeiling = new BigDecimal("2.225667").setScale(2, BigDecimal.ROUND_CEILING);
		// 2.23 如果是正数，相当于BigDecimal.ROUND_UP
		log.info("ROUND_CEILING result ：{}", boundCeiling);
		BigDecimal boundCeiling1 = new BigDecimal("2.224667").setScale(2, BigDecimal.ROUND_CEILING);
		// 2.23 如果是正数，相当于BigDecimal.ROUND_UP
		log.info("ROUND_CEILING1 result ：{}", boundCeiling1);
		// -2.22 如果是负数，相当于BigDecimal.ROUND_DOWN
		BigDecimal boundCeiling2 = new BigDecimal("-2.225667").setScale(2, BigDecimal.ROUND_CEILING);
		log.info("ROUND_CEILING2 result ：{}", boundCeiling2);
		// -2.22 如果是负数，相当于BigDecimal.ROUND_DOWN
		BigDecimal boundCeiling3 = new BigDecimal("-2.224667").setScale(2, BigDecimal.ROUND_CEILING);
		log.info("ROUND_CEILING3 result ：{}\n", boundCeiling3);



		// 4. ROUND_FLOOR 地板（向下），正数舍位向下，负数进位向下
		BigDecimal roundFloor = new BigDecimal("2.225667").setScale(2, BigDecimal.ROUND_FLOOR);
		// 2.22 如果是正数，相当于BigDecimal.ROUND_DOWN
		log.info("ROUND_FLOOR result ：{}", roundFloor);
		BigDecimal roundFloor1 = new BigDecimal("2.224667").setScale(2, BigDecimal.ROUND_FLOOR);
		// 2.22 如果是正数，相当于BigDecimal.ROUND_DOWN
		log.info("ROUND_FLOOR1 result ：{}", roundFloor1);
		BigDecimal roundFloor2 = new BigDecimal("-2.225667").setScale(2, BigDecimal.ROUND_FLOOR);
		// -2.23 如果是负数，相当于BigDecimal.ROUND_HALF_UP
		log.info("ROUND_FLOOR2 result ：{}", roundFloor2);
		BigDecimal roundFloor3 = new BigDecimal("-2.224667").setScale(2, BigDecimal.ROUND_FLOOR);
		// -2.23 如果是负数，相当于BigDecimal.ROUND_HALF_UP
		log.info("ROUND_FLOOR3 result ：{}\n", roundFloor3);



		// 5. ROUND_HALF_UP
		BigDecimal roundHalfUp = new BigDecimal("2.225").setScale(2, BigDecimal.ROUND_HALF_UP);
		// 2.23  四舍五入（若舍弃部分>=.5，就进位）
		log.info("ROUND_HALF_UP result ：{}", roundHalfUp);
		BigDecimal roundHalfUp1 = new BigDecimal("2.224").setScale(2, BigDecimal.ROUND_HALF_UP);
		// 2.22  四舍五入（若舍弃部分>=.5，就进位）
		log.info("ROUND_HALF_UP1 result ：{}", roundHalfUp1);
		BigDecimal roundHalfUp2 = new BigDecimal("-2.225").setScale(2, BigDecimal.ROUND_HALF_UP);
		// -2.23  四舍五入（若舍弃部分>=.5，就进位）
		log.info("ROUND_HALF_UP2 result ：{}", roundHalfUp2);
		BigDecimal roundHalfUp3 = new BigDecimal("-2.224").setScale(2, BigDecimal.ROUND_HALF_UP);
		// -2.22  四舍五入（若舍弃部分>=.5，就进位）
		log.info("ROUND_HALF_UP3 result ：{}\n", roundHalfUp3);



		// 6. ROUND_HALF_DOWN
		BigDecimal roundHalfDown = new BigDecimal("2.226").setScale(2, BigDecimal.ROUND_HALF_DOWN);
		//2.23  四舍五入（若舍弃部分>.5,就进位）
		log.info("ROUND_HALF_DOWN result ：{}", roundHalfDown);
		BigDecimal roundHalfDown1 = new BigDecimal("2.225").setScale(2, BigDecimal.ROUND_HALF_DOWN);
		// 2.22  四舍五入（若舍弃部分>.5,就进位）
		log.info("ROUND_HALF_DOWN1 result ：{}", roundHalfDown1);
		BigDecimal roundHalfDown2 = new BigDecimal("-2.226").setScale(2, BigDecimal.ROUND_HALF_DOWN);
		// -2.23  四舍五入（若舍弃部分>.5,就进位）
		log.info("ROUND_HALF_DOWN2 result ：{}", roundHalfDown2);
		BigDecimal roundHalfDown3 = new BigDecimal("-2.225").setScale(2, BigDecimal.ROUND_HALF_DOWN);
		// -2.22  四舍五入（若舍弃部分>.5,就进位）
		log.info("ROUND_HALF_DOWN3 result ：{}\n", roundHalfDown3);



		// 7. ROUND_HALF_EVEN
		BigDecimal evenRoundHalfEven = new BigDecimal("2.226").setScale(2, BigDecimal.ROUND_HALF_EVEN);
		// 2.23 如果舍弃部分左边的数字为偶数，则作   ROUND_HALF_DOWN
		log.info("EVEN ROUND_HALF_EVEN result ：{}", evenRoundHalfEven);
		BigDecimal evenRoundHalfEven1 = new BigDecimal("2.225").setScale(2, BigDecimal.ROUND_HALF_EVEN);
		// 2.22 如果舍弃部分左边的数字为偶数，则作   ROUND_HALF_DOWN
		log.info("EVEN ROUND_HALF_EVEN1 result ：{}", evenRoundHalfEven1);
		BigDecimal evenRoundHalfEven2 = new BigDecimal("-2.226").setScale(2, BigDecimal.ROUND_HALF_EVEN);
		// -2.23 如果舍弃部分左边的数字为偶数，则作   ROUND_HALF_DOWN
		log.info("EVEN ROUND_HALF_EVEN2 result ：{}", evenRoundHalfEven2);
		BigDecimal evenRoundHalfEven3 = new BigDecimal("-2.225").setScale(2, BigDecimal.ROUND_HALF_EVEN);
		// -2.22 如果舍弃部分左边的数字为偶数，则作   ROUND_HALF_DOWN
		log.info("EVEN ROUND_HALF_EVEN3 result ：{}\n", evenRoundHalfEven3);


		BigDecimal oddRoundHalfEven = new BigDecimal("2.215").setScale(2, BigDecimal.ROUND_HALF_EVEN);
		// 2.22 如果舍弃部分左边的数字为奇数，则作   ROUND_HALF_UP
		log.info("ODD ROUND_HALF_EVEN result ：{}", oddRoundHalfEven);
		BigDecimal oddRoundHalfEven1 = new BigDecimal("2.214").setScale(2, BigDecimal.ROUND_HALF_EVEN);
		// 2.21 如果舍弃部分左边的数字为奇数，则作   ROUND_HALF_UP
		log.info("ODD ROUND_HALF_EVEN1 result ：{}", oddRoundHalfEven1);
		BigDecimal oddRoundHalfEven2 = new BigDecimal("-2.215").setScale(2, BigDecimal.ROUND_HALF_EVEN);
		// -2.22 如果舍弃部分左边的数字为奇数，则作   ROUND_HALF_UP
		log.info("ODD ROUND_HALF_EVEN2 result ：{}", oddRoundHalfEven2);
		BigDecimal oddRoundHalfEven3 = new BigDecimal("-2.214").setScale(2, BigDecimal.ROUND_HALF_EVEN);
		// -2.21 如果舍弃部分左边的数字为奇数，则作   ROUND_HALF_UP
		log.info("ODD ROUND_HALF_EVEN3 result ：{}", oddRoundHalfEven3);

		System.out.println("************************************");

		System.out.println("4.05: "+new BigDecimal("4.05").setScale(1, BigDecimal.ROUND_HALF_EVEN));//4.05: 4.0  down
		System.out.println("4.15: "+new BigDecimal("4.15").setScale(1, BigDecimal.ROUND_HALF_EVEN));//4.15: 4.2  up
		System.out.println("4.25: "+new BigDecimal("4.25").setScale(1, BigDecimal.ROUND_HALF_EVEN));//4.25: 4.2  down
		System.out.println("4.35: "+new BigDecimal("4.35").setScale(1, BigDecimal.ROUND_HALF_EVEN));//4.35: 4.4  up
		System.out.println("4.45: "+new BigDecimal("4.45").setScale(1, BigDecimal.ROUND_HALF_EVEN));//4.45: 4.4  down
		System.out.println("4.55: "+new BigDecimal("4.55").setScale(1, BigDecimal.ROUND_HALF_EVEN));//4.55: 4.6  up
		System.out.println("4.65: "+new BigDecimal("4.65").setScale(1, BigDecimal.ROUND_HALF_EVEN));//4.65: 4.6  down

		System.out.println("3.05: "+new BigDecimal("3.05").setScale(1, BigDecimal.ROUND_HALF_EVEN));//3.05: 3.0  down
		System.out.println("3.15: "+new BigDecimal("3.15").setScale(1, BigDecimal.ROUND_HALF_EVEN));//3.15: 3.2  up
		System.out.println("3.25: "+new BigDecimal("3.25").setScale(1, BigDecimal.ROUND_HALF_EVEN));//3.25: 3.2  down
		System.out.println("3.35: "+new BigDecimal("3.35").setScale(1, BigDecimal.ROUND_HALF_EVEN));//3.35: 3.4  up
		System.out.println("3.45: "+new BigDecimal("3.45").setScale(1, BigDecimal.ROUND_HALF_EVEN));//3.45: 3.4  down
		System.out.println("3.55: "+new BigDecimal("3.55").setScale(1, BigDecimal.ROUND_HALF_EVEN));//3.55: 3.6  up
		System.out.println("3.65: "+new BigDecimal("3.65").setScale(1, BigDecimal.ROUND_HALF_EVEN));//3.65: 3.6  down
	}
}
