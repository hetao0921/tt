package com.fxdigital.hibernate.bean;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * EqualityInfotab entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "equality_infotab", catalog = "nvmp")
public class EqualityInfotab implements java.io.Serializable {

	// Fields

	private Integer id;
	private String setId;
	private String setCenterId;
	private String type;
	private Set<EqualitymapingInfotab> equalitymapingInfotabs = new HashSet<EqualitymapingInfotab>(
			0);

	// Constructors

	/** default constructor */
	public EqualityInfotab() {
	}

	/** minimal constructor */
	public EqualityInfotab(String setId, String setCenterId, String type) {
		this.setId = setId;
		this.setCenterId = setCenterId;
		this.type = type;
	}

	/** full constructor */
	public EqualityInfotab(String setId, String setCenterId, String type,
			Set<EqualitymapingInfotab> equalitymapingInfotabs) {
		this.setId = setId;
		this.setCenterId = setCenterId;
		this.type = type;
		this.equalitymapingInfotabs = equalitymapingInfotabs;
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

	@Column(name = "setId", nullable = false)
	public String getSetId() {
		return this.setId;
	}

	public void setSetId(String setId) {
		this.setId = setId;
	}

	@Column(name = "setCenterId", nullable = false)
	public String getSetCenterId() {
		return this.setCenterId;
	}

	public void setSetCenterId(String setCenterId) {
		this.setCenterId = setCenterId;
	}

	@Column(name = "type", nullable = false, length = 1)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "equalityInfotab")
	public Set<EqualitymapingInfotab> getEqualitymapingInfotabs() {
		return this.equalitymapingInfotabs;
	}

	public void setEqualitymapingInfotabs(
			Set<EqualitymapingInfotab> equalitymapingInfotabs) {
		this.equalitymapingInfotabs = equalitymapingInfotabs;
	}

}