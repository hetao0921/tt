package NVMP.StateManageDomain;

public class XmlToInsert {

	private static XmlToInsert xmlToInsert;
	private UpdateDBConne db;
	private XmlToInsert(){
		db = UpdateDBConne.getDbConne();
	}
//	private XmlToInsert(String url){
//		db = UpdateDBConne.getDbConne(url);
//	}
	
//	public static synchronized XmlToInsert getXmlToInsert(String u) {
//		if (xmlToInsert == null) {
//			xmlToInsert = new XmlToInsert(u);
//		}
//		return xmlToInsert;
//	}
	
	public static synchronized XmlToInsert getXmlToInsert() {
		if (xmlToInsert == null) {
			xmlToInsert = new XmlToInsert();
		}
		return xmlToInsert;
	}
	
	private int getIntNum(String str){
		String s = str.substring(1, (str.length()-1));
		System.out.println("str:"+str);
		System.out.println("s:"+s);
		return Integer.parseInt(s);
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
    
	public void xmlToInsert(String xml){
//		System.out.println("ggggggggggggg从XML中反解析设备基本信息");
//		System.out.println("gggggggggggggUpdateDBConne的URL是："+db.getUrl());
		//从XML中反解析设备基本信息
		String devBase = getStr("DeviceBaseInfoTab",xml);
		String DeviceID = getStr("DeviceID",devBase);
		String DeviceName = getStr("DeviceName",devBase);
		String DeviceIP = getStr("DeviceIP",devBase);
		String DeviceMask = getStr("DeviceMask",devBase);
		String DeviceGate = getStr("DeviceGate",devBase);
		String LoginName = getStr("LoginName",devBase);
		String LoginPwd = getStr("LoginPwd",devBase);
		int DevicePort;
		if(getStr("DevicePort",devBase).trim().equals(""))
			DevicePort = 0;
		else
			DevicePort = getIntNum(getStr("DevicePort",devBase));
		int DeviceCategory;
		if(getStr("DeviceCategory",devBase).equals(""))
			DeviceCategory = 0;
		else
			DeviceCategory = getIntNum(getStr("DeviceCategory",devBase));
//		int DeviceCategory = Integer.parseInt(getStr("DeviceCategory",devBase));
		String RegisterTime = getStr("RegisterTime",devBase);
		String UpdateTime = getStr("UpdateTime",devBase);
		int DeviceType;
		if(getStr("DeviceType",devBase).equals(""))
			DeviceType = 0;
		else
			DeviceType = getIntNum(getStr("DeviceType",devBase));
//		int DeviceType = Integer.parseInt(getStr("DeviceType",devBase));
		int DeviceModel;
		if(getStr("DeviceModel",devBase).equals(""))
			DeviceModel = 0;
		else
			DeviceModel = getIntNum(getStr("DeviceModel",devBase));
//		int DeviceModel = Integer.parseInt(getStr("DeviceModel",devBase));
		String DepartID = getStr("DepartID",devBase);
		String SwitchSrvID = getStr("SwitchSrvID",devBase);
//		int Online = Integer.parseInt(getStr("Online",devBase));
		int Online;
		if(getStr("Online",devBase).equals(""))
			Online = 0;
		else
			Online = getIntNum(getStr("Online",devBase));
//		int CommandStatus = Integer.parseInt(getStr("CommandStatus",devBase));
		int CommandStatus;
		if(getStr("CommandStatus",devBase).equals(""))
			CommandStatus = 0;
		else
			CommandStatus = getIntNum(getStr("CommandStatus",devBase));
		String BeginTime = getStr("BeginTime",devBase);
		String EndTime = getStr("EndTime",devBase);
//		int UsedCpu = Integer.parseInt(getStr("UsedCpu",devBase));
		int UsedCpu;
		if(getStr("UsedCpu",devBase).equals(""))
			UsedCpu = 0;
		else
			UsedCpu = getIntNum(getStr("UsedCpu",devBase));
//		int UsedMem = Integer.parseInt(getStr("UsedMem",devBase));
		int UsedMem;
		if(getStr("UsedMem",devBase).equals(""))
			UsedMem = 0;
		else
			UsedMem = getIntNum(getStr("UsedMem",devBase));
//		int UsedNet = Integer.parseInt(getStr("UsedNet",devBase));
		int UsedNet;
		if(getStr("UsedNet",devBase).equals(""))
			UsedNet = 0;
		else
			UsedNet = getIntNum(getStr("UsedNet",devBase));
		String CenterID = getStr("CenterID",devBase);
		String Version = getStr("Version",devBase);
//		int NetStream = Integer.parseInt(getStr("NetStream",devBase));
		int NetStream;
		if(getStr("NetStream",devBase).equals(""))
			NetStream = 0;
		else
			NetStream = getIntNum(getStr("NetStream",devBase));
		String sql = String.format("select count(*) from Nvmp_DeviceBaseInfoTab where DeviceID = %s",changeObj(DeviceID));
		if(db.getTableNum(sql)==1){
			sql = String.format("delete from Nvmp_DeviceBaseInfoTab where DeviceID = %s",changeObj(DeviceID));
			db.updateSql(sql);
		}
		sql = String.format("insert into Nvmp_DeviceBaseInfoTab(DeviceID,DeviceName,DeviceIP," +
				"DeviceMask,DeviceGate,LoginName,LoginPwd,DevicePort,DeviceCategory,RegisterTime," +
				"UpdateTime,DeviceType,DeviceModel,DepartID,SwitchSrvID,Online,CommandStatus,BeginTime,EndTime" +
				",UsedCpu,UsedMem,UsedNet,CenterID,Version,NetStream) values(%s,%s,%s,%s,%s,%s,%s,%s" +
				",%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s);",changeObj(DeviceID),changeObj
				(DeviceName),changeObj(DeviceIP),changeObj(DeviceMask),changeObj(DeviceGate),changeObj
				(LoginName),changeObj(LoginPwd),changeObj(DevicePort),changeObj(DeviceCategory),changeObj
				(RegisterTime),changeObj(UpdateTime),changeObj(DeviceType),changeObj(DeviceModel),changeObj
				(DepartID),changeObj(SwitchSrvID),changeObj(Online),changeObj(CommandStatus),changeObj
				(BeginTime),changeObj(EndTime),changeObj(UsedCpu),changeObj(UsedMem),changeObj(UsedNet)
				,changeObj(CenterID),changeObj(Version),changeObj(NetStream));
		//向数据库中插入该信息
//		System.out.println(sql);
		
		db.updateSql(sql);
		
		
		//从XML中反解析指挥终端通道信息
//		System.out.println("xml:"+xml);
		String devCh = getStr("CommandDevCHTab",xml);
		if(devCh!=null){
			String [] chs = devCh.split("/CommandDevCHTab");
			for(int i = 0;i<chs.length;i++){
				String ch = chs[i];
				String cDeviceID = getStr("DeviceID",ch);
//				int VideoCH = Integer.parseInt(getStr("VideoCH",ch));
				int VideoCH;
				if(getStr("VideoCH",ch).equals(""))
					VideoCH = 0;
				else
					VideoCH = getIntNum(getStr("VideoCH",ch));
//				int AudioCH = Integer.parseInt(getStr("AudioCH",ch));
				int AudioCH;
				if(getStr("AudioCH",ch).equals(""))
					AudioCH = 0;
				else
					AudioCH = getIntNum(getStr("AudioCH",ch));
				String CHName = getStr("CHName",ch);
//				int BitStream = Integer.parseInt(getStr("BitStream",ch));
				int BitStream;
				if(getStr("BitStream",ch).equals(""))
					BitStream = 0;
				else
					BitStream = getIntNum(getStr("BitStream",ch));
				String cCenterID = getStr("CenterID",ch);
//				int CurrentBitStream = Integer.parseInt(getStr("CurrentBitStream",ch));
				int CurrentBitStream;
				if(getStr("CurrentBitStream",ch).equals(""))
					CurrentBitStream = 0;
				else
					CurrentBitStream = getIntNum(getStr("CurrentBitStream",ch));
				String cUpdateTime = getStr("UpdateTime",ch);
				sql = String.format("select count(*) from Nvmp_CommandDevCHTab where DeviceID = %s and VideoCH = %s", changeObj(cDeviceID),changeObj(VideoCH));
				if(db.getTableNum(sql)==1){
					sql = String.format("delete from Nvmp_CommandDevCHTab where DeviceID = %s and VideoCH = %s", changeObj(cDeviceID),changeObj(VideoCH));
				}
				sql = String.format("insert into Nvmp_CommandDevCHTab(DeviceID,VideoCH,AudioCH,CHName," +
						"BitStream,CenterID,CurrentBitStream,UpdateTime) values(%s,%s,%s,%s,%s,%s,%s,%s);",
						changeObj(cDeviceID),changeObj(VideoCH),changeObj(AudioCH),changeObj(CHName),changeObj(BitStream),changeObj(cCenterID),changeObj(CurrentBitStream),changeObj(cUpdateTime));
				
				//将该语句插入到mysql数据库中
				db.updateSql(sql);
			}
		}
		
		
		//从XML中反解析视频交换信息
		String vdoSwitch = getStr("VideoSwitchCHTab",xml);
		if(vdoSwitch!=null){
			String [] vdos = vdoSwitch.split("/VideoSwitchCHTab"); 
			for(int i=0;i<vdos.length;i++){
				String vdo  = vdos[i];
				String vDeviceID = getStr("DeviceID",vdo);
//				int CH = Integer.parseInt(getStr("CH",vdo));
				int CH;
				if(getStr("CH",vdo).equals(""))
					CH = 0;
				else
					CH = getIntNum(getStr("CH",vdo));
				String CHName = getStr("CHName",vdo);
				String SourceIP = getStr("SourceIP",vdo);
				String DescIP = getStr("DescIP",vdo);
//				int BitStream = Integer.parseInt(getStr("BitStream",vdo));
				int BitStream;
				if(getStr("BitStream",vdo).equals(""))
					BitStream = 0;
				else
					BitStream = getIntNum(getStr("BitStream",vdo));
				String vCenterID = getStr("CenterID",vdo);
				String vUpdateTime = getStr("UpdateTime",vdo);
//				sql = String.format("select count(*) from Nvmp_CommandDevCHTab where DeviceID = %s and VideoCH = %s", changeObj(cDeviceID),changeObj(VideoCH));
//				if(db.getTableNum(sql)==1){
//					sql = String.format("delete from Nvmp_CommandDevCHTab where DeviceID = %s and VideoCH = %s", changeObj(cDeviceID),changeObj(VideoCH));
//				}
				sql = String.format("insert into Nvmp_VideoSwitchCHTab(DeviceID,CH,CHName,SourceIP,DescIP," +
						"BitStream,CenterID,UpdateTime) values(%s,%s,%s,%s,%s,%s,%s,%s);", changeObj(vDeviceID),changeObj(CH),changeObj(CHName),
								changeObj(SourceIP),changeObj(DescIP),changeObj(BitStream),changeObj(vCenterID),changeObj(vUpdateTime));
				//将该sql语句插入mysql数据库
				db.updateSql(sql);
			}
		}
		
	}
	
	private String getStr(String name,String msg){
		int sIndex = msg.indexOf("{"+name+"}")+name.length()+2;
		int eIndex = msg.lastIndexOf("{/"+name+"}");
		if(eIndex==-1){
			return null;
		}else{
			String str = msg.substring(sIndex, eIndex);
			return str;
		}
	}
}