package com.fxdigital.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fxdigital.manager.DbManager;
import com.fxdigital.manager.DeviceConfigImpl;
import com.fxdigital.manager.LocalCenter;
import com.fxdigital.manager.RegisterCenter;
import com.fxdigital.manager.SubStatus;
import com.fxdigital.manager.SupStatus;
import com.fxdigital.manager.SyncServer;
import com.fxdigital.util.LinuxCmd;
import com.impl.LocalServerImpl;
import com.impl.NewAddImpl;
import com.pojo.LocalServer;





/**
 * 
 * @author fucz
 * @version 2014-7-21
 */
@Controller
public class RelationController {
	
	@Autowired
	private DbManager dbManager;

	private static final Logger log = Logger.getLogger(RelationController.class);
	
	@RequestMapping(value = "/localmqset", method = RequestMethod.POST)
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

//	@RequestMapping(value = "/netArgsSetSubmit", method = RequestMethod.POST)
//	public void submit(HttpServletRequest request, HttpServletResponse response) {
//		PrintWriter writer = null;
//		try {
//			LocalServerImpl localimpl = LocalServerImpl.newInstance();
//			String ip = request.getParameter("ip");
//			String gate = request.getParameter("gate");
//			String mask = request.getParameter("mask");
//			LocalServer server = new LocalServer();
//			server.setIp(ip);
//			server.setGate(gate);
//			server.setMask(mask);
//			DeviceConfigImpl dci = DeviceConfigImpl.getDciInstance();
//			
//			writer = response.getWriter();
//			if (dci.upDeviceConfig(ip)) {
//				dbManager.setLocalIp(ip, gate, mask);
//				localimpl.updateLocalServer(server);
//				NewAddImpl.getNewAddInstance().shutDevice();
//				writer.print("true");
//			} else {
//				writer.print("false");
//			}
//			writer.flush();
//			writer.close();
//		} catch (Exception e) {
//			log.error("网络参数设置异常！", e);
//		}
//	}
//	
//	@RequestMapping(value = { "/localmqset" })
//	public String localmqset(ModelMap modelMap,HttpServletRequest request){
//		LocalCenter local = dbManager.getLocalCenter();
//		String autotime=dbManager.getAutoTime();
//		String ip = request.getParameter("apply_ip");
//		String port = request.getParameter("apply_port");
//		List<SupStatus> supStatuses = dbManager.getSupStatus();
//		List<SubStatus> subStatuses = dbManager.getSubStatus();
//		List<RegisterCenter> centers = dbManager.getAllLocalRegisterCenter();
//		modelMap.put("sups", supStatuses);
//		modelMap.put("subs", subStatuses);
//		modelMap.put("clients", centers);
//		modelMap.put("ip", ip);
//		modelMap.put("port", port);
//		if(local != null){
//			modelMap.put("mqIp", local.getSyncServerIP());
//			modelMap.put("mqPort", local.getSyncServerPort());
//		}else{
//			modelMap.put("mqIp", "数据库访问失败");
//			modelMap.put("mqPort", "数据库访问失败");
//		}
//		if(autotime==null){
//			modelMap.put("autotime", "");
//		}else{
//			modelMap.put("autotime", autotime);
//		}
//		return "netArgsSet";
//	}
	
	@RequestMapping(value = { "/localmq" }, method = RequestMethod.POST)
	public String localmq(ModelMap modelMap,HttpServletRequest request){
		LocalCenter local = dbManager.getLocalCenter();
		String autotime=dbManager.getAutoTime();
		String ip = request.getParameter("apply_ip");
		String port = request.getParameter("apply_port");
		List<SupStatus> supStatuses = dbManager.getSupStatus();
		List<SubStatus> subStatuses = dbManager.getSubStatus();
		List<RegisterCenter> centers = dbManager.getAllLocalRegisterCenter();
		modelMap.put("sups", supStatuses);
		modelMap.put("subs", subStatuses);
		modelMap.put("clients", centers);
		modelMap.put("ip", ip);
		modelMap.put("port", port);
		if(local != null){
			modelMap.put("mqIp", local.getSyncServerIP());
			modelMap.put("mqPort", local.getSyncServerPort());
		}else{
			modelMap.put("mqIp", "数据库访问失败");
			modelMap.put("mqPort", "数据库访问失败");
		}
		if(autotime==null){
			modelMap.put("autotime", "");
		}else{
			modelMap.put("autotime", autotime);
		}
		return "/syncserverviews/localmqipset";
	}
	
