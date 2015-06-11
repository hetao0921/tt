package NVMP.Command.Business;

import org.misc.RefObject;

public class SystemData {
	private java.util.ArrayList<CameraGroup> _CameraGroupList;

	public final java.util.ArrayList<CameraGroup> getCameraGroupList() {
		return _CameraGroupList;
	}

	public final void setCameraGroupList(java.util.ArrayList<CameraGroup> value) {
		_CameraGroupList = value;
	}

	private java.util.ArrayList<Commander> _CommanderList;

	public final java.util.ArrayList<Commander> getCommanderList() {
		return _CommanderList;
	}

	public final void setCommanderList(java.util.ArrayList<Commander> value) {
		_CommanderList = value;
	}

	private java.util.ArrayList<CommandGroup> _CommandGroupList;

	public final java.util.ArrayList<CommandGroup> getCommandGroupList() {
		return _CommandGroupList;
	}

	public final void setCommandGroupList(
			java.util.ArrayList<CommandGroup> value) {
		_CommandGroupList = value;
	}

	private java.util.ArrayList<ConferenceGroup> _ConferenceGroupList;

	/**
	 * 视频会议
	 */
	public final java.util.ArrayList<ConferenceGroup> getConferenceGroupList() {
		return _ConferenceGroupList;
	}

	public final void setConferenceGroupList(
			java.util.ArrayList<ConferenceGroup> value) {
		_ConferenceGroupList = value;
	}

	private java.util.ArrayList<ConsultationGroup> _ConsultationGroupList;

	/**
	 * 远程会商结合
	 */
	public final java.util.ArrayList<ConsultationGroup> getConsultationGroupList() {
		return _ConsultationGroupList;
	}

	public final void setConsultationGroupList(
			java.util.ArrayList<ConsultationGroup> value) {
		_ConsultationGroupList = value;
	}

	public SystemData() {
		_CommandGroupList = new java.util.ArrayList<CommandGroup>();
		_CameraGroupList = new java.util.ArrayList<CameraGroup>();
		setConferenceGroupList(new java.util.ArrayList<ConferenceGroup>());
		setConsultationGroupList(new java.util.ArrayList<ConsultationGroup>());
		setCommanderList(new java.util.ArrayList<Commander>());
	}

	protected void finalize() throws Throwable {

	}

	public void dispose() {

	}

	/**
	 * @param aCommander
	 */
	public final void AddCommander(Commander aCommander) {
		for (Commander item : getCommanderList()) {
			if (item.getCommanderId().equals(aCommander.getCommanderId())) {
				getCommanderList().remove(item);
				break;
			}
		}
		getCommanderList().add(aCommander);
	}

	/**
	 * @param aGroup
	 */
	public final void AddCommandGroup(CommandGroup aGroup) {
		/**
		 * zhang
		 */
		for (CommandGroup cmd : getCommandGroupList()) {
			if (cmd.getId().equals(aGroup.getId())) {
				cmd.setId(aGroup.getId());
				cmd.setName(aGroup.getName());
				cmd.setDecription(aGroup.getDecription());
				getCommandGroupList().add(cmd);
				return;
			}
		}
		getCommandGroupList().add(aGroup);

	}

	/**
	 * 添加视频会议列表
	 * 
	 * @param aConferenceGroup
	 */
	public final void AddConferenceGroup(ConferenceGroup aConferenceGroup) {
		for (ConferenceGroup con : getConferenceGroupList()) {
			if (con.getId().equals(aConferenceGroup.getId())) {
				getConferenceGroupList().remove(con);
			}
		}
		getConferenceGroupList().add(aConferenceGroup);

	}

	/**
	 * 添加远程会商列表
	 * 
	 * @param aConsultation
	 */
	public final void AddConsultationGroup(ConsultationGroup aConsultation) {
		for (ConsultationGroup con : getConsultationGroupList()) {
			if (con.getId().equals(aConsultation.getId())) {
				getConsultationGroupList().remove(con);
				break;
			}
		}
		getConsultationGroupList().add(aConsultation);
	}

	/**
	 * 获取小组成员
	 * 
	 * @param CommandId
	 * @return
	 */
	public final boolean GetCommanderById(String CommanderId,
			RefObject<Commander> aCommander) {
		aCommander.argvalue = null;
		for (Commander com : getCommanderList()) {
			if (com.getCommanderId().equals(CommanderId)) {
				aCommander.argvalue = com;
				return true;
			}
		}
		return false;
	}

	public final boolean GetCommanderByDeviceId(String DeviceId,
			RefObject<Commander> aCommander) {
		aCommander.argvalue = null;
		for (Commander com : getCommanderList()) {
			if (com.getDeviceID().equals(DeviceId)) {
				aCommander.argvalue = com;
				return true;
			}
		}
		return false;
	}

