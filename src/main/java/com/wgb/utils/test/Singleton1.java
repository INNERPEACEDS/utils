package com.wgb.utils.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author INNERPEACE
 * @date 2019/1/7 9:59
 **/
@Slf4j
public class Singleton1 {
    private static Singleton1 singleton = new Singleton1("1","2");

    private final String a;
    private String b;
    private Singleton1(String a, String b) {
        log.info("进入构造函数");
        this.a = a;
        this.b = b;

    }

    public static Singleton1 getInstance() {
        return singleton;
    }

    private Object readResolve() {
        return singleton;
    }

    public static void main(String[] args) {
        Singleton1 s = new Singleton1("2", "3");
        log.info("单例的对象：{}，值：{}", s, Singleton1.getInstance());
    }

}
