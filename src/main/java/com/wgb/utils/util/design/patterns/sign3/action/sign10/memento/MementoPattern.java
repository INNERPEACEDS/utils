package com.wgb.utils.util.design.patterns.sign3.action.sign10.memento;

/**
 * 备忘录模式
 * @author INNERPEACE
 * @date 2019/7/1
 **/
public class MementoPattern {

	public static void main(String[] args) {
		Originator or = new Originator();
		Caretaker cr = new Caretaker();
		or.setState("S0");
		System.out.println("初始状态:" + or.getState());
		// 保存状态
		cr.setMemento(or.createMemento());
		or.setState("S1");
		System.out.println("新的状态:" + or.getState());
		// 恢复状态
		or.restoreMemento(cr.getMemento());
		System.out.println("恢复状态:" + or.getState());
	}
}

/**
 * 备忘录
 */
class Memento {

	private String state;

	public Memento(String state) {
		this.state = state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}
}

/**
 * 发起人
 */
class Originator {

	private String state;

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public Memento createMemento() {
		return new Memento(state);
	}

	public void restoreMemento(Memento m) {
		this.setState(m.getState());
	}
}

/**
 * 管理者
 */
class Caretaker {

	private Memento memento;

	public void setMemento(Memento m) {
		memento = m;
	}

	public Memento getMemento() {
		return memento;
	}
}