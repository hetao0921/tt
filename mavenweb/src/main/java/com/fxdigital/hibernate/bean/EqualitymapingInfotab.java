package com.fxdigital.hibernate.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * EqualitymapingInfotab entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "equalitymaping_infotab", catalog = "nvmp")
public class EqualitymapingInfotab implements java.io.Serializable {

	// Fields

	private Integer id;
	private EqualityInfotab equalityInfotab;
	private String mapCenterId;

	// Constructors

	/** default constructor */
	public EqualitymapingInfotab() {
	}

	/** full constructor */
	public EqualitymapingInfotab(EqualityInfotab equalityInfotab,
			String mapCenterId) {
		this.equalityInfotab = equalityInfotab;
		this.mapCenterId = mapCenterId;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "setId", nullable = false)
	public EqualityInfotab getEqualityInfotab() {
		return this.equalityInfotab;
	}

	public void setEqualityInfotab(EqualityInfotab equalityInfotab) {
		this.equalityInfotab = equalityInfotab;
	}

	@Column(name = "mapCenterId", nullable = false)
	public String getMapCenterId() {
		return this.mapCenterId;
	}

	public void setMapCenterId(String mapCenterId) {
		this.mapCenterId = mapCenterId;
	}

}