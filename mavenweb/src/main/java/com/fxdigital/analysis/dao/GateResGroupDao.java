package com.fxdigital.analysis.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.fxdigital.analysis.bean.ResGpMemAttribute;
import com.fxdigital.analysis.bean.ResourceGroup;
import com.fxdigital.analysis.bean.ResourceGroupMember;
@Repository
public class GateResGroupDao extends BaseDao{
	private static Log logger=LogFactory.getLog(GateResGroupDao.class);
	//查询所有组信息
			public List<ResourceGroup> queryGroupinfo(){
				logger.info("come into queryGroupinfo function!");

				String sql="select GroupID,CenterID,GroupName,UserID,GroupType from nvmp_resourcegrouptab";
				String sqlrgm="select GroupID,ParentID,ResourceID,ResourceName,ResourceType,SortIndex from nvmp_resourcegroupmembertab where GroupID in ( select GroupID from nvmp_resourcegrouptab);";
				String sqlrgma="select GroupID,ResourceID,AttributeType,AttributeValue from nvmp_resourcegroupattrtab where GroupID in ( select GroupID from nvmp_resourcegrouptab);";
				
				return doQuery(sql,sqlrgm,sqlrgma);
			}
			
			//查询指定中心所有组信息
					public List<ResourceGroup> queryGroupsByCenters(String[] centerids){
						logger.info("come into queryGroupsByCenters function!");
						String str=getStr(centerids,"CenterID");
						String sql="select GroupID,CenterID,GroupName,UserID,GroupType from nvmp_resourcegrouptab where "+str;
						String sqlrgm="select GroupID,ParentID,ResourceID,ResourceName,ResourceType,SortIndex from nvmp_resourcegroupmembertab where GroupID in ( select GroupID from nvmp_resourcegrouptab where "+str+");";
						String sqlrgma="select GroupID,ResourceID,AttributeType,AttributeValue from nvmp_resourcegroupattrtab where GroupID in ( select GroupID from nvmp_resourcegrouptab where "+str+");";
						logger.info("query groupinfo sql:"+sql);
						logger.info("query groupmenbersinfo sql:"+sqlrgm);
						logger.info("query groupmenberatrrinfo sql:"+sqlrgma);
						
						return doQuery(sql,sqlrgm,sqlrgma);
					}
					//查询指定组类型的所有组信息
					public List<ResourceGroup> queryGroupByType(String[] grouptypes){
						logger.info("come into queryGroupByType function!");
						String str=getStr(grouptypes,"GroupType");
						String sql="select GroupID,CenterID,GroupName,UserID,GroupType from nvmp_resourcegrouptab where "+str;
						String sqlrgm="select GroupID,ParentID,ResourceID,ResourceName,ResourceType,SortIndex from nvmp_resourcegroupmembertab where GroupID in ( select GroupID from nvmp_resourcegrouptab where "+str+");";
						String sqlrgma="select GroupID,ResourceID,AttributeType,AttributeValue from nvmp_resourcegroupattrtab where GroupID in ( select GroupID from nvmp_resourcegrouptab where "+str+");";
						logger.info("query groupinfobytype sql:"+sql);
						logger.info("query groupmenbersinfobytype sql:"+sqlrgm);
						logger.info("query groupmenberatrrinfobytype sql:"+sqlrgma);
					
						return doQuery(sql,sqlrgm,sqlrgma);
					}	
					
					//查询指定中心和组类型的所有组信息
					public List<ResourceGroup> queryGroupByAll(String[] centerids,String[] grouptypes){
						logger.info("come into queryGroupByType function!");
						String str=getStr(centerids,"CenterID");
						String types=getStr(grouptypes,"GroupType");
						String sql="select GroupID,CenterID,GroupName,UserID,GroupType from nvmp_resourcegrouptab where "+str+" and "+types;
						String sqlrgm="select GroupID,ParentID,ResourceID,ResourceName,ResourceType,SortIndex from nvmp_resourcegroupmembertab where GroupID in ( select GroupID from nvmp_resourcegrouptab where "+str+" and "+types+");";
						String sqlrgma="select GroupID,ResourceID,AttributeType,AttributeValue from nvmp_resourcegroupattrtab where GroupID in ( select GroupID from nvmp_resourcegrouptab where "+str+" and "+types+");";
						logger.info("query groupinfobyall sql:"+sql);
						logger.info("query groupmenbersinfobyall sql:"+sqlrgm);
						logger.info("query groupmenberatrrinfobyall sql:"+sqlrgma);

						return doQuery(sql,sqlrgm,sqlrgma);
					}
					//根据组Id查找组信息
					public List<ResourceGroup> queryGroupBygid(String[] groupids){
						logger.info("come into queryGroupBygid function!");
						String str=getStr(groupids,"GroupID");
						String sql="select GroupID,CenterID,GroupName,UserID,GroupType from nvmp_resourcegrouptab where "+str;
						String sqlrgm="select GroupID,ParentID,ResourceID,ResourceName,ResourceType,SortIndex from nvmp_resourcegroupmembertab where GroupID in ( select GroupID from nvmp_resourcegrouptab where "+str+");";
						String sqlrgma="select GroupID,ResourceID,AttributeType,AttributeValue from nvmp_resourcegroupattrtab where GroupID in ( select GroupID from nvmp_resourcegrouptab where "+str+");";
						logger.info("query groupinfoBygid sql:"+sql);
						logger.info("query groupmenbersinfoBygid sql:"+sqlrgm);
						logger.info("query groupmenberatrrinfoBygid sql:"+sqlrgma);
						return doQuery(sql,sqlrgm,sqlrgma);
					}
					
