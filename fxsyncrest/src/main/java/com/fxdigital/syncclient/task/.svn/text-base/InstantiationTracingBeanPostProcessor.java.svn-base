package com.fxdigital.syncclient.task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.fxdigital.syncclient.service.AutoIncrementUploadTask;
import com.fxdigital.syncclient.service.AutoSendTimerTask;
import com.fxdigital.syncclient.service.BackupServer;

public class InstantiationTracingBeanPostProcessor implements ApplicationListener<ContextRefreshedEvent> {
	private Log logger=LogFactory.getLog(InstantiationTracingBeanPostProcessor.class);
	@Autowired
	private AutoSendTimerTask autoSendTimerTask;
	@Autowired
	private BackupServer backupServer;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
    	if(event.getApplicationContext().getParent() == null){
    		//root application context 没有parent，他就是老大.  
//    		autoSendTimerTask.beginInit();
    		
    		try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		boolean isBackup=backupServer.isBackup();
    		if(isBackup){
    			logger.info("备用服务器，不启动自动侦听。");
    			return;
    		}
    			InitThread initThread=new InitThread();
        		new Thread(initThread).start();
    		
    		
       }  
 }

    
    public class InitThread implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			autoSendTimerTask.beginInit();
		}
    	
    }	
    
}