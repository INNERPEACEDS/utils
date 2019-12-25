package com.wgb.utils.util.jms;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author INNERPEACE
 * @date 2019/11/7 13:49
 */
@Component
public class Sender {
	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void sendQueueOne(String message) {
		this.rabbitTemplate.convertAndSend("fanoutExchangeOne", "", message);
	}

	public void sendQueueTwo(String message) {
		this.rabbitTemplate.convertAndSend("fanoutExchangeTwo", "", message);
	}
}
