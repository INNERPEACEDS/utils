package com.wgb.utils.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.codec.Base64;

/**
 * @author INNERPEACE
 * @date 2019/9/17 9:49
 */
@Slf4j
public class Base64Test {
	public static void main(String[] args) {
		byte[] bytes = "[B@532760d8".getBytes();
		log.info("加密值：{},{}", Base64.encodeToString(bytes), Base64.encode(bytes).toString());
		log.info("解密值：{},{},{}", Base64.decode(Base64.decode("4AvVhmFLUs0KTA3Kprsdag==")).toString(), Base64.decodeToString(Base64.decode("4AvVhmFLUs0KTA3Kprsdag==")), Base64.decodeToString(Base64.encode(bytes)));
	}
}
