package com.ui.listener;

import com.db.util.SerlizeObject;
import com.serlize.pojo.StatusSerlize;

public class OpenListener {
	public StatusSerlize statusSerlize;
	public String fileName;
	public OpenListener(StatusSerlize statusSerlize,String fileName){
		this.statusSerlize=statusSerlize;
		this.fileName=fileName;
		
	}
	
	
	public StatusSerlize deSelizedObject(){
		SerlizeObject serlizeObject=new SerlizeObject();
		return (StatusSerlize) serlizeObject.deSerlizeObject(fileName, statusSerlize);
	}
}
