package NVMP.VideoControlDomain;

/**
 * 原始点播源头
 * */
public class PreSource {

	private String deviceid;
	private int channel;
	private int lev;// 临时用一下

	public String getDeviceid() {
		return deviceid;
	} 

	public int getChannel() {
		return channel;
	}

	public int getLev() {
		return lev;
	}

	public void setLev(int lev) {
		this.lev = lev;
	}

	public PreSource(String deviceid, int channel) {
		this.deviceid = deviceid;
		this.channel = channel;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PreSource) {
			PreSource nobj = (PreSource) obj;
			return nobj.deviceid.equals(this.deviceid)
					&& (nobj.channel == this.channel);
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return deviceid.hashCode() + channel;
	}

	static public void main(String... args) {
		// HashSet a = new HashSet();
		// a.add(new PreSource("123", 1));
		// a.add(new PreSource("123", 1));
		// a.add(new PreSource("123", 2));
		// System.out.println(a.size());

	}

}
