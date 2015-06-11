package fxdigital.postserver.contentdispose;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import fxdigital.db.DbManager;
import fxdigital.db.LocalCenter;
import fxdigital.postserver.innertransmition.InnerTransmitter;
import fxdigital.postserver.outertransmition.OuterTransmitter;

/**
 * 消息处理的抽象类
 * @author fucz
 * @version 2014-7-8
 */
public abstract class AbstractHandler implements IHandler{
	
	@Autowired
	protected ContentHandler contentHandler;

	@Autowired
	protected DbManager dbManager;
	@Autowired
	protected OuterTransmitter outerTransmitter;
	@Autowired
	protected InnerTransmitter innerTransmitter;
	protected LocalCenter localCenter;
	protected String localCenterID;
	
	@PostConstruct
	protected void init(){
		localCenter = dbManager.getLocalCenter();
		localCenterID = localCenter.getId();
		//注册到消息内容分发器
		contentHandler.register(getModeAndType(), this);
		afterInit();
	}
	
	protected void afterInit(){
		
	}
	
}
