package com.fxdigital.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.impl.NewAddImpl;

/**
 * 
 * @author hxht
 * @version 2014-9-5
 */
@Controller
public class RebootController {
	
	private static final Logger log = Logger.getLogger(RebootController.class);

	@RequestMapping(value = "/reboot", method = RequestMethod.POST)
	public String entry() {
		return "reboot";
	}

	@RequestMapping(value = "/runReboot", method = RequestMethod.POST)
	public void reboot(HttpServletRequest request,HttpServletResponse response) {
		PrintWriter writer = null;
		String result = null;
		try {
			writer = response.getWriter();
			NewAddImpl newimpl = NewAddImpl.getNewAddInstance();
			newimpl.shutDevice();
			result = "true";
		} catch (Exception e) {
			log.error("重启异常！", e);
			result = "重启异常！";
		}finally{
			if (writer != null) {
				if(result == null){
					result = "重启异常！";
				}
				writer.print(result);
				writer.flush();
				writer.close();
			}
		}
	}

}
