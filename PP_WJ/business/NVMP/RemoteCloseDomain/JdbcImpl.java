package NVMP.RemoteCloseDomain;


import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;


import com.fxdigital.bean.NvmpRemoteclose;
import com.mysql.impl.Hibernate;






public class JdbcImpl {
	private static final Logger  log = Logger.getLogger("Client");
	private static JdbcImpl jdbc;
	private JdbcImpl(){}
	
	public static JdbcImpl getJdbcImpl(){
		if(jdbc==null)
			jdbc=new JdbcImpl();
		return jdbc; 
	}
	public String juadgeRemoteClose(String deviceID,String flag){
		log.info("查询是否已经被遥闭或者开启");
		
		String sql="  select deviceId  from NvmpRemoteclose where deviceId='"+deviceID+"' and remoteStatus='"+flag+"'";
		log.info("查询遥闭sql="+sql);
		List<Object[]> list=Hibernate.getHibernate().createQueryObjectArray(sql);
		
		return Integer.toString(list.size());
		
	}
	
/*	public String juadgeRemoteClose(String deviceID,String flag){
		log.info("查询是否已经被遥闭或者开启");
		String sql=" select count(*) sum from nvmp_remoteclose where DeviceID='"+deviceID+"' and RemoteStatus='"+flag+"'";
		log.info("查询遥闭sql="+sql);
		MySqlManager db=MySqlManager.getInstance();
		List<HashMap<String, String>> list=db.executeQuery(sql);
		return list.get(0).get("sum");
		
	}
*/	
	public boolean updateRemoteClose(String deviceID,String flag){
		String deleteSql="delete from NvmpRemoteclose where deviceId='"+deviceID+"'";
		NvmpRemoteclose close=new NvmpRemoteclose();
		close.setDeviceId(deviceID);
		close.setRemoteStatus(flag);
		log.info("跟新遥闭sql="+deleteSql);
		boolean bol=true;
		try {
			bol=Hibernate.getHibernate().deleteOrUpdate(deleteSql, null);
			bol=Hibernate.getHibernate().save(close)&&bol;
		} catch (Exception e) {
			log.info("遥闭操作错误："+e.getLocalizedMessage());
			bol=false;
			e.printStackTrace();
		}
		return bol;
	}
	
/*	public static void main(String[] args) {
//		JdbcImpl.getJdbcImpl().updateRemoteClose("11", "1");
//		System.out.println(JdbcImpl.getJdbcImpl().queryLocalCenterDevices());
		System.out.println(JdbcImpl.getJdbcImpl().getClientStatus("11"));
	}*/
	
/*	public boolean updateRemoteClose(String deviceID,String flag){
	String deleteSql="delete from nvmp_remoteclose where DeviceID='"+deviceID+"'";
	String sql="insert into  nvmp_remoteclose (RemoteStatus,DeviceID)values( '"+flag+"','"+deviceID+"')";
	log.info("跟新遥闭sql="+sql);
	MySqlManager db=MySqlManager.getInstance();
	boolean bol=true;
	try {
		bol=db.updateInfo(deleteSql);
		bol=db.updateInfo(sql)&&bol;
	} catch (Exception e) {
		log.info("遥闭操作错误："+e.getLocalizedMessage());
		bol=false;
		e.printStackTrace();
	}
	return bol;
}
*/	
	public List<HashMap<String, String>> queryLocalCenterDevices(){
		String sql="select new Map(nct.deviceId as deviceid ,nct.deviceName as devicename ,nct.isOnline as isonline,nct.clientUserId as clientuserid) from NvmpCommanddevtab nct,NvmpCenterinfotab nit where nct.centerId=nit.centerId ";
		
		return Hibernate.getHibernate().createQuery(sql);
	}
/*	public List<HashMap<String, String>> queryLocalCenterDevices(){
		String sql="select nct.DeviceID,nct.DeviceName,nct.IsOnline,nct.ClientUserID from nvmp_commanddevtab nct,nvmp_centerinfotab nit where nct.CenterID=nit.CenterID ";
		MySqlManager db=MySqlManager.getInstance();
		return db.executeQuery(sql);
	}
*/	
	public String getClientStatus(String clientID){
		String sql="select new Map(remoteStatus as remotestatus) from NvmpRemoteclose where deviceId='"+clientID+"'";
		log.info("根据设备ID查询设备状态sql:"+sql);
		
	List<HashMap<String,String>> list=	Hibernate.getHibernate().createQuery(sql);
	String status="0";
	if(list.size()>0){
		
	status=	list.get(0).get("remotestatus");
	}
		return status;
	}
/*	public String getClientStatus(String clientID){
		String sql="select RemoteStatus from nvmp_remoteclose where DeviceID='"+clientID+"'";
		log.info("根据设备ID查询设备状态sql:"+sql);
		MySqlManager db=MySqlManager.getInstance();
		List<HashMap<String,String>> list=	db.executeQuery(sql);
		String status="0";
		if(list.size()>0){
			
			status=	list.get(0).get("remotestatus");
		}
		return status;
	}
*/	
	
}
