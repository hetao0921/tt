package NVMP.jms.util;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.misc.log.LogUtil;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import NVMP.service.impl.GetValue;
import NVMP.service.impl.ServerIPConfig;
import NVMP.service.impl.ServerIPConfigs;





public class JNDIUtil {
	static InitialContext ctx = null;
	static TopicConnection topConn = null;
	static QueueConnection queueConn = null;
	static ServerIPConfig sic = null;
	static {
		init();
	}

	public static void init() {

		try {
			/**
			 * 只修改这里
			 * */
			Properties props = new Properties();
//			try {
//				// 加载类路径下的jndi.proterites文件
//				props.load(JNDIUtil.class.getClassLoader().getResourceAsStream(
//						"jndi.properties"));
//			} catch (FileNotFoundException fnfe) {
//				System.out.println("系统找不到jndi.properties文件");
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			System.out.println("=======================");
			
		
			 
			props.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
			//props.put("url", "http://127.0.0.1:8080/webservice/services/web"); 
			props.put("connectionFactoryNames", "connectionFactory, queueConnectionFactory, topicConnectionFactry ,connF");
			props.put("queue.clientQueue", "nvmp.clientQueue");
			props.put("queue.serverQueue", "nvmp.serverQueue");
//tcp://127.0.0.1:61616
			

			
			
			///////////////////////////注释此处代码     高江 2012.08.10////////////////////////////
			
			String ip = null;
			String port = null;
			try {
				List<String> ls = getSyncInfo();
				ip=ls.get(0);
				port = ls.get(1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				ip = "127.0.0.1";
				port = "5050";
	
			}
			String url = "tcp://"+ip+":"+port;
			
			
			///////////将上面代码注释后，换成一下代码，之前的同步服务IP和端口是从数据库中读取，
			///////////现在改为从FXH3120中心服务器上的webservice读取，读取不到，则一直阻塞在此处
			///////////判断如果是FXH3360的话，就在此处读取中心的信息
			if(isLicense().equals("FXH3360")){
				if(sic==null){
					sic = getServerIPConfig();
					upSyncIp(sic.getDeviceIp(),sic.getDevicePort());
					LogUtil.info("hahaha,has message from webservice。。。。");
					LogUtil.info("hahaha,ip:"+sic.getDeviceIp()+"===port:"+sic.getDevicePort());
					url = "tcp://"+sic.getDeviceIp()+":"+sic.getDevicePort();
				}
			}
			
			///////////////////////////////////////////////////////////////
			
			
//			String url = "tcp://"+ip+":"+port;
//          String url = "tcp://"+sic.getDeviceIp()+":"+sic.getDevicePort();
			
			
			
			props.put("java.naming.provider.url", url);
			ctx = new InitialContext(props);
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}
	


	public static void main(String[] args) {
//		try {
//			String s = (String) ctx.lookup("java.naming.provider.url");
//			System.out.println("url:" + s);
//		} catch (NamingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		ServerIPConfig sic = getServerIPConfig();
//		System.out.println("哈哈哈，仰天大笑出门去，对象终于获取到。。。"+sic);
	}

	public static TopicConnectionFactory lookupTopicConnectionFactory() {
		TopicConnectionFactory connf = null;
		try {
			connf = (TopicConnectionFactory) ctx.lookup("connectionFactory");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return connf;
	}

	public static QueueConnectionFactory lookupQueueConnectionFactory() {
		QueueConnectionFactory connf = null;
		try {
			connf = (QueueConnectionFactory) ctx.lookup("connF");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return connf;
	}

	// 此类维护两个Connection ，一个供server 一个供client ，其实这两个connection 没什么区别，
	public static TopicConnection getTopicConnection() {
		if (topConn == null) {
			try {
				topConn = lookupTopicConnectionFactory()
						.createTopicConnection();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		return topConn;
	}

	// 此类维护两个Connection ，一个供server 一个供client ，其实这两个connection 没什么区别，
	public static QueueConnection getQueueConnection() {
		if (queueConn == null) {
			try {
				queueConn = lookupQueueConnectionFactory()
						.createQueueConnection();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		return queueConn;
	}

	public static QueueConnection crateQueueConnection() {
		try {
			return lookupQueueConnectionFactory().createQueueConnection();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Topic lookupTopic(String topicName) {
		Topic topic = null;
		try {
			topic = (Topic) ctx.lookup(topicName);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return topic;
	}

	public static Queue lookupQueue(String queueName) {
		try {
			return (Queue) ctx.lookup(queueName);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void again() {
		topConn = null;
		queueConn = null;
		init();

	}

	public static void closeSession(Session session) {
		try {
			session.close();
			session = null;
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	//直接连接   效果是，读取 此表  127
	
	
	/**
	 * 获取mysql数据库IP
	 * @return
	 */
	private static String getDBIP(){
		String path = "";
		String ip = "";
		if (System.getProperty("os.name").equals("Linux")) 
		{
			path = "/etc/fxconf/AppService/AppService.conf";
		} else {
			path = "D:\\fxconf\\AppService\\AppService.conf";
		}
		File f = new File(path);
		if(f.exists()){
			try{
				SAXBuilder builder = new SAXBuilder();
				Document doc =  builder.build(new FileInputStream(new File(path)));
				Element root = doc.getRootElement();
				Element appE = root.getChild("AppServer");
				ip = appE.getAttributeValue("DBIP");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return ip;
	}
	

	
	private static Connection getConn() throws SQLException{
		String ip = "127.0.0.1";
		if(getDBIP()!=null||!getDBIP().equals(""))
			ip = getDBIP();
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://"+ip+":3306/NVMP?user=admin&password=111&characterEncoding=gbk&autoReconnect=true";
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	private static List<String> getSyncInfo() throws SQLException{
		String sql = "select * from nvmp_centerinfotab";
		List<String> list = null;
		Connection con = getConn();
		Statement stat = con.createStatement();
		ResultSet rst = stat.executeQuery(sql);
		if(rst.next()){
			list = new ArrayList<String>();
			String ip = rst.getString("SyncServerIP");
			String port = new Integer(rst.getInt("SyncServerPort")).toString();
//			list.add(new Integer(rst.getInt("SyncServerPort")).toString());
			if(Pattern.matches("\\d{1,3}[.]\\d{1,3}[.]\\d{1,3}[.]\\d{1,3}", ip))
				list.add(ip);
			else
				list.add("127.0.0.1");
			if(Pattern.matches("\\d{1,5}", port))
				list.add(port);
			else
				list.add("5050");
		}
		rst.close();
		stat.close();
		con.close();
		return list;
	}
	
	
	
	
	
	/////////////////////////////////以下代码是高江2012.08.10添加////////////////////////////////////
//	private static AllServer all;
//    /**
//     * 验证是否能够获取一个正确的webservice链接
//     */
//    private static boolean validateService(String str){
//    	URL url = null;
//    	boolean result = false;
//    	try {
//			url = new URL("http://"+str+":9527/NetService/gjj?wsdl");
//			AllService ser = new AllService(url,new QName("http://service.com/", "AllService"));
//	    	all = ser.getAllServerPort();
//	    	result = true;
//		} catch (Exception e) {
//			result = false;
//			e.printStackTrace();
//			
////			System.out.println("连接webservice失败，等待重新连接！");
//			LogUtil.info("connect webservice fail!we will connect again!!!");
//			LogUtil.info("Error Message:"+e.getMessage());
//		}
//		return result;
//    }
//    
//	
//	/**
//	 * 循环连接webservice，直到连接成功，返回一个ServerIPConfig对象，否则，一直连接。
//	 * @return
//	 */
//	public static ServerIPConfig getServerIPConfig(){
//		ServerIPConfig sic = null;
//		while(sic==null){
//			LogUtil.info("searching message in while......");
//			sic = getSync();
//			if(sic==null)
//				try{
//					LogUtil.info("没有获取到值，等待5秒再次获取。。");
//					Thread.sleep(5000);
//					
//				}catch(Exception e){
//					e.printStackTrace();
//				}
//			else
//				break;
//		}
//		return sic;
//	}
//	
	/**
	 * 获取mysql数据库IP
	 * @return
	 */
	private static String getCenterIP(){
		String path = "";
		String ip = "";
		if (System.getProperty("os.name").equals("Linux")) 
		{
			path = "/etc/fxconf/AppService/AppService.conf";
		} else {
			path = "D:\\fxconf\\AppService\\AppService.conf";
		}
		File f = new File(path);
		if(f.exists()){
			try{
				SAXBuilder builder = new SAXBuilder();
				Document doc =  builder.build(new FileInputStream(new File(path)));
				Element root = doc.getRootElement();
				Element appE = root.getChild("AppServer");
				ip = appE.getAttributeValue("IP");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return ip;
	}
	/**
	 * 获取同步服务信息，包括IP和端口
	 * @return
	 */
	private static ServerIPConfig getSync(){
		ServerIPConfig sip = null;
		String ip = getCenterIP();
		GetValue gv = new GetValue("http://"+ip+":8080/webservice/services/web");
		if(ip.equals(""))
			return sip;
		LogUtil.info("we are getting message from center:"+ip);
		ServerIPConfigs sics = gv.GetAllInfo();
		if(sics==null)
			return sip;
		if(sics.getList()==null)
			return sip;
		for(ServerIPConfig s:sics.getList()){
			if(s.getDeviceType().equals("FXH3360")&&s.getDeviceLevel().equals("local")){
				sip = s;
			}
		}
		return sip;
	}
	
	/**
	 * 循环连接webservice，直到连接成功，返回一个ServerIPConfig对象，否则，一直连接。
	 * @return 此处换另外一种连接方式连接
	 */
	public static ServerIPConfig getServerIPConfig(){
		ServerIPConfig sic = null;
		while(sic==null){
			LogUtil.info("searching message in while......");
			sic = getSync();
			if(sic==null)
				try{
					LogUtil.info("没有获取到值，等待5秒再次获取。。");
					Thread.sleep(5000);
					
				}catch(Exception e){
					e.printStackTrace();
				}
			else
				break;
		}
		return sic;
	}
	
	/**
	 * 修改同步服务	IP 
	 * @param ip
	 * @param port
	 */
	public static void upSyncIp(String ip,int port){
		String sql = "update nvmp_centerinfotab set CenterIP='"+ip+"',SyncServerIP='"+
		ip+"',SyncServerPort="+port;
		try{
			Connection conn = getConn();
			Statement stat = conn.createStatement();
			stat.execute(sql);
			stat.close();
			LogUtil.info("update syncIp success!");
		}catch(Exception e){
			LogUtil.info("update syncIp fail，fail message is :"+e.getMessage());
		}
	}
	
	
	/**
	 * 判断当前用户是否已经授权
	 * 如果未授权，返回null，否则返回设备类型
	 * 如：FXH3300、FXH3310、FXH312等
	 * @return
	 */
	private static String isLicense(){
		String path = "";
		if (System.getProperty("os.name").equals("Linux")) 
		{
			path = "/etc/fxconf/config/Device.xml";
		} else {
			path = "C:\\WINDOWS\\system32\\config\\Device.xml";
		}
		if(Common.IsExists(path)){
			Document doc = Common.getDocument(path);
			Element root = doc.getRootElement();
			String type = root.getAttributeValue("DeviceType");
			return type;
		}else{
			return "";
		}
	}
}