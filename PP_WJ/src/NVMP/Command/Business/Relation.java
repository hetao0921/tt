package NVMP.Command.Business;

//C# TO JAVA CONVERTER WARNING: Java does not allow user-defined value types. The behavior of this class will differ from the original:
//ORIGINAL LINE: public struct Relation
public final class Relation {
	public String UpNodeId;
	public String NodeId;
	public NodeType Type = NodeType.forValue(0);
	public int Level; // 1就是根节点
	public String Name;

	public Relation clone() {
		Relation varCopy = new Relation();

		varCopy.UpNodeId = this.UpNodeId;
		varCopy.NodeId = this.NodeId;
		varCopy.Type = this.Type;
		varCopy.Level = this.Level;
		varCopy.Name = this.Name;

		return varCopy;
	}
}