package client;

import NVMP.Proxy.CommandDomain;

public class SinglePadHandler extends CommandDomain.EventHandler {
	SinglepadTest app;
	public SinglePadHandler(SinglepadTest singlepadTest) {
		app = singlepadTest;
	}

	@Override
	public Object VideoeAssignEvent(String DevicerId, Integer Index,
			String DestCommander, Boolean IsStart) {
		
		System.out.println("now play  "+ DevicerId);
		app.playVideo(DevicerId, Index);
		
		
		
		return null;
	}
}
