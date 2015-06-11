package NVMP.Command.Business;

import NVMP.AppService.Remoting;

/**
 * 会议
 */
public class ConferenceGroup extends CommandTeam {
	private Commander _Chairman;

	public final Commander getChairman() {
		return _Chairman;
	}

	public final void setChairman(Commander value) {
		_Chairman = value;
	}

	private String _Spokeman;

	public final String getSpokeman() {
		return _Spokeman;
	}

	public final void setSpokeman(String value) {
		_Spokeman = value;
	}

	private boolean _IsDiscuss;

	public final boolean getIsDiscuss() {
		return _IsDiscuss;
	}

	public final void setIsDiscuss(boolean value) {
		_IsDiscuss = value;
	}

	private ConferenceType _CurConferenceType = ConferenceType.forValue(0);

	public final ConferenceType getCurConferenceType() {
		return _CurConferenceType;
	}

	public final void setCurConferenceType(ConferenceType value) {
		_CurConferenceType = value;
	}

	private ConferenceStatus _CurConferenceStatus = ConferenceStatus
			.forValue(0);

	public final ConferenceStatus getCurConferenceStatus() {
		return _CurConferenceStatus;
	}

	public final void setCurConferenceStatus(ConferenceStatus value) {
		_CurConferenceStatus = value;
	}

	public ConferenceGroup(String id, String name, String decription,
			Commander aChainman, String aSpokemanId, boolean bDiscuss,
			ConferenceType aConferenceType, ConferenceStatus aConferenceStatus) {
		this.setId(id);
		this.setName(name);
		this.setDecription(decription);
		this.setSpokeman(aSpokemanId);
		this.setChairman(aChainman);
		this.setIsDiscuss(bDiscuss);
		this.setCurConferenceStatus(aConferenceStatus);
		this.setCurConferenceType(aConferenceType);
		setCommanderLists(new java.util.ArrayList<Commander>());
	}

	// public ConferenceGroup(string id, string name, string decription,
	// ConferenceType aConferenceType)
	// {

	// this.Id = id;
	// this.Name = name;
	// this.Decription = decription;
	// this.Spokeman = "";
	// // this.Chairman = aChainman;
	// this.IsDiscuss = false;
	// this.CurConferenceType = aConferenceType;
	// this.CurConferenceStatus = ConferenceStatus.Helding;
	// CommanderLists = new List<Commander>();
	// }

	public ConferenceGroup() {

	}

	protected void finalize() throws Throwable {

	}

	@Override
	public void dispose() {

	}

	/**
	 * @param commenderId
	 * @param ConferenceId
	 */
	@Remoting
	public final void DisposeEnerConference(String commenderId,
			String ConferenceId) {

	}

} // end ConferenceGroup