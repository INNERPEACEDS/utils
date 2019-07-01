package com.wgb.utils.util.design.patterns.sign3.action.sign8.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author INNERPEACE
 * @date 2019/7/1
 **/
public class IteratorPattern {
	public static void main(String[] args) {
		Aggregate ag = new ConcreteAggregate();
		/*ag.add("哈佛");
		ag.add("斯坦福");
		ag.add("牛津");*/
		System.out.print("聚合的内容有：");
		Iterator it = ag.getIterator();
		while (it.hasNext()) {
			Object ob = it.next();
			System.out.print(ob.toString() + "\t");
		}
		Object ob = it.first();
		System.out.println("\nFirst：" + ob.toString());
	}
}


/**
 * 抽象聚合
 */
interface Aggregate {
	/**
	 * 添加
	 * @param obj
	 */
	void add(Object obj);

	/**
	 * 删除
	 * @param obj
	 */
	void remove(Object obj);

	/**
	 * 获取迭代器
	 * @return
	 */
	Iterator getIterator();
}

/**
 * 具体聚合
 */
class ConcreteAggregate implements Aggregate {

	private List<Object> list = new ArrayList<>();

	@Override
	public void add(Object obj) {
		list.add(obj);
	}

	@Override
	public void remove(Object obj) {
		list.remove(obj);
	}

	@Override
	public Iterator getIterator() {
		return (new ConcreteIterator(list));
	}
}

/**
 * 抽象迭代器
 */
interface Iterator {

	/**
	 * 第一个元素
	 * @return
	 */
	Object first();

	/**
	 * 下一个元素
	 * @return
	 */
	Object next();

	/**
	 * 是否有下一个元素
	 * @return
	 */
	boolean hasNext();
}


/**
 * 具体迭代器
 */
class ConcreteIterator implements Iterator {

	private List<Object> list;
	private int index = -1;

	public ConcreteIterator(List<Object> list) {
		this.list = list;
	}

	@Override
	public boolean hasNext() {
		if (index < list.size() - 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Object first() {
		index = 0;
		Object obj = list.get(index);
		return obj;
	}

	@Override
	public Object next() {
		Object obj = null;
		if (this.hasNext()) {
			obj = list.get(++index);
		}
		return obj;
	}
}
