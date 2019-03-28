package com.wgb.utils.service.query;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : innerpeace
 * @date : 2018/12/11 16:05
 */
@Slf4j
public class Test {
    public static void main(String[] args) {
        String test = "测试内容如下";
        String test1 = "nihao";
        String a = null;

        try {
            int i = a.length() - 1;
        } catch (Exception e) {
            log.info(test1);
        }


    }
}
