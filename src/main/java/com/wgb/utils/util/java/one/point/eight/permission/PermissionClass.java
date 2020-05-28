package com.wgb.utils.util.java.one.point.eight.permission;

import com.wgb.utils.util.java.one.point.eight.lambda.MyLambda;
import com.wgb.utils.util.java.one.point.eight.lambda.MyLambdaImpl;

/**
 * @author 20131
 */
public class PermissionClass {

	private String a;

	/**
	 * 包访问权限，不是public、private、protected、default关键字的权限，仅仅为默认权限，只能在该包下访问。
	 * default关键字用在接口中，例如作为lambda表达式。
	 */
	void test() {
		System.out.println("测试");
	}

	public static void main(String[] args) {
		/*PermissionClass permissionClass = new PermissionClass();
		permissionClass.test();*/
		MyLambda myLambda = new MyLambdaImpl();
		System.out.print(myLambda.test3());
		MyLambda m = y -> System.out.println(y);
		m.test1("好的呀");
	}
}
