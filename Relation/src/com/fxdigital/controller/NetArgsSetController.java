package com.fxdigital.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fxdigital.manager.DbManager;
import com.fxdigital.manager.DeviceConfigImpl;
import com.fxdigital.manager.LocalCenter;
import com.impl.LocalServerImpl;
import com.impl.NewAddImpl;
import com.pojo.LocalServer;



/**
 * 
 * @author hxht
 * @version 2014-9-1
 */
@Controller
public class NetArgsSetController {

	@Autowired
	private DbManager dbManager;
	private static final Logger log = Logger
			.getLogger(NetArgsSetController.class);

	@RequestMapping(value = "/netArgsSet", method = RequestMethod.POST)
	public String init(HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes attr) {
		try {
			if (System.getProperty("os.name").equals("Linux")) {
				LocalServerImpl localimpl = LocalServerImpl.newInstance();
				LocalServer server = localimpl.getLocalServer();
				
				//从数据库拿数据
				LocalCenter center=new LocalCenter();
				center=dbManager.getLocalCenter();
				server.setIp(center.getIp());
				server.setMask(center.getCenterMask());
				server.setGate(center.getCenterGate());
				
				
				request.setAttribute("localServer", server);
			}else{
				attr.addAttribute("message", "获取服务器网络参数的方式不支持Windows系统！");
				return "redirect:/error";
			}
		} catch (Exception e) {
			log.error("网络参数读取异常！", e);
			attr.addAttribute("message", "网络参数读取异常！");
			return "redirect:/error";
		}
		return "netArgsSet";
	}

	@RequestMapping(value = "/netArgsSetSubmit", method = RequestMethod.POST)
	public void submit(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter writer = null;
		try {
			LocalServerImpl localimpl = LocalServerImpl.newInstance();
			String ip = request.getParameter("ip");
			String gate = request.getParameter("gate");
			String mask = request.getParameter("mask");
			LocalServer server = new LocalServer();
			server.setIp(ip);
			server.setGate(gate);
			server.setMask(mask);
			DeviceConfigImpl dci = DeviceConfigImpl.getDciInstance();

			writer = response.getWriter();
			if (dci.upDeviceConfig(ip)) {
				dbManager.setLocalIp(ip, gate, mask);
				localimpl.updateLocalServer(server);
				NewAddImpl.getNewAddInstance().shutDevice();
				writer.print("true");
			} else {
				writer.print("false");
			}
			writer.flush();
			writer.close();
		} catch (Exception e) {
			log.error("网络参数设置异常！", e);
		}
	}

}
