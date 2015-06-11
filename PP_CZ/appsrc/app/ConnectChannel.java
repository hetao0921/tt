package app;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.misc.log.LogUtil;

import NVMP.Proxy.CommandDomain;
import NVMP.Proxy.RemoteCloseDomain;
import NVMP.Proxy.StateManageDomain;
import NVMP.Proxy.VideoContrlDomain;
import Runtime.IConnectOK;
import Runtime.impl.RunTime;
import Runtime.impl.RunTimeDecorator;
import app.handler.CommandHandler;
import app.handler.RemoteCloseHandler;
import app.handler.VideoControlHandler;
import app.singlepad.ISinglePad;
import app.singlepad.SinglePadState;
import app.tcp.SinglePadTCP;
import corenet.exchange.ExchangeClient;
import corenet.netbase.NIOReactor;
import corenet.netbase.Interface.ITimer;

/**
 * 用计时器来实现重连
 * */
public class ConnectChannel implements Runnable, IConnectOK {
	private ITimer time; // 计时器,通过IO实现的，可以保证应用线性安全

	// 服务器连接信息
	String hostIP;
	int port;
	String sessionid;
	
	// 单兵
	ISinglePad singlePad;
	
	SinglePadTCP singlePadTCP;
	PadConnect padConnect;

	// 相关业务处理
	RunTime run;
	CommandDomain commandDomain;
	StateManageDomain stateManageDomain;
	VideoContrlDomain videoContrlDomain;
	RemoteCloseDomain remoteCloseDomain;

	// 连接成功标示
	private boolean isconnect;
	private boolean isPadConnect;
	
	private boolean isDestroyed = false;
	
	public ConnectChannel(String hostIP,int port,ISinglePad singlePad) {

		// 声明服务器连接端口和IP
		this.hostIP = hostIP;
		this.port = port;
		
		this.singlePad = singlePad;
		sessionid = singlePad.getBean().getSinglePadID();

		// 声明业务处理者
		run = new RunTime();
		run.setNewConnectOk(this);
		commandDomain = new CommandDomain(run);
		stateManageDomain = new StateManageDomain(run);
		videoContrlDomain = new VideoContrlDomain(run);
		remoteCloseDomain = new RemoteCloseDomain(run);
		
		CommandHandler commandHandler = new CommandHandler(this);
		commandDomain.setEventhand(commandHandler);
		
		VideoControlHandler videoControlHandler = new VideoControlHandler(this);
		videoContrlDomain.setEventhand(videoControlHandler);
		
		RemoteCloseHandler remoteCloseHandler = new RemoteCloseHandler(this);
		remoteCloseDomain.setEventhand(remoteCloseHandler);
		
		// 获取计时器
		NIOReactor r = (NIOReactor) NIOReactor.defaultDispatcher();
		time = r.newTimer(this);
		
		padConnect = new PadConnect();
		padConnect.start();
		
		singlePadTCP = new SinglePadTCP(singlePad);
	}
	
	public void destroy(){
		time.cancel();
		padConnect.destroy();
		singlePad.destroy();
		run.setNewConnectOk(null);
		singlePadTCP = null;
		padConnect = null;
		singlePad = null;
		isDestroyed = true;
	}
	
	class PadConnect implements Runnable{
		
		private ITimer time;
		
		public PadConnect(){
			NIOReactor r = (NIOReactor) NIOReactor.defaultDispatcher();
			time = r.newTimer(this);
		}
		
		public void destroy(){
			time.cancel();
		}
		
		public void start(){
			connect();
			time.schedule(10000);
		}

		@Override
		public void run() {
			if(isconnect){
				if(!isPadConnect){
					if(singlePad.getState() != SinglePadState.offline){
						LogUtil.info("单兵系统：单兵（"+singlePad.getBean().getSinglePadIP()+"）下线！");
						singlePad.offlineEvent();
						//通知指挥端 单兵下线
						commandDomain.SetCommanderLoginOk(sessionid,
								sessionid, singlePad.getBean().getSinglePadIP(), false, 100,
								10, 4, singlePad.getBean().getSinglePadName(),
								"ss1", "ss2", "ss3", "ss4", null);
					}
				}else{
					//第一次上线时，立刻通知
					if(singlePad.getState() == SinglePadState.offline){
						LogUtil.info("单兵系统：单兵（"+singlePad.getBean().getSinglePadIP()+"）上线！");
						//通知指挥端 单兵上线
						commandDomain.SetCommanderLoginOk(sessionid,
								sessionid, singlePad.getBean().getSinglePadIP(), true, 100,
								10, 4, singlePad.getBean().getSinglePadName(),
								"ss1", "ss2", "ss3", "ss4", null);
						stateManageDomain.SetRTSPCommandEncodeDeviceOnline(sessionid, 250,
								1, 1, 1, singlePad.getBean().getSinglePadRtsp());
						singlePad.onlineEvent();
						singlePadTCP.connect();
					}
					
					//用心跳的方式通知所有人 单兵上线
					commandDomain.SetUserOnline(sessionid, sessionid,
							singlePad.getBean().getSinglePadIP(), null, true, null);
				}
			}else{
				LogUtil.info("单兵系统：指挥端未上线！");
			}
			start();
		}
		
