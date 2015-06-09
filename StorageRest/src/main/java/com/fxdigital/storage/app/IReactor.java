package com.fxdigital.storage.app;
import java.nio.channels.SelectableChannel;
import java.nio.channels.Selector;

public interface IReactor {
    ITimer newTimer(Runnable handler);
    
	void register(SelectableChannel channel, int ops, IEventHandler handler, Object userContext);
	void unregister(SelectableChannel channel, int ops);
	
	boolean isOwnered();
	void runInReactor(Runnable run);
	Selector getSelector();

}
