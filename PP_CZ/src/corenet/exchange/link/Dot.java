package corenet.exchange.link;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author <h1>Hoocoln<h1>
 * @version 2013-9-6
 */
public class Dot {
	
	public static final String WHITE = "white";//未被发现的顶点
	public static final String GREY = "grey";//已被发现，但还没有完全搜索其邻接表的顶点
	public static final String BLACK = "black";//已被发现，并完全搜索了其邻接表的顶点
	
	private String id;//节点的唯一标示
	private String name;//节点别名
	private String color;//节点颜色
	private int distance;//离起点的距离
	private boolean online;//是否在线
	private List<Dot> neighbors = new ArrayList<Dot>();//邻接表
	
	public Dot(String id){
		this.id = id;
		this.color = WHITE;
		this.distance = Integer.MAX_VALUE;
		this.online = true;
	}
	
	public Dot(String id,String name){
		this.id = id;
		this.name = name;
		this.color = WHITE;
		this.distance = Integer.MAX_VALUE;
		this.online = true;
	}
	
	public boolean equals(Object obj){
		if(obj == null || obj.getClass() != Dot.class){
			return false;
		}else{
			Dot dot = (Dot) obj;
			return this.id.equals(dot.getId());
		}
	}
	
	public int hashCode(){
		return this.id.hashCode();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	public List<Dot> getNeighbors() {
		return neighbors;
	}
	public void setNeighbors(Dot ... dots) {
		List<Dot> neighbors = new ArrayList<Dot>();
		for(Dot dot:dots){
			neighbors.add(dot);
		}
		this.neighbors = neighbors;
	}
	public void addNeighbors(Dot ... dots) {
		for(Dot dot:dots){
			addNeighbor(dot);
		}
	}
	public boolean addNeighbor(Dot dot){
		if(!neighbors.contains(dot)){
			return neighbors.add(dot);
		}else{
			return true;
		}
	}
	public void removeNeighbors(Dot ... dots){
		if(neighbors != null && neighbors.size() > 0){
			for(Dot dot:dots){
				neighbors.remove(dot);
			}
		}
	}
	public boolean removeNeighbor(Dot dot){
		if(neighbors != null && neighbors.size() > 0){
			return neighbors.remove(dot);
		}
		return false;
	}
	public void removeAllNeighbors(){
		neighbors.removeAll(neighbors);
	}
	public boolean isNeighbor(Dot dot){
		if(dot == null || this == null){
			return false;
		}
		if(dot.getNeighbors().contains(this)){
			return true;
		}
		return false;
	}

	public boolean isOnline() {
		return online;
	}
	public void setOnline(boolean online) {
		this.online = online;
	}
	
}
