package com.fxdigital.filesync.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fxdigital.filesync.bean.FileSync;

public class FileUtil {

	private static final Logger logger = Logger.getLogger(FileUtil.class);

	List<String> pathList = new ArrayList<String>();

	public List<String> scanFolder(String path) {

		File rootDir = new File(path);
		if(!rootDir.exists()){
			logger.info("the path is not exist...");
			return null;
		}
		if (!rootDir.isDirectory()) {
			// logger.info("文件名" + rootDir.getPath());
			if(rootDir.getPath().toLowerCase().contains("log4j")||rootDir.getPath().toLowerCase().contains("log4")){
				pathList.add(rootDir.getPath());
			}
			if (!rootDir.getPath().toLowerCase().contains("log")) {
				pathList.add(rootDir.getPath());
			}
		} else {
			String[] fileList = rootDir.list();
			for (int i = 0; i < fileList.length; i++) {
				path = rootDir.getAbsolutePath() + "/" + fileList[i];
				scanFolder(path);
			}
		}
		return pathList;
	}

	public String getFileModifiedTime(String filePath) {
		logger.info("to get the modified time ,the current path is: "
				+ filePath);
		File file = new File(filePath);
		Long time = file.lastModified();
		Calendar cd = Calendar.getInstance();
		cd.setTimeInMillis(time);
		// logger.info(cd.getTime());

		SimpleDateFormat formatTime = new SimpleDateFormat("yyyyMMddHHmmss");
		// logger.info(formatTime.format(cd.getTime()));
		return formatTime.format(cd.getTime());
	}

	public Date getModifiedTime(String filePath) {
		logger.info("to get the modified time ,the current path is: "
				+ filePath);
		File file = new File(filePath);
		Long time = file.lastModified();
		Calendar cd = Calendar.getInstance();
		cd.setTimeInMillis(time);
		// logger.info(cd.getTime());

		SimpleDateFormat formatTime = new SimpleDateFormat("yyyyMMddHHmmss");
		// logger.info(formatTime.format(cd.getTime()));
		java.sql.Date modifiedDate = new java.sql.Date(cd.getTime().getTime());
		return cd.getTime();
	}

	public List<FileSync> getFileInfo(List<String> pathList) {
		List<FileSync> list = new ArrayList<FileSync>();
		for (int i = 0; i < pathList.size(); i++) {
			String fileInfo = pathList.get(i);
			File tempFile = new File(fileInfo);
			FileSync fileSync = new FileSync();
			String fileName = tempFile.getName();
			String filePath = tempFile.getParent();
			Date modifiedTime = getModifiedTime(fileInfo);
			FileInputStream inS = null;
			try {
				inS = new FileInputStream(tempFile);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// String
			fileSync.setFileName(fileName);
			fileSync.setFilePath(filePath);
			fileSync.setModifiedTime(new java.sql.Timestamp(modifiedTime
					.getTime()));
			fileSync.setVersion(0);
			fileSync.setFile(inS);
			fileSync.setStrLength(tempFile.length());
			list.add(fileSync);
			logger.info("fileName" + fileSync.getFileName());
			logger.info("filePath" + fileSync.getFilePath());
			logger.info("modifiedTime" + fileSync.getModifiedTime());
			logger.info("version" + fileSync.getVersion());
			logger.info("file" + fileSync.getFile());
		}
		return list;
	}

	public void writeFileInfo(FileSync fileSync) throws IOException {
		String filePath = fileSync.getFilePath();
		String fileName = fileSync.getFileName();
		java.sql.Blob blob = fileSync.getBlob();
		String path = filePath + "/" + fileName;
		logger.info("begin write the file ,the current file is: " + path);
		if (!isFileExist(path)) {
			File dirFile = new File(filePath);
			if (!dirFile.exists() && !dirFile.isDirectory()) {
				dirFile.mkdirs();
			}
			File tempFile = new File(filePath);
			try {
				tempFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// setFileContent(path,blob);
		getBlobToFile(path, blob);
	}

	public void getBlobToFile(String filePath, java.sql.Blob blob) {
		InputStream ins = null;
		FileOutputStream fout = null;
		try {
			ins = blob.getBinaryStream();
			File file = new File(filePath);
			fout = new FileOutputStream(file);
			byte[] b = new byte[4096];
			int len = 0;
			while ((len = ins.read(b)) != -1) {
				fout.write(b, 0, len);
			}

			fout.close();
			ins.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean isFileExist(String filePath) {
		File tempFile = new File(filePath);
		return tempFile.exists();
	}

	public void setFileContent(String filePath, java.sql.Blob blob)
			throws IOException {
		File tempFile = new File(filePath);
		FileOutputStream fout = null;
		InputStream is = null;
		try {
			fout = new FileOutputStream(tempFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			is = blob.getBinaryStream();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int size;
		byte[] buffer = new byte[4096];
		while ((size = is.read(buffer)) != -1) {
			fout.write(buffer, 0, size);
		}
		fout.close();
	}

	public int getIndex(String path, String name, List<FileSync> list) {
		int index = -1;
		for (int i = 0; i < list.size(); i++) {
			FileSync fileSync = list.get(i);
			if (path.equals(fileSync.getFilePath())
					&& name.equals(fileSync.getFileName())) {
				index = i;
			}
		}
		return index;
	}

	public static void main(String[] args) {

		FileUtil fileUtil = new FileUtil();

		List<String> pathList = fileUtil.scanFolder("/etc/fxconf");

		for (int i = 0; i < pathList.size(); i++) {
			logger.info("得到目录：" + pathList.get(i));
		}

		fileUtil.getFileInfo(pathList);

	}

}
