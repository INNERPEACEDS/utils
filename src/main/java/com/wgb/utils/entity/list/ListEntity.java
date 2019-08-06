package com.wgb.utils.entity.list;

/**
 * @author INNERPEACE
 * @date 2019/8/2
 **/
public class ListEntity {
	private String a;
	private int b;

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	private Person person;

	public boolean condition(ListEntity listEntity) {
		if ("a".equals(listEntity.getA())) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "ListEntity{" +
				"a='" + a + '\'' +
				", b=" + b +
				", person=" + person +
				'}';
	}
}


