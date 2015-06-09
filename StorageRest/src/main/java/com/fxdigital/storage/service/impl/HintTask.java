package com.fxdigital.storage.service.impl;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxdigital.storage.app.impl.AppServer;
import com.fxdigital.syncclient.dao.NvmpRecordFileDao;
import com.fxdigital.syncclient.util.VideoConverter;

@Service
public class HintTask implements Runnable{
	private static final Logger logger = Logger.getLogger(HintTask.class);

	@Autowired
	private NvmpRecordFileDao nvmpRecordFileDao;
	
	private static ConcurrentMap<Integer,String> hintMap=new ConcurrentHashMap<Integer,String>();
	
	private String filePath;
	private int fileID;

	private AppServer appServer;
	
	/**
	 * 初始化任务列表
	 * 将需要操作的任务加入列表中
	 * @param filePath
	 * @param fileID
	 */
	public void init(String filePath,int fileID){
		this.filePath=filePath;
		this.fileID=fileID;
		hintMap.put(fileID, filePath);
	}
	

	public void regApp(AppServer appServer){
		this.appServer=appServer;	
	}
	
	/**
	 * 统一接口，在应用层开始任务
	 * @param appServer
	 */
	public AppServer getAppServer(){
		return appServer;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//进行文件转换
		Set set = hintMap.entrySet() ;
		java.util.Iterator it = hintMap.entrySet().iterator();
		while(it.hasNext()){
		java.util.Map.Entry entry = (java.util.Map.Entry)it.next();
		int fileIDStr=Integer.valueOf(entry.getKey().toString());
		String filePathStr=String.valueOf(entry.getValue());
		logger.info("转换文件名称:"+filePathStr+"文件ID :"+fileIDStr);
		String convertResult=VideoConverter.Convert(filePathStr);
		logger.info("转换文件返回信息:"+convertResult);
		//修改数据库转换标志位0-1
		nvmpRecordFileDao.updateHintFlag(fileIDStr);
		hintMap.remove(entry.getKey());
		} 

	}

}
