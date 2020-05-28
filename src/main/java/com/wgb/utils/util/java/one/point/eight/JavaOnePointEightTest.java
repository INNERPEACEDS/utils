package com.wgb.utils.util.java.one.point.eight;

import com.wgb.utils.util.java.one.point.eight.permission.PermissionClass;
import lombok.extern.slf4j.Slf4j;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 1.8新特性：
 * 2.方法引用：方法引用提供了非常有用的语法，可以直接引用已有Java类或对象（实例）的方法或构造器。与lambda联合使用，方法引用可以使语言的构造更紧凑简洁，减少冗余代码。
 * @author INNERPEACE
 * @date 2019/8/2 18:46
 **/
@Slf4j
public class JavaOnePointEightTest {

	public static void main(String[] args) {
		testUrlTransport();
//		 methodReference();
		// sort();
		// functionalInterface();
		// stream();
		// invokeJavaScript();
		// testLocalDateTime();
		// testZonedDateTime();
		// testBase64();
//		testBase641();
	}

	public static void javaDefaultMethodPermissionTest() {
		PermissionClass permissionClass = new PermissionClass();
		// permissionClass.test(); 不在同一包中，无法访问
	}

	public static void methodReference() {
//		UserInterface<String> user = User::getName;
//		user.convert("测试");
//
//		UserInterface<String> userInterface = User::getName;
//		userInterface.convert("自定义function");

		UserInterface<String, User, String> userInterface1 = str -> new User(str);
		User user = userInterface1.convert("自定义function1");
		user.disName("自定义function1");

		Function<String, User> function = User::new;
		User apply = function.apply("构造器");

		User user1 = new User();


		Consumer<String> consumer1 = user1::disName;
		consumer1.accept("实例方法调用");
	}

	public static void sort() {
		List<String> names1 = new ArrayList<String>();
		names1.add("Google ");
		names1.add("Runoob ");
		names1.add("Taobao ");
		names1.add("Baidu ");
		names1.add("Sina ");

		List<String> names2 = new ArrayList<String>();
		names2.add("Google ");
		names2.add("Runoob ");
		names2.add("Taobao ");
		names2.add("Baidu ");
		names2.add("Sina ");

		JavaOnePointEightTest javaOnePointEightTest = new JavaOnePointEightTest();
		System.out.println("使用JAVA 7 排序语法");
		javaOnePointEightTest.sortUsingJava7(names1);
		System.out.println(names1);
		System.out.println("使用JAVA 8 排序语法");
		javaOnePointEightTest.sortUsingJava8(names2);
		System.out.println(names2);
	}

	public void sortUsingJava7(List<String> list) {
		list.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
	}

	public void sortUsingJava8(List<String> list) {
		// Collections.sort(list, (s1, s2) -> s1.compareTo(s2));
		// Collections.sort(list, String::compareTo);
		// list.sort((s1, s2) -> s1.compareTo(s2));
		list.sort(String::compareTo);
	}

	public static void functionalInterface() {
		List<Integer> integers = Arrays.asList(58, 69, 74, 42, 34, 24);
		// Predicate<Integer> predicate = n -> true
		// n 是一个参数传递到 Predicate 接口的 test 方法
		// n 如果存在则 test 方法返回 true
		System.out.println("输出所有数据：");
		// eval(integers, n -> true);
		integers.stream().filter(n -> true).forEach(n -> System.out.print(n + " "));
		System.out.println();

		// Predicate<Integer> predicate1 = n -> n%2 == 0
		// n 是一个参数传递到 Predicate 接口的 test 方法
		// 如果 n%2 为 0 test 方法返回 true
		System.out.println("输出所有偶数：");
		// eval(integers, n -> n % 2 == 0);
		integers.stream().filter(n -> n % 2 == 0).forEach(n -> System.out.print(n + " "));
		System.out.println();

		// Predicate<Integer> predicate2 = n -> n > 50
		// n 是一个参数传递到 Predicate 接口的 test 方法
		// 如果 n 大于 50 test 方法返回 true
		System.out.println("输出所有大于50的数:");
		// eval(integers, n -> n > 50);
		integers.stream().filter(n -> n > 50).forEach(n -> System.out.print(n + " "));
		System.out.println();



		Map map1 = new HashMap();
		map1.put("a", "aa");
		map1.put("b", "bb");
		Map map2 = new HashMap();
		map2 = map1;
		map1.put("c", "cc");
		System.out.println(map1 + "," + map2);
	}

	public static void eval(List<Integer> list, Predicate<Integer> predicate) {
		for (Integer integer : list) {
			if (predicate.test(integer)) {
				System.out.print(integer + " ");
			}
		}
		System.out.println();
	}