	public final String GetCommanderByDevice(String deviceId) {
		String str = "";
		for (Commander com : getCommanderList()) {
			if (com.getDeviceID().equals(deviceId)) {
				str = com.getCommanderId();
				break;
			}
		}
		return str;
	}

	/**
	 * @param CommandGroupId
	 */
	public final boolean GetCommandGroup(String CommandGroupId,
			RefObject<CommandGroup> cmdGroup) {

		cmdGroup.argvalue = null;
		for (CommandGroup cmd : getCommandGroupList()) {
			if (cmd.getId().equals(CommandGroupId)) {
				cmdGroup.argvalue = cmd;
				return true;
			}
		}
		return false;
	}

	/**
	 * @param cmdGroup
	 * @return //public bool GetCommandGroup(out CommandGroup cmdGroup) //{
	 */

	// cmdGroup = null;
	// foreach (CommandGroup cmd in CommandGroupList)
	// {
	// if (!cmd.Equals(null))
	// {
	// cmdGroup = cmd;
	// return true;
	// }
	// }
	// return false;
	// }

	/**
	 * 获取视频会议成员组对象
	 * 
	 * @param ConferenceGroupId
	 *            成员组编号
	 * @return
	 */
	public final boolean GetConferenceGroup(String ConferenceGroupId,
			RefObject<ConferenceGroup> Conference) {
		Conference.argvalue = null;
		for (ConferenceGroup con : getConferenceGroupList()) {
			if (con.getId().equals(ConferenceGroupId)) {
				Conference.argvalue = con;
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取远程会商成员组对象
	 * 
	 * @param ConsultationGroupId
	 *            成员组编号
	 * @return
	 */
	public final boolean GetConsultationGroup(String ConsultationGroupId,
			RefObject<ConsultationGroup> longDis) {

		longDis.argvalue = null;
		for (ConsultationGroup con : getConsultationGroupList()) {
			if (con.getId().equals(ConsultationGroupId)) {
				longDis.argvalue = con;
				return true;
			}
		}
		return false;
	}

	/**
	 * 请出对应编号的成员
	 * 
	 * @param CommanderId
	 */
	public final void RemoveCommander(String CommanderId) {
		for (Commander cmd : getCommanderList()) {
			if (cmd.getCommanderId().equals(CommanderId)) {
				getCommanderList().remove(cmd);
			}
		}
	}

	/**
	 * @param CommandGroupId
	 */
	public final void RemoveCommandGroup(String CommandGroupId) {
		/**
		 * zhang
		 */
		for (CommandGroup cmd : getCommandGroupList()) {
			if (cmd.getId().equals(CommandGroupId)) {
				getCommandGroupList().remove(cmd);
			}
		}

	}

	/**
	 * 撤销视频会议组
	 * 
	 * @param ConferenceId
	 *            视频会议组ID
	 */
	public final void RemoveConferenceGroup(String ConferenceId) {
		for (ConferenceGroup videoCount : getConferenceGroupList()) {
			if (videoCount.getId().equals(ConferenceId)) {
				getConferenceGroupList().remove(videoCount);
			}
		}
	}

	/**
	 * 撤销远程会商组
	 * 
	 * @param ConsultationGroupId
	 *            会商组ID
	 */
	public final void RemoveConsultationGroup(String ConsultationGroupId) {
		for (ConsultationGroup longDis : getConsultationGroupList()) {
			if (longDis.getId().equals(ConsultationGroupId)) {
				getConsultationGroupList().remove(longDis);
			}
		}
	}

	/**
	 * @param aCameraGroup
	 */
	public final void AddCameraGroup(CameraGroup aCameraGroup) { // zh

		// CameraGroup cgp = new CameraGroup();
		for (CameraGroup cg : getCameraGroupList()) {
			if (cg.getId().equals(aCameraGroup.getId())) {
				getCameraGroupList().remove(cg);
				break;
			}
		}
		// getCameraGroupList().remove(cgp);
		getCameraGroupList().add(aCameraGroup);
	}

	/**
	 * @param CameraGroupId
	 */
	public final void RemoveCameraGroup(String aCameraGroupId) {
		for (CameraGroup cg : getCameraGroupList()) {
			if (cg.getId().equals(aCameraGroupId)) {
				getCameraGroupList().remove(cg);
			}
		}
	}

	// public bool GetCommandGroupById(string CommandGroupId,out CommandGroup
	// aCommandGroup)
	// {
	// aCommandGroup = null;
	// return true;
	// }

	// public bool GetConferenceGroupById(string ConferenceGroupId, out
	// ConferenceGroup aConferenceGroup)
	// {
	// aConferenceGroup = null;
	// return true;
	// }

	// public bool GetConsultationGroupById(string ConsultationGrouppId, out
	// ConsultationGroup aConsultationGroup)
	// {
	// aConsultationGroup = null;
	// return true;
	// }

} // end SystemData