package NVMP.DeviceManage.Implement;

class FastPingWrapper {
	public static interface PingNotify {
		void OnPingResult(String host, boolean online, Object obj);
	}

	static {
		System.loadLibrary("fastping");
		init();
	}

	public static native void init();

	public native static long create(int timeOut, PingNotify notify);

	public native static void addHost(long Handle, String host, int timeOut,
			Object obj);

	public native static void delHost(long Handle, String host);

	public native static void start(long Handle);

	public native static void stop(long Handle);

	public native static void destroy(long Handle);
}

public class FastPing {
	private long _Handle;

	private FastPing(long Handle) {
		_Handle = Handle;
	}

	public static FastPing create(int interval,
			FastPingWrapper.PingNotify notify) throws java.io.IOException {
		long Handle = FastPingWrapper.create(interval, notify);
		if (Handle == 0)
			throw new java.io.IOException("open raw socket error");
		FastPing ping = new FastPing(Handle);

		return ping;
	}

	public void close() {
		if (_Handle != 0) {
			long Handle = _Handle;
			_Handle = 0;
			FastPingWrapper.destroy(Handle);
		}
	}

	public void addHost(String host, int timeOut, Object obj) {
		if (_Handle != 0)
			FastPingWrapper.addHost(_Handle, host, timeOut, obj);
	}

	public void delHost(String host) {
		if (_Handle != 0)
			FastPingWrapper.delHost(_Handle, host);
	}

	public void start() {
		if (_Handle != 0)
			FastPingWrapper.start(_Handle);
	}

	public void stop() {
		if (_Handle != 0)
			FastPingWrapper.stop(_Handle);
	}
}
