package com.wgb.utils.util.design.patterns.sign1.creation.sign1.singleton;

/**
 * 饿汉式模式
 * http://c.biancheng.net/design_pattern/
 * @author INNERPEACE
 * @date 2019/6/19 10:48
 **/
public class HungrySingleton {
	private static final HungrySingleton instance = new HungrySingleton();

	private HungrySingleton() {
	}

	public static HungrySingleton getInstance() {
		return instance;
	}
}
