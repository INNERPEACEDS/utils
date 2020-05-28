package com.wgb.utils.test.extend;

import lombok.extern.slf4j.Slf4j;

/**
 * @author INNERPEACE
 * @date 2020/5/14 15:40
 */
@Slf4j
public class SubClassA extends SupClass {
	public boolean parseData(String[] str) {
		if (!loadData(str)) {
			log.info("解析数据错误，返回错误");
			return false;
		}
		log.info("解析数据成功，返回成功");
		return true;
	}
}
