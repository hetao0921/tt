/**
 * 
 */
package com.fxdigital.authority.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fxdigital.authority.bean.Auth;
import com.fxdigital.authority.dao.TestDao;

/**
 * @author lizehua
 *
 */
@Component
public class AuthorityService {
	@Autowired
	private TestDao testDao;
	public void insert(List<Auth> list){
		testDao.insert(list);
	}
	
	public boolean queryauth(String centerID,String sourceID){
		boolean flag=false;
		HashMap<String,Auth> hp=	testDao.queryauth(sourceID);
		if(hp.isEmpty()){
			return flag;
		}
		Auth auth=hp.get(centerID);
		centerID=auth.getParentID();
		if("1".equals(auth.getAggress())){
			while(true){
				auth=hp.get(centerID);			
				if(auth==null){
					break;
				}
				System.out.println("id="+auth.getId()+"--"+"centerID="+auth.getCenterID()+"--parentID="+auth.getParentID()+"--aggree="+auth.getAggress()+"--"+"second="+auth.getSecond());
				if("1".equals(auth.getAggress())&&"1".equals(auth.getSecond())){
					if(auth.getCenterID().equals(auth.getParentID())){
						flag=true;
						break;
					}
					centerID=auth.getParentID();	
				}else{
					break;
				}
			}
		}
		
		return flag;
		
	}
}
