package com.fxdigital.mysqlsync.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.fxdigital.mysqlsync.bundle.MycnfBean;
import com.fxdigital.mysqlsync.bundle.ResourceBundle;

/**
 * @author Administrator mysql配置文件类
 * 
 */
public class MysqlSetCnf {
	private static final Logger logger = Logger.getLogger(MysqlSetCnf.class);
	static MycnfBean mb = new ResourceBundle().getResource();

	public static String getMasterCnf() {
		String point = addPoint();
		String mastercnf = point;
//		mastercnf = "slave_skip_errors=" + mb.getSlaveskiperrors() + point;
		mastercnf = "binlog_format=" + mb.getBinlogformat() + point;
		mastercnf += "log_bin=" + mb.getLogbin() + point;

		List<String> dobdlist = getList(mb.getBinlogdodb());
		for (int i = 0; i < dobdlist.size(); i++) {
			mastercnf += "binlog-do-db=" + dobdlist.get(i) + point;

		}

		if (null != mb.getBinlogignoredb()) {
			List<String> ignorebdlist = getList(mb.getBinlogignoredb());
			if (null != ignorebdlist && ignorebdlist.size() > 0) {
				for (int j = 0; j < ignorebdlist.size(); j++) {
					mastercnf += "binlog-ignore-db=" + ignorebdlist.get(j)
							+ point;

				}
			}
		}

		List<String> ignoretablelist = getList(mb.getReplicateignoretable());
		for (int k = 0; k < ignoretablelist.size(); k++) {
			mastercnf += "replicate-ignore-table=" + ignoretablelist.get(k)
					+ point;

		}

		mastercnf += "basedir=" + mb.getBasedir() + point;

		mastercnf += "datadir=" + mb.getDatadir() + point;

		mastercnf += "port=" + mb.getPort() + point;

		mastercnf += "server_id=" + mb.getMasterserverid() + point;

		mastercnf += "skip-name-resolve" + point;

		mastercnf += "lower_case_table_names=1" + point;

		mastercnf += "auto-increment-increment="
				+ mb.getAutoincrementincrement() + point;

		mastercnf += "auto-increment-offset="
				+ mb.getMasterautoincrementoffset() + point;
		return mastercnf;

	}

	public static String getSlaveCnf() {
		String point = addPoint();
		String slavecnf = point;
//		slavecnf = "slave_skip_errors=" + mb.getSlaveskiperrors() + point;
		slavecnf = "binlog_format=" + mb.getBinlogformat() + point;
		slavecnf += "log_bin=" + mb.getLogbin() + point;

		List<String> dobdlist = getList(mb.getBinlogdodb());
		for (int i = 0; i < dobdlist.size(); i++) {
			slavecnf += "binlog-do-db=" + dobdlist.get(i) + point;

		}
		if (null != mb.getBinlogignoredb()) {
			List<String> ignorebdlist = getList(mb.getBinlogignoredb());
			for (int j = 0; j < ignorebdlist.size(); j++) {
				slavecnf += "binlog-ignore-db=" + ignorebdlist.get(j) + point;

			}
		}

		List<String> ignoretablelist = getList(mb.getReplicateignoretable());
		for (int k = 0; k < ignoretablelist.size(); k++) {
			slavecnf += "replicate-ignore-table=" + ignoretablelist.get(k)
					+ point;

		}

		slavecnf += "basedir=" + mb.getBasedir() + point;

		slavecnf += "datadir=" + mb.getDatadir() + point;

		slavecnf += "port=" + mb.getPort() + point;

		slavecnf += "server_id=" + mb.getSlaveserverid() + point;

		slavecnf += "skip-name-resolve" + point;

		slavecnf += "lower_case_table_names=1" + point;

		slavecnf += "auto-increment-increment="
				+ mb.getAutoincrementincrement() + point;

		slavecnf += "auto-increment-offset=" + mb.getSlaveautoincrementoffset()
				+ point;
		return slavecnf;

	}

	public static List<String> getList(String str) {
		List<String> list = new ArrayList<String>();
		if (str.contains(";")) {
			String[] strarray = str.split(";");
			for (int i = 0; i < strarray.length; i++) {
				list.add(strarray[i]);
				// System.out.println(strarray[i]);
			}
		} else {
			list.add(str);
		}
		return list;
	}

	public static String addPoint() {
		String str = "";
		if (System.getProperty("os.name").equals("Linux")) // 操作系统名称
		{
			str = "\n";
		} else {
			// System.out.println("==============="+System.getProperty("user.dir"));
			str = "\r\n";
		}
		return str;
	}

	public static void main(String[] args) {
		System.out.println(getMasterCnf());
		System.out.println("-------------------");
		System.out.println(getSlaveCnf());
	}
}
