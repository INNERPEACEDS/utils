package com.wgb.utils.util.collection;

import com.wgb.utils.entity.list.ListEntity;
import com.wgb.utils.util.clazz.ClassUtil;
import lombok.extern.slf4j.Slf4j;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author INNERPEACE
 * @date 2019/8/1
 **/
@Slf4j
public class ListUtil {

	/**
	 * 使用java的集合移除工具（内部使用lambda表达式，也可使用匿名类）
	 * @param list
	 */
	public static void removeListDataByCondition(List<ListEntity> list) {
		// 1.使用lambda表达式
		list.removeIf(listEntity -> listEntity.getA().equals("aa"));
		// 2.使用匿名类
		list.removeIf( new Predicate<ListEntity>() {
			@Override
			public boolean test(ListEntity listEntity) {
				return "aa".equals(listEntity.getA());
			}
		});
	}

	/**
	 * 自定义移除集合功能
	 * @param list
	 */
	public static void removeListDataByCondition1(List<?> list) {
		Iterator<?> iterator = list.iterator();
		while(iterator.hasNext()) {
			Object listData = iterator.next();
			if (shouldBeRemoved(listData)) {
				iterator.remove();
			}
		}

	}

	public static boolean shouldBeRemoved(Object listData) {
		Class<?> classType = listData.getClass();
		try {
			// 获取Method对象
			// Method method = classType.getDeclaredMethod(methodName, (Class<?>[]) getClassTypes(args));
			Method method = classType.getDeclaredMethod("condition", ClassUtil.getClassTypes(listData));
			// 抑制Java的访问控制检查
			method.setAccessible(true);
			// 如果不加上上面这句，将会Error: TestPrivate can not access a member of class PrivateClass with modifiers "private"
			return (boolean) method.invoke(listData, listData);
		} catch (Exception e) {
			log.error("执行条件异常", e);
		}
		return false;
	}
}
