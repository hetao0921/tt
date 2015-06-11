package com.fx.db;

import java.util.HashMap;
import java.util.List;

import com.fx.bean.NvmpRecordStorageInfo;
import com.fx.hibernate.ConnDo;
import com.fx.hibernate.ConvertMapUtil;

public class TestHibernate {
	public static void main(String[] args) {
		
		
		String map=ConvertMapUtil.map(NvmpRecordStorageInfo.class);
		String sql = "select "+map+" from NvmpRecordStorageInfo";
		
		List<HashMap<String,String>> list = ConnDo.getConnDo().executeQuery(sql);
		
		for(HashMap<String,String> mapstr:list){
			System.out.println(mapstr);
		}
	}

}
