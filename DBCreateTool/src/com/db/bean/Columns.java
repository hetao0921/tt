package com.db.bean;

import java.util.List;

public class Columns {
	
	private String schemaName;
    private String tableName;
    private List<String> columns;
    private String dropSql;
    private String createSql;
	public String getSchemaName() {
		return schemaName;
	}
	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public List<String> getColumns() {
		return columns;
	}
	public void setColumns(List<String> columns) {
		this.columns = columns;
	}
	public String getDropSql() {
		return dropSql;
	}
	public void setDropSql(String dropSql) {
		this.dropSql = dropSql;
	}
	public String getCreateSql() {
		return createSql;
	}
	public void setCreateSql(String createSql) {
		this.createSql = createSql;
	}
    
    
    

}
