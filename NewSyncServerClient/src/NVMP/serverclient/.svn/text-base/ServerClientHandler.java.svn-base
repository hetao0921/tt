/*    */package NVMP.serverclient;

/*    */
/*    */import NVMP.jms.proxy.DBSynchronization;
import NVMP.jms.proxy.DBSynchronization.Event;
/*    */
import java.io.PrintStream;
/*    */
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;

/*    */
/*    */public class ServerClientHandler extends DBSynchronization.Event
/*    */{
	/*    */private ServerClient sc;

	/*    */
	/*    */public void setServerClient(ServerClient serverClient)
	/*    */{
		/* 13 */this.sc = serverClient;
		/*    */}

	/*    */
	/*    */public void OnServerQueueSendNowVersonEvent(String versions,
			String sessionid, byte[] data)
	/*    */{
		/* 24 */String[] vers = versions.split("}");
		/* 25 */for (String ver : vers) {
			/* 26 */ver = ver.substring(1);
			/* 27 */String centerid = ver.split(":")[0];
			/* 28 */String versis = ver.split(":")[1];
			/* 29 */this.sc.putVersions(centerid, versis);
			/*    */}
		/*    */}

	/*    */
	/*    */public void OnServerDownLoadOverEvent(Integer verson, String centerid,
			String uuid, byte[] data)
	/*    */{
		/* 41 */System.out.println(" server send data " + centerid + " "
				+ verson);
		/*    */
		/* 43 */JdbcImpl jdbc = JdbcImpl.getJdbcImpl();
		/* 44 */if (this.sc.isRequest(centerid, verson.toString()))
		/*    */{
			/* 47 */jdbc.insertOperateRecord(uuid, centerid, "下载", centerid);
			/* 48 */String xml = "";
			/*    */try {
				/* 50 */xml = new String(data, "utf-8");
				/*    */}
			/*    */catch (UnsupportedEncodingException e) {
				/* 53 */e.printStackTrace();
				/*    */}
			/* 55 */String path = "";
			/* 56 */if (System.getProperty("os.name").equals("Linux"))
			/*    */{
				/* 58 */path = "/etc/sync/" + centerid + verson + ".xml";
				/*    */}
			/*    */else {
				/* 61 */path = "c:\\" + centerid + verson + ".xml";
				/*    */}
			/* 63 */wirteXml(path, xml);
			/*    */
			/* 65 */jdbc.updateVersion(centerid, verson.intValue());
			/*    */
			/* 68 */jdbc.insertSource(centerid, uuid, path, verson);
			/*    */
			/* 71 */this.sc.removeRequest(centerid);
			/*    */}
		/*    */}

	/*    */
	/*    */private void wirteXml(String address, String xml)
	/*    */{
		/*    */try {
			/* 78 */RandomAccessFile raf = new RandomAccessFile(address, "rw");
			/* 79 */raf.setLength(0L);
			/* 80 */raf.seek(0L);
			/* 81 */raf.write(xml.getBytes("utf-8"));
			/* 82 */raf.close();
			/*    */} catch (Exception e) {
			/* 84 */e.printStackTrace();
			/*    */}
		/*    */}
	/*    */
}

/*
 * Location: C:\Users\hxht\Desktop\SyncServerClient.jar Qualified Name:
 * NVMP.serverclient.ServerClientHandler JD-Core Version: 0.6.2
 */