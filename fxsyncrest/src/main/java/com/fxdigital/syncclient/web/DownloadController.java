package com.fxdigital.syncclient.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fxdigital.syncclient.bean.SyncVersion;
import com.fxdigital.syncclient.service.DownLoad;

@Controller
public class DownloadController {
	private static Logger logger = Logger.getLogger(DownloadController.class);
	

	@Autowired
	private DownLoad downLoad;


	
	/**
	 * 如果 本地版本号和服务器版本号都是0 不询问服务器
	 * 
	 * 如果本地版本号等于服务器版本号，询问服务器版本号
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public SyncVersion getAllversion(){
		HashMap<String,String> versions=new HashMap<String,String>();
		SyncVersion version=null;
		return version;
		
	}
	/**上传如果标志为1表示正在上传中，直接返回
	 * 
	 * 如果标志为0 置为1，
	 * 上传轮询标志位，如果标志位为1，发送消息，成功 ，将本地服务器版本号赋值给本地版本号
	 * 
	 * @return
	 */
	// 获取上传成功返回标志
	@RequestMapping(value = { "/download/getdownresult" }, produces="text/plain;charset=UTF-8",method = RequestMethod.POST)	
	@ResponseBody
	public HashMap<String,String> upload(){
		HashMap<String,String> result=new HashMap<String,String>();
		/*result=uploadService.upload();*/
		List<HashMap<String, String>> localversion=downLoad.getOldLoadVersionInfo();
		return result;
	}
	
	
	// 获取上传成功返回标志
	@RequestMapping(value = { "/download/getdownversion" }, produces="text/plain;charset=UTF-8",method = RequestMethod.POST)	
	@ResponseBody
	public String getdownversion(){
		logger.info("调用getdownversion");
		List<HashMap<String, String>> localversion=downLoad.getOldLoadVersionInfo();
		String strResult=JSONObject.toJSONString(localversion);
		return strResult;
		
	}
	
	// 获取上传成功返回标志
	@RequestMapping(value = { "/download/getmergeloadversion" }, produces="text/plain;charset=UTF-8",method = RequestMethod.POST)	
	@ResponseBody
	public List<HashMap<String, String>> getMergeLoadVersion(){
		logger.info("调用getMergeLoadVersion");
		List<HashMap<String, String>> lastversion=downLoad.getMergeLoadVersion();
		
		return lastversion;
	}
	
}
