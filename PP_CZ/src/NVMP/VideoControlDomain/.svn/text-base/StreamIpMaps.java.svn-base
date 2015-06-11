package NVMP.VideoControlDomain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class StreamIpMaps {

	/**
	 * 根据真实IP，获取映射IP
	 * @param realIp
	 * @return
	 */
	public static String getVIP(String realIp){
		String path = "";
		if (System.getProperty("os.name").equals("Linux")) 
		{
			path = "/etc/fxconf/streamfoward/StreamFoward.conf";
		} else { 
			path = "D:\\fxconf\\streamfoward\\StreamFoward.conf";
		}
		if(IsExists(path)){
			Document doc = getDocument(path);
			Element root = doc.getRootElement();
			Element StreamIPMaps = root.getChild("StreamIPMaps");
			if(StreamIPMaps==null)
				return realIp;
			@SuppressWarnings("unchecked")
			List<Element> maps = StreamIPMaps.getChildren("IpMap");
			if(maps==null)
				return realIp;
			for(Element ip:maps){
				String real = ip.getAttributeValue("RealIp");
				String vip = ip.getAttributeValue("VIp");
				if(real.equals(realIp)){
					return vip;
				}
			}
		}
		return realIp;
	}
	
	public static Document getDocument(String path){
		SAXBuilder builder = new SAXBuilder();
		Document document = null;
		try {
			document = builder.build(new FileInputStream(new File(path)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return document;
	}
	
	/**
	 * 判断文件是否存在
	 * @param path
	 * @return
	 */
	public static boolean IsExists(String file){
		File f = new File(file);
		if(f.exists()){
			return true;
		}else{
			//如果文件不存在，就判断该文件存放的路径存不存在，如果不存在，就创建目录
			String path = "";
			if (System.getProperty("os.name").equals("Linux")){
				path = file.substring(0, file.lastIndexOf("/"));
			} else {
				path = file.substring(0, file.lastIndexOf("\\"));
			}
			File ff = new File(path);
			if(!ff.exists()){
				ff.mkdirs();
			} 
			return false;
		}

	}
}
