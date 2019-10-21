package com.wgb.utils.test.annotation;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author INNERPEACE
 * @date 2019/4/1 13:29
 **/
@Slf4j
public class UserCaseTest {
	public static void main(String[] args) {
		List<Integer> useCases = new ArrayList<Integer>();
		Collections.addAll(useCases, 47, 48, 49, 50);
		trackUseCases(useCases, PasswordUtils.class);
	}

	public static void trackUseCases(List<Integer> useCases, Class<?> cl) {
		PasswordUtils passwordUtils = new PasswordUtils();
		String password = "abcdefga";
		log.info("encryptPassword():{} ", passwordUtils.encryptPassword(password));
		log.info("validatePassword():{} ", passwordUtils.validatePassword(password));
		for (Method m : cl.getDeclaredMethods()) {
			// 获得注解的对象
			UseCase.UseCases uc = m.getAnnotation(UseCase.UseCases.class);
			if (uc != null) {
				System.out.println("Found Use Case:" + uc.id() + " "
						+ uc.description());
				useCases.remove(new Integer(uc.id()));
			}
		}
		for (int i : useCases) {
			System.out.println("Warning: Missing use case-" + i);
		}
	}
}
