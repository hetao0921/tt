package com.ui.listener;

import com.db.util.SerlizeObject;
import com.serlize.pojo.StatusSerlize;
import com.ui.base.ContactEditor;

public class SaveAsListener {
	public StatusSerlize statusSerlize;
	public String fileDirectory;
	public String fileName;
	public SaveAsListener(String fileName,String fileDirectory,StatusSerlize statusSerlize){
		this.fileName=fileName;
		this.fileDirectory=fileDirectory;
		this.statusSerlize=statusSerlize;
		
	}
	
	public void serlizeObject(){
		SerlizeObject serlizeObject=new SerlizeObject();
		serlizeObject.serlizedObject(fileName,fileDirectory,statusSerlize);
	}

}
