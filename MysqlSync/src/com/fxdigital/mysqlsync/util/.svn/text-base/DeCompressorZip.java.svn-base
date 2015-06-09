package com.fxdigital.mysqlsync.util;
/**
 * 工程名: EManualManage
 * 文件名: DeCompressorZip.java
 * 包名: com.emanual.manage.pub.util
 * 日期: 2013-5-8下午04:26:53
 * Copyright (c) 2013, 北京联龙博通 All Rights Reserved.
 *
*/
/**
 * 工程名: EManualManage
 * 文件名: DeCompressorZip.java
 * 包名: com.emanual.manage.pub.util
 * 日期: 2013-5-8下午04:26:53
 * Copyright (c) 2013, 北京联龙博通 All Rights Reserved.
 *
 */



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;





/**
 * 类名: DeCompressorZip <br/>
 * 功能: 解压zip包 <br/>
 * 日期: 2013-5-8 下午04:26:53 <br/>
 *
 * @author   lzh
 * @version  	 
 */

public class DeCompressorZip {
	/**
	 * 
	 * createDirectory:创建文件夹. <br/>
	 *

	 *
	 * @author lzh
	 * @param directory
	 * @param subDirectory
	 * @since 1.0
	 */
	private  void createDirectory(String directory, String subDirectory){
		String dir[];
		File fl=new File(directory);
		
		if(subDirectory==""&&fl.exists()!=true){
			fl.mkdir();
		}else if(subDirectory!=""){
			dir=subDirectory.replace("\\","/").split("/");
			for(int i=0;i<dir.length;i++){
				File subFile=new File(directory+File.separator+dir[i]);
				if(subFile.exists()==false){
					subFile.mkdir();
				}
				StringBuilder temp=new StringBuilder(directory);
				temp.append(File.separator+dir[i]);
				directory=temp.toString();
			}
		}
		
	}
	/**
	 * 
	 * unZip:解压. <br/>
	 *

	 *
	 * @author lzh
	 * @param spath
	 * @param dpath
	 * @since 1.0
	 */
	
	
	public  void unZip(String spath,String dpath){
		ZipFile zipFile=null;
		InputStream in=null;
		FileOutputStream out=null;
		try {zipFile=new ZipFile(spath);
			Enumeration enu=zipFile.entries();
			
			ZipEntry zipEntry=null;
			/*
			File file=new File(dpath);
			if(!file.exists()){
				file.mkdir();
			}
			*/
			createDirectory(dpath, "");
			while(enu.hasMoreElements()){
			zipEntry=(ZipEntry) enu.nextElement();	
			if(zipEntry.isDirectory()){
				String name=zipEntry.getName();
				name=name.substring(0,name.length()-1);
				File dir=new File(dpath+File.separator+name);
				dir.mkdir();//创建目录
			}else{
				String fileName=zipEntry.getName();
				fileName=fileName.replace("\\", "/");
				if(fileName.indexOf("/")!=-1){
					createDirectory(dpath, fileName.substring(0,fileName.lastIndexOf("/")));
					fileName=fileName.substring(fileName.lastIndexOf("/")+1,fileName.length());
					
					
					
				}
				File f=new File(dpath+File.separator+zipEntry.getName());
				f.createNewFile();
				 in=zipFile.getInputStream(zipEntry);
				out=new FileOutputStream(f);
				byte[] by=new byte[1024];
				int len;
				while((len=in.read(by))!=-1){
					out.write(by,0,len);
				}
			}
			}
			out.close();
			in.close();
		
			
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally{
			try {
				if(zipFile!=null){
					
					zipFile.close();
				}
				if(out!=null){
					out.close();
				}
				if(in!=null){
					in.close();
				}
			} catch (IOException e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		}
	}
	
	

	public static void main(String[] args) {
		DeCompressorZip de=new DeCompressorZip();
		de.unZip("F:\\opt.zip", "e:\\opt");
	}
	

	
	
}

