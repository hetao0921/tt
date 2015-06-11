package NVMP.SyncDomain;

import NVMP.AppService.Remoting;

@Remoting
public class ISyncEvent {

	
	/** 
	 * 查询状态。根据type来确定是查询何种状态。
	 * */
	public void onGetStateEvent(String type, Integer flag, String userid,
			String ip) {
	}


    /**
     * 查询本机的上传版本情况。
     * */
	public void onGetSelfDataEvent(Integer version, String uploadDate, Integer flag) {
	}

    /**
     * 由于是多个结果，我们用{centerid:version:uploadDate:flag}方式存储。
     * */
	public void onGetNativeDataEvent(String versions) {	
	}


	/**
	 * 服务器最新的数据版本
	 * */
	public void onGetServerDataEvent(String versions) {
	}

    /**
     * 告诉客户端操作失败，和原因。
     * */
	public void onFailEvent(String operate, String reason) {
	}

}
