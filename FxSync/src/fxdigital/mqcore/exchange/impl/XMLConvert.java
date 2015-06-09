package fxdigital.mqcore.exchange.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import fxdigital.syncserver.business.hibernate.bean.NvmpDatasynctab;
import fxdigital.syncserver.business.hibernate.dao.NVMPHibernate;

public class XMLConvert implements IConvert {
	
	
    static {

    	    PropertyConfigurator.configure("conf/log4j.properties");
	  
    }
	private static Logger logger = Logger.getLogger(XMLConvert.class);
	/**
	 * 将新的xml数据转换成旧的数据，去掉新表的内容,并替换成{} 兼容老版本
	 * @param onoff 		是否转换开关
	 * @param xml			新xml
	 * @return
	 */
	public String newConvertToOld(boolean onoff,String xml){
		String newXml=xml;
		if(onoff){
			newXml=	convert(newXml);
		}
		return newXml;
	}
	
	
	/**
	 * 将旧的数据转换成新的数据，替换{} 生成xml格式,
	 * @param onoff 		是否转换开关
	 * @param xml			旧数据
	 * @return
	 */
	public String oldConvertToNew(boolean onoff,String xml){
		if(onoff){
			xml=xml.replace("{", "<");
			xml=xml.replace("}", ">");
		}
		return xml;
		
		
	}
	
	public String convert(String newXml){
		List<NvmpDatasynctab> list=NVMPHibernate.getNVMPhibernate().getAllList(NvmpDatasynctab.class);

		Document doc = null;
		try {
			doc  = DocumentHelper.parseText(newXml);
			Element root = doc.getRootElement();
			Attribute attribute= root.attribute("version");
			if(attribute!=null){
				String version=attribute.getValue();
				logger.info("转换获取 xml version："+version);
				if("new".equals(version)){
					logger.info("转换新的xml");
					List<Element> elements=root.elements();
					for (int i = 0; i < elements.size(); i++) {
						Element element=elements.get(i);
						 boolean flag=true;
						for (NvmpDatasynctab nvmpDatasynctab : list) {
							String tableName=nvmpDatasynctab.getTableName();
							if(element.getName().equals(tableName)){
								flag=false;
								break;
							}
						}
						if(flag){
							root.remove(element);
						}
					}
					newXml=doc.asXML();
					newXml=newXml.replace("<", "{");
					newXml=newXml.replace(">", "}");
					
				
				}
			}
		} catch (Exception e) {
			logger.error("xml格式错误", e);
		}

		return newXml;
	}
	public static void main(String[] args) throws DocumentException {
		SAXReader reader=new SAXReader();
		Document doc=reader.read(new File("d:/C89CDCB1882C@0013.xml"));
		String newXml=doc.asXML();
		XMLConvert convert=new XMLConvert();
		String oldXml=convert.convert(newXml);
		System.out.println(oldXml);
	}
	

}
