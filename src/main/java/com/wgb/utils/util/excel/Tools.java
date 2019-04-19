package com.wgb.utils.util.excel;



import com.wgb.utils.util.string.StringUtil;
import lombok.extern.slf4j.Slf4j;
import oracle.sql.TIMESTAMP;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : innerpeace
 * @date : 2018/11/28 14:45
 */
@Slf4j
public class Tools {

	public Tools() {

	}

	/** 日期转换为自定义格式输出 */
	public static String DateToString(Date date, String formatType) {
		if (date == null) {
			return new SimpleDateFormat(formatType).format(new Date());
		}
		return new SimpleDateFormat(formatType).format(date);
	}

	/**
	 * 按照默认格式转换日期
	 *
	 * @param date
	 * @return
	 */
	public static String dateToStringHasNull(Date date) {
		if (date == null) {
			return "";
		}
		return dateToStringHasNull(date, "yyyy-MM-dd HH:mm:ss");
	}

	/** 日期转换为自定义格式输出 如果为null空返回空 */
	public static String dateToStringHasNull(Date date, String formatType) {
		if (date == null) {
			return "";
		}
		return new SimpleDateFormat(formatType).format(date);
	}

	/** 如果返回为空对象则转换为自定义字符串 */
	public static String nullObjectFormat(Object object, String string) {
		String str;
		if (object == null) {
			str = string;
		} else if (object.equals("null")) {
			str = string;
		} else if (object.equals(" ")) {
			str = string;
		} else if (object.equals("")) {
			str = string;
		} else {
			str = object.toString();
		}
		return str;
	}

	/**
	 * 获得用户级别中文描述
	 *
	 * @param object
	 * @return zhangzhou
	 */
	public static String getMerClassForChinaese(Object object) {
		if (object == null) {
			return "";
		}
		if ("0".equals(object.toString().trim())) {
			return "新注册用户";
		}
		if ("4".equals(object.toString().trim())) {
			return "实名认证用户";
		}
		if ("7".equals(object.toString().trim())) {
			return "VIP用户";
		}
		return "";
	}

	/**
	 * 获得是否为合作商户中文描述
	 *
	 * @param object
	 * @return zhangzhou
	 */
	public static String getIsCoopeRateForChinaese(Object object) {
		if (object == null) {
			return "";
		}
		if ("0".equals(object.toString().trim())) {
			return "否";
		}
		if ("1".equals(object.toString().trim())) {
			return "是";
		}
		return "";
	}

	/**
	 * 获得是否为集团商户中文描述
	 *
	 * @param object
	 * @return zhangzhou
	 */
	public static String getIsGroupForChinaese(Object object) {
		if (object == null) {
			return "";
		}
		if ("0".equals(object.toString().trim())) {
			return "否";
		}
		if ("1".equals(object.toString().trim())) {
			return "是";
		}
		return "";
	}

