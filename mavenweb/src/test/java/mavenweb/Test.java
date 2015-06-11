package mavenweb;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.alibaba.fastjson.JSONObject;
import com.fxdigital.analysis.bean.Equality;
import com.fxdigital.analysis.bean.ResourceGroup;

public class Test {
	public static void main(String[] args) {/*
		Equality equality=new Equality();
		equality.setSetId(UUID.randomUUID().toString());
		equality.setOperate("1");
		equality.setEqualityCenterId("7427EA0D1E3E@001");
		List<String> list=new ArrayList<String>();
		list.add("000BAB65C211@001");
		list.add("000BAB65C211@001");
		equality.setMainCenters(list);
		String json=JSONObject.toJSONString(equality);
		System.out.println(json);
	*/
		ResourceGroup  group =new ResourceGroup();
		group.setGroupId("12121");
		String json=JSONObject.toJSONString(group);
		ResourceGroup group1=JSONObject.parseObject("{\"groupId\":\"e4ea248a-5fb7-4cbc-a1e3-4abf0b6860f4\"}",ResourceGroup.class);
		
		System.out.println(group1.getGroupId());
	}

}
