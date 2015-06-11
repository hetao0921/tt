package fxdigital.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public final class LinuxCmd {
	
	public static final String SUCCESS = "0 ";
	public static final String FAIL = "-1 ";
 
	public static final String processUseBasic(String command) {

		Process p = null;

		StringBuilder sb = new StringBuilder();

		try {

			String[] comands = new String[] { "/bin/sh", "-c", command };
			p = Runtime.getRuntime().exec(comands);
			String error = read(p.getErrorStream());
			String outInfo = read(p.getInputStream());
			String resultCode = SUCCESS;   // 脚本中输出0表示命令执行成功
			sb.append(resultCode);
			if (error.length() != 0) { // 如果错误流中有内容，表明脚本执行有问题
				resultCode = FAIL;
				sb.append(resultCode);
				sb.append(error);
				sb.append(outInfo);
			}

			p.waitFor();

		} catch (Exception e) {
		} finally {
			try {
				p.getErrorStream().close();
			} catch (Exception e) {
			}
			try {
				p.getInputStream().close();
			} catch (Exception e) {
			}
			try {
				p.getOutputStream().close();
			} catch (Exception e) {
			}
		}
		return sb.toString();
	}

	public static final String read(InputStream in) throws IOException {
		ByteBuffer bb = ByteBuffer.allocate(5000);
		int ch;
		while (-1 != (ch = in.read()))
			bb.put((byte)ch);
		bb.flip();
		CharBuffer charBuffer = Charset.forName("utf-8")
			.decode(bb);
		return charBuffer.toString();
	}

}
