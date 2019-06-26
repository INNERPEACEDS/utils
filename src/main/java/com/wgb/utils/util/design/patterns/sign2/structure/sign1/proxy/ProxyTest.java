package com.wgb.utils.util.design.patterns.sign2.structure.sign1.proxy;

/**
 * 代理模式测试
 *
 * @author INNERPEACE
 * @date 2019/6/25 10:18
 **/
public class ProxyTest {
	public static void main(String[] args) {
		Proxy proxy = new Proxy();
		proxy.request();
	}
}

/**
 * 抽象主题
 */
interface Subject {
	/**
	 * 操作方法
	 */
	void request();
}

/**
 * 真实主题
 */
class RealSubject implements Subject {

	/**
	 * 具体操作
	 */
	@Override
	public void request() {
		System.out.println("访问真实主题方法...");
	}
}

/**
 * 代理
 */
class Proxy implements Subject {
	private RealSubject realSubject;

	@Override
	public void request() {
		if (realSubject == null) {
			realSubject = new RealSubject();
		}
		preRequest();
		realSubject.request();
		postRequest();
	}

	private void preRequest() {
		System.out.println("访问真实主题之前的预处理。");
	}

	private void postRequest() {
		System.out.println("访问真实主题之后的后续处理。");
	}
}
