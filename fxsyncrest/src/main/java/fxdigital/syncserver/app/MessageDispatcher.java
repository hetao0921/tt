package fxdigital.syncserver.app;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;

import fxdigital.mqcore.exchange.rpc.DBSyncContent;
import fxdigital.mqcore.exchange.rpc.IMessage;
import fxdigital.mqcore.exchange.rpc.Mail;
import fxdigital.mqcore.util.MsgType;
import fxdigital.mqcore.util.RequestType;


public class MessageDispatcher implements IDispatcher{
	private static Log logger=LogFactory.getLog(MessageDispatcher.class);
	private HandlerChain handlerChain;





	@Override
	public void dispatcher(String strFlag,IMessage message) {
		handlerChain.chainProcess(strFlag, message);
		/*
		Mail mail = (Mail)message;
		if (strFlag.equals(RequestType.CLIENT_REQUEST)) {
			String strContent = mail.getContent();
			DBSyncContent content = JSON.parseObject(strContent,
					DBSyncContent.class);
			String flag = content.getFlag();
			if (flag.equals(MsgType.UP_INIT_CS)) {
				logger.info( "收到上传初始化的消息："
						+ strContent);
				upLoad.initUpLoad(content);
			}
			if (flag.equals(MsgType.UP_COMMAND_CS)) {
				logger
						.info( "收到上传操作的消息：" + strContent);
				logger.info("接收到处理上传初始化信息 flag=11");
				String selfServerVersion = String.valueOf(content.getVersion());
				//处理
			}
			if (flag.equals(MsgType.Load_INIT_CS)) {
				Log4jUtil.info(MessageDispatcher.class, "收到下载初始化的消息："
						+ strContent);
				downLoad.initDownLoad(content);
			}
			if (flag.equals(MsgType.Load_COMMAND_CS)) {
				Log4jUtil.info(MessageDispatcher.class, "收到下载操作的消息："
						+ strContent);
				downLoad.processDownCommand(content);
				}
			if (flag.equals(MsgType.AUTO_LOAD_CS)) {
				Log4jUtil.info(MessageDispatcher.class, "收到自动下载操作的消息："
						+ strContent);
				downLoad.processAutoDownLoad(content);
			}
			if (flag.equals(MsgType.INCREMENT_UP_CS)) {
				Log4jUtil.info(MessageDispatcher.class, "收到增量上传操作的消息："
						+ strContent);
				upLoad.processIncrementUpCommand(content);
			}
		}
	*/
//		Mail mail = (Mail)message;
//		if (strFlag.equals(RequestType.CLIENT_REQUEST)) {
//			String strContent = mail.getContent();
//			DBSyncContent content = JSON.parseObject(strContent,
//					DBSyncContent.class);
//			String flag = content.getFlag();
//			if (flag.equals(MsgType.AUTO_LOAD_SC)) {
//				logger.info("业务层收到自动下载消息。");
//				
//			}
//		}
	
	}






	@Override
	public void setHandler(HandlerChain handlerChain) {
		this.handlerChain=handlerChain;
		
	}
}
