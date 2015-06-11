package NVMP.jms.proxy;

import java.util.HashMap;

import javax.jms.JMSException;

import NVMP.jms.ctrl.MessageSendHandler;
import NVMP.jms.impl.ProxyHandler;
import NVMP.jms.impl.Servering;

public class DBSynchronization implements ProxyHandler {

	private MessageSendHandler msh;

	@Override
	public void SetSendHandler(MessageSendHandler ms) {
		msh = ms;

	}

	static public class Event {

		public void OnServerUpLoadOverEvent(String uuid, Integer verson,
				byte[] data) {

		}

		public void OnServerDownLoadOverEvent(Integer verson, String centerid,
				String uuid, byte[] data) {
		}

		public void OnServerQueueSendNowVersonEvent(String versions,
				String sessionid, byte[] data) {

		}

		public void OnClientQueueSendEvent(String centerid, String sessionid,
				String ip, String uuid, byte[] data) {

		}

		public void OnClientQueueNowVersonEvent(String sessionid,
				String centerid, byte[] data) {

		}

		public void OnClientQueueGetDataEvent(String sessionid,
				String centerid, String ip, String uuid, String versions,
				byte[] data) {

		}

		public void OnClientServerQueueSendEvent(String sessionid,
				String centerid, String ip, String uuid, String versions,
				byte[] data) {

		}

	}

	Event e;

	public void setE(Event e) {
		this.e = e;
	}

