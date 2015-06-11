package com.fxdigital.db.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fxdigital.db.pojo.UserLogPojo;
import com.hibernate.bean.NvmpUserlog;
import com.hibernate.db.ConnDo;
import com.mysql.jdbc.StringUtils;

/**
 * 
 * @author hxht
 * @version 2014-9-11
 */
@Component
public class UserLogDao {

	public int insert(UserLogPojo userLog) {
		NvmpUserlog log = new NvmpUserlog();
		log.setClientId(userLog.getClientID());
		log.setContent(userLog.getContent());
		log.setTime(userLog.getTime());
		log.setType(userLog.getType());
		log.setUserId(userLog.getUserID());
		if(ConnDo.getConnDo().save(log))
			return 1;
		else
			return -1;
	}

	public long query(Timestamp start, Timestamp end, String type,
			String userID, String clientID) {
		StringBuilder hql = new StringBuilder(
				"select new Map(count(*) as num) from NvmpUserlog");
		hql = createSql(hql, start, end, type, userID, clientID);
		List<HashMap<String,String>> list = ConnDo.getConnDo().executeQuery(hql.toString());
		if (list == null || list.size() == 0) {
			return 0;
		} else {
			return Long.parseLong(list.get(0).get("num"));
		}
	}

	private StringBuilder createSql(StringBuilder sql, Timestamp start,
			Timestamp end, String type, String userID, String clientID) {
		if (start != null || end != null || !StringUtils.isNullOrEmpty(type)
				|| !StringUtils.isNullOrEmpty(userID)
				|| !StringUtils.isNullOrEmpty(clientID)) {
			sql.append(" where");
		}
		boolean flag = false;
		if (!StringUtils.isNullOrEmpty(type)) {
			sql.append(" type='" + type + "'");
			flag = true;
		}
		if (!StringUtils.isNullOrEmpty(userID)) {
			if (flag) {
				sql.append(" and");
			}
			sql.append(" userId='" + userID + "'");
			flag = true;
		}
		if (!StringUtils.isNullOrEmpty(clientID)) {
			if (flag) {
				sql.append(" and");
			}
			sql.append(" clientId='" + clientID + "'");
			flag = true;
		}
		if (start != null || end != null) {
			if (flag) {
				sql.append(" and");
			}
			if (start != null && end != null) {
				sql.append(" time between '" + start + "' and '" + end + "'");
			} else if (start != null) {
				sql.append(" time>='" + start + "'");
			} else if (end != null) {
				sql.append(" time<='" + end + "'");
			}
		}
		return sql;
	}

	public List<UserLogPojo> query(Timestamp start, Timestamp end, String type,
			String userID, String clientID, int page, int rows, String sidx,
			String sord) {
		StringBuilder hql = new StringBuilder("from NvmpUserlog");
		hql = createSql(hql, start, end, type, userID, clientID);
		if (!StringUtils.isNullOrEmpty(sidx)) {
			hql.append(" order by " + sidx + " " + sord);
		}
		List<HashMap<String,String>> list = ConnDo.getConnDo().executeQueryToList(hql.toString(),(page - 1) * rows,rows);
		if (list == null || list.size() == 0) {
			return null;
		} else {
			List<UserLogPojo> ulps = new ArrayList<UserLogPojo>();
			for (Map<String, String> row : list) {
				UserLogPojo ulp = new UserLogPojo();
				ulp.setClientID(row.get("ClientID"));
				ulp.setContent(row.get("Content"));
				ulp.setTime(Timestamp.valueOf(row.get("Time")));
				ulp.setType(row.get("Type"));
				ulp.setUserID(row.get("UserID"));
				ulps.add(ulp);
			}
			return ulps;
		}
	}

}
