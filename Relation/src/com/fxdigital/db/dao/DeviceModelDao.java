/**
 * 
 */
package com.fxdigital.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.fxdigital.bean.DeviceModel;
import com.hibernate.bean.NvmpVideodevExtTab;
import com.hibernate.db.ConnDo;

/**
 * @author lizehua
 *
 */
@Component
public class DeviceModelDao {
/*	@Autowired
	private JdbcTemplate jdbcTemplate;*/
	
	public List<DeviceModel> getDeviceModel(String devName,String IP,String modelName,String type){
		String sql="select * from ((select temp.*,1 as type from (select vid.DeviceID deviceid,ext.netlinkmode,vid.Devname,vid.DevIP,vid.CenterID from nvmp_videodev_ext_tab ext right join nvmp_videodevtab vid on ext.deviceid=vid.DeviceID) temp ,nvmp_centerinfotab ncf where temp.CenterID=ncf.CenterID ) union "+

"(select temp.*,2 as type from (select vid.DeviceID deviceid,ext.netlinkmode,vid.DeviceName Devname,vid.DevIP,vid.CenterID from nvmp_videodev_ext_tab  ext right join nvmp_commanddevtab vid on ext.deviceid=vid.DeviceID) temp ,nvmp_centerinfotab ncf where temp.CenterID=ncf.CenterID )) te where 1=1  ";
		if(devName!=null&&!"".equals(devName)){
			sql=sql+" and Devname like '%"+devName.trim()+"%'";
		}
		if(IP!=null&&!"".equals(IP)){
			sql=sql+" and DevIP ='"+IP.trim()+"'";
		}
		if(modelName!=null&&!"".equals(modelName)){
			if("tcp".equals(modelName.trim().toLowerCase())){
				modelName="0";
				sql=sql+" and netlinkmode = '"+modelName+"'";
			}else
			if("udp".equals(modelName.trim().toLowerCase())){
				modelName="1";
				sql=sql+"and netlinkmode = '"+modelName+"'";
			}else
			if("组播".equals(modelName.trim())){
				modelName="2";
				sql=sql+"and netlinkmode = '"+modelName+"'";
			}else
			{
				modelName=null;
				sql=sql+"and netlinkmode  is null ";
			}
			
		}
		if(type!=null&&!"".equals(type)){
			
			if("监控设备".equals(type.trim())){
				type="1";
				sql=sql+" and type= '"+type+"'";
			}else if("指挥端".equals(type.trim()))
			{
				type="2";
				sql=sql+" and type= '"+type+"'";
			}else{
				sql=sql+" and type  is null";
			}
			
		}
		
		 List<DeviceModel> list=new ArrayList<DeviceModel>();
		 List<DeviceModel> relist=new ArrayList<DeviceModel>();
		 list=ConnDo.getConnDo().getDeviceModel(sql);
//jdbcTemplate.query(sql,  new RowMapper(){
//
//			@Override
//			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
//				DeviceModel dev=new DeviceModel();
//				dev.setDeviceId(rs.getString("deviceid"));
//				String model=rs.getString("netlinkmode");
//				if("0".equals(model)){
//					dev.setModelName("TCP");
//				}
//				if("1".equals(model)){
//					dev.setModelName("UDP");
//				}
//				if("2".equals(model)){
//					dev.setModelName("组播");
//				}
//				if(model==null||"".equals(model)){
//					model="-1";
//					dev.setModelName("未设置");
//				}
//				dev.setNetlinkmode(model);
//				dev.setDevName(rs.getString("Devname").trim());
//				dev.setDevIP(rs.getString("DevIP").trim());
//				String type=rs.getString(6);
//				if("1".equals(type)){
//					type="监控设备";
//				}else{
//					type="指挥端";
//				}
//				dev.setType(type);
//				list.add(dev);
//				return null;
//			}});

for (int i = 0; i < list.size(); i++) {
	DeviceModel mdel=list.get(i);
	mdel.setId(Integer.toString(i+1));
	relist.add(mdel);
}
		return relist;
	}
	
/*	public List<Map<String, Object>> getDevice(String flag){
		String sql="";
		if("video".equals(flag)){
			sql="select nvb.DeviceID,nvb.Devname,nvb.DevIP from nvmp_videodevtab nvb,nvmp_centerinfotab ncf where nvb.CenterID=ncf.CenterID";
		}else{
			sql="select nvb.DeviceID,nvb.DeviceName Devname,nvb.DevIP from nvmp_commanddevtab nvb,nvmp_centerinfotab ncf where nvb.CenterID=ncf.CenterID";
		}
		List<Map<String,Object>> list=jdbcTemplate.queryForList(sql);
		return list;
		
	}*/
	
	public void setModel(String deviceid,String model){
		
		NvmpVideodevExtTab net=new NvmpVideodevExtTab();
		net.setDeviceid(deviceid);
		net.setNetlinkmode(Integer.parseInt(model));
		deleteModelBydeviceId(deviceid);
		ConnDo.getConnDo().save(net);
//		String sql="insert into nvmp_videodev_ext_tab(deviceid,netlinkmode) values('"+deviceid+"','"+model+"') ";
//		deleteModelBydeviceId(deviceid);
//		jdbcTemplate.update(sql);
		
	}
	
/*	public Map getModelBydeviceId(String deviceid){
		String sql="select * from nvmp_videodev_ext_tab where deviceid=?";
		return jdbcTemplate.queryForMap(sql, new Object[]{deviceid});
	}*/
	public void deleteModelBydeviceId(String deviceid){
		
//		String sql="delete from nvmp_videodev_ext_tab where deviceid=?";
//		 jdbcTemplate.update(sql, new Object[]{deviceid});
		String sql="delete from NvmpVideodevExtTab where deviceid='"+deviceid+"'";
		ConnDo.getConnDo().executeUpdate(sql);
		
	}
	
	
	

}
