package NVMP.service.impl;

import java.util.ArrayList;
import java.util.List;

public class ServerIPConfigs {
	private List<ServerIPConfig> list;

	public List<ServerIPConfig> getList() {
		return list;
	}

	public void setList(List<ServerIPConfig> list) {
		this.list = list;
	}
	
	/**
	 * 获取本级中心信息
	 * @return
	 */
	public ServerIPConfig GetCenter(){
		for(ServerIPConfig sic:list){
			if(sic.getDeviceLevel().equals("本级服务")&&sic.getDeviceType().equals("FXH3120"))
				return sic;
		}
		ServerIPConfig sic = new ServerIPConfig();
		sic.setDeviceIp("");
		sic.setDeviceLevel("本级服务");
		sic.setDeviceType("FXH3120");
		sic.setDeviceId("");
		sic.setDeviceName("集中管理服务（中心管理服务）");
		sic.setDevicePort(1900);
		return sic;
	}
	
	/**
	 * 获取上级中心信息
	 * @return
	 */
	public ServerIPConfig GetParentCenter(){
		for(ServerIPConfig sic:list){
			if(sic.getDeviceLevel().equals("remote")&&sic.getDeviceType().equals("FXH3120"))
				return sic;
		}
		ServerIPConfig sic = new ServerIPConfig();
		sic.setDeviceIp("");
		sic.setDeviceLevel("remote");
		sic.setDeviceType("FXH3120");
		sic.setDeviceId("");
		sic.setDeviceName("集中管理服务（中心管理服务）");
		sic.setDevicePort(1900);
		return sic;
	}
	
	/**
	 * 获取上级同步服务信息
	 * @return
	 */
	public ServerIPConfig GetParentSync(){
		for(ServerIPConfig sic:list){
			if(sic.getDeviceLevel().equals("remote")&&sic.getDeviceType().equals("FXH3360"))
				return sic;
		}
		ServerIPConfig sic = new ServerIPConfig();
		sic.setDeviceIp("");
		sic.setDeviceLevel("remote");
		sic.setDeviceType("FXH3360");
		sic.setDeviceId("");
		sic.setDeviceName("集中配置管理服务（数据同步服务）");
		sic.setDevicePort(5050);
		return sic;
	}
	
	/**
	 * 获取本级同步服务信息
	 * @return
	 */
	public ServerIPConfig GetSync(){
		for(ServerIPConfig sic:list){
			if(sic.getDeviceLevel().equals("本级服务")&&sic.getDeviceType().equals("FXH3360"))
				return sic;
		}
		ServerIPConfig sic = new ServerIPConfig();
		sic.setDeviceIp("");
		sic.setDeviceLevel("本级服务");
		sic.setDeviceType("FXH3360");
		sic.setDeviceId("");
		sic.setDeviceName("集中配置管理服务（数据同步服务）");
		sic.setDevicePort(5050);
		return sic;
	}
	
	/**
	 * 获取8060服务器信息
	 * @return
	 */
	public ServerIPConfig GetServer8060(){
		for(ServerIPConfig sic:list){
			if(sic.getDeviceLevel().equals("本级服务")&&sic.getDeviceType().equals("FXH8060"))
				return sic;
		}
		ServerIPConfig sic = new ServerIPConfig();
		sic.setDeviceIp("");
		sic.setDeviceLevel("本级服务");
		sic.setDeviceType("FXH8060");
		sic.setDeviceId("");
		sic.setDeviceName("指挥调度服务器");
		sic.setDevicePort(0);
		return sic;
	}
	
	/**
	 * 获取流媒体转发服务器信息
	 * @return
	 */
	public List<ServerIPConfig> GetStream(){
		List<ServerIPConfig> ll = new ArrayList<ServerIPConfig>();
		for(ServerIPConfig sic:list){
			if(sic.getDeviceLevel().equals("本级服务")&&sic.getDeviceType().equals("FXH3320"))
				ll.add(sic);
		}
		int temp = 8-ll.size();
		for(int i=0;i<temp;i++){
			ServerIPConfig sic = new ServerIPConfig();
			sic.setDeviceIp("");
			sic.setDeviceLevel("本级服务");
			sic.setDeviceType("FXH3320");
			sic.setDeviceId("");
			sic.setDeviceName("音视频交换（流媒体服务）");
			sic.setDevicePort(5050);
			ll.add(sic);
		}
		return ll;
	}
	
//	public void setStream(){}
//	public void setServer8060(){}
//	public void setSync(){}
//	public void setParentSync(){}
//	public void setParentCenter(){}
//	public void setCenter(){}
}
