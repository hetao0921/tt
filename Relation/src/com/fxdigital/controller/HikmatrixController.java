package com.fxdigital.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fxdigital.bean.Device;
import com.fxdigital.bean.Quality;
import com.fxdigital.manager.DeviceImpl;
import com.fxdigital.manager.NewAddImpl;
import com.impl.HikmatrixImpl;
import com.pojo.Hikmatrix;
import com.pojo.Hikmatrixs;

/**
 * 
 * @author hxht
 * @version 2014-9-2
 */
@Controller
public class HikmatrixController {

	private static final Logger log = Logger.getLogger(HikmatrixController.class);

	@RequestMapping(value = "/showHikmatrix", method = RequestMethod.POST)
	public String entry() {
		return "showHikmatrix";
	}

	@RequestMapping(value = "/getHikmatrix", method = RequestMethod.POST)
	public void getData(HttpServletResponse response) {
		PrintWriter writer = null;
		List<Hikmatrix> list = null;
		try {
			writer = response.getWriter();
			DeviceImpl devimpl = DeviceImpl.getDeviceInstance();
			Quality qit = devimpl.getDevice().getQuality("outmodel");
			HikmatrixImpl hik = HikmatrixImpl.getHikmatrixImplInstance();
			Hikmatrixs hs = hik.getHikmatrix();
			list = hs.getList();
			JSONArray json = JSONObject.parseArray(JSONObject.toJSONString(list));
			int index = 0;
			for(Hikmatrix tmp : list){
				String model = qit.getValueTest(String.valueOf(tmp.getOutmodel()));
				json.getJSONObject(index ++).put("outmodel", model);
			}
			writer.print(json.toJSONString());
			writer.flush();
			writer.close();
		} catch (Exception e) {
			log.error("获得解码矩阵异常！", e);
		}
	}

