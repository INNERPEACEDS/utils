package com.wgb.utils.util.xml;

/**
 * @author INNERPEACE
 * @date 2019/11/12
 */
public abstract class BaseXml<T> {
	public String encoding = "UTF-8";

	public boolean isFormatXml = false;

	/**
	 * Object（T类型）转xml格式
	 * @param t T类型对象
	 * @return
	 */
	abstract String object2Xml(T t);

	/**
	 * xml转Object（T类型）格式
	 * @param xml xml字符
	 * @return
	 */
	abstract T xml2Object(String xml);
}
