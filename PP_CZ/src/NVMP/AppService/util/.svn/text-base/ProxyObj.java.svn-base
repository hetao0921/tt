package NVMP.AppService.util;

import org.misc.log.*;

/**
 * by zzw
 * ÓÃÀŽœøÐÐŽŠÀíŽúÀíÀàÓÃµÄ¹¹ÔìÌå
 * */
public class ProxyObj {
     
    public enum typeSelect {
       function,event; 
    }

	//ÓòÃû
	String domainName;
	//Ãû×Ö
	String name;
	//ÀàÐÍ 
	typeSelect type;	

	//·µ»ØÀàÐÍ
	ParamInfo[]  returnType;
	
	//²ÎÊý×é
	ParamInfo[] paramInfoArray;
	
	public ProxyObj() {
		
	}
	
	
	/**
	 * domainName : ÓòÃû
	 * name       : ·œ·šÃû»òÕßÊÂŒþÃû
	 * type       : ÊÂŒþ»òÕß·œ·š
	 * returnType : ·µ»ØµÄÀàÐÍ
	 * paramInfoArray : ²ÎÊý×é
	 * */
	public ProxyObj(String domainName,String name,typeSelect type,ParamInfo[] returnType,ParamInfo[] paramInfoArray) {
		this.domainName = domainName;
		this.name = name;
		this.type = type;
		this.returnType = returnType;
		this.paramInfoArray = paramInfoArray;
	}


	public String getDomainName() {
		return domainName;
	}


	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public typeSelect getType() {
		return type;
	}


	public void setType(typeSelect type) {
		this.type = type;
	}


	public ParamInfo[] getReturnType() {
		return returnType;
	}


	public void setReturnType(ParamInfo[] returnType) {
		this.returnType = returnType;
	}


	public ParamInfo[] getParamInfoArray() {
		return paramInfoArray;
	}


	public void setParamInfoArray(ParamInfo[] paramInfoArray) {
		this.paramInfoArray = paramInfoArray;
	}


	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		boolean b = false;
		try {
			ProxyObj po = (ProxyObj)obj;
			if(po.getDomainName().equals(this.getDomainName())) b=true;
			
		} catch(Exception e) {
			
			b=false;
		}
		return b;
	}
	
	
	
	//ps:ÏÂÃæŸÍÊÇÆœÊ±ÒªÓÃµÄÌØÊâŽŠÀí£¬ÔÝÊ±¿ÕÎ»
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
