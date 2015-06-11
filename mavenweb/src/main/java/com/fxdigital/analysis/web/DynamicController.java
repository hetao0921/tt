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

import com.fxdigital.analysis.bean.Notice;
import com.fxdigital.analysis.bean.ResourceGroup;
import com.fxdigital.analysis.service.DynamicService;

@Controller
@RequestMapping(value="v01/resourcegroup")
public class DynamicController {
	private static Log logger=LogFactory.getLog(DynamicController.class);
	@Autowired
	private DynamicService dynamicService;
	

	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public void addGroup(@RequestBody String json){
		logger.info("动态分组json:"+json);
		Notice notice=new Notice ();
		notice.setAction("add");
		notice.setSenderType("SystemManage");
		notice.setNoticeType("ResourceGroupChanged");
		
//		ResourceGroup group=JSONObject.parseObject(json, ResourceGroup.class);
		dynamicService.addGroup(json,notice);
		
	}
	@RequestMapping(method=RequestMethod.PUT)
	@ResponseBody
	public void updateGroup(@RequestBody String json){
		logger.info("动态分组json:"+json);
		Notice notice=new Notice();
		notice.setAction("update");
		notice.setSenderType("SystemManage");
		notice.setNoticeType("ResourceGroupChanged");
//		ResourceGroup group=JSONObject.parseObject(json, ResourceGroup.class);
		dynamicService.addGroup(json,notice);
		
	}
	@RequestMapping(method=RequestMethod.DELETE,value="groupid/{groupId}")
	@ResponseBody
	public void deleteGroup(@PathVariable("groupId") String groupId){
		logger.info("动态分组删除:"+groupId);
		Notice notice=new Notice ();
		notice.setAction("update");
		notice.setSenderType("SystemManage");
		notice.setNoticeType("ResourceGroupChanged");
//		ResourceGroup group=JSONObject.parseObject(json, ResourceGroup.class);
		dynamicService.deleteGroup(groupId,notice);
		
	}
	//根据中心Id查找组信息
	@RequestMapping(method=RequestMethod.GET,value="/centerid/{centerId}/grouptype/{groupType}")
	@ResponseBody
	public List<ResourceGroup> getGroup(@PathVariable("centerId")String centerId,@PathVariable("groupType")String groupType){
		logger.info("come into getGroup funtion, centerId="+centerId+",groupType="+groupType);
		List<ResourceGroup> list=new ArrayList<ResourceGroup>();
		if(null==centerId&&"".equals(centerId)&&null==groupType&&"".equals(groupType)){
			return list;
		}
		if("all".equals(centerId)&&"all".equals(groupType)){
			list=dynamicService.getGroupinfo();
		}else if(!"all".equals(centerId)&&"all".equals(groupType)){
			String[] centerids=centerId.split("\\|");
			list=dynamicService.getGroupsByCenters(centerids);
		}else if("all".equals(centerId)&&!"all".equals(groupType)){
			String[] grouptypes=groupType.split("\\|");
			list=dynamicService.getGroupByType(grouptypes);
		}else{
			String[] centerids=centerId.split("\\|");
			String[] grouptypes=groupType.split("\\|");
			list=dynamicService.getGroupByAll(centerids, grouptypes);
		}
		return list;
	}
	//根据组Id查找组信息
	@RequestMapping(method=RequestMethod.GET,value="/groupid/{groupId}")
	@ResponseBody
	public ResourceGroup getGroupbygid(@PathVariable("groupId")String groupId){
		logger.info("come into getGroupbyid funtion, centerId="+groupId);
		ResourceGroup group =new ResourceGroup();
		List<ResourceGroup> list=new ArrayList<ResourceGroup>();
		if(null==groupId&&"".equals(groupId)){
			return null;
		}
		String[] groupids=groupId.split("\\|");
		list=dynamicService.getGroupBygid(groupids);
		if(list!=null&&list.size()!=0){
			group=list.get(0);
			
		}
		return group;
	}
	//根据资源Id和组类型查找分组信息
	@RequestMapping(method=RequestMethod.GET,value="/resourceid/{resourceId}/grouptype/{groupType}")
	@ResponseBody
	public List<ResourceGroup> getGroupbyrid(@PathVariable("resourceId")String resourceId,@PathVariable("groupType")String groupType){
		logger.info("come into getGroupbyid funtion, centerId="+resourceId);
		List<ResourceGroup> list=new ArrayList<ResourceGroup>();
		if(null==resourceId&&"".equals(resourceId)&&null==groupType&&"".equals(groupType)){
			return list;
		}
		if("all".equals(resourceId)&&"all".equals(groupType)){
			list=dynamicService.getGroupByallrid();
		}else if(!"all".equals(resourceId)&&"all".equals(groupType)){
			String[] resourceids=resourceId.split("\\|");
			list=dynamicService.getGroupByrid(resourceids);
		}else if("all".equals(resourceId)&&!"all".equals(groupType)){
			String[] grouptypes=groupType.split("\\|");
			list=dynamicService.getGroupByType(grouptypes);
		}else{
			String[] resourceids=resourceId.split("\\|");
			String[] grouptypes=groupType.split("\\|");
			list=dynamicService.getGroupByallrg(resourceids, grouptypes);
		}
		return list;
	}
	//根据用户Id和组类型查找组信息
	@RequestMapping(method=RequestMethod.GET,value="/userid/{userId}/grouptype/{groupType}")
	@ResponseBody
	public List<ResourceGroup> getGroupbyuid(@PathVariable("userId")String userId,@PathVariable("groupType")String groupType){
		logger.info("come into getGroupbyid funtion, centerId="+userId+",groupType="+groupType);
		List<ResourceGroup> list=new ArrayList<ResourceGroup>();
		if(null==userId&&"".equals(userId)&&null==groupType&&"".equals(groupType)){
			return list;
		}
		if("all".equals(userId)&&"all".equals(groupType)){
			list=dynamicService.getGroupByallutype();
		}else if(!"all".equals(userId)&&"all".equals(groupType)){
			String[] userids=userId.split("\\|");
			list=dynamicService.getGroupByalluser(userids);
		}else if("all".equals(userId)&&!"all".equals(groupType)){
			String[] grouptypes=groupType.split("\\|");
			list=dynamicService.getGroupByType(grouptypes);
		}else{
			String[] userids=userId.split("\\|");
			String[] grouptypes=groupType.split("\\|");
			list=dynamicService.getGroupByallutypes(userids, grouptypes);
		}
		return list;
	}
}
