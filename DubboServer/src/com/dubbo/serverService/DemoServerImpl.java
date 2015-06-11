package com.dubbo.serverService;

import java.util.Date;

public class DemoServerImpl implements DemoServer{
	public String sayHello(String str) {  
        str = "Hello " + str + "2:" + new Date();  
        System.out.println("server:" + str);  
        return str;  
    }  
}
