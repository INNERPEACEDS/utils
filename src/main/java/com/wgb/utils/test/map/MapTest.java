package com.wgb.utils.test.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author INNERPEACE
 * @date 2019/10/31 17:44
 */
public class MapTest {
	public static void main(String[] agrs) throws InterruptedException {
		mapTest();
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
			System.out.println("1");
		}
	}
}
