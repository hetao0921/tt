package NVMP.Command.Business;

public class OrganizationNode {

	private String _Description;

	/**
	 * 组织结构说明
	 */
	public final String getDescription() {
		return _Description;
	}

	public final void setDescription(String value) {
		_Description = value;
	}

	private String _Id;

	/**
	 * 组织结构编号
	 */
	public final String getId() {
		return _Id;
	}

	public final void setId(String value) {
		_Id = value;
	}

	private String _Name;

	/**
	 * 组织结构名称
	 */
	public final String getName() {
		return _Name;
	}

	public final void setName(String value) {
		_Name = value;
	}

	private String _ParentId;

	/**
	 * /组织结构 父节点ID
	 */
	public final String getParentId() {
		return _ParentId;
	}

	public final void setParentId(String value) {
		_ParentId = value;
	}

	public OrganizationNode() {

	}

	protected void finalize() throws Throwable {

	}

	public void dispose() {

	}

	/**
	 * 有参构造
	 * 
	 * @param id
	 *            数据结构编号
	 * @param name
	 *            数据结构名称
	 * @param description
	 *            数据结构说明
	 * @param parentId
	 *            数据结构节点编号
	 */
	public OrganizationNode(String id, String name, String description,
			String parentId) {
		this.setId(id);
		this.setName(name);
		this.setDescription(description);
		this.setParentId(parentId);
	}

} // end OrganizationNode