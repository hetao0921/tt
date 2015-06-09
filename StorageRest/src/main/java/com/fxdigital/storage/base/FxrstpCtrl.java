package com.fxdigital.storage.base;

import org.springframework.stereotype.Component;

import com.fxdigital.fxnvr.FxnvrCtrl;

@Component
public class FxrstpCtrl {
	
	FxnvrCtrl fxnvrCtrl=null;
	
	public FxnvrCtrl getInstance(){
		if(null==fxnvrCtrl){
			fxnvrCtrl=new FxnvrCtrl();
		}
		return fxnvrCtrl;
	}
	
	public boolean Init(){
//		return true;
		return getInstance().Init();
	}
	
	
	public boolean Cleanup(){
		return getInstance().Cleanup();
	}
	
	
	public int StartRecord(String rtspURL, String fileName){
		System.out.println("调用底层实现");
		
//		return 1;
		return getInstance().StartRecord(rtspURL, fileName);
	}

	
	public boolean StopRecord(int handle){
		return getInstance().StopRecord(handle);
		//return true;
	}
	
	
	public int GetFileDuration(String fileName){
		return getInstance().GetFileDuration(fileName);
	}
	
	
	public boolean RepairFile(String fileName) {
 		return getInstance().RepairFile(fileName);
		//return true;
	}
}
