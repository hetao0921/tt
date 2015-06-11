package app;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.misc.log.LogUtil;

import app.singlepad.AbstractSinglePadBean;
import app.singlepad.ISinglePad;
import app.singlepad.ISinglePadBean;
import app.singlepad.SinglePadFactory;
import app.singlepad.SinglePadState;

import com.alibaba.fastjson.JSONObject;
import com.mysql.impl.Hibernate;
import com.mysql.jdbc.StringUtils;

import corenet.netbase.NIOReactor;
import corenet.netbase.Interface.ITimer;

/**
 * 单兵系统
 * @author hxht
 * @version 2014-9-16
 */
public class AppServer implements Runnable{
	
	private ITimer time;
	
	private String hostIP;
	private int port;
	
	private Map<ISinglePadBean,ConnectChannel> container =
			new HashMap<ISinglePadBean,ConnectChannel>();
	
	//启动单兵系统
	public void Run(){
		LogUtil.info("单兵系统：开始启动。。。");
		hostIP = getAppServerAttrValue("IP");
		String s_port = getAppServerAttrValue("PORT");
		if(!StringUtils.isNullOrEmpty(s_port)){
			port = Integer.parseInt(s_port);
		}else{
			LogUtil.error("单兵系统：获取服务器端口失败！");
			return;
		}
		
		List<String> types = getAllSinglePadTypes();
		if(types.size() == 0){
			LogUtil.info("单兵系统：没有单兵设备！");
		}
		for(String type : types){
			ISinglePad singlePad = SinglePadFactory.createSinglePad(type);
			List<? extends AbstractSinglePadBean> beans = singlePad.getSinglePads();
			for(ISinglePadBean bean : beans){
				ISinglePad pad = SinglePadFactory.createSinglePad(type);
				pad.setBean(bean);
				pad.setState(SinglePadState.offline);
				ConnectChannel cc = new ConnectChannel(hostIP,port,pad);
				cc.start();
				pad.setConnectChannel(cc);
				LogUtil.info("单兵系统：新增连接通道（"+JSONObject.toJSONString(bean)+"）");
				container.put(bean, cc);
			}
		}
		
		// 获取计时器
		NIOReactor r = (NIOReactor) NIOReactor.defaultDispatcher();
		time = r.newTimer(this);
		time.schedule(10000);
		
	}
	
	//自动刷新单兵设备
	@Override
	public void run() {
		try{
			List<ISinglePadBean> allBeans = new ArrayList<ISinglePadBean>();
			List<String> types = getAllSinglePadTypes();
			if(types.size() == 0){
				LogUtil.info("单兵系统：没有单兵设备！");
			}
			for(String type : types){
				ISinglePad singlePad = SinglePadFactory.createSinglePad(type);
				List<? extends AbstractSinglePadBean> beans = singlePad.getSinglePads();
				allBeans.addAll(beans);
			}
			
			List<ISinglePadBean> delBeans = new ArrayList<ISinglePadBean>();
			for(ISinglePadBean bean : container.keySet()){
				if(!allBeans.contains(bean)){
					delBeans.add(bean);
				}
			}
			
			for(ISinglePadBean bean : delBeans){
				ConnectChannel cc = container.get(bean);
				cc.destroy();
				LogUtil.info("单兵系统：销毁连接通道（"+JSONObject.toJSONString(bean)+"）");
				container.remove(bean);
			}
			
			for(ISinglePadBean bean : allBeans){
				if(!container.containsKey(bean)){
					ISinglePad pad = SinglePadFactory.createSinglePad(bean.getSinglePadType());
					pad.setBean(bean);
					pad.setState(SinglePadState.offline);
					ConnectChannel cc = new ConnectChannel(hostIP,port,pad);
					cc.start();
					pad.setConnectChannel(cc);
					LogUtil.info("单兵系统：新增连接通道（"+JSONObject.toJSONString(bean)+"）");
					container.put(bean, cc);
				}
			}
		}catch(Exception e){
			LogUtil.error("单兵系统：单兵自动刷新失败！"+e.getMessage());
		}
		
		time.schedule(10000);
	}
	
	//获得所有的单兵类型
	private List<String> getAllSinglePadTypes(){
		List<String> types = new ArrayList<String>();
		String sql = "select  new Map(sp.singlePadType as singlePadType) from NvmpSinglepadtab sp,NvmpCenterinfotab ci where sp.centerId=ci.centerId";
		List<HashMap<String,String>> data =Hibernate.getHibernate().createQuery(sql);
		for(HashMap<String,String> hp : data){
			String type = hp.get("singlePadType");
			types.add(type);
		}
		return types;
	}
	
/*	public static void main(String[] args) {
		AppServer app=new AppServer();
		app.getAllSinglePadTypes();
	}*/
	
/*	private List<String> getAllSinglePadTypes(){
		List<String> types = new ArrayList<String>();
		String sql = "select distinct sp.SinglePadType from nvmp_singlepadtab sp,nvmp_centerinfotab ci where sp.CenterID=ci.CenterID";
		List<HashMap<String,String>> data = MySqlManager.getInstance().executeQuery(sql);
		for(HashMap<String,String> row : data){
			String type = row.get("SinglePadType".toLowerCase());
			types.add(type);
		}
		return types;
	}
*/	
	//获得“AppServer”节点的指定属性值
	private String getAppServerAttrValue(String attr) {
		String path = "";
		String value = "";
		if (System.getProperty("os.name").equals("Linux")) {
			path = "/etc/fxconf/AppService/AppService.conf";
		} else {
			path = "D:\\fxconf\\AppService\\AppService.conf";
		}
		File f = new File(path);
		if (f.exists()) {
			try {
				SAXBuilder builder = new SAXBuilder();
				Document doc = builder
						.build(new FileInputStream(new File(path)));
				Element root = doc.getRootElement();
				Element appE = root.getChild("AppServer");
				value = appE.getAttributeValue(attr);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return value;
	}
	
}
