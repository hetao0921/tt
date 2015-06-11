package app.singlepad.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.misc.log.LogUtil;

import app.bean.VideoBean;
import app.singlepad.AbstractSinglePad;
import app.singlepad.AbstractSinglePadBean;
import app.singlepad.SinglePadFactory;
import app.singlepad.SinglePadState;
import app.tcp.SinglePadTCP;

import com.alibaba.fastjson.JSONObject;
import com.mysql.impl.Hibernate;
import com.mysql.jdbc.StringUtils;

/**
 * 默认类型的单兵
 * @author hxht
 * @version 2014-9-17
 */
public class DefaultSinglePadImpl extends AbstractSinglePad{
	
	public static final String TYPE = SinglePadFactory.DEFAULT_TYPE;
	
	static{
		//类型注册
		SinglePadFactory.register(TYPE, DefaultSinglePadImpl.class);
	}
	
	//已点播的视频
	private VideoBean video_played;
	//播放中的视频
	private VideoBean video_playing;
	
	@Override
	public void playVideo(String deviceId, int index, String uuid) {
		switch(state){
			case offline :
				break;
			case unplayed :
				channel.PlayVideo(deviceId, index, uuid);
				video_played = new VideoBean(deviceId, index, uuid);
				setState(SinglePadState.played);
				break;
			case played :
				break;
			case playing :
				//先停止正在播放的图像
				channel.StopVideo(video_playing.getDevicerId(),
						video_playing.getIndex(), video_playing.getUuid());
				video_playing = null;
				
				channel.PlayVideo(deviceId, index, uuid);
				video_played = new VideoBean(deviceId, index, uuid);
				setState(SinglePadState.played);
				break;
		}
	}
	
	@Override
	public void sendVideo(String deviceId, Integer index, String videoServerIP) {
		switch(state){
			case offline :
				break;
			case unplayed :
				break;
			case played :
				if(deviceId.equals(video_played.getDevicerId())
						&& index == video_played.getIndex()){
					String cmd = "net_recv1.url="+videoServerIP+"\n";
					LogUtil.info("单兵系统：向单兵（"+bean.getSinglePadIP()+"）发送“流媒体接收”指令（"+cmd+"）");
					//向单兵发送“流媒体接收”指令
					boolean sendFlag = channel.sendTcpCommand(SinglePadTCP.SET, cmd);
					if(sendFlag){//指令发送成功
						LogUtil.info("单兵（"+bean.getSinglePadIP()+"）接收成功！");
						video_playing = new VideoBean(
								deviceId, index, video_played.getUuid());
						video_played = null;
						setState(SinglePadState.playing);
					}else{//指令发送失败
						LogUtil.info("单兵（"+bean.getSinglePadIP()+"）接收失败！");
						setState(SinglePadState.unplayed);
					}
				}
				break;
			case playing :
				break;
		}
	}
	
	@Override
	public void offlineEvent() {
		if(video_playing != null){
			channel.StopVideo(video_playing.getDevicerId(),
					video_playing.getIndex(), video_playing.getUuid());
			video_playing = null;
		}
		video_played = null;
		setState(SinglePadState.offline);
	}

	@Override
	public void onlineEvent() {
		if(state == SinglePadState.offline){
			setState(SinglePadState.unplayed);
		}
	}
	
	public List<? extends AbstractSinglePadBean> getSinglePads(){
		String sql = "select new Map(sp.singlePadId as SinglePadID,sp.singlePadName as SinglePadName,sp.singlePadIp as SinglePadIP, "
				+ "sp.rtsp as RTSP,sp.centerId as CenterID ,sp.departId as DepartID ,sp.singlePadDesc as SinglePadDesc ,sp.singlePadMask as SinglePadMask ,"
				+ "sp.singlePadGate as SinglePadGate ,sp.singlePadMac as SinglePadMac ,sp.singlePadType as SinglePadType ,"
				+ "sp.singlePadPort as SinglePadPort,sp.netExport as NetExport ,sp.isDisable as IsDisable ) from NvmpSinglepadtab sp,NvmpCenterinfotab ci where sp.centerId=ci.centerId and sp.singlePadType is null or sp.singlePadType='' or sp.singlePadType='"+TYPE+"'";
		List<HashMap<String,String>> data = Hibernate.getHibernate().createQuery(sql);
		List<DefaultSinglePadBeanImpl> singlePads = new ArrayList<DefaultSinglePadBeanImpl>();
		for(HashMap<String,String> row : data){
			DefaultSinglePadBeanImpl singlePad = new DefaultSinglePadBeanImpl();
			singlePad.setSinglePadID(row.get("SinglePadID"));
			singlePad.setSinglePadName(row.get("SinglePadName"));
			singlePad.setSinglePadIP(row.get("SinglePadIP"));
			singlePad.setSinglePadRtsp(row.get("RTSP"));
			singlePad.setCenterID(row.get("CenterID"));
			singlePad.setDepartID(row.get("DepartID"));
			singlePad.setSinglePadDesc(row.get("SinglePadDesc"));
			singlePad.setSinglePadMask(row.get("SinglePadMask"));
			singlePad.setSinglePadGate(row.get("SinglePadGate"));
			singlePad.setSinglePadMac(row.get("SinglePadMac"));
			singlePad.setSinglePadType(row.get("SinglePadType"));
			
			if(StringUtils.isNullOrEmpty(row.get("SinglePadPort")))
				singlePad.setSinglePadPort(null);
			else
				singlePad.setSinglePadPort(Integer.parseInt(row.get("SinglePadPort")));
			if(StringUtils.isNullOrEmpty(row.get("NetExport")))
				singlePad.setNetExport(null);
			else
				singlePad.setNetExport(Integer.parseInt(row.get("NetExport")));
			if(StringUtils.isNullOrEmpty(row.get("IsDisable")))
				singlePad.setIsDisable(null);
			else
				singlePad.setIsDisable(Integer.parseInt(row.get("IsDisable")));
			
			if(singlePad.getSinglePadID() != null
					&& singlePad.getSinglePadIP() != null
					&& singlePad.getSinglePadName() != null
					&& singlePad.getSinglePadPort() != null
					&& singlePad.getSinglePadRtsp() != null){
				singlePads.add(singlePad);
			}else{
				String json = JSONObject.toJSONString(singlePad);
				LogUtil.error("单兵系统：丢弃不合格的单兵数据（"+json+"）！");
			}
		}
		return singlePads;
	}

	@Override
	public void destroy() {
		if(video_playing != null){
			channel.StopVideo(video_playing.getDevicerId(),
					video_playing.getIndex(), video_playing.getUuid());
		}
	}

}
