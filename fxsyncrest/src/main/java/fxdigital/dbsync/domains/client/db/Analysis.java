package fxdigital.dbsync.domains.client.db;

import com.fxdigital.syncclient.analysis.bean.Notice;



public interface Analysis {
/**
 * 	 * 解析json
	 * 插入sql
	 * 通知客户端
 * @param xml
 * @param centerid
 * @return
 * @throws Exception 
 */
	public boolean analysis(String json, String centerid,Notice notice) throws Exception;

}
