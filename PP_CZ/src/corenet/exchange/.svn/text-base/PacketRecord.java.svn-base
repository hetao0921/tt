package corenet.exchange;

import org.misc.log.LogUtil;

/**
 * 采用HashSet 来进行判断 用linkedlist来实现FIFO策略
 * */
public class PacketRecord { 
	final private int listsize = 10000;

	// private LinkedList<String> list;
	// private HashSet<String> set;

	private java.util.concurrent.ConcurrentLinkedQueue<String> checkList;

	private java.util.concurrent.ConcurrentLinkedQueue<String> checkList2;

	private static PacketRecord pr;

	private PacketRecord() {
		// list = new LinkedList<String>();
		// set = new HashSet<String>(10000);
		checkList = new java.util.concurrent.ConcurrentLinkedQueue<String>();
		checkList2 = new java.util.concurrent.ConcurrentLinkedQueue<String>();
	}

	public static PacketRecord getPacketRecord() {
		if (pr == null)
			pr = new PacketRecord();
		return pr;
	}

	/**
	 * true 表示已经处理过 false 表示未处理过
	 * 
	 * 
	 * */
	public boolean check(String uid) {

		if (checkList.contains(uid)) {
			LogUtil.TestInfo("check :"+ uid);
			return true;
		}
		
		// 判断当前长度，如果长度到了10000，就开始采用FIFO的方式，去掉最上面的100。
		if (checkList.size() >= listsize) {
			for (int i = 0; i < 100; i++)
				checkList.poll();
		}
		if (uid != null)
			checkList.add(uid);
		return false;

	}

	public boolean check2(String uid) {

		if (checkList2.contains(uid))  {
			LogUtil.TestInfo("check :"+ uid);
			return true;
		}
			
		// 判断当前长度，如果长度到了10000，就开始采用FIFO的方式，去掉最上面的100。
		if (checkList2.size() >= listsize) {
			for (int i = 0; i < 100; i++)
				checkList2.poll();
		}
		if (uid != null)
			checkList2.add(uid);
		return false;

	}

	public static void main(String[] args) {

		System.out.println(java.util.Calendar.getInstance().getTime()
				.toString());
		for (int i = 0; i < 10000; i++) {
			PacketRecord.getPacketRecord().check(String.valueOf(i));

		}
		System.out.println(java.util.Calendar.getInstance().getTime()
				.toString());

		for (int i = 9981; i < 10004; i++) {
			System.out.println(PacketRecord.getPacketRecord().check(
					String.valueOf(i)));

		}

		System.out.println(java.util.Calendar.getInstance().getTime()
				.toString());

	}

}
