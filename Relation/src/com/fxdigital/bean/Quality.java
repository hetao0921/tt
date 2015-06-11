package com.fxdigital.bean;

import java.util.List;

public class Quality {

	private String qname;
	private boolean isreadonly;
	private String defaultvalue;
	private List<String> listValue;
	private List<String> listTest;
	public Quality() {
		super();
	}
	public Quality(String qname, boolean isreadonly, String defaultvalue,
			List<String> listValue, List<String> listTest) {
		super();
		this.qname = qname;
		this.isreadonly = isreadonly;
		this.defaultvalue = defaultvalue;
		this.listValue = listValue;
		this.listTest = listTest;
	}
	public String getQname() {
		return qname;
	}
	public void setQname(String qname) {
		this.qname = qname;
	}
	public boolean isIsreadonly() {
		return isreadonly;
	}
	public void setIsreadonly(boolean isreadonly) {
		this.isreadonly = isreadonly;
	}
	public String getDefaultvalue() {
		return defaultvalue;
	}
	public void setDefaultvalue(String defaultvalue) {
		this.defaultvalue = defaultvalue;
	}
	public List<String> getListValue() {
		return listValue;
	}
	public void setListValue(List<String> listValue) {
		this.listValue = listValue;
	}
	public List<String> getListTest() {
		return listTest;
	}
	public void setListTest(List<String> listTest) {
		this.listTest = listTest;
	}
	
	public String getRealValue(){
		if(defaultvalue!=null){
			for(int i=0;i<listTest.size();i++){
				if(defaultvalue.equals(listTest.get(i))){
					return this.listValue.get(i);
				}
			}
		}
		return null;
	}
	
	public String getValueTest(String real){
//		System.out.println("real:"+real);
		for(int i=0;i<this.listValue.size();i++){
//			System.out.println(listValue.get(i)+"==="+listTest.get(i));
			if(real.equals(listValue.get(i)))
				return listTest.get(i);
		}
		return null;
	}
} 
