package com.fxdigital.onvif.ctrl;

import java.util.LinkedList;
import java.util.List;

public class UserSet {

	private List<UserInfo> list;

	public UserSet() {
		list = new LinkedList<UserInfo>();
	}

	public void addUser(String userName, String password, int level) {
		UserInfo ui = new UserInfo(userName, password, level);
		list.add(ui);
	}

	public List<UserInfo> getUserSet() {
		return list;
	}

}
