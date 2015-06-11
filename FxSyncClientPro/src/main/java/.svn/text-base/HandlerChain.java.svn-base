package fxdigital.syncserver.app;

import java.util.ArrayList;
import java.util.List;

import fxdigital.mqcore.exchange.rpc.IMessage;

public class HandlerChain {
	private List<ImessageHandler> list=new ArrayList<ImessageHandler>();

	public void setMessageHandler(ImessageHandler handler){
		list.add(handler);
	}	
	public void chainProcess(String strFlag,IMessage  message){
		
		for(ImessageHandler handler:list){
			boolean flag=handler.handler(strFlag, message);
			if(flag){
				break;
			}
		}
	}
	

}
