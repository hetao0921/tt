package com.fxdigital.onvif.ctrl;

public class SerialPortParam { 
	public int nAddress;
	public int nBaudRate;
	public int nDataBit;
	public int nStopBit;
	public int nParity;
	public int nPTZProtocol;

	public int getnAddress() {
		return nAddress;
	}

	public void setnAddress(int nAddress) {
		this.nAddress = nAddress;
	}

	public int getnBaudRate() {
		return nBaudRate;
	}

	public void setnBaudRate(int nBaudRate) {
		this.nBaudRate = nBaudRate;
	}

	public int getnDataBit() {
		return nDataBit;
	}

	public void setnDataBit(int nDataBit) {
		this.nDataBit = nDataBit;
	}

	public int getnStopBit() {
		return nStopBit;
	}

	public void setnStopBit(int nStopBit) {
		this.nStopBit = nStopBit;
	}

	public int getnParity() {
		return nParity;
	}

	public void setnParity(int nParity) {
		this.nParity = nParity;
	}

	public int getnPTZProtocol() {
		return nPTZProtocol;
	}

	public void setnPTZProtocol(int nPTZProtocol) {
		this.nPTZProtocol = nPTZProtocol;
	}

}