	@RequestMapping(value = { "/applyupmq" }, method = RequestMethod.POST)
	public String applyupmq(ModelMap modelMap,HttpServletRequest request){
		LocalCenter local = dbManager.getLocalCenter();
		String autotime=dbManager.getAutoTime();
		String ip = request.getParameter("apply_ip");
		String port = request.getParameter("apply_port");
		List<SupStatus> supStatuses = dbManager.getSupStatus();
		List<SubStatus> subStatuses = dbManager.getSubStatus();
		List<RegisterCenter> centers = dbManager.getAllLocalRegisterCenter();
		modelMap.put("sups", supStatuses);
		modelMap.put("subs", subStatuses);
		modelMap.put("clients", centers);
		modelMap.put("ip", ip);
		modelMap.put("port", port);
		if(local != null){
			modelMap.put("mqIp", local.getSyncServerIP());
			modelMap.put("mqPort", local.getSyncServerPort());
		}else{
			modelMap.put("mqIp", "数据库访问失败");
			modelMap.put("mqPort", "数据库访问失败");
		}
		if(autotime==null){
			modelMap.put("autotime", "");
		}else{
			modelMap.put("autotime", autotime);
		}
		return "/syncserverviews/applyupmqip";
	}
	
	@RequestMapping(value = { "/showupmq" })
	public String showupmq(ModelMap modelMap,HttpServletRequest request){
		LocalCenter local = dbManager.getLocalCenter();
		String autotime=dbManager.getAutoTime();
		String ip = request.getParameter("apply_ip");
		String port = request.getParameter("apply_port");
		List<SupStatus> supStatuses = dbManager.getSupStatus();
		List<SubStatus> subStatuses = dbManager.getSubStatus();
		List<RegisterCenter> centers = dbManager.getAllLocalRegisterCenter();
		modelMap.put("sups", supStatuses);
		modelMap.put("subs", subStatuses);
		modelMap.put("clients", centers);
		modelMap.put("ip", ip);
		modelMap.put("port", port);
		if(local != null){
			modelMap.put("mqIp", local.getSyncServerIP());
			modelMap.put("mqPort", local.getSyncServerPort());
		}else{
			modelMap.put("mqIp", "数据库访问失败");
			modelMap.put("mqPort", "数据库访问失败");
		}
		if(autotime==null){
			modelMap.put("autotime", "");
		}else{
			modelMap.put("autotime", autotime);
		}
		return "/syncserverviews/showupmqip";
	}
	
	@RequestMapping(value = { "/showlowmq" })
	public String showlowmq(ModelMap modelMap,HttpServletRequest request){
		LocalCenter local = dbManager.getLocalCenter();
		String autotime=dbManager.getAutoTime();
		String ip = request.getParameter("apply_ip");
		String port = request.getParameter("apply_port");
		List<SupStatus> supStatuses = dbManager.getSupStatus();
		List<SubStatus> subStatuses = dbManager.getSubStatus();
		List<RegisterCenter> centers = dbManager.getAllLocalRegisterCenter();
		modelMap.put("sups", supStatuses);
		modelMap.put("subs", subStatuses);
		modelMap.put("clients", centers);
		modelMap.put("ip", ip);
		modelMap.put("port", port);
		if(local != null){
			modelMap.put("mqIp", local.getSyncServerIP());
			modelMap.put("mqPort", local.getSyncServerPort());
		}else{
			modelMap.put("mqIp", "数据库访问失败");
			modelMap.put("mqPort", "数据库访问失败");
		}
		if(autotime==null){
			modelMap.put("autotime", "");
		}else{
			modelMap.put("autotime", autotime);
		}
		return "/syncserverviews/showlowmqip";
	}
	
