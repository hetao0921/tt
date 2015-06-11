package com.sqlite.dao;

import java.util.List;
import java.util.Map;

import com.sqlite.pojo.DeviceStatus;

public interface DeviceStatusDao {

	/**
	 * ��ѯ��������������ķ���
	 * @return
	 */
	String getCenterID(String DID); 
	
	/**
	 * ��ѯ�������߻����ߵ��豸��Ϣ
	 * @param status
	 * @return
	 */
	List<DeviceStatus> getAllDeviceStatus(int status);
	
	/**
	 * ����豸ID����ѯһ���豸��Ϣ
	 * @param DID
	 * @return
	 */
	DeviceStatus getDeviceStatus(String DID);
	
	DeviceStatus getDeviceStatus(String DID,String centerId);
	
	/**
	 * �����豸״̬
	 * @param DID
	 * @param satus
	 * @return
	 */
	boolean updateDeviceStatus(String DID,int satus);
	
	/**
	 * �����豸���������
	 * @param DID
	 * @param server
	 * @return
	 */
	boolean updateDeviceServer(DeviceStatus d);
	
	/**
	 * ��ݽ���������������ѯ�����豸��Ϣ
	 * @return
	 */
	List<Map<String,String>> getDeviceBySwitchSvrID(String switchSvrId);
	
	List<DeviceStatus> getDeviceStatusByCenterId(String centerId);
	
	boolean delDeviceStatusByDevId(String devId);
	
	String[] getDevIpByDevIdName(String devId);
}
