package com.fxdigital.ipmatrix;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.misc.log.LogUtil;

public class RtspMatrixCtrl implements IMatrix {

	/**
	 * 
	 100 HIK
	 * rtsp://192.168.100.83:554/Streaming/Channels/1?transportmode=unicast 202
	 * DH rtsp://192.168.100.89:554/cam/realmonitor?channel=1&subtype=0&unicast=
	 * true&proto=Onvif 221 NV rtsp://192.168.100.80/1
	 * 
	 * 
	 * */
	private static List<RtspResult> list;
	
	static private boolean initflag = false;
	
	private static RtspMatrixCtrl rc = new RtspMatrixCtrl();
	
	public static RtspMatrixCtrl getInstance() {
		return rc;
	}
	

	static {

		try {
			// 此处读取xml，获得相关信息
			list = new ArrayList<RtspResult>();

			// 读一下配置文件中的配置。
			String path;
			if (System.getProperty("os.name").equals("Linux")) {
				path = "/etc/fxconf/hikmatrix/MatrixDevice.xml";
			} else {
				path = "d:\\fxconf\\hikmatrix\\MatrixDevice.xml";
			}
			SAXReader saxReader = new SAXReader();
			Document doc = null;
			doc = saxReader.read(new File(path));
			@SuppressWarnings("unchecked")
			Iterator<Element> itor = doc.getRootElement().elementIterator(
					"devicetype");
			while (itor.hasNext()) {
				Element e = itor.next();
				list.add(new RtspResult(e.attributeValue("name"), e
						.elementText("template"), Boolean.parseBoolean(e
						.elementText("enable")), e.elementText("desc"), e
						.elementText("port")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	static public RtspResult find(String id) {
		RtspResult result = null;
		for (RtspResult e : list) {
			if (e.getName().equals(id)) {
				result = e;
				break;

			}

		}

		if (result == null) {
			result = new RtspResult(false);
		}

		return result;
	}
	
	static public void init() {
		RtspCtrl.init();
		initflag = true;
		
	}
	

	static public int MaxtrixStart(int type,int subtype,String DeviceAddress,Integer DevicePort,String DeviceUser,String DevicePass, String szAddress, int nPort,
			String szName, String szPass, int nChannel, int nScreenRegion) {
		if(!initflag) {
			LogUtil.info(" init lose ");
			return -999;
		}
		LogUtil.info(" * ==============  # type : " + type );
		LogUtil.info(" * ==============  # subtype : " + subtype );
		LogUtil.info(" * ==============  # DeviceAddress : " + DeviceAddress );
		String rtsp = null;
		if(type!=300 && type!=221) {
		
		RtspResult result = find(String.valueOf(type));
		if(!result.isEnable()) {
			return -251;	
		}
		String _dport;
		if(DevicePort==null || DevicePort==0) {
			_dport = result.getPort();
		} else {
			_dport = result.getPort();
		}
		
		nScreenRegion = 0;
		rtsp = String.format(result.getTemplate(), DeviceAddress,_dport,nChannel,DeviceUser,DevicePass);
		} else {
			
			rtsp = DeviceAddress;
		}
		
		LogUtil.info(" * ==============  # rtsp : " + rtsp );
		LogUtil.info(" * ==============  # szAddress : " + szAddress );
		LogUtil.info(" * ==============  # nPort : " + nPort );
		LogUtil.info(" * ==============  # szName : " + szName );
		LogUtil.info(" * ==============  # szPass : " + szPass );
		LogUtil.info(" * ==============  # tvidex : " + nChannel );
		LogUtil.info(" * ==============  # pos : " + nScreenRegion );
		return RtspCtrl.MaxtrixStart(rtsp, szAddress, nPort,
				szName, szPass, nChannel, nScreenRegion);
	}

	 public boolean MaxtrixStop(int nMaxtrixHandle) {
		if(!initflag) {
			LogUtil.info(" init lose ");
			return false;
		}
		return RtspCtrl.MaxtrixStop(nMaxtrixHandle);
	}

	
	static public void main(String ...args) {
//		System.out.println(RtspMatrixCtrl.find("100").getTemplate());
		
//		int a = RtspMatrixCtrl.MaxtrixStart(555, "192.168.0.77","192.168.0.77", 3000, "Admin", "1111", 0, 0);
//		System.out.println(a);
	}
	
	
	
	
}
