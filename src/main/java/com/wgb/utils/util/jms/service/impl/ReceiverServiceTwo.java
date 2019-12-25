package com.wgb.utils.util.jms.service.impl;

import com.wgb.utils.util.jms.service.ReceiverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author INNERPEACE
 * @date 2019/11/7 15:57
 */
@Slf4j
@Service
public class ReceiverServiceTwo implements ReceiverService {

	@Override
	public void handler(String message) {
		String queue = "QueueTwo";
		log.info("处理" + queue);
		message = queue + message;

	}
}
