package NVMP.DeviceManage.Implement.other;

import java.util.List;
import java.util.Map;

import org.misc.log.LogUtil;

import NVMP.DeviceManage.Implement.DeviceRun;

public class TestHandler extends Thread{

//	private SqliteDBConne sqlite = SqliteDBConne.getDbConne(); 
//	private ReadDBConne test;
	private DeviceRun dr;
	private String centerid;
	public TestHandler(String dbip, String user, String passwd, DeviceRun dCLient,String centerid) {
		// TODO Auto-generated constructor stub
//		String url = "jdbc:mysql://"+dbip+":3306/nvmpmonitor?user="+user+"&password="+passwd;
//		test = ReadDBConne.getDbConne(url);
		dr = dCLient;
		this.centerid = centerid;
	}
	
	public void run() {
//		System.out.println("gggggggggg进入的我的线程，从数据库读取数据");
		
		// TODO Auto-generated method stub
		try {
		
		while(true){
			//邹会给我一个centerId
			String centerId = centerid;
			SqliteDBConne sqlite = SqliteDBConne.getDbConne(); 
			ReadDBConne test = ReadDBConne.getDbConne();

			//测试数据库连接，如果不在，10秒后重新连接。
			if(test.getConne()==null){
				System.out.println("获取连接失败，10秒后再次获取连接");
				Thread.sleep(10000);
				continue;
			}
			
			
			//根据centerId获取设备基本信息
			List<Map<String,String>> listDev = test.getNvmp_DeviceBaseInfoTab(centerId);
			//查询内存数据库中的时间记录表中是否有值，如果无值，则直接上报；如果有值，则判断最后修改时间是否改变
			List<Map<String,String>> listTime = sqlite.getTimeRecord();
			//如果时间记录表中无值，说明是第一次开始，直接上报
			if(listTime.size()==0){
//				System.out.println("gggggggggg第一次进来了，读取到数据量："+listDev.size());
				for(int i=0;i<listDev.size();i++){
					//将设备基本信息拼成一个字符串
					String str = "{DeviceBaseInfoTab}{ID}"+listDev.get(i).get("ID")+"{/ID}{DeviceID}"+
					listDev.get(i).get("DeviceID")+"{/DeviceID}{DeviceName}"+listDev.get(i).get("DeviceName")
					+"{/DeviceName}{DeviceIP}"+listDev.get(i).get("DeviceIP")+"{/DeviceIP}{DeviceMask}"+
					listDev.get(i).get("DeviceMask")+"{/DeviceMask}{DeviceGate}"+listDev.get(i).
					get("DeviceGate")+"{/DeviceGate}{LoginName}"+listDev.get(i).get("LoginName")+
					"{/LoginName}{LoginPwd}"+listDev.get(i).get("LoginPwd")+"{/LoginPwd}{DevicePort}"+
					listDev.get(i).get("DevicePort")+"{/DevicePort}{DeviceCategory}"+listDev.get(i).
					get("DeviceCategory")+"{/DeviceCategory}{RegisterTime}"+listDev.get(i).get("RegisterTime")
					+"{/RegisterTime}{UpdateTime}"+listDev.get(i).get("UpdateTime")+"{/UpdateTime}{DeviceType}"+
					listDev.get(i).get("DeviceType")+"{/DeviceType}{DeviceModel}"+listDev.get(i).
					get("DeviceModel")+"{/DeviceModel}{DepartID}"+listDev.get(i).get("DepartID")+
					"{/DepartID}{SwitchSrvID}"+listDev.get(i).get("SwitchSrvID")+"{/SwitchSrvID}{Online}"+
					listDev.get(i).get("Online")+"{/Online}{CommandStatus}"+listDev.get(i).get("CommandStatus")
					+"{/CommandStatus}{BeginTime}"+listDev.get(i).get("BeginTime")+"{/BeginTime}{EndTime}"+listDev.get(i).get("EndTime")+"{/EndTime}{UsedCpu}"+
					listDev.get(i).get("UsedCpu")+"{/UsedCpu}{UsedMem}"+listDev.get(i).get("UsedMem")+
					"{/UsedMem}{UsedNet}"+listDev.get(i).get("UsedNet")+"{/UsedNet}{CenterID}"+listDev.
					get(i).get("CenterID")+"{/CenterID}{Version}"+listDev.get(i).get("Version")+
					"{/Version}{NetStream}"+listDev.get(i).get("NetStream")+"{/NetStream}{/DeviceBaseInfoTab}";
					String devId = listDev.get(i).get("DeviceID");
					//获取指挥终端信息
					List<Map<String,String>> listCH = test.getNvmp_CommandDevCHTabByDeviceId(devId);
					//根据设备ID查询出所有对应的指挥终端通道信息，加入到上面的字符串中
					for(int j=0;j<listCH.size();j++){
						Map<String,String> map = listCH.get(j);
						str += "{CommandDevCHTab}{ID}"+map.get("ID")+"{/ID}{DeviceID}"+map.get("DeviceID")
						+"{/DeviceID}{VideoCH}"+map.get("VideoCH")+"{/VideoCH}{AudioCH}"+map.get("AudioCH")
						+"{/AudioCH}{CHName}"+map.get("CHName")+"{/CHName}{BitStream}"+map.get("BitStream")
						+"{/BitStream}{CenterID}"+map.get("CenterID")+"{/CenterID}{CurrentBitStream}"+
						map.get("CurrentBitStream")+"{/CurrentBitStream}{UpdateTime}"+map.get("UpdateTime")
						+"{/UpdateTime}{/CommandDevCHTab}";
					}
					//获取视频转换信息
					List<Map<String,String>> listSwitch = test.getNvmp_VideoSwitchCHTabByDeviceId(devId);
					//根据设备ID查询出所有对应的指视频转换信息，加入到上面的字符串中
					for(int j=0;j<listSwitch.size();j++){
						Map<String,String> map = listSwitch.get(j);
						str += "{VideoSwitchCHTab}{ID}"+map.get("ID")+"{/ID}{DeviceID}"+map.get("DeviceID")
						+"{/DeviceID}{CH}"+map.get("CH")+"{/CH}{CHName}"+map.get("CHName")+"{/CHName}{SourceIP}"+
						map.get("SourceIP")+"{/SourceIP}{DescIP}"+map.get("DescIP")+"{/DescIP}{BitStream}"+
						map.get("BitStream")+"{/BitStream}{CenterID}"+map.get("CenterID")+"{/CenterID}" +
								"{UpdateTime}"+map.get("UpdateTime")+"{/UpdateTime}{/VideoSwitchCHTab}";
					}
					
					//将数据加到内存数据库中
					String sql = "insert into TimeRecord(DeviceId,UpdateTime) values('"+listDev.get(i)
					.get("DeviceID")+"','"+listDev.get(i).get("UpdateTime")+"');";
//					System.out.println("内存数据库SQL:"+sql);
					sqlite.updateSql(sql);
					
					//将数据上报，调用邹的方法
					dr.sendXML(str);
//					System.out.println("gggggggggggggggggg将数据上报成功了，数据是："+str);
				}
				
			}else{      //如果有值，则说明不是第一次，要判断最后修改时间是否改变，若改变，才上报
//				System.out.println("ggggggggggggggggg不是第一次读取数据");
				for(int i=0;i<listDev.size();i++){
					Map<String,String> map = listDev.get(i);
					String updateTime = getUpdateTime(map.get("DeviceID"),listTime);
					if(updateTime==null){
//						LogUtil.error("根据设备ID从内存数据库中获取到的值是null！");
					}else{
						if(map.get("UpdateTime").equals(updateTime)){
//							LogUtil.error("这条数据的最后修改时间没有改变，所以不用上报......");
						}else{
							//将设备基本信息拼成一个字符串
							String str = "{DeviceBaseInfoTab}{ID}"+map.get("ID")+"{/ID}{DeviceID}"+
							map.get("DeviceID")+"{/DeviceID}{DeviceName}"+map.get("DeviceName")
							+"{/DeviceName}{DeviceIP}"+map.get("DeviceIP")+"{/DeviceIP}{DeviceMask}"+
							map.get("DeviceMask")+"{/DeviceMask}{DeviceGate}"+map.
							get("DeviceGate")+"{/DeviceGate}{LoginName}"+map.get("LoginName")+
							"{/LoginName}{LoginPwd}"+map.get("LoginPwd")+"{/LoginPwd}{DevicePort}"+
							map.get("DevicePort")+"{/DevicePort}{DeviceCategory}"+map.
							get("DeviceCategory")+"{/DeviceCategory}{RegisterTime}"+map.get("RegisterTime")
							+"{/RegisterTime}{UpdateTime}"+map.get("UpdateTime")+"{/UpdateTime}{DeviceType}"+
							map.get("DeviceType")+"{/DeviceType}{DeviceModel}"+map.
							get("DeviceModel")+"{/DeviceModel}{DepartID}"+map.get("DepartID")+
							"{/DepartID}{SwitchSrvID}"+map.get("SwitchSrvID")+"{/SwitchSrvID}{Online}"+
							map.get("Online")+"{/Online}{CommandStatus}"+map.get("CommandStatus")
							+"{/CommandStatus}{BeginTime}"+listDev.get(i).get("BeginTime")+"{/BeginTime}{EndTime}"+listDev.get(i).get("EndTime")+"{/EndTime}{UsedCpu}"+
							map.get("UsedCpu")+"{/UsedCpu}{UsedMem}"+map.get("UsedMem")+
							"{/UsedMem}{UsedNet}"+map.get("UsedNet")+"{/UsedNet}{CenterID}"+map.get("CenterID")
							+"{/CenterID}{Version}"+listDev.get(i).get("Version")+
							"{/Version}{NetStream}"+map.get("NetStream")+"{/NetStream}{/DeviceBaseInfoTab}";
							String devId = map.get("DeviceID");
							//获取指挥终端信息
							List<Map<String,String>> listCH = test.getNvmp_CommandDevCHTabByDeviceId(devId);
							//根据设备ID查询出所有对应的指挥终端通道信息，加入到上面的字符串中
							for(int j=0;j<listCH.size();j++){
								Map<String,String> mapC = listCH.get(j);
								str += "{CommandDevCHTab}{ID}"+mapC.get("ID")+"{/ID}{DeviceID}"+mapC.get("DeviceID")
								+"{/DeviceID}{VideoCH}"+mapC.get("VideoCH")+"{/VideoCH}{AudioCH}"+mapC.get("AudioCH")
								+"{/AudioCH}{CHName}"+mapC.get("CHName")+"{/CHName}{BitStream}"+mapC.get("BitStream")
								+"{/BitStream}{CenterID}"+mapC.get("CenterID")+"{/CenterID}{CurrentBitStream}"+
								mapC.get("CurrentBitStream")+"{/CurrentBitStream}{UpdateTime}"+mapC.get("UpdateTime")
								+"{/UpdateTime}{/CommandDevCHTab}";
							}
							//获取视频转换信息
							List<Map<String,String>> listSwitch = test.getNvmp_VideoSwitchCHTabByDeviceId(devId);
							//根据设备ID查询出所有对应的指视频转换信息，加入到上面的字符串中
							for(int j=0;j<listSwitch.size();j++){
								Map<String,String> mapS = listSwitch.get(j);
								str += "{VideoSwitchCHTab}{ID}"+mapS.get("ID")+"{/ID}{DeviceID}"+mapS.get("DeviceID")
								+"{/DeviceID}{CH}"+mapS.get("CH")+"{/CH}{CHName}"+mapS.get("CHName")+"{/CHName}{SourceIP}"+
								mapS.get("SourceIP")+"{/SourceIP}{DescIP}"+mapS.get("DescIP")+"{/DescIP}{BitStream}"+
								mapS.get("BitStream")+"{/BitStream}{CenterID}"+mapS.get("CenterID")+"{/CenterID}" +
										"{UpdateTime}"+mapS.get("UpdateTime")+"{/UpdateTime}{/VideoSwitchCHTab}";
							}
							
							//修改内存数据库中的最后修改时间
							sqlite.updateSql("update TimeRecord set UpdateTime='"+map.get("UpdateTime")+
									"' where DeviceId='"+map.get("DeviceId")+"';");
							
							//将数据上报，调用邹的方法
							dr.sendXML(str);
//							System.out.println("ggggggggggg数据上报完毕，上报的数据是："+str);
						}
					}
				}
			}
			try{
				Thread.sleep(15000);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
		} catch(Exception e) {
			
			e.printStackTrace();
			System.out.println("如果进入这个，就是中断了循环，是不允许的");
		}
	}
	
	/**
	 * 根据设备ID在内存数据库中查找对应的最后修改时间
	 * @param devId
	 * @param list
	 * @return
	 */
	private String getUpdateTime(String devId,List<Map<String,String>> list){
		for(int i=0;i<list.size();i++){
			
			Map<String,String> map = list.get(i);
//			System.out.println("传入的设备ID："+devId);
//			System.out.println("内存中设备ID："+map.get("DeviceId"));
			if(map.get("DeviceId").equals(devId)){
				return map.get("UpdateTime");
			}
		}
		return null;
	}
	
}