package fxdigital.postserver.contentdispose.handlers.dbsync.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import fxdigital.postserver.contentdispose.handlers.dbsync.dao.SyncDataVersionDao;

/**
 * @author het XML操作类 2014-7-30 BusinessSync
 *         fxdigital.postserver.contentdispose.handlers.dbsync.db
 */
public class JdbcToXml {
	private static final Logger logger = Logger.getLogger(JdbcToXml.class);

	// public String getXml(String centerid) throws SQLException {
	// DBConn db = DBConn.getDBConn();
	// List<String[]> list = null;
	// String xml = "";
	// xml += "{TableMsg}";
	// //��ȡ��Ҫ��ѯ�ı����
	// list =
	// db.executeQueryXml("select * from nvmp.nvmp_datasynctab where SyncFlag = 1");
	// String [] tableNames = new String[list.size()-2];
	// //xml += "{TableName}";
	// for(int i =2;i<list.size();i++){
	// tableNames[i-2] = list.get(i)[1];
	// //xml += "{table}"+tableNames[i-2]+"{/table}";
	// }
	// //xml += "{/TableName}";
	// //��ѯÿһ��������ת��xml��ʽ
	// for(int i =0;i<tableNames.length;i++){
	// list =
	// db.executeQueryXml("select * from nvmp."+tableNames[i]+" where centerid='"+centerid+"'");
	// //��xml����ӱ���
	// xml += "{"+tableNames[i]+"}";
	// //��ȡ���е�������ӵ�XML��
	// //List<String> columns = new ArrayList<String>();
	// String [] columnNames = list.get(0);
	// //xml += "{ColumnName}";
	// //for(int j=0;j<columnNames.length;j++){
	//
	// // xml += "{name"+j+"}"+columnNames[j]+"{/name"+j+"}";
	// //}
	// //xml += "{/ColumnName}";
	// //��ȡ���е�������ӵ�XML��
	// xml += "{ColumnType}";
	// String [] types = list.get(1);
	// for(int j = 0;j<types.length;j++)
	// xml += "{"+columnNames[j]+"}"+types[j]+"{/"+columnNames[j]+"}";
	// xml += "{/ColumnType}";
	// //��ȡ���е���ݣ���ӵ�XML��
	// for(int j =2;j<list.size();j++){
	// xml += "{Record}";
	// String []msg = list.get(j);
	// for(int k = 0;k<msg.length;k++){
	// xml += "{"+columnNames[k]+"}"+msg[k]+"{/"+columnNames[k]+"}";
	// }
	// xml += "{/Record}";
	// }
	// xml +="{/"+tableNames[i]+"}";
	// }
	// xml += "{/TableMsg}";
	// return xml;
	// }

	public String getXml(String centerid) throws SQLException {
		DBConn db = DBConn.getDBConn();
		String xml = "";
		// DBConn db = DBConn.getDBConn();
		List<String[]> list = null;
		Document document = DocumentHelper.createDocument(); // 创建文档
		Element employees = document.addElement("TableMsg");
		// list = db
		// .executeQueryXml("select * from nvmp_datasynctab where SyncFlag = 1");
		list = db
				.executeQueryXml("select * from nvmp_datasynctab where SyncFlag = 1");
		String[] tableNames = new String[list.size() - 2];
		// xml += "{TableName}";
		for (int i = 2; i < list.size(); i++) {
			// logger.info("list.get(i)[1]" + "i" + i + "         "
			// + list.get(i)[1]);
			tableNames[i - 2] = list.get(i)[1];
			// logger.info("tableNames    " + tableNames);
			// xml += "{table}"+tableNames[i-2]+"{/table}";
		}
		// xml += "{/TableName}";
		// ��ѯÿһ��������ת��xml��ʽ
		for (int i = 0; i < tableNames.length; i++) {
			// list = db.executeQueryXml("select * from " + tableNames[i]
			// + " where centerid='" + centerid + "'");
			list = db.executeQueryXml("select * from " + tableNames[i]
					+ " where centerid='" + centerid + "'");
			// ��xml����ӱ���
			// logger.info("---tableNames---" + tableNames[i]);
			Element table = employees.addElement(tableNames[i]);

			// ��ȡ���е�������ӵ�XML��
			// List<String> columns = new ArrayList<String>();
			String[] columnNames = list.get(0);
			// xml += "{ColumnName}";
			// for(int j=0;j<columnNames.length;j++){
			//
			// xml += "{name"+j+"}"+columnNames[j]+"{/name"+j+"}";
			// }
			// xml += "{/ColumnName}";
			// ��ȡ���е�������ӵ�XML��

			Element ColumnType = table.addElement("ColumnType");
			String[] types = list.get(1);
			Element type = null;
			for (int j = 0; j < types.length; j++) {
				type = ColumnType.addElement(columnNames[j]);
				type.setText(types[j]);
			}
			// ��ȡ���е���ݣ���ӵ�XML��
			for (int j = 2; j < list.size(); j++) {
				Element columnRecord = table.addElement("Record");
				String[] msg = list.get(j);
				Element record = null;
				logger.info(" msg.length" + msg.length);
				for (int k = 0; k < msg.length; k++) {
					logger.info(" k" + k);
					// logger.info(" msg[k]" + msg[k]);
					// logger.info(" columnNames[j]" +
					// columnNames[j]+" columnNames[k]"+columnNames[k]);
					record = columnRecord.addElement(columnNames[k]);
					record.setText((null == msg[k] || ("").equals(msg[k])) ? "''"
							: msg[k]);
				}
			}
		}
		xml = document.asXML();
		// xml = getLocalXml(document, centerid);
		logger.info("  " + "xml" + "  " + xml);
		return xml;

	}

