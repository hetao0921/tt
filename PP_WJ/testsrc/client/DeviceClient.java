package client;

import NVMP.Proxy.MatrixDomain;
import NVMP.Proxy.StateManageDomain;
import NVMP.Proxy.SyncDomain;
import NVMP.Proxy.VideoContrlDomain;
import Runtime.IRunTime;
import Runtime.impl.RunTime;
import corenet.exchange.ExchangeClient;

public class DeviceClient {
	public static void main(String[] args) throws Exception {
		ExchangeClient ec = new ExchangeClient();
		IRunTime run = new RunTime();
		run.setTransChannel(ec); //
		StateManageDomain stateDomain = new NVMP.Proxy.StateManageDomain(run);
		VideoContrlDomain vc = new VideoContrlDomain(run);
		MatrixDomain  md = new MatrixDomain(run);
		SyncDomain syncd = new SyncDomain(run);
		ec.SetSessionId("user003");
		ec.ConnectExchange("192.168.1.2", 1900);
		
		Thread.sleep(3000);
		
		
		ec.JoinGroup("G_Matrix");
		
//		syncd.getSelfData("user001");
//		syncd.getServerDataVersion("user001");
//		vc.RequestPlayVideoVer2("user003", "e026d6f4a2b0473ca2f5c27f77a5d49b", 1, "_1222", 123);
//		ec.JoinGroup("G_Cmd");
//		System.out.println(111);
//		stateDomain.GetEncodeDeviceOnline("user003");
//		md.IpMatrixPlayVideo("user003", "111.111", 0, 0, "e026d6f4a2b0473ca2f5c27f77a5d49b", 1, true);
//		md.IpMatrixPlayVideo("user001", "222.222", 0, 0, "0ec99d795a494286af52e8993214da91", 1, true);
//		md.IpMatrixPlayVideo("user001", "111.111", 0, 0, "0ec99d795a494286af52e8993214da91", 1, false);
//		md.IpMatrixPlayVideo("user001", "222.222", 0, 0, "0ec99d795a494286af52e8993214da91", 1, false);
	
		//		md.ResponeIpMatrixPlayVideo("user001", "111.111", 0, 0, "0ec99d795a494286af52e8993214da91", 1, true, "1");
//		stateDomain.getBitRate("user001", "ac4187ddc583424fa889df8afcb3f951", 1);

	}
}
