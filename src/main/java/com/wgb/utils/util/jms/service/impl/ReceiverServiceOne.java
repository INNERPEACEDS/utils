package com.wgb.utils.util.jms.service.impl;

import com.wgb.utils.util.jms.service.ReceiverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author INNERPEACE
 * @date 2019/11/7
 */
@Slf4j
@Service
public class ReceiverServiceOne implements ReceiverService {
	@Override
	public void handler(String message) {
		String queue = "QueueOne";
		log.info("处理" + queue);
		message = queue + message;
	}
}
