package com.fxdigital.tcp.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fxdigital.tcp.util.client.MessageSendClient;

/**
 * @author Administrator 消息处理实体类
 * 
 */
public class ProcessMsg {
	private static Map<String,String> map;
	private  Log logger = LogFactory.getLog(ProcessMsg.class);
	
	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public  String sendMsg(IMsg imsg) throws IOException {
		System.out.println("send message" + imsg);
		if (null != imsg) {
			if (imsg instanceof Msg) {
				Msg msg = (Msg) imsg;
				// 调用Tcp底层通讯
				// String ip=GetSystemProperties.getLocalIp();
				String ip = msg.getIp();
				String msgStr = "the first msg";
				// Msg msg=new Msg();
				// msg.setIp("192.168.1.55");
				// String msg = "file transport";
				MessageSendClient.mysqlSyncStart(ip, imsg);
			}
		}

		return null;

	}

	public  void startExportMysql(String masterip, String slaveip) {
		Msg msg = new Msg();
		String flag = "mysqldumpexport";
		msg.setFlag(flag);
		msg.setMasterip(masterip);
		msg.setSlaveip(slaveip);
		msg.setIp(masterip);
		try {
			ProcessMsg processMsg=new ProcessMsg();
			processMsg.sendMsg((IMsg) msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public  void startImportMysql(String masterip, String slaveip) {
		Msg msg = new Msg();
		String flag = "mysqldumpimport";
		msg.setFlag(flag);
		msg.setMasterip(masterip);
		msg.setSlaveip(slaveip);
		msg.setIp(masterip);
		try {
			ProcessMsg processMsg=new ProcessMsg();
			processMsg.sendMsg((IMsg) msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public  void stopSynMysql(String masterip, String slaveip) {
		Msg msg = new Msg();
		String flag = "stopmysqlsync";
		msg.setFlag(flag);
		msg.setMasterip(masterip);
		msg.setSlaveip(slaveip);
		msg.setIp(masterip);
		try {
			ProcessMsg processMsg=new ProcessMsg();
			processMsg.sendMsg((IMsg) msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public  void stopLocalSynMysql(String masterip, String localip) {
		Msg msg = new Msg();
		String flag = "stoplocalmysqlsync";
		msg.setFlag(flag);
		msg.setMasterip(masterip);
		msg.setSlaveip(localip);
		msg.setIp(masterip);
		try {
			ProcessMsg processMsg=new ProcessMsg();
			processMsg.sendMsg((IMsg) msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public  void searchStopInfo(String masterip, String localip) {
		Msg msg = new Msg();
		String flag = "getstopmysqlsync";
		msg.setFlag(flag);
		msg.setMasterip(masterip);
		msg.setSlaveip(localip);
		msg.setIp(masterip);
		try {
			ProcessMsg processMsg=new ProcessMsg();
			processMsg.sendMsg((IMsg) msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public  void startSynMysql(String masterip, String slaveip) {

		Msg msg = new Msg();
		String flag = "initmasterserver";
		msg.setFlag(flag);
		msg.setMasterip(masterip);
		msg.setSlaveip(slaveip);
		msg.setIp(masterip);
		try {
			ProcessMsg processMsg=new ProcessMsg();
			processMsg.sendMsg((IMsg) msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public  void searchServerInfo(String masterip, String localip) {
		Msg msg = new Msg();
		String flag = "getserverinfo";
		msg.setFlag(flag);
		msg.setMasterip(masterip);
		msg.setSlaveip(localip);
		msg.setIp(masterip);
		try {
			ProcessMsg processMsg=new ProcessMsg();
			processMsg.sendMsg((IMsg) msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public  void searchExportMysql(String masterip, String localip) {
		Msg msg = new Msg();
		String flag = "getdumpexportinfo";
		msg.setFlag(flag);
		msg.setMasterip(masterip);
		msg.setSlaveip(localip);
		msg.setIp(masterip);
		try {
			ProcessMsg processMsg=new ProcessMsg();
			processMsg.sendMsg((IMsg) msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public  void searchServerStatus(String masterip, String localip) {
		Msg msg = new Msg();
		String flag = "getseverstatus";
		msg.setFlag(flag);
		msg.setMasterip(masterip);
		msg.setSlaveip(localip);
		msg.setIp(masterip);
		try {
			ProcessMsg processMsg=new ProcessMsg();
			processMsg.sendMsg((IMsg) msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public  void allStartSynMysql(String masterip, String slaveip) {

		Msg msg = new Msg();
		String flag = "allstartsynmysql";
		msg.setFlag(flag);
		msg.setMasterip(masterip);
		msg.setSlaveip(slaveip);
		msg.setIp(masterip);
		try {
			ProcessMsg processMsg=new ProcessMsg();
			processMsg.sendMsg((IMsg) msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void startFileSync(String masterip, String slaveip,String sendpath,String receivepath){
		Msg msg = new Msg();
		String flag = "startfilesync";
		msg.setFlag(flag);
		msg.setMasterip(masterip);
		msg.setSlaveip(slaveip);
		msg.setIp(masterip);
		msg.setUsername(sendpath);
		msg.setPassword(receivepath);
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("sendpath", sendpath);
		properties.put("receivepath", receivepath);
		msg.setProperties(properties);

		try {
			ProcessMsg processMsg=new ProcessMsg();
			processMsg.sendMsg((IMsg) msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void searchFileSync(String masterip,String localip){

		Msg msg = new Msg();
		String flag = "getfilesync";
		msg.setFlag(flag);
		msg.setMasterip(masterip);
		msg.setSlaveip(localip);
		msg.setIp(masterip);
		try {
			ProcessMsg processMsg=new ProcessMsg();
			processMsg.sendMsg((IMsg) msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
	public void stopBackupService(String masterip,String localip,String command){


		Msg msg = new Msg();
		String flag = "stopbackupservice";
		msg.setFlag(flag);
		msg.setMasterip(masterip);
		msg.setSlaveip(localip);
		msg.setIp(masterip);
		msg.setUsername(command);
		try {
			ProcessMsg processMsg=new ProcessMsg();
			processMsg.sendMsg((IMsg) msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	}

	public  void receiveMsg(IMsg imsg) {
		logger.info("收到消息" + imsg);
		logger.info("消息标识" + imsg.getFlag());
		if (null != imsg) {
			if (imsg instanceof Msg) {
				if (imsg.getFlag().equals("backserverinfo")) {
					Msg msg = (Msg) imsg;
					 map = msg.getProperties();
					logger.info("返回标识" + "flag: " + map.get("flag")
							+ " master: " + map.get("master") + " slave: "
							+ map.get("slave") + " result: "
							+ map.get("result") + " time: " + map.get("time"));
				}
				if (imsg.getFlag().equals("backserverstatus")) {
					Msg msg = (Msg) imsg;
					 map = msg.getProperties();
					logger.info("返回标识" + "flag: " + map.get("flag")
							+ " process: " + map.get("process") + " status: "
							+ map.get("status") + " isActive: "
							+ map.get("isActive"));
				}
				if (imsg.getFlag().equals("backdumpexport")) {
					Msg msg = (Msg) imsg;
					 map = msg.getProperties();
					logger.info("返回标识" + "flag: " + map.get("flag")
							+ " success: " + map.get("success"));
					if (null!=map.get("success")&&map.get("success").equals("success")) {
						logger.info("导出文件成功！");
					} else {
						logger.info("导出文件失败！");
					}
				}
				if (imsg.getFlag().equals("backdumpimport")) {
					Msg msg = (Msg) imsg;
					 map = msg.getProperties();
					logger.info("返回标识" + "flag: " + map.get("flag")
							+ " success: " + map.get("success"));
					if (null!=map.get("success")&&map.get("success").equals("success")) {
						logger.info("导入文件成功！");
					} else {
						logger.info("导入文件失败！");
					}
				}
				if(imsg.getFlag().equals("backstopmysqlsync")){

					Msg msg = (Msg) imsg;
					 map = msg.getProperties();
					logger.info("返回标识" + "flag: " + map.get("flag")
							+ " success: " + map.get("success"));
					if (null!=map.get("success")&&map.get("success").equals("success")) {
						logger.info("解除互备成功！");
					} else {
						logger.info("解除互备失败！");
					}
				
				}
				if(imsg.getFlag().equals("backstoplocalmysqlsync")){
					logger.debug("客户端接收到解除主备反馈消息"+System.currentTimeMillis());
					Msg msg = (Msg) imsg;
					 map = msg.getProperties();
					logger.info("返回标识" + "flag: " + map.get("flag")
							+ " success: " + map.get("success"));
					if (null!=map.get("success")&&map.get("success").equals("success")) {
						logger.info("解除单边互备成功！");
					} else {
						logger.info("解除单边互备失败！");
					}
				
				}
				if(imsg.getFlag().equals("backfilesync")){

					Msg msg = (Msg) imsg;
					 map = msg.getProperties();
					logger.info("返回标识" + "flag: " + map.get("flag")
							+ " success: " + map.get("success"));
					if (null!=map.get("success")&&map.get("success").equals("success")) {
						logger.info("文件备份成功");
					} else {
						logger.info("文件备份失败！");
					}
				
				}
				if(imsg.getFlag().equals("backbackupservice")){

					Msg msg = (Msg) imsg;
					 map = msg.getProperties();
					logger.info("返回标识" + "flag: " + map.get("flag")
							+ " success: " + map.get("success"));
					if (null!=map.get("success")&&map.get("success").equals("success")) {
						logger.info("备用服务结束成功");
					} else {
						logger.info("备用服务结束失败！");
					}
				
				}
			}
		}

	}
	
	

	public static void main(String[] args) throws IOException {
		// MessageReceiveServer.startMessageReceiveServer();

		// 1.启动主备配置指令
//		 String masterip = "192.168.1.55";
//		 String slaveip = "192.168.1.87";
//		 ProcessMsg p=new ProcessMsg();
//		 p.startSynMysql(masterip, slaveip);

		// 2.查询服务器同步情况
//		 String masterip = "192.168.1.208";
//		 String localip = "192.168.1.55";
//		 ProcessMsg p=new ProcessMsg();
//		 p.searchServerInfo(masterip,localip);

		// 3.查询服务器设置主备是否成功
		 String masterip = "192.168.1.211";
		 String localip = "192.168.1.211";
		 ProcessMsg p=new ProcessMsg();
		 p.searchServerStatus(masterip,localip);

		// 4.手工导出mysql
//		String masterip = "192.168.1.211";
//		String slaveip = "192.168.1.208";
//		ProcessMsg p=new ProcessMsg();
//		p.startExportMysql(masterip,slaveip);

		// 5.查询是否导出成功
//		 String masterip = "192.168.1.122";
//		 String localip="192.168.1.55";
//		 ProcessMsg p=new ProcessMsg();
//		 p.searchExportMysql(masterip,localip);
		
		// 5.手工导入mysql
//		 String masterip = "192.168.1.208";
//		 String localip= "192.168.1.55";
//		 ProcessMsg p=new ProcessMsg();
//		 p.startImportMysql(masterip,localip);
		
		 //6.解除互备指令
//		 String masterip = "192.168.1.211";
//		 String slaveip= "192.168.1.208";
//		 ProcessMsg p=new ProcessMsg();
//		 p.stopSynMysql(masterip,slaveip);
		 
		 //7.查询解除是否成功
//		String masterip = "192.168.1.211";
//		String localip="192.168.1.55";
//		ProcessMsg p=new ProcessMsg();
//		 p.searchStopInfo(masterip,localip);
		 
		 
		 //8.导出，导入，互备一条龙指令
//			String masterip = "192.168.1.235";
//			String slaveip="192.168.1.224";
//			ProcessMsg p=new ProcessMsg();
//			p.allStartSynMysql(masterip,slaveip);
		 
		 
		 //9.同步文件
//			String masterip = "192.168.1.235";
//			String slaveip="192.168.1.224";
//			String receivepath="/etc/fxconf";
//			String sendpath="/etc/fxconf";
//			ProcessMsg p=new ProcessMsg();
//			 p.startFileSync(masterip,slaveip,sendpath,receivepath);
			 
			 //10.查看同步文件是否成功
//			 String masterip = "192.168.1.235";
//			 String localip = "192.168.1.55";
//			 ProcessMsg p=new ProcessMsg();
//			 p.searchFileSync(masterip,localip);
		 
		 
		 //11.结束备用进程
//		 String masterip="192.168.1.122";
//		 String localip="192.168.1.55";
//		 String command="ifconfig";
//		 ProcessMsg p=new ProcessMsg();
//		 p.stopBackupService(masterip,localip,command);
		
		
		//12.解除单个服务器互备
//		String masterip="192.168.1.55";
//		String localip="192.168.1.55";
//		ProcessMsg p=new ProcessMsg();
//		p.stopLocalSynMysql(masterip, localip);
		 

	}
}