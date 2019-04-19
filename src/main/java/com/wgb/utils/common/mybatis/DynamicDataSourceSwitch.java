package com.wgb.utils.common.mybatis;

import com.wgb.utils.util.cache.CacheManage;
import com.wgb.utils.util.constants.database.DatabaseConstant;

/**
 * 动态数据源开关
 * @author INNERPEACE
 * @date 2019/3/29 17:08
 **/
public class DynamicDataSourceSwitch {

	private static ThreadLocal<String> routeKey = new ThreadLocal<>();

	public static String getRouteKey() {
		// 获取只读库标记
		String readOnlyFlag = CacheManage.getDataSourceFlagBykey(DatabaseConstant.READONLY_KEY);
		if(DatabaseConstant.READONLY_FLAG.equals(readOnlyFlag)) {
			// 可以走只读
			return routeKey.get();
		}else {
			// 不可以走只读
			return null;
		}
	}

	public static void setRouteKey(String key) {
		routeKey.set(key);
	}

	public static void removeRouteKey(String key) {
		routeKey.remove();
	}

}
