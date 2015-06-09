package com.fxdigital.db.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.fxdigital.db.pojo.UserLogPojo;
import com.mysql.jdbc.StringUtils;

import fxdigital.dbsync.domains.client.db.DBConn;

/**
 * 
 * @author hxht
 * @version 2014-9-11
 */
@Component
public class UserLogDao {

	// private static Logger log = Logger.getLogger(UserLogDao.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int insert(UserLogPojo userLog) {
		String sql = "insert into nvmp.nvmp_userlog(Content,Time,Type,UserID,ClientID) values(?,?,?,?,?)";
		return jdbcTemplate.update(sql, new Object[] { userLog.getContent(),
				userLog.getTime(), userLog.getType(), userLog.getUserID(),
				userLog.getClientID() });
	}

	@SuppressWarnings("unchecked")
	public long query(Timestamp start, Timestamp end, String type,
			String userID, String clientID) {
		StringBuilder sql = new StringBuilder(
				"select count(*) num from nvmp.nvmp_userlog");
		sql = createSql(sql, start, end, type, userID, clientID);
		List<?> list = jdbcTemplate.queryForList(sql.toString());
		if (list == null || list.size() == 0) {
			return 0;
		} else {
			Map<String, Object> row = (Map<String, Object>) list.get(0);
			return (Long) row.get("num");
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
			sql.append(" Type='" + type + "'");
			flag = true;
		}
		if (!StringUtils.isNullOrEmpty(userID)) {
			if (flag) {
				sql.append(" and");
			}
			sql.append(" UserID='" + userID + "'");
			flag = true;
		}
		if (!StringUtils.isNullOrEmpty(clientID)) {
			if (flag) {
				sql.append(" and");
			}
			sql.append(" ClientID='" + clientID + "'");
			flag = true;
		}
		if (start != null || end != null) {
			if (flag) {
				sql.append(" and");
			}
			if (start != null && end != null) {
				sql.append(" Time between '" + start + "' and '" + end + "'");
			} else if (start != null) {
				sql.append(" Time>='" + start + "'");
			} else if (end != null) {
				sql.append(" Time<='" + end + "'");
			}
		}
		return sql;
	}

	@SuppressWarnings("unchecked")
	public List<UserLogPojo> query(Timestamp start, Timestamp end, String type,
			String userID, String clientID, int page, int rows, String sidx,
			String sord) {
		StringBuilder sql = new StringBuilder("select * from nvmp.nvmp_userlog");
		sql = createSql(sql, start, end, type, userID, clientID);
		if (!StringUtils.isNullOrEmpty(sidx)) {
			sql.append(" order by " + sidx + " " + sord);
		}
		if (DBConn.getDBType() == null || DBConn.getDBType().equals("")
				|| DBConn.getDBType().equals("mysql")) {
			sql.append(" limit " + (page - 1) * rows + "," + rows);
		}else if(DBConn.getDBType()!= null &&! DBConn.getDBType().equals("")
				&& DBConn.getDBType().equals("oscar")){
			sql.append(" limit " + rows + " offset " + (page - 1) * rows);
		}
		// log.info(sql.toString());
		List<?> list = jdbcTemplate.queryForList(sql.toString());
		if (list == null || list.size() == 0) {
			return null;
		} else {
			List<UserLogPojo> ulps = new ArrayList<UserLogPojo>();
			for (Object tmp : list) {
				Map<String, Object> row = (Map<String, Object>) tmp;
				UserLogPojo ulp = new UserLogPojo();
				ulp.setClientID((String) row.get("ClientID"));
				ulp.setContent((String) row.get("Content"));
				ulp.setTime((Timestamp) row.get("Time"));
				ulp.setType((String) row.get("Type"));
				ulp.setUserID((String) row.get("UserID"));
				ulps.add(ulp);
			}
			return ulps;
		}
	}

}
