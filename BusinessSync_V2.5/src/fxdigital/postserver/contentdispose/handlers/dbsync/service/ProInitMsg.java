package fxdigital.postserver.contentdispose.handlers.dbsync.service;

import java.util.HashMap;
import java.util.List;

import fxdigital.postserver.contentdispose.handlers.dbsync.dao.DataVersionDao;
/**
 * @author  het
 *初始化消息处理类
 * 2014-7-30
 * BusinessSync
 * fxdigital.postserver.contentdispose.handlers.dbsync.service
 */
public class ProInitMsg {

	public static ProInitMsg proInitMsg = null;

	public static ProInitMsg getInstance() {
		if (null == proInitMsg) {
			proInitMsg = new ProInitMsg();
		}
		return proInitMsg;
	}

	/**
	 * 上传初始化时得到服务器版本
	 * 
	 * @return
	 */
	public int proUpInitMsg(String centerid) {
		String serverVersion = "0";
		List<HashMap<String, String>> upList = DataVersionDao.getInstance().getOneVersion(
				centerid);
		if (upList.size() > 0) {
			serverVersion = upList.get(0).get("version");
		}
		int retVersion = Integer.valueOf(serverVersion);
		return retVersion;

	}

	/**
	 * 下载初始化时得到服务器版本
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> proLoadInitMsg(
			String centerid) {
		return DataVersionDao.getInstance().getNoOneSource(centerid);
		
		//List<HashMap<String, String>> loadList = new ArrayList<HashMap<String, String>>();
		//return loadList;

	}

	public static void main(String[] args) {
		int version = ProInitMsg.getInstance().proUpInitMsg("het@001");
		System.out.println(version);
	}
}
