package com.fxdigital.analysis.bean;

import java.util.List;

public class CenterRelation {
	private String relationId;//关系记录的ID
	private String relationType;//一组关系中心的ID的列表
	private String centerId;//设置中心的ID
	private List<RelatedCenter> relatedCenters;//关系记录的ID
	private String relationName;
	private String CreateTime;
	public String getRelationId() {
		return relationId;
	}
	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}
	public String getRelationType() {
		return relationType;
	}
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
	public String getCenterId() {
		return centerId;
	}
	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}



	public List<RelatedCenter> getRelatedCenters() {
		return relatedCenters;
	}
	public void setRelatedCenters(List<RelatedCenter> relatedCenters) {
		this.relatedCenters = relatedCenters;
	}
	public String getRelationName() {
		return relationName;
	}
	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	
	

}
