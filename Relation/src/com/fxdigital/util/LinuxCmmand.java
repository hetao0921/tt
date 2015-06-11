package com.fxdigital.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class LinuxCmmand {
	
	public int processCmmand(String cmmand){
	int n=-1;
	String[] comands = new String[] { "/bin/sh", "-c",cmmand};
	ProcessBuilder pb = new ProcessBuilder(comands);
	Process p;
	pb.redirectErrorStream(true);
	try {
		p = pb.start();
		InputStream in = p.getInputStream();
		Scanner scanner = new Scanner(in);
		while (scanner.hasNext()) {
			System.out.println("输出："+scanner.nextLine());
		}
	n =	p.waitFor();
//	System.out.println("n:"+n);
	p.destroy();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return n;
}
}
