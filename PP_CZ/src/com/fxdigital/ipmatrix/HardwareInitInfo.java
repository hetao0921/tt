/**
 * 设备初始化信息的对象（用于显示解码相关信息）
 * gjj
 * 2012.10.17
 */
package com.fxdigital.ipmatrix;

public class HardwareInitInfo { 
	public int _succeedDecodeChannelCount;//初始化成功的解码通道个数
	public int _succeedInitDspCount;//初始化成功的DSP个数
	public int _succeedDisplayChannelCount;//初始化成功的显示通道个数
	public int _decodeCardCount;//解码卡个数
	public int get_succeedDecodeChannelCount() {
		return _succeedDecodeChannelCount;
	}
	public void set_succeedDecodeChannelCount(int _succeedDecodeChannelCount) {
		this._succeedDecodeChannelCount = _succeedDecodeChannelCount;
	}
	public int get_succeedInitDspCount() {
		return _succeedInitDspCount;
	}
	public void set_succeedInitDspCount(int _succeedInitDspCount) {
		this._succeedInitDspCount = _succeedInitDspCount;
	}
	public int get_succeedDisplayChannelCount() {
		return _succeedDisplayChannelCount;
	}
	public void set_succeedDisplayChannelCount(int _succeedDisplayChannelCount) {
		this._succeedDisplayChannelCount = _succeedDisplayChannelCount;
	}
	public int get_decodeCardCount() {
		return _decodeCardCount;
	}
	public void set_decodeCardCount(int _decodeCardCount) {
		this._decodeCardCount = _decodeCardCount;
	}
	
	
}
