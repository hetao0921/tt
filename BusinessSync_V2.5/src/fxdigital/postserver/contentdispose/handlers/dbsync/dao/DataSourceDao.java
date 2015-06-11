package fxdigital.postserver.contentdispose.handlers.dbsync.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.hibernate.bean.DataSource;
import com.hibernate.db.ConnDo;

import fxdigital.postserver.contentdispose.handlers.dbsync.db.DBConn;

/**
 * @author het 上传下载数据资源访问层 2014-7-30 BusinessSync
 *         fxdigital.postserver.contentdispose.handlers.dbsync.dao
 */
public class DataSourceDao {
	private static final Logger logger = Logger.getLogger(DataSourceDao.class);

	public static DataSourceDao dataSourceDao = null;


	public static DataSourceDao getInstance() {
		if (null == dataSourceDao) {
			dataSourceDao = new DataSourceDao();
		}
		return dataSourceDao;
	}

	/**
	 * 插入同步的资源的记录
	 * 
	 * @param uuid
	 * @param userid
	 */
	public void insert(DataSource dataSource) {

		try {
			DBConn.getDBConn().save(dataSource);
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
	public List<String[]> getAllSource() {
		DBConn db = DBConn.getDBConn();
		String hql="from DataSource";
		return db.executeQuery(hql);
	}

	/**
	 * 获得对应中心版本的文件的地址
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getSourceAddress(String centerid, String version) {
		DBConn db = DBConn.getDBConn();
		String hql="from DataSource where centerid='"
				+ centerid + "' and version='" + version + "'and (fileaddress is null or fileaddress not like '%increment%')";
		return db
				.executeQueryToList(hql);
	}

	/**
	 * 获得本机的所有资源情况
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getAllServerSource(String centerid) {
		DBConn db = DBConn.getDBConn();
		String hql="select new Map(centerid as centerid,centername as centername,centerip as centerip,uuid as uuid,data.updatetime as update,fileaddress as fileaddress,version as version) from DataSource data where centerid='"
				+ centerid + "'";
		return db
				.executeQuerySql(hql);
	}

	/**
	 * 获得本机的所有资源情况
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getResetServerSource(String centerid) {
		DBConn db = DBConn.getDBConn();
		String hql="from DataSource where centerid='"
				+ centerid + "' and fileaddress not like '%increment%' order by version desc";
		return db
				.executeQueryToList(hql);
	}
	
	
	public List<HashMap<String, String>> getIncrementServerSource(String centerid,String version){
		DBConn db = DBConn.getDBConn();
		String hql="from DataSource where centerid='"
				+ centerid + "' and fileaddress like '%increment%' and version='"+version+"'";
		return db
				.executeQueryToList(hql);
	}

	/**
	 * 获得相应中心的同步信息情况
	 * 
	 * @return
	 */
	public List<String[]> getOneSource(String centerid) {
		DBConn db = DBConn.getDBConn();
		String hql="from DataSource where centerid='"
				+ centerid + "'";
		return db
				.executeQuery(hql);
	}
	
	//规则：先插入老数据，新旧同步服务器兼容
	//后插入新数据，修改新文件字段
	public int updateSource(String centerID,int version,String fileaddress){
		String sql = "update DataSource set fileaddress=? where centerid=? and version=?";
		ConnDo.getConnDo().deleteOrUpdate(sql, new Object[]{fileaddress,centerID,version});
		 return 1;
	}

}
