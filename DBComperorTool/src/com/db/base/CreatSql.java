package com.db.base;

import java.util.List;

import org.apache.log4j.Logger;

public class CreatSql {
	private static final Logger logger = Logger.getLogger(CreatSql.class);
	public static String CreatQuerySql(String database,String datatable,List<String> datacolumns){
		String sql="";
		sql="select ";
		for(int i=0;i<datacolumns.size();i++){
			if(i==datacolumns.size()-1){
				sql+=datacolumns.get(i);
			}else{
				sql+=datacolumns.get(i)+",";
			}
		}
		sql+=" from "+database+"."+datatable+" order by "+datacolumns.get(0)+"";
		logger.info("创建的sql语句为: "+sql);
		return sql;
	}

}
