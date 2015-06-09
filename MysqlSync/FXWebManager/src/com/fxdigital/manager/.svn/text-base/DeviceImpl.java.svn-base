package com.fxdigital.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;

import com.fxdigital.bean.Device;
import com.fxdigital.bean.Index;
import com.fxdigital.bean.Quality;
import com.fxdigital.util.Common;

public class DeviceImpl{
	
	private static final Logger log = Logger.getLogger(DeviceImpl.class);

	private static DeviceImpl deviceInstance;

	synchronized public static DeviceImpl getDeviceInstance() {
		if (deviceInstance == null)
			deviceInstance = new DeviceImpl();
		return deviceInstance;
	}

	private DeviceImpl() {
	}

	/**
	 * 判断当前用户是否已经授权 如果未授权，返回null，否则返回设备类型 如：FXH3300、FXH3310、FXH312等
	 * 
	 * @return
	 */
	public String isLicense() {
		String path = "";
		if (System.getProperty("os.name").equals("Linux")) {
			path = "/etc/fxconf/config/Device.xml";
		} else {
			path = "C:\\WINDOWS\\system32\\config\\Device.xml";
		}
		if (Common.IsExists(path)) {
			Document doc = Common.getDocument(path);
			Element root = doc.getRootElement();
			String type = root.getAttributeValue("DeviceType");
			return type;
		} else {
			return null;
		}
	}

	public Device getDevice() {
		String path = getPath();
		Device d = new Device();
		if (Common.IsExists(path)) {
			Document doc = Common.getDocument(path);
			Element root = doc.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> indexs = root.getChild("Device").getChild("Page")
					.getChildren("Index");
			List<Index> listI = new ArrayList<Index>();
			for (Element e : indexs) {
				Index in = new Index();
				if (e.getAttributeValue("content") != null) {
					in.setContent(e.getAttributeValue("content"));
				}
				if (e.getAttributeValue("url") != null) {
					in.setUrl(e.getAttributeValue("url"));
				}
				List<Index> childs = new ArrayList<Index>();
				@SuppressWarnings("unchecked")
				List<Element> indexChilds = e.getChildren("IndexChild");
				for (Element el : indexChilds) {
					Index ind = new Index();
					if (el.getAttributeValue("content") != null) {
						ind.setContent(el.getAttributeValue("content"));
					}
					if (el.getAttributeValue("url") != null) {
						ind.setUrl(el.getAttributeValue("url"));
					}
					childs.add(ind);
				}
				in.setChildIndexs(childs);
				listI.add(in);
			}
			d.setIndexList(listI);
			@SuppressWarnings("unchecked")
			List<Element> qualitys = root.getChild("Device").getChildren(
					"Quality");
			List<Quality> listQ = new ArrayList<Quality>();
			for (Element e : qualitys) {
				Quality q = new Quality();
				if (e.getAttributeValue("qname") != null) {
					q.setQname(e.getAttributeValue("qname"));
				}
				if (e.getAttributeValue("isreadonly") != null)
					q.setIsreadonly(Boolean.parseBoolean(e
							.getAttributeValue("isreadonly")));
				if (e.getAttributeValue("defaultvalue") != null)
					q.setDefaultvalue(e.getAttributeValue("defaultvalue"));
				@SuppressWarnings("unchecked")
				List<Element> values = e.getChildren("value");
				List<String> listV = new ArrayList<String>();
				List<String> listT = new ArrayList<String>();
				for (Element el : values) {
					if (el.getAttributeValue("realvalue") != null)
						listV.add(el.getAttributeValue("realvalue"));
					if (el.getText() != null)
						listT.add(el.getText());
				}
				q.setListValue(listV);
				q.setListTest(listT);
				listQ.add(q);
			}
			d.setQualityList(listQ);
		} else {
			log.warn("hikmatrix.xml文件不存在");
		}
		return d;
	}

	/**
	 * 获取当前配置文件的路径
	 * 
	 * @return
	 */
	private String getPath() {
		// 获取当前设备的类型
		String type = isLicense();
		// 根据当前设备类型，选择该读取哪一个配置文件来初始化界面
		String folderName = "";
		if (type != null && !type.equals("")) {
			if (type.equals("FXH3300")) {
				folderName = "lvs";
			} else if (type.equals("FXH3310") || type.equals("FXH312")
					|| type.equals("FXH3120")) {
				folderName = "312";
			} else if (type.equals("FXH8060")) {
				folderName = "8060";
			} else if (type.equals("FXH6200")) {
				folderName = "6200";
			} else if (type.equals("FXH3400") || type.equals("FXH3360")) {
				// 之前是确定3350和3360是同步级联配置，在6月6日，将FXH3350改为FXH3400
				folderName = "3350";
			} else if (type.equals("FXH2210")) {
				folderName = "2210";
			} else if (type.equals("FXH3220")) {
				folderName = "3220";
			} else {
				folderName = "3320";
			}
		} else {
			folderName = "other";
		}
		// 构建文件路径
		String path = "";
		if (System.getProperty("os.name").equals("Linux")) {
			path = "/etc/fxconf/DeviceMsg/" + folderName + "/DeviceMsg.xml";
		} else {
			path = "d:\\fxconf\\DeviceMsg\\" + folderName + "\\DeviceMsg.xml";
		}
		return path;
	}
}
