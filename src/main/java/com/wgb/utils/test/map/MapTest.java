package com.wgb.utils.test.map;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author INNERPEACE
 * @date 2019/10/31 17:44
 */
@Slf4j
public class MapTest {
	public static void main(String[] agrs) throws InterruptedException {
//		mapThreadInterruptedTest();
//		mapTest();
		mapErgodicTest();

	}

	public static void mapThreadInterruptedTest() throws InterruptedException {
		// final HashMap<String,String> map = new HashMap<String,String>();
		// final Hashtable<String,String> map = new Hashtable<>();
		final ConcurrentHashMap<String,String> map = new ConcurrentHashMap<>(16);
		long start = System.currentTimeMillis();
		Thread t = new Thread(new Runnable(){
			@Override
			public  void run(){
				for (int x = 0; x < 100000; x++) {
					int finalX = x;
					Thread tt = new Thread(new Runnable() {
						@Override
						public void run() {
							String a = UUID.randomUUID().toString();
							/*if (map.get(a) != null) {
								System.out.println("测试中断");
								return;
							}*/
							// map.put(UUID.randomUUID().toString(),"");
							map.put(a, String.valueOf(2));
						}
					});
					tt.start();
					/*System.out.println(tt.getName());*/
				}
			}
		});
		t.start();
		t.join();
		long end = System.currentTimeMillis();
		Thread.sleep(4000);
		System.out.println("耗时：" + (end - start) + ",map的值：" + map.size());
	}

	public static void mapTest() {
		ArrayList<Map<String, String>> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			HashMap<String, String> data = new HashMap<>();
			data.put(String.valueOf(i), String.valueOf(i));
			list.add(data);
			data.put(String.valueOf(i), "2");
			data = null;
			data = new HashMap<>();
			data.put(String.valueOf(i), "3");
			System.out.println("1");
		}
	}

	public static void mapErgodicTest() {
		Map<String, String> map = new HashMap<>();
		for (int i = 0; i < 10; i++) {
			map.put(String.valueOf(i), String.valueOf(i));
		}

		// 1、map.entrySet方式，该方式可修改已存在键的值信息，不能进行增加和删除操作；
		/*for (Map.Entry entry : map.entrySet()) {
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			log.info("key:{},value:{}", key, value);
			if ("5".equals(key)) {
//				map.remove(key);
				map.put(String.valueOf(value), "555");
			}
		}*/

		// 2.Iterator遍历期间，也不能进行增加移除操作
		Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
		while(entries.hasNext()){
			Map.Entry<String, String> entry = entries.next();
			String key = entry.getKey();
			String value = entry.getValue();
			log.info("key:{},value:{}", key, value);
			if ("5".equals(key)) {
				map.remove(key);
//				map.put(String.valueOf(value), "555");
			}
		}

		log.info("map:{}", map);
	}
}
