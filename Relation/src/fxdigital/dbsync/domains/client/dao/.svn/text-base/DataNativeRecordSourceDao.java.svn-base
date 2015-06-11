package fxdigital.dbsync.domains.client.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.hibernate.bean.DataNativerecordSource;

import fxdigital.dbsync.domains.client.db.DBConn;

/**
 * @author het 本地下载数据最新记录数据访问层 2014-7-30 SyncWebb
 *         fxdigital.dbsync.domains.client.dao
 */
@Component
public class DataNativeRecordSourceDao {
	private static Logger logger = Logger
			.getLogger(DataNativeRecordSourceDao.class);

	public static DataNativeRecordSourceDao dataNativeRecordSourceDao = null;

	public static DataNativeRecordSourceDao getInstance() {
		if (null == dataNativeRecordSourceDao) {
			dataNativeRecordSourceDao = new DataNativeRecordSourceDao();
		}
		return dataNativeRecordSourceDao;
	}

	/**
	 * 插入下载的记录
	 * 
	 * @param uuid
	 * @param userid
	 */
	public void insert(DataNativerecordSource dataNativeRecordSource) {

		try {
			DBConn.getDBConn().save(dataNativeRecordSource);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("添加本地记录数据失败" + e);
		}
	}

	/**
	 * 获得本机的同步信息情况
	 * 
	 * @return
	 */
	public List<String[]> getAllNativeRecordSource() {
		String hql = "from DataNativerecordSource";
		DBConn db = DBConn.getDBConn();
		logger.info("List<String[]> getAllNativeRecordSource: select * from jms_client.data_nativerecord_source");
		return db.executeQuery(hql);
	}

	/**
	 * 获得本机的同步信息情况
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getNativeRecordSource()
			throws Exception {
//		String hql = "select a.* from jms_client.data_nativerecord_source a right join (select max(id) id from jms_client.data_nativerecord_source group by centerid) b on b.id = a.id where a.id is not null ORDER BY centerid";
//		String hql = "select new Map(a.id as id,a.centerid as centerid,a.centername as centername,a.centerip as centerip,a.version as version,a.update as update,a.uuid as uuid,a.downstartdate as downstartdate,a.downenddate as downenddate,a.flag as flag) from DataNativerecordSource a right join (select max(id) id from DataNativerecordSource group by centerid) b on b.id = a.id where a.id is not null ORDER BY centerid";
//      String hql=	"from DataNativerecordSource a right join (select max(id) id from DataNativerecordSource group by centerid) b on b.id = a.id where a.id is not null ORDER BY centerid";
	   
//        String hql="select new Map(max(id),centerid,centername,centerip,version,data.update,uuid,downstartdate,downenddate,flag) from DataNativerecordSource data where data.id is not null ORDER BY data.centerid";
        String hql="from DataNativerecordSource as a where a.id in (select max(id) from DataNativerecordSource group by centerid) and a.id is not null ORDER BY centerid";

        
        
        DBConn db = DBConn.getDBConn();
		logger.info("List<HashMap<String, String>> getNativeRecordSource : select a.* from jms_client.data_nativerecord_source a right join (select max(id) id from jms_client.data_nativerecord_source group by centerid) b on b.id = a.id where a.id is not null ORDER BY centerid");
		return db
				.executeQueryToList(hql);
	}

	/**
	 * 获得某一个中心的同步信息情况
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getOneNativeRecordSource(
			String centerid) throws Exception {
		String hql = "from DataNativerecordSource data where centerid='"
				+ centerid + "' order by id asc";
		DBConn db = DBConn.getDBConn();
		logger.info("List<HashMap<String, String>> getOneNativeRecordSource : select * from jms_client.data_nativerecord_source where centerid='"
				+ centerid + "'  order by id asc");
		return db.executeQueryToList(hql);
	}

	/**
	 * 获得本机的同步信息情况
	 * 
	 * @return
	 */
	public void deleteNativeRecordSource(String centerid) {
		DBConn db = DBConn.getDBConn();
		String sql = "delete from DataNativerecordSource where centerid='"
				+ centerid + "'";
		logger.info("void deleteNativeRecordSource : " + sql);
		try {
			db.updateInfo(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 删除本机的同步信息情况
	 * 
	 * @return
	 */
	public void deleteLoadNativeRecord(List<HashMap<String, String>> list) {
		DBConn db = DBConn.getDBConn();
		for (int i = 0; i < list.size(); i++) {
			String centerid = list.get(i).get("centerid");
			String sql = "delete from DataNativerecordSource where centerid='"
					+ centerid + "'";
			logger.info("void deleteLoadNativeRecord : " + sql);
			try {
				db.updateInfo(sql);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 如果下载失败，则删除保存的新版本（比以前版本大）信息
	 * 
	 * @return
	 */
	public void deleteNativeErrorRecord(List<HashMap<String, String>> list) {

		DBConn db = DBConn.getDBConn();
		for (int i = 0; i < list.size(); i++) {
			String centerid = list.get(i).get("centerid");
			String sql = "delete from DataNativerecordSource where centerid='"
					+ centerid + "'";
			logger.info("void deleteLoadNativeRecord : " + sql);
			try {
				db.updateInfo(sql);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * 
	 * 
	 * @param uuid
	 * @return
	 */
	public void updateNativeRecordFlag(String centerid, String flag) {

		DBConn db = DBConn.getDBConn();
		String sql = "update DataNativerecordSource set flag='" + flag
				+ "' where centerid='" + centerid + "'";
		logger.info("void updateNativeRecordFlag : " + sql);
		try {
			db.updateInfo(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("fail");
		}

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

				DataNativeRecordSourceDao.getInstance().recordAllStates(
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
		logger.info(d);
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
		DataNativeRecordSourceDao.getInstance().insert(dataNativeRecordSource);
	}

	public static void main(String[] args) throws Exception {
		DataNativeRecordSourceDao d = new DataNativeRecordSourceDao();
//		List<HashMap<String, String>> list = d.getNativeRecordSource();
//		logger.info(list);
		
		
		List<String[]> li=d.getAllNativeRecordSource();
		System.out.println(li);
	}

}
