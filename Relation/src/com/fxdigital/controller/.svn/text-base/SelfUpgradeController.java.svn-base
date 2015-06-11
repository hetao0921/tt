package com.fxdigital.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.fxdigital.db.dao.CenterVerDao;
import com.fxdigital.db.pojo.CenterVerinfo;
import com.fxdigital.db.pojo.SendUpResultInfo;
import com.fxdigital.db.pojo.SendVerCheckpojo;
import com.fxdigital.db.pojo.SoftPacketInfo;
import com.fxdigital.db.pojo.UpRecordinfo;
import com.fxdigital.util.LinuxCmmand;

@Controller
public class SelfUpgradeController {
	private static final Logger log = Logger.getLogger(SelfUpgradeController.class);
	private String upserip = null;// 升级服务器ip
	private String upserport = null;// 升级服务器port
	private String version = null;// 当前软件版本
	private String oldver=null;//升级之前的版本
	private String rollcommand = null;// 升级命令
	private static String[] str = new String[3];
	private static final SimpleDateFormat df = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");// 设置日期格式
	private static final LinuxCmmand cmd = new LinuxCmmand();
	@Autowired
	CenterVerDao centerVerDao;
	@RequestMapping(value = { "/upserver" }, method = RequestMethod.POST)
	public String upserver() {
		log.info("upserver!!!!!!!!!");
		return "upgradeviews/upserver";
	}

	@RequestMapping(value = { "/selectversion" }, method = RequestMethod.POST)
	public String selectversion() {
		log.info("selectversion!!!!!!!!!");
		return "upgradeviews/selectversion";
	}

	@RequestMapping(value = { "/definedupserver" }, method = RequestMethod.POST)
	public String definedupserver() {
		log.info("definedupserver!!!!!!!!!");
		return "upgradeviews/definedupserver";
	}

	@RequestMapping(value = { "/upseripset" }, method = RequestMethod.POST)
	public String upseripset() {
		log.info("upseripset!!!!!!!!!");
		return "upgradeviews/upseripset";
	}

