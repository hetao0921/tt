package fxdigital.postserver.contentdispose.handlers.dbsync.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import fxdigital.postserver.contentdispose.handlers.dbsync.dao.DataOperateRecordDao;

/**
 * @author  het
 *下载信息处理类
 * 2014-7-30
 * SyncWebb
 * fxdigital.dbsync.domains.client.service
 */
public class LoadMessage {
	private static Logger logger = Logger.getLogger(LoadMessage.class);
	public static LoadMessage loadMessage = null;

	public static LoadMessage getInstance() {
		if (null == loadMessage) {
			loadMessage = new LoadMessage();
		}
		return loadMessage;
	}



		
//		List<HashMap<String, String>> clientList = new ArrayList<HashMap<String, String>>();
//		HashMap<String,String> map1=new HashMap<String,String>();
//		map1.put("centerid", "het@001");
//		map1.put("version", "2");
//		
//		HashMap<String,String> map2=new HashMap<String,String>();
//		map2.put("centerid", "001@001");
//		map2.put("version", "4");
//		HashMap<String,String> map3=new HashMap<String,String>();
//		map3.put("centerid", "003@001");
//		map3.put("version", "4");
//		
//		clientList.add(map1);
//		clientList.add(map2);
//		clientList.add(map3);
		
		// 从MQ获取服务器上的版本号信息


	/**
	 * 查询下拉框，分正在下载，已经下载
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getLoadinfo(String flag) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		return list;
	}

	/**
	 * 获取下载反馈
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getLoadResult() {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		return list;
	}

	/**
	 * 发送下载命令
	 * 
	 * @return
	 */
	public boolean SendLoadCommand(List<HashMap<String, String>> list) {
		return true;
	}

	/**
	 * 获取下载锁信息
	 * 
	 * @return
	 */

