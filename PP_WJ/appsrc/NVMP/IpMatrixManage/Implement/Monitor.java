package NVMP.IpMatrixManage.Implement;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Monitor extends Thread {
	private Daemon daemon;
	private String command;

	public String getCommand() {
		return command;
	}

	public Monitor(Daemon daemon, String command) {
		this.daemon = daemon;
		this.command = command;
	}

	public void run() {

		String[] comands = new String[] { "/bin/sh", "-c", command };
		System.out.println(command);
		ProcessBuilder pb = new ProcessBuilder(comands);
		Process p;
		pb.redirectErrorStream(true);
		try {
			p = pb.start();
			InputStream in = p.getInputStream();
			Scanner scanner = new Scanner(in);
			while (scanner.hasNext()) {
				System.out.println(scanner.nextLine());
			}

			p.waitFor();
			p.destroy();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		daemon.fireEvent(this);
	}
}
