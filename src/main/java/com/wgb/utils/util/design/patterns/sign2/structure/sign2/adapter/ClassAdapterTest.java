package com.wgb.utils.util.design.patterns.sign2.structure.sign2.adapter;

/**
 * 类适配器
 * 客户端代码
 *
 * @author INNERPEACE
 * @date 2019/6/25 10:43
 **/
public class ClassAdapterTest {
	public static void main(String[] args) {
		System.out.println("类适配器模式测试：");
		Target target = new ClassAdapter();
		target.request();
	}
}

/**
 * 目标接口
 */
interface Target {
	/**
	 * 目标实现
	 */
	void request();
}

/**
 * 适配者接口
 */
class Adaptee {
	public void specificRequest() {
		System.out.println("适配者中的业务代码被调用！");
	}
}

/**
 * 类适配器类
 */
class ClassAdapter extends Adaptee implements Target {
	@Override
	public void request() {
		specificRequest();
	}
}