					//根据资源Id查找组信息
					public List<ResourceGroup> queryGroupByrid(String[] resourceids){
						logger.info("come into queryGroupByrid function!");
						String str=getStr(resourceids,"ResourceID");
						String sql="select GroupID,CenterID,GroupName,UserID,GroupType from nvmp_resourcegrouptab where GroupID in (select GroupID from nvmp_resourcegroupmembertab where "+str+")";
						String sqlrgm="select GroupID,ParentID,ResourceID,ResourceName,ResourceType,SortIndex from nvmp_resourcegroupmembertab where GroupID in (select GroupID from nvmp_resourcegroupmembertab where "+str+")";
						String sqlrgma="select GroupID,ResourceID,AttributeType,AttributeValue from nvmp_resourcegroupattrtab where GroupID in ( select GroupID from nvmp_resourcegroupmembertab where "+str+");";
						logger.info("query groupinfoByrid sql:"+sql);
						logger.info("query groupmenbersinfoByrid sql:"+sqlrgm);
						logger.info("query groupmenberatrrinfoByrid sql:"+sqlrgma);
						return doQuery(sql,sqlrgm,sqlrgma);
					}
					//查询所有资源Id的组信息
					public List<ResourceGroup> queryGroupByallrid(){
						logger.info("come into queryGroupByallrid function!");
						String sql="select GroupID,CenterID,GroupName,UserID,GroupType from nvmp_resourcegrouptab where GroupID in (select GroupID from nvmp_resourcegroupmembertab)";
						String sqlrgm="select GroupID,ParentID,ResourceID,ResourceName,ResourceType,SortIndex from nvmp_resourcegroupmembertab";
						String sqlrgma="select GroupID,ResourceID,AttributeType,AttributeValue from nvmp_resourcegroupattrtab where GroupID in ( select GroupID from nvmp_resourcegroupmembertab);";
						logger.info("query groupinfobyallrid sql:"+sql);
						logger.info("query groupmenbersinfobyallrid sql:"+sqlrgm);
						logger.info("query groupmenberatrrinfobyallrid sql:"+sqlrgma);
						return doQuery(sql,sqlrgm,sqlrgma);
					}
			
//					查询资源id和组类型的组信息
					public List<ResourceGroup> queryGroupByallrg(String[] resourceids,String[] grouptypes){
						logger.info("come into queryGroupByallrg function!");
						String strrid=getStr(resourceids,"ResourceID");
						String strtytes=getStr(grouptypes,"GroupType");
						String sql="select GroupID,CenterID,GroupName,UserID,GroupType from nvmp_resourcegrouptab where GroupID in (select GroupID from nvmp_resourcegroupmembertab where "+strrid+") and "+strtytes;
						String sqlrgm="select GroupID,ParentID,ResourceID,ResourceName,ResourceType,SortIndex from nvmp_resourcegroupmembertab where GroupID in (select GroupID from nvmp_resourcegrouptab where "+strtytes+")";
						String sqlrgma="select GroupID,ResourceID,AttributeType,AttributeValue from nvmp_resourcegroupattrtab where GroupID in ( select GroupID from nvmp_resourcegrouptab where "+strtytes+");";
						logger.info("query groupinfobyallrg sql:"+sql);
						logger.info("query groupmenbersinfobyallrg sql:"+sqlrgm);
						logger.info("query groupmenberatrrinfobyallrg sql:"+sqlrgma);
						return doQuery(sql,sqlrgm,sqlrgma);
					}
					
					//查询所有用户和组类型的组信息
					public List<ResourceGroup> queryGroupByallutype(){
						logger.info("come into queryGroupByallutype function!");
//						String str=getStr(grouptypes,"GroupType");
						String sql="select GroupID,CenterID,GroupName,UserID,GroupType from nvmp_resourcegrouptab";
						String sqlrgm="select GroupID,ParentID,ResourceID,ResourceName,ResourceType,SortIndex from nvmp_resourcegroupmembertab where GroupID in (select GroupID from nvmp_resourcegrouptab)";
						String sqlrgma="select GroupID,ResourceID,AttributeType,AttributeValue from nvmp_resourcegroupattrtab where GroupID in ( select GroupID from nvmp_resourcegrouptab);";
						logger.info("query groupinfoByallutype sql:"+sql);
						logger.info("query groupmenbersinfoByallutype sql:"+sqlrgm);
						logger.info("query groupmenberatrrinfoByallutype sql:"+sqlrgma);
						return doQuery(sql,sqlrgm,sqlrgma);
					}
					
