package com.fxdigital.manage.pub.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.alibaba.fastjson.JSONObject;



/**
 * @author  het
 *本地XML操作类
 * 2014-7-30
 * SyncWebb
 * fxdigital.dbsync.domains.client.dao
 */
public class JdbcToXml {
	private static Logger logger = Logger.getLogger(JdbcToXml.class);
	/**
	 * 增量xml
	 * @param centerid 本中心ID
	 * @return
	 * @throws SQLException
	 */
	public static String assemblyEqualityXml(String centerid,String json,String noticeJson)
			throws SQLException  {
				json=json.replace("'", "\\'");
				String xml = "";
				List<String[]> list = null;
				Document document = DocumentHelper.createDocument(); // 创建文档
				Element employees = document.addElement("TableMsg");
				employees.addAttribute("version","1.0");
				employees.addAttribute("modelType","fxdigital.dbsync.domains.client.db.AnalysisEqualtiy");
				employees.addAttribute("centerId",centerid);
				Element table = employees.addElement("increment");
				table.setText(json);
				Element notice=	employees.addElement("notice");
				notice.setText(noticeJson);
				xml=document.asXML();
				return xml;
			
			
			}
	
	


	


	


}
