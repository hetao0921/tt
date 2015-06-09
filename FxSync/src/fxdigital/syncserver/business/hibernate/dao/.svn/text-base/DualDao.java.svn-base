/**
 * 
 */
package fxdigital.syncserver.business.hibernate.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fxdigital.syncserver.business.hibernate.bean.SyncUpnetworkinfo;

/**
 * @author lizehua
 *
 */

public class DualDao {
	Log logger=LogFactory.getLog("DualDao");
	
	
	private SyncUpnetworkinfoDao syncUpnetworkinfoDao;
	

	
	public List<HashMap<String, String>> queryJMS(){
		NVMPHibernate db = NVMPHibernate.getNVMPhibernate();
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
		List<HashMap<String, String>> list=dualDao.queryJMS();
		System.out.println(list.get(0).get("SyncServerIP"));
		//String str=dest;
		//System.err.println(str);
	}

}
