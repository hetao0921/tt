package corenet.netbase;
import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;
import   java.text.SimpleDateFormat; 
import java.util.concurrent.ConcurrentLinkedQueue;
import org.misc.log.LogUtil;

import corenet.netbase.Interface.*;

class Timer implements ITimer, Comparable, Comparator {
	private long expireTime;
	private long timeout;
	private NIOReactor reactor;
	private Runnable handler;
	private boolean enable;
	
	
	public long getExpireTime() {
		return expireTime;
	}
	
	@Override
	public String toString() {
		return "timer#"+timeout+"#"+expireTime;
	}
	
	@Override
	public void schedule(long millionSecond) {
		timeout = millionSecond;
		expireTime = System.nanoTime() + millionSecond * 1000L * 1000L;
		this.enable = true;
		if (reactor.isOwnered()) {
			reactor.addTimer(this);
		} else {
			class ScheduleAction implements Runnable {
				private NIOReactor reactor;
				private Timer timer;
				public ScheduleAction(NIOReactor _reactor, Timer _timer) {
					this.reactor = _reactor;
					this.timer    = _timer;
				}
				public void run() {
					reactor.addTimer(timer);
				}
			}
			
			reactor.runInReactor(new ScheduleAction(reactor, this));
		}
	}
	
	@Override
	public void cancel() {
		if (reactor.isOwnered()) {
			this.enable = false;
			reactor.removeTimer(this);
		} else {
			class CancelAction implements Runnable {
				private NIOReactor reactor;
				private Timer timer;
				public CancelAction(NIOReactor _reactor, Timer _timer) {
					this.reactor = _reactor;
					this.timer   = _timer;
				}
				public void run() {
					timer.enable = false;
					reactor.removeTimer(timer);
				}
			}
			
			reactor.runInReactor(new CancelAction(reactor, this));
		}
	}
	
	@Override
	public int compareTo(Object object) {
		return compare(this, object);
	}
	
	@Override
	public int compare(Object o1,Object o2)
    {
		Timer timer1 =(Timer)o1;
		Timer timer2 =(Timer)o2;
        return (int) (timer1.expireTime - timer2.expireTime);
    }

    Timer(NIOReactor _reactor, Runnable _handler) {
    	this.expireTime = 0;
    	this.timeout      = 0;
		this.reactor       = _reactor;
		this.handler      = _handler;
		this.enable       = false;
	}
	
	void fire() {
		if (this.enable) {
			this.enable = false;
			try {
				if (handler != null)
					handler.run();
			} catch(Exception e) {
				System.out.println("timer fire exception");
				e.printStackTrace();
			}
			
		}
	}
}

class NIOAttachment {
	public NIOAttachment( IEventHandler handler, Object userContext) {
		this.handler        = handler;
		this.userContext = userContext;
	}
	
	public IEventHandler getEventHandler() {
		return handler;
	}
	
	 public Object getUserContext(){
		 return userContext;
	 }
	 
	public void setEventHandler(IEventHandler handler) {
		this.handler = handler;
	}
		
	public void setUserContext(Object userContext){
		this.userContext = userContext;
	}
	
	private IEventHandler handler;
	private Object userContext;
}

public class NIOReactor implements IReactor, Runnable {
	private Thread                                                    ioThread;
	private TreeSet<Timer>                                     timerQueue;
	private LinkedList<Timer>                                expiredTimers;
	private ConcurrentLinkedQueue<Runnable> deferrQueue;
	
	private Selector selector;
	
	private static IReactor _defaultReactor      = new NIOReactor();
	private static IReactor _defaultDispatcher = new NIOReactor();

	void select() throws IOException {
		if (timerQueue.size() > 0) {
			Timer timer = (Timer)timerQueue.first();
			long currentTime = System.nanoTime();
			long timeout = (timer.getExpireTime() - currentTime) / (1000L * 1000L);
			
			if (timeout < 15)
				timeout = 15;

			selector.select(timeout);
		} else {
			selector.select();
		}
	}
	

