package com.fxdigital.fxnvr;

public class FxnvrCtrl {
	static {
		System.loadLibrary("rtsprecord");
	}

	// 初始化环境
	// 返回值: true表示成功，false表示失败
	public native boolean Init();

	// 清理环境
	// 返回值: true表示成功，false表示失败
	public native boolean Cleanup();

	// 开始录像
	// rtspURL: rtsp流地址
	// fileName: 保存成录像文件的绝对路径名
	// 返回值: 若成功，返回一个非负数，表示本次录像的句柄，若失败则返回-1
	public native int StartRecord(String rtspURL, String fileName);

	// 停止录像
	// handle: StartRecord返回的录像句柄
	// 返回值: true表示成功，false表示失败
	public native boolean StopRecord(int handle);

	// 获取录像文件时长
	// fileName: 录像文件名
	// 返回值: 返回一个正数，表示录像文件时长，用秒数表示，返回0表示失败
	public native int GetFileDuration(String fileName);

	// 修复损坏的录像文件
	// fileName: 录像文件名
	// 返回值: true表示成功，false表示失败
	public native boolean RepairFile(String fileName);
}
