package com.fxdigital.syncclient.service;

import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.fxdigital.syncclient.bean.LocalCenter;
import com.fxdigital.syncclient.dao.IncrementdataInfotabDao;
import com.fxdigital.syncclient.dao.LocalCenterDao;

import fxdigital.mqcore.exchange.rpc.DBSyncContent;
import fxdigital.mqcore.exchange.rpc.Mail;
import fxdigital.mqcore.util.MessageChannelName;
import fxdigital.mqcore.util.MsgType;
import fxdigital.syncserver.app.impl.AppServer;
@Component
public class AutoIncrementUploadTask{
	private Log logger=LogFactory.getLog(AutoIncrementUploadTask.class);
	
	@Autowired
	private LocalCenterDao localCenterDao;

	@Autowired
	private IncrementdataInfotabDao incrementdataInfotabDao;
	@Autowired
	private BackupServer backupServer;
	public void run() {
		boolean isBackup=backupServer.isBackup();
		if(isBackup){
			logger.info("备用服务器，不启动自动增量扫描。");
			return;
		}
	LocalCenter localCenter=localCenterDao.queryInfo();
		logger.info("start AutoIncrementServer timertask begin..."
				+ localCenter.getId());
		List<Map<String, Object>> incrementdataInfo = incrementdataInfotabDao
				.queryIncrementData();

		if (null != incrementdataInfo && incrementdataInfo.size() > 0) {
			for (int i = 0; i < incrementdataInfo.size(); i++) {
				Map<String, Object> map = incrementdataInfo.get(i);
				System.out.println("AutoIncrementServer map"+map);
				String id = map.get("id").toString();
			
				String centerid = localCenter.getId();
				String centerip = localCenter.getIp();
				String centername = localCenter.getName();
				String xml = String.valueOf(map.get("incrementsql"));
				DBSyncContent content = new DBSyncContent();
				// content.setStrlist(strlist);
				content.setXml(xml);
				content.setSender(centerid);
				content.setCenterid(centerid);

				content.setCentername(centername);
				content.setCenterip(centerip);

				// 发送标记 flag=300
				content.setFlag(MsgType.INCREMENT_UP_CS);
				String json = JSON.toJSONString(content);
				int count = 0;
				Mail message=new Mail();
				message.setContent(json);
				message.setSendChannel(MessageChannelName.LOCAL_POST_CHANNEL);
				while (true) {
					if (count < 5) {
						if (AppServer.getInstance().send(message)) {
							// log.info("发送成功！发送内容："+content);
							logger.info("发送增量成功！发送内容：" + content);
							// 删除已经发送成功的项目
							incrementdataInfotabDao.deleteIncrementdataID(id);
							break;

						} else {
							// log.error("发送失败！发送内容："+content);
							logger.info("发送增量失败！发送内容：" + content);
							logger.info("1s后重新发送。。。");
							count++;
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}else{
						break;
					}
				}
			}
		}

	
		logger.info("start AutoIncrementServer timertask end..."
				+ localCenter.getId());
		
	}
	
	

}
