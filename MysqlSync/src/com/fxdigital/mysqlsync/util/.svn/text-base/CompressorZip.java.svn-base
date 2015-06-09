package com.fxdigital.mysqlsync.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;

/**
 * 
 * 类名: CompressorZip <br/>
 * 功能描述: 压缩文件 日期: 2013-5-9 下午02:03:52 <br/>
 * 
 * @author lzh
 * @version
 */

public class CompressorZip {
	private long total = 0l;
	private static final Logger logger = Logger.getLogger(CompressorZip.class);
	/**
	 * 
	 * @param inputFileName
	 *            输入一个文件夹
	 * @param zipFileName
	 *            输出一个压缩文件夹，打包后文件名字
	 * @throws Exception
	 */
	public void zip(String inputFileName, String zipFileName) throws Exception {
		// System.out.println(zipFileName);
		zip(zipFileName, new File(inputFileName));
	}

	private void zip(String zipFileName, File inputFile) throws Exception {

		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
				zipFileName));
		zip(out, inputFile, "");
		// System.out.println("zip done");
		if (out != null) {

			out.close();
		}
	}

	private void zip(ZipOutputStream out, File f, String base) throws Exception {
		if (f.isDirectory()) { // 判断是否为目录
			File[] fl = f.listFiles();
			out.putNextEntry(new ZipEntry(base + "/"));
			base = base.length() == 0 ? "" : base + "/";
			for (int i = 0; i < fl.length; i++) {
				zip(out, fl[i], base + fl[i].getName());
			}
		} else { // 压缩目录中的所有文件
			out.putNextEntry(new ZipEntry(base));
			FileInputStream in = null;
			try {

				in = new FileInputStream(f);
				int b;
				// System.out.println(base);
				while ((b = in.read()) != -1) {
					out.write(b);
				}
				in.close();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (in != null) {
					in.close();
				}
			}
		}
		// out.close();
	}

	/***
	 * * copy file * * @param src * @param dest * @param status * @throws
	 * IOException
	 */
	public void copyFile(File src, File dest) throws Exception {
		if(!src.exists()){
			logger.info("the src file "+src.getPath()+" is not exist...");
			return;
		}
		if(!dest.exists()){
			dest.getParentFile().mkdirs();
			dest.createNewFile();
		}
		FileInputStream input = null;
		FileOutputStream outstrem = null;
		try {
			input = new FileInputStream(src);
			outstrem = new FileOutputStream(dest);
			outstrem.getChannel().transferFrom(input.getChannel(), 0,
					input.available());
			total++;
			String temp = String.format(
					"\ncopy:%s size:%s to: %s complate: %s", src, src.length(),
					dest, total);
			logger.info(temp);
		} catch (Exception e) {
			throw e;
		} finally {
			outstrem.flush();
			outstrem.close();
			input.close();
		}
		//logger.info("cope the log dir over....");
	}

	/** * * @param folder * @param filterFile * @throws Exception */
	public void copyFolder(File srcFolder, File destFolder) throws Exception {
		File[] files = srcFolder.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				String pathname = destFolder + File.separator + file.getName();

				File dest = new File(pathname);
				File destPar = dest.getParentFile();
				destPar.mkdirs();
				if (!dest.exists()) {
					dest.createNewFile();
				}
				copyFile(file, dest);

			} else {
				copyFolder(file, destFolder);
			}
		}
	}

	// 复制文件夹
	public void copyDirectiory(String sourceDir, String targetDir)
			throws IOException {
		File tarFile=new File(targetDir);
		File souFile=new File(sourceDir);
		if(!souFile.exists()){
			logger.info("the file "+sourceDir +"is not exist...");
			return;
			
		}
		if(!tarFile.exists()){
			// 新建目标目录
			(new File(targetDir)).mkdirs();
		}
		
		// 获取源文件夹当前下的文件或目录
		File[] file = (new File(sourceDir)).listFiles();
		for (int i = 0; i < file.length; i++) {
			if (file[i].isFile()) {
				// 源文件
				File sourceFile = file[i];
				// 目标文件
				File targetFile = new File(
						new File(targetDir).getAbsolutePath() + File.separator
								+ file[i].getName());
				try {
					copyFile(sourceFile, targetFile);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (file[i].isDirectory()) {
				// 准备复制的源文件夹
				String dir1 = sourceDir + "/" + file[i].getName();
				// 准备复制的目标文件夹
				String dir2 = targetDir + "/" + file[i].getName();
				copyDirectiory(dir1, dir2);
			}
		}
	}

	/**
	 * 
	 * @param srcFileName
	 * @param destFileName
	 * @param srcCoding
	 * @param destCoding
	 * @throws IOException
	 */
	public void copyFile(File srcFileName, File destFileName, String srcCoding,
			String destCoding) throws IOException {// 把文件转换为GBK文件
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					srcFileName), srcCoding));
			bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(destFileName), destCoding));
			char[] cbuf = new char[1024 * 5];
			int len = cbuf.length;
			int off = 0;
			int ret = 0;
			while ((ret = br.read(cbuf, off, len)) > 0) {
				off += ret;
				len -= ret;
			}
			bw.write(cbuf, 0, off);
			bw.flush();
		} finally {
			if (br != null)
				br.close();
			if (bw != null)
				bw.close();
		}
	}
	
	
	public void deleteFolder(String name){
		DeleteFolder delf = new DeleteFolder();
		delf.delFoder(name);
		//logger.info("delete the log dir over...");
	}

	public static void main(String[] args) {

		String inputFileName = "F:\\opt"; // 你要压缩的文件夹
		String zipFileName = "F:\\opt.zip"; // 压缩后的zip文件

		CompressorZip book = new CompressorZip();
		try {
			book.zip(inputFileName, zipFileName);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

//		String strSrc = "F:\\opt";
		String strDest = "E:\\opt";
//
//		try {
//			book.copyDirectiory(strSrc,strDest);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		book.deleteFolder(strDest);
		String srcFile="/log/log_fxconf_2014/log4j.properties";
		String destFile="/etc/fxconf/log/log4j.properties";
		File src=new File(srcFile);
		File dest=new File(destFile);
		try {
			book.copyFile(src, dest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}