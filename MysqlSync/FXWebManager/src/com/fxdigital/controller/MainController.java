package com.fxdigital.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.fxdigital.db.pojo.MenuInfo;
import com.fxdigital.manager.DbManager;

/**
 * 
 * @author fucz
 * @version 2014-8-12
 */
@Controller
public class MainController {
	
	@Autowired
	private DbManager dbManager;
	
	@RequestMapping(value = {"/"})
	public String entry(){
		return "main";
	}
	
	@RequestMapping(value = {"/getMenu"}, method = RequestMethod.GET)
	public void getMenu(HttpServletResponse response){
		PrintWriter out = null;
		List<MenuInfo> menus = dbManager.getMenuInfo();
		try {
			out = response.getWriter();
			out.print(JSONObject.toJSONString(menus));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
