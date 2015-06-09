package mavenweb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.alibaba.fastjson.JSONObject;
import com.fxdigital.analysis.bean.Equality;

public class TestJson {
	public static void main(String[] args) {
/*		Equality equality=new Equality();
		String id=UUID.randomUUID().toString();
		equality.setEqualityCenterId("000BAB65C307@001");
		equality.setOperate("2");
		equality.setSetId(id);
		equality.setType("equality");
		List<String> list=new ArrayList<String>();
		list.add("7427EA0D1E3E@001");
		list.add("B8975A5FEA2B@001");
		equality.setMainCenters(list);
		String equa =JSONObject.toJSONString(equality);
		System.out.println(equa);
		HashMap<String,Object> hp=new HashMap<String,Object>();
		hp.put("equalityCenterId", "1");
		hp.put("operate", "2");
		hp.put("setId", "3");
		hp.put("mainCenters", list);
		String hpo=JSONObject.toJSONString(hp);
		System.out.println(hpo);
		System.out.println(equa.equals(hpo));
		Equality hpor=JSONObject.parseObject(hpo,Equality.class);*/
	}
	
	public void exchange(){
		String json="{\"id\":\"12\"}";
		Equality hpor=JSONObject.parseObject(json,Equality.class);
	}
	

}
