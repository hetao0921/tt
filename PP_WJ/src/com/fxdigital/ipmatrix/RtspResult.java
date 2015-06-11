package com.fxdigital.ipmatrix;

public class RtspResult {
	private String name;
	private String template;
	private boolean enable;
	private String desc;
	private String port;
	public RtspResult(boolean b) {
		enable = b;
	}
	public RtspResult(String _name,String _template,boolean _enable,String _desc,String _port){
		name = _name;
		template = _template;
		enable = _enable;
		desc = _desc;
		port = _port;
	}
	
	public String getName() {
		return name;
	}
	public String getTemplate() {
		return template;
	}
	public boolean isEnable() {
		return enable;
	}
	public String getDesc() {
		return desc;
	}
	public String getPort() {
		return port;
	}
	
	
	
}
