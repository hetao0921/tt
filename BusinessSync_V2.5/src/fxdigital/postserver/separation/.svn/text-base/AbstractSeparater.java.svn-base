package fxdigital.postserver.separation;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import fxdigital.db.DbManager;
import fxdigital.postserver.contentdispose.ContentHandler;
import fxdigital.postserver.outertransmition.OuterTransmitter;

/**
 * 实现了邮件分发器接口的抽象类
 * @author fucz
 * @version 2014-7-8
 */
public abstract class AbstractSeparater implements ISeparater{

	@Autowired
	protected MailSeparation mailSeparation;
	@Autowired
	protected DbManager dbManager;
	@Autowired
	protected OuterTransmitter outerTransmitter;
	@Autowired
	protected ContentHandler contentHandler;
	protected String localCenterID;
	
	@PostConstruct
	public void init(){
		localCenterID = dbManager.getLocalCenter().getId();
		mailSeparation.register(getMode(), this);
	}
	
}
