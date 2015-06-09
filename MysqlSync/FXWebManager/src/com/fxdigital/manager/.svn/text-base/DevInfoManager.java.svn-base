package com.fxdigital.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fxdigital.bean.DevInfoBean;
import com.fxdigital.db.dao.DevInfoDao;
import com.fxdigital.db.pojo.DevInfoPojo;

/**
 * 
 * @author hxht
 * @version 2014-9-15
 */
@Component
public class DevInfoManager {
	
	@Autowired
	private DevInfoDao devInfoDao;
	
	public List<DevInfoBean> getDevInfos(){
		List<DevInfoPojo> dips = devInfoDao.query();
		List<DevInfoBean> dibs = new ArrayList<DevInfoBean>();
		if(dips != null){
			for(DevInfoPojo dip : dips){
				dibs.add(convertToBean(dip));
			}
		}
		return dibs;
	}
	
	public DevInfoBean getDevInfo(long id){
		DevInfoPojo dip = devInfoDao.query(id);
		return convertToBean(dip);
	}
	
	public boolean add(DevInfoBean dib){
		int num = devInfoDao.insert(convertToPojo(dib));
		if(num == 1){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean edit(DevInfoBean dib){
		int num = devInfoDao.update(convertToPojo(dib));
		if(num == 1){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean del(long id){
		int num = devInfoDao.delete(id);
		if(num == 1){
			return true;
		}else{
			return false;
		}
	}
	
	private DevInfoBean convertToBean(DevInfoPojo dip){
		if(dip == null){
			return null;
		}
		DevInfoBean dib = new DevInfoBean();
		dib.setId(dip.getId());
		dib.setName(dip.getName());
		dib.setUrl(dip.getUrl());
		dib.setDesc(dip.getDesc());
		return dib;
	}
	
	private DevInfoPojo convertToPojo(DevInfoBean dib){
		if(dib == null){
			return null;
		}
		DevInfoPojo dip = new DevInfoPojo();
		dip.setId(dib.getId());
		dip.setName(dib.getName());
		dip.setUrl(dib.getUrl());
		dip.setDesc(dib.getDesc());
		return dip;
	}
	
}
