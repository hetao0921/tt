package com.fxdigital.util;

import com.mysql.jdbc.StringUtils;

/**
 * 
 * @author fucz
 * @version 2014-7-18
 */
public class ArgsUtil {
	
	public static final String POSTADDRESS = "PostAddress";
	
	public static String getPostAddress(){
		String postAddress = ConfigUtil.getString("mqPostAddress");
		if(StringUtils.isNullOrEmpty(postAddress)){
			postAddress = POSTADDRESS;
		}
		return postAddress;
	}
	
}
