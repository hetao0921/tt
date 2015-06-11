package fxdigital.rpc.content.base;

import fxdigital.db.SubStatus;
import fxdigital.db.SupStatus;
import fxdigital.rpc.AbstractContent;
import fxdigital.rpc.contenttype.base.NetworkType;

/**
 * 级联申请消息体
 * 
 * @author fucz
 * @version 2014-7-24
 */
public class NetworkContent extends AbstractContent{
	
	private SubStatus subStatus;//上级信息
	private SupStatus supStatus;//下级信息
	private int status;//申请状态
	
	public SubStatus getSubStatus() {
		return subStatus;
	}
	public void setSubStatus(SubStatus subStatus) {
		this.subStatus = subStatus;
	}
	public SupStatus getSupStatus() {
		return supStatus;
	}
	public void setSupStatus(SupStatus supStatus) {
		this.supStatus = supStatus;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String getType() {
		return NetworkType.TYPE;
	}
	
}
