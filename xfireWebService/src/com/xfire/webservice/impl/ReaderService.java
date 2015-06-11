package com.xfire.webservice.impl;

import java.util.ArrayList;
import java.util.List;

import com.xfire.bean.ReaderBean;
import com.xfire.webservice.IReaderService;

public class ReaderService implements IReaderService{
	 public ReaderBean getReader(String name,String password) {  
	        return new ReaderBean(name,password);  
	    }  
	      
	    public List<ReaderBean> getReaders(){  
	        List<ReaderBean> readerList = new ArrayList<ReaderBean>();  
	        readerList.add(new ReaderBean("shun1","123"));  
	        readerList.add(new ReaderBean("shun2","123"));  
	        return readerList;  
	    }  
	    
	    public String example(String message) {
	    	          return message;
	    	     }
}
