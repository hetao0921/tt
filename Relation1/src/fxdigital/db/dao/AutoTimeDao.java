package fxdigital.db.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.fxdigital.bean.SyncDataSet;
import com.hibernate.ConvertMapUtil;
import com.hibernate.Hibernate;

/**
 * sync_date_set
 * @author hxht
 * @version 2014-8-1
 */
@Repository
public class AutoTimeDao {
	@Autowired
	private Hibernate hibernate;
	public String query(){
		String map=ConvertMapUtil.map(SyncDataSet.class);
		String sql = "select "+map+" from SyncDataSet";
		List<HashMap<String, String>> list = hibernate.createQuery(sql);
		if(list == null || list.size() != 1){
			return null;
		}
		return  (list.get(0).get("autotime"));
	}
	public int updateAutotime(String autotime){
		int bak=0;
		String sqls ="delete from SyncDataSet";
		hibernate.deleteOrUpdate(sqls, null);
		
		Date d = new Date();
		System.out.println(d);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateNowStr = sdf.format(d);

		
//		String sqlss = "insert into nvmp.sync_data_set(autotime,setdate) values(?,?)";
		SyncDataSet set=new SyncDataSet();
		set.setAutotime(Integer.parseInt(autotime));
		set.setSetdate(dateNowStr);
//		bak= jdbcTemplate.update(sqlss, new Object[]{Integer.parseInt(autotime),dateNowStr});
//		System.out.println("jdbcTemplate执行完inssert后返回的bak值："+bak);
		hibernate.save(set);
		return 1;
	}
}
