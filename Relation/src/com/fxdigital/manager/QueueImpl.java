package com.fxdigital.manager;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fxdigital.db.dao.CenterInfoDao;
import com.fxdigital.db.dao.LocalCenterDao;
import com.fxdigital.db.dao.QueueUpLinkDao;
import com.fxdigital.db.pojo.UpLink;
import com.mysql.jdbc.StringUtils;

@Component
public class QueueImpl {
	
	@Autowired
	private LocalCenterDao localCenterDao;
	
	@Autowired
	private QueueUpLinkDao queueUpLinkDao;
	
	@Autowired
	private CenterInfoDao centerInfoDao;

	/**
	 * 获取数据库中的本级IP
	 * 
	 * @return
	 */
	public String getLocalIp() {

		return localCenterDao.query();
	}
	
	public int getMaxID(){
		return queueUpLinkDao.queryForID();
	}

	/**
	 * 获取数据库中的上级级IP
	 * 
	 * @return
	 */
	public List<UpLink> getParentIp() {
		return queueUpLinkDao.query();
	}

	public boolean delIp(String ip) {
		if(queueUpLinkDao.delete(ip) > 0)
			return true;
		else
			return false;
	}

	/**
	 * 修改本级IP和上级IP
	 * 
	 * @param ip
	 * @param parentIp
	 * @return
	 */
	public boolean upId(String ip, String type, int id) {
		if ("local".equals(type)) {
			String centerID = centerInfoDao.query();
			if(centerID == null)
				return false;
			return upLocalIp(ip, centerID, 0);
		} else {
			return upParentIp(ip, null, 0, id);
		}
	}

	/**
	 * 从当前的Device.xml文件中获取本级ID
	 * 
	 * @return
	 */
	private String getLocalId() {
		String id = "";
		File f = new File(getPath());
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(f);
			Element root = doc.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> list = root.elements("Device");
			for (Element el : list) {
				if (el.attributeValue("Type").equals("001")) {
					id = el.attributeValue("DeviceSN");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	/**
	 * 获取当前配置文件Device.xml的路径
	 * 
	 * @return
	 */
	private String getPath() {
		String path = null;
		if (System.getProperty("os.name").equals("Linux")) {
			path = "/etc/fxconf/config/Device.xml";

		} else {
			path = "C:\\WINDOWS\\system32\\config\\Device.xml";
		}
		return path;
	}

	/**
	 * 修改数据库中的本级IP
	 * 
	 * @param ip
	 * @param centerID
	 * @param port
	 * @return
	 */
	private boolean upLocalIp(String ip, String centerID, int port) {
		int result = 0;
		if(localCenterDao.isExsit(centerID)){
			result = localCenterDao.update(centerID, ip, port);
		}else{
			if (centerID == null || centerID.equals(""))
				centerID = getLocalId();
			if (port == 0)
				result = localCenterDao.insert(centerID, ip);
			else
				result = localCenterDao.insert(centerID, ip, port);
		}
		if(result > 0)
			return true;
		else
			return false;
	}

	/**
	 * 修改数据库中的上级IP
	 * 
	 * @param ip
	 * @param centerID
	 * @param port
	 * @return
	 */
	private boolean upParentIp(String ip, String centerID, int port, int id) {
		int result = 0;
		if(queueUpLinkDao.isIPExsit(ip)){
			return false;
		}else if(!queueUpLinkDao.isIDExsit(id) && !queueUpLinkDao.isIPExsit(ip)){
			if (centerID == null)
				centerID = getLocalId();
			if (port != 0)
				result = queueUpLinkDao.insert(centerID, ip, port, "up");
			else
				result = queueUpLinkDao.insert(centerID, ip, "up");
		}else{
			if(!StringUtils.isNullOrEmpty(centerID) && port > 0)
				result = queueUpLinkDao.update(id, centerID, ip, port);
			else if(StringUtils.isNullOrEmpty(centerID) && port > 0)
				result = queueUpLinkDao.update(id, ip, port);
			else if(!StringUtils.isNullOrEmpty(centerID) && port <= 0)
				result = queueUpLinkDao.update(id, centerID, ip);
			else
				result = queueUpLinkDao.update(id, ip);
		}
		if(result > 0)
			return true;
		else
			return false;
	}
}
