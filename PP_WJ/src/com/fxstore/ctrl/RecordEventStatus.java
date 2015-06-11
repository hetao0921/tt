package com.fxstore.ctrl;

//录像事件状态
enum RecordEventStatus
{
	eRecordStatus_Success ,			// 录像成功
	eRecordStatus_SwapRecordFile,		// 更换录像文件
	eRecordStatus_CreateFileFail,		// 创建录像文件失败
	eRecordStatus_RequestStreamFail,	// 请求数据流失败
	eRecordStatus_DiskFullDeleteFile,	// 磁盘满开始删除老录像文件
};
 
