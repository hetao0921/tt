package com.fxdigital.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.fxdigital.manager.pojo.IpMap;

public class InsertXml {
	
	public static 	List<IpMap> insertXml(String url, String realIp,String VIP) {

		try {

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

			DocumentBuilder db = dbf.newDocumentBuilder();

			Document doc = db.parse(url);
			Element ele = (Element)doc.getElementsByTagName("StreamFoward").item(0);
			Element ele0=null;
			if(doc.getElementsByTagName("StreamIPMaps").getLength()==0){
			
		ele0 = doc.createElement("StreamIPMaps");
			ele.appendChild(ele0);
			}else{
				ele0= (Element)doc.getElementsByTagName("StreamIPMaps").item(0);
			}
			Element eltName = doc.createElement("IpMap");

			Attr attr = doc.createAttribute("realIp");
			attr.setValue(realIp);
			Attr attr2 = doc.createAttribute("VIP");
			attr2.setValue(VIP);

			eltName.setAttributeNode(attr);
			eltName.setAttributeNode(attr2);

			ele0.appendChild(eltName);
			
			//--------获取所有信息
			List<IpMap> ipList=new ArrayList<IpMap>();
			NodeList list=doc.getElementsByTagName("IpMap");
			for(int i=0;i<list.getLength();i++){
			Element IpElement=(Element) list.item(i);
			String rp=IpElement.getAttribute("realIp");
			String vp=IpElement.getAttribute("VIP");
			IpMap im=new IpMap();
			        im.setRealIp(rp);
			        im.setVIP(vp);
			        ipList.add(im);
				
				}
			
			
			
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			/** 编码 */
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(url));
			transformer.transform(source, result);
			return ipList;
		} catch (Exception ex) {

			ex.printStackTrace();
 return null;
		}

	}

}
