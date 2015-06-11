package NVMP.AppService;



import NVMP.AppService.Implement.ApplicationServer;

public class main  {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
//		if(args.length!=3) {
//			System.out.println("参数输入错误，请输出 ID IP Port");
//			System.exit(1);
//		}
//		String id = args[0];
//		String ip = args[1];
//		int port = Integer.parseInt(args[2]);
		
//		 TestInit.getInstance().setCenterInfo("00BAB65CDC@001", "0.0.0.0", 1900);
		 ApplicationServer AppServer = new ApplicationServer();

		 AppServer.Run(AppServer);	 
		 
	}

}
