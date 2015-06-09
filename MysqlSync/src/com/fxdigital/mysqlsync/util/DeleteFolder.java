package com.fxdigital.mysqlsync.util;
/**
 * 工程名: MPFManage
 * 文件名: DeleteFolder.java
 * 包名: com.chinamworld.mpf.manage.pub.util
 * 日期: 2013-5-9上午10:53:35
 * Copyright (c) 2013, 北京联龙博通 All Rights Reserved.
 *
*/
/**
 * 工程名: MPFManage
 * 文件名: DeleteFolder.java
 * 包名: com.chinamworld.mpf.manage.pub.util
 * 日期: 2013-5-9上午10:53:35
 * Copyright (c) 2013, 北京联龙博通 All Rights Reserved.
 *
 */



import java.io.File;

/**
 * 类名: DeleteFolder <br/>
 * 功能: 删除文件夹 <br/>
 * 日期: 2013-5-9 上午10:53:35 <br/>
 *
 * @author   lzh
 * @version  	 
 */

public class DeleteFolder {
	public Boolean delAllFile(String path){
		Boolean flag=false;
		File file=new File(path);
		if(!file.exists()){
			return flag;
		}
		if(!file.isDirectory()){
			return flag;
		}
		String[] tempList=file.list();
		File temp=null;
		String str="";
		for(int i=0;i<tempList.length;i++){
			if(path.endsWith(File.separator)){
				str=path+tempList[i];
			}else{
				str=path+File.separator+tempList[i];
			}
			temp=new File(str);
			if(temp.isFile()){
				temp.delete();
			}
			if(temp.isDirectory()){
				delAllFile(str);//先删除文件里面的文件
				delFoder(str);//删除空文件夹
				flag=true;
			}
		}
		
		
		return flag;
		
		
	}

	/**
	 * delFoder:删除文件夹<br/>
	 *

	 *
	 * @author lzh
	 * @param str
	 * @since 1.0
	 */
	public void delFoder(String folderPath) {
		
		delAllFile(folderPath);//删除文件夹里面的所有内容
		File myFile=new  File(folderPath);
		myFile.delete();//删除空文件夹
	}
	
	
	public static void main(String[] args) {
	/*	DeleteFolder test=new DeleteFolder();
		test.delFoder("F:\\opt.zip");*/
		
		File file=new File("F:\\opt.zip");
		file.delete();
	}
	

}

