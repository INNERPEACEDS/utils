package com.wgb.utils.util;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : innerpeace
 * @date : 2018/11/20 16:05
 */
@Slf4j
public class Test {
    public static void main(String[] args) {
        /*Map<String, String> map = new HashMap<>();
        map.put("test", "test");
        if (map.get("test") != null) {
            // BigDecimal test = new BigDecimal("0");
            String a = null,b="test";

            StringBuilder builder = new StringBuilder();
            builder.append(map.get("test1")).append("#").append(b);
            String c = builder.toString();
            log.info("builder的值为:{}", c);
        } else {
            log.info("数据为空");
        }*/
        StringBuffer buffer = new StringBuffer();
        log.info("数据：{}", buffer);
        if ("".equals(buffer.toString())) {
            log.info("1:{}", buffer);
        } else {
            log.info("2");
        }
    }


    public static void main1(String[] args) {

        /*String str = "1";
        String[] s = str.split("#");
        log.info("s长度：{}，s数据：{}", s.length, s);*/

        int i = 0;
        outer:
        for (; true; ) {
            log.info("i1={}", i);
            if (i == 3) {
                log.info("如果i=3，则跳出去");
                return;
            }
            inner:
            for (; i < 10; i++ ) {
                log.info("i2={}", i);
                /*if (i == 3) { // 进行i++操作
                    log.info("continue");
                    continue ;
                }
                if (i == 3) {  // 不进行i++操作
                    log.info("break");
                    break ;
                }
                if (i == 3) {  // 不进行i++操作
                    log.info("continue outer");
                    continue outer;
                }*/
                if (i == 3) { // 不进行i++操作
                    log.info("break outer");
                    break  outer;
                }
                log.info("继续continue");
                if (i == 9) {
                    return;
                }
            }
            log.info("内层结束,i的值为：{}", i);
            return;
        }
        log.info("main中i的值：{}", i);
    }
}
