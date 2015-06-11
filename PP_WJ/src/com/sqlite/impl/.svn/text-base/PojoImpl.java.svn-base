package com.sqlite.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sqlite.pojo.*;

import com.sqlite.conn.GetValue;
import com.sqlite.dao.CenterNetWorkDao;
import com.sqlite.dao.CenterRouteDao;
import com.sqlite.dao.PojoDao;
import com.sqlite.pojo.BasePO;
import com.sqlite.pojo.Canmel;
import com.sqlite.pojo.CenterNetWork;
import com.sqlite.pojo.DeviceStatus;
public class PojoImpl implements PojoDao {
 
	
	/* ��ȡ�豸״̬
    * @param devId
    * @param channelId
    * @param typeid
    * @return
    */
   public int getStatus(String devId,int channelId,int typeid){
	   if(typeid==3||typeid==4){
		   return new CanmelImpl().getCanmelStatus(devId, channelId, typeid);
	   }else if(typeid==1){
		   return new AlrmPointImpl().getAlrmPointStatus(devId, channelId);
	   }else if(typeid==2){
		   return new OutPointImpl().getOutPointStatus(devId,channelId);
	   }else{
		   System.out.println("传入参数不合法！");
		   return -1;
	   }
   }

	
	/**
     * ��ѯ�������߻����ߵ��豸��Ϣ
     * @param status
     * @return
     */
    public List<DeviceStatus> getAllDeivceStatus(int status){
    	return new DeviceStatusImpl().getAllDeviceStatus(status);
    }

    /**
     * ����豸ID������ID����ѯ��Ӧ��ͨ������
     * @param divID
     * @param typeId
     * @return
     */
    public List<BasePO> getBasePOByDeviceId(String divID,int typeId){
    	if(typeId == 3){
    		return new CanmelImpl().getCanmel(divID);
    	}else if(typeId == 4){
    		return new AlrmPointImpl().getAlrmPoint(divID);
    	}else if(typeId == 5){
    		return new OutPointImpl().getOutPoint(divID);
    	}else{
    		System.out.println("�������ʹ���");
    		return null;
    	}
    }
    
    /**
     * 转化字符
     * @param a
     * @return
     */
    public Object changeObj(Object a){
    	if(a==null){
    		return null;
    	}else{
    		if(a.getClass().getSimpleName().equals("String")){
    			return "'"+a+"'";
    		}else{
    			return a;
    		}
    	}
    }
	
	/**
	 * ��ʼ����ݿ����Ϣ
	 * 
	 * @param url
	 */
	public void init(String url,String dbname) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
//		 System.out.println(sdf.format(new Date()));
//		 Calendar calen = Calendar.getInstance();
		 
//		 long wri = 0;       //往内存数据库中写数据花的的时间
//		 long rea = 0;       //读取webservice花的时间
//		 
//		 long startWri = 0;
//		 long endWri = 0;
//		 
//		 long startRea = 0;
//		 long endRea = 0;
		 
//		boolean result = true;
		System.out.println("开始初始化内存数据库。。。");
		BaseDaoImpl impl = new BaseDaoImpl();
		impl.setDbName(dbname);
		GetValue g = new GetValue(url);
		String[] sqls = null;
		int len = 0;
//		
//		startWri = calen.getTimeInMillis();
		
//		String time1  = sdf.format(new Date());
//		System.out.println(time1);
		// �ж��豸��Ϣ���Ƿ���ڣ������ھ�ɾ��
		String sql = "SELECT COUNT(*) FROM sqlite_master where type='table' and name=" +
				"'DeviceStatus'";
		int ise = impl.getTableIsExists(sql);
		if (ise == 1) {
			sql = "drop table DeviceStatus";
			if (!impl.updateInfo(sql)) {
				System.out.println("删除表DeviceStatus失败");
			}
		}
		// �����豸״̬�?������������
		// �ñ��ֶ�����Ϊ �豸ID���豸״̬���������������
		sql = "create table DeviceStatus(ID integer primary key autoincrement,DeviceID " +
				"varchar(64),Devname varchar(64),DevIP varchar(64),DevMask varchar(64)," +
				"DevGate varchar(64),DevMAC varchar(64),DevPort integer,UserName " +
				"varchar(64),Password varchar(64),DevType integer,DevSubType integer" +
				",DevModel varchar(64),DevSN varchar(64),DevVer varchar(64),AreaID varchar(64)" +
				",CameraNum varchar(64),AlarmPointNum varchar(64),OutPutPointNum varchar(64)," +
				"IsDisable varchar(64),RegisterTime varchar(64),SwitchSvrID varchar(64)," +
				"CenterID varchar(64),DeviceStatus Integer);";
		if (!impl.updateInfo(sql)) {
			System.out.println("创建表DeviceStatus失败");
		}
//		calen = Calendar.getInstance();
//		endWri = calen.getTimeInMillis();
//		wri += (endWri-startWri);
		
