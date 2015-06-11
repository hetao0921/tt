package com.fxdigital.app;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.fxdigital.util.FileUtil;

public class RemoveServer {
	private static final Logger logger = Logger.getLogger(RemoveServer.class);

	static{
		PropertyConfigurator.configure("conf/log4j.properties");
	}
	
	/**
	 * 过滤掉存储服务表
	 */
	public void getMycnf(String newPath,String oldPath){
		FileUtil fileUtil=new FileUtil();
		String mycnfString=fileUtil.readFileByLines(oldPath+"my.cnf");
		String oldChar="replicate-ignore-table=nvmp.nvmp_centerinfotab";
		String storageChar="replicate-ignore-table=nvmp.nvmp_record_storage_info";
		String newChar=getRemoveString();
		logger.info("是否是主备关系："+null!=mycnfString&&mycnfString.contains(oldChar)&&!mycnfString.contains(storageChar));
		System.out.println("是否是主备关系："+null!=mycnfString&&mycnfString.contains(oldChar)&&!mycnfString.contains(storageChar));
		//当前是否存在主备
		if(null!=mycnfString&&mycnfString.contains(oldChar)&&!mycnfString.contains(storageChar)){
			
			//替换掉原来，过滤掉存储的表
			mycnfString=mycnfString.replace(oldChar, newChar);
			//重新写文件
			File file=new File(newPath);
			if(!file.exists()){
				try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					logger.info("创建表失败： "+newPath+e);
				}
			}
			fileUtil.wirteXml(newPath, mycnfString);
			//将本文件来替换原文件
			String cmd = "cp -rf "+newPath+" "+oldPath+"";
			logger.info("开始执行命令："+cmd);
			fileUtil.execStr(cmd);
		}
		
		
		
	}
	
	/**
	 * 得到过滤的字符串
	 * @return
	 */
	public String getRemoveString(){
		StringBuffer sb=new StringBuffer();
		sb.append("replicate-ignore-table=nvmp.nvmp_centerinfotab");
		String newLinePoint=FileUtil.getNewLine();
		sb.append(newLinePoint);
		sb.append("replicate-ignore-table=nvmp.nvmp_record_storage_info");
		sb.append(newLinePoint);
		sb.append("replicate-ignore-table=nvmp.nvmp_record_base");
		sb.append(newLinePoint);
		sb.append("replicate-ignore-table=nvmp.nvmp_record_file");
		sb.append(newLinePoint);
		sb.append("replicate-ignore-table=nvmp.nvmp_record_mark");
		sb.append(newLinePoint);
		sb.append("replicate-ignore-table=nvmp.nvmp_record_mark_parameter");
		return sb.toString();
	}
	
	
	 
	
	public static void main(String[] args) {
		RemoveServer removeServer=new RemoveServer();
		String oldPath="/usr/local/mysql/";
		String newPath="/home/hxht/my.cnf";
		
//		String oldPath="D://";
//		String newPath="E://my.cnf";
		removeServer.getMycnf(newPath,oldPath);
		

	}
	

}
