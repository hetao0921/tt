package com.sqlite.dao;

import java.util.List;

import com.sqlite.pojo.AlarmRecordPlan;

public interface PlanDao {

	/**
	 * 根据设备ID和通道ID查询对应的SessionId
	 * @param devId
	 * @param channelId
	 * @return
	 */
	String getSessionId(String devId,int channelId); 
	
	/**
	 * 修改SessionId
	 * @param deviceId
	 * @param channelId
	 * @param sessionId
	 */
	void updateSessionId(String deviceId,int channelId,String sessionId);
	
	/**
	 * 获取所有的设备计划信息
	 * @return
	 */
	List<AlarmRecordPlan> getAlarmRecordPlan();
	
	/**
	 * 根据ID，查询设备计划信息
	 * @param id
	 * @return
	 */
	AlarmRecordPlan getAlarmRecordPlanById(Integer id);
	
	/**
	 * 添加一个设备计划信息
	 * @param id
	 * @param sessionId
	 */
	void addAlarmRecordPlan(Integer id,String sessionId);
}
