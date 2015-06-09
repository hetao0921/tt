package com.fxdigital.mysqlsync.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.fxdigital.mysqlsync.bundle.MySqlDumpBean;
import com.fxdigital.mysqlsync.bundle.MycnfBean;
import com.fxdigital.mysqlsync.bundle.ResourceBundle;
import com.fxdigital.mysqlsync.util.CommandString;
import com.fxdigital.mysqlsync.util.ExecCommand;
import com.fxdigital.mysqlsync.util.FileUtil;

/**
 * @author Administrator
 * 文件导入导出逻辑类
 *
 */
public class MysqlDumpService {
	private static final Logger logger = Logger.getLogger(MysqlDumpService.class);
	static MycnfBean mb = ResourceBundle.getResource();
	static MySqlDumpBean mdb = ResourceBundle.getDumpResource();
	private static MysqlDumpService mysqldumpservice = null;

	public static MysqlDumpService getInstance() {
		if (null == mysqldumpservice) {
			mysqldumpservice = new MysqlDumpService();
		}
		return mysqldumpservice;
	}

	public void setMysqlSync() {

	}

	public int exportMasterDump(String fileName) {
		ExecCommand.excuteStrCommand("/bin/sh -c sudo su");
		StringBuffer sb = new StringBuffer();
		String point = MysqlService.addPoint();
		sb.append(CommandString.getStopMysqlStr());
		sb.append(point);
		sb.append(CommandString.getStartMysqlStr());
		sb.append(point);
		
		List<String> ignoretablelist = MysqlSetCnf.getList(mb
				.getReplicateignoretable());
		List<String> dobdlist = MysqlSetCnf.getList(mb.getBinlogdodb());

		FileUtil.write("exportall.sh", sb.toString()+CommandString.getDumpExportStr(
				mb.getRootpassword(), dobdlist, ignoretablelist,
				mdb.getSendpath(), fileName));
		ExecCommand.excuteStrCommand("chmod 777 "
				+ System.getProperty("user.dir") + "/exportall.sh");
		logger.info("当前目录" + System.getProperty("user.dir"));
		return ExecCommand.excuteGrantCommand(System.getProperty("user.dir")
				+ "/exportall.sh");
	}

	public int importSlaveDump() {
		ExecCommand.excuteStrCommand("/bin/sh -c sudo su");
		StringBuffer sb = new StringBuffer();
		String point = MysqlService.addPoint();
		sb.append(CommandString.getStopMysqlStr());
		sb.append(point);
		sb.append(CommandString.getStartMysqlStr());
		sb.append(point);
		
		String str = CommandString.getDumpImportStr(
				mb.getRootpassword(),
				mdb.getReceivepath());
		
		FileUtil.write("importall.sh",sb.toString()+ str);
		ExecCommand.excuteStrCommand("chmod 777 "
				+ System.getProperty("user.dir") + "/importall.sh");
		logger.info("当前目录" + System.getProperty("user.dir"));
		return ExecCommand.excuteGrantCommand(System.getProperty("user.dir")
				+ "/importall.sh");
	}
}
