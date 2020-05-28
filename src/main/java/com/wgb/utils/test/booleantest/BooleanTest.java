package com.wgb.utils.test.booleantest;

import lombok.extern.slf4j.Slf4j;

/**
 * @author INNERPEACE
 * @date 2020/1/17 11:50
 */
@Slf4j
public class BooleanTest {
	public static void main(String[] args) {
		Boolean flag = false;
		String data = "1";
		setFlag(flag, data);
		if (flag) {
			log.info("{}", flag);
		}
	}
	public static void setFlag(Boolean flag, String data) {
		if ("1".equals(data)) {
			flag = true;
		}
	}

}
