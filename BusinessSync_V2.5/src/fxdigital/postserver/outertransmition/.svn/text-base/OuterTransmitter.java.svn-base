package fxdigital.postserver.outertransmition;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import fxdigital.db.DbManager;
import fxdigital.db.RegisterCenter;
import fxdigital.db.SyncServer;
import fxdigital.rpc.Mail;

/**
 * 非本级MQ发送器
 * @author fucz
 * @version 2014-7-1
 */
@Component
public class OuterTransmitter {
	
	private static final Logger log = Logger.getLogger(OuterTransmitter.class);
	
	@Autowired
	private DbManager dbManager;
	private String localCenterID;
	private Map<String,ITransmitter> transmitters;
	private Mail mail;
	
	/**
	 * 注册分发器
	 * @param mode 发送模式
	 * @param transmitter 分发器
	 */
	public void register(String mode,ITransmitter transmitter){
		transmitters.put(mode, transmitter);
	}
	
	@PostConstruct
	public void init(){
//		System.out.println(dbManager.getLocalCenter()==null);
		localCenterID = dbManager.getLocalCenter().getId();
		transmitters = new HashMap<String,ITransmitter>();
	}
	
	/**
	 * 根据发送模式选择对应的发送器
	 * @param mail 邮件
	 * @return true：分发成功
	 * 			false：分发失败
	 */
	public boolean sendOut(Mail mail){
		this.mail = mail;
		ITransmitter transmitter = transmitters.get(mail.getSendMode());
		return transmitter.sendOut(mail);
	}
	
	/**
	 * 计算路由
	 * @param destID 目的地
	 * @param isCenterID 目的地是否是中心服务器
	 * @return 路线的下一个同步服务器ID
	 */
	public String calRoute(String destID,boolean isCenterID){
		if(isCenterID){
			//检索中心注册同步表，判断centerID注册的MQ是否存在于以本级为根节点的级联体系
			RegisterCenter rci = dbManager.getSyncRegisterCenter(destID);
			if(rci == null ){
				return whenNoDestMailbox();
			}else{
				destID = rci.getServerID();
			}
		}
		//检索MQ信息同步表，判断从中心注册同步表中得到的数据是否正确
		SyncServer syncServer = dbManager.getSyncServer(destID);
		if(syncServer == null){
			return whenNoDestMailbox();
		}else{
			//判断得到的MQ是否是本级
			if(syncServer.getServerID().equals(localCenterID)){
				return syncServer.getServerID();
			}else{
				//获得得到的MQ的上级MQ的ID
				String superID = dbManager.getSuperID(syncServer.getServerID());
				String nextMailboxID = syncServer.getServerID();
				//循环向上级检索，直到检索到本级，输出检索路径上本级的下级ID
				while(superID != null){
					if(superID.equals(localCenterID)){
						return nextMailboxID;
					}
					nextMailboxID = superID;
					superID = dbManager.getSuperID(superID);
				}
				//没有检索到本级
				return whenNoDestMailbox();
			}
		}
		
	}
	
	//没找到目标
	private String whenNoDestMailbox(){
		String superID = dbManager.getSuperID(localCenterID);
		if(superID == null){
			log.warn("邮件已经到达最上级，停止传输！邮件内容："+mail);
			return null;
		}else{
			log.warn("没有找到目的邮箱，向上级发送邮件！");
			return superID;
		}
	}
	
}
