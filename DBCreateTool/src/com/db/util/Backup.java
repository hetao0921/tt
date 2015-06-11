package com.db.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.db.service.GetExistInfo;

public class Backup {
	private static final Logger logger = Logger.getLogger(Backup.class);
	public void backup(String tableSchema,String tableName) {
		 String backupPath="";
		  try {
		   Runtime rt = Runtime.getRuntime();
		   SimpleDateFormat f=new SimpleDateFormat("yyyyMMddhhmmss");
		   java.util.Date nowDate=new Date(); 
		   String cmdPath="";
		  
		   String sqlWinPath="C:\\Users\\Administrator\\Desktop\\mysql+tomcat\\mysql-5.6.20-win32\\bin\\mysqldump -hlocalhost -uadmin -p111 "+tableSchema+" "+tableName+"";
		   String sqlLinuxPath="/usr/local/mysql/bin/mysqldump -hlocalhost -uadmin -p111 "+tableSchema+" "+tableName+"";
			if (System.getProperty("os.name").equals("Linux")) // 操作系统名称
			{
				cmdPath = sqlLinuxPath;
				backupPath="/etc/oldsql/"+tableName+"_"+f.format(nowDate)+".sql";
			} else {
				cmdPath = sqlWinPath;
				backupPath="D:\\oldsql\\"+tableName+"_"+f.format(nowDate)+".sql";
			}

		   // 调用 调用mysql的安装目录的命令
		   Process child = rt
		     .exec(cmdPath);
		   // 设置导出编码为utf-8。这里必须是utf-8
		   // 把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行
		   InputStream in = child.getInputStream();// 控制台的输出信息作为输入流

		   InputStreamReader xx = new InputStreamReader(in, "utf-8");
		   // 设置输出流编码为utf-8。这里必须是utf-8，否则从流中读入的是乱码

		   String inStr;
		   StringBuffer sb = new StringBuffer("");
		   String outStr;
		   // 组合控制台输出信息字符串
		   BufferedReader br = new BufferedReader(xx);
		   while ((inStr = br.readLine()) != null) {
		    sb.append(inStr + "\r\n");
		   }
		   outStr = sb.toString();
		   File file=new File(backupPath);
		   if(!file.exists()){
			   try { 
				   if(!file.getParentFile().exists()){
					   file.getParentFile().mkdirs();
				   }
			        file.createNewFile();    
			    } catch (IOException e) {    
			        // TODO Auto-generated catch block    
			        e.printStackTrace();    
			    }    
		   }
		   // 要用来做导入用的sql目标文件：
		   FileOutputStream fout = new FileOutputStream(
				   backupPath);
		   OutputStreamWriter writer = new OutputStreamWriter(fout, "utf-8");
		   writer.write(outStr);
		   writer.flush();
		   in.close();
		   xx.close();
		   br.close();
		   writer.close();
		   fout.close();

		   logger.info("备份文件"+backupPath+"成功");

		  } catch (Exception e) {
			logger.info("备份文件"+backupPath+"失败，失败原因"+e);
		   e.printStackTrace();
		  }

		 }
	
	
	public static void load(){
        try {      
            String fPath = "c:/test.sql";    
            Runtime rt = Runtime.getRuntime();      
     
            // 调用 mysql 的 cmd:      
            Process child = rt.exec("C://Program Files//MySQL//MySQL Server 5.1//bin//mysql.exe -uroot -proot dlgs_test ");      
            OutputStream out = child.getOutputStream();//控制台的输入信息作为输出流      
            String inStr;      
            StringBuffer sb = new StringBuffer("");      
            String outStr;      
            BufferedReader br = new BufferedReader(new InputStreamReader(      
                    new FileInputStream(fPath), "utf8"));      
            while ((inStr = br.readLine()) != null) {      
                sb.append(inStr + "\r\n");      
            }      
            outStr = sb.toString();      
     
            OutputStreamWriter writer = new OutputStreamWriter(out, "utf8");      
            writer.write(outStr);      
            // 注：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避免      
            writer.flush();      
            // 别忘记关闭输入输出流      
            out.close();      
            br.close();      
            writer.close();      
     
            System.out.println("");      
     
        } catch (Exception e) {      
            e.printStackTrace();      
        }    
	}

}
