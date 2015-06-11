package com.fxdigital.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.fxdigital.bean.ServerLogBean;
import com.fxdigital.util.ConfigUtil;

/**
 * 
 * @author hxht
 * @version 2014-9-15
 */
@Controller
public class ServerLogController {

	private static final Logger log = Logger
			.getLogger(ServerLogController.class);

	@RequestMapping(value = "/serverLog", method = RequestMethod.POST)
	public String entry() {
		return "serverLog";
	}

	@RequestMapping(value = "/getServerLogs", method = RequestMethod.POST)
	public void getServerLogs(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter writer = null;
		String result = null;
		try {
			writer = response.getWriter();

			List<ServerLogBean> slbs = new ArrayList<ServerLogBean>();
			String path = null;
			if (System.getProperty("os.name").equals("Linux")) {
				path = ConfigUtil.getString("path.linux");
			} else {
				path = ConfigUtil.getString("path.windows");
			}
			File dir = new File(path);
			if (dir.isDirectory()) {
				File[] logFiles = dir.listFiles();
				if (logFiles != null) {
					for (File logFile : logFiles) {
						ServerLogBean slb = new ServerLogBean();
						slb.setName(logFile.getName());
						DecimalFormat df = new DecimalFormat("###.000");
						if(logFile.length() == 0){
							slb.setSize("0KB");
						}else if(logFile.length() == 1){
							slb.setSize("0.001KB");
						}else if(logFile.length() >= 1024){
							slb.setSize(logFile.length() / 1024 + "KB");
						}else{
							slb.setSize(df.format(logFile.length() / 1024.0) + "KB");
						}
						String date = new Timestamp(logFile.lastModified())
								.toString();
						if (date.contains(".")) {
							date = date.split("\\.")[0];
						}
						slb.setDate(date);
						slbs.add(slb);
					}
				}
			}
			result = JSONObject.toJSONString(slbs);

		} catch (Exception e) {
			log.error("获得设备信息异常！", e);
			result = "获得设备信息异常！";
		} finally {
			if (writer != null) {
				if (result == null) {
					result = "获得设备信息失败！";
				}
				writer.print(result);
				writer.flush();
				writer.close();
			}
		}
	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void download(HttpServletRequest request,
			HttpServletResponse response) {
		String filename = request.getParameter("filename");// 从页面获取要下载的文件的相对路径
		String path = null;
		if (System.getProperty("os.name").equals("Linux")) {
			path = ConfigUtil.getString("path.linux");
		} else {
			path = ConfigUtil.getString("path.windows");
		}
		path = path + File.separator + filename;
		if (!"".equals(path)) {
			File file = new File(path);// 构造要下载的文件
			if (file.exists()) {
				InputStream ins = null;
				BufferedInputStream bins = null;
				OutputStream outs = null;
				BufferedOutputStream bouts = null;
				try {
					ins = new FileInputStream(path);// 构造一个读取文件的IO流对象
					bins = new BufferedInputStream(ins);// 放到缓冲流里面
					outs = response.getOutputStream();// 获取文件输出IO流
					bouts = new BufferedOutputStream(outs);
					response.setContentType("application/x-download");// 设置response内容的类型
					response.setHeader("Content-disposition",
							"attachment;filename="+filename);// 设置头部信息
					int bytesRead = 0;
					byte[] buffer = new byte[8192];
					// 开始向网络传输文件流
					while ((bytesRead = bins.read(buffer, 0, 8192)) != -1) {
						bouts.write(buffer, 0, bytesRead);
					}
					bouts.flush();// 这里一定要调用flush()方法
				} catch (Exception e) {
					log.error("下载的文件异常！",e);
				} finally {
					try {
						ins.close();
						bins.close();
						outs.close();
						bouts.close();
					} catch (IOException e) {
						log.error("关闭文件流异常！", e);
					}
				}
			} else {
				log.error("下载的文件不存在");
			}
		} else {
			log.error("下载文件时参数错误");
		}
	}

}
