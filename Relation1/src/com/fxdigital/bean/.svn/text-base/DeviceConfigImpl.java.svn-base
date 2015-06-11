package com.fxdigital.bean;

import java.util.List;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;

import fxdigital.util.Common;

public class DeviceConfigImpl{
	
	private static final Logger log = Logger.getLogger(DeviceConfigImpl.class);

	private static DeviceConfigImpl dci;
	
	public static DeviceConfigImpl getDciInstance(){
		if(dci==null){
			dci = new DeviceConfigImpl();
		}
		return dci;
	}
	
	private DeviceConfigImpl(){}
	
	public String getDeviceConfig() {
		String path = "";
		String ip = "";
		if (System.getProperty("os.name").equals("Linux"))
			path = "/etc/fxconf/DeviceConfig/DeviceConfig.xml";
		else
			path = "D:\\fxconf\\DeviceConfig\\DeviceConfig.xml";
		if(Common.IsExists(path)){
			Document doc = Common.getDocument(path);
			Element root = doc.getRootElement();
			Element appE = root.getChild("CommandSystem").getChild("System");
			@SuppressWarnings("unchecked")
			List<Element> list = appE.getChildren("Message");
			for(int i =0;i<list.size();i++){
				Element e = list.get(i);
				String id = e.getAttributeValue("id");
				if(id.equals("ip")){
					ip=e.getAttributeValue("text");
				}
			}
		}else{
			log.warn("DeviceConfig.xml文件不存在");
		}
		return ip;
	}

	public boolean upDeviceConfig(String ip) {
		String path = "";
		boolean result = false;
		if (System.getProperty("os.name").equals("Linux"))
			path = "/etc/fxconf/DeviceConfig/DeviceConfig.xml";
		else
			path = "D:\\fxconf\\DeviceConfig\\DeviceConfig.xml";
		if(Common.IsExists(path)){
			Document doc = Common.getDocument(path);
			Element root = doc.getRootElement();
			Element appE = root.getChild("CommandSystem").getChild("System");
			@SuppressWarnings("unchecked")
			List<Element> list = appE.getChildren("Message");
			for(int i =0;i<list.size();i++){
				Element e = list.get(i);
				String id = e.getAttributeValue("id");
				if(id.equals("ip")){
					e.setAttribute("text",ip);
				}
			}
			result = Common.upXml(doc, path);
		}else{
			log.warn("DeviceConfig.xml文件不存在");
		}
		return result;
	}
}
