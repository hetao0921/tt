package com.fxdigital.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.fxdigital.bean.DevInfoBean;
import com.fxdigital.manager.DevInfoManager;

/**
 * 
 * @author hxht
 * @version 2014-9-12
 */
@Controller
public class GroupConfController {
	
	private static final Logger log = Logger.getLogger(GroupConfController.class);
	
	@Autowired
	private DevInfoManager devInfoManager;
	
	@RequestMapping(value = "/groupConf", method = RequestMethod.POST)
	public String entry(){
		return "groupConf";
	}
	
	@RequestMapping(value = "/getDevInfos", method = RequestMethod.POST)
	public void getDevInfos(HttpServletRequest request, HttpServletResponse response){
		PrintWriter writer = null;
		String result = null;
		try {
			writer = response.getWriter();
			
			List<DevInfoBean> dibs = devInfoManager.getDevInfos();
			result = JSONObject.toJSONString(dibs);
			
		} catch (Exception e) {
			log.error("获得设备信息异常！", e);
			result = "获得设备信息异常！";
		} finally {
			if(writer != null){
				if(result == null){
					result = "获得设备信息失败！";
				}
				writer.print(result);
				writer.flush();
				writer.close();
			}
		}
	}
	
	@RequestMapping(value = "/getDevInfo", method = RequestMethod.POST)
	public void getDevInfo(HttpServletRequest request, HttpServletResponse response){
		PrintWriter writer = null;
		String result = null;
		try {
			writer = response.getWriter();
			
			Long id = Long.valueOf(request.getParameter("id"));
			DevInfoBean dib = devInfoManager.getDevInfo(id);
			if(dib != null){
				result = JSONObject.toJSONString(dib);
			}
			
		} catch (Exception e) {
			log.error("获得设备信息异常！", e);
			result = "获得设备信息异常！";
		} finally {
			if(writer != null){
				if(result == null){
					result = "获得设备信息失败！";
				}
				writer.print(result);
				writer.flush();
				writer.close();
			}
		}
	}
	
	@RequestMapping(value = "/addDev", method = RequestMethod.POST)
	public void addDev(HttpServletRequest request, HttpServletResponse response){
		PrintWriter writer = null;
		String result = null;
		try {
			writer = response.getWriter();
			
			String name = request.getParameter("name");
			String url = request.getParameter("url");
			String desc = request.getParameter("desc");
			DevInfoBean dib = new DevInfoBean();
			dib.setName(name);
			dib.setUrl(url);
			dib.setDesc(desc);
			if(devInfoManager.add(dib)){
				result = "true";
			}else{
				result = "false";
			}
			
		} catch (Exception e) {
			log.error("添加设备信息异常！", e);
			result = "添加设备信息异常！";
		} finally {
			if(writer != null){
				if(result == null){
					result = "添加设备信息失败！";
				}
				writer.print(result);
				writer.flush();
				writer.close();
			}
		}
	}
	
	@RequestMapping(value = "/editDev", method = RequestMethod.POST)
	public void editDev(HttpServletRequest request, HttpServletResponse response){
		PrintWriter writer = null;
		String result = null;
		try {
			writer = response.getWriter();
			
			Long id = Long.valueOf(request.getParameter("id"));
			String name = request.getParameter("name");
			String url = request.getParameter("url");
			String desc = request.getParameter("desc");
			DevInfoBean dib = new DevInfoBean();
			dib.setId(id);
			dib.setName(name);
			dib.setUrl(url);
			dib.setDesc(desc);
			if(devInfoManager.edit(dib)){
				result = "true";
			}else{
				result = "false";
			}
			
		} catch (Exception e) {
			log.error("修改设备信息异常！", e);
			result = "修改设备信息异常！";
		} finally {
			if(writer != null){
				if(result == null){
					result = "修改设备信息失败！";
				}
				writer.print(result);
				writer.flush();
				writer.close();
			}
		}
	}
	
	@RequestMapping(value = "/delDev", method = RequestMethod.POST)
	public void delDev(HttpServletRequest request, HttpServletResponse response){
		PrintWriter writer = null;
		String result = null;
		try {
			writer = response.getWriter();
			
			long id = Long.valueOf(request.getParameter("id"));
			if(devInfoManager.del(id)){
				result = "true";
			}else{
				result = "false";
			}
			
		} catch (Exception e) {
			log.error("删除设备信息异常！", e);
			result = "删除设备信息异常！";
		} finally {
			if(writer != null){
				if(result == null){
					result = "删除设备信息失败！";
				}
				writer.print(result);
				writer.flush();
				writer.close();
			}
		}
	}
	
}
