/**
 * 
 */
package com.fxdigital.authority.web;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fxdigital.authority.bean.Auth;
import com.fxdigital.authority.service.AuthorityService;
import com.fxdigital.authority.service.AuthorityTree;

/**
 * @author lizehua
 *
 */
@Component
public class CreateAuthData {
	@Autowired
	private AuthorityTree authorityTree;
	@Autowired
	private AuthorityService service;
	public void create(int num,int n,int centerID){
//		List<Auth> data=new ArrayList<Auth>();
		for (int i = 0; i < num; i++) {
			String sourceID=UUID.randomUUID().toString();
		List<Auth> list=	authorityTree.create(n, centerID);
		String sCaddress=list.get(list.size()-1).getCenterID();
//		System.out.println("tree ="+list.size());
			for (int j=0 ;j<list.size();j++) {
				Auth auth=list.get(j);
				auth.setSourceID(sourceID);
				auth.setSCaddress(sCaddress);
				auth.setAggress("1");
				auth.setSecond("1");
//				String[] obj	={auth.getParentID(),auth.getCenterID(),auth.getSourceID(),auth.getSCaddress(),auth.getAggress(),auth.getSecond()};
				
			}
			service.insert(list);
			
		}
		
		
	}
	
	public void queryAuth(String centerID,String sourceID){
		boolean flag=service.queryauth(centerID, sourceID);
		System.out.println(flag);
	}

	
	
}
