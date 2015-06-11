package NVMP.DeviceManage.Implement.other;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;


public class ReadDBConne {

	private Connection conn = null;
	private static ReadDBConne dbConne;
	
	private ReadDBConne(){}
	
	
	public static synchronized ReadDBConne getDbConne() {
		if (dbConne == null) {
			dbConne = new ReadDBConne();
		}
		return dbConne;
	}
	
	public static synchronized ReadDBConne getDbConne(String url) {
		if (dbConne == null) {
			dbConne = new ReadDBConne();
		}
		return dbConne;
	}
	
	public Connection getConne(){
		if(conn==null){
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(getUrl());
			} catch (Exception e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				System.out.println("建立连接失败，下次再尝试");
			}
		}
		return conn;
	}
	
	public String getUrl(){
		String path = "";
		if (System.getProperty("os.name").equals("Linux"))
			path = "/etc/fxconf/state/url.xml";
		else
			path = "d:\\fxconf\\state\\url.xml";

		// 读一下配置文件中的配置。
		SAXReader saxReader = new SAXReader();
		Document doc = null;
		try {
			doc = saxReader.read(new File(path));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String  dbip = 	doc.getRootElement().element("IP").getText().trim();
		String  user = 	doc.getRootElement().element("USER").getTextTrim();
		String  passwd = 	doc.getRootElement().element("PASS").getTextTrim();
		String port = doc.getRootElement().element("PORT").getTextTrim();
		String url = "jdbc:mysql://"+dbip+":"+port+"/nvmpmonitor?user="+user+"&password="+passwd+"&characterEncoding=gbk&autoReconnect=true";
		return url;
	}
	
	/**
	 * 异常处理，将对象置null，以便下次重连
	 */
	private void exceptionHandler(){
		try{
			if(conn!=null)
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		conn = null;
		dbConne = null;
	}
	
	/**
	 * 获取设备基本信息
	 * @param centerId
	 *        邹传给我的centerId
	 * @return
	 */
	public List<Map<String,String>> getNvmp_DeviceBaseInfoTab(String centerId){
//		System.out.println("ggggggggggReadDBConne的URL是："+getUrl());
		String sql =String.format("select * from Nvmp_DeviceBaseInfoTab where CenterID=%s",changeObj(centerId));
//		System.out.println("gggggggggggSQL语句是："+sql);
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		try{
			Connection con = getConne();
			if(con==null)
				return null;
			Statement stat = con.createStatement();
			ResultSet rst = stat.executeQuery(sql);
			while(rst.next()){
				Map<String,String> map = new HashMap<String,String>();
				map.put("ID","'"+rst.getInt("ID")+"'");
				map.put("DeviceID",rst.getString("DeviceID"));
				map.put("DeviceName",rst.getString("DeviceName"));
				map.put("DeviceIP",rst.getString("DeviceIP"));
				map.put("DeviceMask",rst.getString("DeviceMask"));
				map.put("DeviceGate",rst.getString("DeviceGate"));
				map.put("LoginName",rst.getString("LoginName"));
				map.put("LoginPwd",rst.getString("LoginPwd"));
				map.put("DeviceID",rst.getString("DeviceID"));
				map.put("DevicePort","'"+rst.getInt("DevicePort")+"'");
				map.put("DeviceCategory","'"+rst.getInt("DeviceCategory")+"'");
				map.put("RegisterTime",rst.getString("RegisterTime"));
				map.put("UpdateTime",rst.getString("UpdateTime"));
				map.put("DeviceType","'"+rst.getInt("DeviceType")+"'");
				map.put("DeviceModel","'"+rst.getInt("DeviceModel")+"'");
				map.put("DepartID",rst.getString("DepartID"));
				map.put("SwitchSrvID",rst.getString("SwitchSrvID"));
				map.put("Online","'"+rst.getInt("Online")+"'");
				map.put("CommandStatus","'"+rst.getInt("CommandStatus")+"'");
				map.put("BeginTime",rst.getString("BeginTime"));
				map.put("EndTime",rst.getString("EndTime"));
				map.put("UsedCpu","'"+rst.getInt("UsedCpu")+"'");
				map.put("UsedMem","'"+rst.getInt("UsedMem")+"'");
				map.put("UsedNet","'"+rst.getInt("UsedNet")+"'");
				map.put("CenterID",rst.getString("CenterID"));
				map.put("Version",rst.getString("Version"));
				map.put("NetStream","'"+rst.getInt("NetStream")+"'");
				list.add(map);
			}
			
			rst.close();
			stat.close();
		}catch(Exception e){
			e.printStackTrace();
			exceptionHandler();
		}
//		System.out.println("ggggggggggg查询到设备基本信息量："+list.size());
		return list;
	}
	
	/**
	 * 获取指挥终端通道信息
	 * @param devId
	 * @return
	 */
	public List<Map<String,String>> getNvmp_CommandDevCHTabByDeviceId(String devId){
		String sql =String.format("select * from Nvmp_CommandDevCHTab where DeviceID=%s",changeObj(devId));
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		try{
			Connection con = getConne();
			Statement stat = con.createStatement();
			ResultSet rst = stat.executeQuery(sql);
			while(rst.next()){
				Map<String,String> map = new HashMap<String,String>();
				map.put("ID","'"+rst.getInt("ID")+"'");
				map.put("DeviceID",rst.getString("DeviceID"));
				map.put("VideoCH","'"+rst.getInt("VideoCH")+"'");
				map.put("AudioCH","'"+rst.getInt("AudioCH")+"'");
				map.put("CHName",rst.getString("CHName"));
				map.put("BitStream","'"+rst.getInt("BitStream")+"'");
				map.put("CenterID",rst.getString("CenterID"));
				map.put("CurrentBitStream","'"+rst.getInt("CurrentBitStream")+"'");
				map.put("UpdateTime",rst.getString("UpdateTime"));
				list.add(map);
			}
			rst.close();
			stat.close();
		}catch(Exception e){
			e.printStackTrace();
			exceptionHandler();
		}
		return list;
	}
	
	/**
	 * 获取视频交换信息
	 * @param devId
	 * @return
	 */
	public List<Map<String,String>> getNvmp_VideoSwitchCHTabByDeviceId(String devId){
		String sql =String.format("select * from Nvmp_VideoSwitchCHTab where DeviceID=%s",changeObj(devId));
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		try{
			Connection con = getConne();
			Statement stat = con.createStatement();
			ResultSet rst = stat.executeQuery(sql);
			while(rst.next()){
				Map<String,String> map = new HashMap<String,String>();
				map.put("ID","'"+rst.getInt("ID")+"'");
				map.put("DeviceID",rst.getString("DeviceID"));
				map.put("CH","'"+rst.getInt("CH")+"'");
				map.put("CHName",rst.getString("CHName"));
				map.put("SourceIP",rst.getString("SourceIP"));
				map.put("DescIP",rst.getString("DescIP"));
				map.put("BitStream","'"+rst.getInt("BitStream")+"'");
				map.put("CenterID",rst.getString("CenterID"));
				map.put("UpdateTime",rst.getString("UpdateTime"));
				list.add(map);
			}
			rst.close();
			stat.close();
		}catch(Exception e){
			e.printStackTrace();
			exceptionHandler();
		}
		return list;
	}
	
	/**
     * ת���ַ�
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

}

