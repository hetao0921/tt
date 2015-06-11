package corenet.exchange.link;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.misc.log.LogUtil;

/**
 * 模拟服务器连接关系
 * 
 * @author <h1>Hoocoln<h1>
 * @version 2013-9-23
 */
public class ServerLink {

	private CenterRoute centerRoute;
	private String centerID;

	private static class ServerLinkInstance {
		private static ServerLink INSTANCE = new ServerLink();
	}

	private ServerLink() {
		centerRoute = new CenterRoute();
	}

	// 获得单例
	public static ServerLink getInstance() {
		return ServerLinkInstance.INSTANCE;
	}

	/**
	 * 导入节点的物理链接关系
	 * 
	 * @param serverId1
	 *            节点1
	 * @param serverId2
	 *            节点2
	 * @return Boolean 导入成功则返回true，反之返回false
	 */
	public void insert(String sonNode, String parentNode) {
		centerRoute.insert(sonNode, parentNode);
	}

	public void createLinkMap(String centerId) {
		this.centerID = centerId;
		centerRoute.count(centerID);
	}

	public void init() {

	}

	public List<String> getOfflineList(String loseNode) {
		return centerRoute.getLoseCenter(this.centerID, loseNode);
	}

	/**
	 * 获得从开始节点到目标节点target的路径上的下一个节点
	 * 
	 * @param target
	 *            目标节点
	 * @return 路径上的下一个节点
	 */
	public String getNextDot(String target) {

		return centerRoute.getDirectRoute(this.centerID, target);
	}

	public boolean isLink(String targetID, String sonID){
		return centerRoute.isNeighbour(targetID, sonID);
	}
	
