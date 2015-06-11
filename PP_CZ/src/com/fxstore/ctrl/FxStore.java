package com.fxstore.ctrl;

import java.util.LinkedList;
import java.util.List;

public class FxStore {
	private static FxStore _this;
	private static final String ClibName = "FxStore"; 

	private FxStore() {
		System.loadLibrary(ClibName);
		this.StoreStartup(1200, isn, "哈哈哈");
	}

	synchronized public static FxStore Instance() {
		if (_this == null) {
			_this = new FxStore();
		}
		return _this;
	}

	// 设置回调函数
	private static IStoreNotify isn;

	public static void setIsn(IStoreNotify isn) {
		FxStore.isn = isn;
	}

	static public void OnSearchResult(int nRecordId, int nSearchAction,
			int nRealDeviceChannel, String pData, int nBufSize,
			String szRealDeviceIds, String sFile, String sStartTime,
			String sEndTime, Object context, Object objContext) {
		System.out.println(sFile);
		System.out.println(szRealDeviceIds);
		if (isn != null) {
			System.out.println(context);
			isn.OnSearchResult(nRecordId, nSearchAction, nRealDeviceChannel,
					pData, nBufSize, szRealDeviceIds, sFile, sStartTime,
					sEndTime, context, objContext);
		}
	}

	static public void OnErrorMessage(String szName, String szError,
			Object lpContext) {
		// TODO Auto-generated method stub
		System.out.println("异常啊" + szName + szError);
	}

	/******
	 * 初始化 参数一：fnStatus 输入参数，录像状态(详见eRecordEventStatus) 参数二：lpContext
	 * 输入参数，用户数据，上下文 返回值：true 成功；false 失败；
	 * 
	 * nScanDiskSecond 间隔时间
	 **/
	public native boolean StoreStartup(int nScanDiskSecond, IStoreNotify is,
			Object o);

	/******
	 * 结束的时候，进行 反初始化 返回值：true 成功；false 失败；
	 **/
	public native boolean StoreCleanup();

	/******
	 * 开始录像 参数一：szAddress 输入参数，设备地址 参数二：nPort 输入参数，设备端口 参数三：szName 输入参数，登录用户
	 * 参数四：szPass 输入参数，登录密码 参数五：nLinkType 输入参数，连接类型 参数六：nSubLinkType 输入参数，连接子类型
	 * 参数七：nLinkMode 输入参数，连接模式 参数八：nBitRate 输入参数，码率（Bit/s） 参数九：nRecordMinute
	 * 输入参数，录像时间（分钟） -1 返回值：true 成功；false 失败；
	 **/
	public native int StartRecord(String szAddress, int nPort, String szName,
			String szPass, String szMark, int nLinkType, int nSubLinkType,
			int nLinkMode, int nChannel, int nBitRate, int nRecordSecond);

	/***
	 * 补入相关的录像信息。
	 * 
	 * **/
	public native boolean SetRecAttrib(int nRecordId, String szAttribName,
			String szAttribValue);

	/******
	 * 录像打标 参数一：nRecordId 输入参数，录像句柄 参数二：pMarkData 输入参数，标记数据 参数三：nMarkSize
	 * 输入参数，标记长度 返回值：true 成功；false 失败；
	 **/

	public native boolean MarkRecord(int nRecordId, String markData,
			int dataLength);

	/******
	 * 停止录像 参数一：nRecordId 输入参数，录像句柄 返回值：true 成功；false 失败；
	 **/
	public native boolean StopRecord(int nRecordId);

	/******
	 * 按时间检索录像文件 参数一：szAddress 输入参数，设备地址 参数二：nPort 输入参数，设备端口 参数三：szMark
	 * 输入参数，录像标记 参数四：szStartTime 输入参数，录像开始时间 参数四：szEndTime 输入参数，录像结束时间 返回值：true
	 * 成功; false 失败
	 **/
	public native long Search(String szCondition, String szStartTime,
			String szEndTime, Object sr);

	// 0表示失败
	public native long GetTableFirstRow(long hTable);

	public native long GetTableNextRow(long hTable);

	public native int GetRecordSetInteger(long hRecord, String szName);

	public native String GetRecordSetString(long hRecord, String szName);

	public native boolean SearchClear(long hTable);

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		StoreCleanup();
	}

	public List<FxResult> SearchNative(String szCondition, String szStartTime,
			String szEndTime, Object sr) {

		// 获得查询后的结果句柄
		long result = Search(szCondition, szStartTime, szEndTime, sr);
		if (result == 0)
			return null;
		List<FxResult> list = null;
		try {
			list = new LinkedList<FxResult>();
			long row;
			// 获取第一个
			row = GetTableFirstRow(result);

			if (row == 0)
				throw new Exception();

			FxResult fr = new FxResult();
			fr.setnRecordId(GetRecordSetInteger(row, "RID"));
			fr.setnChannel(GetRecordSetInteger(row, "Channel"));
			fr.setMemo(GetRecordSetString(row, "Memo"));
			fr.setRealDeviceIds(GetRecordSetString(row, "RealDeviceIds"));
			fr.setFilePath(GetRecordSetString(row, "FilePath"));
			fr.setStartTime(GetRecordSetString(row, "StartTime"));
			fr.setEndTime(GetRecordSetString(row, "EndTime"));
			list.add(fr);

			while ((row = GetTableNextRow(result)) != 0) {

				fr = new FxResult();
				fr.setnRecordId(GetRecordSetInteger(row, "RID"));
				fr.setnChannel(GetRecordSetInteger(row, "Channel"));
				fr.setMemo(GetRecordSetString(row, "Memo"));
				fr.setRealDeviceIds(GetRecordSetString(row, "RealDeviceIds"));
				fr.setFilePath(GetRecordSetString(row, "FilePath"));
				fr.setStartTime(GetRecordSetString(row, "StartTime"));
				fr.setEndTime(GetRecordSetString(row, "EndTime"));
				list.add(fr);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			list = null;
		} finally {
			SearchClear(result);
		}
		return list;
	}

	public static void main(String[] arg) throws InterruptedException {

		FxStore fs = FxStore.Instance();

		List<FxResult> l =  fs.SearchNative(" and 1=1", "", "", "ok");
		System.out.println(l.size());
		for(FxResult a : l) {
			System.out.println(a.getnRecordId());
			System.out.println(a.getFilePath());
			System.out.println(a.getRealDeviceIds());
		}
		
		
//		Thread.sleep(10000);
		// int a =fs.StartRecord("192.168.1.195", 8000, "admin", "12345",
		// "asdasd", 100, 0, 0, 1,200, 50);
		// System.out.println(a);
	}

}
