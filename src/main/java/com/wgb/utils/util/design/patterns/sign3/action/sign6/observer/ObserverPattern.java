package com.wgb.utils.util.design.patterns.sign3.action.sign6.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author INNERPEACE
 * @date 2019/7/1
 **/
public class ObserverPattern {

	public static void main(String[] args) {
		Subject subject = new ConcreteSubject();
		Observer obs1 = new ConcreteObserver1();
		Observer obs2 = new ConcreteObserver2();
		subject.add(obs1);
		subject.add(obs2);
		subject.notifyObserver();
	}
}

/**
 * 抽象目标
 */
abstract class Subject {

	protected List<Observer> observers = new ArrayList<>();

	/**
	 * 增加观察者方法
	 * @param observer
	 */
	public void add(Observer observer) {
		observers.add(observer);
	}

	/**
	 * 删除观察者方法
	 * @param observer
	 */
	public void remove(Observer observer) {
		observers.remove(observer);
	}

	/**
	 * 通知观察者方法
	 */
	public abstract void notifyObserver();
}

/**
 * 具体目标
 */
class ConcreteSubject extends Subject {
	@Override
	public void notifyObserver() {
		System.out.println("具体目标发生改变...");
		System.out.println("--------------");

		for (Object obs : observers) {
			((Observer) obs).response();
		}

	}
}

/**
 * 抽象观察者
 */
interface Observer {

	/**
	 * 反应
	 */
	void response();
}

/**
 * 具体观察者1
 */
class ConcreteObserver1 implements Observer {
	@Override
	public void response() {
		System.out.println("具体观察者1作出反应！");
	}
}

/**
 * 具体观察者2
 */
class ConcreteObserver2 implements Observer {
	@Override
	public void response() {
		System.out.println("具体观察者2作出反应！");
	}
}
