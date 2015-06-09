package com.fxdigital.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.fxdigital.db.dao.MqServerInfoDao;

import fxdigital.dbsync.domains.client.service.MsgClientService;


@Controller
public class Loginfo {
	private static final Logger log = Logger.getLogger(Loginfo.class);
	@Autowired
	MqServerInfoDao mqServerInfoDao;
	@RequestMapping(value = { "/getloginfo" }, method = RequestMethod.POST)
	public void getloginfo(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("进入getloginfo方法！！");
		List<Map<String, Object>> centerinfo=mqServerInfoDao.queryCenterID();
		List<HashMap<String, String>> loginfos=null;
		String centerid=null;
		if(centerinfo!=null&&centerinfo.size()>0){
			centerid=(String) centerinfo.get(0).get("CenterID");
			log.info("centerid:"+centerid);
		}
		loginfos=MsgClientService.getInstance().getClientLogsInfo(centerid);
		System.out.println("前数据loginfos:"+JSONObject.toJSONString(loginfos));
		Object[][] tables=listToArray(loginfos);
		String backvalue=JSONObject.toJSONString(tables);
		log.info("loginfos条数："+loginfos.size()+";backvalue:"+backvalue);
		try {
			PrintWriter out = response.getWriter();
			out.print(backvalue);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = { "/getdetaillog" }, method = RequestMethod.POST)
	public void getdetaillog(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("进入getdetaillog方法！！");
		List<Map<String, Object>> centerinfo=mqServerInfoDao.queryCenterID();
		List<HashMap<String, String>> loginfos=null;
		String centerid=null;
		String operateinfo=null;
		if(centerinfo!=null&&centerinfo.size()>0){
			centerid=(String) centerinfo.get(0).get("CenterID");
			log.info("centerid:"+centerid);
		}
		loginfos=MsgClientService.getInstance().getClientLogsInfo(centerid);
		String logs="";
		log.info("loginfos条数:"+loginfos.size());
		for(int i=0;i<loginfos.size();i++){
			String log="";
			String operate=loginfos.get(i).get("operate");
			String operatetime=loginfos.get(i).get("operatetime");
			String centername=loginfos.get(i).get("centername");
			String centerids=loginfos.get(i).get("centerid");
			String errorinfo=loginfos.get(i).get("errorinfo");
			operateinfo=loginfos.get(i).get("operateinfo");
			String[] infos=operateinfo.split(";");
			for(int j=0;j<infos.length;j++){
				String[] tempinfo=infos[j].split(":");
				log="中心ID:"+centerids+"["+centername+"]在"+operatetime+"执行了"+operate+"操作，详细操作内容{中心ID:"+tempinfo[0]+"版本:"+tempinfo[1]+",操作状态:"+tempinfo[2]+"},错误标识:"+errorinfo+"\r\n";
			}
			logs+=log;
		}
		try {
			PrintWriter out = response.getWriter();
			out.print(logs);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Object[][] listToArray(List<HashMap<String,String>> list){
		Object[][] table=null;
		List<Object[]> datas = new ArrayList<Object[]>();
		if(list.size()>0){
		for (int i = 0; i < list.size(); i++) {
			Object[] array = new Object[6];
			for(int j=0;j<list.get(i).size();j++){
				array[0]=list.get(i).get("centerid");
				array[1]=list.get(i).get("centername");
				array[2]=list.get(i).get("operatetime");
				array[3]=list.get(i).get("operate");
				array[4]=list.get(i).get("operateinfo");
				array[5]=list.get(i).get("errorinfo");
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
