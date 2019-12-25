package com.wgb.utils.util.string;

import com.google.common.base.Charsets;
import lombok.extern.slf4j.Slf4j;
import com.wgb.utils.util.regular.RegularUtil;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
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
    public static int[] convertAllStringsToInts(String[] values) {
        if (values == null) {
            return null;
        }
        int[] ints = new int[values.length];
        int count = 0;
        for (String value : values) {
            if (!RegularUtil.isInteger(value)) {
                log.error("字符中包含非数字字符");
                return null;
            }
            ints[count++] = Integer.parseInt(value);
        }
        return ints;
    }

    public static boolean isBlankParam(String param) {
        return param == null || param.length() == 0 || "".equals(param.trim()) || "null".equals(param.trim());
    }


    /**
     * String转Map
     * 格式：[1=123|2=345|3=89|4=]
     * @param str
     * @return
     */
    public static Map<String, String> stringToMap(String str) {
        Map<String, String> map = new HashMap<>(2);
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
     * String转Map
     * 格式：{deviceID=1&deviceType=4&sourceIP=192.168.1.1&accountIdHash=12236&mobile=12345678901}
     * @param str
     * @return
     */
    public static Map<String, String> getStringToMap(String str) {
        // 判断str是否有值
        if (null == str || "".equals(str)) {
            return null;
        }
        str = str.substring(1, str.length()-1);
        // 根据&截取
        String[] strings = str.split("&");
        // 设置HashMap长度
        int mapLength = strings.length;
        // 判断hashMap的长度是否是2的幂。
        if ((strings.length % 2) != 0) {
            mapLength = mapLength + 1;
        }
        Map<String, String> map = new HashMap<String, String>(mapLength);
        // 循环加入map集合
        for (int i = 0; i < strings.length; i++) {
            // 截取一组字符串
            String[] strArray = strings[i].split("=");
            if (strArray.length == 2) {
                // strArray[0]为KEY strArray[1]为值
                map.put(strArray[0], strArray[1]);
            }
            if (strArray.length == 1) {
                // strArray[0]为KEY strArray[1]为值
                map.put(strArray[0], "");
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

        List<String> list = new ArrayList<String>();
        Pattern p = Pattern.compile("(\\[[^\\]]*\\])");
        Matcher m = p.matcher(msg);
        while(m.find()){
            list.add(m.group().substring(1, m.group().length()-1));
        }
        return list;
    }

    public static void main(String[] args)  {
        /*String a = "";
        Map map = stringToMap(a);
        log.info("map:{}", map);*/
       /* String rate = "6";
        String rateStr = getThousandthRate(rate);
        log.info("费率信息：{}", rateStr);*/
        /*String str = "{123}";
        String substring = str.substring(1,str.length()-1);
        log.info("sub:{}", substring);*/
        String str = "";
        String[] strs = str.split("&");
        log.info("strs:{}", strs);
        log.info("length:{}", strs.length);
    }

    /**
     * 获取千分之一费率：如表示千分之6的费率存放数据为：6，获取后的值为0.006
     * @param rate
     * @return
     */
    public static String getThousandthRate(String rate) {
        if (RegularUtil.isNumber(rate)) {
            BigDecimal rateValue = new BigDecimal(rate).divide(new BigDecimal(1000));
            rate = rateValue.toString();
        }
        return rate;
    }

    public static String inputStreamToString(InputStream in) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int count = -1;
        while ((count = in.read(data, 0, 1024)) != -1) {
            outStream.write(data, 0, count);
        }
        return new String(outStream.toByteArray(), Charsets.UTF_8);
    }

}
