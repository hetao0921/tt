package com.fxdigital.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.fxdigital.manager.QueueImpl;
import com.mysql.jdbc.StringUtils;

/**
 * 
 * @author hxht
 * @version 2014-11-4
 */
@Controller
public class LinkUpIPController {
	
	private static final Logger log = Logger.getLogger(LinkUpIPController.class);
	
	@Autowired
	private QueueImpl queueImpl;
	
	@RequestMapping(value="/linkUpIp", method=RequestMethod.POST)
	public String entry(){
		return "linkUpIp";
	}
	
	@RequestMapping(value="/getLinkIP", method=RequestMethod.POST)
	public void getLinkIP(HttpServletResponse response){
		PrintWriter writer = null;
		String result = null;
		try {
			writer = response.getWriter();
			result = JSONObject.toJSONString(queueImpl.getParentIp());
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
	
	@RequestMapping(value="/updateLinkIP", method=RequestMethod.POST)
	public void updateLinkIP(HttpServletRequest request, HttpServletResponse response){
		PrintWriter writer = null;
		String result = null;
		try {
			writer = response.getWriter();
			int id = queueImpl.getMaxID() + 1;
			String re_id = request.getParameter("id");
			if(!StringUtils.isNullOrEmpty(re_id)){
				id = Integer.parseInt(re_id);
			}
			String centerIP = request.getParameter("centerIP");
			if(queueImpl.upId(centerIP, "", id))
				result = "true";
			else
				result = "false";
		} catch (Exception e) {
			log.error("更新级联IP异常！",e);
			result = "更新级联IP异常！";
		} finally {
			if(writer != null){
				if(result == null){
					result = "更新级联IP失败！";
				}
				writer.print(result);
				writer.flush();
				writer.close();
			}
		}
	}
	
	@RequestMapping(value="/deleteLinkIP", method=RequestMethod.POST)
	public void deleteLinkIP(HttpServletRequest request, HttpServletResponse response){
		PrintWriter writer = null;
		String result = null;
		try {
			writer = response.getWriter();
			String centerIP = request.getParameter("centerIP");
			if(queueImpl.delIp(centerIP))
				result = "true";
			else
				result = "false";
		} catch (Exception e) {
			log.error("删除级联IP异常！",e);
			result = "删除级联IP异常！";
		} finally {
			if(writer != null){
				if(result == null){
					result = "删除级联IP失败！";
				}
				writer.print(result);
				writer.flush();
				writer.close();
			}
		}
	}
	
}
