package fxdigital.dbsync.domains.client.service;

import java.util.HashMap;

import fxdigital.dbsync.domains.client.dao.DataOperateDao;


/**
 * @author  het
 *本地锁操作实现类
 * 2014-7-30
 * SyncWebb
 * fxdigital.dbsync.domains.client.service
 */
public class IUnLock {

	public static IUnLock iUnLock = null;

	public static IUnLock getInstance() {
		if (null == iUnLock) {
			iUnLock = new IUnLock();
		}
		return iUnLock;
	}

	/**
	 * 解除上传下载锁
	 * 
	 * @param uuid
	 * @return
	 */
	public void unLock() {
		DataOperateDao.getInstance().unLockAll();
	}

	/**
	 * 解除上传锁
	 * 
	 * @param uuid
	 * @return
	 */
	public void unUpLock(String uplockid) {

		String operate = "上传";
		DataOperateDao.getInstance().unLockOne(operate,uplockid);

	}

	/**
	 * 解除上传锁
	 * 
	 * @param uuid
	 * @return
	 */
	public void unUpLock() {

		String operate = "上传";
		DataOperateDao.getInstance().unLockOne(operate);

	}
	
	/**
	 * 解除下载锁
	 * 
	 * @param uuid
	 * @return
	 */
	public void unLoadLock(String loadlockid) {
		String operate = "下载";
		DataOperateDao.getInstance().unLockOne(operate,loadlockid);
	}
	
	
	/**
	 * 解除上传锁
	 * 
	 * @param uuid
	 * @return
	 */
	public void unLoadLock() {

		String operate = "下载";
		DataOperateDao.getInstance().unLockOne(operate);

	}
	
	/**
	 * 给下载上锁
	 * 
	 * @param uuid
	 * @return
	 */
	public void loadLock() {
		String operate = "下载";
		DataOperateDao.getInstance().lockOne(operate);
	}
	
	
	
	/**
	 * 给上传上锁
	 * 
	 * @param uuid
	 * @return
	 */
	public void upLock() {
		String operate = "上传";
		DataOperateDao.getInstance().lockOne(operate);
	}
	
	
	/**
	 * 获取上传锁信息
	 * 
	 * @param uuid
	 * @return
	 */
	public HashMap<String, String> getUpLock(){
		String operate = "上传";
		return DataOperateDao.getInstance().getMsg(operate);
	}
	
	
	/**
	 * 获取下载锁信息
	 * 
	 * @param uuid
	 * @return
	 */
	public HashMap<String, String> getLoadLock(){
		String operate = "下载";
		return DataOperateDao.getInstance().getMsg(operate);
	}
	
	

	public static void main(String[] args) {
//		IUnLock.getInstance().unUpLock();
//		IUnLock.getInstance().unLoadLock();
		
		IUnLock.getInstance().upLock();
		IUnLock.getInstance().loadLock();
		System.out.println(IUnLock.getInstance().getUpLock().get("flag"));
		System.out.println(IUnLock.getInstance().getLoadLock().get("flag"));
	}

}
