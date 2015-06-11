package fxdigital.rpc;


/**
 * 发送模式和内容类型
 * @author fucz
 * @version 2014-7-8
 */
public class ModeAndType {
	
	private String mode;
	private String type;
	
	public ModeAndType(String mode,String type){
		this.mode = mode;
		this.type = type;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
