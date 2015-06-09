package com.fxdigital.filesync.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.fxdigital.filesync.bean.FileSync;
import com.fxdigital.filesync.dao.FilesyncDao;
import com.fxdigital.filesync.dao.UtilDao;
import com.fxdigital.filesync.util.FileUtil;

public class FilesyncService {
	private static final Logger logger = Logger
			.getLogger(FilesyncService.class);
	List<FileSync> list=FilesyncDao.getInstance().getFileInfo();
	public void insertFile(String path) throws SQLException {
		FileUtil fileUtil = new FileUtil();
		List<String> pathList = fileUtil.scanFolder(path);
		List<FileSync> fileList = fileUtil.getFileInfo(pathList);
		for (int i = 0; i < fileList.size(); i++) {
			FilesyncDao.getInstance().insertFile(fileList.get(i));
		}

	}

	public void getFile(List<FileSync> list) {

		FileUtil fileUtil = new FileUtil();
		for (int i = 0; i < list.size(); i++) {
			try {
				fileUtil.writeFileInfo(list.get(i));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * 如果服务器是主服务器,第一步：初始化数据库
	 * 
	 */
	public void initConf(String path) {
		FilesyncService filesyncService = new FilesyncService();
		try {
			FilesyncDao.getInstance().deleteAllRecord();
			filesyncService.insertFile(path);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 如果服务器是主服务器,第二步：定时调用查看是否有文件更改
	 * 
	 */
	public void scanModifiedFile(String path) {
		File file=new File(path);
		if(!file.exists()){
			logger.info("the path is not exist...");
			return;
		}
		FileUtil fileUtil = new FileUtil();
		List<String> fileList = fileUtil.scanFolder(path);
		
//		for (int i = 0; i < fileList.size(); i++) {
//			String filePath = fileList.get(i);
//			File file = new File(filePath);
//			String fullPath = file.getParent();
//			String fullName = file.getName();
//			Date modifiedTime = fileUtil.getModifiedTime(filePath);
//			
//			logger.info("the current file :" + fullPath + ": " + fullName + ":"
//					+ modifiedTime);
//			FilesyncDao.getInstance().updateMainFlag(fullPath, fullName,
//					new java.sql.Timestamp(modifiedTime.getTime()));
//
//		}
		
		updateMainServer(fileList,list);
	}
	
	
	public void updateMainServer(List<String> fileList, List<FileSync> list) {
		FileUtil fileUtil = new FileUtil();
		for (int i = 0; i < fileList.size(); i++) {
			String filePath = fileList.get(i);
			File file = new File(filePath);
			String fullPath = file.getParent();
			String fullName = file.getName();
			Date modifiedTime = fileUtil.getModifiedTime(filePath);
			java.sql.Timestamp lasTtime=new java.sql.Timestamp(modifiedTime.getTime());
			logger.info("the current file :" + fullPath + ": " + fullName + ":"
					+ lasTtime);
			int index=fileUtil.getIndex(fullPath,fullName,list);
			if(index!=-1){
				//the file modified
				logger.info("the file modified..."+fullPath+"/"+fullName);
				if(!lasTtime.equals(list.get(index).getModifiedTime())){
					FilesyncDao.getInstance().updateMainFlag(fullPath,fullName,lasTtime);
				}
			}
			//the file added
			else{
				logger.info("the file added..."+fullPath+"/"+fullName);
				FileSync fileSync=new FileSync();
				fileSync.setModifiedTime(lasTtime);
				fileSync.setFileName(fullName);
				fileSync.setFilePath(fullPath);
				FileInputStream inS = null;
				File tempFile=new File(fullPath+"/"+fullName);
				try {
					inS = new FileInputStream(tempFile);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.info("the file added..."+e);
				}
				fileSync.setFile(inS);
				fileSync.setStrLength(tempFile.length());
				try {
					FilesyncDao.getInstance().insertFile(fileSync);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.info("the file added insert..."+e);
				}
			}
		}
	}
	
	public List<FileSync> getFilesyncInfo(){
		List<FileSync> list=FilesyncDao.getInstance().getFileInfo();
		return list;
		
	}
	


	/**
	 * 如果服务器是从服务器,第一步：定时调用查看数据库是否有数据更新
	 * 
	 */

	public void scanModifiedDB() {
		List<FileSync> fileSyncList = FilesyncDao.getInstance()
				.queryMainFileModified();

		FileUtil fileUtil = new FileUtil();
		for (int i = 0; i < fileSyncList.size(); i++) {
			try {
				// 写文件到本地
				fileUtil.writeFileInfo(fileSyncList.get(i));
				// 修改数据库backupflag
				FilesyncDao.getInstance().updateBackupFlag(fileSyncList.get(i));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.info("the backupserver write local file...");
			}
		}

	}



	
	public void startServer(String path){
		File file=new File(path);
		if(!file.exists()){
			logger.info("the path is not exist...");
			return;
		}
		//服务器是主服务器
		if(UtilDao.getInstance().isMainServer()==1){
			logger.info("the current server is main server...");
			scanModifiedFile(path);
		}//服务器是从服务器
		else if(UtilDao.getInstance().isMainServer()==0){
			logger.info("the current server is backup server...");
			scanModifiedDB();
		}else{
			logger.info("the current server is new server...,and do nothing.");
		}
	}

	public static void main(String[] args) throws SQLException {
		FilesyncService filesyncService = new FilesyncService();

		String path = "/etc/fxconf";
		// filesyncService.insertFile(path);

		List<FileSync> list = FilesyncDao.getInstance().getFileInfo();
		filesyncService.getFile(list);
	}
}
