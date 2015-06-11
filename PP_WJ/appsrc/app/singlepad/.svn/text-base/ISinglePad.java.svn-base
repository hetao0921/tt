package app.singlepad;

import java.util.List;

import app.ConnectChannel;


/**
 * 
 * @author hxht
 * @version 2014-9-17
 */
public interface ISinglePad {
	
	/**
	 * 点播图像
	 * @param deviceId
	 * @param index
	 * @param uuid
	 */
	public void playVideo(String deviceId, int index, String uuid);
	
	/**
	 * 获得单兵实例
	 * @return
	 */
	public ISinglePadBean getBean();
	
	/**
	 * 设置单兵实例
	 * @param bean
	 */
	public void setBean(ISinglePadBean bean);
	
	/**
	 * 获得一种类型的所有单兵实例
	 * @return
	 */
	public List<? extends AbstractSinglePadBean> getSinglePads();
	
	/**
	 * 获得单兵当前状态
	 * @return
	 */
	public SinglePadState getState();
	
	/**
	 * 设置单兵当前状态
	 * @param state
	 */
	public void setState(SinglePadState state);
	
	/**
	 * 离线通知
	 */
	public void offlineEvent();
	
	/**
	 * 上线通知
	 */
	public void onlineEvent();
	
	/**
	 * 给单兵发送图像
	 * @param deviceId
	 * @param index
	 * @param videoServerIP
	 */
	public void sendVideo(String deviceId, Integer index, String videoServerIP);
	
	/**
	 * 销毁单兵实例（停止播放中的视频）
	 */
	public void destroy();
	
	/**
	 * 设置连接通道
	 * @param cc
	 */
	public void setConnectChannel(ConnectChannel cc);
	
}
