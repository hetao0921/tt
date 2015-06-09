package fxdigital.syncserver.business.hibernate.dao;

import java.util.List;
import java.util.Map;

import fxdigital.syncserver.business.hibernate.bean.SyncUpnetworkinfo;



public class SyncUpnetworkinfoDao {
	public static SyncUpnetworkinfoDao syncUpnetworkinfoDao = null;

	/**
	 * 实例化对象
	 * 
	 */
	public static SyncUpnetworkinfoDao getInstance() {
		if (null == syncUpnetworkinfoDao) {
			syncUpnetworkinfoDao = new SyncUpnetworkinfoDao();
		}
		return syncUpnetworkinfoDao;
	}
	
	@SuppressWarnings("unchecked")
	public SyncUpnetworkinfo query() {
		String map = ConvertMapUtil.map(SyncUpnetworkinfo.class);
		String sql = "from SyncUpnetworkinfo";
		List<Map<String, Object>> list = NVMPHibernate.getNVMPhibernate()
				.executeQueryToObjectList(sql);
		if (list != null && list.size() > 0) {
			Map<String, Object> data = (Map<String, Object>) list.get(0);
			SyncUpnetworkinfo syncUpnetworkinfo = new SyncUpnetworkinfo();
			syncUpnetworkinfo.setSubId(data.get("subId").toString());
			syncUpnetworkinfo.setSubIp(data.get("subIp").toString());
			syncUpnetworkinfo.setSubPort(Integer.valueOf(("").equals(data.get("subPort").toString())?"0":data.get("subPort").toString()));
			syncUpnetworkinfo.setSupIp(data.get("supIp").toString());
			syncUpnetworkinfo.setSupPort(Integer.valueOf(("").equals(data.get("supPort").toString())?"0":data.get("supPort").toString()));
			return syncUpnetworkinfo;
		} else {
			return null;
		}
	}
	
	
//	public int update(String centerid){
//		String sql = "update SyncUpnetworkinfo set status="+int_status+" where mqIp='"+ip+"' and applyTime='"+time+"'";
//		ConnDo.getConnDo().executeUpdate(sql);
//		return 1;
//	}
	
	
	public int save(SyncUpnetworkinfo syncUpnetworkinfo){
		
		NVMPHibernate.getNVMPhibernate().save(syncUpnetworkinfo);
		return 1;
	}	
	
	
	public int deleteRecord(){
		String sql = "delete from SyncUpnetworkinfo";
		return NVMPHibernate.getNVMPhibernate().executeUpdate(sql);
	}
	
	
	public static void main(String[] args) {
		SyncUpnetworkinfoDao syncUpnetworkinfoDao=SyncUpnetworkinfoDao.getInstance();
		SyncUpnetworkinfo syncUpnetworkinfo=syncUpnetworkinfoDao.query();
		System.out.println(syncUpnetworkinfo.getSupPort());
		//syncUpnetworkinfoDao.deleteRecord();
	}
}
