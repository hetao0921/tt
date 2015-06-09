package com.fxdigital.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fxdigital.db.dao.MqServerInfoDao;

import fxdigital.dbsync.domains.client.service.MsgClientService;



@Controller
public class WaitUpload {
	private static final Logger log = Logger.getLogger(WaitUpload.class);
	@Autowired
	MqServerInfoDao mqServerInfoDao;
	@Autowired
	HttpSession session;
	//做超时统计的
	int count=0;
	// 与数据库数据比较，确定是否能上传
	// flag 0版本不对（页面传过来的本地版本号小于数据库记录本地版本号）
	// 1版本正确  执行getUpBack()获取上传返回值
	// 2上传锁锁定
	// -1无反馈
	@RequestMapping(value = { "/isupload" }, method = RequestMethod.POST)
	public void isupload(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter out = null;
		int upcount=0;
		//全局变量赋初值，方便后面获取上传状态
		count=0;
		String flag="-1";
		log.info("进入isupload方法！！！");
		List<Map<String, Object>> centerinfo = mqServerInfoDao.queryCenterID();
		log.info("获取本中心CenterId：" + centerinfo.get(0));
		log.info("从界面获取参数：" + request.getParameter("selfversion1")
				+ ":" + request.getParameter("serversion1") + ":"
				+ request.getParameter("aftversion1"));
		String sef = request.getParameter("selfversion1");
		String ser = request.getParameter("serversion1");
		String aft = request.getParameter("aftversion1");
		List<String> upversion = new ArrayList<String>();
		upversion.add(sef);
		upversion.add(ser);
		upversion.add(aft);
		log.info("发送上传sendupcommand命令：");
		while(true){
			if(upcount<4){
				flag = MsgClientService.getInstance().sendUpCommand(upversion,
						centerinfo);
				log.info("获取是否能执行上传状态fag:" + flag);
				if(flag!="-1"){
					try {
						out = response.getWriter();
						out.print(flag);
						out.flush();
						out.close();
						break;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else{
					upcount++;
					log.info("第"+upcount+"发生sendUpCommand()命令,获取是否能执行上传状态:"+flag);
					try {
						Thread.sleep(50000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}else{
				try {
					out = response.getWriter();
					out.print(flag);
					out.flush();
					out.close();
					break;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 进入上传操作，上传过程监控
	@RequestMapping(value = { "/uploading" }, method = RequestMethod.POST)
	public void uploading(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter out = null;
		String lastbak =null;
		boolean flag=true;
		List<HashMap<String, String>> list = null;
		while (flag) {
			if(count<20){
			// back=-1上传中，0上传成功，1上传失败
			String back = MsgClientService.getInstance().getUpBack();
			log.info("执行上传后回馈back值:" + back);
			if (back.equals("0")) {
				// 发送解锁指令
				String uplockid = (String) session.getAttribute("uplockid");
				log.info("上传完成。发送解锁指令uplockid:" + uplockid);
				MsgClientService.getInstance().unUpLock(uplockid);
				// 最后一次获取成功值
				list = MsgClientService.getInstance().getUpResult("", "");
				String uploadbak = listToString(list);
				log.info("成功后最后一次获取uploadbak:" + uploadbak);
				lastbak = "0_" + uploadbak;
				try {
					count=0;
					out = response.getWriter();
					out.print(lastbak);
					out.flush();
					out.close();
					flag=false;
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (back.equals("-1")) {
				count++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				list = MsgClientService.getInstance().getUpResult("", "");
				log.info("上传中第"+count+"次获取的值uploadbak:" + listToString(list));
				lastbak = "-1_" + listToString(list);
				try {
					out = response.getWriter();
					out.print(lastbak);
					out.flush();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				list = MsgClientService.getInstance().getUpResult("", "");
				log.info("上传失败获取的值uploadbak:" + listToString(list));
				lastbak = "1_" + listToString(list);
				try {
					out = response.getWriter();
					out.print(lastbak);
					out.flush();
					out.close();
					flag=false;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			}else{
//				log.info("count:"+count);
				log.info("上传失败，停止上传!");
				try {
					lastbak = "2_同步服务器链接失败！";
					out = response.getWriter();
					out.print(lastbak);
					out.flush();
					out.close();
					flag=false;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 获取锁，
	@RequestMapping(value = { "/getuplock" }, method = RequestMethod.POST)
	public void getuplock(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("进入getuplock方法！");
		String backvalue = null;
		String[] uplock = MsgClientService.getInstance().getUpMsg();
		String bak = uplock[2];
		log.info("获取uplock:" + bak);
		if (bak.equals("0")) {
			backvalue = "0";
		} else {
			backvalue = "1";
		}
		try {
			PrintWriter out = response.getWriter();
			out.print(backvalue);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 获取服务器上上传版本号
	@RequestMapping(value = { "/getsyncnumber" }, method = RequestMethod.POST)
	public void preupload(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter out = null;
		int count = 0;
		log.info("进入getsyncnumber方法！！！");
		// System.out.println(request.getParameter("Action")+":"+request.getParameter("Name"));
		while (true) {
			if (count < 4) {
				String serversion = MsgClientService.getInstance()
						.getServerVersion();
				String nextversion = MsgClientService.getInstance()
						.getNextVersion();
				String sync = serversion + "," + nextversion;
				log.info("serversion:" + serversion + ",nextversion:"
						+ nextversion);
				if (serversion != "-1" && nextversion != "-1") {
					try {
						out = response.getWriter();
						out.print(sync);
						out.flush();
						out.close();
						break;
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					count++;
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} else {
				try {
					out = response.getWriter();
					out.print("-1");
					out.flush();
					out.close();
					break;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 获取本地版本号和锁ID
	@RequestMapping(value = { "/getnativever" }, method = RequestMethod.POST)
	public void getnativever(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("进入getnativever方法！！！");
		List<Map<String, Object>> centerinfo = mqServerInfoDao.queryCenterID();
		log.info("获取本中心CenterId：" + centerinfo.get(0));
		String[] uplock = MsgClientService.getInstance().getUpLock();
		List<String> version = MsgClientService.getInstance().getUpVersionInfo(
				centerinfo);
		String localversion = version.get(0) + "," + uplock[0];
		session = request.getSession();
		session.removeAttribute("uplockid");
		session.setAttribute("uplockid", uplock[0]);
		log.info("localversion:" + version.get(0) + ",uplockid:"
				+ uplock[0] + ",uplockstate:" + uplock[2]);
		try {
			PrintWriter out = response.getWriter();
			out.print(localversion);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//获取上次状态
	@RequestMapping(value = { "/getupstate" }, method = RequestMethod.POST)
	public void getupstate(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("进入getupstate()方法！");
		List<HashMap<String, String>> list = MsgClientService.getInstance().getUpResult("", "");
		String data=listToString(list);
	//	String data="刷新界面后，再次获取上传状态值";
		log.info("getupstate获取的值data:" +data );
		try {
			PrintWriter out = response.getWriter();
			out.print(data);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = { "/unlock" }, method = RequestMethod.POST)
	public void unlock(HttpServletRequest request, HttpServletResponse response) {
		log.info("进入unlock方法！！！");
		// System.out.println(request.getParameter("unlock"));
		String uplockid = (String) session.getAttribute("uplockid");
		log.info("uplockid:" + uplockid);
		String unlock = MsgClientService.getInstance().unUpLock(uplockid);
		try {
			PrintWriter out = response.getWriter();
			out.print(unlock);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String listToString(List<HashMap<String, String>> list){
		String lastStr="";
		for(int i=0;i<list.size();i++){
			String centerid=list.get(i).get("uuid");
			String enddate=list.get(i).get("enddate");
			String state=list.get(i).get("state");
			lastStr+=centerid+"在"+enddate+"时"+state+"!\r\n";
		}
		return lastStr;
	}
}
