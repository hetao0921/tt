package com.sqlite.impl;

import java.util.ArrayList;
import java.util.List;

import com.sqlite.dao.VideoDeviceDao;
import com.sqlite.factory.DAOFactory;
import com.sqlite.pojo.AlrmPoint;
import com.sqlite.pojo.BasePO;
import com.sqlite.pojo.Canmel;
import com.sqlite.pojo.DeviceStatus;
import com.sqlite.pojo.OutPoint;
import com.sqlite.pojo.VideoDevCH;
import com.sqlite.pojo.VideoDevice;


public class VideoDeviceImpl extends BaseDaoImpl implements VideoDeviceDao {
 
	@Override
	public List<VideoDevice> getAllVideoDev() {
		List<VideoDevice> list = new ArrayList<VideoDevice>();
		List<DeviceStatus> listD = DAOFactory.getPojoImpl().getAllDeivceStatus(-1);
		for(int i =0;i<listD.size();i++){
			VideoDevice v = new VideoDevice();
			v.setDeviceStatus(listD.get(i));
			//获取该设备对应的所有通道信息
			//填充摄像头信息到通道集合中
			List<BasePO> lc = DAOFactory.getSheXiangIntance().getCanmel(listD.get(i).getDevcieId());
			if(lc.size()>0){
				for(int j = 0 ;j<lc.size();j++){
					Canmel c = (Canmel)lc.get(j);
					VideoDevCH vd = new VideoDevCH();
					vd.setId(c.getCid());
					vd.setDeviceId(c.getDeviceId());
					vd.setChName(c.getDeviceName());
					vd.setChType(3);
					v.getChannels().add(vd);
				}
			}
			
			//填充报警点信息到通道集合中
			lc = DAOFactory.getBaoJingIntance().getAlrmPoint(listD.get(i).getDevcieId());
			if(lc.size()>0){
				for(int j = 0 ;j<lc.size();j++){
					AlrmPoint c = (AlrmPoint)lc.get(j);
					VideoDevCH vd = new VideoDevCH();
					vd.setDeviceId(c.getDeviceId());
					vd.setId(c.getAid());
					vd.setChName(c.getDeviceName());
					vd.setChType(4);
					v.getChannels().add(vd);
				}
			}
			
			//填充输出点信息到通道集合中
			lc = DAOFactory.getShuChuIntance().getOutPoint(listD.get(i).getDevcieId());
			if(lc.size()>0){
				for(int j = 0 ;j<lc.size();j++){
					OutPoint c = (OutPoint)lc.get(j);
					VideoDevCH vd = new VideoDevCH();
					vd.setId(c.getOid());
					vd.setDeviceId(c.getDeviceId());
					vd.setChName(c.getDeviceName());
					vd.setChType(5);
					v.getChannels().add(vd);
				}
			}
			//一个完整的对象获得成功,然后添加到集合中
			list.add(v);
		}
		
		return list;
	}
	
	public static void main(String []args){
		List<VideoDevice> list = new VideoDeviceImpl().getAllVideoDev();
		System.out.println("集合大小:"+list.size());
	}

}
