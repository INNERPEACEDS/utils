package com.wgb.utils.test.extend;

import lombok.extern.slf4j.Slf4j;

/**
 * @author INNERPEACE
 * @date 2020/5/14 15:38
 */
@Slf4j
public class SupClass {
	protected String a;
	protected String b;
	protected String c;

	public SupClass() {

	}

	public SupClass(String a, String b, String c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public boolean loadData(String[] str) {
		a = str[0];
		b = str[1];
		c = str[2];
		try {
			log.info("数据：a={}，b={}，c={}", a, b, c);
			return true;
		} catch (Exception e){
			log.error("异常", e);
			return false;
		}
	}
}
