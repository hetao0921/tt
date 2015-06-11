package com.route;

import java.util.HashMap;
import java.util.Map;

/**
 * 路由中，对用户和中心关系的存储。
 * 
 * */
public class UsersInfo {

	private Map<String, String> userCenterMap; // key 用户ID value 中心ID

	private UsersInfo() {
		userCenterMap = new HashMap<String, String>();
	}

	public static UsersInfo getInstance() {
		return UsersInfoHolder.INSTANCE;
	}

	private static class UsersInfoHolder {
		private static final UsersInfo INSTANCE = new UsersInfo();
	}

	/**
	 * 保存用户和中心对应关系
	 * */
	public void insertUser(String userID, String centerID) {
		synchronized (userCenterMap) {
			userCenterMap.put(userID, centerID);
		}
	}

	/**
	 * 查询用户的中心归属
	 * */

	public String getCenterID(String userID) {
		String centerID = null;
		synchronized (userCenterMap) {
			centerID = userCenterMap.get(userID);
		}
		return centerID;
	}

}