	@RequestMapping(value = { "/showclient" })
	public String showclient(ModelMap modelMap,HttpServletRequest request){
		LocalCenter local = dbManager.getLocalCenter();
		String autotime=dbManager.getAutoTime();
		String ip = request.getParameter("apply_ip");
		String port = request.getParameter("apply_port");
		List<SupStatus> supStatuses = dbManager.getSupStatus();
		List<SubStatus> subStatuses = dbManager.getSubStatus();
		List<RegisterCenter> centers = dbManager.getAllLocalRegisterCenter();
		modelMap.put("sups", supStatuses);
		modelMap.put("subs", subStatuses);
		modelMap.put("clients", centers);
		modelMap.put("ip", ip);
		modelMap.put("port", port);
		if(local != null){
			modelMap.put("mqIp", local.getSyncServerIP());
			modelMap.put("mqPort", local.getSyncServerPort());
		}else{
			modelMap.put("mqIp", "数据库访问失败");
			modelMap.put("mqPort", "数据库访问失败");
		}
		if(autotime==null){
			modelMap.put("autotime", "");
		}else{
			modelMap.put("autotime", autotime);
		}
		return "/syncserverviews/showlocalclient";
	}
	
	@RequestMapping(value = { "/syncserver" })
	public String entry(ModelMap modelMap,HttpServletRequest request){
		LocalCenter local = dbManager.getLocalCenter();
		String autotime=dbManager.getAutoTime();
		String ip = request.getParameter("apply_ip");
		String port = request.getParameter("apply_port");
		List<SupStatus> supStatuses = dbManager.getSupStatus();
		List<SubStatus> subStatuses = dbManager.getSubStatus();
		List<RegisterCenter> centers = dbManager.getAllLocalRegisterCenter();
		modelMap.put("sups", supStatuses);
		modelMap.put("subs", subStatuses);
		modelMap.put("clients", centers);
		modelMap.put("ip", ip);
		modelMap.put("port", port);
		if(local != null){
			modelMap.put("mqIp", local.getSyncServerIP());
			modelMap.put("mqPort", local.getSyncServerPort());
		}else{
			modelMap.put("mqIp", "数据库访问失败");
			modelMap.put("mqPort", "数据库访问失败");
		}
		if(autotime==null){
			modelMap.put("autotime", "");
		}else{
			modelMap.put("autotime", autotime);
		}
		return "/syncserverviews/relation";
	}
	
