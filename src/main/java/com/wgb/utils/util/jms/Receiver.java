package com.wgb.utils.util.jms;

import com.wgb.utils.util.jms.service.impl.ReceiverServiceOne;
import com.wgb.utils.util.jms.service.impl.ReceiverServiceTwo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Receiver {

    @Autowired
    private ReceiverServiceOne receiverServiceOne;

    @Autowired
    private ReceiverServiceTwo receiverServiceTwo;

    @RabbitHandler
    @RabbitListener(queues = "com.wgb.queue.one")
    public void receiverOne(String message) {
        log.info("接收QueueOne的MQ信息：{}", message);
        receiverServiceOne.handler(message);
    }

    @RabbitHandler
    @RabbitListener(queues = "com.wgb.queue.two")
    public void receiverTwo(String message) {
        log.info("接收QueueTwo的MQ信息：{}", message);
        receiverServiceTwo.handler(message);
    }
}
