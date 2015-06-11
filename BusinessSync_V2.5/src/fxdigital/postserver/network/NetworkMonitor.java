package fxdigital.postserver.network;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fxdigital.db.DbManager;
import fxdigital.db.SubStatus;
import fxdigital.db.SupStatus;

/**
 * 级联申请监控器
 * @author fucz
 * @version 2014-7-14
 */
@Component
public class NetworkMonitor {
	
	private static final Logger log = Logger.getLogger(NetworkMonitor.class);
	public static final int INTERVAL_TIME = 5;
	
	public static final int APPLYING = 1;
	public static final int AGREEING = 2;
	public static final int REJECTING = 3;
	public static final int CANCELING = 4;
	public static final int UNCHECKED = 5;
	public static final int DELETING = 6;
	public static final int APPLIED = 11;
	public static final int AGREED = 21;
	public static final int REJECTED = 31;
	public static final int CANCELED = 41;
	public static final int DELETED = 61;
	
	@Autowired
	private DbManager dbManager;
	@Autowired
	private SubRequest subRequest;
	@Autowired
	private SupRequest supRequest;
	
	@PostConstruct
	public void monitor(){
		new Thread(this.new Monitor()).start();
	}
	
	//监控器
	class Monitor implements Runnable{

		@Override
		public void run() {
			while(true){
				try {
					Thread.sleep(INTERVAL_TIME*1000);
				} catch (InterruptedException e) {
				}
				monitorSupTab();
				monitorSubTab();
			}
		}
		
	}
	
	//上级审批下级的申请
	private void monitorSubTab(){
		List<SubStatus> subStatuses = dbManager.getAllSubStatus();
		if(subStatuses != null && subStatuses.size() > 0){
			for(SubStatus ss : subStatuses){
				if(ss.getStatus() == AGREEING
						|| ss.getStatus() == REJECTING
						|| ss.getStatus() == DELETING){
					supRequest.request(ss);
				}
			}
		}
	}
	
	//申请成为下级
	private void monitorSupTab(){
		List<SupStatus> temp = dbManager.getAllSupStatus();
		List<SupStatus> applies = new ArrayList<SupStatus>();
		if(temp != null && temp.size() > 0){
			for(SupStatus ss : temp){
				if(ss.getStatus() == CANCELING){
					subRequest.request(ss);
				}
				if(ss.getStatus() == APPLYING){
					applies.add(ss);
				}
			}
			if(applies.size() > 1){
				log.error("不允许存在多条申请上级的记录！");
				return;
			}
			if(applies.size() == 1){
				SupStatus sup = applies.get(0);
				SubStatus sub = dbManager.getSubStatusFromIP(sup.getMqIP());
				if(sub != null
						&& (UNCHECKED == sub.getStatus()
								|| AGREEING == sub.getStatus()
								|| AGREED == sub.getStatus())
						){
					log.error("所申请的上级已是或将是本级的下级，禁止申请！");
					return;
				}
				subRequest.request(applies.get(0));
			}
		}
	}
	
}
