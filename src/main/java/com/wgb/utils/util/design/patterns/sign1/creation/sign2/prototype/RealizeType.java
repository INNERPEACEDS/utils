package com.wgb.utils.util.design.patterns.sign1.creation.sign2.prototype;

/**
 * 具体原型类
 *
 * @author INNERPEACE
 * @date 2019/6/19 10:55
 **/
class RealizeType implements Cloneable {

	RealizeType() {
		System.out.println("具体原型创建成功！");
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		System.out.println("具体原型复制成功！");
		return (RealizeType) super.clone();
	}
}

