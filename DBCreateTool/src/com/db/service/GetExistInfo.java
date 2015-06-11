package com.db.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.db.base.CreatSql;
import com.db.base.JDBCConnection;
import com.db.base.JDBCExec;
import com.db.bean.BundlePro;
import com.db.bean.Columns;
import com.db.util.Backup;

public class GetExistInfo {
	private static final Logger logger = Logger.getLogger(GetExistInfo.class);

	public static final String DBDRIVER = "com.mysql.jdbc.Driver";

	public static String DBURL = "jdbc:mysql://localhost:3306/";

	public static String username = "admin";

	public static String password = "111"; 
	
	public boolean isExist(){
		boolean flag=false;
		BundlePro bundlePro=new BundlePro();
		List<Columns> columns=bundlePro.readPathXML();
		JDBCConnection jDBCConnection=new JDBCConnection("localhost","3306",username,password);
		Connection conn=jDBCConnection.getConnection();
		for(Columns col:columns){
			CreatSql creatSql=new CreatSql();
			JDBCExec jDBCExec=new JDBCExec();
			List<String> str=creatSql.CreateQuery(col);
			for(String execStr:str){
				flag=jDBCExec.getResultFlag(conn,execStr);
				if(!flag){
					break;
				}
			}
			
			//当前为新数据库表
			if(flag){
				
			}
			//当前为旧数据库表
			else{
				//备份旧表数据
				Backup backup=new Backup();
				backup.backup(col.getSchemaName(), col.getTableName());
					
				//删除旧表，添加新表
				String[] sqlStr=new String[4];
				sqlStr[0]=col.getDropSql().split(";")[0];
				sqlStr[1]=col.getDropSql().split(";")[1];
				sqlStr[2]=col.getDropSql().split(";")[2];
				sqlStr[3]=col.getCreateSql();
				jDBCExec.executeSql(conn, sqlStr);
				logger.info("删除旧表，添加新表成功");
			}
			logger.info(str+" "+flag);	
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	
	public static void main(String[] args) {
		GetExistInfo getExistInfo=new GetExistInfo();
		getExistInfo.isExist();
	}

}