	/**
	 * ��XMLת����insert���
	 * 
	 * @param xml
	 */
	public List<String> xmlToInsert(String xml, String centerid) {
		List<String> sqls = new ArrayList<String>();
		SAXReader read = new SAXReader();
		Document doc = null;
		String path = "";
		if (System.getProperty("os.name").equals("Linux")) // 操作系统名称
		{
			path = "/etc/sync" + centerid + ".xml";
		} else {
			// logger.info("==============="+System.getProperty("user.dir"));
			path = "c:\\sync" + centerid + ".xml";
		}
		wirteXml(path, xml);
		try {

			doc = read.read(path);
			// doc = read.read(xml);
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
		Element root = doc.getRootElement();
		// ��ȡxml�ļ��еı����
		@SuppressWarnings("unchecked")
		List<Element> tabls = root.elements();
		List<String> names = new ArrayList<String>();
		for (int i = 0; i < tabls.size(); i++) {
			names.add(tabls.get(i).getName());
			// logger.info(names.get(i));
		}

		// 添加删除表的语句
		for (int j = 0; j < names.size(); j++) {
			String sql = "delete from nvmp." + names.get(j)
					+ " where centerid='" + centerid + "'";
			sqls.add(sql);
		}

		// ѭ����ȡÿ�������Ϣ
		for (int i = 0; i < names.size(); i++) {
			Element table = root.element(names.get(i));
			// ��ȡ���ÿһ�����������
			List<String> columns = new ArrayList<String>();
			List<String> types = new ArrayList<String>();
			@SuppressWarnings("unchecked")
			List<Element> co = table.element("ColumnType").elements();
			for (int j = 0; j < co.size(); j++) {
				columns.add(co.get(j).getName());
				types.add(co.get(j).getText());
			}
			// ��ȡ���е����м�¼��
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
							&& (msg == null || msg.equals("null") || ("''")
									.equals(msg))) {
						record.add("null");
						temp = k;
					} else if (types.get(k).equals("java.sql.Timestamp")
							&& msg != null) {
						record.add("'" + msg + "'");
					} else if (("'").equals(msg)) {
						record.add("null");
					} else {
						record.add(msg);
					}
				}
				records.add(record);
			}

			// ����SQL���
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

