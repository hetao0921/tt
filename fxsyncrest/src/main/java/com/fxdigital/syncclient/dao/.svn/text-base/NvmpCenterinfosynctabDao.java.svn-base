package com.fxdigital.syncclient.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.fxdigital.syncclient.bean.NvmpCenterinfosynctab;
import com.fxdigital.syncclient.util.ConvertMapUtil;

@Repository
public class NvmpCenterinfosynctabDao extends BaseDao{
	private static final Logger logger = Logger.getLogger(NvmpCenterinfosynctabDao.class);
	
	
	/**
	 * 获得本机的同步信息情况
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getNvmpCenterinfosynctab(String centerid) {
		return executeQuery(ConvertMapUtil.map(NvmpCenterinfosynctab.class)
				+ " from NvmpCenterinfosynctab where centerId='"+centerid+"'");
	}

}
