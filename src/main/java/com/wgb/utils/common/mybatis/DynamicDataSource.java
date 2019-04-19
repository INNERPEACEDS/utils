package com.wgb.utils.common.mybatis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 自定义扩展动态数据源
 * @author INNERPEACE
 * @date 2019/3/29 17:07
 **/
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {
	/**
	 * 动态选择主从库
	 * @return
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		String dataSourceKey = DynamicDataSourceSwitch.getRouteKey();
		dataSourceKey = (dataSourceKey == null ? "master" : dataSourceKey);
		log.info("当前选择数据源：" + dataSourceKey);
		return DynamicDataSourceSwitch.getRouteKey();
	}
}

