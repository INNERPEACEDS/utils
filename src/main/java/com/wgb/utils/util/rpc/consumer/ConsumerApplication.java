package com.wgb.utils.util.rpc.consumer;

import com.wgb.utils.util.rpc.Calculator;
import com.wgb.utils.util.rpc.consumer.service.CalculatorRemoteImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * @author INNERPEACE
 */
@Slf4j
public class ConsumerApplication {
    public static void main(String[] args) {
        Calculator calculator = new CalculatorRemoteImpl();
        int result = calculator.add(6, 6);
        log.info("result is {}", result);
    }
}
