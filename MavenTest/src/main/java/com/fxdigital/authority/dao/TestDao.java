/**
 * 
 */
package com.fxdigital.authority.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.fxdigital.authority.bean.Auth;

/**
 * @author lizehua
 *
 */
@Component
public class TestDao {
	private static final Logger log = Logger.getLogger("TestDao");
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public void insert (  List<Auth> list){
//		log.info(list.size());
//		final List<Auth> list=list1;
		String sql="insert into test(parentID,centerID,sourceID,SCaddress,aggress,second)values(?,?,?,?,?,?)";
/*		
		for (Auth auth : list) {
			jdbcTemplate.update(sql,new String[]{auth.getParentID(),auth.getCenterID(),auth.getSourceID(),auth.getSCaddress(),auth.getAggress(), auth.getSecond()});
		}*/
		
		
		jdbcTemplate.batchUpdate(sql,new MyBatchPreparedStatementSetter(list));

	
		}
	
	private class MyBatchPreparedStatementSetter implements BatchPreparedStatementSetter{
		final List<Auth> list;
		public MyBatchPreparedStatementSetter(List<Auth> list){
			this.list=list;
		}

		@Override
		public int getBatchSize() {
		
			return list.size();
		}

		@Override
		public void setValues(PreparedStatement arg0, int arg1)
				throws SQLException {
			Auth auth=list.get(arg1);
			arg0.setString(1, auth.getParentID());
			arg0.setString(2,auth.getCenterID());
			arg0.setString(3, auth.getSourceID());
			arg0.setString(4, auth.getSCaddress());
			arg0.setString(5, auth.getAggress());
			arg0.setString(6, auth.getSecond());
			// TODO Auto-generated method stub
			
		}
	}
	
	public HashMap<String,Auth> queryauth(String sourceID){
		String sql="select * from test where sourceID=?";
		final HashMap<String,Auth> hp=new HashMap<String, Auth>();
			jdbcTemplate.query(sql,new Object[]{sourceID}, new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs, int arg1) throws SQLException {
				
				Auth auth=new Auth();
				auth.setParentID(rs.getString("parentID"));
				auth.setCenterID(rs.getString("centerID"));
				auth.setSourceID(rs.getString("sourceID"));
				auth.setSCaddress(rs.getString("SCaddress"));
				auth.setAggress(rs.getString("aggress"));
				auth.setSecond(rs.getString("second"));
				auth.setId(rs.getString("id"));
				hp.put(auth.getCenterID(), auth);
				return null;
			}
			
		});
		return hp;
		
	
	/*String sql="select tes.*,temp.centerID mycenter,temp.sourceID mysource from (select sourceID,centerID from test where id in ? ) temp ,test  tes where temp.sourceID=tes.sourceID";
	
	RowMapper mapper=new RowMapper<T>() {

		@Override
		public T mapRow(ResultSet arg0, int arg1) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}
	};*/
	
		
	}

}
