package NVMP.VideoControlDomain;

import java.util.Set;


public class Route {

	private int type;                  //1 P2P   2 Route
	private String routeDesc;          //中心路由信息
	private String localId;            //当前中心ID
	//定义两个静态常量用来标识type的两个值到底代表什么
	final static String type1 = "P2P";
	final static String type2 = "Route";
	public Route() {
		super();
	} 
	
	public void changeCenter(Set<String> set) {
		String [] routes = routeDesc.split(",");
		StringBuffer sb = new StringBuffer();
		sb.append(routes[0]);
		int n = 0;
		for(int i=1;i<routes.length;i++){
			if(!set.contains(routes[i])) {
				n++;
				sb.append(",").append(routes[i]);
			}
		}
		if(n==0) {
			sb.append(",").append(routes[0]);
		}
		
		routeDesc = sb.toString();
	}
	
	public Route(int type, String routeDesc, String localId) {
		super();
		this.type = type;
		this.routeDesc = routeDesc;
		this.localId = localId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getRouteDesc() {
		return routeDesc;
	}
	public void setRouteDesc(String routeDesc) {
		this.routeDesc = routeDesc;
	}
	public String getLocalId() {
		return localId;
	}
	public void setLocalId(String localId) {
		this.localId = localId;
	}
	
	/**
	 * 获取当前中心的下一个中心
	 * @return
	 */
	public String getNextRoute(){
		String [] routes = routeDesc.split(",");
		for(int i=0;i<routes.length;i++){
			if(localId.equals(routes[i])){
				if(i<routes.length-1)
					return routes[i+1];
			}
		}
		return null;
	}
	
	/**
	 * 获取当前中心的上一个中心
	 * @return
	 */
	public String getLastRoute(){
		String [] routes = routeDesc.split(",");
		for(int i=0;i<routes.length;i++){
			if(localId.equals(routes[i])){
				if(i>0)
					return routes[i-1];
			}
		}
		return null;
	}
	
	/**
	 * 获取第一个路由
	 * @return
	 */
	public String getFirstRoute(){
		String [] routes = routeDesc.split(",");
		if(routes.length>0){
			return routes[0];
		}
		return null;
	}
	
	/**
	 * 获取最后一个路由
	 * @return
	 */
	public String getZLastRoute(){
		String [] routes = routeDesc.split(",");
		if(routes.length>0){
			return routes[routes.length-1];
		}
		return null;
	}
	
	/**
	 * 判断当前中心ID是否是路由中的第一个ID
	 * @return
	 */
	public boolean isFist(){
		String [] routes = routeDesc.split(",");
		if(routes[0].equals(localId))
			return true;
		else
			return false;
	}
	
	/**
	 * 判断当前中心ID是否是路由中的最后一个ID
	 * @return
	 */
	public boolean isLast(){
		String [] routes = routeDesc.split(",");
		if(routes[routes.length-1].equals(localId))
			return true;
		else
			return false;
	}
	
	/**
	 * 将对象自身序列化成一个字符串
	 * @return
	 */
	public String routeToStr(){
		return "{Route}{type}"+type+"{/type}{desc}"+routeDesc+"{/desc}{Route}";
	}
	
	/**
	 * 将一个字符串反序列化成一个对象
	 * @param str
	 * @return
	 */
	public static Route strToRoute(String str){
		int type = Integer.parseInt(str.substring(13,14));
		String desc = str.substring(27,str.indexOf("{/desc}"));
		Route r = new Route();
		r.setRouteDesc(desc);
		r.setType(type);
		r.changeCenter(RouteImpl.centerSet);
		return r;
	}
}
