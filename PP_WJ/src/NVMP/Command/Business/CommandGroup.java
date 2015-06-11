package NVMP.Command.Business;

import org.misc.RefObject;




public class CommandGroup extends CommandCollection {

	private java.util.ArrayList<Relation> _Relationlist;

	public final java.util.ArrayList<Relation> getRelationlist() {
		return _Relationlist;
	}

	public final void setRelationlist(java.util.ArrayList<Relation> value) {
		_Relationlist = value;
	}

	public CommandGroup() {
		setRelationlist(new java.util.ArrayList<Relation>());
	}

	protected void finalize() throws Throwable {

	}

	/**
	 * @param Id
	 * @param Name
	 * @param Decription
	 */
	public CommandGroup(String id, String name, String decription) {
		/**
		 * z
		 */
		this.setId(id);
		this.setName(name);
		this.setDecription(decription);
		this.setRelationlist(new java.util.ArrayList<Relation>());
		this.setCommanderLists(new java.util.ArrayList<Commander>());
	}

	public final boolean GetCommanderById(String CommanderId,
			RefObject<Commander> aCommander) {
		aCommander.argvalue = null;
		for (Commander com : getCommanderLists()) {
			if (com.getCommanderId().equals(CommanderId)) {
				aCommander.argvalue = com;
				return true;
			}
		}
		return false;
	}

	@Override
	public void dispose() {

	}

	/**
	 * @param downCommanderId
	 * @param upCommanerId
	 */
	public final void AddCommandRelation(Relation aRelation) {
		for (Relation com : getRelationlist()) {
			if (com.NodeId.equals(aRelation.NodeId)
					&& com.UpNodeId.equals(aRelation.UpNodeId)) {
				getRelationlist().remove(com.clone());
			}
		}
		getRelationlist().add(aRelation.clone());
	}

	/**
	 * 指挥员进入指挥分组
	 * 
	 * @param aCommander
	 *            //public void Enter(Commander aCommander) //{
	 */

	// }

	/**
	 * // <param name="CommanderId"></param>
	 */
	public final java.util.ArrayList<Commander> GetCommanderListByDown(
			String CommanderId) {
		java.util.ArrayList<Commander> aCommanderList = new java.util.ArrayList<Commander>();
		Commander aCommander = null;
		for (Relation item : _Relationlist) {
			if (item.UpNodeId.equals(CommanderId)) {
				RefObject<Commander> tempRef_aCommander = new RefObject<Commander>(
						aCommander);
				boolean tempVar = GetCommanderById(item.NodeId,
						tempRef_aCommander);
				aCommander = tempRef_aCommander.argvalue;
				if (tempVar) {
					aCommanderList.add(aCommander);
				}
			}

		}
		return aCommanderList;
	}

	public final java.util.ArrayList<Relation> GetNodeListByDown(String NodeId) {
		java.util.ArrayList<Relation> NodeList = new java.util.ArrayList<Relation>();
		// Commander aCommander;
		for (Relation item : _Relationlist) {
			if (item.UpNodeId.equals(NodeId)) {
				NodeList.add(item.clone());

			}

		}
		return NodeList;
	}

	/**
	 * // <param name="CommanderId"></param>
	 */
	public final java.util.ArrayList<Commander> GetCommanderListByUp(
			String CommanderId) {

		java.util.ArrayList<Commander> aCommanderList = new java.util.ArrayList<Commander>();
		// Commander aCommander;
		// foreach (Relation item in _Relationlist)
		// {
		// if (item.DownNodeId.Equals(CommanderId))
		// {
		// // aCommanderList.Add(GetCommanderById(CommanderId));
		// if (GetCommanderById(CommanderId, out aCommander))
		// {
		// aCommanderList.Add(aCommander);
		// }
		// }

		// }
		return aCommanderList;
	}

	/**
	 * 得到指挥分群的最上级节点
	 */
	public final java.util.ArrayList<Commander> GetFirstComnanderList() {

		java.util.ArrayList<Commander> aCommanderList = new java.util.ArrayList<Commander>();
		Commander aCommander = null;
		for (Commander item : getCommanderLists()) {
			if ((GetCommanderListByUp(item.getCommanderId())).size() == 0) {

				RefObject<Commander> tempRef_aCommander = new RefObject<Commander>(
						aCommander);
				boolean tempVar = GetCommanderById(item.getCommanderId(),
						tempRef_aCommander);
				aCommander = tempRef_aCommander.argvalue;
				if (tempVar) {
					aCommanderList.add(aCommander);
				}
			}

		}
		return aCommanderList;

	}

	public final Relation GetRoot() {
		Relation ret;
		// ret.UpNodeId = "";
		// ret.NodeId = "";
		// ret.setName("");
		// ret.Level = 1;
		// ret.Type = NodeType.Org;

		for (Relation item : _Relationlist) {
			if (item.UpNodeId.equals("root")) {
				return item;
			}

		}
		// return ret;
		return null;
	}

	/**
	 * 推出指挥分组
	 * 
	 * @param aCommander
	 *            //public void Quit(Commander aCommander) //{
	 */

	// }

	/**
	 * @param DownCommandId
	 * @param UpCommanderId
	 */
	public final void RemoveCommandRelation(String DownCommandId,
			String UpCommanderId) {
		// zhang
		// foreach (Relation com in Relationlist)
		// {
		// if (com.DownNodeId.Equals(DownCommandId) &&
		// com.UpNodeId.Equals(UpCommanderId))
		// {
		// Relationlist.Remove(com);
		// }
		// }
	}

} // end CommandGroup