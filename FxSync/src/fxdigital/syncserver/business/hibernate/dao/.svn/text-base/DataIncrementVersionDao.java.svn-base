package fxdigital.syncserver.business.hibernate.dao;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import fxdigital.syncserver.business.hibernate.bean.DataIncrementVersion;
/**
 * @author  het
 *数据版本最新记录访问层
 * 2014-7-30
 * BusinessSync
 * fxdigital.postserver.contentdispose.handlers.dbsync.dao
 */
public class DataIncrementVersionDao {
	
	private static final Logger logger = Logger.getLogger(DataIncrementVersionDao.class);
	public static DataIncrementVersionDao dataIncrementVersionDao = null;

	public static DataIncrementVersionDao getInstance() {
		if (null == dataIncrementVersionDao) {
			dataIncrementVersionDao = new DataIncrementVersionDao();
		}
		return dataIncrementVersionDao;
	}

	/**
	 * 插入同步的资源的记录
	 * 
	 * @param uuid
	 * @param userid
	 */
	public void insert(DataIncrementVersion dataIncrementVersion) {

		try {
			NVMPHibernate.getNVMPhibernate().save(dataIncrementVersion);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("添加本地记录数据失败" + e);
		}
	}

	/**
	 * 删除同步的资源的记录
	 * 
	 * @param uuid
	 * @param userid
	 */
	public void delete(String centerid) {
		NVMPHibernate db = NVMPHibernate.getNVMPhibernate();
		String sql = "delete from DataIncrementVersion where centerid='" + centerid
				+ "'";
		try {
			db.updateInfo(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("删除本地记录数据失败"+e);
		}

	}

	/**
	 * 获得本机的所有同步信息版本情况
	 * 
	 * @return
	 */
	public List<String[]> getAllSource() {
		NVMPHibernate db = NVMPHibernate.getNVMPhibernate();
		String hql="from DataIncrementVersion";
		return db.executeQuerySqls(hql);
	}

	/**
	 * 获得本机的所有同步信息版本情况
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getAllServerSource() {
		NVMPHibernate db = NVMPHibernate.getNVMPhibernate();
		String hql="select new Map(centerid as centerid,centername as centername,centerip as centerip,version as version) from DataIncrementVersion";
		return db.executeQuerySql(hql);
	}

	/**
	 * 获得某一中心同步信息版本情况
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getOneVersion(String centerid) {
		NVMPHibernate db = NVMPHibernate.getNVMPhibernate();
		String hql="from DataIncrementVersion where centerid='"
				+ centerid + "'";
		return db.executeQueryToList(hql);
	}

	/**
	 * 获得除某一中心同步信息版本情况
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getNoOneSource(String centerid) {
		NVMPHibernate db = NVMPHibernate.getNVMPhibernate();
		String hql="select new Map(centerid as centerid,centername as centername,centerip as centerip,version as version) from DataIncrementVersion where centerid<>'"
				+ centerid + "'";
		return db.executeQuerySql(hql);
	}
	


	public static void main(String[] args) throws UnsupportedEncodingException {
//		logger.info(DataVersionDao.getInstance().getNoOneSource(
//				"het@001"));
		HashMap<String, String> result = DataIncrementVersionDao.getInstance().getAllServerSource().get(2);
		
		String str = result.get("centername");
		
		System.out.println(str);
		
//		 byte[] temp=str.getBytes("gbk");//这里写原编码方式
////         byte[] newtemp=new String(temp,"gbk").//getBytes("gbk");//这里写转换后的编码方式
////         String newStr=new String(newtemp,"gbk");//这里写转换后的编码方式
//		 Charset.forName("gbk").decode(ByteBuffer.wrap(temp)).toString();
//         System.out.println(Charset.forName("utf-8").decode(ByteBuffer.wrap(temp)).toString());
//         
//         
//         NVMPHibernate db = NVMPHibernate.getNVMPhibernate();
//         HashMap<String, String> tt=db.executeQuerySql("select * from nvmp.nvmp_commanddevtab").get(2);
// 		System.out.println(db.executeQuerySql("select * from nvmp.nvmp_commanddevtab").get(2)); 
		

	}
}
