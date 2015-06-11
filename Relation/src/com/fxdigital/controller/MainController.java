package com.fxdigital.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.fxdigital.db.pojo.MenuSyncInfo;
import com.fxdigital.manager.DbManager;
import com.fxdigital.manager.LoginManager;

/**
 * 
 * @author fucz
 * @version 2014-8-12
 */
@Controller
public class MainController {
	
	private static final Logger log = Logger.getLogger(MainController.class);
	
	@Autowired
	private DbManager dbManager;
	@Autowired
	private LoginManager loginManager;
	
	@RequestMapping(value = {"/main"})
	public String entry(HttpServletRequest request){
		try {
			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			String password = (String) session.getAttribute("password");
			if(loginManager.check(username, password)){
				return "main";
			}
		} catch (Exception e) {
			log.error("enter main error!", e);
		}
		return "login";
	}
	
	@RequestMapping(value = {"/getMenu"}, method = RequestMethod.GET)
	public void getMenu(HttpServletResponse response){
		PrintWriter out = null;
		List<MenuSyncInfo> menus = dbManager.getMenuInfo();
		try {
			out = response.getWriter();
			out.print(JSONObject.toJSONString(menus));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = {"/logout"})
	public String logout(HttpServletRequest request){
		try {
			request.getSession().invalidate();
		} catch (Exception e) {
			log.error("logout error!", e);
		}
		return "login";
	}
	
}
