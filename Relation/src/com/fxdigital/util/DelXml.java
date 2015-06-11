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
import org.w3c.dom.NodeList;

public class DelXml {

	public static void delXml(String url, String delrealIp) {

		try {

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

			DocumentBuilder db = dbf.newDocumentBuilder();

			Document doc = db.parse(url);
			NodeList list = doc.getElementsByTagName("IpMap");
			System.out.println("list"+list);
			for (int i = 0; i < list.getLength(); i++) {
				Element IpElement = (Element) list.item(i);
				String Ip = IpElement.getAttribute("realIp");
				System.out.println("Ip"+Ip);
				if (Ip.equals(delrealIp)) {
					list.item(i).getParentNode().removeChild(list.item(i));

				}

			}

			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			/** 编码 */
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(url));
			transformer.transform(source, result);
			
		} catch (Exception ex) {

			ex.printStackTrace();

		}
		
	}

}
