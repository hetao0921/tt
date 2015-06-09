package com.fxdigital.syncclient.web;

import java.io.UnsupportedEncodingException;
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
import com.fxdigital.syncclient.service.ResetLoad;
import com.fxdigital.syncclient.service.UpLoad;

@Controller
public class RestloadController {
	private static Logger logger = Logger.getLogger(RestloadController.class);
	

	@Autowired
	private UpLoad upLoad;
	@Autowired
	private ResetLoad resetLoad;
	

	/**上传如果标志为1表示正在上传中，直接返回
	 * 
	 * 如果标志为0 置为1，
	 * 上传轮询标志位，如果标志位为1，发送消息，成功 ，将本地服务器版本号赋值给本地版本号
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/restload/testsendresetcommand" }, produces="text/plain;charset=UTF-8",method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,String> upload(){
		HashMap<String,String> result=new HashMap<String,String>();
		/*result=uploadService.upload();*/
		List<String> list=upLoad.getUpVersionInfo();
		List<String> strList=upLoad.getUpVersionList();
		logger.info("需要上传的版本号列表:"+strList);
		if(null!=strList&&strList.size()>0){
//			upLoad.sendUpCommand(strList);
//			resetLoad.getResetVersionInfo();
			resetLoad.sendResetCommand("000BAB65C2ED@001", "车载8142",
					"142");
			
			
		}
		
		return result;
	}
	
	
	@RequestMapping(value = { "/restload/getresetversioninfo" }, produces="text/plain;charset=UTF-8",method = RequestMethod.POST)
	@ResponseBody
	public String getresetversioninfo(){
		logger.info("进入还原调用的函数:getresetversioninfo");
		 return resetLoad.getResetVersionInfo();
	}
	
	@RequestMapping(value = { "/restload/getlastrestversion" }, produces="text/plain;charset=UTF-8",method = RequestMethod.POST)
	@ResponseBody
	public List<HashMap<String, String>> getlastrestversion(){
		return resetLoad.getLastResetVersion();
	}
	
	
	@RequestMapping(value = { "/restload/sendresetcommand" }, produces="text/plain;charset=UTF-8",method = RequestMethod.POST)
	@ResponseBody
	public String sendresetcommand(@RequestBody String list) throws UnsupportedEncodingException{
		logger.info("进入sendresetcommand方法！！！");
		logger.info(" list "+list);
		List<String> strList=JSONObject.parseObject(list, List.class);
		
		String centerid=strList.get(0);
		String centername=strList.get(1);
		logger.info(" centername1 "+centername);
		String version=strList.get(2);
		logger.info(" centername2 "+centername);
		return resetLoad.sendResetCommand(centerid, centername,
				version);
	}
	
	
	@RequestMapping(value = { "/restload/getrestback" }, produces="text/plain;charset=UTF-8",method = RequestMethod.POST)
	@ResponseBody
	public String getrestback(){
		return resetLoad.getResetBack();
	}
}
