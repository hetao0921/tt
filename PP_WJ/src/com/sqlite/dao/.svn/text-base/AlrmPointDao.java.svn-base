package com.sqlite.dao;

import java.util.List;

import com.sqlite.pojo.AlrmPoint;
import com.sqlite.pojo.BasePO;

public interface AlrmPointDao {
	/**
	 * ��ѯ�������״̬
	 * @param DID
	 * @param TID
	 * @return
	 */
	int getAlrmPointStatus(String DID,int TID); 
	
	/**
	 * ����豸ID��ѯһ�����������
	 * @param DID
	 * @param TID
	 * @return
	 */
	List<BasePO> getAlrmPoint(String devID);
	
	/**
	 * 根据主键ID，查询对应的报警点对象
	 * @param DID
	 * @param TID
	 * @return
	 */
	AlrmPoint getAlrmPoint(int devID);
	
	/**
	 * ���±������״̬
	 * @param DID
	 * @param TID
	 * @param status
	 * @return
	 */
	boolean updateAlrmPoint(AlrmPoint a);
	
	AlrmPoint getAlarmPoint(String devId,int channelId);
}