		String [][] devicestatus = g.getDeviceServer();
		if(devicestatus==null || devicestatus.length<=1){
			System.out.println("WebService获取DeviceStatus表的信息为NULL......");
		}else{
//			calen = Calendar.getInstance();
//			startRea = calen.getTimeInMillis();
			
			len = devicestatus.length;
			String[] array = devicestatus[0];
			for(int i=0;i<array.length;i++){
				System.out.println(array[i]);
			}
			sqls = new String[len - 1];
			for (int i = 1; i < len; i++) {
				String CenterID =devicestatus[i][0];
				String DevMask = devicestatus[i][1];
				String DevMAC = devicestatus[i][2];
				String RegisterTime = devicestatus[i][3];
				int IsDisable =  Integer.parseInt( devicestatus[i][4]==null?"0":devicestatus[i][4]);
				String Devname = devicestatus[i][5];
				String AreaID = devicestatus[i][6];
				String DevIP = devicestatus[i][7];
				int AlarmPointNum =  Integer.parseInt(devicestatus[i][8]==null?"0":devicestatus[i][8]);
				String Password = devicestatus[i][9];
				int CameraNum = Integer.parseInt( devicestatus[i][10]==null?"0":devicestatus[i][10]);
				String DeviceID = devicestatus[i][11];
				String DevGate = devicestatus[i][13];
				int DevSubType = Integer.parseInt( devicestatus[i][14]==null?"0":devicestatus[i][14]);
				String UserName = devicestatus[i][15];
				String SwitchSvrID = devicestatus[i][16];
				String DevVer = devicestatus[i][17];
				String DevSN = devicestatus[i][18];
				int DevPort = Integer.parseInt( devicestatus[i][19]);
				int OutPutPointNum = Integer.parseInt(devicestatus[i][20]==null?"0":devicestatus[i][20]);
				String DevModel = devicestatus[i][21];
				int DevType = Integer.parseInt( devicestatus[i][22]==null?"0":devicestatus[i][22]);
				
//				String DeviceID = devicestatus[i][1];
//				String Devname = devicestatus[i][2];
//				String DevIP = devicestatus[i][3];
//				String DevMask = devicestatus[i][4];
//				String DevGate = devicestatus[i][5];
//				String DevMAC = devicestatus[i][6];
//				int DevPort = Integer.parseInt( devicestatus[i][7]);
//				String UserName = devicestatus[i][8];
//				String Password = devicestatus[i][9];
//				int DevType = Integer.parseInt( devicestatus[i][10]==null?"0":devicestatus[i][10]);
//				int DevSubType = Integer.parseInt( devicestatus[i][11]==null?"0":devicestatus[i][11]);
//				String DevModel = devicestatus[i][12];
//				String DevSN = devicestatus[i][13];
//				String DevVer = devicestatus[i][14];
//				String AreaID = devicestatus[i][15];
//				int CameraNum = Integer.parseInt( devicestatus[i][16]==null?"0":devicestatus[i][16]);
//				int AlarmPointNum =  Integer.parseInt(devicestatus[i][17]==null?"0":devicestatus[i][17]);
//				int OutPutPointNum = Integer.parseInt(devicestatus[i][18]==null?"0":devicestatus[i][18]);
//				int IsDisable =  Integer.parseInt( devicestatus[i][19]==null?"0":devicestatus[i][19]);
//				String RegisterTime = devicestatus[i][20];
//				String SwitchSvrID = devicestatus[i][21];
//				String CenterID =devicestatus[i][22];
				
				
				sqls[i-1] =String.format( "insert into DeviceStatus(DeviceID,Devname,DevIP,DevMask,DevGate," +
						"DevMAC,DevPort,UserName,Password,DevType,DevSubType,DevModel,DevSN,DevVer" +
						",AreaID,CameraNum,AlarmPointNum,OutPutPointNum,IsDisable,RegisterTime," +
						"SwitchSvrID,CenterID,DeviceStatus) values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s," +
						"%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)",changeObj(DeviceID),
						changeObj(Devname),changeObj(DevIP),changeObj(DevMask),changeObj
						(DevGate),changeObj(DevMAC),changeObj(DevPort),changeObj(UserName),
						changeObj(Password),changeObj(DevType),changeObj(DevSubType),changeObj(DevModel),
						changeObj(DevSN),changeObj(DevVer),changeObj(AreaID),changeObj
						(CameraNum),changeObj(AlarmPointNum),changeObj(OutPutPointNum),
						changeObj(IsDisable),changeObj(RegisterTime),changeObj(SwitchSvrID),
						changeObj(CenterID),0);
				
			}
//			calen = Calendar.getInstance();
//			endRea = calen.getTimeInMillis();
//			rea += (endRea-startRea);
//			
//			
//			calen = Calendar.getInstance();
//			startWri = calen.getTimeInMillis();
			if(!impl.initInsert(sqls)){
				System.out.println("初始化表DeviceStatus失败");
			}else{
				System.out.println("初始化表DeviceStatus成功");
			}
//			calen = Calendar.getInstance();
//			endWri = calen.getTimeInMillis();
//			wri += (startWri - endWri);
		}
		// Ϊ�豸��Ϣ��������
//		System.out.println("g.getDeviceServer():"+g.getDeviceServer());
		

		
		
		
		
		
//		time1  = sdf.format(new Date());
//		System.out.println(time1);
//		calen = Calendar.getInstance();
//		startWri = calen.getTimeInMillis();
		// �жϱ������Ƿ���ڣ������ھ�ɾ��
		sql = "SELECT COUNT(*) FROM sqlite_master where type='table' and name='AlrmPoint'";
		ise = impl.getTableIsExists(sql);
		if (ise == 1) {
			sql = "drop table AlrmPoint";
			if (!impl.updateInfo(sql)) {
				System.out.println("删除表AlrmPoint失败");
			}
		}
		// ���������?������������
		// �ñ��ֶ�����Ϊ ID���豸ID��ͨ���ţ��豸
//		result = true;
		sql = "create table AlrmPoint(aid integer primary key autoincrement,DeviceID " +
				"varchar(64),DeviceName varchar(64),ChannelID integer,DeviceStatus integer);";
		if (!impl.updateInfo(sql)) {
			System.out.println("创建表AlrmPoint失败");
		}
//		calen = Calendar.getInstance();
//		endWri = calen.getTimeInMillis();
//		wri += (endWri - startWri);
		