	@Override
	public Selector getSelector() {
		return selector;
	}
	
	
	@Override
	public void run() {
		LogUtil.info("run event loop");
		while(true) {
			try{
				select();

				// 检查是否有超时的定时器
				checkTimer();

				// 检查是否有触发的IO事件
				checkIo();
				
				// 检查是否有延迟的任务
				checkDeferr();
				
				// 检查是否有超时的定时器
				checkTimer();
	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	void addTimer(Timer timer) {
		timerQueue.add(timer);
	}
	
	void removeTimer(Timer timer) {
		timerQueue.remove(timer);
	}
	
	private void checkDeferr() {
		while(deferrQueue.size() > 0) {
			Runnable runnable = deferrQueue.poll();
			try{
				runnable.run();
			} catch(Exception e) {
				System.out.println("运行延迟任务异常:");
				e.printStackTrace();
			}
		}
	}
	
	private void checkTimer() {
		if (timerQueue.isEmpty())
			return;
		
		long currentTime = System.nanoTime();
		
		try {
			Iterator<Timer> iter = timerQueue.iterator();
			while(iter.hasNext()) {
				Timer timer = iter.next();
				if (timer.getExpireTime() > currentTime)
					break;
				
				expiredTimers.add(timer);
				iter.remove();
			}
			
			iter = expiredTimers.iterator();
			while(iter.hasNext()) {
				Timer timer = iter.next();
				timer.fire();
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			expiredTimers.clear();
		}
	}
	
	private void checkIo() {
	    Set<SelectionKey> selectred = selector.selectedKeys();
	    Iterator<SelectionKey> iter = selectred.iterator();
	    while(iter.hasNext()) {
	    	SelectionKey key = iter.next();
	    	iter.remove();
	    	
	    	NIOAttachment attachment = (NIOAttachment)key.attachment();
	    	try{
	    		attachment.getEventHandler().handleIO(key, attachment.getUserContext());
	    	} catch(Exception e) {
	    		LogUtil.except(e);
	    	}
	     	checkTimer();
	    }
	}
	
	public NIOReactor() {
		timerQueue    = new TreeSet<Timer>();
		expiredTimers = new LinkedList<Timer>();
		deferrQueue   = new ConcurrentLinkedQueue<Runnable>();
		try{
			selector  = Selector.open();
		} catch (IOException e) {
			e.printStackTrace();
		}

		ioThread        = new Thread(this);
		ioThread.start();
	}
	
	@Override
	public ITimer newTimer(Runnable handler) {
		return new Timer(this, handler);
	}
	
	private void unregisterImpl(SelectableChannel channel, int ops) {
		SelectionKey selectionKey    = null;
		NIOAttachment attachment = null;
		try {
			selectionKey = channel.keyFor(selector);
			
			if(selectionKey != null) {
				selectionKey.interestOps(selectionKey.interestOps() & ~ops);
				attachment = (NIOAttachment)selectionKey.attachment();
			}
		} catch(Exception e) {
			e.printStackTrace();
			if (attachment != null && attachment.getEventHandler() != null)
				attachment.getEventHandler().handleError(e, attachment.getUserContext());
		}
	}
	
	public void unregister(SelectableChannel channel, int ops) {
		if (!channel.isOpen()) {
			return;
		}
		if (isOwnered()) {
			unregisterImpl(channel, ops);
		} else {
				class UnregisterAction implements Runnable {
					int ops;
					SelectableChannel channel;
					public UnregisterAction(SelectableChannel _channel, int _ops) {
						this.channel       = _channel;
						this.ops           = _ops;
					}
					@Override
					public void run() {
						unregisterImpl(channel, ops);
					}
				}
				this.runInReactor(new UnregisterAction(channel, ops));
		}
	}

	private void registerImpl(SelectableChannel channel, int ops, IEventHandler handler, Object userContext) {
		try {
			SelectionKey selectionKey = channel.keyFor(selector);
			
			if(selectionKey != null) {
				int oldOps = selectionKey.interestOps();
				selectionKey.interestOps(selectionKey.interestOps() | ops);
				NIOAttachment attachement = (NIOAttachment)selectionKey.attachment();
				attachement.setEventHandler(handler);
				attachement.setUserContext(userContext);
			} else {
				selectionKey =  channel.register(selector, ops, new NIOAttachment(handler, userContext));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			handler.handleError(e, userContext);
		}
	}
	
	@Override
	public void register(SelectableChannel channel, int ops, IEventHandler handler, Object userContext) {
		if (!channel.isOpen()) {
			return;
		}
		if (isOwnered()) {
			 registerImpl(channel, ops, handler, userContext);
		} else {
				class RegisterAction implements Runnable {
					int ops;
					IEventHandler handler;
					SelectableChannel channel;
					Object userContext;
					
					public RegisterAction(SelectableChannel _channel, 
							int _ops, IEventHandler _handler, Object _userContext) {
						this.channel       = _channel;
						this.ops               = _ops;
						this.handler        = _handler;
						this.userContext = _userContext;
					}

					@Override
					public void run() {
						 registerImpl(channel, ops, handler, userContext);
					}
				}
				this.runInReactor(new RegisterAction(channel, ops, handler, userContext));
		}
	}

	@Override
	public boolean isOwnered() {
		return ioThread.getId() == Thread.currentThread().getId();
	}
	
	public void runInReactor(Runnable run) {
		deferrQueue.add(run);
		selector.wakeup();
	}
	
	public static IReactor defaultReactor() {
		return _defaultReactor;
	}
	
	public static IReactor defaultDispatcher() {
		return _defaultDispatcher;
	}
	
	static ITimer t1, t2, t3, t4;

	public static void main(String[] args) throws  Exception {

		class TimerCallback implements Runnable {
			String msg;
			public TimerCallback(String msg) {
				this.msg = msg;
			}
			
			public void run() {
				try{
					t1.schedule(1000);

					SimpleDateFormat   formatter   =   new   SimpleDateFormat   ("yyyy年MM月dd日   HH:mm:ss SSS");     
					 Date   date   =   new   Date(System.currentTimeMillis());
					 String  str   =   formatter.format(date); 
					System.out.println(msg + " " + str);

				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		NIOReactor r = (NIOReactor)NIOReactor.defaultReactor();
		
		t1 = r.newTimer(new TimerCallback("timer1"));
		
		t2 = r.newTimer(new TimerCallback("timer2"));
		t3 = r.newTimer(new TimerCallback("timer3"));
		t4 = r.newTimer(new TimerCallback("timer4"));
		
		t1.schedule(1000);
		t2.schedule(2000);
		t3.schedule(3000);
		t4.schedule(4000);
		
		t1.schedule(1000);
		t2.schedule(2000);
		t3.schedule(3000);
		t4.schedule(4000);
		
	}


}
