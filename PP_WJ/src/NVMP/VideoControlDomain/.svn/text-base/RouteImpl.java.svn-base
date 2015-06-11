package NVMP.VideoControlDomain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.misc.log.LogUtil;

import com.sqlite.dao.CenterInfoDao;
import com.sqlite.dao.CenterNetWorkDao;
import com.sqlite.dao.CenterRouteDao;
import com.sqlite.factory.DAOFactory;
import com.sqlite.impl.CenterInfoImpl;
import com.sqlite.impl.CenterInfoSyncImpl;
import com.sqlite.impl.CenterNetWorkImpl;
import com.sqlite.impl.CenterRouteImpl;
import com.sqlite.pojo.CenterInfo;
import com.sqlite.pojo.CenterInfoSync;
import com.sqlite.pojo.CenterNetWork;
import com.sqlite.pojo.CenterRoute;

public class RouteImpl {
	public static Set<String> centerSet= new CopyOnWriteArraySet<String>();

	private RouteImpl() {
	}

	private static RouteImpl routeImpl;

	public static RouteImpl getRouteImpl() {
		if (routeImpl == null) {
			routeImpl = new RouteImpl();
		}
		return routeImpl;
	}

	public static String[] getRouteIp(String routepath) {
		CenterInfoSyncImpl cisi = CenterInfoSyncImpl.getCenterInfoSyncImpl();
		String ips = "";
		String names = "";
		if (routepath != null && !routepath.equals("")) {
			Route route = Route.strToRoute(routepath);
			String[] path = route.getRouteDesc().split(",");
			// String [] path = routepath.split(",");
			for (String s : path) {
				CenterInfoSync center = cisi.getCenterInfoSync(s);
				ips += "," + center.getCenterIp();
				names += "," + center.getCenterName();
			}
			ips = ips.substring(1);
			names = names.substring(1);
		}
		String[] ipname = { ips, names };
		return ipname;
	}

	public Route getRoute(String sourceId, String devId) {
		// String dev = devId.substring(devId.length()-4);

		LogUtil.BusinessInfo("进入寻找路由方案");

		String destId = "";
		// if(dev.equals("@006")){
		// destId =
		// DAOFactory.getCommandDeviceIntance().getCommandDeviceByDevId(devId).getCenterID();
		// }else{
		// destId =
		// DAOFactory.getDeviceStatusIntance().getDeviceStatus(devId).getCenterID();
		// }

		destId = DAOFactory.getDeviceStatusIntance().getDeviceStatus(devId)
				.getCenterID();
		LogUtil.BusinessInfo("找路由开始：找到中心" + destId);

		CenterInfoDao centerInfoDao = new CenterInfoImpl();
		CenterInfo cen = centerInfoDao.getCenterInfoById(sourceId);
		String model = null;
		if (cen != null)
			model = cen.getRouteMode();
		Route r = null;
		if (sourceId.equals(destId)) { // 如果设备ID所对应的就是当前中心，则直接返回对象
			r = new Route();
			r.setType(2);
			r.setRouteDesc(sourceId + "," + destId);
		} else {
			// 如果通信模式是“P2P”，则直接返回，否则到中心路由信息表中去查找路由信息
			if (model != null && model.toUpperCase().equals("ROUTE")) {

				CenterRouteDao centerRouteDao = new CenterRouteImpl();

				/*
				 * 临时方案啊。由于下述方法，完全无视了路由信息表。 1、判断表中是否有数据 2、如果路由表中无数据，才改用组织架构信息。
				 */
				CenterRoute cenRoute = centerRouteDao.getCenterRoute(sourceId,
						destId);
				// 如果从中心路由信息表中查找到该路由信息，则返回此路由信息，否则到中心联网信息表中去找
				if (cenRoute != null) {
					r = new Route();
					r.setType(2);
					r.setRouteDesc(cenRoute.getRoutePath());
				}

				else {
					// 从中心联网信息表中找到路由信息
					// 1、一次获取全部中心联网信息，存储在Map中
					CenterNetWorkDao centerNetWorkDao = new CenterNetWorkImpl();

					List<CenterNetWork> list = centerNetWorkDao
							.getAllCenterNetWork();

					LogUtil.BusinessInfo("中心数据有：" + list.size());

					Map<String, List<String>> allNets = new HashMap<String, List<String>>();
					for (CenterNetWork c : list) {
						if (c.getIsParentFlag() == 1) { // 如果此中心ID存在上级，则添加到Map中
							addNetWorkInfo(c.getNetWorkNodeID(),
									c.getCenterId(), allNets);
						}
					}
					// 2、将联网信息全部存储完毕之后，递归找出一条路由信息
					List<String> listPath = new ArrayList<String>();
					getRoutePath(listPath, destId, allNets);
					// 3、从listPath中找出路由信息
					for (int i = 0; i < listPath.size(); i++) {
						String[] paths = listPath.get(i).split(",");
						if (paths[paths.length - 1].equals(sourceId)) {
							r = new Route();
							r.setType(2);
							// String []ss = listPath.get(i).split(",");
							String p = paths[paths.length - 1];
							for (int j = paths.length - 2; j >= 0; j--)
								p += "," + paths[j];
							r.setRouteDesc(p);
						}

					}
				}
			} else {
				r = new Route();
				r.setType(1);
				r.setRouteDesc(sourceId + "," + destId);
			}
		}
		r.changeCenter(centerSet);
		return r;
	}

