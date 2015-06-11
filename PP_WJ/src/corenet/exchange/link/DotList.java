package corenet.exchange.link;

import java.util.ArrayList;

/**
 * 
 * @author <h1>Hoocoln<h1>
 * @version 2013-9-23
 */
@SuppressWarnings("serial")
public class DotList<E> extends ArrayList<E>{
	public boolean contains(Object obj){
		Dot curdot = (Dot)obj;
		for(E dot:this){
			Dot selfdot = (Dot) dot;
			if(selfdot.equals(curdot)){
				return true;
			}
		}
		return false;
	}
	public boolean remove(Object obj){
		Dot curdot = (Dot)obj;
		for(E dot:this){
			Dot selfdot = (Dot) dot;
			if(selfdot.equals(curdot)){
				return super.remove(selfdot);
			}
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	public boolean add(Object obj){
		if(!this.contains(obj)){
			return super.add((E) obj);
		}
		return true;
	}
}
