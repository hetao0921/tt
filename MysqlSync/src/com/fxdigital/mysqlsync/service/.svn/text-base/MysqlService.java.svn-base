package com.fxdigital.mysqlsync.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.fxdigital.mysqlsync.bundle.MySqlDumpBean;
import com.fxdigital.mysqlsync.bundle.MycnfBean;
import com.fxdigital.mysqlsync.bundle.ResourceBundle;
import com.fxdigital.mysqlsync.util.CommandString;
import com.fxdigital.mysqlsync.util.ExecCommand;
import com.fxdigital.mysqlsync.util.FileUtil;

/**
 * @author Administrator mysql同步互备类
 * 
 */
public class MysqlService {
	private static final Logger logger = Logger.getLogger(MysqlService.class);
	static MycnfBean mb = ResourceBundle.getResource();
	private static MysqlService mysqlservice = null;

	public static MysqlService getInstance() {
		if (null == mysqlservice) {
			mysqlservice = new MysqlService();
		}
		return mysqlservice;
	}

	public void setMysqlSync() {

	}

	public String getInitStr() {
		String point = addPoint();
		StringBuffer sb = new StringBuffer();
		sb.append(CommandString.getStopMysqlStr());
		sb.append(point);
		sb.append(CommandString.getStartMysqlStr());
		sb.append(point);
		sb.append("cat initmysql.sql | ");
		sb.append(CommandString.getSetRootStr());
		// List<String> list=CommandString.getSetRootPw(mb.getRootpassword());
		// for(int i=0;i<list.size();i++){
		// sb.append(list.get(i));
		// sb.append(point);
		// }
		logger.info("sb.toString()" + sb.toString());
		return sb.toString();
	}

