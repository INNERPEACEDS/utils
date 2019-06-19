package com.wgb.utils.controller;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : innerpeace
 * @date : 2018/11/20 16:02
 */
@Slf4j
public class Test {
	public static void main(String[] args) {
		String[] str = {"str1", "str2", "str3"};
		for (String s : str) {
			log.info("字符串：{}", s);
			for (int i = 0; i < 10; i++) {
				if (i == 1) {
					log.info("跳出去");
					break;
				}
			}
		}
	}
}
