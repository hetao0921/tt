/**
 * 
 */
package com.fxdigital.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fxdigital.bean.DeviceModel;
import com.fxdigital.db.dao.DeviceModelDao;
import com.fxdigital.util.ShowXml;

/**
 * @author lizehua
 *
 */
@Controller
public class DeviceVisitModel {
	
	private static final Logger log = Logger.getLogger(DeviceVisitModel.class);
	@Autowired
	private DeviceModelDao deviceModelDao;
	@RequestMapping(value = { "/deviceVisitModel" }, method = RequestMethod.POST)
	public String enterdeviceVisitModel(HttpServletRequest request, HttpServletResponse response){

		try {
			String url = null;
			if (System.getProperty("os.name").equals("Linux")) {
				url = "/etc/fxconf/streamfoward/StreamFoward.conf";
			} else {
				url = "D:/fxconf/streamfoward/StreamFoward.conf";
			}
			String str = ShowXml.showXml(url);
			response.setContentType("text/html;charset=UTF-8");/* 设定编码和返回类型 */

			request.setCharacterEncoding("UTF-8");
			/* 设定参数编码 */
			if(!"0".equals(str)&&!"1".equals(str)&&!"2".equals(str)){
				str="-1";
			}
			System.out.println(str);
			request.setAttribute("model", str);
			
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		return "deviceVisitModel";
	}
	@RequestMapping(value = { "/getDeviceVisitModel" }, method = RequestMethod.POST)
	
	public  void getDeviceVisitModel(HttpServletRequest request, HttpServletResponse response){
		PrintWriter writer = null;
		String result = null;
		try{
			String devName=request.getParameter("devName");
			String IP=request.getParameter("devIP");
			String modelName=request.getParameter("modelName");
			String type=request.getParameter("type");
			writer = response.getWriter();
			List<DeviceModel> list=	deviceModelDao.getDeviceModel(devName,IP,modelName,type);
			result = JSONObject.toJSONString(list);
		}catch (Exception e) {

			log.error("获得设备信息异常！", e);
			result = "获得数据异常！";
		
		} finally {
			if(writer != null){
				if(result == null){
					result = "获得数据异常！";
				}
				writer.print(result);
				writer.flush();
				writer.close();
			}
		}
	}
	
	

	@RequestMapping(value = { "/addModel" }, method = RequestMethod.POST)
	public void add(HttpServletRequest request, HttpServletResponse response){
		PrintWriter writer = null;
		String result = "true";
		String deviceid=request.getParameter("deviceId");
		String modelType=request.getParameter("netlinkmode");
		try {
			
			writer = response.getWriter();
		deviceModelDao.setModel(deviceid, modelType);
		} catch (Exception e) {
			result="false";
		}
		finally {
			if(writer != null){
				if(result == null){
					result = "获得数据异常！";
				}
				writer.print(result);
				writer.flush();
				writer.close();
			}
		}
	}
	@RequestMapping(value = { "/delModel" }, method = RequestMethod.POST)
	public void delModel(HttpServletRequest request, HttpServletResponse response){
		PrintWriter writer = null;
		String result = "true";
		String deviceid=request.getParameter("deviceid");
		try {
			
			writer = response.getWriter();
			deviceModelDao.deleteModelBydeviceId(deviceid);
		} catch (Exception e) {
			result="false";
		}
		finally {
			if(writer != null){
				if(result == null){
					result = "获得数据异常！";
				}
				writer.print(result);
				writer.flush();
				writer.close();
			}
		}
	}
	
	

}
