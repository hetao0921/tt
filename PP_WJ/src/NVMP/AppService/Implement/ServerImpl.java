package NVMP.AppService.Implement;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.misc.log.LogUtil;

import com.sqlite.factory.DAOFactory;

import NVMP.AppService.ServerInit;
import NVMP.AppService.util.GetDeviceId;
 
public class ServerImpl extends ServerInit {

	@Override
	public String getSessionID() {
		System.out.println("get sessionid");
//		System.out.println(GetDeviceId.getInstance().getDeviceId("001"));
//		return GetDeviceId.getInstance().getDeviceId("001");
		return "A1c1595eada@001";
	}

	
	@Override
	public int getPort() {
		// TODO Auto-generated method stub
		// 开始初始化本地数据
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
					"http://" + hostip + ":8080/webservice/services/web","/ServerDB");
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
			for (int i = 1; ss != null && i < ss.length; i++) {
				String[] s = ss[i];
				System.out.println("连接服务器ID：" + s[1]);
				System.out.println("连接服务器IP：" + s[2]);
				// s[1] : NetWorkID s[2] : NetWorkIP s[3] : NetWorkPort
				this.getAppRuntime().CreateLocalChannel(s[1], s[2],
						Integer.parseInt(s[3]));

			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("connect other center is error, start server is fail");
			System.exit(1);
		}
		LogUtil.info("同其它服务器连接结束");

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


}
