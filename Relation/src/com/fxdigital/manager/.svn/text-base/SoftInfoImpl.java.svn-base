package com.fxdigital.manager;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.fxdigital.SysAuth;

public class SoftInfoImpl {

	private static SoftInfoImpl instance;

	private SoftInfoImpl() {
	}

	public static SoftInfoImpl getInstance() {
		if (instance == null) {
			instance = new SoftInfoImpl();
		}
		return instance;
	}

	/**
	 * 获取设备型号信息
	 * 
	 * @return
	 */
	public String getDeviceType() {
		String type = null;
		String path = getPath();
		if(path==null){
			return "未授权";
		}
		File f = new File(path);
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(f);
			Element root = doc.getRootElement();
			type = root.attributeValue("DeviceType");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return type;
	}

	/**
	 * 获取软件版本信息
	 * 
	 * @return
	 */
	public String getSoftVersion() {
		String version = null;
		String path = getPath();
		if(path==null){
			return "未授权";
		}
		File f = new File(path);
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(f);
			Element root = doc.getRootElement();
			version = root.attributeValue("version");
			version = "V"+version;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (version == null)
			version = "V1.0";
		return version;
	}

	/**
	 * 获取软件授权时间
	 * 
	 * @return
	 */
	public String getLicenseTime() {
		String time = null;
		String path = getPath();
		if(path==null){
			return "未授权";
		}
		File f = new File(path);
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(f);
			Element root = doc.getRootElement();
			time = root.attributeValue("SerialNumber");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(time!=null&&time.length()>=14){
			time = time.substring(0, 4) + "-" + time.substring(4, 6) + "-"
			+ time.substring(6, 8) + " " + time.substring(8, 10) + ":"
			+ time.substring(10, 12) + ":" + time.substring(12, 14);
		}
		
		return time;
	}
	
	/**
	 * 获取MAC地址
	 * @return
	 */
	public String[] getMacAddress(){
		try{
			String[] mac = SysAuth.getMacAddr();
			if(mac==null||mac.length==0){
				return null;
			}
			return mac;
		}catch(Exception e){
			System.out.println("获取MAC地址出错。。。"+e.getMessage());
			return null;
		}
	}

	private String getPath() {
		String path = null;
		if (System.getProperty("os.name").equals("Linux")) {
			path = "/etc/fxconf/config/Device.xml";
			       
		} else {
			path = "C:\\WINDOWS\\system32\\config\\Device.xml";
		}
		if(new File(path).exists()){
			return path;
		}else{
			return null;
		}
	}

	public static void main(String... args) {
		SoftInfoImpl sii = SoftInfoImpl.getInstance();
		String type = sii.getLicenseTime();
		System.out.println("type:" + type);
	}
}
