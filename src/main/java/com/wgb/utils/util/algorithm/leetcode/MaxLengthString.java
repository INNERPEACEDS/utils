package com.wgb.utils.util.algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author INNERPEACE
 * @date 2020/7/7
 */
@Slf4j
public class MaxLengthString {

	public static void main(String[] args) {
		String string = "abbccdefg";
		Map<String, Object> strMaxLength = new MaxLengthString().getStrMaxLength(string);
		log.info("最大长度：{}", strMaxLength.get("maxLength"));
	}

	public Map<String, Object> getStrMaxLength(String string) {
		if (string == null) {
			return null;
		}
		Set<String> maxLengthStr = new HashSet<>();
		int pointer = -1, maxLength = 0;
		int length = string.length();
		for (int i = 0; i < length; i++) {
			if (i != 0) {
				maxLengthStr.remove(String.valueOf(string.charAt(i - 1)));
			}
			while (pointer + 1 < length && !maxLengthStr.contains(String.valueOf(string.charAt(pointer + 1)))) {
				maxLengthStr.add(String.valueOf(string.charAt(pointer + 1)));
				++pointer;
			}
			if (maxLengthStr.size() > maxLength) {
				maxLength = maxLengthStr.size();
			}
		}
		HashMap<String, Object> map = new HashMap<>();
		map.put("maxLength", maxLength);
		return map;
	}
}
