package com.sqlite.test;



public class Test {

	public static void main(String []args){
//		
////		DeviceStatus ds = new DeviceStatus();
////		ds.setDevcieId("614c7474bc5149b994d7b4c513f44261");
////		ds.setDevName("abc");
////		ds.setDevIp("192.168.1.195");
////		ds.setDeviceStatus(1);
////		DAOFactory.getPojoImpl().updateInfo(ds);
//		
//		DeviceStatus d=DAOFactory.getDeviceStatusIntance().getDeviceStatus("614c7474bc5149b994d7b4c513f44261");
//
//		System.out.println(d.userName+"=="+d.getPassword()+"=="+d.getDevName());
//		
//		
//		BaseDaoImpl impl = new BaseDaoImpl();
//		// �ж��豸ת�����Ƿ���ڣ������ھ�ɾ��
//		String sql = "SELECT COUNT(*) FROM sqlite_master where type='table' and name='VideoServer'";
//		int ise = impl.getTableIsExists(sql);
//		if (ise == 1) {
//			sql = "drop table VideoServer";
//			if (!impl.updateInfo(sql)) {
//				System.out.println("ɾ���VideoServerʧ�ܣ�");
//			}
//		}
//		// ���������?������������
//		// �ñ��ֶ�����Ϊ ID���豸ID��ͨ���ţ��豸״̬
//		sql = "create table VideoServer(vid integer primary key autoincrement,ClientId varchar(64)" +
//				",DeviceId varchar(64),DevChId integer,VideoServerId varchar(64),VSChId integer);";
//		if (!impl.updateInfo(sql)) {
//			System.out.println("������VideoServerʧ�ܣ�");
//		} else {
//			System.out.println("������VideoServer�ɹ���");
//		}
//
//		VideoServer v = new VideoServer(1,"c01","d01",11,"v01",22);
//		DAOFactory.getVideoServerIntance().insertVideoServer(v);
//		
//		
//		VideoServerDao im = DAOFactory.getVideoServerIntance();
//		
//		for(int i = 0 ;i<im.getVideoServersByClientId("c01").size();i++){
//			VideoServer vi = im.getVideoServersByClientId("c01").get(i);
//			System.out.println("ID��"+v.getVid()+"    ClientId:"+v.getClientId()+"        DeviceId" +
//					":"+v.getDeviceId()+"       DevCHId:"+v.getDevChId()+"          VideoServerId:"
//					+v.getVideoServerId()+"        VSChId:"+v.getvSChId());;
//		}
//			
//		for(int i = 0 ;i<im.getVideoServersByDeviceId("d01").size();i++){
//			VideoServer vi = im.getVideoServersByDeviceId("d01").get(i);
//			System.out.println("ID��"+v.getVid()+"    ClientId:"+v.getClientId()+"        DeviceId" +
//					":"+v.getDeviceId()+"       DevCHId:"+v.getDevChId()+"          VideoServerId:"
//					+v.getVideoServerId()+"        VSChId:"+v.getvSChId());;
//		}
//		
//		for(int i = 0 ;i<im.getVideoServersByVSId("v01").size();i++){
//			VideoServer vi = im.getVideoServersByVSId("v01").get(i);
//			System.out.println("ID��"+v.getVid()+"    ClientId:"+v.getClientId()+"        DeviceId" +
//					":"+v.getDeviceId()+"       DevCHId:"+v.getDevChId()+"          VideoServerId:"
//					+v.getVideoServerId()+"        VSChId:"+v.getvSChId());;
//		}
//		
//		im.delVideoServer(v);
//		System.out.println(im.getVideoServersByVSId("v01").size());
		
		
		
		
		
//		DeviceStatus ds = DAOFactory.getDeviceStatusIntance().getDeviceStatus("hsj001");
//		System.out.println("Type:"+ds.getDevType());
		
		
		
		
		
		
		
//		DAOFactory.getPojoImpl().init("http://192.168.1.123:8080/webservice/services/web");
		
		//List<Map<String,String>> list = DAOFactory.getDeviceStatusIntance().getDeviceBySwitchSvrID("f58d383ac03e47be836429e09d0ede4a");
		
//		try{
//			GetValue g=new GetValue("http://192.168.1.101:8080/webservice/services/web");
//			System.out.println(g.getDeviceServer());
//		}catch(Exception e){
//			System.out.println("WebService����δ����");
//		}
//		BaseDaoImpl impl = new BaseDaoImpl();
//		for(int i=0;i<10000;i++){
//			String sql = "insert into DeviceStatus(DeviceId,DevIp,DeviceStatus) values('abcdefg"+i+"','2452',0)";
//			impl.updateInfo(sql);
//		}
//		
//		
//		String sql="select * from DeviceStatus";
//		ResultSet rs = new BaseDaoImpl().getInfo(sql);
//		try{
//			int i=0;
//			while(rs.next()){
//				System.out.println("***************"+i+"***************");
//				i++;
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		
		
		
//		BaseDaoImpl b = new BaseDaoImpl();
//		ResultSet rs = b.getInfo("");
//		System.out.println(rs);
//		
		
//		boolean r = DAOFactory.getPojoImpl().updateInfo("ssssssss", 7, 1, 4);
//		System.out.println(r);
		
		
//		//�����豸��
//		DeviceStatus de = new DeviceStatus();
//		de.setDevcieId("sssssssssssssss");
//		de.setDevIp("192.168.1.103");
//		de.setDeviceStatus(2);
//		boolean r = DAOFactory.getPojoImpl().updateInfo(de);
//		System.out.println(r);
		
		
		//�����޸�����ͷ�ķ���
		
//		Canmel c = new Canmel();
//		c.setDeviceId("9312e1bc04554d5bb98cd5f77e95e089");
//		c.setChannelID(11);
//		c.setMotionStatus(1);
//		c.setVideoLost(2);
//		
//		boolean r = DAOFactory.getPojoImpl().updateInfo(c);
//		System.out.println(r);
		
		
//		//�����޸ı�����ķ���
//		AlrmPoint a = new AlrmPoint();
//		a.setChannelID(10);
//		a.setDeviceId("b9879e1a16ff48d686b5e844f0979f79");
//		a.setDeviceStatus(2);
//		boolean r = DAOFactory.getPojoImpl().updateInfo(a);
//		System.out.println(r);
		
		
		
		
//		//�����޸������ķ���
//		OutPoint a = new OutPoint();
//		a.setChannelID(8);
//		a.setDeviceId("9312e1bc04554d5bb98cd5f77e95e089");
//		a.setDeviceStatus(1);
//		boolean r = DAOFactory.getPojoImpl().updateInfo(a);
//		System.out.println(r);
		
		
		
//		String i=DAOFactory.getPojoImpl().getStatus("9312e1bc04554d5bb98cd5f77e95e089",4);
//		System.out.println("������״̬��1�𣿽���ǣ�"+i);
		
//		Class<?> c = b.getClass();
//		String name=c.getSimpleName();
//		System.out.println(name);
//		Test t = new Test();
//		t.init("http://192.168.1.101:8080/webservice/services/web");
//		DAOFactory.getPojoImpl().init("http://192.168.1.101:8080/webservice/services/web");
		//int did=DAOFactory.getSheXiangIntance().getCanmel("9312e1bc04554d5bb98cd5f77e95e089").getChannelID();
		
	}
	/**
	 * ��ʼ����ݿ����Ϣ
	 * @param url
	 */
//	public void init(String url){
//		BaseDaoImpl impl = new BaseDaoImpl();
//		GetValue g=new GetValue(url);
//
//		//�ж��豸��Ϣ���Ƿ���ڣ������ھ�ɾ��
//		String sql="SELECT COUNT(*) FROM sqlite_master where type='table' and name='DeviceStatus'";
//		int ise=impl.getTableIsExists(sql);
//		if(ise==1){
//			sql="drop table DeviceStatus";
//			if(!impl.updateInfo(sql)){
//				System.out.println("ɾ���DeviceStatusʧ�ܣ�");
//			}
//		}
//		//�����豸״̬�?������������
//		//�ñ��ֶ�����Ϊ    �豸ID���豸״̬���������������
//		sql="create table DeviceStatus(DID integer,DeviceID varchar(64),DevIp varchar(64));";
//		if(!impl.updateInfo(sql)){
//			System.out.println("������DeviceStatusʧ�ܣ�");
//		}
//		//Ϊ�豸��Ϣ��������
//		int len=g.getDeviceServer().length;
//		String [] sqls=new String[len-1];
//		for(int i=1;i<len;i++){
//			int id=Integer.parseInt(g.getDeviceServer()[i][0]);
//			String devId = g.getDeviceServer()[i][1];
//			String devIp = g.getDeviceServer()[i][3];
//			sqls[i-1]="insert into DeviceStatus values("+id+",'"+devId+"','"+devIp+"')";
//		}
//		if(!impl.updateInfo(sqls)){
//			System.out.println("��ʼ����DeviceStatusʧ�ܣ�");
//		}else{
//			System.out.println("��ʼ����DeviceStatus�ɹ���");
//		}
//		
//		
//		
//		//�жϱ������Ƿ���ڣ������ھ�ɾ��
//		sql="SELECT COUNT(*) FROM sqlite_master where type='table' and name='AlrmPoint'";
//		ise=impl.getTableIsExists(sql);
//		if(ise==1){
//			sql="drop table AlrmPoint";
//			if(!impl.updateInfo(sql)){
//				System.out.println("ɾ���AlrmPointʧ�ܣ�");
//			}
//		}
//		//���������?������������
//		//�ñ��ֶ�����Ϊ    ID���豸ID��ͨ���ţ��豸״̬
//		sql="create table AlrmPoint(bid integer,DeviceID varchar(64),ChannelID integer,DeviceStatus integer);";
//		if(!impl.updateInfo(sql)){
//			System.out.println("������AlrmPointʧ�ܣ�");
//		}
//		//Ϊ������������
//		len=g.getTongDao("4").length;
//		sqls=new String[len-1];
//		for(int i=1;i<len;i++){
//			int id=Integer.parseInt(g.getTongDao("4")[i][0]);
//			String devId = g.getTongDao("4")[i][1];
//			String channel = g.getTongDao("4")[i][2];
//			sqls[i-1]="insert into AlrmPoint values("+id+",'"+devId+"',"+channel+",0)";
//		}
//		if(!impl.updateInfo(sqls)){
//			System.out.println("��ʼ����AlrmPointʧ�ܣ�");
//		}else{
//			System.out.println("��ʼ����AlrmPoint�ɹ���");
//		}
//		
//		
//		
//		
//		
//		//�ж��������Ƿ���ڣ������ھ�ɾ��
//		sql="SELECT COUNT(*) FROM sqlite_master where type='table' and name='OutPoint'";
//		ise=impl.getTableIsExists(sql);
//		if(ise==1){
//			sql="drop table OutPoint";
//			if(!impl.updateInfo(sql)){
//				System.out.println("ɾ���OutPointʧ�ܣ�");
//			}
//		}
//		//���������?������������
//		//�ñ��ֶ�����Ϊ    ID���豸ID��ͨ���ţ��豸״̬
//		sql="create table OutPoint(bid integer,DeviceID varchar(64),ChannelID integer,DeviceStatus integer);";
//		if(!impl.updateInfo(sql)){
//			System.out.println("������OutPointʧ�ܣ�");
//		}
//		//Ϊ������������
//		len=g.getTongDao("5").length;
//		sqls=new String[len-1];
//		for(int i=1;i<len;i++){
//			int id=Integer.parseInt(g.getTongDao("5")[i][0]);
//			String devId = g.getTongDao("5")[i][1];
//			String channel = g.getTongDao("5")[i][2];
//			sqls[i-1]="insert into OutPoint values("+id+",'"+devId+"',"+channel+",0)";
//		}
//		if(!impl.updateInfo(sqls)){
//			System.out.println("��ʼ����OutPointʧ�ܣ�");
//		}else{
//			System.out.println("��ʼ����OutPoint�ɹ���");
//		}
//		
//		
//		
//		
//		
//		
//		//�ж��������Ƿ���ڣ������ھ�ɾ��
//		sql="SELECT COUNT(*) FROM sqlite_master where type='table' and name='OutPoint'";
//		ise=impl.getTableIsExists(sql);
//		if(ise==1){
//			sql="drop table OutPoint";
//			if(!impl.updateInfo(sql)){
//				System.out.println("ɾ���OutPointʧ�ܣ�");
//			}
//		}
//		//���������?������������
//		//�ñ��ֶ�����Ϊ    ID���豸ID��ͨ���ţ��豸״̬
//		sql="create table OutPoint(bid integer,DeviceID varchar(64),ChannelID integer,MotionStatus integer,VideoLost integer);";
//		if(!impl.updateInfo(sql)){
//			System.out.println("������OutPointʧ�ܣ�");
//		}
//		//Ϊ������������
//		len=g.getTongDao("3").length;
//		sqls=new String[len-1];
//		for(int i=1;i<len;i++){
//			int id=Integer.parseInt(g.getTongDao("3")[i][0]);
//			String devId = g.getTongDao("3")[i][1];
//			String channel = g.getTongDao("3")[i][2];
//			System.out.println("������ͨ���ţ�"+channel);
//			sqls[i-1]="insert into OutPoint values("+id+",'"+devId+"',"+channel+",0,0)";
//		}
//		if(!impl.updateInfo(sqls)){
//			System.out.println("��ʼ����OutPointʧ�ܣ�");
//		}else{
//			System.out.println("��ʼ����OutPoint�ɹ���");
//		}
//		
//	}
	
//	public void testTime(){
//		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
//		String datetime1 = tempDate.format(new java.util.Date());
//		System.out.println("ϵͳ��ǰʱ�䣺"+datetime1);
//		
//		
//		BaseDaoImpl impl = new BaseDaoImpl();
//		String [] sqls=new String[100];
//		for(int i=0;i<100;i++){
//			sqls[i]="insert into SheXiang values('S048',4,0);";
//		}
//		if(impl.updateInfo(sqls)){
//			System.out.println("��ʼ���ɹ�........");
//		}else{
//			System.out.println("��ʼ��ʧ��........");
//		}
//		
//		
//		String datetime2 = tempDate.format(new java.util.Date());
//		System.out.println("ϵͳ��ǰʱ�䣺"+datetime2);
//	}
	
	
}
