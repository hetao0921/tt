package Runtime.impl;

import corenet.exchange.ExchangeClient;

public class RunTimerTask extends Thread {

	private ExchangeClient ec;

	private long starttime;

	private long looptime;

	private boolean active;

	public RunTimerTask(ExchangeClient ec) {
		this.ec = ec;
		active = true;
	}

	// 停止该任务。
	public void stopTimerTask() { 
		active = false;
	}

	public void start(long starttime, long looptime) {
		// 改为多久执行一次
		this.starttime = starttime;
		this.looptime = looptime;
		this.start();

	}

	@Override
	public void run() {


	}

}
