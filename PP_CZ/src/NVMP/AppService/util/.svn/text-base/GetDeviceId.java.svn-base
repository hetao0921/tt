package NVMP.AppService.util;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.fxdigital.bean.NvmpDevice;
import com.mysql.impl.ConvertMapUtil;
import com.mysql.impl.Hibernate;

 
public class GetDeviceId {

	private static GetDeviceId device;
	
	public static GetDeviceId getInstance(){
		if(device==null){
			device = new GetDeviceId();
		}
		return device;
	}
	
	
	
	
	
    /**
     * 转化字符
     * @param a
     * @return
     */
    private Object changeObj(Object a){
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
    
	public String getDeviceId(String type){
		
		String map=ConvertMapUtil.map(NvmpDevice.class);
		String sql="select "+map+" from NvmpDevice where deviceType='"+type+"'";
		String deviceSn=null;
		try {
		List<HashMap<String,String>> list=	Hibernate.getHibernate().createQuery(sql);
		if(list==null||list.size()==0){
			return null;
		}else{
			deviceSn=	list.get(0).get("DeviceSN".toLowerCase());
			System.out.println("get deviceSn:"+deviceSn);
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return deviceSn;
	}
	
	public static void main(String...args){
		String sn = GetDeviceId.getInstance().getDeviceId("001");
		
		System.out.println("DeviceSN:"+sn);
	}
}
