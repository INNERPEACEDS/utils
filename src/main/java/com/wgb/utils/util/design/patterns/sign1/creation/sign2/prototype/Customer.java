package com.wgb.utils.util.design.patterns.sign1.creation.sign2.prototype;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author INNERPEACE
 * @date 2019/6/19 13:30
 **/
@Slf4j
class Customer implements Cloneable{
	public int id;
	public int age;

	public Address address;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	public Customer(int id, int age, Address address) {
		super();
		this.id = id;
		this.age = age;
		this.address = address;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", age=" + age + ", address=" + address + "]";
	}

	@Override
	public Customer clone() {
		Customer customer = null;
		try {
			customer = (Customer) super.clone();
		} catch (CloneNotSupportedException e) {
			log.error("Customer克隆异常", e);
		}
		customer.address = customer.address.clone();
		return customer;
	}
}

