package NVMP.ClientValidateDomain;

import java.util.HashMap;
import java.util.List;

import com.fxdigital.bean.NvmpServerClients;
import com.mysql.impl.Hibernate;

/**
 * 验证信息保存和处理类
 * 1、记录各种验证信息，保存入数据库
 * 2、初始化，清空数据库，判断表是否存在，并进行处理
 * */
public class ValidateDao {
/*	private final String CLEAR_TABLE = "delete from nvmp_server_clients";
	private final String QUERY_ALL_TABLE = "select *  from nvmp_server_clients";
	private final String QUERY_LIMITS_TABLE = "select data from nvmp_serverinfo where attribute = 'clientnums'";
	private final String INSETINTO_TABLE = "insert into nvmp_server_clients (clientID,clientIP) values ('%s','%s')";
	private final String DELETE_TABLE = "delete from nvmp_server_clients where clientID = '%s'";
	private final String QUERY_SINGLE_TABLE = "select * from nvmp_server_clients where clientID = '%s'";*/
	private final String CLEAR_TABLE = "delete from NvmpServerClients";
	private final String QUERY_ALL_TABLE = "select new Map(clientId as clientid ,clientIp as clientip)  from NvmpServerClients";
	private final String QUERY_LIMITS_TABLE = "select new Map(data as data) from NvmpServerinfo  where attribute='clientnums' ";
	private final String INSETINTO_TABLE = "insert into nvmp_server_clients (clientID,clientIP) values ('%s','%s')";
	private final String DELETE_TABLE = "delete from NvmpServerClients where clientID = '%s'";
	private final String QUERY_SINGLE_TABLE = "select new Map(clientId as clientid ,clientIp as clientip)  from NvmpServerClients where clientId = '%s'";

	public void init() {
		String hql="delete NvmpServerClients ";
		Hibernate hibernate =Hibernate.getHibernate();
		hibernate.deleteOrUpdate(hql, null);
//		MySqlManager.getInstance().updateInfo(CLEAR_TABLE);
	}
	

	
	private int limits(){
		int limits  = -1;
		String hql="select new Map(attribute as attribute ,data as data) from NvmpServerinfo  where attribute='clientnums' ";
		Hibernate hibernate =Hibernate.getHibernate();
		List<HashMap<String, String>>  list =hibernate.createQuery(hql);
		if(list.size() == 1) {
			try {
				limits = Integer.parseInt(list.get(0).get("data"));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return limits;
	} 
	
	
	
	private  void insert(String clientID,String clientIP){
		NvmpServerClients client=new NvmpServerClients();
		client.setClientId(clientID);
		client.setClientIp(clientIP);
		Hibernate hibernate =Hibernate.getHibernate();
		hibernate.save(client);
		/*
		String aim = String.format(INSETINTO_TABLE, clientID,clientIP);
		MySqlManager.getInstance().updateInfo(aim);
*/
		
	}
	

	
	/**
	 * 验证客户端连接数量
	 * */
	public boolean clientValidate(String clientID,String clientIP){
		boolean b=false;
		String aim = String.format(QUERY_SINGLE_TABLE, clientID);
		Hibernate hibernate =Hibernate.getHibernate();
		List<HashMap<String, String>>  sigle=hibernate.createQuery(aim);
		if(!sigle.isEmpty()) {
			return true;
		}
		int n = limits();
		if(n>-1){
			List<HashMap<String, String>>  list = hibernate.createQuery(QUERY_ALL_TABLE);
			if(list.size()<n) {
				//插入记录
				insert(clientID,clientIP);
				b = true;
			} 		
		} else {
			b = true;
		}
		return b;
		/*
		boolean b = false;
		String aim = String.format(QUERY_SINGLE_TABLE, clientID);
		List<HashMap<String, String>>  sigle = MySqlManager.getInstance().executeQuery(aim);
		if(!sigle.isEmpty()) {
			return true;
		}
		int n = limits();
		if(n>-1){
			List<HashMap<String, String>>  list = MySqlManager.getInstance().executeQuery(QUERY_ALL_TABLE);
			if(list.size()<n) {
				//插入记录
				insert(clientID,clientIP);
				b = true;
			} 		
		} else {
			b = true;
		}
		return b;
	*/}
	
	/**
	 * 客户端退出操作
	 * */
	public void clientQuit(String clientID){
		
		String aim = String.format(DELETE_TABLE, clientID);
		Hibernate hibernate =Hibernate.getHibernate();
		hibernate.deleteOrUpdate(aim, null);
	
		/*
		String aim = String.format(DELETE_TABLE, clientID);
		MySqlManager.getInstance().updateInfo(aim);
		 */
		}
	
	public static void main(String[] args) {
		ValidateDao dao=new ValidateDao();
		dao.clientQuit("heh");;
	}
	
}
