package NVMP.Command.Business;

public enum NodeType {
	Org, Commander;

	public int getValue() {
		return this.ordinal();
	}

	public static NodeType forValue(int value) {
		return values()[value];
	}
}