		String [][] alarms = g.getTongDao("4");
		if(alarms==null || alarms.length<=1){
			System.out.println("WebService获取AlrmPoint表的信息为NULL......");
		}else{
//			calen = Calendar.getInstance();
//			startRea = calen.getTimeInMillis();
			// Ϊ������������
			
			len = alarms.length;
			sqls = new String[len - 1];
			
			String[] array = alarms[0];
			for(int i=0;i<array.length;i++){
				System.out.println(array[i]);
			}
			
			
			for (int i = 1; i < len; i++) {
				// int id=Integer.parseInt(g.getTongDao("4")[i][0]);
				String devId = alarms[i][7];
				String channel = alarms[i][2];
				String devName = alarms[i][3];
				sqls[i-1] =String.format( "insert into AlrmPoint(DeviceID,DeviceName,ChannelId," +
						"DeviceStatus) values(%s,%s,%s,0)",changeObj(devId),changeObj(devName),
						changeObj(channel));
			}
//			calen = Calendar.getInstance();
//			endRea = calen.getTimeInMillis();
//			rea += (endRea - startRea);
//			
//			calen = Calendar.getInstance();
//			startWri = calen.getTimeInMillis();
			if(!impl.initInsert(sqls)){
				System.out.println("初始化表AlrmPoint失败");
			}else{
				System.out.println("初始化表AlrmPoint成功");
			}
//			calen = Calendar.getInstance();
//			endWri = calen.getTimeInMillis();
//			wri += (endWri - startWri);
		}
		
			

		
		
		
//		time1  = sdf.format(new Date());
//		System.out.println(time1);
//		calen = Calendar.getInstance();
//		startWri = calen.getTimeInMillis();
		//创建表OutPoint
		sql = "SELECT COUNT(*) FROM sqlite_master where type='table' and name='OutPoint'";
		ise = impl.getTableIsExists(sql);
		if (ise == 1) {
			sql = "drop table OutPoint";
			if (!impl.updateInfo(sql)) {
				System.out.println("删除表OutPoint失败");
			}
		}
		// ���������?������������
		// �ñ��ֶ�����Ϊ ID���豸ID��ͨ���ţ��豸״̬
		sql = "create table OutPoint(oid integer primary key autoincrement,DeviceID " +
				"varchar(64),DeviceName varchar(64),ChannelID integer,DeviceStatus integer);";
		if (!impl.updateInfo(sql)) {
			System.out.println("创建表OutPoint失败");
		}
//		calen = Calendar.getInstance();
//		endWri = calen.getTimeInMillis();
//		wri += (endWri - startWri);
		String [][] outpoints = g.getTongDao("5");
		if(outpoints==null || outpoints.length<=1){
			System.out.println("WebService获取OutPoint表的信息为NULL......");
		}else{
//			calen = Calendar.getInstance();
//			startRea = calen.getTimeInMillis();
			// Ϊ������������
			
			len = outpoints.length;
			
//			System.out.println("输出点的数量："+outpoints.length);
			sqls = new String[len - 1];
			for (int i = 1; i < len; i++) {
				// int id=Integer.parseInt(g.getTongDao("5")[i][0]);
				String devId = outpoints[i][7];
				String channel = outpoints[i][2];
				String devName = outpoints[i][3];
				sqls[i-1]=String.format( "insert into OutPoint(DeviceID,DeviceName,ChannelId,DeviceStatus) " +
						"values(%s,%s,%s,0)",changeObj(devId),changeObj(devName),changeObj(channel));
			}
//			calen = Calendar.getInstance();
//			endRea = calen.getTimeInMillis();
//			rea += (endRea - startRea);
//			
//			calen = Calendar.getInstance();
//			startWri = calen.getTimeInMillis();
			if(!impl.initInsert(sqls)){
				System.out.println("初始化表OutPoint失败");
			}else{
				System.out.println("初始化表OutPoint成功");
			}
//			calen = Calendar.getInstance();
//			endWri = calen.getTimeInMillis();
//			wri += (endWri - startWri);
		}
		
		
		
		
		
//		time1  = sdf.format(new Date());
//		System.out.println(time1);
//		calen = Calendar.getInstance();
//		startWri = calen.getTimeInMillis();
		// 判断表Canmel存不存在，如果存在，就删除
		sql = "SELECT COUNT(*) FROM sqlite_master where type='table' and name='Canmel'";
		ise = impl.getTableIsExists(sql);
		if (ise == 1) {
			sql = "drop table Canmel";
			if (!impl.updateInfo(sql)) {
				System.out.println("删除表Canmel失败");
			}
		}
		// ���������?������������
		// �ñ��ֶ�����Ϊ ID���豸ID��ͨ���ţ��豸״̬
		sql = "create table Canmel(cid integer primary key autoincrement,DeviceID " +
				"varchar(64),DeviceName varchar(64),ChannelID integer,MotionStatus " +
				"integer,VideoLost integer);";
		if (!impl.updateInfo(sql)) {
			System.out.println("创建表Canmel失败");
		}
//		calen = Calendar.getInstance();
//		endWri = calen.getTimeInMillis();
//		wri += (endWri - startWri);
		String [][] camels = g.getTongDao("3");
		if(camels==null || camels.length<=1){
			System.out.println("WebService获取Canmel表的信息为NULL......");
		}else{
//			calen = Calendar.getInstance();
//			startRea = calen.getTimeInMillis();
			// Ϊ������������
		
			len = camels.length;
			
			sqls = new String[len - 1];
			for (int i = 1; i < len; i++) {
				// int id=Integer.parseInt(g.getTongDao("3")[i][0]);
				String devId = camels[i][7];
				String channel = camels[i][2];
				String devName = camels[i][3];
				sqls[i-1] =String.format("insert into Canmel(DeviceId,DeviceName,ChannelId,MotionStatus,VideoLost) values(" +
						"%s,%s,%s,0,0)",changeObj(devId),changeObj(devName),changeObj(channel));
			}
//			calen = Calendar.getInstance();
//			endRea = calen.getTimeInMillis();
//			rea += (endRea - startRea);
//			
//			calen = Calendar.getInstance();
//			startWri = calen.getTimeInMillis();
			if(!impl.initInsert(sqls)){
				System.out.println("初始化表Canmel失败");
			}else{
				System.out.println("初始化表Canmel成功");
			}
//			calen = Calendar.getInstance();
//			endWri = calen.getTimeInMillis();
//			wri += (endWri - startWri);
		}
		
			

		
		
//		
//		time1  = sdf.format(new Date());
//		System.out.println(time1);
//		calen = Calendar.getInstance();
//		startWri = calen.getTimeInMillis();
		//判断表VideoServer存不存在，如果存在，就删除
		sql = "SELECT COUNT(*) FROM sqlite_master where type='table' and name='VideoServer'";
		ise = impl.getTableIsExists(sql);
		if (ise == 1) {
			sql = "drop table VideoServer";
			if (!impl.updateInfo(sql)) {
				System.out.println("删除表VideoServer成功");
			}
		}
		// ���������?������������
		// �ñ��ֶ�����Ϊ ID���豸ID��ͨ���ţ��豸״̬
		sql = "create table VideoServer(vid integer primary key autoincrement,ClientId varchar(64)" +
				",DeviceId varchar(64),DevChId integer,VideoServerId varchar(64),VSChId integer" +
				",Flag integer,ClientIp varchar(64),DeviceIP varchar(64),LinkedMode integer,Lev integer);";
		if (!impl.updateInfo(sql)) {
			System.out.println("创建表VideoServer失败");
		} else {
			System.out.println("创建表VideoServer成功");
		}
//		calen = Calendar.getInstance();
//		endWri = calen.getTimeInMillis();
//		wri += (endWri - startWri);
		
		
		
		
		
		
//		time1  = sdf.format(new Date());
//		System.out.println(time1);
//		calen = Calendar.getInstance();
//		startWri = calen.getTimeInMillis();
		//判断表AlarmRecordPlan存不存在，如果存在，就删除
		sql = "SELECT COUNT(*) FROM sqlite_master where type='table' and name='AlarmRecordPlan'";
		ise = impl.getTableIsExists(sql);
		if (ise == 1) {
			sql = "drop table AlarmRecordPlan";
			if (!impl.updateInfo(sql)) {
				System.out.println("删除表AlarmRecordPlan成功");
			}
		}
		//创建表AlarmRecordPlan
		sql = "create table AlarmRecordPlan(ID int primary key,DeviceID varchar(64)," +
				"DevCH int,CHType int,PlanType int,PlanXml text,CenterID varchar(64)," +
				"SessionID varchar(64),PlanDesc varchar(64));";
		if (!impl.updateInfo(sql)) {
			System.out.println("创建表AlarmRecordPlan失败");
		} 
//		calen = Calendar.getInstance();
//		endWri = calen.getTimeInMillis();
//		wri += (endWri - startWri);
		
