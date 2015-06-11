package NVMP.IpMatrixManage.Implement;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.fxdigital.ipmatrix.eMaxtrixCategory;

public class Daemon {

	private Map<String, String> getHostInfo() throws DocumentException {
		Map<String, String> hp = new HashMap<String, String>();
		String path;
		if (System.getProperty("os.name").equals("Linux"))
			path = "/etc/fxconf/hikmatrix/hikmatrix.xml";
		else
			path = "d:\\nvmpdata\\hikmatrix\\hikmatrix.xml";

		SAXReader saxReader = new SAXReader();
		Document doc = null;

		doc = saxReader.read(new File(path));
		Element element = doc.getRootElement().element("Maxtrix");
		if (element.attributeValue("IP") != null) {
			hp.put("IP", element.attributeValue("IP"));
			hp.put("PORT", element.attributeValue("PORT"));
		} else {
			if (System.getProperty("os.name").equals("Linux"))
				path = "/etc/fxconf/AppService/AppService.conf";
			else
				path = "d:\\nvmpdata\\AppService\\AppService.conf";
			doc = saxReader.read(new File(path));
			element = doc.getRootElement().element("AppServer");
			hp.put("IP", element.attributeValue("IP"));
			hp.put("PORT", element.attributeValue("PORT"));

		}

		return hp;
	}

	private List<String> getMatrixParamList(boolean readflag) {
		List<String> list = new LinkedList<String>();
		try {

			Map<String, String> hp = getHostInfo();
			String hostip = hp.get("IP");
			int port = Integer.parseInt(hp.get("PORT"));

			String path;

			if (System.getProperty("os.name").equals("Linux"))
				path = "/etc/fxconf/hikmatrix/hikmatrix.xml";
			else
				path = "d:\\nvmpdata\\hikmatrix\\hikmatrix.xml";

			// 读一下配置文件中的配置。
			SAXReader saxReader = new SAXReader();
			Document doc = null;

			doc = saxReader.read(new File(path));

			@SuppressWarnings("rawtypes")
			Iterator itor = doc.getRootElement().elements("Maxtrix").iterator();
			while (itor.hasNext()) {
				Element element = (Element) itor.next();

				String szAddress;
				String sessionid;
				int nPort;
				String szName;
				String szPass;
				int decMaxtrixStatus;
				int nDeviceType = eMaxtrixCategory.eMaxtrixCategory_Hik;
				int nSubDeviceType = 0;
				String deviceClass;
				@SuppressWarnings("unchecked")
				Iterator<Element> iter = element.elementIterator();
				while (iter.hasNext()) {
					try {
						Element e = iter.next();
						szAddress = e.attributeValue("Address");
						sessionid = e.attributeValue("DeviceId");
						nPort = Integer.parseInt(e.attributeValue("Port"));
						szName = e.attributeValue("Admin");
						szPass = e.attributeValue("Pass");

						decMaxtrixStatus = Integer.parseInt(e
								.attributeValue("OutModel"));
						if (readflag) {
							if (decMaxtrixStatus == 2)
								continue;
						}
						nDeviceType = Integer.parseInt(e
								.attributeValue("DeviceType"));
						deviceClass = e.attributeValue("class");
						// IpMatrixManageRun imr = new IpMatrixManageRun(hostip,
						// port, sessionid, nDeviceType, nSubDeviceType,
						// nPort, szName, szPass, szAddress,
						// decMaxtrixStatus,deviceClass);
						// imr.startUp();

						StringBuilder param = new StringBuilder();
						param.append(szAddress);
						param.append(" ");
						param.append(sessionid);
						param.append(" ");
						param.append(nPort);
						param.append(" ");
						param.append(szName);
						param.append(" ");
						param.append(szPass);
						param.append(" ");
						param.append(decMaxtrixStatus);
						param.append(" ");
						param.append(nDeviceType);
						param.append(" ");
						param.append(nSubDeviceType);
						// param.append(0);
						param.append(" ");
						param.append(deviceClass);
						param.append(" ");
						param.append(hostip);
						param.append(" ");
						param.append(port);

						list.add(param.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	private String getParam(String pathname) {
		StringBuilder result = new StringBuilder();
		File f = new File(pathname);
		File[] files = f.listFiles();
		String delim = System.getProperty("path.separator");

		for (int i = 0; i < files.length; ++i) {
			String fileName = files[i].getName();
			int length = fileName.length();
			if (length > 4 && fileName.endsWith(".jar")) {
				if (i != 0) {
					result.append(delim);
				}

				result.append(fileName);
			}
		}

		return result.toString();
	}

	private String getParam() {
		StringBuilder result = new StringBuilder();
		String delim = System.getProperty("path.separator");
		String result1 = getParam("/usr/local/center/IpMatrix");
		String result2 = getParam("/usr/local/center/IpMatrix/lib");
		result.append(result1);
		result.append(delim);
		result.append(result2);

		return result.toString();
	}

	/**
	 * 当子进程消亡，调用该方法
	 * */
	public void fireEvent(Monitor m) {
		m = new Monitor(this, m.getCommand());
		m.start();
	}

	public void init(boolean flag) {
		String param = getParam();
		List<String> list1 = getMatrixParamList(flag);
		List<String> list2 = new LinkedList<String>();
		for (String elem : list1) {
			StringBuilder command = new StringBuilder();
			command.append("java -cp ");
			command.append("./*:./lib/*");
			command.append(" NVMP.IpMatrixManage.Implement.IpMatrixManageRun ");
			command.append(elem);
			list2.add(command.toString());
		}

		for (String command : list2) {
			try {
				System.out.println("--------Begin of java command-------");

				Monitor m = new Monitor(this, command);
				m.start();
				Thread.sleep(3000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Daemon daemon = new Daemon();
		daemon.init(false);
	}
}
