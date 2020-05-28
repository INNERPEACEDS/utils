package com.wgb.utils.test.high.concurrency;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 清单4.12 安全发布底层状态的机动车追踪器
 * @author INNERPEACE
 * @date 2020/4/28 16:13
 */
@ThreadSafe
@Slf4j
public class PublishingVehicleTracker {
	private final Map<String, SafePoint> locations;
	private final Map<String, SafePoint> unmodifiableMap;

	public PublishingVehicleTracker(Map<String, SafePoint> locations) {
		this.locations = locations;
		// unmodifiableMap将存储locations信息
		this.unmodifiableMap = Collections.unmodifiableMap(this.locations);
	}

	public Map<String, SafePoint> getLocations() {
		// 因为 unmodifiableMap存储有locations信息，所以返回该不可修改unmodifiableMap，会拿到locations最新的信息。
		return unmodifiableMap;
	}

	public SafePoint getLocation(String id) {
		return locations.get(id);
	}

	public void setLocations(String id, int x, int y) {
		if (!locations.containsKey(id)) {
			throw new IllegalArgumentException("" +
					"invalid vehicle name:" + id);
		}
		locations.get(id).set(x, y);
	}

	public static void main(String[] args) {
		Map<String,SafePoint> map = new HashMap<>();
		map.put("1", new SafePoint(1,1));
		PublishingVehicleTracker publishingVehicleTracker = new PublishingVehicleTracker(map);
		Map<String, SafePoint> locations = publishingVehicleTracker.getLocations();
		map.put("1", new SafePoint(1,2));
		log.info("locations:{}", locations);
	}

}