		String [][] plans = g.GetTimePlanAll();
		if(plans==null||plans.length<=1){
			System.out.println("WebService获取AlarmRecordPlan表的信息为NULL......");
		}else{
//			calen = Calendar.getInstance();
//			startRea = calen.getTimeInMillis();
			
			String[] array = plans[0];
			for(int i=0;i<array.length;i++){
				System.out.println(array[i]);
			}
			
			sqls = new String[plans.length-1];
			for (int i = 1; i < plans.length; i++) {
				// int id=Integer.parseInt(g.getTongDao("3")[i][0]);
				int Id = Integer.parseInt(plans[i][0]);
				String devId = plans[i][1];
				int DevCH = Integer.parseInt(plans[i][2]);
				int CHType = Integer.parseInt(plans[i][3]);
				int PlanType = Integer.parseInt(plans[i][4]);
				String PlanXml = plans[i][5];
				String centerId = plans[i][8];
				String sessionId = plans[i][6];
//				System.out.println("sessionId:"+sessionId);
				String desc = plans[i][7];
				sqls[i-1] =String.format("insert into AlarmRecordPlan(ID,DeviceID,DevCH," +
						"CHType,PlanType,PlanXml,CenterID,SessionID,PlanDesc) values(%s," +
						"%s,%s,%s,%s,%s,%s,%s,%s)",changeObj(Id),changeObj(devId),changeObj
						(DevCH),changeObj(CHType),changeObj(PlanType),changeObj(PlanXml)
						,changeObj(centerId),changeObj(sessionId),changeObj(desc));
			}
//			calen = Calendar.getInstance();
//			endRea = calen.getTimeInMillis();
//			rea += (endRea - startRea);
//			
//			calen = Calendar.getInstance();
//			startWri = calen.getTimeInMillis();
			if(!impl.initInsert(sqls)){
				System.out.println("初始化表AlarmRecordPlan失败");
			}else{
				System.out.println("初始化表AlarmRecordPlan成功");
			}
//			calen = Calendar.getInstance();
//			endWri = calen.getTimeInMillis();
//			wri += (endWri - startWri);
		}
		
//		System.out.println("读取webservice花了时间："+rea+"毫秒");
//		System.out.println("写入内存数据库花了 时 间："+wri+"毫秒");
		
		
		
		//判断表CenterInfoTab存不存在，如果存在，就删除
		sql = "SELECT COUNT(*) FROM sqlite_master where type='table' and name='CenterInfoTab'";
		ise = impl.getTableIsExists(sql);
		if (ise == 1) {
			sql = "drop table CenterInfoTab";
			if (!impl.updateInfo(sql)) {
				System.out.println("删除表CenterInfoTab成功");
			}
		}
		//创建表AlarmRecordPlan
		sql = "create table CenterInfoTab(ID int primary key,CenterID varchar(64)," +
				"CenterName varchar(64),CenterDesc varchar(64),CenterIP varchar(64)," +
				"CenterMask varchar(64),CenterGate varchar(64),CenterPort int,LoginUserName " +
				"varchar(64),LoginPwd varchar(64),DataBaseIP varchar(64),DataBaseUser " +
				"varchar(64),DataBasePwd varchar(64),ParentCenterIP varchar(64),ParentCenterPort" +
				" int,DomainName varchar(64),DNSIP varchar(64),CenterVar varchar(64),LinkMode" +
				" int,SyncServerIP varchar(64),SyncServerPort int,RouteMode varchar(64));";
		if (!impl.updateInfo(sql)) {
			System.out.println("创建表CenterInfoTab失败");
		} 
//		calen = Calendar.getInstance();
//		endWri = calen.getTimeInMillis();
//		wri += (endWri - startWri);
		
