package NVMP.Command.Business;

public class CameraGroup {

	private java.util.ArrayList<Camera> _CameraList;

	/**
	 * 相机集合
	 */
	public final java.util.ArrayList<Camera> getCameraList() {
		return _CameraList;
	}

	public final void setCameraList(java.util.ArrayList<Camera> value) {
		_CameraList = value;
	}

	private java.util.ArrayList<OrganizationNode> _OrganizationNodeList;

	/**
	 * 数据结构集合
	 */
	public final java.util.ArrayList<OrganizationNode> getOrganizationNodeList() {
		return _OrganizationNodeList;
	}

	public final void setOrganizationNodeList(
			java.util.ArrayList<OrganizationNode> value) {
		_OrganizationNodeList = value;
	}

	private String _Id;

	/**
	 * 分组ID
	 */
	public final String getId() {
		return _Id;
	}

	public final void setId(String value) {
		_Id = value;
	}

	private String _Name;

	/**
	 * 分组名称
	 */
	public final String getName() {
		return _Name;
	}

	public final void setName(String value) {
		_Name = value;
	}

	private String _Description;

	/**
	 * 分组说明
	 */
	public final String getDescription() {
		return _Description;
	}

	public final void setDescription(String value) {
		_Description = value;
	}

	/**
	 * 方案构造函数
	 * 
	 * @param id
	 *            分组ID
	 * @param name
	 *            分组名称
	 * @param description
	 *            分组说明
	 */
	public CameraGroup(String id, String name, String description) {
		this.setId(id);
		this.setName(name);
		this.setDescription(description);
		setCameraList(new java.util.ArrayList<Camera>());
		setOrganizationNodeList(new java.util.ArrayList<OrganizationNode>());

	}

	public CameraGroup() {
		setCameraList(new java.util.ArrayList<Camera>());
		setOrganizationNodeList(new java.util.ArrayList<OrganizationNode>());
	}

	protected void finalize() throws Throwable {

	}

	public void dispose() {

	}

	public final void AddCamera(Camera aCameraGroup) {
		for (Camera cg : getCameraList()) {
			if ((new Integer(cg.getIndex())).equals(aCameraGroup.getIndex())
					&& cg.getDeviceId().equals(aCameraGroup.getDeviceId())) {
				getCameraList().remove(cg);
			}
		}
		getCameraList().add(aCameraGroup);
	}

	/**
	 * @param aOrganizatinNode
	 */
	public final void AddOrganizatinNode(OrganizationNode aOrganizatinNode) {
		for (OrganizationNode cg : getOrganizationNodeList()) {
			if (cg.getId().equals(aOrganizatinNode.getId())) {

				getOrganizationNodeList().remove(cg);
			}
		}
		getOrganizationNodeList().add(aOrganizatinNode);
	}

	/**
	 * @param Channel
	 * @param DeviceId
	 */
	public final void RemoveCamera(int Index, String DeviceId) {
		for (Camera cg : getCameraList()) {
			if ((new Integer(cg.getIndex())).equals(Index)
					&& cg.getDeviceId().equals(DeviceId)) {
				getCameraList().remove(cg);
			}
		}
	}

	/**
	 * @param OrganizatinNodeId
	 */
	public final void RemoveOrganizatinNode(String OrganizatinNodeId) {
		for (OrganizationNode cg : getOrganizationNodeList()) {
			if (cg.getId().equals(OrganizatinNodeId)) {
				getOrganizationNodeList().remove(cg);
			}
		}
	}

} // end CameraGroup