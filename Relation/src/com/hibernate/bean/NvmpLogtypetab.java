package com.hibernate.bean;

/**
 * NvmpLogtypetab entity. @author MyEclipse Persistence Tools
 */

public class NvmpLogtypetab implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer typeId;
	private String typeName;
	private Integer typeParentId;

	// Constructors

	/** default constructor */
	public NvmpLogtypetab() {
	}

	/** minimal constructor */
	public NvmpLogtypetab(Integer typeId, String typeName) {
		this.typeId = typeId;
		this.typeName = typeName;
	}

	/** full constructor */
	public NvmpLogtypetab(Integer typeId, String typeName, Integer typeParentId) {
		this.typeId = typeId;
		this.typeName = typeName;
		this.typeParentId = typeParentId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getTypeParentId() {
		return this.typeParentId;
	}

	public void setTypeParentId(Integer typeParentId) {
		this.typeParentId = typeParentId;
	}

}