	@RequestMapping(value = { "/rebootserver" }, method = RequestMethod.POST)
	public void reboot(HttpServletResponse response,HttpServletRequest request){
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if(LinuxCmd.SUCCESS.equals(LinuxCmd.processUseBasic("reboot"))){
				out.print("重启服务器成功！");
			}else{
				out.print("重启服务器失败！");
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = { "/setMq" }, method = RequestMethod.POST)
	public void setMq(HttpServletResponse response,HttpServletRequest request){
		String mqIp = request.getParameter("mqIp");
		int mqPort = Integer.parseInt(request.getParameter("mqPort"));
		String autotime = request.getParameter("autotime");
//		dbManager.setMqInfo(mqIp, mqPort);
//		dbManager.setAutoTime(autotime);
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if(dbManager.setMqInfo(mqIp, mqPort)&&dbManager.setAutoTime(autotime)>0){
				out.print("修改成功，服务器马上重启！");
				LinuxCmd.processUseBasic("reboot");
			}else{
				out.print("修改失败！");
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = { "/apply" }, method = RequestMethod.POST)
	public void apply(HttpServletResponse response,HttpServletRequest request){
		String ip = request.getParameter("ip");
		int port = Integer.parseInt(request.getParameter("port"));
		SupStatus supStatus = dbManager.getSupStatus(ip);
		SubStatus subStatus = dbManager.getSubStatusFromIP(ip);
		SyncServer syncServer = dbManager.getSyncServerFromMqIP(ip);
		List<SupStatus> sups = dbManager.getSupStatus();
		PrintWriter out = null;
		if(sups != null && sups.size() > 0){
			int supCount = 0;
			for(SupStatus ss : sups){
				if("申请中".equals(ss.getStatus())
						|| "已申请".equals(ss.getStatus())
						|| "已通过".equals(ss.getStatus())){
					supCount ++;
				}
			}
			if(supCount >= 1){
				try {
					out = response.getWriter();
					out.print("不能申请多个上级！");
					out.flush();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			}
		}
		if(supStatus != null
				&& ("申请中".equals(supStatus.getStatus())
				|| "已申请".equals(supStatus.getStatus())
				|| "撤销中".equals(supStatus.getStatus())
				|| "已通过".equals(supStatus.getStatus()))
				){
			try {
				out = response.getWriter();
				out.print(supStatus.getStatus() + "，不能再次申请！");
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(subStatus != null
				&& ("未审核".equals(subStatus.getStatus())
				|| "通过中".equals(subStatus.getStatus())
				|| "已通过".equals(subStatus.getStatus()))
				){
			try {
				out = response.getWriter();
				out.print("所申请的上级已是或将是本级的下级，禁止申请！");
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(syncServer != null){
			try {
				out = response.getWriter();
				out.print("所申请的上级已是本级的上级或间接下级，禁止申请！");
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			SupStatus ss = new SupStatus();
			ss.setMqIP(ip);
			ss.setMqPort(port);
			ss.setStatus("申请中");
			ss.setApplyTime(new Timestamp(System.currentTimeMillis()));
			try {
				out = response.getWriter();
				if(dbManager.addSupStatus(ss)){
					out.print("申请成功！");
				}else{
					out.print("申请失败！");
				}
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping(value = { "/cancel" }, method = RequestMethod.POST)
	public void cancel(HttpServletResponse response,HttpServletRequest request){
		String ip = request.getParameter("ip");
		SupStatus supStatus = dbManager.getSupStatus(ip);
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if(supStatus == null){
				out.print("数据错误！");
			}else if("申请中".equals(supStatus.getStatus())
					|| "已申请".equals(supStatus.getStatus())
					){
				if(dbManager.changeSupStatus(ip, "撤销中")){
					out.print("撤销成功！");
				}else{
					out.print("撤销失败！");
				}
			}else{
				out.print(supStatus.getStatus()+"，不能撤销！");
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = { "/agree" }, method = RequestMethod.POST)
	public void agree(HttpServletResponse response,HttpServletRequest request){
		String serverID = request.getParameter("id");
		SubStatus subStatus = dbManager.getSubStatusFromID(serverID);
		SyncServer ss = dbManager.getSyncServerFromMqIP(subStatus.getMqIP());
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if(ss != null){
				dbManager.changeSubStatus(
						subStatus.getServerID(),"拒绝中");
				out.print("已是本级的下级或间接上级，拒绝此申请！");
			}else if("未审核".equals(subStatus.getStatus())
					){
				if(dbManager.changeSubStatus(serverID, "通过中")){
					out.print("通过成功！");
				}else{
					out.print("通过失败！");
				}
			}else{
				out.print(subStatus.getStatus()+"，不能通过！");
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = { "/reject" }, method = RequestMethod.POST)
	public void reject(HttpServletResponse response,HttpServletRequest request){
		String serverID = request.getParameter("id");
		SubStatus subStatus = dbManager.getSubStatusFromID(serverID);
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if(subStatus == null){
				out.print("数据错误！");
			}else if("未审核".equals(subStatus.getStatus())
					){
				if(dbManager.changeSubStatus(serverID, "拒绝中")){
					out.print("拒绝成功！");
				}else{
					out.print("拒绝失败！");
				}
			}else{
				out.print(subStatus.getStatus()+"，不能拒绝！");
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = { "/delete" }, method = RequestMethod.POST)
	public void delete(HttpServletResponse response,HttpServletRequest request){
		String serverID = request.getParameter("id");
		SubStatus subStatus = dbManager.getSubStatusFromID(serverID);
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if(subStatus == null){
				out.print("数据错误！");
			}else if("已通过".equals(subStatus.getStatus())
					){
				if(dbManager.changeSubStatus(serverID, "删除中")){
					out.print("删除成功！");
				}else{
					out.print("删除失败！");
				}
			}else{
				out.print(subStatus.getStatus()+"，不能删除！");
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
