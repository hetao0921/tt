package com.fxdigital.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mysql.jdbc.StringUtils;

@Controller
public class HxController {
	
	@RequestMapping(value = "/gapIp", method = RequestMethod.POST)
	public String entry_gapIp(HttpServletRequest request) {
		return "gapIp";
	}
	@RequestMapping(value = "/webservice", method = RequestMethod.POST)
	public String entry_webservice(HttpServletRequest request) {
		return "webservice";
		}
		
		@RequestMapping(value = "/wsdlInfo", method = RequestMethod.POST)
		public String entry_wsdlInfo(HttpServletRequest request) {
			String wsdlURI=request.getParameter("wsdlURI");
			if(!StringUtils.isNullOrEmpty(wsdlURI)){
				request.setAttribute("wsdlURI", wsdlURI);
			}
			return "wsdlInfo";
	}
		@RequestMapping(value = "/transProtocol", method = RequestMethod.POST)
		public String entry_transProtocol(HttpServletRequest request) {
			return "transProtocol";
		}

}
