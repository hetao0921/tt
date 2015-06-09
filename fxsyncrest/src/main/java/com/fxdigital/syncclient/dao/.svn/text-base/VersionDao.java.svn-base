package com.fxdigital.syncclient.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fxdigital.syncclient.bean.NvmpCenterinfotab;
import com.fxdigital.syncclient.bean.SyncVersion;
@Repository
public class VersionDao extends BaseDao{
	
	public NvmpCenterinfotab getLocalCenterInfo(){
		NvmpCenterinfotab centerInfo=new NvmpCenterinfotab();
		List<NvmpCenterinfotab> list= getAllList(NvmpCenterinfotab.class);
		if(list!=null&&list.size()>0){
			centerInfo=list.get(0);
		}
		return centerInfo;
		
	}
	
	public SyncVersion getVersion(){
		SyncVersion version=new SyncVersion();
		List<SyncVersion> list=getAllList(SyncVersion.class);
		if(list!=null&&list.size()>0){
			version=list.get(0);
		}
		return version;
	}
	
}
