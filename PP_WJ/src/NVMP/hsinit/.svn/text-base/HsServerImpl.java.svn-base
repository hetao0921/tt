package NVMP.hsinit;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.misc.log.LogUtil;

import com.sqlite.factory.DAOFactory;

import NVMP.AppService.ServerInit;
import NVMP.AppService.util.GetDeviceId;

public class HsServerImpl extends ServerInit {

	private AllowClientDao allowClientDao;

	public HsServerImpl() {

		allowClientDao = new AllowClientDao();
	}

	@Override
	public String getSessionID() {
		String sessiodId = null;
		try {

			// String path;
			// if (System.getProperty("os.name").equals("Linux"))
			// path = "/etc/fxconf/config/Device.xml";
			// else
			// path = "C:\\Windows\\System32\\config\\Device.xml";
			// SAXReader saxReader = new SAXReader();
			// Document doc = null;
			// doc = saxReader.read(new File(path));
			//
			// @SuppressWarnings("unchecked")
			// Iterator<Element> iter2 = doc.getRootElement().elementIterator();
			// while (iter2.hasNext()) {
			// Element e = iter2.next();
			// if (e.attributeValue("Type").equals("001")) {
			// sessiodId = e.attributeValue("DeviceSN");
			// break;
			// }
			//
			// }
			sessiodId = GetDeviceId.getInstance().getDeviceId("001");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LogUtil.error("加载服务器ID报错 " + e.getMessage());
			System.exit(1);
		}
		if (sessiodId == null) {
			LogUtil.error("no find sessionid ");
			System.exit(1);
		}
		System.out.println("=================sessiodId :" + sessiodId);
		return sessiodId;
	}

	@Override
	public int getPort() {
		String path = null;
		int ServerPort = 1900;
		if (System.getProperty("os.name").equals("Linux"))
			path = "/etc/fxconf/AppService/AppService.conf";
		else
			path = "d:\\fxconf\\AppService\\AppService.conf";

		// 读一下配置文件中的配置。
		SAXReader saxReader = new SAXReader();
		Document doc = null;
		try {
			doc = saxReader.read(new File(path));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String tempString = doc.getRootElement().element("AppServer")
				.attributeValue("PORT");
		if (tempString != null) {
			ServerPort = Integer.parseInt(tempString);
		}
		return ServerPort;
	}

	@Override
	public String getIP() {
		// TODO Auto-generated method stub
		String path = null;
		String ServerIP = "0.0.0.0";
		if (System.getProperty("os.name").equals("Linux"))
			path = "/etc/fxconf/AppService/AppService.conf";
		else
			path = "d:\\fxconf\\AppService\\AppService.conf";

		// 读一下配置文件中的配置。
		SAXReader saxReader = new SAXReader();
		Document doc = null;
		try {
			doc = saxReader.read(new File(path));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String tempString = doc.getRootElement().element("AppServer")
				.attributeValue("IP");
		if (tempString != null) {
			ServerIP = tempString;
		}
		return ServerIP;
	}

	@Override
	public void init_BeginBusiness() {
		String path = null;
		if (System.getProperty("os.name").equals("Linux"))
			path = "/etc/fxconf/AppService/AppService.conf";
		else
			path = "d:\\fxconf\\AppService\\AppService.conf";

		// 读一下配置文件中的配置。
		SAXReader saxReader = new SAXReader();
		Document doc = null;
		try {
			doc = saxReader.read(new File(path));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String tempString = doc.getRootElement().element("AppServer")
				.attributeValue("LEV");
		this.getAppRuntime().setLev(tempString == null ? "0" : tempString);

		String hostip = doc.getRootElement().element("AppServer")
				.attributeValue("WEBIP");

		LogUtil.info("开始初始化本地数据");
		try {

			DAOFactory.getPojoImpl().init(
					"http://" + hostip + ":8080/webservice/services/web",
					"/ServerDB");
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("init data is error, start server is fail");
			System.exit(1);

		}
		LogUtil.info("初始化本地数据完毕");

	}

	@Override
	public void init_AfterBusiness() {
		String path = null;
		if (System.getProperty("os.name").equals("Linux"))
			path = "/etc/fxconf/AppService/AppService.conf";
		else
			path = "d:\\fxconf\\AppService\\AppService.conf";

		// 读一下配置文件中的配置。
		SAXReader saxReader = new SAXReader();
		Document doc = null;
		try {
			doc = saxReader.read(new File(path));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String hostip = doc.getRootElement().element("AppServer")
				.attributeValue("WEBIP");

		LogUtil.info("开始对其它服务器进行连接");
		// CreateLocalChannel("0ff4c51b@001", "192.168.1.113", 1900);

		// 循环读取xml文件中的server设置，有多少连接多少。
		try {
			String[][] ss = DAOFactory.getPojoImpl().getCenterNetWork(
					this.getAppRuntime().getServerId(),
					"http://" + hostip + ":8080/webservice/services/web");
//			String[] array = ss[0];
//			for(int i=0;i<array.length;i++){
//				System.out.println(array[i]);
//			}
			
			for (int i = 1; ss != null && i < ss.length; i++) {
				String[] s = ss[i];
				System.out.println("连接服务器ID：" + s[3]);
				System.out.println("连接服务器IP：" + s[4]);
				// s[1] : NetWorkID s[2] : NetWorkIP s[3] : NetWorkPort
				this.getAppRuntime().CreateLocalChannel(s[3], s[4],
						Integer.parseInt(s[5]));

			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("connect other center is error, start server is fail");
			System.exit(1);
		}
		LogUtil.info("同其它服务器连接结束");

	}

	@Override
	public boolean isAllowLogin(String sessionID, String sessionIP) {
		return isSession(sessionID) && isIP(sessionIP);
	}

	private boolean isSession(String sessionID) {
		boolean b = false;
		if (sessionID == null) {
			b = true;
		} else {
			// 查询哪个表，得到结果
			b = allowClientDao.isSession(sessionID);
		}
		return b;
	}

	private boolean isIP(String sessionIP) {
		return true;
	}

}
