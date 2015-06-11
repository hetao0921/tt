package NVMP.Command.Business;

public class Commander {
	// public OnCommanderWordModeChangeEvent CommanderWordModeChangeEvent;

	private String _CollectionId;

	public final String getCollectionId() {
		return _CollectionId;
	}

	public final void setCollectionId(String value) {
		_CollectionId = value;
	}

	// public CommanderTerminal CommanderDevice;

	private String _CommanderId;

	/** 
	 
	 
	*/
	public final String getCommanderId() {
		return _CommanderId;
	}

	public final void setCommanderId(String value) {
		_CommanderId = value;
	}

	private String _CommanderName;

	/** 
	 
	 
	*/
	public final String getCommanderName() {
		return _CommanderName;
	}

	public final void setCommanderName(String value) {
		_CommanderName = value;
	}

	private boolean _IsOnline;

	public final boolean getIsOnline() {
		return _IsOnline;
	}

	public final void setIsOnline(boolean value) {
		_IsOnline = value;
	}

	private String _DeviceID;

	public final String getDeviceID() {
		return _DeviceID;
	}

	public final void setDeviceID(String value) {
		_DeviceID = value;
	}

	private CommanderWorkStatus _WorkStatus = CommanderWorkStatus.forValue(0);

	public final CommanderWorkStatus getWorkStatus() {
		return _WorkStatus;
	}

	public final void setWorkStatus(CommanderWorkStatus value) {
		_WorkStatus = value;
	}

	private CommanderWorkMode _WorkMode = CommanderWorkMode.forValue(0);

	public final CommanderWorkMode getWorkMode() {
		return _WorkMode;
	}

	public final void setWorkMode(CommanderWorkMode value) {
		if (!_WorkMode.equals(value)) {
			// if (!CommanderWordModeChangeEvent.equals(null))
			// {
			// CommanderWordModeChangeEvent(getCommanderId(), value);
			// }
		}
		_WorkMode = value;

	}

	public Commander(String commanderId, String commanderName) {
		this.setCommanderId(commanderId);
		this.setCommanderName(commanderName);
	}

	public Commander(String commanderId, String commanderName,
			String aDeviceId, String CollectionId, CommanderWorkMode WorkMode,
			CommanderWorkStatus workStatus, boolean isOnline) {
		this.setCommanderId(commanderId);
		this.setCommanderName(commanderName);
		this.setIsOnline(isOnline);
		this.setDeviceID(aDeviceId);
		this.setWorkMode(WorkMode);
		this.setWorkStatus(workStatus);
		this.setCollectionId(CollectionId);

	}

	protected void finalize() throws Throwable {

	}

	public void dispose() {

	}

	public final void SetLogin(String TerminalId, boolean inOnline) {
		this.setIsOnline(inOnline);
		this.setWorkStatus(CommanderWorkStatus.Free);
		this.setDeviceID(TerminalId);
	}

	public final void InitState() {

	}

	public final void EnterCommandGroup(String CommandGroupId, boolean IsEnter) {
		if (IsEnter) {
			this.setWorkStatus(CommanderWorkStatus.Command);
			this.setCollectionId(CommandGroupId);
			DBAccess.EnterCommandGroup(CommandGroupId, this._CommanderId,
					IsEnter);
		} else {
			this.setWorkStatus(CommanderWorkStatus.Free);
			this.setCollectionId("");
		}

	}

	public final void EnterConference(String ConferenceId, boolean IsEnter) {
		if (IsEnter) {
			this.setWorkStatus(CommanderWorkStatus.Conference);
			this.setCollectionId(ConferenceId);
			DBAccess.EnterConference(this._CommanderId, ConferenceId, IsEnter);
		} else {
			this.setWorkStatus(CommanderWorkStatus.Free);
			this.setCollectionId("");
		}

	}

	public final void EnterConsultation(String ConsultationId, boolean IsEnter) {
		if (IsEnter) {
			this.setWorkStatus(CommanderWorkStatus.Consultation);
			this.setCollectionId(ConsultationId);
			DBAccess.EnterConsultation(this._CommanderId, ConsultationId,
					IsEnter);
		} else {
			this.setWorkStatus(CommanderWorkStatus.Free);
			this.setCollectionId("");
		}

	}

	public final void EnterFree() {
		this.setWorkStatus(CommanderWorkStatus.Free);
		this.setCollectionId("");
		DBAccess.EnterFree(this._CommanderId);
	}

} // end Commander