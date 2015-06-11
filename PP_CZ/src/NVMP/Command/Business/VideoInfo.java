package NVMP.Command.Business;

public class VideoInfo {
	public int DisplayWinIndex; // 视频显示索引
	public String DeviceId; // 希望点播的输入视频源Ids
	public String DeviceType; // 设备类型
	public String DeviceSubType; // 设备子类型
	public int CameraIndex; // 希望点播的输入视频源的摄像机序号
	public String SourceIP; // 实际点播的视频源IP
	public int NetPort; // 网络连接端口
	public NetLinkType LinkType = NetLinkType.forValue(0); // 网络传输类型
	public int ServerVideoCH; // 输出的视频服务通道编号
	public int ServerAudioCH; // 输出的音频服务通道编号 {在指挥席位中作为视频设备类型}
	public boolean OnLine; // 点播在线(图像是否在显示)

	public int InRecorder; // 是否录像
	public boolean IsPlayAudio; // 是否播放声音
	public boolean IsPause; // 是否暂停录像
	public boolean IsLoacl; // 是否在本地显示视频
	public boolean IsSingleAudio; // 是否播放单独的声音
	public boolean IsCommandDevice; // 是否指挥终端设备

	private int _Counter; // 点播记数

	public final int getCounter() {
		return _Counter;
	}

	public final void setCounter(int value) {
		_Counter = value;
	}
}