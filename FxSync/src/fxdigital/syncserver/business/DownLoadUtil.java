package fxdigital.syncserver.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fxdigital.syncserver.business.hibernate.dao.DataSourceDao;
import fxdigital.util.FileUtil;
import fxdigital.util.Log4jUtil;

public class DownLoadUtil{
	
	public static DownLoadUtil downLoadUtil = null;

	public static DownLoadUtil getInstance() {
		if (null == downLoadUtil) {
			downLoadUtil = new DownLoadUtil();
		}
		return downLoadUtil;
	}
	
	/**
	 * 拼接客户端
	 * 
	 * @param uuid
	 * @return
	 */
	public List<HashMap<String, String>> mergeMap(
			List<HashMap<String, String>> clientList,
			List<HashMap<String, String>> tempList) {

		for (int k = 0; k < clientList.size(); k++) {
			int theIndex = getIndex(tempList, clientList.get(k));
			if (theIndex == -1) {
				HashMap<String, String> tempmap = new HashMap<String, String>();
				tempmap.put("centerid", clientList.get(k).get("centerid"));
				tempmap.put("local", String.valueOf(k));
				tempmap.put("remote", "");
				tempList.add(tempmap);
			} else {
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
	public static int getIndex(List<HashMap<String, String>> clientList,
			HashMap<String, String> map2) {
		int index = -1;
		for (int i = 0; i < clientList.size(); i++) {
			if ((clientList.get(i).get("centerid".toLowerCase())).equals(map2
					.get("centerid".toLowerCase()))) {
				index = i;
			}
		}
		return index;

	}

	/**
	 * 拼接两个list
	 * 
	 * @param uuid
	 * @return
	 */
	public List<HashMap<String, String>> getAllMap(
			List<HashMap<String, String>> allList,
			List<HashMap<String, String>> clientList,
			List<HashMap<String, String>> serverList) {

		List<HashMap<String, String>> lastList = new ArrayList<HashMap<String, String>>();
		Log4jUtil.info(this.getClass(),"local1  " +allList);
		for (int i = 0; i < allList.size(); i++) {
			Log4jUtil.info(this.getClass(),"local2  " +allList);
			HashMap<String, String> temp = new HashMap<String, String>();
			temp.put("centerid", allList.get(i).get("centerid"));
			// 如果远程服务器上存在
			if (!allList.get(i).get("remote").equals("")) {
				int serverindex = Integer.valueOf(allList.get(i).get("remote"));
				temp.put("serverversion",
						serverList.get(serverindex).get("version"));
				temp.put("centername",
						serverList.get(serverindex).get("centername"));
				temp.put("centerip", serverList.get(serverindex)
						.get("centerip"));
			} else {
				temp.put("serverversion", "0");
			}
			Log4jUtil.info(this.getClass(),"local  " + temp.get("centerip")+" "+allList.get(i).get("local").equals(""));
			// 如果本地存在
			if (!allList.get(i).get("local").equals("")) {
				int clientindex = Integer.valueOf(allList.get(i).get("local"));
				temp.put("clientversion",
						clientList.get(clientindex).get("version"));

				if(("").equals(temp.get("centername"))||null==temp.get("centername")){
					temp.put("centername",
							clientList.get(clientindex).get("centername"));
				}
				if(("").equals(temp.get("centerip"))||null==temp.get("centerip")){
					temp.put("centerip",
					clientList.get(clientindex).get("centerip"));
				}
				temp.put("updatetime",
						clientList.get(clientindex).get("updatetime"));
				temp.put("flag", clientList.get(clientindex).get("flag"));
			} else {
				temp.put("clientversion", "0");
			}
			lastList.add(temp);
		}
		return lastList;
	}
	
	
	/**
	 * 拼接服务器端
	 * 
	 * @param uuid
	 * @return
	 */
	public static List<HashMap<String,String>> mergeServerMap(List<HashMap<String,String>> clientList,List<HashMap<String,String>> tempList){
		
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
	 * 增加是否可以下载字段
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getLastLoadInfos(
			List<HashMap<String, String>> list) {
		if (list.size() > 0 && null != list) {
			for (int i = 0; i < list.size(); i++) {
				int clientVersion = Integer.valueOf(list.get(i).get(
						"clientversion"));
				int serverVersion = Integer.valueOf(list.get(i).get(
						"serverversion"));
				if (clientVersion < serverVersion) {
					// 可以下载
					list.get(i).put("isload", "1");
				} else {
					// 不能下载
					list.get(i).put("isload", "0");
				}
			}
		}
		return list;
	}
	
	
	public List<HashMap<String, String>> getIncrementXml(String centerid,
			String version, String serverVersion) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		List<String> incrementVersion = getIncrementVersion(centerid, version,
				serverVersion);
		if (null != incrementVersion && incrementVersion.size() > 0) {
			for (int i = 0; i < incrementVersion.size(); i++) {
				String localVersion = incrementVersion.get(i);
				List<HashMap<String, String>> addressList = DataSourceDao
						.getInstance().getIncrementServerSource(centerid, localVersion);
				if (null != addressList && addressList.size() > 0) {
					HashMap<String, String> map = new HashMap<String, String>();
					String centerip = addressList.get(0).get("centerip");
					String centername = addressList.get(0).get("centername");
					String fileaddress = addressList.get(0).get("fileaddress");
					Log4jUtil.info(DownLoadUtil.class,"下载增量文件地址fileaddress   " + fileaddress);
					String xml = FileUtil.getInstance().readFileByLines(
							fileaddress);
					map.put("centerid", centerid);
					map.put("centerip", centerip);
					map.put("centername", centername);
					map.put("version", localVersion);
					map.put("xml", xml);
					list.add(map);
				}
			}
		}
		Log4jUtil.info(DownLoadUtil.class,"需要下载的增量信息：" + list);
		return list;
	}
	
	
	// 得到所有需要下载的增量版本
	public List<String> getIncrementVersion(String centerid, String version,
			String serverVersion) {
		Log4jUtil.info(DownLoadUtil.class,"客户端增量最大版本-----:" + centerid + "版本" + version);
		List<String> list = new ArrayList<String>();
		// 把增量的最大版本也传递过去
		if (null == version || ("").equals(version)) {
			version = "0";
		}
		for (int i = Integer.valueOf(version) + 1; i <= Integer
				.valueOf(serverVersion); i++) {
			list.add(String.valueOf(i));
		}
		Log4jUtil.info(DownLoadUtil.class,"所有需要下载增量版本-------:" + centerid + "版本 ---" + list);
		return list;
	}
}