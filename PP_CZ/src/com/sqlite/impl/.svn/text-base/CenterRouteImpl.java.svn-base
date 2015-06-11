package com.sqlite.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.misc.log.LogUtil;

import com.sqlite.conn.DBConne;
import com.sqlite.dao.CenterNetWorkDao;
import com.sqlite.dao.CenterRouteDao;
import com.sqlite.pojo.CenterNetWork;
import com.sqlite.pojo.CenterRoute;

public class CenterRouteImpl implements CenterRouteDao {
	DBConne dbc = DBConne.getDbConne();
 
	
	private List<String> GetVideoPath(String FromCenterId,String DestCenterId)
    {
		CenterNetWorkDao netDao = new CenterNetWorkImpl() ;
		List<CenterNetWork> list = netDao.getAllCenterNetWork();
		
        List<String> path1 = new LinkedList<String>();
        path1.add(FromCenterId);

        List<String> path2 = new LinkedList<String>();
        path2.add(DestCenterId);
       
        if (FromCenterId.equals(DestCenterId))
        {
            return path1;
        }

       
        String ParentId1 = FromCenterId;
        String ParentId2 = DestCenterId;
      

        for (int i = 0; i < 16; i++)
        {
        	for(CenterNetWork cw:list)
            {
                if (cw.getCenterId().equals(ParentId1))
                {
                    path1.add(cw.getNetWorkNodeID());
                    ParentId1 = cw.getNetWorkNodeID();
                    if (DestCenterId.equals(ParentId1))
                    {
                        return path1;
                    }
                }

                if (cw.getCenterId().equals(ParentId2))
                {
                    path2.add(cw.getNetWorkNodeID());
                    ParentId2 = cw.getNetWorkNodeID();
                    if (FromCenterId.equals(ParentId2))
                    {
                    	//hsj  该地方如直接反馈，那么需要调整循序，取消下面代码，后面已经反序聊
                       // return path2;
                    }
                }
            }
        }

        
        int n = 0;
        Boolean bFlag = false;

        List<String> path3 = new LinkedList<String>();
        for (int m = 0; m < path1.size(); m++)
        {
        	String s1 = path1.get(m);
            path3.add(s1);
            for (n = 0; n < path2.size(); n++)
            {
            	String s2 = path2.get(n);
                if (s1.equals(s2))
                {
                    bFlag = true;
                    break;
                }
            }
            if (bFlag) break;
        }
        if (bFlag)
        {
            for (int i = n -1; i >= 0; i--)
            {
            	String s2 = path2.get(i);
                 path3.add(s2);
            }
            return path3;
        }
        else
        {
            path1.add(DestCenterId);
            return path1;
        }
    }

    private String GetVideoPathUrl(String FromCenterId,String DestCenterId)
    {
        List<String> path;
        path = GetVideoPath(FromCenterId,DestCenterId);
        String s = "";
        for (int i = 0; i < path.size(); i++)
        {
        	String s2 = path.get(i);
            s = s + s2;
            if (i != path.size() -1)
            {
                s = s + ",";
            }
        }
                    
        return s;
    }

	@Override
	public CenterRoute getCenterRoute(String sourceId, String destId) {
		// TODO Auto-generated method stub
		
		LogUtil.BusinessInfo("===aaaaaaa=====");
		CenterRoute route = null;
		if (sourceId.equals(destId)) {
			route = new CenterRoute();
			route.setDestCenterID(destId);
			route.setSourceCenterID(sourceId);
			route.setRoutePath(sourceId + "," + destId);
		} else {
			//也就是说，这里少一个根据路由信息表来找路由的方式。
			String routeP = getRouteById(sourceId, destId);
			
			if(routeP!=null){
				route = new CenterRoute();
				route.setDestCenterID(destId);
				route.setSourceCenterID(sourceId);
				route.setRoutePath(routeP);
			}else{
				LogUtil.BusinessInfo("===bbbbbbb=====");
			    //这里被改造成为了读取组织架构来获取路由信息
			    String path = this.GetVideoPathUrl(sourceId, destId);
			    route = new CenterRoute();
				route.setDestCenterID(destId);
				route.setSourceCenterID(sourceId);
				route.setRoutePath(path);
			}
		}

		return route;
	}
	
//	@Override
//	public CenterRoute getCenterRoute(String sourceId, String destId) {
//		// TODO Auto-generated method stub
//		CenterRoute route = null;
//		if (sourceId.equals(destId)) {
//			route = new CenterRoute();
//			route.setDestCenterID(destId);
//			route.setSourceCenterID(sourceId);
//			route.setRoutePath(sourceId + "," + destId);
//		} else {
//			String sql = String.format(
//					"select * from CenterRouteInfoTab where SourceCenterID=%s"
//							+ " and DestCenterID=%s", changeObj(sourceId),
//					changeObj(destId));
//			Connection con = dbc.getConn();
//
//			Statement stat = null;
//			ResultSet rst = null;
//			try {
//				stat = con.createStatement();
//				rst = stat.executeQuery(sql);
//				if (rst.next()) {
//					int id = rst.getInt("ID");
//					String SourceCenterID = rst.getString("SourceCenterID");
//					String DestCenterID = rst.getString("DestCenterID");
//					String RoutePath = rst.getString("RoutePath");
//					route = new CenterRoute(id, SourceCenterID, DestCenterID,
//							RoutePath);
//				} else {
//					sql = String.format(
//							"select * from CenterRouteInfoTab where SourceCenterID=%s"
//									+ " and DestCenterID=%s",
//							changeObj(destId), changeObj(sourceId));
//					rst = stat.executeQuery(sql);
//					if (rst.next()) {
//						int id = rst.getInt("ID");
//						String SourceCenterID = rst.getString("SourceCenterID");
//						String DestCenterID = rst.getString("DestCenterID");
//						String RoutePath = rst.getString("RoutePath");
//						String[] paths = RoutePath.split(",");
//						String path = paths[paths.length - 1];
//						for (int i = paths.length - 2; i >= 0; i--) {
//							path += "," + paths[i];
//						}
//						route = new CenterRoute(id, SourceCenterID,
//								DestCenterID, path);
//					}
//				}
//				rst.close();
//				stat.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//		return route;
//	}

