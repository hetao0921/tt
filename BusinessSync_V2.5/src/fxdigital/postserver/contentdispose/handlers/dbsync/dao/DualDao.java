/**
 * 
 */
package fxdigital.postserver.contentdispose.handlers.dbsync.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hibernate.bean.SyncUpnetworkinfo;

import fxdigital.db.dao.SyncUpnetworkinfoDao;
import fxdigital.postserver.contentdispose.handlers.dbsync.db.DBConn;

/**
 * @author lizehua
 *
 */
@Repository
public class DualDao {
	Log logger=LogFactory.getLog("DualDao");
	
	@Autowired
	private SyncUpnetworkinfoDao syncUpnetworkinfoDao;
	

	
	public List<HashMap<String, String>> queryJMS(){
		DBConn db = DBConn.getDBConn();
		String hql="from NvmpCenterinfotab";
		return db.executeQueryToList(hql);
	} 
	
	public SyncUpnetworkinfo getUpMqInfo(){
		SyncUpnetworkinfo syncUpnetworkinfo=new SyncUpnetworkinfo();
		syncUpnetworkinfo=syncUpnetworkinfoDao.query();
		return syncUpnetworkinfo;
	}
	
	
	public static void main(String[] args) {
		
		DualDao dualDao=new DualDao();
		//String str=dest;
		//System.err.println(str);
	}

}
