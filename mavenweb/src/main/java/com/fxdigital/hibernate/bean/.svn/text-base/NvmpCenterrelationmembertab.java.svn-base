package com.fxdigital.hibernate.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * NvmpCenterrelationmembertab entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "nvmp_centerrelationmembertab", catalog = "nvmp")
public class NvmpCenterrelationmembertab implements java.io.Serializable {

	// Fields

	private Integer id;
	private String relationId;
	private String relatedCenterId;
	private String relationType;
	private String centerId;
	private String relatedCenterName;

	// Constructors

	/** default constructor */
	public NvmpCenterrelationmembertab() {
	}

	/** minimal constructor */
	public NvmpCenterrelationmembertab(String relationId,
			String relatedCenterId, String relationType, String centerId) {
		this.relationId = relationId;
		this.relatedCenterId = relatedCenterId;
		this.relationType = relationType;
		this.centerId = centerId;
	}

	/** full constructor */
	public NvmpCenterrelationmembertab(String relationId,
			String relatedCenterId, String relationType, String centerId,
			String relatedCenterName) {
		this.relationId = relationId;
		this.relatedCenterId = relatedCenterId;
		this.relationType = relationType;
		this.centerId = centerId;
		this.relatedCenterName = relatedCenterName;
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

	@Column(name = "RelatedCenterID", nullable = false, length = 64)
	public String getRelatedCenterId() {
		return this.relatedCenterId;
	}

	public void setRelatedCenterId(String relatedCenterId) {
		this.relatedCenterId = relatedCenterId;
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

	@Column(name = "RelatedCenterName", length = 256)
	public String getRelatedCenterName() {
		return this.relatedCenterName;
	}

	public void setRelatedCenterName(String relatedCenterName) {
		this.relatedCenterName = relatedCenterName;
	}

}