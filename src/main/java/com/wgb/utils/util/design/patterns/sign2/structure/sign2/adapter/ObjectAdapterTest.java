package com.wgb.utils.util.design.patterns.sign2.structure.sign2.adapter;

/**
 * 对象适配器
 * 客户端代码
 * @author INNERPEACE
 * @date 2019/6/25 10:53
 **/
public class ObjectAdapterTest {
	public static void main(String[] args) {
		System.out.println("对象适配器模式测试：");
		Adaptee adaptee = new Adaptee();
		Target target = new ObjectAdapter(adaptee);
		target.request();
	}
}

class ObjectAdapter implements Target {
	private Adaptee adaptee;

	public ObjectAdapter(Adaptee adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void request() {
		adaptee.specificRequest();
	}
}
