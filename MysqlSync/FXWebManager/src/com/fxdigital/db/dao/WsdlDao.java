package com.fxdigital.db.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class WsdlDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<?> query() {
		String sql = "select * from nvmp.nvmp_wsdlinfo";
		//System.out.println(sql);
		//System.out.println(jdbcTemplate);
		return jdbcTemplate.queryForList(sql);

	}
	public List<?> wsdls_show() {
		String sql = "select * from nvmp.nvmp_wsdls";
		//System.out.println(sql);
		//System.out.println(jdbcTemplate);
		return jdbcTemplate.queryForList(sql);

	}
	
	public List<?> wsdlInfo_show() {
		String sql = "select * from nvmp_wsdlinfo";
		
		return jdbcTemplate.queryForList(sql);

	}
	public List<?> selectByWsdlURI(String wsdlURI) {
		String sql = "select * from nvmp_wsdlinfo where wsdlURI=?";
		//System.out.println(sql);
		//System.out.println(jdbcTemplate.queryForList(sql,wsdlURI));
		return jdbcTemplate.queryForList(sql,wsdlURI);

	}
	
	
	public int edit_wsdl(String sURI,String uri,String desc){
		String sql = "update nvmp_wsdls set wsdlURI=?,wsdlDesc=? where wsdlURI=?";
		return jdbcTemplate.update(sql, new Object[]{
				uri,
				desc,
				sURI
				
		});
	}
	

	public int edit_wsdlInfo(int wsdlInfoId,String functionName,String functionDesc,String arguments,String results){
		String sql = "update nvmp_wsdlinfo set functionName=?,functionDesc=?,arguments=?,results=? where wsdlInfoId=?";
		//System.out.println(sql);
		return jdbcTemplate.update(sql, new Object[]{
				functionName,
				functionDesc,
				arguments,
				results,
				wsdlInfoId
				
				
		});
	}
	public int edit_wsdlURI(String sURI,String uri){
		String sql = "update nvmp_wsdlinfo set wsdlURI=?where wsdlURI=?";
		return jdbcTemplate.update(sql, new Object[]{
				uri,
				sURI
				
		});
	}
	
	public int selectByfunctionName(String functionName){
		String sql = "delete from nvmp_wsdlinfo where functionName=?";
		System.out.println(sql);
		System.out.println(jdbcTemplate);
		return jdbcTemplate.update(sql,new Object[]{functionName});
	}
	
	public int insert(String wsdlUri,String wsdlDesc,String functionName,String functionDesc,String arguments,String results){
		String sql = "insert into nvmp_wsdls(wsdlURI,wsdlDesc) values(?,?)";
		String sqlplus="insert into nvmp_wsdlInfo(wsdlURI,functionName,functionDesc,arguments,results) values(?,?,?,?,?)";
		 jdbcTemplate.update(sqlplus, new Object[]{
				wsdlUri,
				functionName,
				functionDesc,
				arguments,
				results
				
		});
		
		 return	jdbcTemplate.update(sql, new Object[]{
				wsdlUri,
				wsdlDesc
				
		});
	}
	
	public int insertInfo(String wsdlUri,String functionName,String functionDesc,String arguments,String results){
		
		String sql="insert into nvmp_wsdlInfo(wsdlURI,functionName,functionDesc,arguments,results) values(?,?,?,?,?)";
		return jdbcTemplate.update(sql, new Object[]{
				wsdlUri,
				functionName,
				functionDesc,
				arguments,
				results
				
		});
		
		
	}
	
	public int delete(String wsdlURI){
		String sql = "delete from nvmp_wsdls where wsdlURI=?";
		String sqlPlus="delete from nvmp_wsdlInfo where wsdlURI=?";
		jdbcTemplate.update(sqlPlus,new Object[]{wsdlURI});
		
		return jdbcTemplate.update(sql,new Object[]{wsdlURI});
	}
	public int deleteInfo(String wsdlInfoId){
		String sql = "delete from nvmp_wsdlInfo where wsdlInfoId=?";
		
		return jdbcTemplate.update(sql,new Object[]{wsdlInfoId});
		
		
	}


	
}
