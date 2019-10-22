package com.wgb.utils.util.serializable;

/**
 * @author INNERPEACE
 * @date 2019/10/10 10:28
 */
public class Manager extends Employee {
	private Employee secretary;


	Manager(String name, double salary, int year, int month, int day) {
		super(name, salary, year, month, day);
		secretary = null;
	}

	public Employee getSecretary() {
		return secretary;
	}

	public void setSecretary(Employee secretary) {
		this.secretary = secretary;
	}

	@Override
	public String toString() {
		return super.toString() + "[secretary=" + secretary + "]";
	}
}
