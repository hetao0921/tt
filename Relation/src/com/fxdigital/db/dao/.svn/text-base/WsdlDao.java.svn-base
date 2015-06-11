package com.fxdigital.db.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hibernate.bean.NvmpWsdlinfo;
import com.hibernate.bean.NvmpWsdls;
import com.hibernate.db.ConnDo;

@Component
public class WsdlDao {
	
	public List<?> wsdls_show() {
		String hql = "from NvmpWsdls";
		return ConnDo.getConnDo().executeQueryToList(hql);
	}
	
	public List<?> wsdlInfo_show() {
		String hql = "from NvmpWsdlinfo";
		return ConnDo.getConnDo().executeQueryToList(hql);
	}
	
	public List<?> selectByWsdlURI(String wsdlURI) {
		String hql = "from NvmpWsdlinfo where wsdlUri='"+wsdlURI+"'";
		return ConnDo.getConnDo().executeQueryToList(hql);
	}
	
	public int edit_wsdl(String sURI,String uri,String desc){
		String hql = "update NvmpWsdls set wsdlUri='%s',wsdlDesc='%s' where wsdlUri='%s'";
		hql = String.format(hql, uri,desc,sURI);
		return ConnDo.getConnDo().executeUpdate(hql);
	}

	public int edit_wsdlInfo(int wsdlInfoId,String functionName,String functionDesc,String arguments,String results){
		String hql = "update NvmpWsdlinfo set functionName='%s',functionDesc='%s',arguments='%s',results='%s' where wsdlInfoId=%d";
		hql = String.format(hql, functionName,functionDesc,arguments,results,wsdlInfoId);
		return ConnDo.getConnDo().executeUpdate(hql);
	}
	public int edit_wsdlURI(String sURI,String uri){
		String hql = "update NvmpWsdlinfo set wsdlUri=? where wsdlUri=?";
		hql = String.format(hql, uri,sURI);
		return ConnDo.getConnDo().executeUpdate(hql);
	}
	
	public int selectByfunctionName(String functionName){
		String hql = "delete from NvmpWsdlinfo where functionName='"+functionName+"'";
		return ConnDo.getConnDo().executeUpdate(hql);
	}
	
	public int insert(String wsdlUri,String wsdlDesc,String functionName,String functionDesc,String arguments,String results){
		NvmpWsdls wsdl = new NvmpWsdls();
		wsdl.setWsdlUri(wsdlUri);
		wsdl.setWsdlDesc(wsdlDesc);
		
		NvmpWsdlinfo wsdlinfo = new NvmpWsdlinfo();
		wsdlinfo.setWsdlUri(wsdlUri);
		wsdlinfo.setFunctionName(functionName);
		wsdlinfo.setFunctionDesc(functionDesc);
		wsdlinfo.setArguments(arguments);
		wsdlinfo.setResults(results);
		
		ConnDo.getConnDo().save(wsdlinfo);
		if(ConnDo.getConnDo().save(wsdl))
			return 1;
		else
			return -1;
	}
	
	public int insertInfo(String wsdlUri,String functionName,String functionDesc,String arguments,String results){
		NvmpWsdlinfo wsdlinfo = new NvmpWsdlinfo();
		wsdlinfo.setWsdlUri(wsdlUri);
		wsdlinfo.setFunctionName(functionName);
		wsdlinfo.setFunctionDesc(functionDesc);
		wsdlinfo.setArguments(arguments);
		wsdlinfo.setResults(results);
		if(ConnDo.getConnDo().save(wsdlinfo))
			return 1;
		else
			return -1;
	}
	
	public int delete(String wsdlURI){
		String hql = "delete from NvmpWsdls where wsdlUri='"+wsdlURI+"'";
		String hqlPlus="delete from NvmpWsdlinfo where wsdlUri='"+wsdlURI+"'";
		ConnDo.getConnDo().executeUpdate(hqlPlus);
		return ConnDo.getConnDo().executeUpdate(hql);
	}
	
	public int deleteInfo(String wsdlInfoId){
		String hql = "delete from NvmpWsdlinfo where wsdlInfoId='"+wsdlInfoId+"'";
		return ConnDo.getConnDo().executeUpdate(hql);
	}

}
