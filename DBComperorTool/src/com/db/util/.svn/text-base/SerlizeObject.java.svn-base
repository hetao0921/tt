package com.db.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SerlizeObject {

	public void serlizedObject(String fileName,String fileDirectory,Object obj) {
		try {
			String filePath="";
			if(null!=fileName&&!("").equals(fileName)){
				filePath=fileName;
			}
			else if(null!=fileDirectory&&!("").equals(fileDirectory)){
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");//设置日期格式
				String nowTime=df.format(new Date());
				if(System.getProperty("os.name").equals("Linux")){
					filePath=fileDirectory+"/"+nowTime+".out";
				}else{
					filePath=fileDirectory+"\\"+nowTime+".out";
				}
			}else{
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");//设置日期格式
				String nowTime=df.format(new Date());
				filePath=""+nowTime+".out";
			}
			FileOutputStream fos = new FileOutputStream(filePath);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
//			System.out.println(" 1> " + obj.getName());
//			obj.setName("My Cat");
			oos.writeObject(obj);
			oos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Object deSerlizeObject(String fileName,Object obj) {
		  try {
              FileInputStream fis = new FileInputStream(fileName);
              ObjectInputStream ois = new ObjectInputStream(fis);
              obj = (Object) ois.readObject();
//              System.out.println(" 2> " + cat.getName());
              ois.close();
      } catch (Exception ex) {
              ex.printStackTrace();
      }
		  return obj;
	}
}
