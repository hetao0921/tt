package NVMP.hsinit;

import java.util.HashMap;
import java.util.List;

import com.mysql.impl.Hibernate;

/**
 * 查询sessionid是否允许进入网络 查询IP是否允许进入网络
 * */
public class AllowClientDao {
	private final String QUERY_ALLOW_SESSIONID = "SELECT new Map(deviceId as deviceid) FROM NvmpRemoteclose WHERE remoteStatus =  '1' and deviceId='%s'";
//	private final String QUERY_ALLOW_SESSIONID = "SELECT 1 FROM nvmp_remoteclose WHERE remotestatus =  '1' and deviceid='%s'";

	public boolean isSession(String sessionID) {
		String aim = String.format(QUERY_ALLOW_SESSIONID, sessionID);
		List<HashMap<String, String>> list = Hibernate.getHibernate().createQuery(aim);
		if (list.size() <= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		AllowClientDao dao=new AllowClientDao();
		dao.isSession("11");
	}
}
