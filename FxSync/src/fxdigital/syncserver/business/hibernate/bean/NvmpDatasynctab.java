package fxdigital.syncserver.business.hibernate.bean;

/**
 * NvmpDatasynctab entity. @author MyEclipse Persistence Tools
 */

public class NvmpDatasynctab implements java.io.Serializable {

	// Fields

	private Integer id;
	private String tableName;
	private Integer syncFlag;

	// Constructors

	/** default constructor */
	public NvmpDatasynctab() {
	}

	/** full constructor */
	public NvmpDatasynctab(String tableName, Integer syncFlag) {
		this.tableName = tableName;
		this.syncFlag = syncFlag;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Integer getSyncFlag() {
		return this.syncFlag;
	}

	public void setSyncFlag(Integer syncFlag) {
		this.syncFlag = syncFlag;
	}

}