package com.fxdigital.syncclient.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fxdigital.syncclient.bean.DataNativerecordSource;
import com.fxdigital.syncclient.util.ConvertMapUtil;

/**
 * @author het 本地下载数据最新记录数据访问层 2014-7-30 SyncWebb
 *         fxdigital.dbsync.domains.client.dao
 */
@Component
public class DataNativeRecordSourceDao extends BaseDao{
	private static Logger logger = Logger
			.getLogger(DataNativeRecordSourceDao.class);


	

	/**
	 * 插入下载的记录
	 * 
	 * @param uuid
	 * @param userid
	 */
	public void insert(DataNativerecordSource dataNativeRecordSource) {
			save(dataNativeRecordSource);
	}

	/**
	 * 获得本机的同步信息情况
	 * 
	 * @return
	 */
	public List<String[]> getAllNativeRecordSource() {
		String hql = "from DataNativerecordSource";
		return executeQueryStrArray(hql);
	}

	/**
	 * 获得本机的同步信息情况
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getNativeRecordSource() {
        String hql=ConvertMapUtil.map(DataNativerecordSource.class)+" from DataNativerecordSource as a where a.id in (select max(id) from DataNativerecordSource group by centerid) and a.id is not null ORDER BY centerid";
		return executeQuery(hql);
	}

	/**
	 * 获得某一个中心的同步信息情况
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getOneNativeRecordSource(
			String centerid) throws Exception {
		String hql = ConvertMapUtil.map(DataNativerecordSource.class)+" from DataNativerecordSource data where centerid='"
				+ centerid + "' order by id asc";
		logger.info("List<HashMap<String, String>> getOneNativeRecordSource : select * from jms_client.data_nativerecord_source where centerid='"
				+ centerid + "'  order by id asc");
		return executeQuery(hql);
	}

	/**
	 * 获得本机的同步信息情况
	 * 
	 * @return
	 */
	public void deleteNativeRecordSource(String centerid) {
		String sql = "delete from DataNativerecordSource where centerid='"
				+ centerid + "'";
		executeUpdate(sql);
	}

	/**
	 * 删除本机的同步信息情况
	 * 
	 * @return
	 */
	public void deleteLoadNativeRecord(List<HashMap<String, String>> list) {
		for (int i = 0; i < list.size(); i++) {
			String centerid = list.get(i).get("centerid");
			String sql = "delete from DataNativerecordSource where centerid='"
					+ centerid + "'";
			executeUpdate(sql);
		}
	}

	/**
	 * 如果下载失败，则删除保存的新版本（比以前版本大）信息
	 * 
	 * @return
	 */
	public void deleteNativeErrorRecord(List<HashMap<String, String>> list) {
		for (int i = 0; i < list.size(); i++) {
			String centerid = list.get(i).get("centerid");
			String sql = "delete from DataNativerecordSource where centerid='"
					+ centerid + "'";
			logger.info("void deleteLoadNativeRecord : " + sql);
			executeUpdate(sql);
		}

	}

	/**
	 * 
	 * 
	 * @param uuid
	 * @return
	 */
	public void updateNativeRecordFlag(String centerid, String flag) {
		String sql = "update DataNativerecordSource set flag='" + flag
				+ "' where centerid='" + centerid + "'";
		logger.info("void updateNativeRecordFlag : " + sql);
		executeUpdate(sql);

	}

	public void initRecordStates(List<HashMap<String, String>> list,
			String loadState) {
		logger.info("initRecordStates   " + list + "loadState  " + loadState);
		if (list.size() > 0 && null != list) {
			for (int i = 0; i < list.size(); i++) {
				logger.info("当前状态" + loadState);
				String centerid = list.get(i).get("centerid");
				String centername = list.get(i).get("centername");
				String centerip = list.get(i).get("centerip");
				int serverversion = Integer.valueOf(list.get(i).get(
						"serverversion"));
				String uuid = list.get(i).get("uuid");

				recordAllStates(
						centerid, centername, centerip, serverversion, uuid,
						loadState);
			}
		}

	}

	public void recordAllStates(String centerid, String centername,
			String centerip, int version, String uuid, String loadState) {
		logger.info("void recordAllStates : ");
		DataNativerecordSource dataNativeRecordSource = new DataNativerecordSource();
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String downStartTime = sdf.format(d);
		String downEndTime = sdf.format(d);
		String update = sdf.format(d);
		dataNativeRecordSource.setCenterid(centerid);
		dataNativeRecordSource.setCentername(centername);
		dataNativeRecordSource.setCenterip(centerip);
		dataNativeRecordSource.setVersion(version);
		dataNativeRecordSource.setUpdatetime(update);
		dataNativeRecordSource.setUuid(uuid);
		dataNativeRecordSource.setDownstartdate(downStartTime);
		dataNativeRecordSource.setDownenddate(downEndTime);
		dataNativeRecordSource.setFlag(loadState);
		save(dataNativeRecordSource);
	}


}
