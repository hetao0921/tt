package mavenweb;

import com.alibaba.fastjson.JSONObject;
import com.fxdigital.analysis.bean.ResourceGroup;

public class ParseJson {
	public static void main(String[] args) {
		String s="{'groupId':'663e1a1f-94b3-4cc4-9e60-e1aed3a2d044','groupName':'fdgfdgf','groupType':'GlobalCommand','userId':'0000','centerId':'000BAB65C2ED@001','members':[{'resourceId':'e20437c3fad24d5b979aa3cfad360b68','resourceName':'fdgfdgf','resourceType':'Department','parentId':null,'sortIndex':0,'attributes':[{'attributeType':'','attributeValue':''}]},{'resourceId':'63376b7b7f404240a5c681059558b3e6','resourceName':'8142车','resourceType':'Department','parentId':'e20437c3fad24d5b979aa3cfad360b68','sortIndex':0,'attributes':[{'attributeType':'','attributeValue':''}]},{'resourceId':'d47e23d7e3744e558398f29d43dd33fd','resourceName':'8122车','resourceType':'Department','parentId':'63376b7b7f404240a5c681059558b3e6','sortIndex':0,'attributes':[{'attributeType':'','attributeValue':''}]},{'resourceId':'46c4ccb9c9a445b38cac28126999c895','resourceName':'8122车.8122-001','resourceType':'Commander','parentId':'d47e23d7e3744e558398f29d43dd33fd','sortIndex':0,'attributes':[{'attributeType':'','attributeValue':''}]},{'resourceId':'ea2d9fbce22444e9898f9e367390ab3a','resourceName':'8122车.8122-002','resourceType':'Commander','parentId':'d47e23d7e3744e558398f29d43dd33fd','sortIndex':1,'attributes':[{'attributeType':'','attributeValue':''}]},{'resourceId':'c250b9c7719a4cf7a8739ca5623fb378','resourceName':'1212车','resourceType':'Department','parentId':'63376b7b7f404240a5c681059558b3e6','sortIndex':1,'attributes':[{'attributeType':'','attributeValue':''}]}]}";
		ResourceGroup rg=JSONObject.parseObject(s, ResourceGroup.class);
		System.out.println(rg.getCenterId());
		
	}

}
