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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.fxdigital.db.dao.MqServerInfoDao;

import fxdigital.dbsync.domains.client.service.MsgClientService;

@Controller
public class DataBackUp {
	private static final Logger log = Logger.getLogger(DataBackUp.class);
	@Autowired
	MqServerInfoDao mqServerInfoDao;
	
	private static int TIMEOUTCOUNT=5;
	int count=0;
	//获取服务器上备份版本
	@RequestMapping(value = { "/getserverdata" }, method = RequestMethod.GET)
	public String getserverdata(ModelMap model,HttpServletRequest request,
			HttpServletResponse response) {
		log.info("进入getserverdata方法！！");
		int ncount = 1;
		count=0;
		String repage = null;
		List<Map<String, Object>> centerinfo = mqServerInfoDao.queryCenterID();
		log.info("获取本中心CenterId：" + centerinfo.get(0));
		
		// 发送获取服务器版本命令
		MsgClientService.getInstance().getResetVersionInfo(centerinfo);
		// 得到所有下载信息
		List<HashMap<String, String>> serverversion = null;
		while (true) {
			if (ncount < TIMEOUTCOUNT) {
				serverversion = MsgClientService.getInstance().getLastResetVersion();
//				log.info("count==========:" + ncount);
				if (serverversion != null) {
					String data = JSONObject.toJSONString(serverversion);
					log.info("第" + ncount + "收到所有下载信息：" + data);
					model.put("data", data);
					repage="syncviews/datarest";
					break;
				} else {
					ncount++;
					log.info("ncount：" + ncount);
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
	}
	
	@RequestMapping(value = { "/databackup" }, method = RequestMethod.POST)
	public String databackup(ModelMap model, HttpServletRequest request) {
		String key=request.getParameter("parm");
		model.put("key", key);
		return "syncviews/databackup";
	}
	
	@RequestMapping(value = { "/getserverinfo" }, method = RequestMethod.POST)
	public void getserverinfo(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("进入getserverinfo方法！！");
		int ncount = 1;
		count=0;
		List<Map<String, Object>> centerinfo = mqServerInfoDao.queryCenterID();
		log.info("获取本中心CenterId：" + centerinfo.get(0));
		PrintWriter out = null;
		// 发送获取服务器版本命令
		MsgClientService.getInstance().getResetVersionInfo(centerinfo);
		// 得到所有下载信息
		List<HashMap<String, String>> serverversion = null;
		while (true) {
			if (ncount < TIMEOUTCOUNT) {
				serverversion = MsgClientService.getInstance().getLastResetVersion();
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
					log.info("ncount：" + ncount);
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
	
	@RequestMapping(value = { "/isrest" }, method = RequestMethod.POST)
	public void isrest(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("进入isrest方法！！！");
		String parm=request.getParameter("centerid");
		log.info("parm:"+parm);
		String[] arrayStr=parm.split(",");
		String centerid=arrayStr[0];
		String centername=arrayStr[1];
		String version=arrayStr[2];
		PrintWriter out = null;
		// 发送还原命令
		String back =MsgClientService.getInstance().sendResetCommand(centerid,centername,version);
		try {
			out = response.getWriter();
			out.print(back);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
	
	//获取还原数据
	@RequestMapping(value = { "/downserverdata" }, method = RequestMethod.POST)
	public void downserverdata(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("进入downserverdata方法！！！");
		List<HashMap<String,String>> backvalue=null;
		String lastback=null;
		String isreset=null;
		PrintWriter out = null;
//		 back=-还原中，0还原成功，1还原失败
		boolean flag=true;
		while (flag) {
			isreset = MsgClientService.getInstance().getResetBack();
			log.info("执行还原后回馈isreset值:" + isreset);
			if(count<20){
			if (isreset.equals("0")) {
				backvalue=MsgClientService.getInstance().getResetResult("", "");
				String data = JSONObject.toJSONString(backvalue);
				log.info("成功后最后一次获取data:" + data+",count值："+count);
				lastback = "0_" + data;
				try {
					out = response.getWriter();
					log.info("lastback=========================:"+lastback);
					out.print(lastback);
					out.flush();
					out.close();
					flag=false;
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (isreset.equals("-1")) {
				count++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				log.info("还原次数count++:"+count);
				backvalue=MsgClientService.getInstance().getResetResult("", "");
				String data = JSONObject.toJSONString(backvalue);
				log.info("还原中获取data:" + data);
				// 拼接返回界面值
				lastback = "-1_" + data;
//				log.info("lastback=========================:"+lastback);
				try {
					out = response.getWriter();
					out.print(lastback);
					out.flush();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				backvalue=MsgClientService.getInstance().getResetResult("","");
				String data = JSONObject.toJSONString(backvalue);
				log.info("还原失败获取data:" + data);
				// 拼接返回界面值
				lastback = "1_" + data;
				try {
					out = response.getWriter();
					out.print(lastback);
					out.flush();
					out.close();
					flag=false;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else{
			log.info("还原失败！停止下载！");
			lastback = "2_同步服务器链接失败";
			try {
				out = response.getWriter();
				out.print(lastback);
				out.flush();
				out.close();
				flag=false;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		}
	}
	public Object[][] listToArray(List<HashMap<String,String>> list){
		Object[][] table=null;
		String centerid="";
		String centername="";
		String centerip="";
		String clientversion="";
		String version="";
		String update="";
//		String isload="";
		List<Object[]> datas = new ArrayList<Object[]>();
		if(list.size()>0){
		for (int i = 0; i < list.size(); i++) {
			Object[] array = new Object[6];
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
				
				
				version=list.get(i).get("version");
				if(version==null||version=="undefined"){
					version="";
				}
				
				update=list.get(i).get("update");
				if(update==null||update=="undefined"){
					update="";
				}
				
//				isload=list.get(i).get("isload");
//				if(isload==null||isload=="undefined"){
//					isload="";
//				}else if(isload=="1"){
//					isload="可以下载";
//				}else{
//				isload="不能下载";
//				}
				array[0]=centerid;
				array[1]=centername;
				array[2]=centerip;
				array[3]=clientversion;
				array[4]=version;
				array[5]=update;
			}
			datas.add(array);
		}
		//System.out.println("datas======"+datas);
		table=datas.toArray(new Object[datas.size()][]);
		}
		//System.out.println("table============"+table);
		return table;
	}
	
	public Object[][] listToArrayBack(List<HashMap<String,String>> list){
		Object[][] table=null;
		String uuid="";
		String uuname="";
		String startdate="";
		String version="";
		String enddate="";
		String resetState="";
//		String isload="";
		List<Object[]> datas = new ArrayList<Object[]>();
		if(list.size()>0){
		for (int i = 0; i < list.size(); i++) {
			Object[] array = new Object[6];
			for(int j=0;j<list.get(i).size();j++){
				uuid=list.get(i).get("uuid");
				if(uuid==null||uuid=="undefined"||"null".equals(uuid)){
					uuid="";
				}
				uuname=list.get(i).get("uuname");
				if(uuname==null||uuname=="undefined"||"null".equals(uuname)){
					uuname="";
				}
				
				startdate=list.get(i).get("startdate");
				if(startdate==null||startdate=="undefined"||"null".equals(startdate)){
					startdate="";
				}
				
				version=list.get(i).get("version");
				if(version==null||version=="undefined"||"null".equals(version)){
					version="";
				}
				
				
				enddate=list.get(i).get("enddate");
				if(enddate==null||enddate=="undefined"||"null".equals(enddate)){
					enddate="";
				}
				
				resetState=list.get(i).get("resetState");
				if(resetState==null||resetState=="undefined"||"null".equals(resetState)){
					resetState="";
				}
				
//				isload=list.get(i).get("isload");
//				if(isload==null||isload=="undefined"){
//					isload="";
//				}else if(isload=="1"){
//					isload="可以下载";
//				}else{
//				isload="不能下载";
//				}
				array[0]=uuid;
				array[1]=uuname;
				array[2]=startdate;
				array[3]=version;
				array[4]=enddate;
				array[5]=resetState;
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
