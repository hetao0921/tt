package server;

import NVMP.AppService.Implement.ApplicationServer;

public class ServerRun {
	public static void main(String[] args) {
		ApplicationServer AppServer = new ApplicationServer();

		AppServer.Run(AppServer);
	}
}
