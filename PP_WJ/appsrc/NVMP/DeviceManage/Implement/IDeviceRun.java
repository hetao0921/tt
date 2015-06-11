package NVMP.DeviceManage.Implement;


import java.util.List;
import java.util.Map;

import javax.swing.tree.MutableTreeNode;


import NVMP.DeviceManage.Implement.devicectrl.DeviceCtrlManager;
import NVMP.Proxy.StateManageDomain;

import com.fxdigital.EcDevice.ctrl.DeviceCtrl;

public interface IDeviceRun {
	public Map<String,DeviceInstance>  getDeviceHp() ;
	public DeviceCtrlManager getDc();
	public StateManageDomain getSmd(); 
	public List<DeviceInstance> getNotAutoConnectionList();
	/**
	 * 获取设备服务器下所有的设备信息
	 * @return
	 */
	public MutableTreeNode getTreeModel();
	
	/**
	 * 实现树状视图节点点击时的操作
	 */
	public void treeSelected(String type,int id);
	
	/**
	 * 实现布防按钮点击时的操作
	 */
	public void getAlarm();
	
	/**
	 * 实现撤防按钮点击时的操作
	 * @param deviceId
	 * @param channId
	 * @param devCHType
	 */
	public void removeAlarm();
	
	/**
	 * 向本地硬盘中写入一条数据
	 * @param str
	 */
	public void writeFile(String str);
	
	/**
	 * 当点击节点时，显示对应的信息到文本框中
	 * 
	 * @param terminalId
	 * @param channelId
	 * @param alarmType
	 * @param states
	 * @param jTextField
	 */
	public void showNodeMsg(String terminalId, Integer channelId,
			Integer alarmType, Integer states);
	
	/**
	 * 当设备状态更改（布防、撤防、报警）时，向表格中插入一条数据
	 */
	public void insertAlarm(String TerminalId, Integer ChannelId,
			Integer AlarmType, Integer DeviceStatus);

	/**
	 * 当获取上下线事件的时候，就向表格中插入一条数据
	 * 
	 * @param TerminalId
	 * @param TerminalIP
	 * @param IsOnline
	 * @param model
	 */
	public void isOnLine(String TerminalId, String TerminalIP,Boolean IsOnline);
	
	
	public void insertTableServer(String[] msg);
	
	public boolean isConnectflag();
	
	
	public void putOnline(String deviceid, Boolean b);

	public boolean isOnline(String deviceid) ;
	
    void deviceLogin(String deviceid,Integer  loginid);
    
	void deviceLogout(String deviceid) ;
	
	void setTime(Integer year, Integer month, Integer day,
			Integer hour, Integer minute, Integer scond);
	void addCenterSource(String centerid);
	void removeCenterSource(String centerid);
	
}
