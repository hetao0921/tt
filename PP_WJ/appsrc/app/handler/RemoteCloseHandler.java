package app.handler;

import org.misc.log.LogUtil;

import app.ConnectChannel;
import NVMP.Proxy.RemoteCloseDomain;

/**
 * 
 * @author hxht
 * @version 2014-11-11
 */
public class RemoteCloseHandler extends RemoteCloseDomain.EventHandler{
	
	ConnectChannel channel;

	public RemoteCloseHandler(ConnectChannel channel) {
		this.channel = channel;
	}
	
	@Override
	public Object onNoticeClientEvent(String returnMsg, String context,
			String uuID) {
		String padIP = channel.getPad().getBean().getSinglePadIP();
		LogUtil.info("单兵系统：收到遥闭指令（"+padIP+"）------");
		LogUtil.info("returnMsg："+returnMsg);
		LogUtil.info("context："+context);
		LogUtil.info("uuID："+uuID);
		//遥闭，重连中心
		channel.noticeOffLine();
		channel.start();
		return null;
	}
	
}
