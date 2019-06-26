package com.wgb.utils.util.design.patterns.sign2.structure.sign3.bridge;

/**
 * 桥接模式
 * @author INNERPEACE
 * @date 2019/6/25 13:59
 **/
public class BridgeTest {
	public static void main(String[] args) {
		Implementor imple = new ConcreteImplementorA();
		Abstraction abs = new RefinedAbstraction(imple);
		abs.operation();
	}
}

/**
 * 实现化角色
 */
interface Implementor {
	public void operationImpl();
}

/**
 * 具体实现化角色
 */
class ConcreteImplementorA implements Implementor {
	@Override
	public void operationImpl() {
		System.out.println("具体实现化(Concrete Implementor)角色被访问");
	}
}

/**
 * 抽象化角色
 */
abstract class Abstraction {

	protected Implementor imple;

	protected Abstraction(Implementor imple) {
		this.imple = imple;
	}

	public abstract void operation();
}

/**
 * 扩展抽象化角色
 */
class RefinedAbstraction extends Abstraction {

	protected RefinedAbstraction(Implementor imple) {
		super(imple);
	}

	@Override
	public void operation() {
		System.out.println("扩展抽象化(Refined Abstraction)角色被访问");
		imple.operationImpl();
	}
}
