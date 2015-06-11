package com.fxdigital.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author hxht
 * @version 2014-9-1
 */
@Controller
public class ErrorController {
	
	private static final Logger log = Logger
			.getLogger(ErrorController.class);

	@RequestMapping(value = "/error")
	public String entry(HttpServletRequest request, HttpServletResponse response) {
		String message = request.getParameter("message");
		try {
			if (message.equals(new String(message.getBytes("iso8859-1"), "iso8859-1"))) {
				message = new String(message.getBytes("iso8859-1"), "utf-8");
			}
			log.error(message);
		} catch (UnsupportedEncodingException e) {
			message = "转码异常！";
			log.error(message, e);
		}
		request.setAttribute("message", message);
		return "error";
	}

}
