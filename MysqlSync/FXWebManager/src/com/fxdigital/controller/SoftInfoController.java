package com.fxdigital.controller;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.fxdigital.SysAuth;
import com.fxdigital.bean.SoftInfo;
import com.fxdigital.manager.SoftInfoImpl;

/**
 * 
 * @author hxht
 * @version 2014-9-5
 */
@Controller
public class SoftInfoController {

	private static final Logger log = Logger
			.getLogger(SoftInfoController.class);

	@RequestMapping(value = "/softinfo", method = RequestMethod.POST)
	public String entry_softinfo(HttpServletRequest request) {
		return "softinfo";
	}
	
	
	@RequestMapping(value = "/license", method = RequestMethod.POST)
	public String entry_license(HttpServletRequest request) {
		return "license";
	}

	@RequestMapping(value = "/getSoftInfo", method = RequestMethod.POST)
	public void getSoftInfo(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter writer = null;
		SoftInfo si = new SoftInfo();
		try {
			writer = response.getWriter();
			SoftInfoImpl sii = SoftInfoImpl.getInstance();
			si.setDeviceType(sii.getDeviceType());
			si.setLicenseTime(sii.getLicenseTime());
			si.setSoftVersion(sii.getSoftVersion());
			si.setMacAddress(sii.getMacAddress());
		} catch (Exception e) {
			log.error("获得授权信息异常！", e);
		} finally {
			if (writer != null) {
				writer.print(JSONObject.toJSONString(si));
				writer.flush();
				writer.close();
			}
		}
	}

	@RequestMapping(value = "/importTxtLicense", method = RequestMethod.POST)
	public void importTxtLicense(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter writer = null;
		String result = null;
		try {
			writer = response.getWriter();
			String license = request.getParameter("license");
			log.info("Txt License : "+license);
			if (SysAuth.importLicense(license)) {
				result = "true";
			} else {
				result = "false";
			}
		} catch (Exception e) {
			log.error("导入字符串授权信息异常！", e);
			result = "导入字符串授权信息异常！";
		} finally {
			if (writer != null) {
				if(result == null){
					result = "导入字符串授权信息异常！";
				}
				writer.print(result);
				writer.flush();
				writer.close();
			}
		}
	}

	@RequestMapping(value = "/importFileLicense", method = RequestMethod.POST)
	public void importFileLicense(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter writer = null;
		String result = null;
		try {
			writer = response.getWriter();
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
			MultipartFile file = null;
			for (Entry<String, MultipartFile> set : fileMap.entrySet()) {
				file = set.getValue();
			}
			if (file.getName() != null && file.getSize() != 0) {
				// 浏览器中取得的文件全路径不为空 大小 不为0 则写入
				java.nio.ByteBuffer bf = java.nio.ByteBuffer
						.allocate((int) file.getSize());
				InputStream in = file.getInputStream();
				int a;
				while ((a = in.read()) != -1) {
					bf.put((byte) a);
				}
				bf.flip();
				String license = new String(bf.array(), "gbk");
				log.info("File License : "+license);
				if (SysAuth.importLicense(license)) {
					result = "true";
				} else {
					result = "false";
				}
			} else {
				result = "文件内容为空！";
			}
		} catch (Exception e) {
			log.error("导入文件授权信息异常！", e);
			result = "导入文件授权信息异常！";
		} finally {
			if (writer != null) {
				if(result == null){
					result = "导入文件授权信息异常！";
				}
				writer.print(result);
				writer.flush();
				writer.close();
			}
		}
	}

}
