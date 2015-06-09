package com.fxdigital.util;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.fxdigital.manager.pojo.IpMap;

public class RefreshXml {

	public static List<IpMap> refreshXml(String url) {

		try {

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

			DocumentBuilder db = dbf.newDocumentBuilder();

			Document doc = db.parse(url);

			List<IpMap> ipList = new ArrayList<IpMap>();
			NodeList list = doc.getElementsByTagName("IpMap");
			for (int i = 0; i < list.getLength(); i++) {
				Element IpElement = (Element) list.item(i);
				String rp = IpElement.getAttribute("realIp");
				String vp = IpElement.getAttribute("VIP");
				IpMap im = new IpMap();
				im.setRealIp(rp);
				im.setVIP(vp);
				ipList.add(im);

			}
			return ipList;
		} catch (Exception ex) {

			ex.printStackTrace();
			return null;
		}
	}

}
