package com.sqlite.factory;

import com.sqlite.dao.AlrmPointDao;
import com.sqlite.dao.CommandDeviceDao;
import com.sqlite.dao.DeviceStatusDao;
import com.sqlite.dao.CanmelDao;
import com.sqlite.dao.OutPointDao;
import com.sqlite.dao.VideoServerDao;
import com.sqlite.impl.AlrmPointImpl;
import com.sqlite.impl.CommandDeviceImpl;
import com.sqlite.impl.DeviceStatusImpl;
import com.sqlite.impl.CanmelImpl;
import com.sqlite.impl.OutPointImpl;
import com.sqlite.impl.PojoImpl;
import com.sqlite.impl.VideoServerImpl;

public class DAOFactory {
 
	/**
	 * ��ȡ�豸״̬�ӿ�
	 * @return
	 */
	public static DeviceStatusDao getDeviceStatusIntance(){
		return new DeviceStatusImpl();
	}
	
	/**
	 * ��ȡ�õ�״̬��ͨ���ŵ�ʵ����
	 * @return
	 */
	public static PojoImpl getPojoImpl(){
		return new PojoImpl();
	}
	
	/**
	 * ��ȡ����ͷ�ӿ�
	 * @return
	 */
	public static CanmelDao getSheXiangIntance(){
		return new CanmelImpl();
	}
	
	/**
	 * ��ȡ�����ӿ�
	 * @return
	 */
	public static OutPointDao getShuChuIntance(){
		return new OutPointImpl();
	}
	
	/**
	 * ��ȡ������ӿ�
	 * @return
	 */
	public static AlrmPointDao getBaoJingIntance(){
		return new AlrmPointImpl();
	}
	
	/**
	 * ����豸ת����Ϣ
	 * @return
	 */
	public static VideoServerDao getVideoServerIntance(){
		return VideoServerImpl.getVideoServerInstance();
	}
	
	
	public static CommandDeviceDao getCommandDeviceIntance(){
		return new CommandDeviceImpl();
	}
}
