package fxdigital.syncserver.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fxdigital.syncserver.business.hibernate.dao.DataIncrementVersionDao;
import fxdigital.syncserver.business.hibernate.dao.DataSourceDao;
import fxdigital.util.FileUtil;
import fxdigital.util.Log4jUtil;

public class ResetLoadUtil {

	public static ResetLoadUtil resetLoadUtil = null;

	public static ResetLoadUtil getInstance() {
		if (null == resetLoadUtil) {
			resetLoadUtil = new ResetLoadUtil();
		}
		return resetLoadUtil;
	}

	// 由当前全量版本得到下一个全量版本
	public String getNextAllVersion(List<HashMap<String, String>> resetlist,
			String version) {
		String nextVersion = null;
		if (null != resetlist && resetlist.size() > 0) {
			for (int i = 0; i < resetlist.size(); i++) {
				HashMap<String, String> tempMap = resetlist.get(i);
				String tempVersion = tempMap.get("version");
				// 拿到当前全量版本的位置
				if (tempVersion.equals(version) && i != 0) {
					nextVersion = resetlist.get(i - 1).get("version");
				}
			}

		}
		return nextVersion;

	}

	public List<String> getResetIncrementVersion(String centerid, int version,
			String nextVersion) {
		List<String> list = new ArrayList<String>();
		// 如果下一版本为空
		if (null == nextVersion || ("").equals(nextVersion)) {
			list = getIncrementVersion(centerid, String.valueOf(version),
					nextVersion);
		} else {
			int intNextVersion = Integer.valueOf(nextVersion);
			for (int i = version + 1; i < intNextVersion; i++) {
				list.add(String.valueOf(i));
			}

		}
		return list;
	}

	// 得到最大的增量版本
	public String getMaxIncrementVersion(String centerid) {
		String maxIncrementVersion = null;
		List<HashMap<String, String>> list = DataIncrementVersionDao
				.getInstance().getOneVersion(centerid);
		if (null != list && list.size() > 0) {
			maxIncrementVersion = list.get(0).get("version");
		}
		return maxIncrementVersion;
	}

	public List<String> getAllIncrementVersion(String centerid,
			String maxVersion) {
		List<String> list = new ArrayList<String>();
		if (null != maxVersion) {
			for (int i = 1; i <= Integer.valueOf(maxVersion); i++) {
				list.add(String.valueOf(i));
			}
		}
		Log4jUtil.info(this.getClass(), "所有需要下载增量版本-------:" + centerid
				+ "版本 ---" + list);
		return list;
	}

	// 得到所有需要下载的增量版本
	public List<String> getIncrementVersion(String centerid, String version,
			String serverVersion) {
		Log4jUtil.info(this.getClass(), "客户端增量最大版本-----:" + centerid + "版本"
				+ version);
		List<String> list = new ArrayList<String>();
		// 把增量的最大版本也传递过去
		if (null == version || ("").equals(version)) {
			version = "0";
		}
		if (null == serverVersion || ("").equals(serverVersion)) {
			serverVersion = "0";
		}
		for (int i = Integer.valueOf(version) + 1; i <= Integer
				.valueOf(serverVersion); i++) {
			list.add(String.valueOf(i));
		}
		Log4jUtil.info(this.getClass(), "所有需要下载增量版本-------:" + centerid
				+ "版本 ---" + list);
		return list;
	}

	public List<HashMap<String, String>> getResetIncrementXml(
			List<String> incrementVersion, String centerid) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		if (null != incrementVersion && incrementVersion.size() > 0) {
			for (int i = 0; i < incrementVersion.size(); i++) {
				String localVersion = incrementVersion.get(i);
				List<HashMap<String, String>> addressList = DataSourceDao
						.getInstance().getIncrementServerSource(centerid, localVersion);
				if (null != addressList && addressList.size() > 0) {
					HashMap<String, String> map = new HashMap<String, String>();
					String centerip = addressList.get(0).get("centerip");
					String centername = addressList.get(0).get("centername");
					String fileaddress = addressList.get(0).get("fileaddress");
					Log4jUtil.info(this.getClass(), "下载增量文件地址fileaddress   "
							+ fileaddress);
					String xml = FileUtil.getInstance().readFileByLines(
							fileaddress);
					map.put("centerid", centerid);
					map.put("centerip", centerip);
					map.put("centername", centername);
					map.put("version", localVersion);
					map.put("xml", xml);
					list.add(map);
				}
			}
		}
		return list;
	}
}
