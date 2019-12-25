package com.wgb.utils.util.jms;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue queueOne() {
        return new Queue("com.wgb.queue.one");
    }

    @Bean
    FanoutExchange fanoutExchangeOne() {
        return new FanoutExchange("fanoutExchangeOne");
    }

    @Bean
    Binding bindingExchangeOne(Queue queueOne, FanoutExchange fanoutExchangeOne) {
        return BindingBuilder.bind(queueOne).to(fanoutExchangeOne);
    }


    @Bean
    public Queue queueTwo() {
        return new Queue("com.wgb.queue.two");
    }

    @Bean
    FanoutExchange fanoutExchangeTwo() {
        return new FanoutExchange("fanoutExchangeTwo");
    }

    @Bean
    Binding bindingExchangeTwo(Queue queueTwo, FanoutExchange fanoutExchangeTwo) {
        return BindingBuilder.bind(queueTwo).to(fanoutExchangeTwo);
    }


}