	/**
	 * 转化字符
	 * 
	 * @param a
	 * @return
	 */
	public Object changeObj(Object a) {
		if (a == null) {
			return null;
		} else {
			if (a.getClass().getSimpleName().equals("String")) {
				return "'" + a + "'";
			} else {
				return a;
			}
		}
	}
	
	/**
	 * 根据起始ID和结束ID，查询路由信息
	 * @param sourceId
	 * @param destId
	 * @return
	 */
	public String getRouteById(String sourceId,String destId){
		String path = null;
		String sql = String.format(
				"select * from CenterRouteInfoTab where SourceCenterID=%s and DestCenterID=%s",
				changeObj(sourceId),changeObj(destId));
//		LogUtil.BusinessInfo("===aaaaaaa====="+sql);
		Connection con = dbc.getConn();
		Statement stat = null;
		ResultSet rst = null;
		try {
			stat = con.createStatement();
			rst = stat.executeQuery(sql);

			if(rst.next()){
				path = rst.getString("RoutePath");
			}
			rst.close();
			stat.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;
	}

	// 得到目标和目标所对应的所有下级
	public List<String> getALLDesc(String sourceId) {
		// TODO Auto-generated method stub
		List<String> list = new LinkedList<String>();
		list.add(sourceId);

		String sql = String.format(
				"select * from CenterRouteInfoTab where SourceCenterID=%s",
				changeObj(sourceId));
		Connection con = dbc.getConn();
		Statement stat = null;
		ResultSet rst = null;
		try {
			stat = con.createStatement();
			rst = stat.executeQuery(sql);

			while (rst.next()) {
//				int id = rst.getInt("ID"); 
				// String SourceCenterID = rst.getString("SourceCenterID");
				String DestCenterID = rst.getString("DestCenterID");
				list.add(DestCenterID);
			}
			rst.close();
			stat.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public boolean addCenterRoute(CenterRoute center) {
		// TODO Auto-generated method stub
		boolean result = false;
		String sql = String.format("insert into CenterRouteInfoTab(ID,"
				+ "SourceCenterID,DestCenterID,RoutePath) values(%s,%s,%s,%s)",
				changeObj(center.getId()),
				changeObj(center.getSourceCenterID()),
				changeObj(center.getDestCenterID()),
				changeObj(center.getRoutePath()));
		Connection con = dbc.getConn();
		Statement stat = null;
		try {
			stat = con.createStatement();
			result = stat.execute(sql);
			stat.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<CenterRoute> getAllCenterRoute() {
		// TODO Auto-generated method stub
		String sql = "select * from CenterRouteInfoTab";
		List<CenterRoute> list = new ArrayList<CenterRoute>();
		Connection con = dbc.getConn();
		Statement stat = null;
		ResultSet rst = null;
		try {
			stat = con.createStatement();
			rst = stat.executeQuery(sql);
			while (rst.next()) {
				int id = rst.getInt("ID");
				String SourceCenterID = rst.getString("SourceCenterID");
				String DestCenterID = rst.getString("DestCenterID");
				String RoutePath = rst.getString("RoutePath");
				CenterRoute route = new CenterRoute(id, SourceCenterID,
						DestCenterID, RoutePath);
				list.add(route);
			}
			rst.close();
			stat.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
