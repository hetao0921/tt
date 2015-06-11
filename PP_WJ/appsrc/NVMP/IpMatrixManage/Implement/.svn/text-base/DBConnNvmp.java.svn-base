package NVMP.IpMatrixManage.Implement;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.misc.log.LogUtil;

public class DBConnNvmp {
	private Connection con = null;
	private static DBConnNvmp db = null;

	private DBConnNvmp() {
	}

	public static DBConnNvmp getDBConn() {
		if (db == null)
			db = new DBConnNvmp();
		return db;
	}



/*	// 在此处根据类型
	public String getRtspValue(String typeid) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT souce FROM nvmp_subtitle where type=");
		sql.append(typeid);

		String str = null;
		try {
			LogUtil.BusinessInfo(sql.toString());
		List<HashMap<String, String>> list = executeQuery(sql.toString());
	
		if(list.size()>0) {
			str = list.get(0).get("souce");
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}*/

}
