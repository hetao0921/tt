/**
 * 
 */
package com.fxdigital.analysis.annotation;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * @author lizehua
 *
 */
public class Main {
	public static void product(String pack)  {
			
		try{
			//生成文件夹
			File file=new File("D:/classes");
			if(!file.exists()){
				
				file.mkdir();
			}
		ClassList cla=new ClassList();
		List<String> list=cla.initClasses(pack, true);
		List<Message> allList=new ArrayList<Message>();
		for (String string : list) {
			ParseAnnotation parse=new ParseAnnotation();
			parse.parseMethod(string);
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
	
public static void main(String[] args) {
	Main.product("com.fxdigital.analysis.web");
}
}
