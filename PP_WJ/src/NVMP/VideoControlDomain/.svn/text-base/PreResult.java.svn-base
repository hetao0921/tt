package NVMP.VideoControlDomain;

/**
 * 前置点播的结果
 * */
public class PreResult {

	private String prefowardid; // 前置接入服务器ID
	private String fowardid; // 转发服务器ID
	
	private int nums; // 发送给转发服务器的次数
	
	private String uuid; // 第一次点播上下文环境

	private String prefowardIP;
	private int prefowardPort;
	private int prefowardChannel; 

	private boolean flag; // 标示状态,记录前置操作时候结束

	public void addPlay() {
		++nums;
	}

	/**
	 * false 不可关，ture 可关闭。
	 * */
	public boolean minusPlay() {
		--nums;
		return nums<=0;
	}
	
	
	// 进行点播判断，当值为0的时候，判定可以关闭。
	public boolean isClose() {
		return nums <= 0;
	}

	public PreResult(String prefowardid,String uuid) {
		this.prefowardid = prefowardid;
		this.uuid = uuid;
		flag = false;
		nums = 0;
	}

	/**
	 * 设置当前状态
	 */
	public void changeFlag() {
		flag = !flag;
	}
    /**
     * 获取当前状态，ture，表明前置操作已经结束 
     * */
	public boolean isFinish() {
		return flag;
	}

	/**
	 * 填充前置操作结束后，发送给转发服务器的相关数据
	 * */
	public void setvalue(String fowardid,String prefowardIP,int prefowardPort,int prefowardChannel) {
		changeFlag();
		this.fowardid = fowardid;
		this.prefowardid = prefowardIP;
		this.prefowardPort = prefowardPort;
		this.prefowardChannel = prefowardChannel;
	}

	public String getFowardid() {
		return fowardid;
	}

	public String getPrefowardIP() {
		return prefowardIP;
	}

	public int getPrefowardPort() {
		return prefowardPort;
	}

	public int getPrefowardChannel() {
		return prefowardChannel;
	}

	public String getPrefowardid() {
		return prefowardid;
	}
	
	
	
	
	
	
}
