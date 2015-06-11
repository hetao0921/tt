package fxdigital.postserver.contentdispose.handlers.dbsync.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author  het
 *数据操作类
 * 2014-7-30
 * BusinessSync
 * fxdigital.postserver.contentdispose.handlers.dbsync.db
 */
public class JdbcImpl {

	private static JdbcImpl jdbcImpl;

	private JdbcImpl() {
	} 

	public static JdbcImpl getJdbcImpl() {
		if (jdbcImpl == null) {
			jdbcImpl = new JdbcImpl();
		}
		return jdbcImpl;
	}

	/**
	 * 获取上传的状态
	 * 
	 * @return
	 */
	public String[] getUpMsg() {
		DBConn db = DBConn.getDBConn();
		String sql = "select * from jms_server.data_operate where operate = '上传'";
		int num = db.executeQuery(sql).size();
		if (num == 0) {
			return null;
		} else {
			return db.executeQuery(sql).get(0);
		}
	}

	public String[] getUuid(String uuid, String operate) {
		DBConn db = DBConn.getDBConn();
		String sql = "select * from jms_server.data_operate where operate = '" + operate
				+ "' and lockid='" + uuid + "'";
		int num = db.executeQuery(sql).size();
		if (num == 0) {
			return null;
		} else {
			return db.executeQuery(sql).get(0);
		}
	}

	/**
	 * 根据uuid查询出操作记录
	 * 
	 * @param uuid
	 * @return
	 */
	public String[] getUpRecord(String uuid) {
		DBConn db = DBConn.getDBConn();
		String[] ss = db
				.executeQuery(
						"select * from jms_server.data_operate_record where uuid = '"
								+ uuid + "'").get(0);
		// String userid = ss[2];
		return ss;
	}

	/**
	 * 获取下载的状态
	 * 
	 * @return
	 */
	public String[] getLoadMsg() {
		DBConn db = DBConn.getDBConn();
		String sql = "select * from jms_server.data_operate where operate = '下载'";
		int num = db.executeQuery(sql).size();
		if (num == 0) {
			return null;
		} else {
			return db.executeQuery(sql).get(0);
		}
	}

	/**
	 * 获取下载时的记录
	 * 
	 * @param uuid
	 * @return
	 */
	public String[] getLoadRecord(String uuid) {
		DBConn db = DBConn.getDBConn();
		String[] ss = db
				.executeQuery(
						"select * from jms_server.data_operate_record where uuid = '"
								+ uuid + "'").get(0);
		return ss;
	}

	// /**
	// * 获取本机版本信息
	// * @return
	// */
	// public String[] getSelfSource(){
	// DBConn db = DBConn.getDBConn();
	// String[] ss = db.executeQuery("select * from data_self_source").get(0);
	// return ss;
	// }

	/**
	 * 获得本机的同步信息情况
	 * 
	 * @return
	 */
	public List<String[]> getAllSelfSource() {
		DBConn db = DBConn.getDBConn();
		return db.executeQuery("select * from jms_server.data_self_source");
	}

