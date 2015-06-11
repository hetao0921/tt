package fxdigital.util;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

/**
 * 读取数据库IP
 * @author fucz
 * @version 2014-7-11
 */
@Component
public class ReadDBIP {
	
	private String dbIP;
	
	@PostConstruct
	public void init(){
		this.dbIP = ConfigUtil.getString("NVMP.AppServer:DBIP");
	}

	public String getDbIP() {
		return dbIP;
	}

	public void setDbIP(String dbIP) {
		this.dbIP = dbIP;
	}
	
	
}
