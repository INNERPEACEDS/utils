package com.wgb.utils.util.design.patterns.sign1.creation.sign2.prototype;

/**
 * 原型模式的测试类
 *
 * @author INNERPEACE
 * @date 2019/6/19 10:56
 **/
public class PrototypeTest {

	public static void main(String[] args) throws CloneNotSupportedException {
		/*RealizeType obj1 = new RealizeType();
		RealizeType obj2 = (RealizeType) obj1.clone();
		System.out.println("obj1==obj2?" + (obj1 == obj2));*/

		Address address = new Address("CH" , "SD" , "QD");
		Customer customer1 = new Customer(1 , 23 , address);
		Customer customer2 = customer1.clone();
		customer2.getAddress().setCity("JN");
		customer2.setId(2);
		System.out.println("customer1:"+customer1.toString());
		System.out.println("customer2:"+customer2.toString());
	}
}