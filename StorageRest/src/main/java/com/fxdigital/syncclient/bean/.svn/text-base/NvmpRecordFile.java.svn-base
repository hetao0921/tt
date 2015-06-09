package com.fxdigital.syncclient.bean;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * NvmpRecordFile entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "nvmp_record_file", catalog = "")
public class NvmpRecordFile implements java.io.Serializable {

	// Fields

	private Integer nvmpFileId;
	private String nvmpBaseUuid;
	private String nvmpFileName;
	private String nvmpChild;
	private Timestamp nvmpStartTime;
	private Timestamp nvmpEndTime;
	private Integer nvmpFileHandler;
	private Boolean nvmpFileHint;

	// Constructors

	/** default constructor */
	public NvmpRecordFile() {
	}

	/** full constructor */
	public NvmpRecordFile(String nvmpBaseUuid, String nvmpFileName,
			String nvmpChild, Timestamp nvmpStartTime, Timestamp nvmpEndTime,
			Integer nvmpFileHandler, Boolean nvmpFileHint) {
		this.nvmpBaseUuid = nvmpBaseUuid;
		this.nvmpFileName = nvmpFileName;
		this.nvmpChild = nvmpChild;
		this.nvmpStartTime = nvmpStartTime;
		this.nvmpEndTime = nvmpEndTime;
		this.nvmpFileHandler = nvmpFileHandler;
		this.nvmpFileHint = nvmpFileHint;
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

	@Column(name = "nvmp_start_time", length = 19)
	public Timestamp getNvmpStartTime() {
		return this.nvmpStartTime;
	}

	public void setNvmpStartTime(Timestamp nvmpStartTime) {
		this.nvmpStartTime = nvmpStartTime;
	}

	@Column(name = "nvmp_end_time", length = 19)
	public Timestamp getNvmpEndTime() {
		return this.nvmpEndTime;
	}

	public void setNvmpEndTime(Timestamp nvmpEndTime) {
		this.nvmpEndTime = nvmpEndTime;
	}

	@Column(name = "nvmp_file_handler")
	public Integer getNvmpFileHandler() {
		return this.nvmpFileHandler;
	}

	public void setNvmpFileHandler(Integer nvmpFileHandler) {
		this.nvmpFileHandler = nvmpFileHandler;
	}

	@Column(name = "nvmp_file_hint")
	public Boolean getNvmpFileHint() {
		return this.nvmpFileHint;
	}

	public void setNvmpFileHint(Boolean nvmpFileHint) {
		this.nvmpFileHint = nvmpFileHint;
	}

}