	// 回应处理信息入口。
	public void ReturnEvent(HashMap<String, Object> Params, String url,
			byte[] data) {

		// ====================先写客户端要处理的代码 =================

		if (url.equals("DBSynchronization.ServerUpLoadOver")) {
			String uuid = Params.get("uuid").toString();
			Integer verson = Integer.valueOf(Params.get("verson").toString());
			if (e != null)
				e.OnServerUpLoadOverEvent(uuid, verson, data);

		} else if (url.equals("DBSynchronization.ServerDownLoadOver")) {

			Integer verson = null;
			String uuid = Params.get("uuid").toString();
			String centerid = Params.get("centerid").toString();
			try {
				verson = Integer.parseInt(Params.get("verson").toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (e != null)
				e.OnServerDownLoadOverEvent(verson, centerid, uuid, data);

		} else if (url.equals("DBSynchronization.ServerQueueSendNowVerson")) {
			String versions = Params.get("versions").toString();
			String sessionid = Params.get("sessionid").toString();
			if (e != null)
				e.OnServerQueueSendNowVersonEvent(versions, sessionid, data);

		} else if (url.equals("DBSynchronization.SendHeard")) {

		}

		// ===============楼下是服务器端接收后的事件处理===========

		else if (url.equals("DBSynchronization.ClientQueueSend")) {

			String centerid = Params.get("centerid").toString();
			String sessionid = Params.get("sessionid").toString();
			String ip = Params.get("ip").toString();
			String uuid = Params.get("uuid").toString();
			if (e != null)
				e.OnClientQueueSendEvent(centerid, sessionid, ip, uuid, data);
		}

		else if (url.equals("DBSynchronization.ClientQueueNowVerson")) {

			String sessionid = Params.get("sessionid").toString();
			String centerid = Params.get("centerid").toString();
			if (e != null)
				e.OnClientQueueNowVersonEvent(sessionid, centerid, data);
		}

		else if (url.equals("DBSynchronization.ClientQueueGetData")) {
			String sessionid = Params.get("sessionid").toString();
			String centerid = Params.get("centerid").toString();
			String ip = Params.get("ip").toString();
			String uuid = Params.get("uuid").toString();
			String versions = Params.get("versions").toString();

			if (e != null)
				e.OnClientQueueGetDataEvent(sessionid, centerid, ip, uuid,
						versions, data);

		} else if (url.equals("DBSynchronization.ClientServerQueueSend")) {
			String sessionid = Params.get("sessionid").toString();
			String centerid = Params.get("centerid").toString();
			String ip = Params.get("ip").toString();
			String uuid = Params.get("uuid").toString();
			String versions = Params.get("versions").toString();
			if (e != null)
				e.OnClientServerQueueSendEvent(sessionid, centerid, ip, uuid,
						versions, data);
		}

	}

	/**
	 * 服务器发送给客户端，上传后版本号等等信息。
	 * 
	 * @throws JMSException
	 * */
	@Servering
	public void ServerUpLoadOver(String reciveId, Integer verson, String uuid,
			byte[] data) throws JMSException {
		String url = "DBSynchronization.ServerUpLoadOver";
		// 参数 ： 就一个版本。
		HashMap<String, Object> hp = new HashMap<String, Object>();
		hp.put("verson", verson);
		hp.put("uuid", uuid);
		// 写下如何发送
		this.msh.QueueSend(reciveId, url, hp, data);
	}

	/**
	 * 服务器发送给客户端，需要下载的相关信息。
	 * 
	 * @throws JMSException
	 * */
	@Servering
	public void ServerDownLoadOver(String reciveId, Integer verson,
			String centerid, String uuid, byte[] data) throws JMSException {
		String url = "DBSynchronization.ServerDownLoadOver";
		// 参数 ： 就一个版本。
		HashMap<String, Object> hp = new HashMap<String, Object>();
		hp.put("verson", verson);
		hp.put("centerid", centerid);
		hp.put("uuid", uuid);
		// 写下如何发送
		this.msh.QueueSend(reciveId, url, hp, data);
	}

	/**
	 * 服务器发送当前最新版本信息
	 * 
	 * @throws JMSException
	 * */
	@Servering
	public void ServerQueueSendNowVerson(String reciveId, String sessionid,
			String versions, byte[] data) throws JMSException {
		String url = "DBSynchronization.ServerQueueSendNowVerson";
		// 参数 ： 就一个版本。
		HashMap<String, Object> hp = new HashMap<String, Object>();
		hp.put("sessionid", sessionid);
		hp.put("versions", versions);
		// 写下如何发送
		this.msh.QueueSend(reciveId, url, hp, data);
	}

	// ========================客户端===============

	/**
	 * 客户端发送同步数据,包含了中心ID，操作人ID，操作人IP和一个xml文件流
	 * 
	 * @throws JMSException
	 * */
	public void ClientQueueSend(String sessionid, String centerid, String ip,
			String uuid, byte[] data) throws JMSException {
		String url = "DBSynchronization.ClientQueueSend";
		// 参数 ：
		HashMap<String, Object> hp = new HashMap<String, Object>();
		hp.put("centerid", centerid);
		hp.put("sessionid", sessionid);
		hp.put("ip", ip);
		hp.put("uuid", uuid);
		this.msh.QueueSend(null, url, hp, data);
	}

	/**
	 * 客户端发送心跳包
	 * 
	 * @throws JMSException
	 * */
	public void SendHeard() throws JMSException {
		String url = "DBSynchronization.SendHeard";
		// 参数 ： 就一个版本。
		HashMap<String, Object> hp = new HashMap<String, Object>();
		// 写下如何发送
		this.msh.QueueSend(null, url, hp, null);
	}

	/**
	 * 客户端发送请求询问当前最新版本
	 * 
	 * @throws JMSException
	 * 
	 * */
	public void ClientQueueNowVerson(String sessionid, String centerid,
			byte[] data) throws JMSException {
		String url = "DBSynchronization.ClientQueueNowVerson";
		// 参数:
		HashMap<String, Object> hp = new HashMap<String, Object>();
		hp.put("sessionid", sessionid);
		hp.put("centerid", centerid);
		this.msh.QueueSend(null, url, hp, data);
	}

	/**
	 * 客户端发送请求，要求同步指定的数据。
	 * 
	 * @throws JMSException
	 * */
	public void ClientQueueGetData(String sessionid, String centerid,
			String ip, String uuid, String versions, byte[] data)
			throws JMSException {
		String url = "DBSynchronization.ClientQueueGetData";
		// 参数：
		HashMap<String, Object> hp = new HashMap<String, Object>();
		hp.put("sessionid", sessionid);
		hp.put("centerid", centerid);
		hp.put("ip", ip);
		hp.put("uuid", uuid);
		hp.put("versions", versions);
		this.msh.QueueSend(null, url, hp, data);
	}

	/**
	 * 特殊客户端发送请求,服务器端接受该信息后，进行直接保存。 客户端发送同步数据,包含了中心ID，操作人ID，操作人IP和一个xml文件流
	 * 
	 * @throws JMSException
	 * */
	public void ClientServerQueueSend(String sessionid, String centerid,
			String ip, String uuid, String versions, byte[] data)
			throws JMSException {
		String url = "DBSynchronization.ClientServerQueueSend";
		// 参数 ：
		HashMap<String, Object> hp = new HashMap<String, Object>();
		hp.put("centerid", centerid);
		hp.put("sessionid", sessionid);
		hp.put("ip", ip);
		hp.put("uuid", uuid);
		hp.put("versions", versions);
		this.msh.QueueSend(null, url, hp, data);
	}

}
