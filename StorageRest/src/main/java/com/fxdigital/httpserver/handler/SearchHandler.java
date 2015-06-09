package com.fxdigital.httpserver.handler;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;
import com.fxdigital.httpserver.handler.util.HandlerUtil;
import com.fxdigital.search.bean.NvmpRecordBaseSearch;
import com.fxdigital.storage.service.impl.SearchService;
import com.sun.net.httpserver.HttpExchange;
@Controller
public class SearchHandler extends BaseHandler { 
	
	private static final Logger logger = Logger.getLogger(SearchHandler.class);
	
	@Autowired
	private SearchService searchService;
	
    public void handle(HttpExchange httpExchange,String falg) throws IOException {  
    	HandlerUtil handlerUtil=new HandlerUtil();
    	String requestStr=handlerUtil.processInput(httpExchange);
        logger.info("client requestStr:"+requestStr);
    	HashMap tempMap=handlerUtil.processSearch(requestStr);
    	String deviceid=String.valueOf(tempMap.get("deviceid"));
    	int channel=Integer.valueOf(tempMap.get("channel").toString());
    	String starttime=String.valueOf(tempMap.get("starttime"));
    	String endtime=String.valueOf(tempMap.get("endtime"));
    	List<NvmpRecordBaseSearch> list=searchRecord(deviceid,channel,starttime,endtime);
        logger.info("查询出来的数据为: "+list.size() +list); 
        String json=JSONObject.toJSONString(list);
        handlerUtil.processOutput(httpExchange,json);  
          
    }  
    
    
    
    public List<NvmpRecordBaseSearch> searchRecord(String deviceid,int channel,String starttime,String endtime){

		
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
