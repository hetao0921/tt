package com.serlize.pojo;

import java.io.Serializable;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;

public class StatusSerlize implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StatusSerlize(){
		
	}
	
	public String hostname1;
	public String port1;
	public String username1;
	public String password1;
	
	
	public String hostname2;
	public String port2;
	public String username2;
	public String password2;
	
    public String database;
    
    public String datatable;
    
    public ListModel columns;
    
    public ListModel selectColumns;
    
    public String result;

	public String getHostname1() {
		return hostname1;
	}

	public void setHostname1(String hostname1) {
		this.hostname1 = hostname1;
	}

	public String getPort1() {
		return port1;
	}

	public void setPort1(String port1) {
		this.port1 = port1;
	}

	public String getUsername1() {
		return username1;
	}

	public void setUsername1(String username1) {
		this.username1 = username1;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getHostname2() {
		return hostname2;
	}

	public void setHostname2(String hostname2) {
		this.hostname2 = hostname2;
	}

	public String getPort2() {
		return port2;
	}

	public void setPort2(String port2) {
		this.port2 = port2;
	}

	public String getUsername2() {
		return username2;
	}

	public void setUsername2(String username2) {
		this.username2 = username2;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getDatatable() {
		return datatable;
	}

	public void setDatatable(String datatable) {
		this.datatable = datatable;
	}


	
	




	public ListModel getColumns() {
		return columns;
	}

	public void setColumns(ListModel columns) {
		this.columns = columns;
	}

	public ListModel getSelectColumns() {
		return selectColumns;
	}

	public void setSelectColumns(ListModel selectColumns) {
		this.selectColumns = selectColumns;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
    
    
    
	

}
