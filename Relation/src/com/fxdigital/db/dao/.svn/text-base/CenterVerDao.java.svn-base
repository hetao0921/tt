package com.fxdigital.db.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fxdigital.db.pojo.CenterVerinfo;
import com.fxdigital.db.pojo.UpRecordinfo;
import com.hibernate.bean.NetupCenterverinfo;
import com.hibernate.bean.NetupUprecord;
import com.hibernate.db.ConnDo;
import com.hibernate.db.ConvertMapUtil;


@Component
public class CenterVerDao {
	private static final Logger log = Logger.getLogger(CenterVerDao.class);
	
	public List<HashMap<String, String>>  queryCenterInfo(){
		String hql = "select new Map(centerId as centerId,centerName as centerName,centerIp as centerIp) from NvmpCenterinfotab";
		List<HashMap<String, String>> list = ConnDo.getConnDo().executeQuery(hql);
		if(list != null && list.size() >0){
//			Map<String, Object> data = (Map<String, Object>)list.get(0);
			return list;
		}else{
			return null;
		}
	}
	
	public List<?> queryCenterNum(){
		String map=ConvertMapUtil.map(NetupCenterverinfo.class);
		String hql = "select "+map+" from NetupCenterverinfo";
		List<?> list = ConnDo.getConnDo().executeQuery(hql);
		return list;
	}
	
	public List<CenterVerinfo> queryCenterVer(){
		String map=ConvertMapUtil.map(NetupCenterverinfo.class);
		String hql = "select "+map+" from NetupCenterverinfo";
		List<CenterVerinfo> centerinfos=new ArrayList<CenterVerinfo>();
		List<HashMap<String, String>> list = ConnDo.getConnDo().executeQuery(hql);
		for(HashMap<String, String> tmp : list){
			HashMap<String, String> data = (HashMap<String, String>)tmp;
			CenterVerinfo centerinfo= new CenterVerinfo();
			centerinfo.setDeviceId(data.get("Deviceid"));
			centerinfo.setDeviceIp(data.get("Deviceip"));
			centerinfo.setDeviceName(data.get("Devicename"));
			centerinfo.setSoftType(data.get("Softtype"));
			centerinfo.setSoftVersion(data.get("Softversion"));
			centerinfo.setSoftOldVer(data.get("Softoldversion"));
			centerinfo.setUpServerip(data.get("Upserverip"));
			centerinfo.setUpServerPort(data.get("Upserverport"));
			centerinfo.setUpState(data.get("Upstate"));
			centerinfos.add(centerinfo);
		}
		return centerinfos;
	}
	
	public int insertCenVerinfo(String deviceid,String deviceip,String devicename,String softtype,String softversion,String upserverip,String upserverport){
		NetupCenterverinfo centerverinfo=new NetupCenterverinfo();
		centerverinfo.setDeviceid(deviceid);
		centerverinfo.setDeviceip(deviceip);
		centerverinfo.setDevicename(devicename);
		centerverinfo.setSofttype(softtype);
		centerverinfo.setSoftversion(softversion);
		centerverinfo.setUpserverip(upserverip);
		centerverinfo.setUpserverport(upserverport);
//			String sql="insert into nvmp.netup_centerverinfo(Deviceid,Deviceip,Devicename,Softtype,Softversion,Upserverip,Upserverport) values('"+deviceid+"','"+deviceip+"','"+devicename+"','"+softtype+"','"+softversion+"','"+softupserverip+"','"+softupserverport+"')";
//			log.info(sql);
		if(ConnDo.getConnDo().save(centerverinfo))
			return 1;
		else
			return -1;
//		log.info("插入数据是否成功！"+reslut);
	}
	
	@SuppressWarnings("unchecked")
	public int getid(){
		int id=0;
		String hql ="select new map(id as id) from NetupCenterverinfo";
		List<Map<String, Object>> list = ConnDo.getConnDo().executeQueryToObjectList(hql);
		for(Object tmp : list){
			Map<String, Object> data = (Map<String, Object>)tmp;
			id=(Integer) data.get("ID");
			}
		return id;
	}
	
	public int updateCenVerInfo(String name,String ip,String deviceid,String upserip,String upserport,String softtype){
		
		String hql = "update NetupCenterverinfo set deviceid='%s',deviceip='%s',devicename='%s', upserverip='%s' ,upserverport='%s' ,softtype='%s'";
		hql = String.format(hql, deviceid,ip,name,upserip,upserport,softtype);
//		int id=getid();
//		String sql="update nvmp.netup_centerverinfo t set t.Deviceid='"+deviceid+"',t.Deviceip='"+ip+"',t.Devicename='"+name+"',t.Upserverip='"+upserip+"',t.Upserverport='"+upserport+"',t.Softtype='"+softtype+"' where t.ID="+id;
		log.info("更新升级中心指向："+hql);
//		reslut=jdbcTemplate.update(sql);
//		log.info("更新bak数据是否成功！"+reslut);
		return ConnDo.getConnDo().executeUpdate(hql);
	}
	//更新最新版本
	public int updateCenVersion(String softver,String softoldver,String upstate){
		String hql = "update NetupCenterverinfo set softversion='%s',softoldversion='%s',upstate='%s'";
		hql = String.format(hql, softver,softoldver,upstate);
//		String sql="update nvmp.netup_centerverinfo t set t.Softversion='"+softver+"',t.Softoldversion='"+softoldver+"',t.Upstate='"+upstate+"' where t.ID="+id;
		log.info("更新最新版本："+hql);
//		reslut=jdbcTemplate.update(sql);
//		log.info("更新bak数据是否成功！"+reslut);
		return ConnDo.getConnDo().executeUpdate(hql);
	}
	
