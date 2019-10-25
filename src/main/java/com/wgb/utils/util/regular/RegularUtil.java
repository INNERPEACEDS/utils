package com.wgb.utils.util.regular;

import com.wgb.utils.util.string.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

/**
 * @author : innerpeace
 * @date : 2018/10/30 15:42
 */
@Slf4j
public class RegularUtil {
    /**
     * 数字正则表达式（整数、小数）
     */
    private static String REGULAR_NUMBER = "^[+-]?\\d+(\\.\\d+)?$";

    /**
     * 整数正则表达式（包含符号）
     */
    private static String REGULAR_INTEGER = "^[+-]?[\\d]*$";
    /**
     * 正整数正则表达式（包含符号）
     */
    private static String REGULAR_POSITIVE_INTEGER = "^[+]?\\d+$";

    /**
     * 正整数正则表达式（不含符号）
     */
    private static String REGULAR_POSITIVE_INTEGER_NO_SYMBOL = "^\\d+$";

    /**
     * 正则表达式：常用图片文件后缀名
     * <p><strong>注意 后缀名字母大小写必须保证一致（例如 正确：JPG、jpg、bmp  错误 ：JpG Bmp）
     */
    public static final String REGEX_IMAGE_UPLOAD_FILE_SUFFIXES="^.*(jpg|JPG|jpeg|JPEG|bmp|BMP|gif|GIF|png|PNG)$";

    /**
     * 正则表达式：常用压缩文件后缀名
     * <p><strong>注意 后缀名字母大小写必须保证一致（例如 正确：ZIP rar  错误 ：Zip Rar）
     */
    public static final String REGEX_ZIP_UPLOAD_FILE_SUFFIXES="^.*(rar|RAR|zip|ZIP)$";


    /**
     * 密码1（数字和字母组合，必须两者都包含）
     */
    public static final String PASSWORD1 = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,10}$";

	/**
	 * 密码2（必须含有数字和字母组合，可以有特殊字符）
	 */
	public static final String PASSWORD2 = "^(?=.*\\d)(?=.*[a-zA-Z]).{8,20}$";

	/**
	 * 密码3（特殊字符、数字和字母组合，三者必须都包含，8位）
	 * ?= 首先定空位，例如：abc123bbdf333#$& 字符串，(?=.*\d)定位 abc1 的空位（abc1前空位），(?=.*[a-zA-Z])定位 a 的空位（a之前的空位），
	 * (?=.*[^A-Za-z0-9])定位 abc123bbdf333# 的空位（abc123bbdf333#之前的空位），符合定位空位再定空位，不符合则字符不满足规则，三者都符合
	 * 以后从空位找到8位非回车非换行字符。
	 */
	public static final String PASSWORD3 = "^(?=.*\\d)(?=.*[a-zA-Z])(?=.*[^A-Za-z0-9]).{8}$";

	/**
	 * 以"逗号（或者逗号前后有空字符）"分割字符
	 */
	public static final Pattern COMMA_SPLIT_PATERN = Pattern.compile("\\s*[,]+\\s*");

	// ab&c3

    /**
     * 判断是否是数字
     * @param value 带判断字符串
     * @return
     */
    public static boolean isNumber(String value) {
        if (StringUtil.isBlank(value)) {
            return false;
        }
        return value.matches(REGULAR_NUMBER);
    }

    /**
     * 判断是否是正整数
     * @param value
     * @return
     */
    public static boolean isPositiveInteger(String value) {
        if (StringUtil.isBlank(value)) {
            return false;
        }
        return value.matches(REGULAR_POSITIVE_INTEGER);
    }

    /**
     * 判断是否是正整数（不含符号）
     * @param value
     * @return
     */
    public static boolean isPositiveIntegerNoSymbol(String value) {
        if (StringUtil.isBlank(value)) {
            return false;
        }
        return value.matches(REGULAR_POSITIVE_INTEGER_NO_SYMBOL);
    }

    /**
     * 判断字符串是否是整数（速度快）
     * @param value 待判断字符串
     * @return
     */
    public static boolean isInteger(String value) {
        if (StringUtil.isBlank(value)) {
            return false;
        }
        return value.matches(REGULAR_INTEGER);
    }


    private static void regularMatcherTest(String regular, String content) {
        Pattern pattern = Pattern.compile(regular);
        boolean result = pattern.matcher(content).matches();
        if (result) {
            log.info("匹配内容：{}", content);
        }
        log.info("匹配结果：{}", result);
    }

    public static void main(String[] args) {
        // regularTest();
	    // password1Test();
	    regularTest1();
    }

	public static void regularTest1() {
		String[] a = COMMA_SPLIT_PATERN.split("aadke sd      ,     d dkl,d  dkls");
		for (String a1 : a) {
			System.out.println(a1);
		}
	}

	public static void regularTest() {
		String regular = "^in*.*$";
		String content = "inndesds";
		regularMatcherTest(regular, content);
	}

	public static void password1Test() {
		String value = "123kaddddddd";
		log.info("结果：{}", value.matches(PASSWORD2));
	}
}
