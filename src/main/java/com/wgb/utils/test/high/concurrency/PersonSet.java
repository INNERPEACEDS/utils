package com.wgb.utils.test.high.concurrency;

import com.wgb.utils.entity.list.Person;

import javax.annotation.concurrent.ThreadSafe;
import java.util.HashSet;
import java.util.Set;

/**
 * 清单4.2 使用限制确保线程安全
 * @author INNERPEACE
 * @date 2020/4/24 16:50
 */
@ThreadSafe
public class PersonSet {
	private final Set<Person> mySet = new HashSet<>();

	public synchronized void addPerson(Person person) {
		mySet.add(person);
	}

	public synchronized boolean containsPerson(Person person) {
		return mySet.contains(person);
	}
}