	public String getInitMysqlStr() {
		String point = addPoint();
		StringBuffer sb = new StringBuffer();
		List<String> list = CommandString.getSetRootPw(mb.getRootpassword());
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
			sb.append(point);
		}
		logger.info("sb.toString()" + sb.toString());
		return sb.toString();
	}

	public static String addPoint() {
		String str = "";
		if (System.getProperty("os.name").equals("Linux")) // 操作系统名称
		{
			str = "\n";
		} else {
			// logger.info("==============="+System.getProperty("user.dir"));
			str = "\r\n";
		}
		return str;
	}

	public void startMysql() {
		ExecCommand.excuteStrCommand("/bin/sh -c sudo su");
		StringBuffer sb = new StringBuffer();
		String point = addPoint();
		sb.append(CommandString.getStopMysqlStr());
		sb.append(point);
		sb.append(CommandString.getStartMysqlStr());
		FileUtil.write("startMysql.sh", sb.toString());
		ExecCommand.excuteStrCommand("chmod 777 "
				+ System.getProperty("user.dir") + "/startMysql.sh");
		ExecCommand.excuteStrCommand(System.getProperty("user.dir")
				+ "/startMysql.sh");
	}

	public void initMysql() {
		ExecCommand.excuteStrCommand("/bin/sh -c sudo su");
		StringBuffer sb = new StringBuffer();
		String point = addPoint();
		sb.append(CommandString.getStopMysqlStr());
		sb.append(point);
		sb.append(CommandString.getStartMysqlStr());
		sb.append(point);
		sb.append("cat " + System.getProperty("user.dir") + "/initmysql.sql | "
				+ CommandString.getSetRootStr());
		FileUtil.write("startMysql.sh", sb.toString());
		ExecCommand.excuteStrCommand("chmod 777 "
				+ System.getProperty("user.dir") + "/startMysql.sh");

		// startMysql();
		// ExecCommand.excuteStrCommand("/bin/sh -c sudo su");
		// String[] stopcommand=new
		// String[]{"/bin/sh","-c",CommandString.getStopMysqlStr()};
		// ExecCommand.excuteStrCommand(stopcommand);
		// String[] startcommand=new
		// String[]{"/bin/sh","-c",CommandString.getStartMysqlStr()};
		// ExecCommand.excuteStrCommand(startcommand);
		String mysqlstr = MysqlService.getInstance().getInitMysqlStr();
		FileUtil.write("initmysql.sql", mysqlstr);
		ExecCommand.excuteStrCommand("chmod 777 "
				+ System.getProperty("user.dir") + "/initmysql.sql");
		logger.info("当前目录" + System.getProperty("user.dir"));
		// String[] command=new
		// String[]{"/bin/sh","-c","cat "+System.getProperty("user.dir")+"/initmysql.sql | "+CommandString.getSetRootStr()};
		// ExecCommand.excuteStrCommand("/bin/sh -c cat "+System.getProperty("user.dir")+"/initmysql.sql | "+CommandString.getSetRootStr());
		ExecCommand.excuteStrCommand(System.getProperty("user.dir")
				+ "/startMysql.sh");
	}

	public int cpMysqlCnf(String cnfpath, String mycnfpath) {
		ExecCommand.excuteStrCommand("/bin/sh -c sudo su");
		StringBuffer sb = new StringBuffer();
		String point = addPoint();
		// 将master指向一个空值
		String stopSlaveStr = "cat " + System.getProperty("user.dir")
				+ "/stopslave.sql | "
				+ CommandString.getLoginMysqlStr(mb.getRootpassword());
		FileUtil.write("stopslave.sh", stopSlaveStr);
		ExecCommand.excuteStrCommand("chmod 777 "
				+ System.getProperty("user.dir") + "/stopslave.sh");
		FileUtil.write("stopslave.sql", CommandString.getstopSlaveStr());
		ExecCommand.excuteStrCommand("chmod 777 "
				+ System.getProperty("user.dir") + "/stopslave.sql");
		if (ExecCommand.excuteGrantCommand(System.getProperty("user.dir")
				+ "/stopslave.sh") == 0) {
			sb.append(CommandString.getStopMysqlStr());
			sb.append(point);
			sb.append(CommandString.getStartMysqlStr());
			sb.append(point);
			sb.append("cat " + System.getProperty("user.dir")
					+ "/stopmasterpos.sql | "
					+ CommandString.getLoginMysqlStr(mb.getRootpassword()));
			sb.append(point);
		}

		// 将配置文件替换为初始文件
		if (FileUtil.isExist(cnfpath.replace(" ", "") + "/my.cnf")) {
			sb.append(CommandString.getStopMysqlStr());
			sb.append(point);
			sb.append("cp " + cnfpath.replace(" ", "") + "/my.cnf "
					+ mycnfpath.replace(" ", "") + "/");
			sb.append(point);
			sb.append(CommandString.getStartMysqlStr());
			sb.append(point);
		}
		FileUtil.write("cpmysqlcnf.sh", sb.toString());
		ExecCommand.excuteStrCommand("chmod 777 "
				+ System.getProperty("user.dir") + "/cpmysqlcnf.sh");

		String mysqlstr = getStopMasterPos();
		FileUtil.write("stopmasterpos.sql", mysqlstr);
		ExecCommand.excuteStrCommand("chmod 777 "
				+ System.getProperty("user.dir") + "/stopmasterpos.sql");

		logger.info("当前目录" + System.getProperty("user.dir"));
		logger.info("正在执行：" + "cp " + cnfpath + "/my.cnf " + mycnfpath
				+ "/");
		return ExecCommand.excuteGrantCommand(System.getProperty("user.dir")
				+ "/cpmysqlcnf.sh");
	}

	// 解除本地互备
	public int cpLocalMysqlCnf(String cnfpath, String mycnfpath) {
		ExecCommand.excuteStrCommand("/bin/sh -c sudo su");
		StringBuffer sb = new StringBuffer();
		String point = addPoint();
		// 将master指向一个空值
		String stopSlaveStr = "cat " + System.getProperty("user.dir")
				+ "/stopslave.sql | "
				+ CommandString.getLoginMysqlStr(mb.getRootpassword());
		FileUtil.write("stopslave.sh", stopSlaveStr);
		ExecCommand.excuteStrCommand("chmod 777 "
				+ System.getProperty("user.dir") + "/stopslave.sh");
		FileUtil.write("stopslave.sql", CommandString.getstopSlaveStr());
		ExecCommand.excuteStrCommand("chmod 777 "
				+ System.getProperty("user.dir") + "/stopslave.sql");
		if (ExecCommand.excuteGrantCommand(System.getProperty("user.dir")
				+ "/stopslave.sh") == 0) {
			sb.append(CommandString.getStopMysqlStr());
			sb.append(point);
			sb.append(CommandString.getStartMysqlStr());
			sb.append(point);
			sb.append("cat " + System.getProperty("user.dir")
					+ "/stoplocalmasterpos.sql | "
					+ CommandString.getLoginMysqlStr(mb.getRootpassword()));
			sb.append(point);
		}
		// 将配置文件替换为初始文件

		if (FileUtil.isExist(cnfpath.replace(" ", "") + "/my.cnf")) {
			sb.append(CommandString.getStopMysqlStr());
			sb.append(point);
			sb.append("cp " + cnfpath.replace(" ", "") + "/my.cnf "
					+ mycnfpath.replace(" ", "") + "/");
			sb.append(point);
			sb.append(CommandString.getStartMysqlStr());
			sb.append(point);
		}

		FileUtil.write("cplocalmysqlcnf.sh", sb.toString());
		ExecCommand.excuteStrCommand("chmod 777 "
				+ System.getProperty("user.dir") + "/cplocalmysqlcnf.sh");

		String mysqlstr = getStopMasterPos();
		FileUtil.write("stoplocalmasterpos.sql", mysqlstr);
		ExecCommand.excuteStrCommand("chmod 777 "
				+ System.getProperty("user.dir") + "/stoplocalmasterpos.sql");

		logger.info("当前目录" + System.getProperty("user.dir"));
		logger.info("正在执行：" + "cp " + cnfpath + "/my.cnf " + mycnfpath
				+ "/");
		return ExecCommand.excuteGrantCommand(System.getProperty("user.dir")
				+ "/cplocalmysqlcnf.sh");
	}

	// 执行失败回滚
	public int rollBack(String cnfpath, String mycnfpath) {
		ExecCommand.excuteStrCommand("/bin/sh -c sudo su");
		StringBuffer sb = new StringBuffer();
		String point = addPoint();
		// 将master指向一个空值
		String stopSlaveStr = "cat " + System.getProperty("user.dir")
				+ "/stopslave.sql | "
				+ CommandString.getLoginMysqlStr(mb.getRootpassword());
		FileUtil.write("stopslave.sh", stopSlaveStr);
		ExecCommand.excuteStrCommand("chmod 777 "
				+ System.getProperty("user.dir") + "/stopslave.sh");
		FileUtil.write("stopslave.sql", CommandString.getstopSlaveStr());
		ExecCommand.excuteStrCommand("chmod 777 "
				+ System.getProperty("user.dir") + "/stopslave.sql");
		if (ExecCommand.excuteGrantCommand(System.getProperty("user.dir")
				+ "/stopslave.sh") == 0) {
			sb.append(CommandString.getStopMysqlStr());
			sb.append(point);
			sb.append(CommandString.getStartMysqlStr());
			sb.append(point);
			sb.append("cat " + System.getProperty("user.dir")
					+ "/rollback.sql | "
					+ CommandString.getLoginMysqlStr(mb.getRootpassword()));
			sb.append(point);
		}
		// 将配置文件替换为初始文件

		if (FileUtil.isExist(cnfpath.replace(" ", "") + "/my.cnf")) {
			sb.append(CommandString.getStopMysqlStr());
			sb.append(point);
			sb.append("cp " + cnfpath.replace(" ", "") + "/my.cnf "
					+ mycnfpath.replace(" ", "") + "/");
			sb.append(point);
			sb.append(CommandString.getStartMysqlStr());
			sb.append(point);
		}

		FileUtil.write("rollback.sh", sb.toString());
		ExecCommand.excuteStrCommand("chmod 777 "
				+ System.getProperty("user.dir") + "/rollback.sh");

		String mysqlstr = getStopMasterPos();
		FileUtil.write("rollback.sql", mysqlstr);
		ExecCommand.excuteStrCommand("chmod 777 "
				+ System.getProperty("user.dir") + "/rollback.sql");

		logger.info("当前目录" + System.getProperty("user.dir"));
		logger.info("正在执行：" + "cp " + cnfpath + "/my.cnf " + mycnfpath
				+ "/");
		return ExecCommand.excuteGrantCommand(System.getProperty("user.dir")
				+ "/rollback.sh");
	}

	public int copyCnf(String cnfpath, String mycnfpath) {
		ExecCommand.excuteStrCommand("/bin/sh -c sudo su");
		StringBuffer sb = new StringBuffer();
		String point = addPoint();
		sb.append(CommandString.getStopMysqlStr());
		sb.append(point);
		sb.append("cp " + cnfpath.replace(" ", "") + "/my.cnf "
				+ mycnfpath.replace(" ", "") + "/");
		sb.append(point);
		sb.append(CommandString.getStartMysqlStr());
		FileUtil.write("copycnf.sh", sb.toString());
		ExecCommand.excuteStrCommand("chmod 777 "
				+ System.getProperty("user.dir") + "/copycnf.sh");
		logger.info("正在执行：" + "cp " + cnfpath + "/my.cnf " + mycnfpath
				+ "/");
		return ExecCommand.excuteGrantCommand(System.getProperty("user.dir")
				+ "/copycnf.sh");
	}

	public int grantMaster(String slaveip) {
		ExecCommand.excuteStrCommand("/bin/sh -c sudo su");
		StringBuffer sb = new StringBuffer();
		String point = addPoint();
		sb.append(CommandString.getStopMysqlStr());
		sb.append(point);
		sb.append(CommandString.getStartMysqlStr());
		sb.append(point);
		sb.append("cat " + System.getProperty("user.dir")
				+ "/grantmaster.sql | "
				+ CommandString.getLoginMysqlStr(mb.getRootpassword()));
		FileUtil.write("grantmaster.sh", sb.toString());
		ExecCommand.excuteStrCommand("chmod 777 "
				+ System.getProperty("user.dir") + "/grantmaster.sh");

		ExecCommand.excuteStrCommand("/bin/sh -c sudo su");
		String mysqlstr = getMasterGrantStr(slaveip);
		FileUtil.write("grantmaster.sql", mysqlstr);
		ExecCommand.excuteStrCommand("chmod 777 "
				+ System.getProperty("user.dir") + "/grantmaster.sql");
		logger.info("当前目录" + System.getProperty("user.dir"));
		// String[] command=new
		// String[]{"/bin/sh","-c","cat "+System.getProperty("user.dir")+"/grantmaster.sql | "+CommandString.getLoginMysqlStr(mb.getRootpassword())};
		// ExecCommand.excuteStrCommand(command);
		return ExecCommand.excuteGrantCommand(System.getProperty("user.dir")
				+ "/grantmaster.sh");
	}

	public String getMasterGrantStr(String slaveip) {
		StringBuffer sb = new StringBuffer();
		String point = addPoint();
		sb.append(CommandString.getGrantStr(slaveip, mb.getGrantusername(),
				mb.getGrantpassword()));
		sb.append(point);
		sb.append(CommandString.getFlushPrivilegesStr());
		sb.append(point);
		return sb.toString();
	}

	public String getSlaveGrantStr(String masterip) {
		StringBuffer sb = new StringBuffer();
		String point = addPoint();
		sb.append(CommandString.getGrantStr(masterip, mb.getGrantusername(),
				mb.getGrantpassword()));
		sb.append(point);
		sb.append(CommandString.getFlushPrivilegesStr());
		sb.append(point);
		return sb.toString();
	}

	public int grantSlave(String masterip) {
		ExecCommand.excuteStrCommand("/bin/sh -c sudo su");
		StringBuffer sb = new StringBuffer();
		String point = addPoint();
		sb.append(CommandString.getStopMysqlStr());
		sb.append(point);
		sb.append(CommandString.getStartMysqlStr());
		sb.append(point);
		sb.append("cat " + System.getProperty("user.dir")
				+ "/grantslave.sql | "
				+ CommandString.getLoginMysqlStr(mb.getRootpassword()));
		FileUtil.write("grantslave.sh", sb.toString());
		ExecCommand.excuteStrCommand("chmod 777 "
				+ System.getProperty("user.dir") + "/grantslave.sh");

		// ExecCommand.excuteStrCommand("/bin/sh -c sudo su");
		// ExecCommand.excuteStrCommand("/bin/sh -c "+CommandString.getStopMysqlStr());
		// ExecCommand.excuteStrCommand("/bin/sh -c "+CommandString.getStartMysqlStr());
		String mysqlstr = getSlaveGrantStr(masterip);
		FileUtil.write("grantslave.sql", mysqlstr);
		ExecCommand.excuteStrCommand("chmod 777 "
				+ System.getProperty("user.dir") + "/grantslave.sql");
		logger.info("当前目录" + System.getProperty("user.dir"));
		// String[] command=new
		// String[]{"/bin/sh","-c","cat "+System.getProperty("user.dir")+"/grantslave.sql | "+CommandString.getLoginMysqlStr(mb.getRootpassword())};
		// ExecCommand.excuteStrCommand(command);
		return ExecCommand.excuteGrantCommand(System.getProperty("user.dir")
				+ "/grantslave.sh");
	}

	public String getMasterPos(String slaveip, String slavelogfile,
			int slavelogpos) {
		StringBuffer sb = new StringBuffer();
		String point = addPoint();
		List<String> list = CommandString.getSetMasterStr(slaveip,
				mb.getGrantusername(), mb.getGrantpassword(), slavelogfile,
				slavelogpos);
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
			sb.append(point);
		}
		sb.append(CommandString.getstartSlaveStr());
		return sb.toString();
	}

	public String getStopMasterPos() {
		StringBuffer sb = new StringBuffer();
		String point = addPoint();
		// List<String> list = CommandString.getStopMasterStr();

		List<String> list = CommandString.getResetMasterStr();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
			sb.append(point);
		}
		// sb.append(CommandString.getstartSlaveStr());
		return sb.toString();
	}

	public void setMasterPos(String slaveip, String slavelogfile,
			int slavelogpos) {
		ExecCommand.excuteStrCommand("/bin/sh -c sudo su");
		StringBuffer sb = new StringBuffer();
		String point = addPoint();
		sb.append(CommandString.getStopMysqlStr());
		sb.append(point);
		sb.append(CommandString.getStartMysqlStr());
		sb.append(point);
		sb.append("cat " + System.getProperty("user.dir") + "/masterpos.sql | "
				+ CommandString.getLoginMysqlStr(mb.getRootpassword()));
		FileUtil.write("masterpos.sh", sb.toString());
		ExecCommand.excuteStrCommand("chmod 777 "
				+ System.getProperty("user.dir") + "/masterpos.sh");

		// ExecCommand.excuteStrCommand("/bin/sh -c sudo su");
		// ExecCommand.excuteStrCommand("/bin/sh -c "+CommandString.getStopMysqlStr());
		// ExecCommand.excuteStrCommand("/bin/sh -c "+CommandString.getStartMysqlStr());
		String mysqlstr = getMasterPos(slaveip, slavelogfile, slavelogpos);
		FileUtil.write("masterpos.sql", mysqlstr);
		ExecCommand.excuteStrCommand("chmod 777 "
				+ System.getProperty("user.dir") + "/masterpos.sql");
		// logger.info("当前目录"+System.getProperty("user.dir"));
		// ExecCommand.excuteStrCommand("cat "+System.getProperty("user.dir")+"/masterpos.sql | "+CommandString.getLoginMysqlStr(mb.getRootpassword()));
		ExecCommand.excuteStrCommand(System.getProperty("user.dir")
				+ "/masterpos.sh");
	}

	public String getSlavePos(String masterip, String masterlogfile,
			int masterlogpos) {
		StringBuffer sb = new StringBuffer();
		String point = addPoint();
		List<String> list = CommandString.getSetMasterStr(masterip,
				mb.getGrantusername(), mb.getGrantpassword(), masterlogfile,
				masterlogpos);
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
			sb.append(point);
		}
		sb.append(CommandString.getstartSlaveStr());
		return sb.toString();
	}

	public void setSlavePos(String masterip, String masterlogfile,
			int masterlogpos) {
		ExecCommand.excuteStrCommand("/bin/sh -c sudo su");
		StringBuffer sb = new StringBuffer();
		String point = addPoint();
		sb.append(CommandString.getStopMysqlStr());
		sb.append(point);
		sb.append(CommandString.getStartMysqlStr());
		sb.append(point);
		sb.append("cat " + System.getProperty("user.dir") + "/slavepos.sql | "
				+ CommandString.getLoginMysqlStr(mb.getRootpassword()));
		FileUtil.write("slavepos.sh", sb.toString());
		ExecCommand.excuteStrCommand("chmod 777 "
				+ System.getProperty("user.dir") + "/slavepos.sh");

		String mysqlstr = getMasterPos(masterip, masterlogfile, masterlogpos);
		FileUtil.write("slavepos.sql", mysqlstr);
		ExecCommand.excuteStrCommand("chmod 777 "
				+ System.getProperty("user.dir") + "/slavepos.sql");
		// logger.info("当前目录"+System.getProperty("user.dir"));
		// ExecCommand.excuteStrCommand("cat "+System.getProperty("user.dir")+"/slavepos.sql | "+CommandString.getLoginMysqlStr(mb.getRootpassword()));
		ExecCommand.excuteStrCommand(System.getProperty("user.dir")
				+ "/slavepos.sh");
	}

	public void setMasterGrant(String slaveip) {
		ExecCommand.excuteStrCommand(CommandString.getLoginMysqlStr(mb
				.getRootpassword()));
		ExecCommand.excuteStrCommand(CommandString.getGrantStr(slaveip,
				mb.getGrantusername(), mb.getGrantpassword()));
		ExecCommand.excuteStrCommand(CommandString.getFlushPrivilegesStr());
	}

	public void setSlaveGrant(String masterip) {
		ExecCommand.excuteStrCommand(CommandString.getLoginMysqlStr(mb
				.getRootpassword()));
		ExecCommand.excuteStrCommand(CommandString.getGrantStr(masterip,
				mb.getGrantusername(), mb.getGrantpassword()));
		ExecCommand.excuteStrCommand(CommandString.getFlushPrivilegesStr());
	}

	public void setMasterFile() {
		String fileName = mb.getFilepath();
		// String content = "new append!";
		String content = MysqlSetCnf.getMasterCnf();
		// 按方法A追加文件
		String str = FileUtil.readFileByLines(fileName);
		if (!(str.contains("server_id=1") || str.contains("server_id=2"))) {
			FileUtil.appendMethodA(fileName, content);
		}
	}

	public void setSlaveFile() {
		String fileName = mb.getFilepath();
		// String content = "new append!";
		String content = MysqlSetCnf.getSlaveCnf();
		// 按方法A追加文件
		String str = FileUtil.readFileByLines(fileName);
		if (!(str.contains("server_id=1") || str.contains("server_id=2"))) {
			FileUtil.appendMethodA(fileName, content);
		}
	}

	public List<HashMap<String, String>> getMasterBinInfo() {
		ExecCommand.excuteStrCommand("/bin/sh -c sudo su");
		StringBuffer sb = new StringBuffer();
		String point = addPoint();
		sb.append(CommandString.getStopMysqlStr());
		sb.append(point);
		sb.append(CommandString.getStartMysqlStr());
		sb.append(point);
		sb.append("cat " + System.getProperty("user.dir") + "/masterbin.sql | "
				+ CommandString.getLoginMysqlStr(mb.getRootpassword()));
		FileUtil.write("masterbin.sh", sb.toString());
		ExecCommand.excuteStrCommand("chmod 777 "
				+ System.getProperty("user.dir") + "/masterbin.sh");

		String mysqlstr = CommandString.getMasterStatusStr();
		FileUtil.write("masterbin.sql", mysqlstr);
		ExecCommand.excuteStrCommand("chmod 777 "
				+ System.getProperty("user.dir") + "/masterbin.sql");
		// logger.info("当前目录"+System.getProperty("user.dir"));
		// ExecCommand.excuteStrCommand("cat "+System.getProperty("user.dir")+"/slavepos.sql | "+CommandString.getLoginMysqlStr(mb.getRootpassword()));
		List<String> strlist = ExecCommand.getExcuteResult(System
				.getProperty("user.dir") + "/masterbin.sh");

		// ExecCommand.excuteStrCommand(CommandString.getStopMysqlStr());
		// ExecCommand.excuteStrCommand(CommandString.getStartMysqlStr());
		// ExecCommand.excuteStrCommand(CommandString.getLoginMysqlStr(mb.getRootpassword()));
		// List<HashMap<String,String>> list=CommandString.getLogBinInfo();
		List<HashMap<String, String>> list = getMasterPos(strlist);

		logger.info("list" + list);

		return list;

	}

	public List<HashMap<String, String>> getMasterPos(List<String> strlist) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		if (null != strlist && strlist.size() > 0) {
			for (int i = 0; i < strlist.size(); i++) {
				String str = strlist.get(i);

				if (str.contains("File")) {
					String[] strfile = str.split(":");

					HashMap<String, String> filemap = new HashMap<String, String>();
					filemap.put("File", strfile[1]);
					list.add(filemap);
				} else if (str.contains("Position")) {
					String[] strposition = str.split(":");
					HashMap<String, String> positionmap = new HashMap<String, String>();
					positionmap.put("Position", strposition[1]);
					list.add(positionmap);
				}
			}
		}

		return list;

	}

	public List<HashMap<String, String>> getSlaveBinInfo() {
		ExecCommand.excuteStrCommand("/bin/sh -c sudo su");
		StringBuffer sb = new StringBuffer();
		String point = addPoint();
		sb.append(CommandString.getStopMysqlStr());
		sb.append(point);
		sb.append(CommandString.getStartMysqlStr());
		sb.append(point);
		sb.append("cat " + System.getProperty("user.dir") + "/slavebin.sql | "
				+ CommandString.getLoginMysqlStr(mb.getRootpassword()));
		FileUtil.write("slavebin.sh", sb.toString());
		ExecCommand.excuteStrCommand("chmod 777 "
				+ System.getProperty("user.dir") + "/slavebin.sh");

		String mysqlstr = CommandString.getSlaveStatusStr();
		FileUtil.write("slavebin.sql", mysqlstr);
		ExecCommand.excuteStrCommand("chmod 777 "
				+ System.getProperty("user.dir") + "/slavebin.sql");
		// logger.info("当前目录"+System.getProperty("user.dir"));
		// ExecCommand.excuteStrCommand("cat "+System.getProperty("user.dir")+"/slavepos.sql | "+CommandString.getLoginMysqlStr(mb.getRootpassword()));
		ExecCommand.excuteStrCommand(System.getProperty("user.dir")
				+ "/slavebin.sh");

		// ExecCommand.excuteStrCommand(CommandString.getStopMysqlStr());
		// ExecCommand.excuteStrCommand(CommandString.getStartMysqlStr());
		// ExecCommand.excuteStrCommand(CommandString.getLoginMysqlStr(mb.getRootpassword()));
		// List<HashMap<String,String>> list=CommandString.getLogBinInfo();
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		return list;

	}

	public void setMasterPos1(String slaveip, String slavelogfile,
			int slavelogpos) {
		// ExecCommand.excuteStrCommand(CommandString.getStopMysqlStr());
		// ExecCommand.excuteStrCommand(CommandString.getStartMysqlStr());
		// ExecCommand.excuteStrCommand(CommandString.getLoginMysqlStr(mb.getRootpassword()));
		List<String> list = CommandString.getSetMasterStr(slaveip,
				mb.getGrantusername(), mb.getGrantpassword(), slavelogfile,
				slavelogpos);
		for (int i = 0; i < list.size(); i++) {
			ExecCommand.excuteStrCommand(list.get(i));
		}
		ExecCommand.excuteStrCommand(CommandString.getstartSlaveStr());
	}

	public void setSlavePos1(String masterip, String masterlogfile,
			int masterlogpos) {
		ExecCommand.excuteStrCommand(CommandString.getStopMysqlStr());
		ExecCommand.excuteStrCommand(CommandString.getStartMysqlStr());
		ExecCommand.excuteStrCommand(CommandString.getLoginMysqlStr(mb
				.getRootpassword()));
		List<String> list = CommandString.getSetMasterStr(masterip,
				mb.getGrantusername(), mb.getGrantpassword(), masterlogfile,
				masterlogpos);
		for (int i = 0; i < list.size(); i++) {
			ExecCommand.excuteStrCommand(list.get(i));
		}
		ExecCommand.excuteStrCommand(CommandString.getstartSlaveStr());
	}

	public String ConfirmGrant(String ip) {
		String result = "no";
		ExecCommand.excuteStrCommand("/bin/sh -c sudo su");

		String strConfirm = CommandString.getConfirmGrant(ip,
				mb.getGrantusername(), mb.getGrantpassword());

		FileUtil.write("confirmgrant.sh", strConfirm);

		ExecCommand.excuteStrCommand("chmod 777 "
				+ System.getProperty("user.dir") + "/confirmgrant.sh");

		int str = ExecCommand.getConfirmResult(System.getProperty("user.dir")
				+ "/confirmgrant.sh");
		if (str == 0) {
			result = "yes";
		} else {
			result = "no";
		}
		return result;
	}

	public String confirmSync() {

		String result = "no";

		ExecCommand.excuteStrCommand("/bin/sh -c sudo su");
		StringBuffer sb = new StringBuffer();
		String point = addPoint();
		sb.append(CommandString.getStopMysqlStr());
		sb.append(point);
		sb.append(CommandString.getStartMysqlStr());
		sb.append(point);
		sb.append("cat " + System.getProperty("user.dir")
				+ "/confirmsync.sql | "
				+ CommandString.getLoginMysqlStr(mb.getRootpassword()));
		FileUtil.write("confirmsync.sh", sb.toString());
		ExecCommand.excuteStrCommand("chmod 777 "
				+ System.getProperty("user.dir") + "/confirmsync.sh");

		String mysqlstr = CommandString.getSlaveStatusStr();
		FileUtil.write("confirmsync.sql", mysqlstr);
		ExecCommand.excuteStrCommand("chmod 777 "
				+ System.getProperty("user.dir") + "/confirmsync.sql");

		List<String> list = ExecCommand.getExcuteResult(System
				.getProperty("user.dir") + "/confirmsync.sh");
		if (null != list && list.size() > 0) {
			String ioStatus = "";
			String sqlStatus = "";
			for (int i = 0; i < list.size(); i++) {
				String tempStr = list.get(i);
				if (tempStr.contains("Slave_IO_Running")) {
					String[] ioArray = tempStr.split(":");
					ioStatus = ioArray[1].replace(" ", "");

				}
				if (tempStr.contains("Slave_SQL_Running")) {
					String[] sqlArray = tempStr.split(":");
					sqlStatus = sqlArray[1].replace(" ", "");
					break;
				}
			}
			logger.info("ioStatus:" + ioStatus + "sqlStatus:"
					+ sqlStatus);
			if (ioStatus.contains("Yes") && sqlStatus.contains("Yes")) {
				result = "yes";
			}
		} else {
			result = "no";
		}
		return result;

	}

	public boolean setCommand(String command) {
		boolean flag = false;
		logger.info("执行命令开始:" + command);
		ExecCommand.excuteStrCommand("/bin/sh -c sudo su");
		FileUtil.write("stopbackupservice.sh", command);
		ExecCommand.excuteStrCommand("chmod 777 "
				+ System.getProperty("user.dir") + "/stopbackupservice.sh");
		int str = ExecCommand.getConfirmResult(System.getProperty("user.dir")
				+ "/stopbackupservice.sh");
		if (str == 0) {
			flag = true;
		}
		return flag;
	}

	public static void main(String[] args) {
	    MySqlDumpBean mdb = ResourceBundle.getDumpResource();
		String cnfpath = mdb.getSendpath();
		String mycnfpath = ResourceBundle.getResource()
		.getBasedir();
          MysqlService.getInstance().rollBack(cnfpath, mycnfpath);
	}
}
