package NVMP.SystemMessageDomain;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import NVMP.AppService.Remoting;
import NVMP.AppService.util.GetDeviceId;
import NVMP.CommandDomain.CommandDomain;

public class MessageManage {
	public IMessageManage Message;
	
	/**
	 * 询问系统版权什么的 
	 * 
	 * */
	@Remoting
	public void GetCenterID(String userid) {
//		SAXReader saxReader = new SAXReader();
//		Document doc = null;
//		String path = null;
//		if (System.getProperty("os.name").equals("Linux"))
//			path = "/etc/fxconf/config/Device.xml";
//		else
//			path = "C:\\Windows\\System32\\config\\Device.xml";
//		try {
//			doc = saxReader.read(new File(path));
//		} catch (DocumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			
//		String centerSessiodId = null;
//		String devSessionId = null;
//		@SuppressWarnings("unchecked")
//		Iterator<Element> iter = doc.getRootElement().elementIterator();
//		while (iter.hasNext()) {
//			Element e = iter.next();
//			if (e.attributeValue("Type").equals("001")) {
//				centerSessiodId = e.attributeValue("DeviceSN");
//			}
//			if (e.attributeValue("Type").equals("002")) {
//				devSessionId = e.attributeValue("DeviceSN");
//			}
//		}
		
		try {
		
		String centerSessiodId = GetDeviceId.getInstance().getDeviceId("001");
		String devSessionId = GetDeviceId.getInstance().getDeviceId("002");
		(SystemMessageDomain.AppRunTime())
		.SetCurChannel("Session://" + userid);
		Message.SendCenterID(centerSessiodId,devSessionId); 
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	

	
	
	
}
