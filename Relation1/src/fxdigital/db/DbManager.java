package fxdigital.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fxdigital.db.dao.AutoTimeDao;
import fxdigital.db.dao.LocalCenterDao;
import fxdigital.db.dao.LocalRegisterCentersDao;
import fxdigital.db.dao.SubApplyDao;
import fxdigital.db.dao.SupApplyDao;
import fxdigital.db.dao.SyncServersDao;

/**
 * 
 * @author fucz
 * @version 2014-7-21
 */
@Component
public class DbManager {
	
	@Autowired
	private LocalCenterDao localCenterDao;
	@Autowired
	private LocalRegisterCentersDao localRegisterCentersDao;
	@Autowired
	private SubApplyDao subApplyDao;
	@Autowired
	private SupApplyDao supApplyDao;
	@Autowired
	private AutoTimeDao autoTimeDao;
	@Autowired
	private SyncServersDao syncServersDao;
	
	
	public void setLocalIp(String ip, String gate,String mask){
		localCenterDao.updateIpInfo(ip, gate, mask);
	}
	
	/**
	 * 获得本级服务器信息
	 * @return 本级服务器信息
	 */
	public LocalCenter getLocalCenter(){
		return localCenterDao.query();
	}
	
	/**
	 * 获得对应MqIP的同步服务器
	 * @return 同步服务器信息
	 */
	public SyncServer getSyncServerFromMqIP(String mqIP){
		return syncServersDao.queryFromMqIP(mqIP);
	}
	
	/**
	 * 获得本级自动同步时间
	 * @return 自动同步时间
	 */
	public String getAutoTime(){
		return autoTimeDao.query();
	}
	/**
	 * 设置自动同步时间
	 * @return 是否成功
	 */
	public int setAutoTime(String autotime){
		return autoTimeDao.updateAutotime(autotime);
	}
	
	/**
	 * 获得所有本地注册中心
	 * @return 所有本地中心注册信息
	 */
	public List<RegisterCenter> getAllLocalRegisterCenter(){
		return localRegisterCentersDao.query();
	}
	
	/**
	 * 设置MQ服务器信息
	 * @param mqIP MQ的IP
	 * @param mqPort MQ的端口
	 * @return true：设置成功
	 * 			false：设置失败
	 */
	public boolean setMqInfo(String mqIP,int mqPort){
		String centerID = localCenterDao.query().getId();
		int num = localCenterDao.update(centerID, mqIP, mqPort);
		if(num == 1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 获得所有申请上级信息
	 * @return
	 */
	public List<SupStatus> getSupStatus(){
		List<SupStatus> all = supApplyDao.query();
		if(all == null){
			return null;
		}
		Map<String,SupStatus> map = new HashMap<String,SupStatus>();
		for(SupStatus temp : all){
			if(!map.containsKey(temp.getMqIP())){
				map.put(temp.getMqIP(), temp);
			}else{
				SupStatus ss = map.get(temp.getMqIP());
				if(temp.getApplyTime().after(ss.getApplyTime())){
					map.put(temp.getMqIP(), temp);
				}
			}
		}
		List<SupStatus> supStatuses = new ArrayList<SupStatus>();
		for(SupStatus temp : map.values()){
			supStatuses.add(temp);
		}
		return supStatuses;
	}
	
	/**
	 * 获得申请上级信息
	 * @return
	 */
	public SupStatus getSupStatus(String ip){
		// 取时间最大的一个
		List<SupStatus> supStatuses = supApplyDao.query(ip);
		if(supStatuses == null){
			return null;
		}
		SupStatus supStatus = supStatuses.get(0);
		for(SupStatus ss : supStatuses){
			if(ss.getApplyTime().after(supStatus.getApplyTime())){
				supStatus = ss;
			}
		}
		return supStatus;
	}
	
	/**
	 * 增加上级
	 * @param supStatus
	 * @return
	 */
	public boolean addSupStatus(SupStatus supStatus){
		int num = supApplyDao.insert(supStatus);
		if(num == 1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 改变申请上级的状态
	 * @param ip
	 * @param status
	 * @return
	 */
	public boolean changeSupStatus(String ip,String status){
		SupStatus supStatus = getSupStatus(ip);
		int num = supApplyDao.update(ip,supStatus.getApplyTime(), status);
		if(num == 1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 获得所有下级申请信息
	 * @return
	 */
	public List<SubStatus> getSubStatus(){
		List<SubStatus> all = subApplyDao.query();
		if(all == null){
			return null;
		}
		Map<String,SubStatus> map = new HashMap<String,SubStatus>();
		for(SubStatus temp : all){
			if(!map.containsKey(temp.getServerID())){
				map.put(temp.getServerID(), temp);
			}else{
				SubStatus ss = map.get(temp.getServerID());
				if(temp.getApplyTime().after(ss.getApplyTime())){
					map.put(temp.getServerID(), temp);
				}
			}
		}
		List<SubStatus> subStatuses = new ArrayList<SubStatus>();
		for(SubStatus temp : map.values()){
			subStatuses.add(temp);
		}
		return subStatuses;
	}
	
	/**
	 * 获得下级申请信息
	 * @return
	 */
	public SubStatus getSubStatusFromID(String id){
		// 取时间最大的一个
		List<SubStatus> subStatuses = subApplyDao.query(id);
		if(subStatuses == null){
			return null;
		}
		SubStatus subStatus = subStatuses.get(0);
		for(SubStatus ss : subStatuses){
			if(ss.getApplyTime().after(subStatus.getApplyTime())){
				subStatus = ss;
			}
		}
		return subStatus;
	}
	
	/**
	 * 获得下级申请信息
	 * @return
	 */
	public SubStatus getSubStatusFromIP(String ip){
		// 取时间最大的一个
		List<SubStatus> subStatuses = subApplyDao.queryFromIP(ip);
		if(subStatuses == null){
			return null;
		}
		SubStatus subStatus = subStatuses.get(0);
		for(SubStatus ss : subStatuses){
			if(ss.getApplyTime().after(subStatus.getApplyTime())){
				subStatus = ss;
			}
		}
		return subStatus;
	}
	
	/**
	 * 改变下级申请的状态
	 * @param id
	 * @param status
	 * @return
	 */
	public boolean changeSubStatus(String id,String status){
		SubStatus subStatus = getSubStatusFromID(id);
		int num = subApplyDao.update(id,subStatus.getApplyTime(), status);
		if(num == 1){
			return true;
		}else{
			return false;
		}
	}
	
}
