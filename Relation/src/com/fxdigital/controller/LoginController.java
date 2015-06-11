package com.fxdigital.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fxdigital.manager.LoginManager;

@Controller
public class LoginController {
	
	private static final Logger log = Logger.getLogger(LoginController.class);
	
	@Autowired
	private LoginManager loginManager;
	
	@RequestMapping(value={"/"})
	public String login(HttpServletRequest request){
		//设置session超时时间10分钟
		request.getSession().setMaxInactiveInterval(600);
		return "login";
	}
	
	@RequestMapping(value={"/checkLogin"},method=RequestMethod.POST)
	public void checkLogin(HttpServletRequest request,
			HttpServletResponse response){
		PrintWriter writer = null;
		try{
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			HttpSession session = request.getSession();
			session.setAttribute("username",username);
			session.setAttribute("password",password);
			
			writer = response.getWriter();
			if(loginManager.check(username, password))
				writer.print("true");
			else
				writer.print("false");
		}catch(Exception e){
			log.error("check login error!", e);
		}finally{
			if(writer != null){
				writer.flush();
				writer.close();
			}
		}
	}
}
