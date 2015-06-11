package com.fx.bean;

/**
 * NvmpRecordStorageInfo entity. @author MyEclipse Persistence Tools
 */

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

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStorageip() {
		return this.storageip;
	}

	public void setStorageip(String storageip) {
		this.storageip = storageip;
	}

	public Integer getStorageport() {
		return this.storageport;
	}

	public void setStorageport(Integer storageport) {
		this.storageport = storageport;
	}

	public String getStoragertsp() {
		return this.storagertsp;
	}

	public void setStoragertsp(String storagertsp) {
		this.storagertsp = storagertsp;
	}

}