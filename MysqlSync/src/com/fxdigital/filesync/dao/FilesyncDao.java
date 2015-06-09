package com.fxdigital.filesync.dao;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;

import com.fxdigital.filesync.bean.FileSync;
import com.fxdigital.filesync.db.DBConn;

public class FilesyncDao {

	private static final Logger logger = Logger
			.getLogger(FilesyncDao.class);

	/**
	 * 实例化对象
	 * 
	 */
	public static FilesyncDao filesyncDao = null;

	public static FilesyncDao getInstance() {
		if (null == filesyncDao) {
			filesyncDao = new FilesyncDao();
		}
		return filesyncDao;
	}



	public void updateMainFlag(String filepath, String filename,
			Timestamp modifiedtime) {

		DBConn db = DBConn.getDBConn();
		Connection conn = db.getConn();
		File file = new File(filepath + '/' + filename);
		String sql = "update sync_file set mainflag=mainflag+1,modifiedtime='"
				+ modifiedtime + "',file=? where filepath='" + filepath
				+ "' and filename='" + filename + "' and modifiedtime<>'"
				+ modifiedtime + "'";
		logger.info("updateMainFlag   " + sql);
		try {
			FileInputStream inS = new FileInputStream(file);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setBinaryStream(1, inS, inS.available());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("修改主服务器标志失败");
		}
	}

	public void updateBackupFlag(FileSync fileSync) {

		DBConn db = DBConn.getDBConn();
		String sql = "update sync_file set backupflag=backupflag+1 where filepath='"
				+ fileSync.getFilePath() + "' and filename='"
				+ fileSync.getFileName() + "'";
		logger.info("updatebackupFlag " + sql);
		try {
			db.updateInfo(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("修改从服务器标志失败");
		}
	}

	public void deleteAllRecord() {
		DBConn db = DBConn.getDBConn();
		String sql = "delete from sync_file";
		try {
			db.updateInfo(sql);
			logger.info("deleteAllRecord " + sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("删除数据失败");
		}
	}



	/**
	 *
	 * 
	 * 查找文件已经更改的数据
	 * 
	 * @return
	 */
	public List<FileSync> queryMainFileModified() {
		// Integer num = -1;
		DBConn db = DBConn.getDBConn();
		String sql = "select * from sync_file where mainflag>backupflag";
		List<FileSync> list = db.executeFileQuery(sql);
		return list;
	}

	public void insertFile(FileSync fileSync) throws SQLException {
		DBConn db = DBConn.getDBConn();
		Connection conn = db.getConn();
		PreparedStatement ps = conn
				.prepareStatement("insert into sync_file(modifiedtime,filepath,filename,file,mainflag,backupflag) values(?,?,?,?,?,?)");
		ps.setTimestamp(1, fileSync.getModifiedTime());
		ps.setString(3, fileSync.getFileName());
		ps.setString(2, fileSync.getFilePath());
		ps.setBinaryStream(4, fileSync.getFile(), fileSync.getStrLength());
		ps.setInt(5, 1);
		ps.setInt(6, 0);
		ps.executeUpdate();

	}

	/**
	 *
	 * 
	 * @param uuid
	 * @return
	 */
	public List<FileSync> getFileInfo() {
		// Integer num = -1;
		DBConn db = DBConn.getDBConn();
		String sql = "select * from sync_file";
		List<FileSync> list = db.executeFileQuery(sql);
		return list;
	}
	
	
	public Integer getBackBigtoMain(){
		DBConn db = DBConn.getDBConn();
		String sql = "select count(*) from sync_file where backupflag>mainflag";
		int num=db.executeQuery(sql).size();
		return num;
	}

	public static void main(String[] args) {
		// int num = FilesyncDao.getInstance().getFileNum();
		// logger.info(num);
		
		
		List<FileSync> list=FilesyncDao.getInstance().getFileInfo();
		System.out.println(list.get(1).getModifiedTime());
	}
}
