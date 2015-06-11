package corenet.exchange.link;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;


/**
 * 
 * @author <h1>Hoocoln<h1>
 * @version 2013-9-6
 */
public class BreathFirstSearch {
	
	private Map<Dot,Dot[]> traces = new HashMap<Dot,Dot[]>();//从起点到指定点的最短路径
	private Queue<Dot> greyDots = new LinkedList<Dot>();//灰色顶点（即那些已被发现，但还没有完全搜索其邻接表的顶点）队列
	private List<Dot> dots = new ArrayList<Dot>();//所有顶点
	private Dot startDot;//起点
	
	/**
	 * 初始化广度优先搜索的所有顶点
	 * @param dots
	 */
	public BreathFirstSearch(List<Dot> dots) {
		if(dots != null && dots.size() > 0){
			this.dots = dots;
		}else{
			try {
				throw new Exception("Dots can not be NULL!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 重构以dot为起点，其他点为终点的最短路径
	 * @param dot
	 * @return
	 */
	public boolean rebuild(Dot dot){
		if(!dot.isOnline()){
			return false;
		}
		if(dots != null && dots.size() > 0){
			for(Dot edot:dots){
				edot.setColor(Dot.WHITE);
			}
			this.startDot = dot;
			traces.clear();
			greyDots.clear();
			
			startDot.setColor(Dot.GREY);
			greyDots.add(this.startDot);
			Dot[] startTrace = {startDot};
			traces.put(startDot, startTrace);
			
			search();
			traces.remove(startDot);
		}else{
			try {
				throw new Exception("Dots can not be NULL!");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}
		return true;
	}
	
	/**
	 * 重构后，获得以startDot为起点，endDot为终点的最短路径
	 * @param endDot
	 * @return
	 */
	public Dot[] getTrace(Dot endDot){
		if(endDot != null){
			return traces.get(endDot);
		}else{
			return null;
		}
	}
	
	/**
	 * 重构后，获得以startDot为起点，targetDot为终点的最短路径上的下一个节点
	 * @param targetDot
	 * @return
	 */
	public Dot getNextDot(Dot targetDot){
		Dot[] trace = getTrace(targetDot);
		if(trace != null && trace.length >= 2){
			return trace[1];
		}else{
			return null;
		}
	}
	
	//打印路径
	public String printTrace(Dot endDot){
		Dot[] trace = getTrace(endDot);
		String result = "";
		if(trace != null){
			for(Dot dot:trace){
				result = result + dot.getId() + "->";
			}
			result = result.substring(0, result.lastIndexOf("->"));
			return result;
		}else{
			return null;
		}
	}
	
	// 广度优先搜索的核心算法（使用递归算法时存在栈溢出错误，因此直接使用循环）
	private Map<Dot,Dot[]> search(){
		
		Dot curDot;
		while((curDot = greyDots.poll()) != null){
			curDot.setColor(Dot.BLACK);
			List<Dot> neighbors = curDot.getNeighbors();
			if(neighbors != null && neighbors.size() > 0){
				for(Dot dot:neighbors){
					if(!dot.isOnline()){
						continue;
					}
					if(Dot.WHITE.equals(dot.getColor())){
						dot.setColor(Dot.GREY);
						greyDots.add(dot);
					}
					if(traces.get(dot) == null){
						Dot[] ancestorTrace = traces.get(curDot);
						Dot[] trace = new Dot[ancestorTrace.length + 1];
						System.arraycopy(ancestorTrace, 0, trace, 0, ancestorTrace.length);
						trace[ancestorTrace.length] = dot;
						traces.put(dot, trace);
					}
				}
			}
		}
		return traces;
	}
	
	public static List<Dot> Dots(Dot ... dots){
		List<Dot> listDots = new ArrayList<Dot>();
		for(Dot dot:dots){
			listDots.add(dot);
		}
		return listDots;
	}
	
}
