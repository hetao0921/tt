package com.fxdigital.tcp.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.fxdigital.filesync.service.ServerRun;
import com.fxdigital.mysqlsync.bean.ServerInfo;
import com.fxdigital.mysqlsync.bean.ServerStatus;
import com.fxdigital.mysqlsync.bundle.MySqlDumpBean;
import com.fxdigital.mysqlsync.bundle.ResourceBundle;
import com.fxdigital.mysqlsync.service.MysqlDumpService;
import com.fxdigital.mysqlsync.service.MysqlService;
import com.fxdigital.mysqlsync.util.CompressorZip;
import com.fxdigital.mysqlsync.util.DeCompressorZip;
import com.fxdigital.mysqlsync.util.DeleteFolder;
import com.fxdigital.mysqlsync.util.FileUtil;
import com.fxdigital.tcp.util.client.MessageSendClient;
import com.fxdigital.tcp.util.server.MessageReceiveServer;

/**
 * @author Administrator 消息处理类
 * 
 */
public class ProcessMsg {

	private static final Logger logger = Logger.getLogger(ProcessMsg.class);
	static MySqlDumpBean mdb = ResourceBundle.getDumpResource();
	static ServerStatus serverstatus = new ServerStatus();
	static ServerInfo serverinfo = new ServerInfo();

	private static ProcessMsg processmsg = null;

	private static String exportFlag = "";

	private static String stopSyncFlag = "";

	private static String fileSyncFlag = "";

	public static ProcessMsg getInstance() {
		if (null == processmsg) {
			processmsg = new ProcessMsg();
		}
		return processmsg;
	}
	
	public static Timer timeOutTimer=null;
	
	
	public static Timer getTimer(){
		if(null==timeOutTimer){
			timeOutTimer=new Timer();
		}
		return timeOutTimer;
	}
	
	public static TimeOutTask task=null;
	public static TimeOutTask getTask(){
		if(null==task){
			task= new TimeOutTask();
		}else{
			task.cancel();
			task= new TimeOutTask();
		}
		return task;
		
	} 

	public static String getServerInfoPath() {
		String filepath = "";
		if (System.getProperty("os.name").equals("Linux")) {
			filepath = System.getProperty("user.dir")
					+ "/resources/serverinfo.xml";
		} else {
			filepath = System.getProperty("user.dir")
					+ "\\resources\\serverinfo.xml";
		}
		logger.info("serverinfopath:" + filepath);
		return filepath;
	}

	public static HashMap<String, String> sendMsg(IMsg imsg) throws IOException {
		logger.info("send message" + imsg);
		if (null != imsg) {
			if (imsg instanceof Msg) {
				Msg msg = (Msg) imsg;
				// 调用Tcp底层通讯
				// String ip=GetSystemProperties.getLocalIp();
				String ip = msg.getIp();
				logger.info("IP" + ip);
				String msgStr = "the first msg";
				try {
					MessageSendClient.mysqlSyncStart(ip, imsg);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("-------------");
					logger.info("send error :" + e);
					e.printStackTrace();
					serverstatus.setActive(false);
					logger.info("向服务器" + ip + "发送消息...出错" + e);
					serverstatus.setStatus("execerror");
				}
			}
		}

		return null;

	}

	public static String sendFileMsg(IMsg imsg) {

		logger.info("send message" + imsg);
		if (null != imsg) {
			if (imsg instanceof FileMsg) {
				FileMsg filemsg = (FileMsg) imsg;
				try {
					MessageSendClient
							.mysqlSyncStart(filemsg.getSlaveIp(), imsg);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					logger.info("send error :" + e);
					e.printStackTrace();
					serverstatus.setActive(false);
					logger.info("向服务器" + filemsg.getMasterIp() + "发送消息...出错"
							+ e);
					serverstatus.setStatus("execerror");
				}
			}
		}

		return null;

	}

	public int getServerStatus() {
		int result = 0;
		// 同步执行完成状态
		if (serverstatus.getProcess().equals("startmysqlsync")
				&& serverstatus.getStatus().equals("overinit")
				&& serverstatus.isActive() == false) {
			result = 1;
		}
		// 同步正在执行
		if (serverstatus.getProcess().equals("startmysqlsync")
				&& serverstatus.isActive() == true) {
			// 主服务器
			if (serverstatus.getStatus().equals("initslaveserver")) {
				result = 2;// 主对从设置权限
			}
			if (serverstatus.getStatus().equals("setslavecnf")) {
				result = 3;// 确认从对主权限
			}
			if (serverstatus.getStatus().equals("confirmsetcnf")) {
				result = 4;// 设置主服务器cnf文件
			}
			if (serverstatus.getStatus().equals("setmasterpos")) {
				result = 5;// a.主设置从服务器偏移量;b.获取主服务器偏移量
			}
			if (serverstatus.getStatus().equals("overinit")) {
				result = 6;// 查看主服务器最后执行结果
			}

			// 备服务器
			if (serverstatus.getStatus().equals("confirminit")) {
				result = 7;// a.从对主设置权限;b.确认主对从权限
			}
			if (serverstatus.getStatus().equals("setmastercnf")) {
				result = 8;// 设置从服务器cnf文件
			}
			if (serverstatus.getStatus().equals("setslavepos")) {
				result = 9;// 获取从服务器偏移量
			}
			if (serverstatus.getStatus().equals("confirmmaster")) {
				result = 10;// a.从设置主服务器偏移量;b.查看从服务器最后执行结果
			}

		}
		// 备份执行完成
		if (serverstatus.getProcess().equals("mysqldumpexport")
				&& serverstatus.getStatus().equals("overexport")
				&& serverstatus.isActive() == false) {
			result = 11;
		}
		// 备份正在执行
		if (serverstatus.getProcess().equals("mysqldumpexport")
				&& serverstatus.isActive() == true) {
			result = 12;
		}
		// 还原执行完成
		if (serverstatus.getProcess().equals("mysqldumpimport")
				&& serverstatus.getStatus().equals("overimport")
				&& serverstatus.isActive() == false) {
			result = 13;
		}
		// 还原正在执行
		if (serverstatus.getProcess().equals("mysqldumpimport")
				&& serverstatus.isActive() == true) {
			result = 14;
		}
		return result;
	}

