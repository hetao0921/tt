package com.fxdigital.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fxdigital.db.dao.MqServerInfoDao;

import fxdigital.dbsync.domains.client.service.MsgClientService;


@Controller
public class DownLoad {
	private static final Logger log = Logger.getLogger(DownLoad.class);
	@Autowired
	MqServerInfoDao mqServerInfoDao;
	@Autowired
	HttpSession session;

	// 下载返回值详细
	List<HashMap<String, List<HashMap<String, String>>>> detaillist = null;
	// 获取界面值
	String setvalue = null;
	// 中心集合
	List<String> centeridlist = null;
	// 下载是否结束
	String isdownend = "1";
	//获取下载状态计时
	int count=0;
	//获取详细状态计时
	int counts=0;
	// 是否可以下载
	@RequestMapping(value = { "/isdownload" }, method = RequestMethod.POST)
	public void isdownload(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		count=0;
		PrintWriter out = null;
		log.info("进入isdownload方法！！！");
		List<Map<String, Object>> centerinfo = mqServerInfoDao.queryCenterID();
		log.info("获取本中心CenterId：" + centerinfo.get(0));
		setvalue = request.getParameter("json");
		List<HashMap<String, String>> loadList = jsontolist(setvalue);
		String isdown = MsgClientService.getInstance().sendLoadCommand(
				loadList, centerinfo);
		log.info("isdown:" + isdown);
		try {
			out = response.getWriter();
			out.print(isdown);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 获取锁
	@RequestMapping(value = { "/getdownlock" }, method = RequestMethod.POST)
	public void getdownlock(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("进入getdownlock方法！");
		String backvalue = null;
		String[] downlock = MsgClientService.getInstance().getLoadLock();
		String bak = downlock[2];
		session.removeAttribute("downlockid");
		session.setAttribute("downlockid", downlock[0]);
		log.info("获取downlock:" + bak + ",downlockid:" + downlock[0]);
		if (bak.equals("0")) {
			backvalue = "0";
		} else {
			backvalue = "1";
		}
		try {
			PrintWriter out = response.getWriter();
			out.print(backvalue);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// 获取所有下载版本信息
	@RequestMapping(value = { "/getdownversion" }, method = RequestMethod.POST)
	public String getdownversion(ModelMap model, HttpServletRequest request,HttpServletResponse response) {
		log.info("进入getdownversion方法！！！");
		int ncount = 1;
//		PrintWriter out = null;
		String repage = null;
		List<Map<String, Object>> centerinfo = mqServerInfoDao.queryCenterID();
		log.info("获取本中心CenterId：" + centerinfo.get(0));
		//获取最新锁Id
		String[] downlock = MsgClientService.getInstance().getLoadLock();
		session = request.getSession();
		session.removeAttribute("downlockid");
		session.setAttribute("downlockid", downlock[0]);
		// 发送获取本地版本命令
		List<HashMap<String, String>> localversion = MsgClientService
				.getInstance().getLoadVersionInfo(centerinfo);
		// 得到所有下载信息
		List<HashMap<String, String>> serverversion = null;
		while (true) {
			if (ncount < 5) {
				serverversion = MsgClientService.getInstance()
						.getMergeLoadVersion();
				if (serverversion != null) {
					String data = JSONObject.toJSONString(listToArray(serverversion));
					log.info("第" + ncount + "收到所有下载信息：" + data);
					model.put("data", data);
					repage="syncviews/downrecord";
					break;
				} else {
					ncount++;
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} else {
				repage="syncviews/onloadfail";
				break;
			}
		}
		return repage;
		// List<HashMap<String, String>> list = new ArrayList<HashMap<String,
		// String>>();
		// for (int i = 0; i <0; i++) {
		// HashMap<String, String> map2 = new HashMap<String, String>();
		// map2.put("centerid", "li@001");
		// map2.put("centername", "lch");
		// map2.put("centerip", "192.168.1.102");
		// map2.put("clientversion", "4");
		// map2.put("serverversion", "5");
		// map2.put("update", "20301102");
		// map2.put("flag", "yes");
		// list.add(map2);
		// }
	}
	
	@RequestMapping(value = { "/getdowninfo" }, method = RequestMethod.POST)
	public void getdowninfo(HttpServletRequest request,HttpServletResponse response) {
		log.info("进入getdowninfo方法！！！");
		int ncount = 1;
		PrintWriter out = null;
		List<Map<String, Object>> centerinfo = mqServerInfoDao.queryCenterID();
		log.info("获取本中心CenterId：" + centerinfo.get(0));
		//获取最新锁Id
		String[] downlock = MsgClientService.getInstance().getLoadLock();
		session = request.getSession();
		session.removeAttribute("downlockid");
		session.setAttribute("downlockid", downlock[0]);
		// 发送获取本地版本命令
		List<HashMap<String, String>> localversion = MsgClientService
				.getInstance().getLoadVersionInfo(centerinfo);
		// 得到所有下载信息
		List<HashMap<String, String>> serverversion = null;
		while (true) {
			if (ncount < 5) {
				serverversion = MsgClientService.getInstance()
						.getMergeLoadVersion();
				if (serverversion != null) {
					String data = JSONObject.toJSONString(listToArray(serverversion));
					log.info("第" + ncount + "收到所有下载信息：" + data);
					try {
						out = response.getWriter();
						out.print(data);
						out.flush();
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				} else {
					ncount++;
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} else {
				try {
					out = response.getWriter();
					out.print("1");
					out.flush();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}
	
	// 获取下载状态
	@RequestMapping(value = { "/getdownstate" }, method = RequestMethod.POST)
	public void getdownstate(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("进入getdownstate方法！！！");
		// 返回界面的实际值
		String lastback = null;
		// 底层实际返回的值
		List<HashMap<String, String>> back = null;
		// 解析返回界面，一个中心的最后一个值
		List<HashMap<String, String>> lastcentervalue = null;
		// 获取发送参数
		List<HashMap<String, String>> loadList = jsontolist(setvalue);
		centeridlist = getCenteridlist(setvalue);
		PrintWriter out = null;
		// back=-1上传中，0上传成功，1上传失败
		while (true) {
			if(count<50){
			isdownend = MsgClientService.getInstance().getLoadBack();
			log.info("执行下载后回馈isdownend值:" + isdownend);
			if (isdownend.equals("0")) {
				// 发送解锁指令
				String downlockid = (String) session.getAttribute("downlockid");
				log.info("下载完成，发送解锁指令downlockid:" + downlockid);
				MsgClientService.getInstance().unLoadLock(downlockid);
				// 数据转换，适应界面需求
				back = MsgClientService.getInstance().getLoadStateInfos(
						loadList);
				detaillist = getdetailList(back, centeridlist);
				lastcentervalue = getlastlist(detaillist, centeridlist);
			//	String data = JSONObject.toJSONString(lastcentervalue);
				String data=getAccordionData(lastcentervalue,detaillist);
				log.info("成功后最后一次获取data:" + data);
				// 拼接返回界面值
				lastback = "0_" + data;
				try {
					count=0;
					out = response.getWriter();
					out.print(lastback);
					out.flush();
					out.close();
					break;
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (isdownend.equals("-1")) {
				count++;
				// 数据转换，适应界面需求
				back = MsgClientService.getInstance().getLoadStateInfos(
						loadList);
				detaillist = getdetailList(back, centeridlist);
				lastcentervalue = getlastlist(detaillist, centeridlist);
				String data = JSONObject.toJSONString(lastcentervalue);
				log.info("下载中获取data:" + data);
				// 拼接返回界面值
				lastback = "-1_" + data;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				try {
					out = response.getWriter();
					out.print(lastback);
					out.flush();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				// 数据转换，适应界面需求
				back = MsgClientService.getInstance().getLoadStateInfos(
						loadList);
				detaillist = getdetailList(back, centeridlist);
				lastcentervalue = getlastlist(detaillist, centeridlist);
				String data = JSONObject.toJSONString(lastcentervalue);
				log.info("下载失败获取data:" + data);
				// 拼接返回界面值
				lastback = "1_" + data;
				try {
					out = response.getWriter();
					out.print(lastback);
					out.flush();
					out.close();
					break;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else{
			lastback = "2_同步服务器链接失败！";
			try {
				out = response.getWriter();
				out.print(lastback);
				out.flush();
				out.close();
				break;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		}
	}

	// 获取下载详细状态
	@RequestMapping(value = { "/getcenterdownstate" }, method = RequestMethod.POST)
	public void getcenterdownstate(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("进入getcenterdownstate方法！！！");
		String centerid=request.getParameter("centerid");
		log.info("centerid:"+centerid);
		// 返回界面的实际值
		String lastback = null;
		// 内存实际返回的值
		List<HashMap<String, String>> back = getlastlistbyid(detaillist, centerid);
		
		PrintWriter out = null;
		// back=-1上传中，0上传成功，1上传失败
//		while (true) {
//			isdownend = MsgClientService.getInstance().getLoadBack();
//			log.info("执行下载后回馈isdownend值:" + isdownend);
//			if(counts<50){
//			if (isdownend.equals("0")) {
				// 发送解锁指令
//				String downlockid = (String) session.getAttribute("downlockid");
//				log.info("下载完成，发送解锁指令downlockid:" + downlockid);
//				MsgClientService.getInstance().unLoadLock(downlockid);
				// 数据转换，适应界面需求
				back = getlastlistbyid(detaillist, centerid);
				String data = JSONObject.toJSONString(back);
				log.info("点击详细按钮这一刻获取内存data:" + data);
				// 拼接返回界面值
				lastback = "0_" + data;
				try {
//					counts=0;
					out = response.getWriter();
					out.print(lastback);
					out.flush();
					out.close();
//					break;
				} catch (IOException e) {
					e.printStackTrace();
				}
//			} else if (isdownend.equals("-1")) {
//				counts++;
//				// 数据转换，适应界面需求
//				 back = getlastlistbyid(detaillist, centerid);
//					String data = JSONObject.toJSONString(back);
//				log.info("下载中获取data:" + data);
//				// 拼接返回界面值
//				lastback = "-1_" + data;
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e1) {
//					e1.printStackTrace();
//				}
//				try {
//					out = response.getWriter();
//					out.print(lastback);
//					out.flush();
//					out.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			} else {
//				// 数据转换，适应界面需求
//				 back = getlastlistbyid(detaillist, centerid);
//				String data = JSONObject.toJSONString(back);
//				log.info("下载失败获取data:" + data);
//				// 拼接返回界面值
//				lastback = "1_" + data;
//				try {
//					out = response.getWriter();
//					out.print(lastback);
//					out.flush();
//					out.close();
//					break;
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}else{
//			log.info("下载失败！停止下载！");
//			lastback = "2_同步服务器链接失败";
//			try {
//				out = response.getWriter();
//				out.print(lastback);
//				out.flush();
//				out.close();
//				break;
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		}
	}


	// 解锁
	@RequestMapping(value = { "/undownlock" }, method = RequestMethod.POST)
	public void undownlock(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("进入undownlock方法！！！");
		String downlockid = (String) session.getAttribute("downlockid");
		log.info("downlockid:" + downlockid);
		String unreslut = MsgClientService.getInstance().unLoadLock(downlockid);
		try {
			PrintWriter out = response.getWriter();
			out.print(unreslut);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<HashMap<String, String>> jsontolist(String json) {
		log.info("从界面获取参数json：" + json);
		JSONArray jsono = JSONObject.parseArray(json);
		log.info("解析size:" + jsono.size());
		List<HashMap<String, String>> loadList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> tempMap = null;
		for (int i = 0; i < jsono.size(); i++) {
			tempMap = new HashMap<String, String>();
			JSONObject object = (JSONObject) jsono.get(i);
			String centerid = object.getString("centerid");
			String centername = object.getString("centername");
			String centerip = object.getString("centerip");
			String clientversion = object.getString("clientversion");
			String serverversion = object.getString("serverversion");
			String update = object.getString("update");
			String flag = object.getString("flag");

			tempMap.put("centerid", centerid);
			tempMap.put("centername", centername);
			tempMap.put("centerip", centerip);
			tempMap.put("clientversion", clientversion);
			tempMap.put("serverversion", serverversion);
			tempMap.put("update", update);
			tempMap.put("flag", flag);
			loadList.add(tempMap);
		}

		System.out
				.println("封装loadlist条数:" + loadList.size() + "内容：" + loadList);
		return loadList;
	}

	public List<String> getCenteridlist(String json) {
		JSONArray jsono = JSONObject.parseArray(json);
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < jsono.size(); i++) {
			JSONObject object = (JSONObject) jsono.get(i);
			String centerid = object.getString("centerid");
			log.info("json中的centerid:" + centerid);
			list.add(centerid);
		}
		return list;
	}

	public List<HashMap<String, List<HashMap<String, String>>>> getdetailList(
			List<HashMap<String, String>> list, List<String> centeridlist) {
		List<HashMap<String, List<HashMap<String, String>>>> listbak = new ArrayList<HashMap<String, List<HashMap<String, String>>>>();
		;
		HashMap<String, List<HashMap<String, String>>> map = null;
		List<HashMap<String, String>> templist = null;
		if (centeridlist.size() > 0 && null != centeridlist) {
			for (int i = 0; i < centeridlist.size(); i++) {
				map = new HashMap<String, List<HashMap<String, String>>>();
				templist = new ArrayList<HashMap<String, String>>();
				for (int j = 0; j < list.size(); j++) {
					String outcenterid = centeridlist.get(i);
					String incenterid = list.get(j).get("centerid");
					if (outcenterid.equals(incenterid)) {
						templist.add(list.get(j));
					}
				}
				map.put(centeridlist.get(i), templist);
				listbak.add(map);
			}
		}
		return listbak;
	}

	public List<HashMap<String, String>> getlastlist(
			List<HashMap<String, List<HashMap<String, String>>>> detaillist,
			List<String> centeridlist) {
		List<HashMap<String, String>> lastlist = null;
		if (centeridlist.size() > 0 && null != centeridlist) {
			lastlist = new ArrayList<HashMap<String, String>>();
			for (int i = 0; i < centeridlist.size(); i++) {
				for (int j = 0; j < detaillist.size(); j++) {
					String centerid = centeridlist.get(i);
					if (detaillist.get(j).get(centerid) != null
							&& detaillist.get(j).get(centerid).size() > 0) {
						int size = detaillist.get(j).get(centerid).size();
						lastlist.add(detaillist.get(j).get(centerid)
								.get(size - 1));
					}
				}
			}
		}
		return lastlist;
	}

	// 根据centerid查找detaillist所有值
	public List<HashMap<String, String>> getlastlistbyid(
			List<HashMap<String, List<HashMap<String, String>>>> detaillist,
			String centerid) {
		List<HashMap<String, String>> lastlist = null;
		lastlist = new ArrayList<HashMap<String, String>>();
		for (int j = 0; j < detaillist.size(); j++) {
			if (detaillist.get(j).get(centerid) != null
					&& detaillist.get(j).get(centerid).size() > 0) {
				lastlist = detaillist.get(j).get(centerid);
			}
		}
		return lastlist;
	}
	
	
	public String getAccordionData(List<HashMap<String, String>> list,List<HashMap<String, List<HashMap<String, String>>>> detaillist){
		String str=new String();
		String head="";
		List<HashMap<String, String>> lastdetail=null;
		if(list.size()>0&&null!=list){
			for(int i=0;i<list.size();i++){
				String centerid=list.get(i).get("centerid");
				String downenddate=list.get(i).get("downenddate");
				String state=list.get(i).get("state");
				String version=list.get(i).get("version");
				head=centerid+"在"+downenddate+"时下载版本"+version+"，下载状态："+state;
				lastdetail=new ArrayList<HashMap<String, String>>();
				lastdetail = getlastlistbyid(detaillist,centerid);
				String content="";
				for(int j=0;j<lastdetail.size();j++){
					String dcenterid=lastdetail.get(j).get("centerid");
					String dcentername=lastdetail.get(j).get("centername");
					String ddownenddate=lastdetail.get(j).get("downenddate");
					String dstate=lastdetail.get(j).get("state");
					String ddownstartdate=lastdetail.get(j).get("downstartdate");
					String dversion=lastdetail.get(j).get("version");
					content+=dcenterid+"["+dcentername+"]于"+ddownstartdate+"开始从同步服务器上下载"+dversion+"版本，于"+ddownenddate+"时下载状态：["+"("+j+")"+dstate+"]。\r\n";
				}
			//	String content="<div class='accrodionContent'><a target='mainFrame' href='" + rr.Node.NodeURL + "'>" + rr.Node.DisplayName + "</a></div>";
				str+= "<div id=\"accrodionHead\">" + head + "</div>"+"\r\n"+"<div id=\"accrodionContent\">" + content + "</div>"+"\r\n";
			}
		}
		return str;
	}
	
	public Object[][] listToArray(List<HashMap<String,String>> list){
		Object[][] table=null;
		String centerid="";
		String centername="";
		String centerip="";
		String clientversion="";
		String serverversion="";
		String update="";
		String isload="";
		List<Object[]> datas = new ArrayList<Object[]>();
		if(list.size()>0){
		for (int i = 0; i < list.size(); i++) {
			Object[] array = new Object[7];
			for(int j=0;j<list.get(i).size();j++){
				centerid=list.get(i).get("centerid");
				if(centerid==null||centerid=="undefined"){
					centerid="";
				}
				centername=list.get(i).get("centername");
				if(centername==null||centername=="undefined"){
					centername="";
				}
				
				centerip=list.get(i).get("centerip");
				if(centerip==null||centerip=="undefined"||"null".equals(centerip)){
					centerip="";
				}
				
				clientversion=list.get(i).get("clientversion");
				if(clientversion==null||clientversion=="undefined"){
					clientversion="";
				}
				
				
				serverversion=list.get(i).get("serverversion");
				if(serverversion==null||serverversion=="undefined"){
					serverversion="";
				}
				
				update=list.get(i).get("update");
				if(update==null||update=="undefined"){
					update="";
				}
				
				isload=list.get(i).get("isload");
				if(isload==null||isload=="undefined"){
					isload="";
				}else if(isload=="1"){
					isload="可以下载";
				}else{
				isload="不能下载";
				}
				array[0]=centerid;
				array[1]=centername;
				array[2]=centerip;
				array[3]=clientversion;
				array[4]=serverversion;
				array[5]=update;
				array[6]=isload;
			}
			datas.add(array);
		}
		//System.out.println("datas======"+datas);
		table=datas.toArray(new Object[datas.size()][]);
		}
		//System.out.println("table============"+table);
		return table;
	}
}
