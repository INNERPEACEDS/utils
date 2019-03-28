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
        String regular = "^in*.*$";
        String content = "inndesds";
        regularMatcherTest(regular, content);
    }
}