	// private List<Dot> dots = new ArrayList<Dot>();
	// private BreathFirstSearch bfs;
	//
	// /**
	// * 初始化，节点关系清空
	// */
	// public void init(){
	// synchronized(this){
	// dots.clear();
	// }
	// }
	//
	// /**
	// * 生成路径表
	// * @param centerId 以此节点为起始节点构造路径表
	// * @return Boolean 构造成功则返回true，反之返回false
	// */
	// public boolean createLinkMap(String centerId){
	// Map<String,String[]> trace_temp_Map = new HashMap<String,String[]>();
	// Dot center = new Dot(centerId);
	// if(centerId == null || "".equals(centerId)){
	// return false;
	// }
	// synchronized(this){
	// if(dots != null && dots.size() > 0 && !dots.contains(center)){
	// return false;
	// }
	// trace_temp_Map.clear();
	// String[] selfTrace = {centerId};
	// trace_temp_Map.put(centerId, selfTrace);
	// if(dots == null || dots.size() == 0){
	// return false;
	// }
	// for(Dot dot:dots){
	// if(center.equals(dot)){
	// center = dot;
	// break;
	// }
	// }
	// bfs = new BreathFirstSearch(dots);
	// bfs.rebuild(center);
	// return true;
	// }
	// }
	//
	// /**
	// * 获得从开始节点到目标节点target的路径
	// * @param target 目标节点
	// * @return String[] 无法获得路径则返回NULL
	// */
	// public String[] getTrace(String target){
	// if(target == null || "".equals(target)){
	// return null;
	// }
	// synchronized(this){
	// Dot[] trace = bfs.getTrace(new Dot(target));
	// if(trace == null || trace.length == 0){
	// return null;
	// }
	// String[] trace_s = new String[trace.length];
	// for(int i=0;i<trace.length;i++){
	// trace_s[i] = trace[i].getId();
	// }
	// return trace_s;
	// }
	// }
	//
	// /**
	// * 获得从开始节点到目标节点target的路径上的下一个节点
	// * @param target 目标节点
	// * @return 路径上的下一个节点
	// */
	// public String getNextDot(String target){
	// try {
	// String[] trace = getTrace(target);
	// if(trace.length < 2){
	// return null;
	// }else{
	// return trace[1];
	// }
	// } catch (Exception e) {
	// return null;
	// }
	// }
	//
	// /**
	// * 导入节点的物理链接关系
	// * @param serverId1 节点1
	// * @param serverId2 节点2
	// * @return Boolean 导入成功则返回true，反之返回false
	// */
	// public boolean insert(String serverId1,String serverId2){
	// if(serverId1 == null || "".equals(serverId1) || serverId2 == null ||
	// "".equals(serverId2)){
	// return false;
	// }
	// Dot server1 = new Dot(serverId1);
	// Dot server2 = new Dot(serverId2);
	// synchronized(this){
	// if(serverId1.equals(serverId2)){
	// return false;
	// }
	// if(dots.contains(server1) && !dots.contains(server2)){
	// dots.add(server2);
	// }else if(!dots.contains(server1) && dots.contains(server2)){
	// dots.add(server1);
	// }else{
	// dots.add(server1);
	// dots.add(server2);
	// }
	// server1 = dots.get(dots.indexOf(server1));
	// server2 = dots.get(dots.indexOf(server2));
	// server1.addNeighbor(server2);
	// server2.addNeighbor(server1);
	// return true;
	// }
	// }
	//
	// /**
	// * 更新节点连接状态
	// * @param serverId1 节点1
	// * @param serverId2 节点2
	// * @param linkState 连接状态
	// * @return Boolean 更新成功则返回true，反之返回false
	// */
	// public boolean update(String serverId1,String serverId2,boolean
	// linkState){
	// if(serverId1 == null || "".equals(serverId1) || serverId2 == null ||
	// "".equals(serverId2)){
	// return false;
	// }
	// Dot server1 = new Dot(serverId1);
	// Dot server2 = new Dot(serverId2);
	// synchronized(this){
	// if(dots == null || dots.size() == 0){
	// return false;
	// }
	// if(!dots.contains(server1) || !dots.contains(server2)){
	// return false;
	// }
	// server1 = dots.get(dots.indexOf(server1));
	// server2 = dots.get(dots.indexOf(server2));
	// if(linkState){
	// server1.addNeighbor(server2);
	// server2.addNeighbor(server1);
	// }else{
	// server1.removeNeighbor(server2);
	// server2.removeNeighbor(server1);
	// }
	// return true;
	// }
	// }
	//
	// /**
	// * 节点nodeId状态改变
	// * @param nodeId 节点ID
	// * @param state 节点状态
	// * @return Boolean 改变成功则返回true，反之返回false
	// */
	// public boolean changeNodeState(String nodeId,boolean state){
	// if(nodeId == null || "".equals(nodeId)){
	// return false;
	// }
	// Dot node = new Dot(nodeId);
	// synchronized(this){
	// if(dots == null || dots.size() == 0){
	// return false;
	// }
	// if(!dots.contains(node)){
	// return false;
	// }
	// dots.get(dots.indexOf(node)).setOnline(state);
	// return true;
	// }
	// }
	//
	// /**
	// * 删除节点serverId（警告：不可恢复）
	// * @param serverId 节点ID
	// * @return Boolean 删除成功则返回true，反之返回false
	// */
	// public boolean delete(String serverId){
	// if(serverId == null || "".equals(serverId)){
	// return false;
	// }
	// synchronized(this){
	// if(dots == null || dots.size() == 0){
	// return false;
	// }
	// Dot server = new Dot(serverId);
	// if(!dots.contains(server)){
	// return false;
	// }
	// server = dots.get(dots.indexOf(server));
	// List<Dot> neighbors = server.getNeighbors();
	// if(dots.remove(server)){
	// for(Dot dot:neighbors){
	// dot.removeNeighbor(server);
	// }
	// return true;
	// }
	// return false;
	// }
	// }
	//
	// /**
	// * 获得物理链接serverId节点的其他节点ID
	// * @param serverId 节点ID
	// * @return List<String>
	// */
	// public List<String> getNeighbors(String serverId){
	// if(serverId == null || "".equals(serverId)){
	// return null;
	// }
	// synchronized(this){
	// if(dots == null || dots.size() == 0){
	// return null;
	// }
	// Dot server = new Dot(serverId);
	// if(dots.contains(server)){
	// server = dots.get(dots.indexOf(server));
	// List<Dot> Dot_neighbors = server.getNeighbors();
	// List<String> neighbors = new ArrayList<String>();
	// if(Dot_neighbors == null || Dot_neighbors.size() == 0){
	// return neighbors;
	// }
	// for(Dot dot:Dot_neighbors){
	// neighbors.add(dot.getId());
	// }
	// return neighbors;
	// }
	// }
	// return null;
	// }
	//
	// /**
	// * 获得离线列表
	// * @return List<String>
	// */
	// public List<String> getOfflineList(){
	// synchronized(this){
	// List<String> offlineList = new ArrayList<String>();
	// for(Dot dot:dots){
	// if(!dot.isOnline()){
	// offlineList.add(dot.getId());
	// }
	// }
	// return offlineList;
	// }
	// }
	//
	// /**
	// * 判断两个节点是否物理链接
	// * @param serverId1 节点1
	// * @param serverId2 节点2
	// * @return Boolean 是物理链接则返回true，反之返回false
	// */
	// public boolean isLink(String serverId1,String serverId2){
	// if(serverId1 == null || "".equals(serverId1) || serverId2 == null ||
	// "".equals(serverId2)){
	// return false;
	// }
	// synchronized(this){
	// int index = dots.indexOf(new Dot(serverId1));
	// if(index >= 0){
	// Dot dot = dots.get(index);
	// if(new Dot(serverId2).isNeighbor(dot)){
	// return true;
	// }
	// }
	// return false;
	// }
	// }
	//
	// /**
	// * 写日志
	// * @param centerId 中心ID
	// * @param desc 描述（给运维人员看）
	// * @param errorMsg 错误描述
	// * @param errorType 错误类型
	// */
	public static void writeLog(String centerId, String desc,
			Exception errorMsg, String errorType) {
		String enter = "";
		String message = centerId + " | " + errorType + " | " + desc + " | ";
		if (System.getProperty("os.name").equals("Linux")) // 操作系统名称
		{
			enter = "\n";
		} else {
			enter = "\r\n";
		}

		String stackTrace = "";
		for (StackTraceElement ste : errorMsg.getStackTrace()) {
			stackTrace = stackTrace + "\tat " + ste + enter;
		}
		stackTrace = stackTrace.substring(0, stackTrace.lastIndexOf(enter));

		message = message + enter + errorMsg.toString() + " | " + enter
				+ stackTrace + enter
				+ "-------------------------------------------";
		LogUtil.connectError(message);

	}

}
