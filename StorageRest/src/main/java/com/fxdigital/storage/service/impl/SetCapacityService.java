package com.fxdigital.storage.service.impl;

import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxdigital.syncclient.bean.NvmpRecordCapacityInfo;
import com.fxdigital.syncclient.bean.NvmpRecordFile;
import com.fxdigital.syncclient.dao.NvmpRecordFileDao;
import com.fxdigital.syncclient.util.BundlerProperties;
import com.fxdigital.syncclient.util.FileUtil;
import com.fxdigital.syncclient.util.VideoConverter;

@Service
public class SetCapacityService {
	private static final Logger logger = Logger.getLogger(SetCapacityService.class);


	
	@Autowired
	private NvmpRecordFileDao nvmpRecordFileDao;
	

	
	/**
	 * 通过配置文件获取容量,单位MB
	 * 当前最小为900MB
	 * @return
	 */
	public int getCurrentFileCapacity(){
		BundlerProperties bundlerProperties=new BundlerProperties();
		Properties pps= bundlerProperties.getPropertie("ontime.properties");
		NvmpRecordCapacityInfo nvmpRecordCapacityInfo=new NvmpRecordCapacityInfo();
		nvmpRecordCapacityInfo.setVideorate(Integer.valueOf(null==pps.get("videofile.coderate")?"4":pps.get("videofile.coderate").toString()));
		nvmpRecordCapacityInfo.setVideotime(Integer.valueOf(null==pps.get("videofile.duraltime")?"1800":pps.get("videofile.duraltime").toString()));
		nvmpRecordCapacityInfo.setVideochannel(Integer.valueOf(null==pps.get("videofile.channelcount")?"10":pps.get("videofile.channelcount").toString()));
		int capacity=nvmpRecordCapacityInfo.getVideorate()*nvmpRecordCapacityInfo.getVideotime()/8;
	    return capacity; 
		
	}
	
	/**
	 * 是否有足够的空间录像
	 * @return
	 */
	public boolean isEnough(){
		String filePath = "/usr/local/movies/storage/";
		boolean flag=false;
		int currentVideoCapacity=getCurrentFileCapacity();
		FileUtil fileUtil=new FileUtil();
		int currentDiskResidualCapacity=fileUtil.getIntegerVolumn(filePath);
		if(currentDiskResidualCapacity>=currentVideoCapacity){
			flag=true;
		}
		logger.info("当前录像的容量: 单位(M)"+String.valueOf(currentVideoCapacity));
		logger.info("当前磁盘的剩余容量 ： 单位(M)"+String.valueOf(currentDiskResidualCapacity));
		logger.info("比较结果，当前空间是否够用："+flag);
		return flag;
	}
	
	/**
	 * 获取最大的存储空间
	 * @return
	 */
	public int getEnoughCapacity(){
        boolean flag=isEnough();
        //如果没有足够的空间，则根据数据库时间最长来删除磁盘上的文件
        if(!flag){
        	List<Object> fileList=nvmpRecordFileDao.queryLastObject();
        	for(Object obj:fileList){
        		NvmpRecordFile nvmpRecordFile=(NvmpRecordFile)obj;
        		if(nvmpRecordFile.getNvmpFileHint().equals(true)&&null!=nvmpRecordFile.getNvmpEndTime()){
        			String filePath=nvmpRecordFile.getNvmpFileName();
        			int fileId=nvmpRecordFile.getNvmpFileId();
        			String result=VideoConverter.execStr("rm -rf "+filePath);
        			logger.info("删除文件"+filePath+"返回消息: "+result);
        			int backup=nvmpRecordFileDao.deleteRecordFile(fileId);
                    logger.info("删除文件ID"+fileId+"返回消息: "+backup);
        			boolean tempFlag=isEnough();
        			if(!tempFlag){
        				continue;
        			}else{
        				return 1;
        			}
        		}
        	}
        }
		return 1;
	}
	
	

	
	public static void main(String[] args) {
		SetCapacityService setCapacityService=new SetCapacityService();
		setCapacityService.getEnoughCapacity();
	}

}
