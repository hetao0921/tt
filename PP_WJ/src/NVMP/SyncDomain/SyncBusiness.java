package NVMP.SyncDomain;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;

import corenet.exchange.Encoding;
import NVMP.AppService.Remoting;

public class SyncBusiness {
	public ISyncEvent syncEvent;
	private Client client;

	public SyncBusiness() {
		client = new Client(SyncDomain.AppRunTime().getServerId());
		System.out.println("||" + SyncDomain.AppRunTime().getServerId());
	}

	/**
	 * 获得当前的上传情况，如果处于上传，则显示那个IP调用的上传，其SessionId为多少。
	 *  
	 * */
	@Remoting
	public void getUpLoadState(String Sessionid) {
		// System.out.println("????????"+Sessionid);
		// DBConn db = DBConn.getDBConn();
		// String[] ss =
		// db.executeQuery("select * from data_operate where operate = '上传'").get(0);
		JdbcImpl jdbc = JdbcImpl.getJdbcImpl();
		String[] ss = jdbc.getUpMsg();
		int flag = -1;
		if (ss == null) {
			flag = 0;
		} else {
			String fla = ss[2];
			// TODO 当前状态，从数据库中读出
			flag = Integer.parseInt(fla);
		}
		String type = "上传";
		if (flag == 0) {
			(SyncDomain.AppRunTime()).SetCurChannel(Encoding
					.AdsToPointProtocol(Sessionid));
			syncEvent.onGetStateEvent(type, flag, null, null);

		} else {
			// TODO 查询当前锁定的人和来源IP
			// ss =
			// db.executeQuery("select * from data_operate_record where uuid = '"+ss[3]+"'").get(0);
			ss = jdbc.getUpRecord(ss[3]);
			String userid = ss[2];
			String ip = ss[3];

			(SyncDomain.AppRunTime()).SetCurChannel(Encoding
					.AdsToPointProtocol(Sessionid));
			syncEvent.onGetStateEvent(type, flag, userid, ip);
		}

	}

	/**
	 * 获得当前的同步下载情况，如果处于下载，显示该下载是何IP调用，已经sessionid等其它情况。
	 * */
	@Remoting
	public void getDownLoadState(String Sessionid) {
		// TODO 当前状态，从数据库中读出
		// DBConn db = DBConn.getDBConn();
		// String[] ss =
		// db.executeQuery("select * from data_operate where operate = '下载'").get(0);
		JdbcImpl jdbc = JdbcImpl.getJdbcImpl();
		String[] ss = jdbc.getLoadMsg();
		int flag = -1;
		if (ss == null) {
			flag = 0;
		} else {
			String fla = ss[2];
			flag = Integer.parseInt(fla);
		}
		String type = "下载";
		if (flag == 0) {

			(SyncDomain.AppRunTime()).SetCurChannel(Encoding
					.AdsToPointProtocol(Sessionid));
			syncEvent.onGetStateEvent(type, flag, null, null);

		} else {
			// TODO 查询当前锁定的人和来源IP
			ss = jdbc.getLoadRecord(ss[3]);
			String userid = ss[2];
			String ip = ss[3];
			// String userid = "";
			// String ip = "";

			(SyncDomain.AppRunTime()).SetCurChannel(Encoding
					.AdsToPointProtocol(Sessionid));
			syncEvent.onGetStateEvent(type, flag, userid, ip);
		}
	}

	/**
	 * 获得本机的数据版本情况
	 * 
	 * */
	@Remoting
	public void getSelfData(String Sessionid) {
		// TODO 在这里读取数据库中关于本机的数据。
		// DBConn db = DBConn.getDBConn();
		// String[] ss =
		// db.executeQuery("select * from data_self_source").get(0);
		JdbcImpl jdbc = JdbcImpl.getJdbcImpl();
		String[] ss = jdbc.getSelfSource();
		int version = 0;
		String uploadDate = "";
		int flag = 0;
		if (ss != null) {
			if (ss.length > 0) {
				version = Integer.parseInt(ss[2]);
				uploadDate = ss[3];
				flag = Integer.parseInt(ss[5]);
			}
		}

		(SyncDomain.AppRunTime()).SetCurChannel(Encoding
				.AdsToPointProtocol(Sessionid));
		syncEvent.onGetSelfDataEvent(version, uploadDate, flag);
	}

