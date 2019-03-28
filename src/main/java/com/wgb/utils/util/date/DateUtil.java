package com.wgb.utils.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * @author INNERPEACE
 * @date 2018/12/27 18:56
 **/
public class DateUtil {
    private DateUtil() {
    }

    public static final long THREE_DAY_SECOUND = 259200001L;

    public static final String FULL_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "HH:mm:ss";

    public static final String TIME_START = "00:00:00";
    public static final String TIME_END = "23:59:59";

    /**
     * 是否小于三天
     *
     * @param date1 日期1
     * @param date2 日期2
     */
    public static boolean isLessThenThreeDays(Date date1, Date date2) {
        return !isGreaterThanThreeDays(date1, date2);
    }

    /**
     * 是否大于三天
     *
     * @param date1 日期1
     * @param date2 日期2
     */
    public static boolean isGreaterThanThreeDays(Date date1, Date date2) {
        if (Math.abs(date1.getTime() - date2.getTime()) >= THREE_DAY_SECOUND) {
            return true;
        }
        return false;
    }
    /**
     * 是否大于三天
     *
     * @param date1 日期1
     * @param date2 日期2
     */
    public static boolean isGreaterThanThreeDays(String date1, String date2, String fmt) throws ParseException {
        Date d1 = toDate(date1, fmt);
        Date d2 = toDate(date2, fmt);
        return isGreaterThanThreeDays(d1,d2);
    }
    /**
     * 是否大于三天
     *
     * @param date1 日期1
     * @param date2 日期2
     */
    public static boolean isGreaterThanThreeDays(String date1, String date2) throws ParseException {
        Date d1 = toDate(date1, FULL_FORMAT);
        Date d2 = toDate(date2, FULL_FORMAT);
        return isGreaterThanThreeDays(d1,d2);
    }


    /**
     * 字符串转日期
     *
     * @param str 日期字符串
     * @return Date类型
     * @throws ParseException 转换异常
     */
    public static Date strToFullDate(String str) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(FULL_FORMAT);
        return sdf.parse(str);
    }

    /**
     * 字符串转日期
     *
     * @param str    日期字符串
     * @param format 日期格式
     * @return Date类型
     * @throws ParseException 转换异常
     */
    public static Date toDate(String str, String format) throws ParseException {
        if(str == null){
            throw new NullPointerException();
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(str);
    }


    /**
     * 转换时间为字符串
     *
     * @param date   时间对象
     * @param format 格式
     * @return 时间字符串
     */
    public static String toStr(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 获取第一秒的时间字符串
     *
     * @param date 时间
     * @return 时间字符串
     */
    public static String getTodayFirstSecondTime(Date date) {
        return toStr(date, "yyyy-MM-dd 00:00:00");
    }

    /**
     * 获取最后一秒的时间字符串
     *
     * @param date 时间
     * @return 时间字符串
     */
    public static String getTodayLastSecondTime(Date date) {
        return toStr(date, "yyyy-MM-dd 23:59:59");
    }
}

