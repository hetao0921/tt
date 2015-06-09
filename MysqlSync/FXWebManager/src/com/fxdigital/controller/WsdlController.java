package com.fxdigital.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.fxdigital.db.dao.WsdlDao;

@Controller
public class WsdlController {

	@Autowired
	private WsdlDao wsdlDao;
	private static final Logger log = Logger.getLogger(WsdlController.class);

	@RequestMapping(value = { "wsdl_show" }, method = RequestMethod.POST)
	public void show(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		try {

			// System.out.println("1111111111111begin");

			List<?> s = new ArrayList<Object>();
			s = wsdlDao.wsdls_show();
			// int i= wsdlDao.selectByfunctionName("fx1");
			// System.out.println("-------------------结果是"+wsdlDao.selectByfunctionName("fx1"));

			response.setContentType("text/html;charset=UTF-8");

			request.setCharacterEncoding("UTF-8");

			out = response.getWriter();

			out.print(JSONObject.toJSONString(s));
		} catch (Exception e) {
			log.error("出现异常！", e);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	@RequestMapping(value = { "wsdlInfo_show" }, method = RequestMethod.POST)
	public void wsdlInfo_show(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter out = null;
		try {
			List<?> s = new ArrayList<Object>();
			s = wsdlDao.wsdlInfo_show();
			response.setContentType("text/html;charset=UTF-8");

			request.setCharacterEncoding("UTF-8");

			out = response.getWriter();

			out.print(JSONObject.toJSONString(s));
		} catch (Exception e) {
			log.error("出现异常！", e);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	// @RequestMapping(value = { "save_uri" }, method = RequestMethod.GET)
	// public ModelAndView save_uri(Model model, HttpServletRequest request,
	// HttpServletResponse response) {
	// String wsdlURI=request.getParameter("wsdlURI");
	// ModelAndView mav=new ModelAndView("wsdlInfo");
	// mav.addObject("page",wsdlURI);
	// return mav;
	//
	//

	// }

	@RequestMapping(value = { "edit_wsdl" }, method = RequestMethod.POST)
	public void edit_wsdl(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter out = null;
		try {
			String sURI = request.getParameter("sURI");
			String uri = request.getParameter("uri");
			String desc = request.getParameter("desc");
			// System.out.println("--------------------------"+desc);
			// List<?> s = new ArrayList<Object>();
			int i = wsdlDao.edit_wsdl(sURI, uri, desc);
			wsdlDao.edit_wsdlURI(sURI, uri);
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			out = response.getWriter();

			out.print(JSONObject.toJSONString(i));
		} catch (Exception e) {
			log.error("出现异常！", e);
		} finally {
			if (out != null) {
				out.close();
			}
		}

	}

	@RequestMapping(value = { "edit_wsdlInfo" }, method = RequestMethod.POST)
	public void edit_wsdlInfo(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter out = null;
		try {

			String wsdlInfoId = request.getParameter("wsdlInfoId");
			// System.out.println(wsdlInfoId);
			String functionName = request.getParameter("functionName");
			String functionDesc = request.getParameter("functionDesc");
			String arguments = request.getParameter("arguments");
			String results = request.getParameter("results");
			int id = 0;
			id = Integer.parseInt(wsdlInfoId);
			// List<?> s = new ArrayList<Object>();
			int i = wsdlDao.edit_wsdlInfo(id, functionName, functionDesc,
					arguments, results);

			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			out = response.getWriter();

			out.print(JSONObject.toJSONString(i));
		} catch (Exception e) {
			log.error("出现异常！", e);
		} finally {
			if (out != null) {
				out.close();
			}
		}

	}

	@RequestMapping(value = { "addWsdl" }, method = RequestMethod.POST)
	public void addWsdl(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			String wsdlUri = request.getParameter("wsdlUri");
			String wsdlDesc = request.getParameter("wsdlDesc");
			String functionName = request.getParameter("functionName");
			String functionDesc = request.getParameter("functionDesc");
			String arguments = request.getParameter("arguments");
			String results = request.getParameter("results");

			int i = wsdlDao.insert(wsdlUri, wsdlDesc, functionName,
					functionDesc, arguments, results);

			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			out = response.getWriter();

			out.print(JSONObject.toJSONString(i));
		} catch (Exception e) {
			log.error("出现异常！", e);
		} finally {
			if (out != null) {
				out.close();
			}
		}

	}

	@RequestMapping(value = { "addWsdlInfo" }, method = RequestMethod.POST)
	public void addWsdlInfo(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter out = null;
		try {
			String wsdlUri = request.getParameter("wsdlUri");
			// System.out.println(wsdlUri+"11111111111111111");
			String functionName = request.getParameter("functionName");
			String functionDesc = request.getParameter("functionDesc");
			String arguments = request.getParameter("arguments");
			String results = request.getParameter("results");

			int i = wsdlDao.insertInfo(wsdlUri, functionName, functionDesc,
					arguments, results);

			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			out = response.getWriter();

			out.print(JSONObject.toJSONString(i));

		} catch (Exception e) {

			log.error("出现异常！", e);
		} finally {
			if (out != null) {
				out.close();
			}
		}

	}

	@RequestMapping(value = { "delWsdl" }, method = RequestMethod.POST)
	public void delWsdl(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			String wsdlURI = request.getParameter("wsdlURI");

			int i = wsdlDao.delete(wsdlURI);

			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			out = response.getWriter();

			out.print(JSONObject.toJSONString(i));
		} catch (Exception e) {
			log.error("出现异常！", e);
		} finally {
			if (out != null) {
				out.close();
			}
		}

	}

	@RequestMapping(value = { "delWsdlInfo" }, method = RequestMethod.POST)
	public void delWsdlInfo(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter out = null;
		try {
			String wsdlInfoId = request.getParameter("ID");

			int i = wsdlDao.deleteInfo(wsdlInfoId);

			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			out = response.getWriter();

			out.print(JSONObject.toJSONString(i));
		} catch (Exception e) {
			log.error("出现异常！", e);
		} finally {
			if (out != null) {
				out.close();
			}
		}

	}

	@RequestMapping(value = { "selectByWsdlURI" }, method = RequestMethod.POST)
	public void selectByWsdlURI(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter out = null;

		try {
			String wsdlURI = request.getParameter("wsdlURI");
			// System.out.println("----------ajax传值-------"+wsdlURI);
			List<?> s = new ArrayList<Object>();
			s = wsdlDao.selectByWsdlURI(wsdlURI);
			response.setContentType("text/html;charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			out = response.getWriter();

			out.print(JSONObject.toJSONString(s));
		} catch (Exception e) {
			log.error("出现异常！", e);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

}
