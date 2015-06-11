package com.fxstore.ctrl;

public interface IStoreNotify {
    void OnErrorMessage(String szName,String szError,Object lpContext);
    
    void OnSearchResult(int nRecordId,int nSearchAction,int nRealDeviceChannel,	String pData,int nBufSize,
    		String szRealDeviceIds,String sFile,String sStartTime,String sEndTime,Object context,Object objContext );
}
	 