package com.fxdigital.analysis.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fxdigital.analysis.bean.Resources;
import com.fxdigital.analysis.service.SolidResService;

@Controller
@RequestMapping(value="v01/resource")
public class SolidResController {
	private static Log logger=LogFactory.getLog(SolidResController.class);
	@Autowired
	private SolidResService solidResService;
	@RequestMapping(method=RequestMethod.GET,value="/centerid/{centerid}/resourcetype/{resourcetype}")
	@ResponseBody
	public List<Resources> getSolidResources(@PathVariable("centerid")String centerId,@PathVariable("resourcetype")String resourceType){
		logger.info("come into getSolidResources function!centerId="+centerId+",resourceType="+resourceType);
		List<Resources> list=new ArrayList<Resources>();
		if(centerId==null&&"".equals(centerId)&&resourceType==null&&"".equals(resourceType)){
			return list;
		}
		if("all".equals(centerId)&&"all".equals(resourceType)){
			list=solidResService.getResources();
		}else if(!"all".equals(centerId)&&"all".equals(resourceType)){
			String[] centerids=centerId.split("\\|");
			list=solidResService.getResbyCenterid(centerids);
		}else if("all".equals(centerId)&&!"all".equals(resourceType)){
			String[] resourcetypes=resourceType.split("\\|");
			list=solidResService.getResbyResType(resourcetypes);
		}else{
			String[] centerids=centerId.split("\\|");
			String[] resourcetypes=resourceType.split("\\|");
			list=solidResService.getResbyAll(centerids, resourcetypes);
		}
		return list;
	}
}
