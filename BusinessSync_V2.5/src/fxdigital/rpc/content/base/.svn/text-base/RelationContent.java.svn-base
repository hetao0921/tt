package fxdigital.rpc.content.base;

import java.util.List;

import fxdigital.db.RegisterCenter;
import fxdigital.db.ServerRelation;
import fxdigital.db.SyncServer;
import fxdigital.rpc.AbstractContent;
import fxdigital.rpc.contenttype.base.RelationType;

/**
 * 级联数据消息体
 * 
 * @author fucz
 * @version 2014-7-24
 */
public class RelationContent extends AbstractContent{
	
	private String syncID;//同步ID
	private List<ServerRelation> serverRelations;//同步服务器级联关系数据
	private List<SyncServer> syncServers;//同步服务器信息
	private List<RegisterCenter> registerCenters;//注册的中心信息
	
	public String getSyncID() {
		return syncID;
	}
	public void setSyncID(String syncID) {
		this.syncID = syncID;
	}
	public List<SyncServer> getSyncServers() {
		return syncServers;
	}
	public void setSyncServers(List<SyncServer> syncServers) {
		this.syncServers = syncServers;
	}
	public List<ServerRelation> getServerRelations() {
		return serverRelations;
	}
	public void setServerRelations(List<ServerRelation> serverRelations) {
		this.serverRelations = serverRelations;
	}
	public List<RegisterCenter> getRegisterCenters() {
		return registerCenters;
	}
	public void setRegisterCenters(List<RegisterCenter> registerCenters) {
		this.registerCenters = registerCenters;
	}
	@Override
	public String getType() {
		return RelationType.TYPE;
	}
	
	
}