					//查询用户的组信息
					public List<ResourceGroup> queryGroupByalluser(String[] userids){
						logger.info("come into queryGroupByalluser function!");
						String str=getStr(userids,"UserID");
						String sql="select GroupID,CenterID,GroupName,UserID,GroupType from nvmp_resourcegrouptab where "+str;
						String sqlrgm="select GroupID,ParentID,ResourceID,ResourceName,ResourceType,SortIndex from nvmp_resourcegroupmembertab where GroupID in (select GroupID from nvmp_resourcegrouptab where "+str+")";
						String sqlrgma="select GroupID,ResourceID,AttributeType,AttributeValue from nvmp_resourcegroupattrtab where GroupID in ( select GroupID from nvmp_resourcegrouptab where "+str+");";
						logger.info("query groupinfoByalluser sql:"+sql);
						logger.info("query groupmenbersinfoByalluser sql:"+sqlrgm);
						logger.info("query groupmenberatrrinfoByalluser sql:"+sqlrgma);
						return doQuery(sql,sqlrgm,sqlrgma);
					}
					
					//查询用户和组类型的组信息
					public List<ResourceGroup> queryGroupByallutypes(String[] userids,String[] types){
						logger.info("come into queryGroupByallutypes function!");
						String str=getStr(userids,"UserID");
						String strs=getStr(types,"GroupType");
						String sql="select GroupID,CenterID,GroupName,UserID,GroupType from nvmp_resourcegrouptab where "+str+"and "+strs;
						String sqlrgm="select GroupID,ParentID,ResourceID,ResourceName,ResourceType,SortIndex from nvmp_resourcegroupmembertab where GroupID in (select GroupID from nvmp_resourcegrouptab where "+str+"and "+strs+")";
						String sqlrgma="select GroupID,ResourceID,AttributeType,AttributeValue from nvmp_resourcegroupattrtab where GroupID in ( select GroupID from nvmp_resourcegrouptab where "+str+"and "+strs+");";
						logger.info("query groupinfoallutypes sql:"+sql);
						logger.info("query groupmenbersinfoallutypes sql:"+sqlrgm);
						logger.info("query groupmenberatrrinfoallutypes sql:"+sqlrgma);
						return doQuery(sql,sqlrgm,sqlrgma);
					}
					
					public String getStr(String[] strs,String type){
						String str="(";
						  for (int i = 0; i < strs.length-1; i++) {
						   str+=type+"='"+strs[i]+"' or ";
						  }
						  str+=type+"='"+strs[strs.length-1]+"')";
						  return str;
					}
					
					public List<ResourceGroup> doQuery(String sql,String sqlrgm,String sqlrgma){
						List<Object[]> listAllAttribute=querySql(sqlrgma);
						List<Object[]> listAllMember=querySql(sqlrgm);
						List<Object[]> listAllGroup=querySql(sql);
						
						List<ResourceGroup> groups = new ArrayList<ResourceGroup>();
						for (Object[] oGourp : listAllGroup) {
							ResourceGroup group = new ResourceGroup();
							group.setGroupId(oGourp[0]==null?"":oGourp[0].toString());
							group.setCenterId(oGourp[1]==null?"":oGourp[1].toString());
							group.setGroupName(oGourp[2]==null?"":oGourp[2].toString());
							group.setUserId(oGourp[3]==null?"":oGourp[3].toString());
							group.setGroupType(oGourp[4]==null?"":oGourp[4].toString());
							List<ResourceGroupMember> members = new ArrayList<ResourceGroupMember>();
							for (Object[] oMember : listAllMember) {
								if((oMember[0]==null?"":oMember[0].toString()).equals(group.getGroupId())){
									ResourceGroupMember rgm=new ResourceGroupMember();
									rgm.setParentId(oMember[1]==null?"":oMember[1].toString());
									rgm.setResourceId(oMember[2]==null?"":oMember[2].toString());
									rgm.setResourceName(oMember[3]==null?"":oMember[3].toString());
									rgm.setResourceType(oMember[4]==null?"":oMember[4].toString());
									rgm.setSortIndex((Integer)(oMember[5]==null?"":oMember[5]));
									List<ResGpMemAttribute> memberattrs = new ArrayList<ResGpMemAttribute>();
									for (Object[] oMemberattr : listAllAttribute) {
										if((oMemberattr[0]==null?"":oMemberattr[0].toString()).equals(group.getGroupId())&& (oMemberattr[1]==null?"":oMemberattr[1].toString()).equals(rgm.getResourceId())){
											ResGpMemAttribute rgma=new ResGpMemAttribute();
											rgma.setAttributeType(oMemberattr[2]==null?"":oMemberattr[2].toString());
											rgma.setAttributeValue(oMemberattr[3]==null?"":oMemberattr[3].toString());
											memberattrs.add(rgma);
										}
									}
									rgm.setAttributes(memberattrs);
									members.add(rgm);
								}
							}
							group.setMembers(members);
							groups.add(group);
						}
						return groups;
					}
}
