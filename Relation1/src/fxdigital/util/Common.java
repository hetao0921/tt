/**
 * 文件不存在时不用创建，只返回一个默认值
 * 最后修改时间：2011.11.28
 * gj
 */
package fxdigital.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class Common {
	private static final char[] HEX = { '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static String getPwd(String pwd) {
		// 获得加密后的密码
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(pwd.getBytes());
			byte[] md = md5.digest();
			
			char[] cs = new char[md.length*2];
			int idx = 0;
			for (int i = 0; i < md.length; i++) {
				byte b = md[i];
				cs[idx++] = HEX[b>>>4 & 0xf];
				cs[idx++] = HEX[b & 0xf];
			}
			pwd = new String(cs);
			System.out.println(pwd);

			// for (int i = 0; i < md.length; i++) {
			// int val = md[i] & 0xff;
			// if (val < 0xf) {
			// buf.append("0");
			// }
			// buf.append(Integer.toHexString(val));
			// }
			// System.out.println(buf.toString());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
		return pwd.substring(8, 24);
	}

	public static Document getDocument(String path) {
		SAXBuilder builder = new SAXBuilder();
		Document document = null;
		try {
			document = builder.build(new FileInputStream(new File(path)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return document;
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param path
	 * @return
	 */
	public static boolean IsExists(String file) {
		File f = new File(file);
		if (f.exists()) {
			return true;
		} else {
			// 如果文件不存在，就判断该文件存放的路径存不存在，如果不存在，就创建目录
			String path = "";
			if (System.getProperty("os.name").equals("Linux")) {
				path = file.substring(0, file.lastIndexOf("/"));
			} else {
				path = file.substring(0, file.lastIndexOf("\\"));
			}
			File ff = new File(path);
			if (!ff.exists()) {
				ff.mkdirs();
			}
			return false;
		}

	}

	public static boolean upXml(Document doc, String path) {
		boolean result = false;
		Format format = Format.getRawFormat();
		format.setEncoding("UTF-8");
		// XMLOutputter
		XMLOutputter output = new XMLOutputter(format);
		try {
			output.output(doc, new FileOutputStream(path));
			result = true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static List<String> readFile(String path) {
		List<String> list = new ArrayList<String>();
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			fis = new FileInputStream(path);
			isr = new InputStreamReader(fis, "UTF-8");
			br = new BufferedReader(isr);
			String ss = null;
			while ((ss = br.readLine()) != null) {
				list.add(ss);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
				if (isr != null)
					isr.close();
				if (fis != null)
					fis.close();
			} catch (Exception e) {
				System.out.println("IO");
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * 写锟斤拷锟侥硷拷
	 */
	public static void writeFile(String xml, String path) {
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;

		try {
			fos = new FileOutputStream(path);
			osw = new OutputStreamWriter(fos);
			osw.write(xml, 0, xml.length());
			osw.flush();
			osw.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (osw != null)
					osw.close();
				if (fos != null)
					fos.close();
			} catch (Exception e) {
				System.out.println("");
				e.printStackTrace();
			}
		}
	}

	/**
	 * 追加记录日志文本
	 * 
	 * @param str
	 * @param path
	 */
	public static void apendedFile(String str, String path) {
		str = getNowTime() + "  :" + str + "\n";
		try {
			RandomAccessFile raf = new RandomAccessFile(path, "rw");
			raf.seek(raf.length());
			raf.write(str.getBytes("utf-8"));

			raf.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static String getNowTime() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		return sdf.format(cal.getTime());
	}

	public static void main(String... args) {
		getPwd("123");
		// apendedFile("测试写入文件东东继续写入\n","c:\\abc.txt");
		// System.out.println("写入完毕");
	}
}