	/**
	 * ��XMLת����insert���
	 * 
	 * @param xml
	 */
	public List<String> insert(String path, String centerid) {
		List<String> sqls = new ArrayList<String>();
		SAXReader read = new SAXReader();
		Document doc = null;
		// String path = "";
		// if (System.getProperty("os.name").equals("Linux")) // 操作系统名称
		// {
		// path = "/etc/sync"+centerid+".xml";
		// } else {
		// //
		// logger.info("==============="+System.getProperty("user.dir"));
		// path = "c:\\sync"+centerid+".xml";
		// }
		// wirteXml(path,xml);
		try {
			// path="c:\\"+path;
			// logger.info("==============="+System.getProperty("user.dir"));
			// path=System.getProperty("user.dir").toString()+"\\"+path;
			// logger.info("path"+path);
			doc = read.read(path);
			// doc = read.read(xml);
		} catch (Exception e) {
			logger.info("read path: " + path + "  error" + e);
			e.printStackTrace();
			return null;
		}
		Element root = doc.getRootElement();
		// ��ȡxml�ļ��еı����
		@SuppressWarnings("unchecked")
		List<Element> tabls = root.elements();
		List<String> names = new ArrayList<String>();
		for (int i = 0; i < tabls.size(); i++) {
			names.add(tabls.get(i).getName());
			// logger.info(names.get(i));
		}

		// 添加删除表的语句
		for (int j = 0; j < names.size(); j++) {
			String sql = "delete from nvmp." + names.get(j)
					+ " where centerid='" + centerid + "'";
			sqls.add(sql);
		}

		// ѭ����ȡÿ�������Ϣ
		for (int i = 0; i < names.size(); i++) {
			Element table = root.element(names.get(i));
			// ��ȡ���ÿһ�����������
			List<String> columns = new ArrayList<String>();
			List<String> types = new ArrayList<String>();
			@SuppressWarnings("unchecked")
			List<Element> co = table.element("ColumnType").elements();
			for (int j = 0; j < co.size(); j++) {
				columns.add(co.get(j).getName());
				types.add(co.get(j).getText());
			}
			// ��ȡ���е����м�¼��
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
							&& (msg == null || msg.equals("null") || ("''")
									.equals(msg))) {
						record.add("null");
						temp = k;
					} else if (types.get(k).equals("java.sql.Timestamp")
							&& msg != null) {
						record.add("'" + msg + "'");
					} else if (("'").equals(msg)) {
						record.add("null");
					} else {
						record.add(msg);
					}

				}
				records.add(record);
			}

			// ����SQL���
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
		// File fileName = new File("D:\\fileName.txt");
		File fileName = new File(path);
		PrintWriter outFile = null;
		try {
			outFile = new PrintWriter(fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (null != outFile) {
			outFile.write(str);
			outFile.flush();
			outFile.close();
		}
	}

	public static void main(String[] args) {
		JdbcToXml jdbc = new JdbcToXml();
		// String xml="sync005008047DEB@001.xml";
		String xml = "sync000BAB65C2ED@001166.xml";
		// String xml=args[0].toString();
		logger.info("xmlname   " + xml);
		String centerid = xml.substring(4, 20);
		logger.info("centerid   " + centerid);
		List<String> sqls = jdbc.insert(xml, centerid);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < sqls.size(); i++) {

			String str = sqls.get(i) + ";".toString();
			if (System.getProperty("os.name").equals("Linux")) // 操作系统名称
			{
				sb.append(str + "\r");
			} else {
				// logger.info("==============="+System.getProperty("user.dir"));
				sb.append(str + "\r\n");
			}
			// logger.info(sqls.get(i)+";");

		}
		// String path=System.getProperty("user.dir").toString()+"\\";
		jdbc.write("sync" + centerid + ".sql", sb.toString());

		// jdbc.SayInFile();
		// JdbcImpl jdb = JdbcImpl.getJdbcImpl();
		// boolean re = jdb.updateSqls(sqls);

		// logger.info("执行事物完毕，结果："+re);
	}
	//
	// public static void testInsert(){
	// JdbcToXml j = new JdbcToXml();
	// List<String> sql = j.xmlToInsert("c://a.xml");
	// // logger.info("���ϣ�");
	// for(String s:sql)
	// logger.info(s);
	// }

	// public static void testXml(){
	// JdbcToXml j = new JdbcToXml();
	// try {
	// String s = j.getXml();
	// String ss = s.replace("{", "<");
	// ss = ss.replace("}", ">");
	// // logger.info("����ݿ��ȡ��ݳɹ�");
	// RandomAccessFile raf = new RandomAccessFile("c://a.xml","rwd");
	// raf.write(ss.getBytes("utf-8"));
	// raf.close();
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// logger.info("д���ļ��ɹ�");
	// }
}
