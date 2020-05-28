package com.wgb.utils.test.high.concurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 清单4.7 将线程安全委托到ConcurrentHashMap
 * @author INNERPEACE
 * @date 2020/4/27 9:30
 */
@Slf4j
public class DelegatingVehicleTracker {
	// 该类线程安全性源自于它所委托的底层ConcurrentHashMap，因为Point是不可变的，所以不会修改Point值，
	// 但如果Point是可变的类，在使用getLocation方法返回底层Map的不可变拷贝，调用者在其上
	// 无法添加或移除车辆，却可以通过修改返回的Map中的Point的值，改变一个机动车的位置。
	
	private final ConcurrentHashMap<String, Point> locations;
	private final Map<String, Point> unmodifiableMap;

	DelegatingVehicleTracker(Map<String, Point> points) {
		this.locations = new ConcurrentHashMap<>(points);
		this.unmodifiableMap = Collections.unmodifiableMap(locations);
	}

	public Map<String, Point> getLocations() {
		return unmodifiableMap;
	}

	public Point getLocation(String id) {
		return locations.get(id);
	}

	public void setLocation(String id, int x, int y) {
//		replace方法返回map的值的修改之前的值
//		log.info("值：{}", locations.replace(id, new Point(x, y)));
		if (locations.replace(id, new Point(x, y)) == null) {
			throw new IllegalArgumentException("invalid vehicle name："+ id);
		}
	}

	public static void main(String[] args) {
		Map<String, Point> vehicleMap = new HashMap<>();
		vehicleMap.put("0", new Point(0, 0));
		DelegatingVehicleTracker delegatingVehicleTracker = new DelegatingVehicleTracker(vehicleMap);
		delegatingVehicleTracker.setLocation("0", 1, 1);
		log.info("对象:{}", delegatingVehicleTracker);
		/*Map<String, String> map = getMap("1", "2");
		log.info("外层map：{}", map.toString());*/
		Map<String, Point> location = new HashMap<>();
		location = delegatingVehicleTracker.getLocations();
		location.put("1", new Point(2, 2));
		log.info("location:{}", location);
	}

	public static Map<String, String> getMap(String a, String b) {
		HashMap<String, String> map1 = new HashMap<>();
		HashMap<String, String> map = new HashMap<>();
		log.info("map值：{},map:{}", map.put(a, b), map.toString());
		return map;

	}
}
