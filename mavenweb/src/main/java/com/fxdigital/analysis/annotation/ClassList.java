/**
 * 
 */
package com.fxdigital.analysis.annotation;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author lizehua
 *
 */
public class ClassList {
	/**
     * 获取包下的所有类
     *
     * @param pack
     * @return
     */
    public List<String> initClasses(String pack,boolean recursive) {
    	List<String> list=new ArrayList<String>();
        //boolean recursive = true;
        String packageName = pack;
        String packageDirName = packageName.replace('.', '/');
        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            while (dirs.hasMoreElements()) {
                URL url = dirs.nextElement();
                String protocol = url.getProtocol();
                if ("file".equals(protocol)) {
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    list=   getClassByFile(packageName, filePath, recursive);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
 
    /**
     * 以文件的形式来获取包下的所有Class
     *
     * @param pkgName 包名
     * @param pkgPath 包路径
     * @param recursive 是否迭代
     */
    protected List<String> getClassByFile(String pkgName,String pkgPath, final boolean recursive) {
    	List<String> list=new ArrayList<String>();
        File dir = new File(pkgPath);
        if (!dir.exists() || !dir.isDirectory()) {
             
            return list;
        }
         
        File[] dirfiles = dir.listFiles(new FileFilter() {
             
            public boolean accept(File file) {
                return (recursive && file.isDirectory())|| (file.getName().endsWith(".class"));
            }
        });
         
        for (File file : dirfiles) {
            //是目录则继续迭代
            if (file.isDirectory()) {
                getClassByFile(pkgName + "." + file.getName(), file.getAbsolutePath(), recursive);
            } else {
                 
                String className = file.getName().substring(0,file.getName().length() - 6);
                try {
                	
//                    System.out.println(Thread.currentThread().getContextClassLoader().loadClass(pkgName + '.' + className));
                    String claName=pkgName + '.' + className;
//                    System.out.println(claName);
//                    System.out.println(className.contains("ClassList"));
                    
                   if(!className.contains("className")){
                	   
                	   list.add(claName);
                   }
 
                } catch (Exception e) {
                     
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
    
    public static void main(String[] args) {
    	ClassList te=new ClassList();
    	
    	te.initClasses("com.fxdigital.backup.web", false);
	}
}
