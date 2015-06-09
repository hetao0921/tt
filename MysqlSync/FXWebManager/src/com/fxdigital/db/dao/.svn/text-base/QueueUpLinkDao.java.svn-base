package com.fxdigital.db.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.fxdigital.db.pojo.UpLink;

/**
 * 
 * @author hxht
 * @version 2014-11-4
 */
@Component
public class QueueUpLinkDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings("unchecked")
	public List<UpLink> query(){
		String sql = "select * from nvmp.mq_queueuplinktab";
		List<?> list = jdbcTemplate.queryForList(sql);
		List<UpLink> upLinks = new ArrayList<UpLink>();
		if(list != null && list.size() >= 1){
			for(Object tmp : list){
				Map<String, Object> data = (Map<String, Object>)tmp;
				UpLink ul = new UpLink();
				ul.setId(Long.parseLong(data.get("ID").toString()));
				ul.setCenterIP((String) data.get("CenterIP"));
				String relation = (String) data.get("Flag");
				if("up".equals(relation))
					ul.setRelation("上级ip");
				else
					ul.setRelation("下级ip");
				upLinks.add(ul);
			}
		}
		return upLinks;
	}
	
	@SuppressWarnings("unchecked")
	public int queryForID(){
		String sql = "select id from nvmp.mq_queueuplinktab order by id desc limit 1";
		List<?> list = jdbcTemplate.queryForList(sql);
		if(list != null && list.size() >= 1){
			Map<String, Object> data = (Map<String, Object>)list.get(0);
			return Integer.parseInt(data.get("ID").toString());
		}
		return 0;
	}
	
	public int delete(String centerIP){
		String sql = "delete from nvmp.mq_queueuplinktab where CenterIP=?";
		return jdbcTemplate.update(sql, new Object[]{centerIP});
	}
	
	public boolean isIDExsit(int id){
		String sql = "select * from nvmp.mq_queueuplinktab where ID = ?";
		List<?> list = jdbcTemplate.queryForList(sql,new Object[]{id});
		if(list != null && list.size() >= 1)
			return true;
		else
			return false;
	}
	
	public boolean isIPExsit(String ip){
		String sql = "select * from nvmp.mq_queueuplinktab where CenterIP = ?";
		List<?> list = jdbcTemplate.queryForList(sql,new Object[]{ip});
		if(list != null && list.size() >= 1)
			return true;
		else
			return false;
	}
	
	public int insert(String centerID,String centerIP,int centerPort,String relation){
		String sql = "insert into nvmp.mq_queueuplinktab(CenterID,CenterIP,CenterPort,Flag) values(?,?,?,?)";
		return jdbcTemplate.update(sql, new Object[]{centerID,centerIP,centerPort,relation});
	}
	
	public int insert(String centerID,String centerIP,String relation){
		String sql = "insert into nvmp.mq_queueuplinktab(CenterID,CenterIP,Flag) values(?,?,?)";
		return jdbcTemplate.update(sql, new Object[]{centerID,centerIP,relation});
	}
	
	public int update(int id,String centerID,String centerIP,int centerPort){
		String sql = "update nvmp.mq_queueuplinktab set CenterIP=? where ID=? and CenterID=? and CenterPort=?";
		return jdbcTemplate.update(sql, new Object[]{centerIP,id,centerID,centerPort});
	}
	
	public int update(int id,String centerIP,int centerPort){
		String sql = "update nvmp.mq_queueuplinktab set CenterIP=? where ID=? and CenterPort=?";
		return jdbcTemplate.update(sql, new Object[]{centerIP,id,centerPort});
	}
	
	public int update(int id,String centerID,String centerIP){
		String sql = "update nvmp.mq_queueuplinktab set CenterIP=? where ID=? and CenterID=?";
		return jdbcTemplate.update(sql, new Object[]{centerIP,id,centerID});
	}
	
	public int update(int id,String centerIP){
		String sql = "update nvmp.mq_queueuplinktab set CenterIP=? where ID=?";
		return jdbcTemplate.update(sql, new Object[]{centerIP,id});
	}
	
}
