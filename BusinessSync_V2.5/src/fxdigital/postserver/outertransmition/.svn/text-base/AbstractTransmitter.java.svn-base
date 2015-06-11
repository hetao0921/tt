package fxdigital.postserver.outertransmition;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import fxdigital.db.DbManager;
import fxdigital.postserver.exchange.Exchange;

/**
 * 实现非本级MQ发送接口的抽象类
 * @author fucz
 * @version 2014-7-9
 */
public abstract class AbstractTransmitter implements ITransmitter{

	@Autowired
	protected OuterTransmitter outerTransmitter;
	@Autowired
	protected DbManager dbManager;
	@Autowired
	protected Exchange exchange;
	protected String localCenterID;
	
	@PostConstruct
	public void init(){
		localCenterID = dbManager.getLocalCenter().getId();
		outerTransmitter.register(getMode(), this);
	}
	
}
