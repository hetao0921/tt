package NVMP.jms.impl;

import java.util.HashMap;

import javax.jms.JMSException;

import NVMP.jms.ctrl.IMessageHander;
import NVMP.jms.ctrl.MessageSendHandler;
import NVMP.jms.rpc.Message;

public class ServerRun implements IMessageHander, MessageSendHandler {
	private ServerImpl si;

	// 用来存储环境中所有注册的处理。
	HashMap<String, ProxyHandler> proxyHp;

	public void registerProxy(ProxyHandler obj) {
		proxyHp.put(obj.getClass().getSimpleName(), obj);
		obj.SetSendHandler(this);
	}

	public ServerRun(String name) throws JMSException {
		proxyHp = new HashMap<String, ProxyHandler>();

		si = new ServerImpl(name);
		si.AddListener(this);

	}

	@Override
	public void ReceiveMessage(Message mg, byte[] data) {
		// 根据message里面的相关信息，选择哪个注册。
		String url = mg.get_Url();
		String domainName = url.substring(0, url.indexOf("."));

		// System.out.println("domain:"+url);
		ProxyHandler rd = this.proxyHp.get(domainName);
		if (rd != null) {
			rd.ReturnEvent(mg.GetParams(), url, data);
		}
	}

	public void QueueSend(String userid, String url,
			HashMap<String, Object> hp, byte[] data) throws JMSException {

		Message m = new Message();
		m.set_Url(url);
		m.AddParams(hp);
		si.QueueSend(userid, m, data);

	}

	public void start() throws JMSException {

		si.start();

	}

	public void stop() throws JMSException {

		si.stop();
	}

	@Override
	public void TopPush(String url, HashMap<String, Object> hp, byte[] data) {
		System.out.println("很遗憾，服务器目前也放弃了这个功能。");

	}

	// @Override
	// public void ReceiveMessage(MessageTemplate mt) {
	//
	// System.out.println("id : " + mt.getReceiveId());
	// System.out.println("Type : " + mt.getMessageType());
	// System.out.println(Encoding.byteToString(mt.getbArray()));
	//
	// // TODO Auto-generated method stub
	// // 接收到请求了，分析类型
	// Message mg = new Message(Encoding.byteToString(mt.getbArray()));
	// int type = mt.getMessageType();
	// switch (type) {
	// // 客户端发送数据库操作过来
	// case MessageType.send: {
	//
	// try {
	// String aim_max = "select record from DBCOUNT";
	// java.util.List<HashMap<String, String>> l = DBConne
	// .getDBConne().executeQuery(aim_max);
	// HashMap<String, String> h = l.get(0);
	// mg.set_Version(String.valueOf(Integer.valueOf(h.get("record")) + 1));
	// mt = new MessageTemplate();
	// mt.setbArray(Encoding.StringToByte(mg.Serilize()));
	// mt.setMessageType(MessageType.push);
	//
	// String aim = "insert into VERSION (ID,operid,record) values ('"
	// + mg.get_Version() + "','" + mg.get_Operator() + "','"
	// + mg.GetParam("Sql").toString().replaceAll("'", "''")
	// + "')";
	// aim_max = "update DBCOUNT set record = record+1";
	// DBConne.getDBConne().executeUpdate(aim);
	// DBConne.getDBConne().executeUpdate(aim_max);
	//
	// // 保存完后，发送回去给各个节点。
	// TopPush(mt);
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// break;
	// }
	//
	// // 客户端请求发回去数据
	// case MessageType.request: {
	//
	// String userid = mt.getReceiveId();
	//
	// String aim = "select ID,operid,record from VERSION where ID between "
	// + mg.GetParam("start") + " and " + mg.GetParam("end");
	//
	// try {
	// java.util.List<HashMap<String, String>> l = DBConne
	// .getDBConne().executeQuery(aim);
	// for (HashMap<String, String> hp : l) {
	// mg = new Message();
	// mg.set_Operator(hp.get("operid"));
	// mg.set_Version(hp.get("ID"));
	// mg.AddParam("Sql", hp.get("record"));
	//
	// mt = new MessageTemplate();
	// mt.setReceiveId(userid);
	// mt.setMessageType(MessageType.reponse);
	// mt.setbArray(Encoding.StringToByte(mg.Serilize()));
	//
	// QueueSend(mt);
	//
	// }
	//
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// break;
	// }
	//
	// // 客户端查询最大的
	// case MessageType.verson: {
	// String aim_max = "select record from DBCOUNT";
	// java.util.List<HashMap<String, String>> l;
	// try {
	// l = DBConne.getDBConne().executeQuery(aim_max);
	// HashMap<String, String> h = l.get(0);
	// mg = new Message();
	// mg.AddParam("MaxVersion", h.get("record"));
	// mt.setMessageType(MessageType.verson);
	// mt.setbArray(Encoding.StringToByte(mg.Serilize()));
	// QueueSend(mt);
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// break;
	// }
	//
	// }
	//
	// }

}
