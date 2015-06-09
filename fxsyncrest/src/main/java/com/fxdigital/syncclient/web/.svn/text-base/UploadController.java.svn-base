package com.fxdigital.syncclient.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fxdigital.syncclient.bean.SyncVersion;
import com.fxdigital.syncclient.service.ResetLoad;
import com.fxdigital.syncclient.service.UpLoad;
import com.fxdigital.syncclient.service.UploadService;

@Controller
public class UploadController {
	private static Logger logger = Logger.getLogger(UploadController.class);

	@Autowired
	private UploadService uploadService;
	@Autowired
	private UpLoad upLoad;
	@Autowired
	private ResetLoad resetLoad;
	
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
		SyncVersion version=uploadService.getLocalVersion();
		return version;
		
	}
	/**上传如果标志为1表示正在上传中，直接返回
	 * 
	 * 如果标志为0 置为1，
	 * 上传轮询标志位，如果标志位为1，发送消息，成功 ，将本地服务器版本号赋值给本地版本号
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/upload" }, method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,String> upload(){
		HashMap<String,String> result=new HashMap<String,String>();
		/*result=uploadService.upload();*/
		List<String> list=upLoad.getUpVersionInfo();
		List<String> strList=upLoad.getUpVersionList();
		logger.info("需要上传的版本号列表:"+strList);
		if(null!=strList&&strList.size()>0){
			upLoad.sendUpCommand(strList);
//			resetLoad.getResetVersionInfo();
//			resetLoad.sendResetCommand("000BAB65C2ED@001", "车载8142",
//					"142");
			
			
		}
		
		return result;
	}
	
	// 获取本地版本号和锁ID
	@RequestMapping(value = { "/upload/getuploadversion" }, method = RequestMethod.POST)	
	@ResponseBody
	public String getnativever() {
		logger.info("进入getnativever方法！！！");
		List<String> list=upLoad.getUpVersionInfo();
		String returnStr=JSONObject.toJSONString(list);
		return returnStr;
		}
	
	// 获取本地版本号和锁ID
	@RequestMapping(value = { "/upload/sendupcommand" }, method = RequestMethod.POST)	
	@ResponseBody
	public String sendupcommand(@RequestBody String list) {
		logger.info("进入sendupcommand方法！！！");
		logger.info("界面传递过来的参数："+list);
		List<String> strList=JSONObject.parseObject(list, List.class);
		logger.info("界面上传递过来的参数为："+strList);
		String strReturn="";
		if(null!=strList&&strList.size()>0){
			strReturn=upLoad.sendUpCommand(strList);
		}
		return strReturn;	
	}
	
	
	// 获取本地版本号和锁ID
	@RequestMapping(value = { "/upload/getserverversion" }, method = RequestMethod.POST)	
	@ResponseBody
	public String getserverversion() {
		logger.info("进入getserverversion方法！！！");
		List<String> list=new ArrayList<String>();
		String serverVersion=upLoad.getUpVersion();
		String nextVersion=upLoad.getNextVersion();
		list.add(serverVersion);
		list.add(nextVersion);
		String returnStr=JSONObject.toJSONString(list);
		return returnStr;	
	}
	
	
	// 获取上传成功返回标志
	@RequestMapping(value = { "/upload/getupback"}, produces="text/plain;charset=UTF-8",method = RequestMethod.POST)	
	@ResponseBody
	public String getupback() {
		logger.info("进入getupback方法！！！");
		String upBack=upLoad.getUpBack();
		return upBack;
	}
	
	
	// 获取上传成功返回标志
	@RequestMapping(value = { "/upload/getupresult" }, produces="text/plain;charset=UTF-8",method = RequestMethod.POST)	
	@ResponseBody
	public String getupresult() {
		logger.info("进入getupresult方法！！！");
		List<HashMap<String, String>> upResult=upLoad.getUpResult("", "");
		logger.info("upResult  "+upResult);
		String returnStr=JSONObject.toJSONString(upResult);
		return returnStr;
	}
	
}
