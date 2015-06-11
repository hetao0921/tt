package NVMP.service.impl;


import java.util.List;

import org.codehaus.xfire.XFire;
import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.misc.log.LogUtil;





public class GetValue{
	public GetValue(String url){
		this.url=url;
	}
	Service service =new ObjectServiceFactory().create(IWebservice.class);
	//����Web�������
	XFire xFire = XFireFactory.newInstance().getXFire();
	XFireProxyFactory factory=new XFireProxyFactory(xFire);
	//��������ַ
	String url="";
	
	
	public ServerIPConfigs GetAllInfo() {
		ServerIPConfigs sic = null;
		// TODO Auto-generated method stub
		try {
			IWebservice ig =(IWebservice)factory.create(service, url);
			sic = ig.GetAllInfo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			sic = null;
			LogUtil.info("there is a error in webservice:"+e.getMessage());
		}
		return sic;
	}

	
	public static void main(String...args){
		GetValue gv = new GetValue("http://192.168.1.23:8080/webservice/services/web");
		ServerIPConfigs sics = gv.GetAllInfo();
		List<ServerIPConfig> list = sics.getList();
		for(ServerIPConfig sic:list){
			System.out.println(sic.getDeviceId()+"==="+sic.getDeviceIp()+"==="
					+sic.getDeviceLevel()+"==="+sic.getDeviceName()+"==="+sic.getDevicePort()
					+"==="+sic.getDeviceType()+"==="+sic.getId());
		}
	}
}