	public static void receiveMsg(IMsg imsg) {
		logger.info("收到消息" + imsg);
		logger.info("当前服务器正在进行操作: " + serverstatus.getProcess());
		logger.info("当前操作步骤： " + serverstatus.getStatus());
		logger.info("启动超时：300000ms "  );
		Timer timer=getTimer();
		TimeOutTask task =getTask();
		timer.schedule(task, 300000);
		if (null != imsg) {
			if (imsg instanceof Msg) {
				Msg msg = (Msg) imsg;

				logger.info("消息标识" + msg.getFlag());
				logger.info("服务器是否可用" + serverstatus.isActive());
				logger.info("进程状态" + serverstatus.getStatus());
				if (msg.getFlag().equals("initmasterserver")
						&& serverstatus.isActive() == false) {
                    logger.debug("开始主服务器备份my.cnf到/home/hxht下"+System.currentTimeMillis());
					serverstatus.setProcess("startmysqlsync");
					serverstatus.setActive(true);
					// 第一次配置则备份my.cnf到/home/hxht下

					String filePath = mdb.getSendpath() + "/my.cnf";
					logger.info("备份配置文件位置 " + filePath);
					if (!FileUtil.isExist(filePath)) {
						String cnfpath = mdb.getSendpath();
						String mycnfpath = ResourceBundle.getResource()
								.getBasedir();
						int result = MysqlService.getInstance().copyCnf(
								mycnfpath, cnfpath);
						if (result == 0) {
							logger.info("备份配置文件成功: 从" + mycnfpath + "移动到"
									+ cnfpath);
						} else {
							logger.info("备份配置文件失败: 未从" + mycnfpath + "移动到"
									+ cnfpath);
							serverstatus.setActive(false);
							serverstatus.setStatus("execerror");
							return;
						}
					} else {
						logger.info("备份配置文件已经存在");
					}
					
					logger.debug("结束主服务器备份my.cnf到/home/hxht下"+System.currentTimeMillis());
					logger.debug("开始主服务器给从服务器授权"+System.currentTimeMillis());
					// 给从权限

					logger.info("主服务器收到消息,给从服务器相应权限");
					logger.info("开始授予权限...");
					String slaveip = msg.getSlaveIp();

					int result = MysqlService.getInstance()
							.grantMaster(slaveip);
					if (result == 0) {
						Msg slavemsg = new Msg();
						String flag = "initslaveserver";
						slavemsg.setFlag(flag);
						slavemsg.setMasterip(msg.getMasterIp());
						slavemsg.setSlaveip(msg.getSlaveIp());
						slavemsg.setIp(msg.getSlaveIp());
						try {

							logger.info("主给从服务器授予权限成功");
							logger.info("向从服务器" + msg.getSlaveIp() + "发送消息...");
							ProcessMsg.sendMsg((IMsg) slavemsg);
							logger.info("initmasterserver success!");
							serverstatus.setStatus("initslaveserver");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							serverstatus.setActive(false);
							logger.info("主给从服务器授予权限时，向从服务器" + msg.getSlaveIp()
									+ "发送消息...出错" + e);
							serverstatus.setStatus("execerror");
						}

					} else {
						serverstatus.setActive(false);
						logger.info("授予指令失败");
						serverstatus.setStatus("execerror");
					}
					logger.debug("结束主服务器给从服务器授权"+System.currentTimeMillis());
				}
				if (msg.getFlag().equals("initslaveserver")
						&& serverstatus.isActive() == false) {
					serverstatus.setProcess("startmysqlsync");
					serverstatus.setActive(true);
					// 第一次配置则备份my.cnf到/home/hxht下
					logger.debug("开始从服务器备份my.cnf到/home/hxht下"+System.currentTimeMillis());
					String filePath = mdb.getSendpath() + "/my.cnf";
					logger.info("filePath " + filePath);
					if (!FileUtil.isExist(filePath)) {
						String cnfpath = mdb.getSendpath();
						String mycnfpath = ResourceBundle.getResource()
								.getBasedir();
						int result = MysqlService.getInstance().copyCnf(
								mycnfpath, cnfpath);
						if (result == 0) {
							logger.info("备份配置文件成功: 从" + mycnfpath + "移动到"
									+ cnfpath);
						} else {
							logger.info("备份配置文件失败: 未从" + mycnfpath + "移动到"
									+ cnfpath);
							serverstatus.setActive(false);
							serverstatus.setStatus("execerror");
							return;
						}
					} else {
						logger.info("备份配置文件已经存在");
					}
					logger.debug("结束从服务器备份my.cnf到/home/hxht下"+System.currentTimeMillis());
					logger.debug("开始从服务器给主服务器授权"+System.currentTimeMillis());
					logger.info("从服务器收到消息,给主服务器相应权限");
					logger.info("开始授予权限...");
					String masterip = msg.getMasterIp();
					int str = MysqlService.getInstance().grantSlave(masterip);
					logger.debug("结束从服务器给主服务器授权"+System.currentTimeMillis());
					logger.debug("开始从服务器对主服务器确认权限"+System.currentTimeMillis());
					if (str == 0) {
						String flag = "confirminit";
						Msg slavemsg = new Msg();
						slavemsg.setFlag(flag);
						slavemsg.setMasterip(msg.getMasterIp());
						slavemsg.setSlaveip(msg.getSlaveIp());
						slavemsg.setIp(msg.getMasterIp());
						String result = MysqlService.getInstance()
								.ConfirmGrant(masterip);
						logger.info("result" + result);
						if (result.contains("yes")) {
							try {
								logger.info("从对主授予权限成功");
								logger.info("向主服务器" + msg.getMasterIp()
										+ "发送消息...");
								ProcessMsg.sendMsg((IMsg) slavemsg);
								logger.info("initslaveserver success!");
								serverstatus.setStatus("confirminit");
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								serverstatus.setActive(false);
								logger.info("从对主授予权限时，向主服务器"
										+ msg.getMasterIp() + "发送消息...出错" + e);
								serverstatus.setStatus("execerror");
							}

						} else {
							serverstatus.setActive(false);
							logger.info("主对从授予权限失败");
							serverstatus.setStatus("execerror");
							logger.info("向主服务器发送失败信息。。。");
							sendSlaveErrorNoRoll(msg);
						}

					}
					logger.debug("结束从服务器对主服务器确认权限"+System.currentTimeMillis());
				}
				if (msg.getFlag().equals("confirminit")
						&& (serverstatus.getStatus().equals("initslaveserver") || serverstatus
								.getStatus().equals("allinitslaveserver"))) {
					// 开始设置配置文件
					logger.debug("开始主服务器对从服务器确认权限"+System.currentTimeMillis());
					logger.info("主服务器收到消息,开始确认权限");
					logger.info("开始确认权限...");
					String flag = "setslavecnf";
					Msg slavemsg = new Msg();
					slavemsg.setFlag(flag);
					slavemsg.setMasterip(msg.getMasterIp());
					slavemsg.setSlaveip(msg.getSlaveIp());
					slavemsg.setIp(msg.getSlaveIp());
					String result = MysqlService.getInstance().ConfirmGrant(
							msg.getSlaveIp());
					if (result.contains("yes")) {
						try {
							logger.info("从对主确认权限成功");
							logger.info("向从服务器" + msg.getSlaveIp() + "发送消息...");
							ProcessMsg.sendMsg((IMsg) slavemsg);
							logger.info("confirminit success!");
							serverstatus.setStatus("setslavecnf");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							serverstatus.setActive(false);
							logger.info("从对主确认权限时，向从服务器" + msg.getSlaveIp()
									+ "发送消息...出错" + e);
							serverstatus.setStatus("execerror");
						}

					} else {
						serverstatus.setActive(false);
						logger.info("从对主确认权限失败");
						serverstatus.setStatus("execerror");
					}
					logger.debug("结束主服务器对从服务器确认权限"+System.currentTimeMillis());
				}
				if (msg.getFlag().equals("setslavecnf")
						&& serverstatus.getStatus().equals("confirminit")) {
					// 开始设置配置文件
					logger.debug("开始给从服务器设置配置文件slavecnf"+System.currentTimeMillis());
					logger.info("从服务器收到消息,开始给从服务器设置配置文件");
					logger.info("开始设置配置文件...");
					MysqlService.getInstance().setSlaveFile();
					String flag = "setmastercnf";
					Msg slavemsg = new Msg();
					slavemsg.setFlag(flag);
					slavemsg.setMasterip(msg.getMasterIp());
					slavemsg.setSlaveip(msg.getSlaveIp());
					slavemsg.setIp(msg.getMasterIp());
					try {
						logger.info("从服务器设置配置成功");
						logger.info("向主服务器" + msg.getMasterIp() + "发送消息...");
						ProcessMsg.sendMsg((IMsg) slavemsg);
						logger.info("setslavecnf success!");
						serverstatus.setStatus("setmastercnf");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						serverstatus.setActive(false);
						logger.info("从服务器设置配置时，向主服务器" + msg.getMasterIp()
								+ "发送消息...出错" + e);
						serverstatus.setStatus("execerror");
						logger.info("向主服务器发送失败信息。。。");
						sendSlaveError(msg);
					}
					logger.debug("结束给从服务器设置配置文件slavecnf"+System.currentTimeMillis());
				}
				if (msg.getFlag().equals("setmastercnf")
						&& serverstatus.getStatus().equals("setslavecnf")) {
					// 开始设置配置文件
					logger.debug("开始给主服务器设置配置文件mastercnf"+System.currentTimeMillis());
					logger.info("主服务器收到消息,开始给主服务器设置配置文件");
					logger.info("开始设置配置文件...");
					MysqlService.getInstance().setMasterFile();
					String flag = "confirmsetcnf";
					Msg slavemsg = new Msg();
					slavemsg.setFlag(flag);
					slavemsg.setMasterip(msg.getMasterIp());
					slavemsg.setSlaveip(msg.getSlaveIp());
					slavemsg.setIp(msg.getSlaveIp());
					try {
						logger.info("主服务器设置配置成功");
						logger.info("向从服务器" + msg.getSlaveIp() + "发送消息...");
						ProcessMsg.sendMsg((IMsg) slavemsg);
						logger.info("setmastercnf success!");
						serverstatus.setStatus("confirmsetcnf");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						serverstatus.setActive(false);
						logger.info("主服务器设置配置时，向从服务器" + msg.getSlaveIp()
								+ "发送消息...出错" + e);
						serverstatus.setStatus("execerror");

						// 回滚
						String cnfpath = mdb.getSendpath();
						String mycnfpath = ResourceBundle.getResource()
								.getBasedir();
						MysqlService.getInstance().rollBack(cnfpath, mycnfpath);

					}
					logger.debug("结束给主服务器设置配置文件mastercnf"+System.currentTimeMillis());
				}
				if (msg.getFlag().equals("confirmsetcnf")
						&& serverstatus.getStatus().equals("setmastercnf")) {
					// 开始设置偏移量
					logger.debug("开始给从服务器确认配置mastercnf，并获取偏移量"+System.currentTimeMillis());
					logger.info("从服务器收到消息,开始确认配置");
					logger.info("开始确认配置...");
					List<HashMap<String, String>> list = MysqlService
							.getInstance().getMasterBinInfo();
					if (list.size() > 0 && null != list) {
						logger.info("list" + list);
						String flag = "setslavepos";
						Msg slavemsg = new Msg();
						slavemsg.setFlag(flag);
						slavemsg.setMasterip(msg.getMasterIp());
						slavemsg.setSlaveip(msg.getSlaveIp());
						slavemsg.setIp(msg.getMasterIp());
						slavemsg.setLogfile(list.get(0).get("File")
								.replace(" ", ""));
						slavemsg.setLogpos(Integer.valueOf(list.get(1)
								.get("Position").replace(" ", "")));
						try {
							logger.info("从服务器确认配置成功");
							logger.info("向主服务器" + msg.getMasterIp() + "发送消息...");
							ProcessMsg.sendMsg((IMsg) slavemsg);
							logger.info("confirmsetcnf success!");
							serverstatus.setStatus("setslavepos");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							serverstatus.setActive(false);
							logger.info("从服务器确认配置时，向主服务器" + msg.getMasterIp()
									+ "发送消息...出错" + e);
							serverstatus.setStatus("execerror");

							// 回滚
							String cnfpath = mdb.getSendpath();
							String mycnfpath = ResourceBundle.getResource()
									.getBasedir();
							MysqlService.getInstance().rollBack(cnfpath,
									mycnfpath);
						}

					} else {
						serverstatus.setActive(false);
						logger.info("从服务器确认主配置失败");
						serverstatus.setStatus("execerror");
						sendSlaveError(msg);
					}
					logger.debug("结束给从服务器确认配置mastercnf，并获取偏移量"+System.currentTimeMillis());
				}
				if (msg.getFlag().equals("setslavepos")
						&& serverstatus.getStatus().equals("confirmsetcnf")) {
					logger.info("主服务器收到消息,开始设置bin文件偏移量");
					logger.info("开始设置bin文件偏移量...");
					logger.debug("开始给主服务器设置bin文件偏移量"+System.currentTimeMillis());
					MysqlService.getInstance().setSlavePos(msg.getSlaveIp(),
							msg.getLogfile(), msg.getLogpos());
					logger.debug("结束给主服务器设置bin文件偏移量"+System.currentTimeMillis());
					logger.debug("开始获取主服务器bin文件偏移量"+System.currentTimeMillis());
					// 开始设置偏移量
					List<HashMap<String, String>> list = MysqlService
							.getInstance().getMasterBinInfo();
					if (list.size() > 0 && null != list) {
						String flag = "setmasterpos";
						Msg slavemsg = new Msg();
						slavemsg.setFlag(flag);
						slavemsg.setMasterip(msg.getMasterIp());
						slavemsg.setSlaveip(msg.getSlaveIp());
						slavemsg.setIp(msg.getSlaveIp());
						slavemsg.setLogfile(list.get(0).get("File")
								.replace(" ", ""));
						slavemsg.setLogpos(Integer.valueOf(list.get(1)
								.get("Position").replace(" ", "")));
						try {
							logger.info("主服务器设置偏移量成功");
							logger.info("向从服务器" + msg.getSlaveIp() + "发送消息...");
							ProcessMsg.sendMsg((IMsg) slavemsg);
							logger.info("setslavepos success!");
							logger.info("本次主服务器配置完成");
							serverstatus.setStatus("setmasterpos");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							serverstatus.setActive(false);
							logger.info("主服务器设置偏移量时，向从服务器" + msg.getSlaveIp()
									+ "发送消息...出错" + e);
							serverstatus.setStatus("execerror");

							// 回滚
							String cnfpath = mdb.getSendpath();
							String mycnfpath = ResourceBundle.getResource()
									.getBasedir();
							MysqlService.getInstance().rollBack(cnfpath,
									mycnfpath);
						}
						
					} else {
						serverstatus.setActive(false);
						logger.info("主服务器确认从配置失败");
						serverstatus.setStatus("execerror");

						// 回滚
						String cnfpath = mdb.getSendpath();
						String mycnfpath = ResourceBundle.getResource()
								.getBasedir();
						MysqlService.getInstance().rollBack(cnfpath, mycnfpath);
					}
					logger.debug("结束获取主服务器bin文件偏移量"+System.currentTimeMillis());
				}
				if (msg.getFlag().equals("setmasterpos")
						&& serverstatus.getStatus().equals("setslavepos")) {
					logger.info("从服务器收到消息,开始设置bin文件偏移量");
					logger.info("开始设置bin文件偏移量...");
					logger.debug("开始给从服务器设置bin文件偏移量"+System.currentTimeMillis());
					MysqlService.getInstance().setMasterPos(msg.getMasterIp(),
							msg.getLogfile(), msg.getLogpos());
					logger.debug("结束给从服务器设置bin文件偏移量"+System.currentTimeMillis());
					logger.debug("开始获取设置主备中从服务器设置最终结果"+System.currentTimeMillis());
					String result = MysqlService.getInstance().confirmSync();
					logger.info("最后执行结果: " + result);
					if (result.contains("yes")) {
						logger.info("setmasterpos success!");
						logger.info("从服务器设置偏移量成功");
						logger.info("本次从服务器配置成功");

						// 记录下配置信息
						serverinfo.setFlag("0");
						serverinfo.setMaster(msg.getMasterIp());
						serverinfo.setSlave(msg.getSlaveIp());
						serverinfo.setResult("0");
						SimpleDateFormat df = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");// 设置日期格式
						// logger.info(df.format(new Date()));// new
						// Date()为获取当前系统时间
						String time = df.format(new Date());
						serverinfo.setTime(time);
						FileUtil.createXMLFile(getServerInfoPath(), serverinfo);

						String flag = "confirmmaster";
						Msg slavemsg = new Msg();
						slavemsg.setFlag(flag);
						slavemsg.setMasterip(msg.getMasterIp());
						slavemsg.setSlaveip(msg.getSlaveIp());
						slavemsg.setIp(msg.getMasterIp());
						try {
							logger.info("向主服务器" + msg.getMasterIp() + "发送消息...");
							ProcessMsg.sendMsg((IMsg) slavemsg);
							logger.info("setmasterpos success!");

							serverstatus.setActive(false);
							serverstatus.setStatus("confirmmaster");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							serverstatus.setActive(false);
							logger.info("从服务器设置偏移量时，向主服务器" + msg.getMasterIp()
									+ "发送消息...出错" + e);
							serverstatus.setStatus("execerror");

							// 回滚
							String cnfpath = mdb.getSendpath();
							String mycnfpath = ResourceBundle.getResource()
									.getBasedir();
							MysqlService.getInstance().rollBack(cnfpath,
									mycnfpath);
						}
						
					} else {
						serverstatus.setActive(false);
						logger.info("本次从服务器配置失败");
						serverstatus.setStatus("execerror");
						logger.info("向主服务器发送失败信息。。。");
						sendSlaveError(msg);

					}
					logger.debug("结束获取设置主备中从服务器设置最终结果"+System.currentTimeMillis());
				}
				if (msg.getFlag().equals("confirmmaster")
						&& serverstatus.getStatus().equals("setmasterpos")) {
					logger.debug("开始获取设置主备中主服务器设置最终结果"+System.currentTimeMillis());
					String result = MysqlService.getInstance().confirmSync();
					logger.info("最后执行结果: " + result);
					if (result.contains("yes")) {
						logger.info("本次主服务器配置成功");
						serverstatus.setActive(false);
						serverstatus.setStatus("overinit");

						// 记录下配置信息
						serverinfo.setFlag("0");
						serverinfo.setMaster(msg.getMasterIp());
						serverinfo.setSlave(msg.getSlaveIp());
						serverinfo.setResult("0");
						SimpleDateFormat df = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");// 设置日期格式
						// logger.info(df.format(new Date()));// new
						// Date()为获取当前系统时间
						String time = df.format(new Date());
						serverinfo.setTime(time);
						FileUtil.createXMLFile(getServerInfoPath(), serverinfo);

					} else {
						serverstatus.setActive(false);
						logger.info("本次主服务器配置失败");
						serverstatus.setStatus("execerror");

						// 回滚
						String cnfpath = mdb.getSendpath();
						String mycnfpath = ResourceBundle.getResource()
								.getBasedir();
						MysqlService.getInstance().rollBack(cnfpath, mycnfpath);
					}
					logger.debug("结束获取设置主备中主服务器设置最终结果"+System.currentTimeMillis());
				}
				if (msg.getFlag().equals("slaveerror")) {
					serverstatus.setActive(false);
					logger.info("本次主服务器配置失败");
					exportFlag = "execerror";
					stopSyncFlag = "execerror";
					fileSyncFlag = "execerror";
					// 回滚
					String cnfpath = mdb.getSendpath();
					String mycnfpath = ResourceBundle.getResource()
							.getBasedir();
					MysqlService.getInstance().rollBack(cnfpath, mycnfpath);
					serverstatus.setStatus("execerror");
				}

				if (msg.getFlag().equals("mysqldumpexport")
						&& serverstatus.isActive() == false) {
					serverstatus.setActive(true);
					serverstatus.setProcess("mysqldumpexport");
					logger.info("接收到备份本地数据指令");
					logger.info("开始传输文件。。。");
					logger.info("开始打包本地文件 。。。");
					SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");// 设置日期格式
					String dateStr = df.format(new Date());
					String fileName = dateStr + ".sql";
					int result = MysqlDumpService.getInstance()
							.exportMasterDump(fileName);
					if (result == 0) {
						logger.info("打包本地文件完成");
						logger.info("开始传输文件由主服务器" + msg.getMasterIp() + "到从服务器"
								+ msg.getSlaveIp() + "...");

						FileMsg slavefilemsg = new FileMsg();
						String flag = "startdumpexport";
						slavefilemsg.setFlag(flag);
						slavefilemsg.setSendFilePath(mdb.getSendpath() + "/"
								+ fileName);
						slavefilemsg.setReceiveFilePath(mdb.getReceivepath()
								+ "/" + fileName);

						slavefilemsg.setMasterip(msg.getMasterIp());
						slavefilemsg.setSlaveip(msg.getSlaveIp());
						logger.info("getSendpath "
								+ slavefilemsg.getSendFilePath());
						logger.info("getReceivepath "
								+ slavefilemsg.getReceiveFilePath());
						logger.info("master " + slavefilemsg.getMasterIp());
						logger.info("slave " + slavefilemsg.getSlaveIp());
						logger.info("向从服务器" + slavefilemsg.getSlaveIp()
								+ "发送消息...");
						try {
							ProcessMsg.sendFileMsg((IMsg) slavefilemsg);
						} catch (Exception e) {
							logger.info("主服务器" + slavefilemsg.getMasterIp()
									+ "向从服务器" + slavefilemsg.getSlaveIp()
									+ "发送消息...失败" + e);
							serverstatus.setActive(false);
							serverstatus.setStatus("execerror");
						}
						logger.info("export success!");
						serverstatus.setStatus("startdumpexport");
					} else {
						serverstatus.setActive(false);
						exportFlag = "execerror";
						logger.info("本次导出数据包失败");
						serverstatus.setStatus("execerror");
					}

				}
				if (msg.getFlag().equals("confirmdumpexport")
						&& serverstatus.getStatus().equals("startdumpexport")) {
					logger.info("收到 确认导出消息...");

					exportFlag = "success";

					serverstatus.setActive(false);
					serverstatus.setStatus("overexport");

				}
				if (msg.getFlag().equals("mysqldumpimport")
						&& serverstatus.isActive() == false) {
					serverstatus.setActive(true);
					serverstatus.setProcess("mysqldumpimport");
					logger.info("接收到还原本地数据指令");
					logger.info("开始还原本地数据。。。");
					int result = MysqlDumpService.getInstance()
							.importSlaveDump();
					if (result == 0) {
						logger.info("还原本地数据完成...");

						String flag = "backdumpimport";
						Msg slavemsg = new Msg();
						slavemsg.setFlag(flag);
						slavemsg.setMasterip(msg.getMasterIp());
						slavemsg.setSlaveip(msg.getSlaveIp());
						slavemsg.setIp(msg.getSlaveIp());
						Map<String, String> properties = new HashMap<String, String>();
						properties.put("flag", "dumpimport");
						properties.put("success", "success");
						slavemsg.setProperties(properties);

						// ProcessMsg.sendMsg((IMsg) msg);

						try {
							logger.info("向客户端" + slavemsg.getIp() + "发送反馈");
							MessageReceiveServer.sendMessage(slavemsg,
									slavemsg.getIp());
						} catch (Exception e) {
							serverstatus.setActive(false);
							logger.info("向客户端" + slavemsg.getIp() + "发送反馈失败"
									+ e);
							serverstatus.setStatus("execerror");
						}

						logger.info("import success!");
						serverstatus.setActive(false);
						serverstatus.setStatus("overimport");
					} else {
						serverstatus.setActive(false);
						logger.info("还原本地数据失败");
						serverstatus.setStatus("execerror");
					}

				}

				if (msg.getFlag().equals("startfilesync")
						&& serverstatus.isActive() == false) {
					logger.debug("开始主服务器备份本地配置文件"+System.currentTimeMillis());
					serverstatus.setActive(true);
					serverstatus.setProcess("startfilesync");
					logger.info("接收到备份本地配置文件指令");
					logger.info("开始传输文件。。。");

					logger.info("开始压缩传输文件....");

					String inputFileName = msg.getUsername(); // 你要压缩的文件夹
					String zipFileName = msg.getUsername() + ".zip"; // 压缩后的zip文件
					logger.info("压缩文件路径:inputFileName " + inputFileName + " : "
							+ "zipFileName " + zipFileName);
					CompressorZip book = new CompressorZip();
					try {
						// copy and delete the log file
						SimpleDateFormat df = new SimpleDateFormat(
								"yyyyMMddHHmmss");// 设置日期格式
						String strNow = df.format(new Date());// new
																// Date()为获取当前系统时间
						String srPath = msg.getUsername() + "/log";
						String desPath = "/log/log_fxconf_" + strNow;
						logger.info("copy and delete the log file:srcFileName "
								+ msg.getUsername() + ":desFileName" + desPath);
						logger.debug("开始主服务器复制文件log到备份地址"+System.currentTimeMillis());
						book.copyDirectiory(srPath, desPath);
						logger.debug("结束主服务器复制文件log到备份地址"+System.currentTimeMillis());
						logger.debug("开始主服务器删除配置文件下log文件"+System.currentTimeMillis());
						book.deleteFolder(msg.getUsername() + "/log");
						logger.debug("结束主服务器删除配置文件下log文件"+System.currentTimeMillis());
						logger.debug("开始主服务器将log文件下log4j.properties复制回来"+System.currentTimeMillis());
						book.copyFile(new File(desPath + "/log4j.properties"),
								new File(srPath + "/log4j.properties"));
						logger.debug("结束主服务器将log文件下log4j.properties复制回来"+System.currentTimeMillis());
						logger.debug("开始主服务器将fxconf文件压缩"+System.currentTimeMillis());
						book.zip(inputFileName, zipFileName);
						logger.debug("结束主服务器将fxconf文件压缩"+System.currentTimeMillis());
					} catch (Exception ex) {
						ex.printStackTrace();
						logger.info("压缩失败,inputFileName " + inputFileName
								+ " : " + "zipFileName " + zipFileName + ex);

						// 回滚
						String cnfpath = mdb.getSendpath();
						String mycnfpath = ResourceBundle.getResource()
								.getBasedir();
						MysqlService.getInstance().rollBack(cnfpath, mycnfpath);
					}

					logger.info("开始传输文件由主服务器" + msg.getMasterIp() + "到从服务器"
							+ msg.getSlaveIp() + "...");
					FileMsg slavefilemsg = new FileMsg();
					String flag = "filetransport";
					slavefilemsg.setFlag(flag);
					slavefilemsg.setSendFilePath(zipFileName);
					slavefilemsg.setReceiveFilePath(msg.getPassword() + ".zip");

					slavefilemsg.setMasterip(msg.getMasterIp());
					slavefilemsg.setSlaveip(msg.getSlaveIp());
					logger.info("getSendpath " + slavefilemsg.getSendFilePath()
							+ " : " + msg.getUsername());
					logger.info("getReceivepath "
							+ slavefilemsg.getReceiveFilePath() + " : "
							+ msg.getPassword());
					logger.info("master " + slavefilemsg.getMasterIp());
					logger.info("slave " + slavefilemsg.getSlaveIp());
					logger.info("向从服务器" + slavefilemsg.getSlaveIp() + "发送消息...");
					try {
						ProcessMsg.sendFileMsg((IMsg) slavefilemsg);
					} catch (Exception e) {
						logger.info("主服务器" + slavefilemsg.getMasterIp()
								+ "向从服务器" + slavefilemsg.getSlaveIp()
								+ "发送消息...失败" + e);
						serverstatus.setActive(false);
						serverstatus.setStatus("execerror");
					}
					logger.info("filesync success!");
					serverstatus.setStatus("startfilesync");
					logger.debug("开始主服务器备份本地配置文件"+System.currentTimeMillis());

				}
				if (msg.getFlag().equals("confirmfilesync")
						&& serverstatus.getStatus().equals("startfilesync")) {
					logger.info("配置文件备份传输完成");
					fileSyncFlag = "success";
					logger.info("delete local zip file  " + msg.getUsername());
					File file = new File(msg.getUsername());
					file.delete();
					logger.info("delete local zip file success!");
					logger.info("startfilesync success!");
					serverstatus.setStatus("overfilesync");
					serverstatus.setActive(false);
				}
				if (msg.getFlag().equals("stopmysqlsync")
						&& serverstatus.isActive() == false) {
					serverstatus.setActive(true);
					serverstatus.setProcess("stopmysqlsync");
					logger.info("主服务器接收解除互备指令stopmysqlsync");
					logger.info("开始修改本地配置文件。。。");
					String cnfpath = mdb.getSendpath();
					String mycnfpath = ResourceBundle.getResource()
							.getBasedir();
					int result = MysqlService.getInstance().cpMysqlCnf(cnfpath,
							mycnfpath);
					if (result == 0) {
						logger.info("主服务器修改本地配置文件完成...");

						String flag = "stopslavemysqlsync";
						Msg slavemsg = new Msg();
						slavemsg.setFlag(flag);
						slavemsg.setMasterip(msg.getMasterIp());
						slavemsg.setSlaveip(msg.getSlaveIp());
						slavemsg.setIp(msg.getSlaveIp());

						logger.info("向从服务器" + slavemsg.getIp() + "发送信息");
						try {
							ProcessMsg.sendMsg((IMsg) slavemsg);
							serverstatus.setStatus("stopslavemysqlsync");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							serverstatus.setActive(false);
							logger.info("主服务器修改本地配置文件时，向从服务器"
									+ msg.getSlaveIp() + "发送消息...出错" + e);
							serverstatus.setStatus("execerror");
							stopSyncFlag = "execerror";

						}

					} else {
						serverstatus.setActive(false);
						logger.info("修改本地配置文件失败");
						serverstatus.setStatus("execerror");
						stopSyncFlag = "execerror";
					}
				}
				if (msg.getFlag().equals("stopslavemysqlsync")
						&& serverstatus.isActive() == false) {
					serverstatus.setActive(true);
					serverstatus.setProcess("stopmysqlsync");
					logger.info("从服务器接收解除互备指令stopmysqlsync");
					logger.info("从服务器开始修改本地配置文件。。。");
					String cnfpath = mdb.getSendpath();
					String mycnfpath = ResourceBundle.getResource()
							.getBasedir();
					int result = MysqlService.getInstance().cpMysqlCnf(cnfpath,
							mycnfpath);
					if (result == 0) {
						logger.info("从服务器修改本地配置文件完成...");
						stopSyncFlag = "success";
						String flag = "confirmstopmysqlsync";
						Msg slavemsg = new Msg();
						slavemsg.setFlag(flag);
						slavemsg.setMasterip(msg.getMasterIp());
						slavemsg.setSlaveip(msg.getSlaveIp());
						slavemsg.setIp(msg.getMasterIp());
						String serverInfoPath = getServerInfoPath();
						logger.info("删除服务器同步信息文件..." + serverInfoPath);
						File serverInfo = new File(serverInfoPath);
						serverInfo.delete();
						logger.info("向主服务器" + slavemsg.getIp() + "发送信息");
						try {
							ProcessMsg.sendMsg((IMsg) slavemsg);
							serverstatus.setActive(false);
							serverstatus.setStatus("confirmstopmysqlsync");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							serverstatus.setActive(false);
							logger.info("从服务器修改本地配置文件时，向主服务器"
									+ msg.getMasterIp() + "发送消息...出错" + e);
							serverstatus.setStatus("execerror");
							stopSyncFlag = "execerror";
						}

					} else {
						serverstatus.setActive(false);
						logger.info("从服务器修改本地配置文件失败");
						serverstatus.setStatus("execerror");
						stopSyncFlag = "execerror";
						logger.info("向主服务器发送失败信息。。。");
						sendSlaveError(msg);

					}
				}
				if (msg.getFlag().equals("confirmstopmysqlsync")
						&& serverstatus.getStatus()
								.equals("stopslavemysqlsync")) {
					logger.info("主服务器接收确认互备指令confirmstopmysqlsync");
					logger.info("主服务器开始修改标识stopSyncFlag");
					String serverInfoPath = getServerInfoPath();
					logger.info("删除服务器同步信息文件..." + serverInfoPath);
					File serverInfo = new File(serverInfoPath);
					serverInfo.delete();
					stopSyncFlag = "success";
					serverstatus.setActive(false);
					serverstatus.setStatus("overstopmysqlsync");

				}

				// 解除本地互备
				if (msg.getFlag().equals("stoplocalmysqlsync")
						&& serverstatus.isActive() == false) {
					logger.debug("开始本服务器解除互备stoplocalmysqlsync"+System.currentTimeMillis());
					serverstatus.setActive(true);
					serverstatus.setProcess("stoplocalmysqlsync");
					logger.info("服务器接收解除互备指令stoplocalmysqlsync");
					logger.info("服务器开始修改本地配置文件。。。");
					String cnfpath = mdb.getSendpath();
					String mycnfpath = ResourceBundle.getResource()
							.getBasedir();
					int result = MysqlService.getInstance().cpLocalMysqlCnf(
							cnfpath, mycnfpath);
					String flag = "backstoplocalmysqlsync";
					Msg slavemsg = new Msg();
					slavemsg.setFlag(flag);
					slavemsg.setMasterip(msg.getMasterIp());
					slavemsg.setSlaveip(msg.getSlaveIp());
					slavemsg.setIp(msg.getSlaveIp());
					Map<String, String> properties = new HashMap<String, String>();
					properties.put("flag", "stoplocalmysqlsync");
					if (result == 0) {
						logger.info("服务器修改本地配置文件完成...");
						properties.put("success", "success");
						slavemsg.setProperties(properties);
						serverstatus.setActive(false);
						serverstatus.setStatus("stoplocalmysqlsync");
					} else {
						serverstatus.setActive(false);
						logger.info("服务器修改本地配置文件失败");
						serverstatus.setStatus("execerror");
						properties.put("success", "execerror");
						slavemsg.setProperties(properties);
					}

					try {
						logger.info("向客户端" + slavemsg.getIp() + "发送反馈");
						logger.debug("开始本服务器解除互备反馈客户端发送时间："+System.currentTimeMillis());
						MessageReceiveServer.sendMessage(slavemsg,
								slavemsg.getIp());
						logger.debug("结束本服务器解除互备反馈客户端发送时间："+System.currentTimeMillis());
					} catch (Exception e) {
						serverstatus.setActive(false);
						logger.info("向客户端" + slavemsg.getIp() + "发送反馈失败" + e);
						serverstatus.setStatus("execerror");
					}
					logger.debug("结束本服务器解除互备stoplocalmysqlsync"+System.currentTimeMillis());
				}

				// 以下是获取服务器相关参数的代码
				if (msg.getFlag().equals("getserverinfo")) {
					logger.info("收到获取服务器信息指令");
					ServerInfo serverInfo = FileUtil
							.readPathXML(getServerInfoPath());

					String flag = "backserverinfo";
					Msg slavemsg = new Msg();
					slavemsg.setFlag(flag);
					slavemsg.setMasterip(msg.getMasterIp());
					slavemsg.setSlaveip(msg.getSlaveIp());
					slavemsg.setIp(msg.getSlaveIp());
					Map<String, String> properties = new HashMap<String, String>();
					properties.put("flag", "serverinfo");
					properties.put("master", serverInfo.getMaster());
					properties.put("slave", serverInfo.getSlave());
					properties
							.put("result",
									("".equals(serverInfo.getResult()) || null == serverInfo
											.getResult()) ? "1" : serverInfo
											.getResult());
					properties.put("time", serverInfo.getTime());
					slavemsg.setProperties(properties);

					// ProcessMsg.sendMsg((IMsg) msg);
					try {
						logger.info("向客户端" + slavemsg.getIp() + "发送反馈");
						MessageReceiveServer.sendMessage(slavemsg,
								slavemsg.getIp());
					} catch (Exception e) {
						serverstatus.setActive(false);
						logger.info("向客户端" + slavemsg.getIp() + "发送反馈失败" + e);
						serverstatus.setStatus("execerror");
					}

					// return serverInfo;
				}
				if (msg.getFlag().equals("getdumpexportinfo")) {
					logger.info("收到获取导出文件是否成功指令");

					String flag = "backdumpexport";
					Msg slavemsg = new Msg();
					slavemsg.setFlag(flag);
					slavemsg.setMasterip(msg.getMasterIp());
					slavemsg.setSlaveip(msg.getSlaveIp());
					slavemsg.setIp(msg.getSlaveIp());
					Map<String, String> properties = new HashMap<String, String>();
					properties.put("flag", "dumpexport");
					properties.put("success", exportFlag);
					slavemsg.setProperties(properties);

					// ProcessMsg.sendMsg((IMsg) msg);

					try {
						logger.info("向客户端" + slavemsg.getIp() + "发送反馈");
						MessageReceiveServer.sendMessage(slavemsg,
								slavemsg.getIp());
					} catch (Exception e) {
						serverstatus.setActive(false);
						logger.info("向客户端" + slavemsg.getIp() + "发送反馈失败" + e);
						serverstatus.setStatus("execerror");
					}

					// return serverInfo;
				}
				if (msg.getFlag().equals("getdumpimport")) {
					logger.info("收到获取导入数据库结果。。。");

					String flag = "backdumpimport";
					Msg slavemsg = new Msg();
					slavemsg.setFlag(flag);
					slavemsg.setMasterip(msg.getMasterIp());
					slavemsg.setSlaveip(msg.getSlaveIp());
					slavemsg.setIp(msg.getSlaveIp());
					Map<String, String> properties = new HashMap<String, String>();
					properties.put("flag", "dumpimport");
					properties.put("success", "success");

					// ProcessMsg.sendMsg((IMsg) msg);
					try {
						logger.info("向客户端" + slavemsg.getIp() + "发送反馈");
						MessageReceiveServer.sendMessage(slavemsg,
								slavemsg.getIp());
					} catch (Exception e) {
						serverstatus.setActive(false);
						logger.info("向客户端" + slavemsg.getIp() + "发送反馈失败" + e);
						serverstatus.setStatus("execerror");
					}

					// return serverInfo;
				}
				if (msg.getFlag().equals("getstopmysqlsync")) {
					logger.info("获取解除互备数据库结果。。。");

					String flag = "backstopmysqlsync";
					Msg slavemsg = new Msg();
					slavemsg.setFlag(flag);
					slavemsg.setMasterip(msg.getMasterIp());
					slavemsg.setSlaveip(msg.getSlaveIp());
					slavemsg.setIp(msg.getSlaveIp());
					Map<String, String> properties = new HashMap<String, String>();
					properties.put("flag", "stopmysqlsync");
					properties.put("success", stopSyncFlag);
					slavemsg.setProperties(properties);
					logger.info("解除互备结果:" + stopSyncFlag);

					try {
						logger.info("向客户端" + slavemsg.getIp() + "发送反馈"
								+ stopSyncFlag);
						MessageReceiveServer.sendMessage(slavemsg,
								slavemsg.getIp());
					} catch (Exception e) {
						serverstatus.setActive(false);
						logger.info("向客户端" + slavemsg.getIp() + "发送反馈失败" + e);
						serverstatus.setStatus("execerror");
					}
				}

				if (msg.getFlag().equals("getseverstatus")) {
					logger.info("收到获取服务器状态指令");

					String flag = "backserverstatus";
					Msg slavemsg = new Msg();
					slavemsg.setFlag(flag);
					slavemsg.setMasterip(msg.getMasterIp());
					slavemsg.setSlaveip(msg.getSlaveIp());
					slavemsg.setIp(msg.getSlaveIp());

					Map<String, String> properties = new HashMap<String, String>();
					properties.put("flag", "serverstatus");
					properties.put("process", serverstatus.getProcess());
					properties.put("status", serverstatus.getStatus());
					properties.put("isActive", serverstatus.isActive() + "");
					slavemsg.setProperties(properties);
					// ProcessMsg.sendMsg((IMsg) msg);

					try {
						logger.info("向客户端" + slavemsg.getIp() + "发送反馈");
						MessageReceiveServer.sendMessage(slavemsg,
								slavemsg.getIp());
					} catch (Exception e) {
						serverstatus.setActive(false);
						logger.info("向客户端" + slavemsg.getIp() + "发送反馈失败" + e);
						serverstatus.setStatus("execerror");
					}

				}
				if (msg.getFlag().equals("getfilesync")) {
					logger.info("收到获取文件传输指令。。。");

					String flag = "backfilesync";
					Msg slavemsg = new Msg();
					slavemsg.setFlag(flag);
					slavemsg.setMasterip(msg.getMasterIp());
					slavemsg.setSlaveip(msg.getSlaveIp());
					slavemsg.setIp(msg.getSlaveIp());
					Map<String, String> properties = new HashMap<String, String>();
					properties.put("flag", "filesync");
					properties.put("success", fileSyncFlag);
					slavemsg.setProperties(properties);
					// ProcessMsg.sendMsg((IMsg) msg);

					try {
						logger.info("向客户端" + slavemsg.getIp() + "发送反馈");
						MessageReceiveServer.sendMessage(slavemsg,
								slavemsg.getIp());
					} catch (Exception e) {
						serverstatus.setActive(false);
						logger.info("向客户端" + slavemsg.getIp() + "发送反馈失败" + e);
						serverstatus.setStatus("execerror");
					}

					// return serverInfo;
				}

				AllStartSynMysql(msg);

				// 以下是结束进程消息

				if (msg.getFlag().equals("stopbackupservice")) {
					logger.info("收到结束备用服务器进程消息");

					String flag = "backbackupservice";
					String command = msg.getUsername();
					logger.info("当前执行命令: " + command);
					Boolean backFlag = MysqlService.getInstance().setCommand(
							command);
					logger.info("命令执行结果: " + backFlag);
					if (backFlag) {

						Msg slavemsg = new Msg();
						slavemsg.setFlag(flag);
						slavemsg.setMasterip(msg.getMasterIp());
						slavemsg.setSlaveip(msg.getSlaveIp());
						slavemsg.setIp(msg.getSlaveIp());
						Map<String, String> properties = new HashMap<String, String>();
						properties.put("flag", "backupservice");
						properties.put("success", "success");
						slavemsg.setProperties(properties);
						// ProcessMsg.sendMsg((IMsg) msg);

						try {
							logger.info("向客户端" + slavemsg.getIp() + "发送反馈");
							MessageReceiveServer.sendMessage(slavemsg,
									slavemsg.getIp());
						} catch (Exception e) {
							serverstatus.setActive(false);
							logger.info("向客户端" + slavemsg.getIp() + "发送反馈失败"
									+ e);
							serverstatus.setStatus("execerror");
						}

						// return serverInfo;
					}
				}

			} else if (imsg instanceof FileMsg) {
				logger.info("此为文件消息");
				FileMsg fileMsg = (FileMsg) imsg;
				if (fileMsg.getFlag().equals("startdumpexport")
						&& serverstatus.isActive() == false) {
					logger.info("接收文件后将文件写到本地给主服务器回馈");
					String flag = "confirmdumpexport";
					serverstatus.setActive(true);
					serverstatus.setProcess("mysqldumpexport");
					Msg slavemsg = new Msg();
					slavemsg.setFlag(flag);
					slavemsg.setMasterip(fileMsg.getMasterIp());
					slavemsg.setSlaveip(fileMsg.getSlaveIp());
					slavemsg.setIp(fileMsg.getMasterIp());
					try {
						logger.info("向主服务器" + fileMsg.getMasterIp() + "发送消息...");
						ProcessMsg.sendMsg((IMsg) slavemsg);
						serverstatus.setStatus("confirmdumpexport");
						serverstatus.setActive(false);

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						serverstatus.setActive(false);
						logger.info("接收文件后将文件写到本地给主服务器回馈时，向主服务器"
								+ fileMsg.getMasterIp() + "发送消息...出错" + e);
						serverstatus.setStatus("execerror");
					}

				}
				if (fileMsg.getFlag().equals("allstartdumpexport")
						&& serverstatus.isActive() == false) {
					logger.info("此为文件消息");
					logger.info("接收文件后开始进行还原操作");
					 logger.debug("开始从服务器开始导入.sql备份文件"+System.currentTimeMillis());
					serverstatus.setActive(true);
					serverstatus.setProcess("allstartsynmysql");
					String flag = "allconfirmdumpexport";

					Msg slavemsg = new Msg();
					slavemsg.setFlag(flag);
					slavemsg.setMasterip(fileMsg.getMasterIp());
					slavemsg.setSlaveip(fileMsg.getSlaveIp());
					slavemsg.setIp(fileMsg.getMasterIp());
					logger.info("开始还原本地数据。。。");
					int result = MysqlDumpService.getInstance()
							.importSlaveDump();
					if (result == 0) {
						try {
							logger.info("向主服务器" + fileMsg.getMasterIp()
									+ "发送消息...");
							ProcessMsg.sendMsg((IMsg) slavemsg);
							serverstatus.setStatus("allconfirmdumpexport");

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							serverstatus.setActive(false);
							logger.info("还原本地数据时，向主服务器" + fileMsg.getMasterIp()
									+ "发送消息...出错" + e);
							serverstatus.setStatus("execerror");

						}

					} else {
						serverstatus.setActive(false);
						logger.info("还原本地数据失败");
						serverstatus.setStatus("execerror");
						logger.info("向主服务器发送失败信息。。。");
						sendSlaveError(slavemsg);
						// sendSlaveError(msg);
					}
					 logger.debug("结束从服务器开始导入.sql备份文件"+System.currentTimeMillis());
				}
				if (fileMsg.getFlag().equals("filetransport")
						&& serverstatus.isActive() == false) {
					logger.debug("开始从服务器将fxconf文件压缩包解压"+System.currentTimeMillis());
					logger.info("此为文件消息");
					logger.info("接收文件对主服务器发送确认");
					serverstatus.setActive(true);
					serverstatus.setProcess("startfilesync");
					String flag = "confirmfilesync";
					logger.info("先删除本地配置文件");
					String receiveFileName = fileMsg.getReceiveFilePath();
					String name = receiveFileName.substring(0,
							receiveFileName.lastIndexOf("."));
					DeleteFolder delf = new DeleteFolder();
					logger.debug("开始从服务器将本地fxconf文件删除"+System.currentTimeMillis());
					delf.delFoder(name);
					logger.debug("结束从服务器将本地fxconf文件删除"+System.currentTimeMillis());
					logger.info("再解压主服务器传递过来的配置文件");
					DeCompressorZip de = new DeCompressorZip();
					logger.debug("开始从服务器将fxconf文件压缩包解压还原"+System.currentTimeMillis());
					de.unZip(receiveFileName, name);
					logger.debug("结束从服务器将fxconf文件压缩包解压还原"+System.currentTimeMillis());
					logger.info("解压文件,由" + receiveFileName + "解压为" + name);
					logger.info("最后删除主服务器传递过来的配置文件");
					logger.debug("开始从服务器删除原始fxconf文件压缩包"+System.currentTimeMillis());
					File file = new File(receiveFileName);
					file.delete();
					logger.debug("结束从服务器删除原始fxconf文件压缩包"+System.currentTimeMillis());
					Msg slavemsg = new Msg();
					slavemsg.setFlag(flag);
					slavemsg.setMasterip(fileMsg.getMasterIp());
					slavemsg.setSlaveip(fileMsg.getSlaveIp());
					slavemsg.setIp(fileMsg.getMasterIp());
					slavemsg.setUsername(fileMsg.getSendFilePath());
					slavemsg.setPassword(fileMsg.getReceiveFilePath());

					try {
						logger.info("向主服务器" + fileMsg.getMasterIp() + "发送消息...");
						ProcessMsg.sendMsg((IMsg) slavemsg);
						serverstatus.setStatus("confirmfilesync");
						serverstatus.setActive(false);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						serverstatus.setActive(false);
						logger.info("还原本地数据时，向主服务器" + fileMsg.getMasterIp()
								+ "发送消息...出错" + e);
						serverstatus.setStatus("execerror");

						// 回滚
						String cnfpath = mdb.getSendpath();
						String mycnfpath = ResourceBundle.getResource()
								.getBasedir();
						MysqlService.getInstance().rollBack(cnfpath, mycnfpath);
					}

				}
				logger.debug("结束从服务器将fxconf文件压缩包解压"+System.currentTimeMillis());
			} else {
				logger.info("消息为空");
			}
		}

	}

