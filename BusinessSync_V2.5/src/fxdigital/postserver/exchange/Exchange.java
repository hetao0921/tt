package fxdigital.postserver.exchange;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fxdigital.db.DbManager;
import fxdigital.db.LocalCenter;
import fxdigital.mqcore.base.BaseSender;
import fxdigital.rpc.Mail;
import fxdigital.util.ArgsUtil;
import fxdigital.util.Log4jUtil;

/**
 * 邮件集合发送器
 * @author fucz
 * @version 2014-6-30
 */
@Component
public class Exchange {
	
	private static final String SUCCESS = "success";
	private static final String FAILED = "failed";
	private static final String CONNECTING = "connecting";
	private static Map<String,String> connects =
			new ConcurrentHashMap<String,String>();
	private static Map<String,Integer> aimConnectCount =
			new HashMap<String,Integer>();
	
	@Autowired
	private DbManager dbManager;
	private BaseSender sender;
	
	@PostConstruct
	public void init(){
		LocalCenter local = dbManager.getLocalCenter();
		sender = new BaseSender(local.getSyncServerIP(),
				local.getSyncServerPort(),true);
	}
	
	/**
	 * 发送邮件
	 * @param mail 邮件
	 * @return true：发送成功
	 * 			false：发送失败
	 */
	public boolean exchange(Mail mail){
		return sender.sendMessage(ArgsUtil.getExchangeAddress(),mail);
	}
	
	/**
	 * 直接发送到MQ
	 * @param mail 邮件
	 * @param ip MQIP
	 * @param port MQ端口
	 * @param channelName 通道名称
	 * @return true：发送成功
	 * 			false：发送失败
	 */
	public boolean sendIpMail(Mail mail,String ip,int port,String channelName){
		if(CONNECTING.equals(connects.get(ip))){
//			Log4jUtil.warn(getClass(), "目的地【"+ip+"】依然无法连接，IP邮件发送失败！");
			addAimConnectNum(ip);
			return false;
		}
		new Thread(new TryConnect(ip,port)).start();
		BaseSender ipSender = new BaseSender(ip,port,true);
		int num = 10;
		while(num > 0){
			num --;
			String state = connects.get(ip);
			if(SUCCESS.equals(state)){
				deleteAim(ip);
				Log4jUtil.warn(getClass(), "目的地【"+ip+"】连接成功！");
				return ipSender.sendMessage(channelName, mail);
			}else if(FAILED.equals(state)){
				Log4jUtil.warn(getClass(), "目的地【"+ip+"】连接失败，IP邮件发送失败！");
				return false;
			}
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
			}
		}
		addAimConnectNum(ip);
		Log4jUtil.warn(getClass(), "目的地【"+ip+"】无法连接，邮件静默发送！");
		return false;
	}
	
	/**
	 * 尝试连接MQ
	 * 
	 * @author fucz
	 * @version 2014-7-24
	 */
	class TryConnect implements Runnable{
		
		private String ip;
		private BaseSender ipSender;
		
		public TryConnect(String ip,int port){
			this.ip = ip;
			ipSender = new BaseSender(ip,port,true);
		}

		@Override
		public void run() {
			connects.put(ip, CONNECTING);
			if(ipSender.tryConnect()){
				connects.put(ip, SUCCESS);
			}else{
				connects.put(ip, FAILED);
			}
		}
		
	}
	
	public static int getAimConnectNum(String aim){
		Integer num = aimConnectCount.get(aim);
		if(num != null){
			return num;
		}else{
			return 0;
		}
	}
	
	private void addAimConnectNum(String aim){
		Integer num = aimConnectCount.get(aim);
		if(num != null){
			if(num > 9){
				aimConnectCount.put(aim, 10);
			}else{
				aimConnectCount.put(aim, num + 1);
			}
		}else{
			aimConnectCount.put(aim, 1);
		}
	}
	
	private void deleteAim(String aim){
		aimConnectCount.remove(aim);
	}
	
}