	//判断是否有升级记录值
//	public int queryUpRecord(String centerid,String softtype,String softversion,String softcurver){
//		String sql="select * form nvmp.netup_uprecord where Deviceid='"+centerid+"' and Softtype='"+softtype+"' and Softversion='"+softversion+"' and Softcurversion='"+softcurver+"'";
//		List<?> list = jdbcTemplate.queryForList(sql);
//		if(null!=list&&list.size()>0){
//			return 1;
//		}else if(null!=list&&list.size()==0){
//			return 0;
//		}
//		return -1;
//	}
	//插入升级记录
	public int insertUprecord(String deviceid,String softname,String softtype,String softversion,String softcurversion,String fileid,String filelen,String publishdate,String operatetype,String operatetatus,String lockfile,String issend,String upgradedate){
		NetupUprecord uprecord=new NetupUprecord();
		uprecord.setDeviceid(deviceid);
		uprecord.setSoftname(softname);
		uprecord.setSofttype(softtype);
		uprecord.setSoftversion(softversion);
		uprecord.setSoftcurversion(softcurversion);
		uprecord.setFileid(fileid);
		uprecord.setFilelen(filelen);
		uprecord.setPublishdate(publishdate);
		uprecord.setUpgradedate(upgradedate);
		uprecord.setOperatetype(operatetype);
		uprecord.setOperatetatus(operatetatus);
		uprecord.setLockfile(lockfile);
		uprecord.setIssend(issend);
		if(ConnDo.getConnDo().save(uprecord))
			return 1;
		else
			return -1;
//		String deviceid=upRecordinfo.getDeviceid();
//		String softname=upRecordinfo.getSoftname();
//		String softtype=upRecordinfo.getSofttype();
//		String softver=upRecordinfo.getSoftversion();
//		String softurver=upRecordinfo.getSoftcurversion();
//		String fileid=upRecordinfo.getFileid();
//		String filelen=upRecordinfo.getFilelen();
//		String publishdate=upRecordinfo.getPublishdate();
//		String operatetype=upRecordinfo.getOperatetype();
//		String operatetatus=upRecordinfo.getOperatetatus();
//		String lock=upRecordinfo.getLock();
//		String issend=upRecordinfo.getIssend();
//		String sql="insert into nvmp.netup_uprecord(Deviceid,SoftName,Softtype,Softversion,Softcurversion,Filelen,Publishdate,Upgradedate,Operatetype,Operatetatus,Fileid,Lockfile,Issend) values('"+deviceid+"','"+softname+"','"+softtype+"','"+softver+"','"+softurver+"','"+filelen+"','"+publishdate+"','"+date+"','"+operatetype+"','"+operatetatus+"','"+fileid+"','"+lock+"','"+issend+"')";
//		int reslut=jdbcTemplate.update(sql);
	}
	//查询升级记录
	public List<UpRecordinfo> queryUprecords(){
		String map=ConvertMapUtil.map(NetupUprecord.class);
		String hql = "select "+map+" from NetupUprecord order by Upgradedate desc";
		List<UpRecordinfo> upRecordinfos=new ArrayList<UpRecordinfo>();
		List<HashMap<String, String>> list = ConnDo.getConnDo().executeQuery(hql);
		for(HashMap<String, String> tmp : list){
			HashMap<String, String> data = (HashMap<String, String>)tmp;
			UpRecordinfo upRecordinfo= new UpRecordinfo();
			upRecordinfo.setDeviceid(data.get("Deviceid"));
			upRecordinfo.setSoftname(data.get("Softname"));
			upRecordinfo.setSofttype(data.get("Softtype"));
			upRecordinfo.setSoftversion(data.get("Softversion"));
			upRecordinfo.setSoftcurversion(data.get("Softcurversion"));
			upRecordinfo.setFilelen(data.get("Filelen"));
			upRecordinfo.setPublishdate(data.get("Publishdate"));
			upRecordinfo.setUpgradedate(data.get("Upgradedate"));
			upRecordinfo.setOperatetype(data.get("Operatetype"));
			upRecordinfo.setOperatetatus(data.get("Operatetatus"));
			upRecordinfo.setFileid(data.get("Fileid"));
			upRecordinfo.setLock(data.get("Lockfile"));
			upRecordinfo.setIssend(data.get("Issend"));
			upRecordinfos.add(upRecordinfo);
		}
		return upRecordinfos;
	}
	
//	@SuppressWarnings("unchecked")
//	public List<UpRecordinfo> queryOpeStatus(String centerid,String softtype,String softversion){
//		String sql="select * form nvmp.netup_uprecord where Deviceid='"+centerid+"' and Softtype='"+softtype+"' and Softversion='"+softversion+"'";
//		List<UpRecordinfo> uprecords=new ArrayList<UpRecordinfo>();
//		List<?> list = jdbcTemplate.queryForList(sql);
//		for(Object tmp : list){
//			Map<String, Object> data = (Map<String, Object>)tmp;
//			UpRecordinfo uprecord= new UpRecordinfo();
//			uprecord.setFileid((String)data.get("Fileid"));
//			uprecord.setOperatetatus((String)data.get("Operatetatus"));
//			uprecords.add(uprecord);
//		}
//		return uprecords;
//	}
}
