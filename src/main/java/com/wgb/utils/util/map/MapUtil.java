package com.wgb.utils.util.map;

import java.util.*;

/**
 * @author INNERPEACE
 * @date 2019/11/15 9:52
 */
public class MapUtil {
	/**
	 * 求Map<K,V>中Value(值)的最大值
	 * @param map
	 * @return
	 */
	public static int getMaxValue(Map<Integer, Double> map) {
		List<Map.Entry<Integer, Double>> list= new ArrayList<Map.Entry<Integer, Double>>(map.entrySet());
		//降序排序
		Collections.sort(list, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));
		//获取最大的概率对应的key值
		int key = 0;
		for (Map.Entry<Integer, Double> mapping : list) {
			key = mapping.getKey();
			break;
		}
		return key;
	}
	/**
	 * 获取map中第一个数据值
	 *
	 * @param <K> Key的类型
	 * @param <V> Value的类型
	 * @param map 数据源
	 * @return 返回的值
	 */
	public static <K, V> K getFirstOrNull(Map<K, V> map) {
		K obj = null;
		for (Map.Entry<K, V> entry : map.entrySet()) {
			obj = entry.getKey();
			if (obj != null) {
				break;
			}
		}
		return obj;
	}

	/**
	 * 通过map获取key值
	 */
	public class MapValueGetKey {
		HashMap map;
		public MapValueGetKey(HashMap map) { // 初始化操作
			this.map = map;
		}

		public Object getKey(Object value) {
			Object o = null;
			ArrayList all = new ArrayList(); // 建一个数组用来存放符合条件的KEY值

			/*
			 * 这里关键是那个entrySet()的方法,它会返回一个包含Map.Entry集的Set对象.
			 * Map.Entry对象有getValue和getKey的方法,利用这两个方法就可以达到从值取键的目的了 *
			 */

			Set set = map.entrySet();
			for (Object item : set) {
				Map.Entry entry = (Map.Entry) item;
				if (entry.getValue().equals(value)) {
					o = entry.getKey();
					all.add(o); // 把符合条件的项先放到容器中,下面再一次性打印出
				}
			}
			return all;
		}
	}
}