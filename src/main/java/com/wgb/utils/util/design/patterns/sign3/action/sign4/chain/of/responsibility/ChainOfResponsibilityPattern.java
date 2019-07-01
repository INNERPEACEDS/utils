package com.wgb.utils.util.design.patterns.sign3.action.sign4.chain.of.responsibility;

/**
 * 责任链模式
 *
 * @author INNERPEACE
 * @date 2019/7/1
 **/
public class ChainOfResponsibilityPattern {

	public static void main(String[] args) {
		//组装责任链
		Handler handler1 = new ConcreteHandler1();
		Handler handler2 = new ConcreteHandler2();
		handler1.setNext(handler2);
		//提交请求
		handler1.handleRequest("two");
	}
}

/**
 * 抽象处理者角色
 */
abstract class Handler {

	private Handler next;

	public void setNext(Handler next) {
		this.next = next;
	}

	public Handler getNext() {
		return next;
	}

	/**
	 * 处理请求的方法
	 * @param request
	 */
	public abstract void handleRequest(String request);
}

/**
 * 具体处理者角色1
 */
class ConcreteHandler1 extends Handler {
	@Override
	public void handleRequest(String request) {
		if (Constants.ONE.equals(request)) {
			System.out.println("具体处理者1负责处理该请求！");
		} else {
			if (getNext() != null) {
				getNext().handleRequest(request);
			} else {
				System.out.println("没有人处理该请求！");
			}
		}
	}
}

/**
 * 具体处理者角色2
 */
class ConcreteHandler2 extends Handler {

	@Override
	public void handleRequest(String request) {
		if (Constants.TWO.equals(request)) {
			System.out.println("具体处理者2负责处理该请求！");
		} else {
			if (getNext() != null) {
				getNext().handleRequest(request);
			} else {
				System.out.println("没有人处理该请求！");
			}
		}
	}
}

/**
 * 常量
 */
class Constants {
	public static String ONE = "one";

	public static String TWO = "two";

}
