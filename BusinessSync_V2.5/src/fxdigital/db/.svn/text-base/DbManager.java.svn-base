package fxdigital.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fxdigital.db.dao.DeviceDao;
import fxdigital.db.dao.LocalCenterDao;
import fxdigital.db.dao.LocalRegisterCentersDao;
import fxdigital.db.dao.NetworkDao;
import fxdigital.db.dao.RegisterCentersDao;
import fxdigital.db.dao.SubApplyDao;
import fxdigital.db.dao.SupApplyDao;
import fxdigital.db.dao.SyncMqDao;
import fxdigital.db.dao.SyncServersDao;

/**
 * 操作数据库
 * 
 * @author fucz
 * @version 2014-6-12
 */
@Component
public class DbManager {
	
//	private static final Logger log = Logger.getLogger(DbManager.class);
	
	@Autowired
	private LocalCenterDao localCenterDao;
	@Autowired
	private DeviceDao deviceDao;
	@Autowired
	private SyncMqDao syncMqDao;
	@Autowired
	private SyncServersDao syncServersDao;
	@Autowired
	private LocalRegisterCentersDao localRegisterCentersDao;
	@Autowired
	private NetworkDao networkDao;
	@Autowired
	private RegisterCentersDao registerCentersDao;
	@Autowired
	private SubApplyDao subApplyDao;
	@Autowired
	private SupApplyDao supApplyDao;
	
	/**
	 * 获得本级服务器信息
	 * @return 本级服务器信息
	 */
	public LocalCenter getLocalCenter(){
		return localCenterDao.query();
	}
	
	/**
	 * 设置MQ服务器信息
	 * @param mqIP MQ的IP
	 * @param mqPort MQ的端口
	 * @return true：设置成功
	 * 			false：设置失败
	 */
	@Deprecated
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
	 * 获得中心绑定的同步MQ
	 * @return 同步MQ信息
	 */
	public SyncMq getSyncMq(){
		return syncMqDao.query();
	}
	
	/**
	 * 获得对应ID的同步服务器
	 * @return 同步服务器信息
	 */
	public SyncServer getSyncServer(String id){
		return syncServersDao.query(id);
	}
	
