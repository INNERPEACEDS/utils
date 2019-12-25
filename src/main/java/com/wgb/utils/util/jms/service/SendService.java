package com.wgb.utils.util.jms.service;

/**
 * @author INNERPEACE
 * @date 2019/11/7 15:47
 */
public interface SendService {
	/**
	 * 生产请求消息
	 * @param message 请求消息
	 */
	void handler(String message);
}
