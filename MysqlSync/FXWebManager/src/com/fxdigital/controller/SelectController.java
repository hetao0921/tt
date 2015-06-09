package com.fxdigital.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fxdigital.manager.pojo.IpMap;
import com.fxdigital.util.DelXml;
import com.fxdigital.util.InsertXml;
import com.fxdigital.util.RefreshXml;
import com.fxdigital.util.SelectXml;
import com.fxdigital.util.ShowXml;

@Controller
public class SelectController {

	private static final Logger log = Logger.getLogger(SelectController.class);

	@RequestMapping(value = { "select" }, method = RequestMethod.GET)
	public void select(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			String num = request.getParameter("num");
			// System.out.println("------num=" + num);
			String url = null;
			if (System.getProperty("os.name").equals("Linux")) {
				url = "/etc/fxconf/streamfoward/StreamFoward.conf";
			} else {
				url = "D:/fxconf/streamfoward/StreamFoward.conf";
			}
			SelectXml.selectXml(url, num);
			response.setContentType("text/html;charset=UTF-8");/* 设定编码和返回类型 */

			request.setCharacterEncoding("UTF-8");

			out = response.getWriter();

			out.print(SelectXml.selectXml(url, num));/* 将数据输入到缓冲区 */

			// ModelAndView mav = new ModelAndView("new");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}/* 设定参数编码 */catch (IOException e) {
			log.error("文件读写异常！", e);
		}

		finally {
			if (out != null) {
				out.close();
			}
		}
	}

	@RequestMapping(value = { "insert" }, method = RequestMethod.GET)
	public void insert(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			String realIp = request.getParameter("realIp");
			String VIP = request.getParameter("VIP");
			String url = null;
			if (System.getProperty("os.name").equals("Linux")) {
				url = "/etc/fxconf/streamfoward/StreamFoward.conf";
			} else {
				url = "D:/fxconf/streamfoward/StreamFoward.conf";
			}

			List<IpMap> ipList = new ArrayList<IpMap>();
			ipList = InsertXml.insertXml(url, realIp, VIP);
			response.setContentType("text/html;charset=UTF-8");/* 设定编码和返回类型 */

			request.setCharacterEncoding("UTF-8");
			/* 设定参数编码 */
			out = response.getWriter();
			JSONArray json = JSONObject.parseArray(JSONObject
					.toJSONString(ipList));

			out.print(json.toJSONString());/* 将数据输入到缓冲区 */

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			log.error("文件读写异常！", e);
		} finally {
			
				if (out != null) {
					out.close();
				}
			

		}

	}

	@RequestMapping(value = { "delrealIp" }, method = RequestMethod.GET)
	public void del(HttpServletRequest request, HttpServletResponse response) {
		if (System.getProperty("os.name").equals("Linux")) {
			String delrealIp = request.getParameter("delrealIp");

			String url = null;
			if (System.getProperty("os.name").equals("Linux")) {
				url = "/etc/fxconf/streamfoward/StreamFoward.conf";
			} else {
				url = "D:/fxconf/streamfoward/StreamFoward.conf";
			}
			DelXml.delXml(url, delrealIp);
		}
	}

	@RequestMapping(value = { "refresh" }, method = RequestMethod.GET)
	public void refresh(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			String url = null;
			if (System.getProperty("os.name").equals("Linux")) {
				url = "/etc/fxconf/streamfoward/StreamFoward.conf";
			} else {
				url = "D:/fxconf/streamfoward/StreamFoward.conf";
			}

			List<IpMap> ipList = new ArrayList<IpMap>();
			ipList = RefreshXml.refreshXml(url);
			response.setContentType("text/html;charset=UTF-8");/* 设定编码和返回类型 */

			request.setCharacterEncoding("UTF-8");
			/* 设定参数编码 */
			out = response.getWriter();
			JSONArray json = JSONObject.parseArray(JSONObject
					.toJSONString(ipList));

			out.print(json.toJSONString());/* 将数据输入到缓冲区 */

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			log.error("文件读写异常！", e);
		}

		finally {
			if (out != null) {
				out.close();
			}

		}

	}

	@RequestMapping(value = { "show" }, method = RequestMethod.GET)
	public void show(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			String url = null;
			if (System.getProperty("os.name").equals("Linux")) {
				url = "/etc/fxconf/streamfoward/StreamFoward.conf";
			} else {
				url = "D:/fxconf/streamfoward/StreamFoward.conf";
			}
			String str = ShowXml.showXml(url);
			response.setContentType("text/html;charset=UTF-8");/* 设定编码和返回类型 */

			request.setCharacterEncoding("UTF-8");
			/* 设定参数编码 */
			out = response.getWriter();

			out.print(str);/* 将数据输入到缓冲区 */

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			log.error("文件读写异常！", e);
		}

		finally {
			if (out != null) {
				out.close();
			}

		}

	}

}
