package com.wgb.utils.util.parameter;

import com.wgb.utils.util.string.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : innerpeace
 * @date : 2018/11/29 17:50
 */
@Slf4j
public class ParameterCheckUtil {
	/**
	 * 加密实例
	 */
	//public static Encrypt en = new Encrypt();

	/**
	 * 时间格式化
	 */
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 一般空参数重置
	 * @param parameter 参数
	 * @param object    DTO对象
	 * @param fieldName DTO对象属性名
	 * @return
	 */
	public static String nullReset(String parameter, Object object, String fieldName) {
		if ((parameter != null) && (!("".equals(parameter))&&(!"all".equals(parameter)))){
			fieldSetExecute(parameter,object,fieldName);
		}else {
			parameter = "";
		}
		return parameter;
	}

	/**
	 * 条件类型空参数重置（type：1代表时间天开始时间：00:00:00；2代表天的结束时间：23:59:59；3：代表数据加密）
	 * @param parameter 参数
	 * @param object    DTO对象
	 * @param fieldName DTO对象属性名
	 * @return
	 */
	public static String nullReset(String parameter, Object object, String fieldName, int type) {
		String resetValue = "";
		if(!StringUtil.isEmpty(parameter)){
			// 加密类型
			if (type == 3) {
				try {
					//fieldName = en.encryptByWK(fieldName);
				} catch (Exception e) {
					log.info("加密失败",e);
				}
			}
			fieldSetExecute(parameter, object, fieldName);
		}
		// 类型为当日开始日期
		if (type == 1) {
			String date = sdf.format(new Date());
			resetValue = date + "00:00:00";
		}
		// 类型为当日结束日期
		if (type == 2) {
			String date = sdf.format(new Date());
			resetValue = date + "23:59:59";
		}
		parameter = resetValue;
		return parameter;
	}

	/**
	 * 类属性重新赋值执行
	 * @param parameter 赋值参数
	 * @param object    属性所属类
	 * @param fieldName 属性名称
	 */
	public static void fieldSetExecute(String parameter, Object object, String fieldName) {
		try {
			Field declaredField = object.getClass().getDeclaredField(fieldName);
			declaredField.setAccessible(true);
			declaredField.set(object, parameter);
		} catch (NoSuchFieldException e) {
			log.info("此类中无[{}]属性，请检查输入的属性名称",fieldName,e);
		} catch (SecurityException e) {
			log.info("安全错误异常", e);
		} catch (IllegalArgumentException e) {
			log.info("不合法的参数异常", e);
		} catch (IllegalAccessException e) {
			log.info("不合法的入口异常", e);
		}
	}
}