		plans = g.GetCenterInfoTab();
		if(plans==null  || plans.length<=1){
			System.out.println("WebService获取CenterInfoTab表的信息为NULL......");
		}else{
//			calen = Calendar.getInstance();
//			startRea = calen.getTimeInMillis();
			String[] array = plans[0];
			for(int i=0;i<array.length;i++){
				System.out.println(array[i]);
			}
			
			
			sqls = new String[plans.length-1];
			for (int i = 1; i < plans.length; i++) {
				// int id=Integer.parseInt(g.getTongDao("3")[i][0]);
				int ParentCenterPort = Integer.parseInt(plans[i][0]);
				String CenterID = plans[i][1];
				String SyncServerIP = plans[i][2];
				String CenterVar = plans[i][3];
				String DataBasePwd = plans[i][4];
				String DataBaseUser = plans[i][5];
				String CenterMask = plans[i][6];
				String CenterIP = plans[i][7];
				int Id = Integer.parseInt(plans[i][8]);
				String CenterDesc = plans[i][9];
				String CenterName = plans[i][10];
				String CenterGate = plans[i][11];
				String DomainName = plans[i][12];
				int SyncServerPort = Integer.parseInt(plans[i][13]);
				String LoginPwd = plans[i][14];
				int LinkMode = Integer.parseInt(plans[i][15]);
				int CenterPort = Integer.parseInt(plans[i][16]);
				String DNSIP = plans[i][17];
				String DataBaseIP = plans[i][18];
				String RouteMode = plans[i][19];
				String ParentCenterIP = plans[i][20];
				String LoginUserName = plans[i][21];
				
				
				
	
				sqls[i-1] =String.format("insert into CenterInfoTab(ID,CenterID,CenterName," +
						"CenterDesc,CenterIP,CenterMask,CenterGate,CenterPort,LoginUserName," +
						"LoginPwd,DataBaseIP,DataBaseUser,DataBasePwd,ParentCenterIP," +
						"ParentCenterPort,DomainName,DNSIP,CenterVar,LinkMode,SyncServerIP," +
						"SyncServerPort,RouteMode) values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s," +
						"%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)",changeObj(Id),changeObj(CenterID),
						changeObj(CenterName),changeObj(CenterDesc),changeObj(CenterIP),
						changeObj(CenterMask),changeObj(CenterGate),changeObj(CenterPort),
						changeObj(LoginUserName),changeObj(LoginPwd),changeObj(DataBaseIP),
						changeObj(DataBaseUser),changeObj(DataBasePwd),changeObj(ParentCenterIP)
						,changeObj(ParentCenterPort),changeObj(DomainName),changeObj(DNSIP),
						changeObj(CenterVar),changeObj(LinkMode),changeObj(SyncServerIP),
						changeObj(SyncServerPort),changeObj(RouteMode));
			}
//			calen = Calendar.getInstance();
//			endRea = calen.getTimeInMillis();
//			rea += (endRea - startRea);
//			
//			calen = Calendar.getInstance();
//			startWri = calen.getTimeInMillis();
			if(!impl.initInsert(sqls)){
				System.out.println("初始化表CenterInfoTab失败");
			}else{
				System.out.println("初始化表CenterInfoTab成功");
			}
//			calen = Calendar.getInstance();
//			endWri = calen.getTimeInMillis();
//			wri += (endWri - startWri);
		}
		
		
		
		
		//判断表CenterRouteInfoTab存不存在，如果存在，就删除
		sql = "SELECT COUNT(*) FROM sqlite_master where type='table' and name='CenterRouteInfoTab'";
		ise = impl.getTableIsExists(sql);
		if (ise == 1) {
			sql = "drop table CenterRouteInfoTab";
			if (!impl.updateInfo(sql)) {
				System.out.println("删除表CenterRouteInfoTab成功");
			}
		}
		//创建表AlarmRecordPlan
		sql = "create table CenterRouteInfoTab(ID integer primary key autoincrement,SourceCenterID varchar(64)," +
				"DestCenterID varchar(64),RoutePath varchar(64));";
		if (!impl.updateInfo(sql)) {
			System.out.println("创建表CenterRouteInfoTab失败");
		} 
//		calen = Calendar.getInstance();
//		endWri = calen.getTimeInMillis();
//		wri += (endWri - startWri);
		
		plans = g.GetCenterRouteInfoTab();
		if(plans==null || plans.length<=1){
			System.out.println("WebService获取CenterRouteInfoTab表的信息为NULL......");
		}else{
//			calen = Calendar.getInstance();
//			startRea = calen.getTimeInMillis();
			
			String[] array = plans[0];
			for(int i=0;i<array.length;i++){
				System.out.println(array[i]);
			}
			
			
			sqls = new String[plans.length-1];
			for (int i = 1; i < plans.length; i++) {
				// int id=Integer.parseInt(g.getTongDao("3")[i][0]);
//				int Id = Integer.parseInt(plans[i][0]);
				String SourceCenterID = plans[i][1];
				String DestCenterID = plans[i][2];
				String RoutePath = plans[i][3];
				sqls[i-1] =String.format("insert into CenterRouteInfoTab(SourceCenterID," +
						"DestCenterID,RoutePath) values(%s,%s,%s)",
						changeObj(SourceCenterID),changeObj(DestCenterID),changeObj(RoutePath));
			}
//			calen = Calendar.getInstance();
//			endRea = calen.getTimeInMillis();
//			rea += (endRea - startRea);
//			
//			calen = Calendar.getInstance();
//			startWri = calen.getTimeInMillis();
			if(!impl.initInsert(sqls)){
				System.out.println("初始化表CenterRouteInfoTab失败");
			}else{
				System.out.println("初始化表CenterRouteInfoTab成功");
			}
//			calen = Calendar.getInstance();
//			endWri = calen.getTimeInMillis();
//			wri += (endWri - startWri);
		}
		
		
		
		
		