	/**
	 * 获得本机的同步信息情况 {centerid:version:uploadDate:flag}
	 * */
	@Remoting
	public void getNativeData(String Sessionid) {
		// TODO 在这里读取数据库中关于本机的数据。
		JdbcImpl jdbc = JdbcImpl.getJdbcImpl();
		List<String[]> list = jdbc.getNativeSource();
		String versions = "";
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				String[] s = list.get(i);
				versions += "{" + s[1] + ":" + s[2] + ":" + s[3] + ":" + s[7]
						+ "}";
			}
		}

		// String versions =
		// "{centerid:version:uploadDate:flag}{centerid:version:uploadDate:flag}";

		(SyncDomain.AppRunTime()).SetCurChannel(Encoding
				.AdsToPointProtocol(Sessionid));
		syncEvent.onGetNativeDataEvent(versions);
	}

	/**
	 * 获得服务器上，最新的同步信息。
	 * 
	 * */
	@Remoting
	public void getServerDataVersion(String Sessionid) {
		// TODO 在这里去找服务器要最新的同步信息。模拟，待会进行修改。

		boolean b = this.client.show(Sessionid);
		if (!b) {
			(SyncDomain.AppRunTime()).SetCurChannel(Encoding
					.AdsToPointProtocol(Sessionid));
			String operate = "查看最新版本";
			String reason = "连接JMS服务器失败";
			syncEvent.onFailEvent(operate, reason);
			return;
		}

		String str = this.client.cdh.getVersions();
		if (str == null) {
			(SyncDomain.AppRunTime()).SetCurChannel(Encoding
					.AdsToPointProtocol(Sessionid));
			String operate = "查看最新版本";
			String reason = "稍后再查看";
			syncEvent.onFailEvent(operate, reason);
			return;
		} else {

			(SyncDomain.AppRunTime()).SetCurChannel(Encoding
					.AdsToPointProtocol(Sessionid));
			syncEvent.onGetServerDataEvent(str);

		}

	}

	/**
	 * 清除本机的一切同步信息
	 * */
	@Remoting
	public void clearNativeData(String Sessionid) {
		// DBConn db = DBConn.getDBConn();
		// String []ss =
		// db.executeQuery("select * from data_operate where operate = '下载'").get(0);
		JdbcImpl jdbc = JdbcImpl.getJdbcImpl();
		String[] ss = jdbc.getLoadMsg();
		if (Integer.parseInt(ss[2]) == 1) {
			(SyncDomain.AppRunTime()).SetCurChannel(Encoding
					.AdsToPointProtocol(Sessionid));
			String operate = "下载";
			String reason = "清除数据失败。有人正在下载";
			syncEvent.onFailEvent(operate, reason);
			return;
		}
		jdbc.clearSyncData();
	}

	/**
	 * 
	 * 上传
	 * */
	@Remoting
	public void syncUpLoad(String Sessionid, String userid) {
		// 先判断是否能够获得当前的锁定
		boolean result = true;
		JdbcImpl jdbc = JdbcImpl.getJdbcImpl();
		String[] ss = jdbc.getUpMsg();
		// DBConn db = DBConn.getDBConn();
		// String[] ss =
		// db.executeQuery("select * from data_operate where operate = '上传'").get(0);
		if (ss != null) {
			if (Integer.parseInt(ss[2]) == 1) {
				(SyncDomain.AppRunTime()).SetCurChannel(Encoding
						.AdsToPointProtocol(Sessionid));
				String operate = "上传";
				String reason = "获取上传的权限失败。";
				syncEvent.onFailEvent(operate, reason);
				result = false;
				return;
			}
		}

		// //如果不能，返回失败，和失败原因
		// (SyncDomain.AppRunTime()).SetCurChannel(Encoding
		// .AdsToPointProtocol(Sessionid));
		// String operate = null;
		// String reason = "获取上传的权限失败。";
		// syncEvent.onFailEvent(operate,reason);

		/**
		 * 如果可以，开始调用jms的上传方法。
		 * 
		 * */
		if (result) {
			// 修改状态
			String uuid = Encoding.getUuid();

			boolean re = jdbc.updateUpState(uuid, 1);
			if (!re) {
				(SyncDomain.AppRunTime()).SetCurChannel(Encoding
						.AdsToPointProtocol(Sessionid));
				String operate = "上传";
				String reason = "获取上传的权限失败。";
				syncEvent.onFailEvent(operate, reason);
				return;
			}

			jdbc.insertUpRecord(uuid, userid);
			String[] vers = jdbc.getSelfSource();
			Integer version = -1;
			if (vers == null)
				version = 0;
			else
				version = Integer.parseInt(vers[2]);
			System.out.println("我们来这里了嘛？版本是多少：" + version);
			jdbc.updateSelfSource(version, uuid, 1);
			JdbcToXml jd = new JdbcToXml();
			String xml = "";
			try {
				xml = jd.getXml(client.getClientId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 调用JMS的上传方法
			// System.out.println(xml);

			boolean b = this.client.upLoad(Sessionid, "", uuid, xml);
			System.out.println(b);

			if (!b) {
				jdbc.updateUpState(uuid, 0);

				(SyncDomain.AppRunTime()).SetCurChannel(Encoding
						.AdsToPointProtocol(Sessionid));
				String operate = "下载";
				String reason = "JMS服务连接失败。";
				syncEvent.onFailEvent(operate, reason);
				return;
			}

		}

	}

	/**
	 * 
	 * 同步下载,versions的格式基本是 {中心ID:版本}{}
	 * */
	@Remoting
	public void syncDownLoad(String Sessionid, String userid) {
		// 先判断是否能够获得当前的锁定
		JdbcImpl jdbc = JdbcImpl.getJdbcImpl();
		String[] ss = jdbc.getLoadMsg();
		Integer flag = -1;
		if (ss == null) {
			flag = 0;
		} else {
			flag = Integer.parseInt(ss[2]);
		}
		// DBConn db = DBConn.getDBConn();
		// String[]ss =
		// db.executeQuery("select * from data_operate where operate = '下载'").get(0);
		if (flag == 1) {
			(SyncDomain.AppRunTime()).SetCurChannel(Encoding
					.AdsToPointProtocol(Sessionid));
			String operate = "下载";
			String reason = "获取下载的权限失败。";
			syncEvent.onFailEvent(operate, reason);

			return;
		} else {
			// 修改状态
			String uuid = Encoding.getUuid();
			boolean res = jdbc.updateLoadState(uuid, 1);
			if (!res) {
				(SyncDomain.AppRunTime()).SetCurChannel(Encoding
						.AdsToPointProtocol(Sessionid));
				String operate = "下载";
				String reason = "获取下载的权限失败。";
				syncEvent.onFailEvent(operate, reason);
				return;
			}
			jdbc.insertLoadRecord(uuid, userid);

			/**
			 * 如果可以，开始调用jms的下载方法。
			 * 
			 * */
			String version = "";
			String nowV = this.client.cdh.getVersions();
			if (nowV == null||nowV.equals("")) {
				// 表明无最新的下载版本信息。
				(SyncDomain.AppRunTime()).SetCurChannel(Encoding
						.AdsToPointProtocol(Sessionid));
				String operate = "下载";
				String reason = "获得不了最新的版本信息。";
				if(nowV!=null){
					reason = "当前版本已经是最新";
				}
				jdbc.updateLoadState(uuid, 0);

				syncEvent.onFailEvent(operate, reason);
				return;
			} else {
				List<Map<String, String>> list = jdbc.getAllNativeSource();
				System.out.println("=====!!!!!!!!" + nowV);
				String[] ver = nowV.split("}");
				for (int i = 0; i < ver.length; i++) {

					String[] cenver = ver[i].substring(1).split(":");
					if (!cenver[0].equals(this.client.getClientId())) {
						int j = 0;
						for (; j < list.size(); j++) {
							if (list.get(j).get("centerid").equals(cenver[0])
									&& list.get(j).get("version")
											.equals(cenver[1])) {
								break;

							}
						}
						if (j == list.size()) {
							version += "{" + cenver[0] + ":" + cenver[1] + "}";
							jdbc.updateNativeSource(cenver[0],
									Integer.parseInt(cenver[1]), uuid);
						}

					}

				}

			}
			if (version.equals("")) {

				(SyncDomain.AppRunTime()).SetCurChannel(Encoding
						.AdsToPointProtocol(Sessionid));
				String operate = "下载";
				String reason = "已经是最新版本了。";
				syncEvent.onFailEvent(operate, reason);
				jdbc.updateLoadState(uuid, 0);
				return;
			}

			// jdbc.updateSelfSource(Integer.parseInt(version), uuid, 1);

			boolean re = client.downLoad(Sessionid, "", uuid, version);
			if (!re) {
				jdbc.updateLoadState(uuid, 0);
				(SyncDomain.AppRunTime()).SetCurChannel(Encoding
						.AdsToPointProtocol(Sessionid));
				String operate = "下载";
				String reason = "JMS服务连接失败。";
				syncEvent.onFailEvent(operate, reason);
				return;
			}
		}
	}

}
