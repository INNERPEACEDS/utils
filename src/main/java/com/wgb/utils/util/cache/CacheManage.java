package com.wgb.utils.util.cache;

import com.wgb.utils.util.constants.database.DatabaseConstant;
import com.wgb.utils.util.string.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

/**
 * 缓存管理器 用于管理缓存信息
 * @author INNERPEACE
 * @date 2019/3/29 17:11
 **/
@Slf4j
public class CacheManage {

	/**
	 * 验证码缓存 存在时间 5分钟
	 * cache Key
	 */
	public static Cache<String,SmsCodeModel> emailCodeCache = new Cache<String,SmsCodeModel>(5);

	/**
	 * 只读库标识缓存【用于当只读库奔溃时，切换至生产库】 存在时间 1分钟
	 *
	 */
	public static Cache<String,String> dataSourceFlagCache = new Cache<String,String>(1);

	/**
	 * 获取只读库标识
	 * @param key
	 * @return
	 */
	public static String getDataSourceFlagBykey(String key) {
		String readOnlyFlag = "";
		try {
			String val = dataSourceFlagCache.get(DatabaseConstant.READONLY_KEY);
			/**
			 * 缓存中取不到从配置文件中获取[重新加载配置文件获取最新数据]
			 */
			if(StringUtil.isEmpty(val)) {
				PropertiesConfiguration config = new PropertiesConfiguration("database/databaseStatus.properties");
				config.setReloadingStrategy(new FileChangedReloadingStrategy());
				String flag = config.getString(DatabaseConstant.READONLY_KEY);
				readOnlyFlag = flag;
				dataSourceFlagCache.put(DatabaseConstant.READONLY_KEY, flag);
			}else {
				readOnlyFlag = val;
			}
		} catch (Exception e) {
			log.error("获取只读库标识失败",e);
			readOnlyFlag = "";
		}
		return readOnlyFlag;
	}
}

