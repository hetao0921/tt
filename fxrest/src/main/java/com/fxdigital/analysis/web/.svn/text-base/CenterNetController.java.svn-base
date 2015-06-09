package com.fxdigital.analysis.web;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fxdigital.analysis.bean.Center;
import com.fxdigital.analysis.service.SolidResService;

@Controller
@RequestMapping(value="v01/centernetwork")
public class CenterNetController {
	private static Log logger=LogFactory.getLog(CenterNetController.class);
	@Autowired
	private SolidResService solidResService;
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public List<Center> getCenterNet(){
		logger.info("come into getCenterNet function!");
		List<Center> list=solidResService.getCenterNet();
		return list;
	}
}
