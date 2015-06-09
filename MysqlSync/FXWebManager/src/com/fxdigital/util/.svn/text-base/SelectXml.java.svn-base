package com.fxdigital.util;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class SelectXml {
	public static String selectXml(String url, String num) {

		try {

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

			DocumentBuilder db = dbf.newDocumentBuilder();

			Document doc = db.parse(url);
			// 获取并修改default子节点值
			Element ele = (Element) doc.getElementsByTagName("TransProtocol")
					.item(0);
			// System.out.println(ele.getChildNodes().item(1).getTextContent());
			ele.getChildNodes().item(1).setTextContent(num);
			// System.out.println("修改后---");

			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			/** 编码 */
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(url));
			transformer.transform(source, result);
			return	ele.getChildNodes().item(1).getTextContent();
		} catch (Exception ex) {

			ex.printStackTrace();
			return null;

		}

	}

}
