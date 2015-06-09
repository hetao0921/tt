package com.fxdigital.syncclient.analysis.bean;

import java.util.List;

public class ResourceGroupMember {
	private String resourceId;
	private String resourceName;
	private String resourceType;
	private String parentId;
	private int sortIndex;
	private List<ResGpMemAttribute> attributes;
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public int getSortIndex() {
		return sortIndex;
	}
	public void setSortIndex(int sortIndex) {
		this.sortIndex = sortIndex;
	}
	public List<ResGpMemAttribute> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<ResGpMemAttribute> attributes) {
		this.attributes = attributes;
	}
}
