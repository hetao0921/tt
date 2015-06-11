package NVMP.SyncDomain;

import javax.jms.JMSException;

import NVMP.jms.impl.ClientRun;
import NVMP.jms.proxy.DBSynchronization;
import NVMP.jms.util.Encoding;
import NVMP.jms.util.JNDIUtil;

public class Client {
	private String ClientId; 
	private ClientRun cr;
	private DBSynchronization dbs;
	private boolean activeflag;
	public SyncBusiness business;
	public ClientDBSynchronizationHandler cdh;

	private boolean initflag;

	private String versions;

	public String getVersions() {
		return versions;
	}

	public void setVersions(String versions) {
		this.versions = versions;
	}

	public DBSynchronization getDbs() {
		return dbs;
	}

	public String getClientId() {
		return ClientId;

	}

	public Client(String sessionid) {
		this.ClientId = sessionid;
		activeflag = false;
		initflag = false;
		
		//这里顺手初始化一下
		JdbcImpl.getJdbcImpl().startInit();

		
	}

	public void init() throws JMSException {

		dbs = new DBSynchronization();
		cdh = new ClientDBSynchronizationHandler();
		dbs.setE(cdh);
		cdh.setDbs(dbs);
		cr = new ClientRun(ClientId);
		cr.registerProxy(dbs);
		activeflag = true;
	}

	public void connect() throws Exception {
		if (!activeflag) {
			JNDIUtil.again();
			init();
		}
		if (!activeflag)
			throw new Exception("error");
	}

	// 上传数据
	public boolean upLoad(String sessionid, String ip, String uuid, String xml) {

		try {
			connect();

			dbs.ClientQueueSend(sessionid, this.ClientId, ip, uuid,
					Encoding.StringToByte(xml));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			activeflag = false;
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// 下载数据
	public boolean downLoad(String sessionid, String ip, String uuid,
			String versions) {
		try {
			connect();
			dbs.ClientQueueGetData(sessionid, this.ClientId, ip, uuid,
					versions, null);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			activeflag = false;
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// 请求查看版本
	public boolean show(String sessionid) {
		try {
			System.out.println("进行查看");
			connect();
			dbs.ClientQueueNowVerson(sessionid, this.ClientId, null);
			System.out.println("查看已经发出");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.cdh.setVersions(null);
			activeflag = false;
			e.printStackTrace();
			return false;
		}
		return true;

	}

	static public void main(String[] args) {
		Client c = new Client("center154@001");
		c.show("center154@001");
	}

}
