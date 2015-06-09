package fxdigital.dbsync.domains.client.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.hibernate.bean.DataNativeSource;

import fxdigital.dbsync.domains.client.db.DBConn;

/**
 * @author het 本地下载数据记录数据访问层 2014-7-30 SyncWebb
 *         fxdigital.dbsync.domains.client.dao
 */
@Component
public class DataNativeSourceDao {
	private static Logger logger = Logger.getLogger(DataNativeSourceDao.class);
	public static DataNativeSourceDao dataNativeSourceDao = null;

	public static DataNativeSourceDao getInstance() {
		if (null == dataNativeSourceDao) {
			dataNativeSourceDao = new DataNativeSourceDao();
		}
		return dataNativeSourceDao;
	}

	/**
	 * 插入下载的记录
	 * 
	 * @param uuid
	 * @param userid
	 */
	public void insert(DataNativeSource dataNativeSource) {
		try {
			DBConn.getDBConn().save(dataNativeSource);
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
	public List<String[]> getAllNativeSource() {
		DBConn db = DBConn.getDBConn();
		String hql = "from DataNativeSource";
		return db.executeQuery(hql);
	}

	/**
	 * 获得本机的同步信息情况
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getNativeSource() throws Exception {
		DBConn db =null;
		try{
		 db = DBConn.getDBConn();
		}catch(Exception e){
			System.out.println(e);
		}
//		String hql="select a.* from jms_client.data_native_source a right join (select max(id) id from jms_client.data_native_source group by centerid) b on b.id = a.id where a.id is not null ORDER BY centerid";
		String hql="select new Map(a.id as id,a.centerid as centerid,a.centername as centername,a.centerip as centerip,a.version as version,a.update as update,a.uuid as uuid,a.downstartdate as downstartdate,a.downenddate as downenddate,a.flag as flag) from DataNativeSource a right join (select max(id) id from DataNativeSource group by centerid) b on b.id = a.id where a.id is not null ORDER BY centerid";
		
		
		logger.info("List<HashMap<String, String>> getNativeSource : select a.* from jms_client.data_native_source a right join (select max(id) id from jms_client.data_native_source group by centerid) b on b.id = a.id where a.id is not null ORDER BY centerid");
		return db
				.executeQuerySql(hql);
	}

	/**
	 * 获得本机的同步信息情况
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getAllNativeRecord() throws Exception {
		DBConn db = DBConn.getDBConn();
		String sql = "select new Map(centerid as centerid,centername as centername,centerip as centerip,version as version,update as update,uuid as uuid,downstartdate as downstartdate,downenddate as downenddate,flag as flag) from DataNativeSource";
		return db.executeQuerySql(sql);

	}

	/**
	 * 查询同一uuid的下载记录中是否下载完毕，返回0则下载完毕
	 * 
	 * @param uuid
	 * @return
	 */
	public Integer getNativeFlagNum() {
		// Integer num = -1;
		DBConn db = DBConn.getDBConn();
		String sql = "from DataNativeSource where flag='1'";
		List<String[]> list = db.executeQuery(sql);
		return list.size();
	}
}
