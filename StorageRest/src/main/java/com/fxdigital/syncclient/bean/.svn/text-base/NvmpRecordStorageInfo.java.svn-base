package com.fxdigital.syncclient.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * NvmpRecordStorageInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "nvmp_record_storage_info", catalog = "")
public class NvmpRecordStorageInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String storageip;
	private Integer storageport;
	private String storagertsp;

	// Constructors

	/** default constructor */
	public NvmpRecordStorageInfo() {
	}

	/** full constructor */
	public NvmpRecordStorageInfo(String storageip, Integer storageport,
			String storagertsp) {
		this.storageip = storageip;
		this.storageport = storageport;
		this.storagertsp = storagertsp;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "storageip")
	public String getStorageip() {
		return this.storageip;
	}

	public void setStorageip(String storageip) {
		this.storageip = storageip;
	}

	@Column(name = "storageport")
	public Integer getStorageport() {
		return this.storageport;
	}

	public void setStorageport(Integer storageport) {
		this.storageport = storageport;
	}

	@Column(name = "storagertsp")
	public String getStoragertsp() {
		return this.storagertsp;
	}

	public void setStoragertsp(String storagertsp) {
		this.storagertsp = storagertsp;
	}

}