	private boolean getRoutePath(List<String> listPath, String centerId,
			Map<String, List<String>> map) {
		boolean result = true; // 标识是否循环完毕，循环完毕则为false，否则为true
		while (result) {
			List<String> parents = map.get(centerId);
			if (parents != null && parents.size() > 0) {
				result = false;
				// 如果listPath集合中有值，则将当前中心ID对应的上级ID，追加在集合中
				if (listPath.size() > 0) {
					// 找到当前的centerId对应的一条Path
					String path = "";
					for (int i = 0; i < listPath.size(); i++) {
						String[] paths = listPath.get(i).split(",");
						if (paths[paths.length - 1].equals(centerId)) {
							path = listPath.get(i);
						}
					}
					// 将每一个上级和查询到的Path拼凑起来
					for (int i = 0; i < parents.size(); i++) {
						listPath.add(path + "," + parents.get(i));
					}
					// 递归调用自己，直到每个当前的centerId没有上级的时候，退出循环
					for (int i = 0; i < parents.size(); i++) {
						result = getRoutePath(listPath, parents.get(i), map);
					}
				} else { // 如果listPath集合中没有值
					// 将每一个上级和当前的centerId拼凑起来
					for (int i = 0; i < parents.size(); i++) {
						listPath.add(centerId + "," + parents.get(i));
					}
					// 递归调用自己，直到每个当前的centerId没有上级的时候，退出循环
					for (int i = 0; i < parents.size(); i++) {
						result = getRoutePath(listPath, parents.get(i), map);
					}
				}
			} else {
				result = false;
			}
		}
		return result;
	}

	/**
	 * 一次获取全部中心联网信息，存储在Map中
	 * 
	 * @param sourceId
	 * @param destId
	 * @param map
	 */
	private void addNetWorkInfo(String sourceId, String destId,
			Map<String, List<String>> map) {
		boolean result = false; // 记录当前的中心ID是否存在于集合中,true为存在，反之亦然
		Set<String> set = map.keySet();
		Iterator<String> iter = set.iterator();
		while (iter.hasNext()) {
			String k = (String) iter.next();
			// System.out.println(k+"============"+map.get(k));
			if (k.equals(destId)) {
				result = true;
			}

		}
		if (result) // 如果当前中心ID存在，则在它的上级列表中，添加一个
			map.get(destId).add(sourceId);
		else { // 如果当前中心ID不存在，则将当前中心ID存入Map中
			List<String> l = new ArrayList<String>();
			l.add(sourceId);
			map.put(destId, l);
		}
	}

	// 设置当前路由被本中心取代的路由
	public void changeCenter(String otherCenterID, boolean flag) {
		if (flag) {
			centerSet.add(otherCenterID);
		} else {
			centerSet.remove(otherCenterID);
		}

	}

}
