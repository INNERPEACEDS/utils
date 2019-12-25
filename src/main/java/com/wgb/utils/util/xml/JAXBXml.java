package com.wgb.utils.util.xml;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

/**
 * @author INNERPEACE
 * @date 2019/11/29 15:20
 */
public class JAXBXml<T>  extends BaseXml<T> {
	private Class clazz;
	private JAXBContext jaxbContext;
	private int maxCacheSize = 10;
	private static Multimap<String, JAXBContext> cacheMap = ArrayListMultimap.create();

	public JAXBXml(Class clazz) {
		this.clazz = clazz;
	}

	public JAXBXml(Class clazz, int maxCacheSize) {
		this.clazz = clazz;
		this.maxCacheSize = maxCacheSize;
	}

	private JAXBContext getContext() throws JAXBException {
		synchronized(cacheMap) {
			String className = this.clazz.getName();
			if (cacheMap != null && !cacheMap.isEmpty()) {
				if (cacheMap.containsKey(className)) {
					this.jaxbContext = (JAXBContext)cacheMap.get(className).toArray()[0];
					cacheMap.remove(className, this.jaxbContext);
				} else {
					this.jaxbContext = JAXBContext.newInstance(new Class[]{this.clazz});
				}
			} else {
				this.jaxbContext = JAXBContext.newInstance(new Class[]{this.clazz});
			}
		}
		return this.jaxbContext;
	}

	private void putContext() {
		synchronized(cacheMap) {
			String className = this.clazz.getName();
			if (this.jaxbContext != null && this.maxCacheSize > 0 && this.maxCacheSize > cacheMap.get(className).size()) {
				cacheMap.put(this.clazz.getName(), this.jaxbContext);
			} else {
				System.out.println("o");
			}
		}
	}

	@Override
	public String object2Xml(T t) {
		Marshaller jaxbMarshaller = null;
		StringWriter writer = null;
		String xml = null;
		try {
			this.getContext();
			jaxbMarshaller = this.jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty("jaxb.formatted.output", this.isFormatXml);
			writer = new StringWriter();
			jaxbMarshaller.marshal(t, writer);
			xml = writer.toString().replace("standalone=\"yes\"", "");
		} catch (JAXBException var14) {
			var14.printStackTrace();
		} finally {
			this.putContext();
			if (writer != null) {
				writer.flush();
				try {
					writer.close();
				} catch (IOException var13) {
					var13.printStackTrace();
				}
			}
		}
		return xml;
	}

	@Override
	public T xml2Object(String xml) {
		Object t = null;
		try {
			this.getContext();
			Unmarshaller jaxbUnmarshaller = this.jaxbContext.createUnmarshaller();
			t = jaxbUnmarshaller.unmarshal(new ByteArrayInputStream(xml.getBytes(this.encoding)));
		} catch (JAXBException | UnsupportedEncodingException var9) {
			var9.printStackTrace();
		} finally {
			this.putContext();
		}
		return (T) t;
	}
}
