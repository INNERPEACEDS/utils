package com.wgb.utils.util.string;

import lombok.extern.slf4j.Slf4j;


import com.wgb.utils.util.regular.RegularUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符工具类
 *
 * @author : innerpeace
 * @date : 2018/10/30 11:07
 */
@Slf4j
public class StringUtil {
    /**
     * 判断字符是否为空
     *
     * StringUtil.isBlank(null) = true
     * StringUtil.isBlank("")   = true
     * StringUtil.isBlank(" ")  = true
     *
     * @param value
     * @return
     */
    public static boolean isBlank(String value) {
        if((value == null) || value.length() == 0 || ("".equals(value.trim()))) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串是否为空，去除两端空白
     *
     * StringUtil.isEmpty(null)      = true
     * StringUtil.isEmpty("")        = true
     * StringUtil.isEmpty(" ")       = false
     * StringUtil.isEmpty("bob")     = false
     * StringUtil.isEmpty("  bob  ") = false
     *
     * @param value
     * @return
     */
    public static boolean isEmpty(String value) {
        if (value == null || "".equals(value)) {
            return true;
        }
        return false;
    }

    /**
     * 转换String数组成int数组
     *
     * @param values
     * @return
     */
    public static int[] ConvertAllStringsToInts(String[] values) {
        if (values == null) {
            return null;
        }
        int[] ints = new int[values.length];
        int count = 0;
        for (String value : values) {
            if (!RegularUtil.isInteger(value)) {
                return null;
            }
            ints[count++] = Integer.parseInt(value);
        }
        return ints;
    }

    public static boolean isBlankParam(String param) {
        return param == null || param.length() == 0 || "".equals(param.trim()) || "null".equals(param.trim());
    }



    public static Map<String, String> stringToMap(String str) {
        Map<String, String> map = new HashMap<>();
        log.info("要转的str：{}", str);
        List<String> list = extractMessageByRegular(str);
        if (list.size() != 1) {
            log.error("产品拼接参数格式有误，请检查！");
            throw new RuntimeException("产品拼接参数格式有误，请检查！");
        }
        String string = list.get(0);

        log.info("中括号中的参数为：{}", string);
        if (string == null) {
            return null;
        }

        String[] str1 = string.split("\\|");
        log.info("str1:{},str1.length:{}", str1, str1.length);
        if (str1 == null) {
            String[] str3 = {string} ;
            log.info("转换后的map值为：{}", map);
            map = equalSplit(str3);
            return map;
        }
        if (str1.length < 1) {
            log.error("产品拼接参数格式有误，请检查！");
            throw new RuntimeException("产品拼接参数格式有误，请检查！");
        }
        map = equalSplit(str1);

        log.info("转换后的map值为：{}", map);
        return map;
    }

    public static Map equalSplit(String[] str1) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < str1.length; i++) {
            String[] str2 = str1[i].split("=");
            log.info("str2:{},str2.length{}", str2, str2.length);
            if ((str2 == null) || (str2.length < 1) || (str2.length > 2)) {
                log.error("产品拼接参数格式有误，请检查！");
                throw new RuntimeException("产品拼接参数格式有误，请检查！");
            }
            if (str2.length == 1) {
                map.put(str2[0], "");
            }
            if (str2.length == 2) {
                map.put(str2[0], str2[1]);
            }
        }
        return map;
    }

    /**
     * 使用正则表达式提取中括号中的内容
     * @param msg
     * @return
     */
    public static List<String> extractMessageByRegular(String msg){

        List<String> list=new ArrayList<String>();
        Pattern p = Pattern.compile("(\\[[^\\]]*\\])");
        Matcher m = p.matcher(msg);
        while(m.find()){
            list.add(m.group().substring(1, m.group().length()-1));
        }
        return list;
    }

    public static void main(String[] args)  {
        String a = "";
        Map map = stringToMap(a);
        log.info("map:{}", map);
    }


}
