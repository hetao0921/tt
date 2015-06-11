package NVMP.IpMatrixManage.Implement;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.misc.log.LogUtil;

/**
 * 按配置文件来建立进程。
 * 
 * */
public class MainRun extends Thread {

	static public String getCmd(String szAddress, String sessionid,
			String nPort, String szName, String szPass, String decMaxtrixStatus) {
		String pr; 
		if (System.getProperty("os.name").equals("Linux")) {
			pr = ":";
		} else {
			pr = ";";
		}

		System.out.println(System.getProperty("user.dir"));

		String path = System.getProperty("user.dir");
		String path2 = path + File.separator + "lib";
		StringBuffer sb = new StringBuffer("java -classpath \"");
		File f = new File(path);
		for (String s : f.list()) {
			if (s.matches(".*\\.jar$"))
				sb.append(s + pr);

		}
		f = new File(path2);
		for (String s : f.list()) {
			if (s.matches(".*\\.jar$"))
				sb.append("." + File.separator + "lib" + File.separator + s
						+ pr);

		}
		String cmd = sb.substring(0, sb.length() - 1);

		cmd = cmd + "\" NVMP.IpMatrixManage.Implement.IpMatrixManageRun "
				+ szAddress + " " + sessionid + " " + nPort + " " + szName
				+ " " + szPass + " " + decMaxtrixStatus;
		System.out.println(cmd);
		return cmd;
	}

	public static void prcess() {

		// 读取配置文件， 然后建立多个进程。
		// List<String> l = new LinkedList<String>();
		try {
			String path;
			if (System.getProperty("os.name").equals("Linux"))
				path = "/etc/fxconf/hikmatrix/hikmatrix.xml";
			else
				path = "d:\\fxconf\\hikmatrix\\hikmatrix.xml";

			// 读一下配置文件中的配置。
			SAXReader saxReader = new SAXReader();
			Document doc = null;
			doc = saxReader.read(new File(path));
			@SuppressWarnings("unchecked")
			Iterator<Element> iter = doc.getRootElement().element("Maxtrix")
					.elementIterator();
			while (iter.hasNext()) {
				Element e = iter.next();

				// <Device Address="192.168.2.79" Port="8000" Admin="admin"
				// Pass="12345" class="DS6308" DeviceId="s001" OutModel="1" />
				String szAddress = e.attributeValue("Address");
				String sessionid = e.attributeValue("DeviceId");
				String nPort = e.attributeValue("Port");
				String szName = e.attributeValue("Admin");
				String szPass = e.attributeValue("Pass");
				String decMaxtrixStatus = e.attributeValue("OutModel");
				Runtime.getRuntime().exec(
						getCmd(szAddress, sessionid, nPort, szName, szPass,
								decMaxtrixStatus));
				// l.add(getCmd(szAddress, sessionid, nPort, szName, szPass,
				// decMaxtrixStatus));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LogUtil.error("建立进程失败。");
		}
		// return l;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		prcess();
		while (true) {
			try {
				this.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	static public void main(String... args) {
		MainRun mr = new MainRun();

		mr.start();

		// ProcessBuilder pb = new ProcessBuilder(prcess());
		// // Map<String, String> env = pb.environment();
		// // env.put("path", "/usr/bin");
		//
		// try {
		// pb.start();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}
}
