package com.fxdigital.onvif.ctrl;

public class NetInfo {

	private String ip;
	private String subMask;
	private String gateWay;
	private String dns;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSubMask() {
		return subMask;
	}

	public void setSubMask(String subMask) {
		this.subMask = subMask;
	}

	public String getGateWay() {
		return gateWay;
	}

	public void setGateWay(String gateWay) {
		this.gateWay = gateWay;
	}

	public String getDns() {
		return dns;
	}

	public void setDns(String dns) {
		this.dns = dns;
	}

}
