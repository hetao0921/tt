package fxdigital.mqcore.util;

import java.nio.ByteBuffer;

public class ByteArrayUtil {

	// 合并多个数组成为一个byte[];
	static public byte[] Sum(byte[]... array) {
		int n = 0;
		if (array == null)
			return null;
		for (byte[] b : array) {
			if(b!=null)  n += b.length;
		}
		ByteBuffer bf = ByteBuffer.allocate(n);
		for (byte[] b : array) {
		  if(b!=null) bf.put(b);
		}
		return bf.array();

	}

	// 拆分一个数组，输入新数组的开始，长度。
	static public byte[] Trim(byte[] array, int start, int len) {
		if (array == null)
			return null;
		if (array.length < (start + len))
			return null;
		ByteBuffer bf = ByteBuffer.allocate(len);
		bf.put(array, start, len);

		return bf.array();
	}

}
