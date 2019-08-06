package com.wgb.utils.util.java.one.point.eight;

/**
 * @author INNERPEACE
 * @date 2019/8/2 18:45
 **/
public class User {
	static void getName(String s) {
		System.out.println("该用户的名字:" + s);
	}

	public User() {
		System.out.println("无参构造器");
	}

	public User(String str) {
		System.out.println("有参" + str);
	}

	public void disName(String str) {
		System.out.println(str);
	}
}
