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
import com.fxdigital.bean.TableData;
import com.fxdigital.manager.UserLogManager;

/**
 * 
 * @author hxht
 * @version 2014-9-11
 */
@Controller
public class UserLogController {
	
	private static final Logger log = Logger.getLogger(UserLogController.class);
	
	@Autowired
	private UserLogManager userLogManager;

	@RequestMapping(value = "/userLog", method = RequestMethod.POST)
	public String entry() {
		return "userLog";
	}
	
	@RequestMapping(value = "/getType", method = RequestMethod.POST)
	public void getType(HttpServletRequest request, HttpServletResponse response){
		PrintWriter writer = null;
		String result = null;
		try {
			writer = response.getWriter();
			List<String> types = userLogManager.getLogType();
			result = JSONObject.toJSONString(types);
		} catch (Exception e) {
			log.error("获得用户日志类型异常！", e);
			result = "false";
		} finally {
			if(writer != null){
				if(result == null){
					result = "false";
				}
				writer.print(result);
				writer.flush();
				writer.close();
			}
		}
	}
	
	@RequestMapping(value = "/getUserLog", method = RequestMethod.POST)
	public void getUserLog(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter writer = null;
		String result = null;
		try {
			writer = response.getWriter();
			String _search = request.getParameter("_search");
			int page = Integer.parseInt(request.getParameter("page"));
			int rows = Integer.parseInt(request.getParameter("rows"));
			String sidx = request.getParameter("sidx");
			String sord = request.getParameter("sord");
			String s_startLogTime = null;
			String s_endLogTime = null;
			String s_type = null;
			String s_userID = null;
			String s_clientID = null;
			if("true".equals(_search)){
				s_startLogTime = request.getParameter("startLogTime");
				s_endLogTime = request.getParameter("endLogTime");
				s_type = request.getParameter("type");
				String s_userName = request.getParameter("userName");
				s_userID = userLogManager.getUserID(s_userName);
				s_clientID = request.getParameter("clientID");
			}
			TableData results = userLogManager.getUserLog(
					s_startLogTime, s_endLogTime, s_type, s_userID,
					s_clientID, page, rows, sidx, sord);
			TableData tableData = new TableData();
			tableData.setPage(page);
			tableData.setTotal((int) Math.ceil(results.getRecords()/(rows*1.0)));
			tableData.setRecords(results.getRecords());
			tableData.setRows(results.getRows());
			result = JSONObject.toJSONString(tableData);
		} catch (Exception e) {
			log.error("获得用户日志异常！", e);
		} finally {
			if(writer != null){
				if(result == null){
					result = "获得用户日志失败！";
				}
				writer.print(result);
				writer.flush();
				writer.close();
			}
		}
	}
	
	@RequestMapping(value = "/getAllType", method = RequestMethod.POST)
	public void getAllType(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter writer = null;
		String result = null;
		try {
			writer = response.getWriter();
		} catch (Exception e) {
			log.error("获得所有日志类型异常！", e);
			result = "获得所有日志类型异常！";
		} finally {
			if(writer != null){
				if(result == null){
					result = "获得所有日志类型失败！";
				}
				writer.print(result);
				writer.flush();
				writer.close();
			}
		}
	}
	
}
