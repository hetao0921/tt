package com.fxdigital.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.fxdigital.db.dao.MqServerInfoDao;


 
/**
 * @author hxht
 */
@Controller
public class ShowDataController {
	private static final Logger log = Logger.getLogger(ShowDataController.class);
	@Autowired
	MqServerInfoDao mqServerInfoDao;

	//页面跳转
	@RequestMapping(value = { "/downloading" }, method = RequestMethod.GET)
	public String downloading(ModelMap model, HttpServletRequest request) {
		return "syncviews/downingstate";
	}
	@RequestMapping(value = { "/onloadfail" }, method = RequestMethod.GET)
	public String onloadfail(ModelMap model, HttpServletRequest request) {
		System.out.println("进入后台onloadfail拦截器");
		return "syncviews/onloadfail";
	}
	
	// 验证登陆
	@RequestMapping(value = { "/validate" }, method = RequestMethod.POST)
	public String show(ModelMap model, HttpServletRequest request) {
		log.info("进入validate函数!");
		String bakpages=null;
		String username = request.getParameter("j_username");
		String password = request.getParameter("j_password");
		log.info(username + ":" + password);
		if("admin".equals(username)&&"123456".equals(password)){
			model.put("versionnum", username);
			bakpages="main";
		}else{
			bakpages="index";
		}
		return bakpages;
	}

	// 显示本地版本和锁
	@RequestMapping(value = { "/showsyncnumber" }, method = RequestMethod.POST)
	public String showsync(ModelMap model, HttpServletRequest request) {
		log.info("进入showsyncnumber方法！！！");
		String key = request.getParameter("key");
		log.info("key:" + key);
		String[] subkey = key.split(",");
		log.info("sef:" + subkey[0]+",lock:" + subkey[1]);
		model.put("selfversion", subkey[0]);
		model.put("uplock", subkey[1]);
		return "syncviews/upload";
	}


	
	@RequestMapping(value = { "/getsyncinfo" }, method = RequestMethod.POST)
	public void getsyncinfo(HttpServletRequest request, HttpServletResponse response) {
		log.info("进入getsyncinfo方法！！！");
		PrintWriter out = null;
		List<Map<String,Object>> list=mqServerInfoDao.querySyncInfo();
		if(list.size()>0){
		String syncip=String.valueOf(list.get(0).get("MqIP"));
		String syncport=String.valueOf(list.get(0).get("MqPort"));
		String bakvalue=syncip+","+syncport;
		log.info(bakvalue);
		try {
			out= response.getWriter();
			out.print(bakvalue);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}else{
			try {
				out= response.getWriter();
				out.print("-1");
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping(value = { "/setsyncinfo" }, method = RequestMethod.POST)
	public void setsyncinfo(HttpServletRequest request, HttpServletResponse response) {
		log.info("进入setsyncinfo方法！！！");
		PrintWriter out = null;
		String syncip=request.getParameter("syncip");
		String syncport=request.getParameter("syncport");
		log.info("syncip:"+syncip+","+"syncport:"+syncport);
		int bak=mqServerInfoDao.updateSyncinfo(syncip, syncport);
		log.info("数据库返回的结果bak:"+bak);
		if(bak>0){
		try {
			out= response.getWriter();
			out.print(bak);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		}else{
			try {
				out= response.getWriter();
				out.print(bak);
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
