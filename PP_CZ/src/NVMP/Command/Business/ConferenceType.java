package NVMP.Command.Business;

public enum ConferenceType {
	Plan, Temp;

	public int getValue() {
		return this.ordinal();
	}

	public static ConferenceType forValue(int value) {
		return values()[value];
	}
}