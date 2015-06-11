package NVMP.Command.Business;

public enum FunType {
	RequestAppointSpokeman, RequestCooperate, OperateCommandCall;

	public int getValue() {
		return this.ordinal();
	}

	public static FunType forValue(int value) {
		return values()[value];
	}
}