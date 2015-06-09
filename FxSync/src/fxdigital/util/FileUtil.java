package fxdigital.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class FileUtil {
	public static FileUtil fileUtil = null;

	public static FileUtil getInstance() {
		if (null == fileUtil) {
			fileUtil = new FileUtil();
		}
		return fileUtil;
	}
	
	public static List<String> readPathXML() {
		ArrayList<String> list = new ArrayList<String>();
		SAXReader read = new SAXReader();
		Document doc = null;
		String path = "";
		if (System.getProperty("os.name").equals("Linux")) // 操作系统名称
		{
			path = "/etc/fxconf/Sync/SyncDataSet.xml";
		} else {
			// Log4jUtil.info(this.getClass(),"==============="+System.getProperty("user.dir"));
			path = "D:\\SyncDataSet.xml";
		}
		try {
			doc = read.read(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null != doc) {
			Element root = doc.getRootElement();
			Element url = root.element("syncwindows");
			Element time = root.element("synclinux");
			list.add(url.getStringValue());
			list.add(time.getStringValue());
		}
		return list;
	}
	
	
	/**
	 * 写xml文件
	 * 
	 * @return
	 */
	public static void wirteXml(String address, String xml) {
		try {
			RandomAccessFile raf = new RandomAccessFile(address, "rw");
			raf.setLength(0);
			raf.seek(0);
			raf.write(xml.getBytes("utf-8"));
			raf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static String getOldPath(String baseRoot, String centerid,
			int localSelfVersion) {
		String oldPath = "";
		File file = new File(baseRoot);
		if (!file.exists() && !file.isDirectory()) {
			System.out.println("//不存在");
			file.mkdir();
		} else {
			System.out.println("//目录存在");
		}
		oldPath = baseRoot + "/sync" + centerid + localSelfVersion + ".xml";
		return oldPath;
	}
	
	
	 /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public  String readFileByLines(String path) {
    	if(("").equals(path)||null==path){
    		return null;
    	}
        File file = new File(path);
        BufferedReader reader = null;
        String result="";
        try {
        	reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                //System.out.println("line " + line + ": " + tempString);
                result=result+tempString;
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return result;
    }
    
    
	public List<HashMap<String, String>> MergeLoadInfo(
			List<HashMap<String, String>> serverLoadList,
			List<HashMap<String, String>> clientLoadList) {
		List<HashMap<String, String>> lastLoadList = null;
		List<HashMap<String, String>> tempLoadList = new ArrayList<HashMap<String, String>>();
		if (serverLoadList != null) {
			Log4jUtil.info(this.getClass(),"receive  " + "clientLoadList  " + clientLoadList);
			Log4jUtil.info(this.getClass(),"receive  " + "serverLoadList  " + serverLoadList);
			tempLoadList = MergeMap(clientLoadList,
					tempLoadList);
			Log4jUtil.info(this.getClass(),"receive  " + "tempLoadList1  " + tempLoadList);
			tempLoadList = MergeServerMap(
					serverLoadList, tempLoadList);
			Log4jUtil.info(this.getClass(),"receive  " + "tempLoadList2  " + tempLoadList);
			lastLoadList = new ArrayList<HashMap<String, String>>();
			Log4jUtil.info(this.getClass(),"receive  " + "lastLoadList1  " + lastLoadList);
			lastLoadList = getAllMap(tempLoadList,
					clientLoadList, serverLoadList);
			Log4jUtil.info(this.getClass(),"receive  " + "lastLoadList2  " + lastLoadList);
		}

		return lastLoadList;
	}
	
	
	/**
	 * 拼接客户端
	 * 
	 * @param uuid
	 * @return
	 */
	public static List<HashMap<String,String>> MergeMap(List<HashMap<String,String>> clientList,List<HashMap<String,String>> tempList){
		
		for(int k=0;k<clientList.size();k++){
			int theIndex=getIndex(tempList,clientList.get(k));
			if(theIndex==-1){
				HashMap<String,String> tempmap=new HashMap<String,String>();
				tempmap.put("centerid", clientList.get(k).get("centerid"));
				tempmap.put("local", String.valueOf(k));
				tempmap.put("remote", "");
				tempList.add(tempmap);
				}else{
					tempList.get(theIndex).put("remote", String.valueOf(k));
			}
		}
		return tempList;
	}
	
	
	/**
	 * 是否存在当前map
	 * 
	 * 
	 * @param uuid
	 * @return
	 */
	public static int getIndex(List<HashMap<String,String>> clientList,HashMap<String,String> map2){
		int index=-1;
		for(int i=0;i<clientList.size();i++){
			if((clientList.get(i).get("centerid".toLowerCase())).equals(map2.get("centerid".toLowerCase()))){
				index=i;
			}
		}
		return index;
		
	}
	
	
	/**
	 * 拼接服务器端
	 * 
	 * @param uuid
	 * @return
	 */
	public static List<HashMap<String,String>> MergeServerMap(List<HashMap<String,String>> clientList,List<HashMap<String,String>> tempList){
		
		for(int k=0;k<clientList.size();k++){
			int theIndex=getIndex(tempList,clientList.get(k));
			if(theIndex==-1){
				HashMap<String,String> tempmap=new HashMap<String,String>();
				tempmap.put("centerid", clientList.get(k).get("centerid"));
				tempmap.put("local", "");
				tempmap.put("remote", String.valueOf(k));
				tempList.add(tempmap);
				}else{
					tempList.get(theIndex).put("remote", String.valueOf(k));
			}
		}
		return tempList;
	}
	
	
	/**
	 * 拼接两个list
	 * 
	 * @param uuid
	 * @return
	 */
	public static List<HashMap<String,String>> getAllMap(List<HashMap<String,String>> allList,List<HashMap<String,String>> clientList,List<HashMap<String,String>> serverList){
		
		List<HashMap<String,String>> lastList=new ArrayList<HashMap<String,String>>();
		for(int i=0;i<allList.size();i++){
			HashMap<String,String> temp=new HashMap<String,String>();
			temp.put("centerid", allList.get(i).get("centerid"));
			//如果远程服务器上存在
			if(!allList.get(i).get("remote").equals("")){
				int serverindex=Integer.valueOf(allList.get(i).get("remote"));
				temp.put("serverversion", serverList.get(serverindex).get("version"));
				temp.put("centername", serverList.get(serverindex).get("centername"));
				temp.put("centerip", serverList.get(serverindex).get("centerip"));
			}
			else{
				temp.put("serverversion", "0");
			}
			//如果本地存在
			if(!allList.get(i).get("local").equals("")){
				int clientindex=Integer.valueOf(allList.get(i).get("local"));
				temp.put("clientversion", clientList.get(clientindex).get("version"));
				if(("").equals(temp.get("centername"))||null==temp.get("centername")){
					temp.put("centername", clientList.get(clientindex).get("centername"));
				}
				if(("").equals(temp.get("centerip"))||null==temp.get("centerip")){
					temp.put("centerip", clientList.get(clientindex).get("centerip"));
				}
				temp.put("updatetime", clientList.get(clientindex).get("updatetime"));
				temp.put("flag", clientList.get(clientindex).get("flag"));
			}
			else{
				temp.put("clientversion", "0");
			}

			lastList.add(temp);
		}
		return lastList;
	}
	
	

	
	
}
