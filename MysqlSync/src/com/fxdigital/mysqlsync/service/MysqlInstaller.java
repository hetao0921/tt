package com.fxdigital.mysqlsync.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import com.fxdigital.mysqlsync.util.CommandString;
import com.fxdigital.mysqlsync.util.ExecCommand;
import com.fxdigital.mysqlsync.util.GetSystemProperties;
import com.fxdigital.mysqlsync.util.LogUtil;

public class MysqlInstaller {
	private static final Logger logger = Logger.getLogger(MysqlInstaller.class);
	public static void main(String[] args) {
		//ExecCommand.excuteStrCommand("ifconfig");
		//ExecCommand.excuteStrCommand("ping 192.168.1.122 -t");

		// GetSystemProperties.getLocalIp();
		String ip = GetSystemProperties.getLocalIp();
		logger.info(ip);
		// 初始化mysql
		initMysql();
	}

	public static void initMysql() {
		List<String> list = CommandString.getMysqlInitStr();
		for (int i = 0; i < list.size(); i++) {
			logger.info("当前命令 :" + list.get(i));
			ExecCommand.excuteStrCommand(list.get(i));
		}
	}

	
}
