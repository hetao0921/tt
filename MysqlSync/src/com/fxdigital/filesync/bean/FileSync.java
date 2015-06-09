package com.fxdigital.filesync.bean;

import java.io.FileInputStream;
import java.sql.Timestamp;
import java.util.Date;


public class FileSync {
	
	public Timestamp modifiedTime;
	public String fileName;
	public String filePath;
	public FileInputStream file;
	public int version;
	public long strLength;
	
	public java.sql.Blob blob;
	
	
	public int mainFlag;
	public int backupFlag;
	

	public Timestamp getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
	
	
	public FileInputStream getFile() {
		return file;
	}
	public void setFile(FileInputStream file) {
		this.file = file;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public java.sql.Blob getBlob() {
		return blob;
	}
	public void setBlob(java.sql.Blob blob) {
		this.blob = blob;
	}
	public long getStrLength() {
		return strLength;
	}
	public void setStrLength(long strLength) {
		this.strLength = strLength;
	}
	public int getMainFlag() {
		return mainFlag;
	}
	public void setMainFlag(int mainFlag) {
		this.mainFlag = mainFlag;
	}
	public int getBackupFlag() {
		return backupFlag;
	}
	public void setBackupFlag(int backupFlag) {
		this.backupFlag = backupFlag;
	}
	
	
	
	
	
	
	

}
