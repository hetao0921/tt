package fxdigital.dbsync.domains.client.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
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

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.Base64;
import com.fxdigital.syncclient.analysis.bean.Notice;
import com.fxdigital.syncclient.util.DBConn;

/**
 * @author het 本地XML操作类 2014-7-30 SyncWebb fxdigital.dbsync.domains.client.dao
 */
public class JdbcToXml {
	private static Logger logger = Logger.getLogger(JdbcToXml.class);
	private static JdbcToXml jdbcToXml = null;

	private JdbcToXml() {
	}

	public static JdbcToXml getInstance() {
		if (jdbcToXml == null) {
			jdbcToXml = new JdbcToXml();
		}
		return jdbcToXml;
	}

	/**
	 * 解析xml
	 * 
	 * @param xml
	 * @param centerid
	 * @return
	 * @throws Exception 
	 */
	public boolean xmlToInsert(String xml, String centerid) throws Exception {
		boolean result = false;
		xml = xml.replace("{", "<");
		xml = xml.replace("}", ">");
		logger.info("xml------" + xml);
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xml);
		} catch (Exception e) {
			logger.error("xml格式有误", e);
		}
		Element root = doc.getRootElement();
		Attribute attribute = root.attribute("version");
		if (attribute != null) {

			String version = attribute.getValue();
			logger.info("解析获取 xml version：" + version);
			if ("increment".equals(version)) {
				logger.info("解析增量的xml");
				Attribute modelTypeAtt = root.attribute("modelType");
				String modelType = modelTypeAtt.getValue();
				Element increEl = root.element("increment");
				String json = increEl.getText();
				logger.info("increment:" + json);
				json = new String(Base64.decodeFast(json),
						Charset.forName("utf-8"));
				logger.info("increment 转码后:" + json);
				Element noticeEl = root.element("notice");
				String noticeJson = noticeEl.getText();
				noticeJson = new String(Base64.decodeFast(noticeJson),
						Charset.forName("utf-8"));
				logger.info("noticeJson 转码后:" + noticeJson);
				Notice notice = JSONObject
						.parseObject(noticeJson, Notice.class);
				try {
					Class clazz = Class.forName(modelType);
					Analysis analysis = (Analysis) clazz.newInstance();
					result = analysis.analysis(json, centerid, notice);
				} catch (Exception e) {
					logger.error("调用增量解析失败", e);
					throw new Exception(e);
				}
			}
			if ("new".equals(version)) {
				logger.info("解析新的xml");
				AnalysisNewXml analysis = new AnalysisNewXml();

				try {
					result = analysis.analysis(xml, centerid);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new Exception(e);
				}
			}
		} else {
			logger.info("解析旧的xml");
			AnalysisOldXml analysis = new AnalysisOldXml();

			result = analysis.analysis(xml, centerid);
		}
		return result;
	}

	public void wirteXml(String address, String xml) {
		try {
			RandomAccessFile raf = new RandomAccessFile(address, "rw");
			raf.setLength(0);
			raf.seek(0);
			raf.write(xml.getBytes("utf-8"));
			raf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<String> insert(String path, String centerid) {
		List<String> sqls = new ArrayList<String>();
		SAXReader read = new SAXReader();
		Document doc = null;

		try {

			doc = read.read(path);

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
		Element root = doc.getRootElement();

		@SuppressWarnings("unchecked")
		List<Element> tabls = root.elements();
		List<String> names = new ArrayList<String>();
		for (int i = 0; i < tabls.size(); i++) {
			names.add(tabls.get(i).getName());

		}

		// 添加删除表的语句
		for (int j = 0; j < names.size(); j++) {
			String sql = "delete from nvmp." + names.get(j)
					+ " where centerid='" + centerid + "'";
			sqls.add(sql);
		}

		for (int i = 0; i < names.size(); i++) {
			Element table = root.element(names.get(i));

			List<String> columns = new ArrayList<String>();
			List<String> types = new ArrayList<String>();
			@SuppressWarnings("unchecked")
			List<Element> co = table.element("ColumnType").elements();
			for (int j = 0; j < co.size(); j++) {
				columns.add(co.get(j).getName());
				types.add(co.get(j).getText());
			}

			List<List<String>> records = new ArrayList<List<String>>();

			int temp = -1;

			@SuppressWarnings("unchecked")
			List<Element> re = table.elements("Record");
			for (int j = 0; j < re.size(); j++) {
				Element r = re.get(j);
				@SuppressWarnings("unchecked")
				List<Element> rel = r.elements();
				List<String> record = new ArrayList<String>();
				for (int k = 0; k < rel.size(); k++) {
					String msg = rel.get(k).getText();
					if (types.get(k).equals("java.lang.String")) {
						record.add("'" + msg + "'");
					} else if (types.get(k).equals("java.sql.Timestamp")
							&& (msg == null || msg.equals("null"))) {
						record.add("null");
						temp = k;
					} else if (types.get(k).equals("java.sql.Timestamp")
							&& msg != null) {
						record.add("'" + msg + "'");
					} else {
						record.add(msg);
					}
				}
				records.add(record);
			}

			String sql = "insert into nvmp." + names.get(i) + "(";
			for (int j = 1; j < columns.size(); j++) {
				if (j == (columns.size() - 1)) {
					if (j == temp) {
						sql += ") values(";
					} else {
						sql += columns.get(j) + ") values(";
					}

				}

				else {
					if (j == temp) {
						// sql +=") values(";
					} else {
						sql += columns.get(j) + ",";
					}

				}

			}
			for (int j = 0; j < records.size(); j++) {
				String sqq = sql;
				List<String> record = records.get(j);
				for (int k = 1; k < record.size(); k++) {
					if (k == (record.size() - 1)) {
						if (temp == k) {
							sqq += ")";
						} else {
							sqq += record.get(k) + ")";
						}
					}

					else {
						if (temp == k) {
							// sqq += ")";
						} else {
							sqq += record.get(k) + ",";
						}
					}

				}
				sqls.add(sqq);
			}
		}
		return sqls;
	}

	public void write(String path, String str) {

		File fileName = new File(path);
		PrintWriter outFile = null;
		try {
			outFile = new PrintWriter(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		outFile.write(str);
		outFile.flush();
		outFile.close();
	}

	/**
	 * 组装xml
	 * 
	 * @param centerid
	 * @return
	 * @throws Exception 
	 */

	public static String writeTheXml(String centerid) throws Exception {

		/*
		 * String xml = ""; List<String[]> list = null; xml += "{TableMsg}";
		 * list = db.executeQueryXml(
		 * "select * from nvmp.nvmp_datasynctab where SyncFlag = 1"); String []
		 * tableNames = new String[list.size()-2]; for(int i
		 * =2;i<list.size();i++){ tableNames[i-2] = list.get(i)[1]; } for(int i
		 * =0;i<tableNames.length;i++){ list =
		 * db.executeQueryXml("select * from nvmp."
		 * +tableNames[i]+" where centerid='"+centerid+"'"); xml +=
		 * "{"+tableNames[i]+"}"; String [] columnNames = list.get(0); xml +=
		 * "{ColumnType}"; String [] types = list.get(1); for(int j =
		 * 0;j<types.length;j++) xml +=
		 * "{"+columnNames[j]+"}"+types[j]+"{/"+columnNames[j]+"}"; xml +=
		 * "{/ColumnType}"; for(int j =2;j<list.size();j++){ xml += "{Record}";
		 * String []msg = list.get(j); for(int k = 0;k<msg.length;k++){ xml +=
		 * "{"+columnNames[k]+"}"+msg[k]+"{/"+columnNames[k]+"}"; } xml +=
		 * "{/Record}"; } xml +="{/"+tableNames[i]+"}"; } xml += "{/TableMsg}";
		 */

		String json ="";
		try{
		String newXml = assemblyNewXml(centerid);
		HashMap<String, String> map = new HashMap<String, String>();
		// map.put("oldXml", xml);
		map.put("newXml", newXml);
	    json = JSONObject.toJSONString(map);
		}catch(Exception e){
			logger.info(e);
			throw new Exception(e);
		}
		return json;

	}

	/**
	 * 新xml
	 * 
	 * @param centerid
	 *            本中心ID
	 * @return
	 * @throws SQLException
	 */
	// public static String assemblyNewXml(String centerid)
	// throws SQLException {
	//
	// String xml = "";
	// DBConn db = DBConn.getDBConn();
	// List<String[]> list = null;
	// Document document = DocumentHelper.createDocument(); // 创建文档
	// Element employees = document.addElement("TableMsg");
	//
	// employees.addAttribute("version","new");
	// employees.addAttribute("modelType","fxdigital.dbsync.domains.client.db.AnalysisNewXml");
	// employees.addAttribute("centerId",centerid);
	// list =
	// db.executeQueryXml("select * from nvmp.nvmp_datasyncnewtab where SyncFlag = 1");
	// String[] tableNames = new String[list.size() - 2];
	// for (int i = 2; i < list.size(); i++) {
	// tableNames[i - 2] = list.get(i)[1];
	// }
	// for (int i = 0; i < tableNames.length; i++) {
	// list = db.executeQueryXml("select * from nvmp." + tableNames[i]
	// + " where centerid='" + centerid + "'");
	// Element table = employees.addElement(tableNames[i]);
	// String[] columnNames = list.get(0);
	//
	// Element ColumnType = table.addElement("ColumnType");
	// String[] types = list.get(1);
	// Element type = null;
	// // int[] intFlag=new int[types.length];
	// // int[] timeFlag=new int[types.length];
	// for (int j = 0; j < types.length; j++) {
	// type = ColumnType.addElement(columnNames[j]);
	// type.setText(types[j]);
	// // if("java.lang.Integer".equals(types[j])){
	// // logger.info("整型："+ types[j]);
	// // intFlag[j]=j;
	// // }
	// // if("java.sql.Timestamp".equals(types[j])){
	// // logger.info("整型："+ types[j]);
	// // timeFlag[j]=j;
	// // }
	// }
	// for (int j = 2; j < list.size(); j++) {
	// Element columnRecord = table.addElement("Record");
	// String[] msg = list.get(j);
	// Element record = null;
	// for (int k = 0; k < msg.length; k++) {
	//
	// try{
	// logger.info("注入字段时   "+columnNames[k]+"  为: "+msg[k]+"----"+intFlag[k]);
	// String text=msg[k]==null?"":msg[k];
	// logger.info("text:"+text);
	// // if(("").equals(text)&&(intFlag[k]==k||timeFlag[k]==k)){
	// // continue;
	// // }else{
	// // record = columnRecord.addElement(columnNames[k]);
	// // record.setText(text);
	// // }
	//
	// }catch (Exception e) {
	// logger.error("注入字段时"+msg[k]+"出错:",e);
	//
	// }
	//
	// }
	// }
	// }
	// xml=document.asXML();
	// return xml;
	//
	//
	// }

	public static String assemblyNewXml(String centerid) throws Exception {
		DBConn db = DBConn.getDBConn();
		String xml = "";
		List<String[]> list = null;
		xml += "{TableMsg version='new' modelType='fxdigital.dbsync.domains.client.db.AnalysisNewXml' centerId='"
				+ centerid + "'}";
		try {
			list = db
					.executeQueryXml("select * from nvmp.nvmp_datasynctab where SyncFlag = 1");
		} catch (Exception e) {
			logger.info("query the nvmp_datasynctab error: "+e);
			throw new Exception("query the nvmp_datasynctab error: "+e);
		}
		String[] tableNames = new String[list.size() - 2];
		for (int i = 2; i < list.size(); i++) {
			tableNames[i - 2] = list.get(i)[1];
		}
		for (int i = 0; i < tableNames.length; i++) {
			try{
				list = db.executeQueryXml("select * from nvmp." + tableNames[i]
						+ " where centerid='" + centerid + "'");
			}catch(Exception e){
				logger.info("query the "+tableNames[i]+" error: "+e);
				throw new Exception("query the "+tableNames[i]+" error: "+e);
			}
			
			xml += "{" + tableNames[i] + "}";
			String[] columnNames = list.get(0);
			xml += "{ColumnType}";
			String[] types = list.get(1);
			for (int j = 0; j < types.length; j++)
				xml += "{" + columnNames[j] + "}" + types[j] + "{/"
						+ columnNames[j] + "}";
			xml += "{/ColumnType}";
			for (int j = 2; j < list.size(); j++) {
				xml += "{Record}";
				String[] msg = list.get(j);
				for (int k = 0; k < msg.length; k++) {
					xml += "{" + columnNames[k] + "}" + msg[k] + "{/"
							+ columnNames[k] + "}";
				}
				xml += "{/Record}";
			}
			xml += "{/" + tableNames[i] + "}";
		}
		xml += "{/TableMsg}";
		xml = xml.replace("{", "<");
		xml = xml.replace("}", ">");
		return xml;
	}

	/**
	 * 增量xml
	 * 
	 * @param centerid
	 *            本中心ID
	 * @return
	 * @throws SQLException
	 */
	public static String assemblyIncrementXml(String centerid, String json,
			Notice notice) throws SQLException {

		DBConn db = DBConn.getDBConn();
		String xml = "";
		List<String[]> list = null;
		Document document = DocumentHelper.createDocument(); // 创建文档
		Element employees = document.addElement("TableMsg");
		employees.addAttribute("version", "increment");
		employees.addAttribute("modelType",
				"fxdigital.dbsync.domains.client.db.AnalysisIncrementXml");
		employees.addAttribute("centerId", centerid);
		Element table = employees.addElement("increment");
		table.setText(json);
		Element noticeEl = employees.addElement("notice");
		String noticeJson = JSONObject.toJSONString(notice);
		noticeEl.setText(noticeJson);
		xml = document.asXML();
		return xml;

	}

	public static List<String> readPathXML() {
		ArrayList<String> list = new ArrayList<String>();
		SAXReader read = new SAXReader();
		Document doc = null;
		String path = "";
		if (System.getProperty("os.name").equals("Linux")) // 操作系统名称
		{
			path = "/etc/SyncDataSet.xml";
		} else {
			// logger.info("==============="+System.getProperty("user.dir"));
			path = "D:\\SyncDataSet.xml";
		}
		try {
			doc = read.read(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Element root = doc.getRootElement();
		Element url = root.element("syncwindows");
		Element time = root.element("synclinux");
		Element dbAdress = root.element("autowindows");
		// logger.info(url.getStringValue());
		// logger.info(time.getStringValue());
		list.add(url.getStringValue());
		list.add(time.getStringValue());
		list.add(dbAdress.getStringValue());
		return list;

	}

}
