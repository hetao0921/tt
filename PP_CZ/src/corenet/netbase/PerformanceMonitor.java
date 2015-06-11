package corenet.netbase;

import org.misc.log.LogUtil;

import corenet.netbase.Interface.ITimer;

/**
 * 性能检测，对服务器的吞吐、处理等，进行记录 每5秒进行一次写入
 * */
public class PerformanceMonitor implements Runnable {

	static private class PerformanceMonitorHolder {
		static final PerformanceMonitor INSTANCE = new PerformanceMonitor();
	}

	private PerformanceMonitor() {
		writeBytes = 0;
		reciveBytes = 0;
		disposeNum = 0;
		NIOReactor r = (NIOReactor)NIOReactor.defaultDispatcher();
		time = r.newTimer(this);
		LogUtil.performanceMonitor("writeBytes  |" +System.nanoTime()+"|"+ writeBytes);
		LogUtil.performanceMonitor("reciveBytes |" +System.nanoTime()+"|"+  reciveBytes);
		LogUtil.performanceMonitor("disposeNum  |" +System.nanoTime()+"|"+  disposeNum);
		time.schedule(5000);
	};

	static public PerformanceMonitor getInstance() {
		return PerformanceMonitorHolder.INSTANCE;
	}

	int writeBytes; // 写字节数
	int reciveBytes;// 读字节数
	int disposeNum; // 处理数量
	ITimer time; // 计时器

	public void addWriteBytes(int nums){
		writeBytes = writeBytes + nums;
	}
	
	public void addReciveBytes(int nums) {
		reciveBytes = reciveBytes + nums;
	}
	
	public void addDisposeNum(int nums){
		disposeNum = disposeNum + nums;
	}
	
	
	@Override
	public void run() {
		time.schedule(5000);
		
		LogUtil.performanceMonitor("writeBytes |" +System.nanoTime()+"|"+ writeBytes);
		LogUtil.performanceMonitor("reciveBytes |" +System.nanoTime()+"|"+  reciveBytes);
		LogUtil.performanceMonitor("disposeNum |" +System.nanoTime()+"|"+  disposeNum);
		writeBytes = 0;
		reciveBytes = 0;
		disposeNum = 0;
		
	}

}
