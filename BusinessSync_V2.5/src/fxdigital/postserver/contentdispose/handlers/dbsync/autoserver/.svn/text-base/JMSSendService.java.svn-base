/**
 * 
 */
package fxdigital.postserver.contentdispose.handlers.dbsync.autoserver;

import java.util.HashMap;

import javax.jms.Destination;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import fxdigital.db.LocalCenter;
import fxdigital.db.dao.JMSSendDao;
import fxdigital.db.dao.LocalCenterDao;

/**
 * @author lizehua
 *
 */
@Service
public class JMSSendService {
	
	private Log logger = LogFactory.getLog(JMSSendService.class);
	

	
	@Autowired
	private DualService dualServeice;

	 @Autowired  
	 private JMSSendDao jMSSendDao;  
	 @Autowired
	 private LocalCenterDao localCenterDao;
	 
	    @Autowired  
	    @Qualifier("targetName")  
	    private Destination destination;
	    
	    String receiveFlag="receiveFlag";
	    


	    
	    public boolean sendMessage(){
//			String targetIp="192.168.1.222";
//			int targetport=5050;
//			String targetName= "nvmp.clientQueue";
			
//			BaseSender bs =new BaseSender(targetIp, targetport, false); 
			
			String sessionid="center154@001";//中心ID
			String centerid="center154@001";
			String url = "DBSynchronization.ClientQueueNowVerson";
			byte[] data=null;
			String receiveId=null;
			
			// 参数:
			HashMap<String, Object> hp = new HashMap<String, Object>();
			hp.put("sessionid", sessionid);
			hp.put("centerid", centerid);
			
			
			Msg m = new Msg();
			m.set_Url(url);
			m.AddParams(hp);		
	    	jMSSendDao.sendMessage(destination, m, data, receiveId);
	    	return true;
	    }

	    
		
		public boolean SendAutoSyncCommand(){
			LocalCenter localCenter=localCenterDao.query();
	
			String sessionid=null;
			String centerid=null;
			String ip=null;
			if(null!=localCenter){
				 ip=String.valueOf(localCenter.getIp());
				 sessionid=String.valueOf(localCenter.getName());
				 centerid=String.valueOf(localCenter.getId());
			}
			
		
			String url = "DBSynchronization.ClientQueueNowVerson";
			byte[] data=null;
			String receiveId=receiveFlag;
			
			// 参数:
			HashMap<String, Object> hp = new HashMap<String, Object>();
			hp.put("sessionid", sessionid);
			hp.put("centerid", centerid);
			hp.put("ip", ip);
			hp.put("centertype", "new");
			
			Msg m = new Msg();
			m.set_Url(url);
			m.AddParams(hp);

			jMSSendDao.sendMessage(destination, m, data, receiveId);
			logger.info("发送自动同步消息  ");
			return true;

		}
		
		

		
		
}
