package com.xfire.webservice;

import java.util.List;

import com.xfire.bean.ReaderBean;

public interface IReaderService {
	public ReaderBean getReader(String name,String password);  
    public List<ReaderBean> getReaders(); 
    public String example(String message);
}
