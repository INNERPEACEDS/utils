package com.wgb.utils.util.design.patterns.sign3.action.sign5.state;

/**
 * 状态模式
 * @author INNERPEACE
 * @date 2019/7/1
 **/
public class StatePatternClient {

	public static void main(String[] args) {
		// 创建环境
		Context context = new Context();
		// 处理请求
		context.handle();
		context.handle();
		context.handle();
		context.handle();
	}
}

/**
 * 环境类
 */
class Context {

	private State state;

	/**
	 * 定义环境类的初始状态
	 */
	public Context() {
		this.state = new ConcreteStateA();
	}

	/**
	 * 设置新状态
	 * @param state
	 */
	public void setState(State state) {
		this.state = state;
	}

	/**
	 * 读取状态
	 * @return
	 */
	public State getState() {
		return (state);
	}

	/**
	 * 对请求做处理
	 */
	public void handle() {
		state.handle(this);
	}
}

/**
 * 抽象状态类
 */
abstract class State {

	/**
	 * 对请求做处理
	 * @param context
	 */
	public abstract void handle(Context context);
}

/**
 * 具体状态A类
 */
class ConcreteStateA extends State {

	@Override
	public void handle(Context context) {
		System.out.println("当前状态是 A.");
		context.setState(new ConcreteStateB());
	}
}

/**
 * 具体状态B类
 */
class ConcreteStateB extends State {
	@Override
	public void handle(Context context) {
		System.out.println("当前状态是 B.");
		context.setState(new ConcreteStateA());
	}
}