package com.fxdigital.analysis.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.fxdigital.analysis.bean.Center;
import com.fxdigital.analysis.bean.Resources;
import com.fxdigital.analysis.bean.ResourcesAttribute;

@Repository
public class GateResourceDao extends BaseDao{
	private static Log logger=LogFactory.getLog(GateResourceDao.class);
	
	public List<Resources> queryResources(){
		logger.info("come into GateResourceDao function!");
		List<Resources> resources=new ArrayList<Resources>();
		//查询部门资源
		String sql="select DepartID,DepartName,DepartParentID,CenterID from nvmp_departmenttab";
		List<Object[]> listDepartments=querySql(sql);
		for (Object[] oResources : listDepartments) {
			Resources rcs=new Resources();
			rcs.setResourceId(oResources[0]==null?"":oResources[0].toString());
			rcs.setResourceName(oResources[1]==null?"":oResources[1].toString());
			rcs.setResourceType("Department");
			rcs.setDepartId(oResources[2]==null?"":oResources[2].toString());
			rcs.setCenterId(oResources[3]==null?"":oResources[3].toString());
			rcs.setResourceAttributes(new ArrayList<ResourcesAttribute>());
			resources.add(rcs);
		}
		
		//查询人员资源
		sql="select UserID,UserName,DepartID,CenterID,UserNickName,OPLevel,RoleID from nvmp_userinfotab";
		List<Object[]> listUsers=querySql(sql);
		for (Object[] oUsers : listUsers) {
			if(!(oUsers[0]==null?"":oUsers[0].toString()).equals("0000")){
				Resources rcs=new Resources();
				rcs.setResourceId(oUsers[0]==null?"":oUsers[0].toString());
				rcs.setResourceName(oUsers[1]==null?"":oUsers[1].toString());
				rcs.setResourceType("Commander");
				rcs.setDepartId(oUsers[2]==null?"":oUsers[2].toString());
				rcs.setCenterId(oUsers[3]==null?"":oUsers[3].toString());
				
				List<ResourcesAttribute> listrsas=new ArrayList<ResourcesAttribute>();
				ResourcesAttribute rsas=new ResourcesAttribute();
				rsas.setAttributeType("UserNickName");
				rsas.setAttributeIndex(1);
				rsas.setAttributeValue(oUsers[4]==null?"":oUsers[4].toString());
				listrsas.add(rsas);
				rsas=new ResourcesAttribute();
				rsas.setAttributeType("OPLevel");
				rsas.setAttributeIndex(1);
				rsas.setAttributeValue(oUsers[5]==null?"":oUsers[5].toString());
				listrsas.add(rsas);
				rsas=new ResourcesAttribute();
				rsas.setAttributeType("RoleID");
				rsas.setAttributeIndex(1);
				rsas.setAttributeValue(oUsers[6]==null?"":oUsers[6].toString());
				listrsas.add(rsas);
				
				rcs.setResourceAttributes(listrsas);
				resources.add(rcs);
			}
		}
		
		//查询摄像机资源
		sql="select a.DeviceID,a.CHName,b.AreaID,b.CenterID,b.Devname,b.DevIP,b.DevVer,a.DevCH from nvmp_videodevchtab as a  left join nvmp_videodevtab as b on a.DeviceID=b.DeviceID where a.CHType=3";
		List<Object[]> listCamera=querySql(sql);
		for (Object[] oCameras : listCamera) {
				Resources rcs=new Resources();
				rcs.setResourceId(oCameras[0]==null?"":oCameras[0].toString());
				rcs.setResourceName(oCameras[1]==null?"":oCameras[1].toString());
				rcs.setResourceType("Camera");
				rcs.setDepartId(oCameras[2]==null?"":oCameras[2].toString());
				rcs.setCenterId(oCameras[3]==null?"":oCameras[3].toString());
				
				List<ResourcesAttribute> listrsas=new ArrayList<ResourcesAttribute>();
				ResourcesAttribute rsas=new ResourcesAttribute();
				rsas.setAttributeType("DeviceID");
				rsas.setAttributeIndex(1);
				rsas.setAttributeValue(oCameras[0]==null?"":oCameras[0].toString());
				listrsas.add(rsas);
				rsas=new ResourcesAttribute();
				rsas.setAttributeType("Devname");
				rsas.setAttributeIndex(1);
				rsas.setAttributeValue(oCameras[4]==null?"":oCameras[4].toString());
				listrsas.add(rsas);
				rsas=new ResourcesAttribute();
				rsas.setAttributeType("DevIP");
				rsas.setAttributeIndex(1);
				rsas.setAttributeValue(oCameras[5]==null?"":oCameras[5].toString());
				listrsas.add(rsas);
				rsas=new ResourcesAttribute();
				rsas.setAttributeType("DevVer");
				rsas.setAttributeIndex(1);
				rsas.setAttributeValue(oCameras[6]==null?"":oCameras[6].toString());
				listrsas.add(rsas);
				rsas=new ResourcesAttribute();
				rsas.setAttributeType("DevCH");
				rsas.setAttributeIndex(1);
				rsas.setAttributeValue(oCameras[7]==null?"":oCameras[7].toString());
				listrsas.add(rsas);
				rcs.setResourceAttributes(listrsas);
				resources.add(rcs);
		}
		return resources;
	}
	//根据中心查找所有资源
	public List<Resources> queryResbyCenterid(String[] centerids){
		logger.info("come into queryResbyCenterid function!");
		List<Resources> resources=new ArrayList<Resources>();
		//查询部门资源
		  String str="(";
		  for (int i = 0; i < centerids.length-1; i++) {
		   str+="b.CenterID='"+centerids[i]+"' or ";
		  }
		  str+="b.CenterID='"+centerids[centerids.length-1]+"')";
		String sql="select b.DepartID,b.DepartName,b.DepartParentID,b.CenterID from nvmp_departmenttab as b where "+str;
		logger.info("queryResbyCenterid departmentd sql:"+sql);
		List<Object[]> listDepartments=querySql(sql);
		for (Object[] oResources : listDepartments) {
			Resources rcs=new Resources();
			rcs.setResourceId(oResources[0]==null?"":oResources[0].toString());
			rcs.setResourceName(oResources[1]==null?"":oResources[1].toString());
			rcs.setResourceType("Department");
			rcs.setDepartId(oResources[2]==null?"":oResources[2].toString());
			rcs.setCenterId(oResources[3]==null?"":oResources[3].toString());
			rcs.setResourceAttributes(new ArrayList<ResourcesAttribute>());
			resources.add(rcs);
		}
		
		//查询人员资源
		sql="select b.UserID,b.UserName,b.DepartID,b.CenterID,b.UserNickName,b.OPLevel,b.RoleID from nvmp_userinfotab as b where "+str;
		logger.info("queryResbyCenterid user sql:"+sql);
		List<Object[]> listUsers=querySql(sql);
		for (Object[] oUsers : listUsers) {
			if(!(oUsers[0]==null?"":oUsers[0].toString()).equals("0000")){
				Resources rcs=new Resources();
				rcs.setResourceId(oUsers[0]==null?"":oUsers[0].toString());
				rcs.setResourceName(oUsers[1]==null?"":oUsers[1].toString());
				rcs.setResourceType("Commander");
				rcs.setDepartId(oUsers[2]==null?"":oUsers[2].toString());
				rcs.setCenterId(oUsers[3]==null?"":oUsers[3].toString());
				
				List<ResourcesAttribute> listrsas=new ArrayList<ResourcesAttribute>();
				ResourcesAttribute rsas=new ResourcesAttribute();
				rsas.setAttributeType("UserNickName");
				rsas.setAttributeIndex(1);
				rsas.setAttributeValue(oUsers[4]==null?"":oUsers[4].toString());
				listrsas.add(rsas);
				rsas=new ResourcesAttribute();
				rsas.setAttributeType("OPLevel");
				rsas.setAttributeIndex(1);
				rsas.setAttributeValue(oUsers[5]==null?"":oUsers[5].toString());
				listrsas.add(rsas);
				rsas=new ResourcesAttribute();
				rsas.setAttributeType("RoleID");
				rsas.setAttributeIndex(1);
				rsas.setAttributeValue(oUsers[6]==null?"":oUsers[6].toString());
				listrsas.add(rsas);
				
				rcs.setResourceAttributes(listrsas);
				resources.add(rcs);
			}
		}
		
		//查询摄像机资源
		sql="select a.DeviceID,a.CHName,b.AreaID,b.CenterID,b.Devname,b.DevIP,b.DevVer,a.DevCH from nvmp_videodevchtab as a  left join nvmp_videodevtab as b on a.DeviceID=b.DeviceID where a.CHType=3 and "+str;
		List<Object[]> listCamera=querySql(sql);
		for (Object[] oCameras : listCamera) {
				Resources rcs=new Resources();
				rcs.setResourceId(oCameras[0]==null?"":oCameras[0].toString());
				rcs.setResourceName(oCameras[1]==null?"":oCameras[1].toString());
				rcs.setResourceType("Camera");
				rcs.setDepartId(oCameras[2]==null?"":oCameras[2].toString());
				rcs.setCenterId(oCameras[3]==null?"":oCameras[3].toString());
				
				List<ResourcesAttribute> listrsas=new ArrayList<ResourcesAttribute>();
				ResourcesAttribute rsas=new ResourcesAttribute();
				rsas.setAttributeType("DeviceID");
				rsas.setAttributeIndex(1);
				rsas.setAttributeValue(oCameras[0]==null?"":oCameras[0].toString());
				listrsas.add(rsas);
				rsas=new ResourcesAttribute();
				rsas.setAttributeType("Devname");
				rsas.setAttributeIndex(1);
				rsas.setAttributeValue(oCameras[4]==null?"":oCameras[4].toString());
				listrsas.add(rsas);
				rsas=new ResourcesAttribute();
				rsas.setAttributeType("DevIP");
				rsas.setAttributeIndex(1);
				rsas.setAttributeValue(oCameras[5]==null?"":oCameras[5].toString());
				listrsas.add(rsas);
				rsas=new ResourcesAttribute();
				rsas.setAttributeType("DevVer");
				rsas.setAttributeIndex(1);
				rsas.setAttributeValue(oCameras[6]==null?"":oCameras[6].toString());
				listrsas.add(rsas);
				rsas=new ResourcesAttribute();
				rsas.setAttributeType("DevCH");
				rsas.setAttributeIndex(1);
				rsas.setAttributeValue(oCameras[7]==null?"":oCameras[7].toString());
				listrsas.add(rsas);
				rcs.setResourceAttributes(listrsas);
				resources.add(rcs);
		}
		return resources;

	}
	//根据类型查所有资源
	public List<Resources> queryResbyResType(String[] resourcetypes){
		logger.info("come into queryResbyResType function!resourcetype="+resourcetypes[0]);
		List<Resources> resources=new ArrayList<Resources>();
		String sql=null;
		for(int i=0;i<resourcetypes.length;i++){
			logger.info("resourcetypes:"+resourcetypes[i]);
			if("Department".equals(resourcetypes[i])){
				sql="select DepartID,DepartName,DepartParentID,CenterID from nvmp_departmenttab";
				List<Object[]> listDepartments=querySql(sql);
				for (Object[] oResources : listDepartments) {
					Resources rcs=new Resources();
					rcs.setResourceId(oResources[0]==null?"":oResources[0].toString());
					rcs.setResourceName(oResources[1]==null?"":oResources[1].toString());
					rcs.setResourceType("Department");
					rcs.setDepartId(oResources[2]==null?"":oResources[2].toString());
					rcs.setCenterId(oResources[3]==null?"":oResources[3].toString());
					rcs.setResourceAttributes(new ArrayList<ResourcesAttribute>());
					resources.add(rcs);
				}
			}else if("Commander".equals(resourcetypes[i])){
				sql="select UserID,UserName,DepartID,CenterID,UserNickName,OPLevel,RoleID from nvmp_userinfotab";
				List<Object[]> listUsers=querySql(sql);
				for (Object[] oUsers : listUsers) {
					if(!(oUsers[0]==null?"":oUsers[0].toString()).equals("0000")){
						Resources rcs=new Resources();
						rcs.setResourceId(oUsers[0]==null?"":oUsers[0].toString());
						rcs.setResourceName(oUsers[1]==null?"":oUsers[1].toString());
						rcs.setResourceType("Commander");
						rcs.setDepartId(oUsers[2]==null?"":oUsers[2].toString());
						rcs.setCenterId(oUsers[3]==null?"":oUsers[3].toString());
						
						List<ResourcesAttribute> listrsas=new ArrayList<ResourcesAttribute>();
						ResourcesAttribute rsas=new ResourcesAttribute();
						rsas.setAttributeType("UserNickName");
						rsas.setAttributeIndex(1);
						rsas.setAttributeValue(oUsers[4]==null?"":oUsers[4].toString());
						listrsas.add(rsas);
						rsas=new ResourcesAttribute();
						rsas.setAttributeType("OPLevel");
						rsas.setAttributeIndex(1);
						rsas.setAttributeValue(oUsers[5]==null?"":oUsers[5].toString());
						listrsas.add(rsas);
						rsas=new ResourcesAttribute();
						rsas.setAttributeType("RoleID");
						rsas.setAttributeIndex(1);
						rsas.setAttributeValue(oUsers[6]==null?"":oUsers[6].toString());
						listrsas.add(rsas);
						
						rcs.setResourceAttributes(listrsas);
						resources.add(rcs);
					}
				}
			}else if("Camera".equals(resourcetypes[i])){
				sql="select a.DeviceID,a.CHName,b.AreaID,b.CenterID,b.Devname,b.DevIP,b.DevVer,a.DevCH from nvmp_videodevchtab as a  left join nvmp_videodevtab as b on a.DeviceID=b.DeviceID where a.CHType=3";
				List<Object[]> listCamera=querySql(sql);
				for (Object[] oCameras : listCamera) {
						Resources rcs=new Resources();
						rcs.setResourceId(oCameras[0]==null?"":oCameras[0].toString());
						rcs.setResourceName(oCameras[1]==null?"":oCameras[1].toString());
						rcs.setResourceType("Camera");
						rcs.setDepartId(oCameras[2]==null?"":oCameras[2].toString());
						rcs.setCenterId(oCameras[3]==null?"":oCameras[3].toString());
						
						List<ResourcesAttribute> listrsas=new ArrayList<ResourcesAttribute>();
						ResourcesAttribute rsas=new ResourcesAttribute();
						rsas.setAttributeType("DeviceID");
						rsas.setAttributeIndex(1);
						rsas.setAttributeValue(oCameras[0]==null?"":oCameras[0].toString());
						listrsas.add(rsas);
						rsas=new ResourcesAttribute();
						rsas.setAttributeType("Devname");
						rsas.setAttributeIndex(1);
						rsas.setAttributeValue(oCameras[4]==null?"":oCameras[4].toString());
						listrsas.add(rsas);
						rsas=new ResourcesAttribute();
						rsas.setAttributeType("DevIP");
						rsas.setAttributeIndex(1);
						rsas.setAttributeValue(oCameras[5]==null?"":oCameras[5].toString());
						listrsas.add(rsas);
						rsas=new ResourcesAttribute();
						rsas.setAttributeType("DevVer");
						rsas.setAttributeIndex(1);
						rsas.setAttributeValue(oCameras[6]==null?"":oCameras[6].toString());
						listrsas.add(rsas);
						rsas=new ResourcesAttribute();
						rsas.setAttributeType("DevCH");
						rsas.setAttributeIndex(1);
						rsas.setAttributeValue(oCameras[7]==null?"":oCameras[7].toString());
						listrsas.add(rsas);
						rcs.setResourceAttributes(listrsas);
						resources.add(rcs);
				}
			}
		}
		return resources;
	}
	//根据中心和类型查特定资源
	public List<Resources> queryResbyAll(String[] centerids,String[] resourcetypes){
		logger.info("come into queryResbyAll function!resourcetype="+resourcetypes[0]);
		List<Resources> resources=new ArrayList<Resources>();
		String sql=null;
		  String str="(";
		  for (int i = 0; i < centerids.length-1; i++) {
		   str+="b.CenterID='"+centerids[i]+"' or ";
		  }
		  str+="b.CenterID='"+centerids[centerids.length-1]+"')";
		  for(int j=0;j<resourcetypes.length;j++){
			  if("Department".equals(resourcetypes[j])){
					sql="select b.DepartID,b.DepartName,b.DepartParentID,b.CenterID from nvmp_departmenttab as b where "+str;
					List<Object[]> listDepartments=querySql(sql);
					for (Object[] oResources : listDepartments) {
						Resources rcs=new Resources();
						rcs.setResourceId(oResources[0]==null?"":oResources[0].toString());
						rcs.setResourceName(oResources[1]==null?"":oResources[1].toString());
						rcs.setResourceType("Department");
						rcs.setDepartId(oResources[2]==null?"":oResources[2].toString());
						rcs.setCenterId(oResources[3]==null?"":oResources[3].toString());
						rcs.setResourceAttributes(new ArrayList<ResourcesAttribute>());
						resources.add(rcs);
					}
				}else if("Commander".equals(resourcetypes[j])){
					sql="select b.UserID,b.UserName,b.DepartID,b.CenterID,b.UserNickName,b.OPLevel,b.RoleID from nvmp_userinfotab as b where "+str;
					List<Object[]> listUsers=querySql(sql);
					for (Object[] oUsers : listUsers) {
						if(!(oUsers[0]==null?"":oUsers[0].toString()).equals("0000")){
							Resources rcs=new Resources();
							rcs.setResourceId(oUsers[0]==null?"":oUsers[0].toString());
							rcs.setResourceName(oUsers[1]==null?"":oUsers[1].toString());
							rcs.setResourceType("Commander");
							rcs.setDepartId(oUsers[2]==null?"":oUsers[2].toString());
							rcs.setCenterId(oUsers[3]==null?"":oUsers[3].toString());
							
							List<ResourcesAttribute> listrsas=new ArrayList<ResourcesAttribute>();
							ResourcesAttribute rsas=new ResourcesAttribute();
							rsas.setAttributeType("UserNickName");
							rsas.setAttributeIndex(1);
							rsas.setAttributeValue(oUsers[4]==null?"":oUsers[4].toString());
							listrsas.add(rsas);
							rsas=new ResourcesAttribute();
							rsas.setAttributeType("OPLevel");
							rsas.setAttributeIndex(1);
							rsas.setAttributeValue(oUsers[5]==null?"":oUsers[5].toString());
							listrsas.add(rsas);
							rsas=new ResourcesAttribute();
							rsas.setAttributeType("RoleID");
							rsas.setAttributeIndex(1);
							rsas.setAttributeValue(oUsers[6]==null?"":oUsers[6].toString());
							listrsas.add(rsas);
							
							rcs.setResourceAttributes(listrsas);
							resources.add(rcs);
						}
					}
				}else if("Camera".equals(resourcetypes[j])){
					sql="select a.DeviceID,a.CHName,b.AreaID,b.CenterID,b.Devname,b.DevIP,b.DevVer,a.DevCH from nvmp_videodevchtab as a  left join nvmp_videodevtab as b on a.DeviceID=b.DeviceID where a.CHType=3 and "+str;
					List<Object[]> listCamera=querySql(sql);
					for (Object[] oCameras : listCamera) {
							Resources rcs=new Resources();
							rcs.setResourceId(oCameras[0]==null?"":oCameras[0].toString());
							rcs.setResourceName(oCameras[1]==null?"":oCameras[1].toString());
							rcs.setResourceType("Camera");
							rcs.setDepartId(oCameras[2]==null?"":oCameras[2].toString());
							rcs.setCenterId(oCameras[3]==null?"":oCameras[3].toString());
							
							List<ResourcesAttribute> listrsas=new ArrayList<ResourcesAttribute>();
							ResourcesAttribute rsas=new ResourcesAttribute();
							rsas.setAttributeType("DeviceID");
							rsas.setAttributeIndex(1);
							rsas.setAttributeValue(oCameras[0]==null?"":oCameras[0].toString());
							listrsas.add(rsas);
							rsas=new ResourcesAttribute();
							rsas.setAttributeType("Devname");
							rsas.setAttributeIndex(1);
							rsas.setAttributeValue(oCameras[4]==null?"":oCameras[4].toString());
							listrsas.add(rsas);
							rsas=new ResourcesAttribute();
							rsas.setAttributeType("DevIP");
							rsas.setAttributeIndex(1);
							rsas.setAttributeValue(oCameras[5]==null?"":oCameras[5].toString());
							listrsas.add(rsas);
							rsas=new ResourcesAttribute();
							rsas.setAttributeType("DevVer");
							rsas.setAttributeIndex(1);
							rsas.setAttributeValue(oCameras[6]==null?"":oCameras[6].toString());
							listrsas.add(rsas);
							rsas=new ResourcesAttribute();
							rsas.setAttributeType("DevCH");
							rsas.setAttributeIndex(1);
							rsas.setAttributeValue(oCameras[7]==null?"":oCameras[7].toString());
							listrsas.add(rsas);
							rcs.setResourceAttributes(listrsas);
							resources.add(rcs);
					}
				}
		}
		return resources;
	}
	//检查中心级联表信息
	public List<Center> queryCenterNet(){
		String sql="select a.CenterID,b.CenterName,b.CenterIP,a.NetWorkNodeID from nvmp_centernetworkinfotab as a left join nvmp_centerinfosynctab as b on a.CenterID=b.CenterID where a.IsParentFlag=1;";
		logger.info("come into queryCenterNet function!");
		List<Center> centers=new ArrayList<Center>();
		List<Object[]> listCenters=querySql(sql);
		for (Object[] oCenters : listCenters) {
			Center center=new Center();
			center.setCenterId(oCenters[0]==null?"":oCenters[0].toString());
			center.setCenterName(oCenters[1]==null?"":oCenters[1].toString());
			center.setCenterIp(oCenters[2]==null?"":oCenters[2].toString());
			center.setParentId(oCenters[3]==null?"":oCenters[3].toString());
			centers.add(center);
		}
		return centers;
	}
	//获取本级中心级联表信息
	public Center querySefCenterNet(){
		String sql="select b.CenterID,b.CenterName,b.CenterIP,a.NetWorkNodeID from nvmp_centerinfotab as b left join nvmp_centernetworkinfotab as a on a.CenterID=b.CenterID where a.IsParentFlag=1;";
		logger.info("come into queryCenterNet function!");
		Center center=new Center();
		List<Object[]> listCenters=querySql(sql);
		if(listCenters.size()>0){
			Object[] oCenters=listCenters.get(0);
			center.setCenterId(oCenters[0]==null?"":oCenters[0].toString());
			center.setCenterName(oCenters[1]==null?"":oCenters[1].toString());
			center.setCenterIp(oCenters[2]==null?"":oCenters[2].toString());
			center.setParentId(oCenters[3]==null?"":oCenters[3].toString());
		}else{
			sql="select CenterID,CenterName,CenterIP from nvmp_centerinfotab";
			listCenters=querySql(sql);
			for (Object[] oselfs : listCenters) {
				center.setCenterId(oselfs[0]==null?"":oselfs[0].toString());
				center.setCenterName(oselfs[1]==null?"":oselfs[1].toString());
				center.setCenterIp(oselfs[2]==null?"":oselfs[2].toString());
				center.setParentId("null");
			}
		}
		return center;
	}
}
