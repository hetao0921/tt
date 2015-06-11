package NVMP.Command.Business;

public enum NetLinkType {
	SelfTcp, SelfUdp, HKTcp, JKUdp;
	public int getValue() {
		return this.ordinal();
	}

	public static NetLinkType forValue(int value) {
		return values()[value];
	}
}