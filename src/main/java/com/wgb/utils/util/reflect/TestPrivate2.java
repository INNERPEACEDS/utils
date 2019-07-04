package com.wgb.utils.util.reflect;

import com.wgb.utils.entity.user.UserSession;

import java.lang.reflect.Field;

/**
 * 访问私有属性
 * @author INNERPEACE
 * @date 2019/7/2
 **/
public class TestPrivate2 {

	public static void main(String[] args) throws Exception {
		PrivateClass2 p = new PrivateClass2();
		Class<?> classType = p.getClass();
		Field field = classType.getDeclaredField("name");
		// 抑制Java对修饰符的检查
		field.setAccessible(true);
		field.set(p, "lisi");
		System.out.println(p.getName());
	}

	public static Class<?> obtainObject() {
		UserSession userSession = new UserSession();
		return userSession.getClass();
	}
}
