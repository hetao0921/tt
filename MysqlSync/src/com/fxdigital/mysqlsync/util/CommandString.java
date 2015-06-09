package com.fxdigital.mysqlsync.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.fxdigital.mysqlsync.bundle.MySqlDumpBean;
import com.fxdigital.mysqlsync.bundle.MycnfBean;
import com.fxdigital.mysqlsync.bundle.ResourceBundle;
import com.fxdigital.mysqlsync.service.MysqlSetCnf;

/**
 * @author Administrator
 * 命令行字符拼接类
 *
 */
public class CommandString {
	private static final Logger logger = Logger.getLogger(CommandString.class);

	public static List<String> getMysqlInitStr() {
		List<String> strList = new ArrayList<String>();
		strList.add("groupadd mysql");
		strList.add("mkdir /home/mysql");
		strList.add("useradd -g mysql -d /home/mysql mysql");
		strList.add("cp /home/hxht/mysqlsync/mysql-5.6.19-linux-glibc2.5-i686.tar.gz /usr/local/");
		strList.add("cd /usr/local");
		strList.add("cd /usr/local");
		strList.add("tar zxvf /usr/local/mysql-5.6.19-linux-glibc2.5-i686.tar.gz");
		strList.add("ln -s mysql-5.6.19-linux-glibc2.5-i686 mysql");
		strList.add("cd /usr/local/mysql");
		strList.add("cd /usr/local/mysql");
		strList.add("chown -R mysql .");
		strList.add("chgrp -R mysql .");
		strList.add("scripts/mysql_install_db --user=mysql");
		return strList;
	}

	public static String getStartMysqlStr() {
		String startStr = "/usr/local/mysql/support-files/mysql.server start";
		return startStr;
	}

	public static String getStopMysqlStr() {
		String stopStr = "/usr/local/mysql/support-files/mysql.server stop";
		return stopStr;
	}

	public static String getLoginMysqlStr(String password) {
		String loginStr = "/usr/local/mysql/bin/mysql -uroot -p" + password;
		return loginStr;
	}

	public static String getSetRootStr() {
		String rootStr = "/usr/local/mysql/bin/mysql -uroot mysql";
		return rootStr;
	}

	public static List<String> getSetRootPw(String password) {
		List<String> strList = new ArrayList<String>();
		strList.add("use mysql;");
		strList.add("update user set password=PASSWORD('" + password
				+ "') where user='root';");
		strList.add("flush privileges;");

		return strList;
	}

	public static String getMasterStatusStr() {
		String masterStatusStr = "show master status\\G";
		return masterStatusStr;
	}

	public static String getSlaveStatusStr() {
		String slaveStatusStr = "show slave status\\G";
		return slaveStatusStr;
	}

	public static String getGrantStr(String ip, String username, String password) {
		String grantStr = "grant replication slave,reload,super on *.* to '"
				+ username + "'@'" + ip + "' identified by '" + password + "';";
		return grantStr;
	}

	public static String getConfirmGrant(String ip, String username,
			String password) {
		String confirmGrantStr = "echo 'exit' | /usr/local/mysql/bin/mysql -h"
				+ ip + " -u" + username + " -p" + password;
		return confirmGrantStr;
	}

	public static String getGrantAllStr(String password) {
		String grantStr = "grant all privileges on *.* to root@'%' identified by '"
				+ password + "' with grant option;";
		return grantStr;
	}

	public static String getFlushPrivilegesStr() {
		String flushPrivilegesStr = "flush privileges;";
		return flushPrivilegesStr;
	}

	public static List<String> getSetMasterStr(String ip, String username,
			String password, String logfile, int logpos) {
		List<String> strList = new ArrayList<String>();
		strList.add("stop slave;");
		strList.add("change master to master_host='" + ip + "',master_user='"
				+ username + "',master_password='" + password
				+ "',master_log_file='" + logfile + "',master_log_pos="
				+ logpos + ";");
		return strList;
	}

	public static List<String> getStopMasterStr() {
		List<String> strList = new ArrayList<String>();
		strList.add("stop slave;");
		strList.add("change master to master_host=' ';");
		return strList;
	}
	
	public static List<String> getResetMasterStr() {
		List<String> strList = new ArrayList<String>();
		strList.add("stop slave;");
		strList.add("reset slave;");
		return strList;
	}
	
	public static String getstopSlaveStr(){
		String stopSlaveStr="stop slave;";
		return stopSlaveStr;
	}
	
	public static String getChangeMasterStr(){
		String changeMasterStr="change master to master_host=' ';";
		return changeMasterStr;
	}
	
	public static String getstartSlaveStr() {
		String startSlaveStr = "start slave;";
		return startSlaveStr;
	}

	public static String getDumpExportStr(String password, String dbname,
			String sendpath) {
		String exportStr = "/usr/local/mysql/bin/mysqldump -uroot -p"
				+ password + " -B " + dbname + " > " + sendpath + "/" + dbname
				+ ".sql";
		return exportStr;
	}

	public static String getAllDumpExportStr(String password, String sendpath,
			String fileName) {

		logger.info("备份文件位置: " + sendpath);

		String exportStr = "/usr/local/mysql/bin/mysqldump -uroot -p"
				+ password + " --all-databases > " + sendpath + "/" + fileName;
		return exportStr;
	}
	
