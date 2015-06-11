package fxdigital.db;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import fxdigital.db.dao.LocalCenterDao;
import fxdigital.db.dao.LocalMqInfoDao;
import fxdigital.db.dao.SyncServersDao;


/**
 * 操作数据库
 * 
 * @author fucz
 * @version 2014-6-12
 */

public class DbManager {
	private static Logger logger = Logger.getLogger(DbManager.class);
//	private static final Logger log = Logger.getLogger(DbManager.class);
	LocalCenterDao localCenterDao=new LocalCenterDao();
	SyncServersDao syncServersDao=new SyncServersDao();
	LocalMqInfoDao localMqInfoDao=new LocalMqInfoDao();
	
	/**
	 * 获得本级服务器信息
	 * @return
	 */
	public LocalCenter getLocalCenter(){
		
		LocalCenter local = null;
		try{
			local = localCenterDao.query();
		}catch(Exception e){
			//Log4jUtil.error(this.getClass(),"获得本级服务器信息失败！",e);
			logger.info("获得本级服务器信息失败！"+e);
		}
		return local;
	}
	

	/**
	 * 获得对应ID的同步服务器
	 * @return
	 */
	public SyncServer getSyncServer(String id){
		
		SyncServer server = new SyncServer();
		try {
			server = syncServersDao.query(id);
			logger.info("server"+server);
		} catch (Exception e) {
			//Log4jUtil.error(this.getClass(),"获得对应ID的同步服务器失败！",e);
			logger.info("获得对应ID的同步服务器失败！"+e);
		}
		return server;
	}
	
	
	
	/**
	 * 获得本地MQ对应的IP
	 * @return
	 */
	public String getMQServerIP(String centerid){
		
		String IP = null;
		try {
			IP = localMqInfoDao.getMQServerIP(centerid);
		} catch (Exception e) {
			//Log4jUtil.error(this.getClass(),"获得对应ID的同步服务器失败！",e);
			logger.info("获得对应ID的同步服务器失败！"+e);
		}
		return IP;
	}
	
	
	
	
	
	/**
	 * 获得本级MQ信息
	 * @return
	 */
	public List<LocalMqInfo> getLocalMqInfo(){
		
		List<LocalMqInfo> centerMqServerInfos =null;
		try{
			centerMqServerInfos = localMqInfoDao.query();
		}catch(Exception e){
			//Log4jUtil.error(this.getClass(),"获得本级MQ信息失败！",e);
			logger.info("获得本级MQ信息失败！"+e);
		}
		return centerMqServerInfos ;
	}
	
	
	/**
	 * 获得本级MQ信息
	 * @return
	 */
	public void insertLocalMqInfo(String centerid, String ip, int port){
		
		
		try{
			localMqInfoDao.insert(centerid, ip, port);
		}catch(Exception e){
			logger.info("插入数据失败！"+e);
			//Log4jUtil.error(this.getClass(),"插入数据失败！",e);
		}
		
	}
	
	/**
	 * 是否存在本级MQ信息
	 * @return
	 */
	public boolean isExist(String centerID) {
		boolean flag=localMqInfoDao.isExist(centerID);
		return flag;
	}
	
}
