package com.wgb.utils.test.string;

import com.wgb.utils.test.MessageList;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author INNERPEACE
 * @date 2019/11/1 16:16
 */
@Slf4j
public class StringTest {

	public static void main(String[] args) {
		stringTest();
		String a = "2" + "\\" + "2kk";
		System.out.println(a);
		List<String> list = MessageList.getInstance().getMessages();
		System.out.println("list1:" + list);
		if (list == null) {
			list = new ArrayList<>();
			MessageList.getInstance().setMessages(list);
		}
		list.add("2");
		list.add("3333");
		list.forEach(System.out::println);
		// System.out.println("list2:" + list);
		System.out.println("list2:" + MessageList.getInstance().getMessages());
		// list = null;
		// System.out.println("list3:" + list);
		System.out.println("list3:" + MessageList.getInstance().getMessages());
		ArrayList<String> strings = new ArrayList<>(list);
		System.out.println("list:" + list.toString() + ";newlist:" + strings.toString() + "值：" + (list==strings));
		strings.forEach(System.out::println);
		String a1 = "2";
		for (int i = 0; i < 100; i++) {
			String newI = getNewSequence(String.valueOf(i), 2);
			String a2 = a1 + newI;
			String newSequence = getNewSequence(a2, 6);
			System.out.print(newSequence+",");
		}
	}

	public static void stringTest() {
		String a = new String("abcde1234");
		String b = new String("abcde1234");
		String c = "abcde1234";
		String d = "abcde1234";

		log.info("a=b:{}, a=c:{}, c=d:{}", a == b, a == c, c == d);
	}

	private static String getNewSequence(String remark, int n){
		String newRemark = String.valueOf(Integer.valueOf(remark));
		int length = newRemark.length();
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < n-length; i++) {
			stringBuilder.append("0");
		}
		return stringBuilder.append(newRemark).toString();
	}

}
