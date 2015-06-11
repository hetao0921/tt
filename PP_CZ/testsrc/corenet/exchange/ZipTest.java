package corenet.exchange;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * 测试压缩
 * */
public class ZipTest {

	// 压缩数据到本地文件

	static public void zip() throws IOException {
		LinkedList<ClientInfo> list = new LinkedList<ClientInfo>();
		for (int i = 0; i < 50000; i++) {
			String temp = Encoding.getUuid();
			ClientInfo clientInfo = new ClientInfo(String.valueOf(i), temp,
					temp, temp, temp, false, temp, false, false, i, temp, temp,
					temp, temp, temp, temp);
			list.add(clientInfo);
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream(1024 * 1024);

		ObjectOutputStream so = new ObjectOutputStream(out);
		so.writeObject(list);
		so.close();

		CheckedOutputStream cos = new CheckedOutputStream(new FileOutputStream(
				"D:\\BigData.z"), new CRC32());

		ZipOutputStream zos = new ZipOutputStream(cos);
		ZipEntry entry = new ZipEntry("zzw");

		zos.putNextEntry(entry);
		zos.write(out.toByteArray());
		zos.flush();
		zos.close();
	}

	// 从本地文件解压数据
	static public void unZip() throws IOException {

		CheckedInputStream cis = new CheckedInputStream(new FileInputStream(
				"D:\\BigData.z"), new CRC32());
		ZipInputStream zis = new ZipInputStream(cis);
		@SuppressWarnings("unused")
		ZipEntry entry = null;
		while ((entry = zis.getNextEntry()) != null) {

			ObjectInputStream si = new ObjectInputStream(zis);

			try {

				@SuppressWarnings("unchecked")
				LinkedList<ClientInfo> list = (LinkedList<ClientInfo>) si
						.readObject();

				for (ClientInfo info : list) {
					System.out.println(info.getCommanderId());
					// break;

				}

			} catch (ClassNotFoundException e)

			{
				System.out.println(e);
			}

			zis.closeEntry();
		}

	}

	
	//在内存中进行操作处理
	static public void userByteArrayInputStream() throws IOException {
		
		System.out.println("start " + Calendar.getInstance().getTime());
		LinkedList<ClientInfo> list = new LinkedList<ClientInfo>();
		for (int i = 0; i < 500000; i++) {
			String temp = Encoding.getUuid();
			ClientInfo clientInfo = new ClientInfo(String.valueOf(i), temp,
					temp, temp, temp, false, temp, false, false, i, temp, temp,
					temp, temp, temp, temp);
			list.add(clientInfo);
		}
		System.out.println("initData ok " + Calendar.getInstance().getTime());
		
		ByteArrayOutputStream out = new ByteArrayOutputStream(1024 * 1024);

		ObjectOutputStream so = new ObjectOutputStream(out);
		so.writeObject(list);
		so.close();
		System.out.println("object write ok " + Calendar.getInstance().getTime());

		
		byte[] arrays = out.toByteArray();
		System.out.println(arrays.length);
		
		//提供内存
		out.reset();

		
		CheckedOutputStream cos = new CheckedOutputStream(out, new CRC32());

		ZipOutputStream zos = new ZipOutputStream(cos);
		ZipEntry entry = new ZipEntry("zzw");

		zos.putNextEntry(entry);
		zos.write(arrays);
		zos.flush();
		zos.close();
		
		System.out.println("zip ok " + Calendar.getInstance().getTime());

		System.out.println(out.size());
		ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
		
		
		CheckedInputStream cis = new CheckedInputStream(in, new CRC32());
		ZipInputStream zis = new ZipInputStream(cis);

		while ((entry = zis.getNextEntry()) != null) {

			ObjectInputStream si = new ObjectInputStream(zis);

			try {

				@SuppressWarnings("unchecked")
				LinkedList<ClientInfo> list2 = (LinkedList<ClientInfo>) si
						.readObject();



			} catch (ClassNotFoundException e)

			{
				System.out.println(e);
			}

			zis.closeEntry();
		}
		System.out.println("unzip ok " + Calendar.getInstance().getTime());

		
		
		
		
	}
	
	
	public static void main(String[] args) throws IOException {
		// zip();
//		unZip();
		userByteArrayInputStream();
		
	}

}
