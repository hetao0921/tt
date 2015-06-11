package com.fxdigital.video.ctrl;

/**
 * 用来进行回调的对象。
 *  
 * */
public interface IViDeoModuleNotify {
   
	/**
	 *异常信息回调
	 * */
	void FnErrorMessage(String szName, String szError, Object lpContext);

	/**
	 * 报警通知。
	 * */
	void FnAlarmCallBack(int hSession, int nAlarmType, int nValue, Object lpContext);
	
	/**
	 * 登陆返回信息。
	 * */
	void FnConnectCallBack(int hSession, boolean bConnected, Object lpContext);
	
}
