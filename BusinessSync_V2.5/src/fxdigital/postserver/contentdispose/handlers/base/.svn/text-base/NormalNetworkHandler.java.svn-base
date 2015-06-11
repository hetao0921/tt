package fxdigital.postserver.contentdispose.handlers.base;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import fxdigital.postserver.contentdispose.AbstractHandler;
import fxdigital.postserver.network.NetworkMonitor;
import fxdigital.postserver.network.SubResponse;
import fxdigital.postserver.network.SupResponse;
import fxdigital.rpc.ModeAndType;
import fxdigital.rpc.content.base.NetworkContent;
import fxdigital.rpc.contenttype.base.NetworkType;
import fxdigital.rpc.sendmode.NormalMode;

/**
 * 级联申请消息处理
 * @author fucz
 * @version 2014-7-15
 */
@Component
public class NormalNetworkHandler extends AbstractHandler{
	
	private static final Logger log = Logger.getLogger(NormalNetworkHandler.class);
	
	@Autowired
	private SupResponse supResponse;
	@Autowired
	private SubResponse subResponse;

	@Override
	public boolean handle(String strContent) {
		log.info("收到级联申请反馈信息："+strContent);
		NetworkContent content = JSON.parseObject(strContent, NetworkContent.class);
		int status = content.getStatus();
		if(status == NetworkMonitor.APPLYING
				|| status == NetworkMonitor.CANCELING){
			return supResponse.response(content.getSubStatus());
		}
		if(status == NetworkMonitor.AGREEING
				|| status == NetworkMonitor.DELETING
				|| status == NetworkMonitor.REJECTING){
			return subResponse.response(content.getSupStatus());
		}
		log.warn("状态不能解析，消息返回通道！");
		return false;
	}

	@Override
	public ModeAndType getModeAndType() {
		return new ModeAndType(NormalMode.MODE,NetworkType.TYPE);
	}

}
