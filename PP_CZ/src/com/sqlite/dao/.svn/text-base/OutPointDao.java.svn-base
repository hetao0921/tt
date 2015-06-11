package com.sqlite.dao;

import java.util.List;

import com.sqlite.pojo.BasePO;
import com.sqlite.pojo.OutPoint;

public interface OutPointDao { 
	/**
	 * ��ѯ������״̬
	 * @param DID
	 * @param TID
	 * @return
	 */
	int getOutPointStatus(String DID,int TID);
	
	/**
	 *根据设备ID，查询所有的输出点信息
	 * @param DID
	 * @param TID
	 * @return
	 */
	List<BasePO> getOutPoint(String DID);
	
	/**
	 * 查询所有的输出点信息
	 * @param DID
	 * @param TID
	 * @return
	 */
	List<BasePO> getOutPoint();
	
	/**
	 * 根据主键ID，查询出一个对应的输出点对象
	 * @param DID
	 * @param TID
	 * @return
	 */
	OutPoint getOutPoint(int id);
	
	/**
	 * ����������״̬
	 * @param DID
	 * @param TID
	 * @param status
	 * @return
	 */
	boolean updateOutPoint(OutPoint a);
}
