package com.wgb.utils.util.design.patterns.sign3.action.sign11.interpreter;

import java.util.HashSet;
import java.util.Set;

/**
 * 文法规则
 * <expression> ::= <city>的<person>
 * <city> ::= 韶关|广州
 * <person> ::= 老人|妇女|儿童
 *
 * @author INNERPEACE
 * @date 2019/7/1
 **/
public class InterpreterPatternDemo {

	public static void main(String[] args) {
		Context bus = new Context();
		bus.freeRide("英国的老人");
		bus.freeRide("法国的年轻人");
		bus.freeRide("威尼斯的妇女");
		bus.freeRide("迪拜的儿童");
		bus.freeRide("卡丁斯堡的儿童");
	}
}

/**
 * 抽象表达式类
 */
interface Expression {
	/**
	 * 解释器
	 * @param info
	 * @return
	 */
	boolean interpret(String info);
}


/**
 * 终结符表达式类
 */
class TerminalExpression implements Expression {

	private Set<String> set = new HashSet<>();

	public TerminalExpression(String[] data) {
		for (int i = 0; i < data.length; i++){
			set.add(data[i]);
		}
	}

	@Override
	public boolean interpret(String info) {
		if (set.contains(info)) {
			return true;
		}
		return false;
	}
}


/**
 * 非终结符表达式类
 */
class AndExpression implements Expression {

	private Expression city;
	private Expression person;

	public AndExpression(Expression city, Expression person) {
		this.city = city;
		this.person = person;
	}

	@Override
	public boolean interpret(String info) {
		String[] s = info.split("的");
		return city.interpret(s[0]) && person.interpret(s[1]);
	}
}

/**
 * 环境类
 */
class Context {

	private static final String[] CITIES = {"英国", "法国"};
	private static final String[] PERSONS = {"老人", "妇女", "儿童"};
	private Expression cityPerson;

	public Context() {
		Expression city = new TerminalExpression(CITIES);
		Expression person = new TerminalExpression(PERSONS);
		cityPerson = new AndExpression(city, person);
	}

	public void freeRide(String info) {
		boolean ok = cityPerson.interpret(info);
		if (ok){
			System.out.println("您是" + info + "，您本次乘车免费！");
		} else {
			System.out.println(info + "，您不是免费人员，本次乘车扣费2元！");
		}
	}
}
