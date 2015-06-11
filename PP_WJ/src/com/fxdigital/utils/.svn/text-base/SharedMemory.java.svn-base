package com.fxdigital.utils;


public class SharedMemory
{
	public static final int SEEK_SET = 0; 
	public static final int SEEK_CUR = 1;
	public static final int SEEK_END = 2;
	
	/* jni 本地方法 */
	private static native long    _create(String name, int size);
	private static native long    _open(String name);
	private static native int     _write(long handle, byte[] data);
	private static native int     _write2(long handle, byte[] data, int pos, int len);
	private static native int     _read(long handle, byte[] data);
	private static native boolean _lock(long handle);
	private static native void    _unlock(long handle);
	private static native int     _getReadPos(long handle);
	private static native int     _getWritePos(long handle);
	private static native int     _getSize(long handle);
	private static native int     _readSeek(long handle, int offset, int whence);
	private static native int     _writeSeek(long handle, int offset, int whence);

	/* 本地代码使用的共享内存句柄 */
	private long _handle;


	static {
		System.loadLibrary("sharedmem");
	}

	public SharedMemory()
	{
		_handle = 0;
	}

	/*++
		创建共享内存, 如果共享内存已经存在，直接返回
		name 名称
        size 大小
	--*/
	public boolean create(String name, int size)
	{
		_handle = _create(name, size);
		return _handle != 0;
	}

	/*++
		打开共享内存，name 共享内存名称
	--*/
	public boolean open(String name)
	{	
		_handle = _open(name);
		return _handle != 0;
	}

	/* 写数据 */
	public int write(byte[] data)
	{
		return _write(_handle, data);
	}

	/* 写数据 */
	public int write(byte[] data, int pos, int len)
	{
		return _write2(_handle, data, pos, len);
	}

	/* 读数据 */
	public int read(byte[] data)
	{
		return _read(_handle, data);
	}

	/* 锁定共享内存 */
	public boolean lock()
	{
		return _lock(_handle);
	}

	/* 解锁 */
	public void unlock()
	{
		_unlock(_handle);
	}

	/*++
	移动读指针
		offset 偏移大小
		whence 从什么地方开始计算偏移
    返回值：
		读指针的当前位置，偏移超出共享内存范围，返回失败-1
	--*/
	public int readSeek(int offset, int whence)
	{
		return _readSeek(_handle, offset, whence);
	}

	/*++
	移动写指针
		offset 移动的偏移大小
		whence 从什么地方开始计算偏移：
			SEEK_SET 共享内存开始
			SEEK_CUR 当前位置
			SEEK_END 共享内存末尾
    返回值：
		写指针的当前位置，偏移超出共享内存范围，返回失败-1
	--*/
	public int writeSeek(int offset, int whence)
	{
		return _writeSeek(_handle, offset, whence);
	}

	/* 获取读指针位置 */
	public int getReadPos()
	{
		return _getReadPos(_handle);
	}

	/* 获取写指针位置 */
	public int getWritePos()
	{
		return _getWritePos(_handle);
	}

	/* 获取共享内存大小 */
	public int getSize()
	{
		return _getSize(_handle);
	}
	
	
	/**
	 * 从头写一段字符串，用ansic的方式进行保存。
	 * 
	 * */
	
	public byte[] StringToByte(String str) {
		if (str == null)
			return null;
		try {
			return str.getBytes("US-ASCII");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean writeAnsicStr(String str,Integer start) {
		byte[] data = StringToByte(str);
		lock();
		writeSeek(0, start);
		write(data, start, data.length);
		unlock();
		return false;
	}
	

	public static void main(String[] args)
	{
		SharedMemory sharedMemory = new SharedMemory();
		
		 byte[] a = sharedMemory.StringToByte("");
         for(byte e:a) {
        	 System.out.println((byte)e);
         }
//		byte[] data = {65, 66, 67, 68, 69 ,70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81};
//		
//		if (!sharedMemory.create("avstream", 1024*16))
//		{
//			System.out.println("create shared memory failed");
//			return;
//		}
//
//		try{
//			for(;;)
//			{
//				for(int i = 0; i < data.length; i++)
//				{
//					sharedMemory.lock();
//					sharedMemory.writeSeek(0, SEEK_SET);
//					sharedMemory.write(data, 0, i+1);
//					sharedMemory.unlock();
//					Thread.sleep(1000);
//				}
//
//
//
//			}
//		}catch(Exception e)
//		{
//
//		}

		
	}

}


