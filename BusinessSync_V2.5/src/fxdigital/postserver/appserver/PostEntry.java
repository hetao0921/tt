package fxdigital.postserver.appserver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

/**
 * 邮局服务启动入口
 * @author fucz
 * @version 2014-6-30
 */
public class PostEntry {
	
	static {
		PropertyConfigurator.configure("conf/log4j.properties");
		System.setProperty("file.encoding", "UTF-8");
//		new ReadXml().read();
	}
 
	public static void main(String[] args) {
		new PostServer().run();
	}
	
	static class ReadXml{
		public void read(){
			InputStream is=this.getClass().getResourceAsStream("/conf/log4j.properties");   
			Properties pp = new Properties();
			try {
				pp.load(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
			PropertyConfigurator.configure(pp);
		}
	}

}
