package com.wgb.utils.util.collection;

import com.wgb.utils.entity.list.ListEntity;
import com.wgb.utils.entity.list.Person;
import com.wgb.utils.util.clazz.ClassUtil;
import com.wgb.utils.util.concurrency.thread.pool.ThreadPoolTool;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author INNERPEACE
 * @date 2019/8/2
 **/
@Slf4j
public class CollectionTest {
	public static void main(String[] args) {
		// listTest();
		// lambdaTest();
		ThreadPoolTool.getClassTypes(new ListEntity(), "aa", 56);
		ClassUtil.getClassTypes(new ListEntity(), "aa", 56);
	}

	public static void listTest() {
		List<ListEntity> list = new ArrayList<>();
		ListEntity listEntity = new ListEntity();
		listEntity.setA("a");
		listEntity.setB(1);
		Person person = new Person();
		person.setName("personA");
		listEntity.setPerson(person);
		list.add(listEntity);

		listEntity = new ListEntity();
		listEntity.setA("aa");
		listEntity.setB(2);
		person = new Person();
		person.setName("personAA");
		listEntity.setPerson(person);
		list.add(listEntity);

		listEntity = new ListEntity();
		listEntity.setA("aaa");
		listEntity.setB(3);
		person = new Person();
		person.setName("personAAA");
		listEntity.setPerson(person);
		list.add(listEntity);

		log.info("移除前的数据，list:{}", list);
		ListUtil.removeListDataByCondition(list);
		log.info("移除后的数据，list:{}", list);
	}

	public static void lambdaTest() {
		List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
		features.forEach(n -> System.out.println(n));

		// 使用Java 8的方法引用更方便，方法引用由::双冒号操作符标示，
		// 看起来像C++的作用域解析运算符
		features.forEach(System.out::println);
	}


}
