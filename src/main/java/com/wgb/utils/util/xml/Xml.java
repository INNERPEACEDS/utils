package com.wgb.utils.util.xml;

/**
 * @author INNERPEACE
 * @date 2019/11/29 15:42
 */
public class Xml<T> extends BaseXml<T> {
	private BaseXml xml;
	private Class clazz;

	public Xml(Class clazz) {
		this.xml = new JAXBXml(clazz);
	}

	@Override
	public String object2Xml(T t) {
		return this.xml.object2Xml(t);
	}

	@Override
	public T xml2Object(String xml) {
		return (T) this.xml.xml2Object(xml);
	}

}
