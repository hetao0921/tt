package corenet.exchange;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import corenet.exchange.link.ServerLink;

class Node {
	public String nodeID;
	public String parentNodeID;

	public Node(String nodeID, String parentNodeID) {
		this.nodeID = nodeID;
		this.parentNodeID = parentNodeID;
	}
}

public class RouteTest {

	private Node root;

	private int levNum;

	private int branches;

	private List<Node> nodeList;

	public RouteTest(String rootName, int levNum, int branches) {
		this.root = new Node(rootName, null);
		this.levNum = levNum;
		this.branches = branches;
		nodeList = new LinkedList<Node>();

	}

	
	/**
	 * 填充整个数据，每个节点下有100个节点，总共5层
	 * */
	public void initData() {
		System.out.println("开始初始化测试数据");
		addNodes(root.nodeID, 0);
		System.out.println("节点数为"+nodeList.size());
		ServerLink.getInstance().init();
		for (Node node : nodeList) {
			ServerLink.getInstance().insert(node.nodeID, node.parentNodeID);
		}
		System.out.println("添加结束");
	}

	public void addNodes(String node, int lev) {
		if (lev >= levNum - 1) {
			return;
		} else {
			for (int i = 0; i < branches; i++) {
				String newNode = node + i;
				nodeList.add(new Node(newNode, node));
				addNodes(newNode, lev + 1);
			}
		}
	}

	/**
	 * 设置中心，重新初始化link；
	 * */
	public void setCenter(String centerID) {
		System.out.println("开始初始化 centerID " +  centerID);
		ServerLink.getInstance().createLinkMap(centerID);
		System.out.println("初始化结束 centerID " +  centerID);
	}

	public boolean isRightResulut(String node, String targetNode) {
		String result = null;
		String resultLink = ServerLink.getInstance().getNextDot(targetNode);
		if(resultLink==null) return false;
		if (targetNode.contains(node)) {
			result = targetNode.substring(0, node.length()+1);
		} else {
			result = node.substring(0, node.length() - 1);
		}
		return resultLink.equals(result);
	}

	public static void main(String[] args) {
		RouteTest test = new RouteTest("A", 5, 10);
		test.initData();
	
		//测试getNextDot 方案
		test.setCenter("A");
		
		
		System.out.println(ServerLink.getInstance().getNextDot("B"));
		
		for(Node node:test.nodeList) {
			System.out.println("测试以节点为中心  "+node.nodeID);
			test.setCenter(node.nodeID);
			System.out.println("初始完毕");
			System.out.println(Calendar.getInstance().getTime());
			for(Node nodeItem:test.nodeList){
				if(nodeItem != node ){
					 assert(test.isRightResulut(node.nodeID, nodeItem.nodeID));
				}
				
			}
			System.out.println(Calendar.getInstance().getTime());
			System.out.println("测试 中心结束  "+node.nodeID);
		}
		
		
	}
}
