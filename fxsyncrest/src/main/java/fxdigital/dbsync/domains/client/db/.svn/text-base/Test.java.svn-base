package fxdigital.dbsync.domains.client.db;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fxdigital.syncclient.analysis.bean.Notice;



public class Test implements Analysis{
	private static Log logger=LogFactory.getLog(Test.class);
	@Override
	public boolean analysis(String xml, String centerid, Notice notice) {
		boolean result=true;
		logger.info("测试文件大小:"+xml.length()+"byte"+"---处理时间:"+new Date().toGMTString());
//		UDPClient.send(notice);
		return result;
	}

}
