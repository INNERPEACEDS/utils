package com.wgb.utils.test;

/**
 * @author INNERPEACE
 * @date 2019/1/7 9:48
 **/
public class A {
    public A a;

    A() {
        a = new A();
    }
    public static void main(String[] args) {
        A b = new A();
    }
}
