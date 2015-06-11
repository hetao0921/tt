package com.xfire.client;

import java.io.Reader;
import java.net.MalformedURLException;

import javax.xml.ws.Service;

import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;

import com.xfire.bean.ReaderBean;
import com.xfire.webservice.IReaderService;

public class ReaderClient {
	public static void main(String[] args) {
		  //这里是创建一个service，需要传入一个接口类，因为我们后面必须调用相应的接口方法  
        org.codehaus.xfire.service.Service srcModel = new ObjectServiceFactory().create(IReaderService.class);  
        //代理工厂，这里是为了后面创建相应的接口类  
        XFireProxyFactory factory = new XFireProxyFactory(XFireFactory.newInstance().getXFire());  
        //webservice地址，不需要加wsdl  
        String readerServiceUrl = "http://localhost:8080/xfireWebService/services/readerService";  
          
        try {  
            //利用工厂返回相应的接口类  
            IReaderService readerService = (IReaderService)factory.create(srcModel,readerServiceUrl);  
            String example=readerService.example("ttt");
            System.out.println(example);
            ReaderBean reader = readerService.getReader("shun","123");  
            System.out.println(reader.getName()+" "+reader.getPassword()); 
            System.out.println(reader.toString()); 
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        }  
    
	}

}
