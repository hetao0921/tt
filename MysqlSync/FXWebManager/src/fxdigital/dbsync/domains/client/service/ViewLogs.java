package fxdigital.dbsync.domains.client.service;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import fxdigital.dbsync.domains.client.dao.DataOperateRecordDao;

/**
 * @author  het
 *查看日志处理类
 * 2014-7-30
 * SyncWebb
 * fxdigital.dbsync.domains.client.service
 */
public class ViewLogs {
	private static Logger logger = Logger.getLogger(MsgClientService.class);
	public static ViewLogs viewLogs=null;
	public static ViewLogs getInstance(){
		if(null==viewLogs){
			viewLogs=new ViewLogs();
		}
		return viewLogs;
	}
	
	
	/**
	 * 获取客户端日志
	 * 
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getClientLogs(String uuid,String sessionid,String centerid){
		List<HashMap<String, String>> logList;
		try {
			logList = DataOperateRecordDao.getInstance().getAllLogRecord(centerid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("getLoadVersionInfo error"+e);
			e.printStackTrace();
			return null;
			
		}
		return logList;
	}

	
	/**
	 * 获取服务器端同步日志
	 * 
	 *
	 * @return
	 */
	public String[] getSyncServerLogs(){
		String[] str=new String[2];
		return str;
	}

	
	/**
	 * 获取服务器端上传下载日志
	 * 
	 * 
	 * @return
	 */
	public String[] getServerLogs(){
		String[] str=new String[2];
		return str;
	}

}