	public static void AllStartSynMysql(Msg msg) {
		if (msg.getFlag().equals("allstartsynmysql")
				&& serverstatus.isActive() == false) {
			logger.debug("开始主服务器导出本地数据到.sql文件中"+System.currentTimeMillis());
			serverstatus.setActive(true);
			serverstatus.setProcess("allstartsynmysql");
			logger.info("接收到备份本地数据指令");
			logger.info("开始传输文件。。。");
			logger.info("开始打包本地文件 。。。");
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");// 设置日期格式
			String dateStr = df.format(new Date());
			String fileName = dateStr + ".sql";
			int result = MysqlDumpService.getInstance().exportMasterDump(
					fileName);
			if (result == 0) {
				logger.info("打包本地文件完成");
				logger.info("开始传输文件到从服务器...");

				FileMsg slavefilemsg = new FileMsg();
				String flag = "allstartdumpexport";
				slavefilemsg.setFlag(flag);
				slavefilemsg
						.setSendFilePath(mdb.getSendpath() + "/" + fileName);
				slavefilemsg.setReceiveFilePath(mdb.getReceivepath() + "/"
						+ fileName);

				slavefilemsg.setMasterip(msg.getMasterIp());
				slavefilemsg.setSlaveip(msg.getSlaveIp());
				logger.info("getSendpath " + slavefilemsg.getSendFilePath());
				logger.info("getReceivepath "
						+ slavefilemsg.getReceiveFilePath());
				logger.info("master " + slavefilemsg.getMasterIp());
				logger.info("slave " + slavefilemsg.getSlaveIp());
				logger.info("主服务器" + slavefilemsg.getMasterIp() + "向从服务器"
						+ slavefilemsg.getSlaveIp() + "发送消息...");
				try {
					ProcessMsg.sendFileMsg((IMsg) slavefilemsg);
				} catch (Exception e) {
					logger.info("主服务器" + slavefilemsg.getMasterIp() + "向从服务器"
							+ slavefilemsg.getSlaveIp() + "发送消息...失败" + e);
					serverstatus.setActive(false);
					serverstatus.setStatus("execerror");
				}

				logger.info("export success!");
				serverstatus.setStatus("allstartdumpexport");
			} else {
				serverstatus.setActive(false);
				logger.info("打包本地文件失败");
				serverstatus.setStatus("execerror");

			}
			logger.debug("结束主服务器导出本地数据到.sql文件中"+System.currentTimeMillis());
		}
		if (msg.getFlag().equals("allconfirmdumpexport")
				&& serverstatus.getStatus().equals("allstartdumpexport")) {
			logger.debug("开始主服务器备份my.cnf到/home/hxht下"+System.currentTimeMillis());
			// 第一次配置则备份my.cnf到/home/hxht下

			String filePath = mdb.getSendpath() + "/my.cnf";
			logger.info("备份配置文件路径 " + filePath);
			if (!FileUtil.isExist(filePath)) {
				String cnfpath = mdb.getSendpath();
				String mycnfpath = ResourceBundle.getResource().getBasedir();
				int result = MysqlService.getInstance().copyCnf(mycnfpath,
						cnfpath);
				if (result == 0) {
					logger.info("备份配置文件成功: 从" + mycnfpath + "移动到" + cnfpath);
				} else {
					logger.info("备份配置文件失败: 未从" + mycnfpath + "移动到" + cnfpath);
					serverstatus.setActive(false);
					serverstatus.setStatus("execerror");
					return;
				}
			} else {
				logger.info("备份配置文件已经存在");
			}
			logger.debug("结束主服务器备份my.cnf到/home/hxht下"+System.currentTimeMillis());
			logger.debug("开始主服务器给从服务器授予权限"+System.currentTimeMillis());
			// 给从权限
			logger.info("主服务器收到消息,给从服务器相应权限");
			logger.info("开始授予权限...");
			String slaveip = msg.getSlaveIp();

			int result = MysqlService.getInstance().grantMaster(slaveip);
			if (result == 0) {
				Msg slavemsg = new Msg();
				String flag = "allinitslaveserver";
				slavemsg.setFlag(flag);
				slavemsg.setMasterip(msg.getMasterIp());
				slavemsg.setSlaveip(msg.getSlaveIp());
				slavemsg.setIp(msg.getSlaveIp());
				try {

					logger.info("主对从授予权限成功");
					logger.info("向从服务器" + msg.getSlaveIp() + "发送消息...");
					ProcessMsg.sendMsg((IMsg) slavemsg);
					logger.info("initmasterserver success!");
					serverstatus.setStatus("allinitslaveserver");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					serverstatus.setActive(false);
					logger.info("主对从授予权限时，向从服务器" + msg.getSlaveIp()
							+ "发送消息...出错" + e);
					serverstatus.setStatus("execerror");
				}

			} else {
				serverstatus.setActive(false);
				logger.info("主对从授予权限失败");
				serverstatus.setStatus("execerror");

			}
			logger.debug("结束主服务器给从服务器授予权限"+System.currentTimeMillis());
		}
		if (msg.getFlag().equals("allinitslaveserver")
				&& serverstatus.getStatus().equals("allconfirmdumpexport")) {
			// 第一次配置则备份my.cnf到/home/hxht下
			logger.debug("开始从服务器备份my.cnf到/home/hxht下"+System.currentTimeMillis());
			String filePath = mdb.getSendpath() + "/my.cnf";
			logger.info("备份配置文件路径 " + filePath);
			if (!FileUtil.isExist(filePath)) {
				String cnfpath = mdb.getSendpath();
				String mycnfpath = ResourceBundle.getResource().getBasedir();
				int result = MysqlService.getInstance().copyCnf(mycnfpath,
						cnfpath);
				if (result == 0) {
					logger.info("备份配置文件成功: 从" + mycnfpath + "移动到" + cnfpath);
				} else {
					logger.info("备份配置文件失败: 未从" + mycnfpath + "移动到" + cnfpath);
					serverstatus.setActive(false);
					serverstatus.setStatus("execerror");
					return;
				}
			} else {
				logger.info("备份配置文件已经存在");
			}
			logger.debug("结束从服务器备份my.cnf到/home/hxht下"+System.currentTimeMillis());
			logger.debug("开始从服务器给主服务器授予权限"+System.currentTimeMillis());
			logger.info("从服务器收到消息,给主服务器相应权限");
			logger.info("开始授予权限...");
			String masterip = msg.getMasterIp();
			int str = MysqlService.getInstance().grantSlave(masterip);
			logger.debug("结束从服务器给主服务器授予权限"+System.currentTimeMillis());
			logger.debug("开始从服务器对主服务器确认权限"+System.currentTimeMillis());
			if (str == 0) {
				String flag = "confirminit";
				Msg slavemsg = new Msg();
				slavemsg.setFlag(flag);
				slavemsg.setMasterip(msg.getMasterIp());
				slavemsg.setSlaveip(msg.getSlaveIp());
				slavemsg.setIp(msg.getMasterIp());
				String result = MysqlService.getInstance().ConfirmGrant(
						masterip);
				logger.info("result" + result);
				if (result.contains("yes")) {
					try {
						logger.info("从对主予权限成功");
						logger.info("向主服务器" + msg.getMasterIp() + "发送消息...");
						ProcessMsg.sendMsg((IMsg) slavemsg);
						serverstatus.setStatus("confirminit");
						logger.info("initslaveserver success!");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						serverstatus.setActive(false);
						logger.info("从对主予权限时，向主服务器" + msg.getMasterIp()
								+ "发送消息...出错" + e);
						serverstatus.setStatus("execerror");
					}

				} else {
					serverstatus.setActive(false);
					logger.info("从对主予权限失败");
					serverstatus.setStatus("execerror");
					logger.info("向主服务器发送失败信息。。。");
					sendSlaveErrorNoRoll(msg);

				}

			}
			logger.debug("结束从服务器对主服务器确认权限"+System.currentTimeMillis());
		}

	}

