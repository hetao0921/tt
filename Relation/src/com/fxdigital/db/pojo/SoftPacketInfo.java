package com.fxdigital.db.pojo;

public class SoftPacketInfo {
	private String filename;
	private String fileID;
	private String softtype;
	private String softversion;
	private long filesize;
	private long softvernum;
	private String softuptype;//增量或全量
	private String softlevel;//软件等级
	private String publishdate;//发布时间
	public SoftPacketInfo() {
		super();
	}
	
	public String getSoftuptype() {
		return softuptype;
	}

	public void setSoftuptype(String softuptype) {
		this.softuptype = softuptype;
	}

	public long getSoftvernum() {
		return softvernum;
	}

	public void setSoftvernum(long softvernum) {
		this.softvernum = softvernum;
	}

	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public String getFileID() {
		return fileID;
	}

	public void setFileID(String fileID) {
		this.fileID = fileID;
	}

	public String getSofttype() {
		return softtype;
	}
	public void setSofttype(String softtype) {
		this.softtype = softtype;
	}
	public String getSoftversion() {
		return softversion;
	}
	public void setSoftversion(String softversion) {
		this.softversion = softversion;
	}

	public long getFilesize() {
		return filesize;
	}

	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}

	public String getSoftlevel() {
		return softlevel;
	}

	public void setSoftlevel(String softlevel) {
		this.softlevel = softlevel;
	}

	public String getPublishdate() {
		return publishdate;
	}

	public void setPublishdate(String publishdate) {
		this.publishdate = publishdate;
	}
}
