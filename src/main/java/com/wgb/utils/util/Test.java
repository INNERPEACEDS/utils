package com.wgb.utils.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : innerpeace
 * @date : 2018/11/20 16:05
 */
@Slf4j
public class Test {
    public static void main(String[] args) {
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
