package com.wgb.utils.util.typeconversion;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 不同类型转换工具类
 * @author : innerpeace
 * @date : 2018/11/12 18:18
 */
@Slf4j
public class DifferentTypeConversionUtil<T> {
    /**
     * object类型转换成int类型，不能转换返回0（字符串都是整数才能转换，可以包含正负号）
     * @param o
     * @return
     */
    public static int objectToInt(Object o) {
        try {
            Number num = (Number) o;
            return num.intValue();
        } catch (Exception e) {
            try {
                log.info("o.toString():{}",o.toString());
                log.info("值：{}",Integer.parseInt(o.toString()));
                return Integer.parseInt(o.toString());
            } catch (Exception e2) {
                return 0;
            }
        }
    }

    /**
     * Object类型转成Long类型，不能转化的返回0
     * @param o
     * @return
     */
    public static long objectToLong(Object o) {
        try {
            Number n = (Number) o;
            return n.longValue();
        } catch (Exception e) {
            try {
                return Long.parseLong(o.toString());
            } catch (Exception e2) {
                return 0;
            }
        }
    }

    /**
     * 把一个object类型变量转化成boolean型。如果是1,t,y,true,yes则转化为true，否则false
     * @param o
     * @return
     */
    public static boolean objectToBoolean(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof Number) {
            if (((Number) o).byteValue() == 0) {
                return false;
            } else {
                return true;
            }
        } else if (o instanceof Boolean) {
            return ((Boolean) o).booleanValue();
        } else {
            String str = o.toString();
            if (str.equalsIgnoreCase("1") || str.equalsIgnoreCase("true")
                    || str.equalsIgnoreCase("yes") || str.equalsIgnoreCase("t")
                    || str.equalsIgnoreCase("y")) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 格式化金额，将金额四舍五入,返回值为double类型
     * @param value 金额
     * @param bit   位数
     * @return
     */
    public static double formatMoneyToDouble(double value, int bit) {
        return new BigDecimal(String.valueOf(value)).setScale(bit, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     *
     * @param value 金额
     * @param bit   位数
     * @return
     */
    public static double formatNumber(double value, int bit) {
        double v = new BigDecimal(String.valueOf(value)).setScale(bit + 1,
                RoundingMode.HALF_UP).doubleValue();
        return new BigDecimal(String.valueOf(v)).setScale(bit,
                RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 格式化金额为标准模式，返回值为String类型
     * @param value 金额
     * @param fmt   要添加的格式化符号（例如：￥符号）
     * @return
     */
    public static String formatMoneyToString(double value, String fmt) {
        double fmtMoney = formatMoneyToDouble(value, 2);
        DecimalFormat df = new DecimalFormat(fmt);
        return df.format(fmtMoney);
    }

    /**
     * 格式化金额，将金额乘以比率返回long类型
     *
     * @param value 金额
     * @param ratio 比率
     * @return
     */
    public static long formatMoneyToLong(String value, int ratio) {
        double amt = Double.parseDouble(value);
        return formatMoneyToLong(amt, ratio);
    }

    /**
     * 格式化金额，将金额乘以比率返回long类型
     *
     * @param value 金额
     * @param ratio 保留的小数位数
     * @return
     */
    public static long formatMoneyToLong(double value, int ratio) {
        Double formatMoney = formatMoneyToDouble(value * ratio, 2);
        return formatMoney.longValue();
    }

    /**
     * 将小写的人民币转换成大写
     * @param number 小写人民币
     * @return 大写人民币
     */
    public static String convertToChineseNumber(double number) {
        StringBuffer chineseNumber = new StringBuffer();
        String[] num = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
        String[] unit = { "分", "角", "元", "拾", "佰", "仟", "万", "拾", "佰", "仟",
                "亿", "拾", "佰", "仟", "万" };
        String tempNumber = String.valueOf(Math.round((number * 100)));
        int tempNumberLength = tempNumber.length();
        if ("0".equals(tempNumber)) {
            return "零元整";
        }
        if (tempNumberLength > 15) {
            try {
                throw new Exception("超出转化范围.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 前面的字符是否读零
        boolean preReadZero = true;
        for (int i = tempNumberLength; i > 0; i--) {
            if ((tempNumberLength - i + 2) % 4 == 0) {
                // 如果在（圆，万，亿，万）位上的四个数都为零,如果标志为未读零，则只读零，如果标志为已读零，则略过这四位
                if (i - 4 >= 0 && "0000".equals(tempNumber.substring(i - 4, i))) {
                    if (!preReadZero) {
                        chineseNumber.insert(0, "零");
                        preReadZero = true;
                    }
                    // 下面还有一个i--
                    i -= 3;
                    continue;
                }
                // 如果当前位在（圆，万，亿，万）位上，则设置标志为已读零（即重置读零标志）
                preReadZero = true;
            }
            int digit = Integer.parseInt(tempNumber.substring(i - 1, i));
            if (digit == 0) {
                // 如果当前位是零并且标志为未读零，则读零，并设置标志为已读零
                if (!preReadZero) {
                    chineseNumber.insert(0, "零");
                    preReadZero = true;
                }
                // 如果当前位是零并且在（圆，万，亿，万）位上，则读出（圆，万，亿，万）
                if ((tempNumberLength - i + 2) % 4 == 0) {
                    chineseNumber.insert(0, unit[tempNumberLength - i]);
                }
            }
            // 如果当前位不为零，则读出此位，并且设置标志为未读零
            else {
                chineseNumber
                        .insert(0, num[digit] + unit[tempNumberLength - i]);
                preReadZero = false;
            }
        }
        // 如果分角两位上的值都为零，则添加一个“整”字
        if (tempNumberLength - 2 >= 0
                && "00".equals(tempNumber.substring(tempNumberLength - 2,
                tempNumberLength))) {
            chineseNumber.append("整");
        }
        return chineseNumber.toString();
    }

    /**
     * Double类型转String类型
     * @param value
     * @return
     */
    public static String doubleToString(double value) {
        String format = DecimalFormat.getInstance().format(value);
        //格式化double类型的的值：5,451,345.56
        log.info("格式化double类型的的值：{}", format);
        String result = format.replace(",", "");
        return result;
    }

    /**
     * array转ArrayList（其他参考方式：https://www.cnblogs.com/GarfieldEr007/p/7082945.html）
     * @param array
     * @return
     */
    public static ArrayList<Object> array2ArrayList(Object[] array) {
        ArrayList<Object> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, array);
        return arrayList;
    }
}
