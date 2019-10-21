package com.wgb.utils.util.rpc.provider.service;

import com.wgb.utils.util.rpc.Calculator;

/**
 * @author INNERPEACE
 */
public class CalculatorImpl implements Calculator {
    @Override
    public int add(int a, int b) {
        return a + b;
    }
}
