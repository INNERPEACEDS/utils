package com.wgb.utils.util.reflect;

import com.wgb.utils.entity.user.UserSession;

import java.lang.reflect.Method;

/**
 * 调用私有方法
 * @author INNERPEACE
 * @date 2019/7/2
 **/
public class TestPrivate {


	public static void main(String[] args) throws Exception {
		PrivateClass p = new PrivateClass();

		Class<?> classType = p.getClass();
		// 获取Method对象
		Method method = classType.getDeclaredMethod("sayHello",
				new Class[]{String.class, TestPrivate2.obtainObject()});
		// 抑制Java的访问控制检查
		method.setAccessible(true);
		// 如果不加上上面这句，将会Error: TestPrivate can not access a member of class PrivateClass with modifiers "private"
		String str = (String) method.invoke(p, new Object[]{"zhangsan", new UserSession()});
		Method method1 = classType.getDeclaredMethod("setField",
				new Class[]{"a".getClass()});
		method1.setAccessible(true);
		method1.invoke(p, "测试");
		System.out.println("field:" + p.getField());

		System.out.println(str);
	}
}