	public static String getDumpExportStr(String password, List<String> dblist,
			List<String> ignoretablelist,String sendpath,String fileName){
		logger.info("备份文件位置: " + sendpath);
		String exportStr ="";
		String ignoreStr = "";
		List<String> exportStrList = new ArrayList<String>();
		if (dblist.size() > 0 && null != dblist) {
			for (int i = 0; i < dblist.size(); i++) {
				ignoreStr+=" -B "+dblist.get(i);
				if (ignoretablelist.size() > 0 && null != ignoretablelist) {
					for (int j = 0; j < ignoretablelist.size(); j++) {
						
						if (ignoretablelist.get(j).contains(dblist.get(i))) {
							ignoreStr += " --ignore-table="
									+ ignoretablelist.get(j);
							
						}
					}
				}
				 exportStr = "/usr/local/mysql/bin/mysqldump -uroot -p"
					+ password+ignoreStr+" > " + sendpath + "/" + fileName;
			
			}
		}
		logger.info("导出文件命令: "+exportStr);
		return exportStr;
	}

	public static String getDumpImportStr(String password,
			String receivepath) {
		logger.info("导入文件位置: " + receivepath);
		String str = "/usr/local/mysql/bin/mysql -hlocalhost -uroot -p"
			+ password + " < "
			+ receivepath + "/"+getFileName(receivepath);
		logger.info("导入文件名称: " + getFileName(receivepath));

		return str;

	}

	public static String getFileName(String filePath) {
		String fileName = "";
		logger.info("the current filePath " + filePath);
		if ("".equals(filePath) || null == filePath) {
			logger.info("the current filePath is empty");
			return null;
		}
		File file = new File(filePath);
		if (file.isDirectory()) { // 如果是目录， 遍历所有子目录取出所有文件名
			String[] filelist = file.list();
			long maxFileName=0;
			for (int i = 0; i < filelist.length; i++) {
				File readfile = new File(filePath + File.separator + filelist[i]);
				if (!readfile.isDirectory()) {
					String tempName = readfile.getName();
					String fileprefix = tempName.substring(0,tempName
							.lastIndexOf(".") );
					String fileSuffix = tempName.substring(tempName
							.lastIndexOf(".") + 1);
					if (fileSuffix.toUpperCase().equals("SQL")) {
						//logger.info("fileprefix: " + fileprefix+" fileSuffix: "+fileSuffix);
						//logger.info("the current fileName " + tempName);
						Pattern pattern = Pattern.compile("[0-9]*"); 
						if(pattern.matcher(fileprefix).matches()&&maxFileName<Long.parseLong(fileprefix)){
							maxFileName= Long.parseLong(fileprefix);
						}
						fileName=maxFileName+".sql";
					}
				}
			}
		}
		return fileName;

	}
	
	public static String getFileName(String filePath,String dbName) {
		String fileName = "";
		logger.info("the current filePath " + filePath);
		if ("".equals(filePath) || null == filePath) {
			logger.info("the current filePath is empty");
			return null;
		}
		File file = new File(filePath);
		if (file.isDirectory()) { // 如果是目录， 遍历所有子目录取出所有文件名
			String[] filelist = file.list();
			int maxFileName=0;
			for (int i = 0; i < filelist.length; i++) {
				File readfile = new File(filePath + "/" + filelist[i]);
				if (!readfile.isDirectory()&&readfile.getName().contains(dbName)) {
					String tempName = readfile.getName().replace(dbName, "");
					String fileprefix = tempName.substring(0,tempName
							.lastIndexOf(".") );
					String fileSuffix = tempName.substring(tempName
							.lastIndexOf(".") + 1);
					if (fileSuffix.toUpperCase().equals("SQL")) {
						logger.info("the current fileName " + tempName);
						Pattern pattern = Pattern.compile("[0-9]*"); 
						if(pattern.matcher(fileprefix).matches()&&maxFileName<Integer.valueOf(fileprefix)){
							maxFileName=Integer.valueOf(fileprefix);
						}
						fileName=dbName+maxFileName+".sql";
					}
				}
			}
		}
		return fileName;

	}
	
	
	

	public static void main(String[] args) {
//		 String str = CommandString.getAllDumpExportStr("root", ResourceBundle
//		 .getDumpResource().getSendpath());
//		 System.out.println(str);

		 MycnfBean mb = new ResourceBundle().getResource();
		 MySqlDumpBean mdb = new ResourceBundle().getDumpResource();
		 List<String> strList = new ArrayList<String>();
		 List<String> ignoretablelist = MysqlSetCnf.getList(mb
		 .getReplicateignoretable());
		 List<String> dobdlist = MysqlSetCnf.getList(mb.getBinlogdodb());
		 String strList1 = CommandString.getDumpImportStr(mb.getRootpassword(),
		 mdb.getReceivepath());
		 for (int i = 0; i < strList.size(); i++) {
		 System.out.println("33333"+strList.get(i));
		 }
		 String list=CommandString.getDumpExportStr(mb.getRootpassword(), dobdlist, ignoretablelist, "D://", "1.sql");
		
		 System.out.println("2222"+list);
		 
		String file=CommandString.getFileName("D://");
		System.out.println(file);

	}

}
