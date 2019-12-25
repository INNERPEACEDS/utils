package com.wgb.utils.util.jms.service.impl;

import com.wgb.utils.util.jms.Sender;
import com.wgb.utils.util.jms.service.SendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author INNERPEACE
 * @date 2019/11/7 16:19
 */
@Slf4j
@Service
public class SendServiceTwo implements SendService {
	@Autowired
	Sender sender;

	@Override
	public void handler(String message) {
		String queue = "QueueTwo";
		log.info("生产" + queue);
		sender.sendQueueTwo(message);
	}
}
