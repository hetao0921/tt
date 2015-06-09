/**
 * 
 */
package com.fxdigital.analysis.web;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fxdigital.analysis.annotation.Generate;
import com.fxdigital.analysis.bean.Equality;
import com.fxdigital.analysis.bean.Notice;
import com.fxdigital.analysis.service.SetService;

/**
 * @author lizehua
 *
 */
@Controller
@RequestMapping("equality")
public class SetController  {
	private static Log logger=LogFactory.getLog(SetController.class);
	@Autowired
	private SetService setService;

	@RequestMapping(method=RequestMethod.GET,value="{type}")
	@ResponseBody
	public HashMap<String,Object> getListCenterInfo(@PathVariable("type")String type){
		logger.info("查询所有中心和平级中心信息 type="+type);
		HashMap<String,Object> map=setService.getListCenterInfo(type);
		return map;
	}
	
	/**
	 * 平级中心，三位一体设置
	 * 
	 * 解析json
	 * 生成sql;
	 * 插入sql
	 * 通知客户端
	 * 
	 * 
	
	 * 生成xml
	 * 将xml插入数据库
	 * @param json
	 */

	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public void setQuality(@RequestBody String json){
		Notice notice=new Notice();
		notice.setSenderType("CommandSystem");
/*		notice.setSenderId("");
		Equality equality=JSONObject.parseObject(json, Equality.class);
		String operate="1";
		equality.setOperate(operate);
		notice.setNoticeContent(json);*/
		setService.setQuality(json,notice);
		
	}
	/**
	 * 删除
	 * @param setId
	 */
	@RequestMapping(method=RequestMethod.DELETE,value="{setId}")
	@ResponseBody
	public void deleteQuality(@PathVariable("setId")String setId){

		Notice notice=new Notice();
/*		notice.setSenderType("CommandSystem");
		notice.setSenderId("");*/
		Equality equality=new Equality();
		String json=JSONObject.toJSONString(equality);
		/*equality.setSetId(setId);
		String operate="3";
		equality.setOperate(operate);
		notice.setNoticeContent(json);*/
		setService.setQuality(json,notice);
		
	
		
		
		
	}
	/**
	 * 修改
	 * @param json
	 */
	@RequestMapping(method=RequestMethod.PUT)
	@ResponseBody
	public void updateQuality(@RequestBody String json){

		Notice notice=new Notice();
		notice.setSenderType("CommandSystem");
/*		notice.setSenderId("");
		Equality equality=JSONObject.parseObject(json, Equality.class);
		String operate="2";
		equality.setOperate(operate);
		notice.setNoticeContent(json);*/
		setService.setQuality(json,notice);
		
	
		
		
		/*
		Equality equality=JSONObject.parseObject(json, Equality.class);
		setService.setQuality(equality,json);
	*/}



}
