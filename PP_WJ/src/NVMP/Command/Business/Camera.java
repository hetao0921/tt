package NVMP.Command.Business;

public class Camera {

	private String _CameraName;

	/**
	 * 相机名称
	 */
	public final String getCameraName() {
		return _CameraName;
	}

	public final void setCameraName(String value) {
		_CameraName = value;
	}

	private int _Index;

	/**
	 * 序号
	 */
	public final int getIndex() {
		return _Index;
	}

	public final void setIndex(int value) {
		_Index = value;
	}

	private String _DeviceId;

	/**
	 * 设备编号
	 */
	public final String getDeviceId() {
		return _DeviceId;
	}

	public final void setDeviceId(String value) {
		_DeviceId = value;
	}

	private boolean _IsOnline;

	/**
	 * 是否在线
	 */
	public final boolean getIsOnline() {
		return _IsOnline;
	}

	public final void setIsOnline(boolean value) {
		_IsOnline = value;
	}

	private String _ParentId;

	/**
	 * 父节点ID
	 */
	public final String getParentId() {
		return _ParentId;
	}

	public final void setParentId(String value) {
		_ParentId = value;
	}

	/**
	 * 摄像机含参数构造函数
	 * 
	 * @param cameraName
	 *            相机名
	 * @param index
	 *            相机序号
	 * @param deviceId
	 *            设备编号
	 * @param isOnline
	 *            是否在线
	 * @param parentId
	 *            隶属ID
	 */
	public Camera(String cameraName, int index, String deviceId,
			boolean isOnline, String parentId) {
		this.setCameraName(cameraName);
		this.setIndex(index);
		this.setDeviceId(deviceId);
		this.setIsOnline(isOnline);
		this.setParentId(parentId);
	}

	protected void finalize() throws Throwable {

	}

	public void dispose() {

	}

} // end Camera