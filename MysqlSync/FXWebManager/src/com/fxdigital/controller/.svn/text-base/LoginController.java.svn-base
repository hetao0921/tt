package com.fxdigital.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


public class LoginController {
	private static final Logger log = Logger.getLogger(LoginController.class);
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
			bakpages="login";
		}
		return bakpages;
	}
}
