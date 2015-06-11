package corenet.exchange;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import org.misc.log.LogUtil;

class QuerySet {

	private LinkedList<String> list; 
	private HashSet<String> set;

	public QuerySet() {
		list = new LinkedList<String>();
		set = new HashSet<String>();
	}

	public boolean contains(String uuid) {
		return set.contains(uuid);
	}

	public int size() {
		return set.size();
	}

	public void poll() {
		String temp = list.poll();
		set.remove(temp);
	}

	public void add(String uuid) {
		list.add(uuid);
		set.add(uuid);
	}

}

/**
 * 为了检测，同一个数据包，同一个来源，在这里最多过一次。
 * 
 * */
public class DuplicateCheck {

	final int listsize = 20000;

	private HashMap<String, QuerySet> oneSet;
	static private DuplicateCheck dc;

	private QuerySet  centerMessageQuery;
	
	
    static public DuplicateCheck getDuplicateCheck() {
		if (dc == null) {
			dc = new DuplicateCheck();
		}
		return dc;
	}

	private DuplicateCheck() {
		oneSet = new HashMap<String, QuerySet>();
		centerMessageQuery = new QuerySet();
	}
	
	
	synchronized public boolean centerMessagecheck(String uuid){
		boolean b  = false;
		if(centerMessageQuery.contains(uuid)) {
			b= true;
		} else {
			if (centerMessageQuery.size() >= listsize) {
				centerMessageQuery.poll();
			}
			centerMessageQuery.add(uuid);
		}
		return b;
	}
	
//    public void centerMessageAdd(String uuid){
//			if (centerMessageQuery.size() >= listsize) {
//				centerMessageQuery.poll();
//			}
//			centerMessageQuery.add(uuid);
//	}
	
	
	
	
	
	
	
	
	synchronized public boolean check(String centerid, String uuid) {

		QuerySet list = null;

		if (oneSet.containsKey(centerid)) {
			list = oneSet.get(centerid);
		} else {
			list = new QuerySet();
			oneSet.put(centerid, list);
			list.add(uuid);
			return false;

		}

		if (list.contains(uuid)) {
			LogUtil.TestInfo("check :"+ uuid);
			return true;
		}
		if (list.size() >= listsize) {
			list.poll();
		}
		list.add(uuid);
		return false;
	}
	
	
	

}
