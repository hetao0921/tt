package NVMP.VideoServerDomain;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import NVMP.IpMatrixManage.Implement.Daemon;
import NVMP.IpMatrixManage.Implement.IpMatrixManageRun;

import com.fxdigital.ipmatrix.eMaxtrixCategory;

public class IpMatrixManage extends Thread {
	
	private Map<String,String> getHostInfo() throws DocumentException{
		Map<String,String> hp = new HashMap<String, String>();
		String path;
		if (System.getProperty("os.name").equals("Linux"))
			path = "/etc/fxconf/hikmatrix/hikmatrix.xml";
		else
			path = "d:\\nvmpdata\\hikmatrix\\hikmatrix.xml";
		
		SAXReader saxReader = new SAXReader();
		Document doc = null;

		doc = saxReader.read(new File(path));
		Element element= doc.getRootElement().element("Maxtrix");
		if(element.attributeValue("IP")!=null) {
			hp.put("IP", element.attributeValue("IP"));
			hp.put("PORT", element.attributeValue("PORT"));
		} else {
			if (System.getProperty("os.name").equals("Linux"))
				path = "/etc/fxconf/AppService/AppService.conf";
			else
				path = "d:\\nvmpdata\\AppService\\AppService.conf";
			doc = saxReader.read(new File(path));
			element= doc.getRootElement().element("AppServer");
			hp.put("IP", element.attributeValue("IP"));
			hp.put("PORT", element.attributeValue("PORT"));	
			
		}
		
		return hp;
	}
	
	
	@Override
	public void run() {
		//启动守护进程，调用8060板卡控制进程和vga软解进程
		Daemon daemon = new Daemon();
		daemon.init(true);
		
		try {
			
			
			
			Map<String,String> hp = getHostInfo();
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
						if(decMaxtrixStatus!=2) continue;
						deviceClass = e.attributeValue("class");
						IpMatrixManageRun imr = new IpMatrixManageRun(hostip,
								port, sessionid, nDeviceType, nSubDeviceType,
								nPort, szName, szPass, szAddress,
								decMaxtrixStatus,deviceClass);
						imr.startUp();
					} catch (Exception e) {
						e.printStackTrace();

					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws DocumentException {
		IpMatrixManage im = new IpMatrixManage();
		System.out.println(im.getHostInfo());
		
	}
}
