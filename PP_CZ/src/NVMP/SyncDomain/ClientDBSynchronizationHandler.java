package NVMP.SyncDomain;

import java.util.List;



import NVMP.jms.proxy.DBSynchronization;
import NVMP.jms.proxy.DBSynchronization.Event;
import NVMP.jms.util.Encoding;

public class ClientDBSynchronizationHandler extends Event {
	// private DBSynchronization dbs;
	private Client client; 
	private String versions;

	public String getVersions() {
		return versions;
	}

	public void setVersions(String versions) {
		this.versions = versions;
	}

	public void setDbs(DBSynchronization dbs) {
		// this.dbs = dbs;
	}

	public void setClient(Client c) {
		client = c;
	}

	/**
	 * 服务器回馈，下载的相关数据
	 * */
	@Override
	public void OnServerDownLoadOverEvent(Integer verson, String centerid,
			String uuid, byte[] data) {
		// TODO Auto-generated method stub
		// super.OnServerDownLoadOverEvent(verson, centerid, uuid, data);
		System.out.println("@@@@@@@@@@@@" + centerid);
		String xml = Encoding.byteToString(data);
		xml = xml.replace("{", "<");
		xml = xml.replace("}", ">");
		JdbcImpl jdbc = JdbcImpl.getJdbcImpl();
		Integer state = 1;
		if (xml.equals(""))
			state = 2;
		else {
			JdbcToXml jdbcToXml = new JdbcToXml();
			List<String> sqls = jdbcToXml.xmlToInsert(xml, centerid);
			if(sqls==null){
				state = 2;
			} else {
			System.out.println("数据开始插入......SQL的数目："+sqls.size());
			boolean result = jdbc.updateSqls(sqls);
			System.out.println("数据全部执行完毕,返回结果："+result);
			if (result)
				state = 0;
			else
				state = 2;
			}
		
		}

		System.out.println("开始修改data_native_record表的记录");
		if(state==0){
			jdbc.updateNativeRecord(verson, centerid, uuid, state);
			System.out.println("data_native_record表的记录修改完毕");
		}else{
			jdbc.updateNativeRecord(centerid, uuid, state);
		}
		
		
		// 如果下载完毕，就解除锁定
		if (jdbc.getNativeFlagNum(uuid) == 0) {
			jdbc.unLock(uuid);
			System.out.println("解锁完毕");
		}
	}

	/**
	 * 服务器回馈，上传的版本信息
	 * */
	@Override
	public void OnServerUpLoadOverEvent(String uuid, Integer verson, byte[] data) {

		JdbcImpl jdbc = JdbcImpl.getJdbcImpl();
		System.out.println("回馈的版本号：" + verson);
		jdbc.updateSelfSource(verson, uuid, 0);
		jdbc.updateUpState(uuid, 0);

	}

	/**
	 * 服务器回馈，当前的最新版本
	 * */

	@Override
	public void OnServerQueueSendNowVersonEvent(String versions,
			String sessionid, byte[] data) {
		// 记录最新版本，放在内存中，方便进行上传对比。
		this.versions = versions;
	}
	

}
