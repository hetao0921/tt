package com.fxdigital.syncclient.service;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxdigital.syncclient.bean.LocalCenter;
import com.fxdigital.syncclient.dao.BackupInfotabDao;
import com.fxdigital.syncclient.dao.LocalCenterDao;
@Service
public class BackupServer {
	private static Logger logger = Logger.getLogger(BackupServer.class);
	@Autowired
	private BackupInfotabDao backupInfotabDao;
	@Autowired
	private LocalCenterDao localCenterDao;

	public boolean isBackup() {
		boolean flag = false;
		LocalCenter localCenter=localCenterDao.queryInfo();
		String centerIp=localCenter.getIp();
		if(!("").equals(centerIp)&&centerIp!=null){
			List<HashMap<String, String>> list=backupInfotabDao.getOneType(centerIp);
			if(list!=null&&list.size()>0){
				flag = true;
			}
		}
		logger.info("该服务器是否为备用服务器：  "+flag);
		return flag;
	}

}
