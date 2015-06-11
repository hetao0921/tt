package corenet.exchange;



/**
 * 
 * 用来进行测试回路用例。 总共模拟4种情况。 1、设备上下线 2、客户端上下线 3、客户端进出组 4、服务器模拟初始化。
 * 
 * */
public class Test extends Thread {

//	private ExchangeServer ec;
//
//	// 防止各种设置，间隔时间，数量等。
//
//	private static Random randomutil = new Random();
//
//	private static int DeviceNum, ClientNum, GroupNum, DeviceDelay, GroupDelay,
//			ClientDelay;
//	private static boolean devRunFlag, clientRunFlag, groupRunFlag;
//
//	public Test(ExchangeServer exchangeServer) {
//		ec = exchangeServer;
//		ReadConfig();
//	}
//
//	// 进行模拟登陆。
//	public void initServer() {
//
//		/**
//		 * Flag:0 还没进过 TO这个点， 1 进过此点。
//		 * */
//		IMessage initMessage = new BaseMessage();
//		initMessage.AddParam("Form", ec.getServerID());
//		// 这里需要目的地的ID，需要外接输入。
//		String targerId = "";
//		initMessage.AddParam("To", targerId);
//		initMessage.AddParam("Flag", "0");
//		initMessage.SetAction("initDec");
//		initMessage.AddParam("RealCenter", ec.getServerID());
//
//		ec.CenterMessage("ALL", ec.getServerID(), new BaseMessage(), null,
//				initMessage, null);
//
//		/**
//		 * FormFlag:0 还没进过Form这个点， 1 进过此点。 ToFlag:0 还没进过To这个点， 1 进过此点。
//		 * */
//		initMessage = new BaseMessage();
//		initMessage.AddParam("Form", ec.getServerID());
//		initMessage.AddParam("To", targerId);
//		initMessage.AddParam("FormFlag", "1");
//		initMessage.AddParam("ToFlag", "0");
//		initMessage.SetAction("initData");
//		initMessage.AddParam("RealCenter", ec.getServerID());
//
//		for (IMessage message : ec.getEsm().getCenterMessage()) {
//
//			System.out.println("===传送出去的信息" + message.Serilize());
//			// ec.CenterSendMessage(message,targerId,
//			// ec.getServerID(), null, initMessage);
//			ec.CenterMessage(targerId, ec.getServerID(), message, null,
//					initMessage, null);
//		}
//
//	}
//
//	@Override
//	public void run() {
//		// 等待30秒后，开始进行循环输出，没1秒输出100个上下线
//		try {
//			Thread.sleep(30000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		while (true) {
//
//			if (devRunFlag) {
//				SimulateDeviceOnLineOffLine(DeviceNum, DeviceDelay);
//			}
//
//			if (clientRunFlag) {
//				SimulateClientOnLineOffLine(ClientNum, ClientDelay);
//			}
//
//			if (groupRunFlag) {
//				SimulateGroupInOut(ClientNum, GroupNum, GroupDelay);
//			}
//
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//	}
//
//	private void SimulateDeviceOnLineOffLine(int DeviceNum, int waitTime) {
//		for (int i = 0; i < DeviceNum; i++) {
//			StateManageDomainProxy.SetEncodeDeviceOnline(
//					GenerateSimulateDeviceID(i), "127.0.0.1",
//					GetRandomBoolValue(), -1, -1, "justtest");
//			if (0 < waitTime) {
//				try {
//					sleep(waitTime);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
//
//	private void SimulateClientOnLineOffLine(int ClientNum, int waitTime) {
//		for (int i = 0; i < ClientNum; i++) {
//			ec.GobalOnline(GetRandomBoolValue(), null,
//					GenerateSimulateClientID(i), null, null);
//			if (0 < waitTime) {
//				try {
//					sleep(waitTime);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
//
//	private void SimulateGroupInOut(int ClientNum, int GroupNum, int waitTime) {
//
//		if ((0 == ClientNum) || (0 == GroupNum)) {
//			return;
//		}
//
//		int ClientNumPerGroup = ClientNum / GroupNum;
//
//		String GroupName = "TestGroup0";
//
//		if (0 == ClientNumPerGroup) {
//			for (int i = 0; i < ClientNum; i++) {
//				ec.GobalGroupLeave(GetRandomBoolValue(), null,
//						GenerateSimulateClientID(i), GroupName, null, null);
//				if (0 < waitTime) {
//					try {
//						sleep(waitTime);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		} else {
//			for (int i = 0; i < ClientNum; i++) {
//				GroupName = GenerateTestGroupName(i / ClientNumPerGroup);
//				ec.GobalGroupLeave(GetRandomBoolValue(), null,
//						GenerateSimulateClientID(i), GroupName, null, null);
//				if (0 < waitTime) {
//					try {
//						sleep(waitTime);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}
//	}
//
//	private static boolean GetRandomBoolValue() {
//		return randomutil.nextBoolean();
//	}
//
//	private String GenerateSimulateClientID(int clientIndex) {
//		return ec.getServerID() + "||" + "SimulateClient" + "||" + clientIndex;
//	}
//
//	private String GenerateSimulateDeviceID(int DeviceIndex) {
//		return ec.getServerID() + "||" + "SimulateDevice" + "||" + DeviceIndex;
//	}
//
//	private String GenerateTestGroupName(int GroupIndex) {
//		return "TestGroup" + GroupIndex;
//	}
//
//	private static void ReadConfig() {
//		SAXReader saxReader = new SAXReader();
//		Document doc = null;
//		try {
//		
//			doc = saxReader.read(new File("/etc/fxconf/config/test.xml"));
//			DeviceNum = Integer.parseInt(doc.getRootElement().element("Device")
//					.attributeValue("SimulateDeviceNum"));
//			DeviceDelay = Integer.parseInt(doc.getRootElement()
//					.element("Device").attributeValue("delay"));
//			devRunFlag = Boolean.parseBoolean(doc.getRootElement()
//					.element("Device").attributeValue("Runflag"));
//			ClientNum = Integer.parseInt(doc.getRootElement().element("Client")
//					.attributeValue("SimulateClientNum"));
//			ClientDelay = Integer.parseInt(doc.getRootElement()
//					.element("Client").attributeValue("delay"));
//			clientRunFlag = Boolean.parseBoolean(doc.getRootElement()
//					.element("Client").attributeValue("Runflag"));
//			GroupNum = Integer.parseInt(doc.getRootElement().element("Group")
//					.attributeValue("SimulateGroupNum"));
//			GroupDelay = Integer.parseInt(doc.getRootElement().element("Group")
//					.attributeValue("delay"));
//			groupRunFlag = Boolean.parseBoolean(doc.getRootElement()
//					.element("Group").attributeValue("Runflag"));
//
//		} catch (DocumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

}
