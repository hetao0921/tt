package com.fxdigital.search.bean;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author hxht
 *
 */
public class NvmpRecordFileSearch implements java.io.Serializable {

	// Fields

	private Integer nvmpFileId;
	private String nvmpBaseUuid;
	private String nvmpFileName;
	private String nvmpChild;
	private String nvmpStartTime;
	private String nvmpEndTime;
	private Integer nvmpFileHandler;

	// Constructors

	/** default constructor */
	public NvmpRecordFileSearch() {
	}


	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "nvmp_file_id", unique = true, nullable = false)
	public Integer getNvmpFileId() {
		return this.nvmpFileId;
	}

	public void setNvmpFileId(Integer nvmpFileId) {
		this.nvmpFileId = nvmpFileId;
	}

	@Column(name = "nvmp_base_uuid", length = 64)
	public String getNvmpBaseUuid() {
		return this.nvmpBaseUuid;
	}

	public void setNvmpBaseUuid(String nvmpBaseUuid) {
		this.nvmpBaseUuid = nvmpBaseUuid;
	}

	@Column(name = "nvmp_file_name")
	public String getNvmpFileName() {
		return this.nvmpFileName;
	}

	public void setNvmpFileName(String nvmpFileName) {
		this.nvmpFileName = nvmpFileName;
	}

	@Column(name = "nvmp_child")
	public String getNvmpChild() {
		return this.nvmpChild;
	}

	public void setNvmpChild(String nvmpChild) {
		this.nvmpChild = nvmpChild;
	}


	public String getNvmpStartTime() {
		return nvmpStartTime;
	}


	public void setNvmpStartTime(String nvmpStartTime) {
		this.nvmpStartTime = nvmpStartTime;
	}


	public String getNvmpEndTime() {
		return nvmpEndTime;
	}


	public void setNvmpEndTime(String nvmpEndTime) {
		this.nvmpEndTime = nvmpEndTime;
	}


	public Integer getNvmpFileHandler() {
		return nvmpFileHandler;
	}


	public void setNvmpFileHandler(Integer nvmpFileHandler) {
		this.nvmpFileHandler = nvmpFileHandler;
	}

	
}