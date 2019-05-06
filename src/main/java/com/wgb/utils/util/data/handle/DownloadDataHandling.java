package com.wgb.utils.util.data.handle;

import com.wgb.utils.util.string.StringUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : innerpeace
 * @date : 2018/12/13 15:20
 */
@Slf4j
public class DownloadDataHandling {
	/**
	 * 序号前缀
	 */
	private static String indexPrefix = "0";

	private static int indexLength = 5;

	/**
	 * 序号转换
	 * @param indexStr
	 * @return
	 */
	public static String bookIndexConversion(String indexStr) {
		String index = null;
		if (StringUtil.isEmpty(indexStr)) {
			String temp = "";
			for (int i = 0; i < indexLength; i++) {
				index = indexPrefix + temp;
			}
			return index;
		}
		int indexStrLength = indexStr.length();
		// log.info("序号[{}]的长度为：{}", indexStr, indexStrLength);
		for (int i = 0; i < (indexLength - indexStrLength); i++) {
			indexStr = indexPrefix + indexStr;
		}
		index = indexStr;
		return index;
	}
}