	/**
	 * 设置本级服务器同步信息
	 * @return true：设置成功
	 * 			false：设置失败
	 */
	public boolean setSyncLocalServer(LocalCenter localCenter){
		deleteLocalSyncServer();
		SyncServer ss = new SyncServer();
		ss.setServerID(localCenter.getId());
		ss.setServerIP(localCenter.getIp());
		ss.setServerName(localCenter.getName());
		SyncMq sm = new SyncMq();
		sm.setIp(localCenter.getSyncServerIP());
		sm.setPort(localCenter.getSyncServerPort());
		sm.setPostAddress(localCenter.getSyncServerPostAddress());
		ss.setMq(sm);
		int num = syncServersDao.insert("local", ss);
		if(num == 1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 增加同步服务器
	 * @param syncServer 同步服务器信息
	 * @return true：增加成功
	 * 			false：增加失败
	 */
	public boolean addSyncServer(SyncServer syncServer){
		int num = syncServersDao.insert(null, syncServer);
		if(num == 1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 删除同步服务器
	 * @param id 同步服务器ID
	 * @return true：删除成功
	 * 			false：删除失败
	 */
	public boolean deleteSyncServer(String id){
		int num = syncServersDao.deleteServerID(id);
		if(num == 1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 删除本地同步服务器
	 * @return true：删除成功
	 * 			false：删除失败
	 */
	public boolean deleteLocalSyncServer(){
		int num = syncServersDao.deleteSyncID("local");
		if(num == 1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 获得DeviceSN
	 * @param deviceType
	 * @return DeviceSN
	 */
	public String getDeviceSN(String deviceType){
		return deviceDao.queryForDeviceSN(deviceType);
	}
	
	/**
	 * 判断中心是否是本地注册中心
	 * @param centerID 中心ID
	 * @return true：是本地注册中心
	 * 			false：不是本地注册中心
	 */
	public boolean isLocalRegisterCenterExist(String centerID){
		return localRegisterCentersDao.isExist(centerID);
	}
	
	/**
	 * 获得本地注册中心
	 * @param centerID 中心ID
	 * @return 中心注册信息
	 */
	public RegisterCenter getLocalRegisterCenterInfo(String centerID){
		return localRegisterCentersDao.query(centerID);
	}
	
	/**
	 * 获得所有本地注册中心
	 * @return 所有本地中心注册信息
	 */
	public List<RegisterCenter> getAllLocalRegisterCenter(){
		return localRegisterCentersDao.query();
	}
	
	/**
	 * 删除本地注册中心
	 * @param centerID 中心ID
	 * @return true：删除成功
	 * 			false：删除失败
	 */
	public boolean deleteLocalRegisterCenterInfo(String centerID){
		int num = localRegisterCentersDao.delete(centerID);
		if(num == 1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 更新本地中心注册数据
	 * @param registerCenter 中心注册信息
	 * @return true：更新成功
	 * 			false：更新失败
	 */
	public boolean updateLocalRegisterCenterInfo(RegisterCenter registerCenter){
		int num = localRegisterCentersDao.update(registerCenter);
		if(num == 1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 添加本地注册中心数据
	 * @param registerCenter 中心注册信息
	 * @return true：添加成功
	 * 			false：添加失败
	 */
	public boolean addLocalRegisterCenterInfo(RegisterCenter registerCenter){
		int num = localRegisterCentersDao.insert(registerCenter);
		if(num == 1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 判断中心是否存在于注册中心同步表
	 * @param centerID 中心ID
	 * @return true：存在于注册中心同步表
	 * 			false：不存在于注册中心同步表
	 */
	public boolean isSyncRegisterCenterExist(String centerID){
		if(registerCentersDao.query(centerID) != null){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 获得同步表中的注册中心
	 * @param centerID 中心ID
	 * @return 中心注册信息
	 */
	public RegisterCenter getSyncRegisterCenter(String centerID){
		return registerCentersDao.query(centerID);
	}
	
	/**
	 * 删除同步表中的注册中心
	 * @param centerID 中心ID
	 * @return true：删除成功
	 * 			false：删除失败
	 */
	public boolean deleteSyncRegisterCenter(String centerID){
		int num = registerCentersDao.delete(centerID);
		if(num == 1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 更新注册中心同步表
	 * @param registerCenter 中心注册信息
	 * @param syncID 同步ID
	 * @return true：更新成功
	 * 			false：更新失败
	 */
	public boolean updateSyncRegisterCenter(
			RegisterCenter registerCenter,String syncID){
		int num = registerCentersDao.update(registerCenter,syncID);
		if(num == 1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 添加到注册中心同步表
	 * @param registerCenter 中心注册信息
	 * @return true：添加成功
	 * 			false：添加失败
	 */
	public boolean addSyncRegisterCenter(RegisterCenter registerCenter){
		int num = registerCentersDao.insert(null, registerCenter);
		if(num == 1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 获得上级同步服务器的ID
	 * @param subID 下级同步服务器ID
	 * @return 上级同步服务器ID
	 */
	public String getSuperID(String subID){
		return networkDao.querySuperID(subID);
	}
	
	/**
	 * 获得指定上级的所有下级同步服务器ID
	 * @param superID 上级同步服务器ID
	 * @return 所有下级同步服务器的ID
	 */
	public List<String> getALlSubIDs(String superID){
		return networkDao.querySubIDs(superID);
	}
	
	/**
	 * 获得所有同步服务器关联信息
	 * @return 所有同步服务器之间的关联信息
	 */
	public List<ServerRelation> getAllServerRelations(){
		return networkDao.query();
	}
	
	/**
	 * 增加本级与本级的上级之间的关联信息
	 * @param supStatus 上级同步服务器信息
	 * @return true：增加成功
	 * 			false：增加失败
	 */
	public boolean addLocalAndSupRelation(SupStatus supStatus){
		LocalCenter local = getLocalCenter();
		int num = networkDao.insert(local.getId(),local.getIp(),
				supStatus.getServerID(),supStatus.getServerIP());
		if(num == 1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 删除对应下级的上下级关联信息
	 * @param subID 下级同步服务器ID 
	 * @return true：删除成功
	 * 			false：删除失败
	 */
	public boolean deleteSupAndSubRelation(String subID){
		int num = networkDao.deleteSub(subID);
		if(num == 1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 覆盖同步服务器关联数据
	 * @param syncID 同步ID 
	 * @param serverRelations 关联信息
	 * @return true：覆盖成功
	 * 			false：覆盖失败
	 */
	public boolean coverServerRelations(String syncID,
			List<ServerRelation> serverRelations){
//		try{
			networkDao.deleteSyncID(syncID);
			if(serverRelations != null){
				for(ServerRelation mq : serverRelations){
					//mqNetworkInfoDao.deleteSub(mq.getSubID());
					networkDao.insert(syncID,mq);
				}
			}
			return true;
//		}catch(Exception e){
//			Log4jUtil.error(this.getClass(),"覆盖同步服务器级联数据时发生异常！",e);
//			return false;
//		}
	}
	
	/**
	 * 获得所有同步服务器信息
	 * @return 所有同步服务器信息
	 */
	public List<SyncServer> getAllSyncServers(){
		return syncServersDao.query();
	}
	
	/**
	 * 覆盖同步服务器信息。删除对应同步ID的同步服务器信息，覆盖已存在的同步服务器信息，增加新的同步服务器信息
	 * @param syncID 同步ID
	 * @param syncServers 同步服务器信息的集合
	 * @return true：覆盖成功
	 * 			false：覆盖失败
	 */
	public boolean coverSyncServers(String syncID,List<SyncServer> syncServers){
//		try {
			syncServersDao.deleteSyncID(syncID);
			if(syncServers != null){
				for(SyncServer ss : syncServers){
					syncServersDao.deleteServerID(ss.getServerID());
					syncServersDao.insert(syncID, ss);
				}
			}
			return true;
//		} catch (Exception e) {
//			Log4jUtil.error(this.getClass(),"覆盖同步服务器信息时发生异常！",e);
//			return false;
//		}
	}
	
	/**
	 * 获得所有注册的中心信息
	 * @return 所有注册的中心信息
	 */
	public List<RegisterCenter> getAllRegisterCenters(){
		return registerCentersDao.query();
	}
	
	/**
	 * 覆盖注册的中心信息。删除对应同步ID的注册的中心信息，覆盖已存在的注册的中心信息，增加新的注册的中心信息
	 * @param syncID 同步ID
	 * @param registerCenters 注册中心的集合
	 * @return true：覆盖成功
	 * 			false：覆盖失败
	 */
	public boolean coverRegisterCenters(String syncID,
			List<RegisterCenter> registerCenters){
//		try {
			registerCentersDao.deleteFromSyncID(syncID);
			if(registerCenters != null){
				for(RegisterCenter rci : registerCenters){
					RegisterCenter old_rci = registerCentersDao.query(
							rci.getCenterID());
					if(old_rci != null){
						if(rci.getRegisterTime().after(old_rci.getRegisterTime())){
							registerCentersDao.delete(rci.getCenterID());
							registerCentersDao.insert(syncID, rci);
						}
					}else{
						registerCentersDao.insert(syncID, rci);
					}
				}
			}
			return true;
//		} catch (Exception e) {
//			Log4jUtil.error(this.getClass(),"覆盖注册的中心信息时发生异常！",e);
//			return false;
//		}
	}
	
	/**
	 * 获得所有申请的上级信息
	 * @return 所有申请的上级信息
	 */
	public List<SupStatus> getAllSupStatus(){
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
	 * 获得对应MQIP的申请上级的信息
	 * @return 申请上级的信息
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
	 * 改变申请上级的状态
	 * @param ip 上级的MQIP
	 * @param status 申请状态
	 * @return true：变更成功
	 * 			false：变更失败
	 */
	public boolean changeSupStatus(String ip,int status){
		SupStatus supStatus = getSupStatus(ip);
		int num = supApplyDao.update(ip,supStatus.getApplyTime(), status);
		if(num == 1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 更新申请的上级信息
	 * @param supStatus 申请的上级信息
	 * @return true：更新成功
	 * 			false：更新失败
	 */
	public boolean updateSupStatus(SupStatus supStatus){
		SupStatus ss = getSupStatus(supStatus.getMqIP());
		int num = supApplyDao.update(supStatus, ss.getApplyTime());
		if(num == 1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 增加申请上级的信息
	 * @param supStatus 申请的上级信息
	 * @return true：增加成功
	 * 			false：增加失败
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
	 * 获得所有申请过来的下级信息
	 * @return 申请过来的下级信息的集合
	 */
	public List<SubStatus> getAllSubStatus(){
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
	 * 获得申请过来的下级信息
	 * @param subID 下级同步服务器ID
	 * @return 申请过来的下级信息
	 */
	public SubStatus getSubStatusFromID(String subID){
		// 取时间最大的一个
		List<SubStatus> subStatuses = subApplyDao.queryFromID(subID);
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
	 * 获得申请过来的下级信息
	 * @param subIP 下级同步服务器IP
	 * @return 申请过来的下级信息
	 */
	public SubStatus getSubStatusFromIP(String subIP){
		// 取时间最大的一个
		List<SubStatus> subStatuses = subApplyDao.queryFromIP(subIP);
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
	 * 改变申请过来的下级的状态
	 * @param subID 下级同步服务器ID
	 * @param status 申请状态
	 * @return true：变更成功
	 * 			false：变更失败
	 */
	public boolean changeSubStatus(String subID,int status){
		SubStatus subStatus = getSubStatusFromID(subID);
		int num = subApplyDao.update(subID,subStatus.getApplyTime(), status);
		if(num == 1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 增加申请过来的下级信息
	 * @param subStatus 申请过来的下级信息
	 * @return true：增加成功
	 * 			false：增加失败
	 */
	public boolean addSubStatus(SubStatus subStatus){
		int num = subApplyDao.insert(subStatus);
		if(num == 1){
			return true;
		}else{
			return false;
		}
	}
	
}