	/**
	 * 制造随机数 马海 prm 随机数长度
	 */
	public static String getRandomNumber(int length) {
		String randomNumber = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			String rand = String.valueOf(random.nextInt(10));
			randomNumber += rand;
		}
		return randomNumber;
	}

	/**
	 * 制造随机字母和数字的组合
	 *
	 * @param length
	 * @return
	 */
	public static String getRandomNumberString1(int length, int sum) {
		String radStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		String randStr = "0123456789";
		StringBuffer generateRandStr = new StringBuffer();
		Random rand = new Random();
		for (int i = 0; i < length - sum; i++) {
			int randNum = rand.nextInt(52);
			generateRandStr.append(radStr.substring(randNum, randNum + 1));
		}
		for (int i = 0; i < sum; i++) {
			int randNum = rand.nextInt(10);
			generateRandStr.append(randStr.substring(randNum, randNum + 1));
		}
		return generateRandStr + "";
	}

	/**
	 * 产生随机的字符串(随机产生在规定长度范围内的字符串)
	 *
	 * @param max
	 *            最大的位数
	 * @param min
	 *            最小的位数 （8,6）最大8位最小2位
	 * @return
	 */
	public static String getRandomString(int max, int min) {
		String radStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuffer generateRandStr = new StringBuffer();
		Random rand = new Random();
		if (max < min) {
			int temp = max;
			max = min;
			min = temp;
		}
		int length = rand.nextInt(max + 1);
		if (length < min) {
			length = min;
		}
		for (int i = 0; i < length; i++) {
			int randNum = rand.nextInt(62);
			generateRandStr.append(radStr.substring(randNum, randNum + 1));
		}

		return generateRandStr + "";
	}

	/**
	 * 随机获取一节字符串 不含混淆字符（Oo0 Ll1）
	 *
	 * @param max
	 *            最大长度
	 * @param min
	 *            最小长度
	 * @return
	 */
	public static String getRandomStringNonConfuseCharacter(int max, int min) {
		String radStr = "ABCDEFGHIJKMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz23456789";
		StringBuffer generateRandStr = new StringBuffer();
		Random rand = new Random();
		if (max < min) {
			int temp = max;
			max = min;
			min = temp;
		}
		int length = rand.nextInt(max + 1);
		if (length < min) {
			length = min;
		}
		for (int i = 0; i < length; i++) {
			int randNum = rand.nextInt(56);
			generateRandStr.append(radStr.substring(randNum, randNum + 1));
		}
		return generateRandStr + "";
	}

	// 随机生成字符串(自定义长度)
	public static String getRandomString(int length) {
		String radStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuffer generateRandStr = new StringBuffer();
		Random rand = new Random();
		for (int i = 0; i < length; i++) {
			int randNum = rand.nextInt(36);
			generateRandStr.append(radStr.substring(randNum, randNum + 1));
		}
		return generateRandStr + "";
	}

	// 随机生成纯数字字符串(自定义长度)
	public static String getRandomNumberString(int length) {
		String radStr = "0123456789";
		StringBuffer generateRandStr = new StringBuffer();
		Random rand = new Random();
		for (int i = 0; i < length; i++) {
			int randNum = rand.nextInt(10);
			generateRandStr.append(radStr.substring(randNum, randNum + 1));
		}
		return generateRandStr + "";
	}

	/**
	 * 扩大100倍，并保留2位小数
	 *
	 * @param number
	 * @return
	 */
	public static String expand100Time(double number) {
		BigDecimal bigNum = new BigDecimal(number)
				.multiply(new BigDecimal(100));
		// 设置小数的格式
		DecimalFormat df = new DecimalFormat("####0.0000");
		return df.format(bigNum.doubleValue());
	}

	/**
	 * 把元为单位的数字转化为分为单位
	 *
	 * @param num
	 *            待格式化的数值
	 * @return 转换后的值
	 */
	public static long yuan2Fen(String num) {
		long fen = 0L;
		String fenStr = "";
		int pos = num.indexOf(".");

		if (pos == -1) {
			fenStr = num + "00";
		} else {
			// 取小数点前的值
			fenStr = num.substring(0, pos);
			if (num.length() - pos >= 3) { // 小数点后面大于等于2位
				fenStr += num.substring(pos + 1, pos + 3);
			} else if (num.length() == pos) { // 小数点后面没有值
				fenStr += "00";
			} else { // 小数点后面1位
				fenStr += num.substring(pos + 1) + "0";
			}
		}
		try {
			fen = Long.parseLong(fenStr);
		} catch (Exception e) {
			return 0L;
		}
		return fen;
	}

	/** 修改价格显示形式分到元的转换 */
	public static String formatAmt(Long price) {
		DecimalFormat df1 = new DecimalFormat("0.00");
		String str = "0.00";
		if (price != null) {
			str = df1.format(price.doubleValue() / 100);
		}
		return str;
	}

	/** 修改价格显示形式分到元的转换 */
	public static String formatAmtForString(String price) {
		if (price == null || price.trim().equals("")) {
			return "";
		}

		DecimalFormat df1 = new DecimalFormat("0.00");
		String str = "0.00";
		try {
			if (price != null) {
				str = df1.format(Long.valueOf(price).doubleValue() / 100);
			}
		} catch (Exception e) {
			return price;
		}

		return str;
	}

	/** 修改价格显示形式分到元的转换 */
	public static String formatAmtForLong(String price) {
		if (price == null || price.trim().equals("")) {
			return "";
		}

		// DecimalFormat df1 = new DecimalFormat("0.00");
		String str = "0";
		if (price != null) {
			str = (Long.valueOf(price) / 100) + "";
		}

		return str;

	}

	/** get方式请求 汉字乱码处理 */
	public static String getParamdeal(String param) {
		try {
			param = new String(param.getBytes("ISO-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return param;
	}

	/**
	 * 打印对象属性值
	 *
	 * @param ob
	 * @return
	 */
	public static String printObjectParamValue(Object ob) {
		Class cla = ob.getClass();
		// Method[] method=cla.getDeclaredMethods();
		Field[] field = cla.getDeclaredFields();
		StringBuffer strbuff = new StringBuffer();
		for (int i = 0; i < field.length; i++) {
			String value = "";
			try {
				String name = field[i].getName();
				value = cla
						.getMethod(
								"get" + name.substring(0, 1).toUpperCase()
										+ name.substring(1), null)
						.invoke(ob, null).toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
			strbuff.append(field[i].getName()).append(" [").append(value)
					.append(" ] ");
		}
		return strbuff.toString();
	}

	/**
	 * 百分之多少格式化
	 */
	public static String Percent(Long top, Long foot) {
		if (foot == 0L) {
			return "0.00%";
		}
		Double number = top * 1D / foot;
		Locale loc = new Locale("UTF-8");
		DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance(loc);
		df.applyLocalizedPattern("0.00%");
		return df.format(number);
	}

	/**
	 * 千分之多少格式化
	 */
	public static String Thousandths(Long top, Long foot) {
		if (foot == 0L) {
			return "0.00\u2030";
		}
		Double number = top * 1D / foot;
		Locale loc = new Locale("UTF-8");
		DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance(loc);
		df.applyLocalizedPattern("0.00\u2030");
		return df.format(number);
	}

	/**
	 * 手机号码验证
	 */
	public static boolean isMobileNO(String mobiles) {
		// Pattern p =
		// Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		// Matcher m = p.matcher(mobiles);
		// System.out.println(m.matches()+"---");
		// return m.matches();
		String regExp = "^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(mobiles);
		return m.find();
	}

	/**
	 * 获得预付卡状态
	 */
	public static String getYuFuKaTransState(Object value) {
		if (value == null) {
			return "-";
		}
		String str = "-";
		try {
			switch (Integer.parseInt(value.toString())) {
				case 0:
					str = "正常";
					break;
				case 1:
					str = "已冲正";
					break;
				case 2:
					str = "已撤销";
					break;
				case 3:
					str = "已冲账";
					break;
				default:
					break;
			}
		} catch (Exception e) {
			log.error("预付卡状态获得出现异常", e);
		}

		return str;
	}

	/**
	 * 邮箱验证
	 *
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		String regExp = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(email);
		return m.find();

	}

	/**
	 * 卡号验证
	 */
	public static boolean isNumber(String num) {
		String regExp = "^\\d*$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(num);
		return m.find();
	}

	/**
	 * 金额验证
	 */
	public static boolean isAmt(String amt) {
		String regExp = "^\\d{1,9}(\\.\\d{1,2})?$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(amt);
		return m.find();
	}

	/**
	 * 除法
	 *
	 * @param a
	 * @param b
	 * @return
	 */
	public static Double divide(Double a, Double b) {
		return new BigDecimal(Double.toString(a)).divide(
				new BigDecimal(Double.toString(b))).doubleValue();
	}

	/**
	 * 乘法
	 *
	 * @param a
	 * @param b
	 * @return
	 */
	public static Double multiply(Double a, Double b) {
		return new BigDecimal(Double.toString(a)).multiply(
				new BigDecimal(Double.toString(b))).doubleValue();
	}

	/**
	 * 把小数格式化为百分之多少
	 *
	 * @param a
	 * @return
	 */
	public static String Percent(Double a) {
		Locale loc = new Locale("UTF-8");
		DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance(loc);
		df.applyLocalizedPattern("0.0000%");
		return df.format(a);
	}

	/**
	 * 把小数格式化为百分之多少 （注意重载类型）
	 *
	 * @param number
	 *            要格式化的数字
	 * @param length
	 *            保留几位小数 小于等于0的按照零算
	 * @return
	 */
	public static String Percent(Double number, int length) {
		Locale loc = new Locale("UTF-8");
		DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance(loc);
		String digit = "0";
		if (length > 0) {
			digit = digit + ".";
			for (int i = 0; i < length; i++) {
				digit = digit + "0";
			}
		}
		df.applyLocalizedPattern(digit + "%");
		return df.format(number);
	}

	/**
	 * 将oracle日期转换为字符串数据
	 *
	 * @return 日期字符串
	 */
	public static String getDateBySqlTimestamp(Object obj, String formatStr) {
		try {
			TIMESTAMP t = (TIMESTAMP) obj;
			if (formatStr == null || formatStr.equals("")) {
				formatStr = "yyyy-MM-dd HH:mm:ss";
			}
			Timestamp tt;
			tt = t.timestampValue();
			Date date = new Date(tt.getTime());
			SimpleDateFormat sd = new SimpleDateFormat(formatStr);
			return sd.format(date);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "-";
	}

	// 获取2个日期之间的年份列表
	public static List<String> getYearList(Date startDate, Date endDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		int dateStartYear = cal.get(Calendar.YEAR);
		cal.setTime(endDate);
		int dateEndYear = cal.get(Calendar.YEAR);
		if (dateStartYear > dateEndYear) {
			int temp = dateStartYear;
			dateStartYear = dateEndYear;
			dateEndYear = temp;
		}
		List<String> years = new ArrayList<String>();
		for (int i = dateStartYear; i <= dateEndYear; i++) {
			years.add(i + "");
		}
		return years;
	}

	public static boolean isTrue(String str1, String str2) {
		if (str1 != null) {
			if (str1.equals(str2)) {
				return true;
			}
		} else {
			if (str2 == null) {
				return true;
			}
		}
		return false;
	}

	public static String toCombine(String... values) {
		String str = "";
		for (String val : values) {
			str += val;
		}
		return str;
	}

	public static String divideStr(double n1, double n2) {
		if (n2 == 0) {
			return "除数为0";
		}
		double result = n1 / n2;
		double finalRusult = (double) Math.round(result * 100) / 100;
		return String.valueOf(finalRusult);
	}

	/**
	 * 去掉数据前0
	 */
	public static String removeZero(String num) {
		if (null != num && !"".equals(num)) {
			if (num.subSequence(0, 1).equals("0")) {
				char[] strs = num.toCharArray();
				int j = 0;
				for (int i = 0; i < strs.length; i++) {
					if (strs[i] == '0') {
						j += 1;
					}
				}
				for (int i = 0; i < strs.length; i++) {
					if (strs[i] != '0') {
						return num.substring(i);
					}
				}
				if (strs.length == j) {
					return "0";
				}
			}
		}
		return num;
	}

	/**
	 *
	 * 将hhmmss格式的时间转化成hh:mm:ss格式的时间
	 *
	 * @return
	 */
	public static String timeFormat(String timeStr) {
		String newTimeStr = "";
		if (StringUtil.isBlank(timeStr)) {
			newTimeStr = "-";
		} else {
			timeStr = timeStr.trim();
			if (timeStr.length() == 6) {
				newTimeStr = timeStr.substring(0, 2) + ":"
						+ timeStr.substring(2, 4) + ":"
						+ timeStr.substring(4, 6);
			} else {
				newTimeStr = "illegal val";
			}
		}
		return newTimeStr;
	}
	/**
	 * 将Double的金额转化成long类型并且将元转化成分
	 * @param amt
	 * @return
	 * @throws Exception
	 */
	public static long tranAmtFormat(String amt) throws Exception{
		Double tempAmtMax = Double.parseDouble(amt);
		// 将元转为分 *100后，再将Double类型转化为Long类型
		long resAmtMax = Math.round(tempAmtMax * 100);
		return resAmtMax;
	}
	/**
	 * 将字符串为null的转化为""
	 *
	 * @param oriStr
	 * @return
	 */
	public static String getStrEmpty(String oriStr) {
		return oriStr == null ? "" : oriStr.toString();
	}

	/**
	 * 返回交易类型字符串
	 * @param type
	 * @return
	 */
	public static String getTransTyeStr(String type) {
		String typeStr = "未知";
		switch (type) {
			case "11":
				typeStr = "微信支付";
				break;
			case "12":
				typeStr = "支付宝支付";
				break;
			case "13":
				typeStr = "QQ钱包";
				break;
			case "14":
				typeStr = "微信wap";
				break;
			case "15":
				typeStr = "支付宝wap";
				break;
			case "16":
				typeStr = "微信app";
				break;
			case "17":
				typeStr = "支付宝app";
				break;
			case "18":
				typeStr = "银联扫码";
				break;
			case "19":
				typeStr = "银联条码";
				break;
			case "20":
				typeStr = "百度钱包扫码";
				break;
			case "21":
				typeStr = "百度钱包条码";
				break;
			case "22":
				typeStr = "银联固定码";
				break;
			case "23":
				typeStr = "京东钱包";
				break;
			case "24":
				typeStr = "京东WAP";
				break;

			default:
				break;
		}
		return typeStr;
	}

}