	public static void sendSlaveError(Msg msg) {
		String flag = "slaveerror";
		Msg slavemsg = new Msg();
		slavemsg.setFlag(flag);
		slavemsg.setMasterip(msg.getMasterIp());
		slavemsg.setSlaveip(msg.getSlaveIp());
		slavemsg.setIp(msg.getMasterIp());
		try {
			logger.info("从服务器设置失败时，向主服务器" + msg.getMasterIp() + "发送消息...");
			ProcessMsg.sendMsg((IMsg) slavemsg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("从服务器设置失败时，向主服务器" + msg.getMasterIp() + "发送消息...出错" + e);
		} finally {
			// 回滚
			String cnfpath = mdb.getSendpath();
			String mycnfpath = ResourceBundle.getResource().getBasedir();
			MysqlService.getInstance().rollBack(cnfpath, mycnfpath);
		}

	}

	// 执行导出，导入，赋权时出错无需回滚
	public static void sendSlaveErrorNoRoll(Msg msg) {
		String flag = "slaveerror";
		Msg slavemsg = new Msg();
		slavemsg.setFlag(flag);
		slavemsg.setMasterip(msg.getMasterIp());
		slavemsg.setSlaveip(msg.getSlaveIp());
		slavemsg.setIp(msg.getMasterIp());
		try {
			logger.info("从服务器设置失败时，向主服务器" + msg.getMasterIp() + "发送消息...");
			ProcessMsg.sendMsg((IMsg) slavemsg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("从服务器设置失败时，向主服务器" + msg.getMasterIp() + "发送消息...出错" + e);
		}

	}

	static class TimeOutTask extends TimerTask {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			if(serverstatus.isActive()==true){
				logger.info("start timer out ...and reset server");
				serverstatus.setActive(false);
				serverstatus.setStatus("execerror");
			}
			

		}
	}

	public static void main(String[] args) throws IOException {
		// 启动文件主从备份定时器
		ServerRun serverRun = new ServerRun();
		serverRun.startFileSync();

		// 启动数据库主从备份侦听接口
		MessageReceiveServer.startMessageReceiveServer();

		// JdbcImpl java=JdbcImpl.getJdbcImpl();
		// java.selectOnLineClientID();

		// Msg msg = new Msg();
		// // String flag = "confirmsetcnf";
		// String flag1 = "mysqldumpexport";
		// String masterip = "192.168.1.134";
		// String slaveip = "192.168.1.127";
		// msg.setFlag(flag1);
		// msg.setMasterip(masterip);
		// msg.setSlaveip(slaveip);
		// msg.setIp(masterip);
		// ProcessMsg.sendMsg((IMsg) msg);
		/*
		 * Msg msg = new Msg(); // String flag = "confirmsetcnf";
		 * 
		 * String masterip = "192.168.1.55"; String slaveip = "192.168.1.23";
		 * 
		 * // String flag1 = "mysqldumpexport"; // msg.setIp(masterip);
		 * 
		 * String flag1 = "getserverinfo"; msg.setIp(slaveip);
		 * 
		 * msg.setFlag(flag1); msg.setMasterip(masterip);
		 * msg.setSlaveip(slaveip);
		 * 
		 * ProcessMsg.sendMsg((IMsg) msg);
		 * 
		 * /* FileMsg filemsg = new FileMsg(); String flag1 = "mysqldumpexport";
		 * String masterip1 = "192.168.1.134"; String slaveip1 =
		 * "192.168.1.127"; filemsg.setFlag(flag1);
		 * filemsg.setMasterip(masterip1); filemsg.setSlaveip(slaveip1);
		 * filemsg.setIp(masterip1);
		 * filemsg.setSendFilePath("/home/hxht/all.sql");
		 * filemsg.setReceiveFilePath("/home/hxht/all.sql");
		 * ProcessMsg.sendMsg((IMsg) filemsg);
		 */
	}
}