	/**
	 * 获取下载时的记录
	 * 
	 * @param uuid
	 * @return
	 */
	public String[] getLoadRecord() {
		return DataOperateRecordDao.getInstance().getAllRecord();

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
				temp.put("centername", clientList.get(clientindex).get("centername"));
			//	temp.put("centerip", clientList.get(clientindex).get("centerip"));
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
	 * 返回两个同步服务器之间需要同步的数据
	 * 例：下级数据
	 * lowMap.put("centerid", "001");
		lowMap.put("version", "2");
		lowMap2.put("centerid", "002");
		lowMap2.put("version", "2");
		lowMap3.put("centerid", "003");
		lowMap3.put("version", "2");
		上级数据：
	 *  upMap1.put("centerid", "001");
		upMap1.put("version", "3");
		upMap2.put("centerid", "002");
		upMap2.put("version", "3");
		upMap3.put("centerid", "003");
		upMap3.put("version", "1");
		
		返回数据集合：
		上级需同步的集合[{centerid=003, version=2}]
                      下级需同步的集合[{centerid=001, version=3}, {centerid=002, version=3}]
	 * @param uuid
	 * @return
	 */
	public List<List<HashMap<String,String>>> processAutoCommand(){
		HashMap<String, String> lowMap=new HashMap<String, String>();
		HashMap<String, String> lowMap2=new HashMap<String, String>();
		HashMap<String, String> lowMap3=new HashMap<String, String>();
		
		lowMap.put("centerid", "001");
		lowMap.put("version", "2");
		lowMap2.put("centerid", "002");
		lowMap2.put("version", "2");
		lowMap3.put("centerid", "003");
		lowMap3.put("version", "2");
		
		//得到下级的所有数据的版本
		List<HashMap<String, String>> serverLowlist = new ArrayList<HashMap<String, String>>();
		serverLowlist.add(lowMap);		
		serverLowlist.add(lowMap2);		
		serverLowlist.add(lowMap3);		
		
		HashMap<String, String> upMap1=new HashMap<String, String>();
		HashMap<String, String> upMap2=new HashMap<String, String>();
		HashMap<String, String> upMap3=new HashMap<String, String>();
		upMap1.put("centerid", "001");
		upMap1.put("version", "3");
		upMap2.put("centerid", "002");
		upMap2.put("version", "3");
		upMap3.put("centerid", "003");
		upMap3.put("version", "1");
		
		//得到上级所有数据的版本
		List<HashMap<String,String>> serverUplist= new ArrayList<HashMap<String, String>>();
		serverUplist.add(upMap1);
		serverUplist.add(upMap2);
		serverUplist.add(upMap3);
		
		
		
		List<HashMap<String,String>> lastLowlist=new ArrayList<HashMap<String,String>>();
		List<HashMap<String,String>> lastUplist=new ArrayList<HashMap<String,String>>();
		List<List<HashMap<String,String>>> lastList=new ArrayList<List<HashMap<String,String>>>();
		if(serverLowlist.size()>0){
          if(serverUplist.size()>0){
        	  //看下级在上级存在不
				for(int i=0;i<serverLowlist.size();i++){
					int index=LoadMessage.getInstance().getIndex(serverUplist, serverLowlist.get(i));
					System.out.println("index   "+index);

					if(index==-1){
						lastUplist.add(serverLowlist.get(i));
					}
					else{
						System.out.println("下级版本"+serverLowlist.get(i).get("version"));
						System.out.println("上级版本"+serverUplist.get(index).get("version"));
					 if(Integer.valueOf(serverLowlist.get(i).get("version"))>Integer.valueOf(serverUplist.get(index).get("version"))){
						System.out.println("index   下级大");
						lastUplist.add(serverLowlist.get(i));
					}
					}
				}
				
				//看上级在下级存在不
				for(int i=0;i<serverUplist.size();i++){
					int index=LoadMessage.getInstance().getIndex(serverLowlist, serverUplist.get(i));
					if(index==-1){
						lastLowlist.add(serverUplist.get(i));
					}
					else if(Integer.valueOf(serverLowlist.get(index).get("version"))<Integer.valueOf(serverUplist.get(i).get("version"))){
						lastLowlist.add(serverUplist.get(i));
					}
				}
			}else{
				lastUplist=serverLowlist;
				System.out.println("上级服务器上都没有数据!");
			}
		}else{
			if(serverUplist.size()>0){
				lastLowlist=serverUplist;
				System.out.println("下级服务器上都没有数据!");
			}else{
				System.out.println("服务器上都没有数据,无同步数据!");
			}
		}
		//上级需要向下级要在信息
		lastList.add(lastUplist);
		//下级需要向上级要在信息
		lastList.add(lastLowlist);
		return lastList;
	}
	
	 /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public  String readFileByLines(String path) {
    	//SAXReader read = new SAXReader();
//		String path  = "";
//		if (System.getProperty("os.name").equals("Linux")) // 操作系统名称
//		{
//			path = "/etc/sync"+centerid+version+".xml";
//		} else {
//			// System.out.println("==============="+System.getProperty("user.dir"));
//			path = "c:\\sync"+centerid+version+".xml";
//		}
    	if(("").equals(path)||null==path){
    		return null;
    	}
        File file = new File(path);
        BufferedReader reader = null;
        String result="";
        try {
        	//System.out.println("");
            //System.out.println("以行为单位读取文件内容，一次读一整行：");
          
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
        //System.out.println("------result-------"+result);
        return result;
    }

    
	public List<HashMap<String, String>> MergeLoadInfo(
			List<HashMap<String, String>> serverLoadList,
			List<HashMap<String, String>> clientLoadList) {
		List<HashMap<String, String>> lastLoadList = null;
		List<HashMap<String, String>> tempLoadList = new ArrayList<HashMap<String, String>>();
		if (serverLoadList != null) {
			logger.info("receive  " + "clientLoadList  " + clientLoadList);
			logger.info("receive  " + "serverLoadList  " + serverLoadList);
			tempLoadList = LoadMessage.getInstance().MergeMap(clientLoadList,
					tempLoadList);
			logger.info("receive  " + "tempLoadList1  " + tempLoadList);
			tempLoadList = LoadMessage.getInstance().MergeServerMap(
					serverLoadList, tempLoadList);
			logger.info("receive  " + "tempLoadList2  " + tempLoadList);
			lastLoadList = new ArrayList<HashMap<String, String>>();
			logger.info("receive  " + "lastLoadList1  " + lastLoadList);
			lastLoadList = LoadMessage.getInstance().getAllMap(tempLoadList,
					clientLoadList, serverLoadList);
			logger.info("receive  " + "lastLoadList2  " + lastLoadList);
		}

		return lastLoadList;
	}
	
	
	

	public static void main(String[] args) {
		List<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();//=LoadMessage.getInstance().getLoadVersionInfo();
		System.out.println(list.size());
		for(int k=0;k<list.size();k++){
			System.out.println(list.get(k));
		}
		
		
		List<List<HashMap<String,String>>> list2=LoadMessage.getInstance().processAutoCommand();
		System.out.println(list2.size());
		for(int k=0;k<list2.size();k++){
			System.out.println(list2.get(k));
		}
	}

}
