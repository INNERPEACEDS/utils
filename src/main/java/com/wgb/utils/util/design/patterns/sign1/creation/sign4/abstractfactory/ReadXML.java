package com.wgb.utils.util.design.patterns.sign1.creation.sign4.abstractfactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * @author INNERPEACE
 * @date 2019/6/19 15:31
 **/
class ReadXML {
	public static Object getObject() {
		try {
			DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dFactory.newDocumentBuilder();
			Document doc;
			doc = builder.parse(new File("target/classes/properties/xml/config.xml"));
			NodeList nl = doc.getElementsByTagName("classname");
			Node classNode = nl.item(0).getFirstChild();
			String cName = "com.wgb.utils.util.design.patterns.sign1.creation.sign4.abstractfactory." + classNode.getNodeValue();
			System.out.println("创建的新类名：" + cName);
			Class<?> c = Class.forName(cName);
			Object obj = c.newInstance();
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
