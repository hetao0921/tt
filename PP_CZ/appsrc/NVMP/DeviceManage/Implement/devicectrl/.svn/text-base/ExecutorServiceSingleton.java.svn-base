package NVMP.DeviceManage.Implement.devicectrl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceSingleton {
	private ExecutorService fixedThreadPool = null;
	private static ExecutorServiceSingleton ess = null;
	
	private ExecutorServiceSingleton() {
		if (fixedThreadPool == null) {
			fixedThreadPool = Executors.newFixedThreadPool(10);
		}
	}
	
	public static ExecutorServiceSingleton getInstace() {
		if (ess == null)
			ess = new ExecutorServiceSingleton();
		
		return ess;
	}
	
	public void sendGetRequest(String deviceSource) {
		for (int i = 0; i < 10; ++i) {
			final String deviceSourceTmp = deviceSource;
			fixedThreadPool.execute(new Runnable() {
				public void run() {
					try {
						new HttpRequest(deviceSourceTmp).sendGetRequest();
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
}