		//连接单兵
		private void connect(){
			int timeOut = 3000;
			try {
				if(InetAddress.getByName(
						singlePad.getBean().getSinglePadIP()).isReachable(timeOut)){
					isPadConnect = true;
					return;
				}
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			isPadConnect = false;
		}
	}

	// 服务器开始运行
	public void start() {

		ExchangeClient ec = new ExchangeClient();
		RunTimeDecorator decRunTime = new RunTimeDecorator(run);
		decRunTime.setTransChannel(ec);
		ec.SetSessionId(sessionid);
		try {
			isconnect = false;
			ec.ConnectExchange(hostIP, port);
		} catch (Exception e) {
			e.printStackTrace();
		}
		time.schedule(10000);
	}

	// 任何运行时，进行检测
	@Override
	public void run() {
		if(isconnect) {
			//连接成功，调用连接成功操作
			businessStart();
		} else {
			//继续重连
			start();	
		}
	}

	//连接成功建立，开始进行业务处理
	private void businessStart(){
		
//		commandDomain.SetCommanderLoginOk(sessionid,
//				sessionid, singlePad.getBean().getSinglePadIP(), true, 100,
//				10, 4, singlePad.getBean().getSinglePadName(),
//				"ss1", "ss2", "ss3", "ss4", null);
//		stateManageDomain.SetRTSPCommandEncodeDeviceOnline(sessionid, 250,
//				1, 1, 1, singlePad.getBean().getSinglePadRtsp());

	}
	
	//连接关闭，调用业务关闭操作。
	private void businessClose(){
		//目前没有操作。
		
	}
	
	
	@Override
	public void connect() {
		this.isconnect = true;
	}

	@Override
	public ExchangeClient onAgainConnect() {
		this.isconnect = false;
		businessClose();
		time.schedule(10000);
		return null;
	}
	
	/**
	 * 点播图像
	 * 
	 * */
	public void PlayVideo(String DeviceId, int Index, String uuid) {
		LogUtil.info("单兵系统：单兵（"+singlePad.getBean().getSinglePadIP()+"）点播视频---------");
		LogUtil.info("DeviceId："+DeviceId);
		LogUtil.info("Index："+Index);
		LogUtil.info("uuid："+uuid);
		// 设置级别为该中心最高的点播。
		videoContrlDomain.RequestPlayVideo(sessionid, DeviceId, Index, uuid, -1, 999);
	}

	/**
	 * 停止图像
	 * 
	 * */
	public void StopVideo(String DeviceId, Integer Index, String uuid) {
		LogUtil.info("单兵系统：单兵（"+singlePad.getBean().getSinglePadIP()+"）停止点播的视频---------");
		LogUtil.info("DeviceId："+DeviceId);
		LogUtil.info("Index："+Index);
		LogUtil.info("uuid："+uuid);
		videoContrlDomain.RequestStopVideo(sessionid, DeviceId, Index, uuid);
	}
	
	//通知单兵 指挥端推送图像过来了
	public void noticeSPToPlayVideo(String DeviceId, int Index, String uuid){
		if(!isDestroyed){
			singlePad.playVideo(DeviceId, Index, uuid);
		}
	}
	
	//向单兵推送图像
	public void sendVideoToSP(String deviceId, Integer index, String videoServerIP){
		if(!isDestroyed){
			singlePad.sendVideo(deviceId, index, videoServerIP);
		}
	}
	
	//向单兵发送指令
	public boolean sendTcpCommand(short type, String command){
		if(!isDestroyed){
			return singlePadTCP.send(type, command);
		}else{
			return false;
		}
	}
	
	public ISinglePad getPad(){
		return singlePad;
	}
	
	public void noticeOffLine(){
		//通知指挥端 单兵下线
		commandDomain.SetCommanderLoginOk(sessionid,
				sessionid, singlePad.getBean().getSinglePadIP(), false, 100,
				10, 4, singlePad.getBean().getSinglePadName(),
				"ss1", "ss2", "ss3", "ss4", null);
	}
	
}
