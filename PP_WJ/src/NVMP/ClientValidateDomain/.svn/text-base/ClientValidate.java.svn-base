package NVMP.ClientValidateDomain;

import NVMP.AppService.Remoting;

/**
 * 客户端有效性验证类
 * 1、仅仅针对客户端数量进行验证。
 * 2、具体信息保存在nvmp_server_clients
 * 3、根据客户端的ID进行注册判断
 * */
public class ClientValidate {

	public IMessage message;
	
	private ValidateDao validateDao;
	
	public ClientValidate() {
		validateDao = new ValidateDao();
		validateDao.init();
	}
	
	@Remoting
	public void clientValidate(String clientID){
		String ip = ClientValidateDomain.AppRunTime().getIP(clientID);
		ClientValidateDomain.AppRunTime().SetCurChannel("Local://Session://"+clientID);
		message.onClientValidate(validateDao.clientValidate(clientID, ip));
	}
	
	public void clientQuit(String clientID){
		validateDao.clientQuit(clientID);
	}
	
}
