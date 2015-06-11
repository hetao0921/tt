package Runtime;

import java.util.HashMap;

import corenet.netbase.Interface.IChannel;
import corenet.rpc.IMessage;


public interface IRunTime {
	public void setTransChannel(IChannel aChannel);

	public void Invoke(String ObjUrl, HashMap<String, Object> Param,
			ReturnDo AsyncResult, Object Context);

	public void OnRecvMessage(IMessage Message);
	
	public void setNewConnectOk(IConnectOK co); 
	public void OnAgainConnect();
}
