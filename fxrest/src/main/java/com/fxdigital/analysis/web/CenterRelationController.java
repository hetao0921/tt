package com.fxdigital.analysis.web;

import java.util.ArrayList;
import java.util.List;

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
import com.fxdigital.analysis.bean.CenterRelation;
import com.fxdigital.analysis.bean.Notice;
import com.fxdigital.analysis.service.CenterRelationService;

@Controller
@RequestMapping("v01/centerrelation")
public class CenterRelationController {
	private static Log logger=LogFactory.getLog(CenterRelationController.class);
	@Autowired
	private CenterRelationService service;
	/**
	 * 根据relationid 查找中心关系
	 * @param relationId
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET,value="relatedcenterid/{relatedCenterId}/relationtype/{relationType}")
	@ResponseBody
	public List<CenterRelation> getCenterRelationbyRelationId(@PathVariable("relatedCenterId")String relatedCenterId,@PathVariable("relationType")String relationType){
		logger.info("根据中心关系ID 查询relatedCenterId="+relatedCenterId);
		List<CenterRelation> list=new ArrayList<CenterRelation>();
		String[] relatedCenterIDs=relatedCenterId.split("\\|");
		list=service.getCenterRelationbyRelationId(relatedCenterIDs,relationType);
		return list;
		
	}
	/**
	 * 根据 centerId relationType查找中心关系
	 * @param centerId
	 * @param relationType
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET,value="centerid/{centerId}/relationtype/{relationType}")
	@ResponseBody
	public List<CenterRelation> getCenterRelationbycenterid(@PathVariable("centerId")String centerId,@PathVariable("relationType")String relationType){
		logger.info("根据centerId,relationType 查询centerId="+centerId+"--relationType="+relationType);
		List<CenterRelation> list=new ArrayList<CenterRelation>();
		String[] centerIds=centerId.split("\\|");
		list=service.getCenterRelationbycenterid(centerIds, relationType);
		return list;
		
	}
	/**
	 * 添加中心关系
	 * @param json
	 */
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public void addCenterRelation(@RequestBody String json){
		logger.info("添加中心关系:"+json);
		Notice notice=new Notice();

		service.addCenterRelation(json, notice);
	}
	/**
	 * 根据relationid删除中心关系
	 * @param relationId
	 */
	@RequestMapping(method=RequestMethod.DELETE,value="relationid/{relationId}")
	@ResponseBody
	public void DeleteCenterRelation(@PathVariable("relationId")String relationId){
		logger.info("删除中心关系relationId="+relationId);
		CenterRelation relation=new CenterRelation();
		relation.setRelationId(relationId);
		String json=JSONObject.toJSONString(relation);
		Notice notice=new Notice();
//		notice.setNoticeContent(json);
//		notice.setSenderType("CommandSystem");
		service.addCenterRelation(json, notice);
	}

}
