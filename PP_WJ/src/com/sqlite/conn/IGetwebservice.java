package com.sqlite.conn;


public interface IGetwebservice {
	/**
	 * ��ȡ�����豸��Ϣ
	 * @return
	 */
	public String[][] GetAllDeviceInfo(); 
	
	/**
	 * ����豸��ź����Ͳ�ѯ��Ӧ��һ��ͨ����Ϣ
	 * @param DeviceID
	 * @param DH
	 * @return
	 */
	public String[][] GetDeviceInfo(String DeviceID,String DH); 
	
	/**
	 * ���ͨ�����Ͳ�ѯ��Ӧ��ͨ����Ϣ
	 * @param DH
	 * @return
	 */
	public String[][] GetDeviceInfo(String DH); 
	
	public String[][] GetTimePlan(String Device, int DevCH);
	
	public String[][] GetTimePlan(String SessionId);
	
	public String[][] GetTimePlanList(int ID,String SessionID);
	
	public String[][] GetTimePlanAll();
	
	public String [][] getCenterNetworkInfo(String ip);
	
	
	
	// 获取中心路由信息
	public String[][] GetCenterNetWorkInfoTab();

	public String[][] GetCenterRouteInfoTab();

	public String[][] GetCenterInfoTab();
	
	
	 public String[][] Getcommanddevtab(); 
	 
	 public String[][] getCenterinfosynctab();
}
