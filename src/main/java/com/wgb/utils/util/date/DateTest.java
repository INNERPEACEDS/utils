package com.wgb.utils.util.date;

import lombok.extern.slf4j.Slf4j;
import java.util.Collections;
import java.util.List;

/**
 * @author INNERPEACE
 * @date 2019/8/2
 **/
@Slf4j
public class DateTest {
	public static void main(String[] args) {
		// calendarUtilTest();
		dateTest();
	}

	public static void dateTest() {
		long time = 999999999999999999L;
		System.out.println(DateUtil.timestampToDateStr(time));

	}

	private static void output(List<Long> list, String msg) {
		System.out.println(msg);
		list.forEach((n) -> System.out.println(DateUtil.timestampToDateStr(n)));
	}

	public static void calendarUtilTest() {
		String msg;
		List<Long> list = CalendarUtil.getMonthStartAndEnd();
		msg = "获取当月第一天开始时间和最后一天结束时间:";
		output(list, msg);

		list = Collections.singletonList(CalendarUtil.getDayStart());
		msg = "获取当天的开始时间";
		output(list, msg);

		list = Collections.singletonList(CalendarUtil.getDayStart(4));
		msg = "获取某天的开始时间";
		output(list, msg);

		list = Collections.singletonList(CalendarUtil.getDayEnd(4));
		msg = "获取某天的结束时间";
		output(list, msg);

		list = Collections.singletonList(CalendarUtil.getDayEnd(2,3));
		msg = "获取本月或者次月某天的结束时间";
		output(list, msg);

		list = CalendarUtil.getQuarterStartAndEnd();
		msg = "获取本季度的开始时间和结束时间";
		output(list, msg);

		list = CalendarUtil.getQuarterStart(3, 3);
		msg = "获取每个季度第某个月，第某个天数的开始时间";
		output(list, msg);

		list = Collections.singletonList(CalendarUtil.getQuarterEnd(2,3));
		msg = "获取某(该)个季度某（第几）个月，某天的结束时间";
		output(list, msg);

		list = CalendarUtil.getYearStartAndEnd();
		msg = "获取当年的开始时间和结束时间";
		output(list, msg);

		list = Collections.singletonList(CalendarUtil.getYearStart(2,3));
		msg = "获取当前年第某个月，第某个天数的开始时间";
		output(list, msg);

		list = Collections.singletonList(CalendarUtil.getYearEnd(2,3));
		msg = "获取当前年第某个月，第某个天数的结束时间";
		output(list, msg);
	}



}
