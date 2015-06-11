package NVMP.AppService.Implement;

import java.io.File;

import java.util.HashMap;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.VisitorSupport;
import org.dom4j.io.SAXReader;

/** 
 * by zzw ÓÃÀŽ¶ÁÈ¡ÕûžöxmlÎÄŒþ£¬œ«element¶ÁÈ¡œøÈë¡£
 * */
public class VisitXML extends VisitorSupport {

	private HashMap<String, HashMap<String, HashMap<String, String>>> hp;

	public VisitXML(String str) {
		// TODO Auto-generated constructor stub
		hp = new HashMap<String, HashMap<String, HashMap<String, String>>>();
		try {
			SAXReader reader = new SAXReader();
			File file = new File(str);
			Document dc;
			dc = reader.read(file);
			dc.accept(this);
		} catch (Exception e) {

		}

	}

	public HashMap<String, HashMap<String, HashMap<String, String>>> getHp() {
		return hp;
	}

	public void setHp(
			HashMap<String, HashMap<String, HashMap<String, String>>> hp) {
		this.hp = hp;
	}

	/**
	 * by zzw Ö÷ÒªÌæ»»µÄµØ·œ £¬°ŽÕÕœÚµãµÄ·ÅÈëÏà¹ØÊýŸÝ¡£
	 * 
	 * */
	@Override
	public void visit(Element node) {
		// TODO Auto-generated method stub
		if (!hp.containsKey(node.getName())) {
			hp.put(node.getName(),
					new HashMap<String, HashMap<String, String>>());
		}

		if (node.getName().equals("Class")) {
			HashMap<String, HashMap<String, String>> h = hp.get(node.getName());
			if (!h.containsKey(node.attribute("id").getValue())) {
				h.put(node.attribute("id").getValue(),
						new HashMap<String, String>());
//				System.out.println(node.attribute("id").getValue());
				
			}
		}
		if (node.getName().equals("message")) {
			HashMap<String, String> h = hp.get(node.getParent().getName()).get(
					node.getParent().attribute("id").getValue());
			h.put(node.attribute("id").getValue(), node.attribute("text")
					.getValue());
		}
		if (node.getName().equals("Group")) {
			HashMap<String,  HashMap<String, String>> h = hp.get(node.getName());
			h.put(node.attributeValue("id"), null);
		}
		
		

	}

}
