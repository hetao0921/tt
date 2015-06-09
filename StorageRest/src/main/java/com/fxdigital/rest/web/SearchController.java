package com.fxdigital.rest.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fxdigital.search.bean.NvmpRecordBaseSearch;
import com.fxdigital.storage.service.impl.SearchService;
@Controller
public class SearchController {
	private static final Logger logger = Logger.getLogger(SearchController.class);
	
	@Autowired
	private SearchService searchService;
	

	@RequestMapping(value = { "/searchrecord" }, method = RequestMethod.GET,produces="text/html;charset=UTF-8")
	@ResponseBody
	public Object upload(@RequestParam("deviceid") String deviceid,
			@RequestParam("channel") int channel,@RequestParam("starttime") String starttime,@RequestParam("endtime") String endtime){
		
		List<NvmpRecordBaseSearch> result= new ArrayList<NvmpRecordBaseSearch>();

//		String jsonObj;
	
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("mintime", starttime);
		map.put("maxtime", endtime);

		HashMap<String, Object> subMap = new HashMap<String, Object>();
		subMap.put("deviceid", deviceid);
		subMap.put("channel", channel);
		map.put("mark", subMap);
		
		String jsonObj = JSONObject.toJSONString(map);
		
        System.out.println("test"+deviceid+" "+channel);

//        jsonObj = "{'mark':{'a':1,'b':2,'c':3},'mintime':'2015-04-17 09:38:21.0','maxtime':'2015-04-17 09:39:21.0'}";
//      System.out.println("jsonObj:"+jsonObj); 
        result =  searchService.search(jsonObj);
//
//        System.out.println("test"+deviceid);
// //       searchService.search(jsonpara);
        if(null!=result&&result.size()>0){
        logger.info("返回结果json "+JSONObject.toJSONString(result));
        }
        logger.info("result  "+result);
		return result;
	}
	
	

}
