package com.db.bean;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.db.base.CreatSql;

public class BundlePro {
	private static final Logger logger = Logger.getLogger(BundlePro.class);

	/**
	 * @param properties
	 */
	private static void showKeys(Properties properties) {
		Enumeration<?> enu = properties.propertyNames();
		while (enu.hasMoreElements()) {
			Object key = enu.nextElement();
			logger.info(key);
		}
	}

	/**
	 * @param properties
	 */
	private static void showValues(Properties properties) {
		Enumeration<Object> enu = properties.elements();
		while (enu.hasMoreElements()) {
			Object value = enu.nextElement();
			logger.info(value);
		}
	}

	public static List<Columns> getProperties(Properties properties) {
		Columns columns = null;
		List<Columns> columnList = null;
		List<String> columnsStr = null;
		Enumeration<?> enu = properties.propertyNames();
		columnList = new ArrayList<Columns>();
		while (enu.hasMoreElements()) {
			columns = new Columns();
			columnsStr = new ArrayList<String>();
			Object key = enu.nextElement();
			String schemaName = key.toString().split("\\.")[0];
			String tableName = key.toString().split("\\.")[1];
			columns.setSchemaName(schemaName);
			columns.setTableName(tableName);
			String columnsName = properties.getProperty((String) key);
			for (String str : columnsName.split(";")) {
				columnsStr.add(str);
			}
			columns.setColumns(columnsStr);
			columnList.add(columns);
		}

		return columnList;
	}

	public static List<Columns> getProperInfo() {
		Properties properties = new Properties();
		try {
			InputStream inputStream = new FileInputStream("conf/db.properties");
			properties.load(inputStream);
			inputStream.close(); // 关闭流
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<Columns> columnList = getProperties(properties);
		return columnList;
	}

	public List<Columns> readPathXML() {
		ArrayList<Columns> list = new ArrayList<Columns>();
		SAXReader read = new SAXReader();
		Document doc = null;
		String path = "";
		Columns col=null;
		List<String> columnsStr = null;
		path ="conf/db.xml";
		try {
			doc = read.read(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Element root = doc.getRootElement();
		Element foo;
		for (Iterator i = root.elementIterator("table"); i.hasNext();) {
			foo = (Element) i.next();
			col=new Columns();
			columnsStr = new ArrayList<String>();
			String tableSchema=foo.elementText("table_shema");
			String tableName=foo.elementText("table_name");
			String columns=foo.elementText("column_name");
			String dropSql=foo.elementText("drop_sql");
			String createSql=foo.elementText("create_sql");
			col.setSchemaName(tableSchema);
			col.setTableName(tableName);
			for (String str : columns.split(";")) {
				columnsStr.add(str);
			}
			col.setColumns(columnsStr);
			col.setDropSql(dropSql);
			col.setCreateSql(createSql);
			list.add(col);
		}
		return list;

	}

	public static void main(String[] args) {
		Properties properties = new Properties();
		try {
			InputStream inputStream = new FileInputStream("conf/db.properties");
			properties.load(inputStream);
			inputStream.close(); // 关闭流
		} catch (IOException e) {
			e.printStackTrace();
		}

		// show keys
		showKeys(properties);

		// show values
		showValues(properties);

		List<Columns> columns = getProperties(properties);
		for (Columns col : columns) {
			System.out
					.println("aaa" + col.getSchemaName() + col.getTableName());
		}

		List<String> list = CreatSql.CreateQuery(columns);
		for (String str : list) {
			logger.info(str);
		}

		// show keys and values
		// showKeysAndValues(properties)

//		readPathXML();

	}

}
