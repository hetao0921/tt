package com.sqlite.dao;

import java.util.List;

import com.sqlite.pojo.*;
public interface VideoServerDao { 

	/**
	 * ��ݿͻ���ID��ѯ���е��豸ת����Ϣ
	 * @param clientId
	 * @return
	 */
	public List<VideoServer> getVideoServersByClientId(String clientId);
	
	/**
	 * ����豸ID��ѯ���е��豸ת����Ϣ
	 * @param deviceId
	 * @return
	 */
	public List<VideoServer> getVideoServersByDeviceId(String deviceId);
	
	public List<VideoServer> getAllVideoServers();
	
	/**
	 * ���ת��ID����ѯ��Ӧ���豸ת����Ϣ
	 * @return
	 */
	public List<VideoServer> getVideoServersByVSId(String vsId);
	List<VideoServer> getVideoServerByLev(int lev);
	/**
	 * ɾ���豸ת����Ϣ
	 * @param videoServer
	 * @return
	 */
	public boolean delVideoServer(VideoServer video);

	/**
	 * ���һ���豸ת����Ϣ
	 * @param video
	 * @return
	 */
	public boolean insertVideoServer(VideoServer video);
	
	
	public List<VideoServer> getVideoServersByClientId(String clientId,String deviceId,int devChId);
}
