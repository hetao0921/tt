package fxdigital.postserver.contentdispose.handlers.dbsync.service;

import java.io.RandomAccessFile;
/**
 * @author  het
 *上传消息处理类
 * 2014-7-30
 * BusinessSync
 * fxdigital.postserver.contentdispose.handlers.dbsync.service
 */
public class UpMessage {
	public static UpMessage upMessage = null;

	public static UpMessage getInstance() {
		if (null == upMessage) {
			upMessage = new UpMessage();
		}
		return upMessage;
	}
	
	
	/**
	 * 写xml文件
	 * 
	 * @return
	 */
	public void wirteXml(String address,String xml){
		try {
			RandomAccessFile raf = new RandomAccessFile(address, "rw");
			raf.setLength(0);
			raf.seek(0);
			raf.write(xml.getBytes("utf-8"));
			raf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
