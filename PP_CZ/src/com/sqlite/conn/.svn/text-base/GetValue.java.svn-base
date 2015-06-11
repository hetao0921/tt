package com.sqlite.conn;

import java.net.MalformedURLException;

import org.codehaus.xfire.XFire;
import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;

public class GetValue  {
	public GetValue(String url){
		this.url=url;
	}
	Service service =new ObjectServiceFactory().create(IGetwebservice.class);
	//����Web�������
	XFire xFire = XFireFactory.newInstance().getXFire();
	XFireProxyFactory factory=new XFireProxyFactory(xFire); 
	//��������ַ
	String url="";
	
	/**
	 * ��ȡ�豸��Ϣ
	 * @return
	 */
	public String[][] getDeviceServer(){
		String[][] getAllDeviceInfo=null;
		//���Web������ö���
		try {
			IGetwebservice ig =(IGetwebservice)factory.create(service, url);
			//System.out.println("���õ���GetAllDevice()");
	        getAllDeviceInfo=ig.GetAllDeviceInfo();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getAllDeviceInfo;
	}
	
	/**
	 * ���ͨ�����ͣ���ѯ��Ӧ������ͷ������㡢��������Ϣ
	 * @param typeID
	 * @return
	 */
	public String[][] getTongDao(String typeID){
		String[][] getAllDeviceInfo=null;
		//���Web������ö���
		try {
			IGetwebservice ig =(IGetwebservice)factory.create(service, url);
			//System.out.println("���õ���GetAllDevice()");
	        getAllDeviceInfo=ig.GetDeviceInfo(typeID);  
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getAllDeviceInfo;
	}
	
	
//	public void init(){
//		BaseDaoImpl impl = new BaseDaoImpl();
//		//GetValue g=new GetValue();
//		String [] sqls=new String[4];
//		//�����豸״̬�?������������
//		//�ñ��ֶ�����Ϊ    �豸ID���豸״̬���������������
//		sqls[0]="create table DeviceStatus(DID integer,DeviceID varchar(64),CenterID varchar(64));";
//		//����������?������������
//		//�ñ��ֶ�����Ϊ     �豸ID��ͨ��ID���豸״̬
//		sqls[1]="create table BaoJing(bid integer,DeviceID varchar(64),TongDaoID integer," +
//				"DeviceStatus integer);";
//		//���������?������������
//		sqls[2]="create table ShuChu(bid integer,DeviceID varchar(64),TongDaoID integer," +
//				"DeviceStatus integer);";
//		//���������?������������
//		sqls[3]="create table SheXiang(bid integer,DeviceID varchar(64),TongDaoID integer," +
//				"DeviceStatus integer);";
//		
//		if(impl.updateInfo(sqls)){
//			System.out.println("��ʼ�����ɹ�........");
//		}else{
//			System.out.println("��ʼ�����ʧ��........");
//		}
//		
////		int len = getDeviceServer().length-1;
////		String [] sqlss = new String[len];
//		for(int i=1;i<getDeviceServer().length;i++){
//			for(int j=0;j<getDeviceServer()[i].length;j++){
//				
//			}
//		}
//	}
	
	
	public void temp(){
		Service service =new ObjectServiceFactory().create(IGetwebservice.class);
		//����Web�������
		XFire xFire = XFireFactory.newInstance().getXFire();
		XFireProxyFactory factory=new XFireProxyFactory(xFire);
		//��������ַ
//		String url="http://192.168.1.244:8080/webservice/services/web";
		//���Web������ö���
		try {
			IGetwebservice ig =(IGetwebservice)factory.create(service, url);
			
			
			System.out.println("���õ���GetAllDevice(),�����豸ͨ����");
	        String[][] getAllDeviceInfo=ig.GetAllDeviceInfo();
//	        String[][] ss=null;
	        String show="";
	        for(int i=0;i<getAllDeviceInfo.length;i++){
	        	for(int j=0;j<getAllDeviceInfo[i].length;j++){
	        		show+=getAllDeviceInfo[i][j]+"  ";
	        	}
		        	show+="\r\n";
	        }
	        System.out.println(show);
//	        
//	        
//	        
//	        
//	        
//	        System.out.println("������GetDeviceInfo����,�����豸ͨ����Ϣ ");
//	        String[][] getDeviceInfoStrings=ig.GetDeviceInfo("0ad6cf8cffe64c1797d7f956c3d3b1bc", "3");
//	        String getDeviceInfos="";
//	        for(String[] s:getDeviceInfoStrings)
//	        {
//	        	for(String strValue:s)
//	        	{
//	        		getDeviceInfos+=strValue+"    ";
//	        	}
//	        	getDeviceInfos+="\r\n";
//	        }
//	        System.out.println(getDeviceInfos);
	        
//	        
//	        
//	        
//	        
//	        
//	        
	        System.out.println("������GetDeviceInfo����tongdao ");
	        String[][] getDevfos=ig.GetDeviceInfo("3");
	        String getD="";
	        for(String[] s:getDevfos)
	        {
	        	for(String strValue:s)
	        	{
	        		getD+=strValue+"    ";
	        	}
	        	getD+="\r\n";
	        }
	        System.out.println(getD);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 获得计划信息
	 * @param sessionId
	 * @return
	 */
	public String[][] getPlan(String sessionId){
		Service service =new ObjectServiceFactory().create(IGetwebservice.class);
		String[][] getDevfos = null;
		XFire xFire = XFireFactory.newInstance().getXFire();
		XFireProxyFactory factory=new XFireProxyFactory(xFire);
		try {
			IGetwebservice ig =(IGetwebservice)factory.create(service, url);       
//	        System.out.println("获取计划信息 ");
	        getDevfos=ig.GetTimePlan(sessionId);
		}catch(Exception e){
			System.out.println("WebService获取数据失败");
			e.printStackTrace();
		}
		return getDevfos;
	}
	
	/**
	 * 获得更新后的计划信息
	 * @param ID
	 * @param SessionID
	 * @return
	 */
	public String[][] GetTimePlanList(int ID,String SessionID){
		Service service =new ObjectServiceFactory().create(IGetwebservice.class);
		String[][] getDevfos = null;
		XFire xFire = XFireFactory.newInstance().getXFire();
		XFireProxyFactory factory=new XFireProxyFactory(xFire);
		try {
			IGetwebservice ig =(IGetwebservice)factory.create(service, url);       
//	        System.out.println("获取计划信息 ");
	        getDevfos=ig.GetTimePlanList(ID, SessionID);
		}catch(Exception e){
			System.out.println("WebService获取数据失败");
			e.printStackTrace();
		}
		return getDevfos;
	}
	
	/**
	 * 获得所有设备计划
	 * @return
	 */
	public String [][] GetTimePlanAll(){
		Service service =new ObjectServiceFactory().create(IGetwebservice.class);
		String[][] getDevfos = null;
		XFire xFire = XFireFactory.newInstance().getXFire();
		XFireProxyFactory factory=new XFireProxyFactory(xFire);
		try {
			IGetwebservice ig =(IGetwebservice)factory.create(service, url);       
//	        System.out.println("获取计划信息 ");
	        getDevfos=ig.GetTimePlanAll();
		}catch(Exception e){
			System.out.println("WebService获取数据失败");
			e.printStackTrace();
		}
		return getDevfos;
	}
	
	/**
	 * 获取中心网络信息
	 * @return
	 */
	public String [][] getCenterNetworkInfo(String ip){
		Service service =new ObjectServiceFactory().create(IGetwebservice.class);
		String[][] getDevfos = null;
		XFire xFire = XFireFactory.newInstance().getXFire();
		XFireProxyFactory factory=new XFireProxyFactory(xFire);
		try {
			IGetwebservice ig =(IGetwebservice)factory.create(service, url);       
//	        System.out.println("获取计划信息 ");
	        getDevfos=ig.getCenterNetworkInfo(ip);
		}catch(Exception e){
			System.out.println("WebService获取数据失败");
			e.printStackTrace();
		}
		return getDevfos;
	}
	
	public String[][] GetCenterNetWorkInfoTab(){
		Service service =new ObjectServiceFactory().create(IGetwebservice.class);
		String[][] getDevfos = null;
		XFire xFire = XFireFactory.newInstance().getXFire();
		XFireProxyFactory factory=new XFireProxyFactory(xFire);
		try {
			IGetwebservice ig =(IGetwebservice)factory.create(service, url);       
//	        System.out.println("获取计划信息 ");
	        getDevfos=ig.GetCenterNetWorkInfoTab();
		}catch(Exception e){
			System.out.println("WebService获取数据失败");
			e.printStackTrace();
		}
		return getDevfos;
	}
	
	public String[][] getCenterinfosynctab(){
		Service service =new ObjectServiceFactory().create(IGetwebservice.class);
		String[][] getDevfos = null;
		XFire xFire = XFireFactory.newInstance().getXFire();
		XFireProxyFactory factory=new XFireProxyFactory(xFire);
		try {
			IGetwebservice ig =(IGetwebservice)factory.create(service, url);       
//	        System.out.println("获取计划信息 ");
	        getDevfos=ig.getCenterinfosynctab();
		}catch(Exception e){
			System.out.println("WebService获取数据失败");
			e.printStackTrace();
		}
		return getDevfos;
	}

	public String[][] GetCenterRouteInfoTab(){
		Service service =new ObjectServiceFactory().create(IGetwebservice.class);
		String[][] getDevfos = null;
		XFire xFire = XFireFactory.newInstance().getXFire();
		XFireProxyFactory factory=new XFireProxyFactory(xFire);
		try {
			IGetwebservice ig =(IGetwebservice)factory.create(service, url);       
//	        System.out.println("获取计划信息 ");
	        getDevfos=ig.GetCenterRouteInfoTab();
		}catch(Exception e){
			System.out.println("WebService获取数据失败");
			e.printStackTrace();
		}
		return getDevfos;
	}

	public String[][] GetCenterInfoTab(){
		Service service =new ObjectServiceFactory().create(IGetwebservice.class);
		String[][] getDevfos = null;
		XFire xFire = XFireFactory.newInstance().getXFire();
		XFireProxyFactory factory=new XFireProxyFactory(xFire);
		try {
			IGetwebservice ig =(IGetwebservice)factory.create(service, url);       
//	        System.out.println("获取计划信息 ");
	        getDevfos=ig.GetCenterInfoTab();
		}catch(Exception e){
			System.out.println("WebService获取数据失败");
			e.printStackTrace();
		}
		return getDevfos;
	}
	
	
	public String[][] Getcommanddevtab(){
		Service service =new ObjectServiceFactory().create(IGetwebservice.class);
		String[][] getDevfos = null;
		XFire xFire = XFireFactory.newInstance().getXFire();
		XFireProxyFactory factory=new XFireProxyFactory(xFire);
		try {
			IGetwebservice ig =(IGetwebservice)factory.create(service, url);       
//	        System.out.println("获取计划信息 ");
	        getDevfos=ig.Getcommanddevtab();
		}catch(Exception e){
			System.out.println("WebService获取数据失败");
			e.printStackTrace();
		}
		return getDevfos;
	}
	
	public static void testPlan(){
		Service service =new ObjectServiceFactory().create(IGetwebservice.class);
		//锟斤拷锟斤拷Web锟斤拷锟斤拷锟斤拷锟?
		XFire xFire = XFireFactory.newInstance().getXFire();
		XFireProxyFactory factory=new XFireProxyFactory(xFire);
		String u = "http://192.168.1.123:8080/webservice/services/web";
		try {
			IGetwebservice ig =(IGetwebservice)factory.create(service, u);       
	        System.out.println("获取计划信息 ");
	        String[][] getDevfos=ig.GetTimePlan("Test");
	        String getD="";
	        for(String[] s:getDevfos)
	        {
	        	for(String strValue:s)
	        	{
	        		getD+=strValue+"    ";
	        	}
	        	getD+="\r\n";
	        }
	        System.out.println(getD);
	        System.out.println("===================================");
	        System.out.println("集合大小："+getDevfos.length);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void testNowPlan(){
		GetValue g = new GetValue("http://192.168.1.123:8080/webservice/services/web");
		String[][]ss = g.GetTimePlanList(4,"Test");
		System.out.println("集合大小："+ss);
		String getD="";
        for(String[] s:ss)
        {
        	for(String strValue:s)
        	{
        		getD+=strValue+"    ";
        	}
        	getD+="\r\n";
        }
        System.out.println(getD);
	}
	
	public static void testAllPlan(){
		GetValue g = new GetValue("http://192.168.1.246:8080/webservice/services/web");
		String[][]ss = g.GetTimePlanAll();
		System.out.println("集合大小："+ss.length);
		String getD="";
        for(String[] s:ss)
        {
        	for(String strValue:s)
        	{
        		getD+=strValue+"    ";
        	}
        	getD+="\r\n";
        }
        System.out.println(getD);
	}
	
	public static void testCenter(){
		GetValue g = new GetValue("http://192.168.1.123:8080/webservice/services/web");
		String[][]ss = g.GetCenterNetWorkInfoTab();
		System.out.println("集合大小："+ss);
		String getD="";
        for(String[] s:ss)
        {
        	for(String strValue:s)
        	{
        		getD+=strValue+"    ";
        	}
        	getD+="\r\n";
        }
        System.out.println(getD);
	}
	
	public static void main(String[] args) {
//		GetValue g = new GetValue("http://192.168.1.123:8080/webservice/services/web");
//////		g.init();
////		g.temp();
//		String [][] ss = g.getCenterNetworkInfo("face9a9c@001");
//		String str = "";
//		for(int i =0;i<ss.length;i++){
//			for(String s :ss[i]){
//				str += s+"\t";
//			}
//			str += "\n";
//		}
//		System.out.println(str);
//		testPlan();
//		testNowPlan();
//		testAllPlan();
		//System.out.println(g.getTongDao("3"));
		testCenter();
	}
}
