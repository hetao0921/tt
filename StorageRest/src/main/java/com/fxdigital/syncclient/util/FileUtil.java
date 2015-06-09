package com.fxdigital.syncclient.util;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

public class FileUtil {
	private static final Logger logger = Logger.getLogger(FileUtil.class);

	/**
	 * 
	 * @param f
	 * @return
	 * @throws Exception
	 */
	// 递归获取文件夹大小
	public long getFileSize(File f) throws Exception// 取得文件夹大小
	{
		long size = 0;
		if (!f.isDirectory()) {
			return f.length();
		}
		File flist[] = f.listFiles();
		for (File file : flist) {
			if (file.isDirectory()) {
				size = size + getFileSize(file);
			} else {
				size = size + file.length();
			}
		}
		return size;
	}

	// 获取文件大小
	/**
	 * 
	 * @param fileS
	 * @return
	 */
	public String FormetFileSize(long fileS) {// 转换文件大小
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "K";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}

	public void getFileInfo(String filePath) {

		File diskPartition = new File(filePath);

		long totalCapacity = diskPartition.getTotalSpace();

		long freePartitionSpace = diskPartition.getFreeSpace();
		long usablePatitionSpace = diskPartition.getUsableSpace();

		logger.info("**** Sizes in Mega Bytes ****\n");

		logger.info("Total C partition size : " + totalCapacity
				/ (1024 * 1024) + " MB");
		logger.info("Usable Space : " + usablePatitionSpace
				/ (1024 * 1024) + " MB");
		logger.info("Free Space : " + freePartitionSpace / (1024 * 1024)
				+ " MB");

		logger.info("\n**** Sizes in Giga Bytes ****\n");

		logger.info("Total C partition size : " + totalCapacity
				/ (1024 * 1024 * 1024) + " GB");
		logger.info("Usable Space : " + usablePatitionSpace
				/ (1024 * 1024 * 1024) + " GB");
		logger.info("Free Space : " + freePartitionSpace
				/ (1024 * 1024 * 1024) + " GB");
	}

	/**
	 * 获取当前文件时间
	 * 
	 * @param args
	 * @throws IOException
	 */
	public String getFileCreatTime(String fileName) {
		File file = new File("Test.java");
		Long time = file.lastModified();
		Calendar cd = Calendar.getInstance();
		cd.setTimeInMillis(time);
		logger.info(cd.getTime());
		return "";
	}

	// 当前硬盘剩余容量
	public int getDiskResidualCapacity() {
		String filePath = "/usr/local/movies/storage/";
		List<String> usedCapacity = VideoConverter.execStrList("df -h");
		for (String str : usedCapacity) {
			String[] strList = str.split("\\s{1,}");
			logger.info("length: " + strList.length);
			for (String arrayStr : strList) {
				logger.info("usedCapacity " + arrayStr);
			}

		}
		return 1;

	}

	/**
	 * 找到可能的挂载点
	 * 
	 * @param filePath
	 * @return
	 */
	public List<String> getDevList(String filePath) {
		List<String> strList = new ArrayList<String>();
		for (int i = 0; i <= filePath.split("/").length; i++) {
			filePath = filePath.substring(0, filePath.lastIndexOf("/"));
			strList.add(filePath);
			logger.info("tt " + filePath);
		}
		return strList;
	}

	/**
	 * 获取挂载点
	 * 
	 * @return
	 */
	public HashMap<String, String> getDev() {

		String result = null;
		List<String> usedCapacity = VideoConverter.execStrList("df -h");
		HashMap<String, String> map = new HashMap<String, String>();
		for (String str : usedCapacity) {
			String[] strList = str.split("\\s{1,}");
			map.put(strList[strList.length - 1], strList[strList.length - 3]);
		}
		return map;
	}

	/**
	 * 获取当前挂载点还可以用的容量
	 * 
	 * @return
	 */
	public String getVolumn(String filePath) {
		HashMap<String, String> hm = getDev();
		Set set = hm.entrySet();
		String volumn = "";
		java.util.Iterator its = hm.entrySet().iterator();
		List<String> devList = getDevList(filePath);
		logger.info("录像文件存储位置为： "+filePath);
		while (its.hasNext()) {
			java.util.Map.Entry entrys = (java.util.Map.Entry) its.next();
			for (String str : devList) {
				if (entrys.getKey().toString().equals(str)) {
					volumn = entrys.getValue().toString();
					logger.info("当前挂载点为： " + entrys.getKey() + " 当前容量为： "
							+ entrys.getValue());
					return volumn;
				}
			}
		}

		java.util.Iterator it = hm.entrySet().iterator();
		logger.info("hm " + hm);
		logger.info("devList " + devList);
		while (it.hasNext()) {
			java.util.Map.Entry entry = (java.util.Map.Entry) it.next();
			for (String str : devList) {
				if (entry.getKey().toString().contains(str)) {
					volumn = entry.getValue().toString();
					logger.info("当前挂载点为： " + entry.getKey() + " 当前容量为： "
							+ entry.getValue());
					return volumn;
				}
			}
		}
		logger.info("volumn " + null == volumn || "".equals(volumn));
		java.util.Iterator itor = hm.entrySet().iterator();
		if (null == volumn || "".equals(volumn)) {
			while (itor.hasNext()) {
				java.util.Map.Entry entry = (java.util.Map.Entry) itor.next();
				if (entry.getKey().toString().trim().equals("/")) {
					volumn = entry.getValue().toString();
					logger.info("当前挂载点为： " + entry.getKey() + " 当前容量为： "
							+ entry.getValue());
				}
			}
		}
		logger.info("volumn " + volumn);
		return volumn;
	}

	/**
	 * 获取以M为单位的可用容量
	 */
	public int getIntegerVolumn(String filePath) {
		String volumn = getVolumn(filePath);
		int intVolumn = 0;
		if (volumn.contains("G")) {
			volumn = volumn.replace("G", "").trim();
			intVolumn = Integer.valueOf(volumn) * 1024;
		}
		if (volumn.contains("M")) {
			volumn = volumn.replace("M", "").trim();
			intVolumn = Integer.valueOf(volumn);
		}
		return intVolumn;

	}

	public static void main(String[] args) throws IOException {
		FileUtil fd = new FileUtil();
		long all = 0;
		try {
			all = fd.getFileSize(new File("f:\\TDDOWNLOAD"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("ALL:  " + fd.FormetFileSize(all));

		fd.getDiskResidualCapacity();

		String filePath = "/usr/local/movies/storage/";

		int path_1 = filePath.indexOf("/");// 第一个位置

		int path_2 = path_1 + filePath.substring(path_1 + 1).indexOf("/") + 1;// 第二个位置

		int path_3 = path_2 + filePath.substring(path_2 + 1).indexOf("/") + 1;// 第三个位置

		int path_4 = path_3 + filePath.substring(path_3 + 1).indexOf("/") + 1;// 第四个位置
		logger.info(path_1 + "-" + path_2 + "-" + path_3 + "-" + path_4);
		logger.info(filePath.substring(0, path_4));
		logger.info(filePath.split("/").length);
		logger.info(filePath.lastIndexOf("/"));
		logger.info(filePath.substring(0, filePath.lastIndexOf("/")));
		List<String> strList = new ArrayList<String>();

		for (int i = 0; i <= filePath.split("/").length; i++) {
			// int
			filePath = filePath.substring(0, filePath.lastIndexOf("/"));
			strList.add(filePath);
			logger.info("tt " + filePath);

		}

		fd.getIntegerVolumn(filePath);

	}

}