	@RequestMapping(value = "/addHikmatrix", method = RequestMethod.POST)
	public void add(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			String user = request.getParameter("txtUser");
			String pass = request.getParameter("txtPass");
			String type = request.getParameter("txtClass");
			String outmodel = request.getParameter("txtOutModel");
			String devId = request.getParameter("txtDeviceId");
			String devType = request.getParameter("txtDeviceType");
			String adress = request.getParameter("txtAddress");
			String port = request.getParameter("txtPort");
			HikmatrixImpl hikimpl = HikmatrixImpl.getHikmatrixImplInstance();
			Hikmatrix hik = new Hikmatrix();
			hik.setAddress(adress);
			hik.setAdmin(user);
			hik.setClassType(type);
			hik.setDeviceId(devId);
			hik.setDeviceType(devType);
			hik.setOutmodel(Integer.parseInt(outmodel));
			hik.setPass(pass);
			hik.setPort(Integer.parseInt(port));
			if (hikimpl.addHikmatrix(hik)) {
				writer.print("true");
			} else {
				writer.print("false");
			}
			writer.flush();
			writer.close();
		} catch (Exception e) {
			log.error("添加解码矩阵异常！", e);
		}
	}
	
	@RequestMapping(value = "/editHikmatrix", method = RequestMethod.POST)
	public void edit(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			int selectedId = Integer.parseInt(request.getParameter("selectedId"));
			String user = request.getParameter("txtUser");
			String pass = request.getParameter("txtPass");
			String type = request.getParameter("txtClass");
			String outmodel = request.getParameter("txtOutModel");
			String devId = request.getParameter("txtDeviceId");
			String devType = request.getParameter("txtDeviceType");
			String adress = request.getParameter("txtAddress");
			String port = request.getParameter("txtPort");
			HikmatrixImpl hikimpl = HikmatrixImpl.getHikmatrixImplInstance();
			Hikmatrix hik = new Hikmatrix();
			hik.setAddress(adress);
			hik.setAdmin(user);
			hik.setClassType(type);
			hik.setDeviceId(devId);
			hik.setDeviceType(devType);
			hik.setOutmodel(Integer.parseInt(outmodel));
			hik.setPass(pass);
			hik.setPort(Integer.parseInt(port));
			Hikmatrixs hiks = hikimpl.getHikmatrix();
			hiks.getList().set(selectedId-1, hik);
			if (hikimpl.updateHikmatrix(hiks)) {
				writer.print("true");
			} else {
				writer.print("false");
			}
			writer.flush();
			writer.close();
		} catch (Exception e) {
			log.error("修改解码矩阵异常！", e);
		}
	}
	
	@RequestMapping(value = "/delHikmatrix", method = RequestMethod.POST)
	public void del(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			int selectedId = Integer.parseInt(request.getParameter("selectedId"));
			HikmatrixImpl hikimpl = HikmatrixImpl.getHikmatrixImplInstance();
			if (hikimpl.delHikmatrix(selectedId-1)) {
				writer.print("true");
			} else {
				writer.print("false");
			}
			writer.flush();
			writer.close();
		} catch (Exception e) {
			log.error("删除解码矩阵异常！", e);
		}
	}
	
	@RequestMapping(value = "/refreshHikmatrix", method = RequestMethod.POST)
	public void refresh(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			NewAddImpl add = NewAddImpl.getNewAddInstance();
			if (add.hikmatrixReflush()) {
				writer.print("true");
			} else {
				writer.print("false");
			}
			writer.flush();
			writer.close();
		} catch (Exception e) {
			log.error("IP矩阵刷新异常！", e);
		}
	}

	@RequestMapping(value = "/getClasses", method = RequestMethod.POST)
	public void getClasses(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			Device de = DeviceImpl.getDeviceInstance().getDevice();
			Quality qu = de.getQuality("classType");
			String def = qu.getDefaultvalue();
			List<String> classes = new ArrayList<String>();
			if (!qu.isIsreadonly()) {
				classes = qu.getListTest();
			} else {
				classes.add(def);
			}
			writer.print(JSONObject.toJSONString(classes));
			writer.flush();
			writer.close();
		} catch (Exception e) {
			log.error("获得类型异常！", e);
		}
	}

	@RequestMapping(value = "/getDeviceTypes", method = RequestMethod.POST)
	public void getDeviceTypes(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			Device de = DeviceImpl.getDeviceInstance().getDevice();
			Quality qu = de.getQuality("deviceType");
			String def = qu.getDefaultvalue();
			String defreal = qu.getRealValue();
			Map<String, String> deviceTypes = new HashMap<String, String>();
			if(!qu.isIsreadonly()){
				for (int i = 0; i < qu.getListTest().size(); i++) {
					String te = qu.getListTest().get(i);
					String va = qu.getListValue().get(i);
					deviceTypes.put(te, va);
				}
			}else{
				deviceTypes.put(defreal, def);
			}
			writer.print(JSONObject.toJSONString(deviceTypes));
			writer.flush();
			writer.close();
		} catch (Exception e) {
			log.error("获得设备类型异常！", e);
		}
	}

	@RequestMapping(value = "/getOutModels", method = RequestMethod.POST)
	public void getOutModels(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			Device de = DeviceImpl.getDeviceInstance().getDevice();
			Quality qu = de.getQuality("outmodel");
			String def = qu.getDefaultvalue();
			String defreal = qu.getRealValue();
			Map<String, String> outModels = new HashMap<String, String>();
			if(!qu.isIsreadonly()){
				for (int i = 0; i < qu.getListTest().size(); i++) {
					String te = qu.getListTest().get(i);
					String va = qu.getListValue().get(i);
					outModels.put(te, va);
				}
			}else{
				outModels.put(defreal, def);
			}
			writer.print(JSONObject.toJSONString(outModels));
			writer.flush();
			writer.close();
		} catch (Exception e) {
			log.error("获得输出模式异常！", e);
		}
	}

}