	/**
	 * 清空同步数据
	 */
//	public void clearSyncData() {
//		DBConn db = DBConn.getDBConn();
//		try {
//			db.executeProc("ClearOtherDataSyncInfoProc");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	/**
	 * 更改上传的状态
	 * 
	 * @param uuid
	 */
	public boolean updateUpState(String uuid, Integer flag) {
		// System.out.println("ssssssssssssss执行了上传的方法。。。。");
		DBConn db = DBConn.getDBConn();
		String sql = "select * from jms_server.data_operate where operate='上传'";
		int num = db.executeQuery(sql).size();
		if (num == 0) {
			sql = "insert into jms_server.data_operate(operate,flag,lockid) values('上传',1,'"
					+ uuid + "')";
		} else {
			if (flag == 1)
				sql = "update jms_server.data_operate set flag =" + flag + ",lockid='"
						+ uuid + "' where operate = '上传' and flag=0";
			else
				sql = "update jms_server.data_operate set flag =" + flag + ",lockid='"
						+ uuid + "' where operate = '上传'";
		}

		System.out.println("$$$$$$$$$$" + sql);
		try {
			db.updateInfo(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("修改状态失败");
			return false;
		}
		sql = "select * from jms_server.data_operate where operate='上传' and lockid='"
				+ uuid + "'";
		if (db.executeQuery(sql).size() > 0)
			return true;
		else
			return false;
	}

	/**
	 * 修改下载的状态
	 * 
	 * @param uuid
	 */
	public boolean updateLoadState(String uuid, Integer flag) {
		DBConn db = DBConn.getDBConn();
		String sql = "select * from jms_server.data_operate where operate='下载'";
		int num = db.executeQuery(sql).size();
		if (num == 0) {
			sql = "insert into jms_server.data_operate(operate,flag,lockid) values('下载',1,'"
					+ uuid + "')";
		} else {
			if (flag == 1)
				sql = "update jms_server.data_operate set flag =" + flag + ",lockid='"
						+ uuid + "' where operate = '下载' and flag=0";
			else
				sql = "update jms_server.data_operate set flag =" + flag + ",lockid='"
						+ uuid + "' where operate = '下载'";
		}
		try {
			db.updateInfo(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("修改状态失败");
			return false;
		}
		sql = "select * from jms_server.data_operate where operate='下载' and lockid='"
				+ uuid + "'";
		if (db.executeQuery(sql).size() > 0)
			return true;
		else
			return false;
	}

	/**
	 * 保存上传的记录
	 * 
	 * @param uuid
	 * @param userid
	 */
	public void insertUpRecord(String uuid, String userid) {
		DBConn db = DBConn.getDBConn();
		String sql = "insert into jms_server.data_operate_record(uuid,operatorsessionid,operatetype) values('"
				+ uuid + "','" + userid + "','上传')";
		try {
			db.updateInfo(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("添加同步记录失败");
		}
	}

	/**
	 * 插入下载的记录
	 * 
	 * @param uuid
	 * @param userid
	 */
	public void insertLoadRecord(String uuid, String userid) {
		DBConn db = DBConn.getDBConn();
		String sql = "insert into jms_server.data_operate_record(uuid,operatorsessionid,operatetype) values('"
				+ uuid + "','" + userid + "','下载')";
		try {
			db.updateInfo(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("添加同步记录失败");
		}
	}

	/**
	 * 添加本地下载记录
	 * 
	 * @param version
	 * @param centerid
	 * @param uuid
	 * @param state
	 */
	public void updateNativeRecord(Integer version, String centerid,
			String uuid, Integer state) {
		DBConn db = DBConn.getDBConn();
		// String sql =
		// "insert into data_native_source(centerid,version,uuid,flag) values('"+centerid+
		// "',"+version+",'"+uuid+"','"+state+"')";
		String sql = "update jms_server.data_native_source set version=" + version
				+ ",flag='" + state + "' where centerid='" + centerid
				+ "' and uuid='" + uuid + "'";
		System.out.println("SQL:" + sql);
		try {
			db.updateInfo(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateNativeRecord(String centerid, String uuid, Integer state) {
		DBConn db = DBConn.getDBConn();
		// String sql =
		// "insert into data_native_source(centerid,version,uuid,flag) values('"+centerid+
		// "',"+version+",'"+uuid+"','"+state+"')";
		String sql = "update jms_server.data_native_source set flag='" + state
				+ "' where centerid='" + centerid + "' and uuid='" + uuid + "'";
		System.out.println("SQL:" + sql);
		try {
			db.updateInfo(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 查询同一uuid的下载记录中是否下载完毕，返回0则下载完毕
	 * 
	 * @param uuid
	 * @return
	 */
	public Integer getNativeFlagNum(String uuid) {
		// Integer num = -1;
		DBConn db = DBConn.getDBConn();
		String sql = "select * from jms_server.data_native_source where uuid='" + uuid
				+ "' and flag='1'";
		List<String[]> list = db.executeQuery(sql);
		return list.size();
	}

	/**
	 * 解除锁定
	 * 
	 * @param uuid
	 */
	public void unLock(String uuid) {
		DBConn db = DBConn.getDBConn();
		String sql = "update jms_server.data_operate set flag=0 where lockid='" + uuid
				+ "'";
		try {
			db.updateInfo(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("解锁失败");
		}
	}

	/**
	 * 执行存储过程
	 * 
	 * @param l
	 * @return
	 * @throws Exception 
	 */
	public boolean updateSqls(List<String> l) throws Exception {
		DBConn db = DBConn.getDBConn();
		String[] sql = new String[l.size()];
		for (int i = 0; i < l.size(); i++) {
			sql[i] = l.get(i);
		}
		return db.updateInfo(sql);
	}

	public void updateSelfSource(Integer version, String uuid, Integer flag) {
		// System.out.println("执行了嘛？执行了就没我的问题了。");
		String sql = "select * from jms_server.data_self_source";
		DBConn db = DBConn.getDBConn();
		if (db.executeQuery(sql).size() == 0) {
			sql = "insert into jms_server.data_self_source(uuid,version,flag) values('"
					+ uuid + "'," + version + "," + flag + ")";
		} else {
			sql = "update jms_server.data_self_source set flag=" + flag + ",uuid='" + uuid
					+ "',version=" + version;
		}
		System.out.println("SQL:" + sql);
		try {
			db.updateInfo(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Map<String, String>> getAllNativeSource() {
		DBConn db = DBConn.getDBConn();
		String sql = "select * from jms_server.data_native_source";
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		List<String[]> li = db.executeQuery(sql);
		for (int i = 0; i < li.size(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("centerid", li.get(i)[1]);
			map.put("version", li.get(i)[2]);
			list.add(map);
		}
		return list;
	}

	public void updateNativeSource(String centerid, Integer version, String uuid) {
		DBConn db = DBConn.getDBConn();
		String sql = "";
		if (getNativeSource(centerid) == 0) {
			sql = "insert into jms_server.data_native_source(centerid,version,uuid,flag)"
					+ " values('" + centerid + "'," + 0 + ",'" + uuid
					+ "','1')";
		} else {
			sql = "update jms_server.data_native_source set flag=1,uuid='" + uuid + "'"
					+ " where centerid='" + centerid + "'";
		}
		try {
			db.updateInfo(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Integer getNativeSource(String centerid) {
		DBConn db = DBConn.getDBConn();
		String sql = "from DataNativeSourceDao where centerid='"
				+ centerid + "'";
		return db.executeQuery(sql).size();
	}

	public String[] getSelfSource() {
		DBConn db = DBConn.getDBConn();
		String sql = "select * from jms_server.data_self_source";
		if (db.executeQuery(sql).size() == 0)
			return null;
		else
			return db.executeQuery(sql).get(0);
	}

	public List<String[]> getNativeSource() {
		DBConn db = DBConn.getDBConn();
		String sql = "select * from jms_server.data_native_source";
		return db.executeQuery(sql);
	}

	public void startInit() {
		DBConn db = DBConn.getDBConn();
		System.out.println("初始化data_operate记录。。。");
		String sql1 = "delete from jms_server.data_operate";
		String sql2 = "insert into jms_server.data_operate(operate,flag) values('上传',0)";
		String sql3 = "insert into jms_server.data_operate(operate,flag) values('下载',0)";

		try {
			db.updateInfo(sql1);
			db.updateInfo(sql2);
			db.updateInfo(sql3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
