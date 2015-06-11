package com.fxdigital.syncclient.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fxdigital.syncclient.bean.DataNativeSource;
import com.fxdigital.syncclient.bean.DataOperate;
import com.fxdigital.syncclient.util.ConvertMapUtil;

/**
 * @author het 本地下载数据记录数据访问层 2014-7-30 SyncWebb
 *         fxdigital.dbsync.domains.client.dao
 */
@Component
public class DataNativeSourceDao extends BaseDao{
	private static Logger logger = Logger.getLogger(DataNativeSourceDao.class);


	/**
	 * 插入下载的记录
	 * 
	 * @param uuid
	 * @param userid
	 */
	public void insert(DataNativeSource dataNativeSource) {
			save(dataNativeSource);
	}

	/**
	 * 获得本机的同步信息情况
	 * 
	 * @return
	 */
	public List<String[]> getAllNativeSource() {
		String hql = "from DataNativeSource";
		return executeQueryStrArray(hql);
	}

	/**
	 * 获得本机的同步信息情况
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getNativeSource() throws Exception {
        String hql= ConvertMapUtil.map(DataNativeSource.class)
				+ " from DataNativeSource as a where a.id in (select max(id) from DataNativeSource group by centerid) and a.id is not null ORDER BY centerid";
		
		logger.info("List<HashMap<String, String>> getNativeSource : select a.* from jms_client.data_native_source as a right join select max(id) id from jms_client.data_native_source group by centerid as b on b.id = a.id where a.id is not null ORDER BY centerid");
		return executeQuery(hql);
	}

	/**
	 * 获得本机的同步信息情况
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getAllNativeRecord() throws Exception {
		String sql = "select new Map(centerid as centerid,centername as centername,centerip as centerip,version as version,data.updatetime as update,uuid as uuid,downstartdate as downstartdate,downenddate as downenddate,flag as flag) from DataNativeSource data";
		return executeQuery(sql);

	}

	/**
	 * 查询同一uuid的下载记录中是否下载完毕，返回0则下载完毕
	 * 
	 * @param uuid
	 * @return
	 */
	public Integer getNativeFlagNum() {
		String sql = "from DataNativeSource where flag='1'";
		List<String[]> list = executeQueryStrArray(sql);
		return list.size();
	}
}
