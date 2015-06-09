package fxdigital.syncserver.business;

import java.util.List;

import com.alibaba.fastjson.JSON;

import fxdigital.mqcore.exchange.rpc.DBSyncContent;
import fxdigital.mqcore.exchange.rpc.IMessage;
import fxdigital.mqcore.exchange.rpc.Mail;
import fxdigital.syncserver.app.IAppServer;
import fxdigital.syncserver.app.IBusiness;
import fxdigital.util.Log4jUtil;
import fxdigital.util.MsgType;
import fxdigital.util.RequestType;

public class UpLoadBusiness implements IBusiness{
	public static IAppServer sender ;
	UpLoad upLoad=null;
	DownLoad downLoad=null;
	ResetLoad resetLoad=null;
	
	public static IAppServer getSender(){
		return sender;
	} 

	@Override
	public List<String> getHandler() {
		return null;
	}

	@Override
	public void regApp(IAppServer appServer) {
		sender = appServer;
		upLoad=new UpLoad();
		downLoad=new DownLoad();
		resetLoad=new ResetLoad();
	}

	@Override
	public void OnMessage(String strFlag,IMessage message) {
		Mail mail = (Mail)message;
		if (strFlag.equals(RequestType.CLIENT_REQUEST)) {
			String strContent = mail.getContent();
			DBSyncContent content = JSON.parseObject(strContent,
					DBSyncContent.class);
			String flag = content.getFlag();
			if (flag.equals(MsgType.UP_INIT_CS)) {
				Log4jUtil.info(UpLoadBusiness.class, "收到上传初始化的消息："
						+ strContent);
				upLoad.initUpLoad(content);
			}
			if (flag.equals(MsgType.UP_COMMAND_CS)) {
				Log4jUtil
						.info(UpLoadBusiness.class, "收到上传操作的消息：" + strContent);
				upLoad.processUpCommand(content);
			}
			if (flag.equals(MsgType.Load_INIT_CS)) {
				Log4jUtil.info(UpLoadBusiness.class, "收到下载初始化的消息："
						+ strContent);
				downLoad.initDownLoad(content);
			}
			if (flag.equals(MsgType.Load_COMMAND_CS)) {
				Log4jUtil.info(UpLoadBusiness.class, "收到下载操作的消息："
						+ strContent);
				downLoad.processDownCommand(content);
				}
			if (flag.equals(MsgType.AUTO_LOAD_CS)) {
				Log4jUtil.info(UpLoadBusiness.class, "收到自动下载操作的消息："
						+ strContent);
				downLoad.processAutoDownLoad(content);
			}
			if (flag.equals(MsgType.INCREMENT_UP_CS)) {
				Log4jUtil.info(UpLoadBusiness.class, "收到增量上传操作的消息："
						+ strContent);
				upLoad.processIncrementUpCommand(content);
			}
			// 处理还原命令flag=3
			if (flag.equals(MsgType.Load_RESET_CS)) {
				resetLoad.initResetLoad(content);
			}
			// 处理还原命令flag=5
			if (flag.equals(MsgType.Load_RESET_SECOND_CS)) {
				resetLoad.processResetLoad(content);
			}
		}
	}
}
