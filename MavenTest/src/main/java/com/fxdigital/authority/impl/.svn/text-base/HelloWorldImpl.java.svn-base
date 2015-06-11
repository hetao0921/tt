/**
 * 
 */
package com.fxdigital.authority.impl;

import java.util.List;

import javax.jws.WebService;

import com.fxdigital.authority.bean.User;
import com.fxdigital.authority.interfaces.HelloWorld;

/**
 * @author lizehua
 *
 */
@WebService(endpointInterface="com.fxdigital.authority.interfaces.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

	/* (non-Javadoc)
	 * @see com.fxdigital.authority.interfaces.HelloWorld#sayHi(java.lang.String)
	 */
	@Override
	public String sayHi(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.fxdigital.authority.interfaces.HelloWorld#sayHiToUser(com.fxdigital.authority.bean.User)
	 */
	@Override
	public String sayHiToUser(User user) {
		
		
		return "hello"+user.getName();
	}

	/* (non-Javadoc)
	 * @see com.fxdigital.authority.interfaces.HelloWorld#SayHiToUserList(java.util.List)
	 */
	@Override
	public String[] SayHiToUserList(List<User> userList) {String[] result = new String[userList.size()];
    int i=0;
    for(User u:userList){
         result[i] = "Hello " + u.getName();
         i++;
    }
return result;
}}
