package NVMP.AppService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


/**
 * 
 * @author <h1>Hoocoln<h1>
 * @version 2013-8-29
 */
public class Exec {

	public void cmd(String cmd, String outFile, String...args) {
		try {
			String command = cmd;
			if (args.length > 0) {
				for (int i = 0; i < args.length; i++) {
					command = command + " " + args[i];
				}
			} else {
				throw new MyException("got no params!");
			}
			Process pro = Runtime.getRuntime().exec(command);
			new Thread(new out(pro.getInputStream())).start();
			new Thread(new outToFile(pro.getErrorStream(), outFile)).start();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MyException e) {
			System.out.println(e.getMessage());
		}
	}

	@SuppressWarnings("serial")
	class MyException extends Exception {
		String message;

		public MyException(String ErrorMessagr) {
			message = ErrorMessagr;
		}

		public String getMessage() {
			return message;
		}
	}

	class out implements Runnable {

		private InputStream input;

		public out(InputStream input) {
			this.input = input;
		}

		public void run() {
			BufferedReader br = new BufferedReader(new InputStreamReader(input));
			String line = null;
			try {
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	class outToFile implements Runnable {

		private InputStream input;
		private String filename;

		public outToFile(InputStream input, String filename) {
			this.input = input;
			this.filename = filename;
		}

		public void run() {
			BufferedReader br = new BufferedReader(new InputStreamReader(input));
			BufferedWriter bw = null;
			try {
				bw = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(filename, true)));
				String line = null;
				while ((line = br.readLine()) != null) {
					bw.write(line + "\r\n");
				}
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					br.close();
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if(args.length!=1){
			System.out.println("请输入本机IP");
			System.exit(1);
		}
		Exec exec = new Exec();
		List<HashMap<String, String>> list= exec.executeQuery("select * from nvmp_centerinfosynctab where CenterIP = '"+args[0]+"'");
//		list.clear();
//		HashMap<String, String> item = new HashMap<String, String>();
//		item.put("CenterID","haha");
//		item.put("CenterIP", "0.0.0.0");
//		item.put("CenterPort", "9901");	
//		list.add(item);
		for(HashMap<String, String> hp:list) {
			exec.cmd("java -jar -Xmx100m ServerApp.jar", "AppServerLog_"+hp.get("CenterID")+".txt",hp.get("CenterID"),"0.0.0.0",hp.get("CenterPort"));
		}
		

	}

	
	public String getUrl(){
		String ip= "127.0.0.1";
		String url = "jdbc:mysql://"+ip+":3306/nvmp?user=admin&password=111&characterEncoding=" +
		"gbk&autoReconnect=true";
		return url;
	}
	
	/**
	 * 根据SQL语句，返回查询结果
	 * @param aim
	 * @return
	 */
	public List<HashMap<String, String>> executeQuery(String aim){

		LinkedList<HashMap<String, String>> lnkd = new LinkedList<HashMap<String, String>>();
//		LinkedList<String[]> lnkd = new LinkedList<String[]>();
		try{
			Connection conn = getConn();
			java.sql.Statement st = conn.createStatement();
			ResultSet resultSet = st.executeQuery(aim);
			ResultSetMetaData remd = resultSet.getMetaData();
			while (resultSet.next()) {
				HashMap<String, String> hm = new HashMap<String, String>();
//				String[] ss = new String[remd.getColumnCount()];
				for (int i = 1; i <= remd.getColumnCount(); i++) {
//					ss[i-1] = resultSet.getString(i);
					hm.put(remd.getColumnName(i), resultSet.getString(i));
				}
				lnkd.add(hm);
			}
			resultSet.close();
			st.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return lnkd;
	}


	private Connection getConn() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(getUrl());
		return con;
	}
	
	
}
