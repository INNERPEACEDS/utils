package com.wgb.utils.test;

import com.wgb.utils.entity.list.Person;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * @author INNERPEACE
 * @date 2019/10/16 9:55
 */
@Slf4j
public class InputTest {
	public static void main(String[] args) throws IOException {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(7, 3);
		log.info("时间：{}", c.getTime());
		Person a = getRandomPerson();
		assert a != null;
		String b = "order" + a.toString();
		System.out.println(b);
		/*while (true) {
			int value = System.in.read();
			if (value == 97) {
				break;
			}
			System.out.println(value);
		}*/
		// System.out.println(System.in.read());
	}

	public static Person getRandomPerson() {
		Person person = null;
		if (null != person) {
			return person;
		}
		return null;
	}
}