	public static void stream() {
		List<Integer> integers = Arrays.asList(2, 5, 3, -2, -5, 6, -1, 7, -2);
		integers.stream().map(i -> i * i).distinct().collect(Collectors.toList()).forEach(System.out::println);
	}

	public static void invokeJavaScript() {
		ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		ScriptEngine nashorn = scriptEngineManager.getEngineByName("nashorn");
		String path = "D:\\wgb\\JavaScript\\sample.js";

		try (
				BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
		) {
			nashorn.eval(bufferedReader);
		} catch (ScriptException | IOException e) {
			System.out.print(e);
		}

	}

	public static void testLocalDateTime(){
		// 获取当前的日期时间
		LocalDateTime currentTime = LocalDateTime.now();
		System.out.println("当前时间: " + currentTime);
		LocalDate localDate = currentTime.toLocalDate();
		System.out.println("当前日期:" + localDate);

		Month month = currentTime.getMonth();
		int dayOfMonth = currentTime.getDayOfMonth();
		int hours = currentTime.getHour();
		int minutes = currentTime.getMinute();
		int seconds = currentTime.getSecond();
		System.out.println("月: " + month + ", 日: " + dayOfMonth + ", 时: " + hours + ", 分: " + minutes + ", 秒: " + seconds);

		LocalDateTime localDateTime = currentTime.withDayOfMonth(10).withYear(2012);
		System.out.println(localDateTime);
		LocalDate date = LocalDate.of(2014, Month.DECEMBER, 12);
		System.out.println(date);
		// 22 小时 15 分钟
		LocalTime time = LocalTime.of(22, 15);
		System.out.println(time);
		// 解析字符串
		LocalTime parseTime = LocalTime.parse("17:20:29");
		System.out.println(parseTime);
	}

	public static void testZonedDateTime() {
		// 获取当前时间日期
		ZonedDateTime date1 = ZonedDateTime.parse("2019-10-28T18:55:30+05:30[Asia/Shanghai]");
		System.out.println("date1: " + date1);

		ZoneId id = ZoneId.of("Europe/Paris");
		System.out.println("ZoneId: " + id);

		ZoneId currentZone = ZoneId.systemDefault();
		System.out.println("当期时区: " + currentZone);
	}

	public static void testBase64() {
		// 1.使用基本编码
		String base64encodedString = Base64.getEncoder().encodeToString("runoob?java8".getBytes(StandardCharsets.UTF_8));
		System.out.println("Base64 编码字符串 (基本) :" + base64encodedString);
		byte[] base64decodedBytes  = Base64.getDecoder().decode(base64encodedString);
		System.out.println("解密后的字符串：" + new String(base64decodedBytes, StandardCharsets.UTF_8));

		// 2.使用URL编码
		base64encodedString = Base64.getUrlEncoder().encodeToString("runoob?java8".getBytes(StandardCharsets.UTF_8));
		System.out.println("Base64 编码字符串 (URL) :" + base64encodedString);
		System.out.println("解密：" + new String(Base64.getUrlDecoder().decode(base64encodedString), StandardCharsets.UTF_8));
		// 3.使用MIME
		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0; i < 10; ++i) {
			stringBuilder.append(UUID.randomUUID().toString());
		}

		byte[] mimeBytes = stringBuilder.toString().getBytes(StandardCharsets.UTF_8);
		String mimeEncodedString = Base64.getMimeEncoder().encodeToString(mimeBytes);
		System.out.println("Base64 编码字符串 (MIME) :" + mimeEncodedString);
		System.out.println("解密：" + new String(Base64.getMimeDecoder().decode(mimeEncodedString), StandardCharsets.UTF_8));
	}

	public static void testBase641() {
		String str = "ZXlKeVpYTjFiSFFpT2lKdmF5SXNJbU5vWVc1dVpXd2lPaUpYV0NJc0ltbGtJam9pVnpFNU1UQXdPVEk1TWpNeU9USXpOakEyT1RjNE9Ea2lmUT09";
		byte[] decode = Base64.getDecoder().decode(str);
		System.out.println("解密：" + new String(Base64.getDecoder().decode(new String(decode))));
	}

	public static void testUrlTransport() {
		String encryptStr = "TNbihRPVSX1Dw iW8h9bS3xjM1wXFr2mRh4N2Wham7v7/IgEo8UeGIC1k1XuZc9P 3JoESbvnZE5JUJWq6Fz5ehTinEFgG65NYbKTrb2N Rf0sCMsMYgQcIv/w7vCn6B9p66BG20vkiXlSnjDDCe kYzjwUmonUk0KBvJRXGuPo=";
		try {
			log.info("结果：{}", URLEncoder.encode(encryptStr, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
