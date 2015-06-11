package com.fxdigital.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fxdigital.manager.QueueImpl;

/**
 * 
 * @author hxht
 * @version 2014-11-4
 */
@Controller
public class LinkLocalIPController {
	
	private static final Logger log = Logger.getLogger(LinkLocalIPController.class);
	
	@Autowired
	private QueueImpl queueImpl;
	
	@RequestMapping(value="/linkLocalIp", method=RequestMethod.POST)
	public String entry(){
		return "linkLocalIp";
	}
	
	@RequestMapping(value="/getLocalIP" ,method=RequestMethod.POST)
	public void getLocalIP(HttpServletResponse response){
		PrintWriter writer = null;
		String result = null;
		try {
			writer = response.getWriter();
			result = queueImpl.getLocalIp();
		} catch (Exception e) {
			log.error("获得本级IP异常！",e);
			result = "获得本级IP异常！";
		} finally {
			if(writer != null){
				if(result == null){
					result = "获得本级IP失败！";
				}
				writer.print(result);
				writer.flush();
				writer.close();
			}
		}
	}
	
	@RequestMapping(value="/updateLocalIP" ,method=RequestMethod.POST)
	public void updateLocalIP(HttpServletRequest request, HttpServletResponse response){
		PrintWriter writer = null;
		String result = null;
		try {
			String localIP = request.getParameter("localIP");
			writer = response.getWriter();
			if(queueImpl.upId(localIP, "local", 0))
				result = "true";
			else
				result = "false";
		} catch (Exception e) {
			log.error("更新本级IP异常！",e);
			result = "更新本级IP异常！";
		} finally {
			if(writer != null){
				if(result == null){
					result = "更新本级IP失败！";
				}
				writer.print(result);
				writer.flush();
				writer.close();
			}
		}
	}
	
}
