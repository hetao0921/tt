package app.bean;

/**
 * 
 * @author hxht
 * @version 2014-9-16
 */
public class VideoBean {
	
	private String devicerId;
	private int index;
	private String uuid;
	
	public VideoBean(String devicerId,int index,String uuid){
		this.devicerId = devicerId;
		this.index = index;
		this.uuid = uuid;
	}
	
	public String getDevicerId() {
		return devicerId;
	}
	public void setDevicerId(String devicerId) {
		this.devicerId = devicerId;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
}
