package corenet.exchange.link;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CenterRoute {

	private List<CenterNode> list; // 放置全部节点
	private Map<String, CenterNode> map; // 按照key-value 存放节点
	private CenterNode rootNode;

	public CenterRoute() {
		list = new ArrayList<CenterNode>();
		map = new HashMap<String, CenterNode>();
	}

	public CenterNode insert(String node, String parentNode) {
		// System.out.println(node + "|" + parentNode);
		CenterNode cn = new CenterNode(node, parentNode);
		list.add(cn);
		map.put(node, cn);
		if (parentNode == null) {
			rootNode = cn;
		}
		return cn;
	}

	public void count(String nowCenterID) {

//		String rootName = null;
//		for (CenterNode node : list) {
//			// 根据值，找到父节点，进行相互关联
//			// 如果父节点不存在，表明该父节点是根节点，创建出
//			CenterNode cn = map.get(node.getTempParentName());
//			if (cn == null) {
//				rootName = node.getTempParentName();
//				break;
//			}
//		}
//		if (rootName != null) {
//			insert(rootName, null);
//		}
		String rootName = nowCenterID;
		CenterNode cnode =  map.get(nowCenterID);
		while(cnode!=null){
			rootName = cnode.getTempParentName();
			cnode =  map.get(cnode.getTempParentName());
		}
		
		insert(rootName, null);

		for (CenterNode node : list) {
			// 根据值，找到父节点，进行相互关联
			// 如果父节点不存在，表明该父节点是根节点，创建出
			CenterNode cn = map.get(node.getTempParentName());
			node.setParent(cn);
			if (cn != null)
				cn.addChild(node);
		}

		// list.add(rootNode);

		// 以根节点开始，进行级别递归计算
		if (rootNode != null)
			setLev(rootNode, 0);

	}

	/**
	 * @param cn
	 *            节点
	 * @param n
	 *            父节点级别 ，根节点传入0
	 * */
	private void setLev(CenterNode cn, int n) {
		n = n + 1;
		cn.setLevel(n);
		for (CenterNode node : cn.getChilds()) {
			setLev(node, n);
		}

	}

	/**
	 * 获取当前节点到目前等级最高的节点怎么走
	 * */
	public String getUpRoute(String startCenterid) {
		if (startCenterid == null)
			return null;
		CenterNode startNode = map.get(startCenterid);
		if (startNode == null || startNode.getParent() == null)
			return null;
		return startNode.getParent().getNodeName();

	}

	/**
	 * 计算从A节点去B节点，首先要去的节点
	 * */
	public String getDirectRoute(String startCenterid, String endCenterid) {

		if (startCenterid.equals(endCenterid)) {
			return startCenterid;
		}

		CenterNode startNode = map.get(startCenterid);
		CenterNode endNode = map.get(endCenterid);

		if (endNode == null || startNode==null) {
			return null;
		}

		// System.out.println("center  all   "+startCenterid +"    "+endCenterid
		// +" | " );
		// 如果A的层次大于或等于B，则直接是往上走
		if (startNode.getLevel() >= endNode.getLevel()) {

			return startNode.getParent().getNodeName();

		}

		// 让B节点爬到A的兄弟位置，再进行判断
		CenterNode lastNode = null;
		while (endNode.getLevel() > startNode.getLevel()) {
			lastNode = endNode;
			endNode = endNode.getParent();
		}

		// 如果是A，则返回 最后一个B，如果不是A，则返回A的上级
		if (endNode == startNode) {
			return lastNode.getNodeName();
		} else {
			return startNode.getParent().getNodeName();
		}

	}


	// 验证是否是直接连接关系
	public boolean isNeighbour(String nodeid, String nextID) {
		boolean isNeighbour = false;
		CenterNode nodeA = map.get(nodeid);
		CenterNode nodeB = map.get(nextID);
		if (nodeA != null && nodeB != null) {
			if (nodeA.getParent() == nodeB || nodeB.getParent() == nodeA) {
				isNeighbour = true;
			}
		}

		return isNeighbour;
	}

	/**
	 * 计算中心A的临近中心B下线后，相对A下线的中心
	 * */
	public List<String> getLoseCenter(String centerID, String loseCenter) {
		List<String> loseList = null;
		CenterNode loseNode = map.get(loseCenter);
		CenterNode centerNode = map.get(centerID);
		if (loseNode != null && centerNode != null) {
			if (loseNode.getParent() == centerNode) {
				loseList = getAllSon(loseNode);
			} else if (centerNode.getParent() == loseNode) {
				loseList = getAllParent(loseNode, centerNode);
			}
		}

		return loseList;
	}

	// 求反，计算在上级方向的所有节点
	private List<String> getAllParent(CenterNode loseNode,
			CenterNode localCenter) {

		List<String> sonList = getAllSon(localCenter);
		// 计算所有的节点，进行互减
		List<String> allList = new LinkedList<String>();
		for (CenterNode node : list) {
			if (node != loseNode && node != localCenter)
				allList.add(node.getNodeName());
		}
		allList.removeAll(sonList);

		return allList;
	}

	// 计算所有子节点的信息
	private List<String> getAllSon(CenterNode loseNode) {
		List<String> list = new LinkedList<String>();
		for (CenterNode sonNode : loseNode.getChilds()) {
			insertSon(sonNode, list);
		}
		return list;
	}

	private void insertSon(CenterNode node, List<String> list) {
		list.add(node.getNodeName());
		for (CenterNode sonNode : node.getChilds()) {
			insertSon(sonNode, list);
		}
	}

}
