package com.wgb.utils.util.clazz;

import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;

/**
 * @author INNERPEACE
 * @date 2019/8/2
 **/
@Slf4j
public class ClassUtil {
	/**
	 * 获取参数的类类型
	 * @param args 参数
	 * @return
	 */
	public static Class[] getClassTypes(Object... args) {
		Class[] classTypes = new Class[args.length];
		int i = 0;
		for (Object arg : args) {
			classTypes[i++] = arg.getClass();
		}
		log.info("参数的类类型为：{}", classTypes);
		return classTypes;
	}

}
