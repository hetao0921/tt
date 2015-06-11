package client;

import corenet.exchange.ExchangeClient;
import NVMP.Proxy.CommandDomain;
import NVMP.Proxy.StateManageDomain;
import NVMP.Proxy.VideoContrlDomain;
import Runtime.IRunTime;
import Runtime.impl.RunTime;
import Runtime.impl.RunTimeDecorator;

/**
 * 
 * 单兵模拟客户端测试代码
 * */
public class SinglepadTest {

	CommandDomain commandDomain;
	StateManageDomain stateManageDomain;
	RunTime run;
	VideoContrlDomain videoContrlDomain ;

	public SinglepadTest() {
		
		run = new RunTime();
		commandDomain = new CommandDomain(run);
		stateManageDomain = new StateManageDomain(run);

		SinglePadHandler sph = new SinglePadHandler(this);
		commandDomain.setEventhand(sph);
		
		videoContrlDomain = new VideoContrlDomain(run);

	}
	
	
	//模拟设备上线
	public void setCommandLogin(){
		commandDomain.SetCommanderLoginOk("test012@006",
				"fcf4fc69ac804294836914ff4b395a3d", "192.168.1.2", true, 100,
				10, 4, "zzw", "ss1", "ss2", "ss3", "ss4", null);
		stateManageDomain.SetRTSPCommandEncodeDeviceOnline("test012@006", 250,
				1, 1, 1, "rtsp://admin:12345@192.168.1.165:554/H264");
	}
	
	//点播图像
	public void playVideo(String deviceID,Integer channel){
		
		videoContrlDomain.RequestPlayVideoVer2("test012@006", deviceID, channel, "zzw_zzw", 100);
	}
	
	
	
	

	public void start() throws Exception {
		ExchangeClient ec = new ExchangeClient();
		RunTimeDecorator decRunTime = new RunTimeDecorator(run);
		decRunTime.setTransChannel(ec);
		ec.SetSessionId("test012@006");
		ec.ConnectExchange("192.168.1.223", 1900);

		Thread.sleep(3000);

		setCommandLogin();
		playVideo("test012@006",1);
		
	}

	public static void main(String[] args) throws Exception {

		// while(true){
		// Thread.sleep(30*1000);
		// commandDomain.SetUserOnline("test02",
		// "fcf4fc69ac804294836914ff4b395a3d", "192.168.1.2", "1", true, "");
		// }
		SinglepadTest st = new SinglepadTest();
		st.start();
		
	}

}
