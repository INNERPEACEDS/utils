package com.wgb.utils.util.java.one.point.eight;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author INNERPEACE
 * @date 2019/8/2 18:46
 **/
public class JavaOnePointEightTest {

	public static void main(String[] args) {
		UserInterfce<String> user = User::getName;
		user.convert("测试");

		UserInterfce<String> userInterfce = User::getName;
		userInterfce.convert("自定义fancation");
		Function<String, User> function = User::new;
		User apply = function.apply("构造器");

		User user1 = new User();
		Consumer<String> consumer1 = user1::disName;
		consumer1.accept("实例方法调用");
	}

}
