package fxdigital.rpc.content;

import fxdigital.rpc.AbstractContent;
import fxdigital.rpc.contenttype.BusinessType;

public class BusinessContent extends AbstractContent{
	
	private String businessName;
	
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	@Override
	public String getType() {
		return BusinessType.TYPE;
	}
	
}
