package com.fxdigital.mysqlsync.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.fxdigital.mysqlsync.bean.ServerInfo;
import com.fxdigital.mysqlsync.bundle.MySqlDumpBean;
import com.fxdigital.mysqlsync.bundle.ResourceBundle;

/**
 * @author Administrator
 * 文件工具类
 *
 */
public class FileUtil {

	private static final Logger logger = Logger.getLogger(FileUtil.class);

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 */
	public static String readFileByLines(String path) {
		File file = new File(path);
		BufferedReader reader = null;
		String result = "";
		try {

			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			while ((tempString = reader.readLine()) != null) {
				result = result + tempString;
				logger.info(tempString);
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}

		return result;
	}

	/**
	 * A方法追加文件：使用RandomAccessFile
	 */
	public static void appendMethodA(String fileName, String content) {
		try {
			// 打开一个随机访问文件流，按读写方式
			RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
			// 文件长度，字节数
			long fileLength = randomFile.length();
			// 将写文件指针移到文件尾。
			randomFile.seek(fileLength);
			randomFile.writeBytes(content);
			randomFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * B方法追加文件：使用FileWriter
	 */
	public static void appendMethodB(String fileName, String content) {
		try {
			// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			FileWriter writer = new FileWriter(fileName, true);
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void write(String path, String str) {
		// File fileName = new File("D:\\fileName.txt");
		File fileName = new File(path);
		PrintWriter outFile = null;
		try {
			outFile = new PrintWriter(fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		outFile.write(str);
		outFile.flush();
		outFile.close();
	}

	public static String addPoint() {
		String str = "";
		if (System.getProperty("os.name").equals("Linux")) // 操作系统名称
		{
			str = "\n";
		} else {
			// logger.info("==============="+System.getProperty("user.dir"));
			str = "\r\n";
		}
		return str;
	}

	public static ServerInfo readPathXML(String path) {
		ServerInfo serverinfo=new ServerInfo();
		SAXReader read = new SAXReader();
		Document doc = null;
        if(FileUtil.isExist(path)){
    		try {
    			doc = read.read(path);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		Element root = doc.getRootElement();
    		Element flag = root.element("flag");
    		Element master = root.element("master");
    		Element slave = root.element("slave");
    		Element result = root.element("result");
    		Element time = root.element("time");

    		// logger.info(url.getStringValue());
    		// logger.info(time.getStringValue());
    		serverinfo.setFlag(flag.getStringValue());
    		serverinfo.setMaster(master.getStringValue());
    		serverinfo.setSlave(slave.getStringValue());
    		serverinfo.setResult(result.getStringValue());
    		serverinfo.setTime(time.getStringValue());
        }


		return serverinfo;

	}

	public static int modiXMLFile(String path, String node, String value) {
		int returnValue = 0;
		try {
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(new File(path));

			/** 修改内容之三: 若title内容为Dom4j Tutorials,则删除该节点 */
			List list = document.selectNodes("/server/" + node);
			Iterator iter = list.iterator();
			while (iter.hasNext()) {
				Element bookElement = (Element) iter.next();
				bookElement.setText(value);
			}
			try {
				/** 将document中的内容写入文件中 */
				XMLWriter writer = new XMLWriter(new FileWriter(new File(path)));
				writer.write(document);
				writer.close();
				/** 执行成功,需返回1 */
				returnValue = 1;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return returnValue;
	}

	public static int createXMLFile(String filename, ServerInfo serverInfo) {

		int returnValue = 0;

		Document document = DocumentHelper.createDocument();

		Element booksElement = document.addElement("server");

		Element flagElement = booksElement.addElement("flag");
		/** 加入flag属性内容 */
		flagElement.setText(serverInfo.getFlag() == null ? "" : serverInfo
				.getFlag());
		/** 加入master节点 */
		Element masterElement = booksElement.addElement("master");
		/** 为master设置内容 */
		masterElement.setText(serverInfo.getMaster() == null ? "" : serverInfo
				.getMaster());
		/** 加入slave节点 */
		Element slaveElement = booksElement.addElement("slave");
		/** 为slave设置内容 */
		slaveElement.setText(serverInfo.getSlave() == null ? "" : serverInfo
				.getSlave());
		/** 加入result节点 */
		Element resultElement = booksElement.addElement("result");
		/** 为result设置内容 */
		resultElement.setText(serverInfo.getResult() == null ? "" : serverInfo
				.getResult());
		/** 加入time节点 */
		Element timeElement = booksElement.addElement("time");
		/** 为time设置内容 */
		timeElement.setText(serverInfo.getTime() == null ? "" : serverInfo
				.getTime());

		try {
			/** 将document中的内容写入文件中 */
			XMLWriter writer = new XMLWriter(new FileWriter(new File(filename)));
			writer.write(document);
			writer.close();
			/** 执行成功,需返回1 */
			returnValue = 1;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return returnValue;
	}
	
	
	public static boolean isExist(String filePath){
		File file=new File(filePath);
		if(file.exists()){
			return true;
		}else{
			return false;
		}
	}

	public static void main(String[] args) {

		// String fileName = "C:\\Users\\Administrator\\Desktop\\8042\\my.cnf";
		// // String content = "new append!";
		// String content = MysqlSetCnf.getSlaveCnf();
		// // 按方法A追加文件
		// String str = FileUtil.readFileByLines(fileName);
		// if (!str.contains("server_id=2")) {
		// FileUtil.appendMethodA(fileName, content);
		// }

		// FileUtil.appendMethodA(fileName, "append end.");
		// 显示文件内容
		// FileUtil.readFileByLines(fileName);// .readFileByLines(fileName);

		/*
		 * //按方法B追加文件 AppendToFile.appendMethodB(fileName, content);
		 * AppendToFile.appendMethodB(fileName, "append end. \n"); //显示文件内容
		 * ReadFromFile.readFileByBytes(fileName);
		 */
		// ReadFromFile.readFileByLines(fileName);

		logger.info(System.getProperty("user.dir")
				+ "\\resources\\serverinfo.properties");

		// FileUtil.writeProperties("server.time", "2222");
		String path = System.getProperty("user.dir")
				+ "\\resources\\serverinfo111.xml";
		//FileUtil.readPathXML(path);
		ServerInfo serverInfo = new ServerInfo();
		serverInfo.setFlag("0");
		serverInfo.setResult("1");
		String node = "result";

		// FileUtil.createXMLFile(System.getProperty("user.dir")
		// + "\\resources\\serverinfo111.xml", serverInfo);
		//FileUtil.modiXMLFile(path, node, "3");
		
		
		FileUtil.createXMLFile(path,serverInfo);
	    MySqlDumpBean mdb = ResourceBundle.getDumpResource();
		String cnfpath = mdb.getSendpath();
		System.out.println(FileUtil.isExist(cnfpath.replace(" ", "") + "/my.cnf"));
	}
}