		//判断表CenterNetWorkInfoTab存不存在，如果存在，就删除
		sql = "SELECT COUNT(*) FROM sqlite_master where type='table' and name='CenterNetWorkInfoTab'";
		ise = impl.getTableIsExists(sql);
		if (ise == 1) {
			sql = "drop table CenterNetWorkInfoTab";
			if (!impl.updateInfo(sql)) {
				System.out.println("删除表CenterNetWorkInfoTab成功");
			}
		}
		//创建表CenterNetWorkInfoTab
		sql = "create table CenterNetWorkInfoTab(ID int primary key,NetWorkNodeID varchar(64)," +
				"NetWorkNodeIP varchar(64),NetWorkNodePort int,IsParentFlag int,IsControlFlag " +
				"int,CenterID varchar(64));";
		if (!impl.updateInfo(sql)) {
			System.out.println("创建表CenterNetWorkInfoTab失败");
		} 
//		calen = Calendar.getInstance();
//		endWri = calen.getTimeInMillis();
//		wri += (endWri - startWri);
		
		plans = g.GetCenterNetWorkInfoTab();
		if(plans==null|| plans.length<=1){
			System.out.println("WebService获取CenterNetWorkInfoTab表的信息为NULL......");
		}else{
//			calen = Calendar.getInstance();
//			startRea = calen.getTimeInMillis();
			
			String[] array = plans[0];
			for(int i=0;i<array.length;i++){
				System.out.println(array[i]);
			}
			
			sqls = new String[plans.length-1];
			for (int i = 1; i < plans.length; i++) {
				// int id=Integer.parseInt(g.getTongDao("3")[i][0]);
				int Id = Integer.parseInt(plans[i][0]);
				String CenterID = plans[i][1];
				int IsControlFlag = Integer.parseInt(plans[i][2]);
				String NetWorkNodeID = plans[i][3];
				String NetWorkNodeIP = plans[i][4];
				int NetWorkNodePort = Integer.parseInt(plans[i][5]);
				int IsParentFlag = Integer.parseInt(plans[i][6]);
				
			
				sqls[i-1] =String.format("insert into CenterNetWorkInfoTab(ID,NetWorkNodeID," +
						"NetWorkNodeIP,NetWorkNodePort,IsParentFlag,IsControlFlag,CenterID) " +
						"values(%s,%s,%s,%s,%s,%s,%s)",changeObj(Id),changeObj(NetWorkNodeID),
						changeObj(NetWorkNodeIP),changeObj(NetWorkNodePort),changeObj(IsParentFlag)
						,changeObj(IsControlFlag),changeObj(CenterID));
			}
//			calen = Calendar.getInstance();
//			endRea = calen.getTimeInMillis();
//			rea += (endRea - startRea);
//			
//			calen = Calendar.getInstance();
//			startWri = calen.getTimeInMillis();
			if(!impl.initInsert(sqls)){
				System.out.println("初始化表CenterNetWorkInfoTab失败");
			}else{
				System.out.println("初始化表CenterNetWorkInfoTab成功");
			}
//			calen = Calendar.getInstance();
//			endWri = calen.getTimeInMillis();
//			wri += (endWri - startWri);
		}
		
		
//		//判断表CommandDevice存不存在，如果存在，就删除
//		sql = "SELECT COUNT(*) FROM sqlite_master where type='table' and name='CommandDevice'";
//		ise = impl.getTableIsExists(sql);
//		if (ise == 1) {
//			sql = "drop table CommandDevice";
//			if (!impl.updateInfo(sql)) {
//				System.out.println("删除表CommandDevice成功");
//			}
//		}
//		//创建表CommandDevice
//		sql = "create table CommandDevice(ID int primary key,DeviceID varchar(64)," +
//				" DeviceName varchar(64),DeviceDesc  varchar(256),DevIP int,DevMAC " +
//				"varchar(64),WorkStatus int,IsOnline int,ClientUserID varchar(64)," +
//				"CommandStatus int,ConferenceStatus int,ConsultationStatus int,DevModal " +
//				"int,DevVer varchar(32),CameraNum int,CommandTeamID varchar(64),CenterID " +
//				"varchar(64));";
//		if (!impl.updateInfo(sql)) {
//			System.out.println("创建表CommandDevice失败");
//		} 
//		plans = g.Getcommanddevtab();
//		if(plans==null){
//			System.out.println("WebService获取CommandDevice表的信息为NULL......");
//		}else{
//			sqls = new String[plans.length-1];
//			for (int i = 1; i < plans.length; i++) {
//				int Id = Integer.parseInt(plans[i][0]);
//				String DeviceID = plans[i][1];
//				String DeviceName = plans[i][2];
//				String DeviceDesc = plans[i][3];
//				String DevIP = plans[i][4];
//				String DevMAC = plans[i][5];
//				int WorkStatus = Integer.parseInt(plans[i][6]);
//				int IsOnline = Integer.parseInt(plans[i][7]);
//				String ClientUserID = plans[i][8];
//				int CommandStatus = Integer.parseInt(plans[i][9]);
//				int ConferenceStatus = Integer.parseInt(plans[i][10]);
//				int ConsultationStatus = Integer.parseInt(plans[i][11]);
//				int DevModal = Integer.parseInt(plans[i][12]);
//				String DevVer = plans[i][13];
//				int CameraNum = Integer.parseInt(plans[i][14]);
//				String CommandTeamID = plans[i][15];
//				String CenterID = plans[i][16];
////				System.out.println("DeviceID:"+DeviceID+"=============CenterID:"+CenterID);
//				sqls[i-1] =String.format("insert into CommandDevice(ID,DeviceID,DeviceName," +
//						"DeviceDesc,DevIP,DevMAC,WorkStatus,IsOnline,ClientUserID," +
//						"CommandStatus,ConferenceStatus,ConsultationStatus,DevModal,DevVer," +
//						"CameraNum,CommandTeamID,CenterID) values(%s,%s,%s,%s,%s,%s,%s,%s,%s," +
//						"%s,%s,%s,%s,%s,%s,%s,%s)",changeObj(Id),changeObj(DeviceID),
//						changeObj(DeviceName),changeObj(DeviceDesc),changeObj(DevIP)
//						,changeObj(DevMAC),changeObj(WorkStatus),changeObj(IsOnline),
//						changeObj(ClientUserID),changeObj(CommandStatus)
//						,changeObj(ConferenceStatus),changeObj(ConsultationStatus),
//						changeObj(DevModal),changeObj(DevVer),changeObj(CameraNum),
//						changeObj(CommandTeamID),changeObj(CenterID));
//			}
//			if(!impl.initInsert(sqls)){
//				System.out.println("初始化表CommandDevice失败");
//			}else{
//				System.out.println("初始化表CommandDevice成功");
//			}
//		}
		
