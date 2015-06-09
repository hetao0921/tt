package fxdigital.mqcore.base;

class MqConnectionInfo {

	public String ip;
	public int port;
	public boolean synFlag;

	public MqConnectionInfo(String ip, int port, boolean synFlag) {
		this.ip = ip;
		this.port = port;
		this.synFlag = synFlag;
	}

	@Override
	public boolean equals(Object obj) {
		boolean b = false;
		try {
			MqConnectionInfo info = (MqConnectionInfo) obj;
			if (ip.equals(info.ip) && port == info.port
					&& synFlag == info.synFlag)
				b = true;
		} catch (Exception e) {
			b = false;
		}
		return b;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return ip.hashCode() + port + (synFlag ? 1 : 0);
	}

}