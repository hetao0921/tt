package com.fxdigital.syncclient.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxdigital.syncclient.bean.NvmpCenterinfotab;
import com.fxdigital.syncclient.bean.SyncVersion;
import com.fxdigital.syncclient.dao.VersionDao;



@Service
public class UploadService {
	@Autowired
	VersionDao versionDao;
	
	/**
	 * @return
	 */
	public SyncVersion getLocalVersion(){
		NvmpCenterinfotab centerInfo=versionDao.getLocalCenterInfo();
		 SyncVersion version=versionDao.getVersion();
		 int localVersion=version.getLocalVersion();
		 int serverVersion=version.getServerVersion();
		 if(version==null){
			 version.setFlag(0);
			 version.setLocalVersion(1);
			 version.setServerVersion(0);
			 versionDao.save(version);
			 //发送获取版本信息
		 }
		
		 
		 
		
		return version;
		
	}
	
	public HashMap<String,String> upload(){
		HashMap<String,String> hp=new HashMap<String,String>();
		SyncVersion version=versionDao.getVersion();
		int flag=version.getFlag();
		if(flag==1){
			//正在上传中
			hp.put("code", "0x00");
			hp.put("desc", "正在上传中,请等待");
		}else{
			version.setFlag(1);
			// 获取版本号
			String serverVersion=null;
			
			
			versionDao.update(version);
			hp.put("code", "0x01");
			hp.put("desc", "上传中");
		}
		
		return hp;
	} 
	
	

}
