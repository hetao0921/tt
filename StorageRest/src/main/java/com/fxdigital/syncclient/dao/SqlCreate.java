package com.fxdigital.syncclient.dao;

import java.util.Iterator;

import net.sf.json.JSONObject;

public class SqlCreate {
	
	public String sqlCreate(JSONObject jsonObj){
		
		String sql ="SELECT * FROM nvmp_record_mark as a,(SELECT t.nvmp_mark_uuid ";
		String sql1 =" FROM nvmp_record_mark_parameter t GROUP BY nvmp_mark_uuid) as b " +
						"WHERE a.nvmp_mark_uuid=b.nvmp_mark_uuid";
		for (Iterator iter = jsonObj.keys(); iter.hasNext();) {		
			
			String parameter_key = (String) iter.next();
			String parameter_value = jsonObj.getString(parameter_key);
			sql += ",MAX(CASE parameter_key WHEN '"+parameter_key+"' THEN parameter_value ELSE '-1' END ) "+parameter_key ;			
			sql1+=" AND b."+parameter_key+"='"+parameter_value+"'";
		}
		sql +=sql1;
		
		return sql;
		
	}
}
