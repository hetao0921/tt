package NVMP.VideoControlDomain;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.misc.log.LogUtil;

/**
 * 本类是专门为处理视频点播前置操作的。
 * */
public class PreVideoTool {

	private String preVideoServerID = null;
	private Set<PreVideo> preVideoSet;
	private String fatherServerID = null;

	public PreVideoTool() {
		preVideoSet = new HashSet<PreVideo>();
		fatherServerID= DBConnNvmp.getDBConn().getFatherServerID();
	}



	public void setPreServerID(String serverID) {
		preVideoServerID = serverID;
	}

	public String getPreVideoServerID() {
		return preVideoServerID;
	}

	/**
	 * 根据路由判断，是否要进行前置操作。
	 * */
	public boolean isPreVideoProcess(Route centerRoute, String centerID,
			String deviceID) {
		boolean b = false;
//		if (!deviceID.contains("@006")) {
//			centerRoute.setLocalId(centerID);
//			if (centerRoute.getLastRoute().equals(this.fatherServerID)) {
//				b = true;
//			}
//		}
		return b;
	}

	// 保存点播请求
	public void saveStartPlay(String ClientUserId, String DeviceId,
			Integer Index, String Context, Integer NetLinkMode,
			Integer userLev, String CenterMap, String OCenterid, String clientIP) {

		PreVideo pv = new PreVideo(ClientUserId, DeviceId, Index, Context,
				NetLinkMode, userLev, CenterMap, OCenterid, clientIP);
		preVideoSet.add(pv);

	}

	public PreVideo getPreVideoByContext(String context) {
		PreVideo pv = null;
		// System.out.println("==="+context);
		for (PreVideo p : preVideoSet) {
			// System.out.println("="+p.getContext());
			if (p.getContext().equals(context)) {
				pv = p;
				break;
			}

		}
		return pv;
	}

	// 移除已存在的前置点播
	public void removePreVideo(PreVideo pv) {
		preVideoSet.remove(pv);
	}

	// 得到相关客户的点播
	public List<PreVideo> getPreVideosByClient(String clientID) {
		List<PreVideo> list = new LinkedList<PreVideo>();
		for (PreVideo pv : preVideoSet) {
			if (pv.getClientUserId().equals(clientID)) {
				list.add(pv);
			}

		}
		return list;
	}

	// 得到相关设备的点播
	public List<PreVideo> getPreVideosByDevice(String deviceID) {
		List<PreVideo> list = new LinkedList<PreVideo>();
		for (PreVideo pv : preVideoSet) {
			if (pv.getDeviceID().equals(deviceID)) {
				list.add(pv);
			}
		}
		return list;
	}

	// 打印所有当前的点播
	public List<PreVideo> all() {
		List<PreVideo> list = new LinkedList<PreVideo>();
		LogUtil.TestInfo("======PreVideoSet========");
		for (PreVideo pv : preVideoSet) {
			list.add(pv);
			LogUtil.TestInfo(pv.getClientUserId() + "  " + pv.getDeviceID()
					+ "  " + pv.getChannel() + " context" + pv.getContext());
		}
		return list;
	}

}