		/**
		 * 初始化CenterRouteInfo表的信息
		 * 注释于2012年6月8日
		 * 高江
		 */
//		initCenterRouteInfo();
		
		
		
		//初始化表nvmp_centerinfosynctab
		//判断表CenterInfoSync存不存在，如果存在，就删除
		sql = "SELECT COUNT(*) FROM sqlite_master where type='table' and name='CenterInfoSync'";
		ise = impl.getTableIsExists(sql);
		if (ise == 1) {
			sql = "drop table CenterInfoSync";
			if (!impl.updateInfo(sql)) {
				System.out.println("删除表CenterInfoSync成功");
			}
		}
		//创建表AlarmRecordPlan
		sql = "create table CenterInfoSync(ID int primary key,CenterID" +
				" varchar(64)," +
				"CenterName varchar(64),CenterDesc varchar(64)," +
				"CenterIP varchar(64),CenterMask varchar(64)," +
				"CenterGate varchar(64)," +
				"CenterPort int,LoginUserName varchar(64)," +
				"LoginPwd varchar(64),DataBaseIP varchar(64)," +
				"DataBaseUser varchar(64),DataBasePwd varchar(64)," +
				"ParentCenterIP varchar(64),ParentCenterPort int," +
				"DomainName varchar(64),DNSIP varchar(64)," +
				"CenterVar varchar(64),LinkMode int,SyncServerIP varchar(64)," +
				"SyncServerPort int,RouteMode varchar(64));";
		if (!impl.updateInfo(sql)) {
			System.out.println("创建表CenterInfoSync失败");
		} 
//		calen = Calendar.getInstance();
//		endWri = calen.getTimeInMillis();
//		wri += (endWri - startWri);
		
		plans = g.getCenterinfosynctab();
		if(plans==null||plans.length<=1){
			System.out.println("WebService获取CenterInfoSync表的信息为NULL......");
		}else{
//			calen = Calendar.getInstance();
//			startRea = calen.getTimeInMillis();
			String[] array = plans[0];
			for(int i=0;i<array.length;i++){
				System.out.println(array[i]);
			}
			
			
			sqls = new String[plans.length-1];
			for (int i = 1; i < plans.length; i++) {
				// int id=Integer.parseInt(g.getTongDao("3")[i][0]);
				int ParentCenterPort = Integer.parseInt(plans[i][0]);
				String CenterID = plans[i][1];
				String SyncServerIP = plans[i][2];
				String CenterVar = plans[i][3];
				String DataBasePwd = plans[i][4];
				String DataBaseUser = plans[i][5];
				String CenterMask = plans[i][6];
				String CenterIP = plans[i][7];
				int Id = Integer.parseInt(plans[i][8]);
				String CenterDesc = plans[i][9];
				String CenterName = plans[i][10];
				String CenterGate = plans[i][11];
				String DomainName = plans[i][12];
				int SyncServerPort = Integer.parseInt(plans[i][13]);
				String LoginPwd = plans[i][14];
				int LinkMode = Integer.parseInt(plans[i][15]);
				int CenterPort = Integer.parseInt(plans[i][16]);
				String DNSIP = plans[i][17];
				String DataBaseIP = plans[i][18];
				String RouteMode = plans[i][19];
				String ParentCenterIP = plans[i][20];
				String LoginUserName = plans[i][21];
				
			
				
				
			
				
				
				
				
				
				
				
				
				
				sqls[i-1] =String.format("insert into CenterInfoSync(ID, " +
						"CenterID, CenterName, CenterDesc, CenterIP, CenterMask," +
						" CenterGate, CenterPort, LoginUserName, LoginPwd, " +
						"DataBaseIP, DataBaseUser, DataBasePwd, ParentCenterIP" +
						", ParentCenterPort, DomainName, DNSIP, CenterVar," +
						" LinkMode, SyncServerIP, SyncServerPort, RouteMode)" +
						" values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s," +
						"%s,%s,%s,%s,%s,%s,%s,%s)",changeObj(Id),
						changeObj(CenterID),changeObj(CenterName),
						changeObj(CenterDesc),changeObj(CenterIP),
						changeObj(CenterMask),changeObj(CenterGate),
						changeObj(CenterPort),changeObj(LoginUserName)
						,changeObj(LoginPwd),changeObj(DataBaseIP)
						,changeObj(DataBaseUser),changeObj(DataBasePwd)
						,changeObj(ParentCenterIP),changeObj(ParentCenterPort)
						,changeObj(DomainName),changeObj(DNSIP),
						changeObj(CenterVar),changeObj(LinkMode),
						changeObj(SyncServerIP),changeObj(SyncServerPort),
						changeObj(RouteMode));
			}
//			calen = Calendar.getInstance();
//			endRea = calen.getTimeInMillis();
//			rea += (endRea - startRea);
//			
//			calen = Calendar.getInstance();
//			startWri = calen.getTimeInMillis();
			if(!impl.initInsert(sqls)){
				System.out.println("初始化表CenterInfoSync失败");
			}else{
				System.out.println("初始化表CenterInfoSync成功");
			}
		}
	}
	
	private void initCenterRouteInfo(){
		
		CenterNetWorkDao netDao = new CenterNetWorkImpl() ;
		List<CenterNetWork> list = netDao.getAllCenterNetWork();
		CenterRouteDao routeDao = new CenterRouteImpl();
		int id = routeDao.getAllCenterRoute().size()+1;
		for(int i=0;i<list.size();i++){
			
			CenterNetWork cen = list.get(i);
			
			String route = cen.getNetWorkNodeID();
//			System.out.println("i:"+i+"      "+route);
			StringBuffer str = new StringBuffer(route);
			List<String> listP = new ArrayList<String>();
			//向下级查找路由信息
			getNextRoutePath(route,str,list,listP);
//			//向上级级查找路由信息
//			getLastRoutePath(route,str,list,listP);
//			for(String s:listP){
//				System.out.println("S:"+s);
//			}
			for(int j = 0;j<listP.size();j++){
				//将每一个路由信息分解成一个数组
				String [] paths = listP.get(j).split(",");
				if(paths.length>0){
					CenterRoute cenRou = new CenterRoute(id++,paths[0],paths[paths.length-1],listP.get(j));
					routeDao.addCenterRoute(cenRou);
				}
			}
			
			
		}
	}
	
	/**
	 * 向下级查找路由信息
	 * @param route
	 * @param str
	 * @param list
	 * @return
	 */
	private boolean getNextRoutePath(String route,StringBuffer str,List<CenterNetWork> list,List<String> paths){
		boolean result = true;
		while(result){
			result = false;
			for(int i=0;i<list.size();i++){
				CenterNetWork cen = list.get(i);
				if(cen.getNetWorkNodeID().equals(route)&&cen.getIsParentFlag()==1){
					str.append(","+cen.getCenterId());
//					System.out.println(str);
					paths.add(str.toString());
//					System.out.println("==="+str);
					result = getNextRoutePath(cen.getCenterId(),str,list,paths);
					
					str.delete(str.lastIndexOf(","),str.length());
//					System.out.println("****"+str);
				}
			}
		}
		return result;
	}
	