	@RequestMapping(value = { "/getupinfo" }, method = RequestMethod.POST)
	public void getupinfo(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("getupinfo!!!!!!!!!");
		PrintWriter out = null;
		List<CenterVerinfo> centervers = centerVerDao.queryCenterVer();
		if (null != centervers && centervers.size() > 0) {
			SendVerCheckpojo rv = new SendVerCheckpojo();
			rv.setDeviceId(centervers.get(0).getDeviceId());
			rv.setSoftType(centervers.get(0).getSoftType());
			rv.setSoftVer(centervers.get(0).getSoftVersion().toUpperCase());
			rv.setClientIP(centervers.get(0).getDeviceIp());
			rv.setDeviceName(centervers.get(0).getDeviceName());
			version = centervers.get(0).getSoftVersion().toUpperCase();
			String json = getLatestVer(rv);
			if ("[null]".equals(json)) {
				try {
					//System.out.println(json.toString());
					out = response.getWriter();
					out.print("null");
					out.flush();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
			try {
				List<SoftPacketInfo> list = JSONObject.parseArray(json,
						SoftPacketInfo.class);
				json = JSONObject.toJSONString(jsontoArray(list));
				out = response.getWriter();
				out.print(json);
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			}
		} else {
			try {
				out = response.getWriter();
				out.print("null");
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 是否可以下载
	@RequestMapping(value = { "/isdownpack" }, method = RequestMethod.POST)
	public void isdownpack(HttpServletRequest request,
			HttpServletResponse response) {
		String fileid = request.getParameter("fileid");
		log.info("isdownpack!!!!!!!!!" + fileid);
		long offset = 0;
		File file = new File("/home/hxht/downpacket/");
		if (file.exists()) {
			offset = scanFolder("/home/hxht/downpacket/", fileid);
		} else {
			file.mkdirs();
		}
		log.info("offset:" + offset);
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(offset);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 开始下载
	@RequestMapping(value = { "/downPacket" }, method = RequestMethod.POST)
	public void downPacket(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String[] str = new String[2];
		String requetpra = request.getParameter("downpram");
		str = requetpra.split(",");
		String fileid = str[0];
		String offset = str[1];
		log.info("进入downPacket方法:" + requetpra + "开始下载！");
		String downurl = "http://" + upserip + ":" + upserport
				+ "/NetUpgrade/downPacket?offset=" + offset + "&fileID="
				+ fileid;
		log.info("下载url:" + downurl);
		URL url = new URL(downurl);
		URLConnection rulConnection = url.openConnection();
		HttpURLConnection httpConn = (HttpURLConnection) rulConnection;
		InputStream inStrm = httpConn.getInputStream();
		byte[] buffer = new byte[1024 * 10];
		int hasRead = 0;
		hasRead = inStrm.read(buffer);
		RandomAccessFile f = new RandomAccessFile("/home/hxht/downpacket/"
				+ fileid, "rw");
		while (-1 != hasRead) {
			f.write(buffer, 0, hasRead);
			hasRead = inStrm.read(buffer);
			// log.info("下载中："+hasRead);
		}
		f.close();
		inStrm.close();
		httpConn.disconnect();

		log.info("下载成功！");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print("下载成功!");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = { "/unZip" }, method = RequestMethod.POST)
	public void unZip(HttpServletRequest request, HttpServletResponse response) {
		log.info("unZip!!!!!!!!!");
		String fileid = request.getParameter("fileid");
		// String filepath=System.getProperty("user.dir");
		File file = new File("/home/hxht/workdir/");
		if (!file.exists()) {
			file.mkdirs();
		}
		log.info("filepath:" + file.getPath());
		String command = "tar -xvf /home/hxht/downpacket/" + fileid + " -C "
				+ file.getPath();
		int back = cmd.processCmmand(command);
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(back);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 升级操作
	@RequestMapping(value = { "/upGrade" }, method = RequestMethod.POST)
	public void upGrade(HttpServletRequest request, HttpServletResponse response) {
		log.info("upGrade!!!!!!!!!");
		List<String> packetinfo = readXml("/home/hxht/workdir/upgrade.xml");
		// 回滚命令
		rollcommand = packetinfo.get(12);
		// 执行解压
		String command = "tar -xvf /home/hxht/workdir/install.tar.gz -C /home/hxht/workdir/";
		cmd.processCmmand(command);
		// 执行备份
		command = "/home/hxht/workdir/install/bin/" + packetinfo.get(10);
		cmd.processCmmand(command);
		// 执行升级
		command = "/home/hxht/workdir/install/bin/" + packetinfo.get(11);
		log.info("upgradecommand====" + command);
		int back = cmd.processCmmand(command);
		log.info("backupgradecommand===" + back);
		String upgradedate = df.format(new Date());
		log.info("upgradedate====" + upgradedate);

//		UpRecordinfo upRecordinfo = new UpRecordinfo();
		SendUpResultInfo send = new SendUpResultInfo();
		str = getCenterinfo().split(",");
		String centerid = str[1];
		send.setDeviceID(centerid);
		send.setSoftOldVersion(version);
		send.setSoftCurVersion(packetinfo.get(4));
		send.setSoftType(packetinfo.get(0));
		send.setUpgradeTime(upgradedate);
		send.setUpMode("defined");
		PrintWriter out = null;
		if (back == 0) {
//			upRecordinfo.setDeviceid(centerid);
//			upRecordinfo.setSoftname(packetinfo.get(1));
//			upRecordinfo.setSoftversion(version);
//			upRecordinfo.setSoftcurversion(packetinfo.get(4));
//			upRecordinfo.setSofttype(packetinfo.get(0));
//			upRecordinfo.setPublishdate(packetinfo.get(5));
//			upRecordinfo.setOperatetype("手动升级");
//			upRecordinfo.setOperatetatus("成功");
//			centerVerDao.insertUprecord(upRecordinfo, upgradedate);
			centerVerDao.insertUprecord(centerid, packetinfo.get(1), packetinfo.get(0), version, packetinfo.get(4), "", "", packetinfo.get(5), "手动升级", "成功", "", "", upgradedate);
			centerVerDao.updateCenVersion(packetinfo.get(4), version, "1");
			try {
				out = response.getWriter();
				out.print("0");
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			send.setStatus("1");
			sendUpResult(send);
		} else {
//			upRecordinfo.setDeviceid(centerid);
//			upRecordinfo.setSoftname(packetinfo.get(1));
//			upRecordinfo.setSoftversion(version);
//			upRecordinfo.setSoftcurversion(packetinfo.get(4));
//			upRecordinfo.setSofttype(packetinfo.get(0));
//			upRecordinfo.setPublishdate(packetinfo.get(5));
//			upRecordinfo.setOperatetype("手动升级");
			log.info("自动回滚");
			command = "/home/hxht/workdir/install/bin/" + rollcommand;
			int re = cmd.processCmmand(command);
			if (re == 0) {
//				upRecordinfo.setOperatetatus("失败（自动成功回滚）");
//				centerVerDao.insertUprecord(upRecordinfo, upgradedate);
				centerVerDao.insertUprecord(centerid, "服务器升级包", packetinfo.get(0), version, packetinfo.get(4), "", "", "", "手动升级", "失败（自动成功回滚）", "", "", upgradedate);
				try {
					out = response.getWriter();
					out.print("升级失败，已经成功回滚");
					out.flush();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				send.setStatus("失败（自动成功回滚）");
				sendUpResult(send);
			} else {
//				upRecordinfo.setOperatetatus("失败（自动回滚失败）");
//				centerVerDao.insertUprecord(upRecordinfo, upgradedate);
				centerVerDao.insertUprecord(centerid, "服务器升级包", packetinfo.get(0), version, packetinfo.get(4), "", "", "", "手动升级", "失败（自动回滚失败）", "", "", upgradedate);
				try {
					out = response.getWriter();
					out.print("回滚失败，请手动回滚");
					out.flush();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				send.setStatus("失败（自动回滚失败）");
				sendUpResult(send);
			}
		}
	}

	// 提示是否回滚
		@RequestMapping(value = { "/isrollback" })
		public void isrollback(HttpServletRequest request,
				HttpServletResponse response) {
			log.info("进入isrollback");
			PrintWriter out = null;
			if (null == rollcommand) {
				List<String> packetinfo = readXml("/home/hxht/workdir/upgrade.xml");
				rollcommand = packetinfo.get(12);
				}
			List<CenterVerinfo> lists = centerVerDao.queryCenterVer();
			if (null != lists && lists.size() > 0) {
				oldver = lists.get(0).getSoftOldVer();
				String curver=lists.get(0).getSoftVersion();
				String back=curver+","+oldver;
				if("".equals(oldver)||null==oldver){
					try {
						out = response.getWriter();
						out.print("0");
						out.flush();
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else{
					try {
						out = response.getWriter();
						out.print(back);
						out.flush();
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}else {
				try {
					out = response.getWriter();
					out.print("0");
					out.flush();
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	
	// 回滚到上一个版本
	@RequestMapping(value = { "/rollback" })
	public void rollback(HttpServletRequest request,
			HttpServletResponse response) {
		
		log.info("进入rollback");
		PrintWriter out = null;
		log.info("手动回滚");
//		if (null == rollcommand) {
//			List<String> packetinfo = readXml("/home/hxht/workdir/upgrade.xml");
//			rollcommand = packetinfo.get(12);
//		}
		List<CenterVerinfo> lists = centerVerDao.queryCenterVer();
//		if (null != lists && lists.size() > 0) {
			String centerid=lists.get(0).getDeviceId();
			oldver = lists.get(0).getSoftOldVer();
			String curver=lists.get(0).getSoftVersion();
			String state = lists.get(0).getUpState();
			String softype=lists.get(0).getSoftType();
//			if("".equals(oldver)||null==oldver){
//				try {
//					out = response.getWriter();
//					out.print("没有还原版本");
//					out.flush();
//					out.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}else{
				String upgradedate = df.format(new Date());
//				UpRecordinfo upRecordinfo = new UpRecordinfo();
				SendUpResultInfo send = new SendUpResultInfo();
				send.setDeviceID(centerid);
				send.setSoftOldVersion(curver);
				send.setSoftCurVersion(oldver);
				send.setSoftType(softype);
				send.setUpgradeTime(upgradedate);
				send.setUpMode("defined");
				String command = "/home/hxht/workdir/install/bin/" + rollcommand;
				log.info("回滚命令：" + command);
				int back = cmd.processCmmand(command);
				log.info("回滚结果：" + back);
				if (back == 0) {
//					if ("1".equals(state)) {
				centerVerDao.updateCenVersion(oldver, "", "0");
//					}
					try {
						out = response.getWriter();
						out.print("回滚成功");
						out.flush();
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
//					upRecordinfo.setDeviceid(centerid);
//					upRecordinfo.setSoftname("服务器升级包");
//					upRecordinfo.setSoftversion(curver);
//					upRecordinfo.setSoftcurversion(oldver);
//					upRecordinfo.setSofttype(softype);
//					upRecordinfo.setPublishdate(upgradedate);
//					upRecordinfo.setOperatetype("手动回滚");
//					upRecordinfo.setOperatetatus("回滚成功");
//					centerVerDao.insertUprecord(centerid,"服务器升级包",curver,oldver,softype,"","手动回滚","回滚成功",upgradedate);
					centerVerDao.insertUprecord(centerid, "服务器升级包", softype, oldver, curver, "", "", "", "手动回滚", "回滚成功", "", "", upgradedate);
					send.setStatus("手动回滚成功");
					sendUpResult(send);
				} else {
//					cmd.processCmmand(command);
					try {
						out = response.getWriter();
						out.print("回滚失败，请手动回滚");
						out.flush();
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
//					upRecordinfo.setDeviceid(centerid);
//					upRecordinfo.setSoftname("服务器升级包");
//					upRecordinfo.setSoftversion(curver);
//					upRecordinfo.setSoftcurversion(oldver);
//					upRecordinfo.setSofttype(softype);
//					upRecordinfo.setPublishdate(upgradedate);
//					upRecordinfo.setOperatetype("手动回滚");
//					upRecordinfo.setOperatetatus("回滚失败");
//					centerVerDao.insertUprecord(upRecordinfo, upgradedate);
					centerVerDao.insertUprecord(centerid, "服务器升级包", softype, oldver, curver, "", "", "", "手动回滚", "回滚失败", "", "", upgradedate);
					send.setStatus("手动回滚失败");
					sendUpResult(send);
//				}
			}
		} 
//	else {
//			try {
//				out = response.getWriter();
//				out.print("没有还原版本");
//				out.flush();
//				out.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}

	// 显示最新版本
	@RequestMapping(value = { "/getseftVer" })
	public void getseftVer(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("进入getseftVer");
		PrintWriter out = null;
		if (version == null) {
			try {
				out = response.getWriter();
				out.print("0");
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				out = response.getWriter();
				out.print(version);
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 显示升级记录
	@RequestMapping(value = { "/getUprecord" })
	public void getUprecord(HttpServletRequest request,
			HttpServletResponse response) {
		List<UpRecordinfo> uprecords = centerVerDao.queryUprecords();
		String json = JSONObject.toJSONString(uprecords);
		log.info("uprecords:" + json);
		PrintWriter out = null;
		if (null != uprecords && uprecords.size() > 0) {
			try {
				out = response.getWriter();
				out.print(json);
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				out = response.getWriter();
				out.print(json);
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 获取中心信息
	@RequestMapping(value = { "/getCenterinfo" })
	public String getCenterinfo() {
		List<HashMap<String, String>> centerinfos = centerVerDao.queryCenterInfo();
		String centerid = null;
		String centerip = null;
		String centername = null;
		if (null == centerinfos) {
			log.info("获取本机中心信息失败！");
			return null;
		}
		for (int i = 0; i < centerinfos.size(); i++) {
			centerid =  centerinfos.get(0).get("CenterID");
			centername =  centerinfos.get(0).get("CenterName");
			centerip =  centerinfos.get(0).get("CenterIP");
		}
		log.info("本机中心名称：" + centername + ",中心Id：" + centerid + ",中心ip:"
				+ centerip);
		String bak = centername + "," + centerid + "," + centerip;
		return bak;
	}

	// 获取升级服务器信息
	@RequestMapping(value = { "/getupserinfo" })
	public void getupserinfo(HttpServletRequest request,
			HttpServletResponse response) {
		// 查询中心版本信息
		List<?> centerVer = centerVerDao.queryCenterNum();
		String bakvalue = null;
		String backs = null;
		try {
			PrintWriter out = response.getWriter();
			if (null != centerVer && centerVer.size() > 0) {
				// 查询最新中心信息
				// String re = getCenterinfo();
				// if (null == re) {
				// log.info("中心信息为空！");
				// out.print("0");
				// out.flush();
				// out.close();
				// return;
				// }
				// str = re.split(",");
				// 更新中心信息
				// centerVerDao.updateCenVerInfo(str[0], str[2], str[1]);
				// 查询升级服务器指向
				List<CenterVerinfo> centervers = centerVerDao.queryCenterVer();
				for (int i = 0; i < centervers.size(); i++) {
					bakvalue = centervers.get(i).getUpServerip() + ","
							+ centervers.get(i).getUpServerPort() + ","
							+ centervers.get(i).getSoftType();
					upserip = centervers.get(i).getUpServerip();
					upserport = centervers.get(i).getUpServerPort();
					log.info("softtype:" + centervers.get(i).getSoftType());
					if ("".equals(centervers.get(i).getSoftType())) {
						String softtype = getSoftType();
						backs = upserip + "," + upserport + "," + softtype;
						out.print("0_" + backs);
						out.flush();
						out.close();
					}
				}
				out.print("1_" + bakvalue);
				out.flush();
				out.close();
			} else if (null != centerVer && centerVer.size() == 0) {
				// 查询软件类型字典
				String softtype = getSoftType();
				backs = upserip + "," + upserport + "," + softtype;
				out.print("0_" + backs);
				out.flush();
				out.close();
			} else {
				log.info("数据库连接失败！");
				out.print("-1");
				out.flush();
				out.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 设置升级服务器信息
	@RequestMapping(value = { "/setupserinfo" })
	public void setupserinfo(HttpServletRequest request,
			HttpServletResponse response) {
		log.info("进入setupserinfo方法！！！");
		int bak = 0;
		str = getCenterinfo().split(",");
		String clientname = str[0];
		String clientid = str[1];
		String clientip = str[2];
		upserip = request.getParameter("upserip");
		upserport = request.getParameter("upserport");
		String softtype = request.getParameter("devicetype");
		List<CenterVerinfo> centervers = centerVerDao.queryCenterVer();
		if (null != centervers && centervers.size() > 0) {
			bak = centerVerDao.updateCenVerInfo(clientname, clientip, clientid,
					upserip, upserport, softtype);
		} else if (null != centervers && centervers.size() == 0) {
//			CenterVerinfo centerinfo = new CenterVerinfo();
//			centerinfo.setDeviceId(clientid);
//			centerinfo.setDeviceIp(clientip);
//			centerinfo.setDeviceName(clientname);
//			centerinfo.setSoftType(softtype);
//			centerinfo.setSoftVersion("0");
//			centerinfo.setUpServerip(upserip);
//			centerinfo.setUpServerPort(upserport);
			bak = centerVerDao.insertCenVerinfo(clientid,clientip,clientname,softtype,"V2.0.0.0",upserip,upserport);
		} else {
			log.info("数据异常！");
		}

		try {
			PrintWriter out = response.getWriter();
			out.print(bak);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 获取设备类型字典
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/getSoftType" })
	public @ResponseBody
	String getSoftType() {
		String sofytype = "";
		RestTemplate rt = new RestTemplate();
		FastJsonHttpMessageConverter convert = new FastJsonHttpMessageConverter();
		rt.getMessageConverters().add(convert);
		if (null == upserip || null == upserport || "".equals(upserip)
				|| "".equals(upserport)) {
			return sofytype;
		}
		String url = "http://" + upserip + ":" + upserport
				+ "/NetUpgrade/getSoftType?systemtype=0";
		log.info("连接升级服务器获取设备类型url：" + url);
		String s = rt.getForObject(url, String.class);
		log.info("升级服务器反馈信息：" + s);
		if (null != s) {
			List<String> np = JSONObject.parseObject(s, ArrayList.class);
			for (int i = 0; i < np.size(); i++) {
				sofytype += np.get(i) + ",";
			}
		}
		log.info(sofytype);
		return sofytype;
	}

	// 向服务器获取最新版本
	@RequestMapping(value = { "/getLatestVer" })
	public @ResponseBody
	String getLatestVer(@RequestBody SendVerCheckpojo requestVer) {
		RestTemplate rt = new RestTemplate();
		FastJsonHttpMessageConverter convert = new FastJsonHttpMessageConverter();
		rt.getMessageConverters().add(convert);
		if (null == upserip || null == upserport || "".equals(upserip)
				|| "".equals(upserport)) {
			List<CenterVerinfo> centervers = centerVerDao.queryCenterVer();
			for (int i = 0; i < centervers.size(); i++) {
				upserip = centervers.get(0).getUpServerip();
				upserport = centervers.get(0).getUpServerPort();
			}
			if (null == upserip || null == upserport || "".equals(upserip)
					|| "".equals(upserport)) {
				return "[null]";
			}
		}
		log.info("设备Id：" + requestVer.getDeviceId() + ",设备Ip："
				+ requestVer.getClientIP() + ",软件版本：" + requestVer.getSoftVer());
		String url = "http://" + upserip + ":" + upserport
				+ "/NetUpgrade/getLatestVer";
		log.info("连接升级服务器获取最新版本url：" + url);
		String json = rt.postForObject(url, requestVer, String.class);
		log.info(json);
		return json;
	}

	// 反馈升级信息
	@RequestMapping(value = { "/backupreslut" })
	public @ResponseBody
	String sendUpResult(@RequestBody SendUpResultInfo send) {
		RestTemplate rt = new RestTemplate();
		FastJsonHttpMessageConverter convert = new FastJsonHttpMessageConverter();
		rt.getMessageConverters().add(convert);
		String url = "http://" + upserip + ":" + upserport
				+ "/NetUpgrade/upgradeResult";
		String json = rt.postForObject(url, send, String.class);
		System.out.println(json);
		return json;
	}

	// 计算百分比
	public double div(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).doubleValue() * 100;
	}

	// 扫描本地文件 找到对应文件 返回文件大小
	public long scanFolder(String path, String filename) {
		if (null != path && null != filename) {
			File rootDir = new File(path);
			log.info("path:" + path + ",filename:" + filename + "，文件个数："
					+ rootDir.list().length);
			File[] files = rootDir.listFiles();
			for (File file : files) {
				if (!file.isDirectory() && filename.equals(file.getName())) {
					log.info(file.getName() + ",大小：" + file.length());
					return file.length();
				}
			}
		}
		return 0;
	}

	// 读取upgrade.xml信息
	@SuppressWarnings("unchecked")
	public List<String> readXml(String xmlpath) {
		List<String> list = new ArrayList<String>();
		log.info(xmlpath);
		SAXReader sr = new SAXReader();
		Document document = null;
		try {
			document = sr.read(xmlpath);
			// log.info(document);
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		XPath xsub = document.createXPath("//type");
		// log.info(xsub);
		List<Node> nodes = xsub.selectNodes(document);
		log.info(nodes.get(0).getText().trim());
		list.add(nodes.get(0).getText().trim());

		xsub = document.createXPath("//name");
		// log.info(xsub);
		nodes = xsub.selectNodes(document);
		log.info(nodes.get(0).getText().trim());
		list.add(nodes.get(0).getText().trim());

		xsub = document.createXPath("//os");
		// log.info(xsub);
		nodes = xsub.selectNodes(document);
		log.info(nodes.get(0).getText().trim());
		list.add(nodes.get(0).getText().trim());

		xsub = document.createXPath("//lang");
		// log.info(xsub);
		nodes = xsub.selectNodes(document);
		log.info(nodes.get(0).getText().trim());
		list.add(nodes.get(0).getText().trim());

		xsub = document.createXPath("//version");
		// log.info(xsub);
		nodes = xsub.selectNodes(document);
		log.info(nodes.get(0).getText().trim());
		list.add(nodes.get(0).getText().trim());

		xsub = document.createXPath("//publishDate");
		// log.info(xsub);
		nodes = xsub.selectNodes(document);
		log.info(nodes.get(0).getText().trim());
		list.add(nodes.get(0).getText().trim());

		xsub = document.createXPath("//size");
		// log.info(xsub);
		nodes = xsub.selectNodes(document);
		log.info(nodes.get(0).getText().trim());
		String size = nodes.get(0).getText().trim();
		double longth = Double.parseDouble(size);
		double re = longth / (1024 * 1024);
		DecimalFormat df = new DecimalFormat(".00");
		String relust = String.valueOf(df.format(re)) + "M";
		log.info(relust);
		list.add(relust);

		xsub = document.createXPath("//upgradeType");
		// log.info(xsub);
		nodes = xsub.selectNodes(document);
		log.info(nodes.get(0).getText().trim());
		String upgradeType = nodes.get(0).getText().trim();
		String[] str = new String[2];
		str = upgradeType.split("_");
		if (str.length == 2) {
			if ("INC".equals(str[0])) {
				list.add("补丁包");
			} else {
				list.add("完整安装包");
			}
			if ("FAST".equals(str[1])) {
				list.add("紧急");
			} else {
				list.add("一般");
			}
		} else {
			if ("INC".equals(str[0])) {
				list.add("补丁包");
			} else {
				list.add("完整安装包");
			}
			list.add("");
		}

		xsub = document.createXPath("//workDir");
		// log.info(xsub);
		nodes = xsub.selectNodes(document);
		log.info(nodes.get(0).getText().trim());
		list.add(nodes.get(0).getText().trim());

		xsub = document.createXPath("//@value");
		// log.info(xsub);
		nodes = xsub.selectNodes(document);
		log.info(nodes.get(1).getText());
		list.add(nodes.get(0).getText());
		list.add(nodes.get(1).getText());
		list.add(nodes.get(2).getText());
		return list;
	}

	public Object[][] jsontoArray(List<SoftPacketInfo> list) {
		Object[][] table = null;
		String softtype = "";
		String softver = "";
		String softname = "";
		String softsize = "";
		String softlevel = "";
		String softuptype = "";
		String publishdate = "";
		String fileid = "";
		long filelength = 0;
		List<Object[]> datas = new ArrayList<Object[]>();
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Object[] array = new Object[9];
				softtype = list.get(i).getSofttype();
				softver = list.get(i).getSoftversion();
				softname = list.get(i).getFilename();
				long size = list.get(i).getFilesize();
				if (size > 0) {
					double re = size / (1024 * 1024);
					DecimalFormat df = new DecimalFormat(".00");
					softsize = String.valueOf(df.format(re)) + "M";
				}
				softlevel = list.get(i).getSoftlevel();
				if ("FAST".equals(softlevel)) {
					softlevel = "紧急";
				} else {
					softlevel = "一般";
				}
				softuptype = list.get(i).getSoftuptype();
				if ("INC".equals(softuptype)) {
					softuptype = "补丁包";
				} else {
					softuptype = "完整安装包";
				}
				publishdate = list.get(i).getPublishdate();
				fileid = list.get(i).getFileID();
				filelength = list.get(i).getFilesize();
				array[0] = softtype;
				array[1] = softver;
				array[2] = softname;
				array[3] = softsize;
				array[4] = softlevel;
				array[5] = softuptype;
				array[6] = publishdate;
				array[7] = fileid;
				array[8] = filelength;
				datas.add(array);
			}
			table = datas.toArray(new Object[datas.size()][]);
		}
		return table;
	}

	// 测试前台显示数据
	@RequestMapping(value = { "/showLatestVer" }, method = RequestMethod.POST)
	public void showLatestVer(HttpServletRequest request,
			HttpServletResponse response) {
		List<HashMap<String, String>> datalist = new ArrayList<HashMap<String, String>>();
		for (int i = 1; i < 4; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("filename", "FXC8000-V1.0.0.1-20141112.tar.gz");
			map.put("softversion", "V1.0.0." + i);
			map.put("filesize", "1" + i + "M");
			map.put("softtype", "FXS3310");
			map.put("softuptype", "ALL");
			map.put("softlevel", "Fast");
			map.put("publishdate", "2014-11-19");
			datalist.add(map);
		}
		String json = JSONObject.toJSONString(datalist);
		try {
			PrintWriter out = response.getWriter();
			out.print(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
