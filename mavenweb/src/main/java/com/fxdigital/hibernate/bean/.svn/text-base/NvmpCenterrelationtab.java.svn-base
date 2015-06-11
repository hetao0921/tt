package com.fxdigital.hibernate.bean;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * NvmpCenterrelationtab entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "nvmp_centerrelationtab", catalog = "nvmp")
public class NvmpCenterrelationtab implements java.io.Serializable {

	// Fields

	private Integer id;
	private String relationId;
	private String relationType;
	private String centerId;
	private Timestamp createTime;
	private String relationName;

	// Constructors

	/** default constructor */
	public NvmpCenterrelationtab() {
	}

	/** minimal constructor */
	public NvmpCenterrelationtab(String relationId, String relationType,
			String centerId, Timestamp createTime) {
		this.relationId = relationId;
		this.relationType = relationType;
		this.centerId = centerId;
		this.createTime = createTime;
	}

	/** full constructor */
	public NvmpCenterrelationtab(String relationId, String relationType,
			String centerId, Timestamp createTime, String relationName) {
		this.relationId = relationId;
		this.relationType = relationType;
		this.centerId = centerId;
		this.createTime = createTime;
		this.relationName = relationName;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "RelationID", nullable = false, length = 64)
	public String getRelationId() {
		return this.relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	@Column(name = "RelationType", nullable = false, length = 64)
	public String getRelationType() {
		return this.relationType;
	}

	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}

	@Column(name = "CenterID", nullable = false, length = 64)
	public String getCenterId() {
		return this.centerId;
	}

	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}

	@Column(name = "CreateTime", nullable = false, length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "RelationName", length = 64)
	public String getRelationName() {
		return this.relationName;
	}

	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}

}