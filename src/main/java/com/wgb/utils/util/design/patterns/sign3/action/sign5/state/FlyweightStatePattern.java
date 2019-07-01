package com.wgb.utils.util.design.patterns.sign3.action.sign5.state;

import java.util.HashMap;

/**
 * @author INNERPEACE
 * @date 2019/7/1
 **/
public class FlyweightStatePattern {

	public static void main(String[] args) {
		// 创建环境
		ShareContext context = new ShareContext();
		// 处理请求
		context.Handle();
		context.Handle();
		context.Handle();
		context.Handle();
	}
}

/**
 * 环境类
 */
class ShareContext {

	private ShareState state;
	private HashMap<String, ShareState> stateSet = new HashMap<>();

	public ShareContext() {
		state = new ConcreteState1();
		stateSet.put("1", state);
		state = new ConcreteState2();
		stateSet.put("2", state);
		state = getState("1");
	}

	/**
	 * 设置新状态
	 * @param state
	 */
	public void setState(ShareState state) {
		this.state = state;
	}


	/**
	 * 读取状态
	 * @param key
	 * @return
	 */
	public ShareState getState(String key) {
		ShareState s = stateSet.get(key);
		return s;
	}

	/**
	 * 对请求做处理
	 */
	public void Handle() {
		state.Handle(this);
	}
}

/**
 * 抽象状态类
 */
abstract class ShareState {

	/**
	 * 对请求做处理
	 * @param context
	 */
	public abstract void Handle(ShareContext context);
}

/**
 * 具体状态1类
 */
class ConcreteState1 extends ShareState {
	@Override
	public void Handle(ShareContext context) {
		System.out.println("当前状态是： 状态1");
		context.setState(context.getState("2"));
	}
}

/**
 * 具体状态2类
 */
class ConcreteState2 extends ShareState {
	@Override
	public void Handle(ShareContext context) {
		System.out.println("当前状态是： 状态2");
		context.setState(context.getState("1"));
	}
}