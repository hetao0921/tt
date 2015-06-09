package com.fxdigital.manager;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fxdigital.bean.TableData;
import com.fxdigital.bean.UserLogBean;
import com.fxdigital.db.dao.LogTypeDao;
import com.fxdigital.db.dao.UserDao;
import com.fxdigital.db.dao.UserLogDao;
import com.fxdigital.db.pojo.UserLogPojo;
import com.mysql.jdbc.StringUtils;

/**
 * 
 * @author hxht
 * @version 2014-9-11
 */
@Component
public class UserLogManager {
	
	private static Logger log = Logger.getLogger(UserLogManager.class);
	
	@Autowired
	private UserLogDao userLogDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private LogTypeDao logTypeDao;
	
	public List<String> getLogType(){
		return logTypeDao.query();
	}
	
	public TableData getUserLog(String start,String end,String type,
			String userID,String clientID,int page,int rows,String sidx,String sord){
		TableData tableData = new TableData();
		try {
			Timestamp t_start = null;
			Timestamp t_end = null;
			if(!StringUtils.isNullOrEmpty(start)){
				if(start.length() == 10){
					start = start + " 00:00:00";
				}
				t_start = Timestamp.valueOf(start);
			}
			if(!StringUtils.isNullOrEmpty(end)){
				if(end.length() == 10){
					end = end + " 00:00:00";
				}
				t_end = Timestamp.valueOf(end);
			}
			List<UserLogPojo> userLogPojos = userLogDao.query(t_start, t_end, type,
					userID, clientID, page, rows, sidx, sord);
			long rowNum = userLogDao.query(t_start, t_end, type,userID, clientID);
			List<UserLogBean> userLogBeans = new ArrayList<UserLogBean>();
			if(userLogPojos != null){
				for(UserLogPojo userLogPojo : userLogPojos){
					userLogBeans.add(convertToBean(userLogPojo));
				}
			}
			tableData.setRecords(rowNum);
			tableData.setRows(userLogBeans);
			return tableData;
		} catch (Exception e) {
			log.error("获得用户日志异常！", e);
			return null;
		}
	}
	
	private UserLogBean convertToBean(UserLogPojo userLogPojo){
		if(userLogPojo == null){
			return null;
		}else{
			UserLogBean ulb = new UserLogBean();
			ulb.setClientID(userLogPojo.getClientID());
			ulb.setContent(userLogPojo.getContent());
			String logTime = userLogPojo.getTime().toString();
			if(!StringUtils.isNullOrEmpty(logTime)){
				if(logTime.contains(".")){
					logTime = logTime.split("\\.")[0];
				}
			}
			ulb.setTime(logTime);
			ulb.setType(userLogPojo.getType());
			ulb.setUserName(getUserName(userLogPojo.getUserID()));
			return ulb;
		}
	}
	
	public String getUserName(String userID){
		return userDao.queryForName(userID);
	}
	
	public String getUserID(String userName){
		return userDao.queryForID(userName);
	}
	
}
