package fxdigital.syncserver.business;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

import fxdigital.mqcore.exchange.rpc.DBSyncContent;
import fxdigital.mqcore.exchange.rpc.Mail;
import fxdigital.syncserver.business.hibernate.bean.DataOperateRecord;
import fxdigital.syncserver.business.hibernate.dao.DataIncrementVersionDao;
import fxdigital.syncserver.business.hibernate.dao.DataOperateRecordDao;
import fxdigital.syncserver.business.hibernate.dao.DataSourceDao;
import fxdigital.util.FileUtil;
import fxdigital.util.Log4jUtil;
import fxdigital.util.MsgType;

public class ResetLoad {
	List<HashMap<String,String>> resetlist=null;
	public void initResetLoad(DBSyncContent content){
		String centerid = content.getCenterid();
		String centerip = content.getCenterip();
		content.setFlag(MsgType.Load_RESET_SC);
		resetlist = DataSourceDao.getInstance().getResetServerSource(
				centerid);
		content.setList(resetlist);
		// TODO
		send(content);
	}
	
	
	public  void send(DBSyncContent content){
		Mail message=new Mail();
		message.setDestMailboxID(content.getCenterid());
		message.setContent(JSONObject.toJSONString(content));
		UpLoadBusiness.getSender().send(message);
	}
	
	
	public void processResetLoad(DBSyncContent content){
		String centerid = content.getCenterid();
		String centername = content.getCentername();
		String centerip = content.getCenterip();
		int version = content.getVersion();


		// 得到所有的增量版本
		// 得到下一个全量版本
//		String nextVersion = ResetLoadUtil.getInstance().getNextAllVersion(resetlist,
//				String.valueOf(version));
//		List<String> resetIncrementVersionList = ResetLoadUtil.getInstance().getResetIncrementVersion(
//				centerid, version, nextVersion);
		
		String maxIncrementVersion=ResetLoadUtil.getInstance().getMaxIncrementVersion(centerid);
		List<HashMap<String, String>> resetIncrementXml =null;
		List<String> resetIncrementVersionList=null;
		if(null!=maxIncrementVersion){
			resetIncrementVersionList=ResetLoadUtil.getInstance().getAllIncrementVersion(centerid, maxIncrementVersion);
		}
		if(null!=resetIncrementVersionList&&resetIncrementVersionList.size()>0){
			resetIncrementXml =  ResetLoadUtil.getInstance().getResetIncrementXml(
					resetIncrementVersionList, centerid);
		}
		Log4jUtil.info(this.getClass(),"增量量版本：centerid " + centerid + " 所有增量版本: "
				+ resetIncrementVersionList);
		List<HashMap<String, String>> addressList = DataSourceDao
				.getInstance().getSourceAddress(centerid,
						String.valueOf(version));
		String newXml = null;
		String oldXml = null;
		if (null != addressList && addressList.size() > 0) {
			String newfileaddress = addressList.get(0).get("fileaddress");
			String oldfileaddress = addressList.get(0)
					.get("oldfileaddress");
			Log4jUtil.info(this.getClass(),"下载文件地址newfileaddress   " + newfileaddress);
			newXml = FileUtil.getInstance().readFileByLines(
					newfileaddress);
			oldXml = FileUtil.getInstance().readFileByLines(
					oldfileaddress);
		}
		HashMap<String, String> map = new HashMap<String, String>();
		//map.put("oldXml", oldXml);
		map.put("newXml", newXml);
		map.put("incrementXml", JSONObject.toJSONString(resetIncrementXml));
		String json = JSONObject.toJSONString(map);
		Log4jUtil.info(this.getClass(),"全部下载数据：centerid " + centerid + " 所有数据: " + json);
		content.setXml(json);
		// Log4jUtil.info(this.getClass(),"xml----" + xml);
		content.setFlag(MsgType.Load_RESET_SECOND_SC);
		// Log4jUtil.info(this.getClass(),"Load_RESET_SECOND_SC"
		// + MsgType.Load_RESET_SECOND_SC);

		// 修改操作记录data_operate_record
		DataOperateRecord dataOperateRecord = new DataOperateRecord();
		dataOperateRecord.setOperate("还原");
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateNowStr = sdf.format(d);
		dataOperateRecord.setOperatetime(dateNowStr);
		dataOperateRecord.setCenterid(centerid);
		dataOperateRecord.setCentername(centername);
		dataOperateRecord.setOperatorip(centerip);
		DataOperateRecordDao.getInstance().insert(dataOperateRecord);

		send(content);
	
	}

}
