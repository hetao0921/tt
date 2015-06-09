package com.fxdigital.syncclient.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
@Repository
public class BackupInfotabDao extends BaseDao{
	
	private static final Logger logger = Logger.getLogger(BackupInfotabDao.class);

	/**
	 * 获得是否是主备信息
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getOneType(String centerip) {
		String hql="select new Map(centerIp as centerIp,centerName as centerName,centerId as centerId,flag as flag,status as status) from BackupInfotab where centerip='"
				+ centerip + "' and flag=0";
		return executeQuery(hql);
	}

}
