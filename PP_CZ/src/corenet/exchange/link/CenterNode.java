package corenet.exchange.link;



import java.util.ArrayList;
import java.util.List;

public class CenterNode {
	private CenterNode preNode;

	private List<CenterNode> nextNodes;

	private int level;

	private String nodeName;

	private String parentName;

	public CenterNode(String nodeName, String parentName) {
		this.nodeName = nodeName;
		this.parentName = parentName;
		preNode = null;
		level = 0;
		nextNodes = new ArrayList<CenterNode>();
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setParent(CenterNode cn) {
		preNode = cn;
	}

	public void addChild(CenterNode cn) {
		nextNodes.add(cn);
	}

	public String getNodeName() {
		return nodeName;
	}

	public String getTempParentName() {
		return parentName;
	}

	public CenterNode getParent() {
		return preNode;
	}

	public List<CenterNode> getChilds() {
		// TODO Auto-generated method stub
		return nextNodes;
	}
}
