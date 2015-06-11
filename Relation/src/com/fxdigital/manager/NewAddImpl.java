package com.fxdigital.manager;

import org.apache.log4j.Logger;

public class NewAddImpl {
	
	private static final Logger log = Logger.getLogger(NewAddImpl.class);
	
	private static NewAddImpl newimpl;
	
	synchronized public static NewAddImpl getNewAddInstance(){
		if(newimpl==null)
			newimpl = new NewAddImpl();
		return newimpl;
	}
	
	private NewAddImpl(){}
	
	public void shutDevice(){
		try{
			if (System.getProperty("os.name").equals("Linux")){
				Runtime.getRuntime().exec("reboot");
			}else{
				Runtime.getRuntime().exec("shutdown -r");
			}
			log.info("设备重启...");
		}catch(Exception e){
			e.printStackTrace();
		}
	} 
	
	/**
	 * 矩阵刷新
	 * @return
	 */
	public boolean hikmatrixReflush(){
		log.info("开始矩阵更新。。。");
		try{
			if (System.getProperty("os.name").equals("Linux")){
				java.lang.Process process = Runtime.getRuntime().exec("/usr/local/bin/srvldr restart ipmatrix");
				process.waitFor();
			}else{
				Runtime.getRuntime().exec("srvldr kill ipmatrix");
				Runtime.getRuntime().exec("srvldr restart ipmatrix");
			}
			log.info("矩阵更新完成。");
		}catch(Exception e){
			log.error("矩阵更新报错。。。",e);
			return false;
		}
		return true;
	}
	
}
