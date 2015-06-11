package com.sqlite.dao;

import java.util.List;

import com.sqlite.pojo.BasePO;
import com.sqlite.pojo.Canmel;

public interface CanmelDao {
	/**
	 * ��ѯ����ͷ��״̬
	 * @param DID
	 * @param TID
	 * @return
	 */
	int getCanmelStatus(String DID,int TID,int typeid);
	 
	/**
	 * ����豸ID��ѯһ������ͷ����
	 * @param DID
	 * @param TID
	 * @return
	 */
	List<BasePO> getCanmel(String DID);
	
	Canmel getCanmel(String devId,Integer channelId);
	
	/**
	 * ��������ͷ��״̬
	 * @param DID
	 * @param TID
	 * @param status
	 * @return
	 */
	boolean updateCanmel(Canmel c);
	
	/**
	 * 根据一个ID查询一个摄像头对象
	 * @param id
	 * @return
	 */
	Canmel getCanmelById(int id);
	
	/**
	 * 获取所有摄像头信息
	 * @return
	 */
	List<Canmel> getAllCanmels();
}
