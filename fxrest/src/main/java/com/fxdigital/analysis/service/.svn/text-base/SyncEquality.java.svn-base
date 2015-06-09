package com.fxdigital.analysis.service;

import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import sun.misc.BASE64Encoder;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.Base64;
import com.fxdigital.analysis.bean.Equality;
import com.fxdigital.analysis.bean.Notice;
import com.fxdigital.analysis.dao.BaseDao;
import com.fxdigital.manage.pub.util.JdbcToXml;

@Component
public class SyncEquality {
	private static Log logger=LogFactory.getLog(DynamicService.class);
	@Autowired
	private BaseDao baseDao;
	/**

	
	 * 生成xml
	 * 将xml插入数据库
	 * @param json
	 * @param notice 通知
	 * @param calzz 处理类名
	 * @throws SQLException 
	 */
	public void  sync(String json, Notice notice,String clazz,String businesstype) {
		
		//生成xml开始
		String queryCenterSql=CreateSql.queryLocalCenterID();
		List<Object> center=baseDao.querySqlList(queryCenterSql);
		String centerId = center.get(0).toString();
		String xml = "";
		List<String[]> list = null;
		Document document = DocumentHelper.createDocument(); // 创建文档
		Element employees = document.addElement("TableMsg");
		employees.addAttribute("version","increment");
		employees.addAttribute("modelType","fxdigital.dbsync.domains.client.db."+clazz);
		employees.addAttribute("centerId",centerId);
		
		Element table = employees.addElement("increment");
		BASE64Encoder encode=new BASE64Encoder();
		json=encode.encode(json.getBytes(Charset.forName("utf-8")));
		logger.info("increment base64 编码:"+json);
		table.setText(json);
		Element noticeEl=	employees.addElement("notice");
		String noticeJson=JSONObject.toJSONString(notice);
		noticeJson=encode.encode(noticeJson.getBytes(Charset.forName("utf-8")));
		logger.info("noticeJson base64 编码:"+json);
		noticeEl.setText(noticeJson);
		xml=document.asXML();
			logger.info("xml------:"+xml);
			String incrment=CreateSql.insertIncrementData(xml,businesstype);
			logger.info("incrment------:"+incrment);
			baseDao.executeSql(incrment);//将要同步的xml插入数据库
	} 



	

}
