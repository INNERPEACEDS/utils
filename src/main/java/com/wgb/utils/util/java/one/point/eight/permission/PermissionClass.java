package com.wgb.utils.util.java.one.point.eight.permission;

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
		PermissionClass permissionClass = new PermissionClass();
		permissionClass.test();
	}
}
