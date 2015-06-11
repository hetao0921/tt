package NVMP.Command.Business;

public class CommandCollection {
	private java.util.HashMap<String, Commander> _CurCommanderList;

	private java.util.ArrayList<Commander> _CommanderLists;

	/**
	 * 组成员集合
	 */
	public final java.util.ArrayList<Commander> getCommanderLists() {
		return _CommanderLists;
	}

	public final void setCommanderLists(java.util.ArrayList<Commander> value) {
		_CommanderLists = value;
	}

	private String _Decription;

	/**
	 * 说明
	 */
	public final String getDecription() {
		return _Decription;
	}

	public final void setDecription(String value) {
		_Decription = value;
	}

	private String _Id;

	/**
	 * ID
	 */
	public final String getId() {
		return _Id;
	}

	public final void setId(String value) {
		_Id = value;
	}

	private String _Name;

	// Name
	public final String getName() {
		return _Name;
	}

	public final void setName(String value) {
		_Name = value;
	}

	public CommandCollection() {
		setCommanderLists(new java.util.ArrayList<Commander>());
	}

	protected void finalize() throws Throwable {

	}

	public void dispose() {

	}

	/**
	 * @param aCommander
	 */
	public final void AddCommander(Commander aCommander) {
		// zhang
		if (aCommander == null) {
			return;
		}
		for (Commander com : getCommanderLists()) {
			if (com.getCommanderId().equals(aCommander.getCommanderId())) {
				getCommanderLists().remove(com);
				break;
			}
		}
		getCommanderLists().add(aCommander);

	}

	/**
	 * @param CommandId
	 */
	public final void RemoveCommander(String CommandId) {
		// zhang
		for (Commander cg : getCommanderLists()) {
			if (cg.getCommanderId().equals(CommandId)) {
				getCommanderLists().remove(cg);
			}
		}
	}

} // end CommandCollection