//	/**
//	 * 向上级级查找路由信息
//	 * @param route
//	 * @param str
//	 * @param list
//	 * @return
//	 */
//	private boolean getLastRoutePath(String route,StringBuffer str,List<CenterNetWork> list,List<String> paths){
//		boolean result = true;
//		while(result){
//			result = false;
//			for(int i=0;i<list.size();i++){
//				CenterNetWork cen = list.get(i);
//				if(cen.getCenterId().equals(route)&&cen.getIsParentFlag()==1){
//					str.append(","+cen.getNetWorkNodeID());
//					paths.add(new String(str));
//					result = getLastRoutePath(cen.getNetWorkNodeID(),str,list,paths);
//				}
//			}
//		}
//		return result;
//	}
	
	
	
	/**
     * �����豸��Ϣ
     * @param bpo
     * @return
     */
    public boolean updateInfo(BasePO bpo){
    	//ͨ�����ȡ����
    	Class<?> c = bpo.getClass();
		String name=c.getSimpleName();
		System.out.println("Class:"+name);
//		System.out.println("����Ķ���"+name);
		//��ݲ�ͬ���ͣ��޸Ĳ�ͬ�ı�
		if(name.equals("Canmel")){
			return new CanmelImpl().updateCanmel((Canmel)bpo);
		}else if(name.equals("AlrmPoint")){
			return new AlrmPointImpl().updateAlrmPoint((AlrmPoint)bpo);
		}else if(name.equals("DeviceStatus")){
			return new DeviceStatusImpl().updateDeviceServer((DeviceStatus)bpo);
		}else if(name.equals("OutPoint")){
			return new OutPointImpl().updateOutPoint((OutPoint)bpo);
		}else{
			System.out.println("传入对象类型错误");
			return false;
		}
    }
    
    /**
     * �����豸��Ϣ
     * @param devId
     * @param channelId
     * @param devStatus
     * @param typeid
     * @return
     */
    public boolean updateInfo(String devId,int channelId,int devStatus,int typeid){
    	if(typeid==3){
    		Canmel c = new Canmel();
    		c.setChannelID(channelId);
    		c.setDeviceId(devId);
    		c.setMotionStatus(devStatus);
    		return updateInfo(c);
    	}else if(typeid==4){
    		Canmel c = new Canmel();
    		c.setChannelID(channelId);
    		c.setDeviceId(devId);
    		c.setVideoLost(devStatus);
    		return updateInfo(c);
    	}else if(typeid==1){
    		AlrmPoint a = new AlrmPoint();
    		a.setChannelID(channelId);
    		a.setDeviceId(devId);
    		a.setDeviceStatus(devStatus);
    		return updateInfo(a);
    	}else if(typeid==2){
    		OutPoint o = new OutPoint();
    		o.setChannelID(channelId);
    		o.setDeviceId(devId);
    		o.setDeviceStatus(devStatus);
    		return updateInfo(o);
    	}else{
    		System.out.println("�������ʹ���");
    		return false;
    	}
    }
	
    /**
     * ������������������ѯ����Ӧ�����е��豸��Ϣ
     * @return
     */
    public List<Map<String,String>> getDeviceBySwitchSvrID(String switchSvrId){
    	return new DeviceStatusImpl().getDeviceBySwitchSvrID(switchSvrId);
    }
    
    public List<Map<String,String>> getDeviceByCenterID(String centerID){
    	return new DeviceStatusImpl().getDeviceByCenterID(centerID);
    }
    
    
    public String[][] getCenterNetWork(String centerId,String url){
    	GetValue g = new GetValue(url);
    	String[][] ss = g.getCenterNetworkInfo(centerId);
    	return ss;
    }
}
