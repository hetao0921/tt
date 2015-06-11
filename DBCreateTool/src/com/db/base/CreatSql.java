package com.db.base;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.db.bean.Columns;

public class CreatSql {
	private static final Logger logger = Logger.getLogger(CreatSql.class);

	public static String CreatQuerySql(String database, String datatable,
			List<String> datacolumns) {
		String sql = "";
		sql = "select ";
		for (int i = 0; i < datacolumns.size(); i++) {
			if (i == datacolumns.size() - 1) {
				sql += datacolumns.get(i);
			} else {
				sql += datacolumns.get(i) + ",";
			}
		}
		sql += " from " + database + "." + datatable + " order by "
				+ datacolumns.get(0) + "";
		logger.info("创建的sql语句为: " + sql);
		return sql;
	}

	public static List<String> CreateQuery(List<Columns> columns) {
		List<String> list = new ArrayList<String>();
		String sql = "";
		for (Columns col : columns) {
			String schemaName = col.getSchemaName();
			String tableName = col.getTableName();
			List<String> columnList = col.getColumns();
			sql = "select * from information_schema.columns where table_schema='"
					+ schemaName
					+ "' and table_name='"
					+ tableName
					+ "' and column_name='" + columnList.get(0) + "'";
			list.add(sql);
		}

		return list;
	}

	public List<String> CreateQuery(Columns col) {
		String sql = "";
		List<String> sqlList = new ArrayList<String>();
		String schemaName = col.getSchemaName();
		String tableName = col.getTableName();
		List<String> columnList = col.getColumns();
		for (String str : columnList) {
			sql = "select * from information_schema.columns where table_schema='"
					+ schemaName
					+ "' and table_name='"
					+ tableName
					+ "' and column_name='" + str + "'";
			sqlList.add(sql);
		}
		return sqlList;
	}

}
