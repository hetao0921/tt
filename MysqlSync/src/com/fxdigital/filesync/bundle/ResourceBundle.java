package com.fxdigital.filesync.bundle;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.fxdigital.filesync.bean.FileSyncInfoBean;

/**
 * @author Administrator 配置文件读取类
 * 
 */
public class ResourceBundle {
	private static final Logger logger = Logger.getLogger(ResourceBundle.class);



	public static FileSyncInfoBean getFileSyncInfo() {
		Properties prop = new Properties();
		FileSyncInfoBean fileSyncInfoBean=new FileSyncInfoBean();
		try {
			
			prop.load(new FileInputStream("resources/filesyncinfo.properties"));
			fileSyncInfoBean.setMainserverpath(prop.getProperty("mainserver.path"));
			fileSyncInfoBean.setBackupserverpath(prop.getProperty("backupserver.path"));
			fileSyncInfoBean.setFilesyncdelay(Integer.valueOf(prop.getProperty("filesync.delay")));
			fileSyncInfoBean.setFilesyncinterval(Integer.valueOf(prop.getProperty("filesync.interval")));
			logger.info(prop.getProperty("mainserver.path"));
			logger.info(prop.getProperty("backupserver.path"));
			logger.info(prop.getProperty("filesync.delay"));
			logger.info(prop.getProperty("filesync.interval"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileSyncInfoBean;

	}

	public static void main(String[] args) {
		ResourceBundle.getFileSyncInfo();
	}

}
