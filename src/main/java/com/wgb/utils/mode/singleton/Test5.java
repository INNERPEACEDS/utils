package com.wgb.utils.mode.singleton;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
@Slf4j
public class Test5 {
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		
		Singleton5 s1 = Singleton5.getInstance();
		Singleton5  s3  = Singleton5.getInstance();
		log.info("s1==s3为:{}",s1==s3);

		
	    Class<?> c = s1.getClass();
		Constructor<?> constructor = c.getDeclaredConstructor();
		
		constructor.setAccessible(true);
		Singleton5 s2 = (Singleton5)constructor.newInstance();
		log.info("s1==s2为:{}",s1==s2);
	}
	
}
