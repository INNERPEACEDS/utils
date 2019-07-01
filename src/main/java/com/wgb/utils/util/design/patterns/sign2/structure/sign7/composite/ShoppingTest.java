package com.wgb.utils.util.design.patterns.sign2.structure.sign7.composite;

import java.util.ArrayList;

/**
 * 组合模式：安全组合模式
 *
 * @author INNERPEACE
 * @date 2019/6/27 9:55
 **/
public class ShoppingTest {
	public static void main(String[] args) {
		float s = 0;
		Bags bigBag, mediumBag, smallRedBag, smallWhiteBag;
		Goods sp;
		bigBag = new Bags("大袋子");
		mediumBag = new Bags("中袋子");
		smallRedBag = new Bags("红色小袋子");
		smallWhiteBag = new Bags("白色小袋子");
		sp = new Goods("婺源特产", 2, 7.9f);
		smallRedBag.add(sp);
		sp = new Goods("婺源地图", 1, 9.9f);
		smallRedBag.add(sp);
		sp = new Goods("韶关香菇", 2, 68);
		smallWhiteBag.add(sp);
		sp = new Goods("韶关红茶", 3, 180);
		smallWhiteBag.add(sp);
		sp = new Goods("景德镇瓷器", 1, 380);
		mediumBag.add(sp);
		mediumBag.add(smallRedBag);
		sp = new Goods("李宁牌运动鞋", 1, 198);
		bigBag.add(sp);
		bigBag.add(smallWhiteBag);
		bigBag.add(mediumBag);
		System.out.println("您选购的商品有：");
		bigBag.show();
		s = bigBag.calculation();
		System.out.println("要支付的总价是：" + s + "元");
	}
}
//

/**
 * 抽象构件：物品
 */
interface Articles {

	/**
	 * 计算
	 * @return
	 */
	public float calculation();

	/**
	 * 展示
	 */
	public void show();
}

/**
 * 树叶构件：商品
 */
class Goods implements Articles {
	/**
	 * 名字
	 */
	private String name;

	/**
	 * 数量
	 */
	private int quantity;

	/**
	 * 单价
	 */
	private float unitPrice;

	public Goods(String name, int quantity, float unitPrice) {
		this.name = name;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}

	@Override
	public float calculation() {
		return quantity * unitPrice;
	}

	@Override
	public void show() {
		System.out.println(name + "(数量：" + quantity + "，单价：" + unitPrice + "元)");
	}
}

/**
 * 树枝构件：袋子
 */
class Bags implements Articles {

	/**
	 * 名字
	 */
	private String name;

	private ArrayList<Articles> bags = new ArrayList<>();

	public Bags(String name) {
		this.name = name;
	}

	public void add(Articles c) {
		bags.add(c);
	}

	public void remove(Articles c) {
		bags.remove(c);
	}

	public Articles getChild(int i) {
		return bags.get(i);
	}

	@Override
	public float calculation() {
		float s = 0;
		for (Object obj : bags) {
			s += ((Articles) obj).calculation();
		}
		return s;
	}

	@Override
	public void show() {
		for (Object obj : bags) {
			((Articles) obj).show();
		}
	}
}