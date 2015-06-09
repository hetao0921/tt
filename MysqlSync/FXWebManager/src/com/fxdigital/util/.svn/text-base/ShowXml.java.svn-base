package com.fxdigital.util;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ShowXml {

	public static String showXml(String url) {

		try {

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

			DocumentBuilder db = dbf.newDocumentBuilder();

			Document doc = db.parse(url);
			// 获取并修改default子节点值
			Element ele = (Element) doc.getElementsByTagName("TransProtocol")
					.item(0);
			return ele.getChildNodes().item(1).getTextContent();
	
			
		} catch (Exception ex) {

			ex.printStackTrace();
			return null;

		}

	}
}
