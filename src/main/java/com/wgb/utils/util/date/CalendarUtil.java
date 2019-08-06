package com.wgb.utils.util.date;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author INNERPEACE
 * @date 2019/8/2
 **/
public class CalendarUtil {
	/**
	 * 某日第一小时
	 * @param calendar
	 */
	private static void addDefaultHour(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		calendar.set(Calendar.MILLISECOND,0);
	}


	/**
	 * 某月第一天
	 * @param calendar
	 */
	private static void addDefaultDay(Calendar calendar) {
		calendar.set(Calendar.DAY_OF_MONTH,1);
		addDefaultHour(calendar);
	}
	/**
	 * 获取当月第一天开始时间和最后一天结束时间
	 * @return
	 */
	public static List<Long> getMonthStartAndEnd(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH,0);
		addDefaultDay(calendar);
		return getStartAndEndTime(calendar, 1);
	}

	/**
	 * 获取当天的开始时间
	 * @return
	 */
	public static Long getDayStart(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH,0);
		addDefaultHour(calendar);
		return calendar.getTime().getTime();
	}

	/**
	 * 获取某天的开始时间
	 * @param day
	 * @return
	 */
	public static Long getDayStart(Integer day){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH,0);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		addDefaultHour(calendar);
		return calendar.getTime().getTime();
	}

	/**
	 * 获取某天的结束时间
	 * @param day
	 * @return
	 */
	public static Long getDayEnd(Integer day){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH,0);
		calendar.set(Calendar.DAY_OF_MONTH, day + 1);
		addDefaultHour(calendar);
		return calendar.getTime().getTime() - 1;
	}

	/**
	 * 获取本月或者次月某天的结束时间
	 * @param  month
	 * @param day
	 * @return
	 */
	public static Long getDayEnd(Integer month,Integer day){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, month);
		addDefaultHour(calendar);
		calendar.set(Calendar.DAY_OF_MONTH, day + 1);
		return calendar.getTime().getTime() - 1;
	}

	/**
	 * 获取本季度的开始时间和结束时间
	 * @return
	 */
	public static List<Long> getQuarterStartAndEnd(){
		// 获取时间
		Calendar calendar = Calendar.getInstance();
		// 判读当前时间是哪个季度
		int month = calendar.get(Calendar.MONTH) + 1;
		Integer quarter = getQuarter(month);
		calendar.set(Calendar.MONTH, 3 * (quarter - 1));
		addDefaultDay(calendar);
		return getStartAndEndTime(calendar, 3);
	}

	/**
	 * 获取每个季度第某个月，第某个天数的开始时间
	 * @return
	 */
	public static List<Long> getQuarterStart(Integer month,Integer day){
		List<Long> times = new ArrayList<>();
		for (int i = 1; i <= 4; i++) {
			//获取时间
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.MONTH, 3 * (i - 1) - 1 + month);
			calendar.set(Calendar.DAY_OF_MONTH,day);
			addDefaultHour(calendar);
			times.add(calendar.getTime().getTime());
		}
		return times;
	}

	/**
	 * 获取某(该)个季度某（第几）个月，某天的结束时间
	 * @return
	 */
	public static Long getQuarterEnd(Integer month,Integer day){
		//获取时间
		Calendar calendar = Calendar.getInstance();
		//判读当前时间是哪个季度
		int currentMonth = calendar.get(Calendar.MONTH) + 1;
		Integer quarter = getQuarter(currentMonth);
		calendar.set(Calendar.MONTH, 3 * (quarter - 1) + month);
		calendar.set(Calendar.DAY_OF_MONTH, day + 1);
		addDefaultHour(calendar);
		return calendar.getTime().getTime()-1;
	}

	/**
	 * 获取当年的开始时间和结束时间
	 * @return
	 */
	public static List<Long> getYearStartAndEnd(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH,0);
		calendar.set(Calendar.DAY_OF_MONTH,1);
		addDefaultHour(calendar);
		return getStartAndEndTime(calendar, 12);
	}

	/**
	 * 获取当前年第某个月，第某个天数的开始时间
	 * @return
	 */
	public static Long getYearStart(Integer month,Integer day){
		// 获取时间
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH,month-1);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		addDefaultHour(calendar);
		return calendar.getTime().getTime();
	}

	/**
	 * 获取当前年第某个月，第某个天数的结束时间
	 * @return
	 */
	public static Long getYearEnd(Integer month,Integer day){
		// 获取时间
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH,month-1);
		calendar.set(Calendar.DAY_OF_MONTH, day + 1);
		addDefaultHour(calendar);
		return calendar.getTime().getTime()-1;
	}

	public static Integer getQuarter(int currentMonth) {
		Integer quarter = 0;
		if (currentMonth>=1 && currentMonth <=3) {
			quarter = 1;
		} else if (currentMonth>=4 && currentMonth <=6) {
			quarter = 2;
		} else if (currentMonth>=7 && currentMonth <=9) {
			quarter = 3;
		} else if (currentMonth>=10 && currentMonth <=12) {
			quarter = 4;
		}
		return quarter;
	}


	/**
	 * 获取某几月内的开始和结束
	 * @param calendar
	 * @param addMonth 某几个月
	 * @return
	 */
	public static List<Long> getStartAndEndTime(Calendar calendar, Integer addMonth) {
		List<Long> times = new ArrayList<>();
		Long firstDay = calendar.getTime().getTime();
		calendar.add(Calendar.MONTH,addMonth);
		Long lastDay = calendar.getTime().getTime()-1;
		times.add(firstDay);
		times.add(lastDay);
		return times;
	}

}
