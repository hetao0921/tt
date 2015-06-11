package NVMP.AppService.Implement;
import NVMP.AppService.Interface.*;
import java.util.ArrayList;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.IOException;  
import java.io.StringReader;  
import java.util.List;      

class DomainFactory
{
	private static String getSubElementText(Element ele, String subNode)
	{
		String text = "";
		try{
			text = ((Element)ele.element(subNode)).getTextTrim();
		}catch(Exception e)
		{ 
			
		}
		return text;
	}

	public static ArrayList<IBusinessDomain> getDomains()
	{
		ArrayList<IBusinessDomain> domains = new ArrayList<IBusinessDomain>();

		try {
			SAXReader saxReader = new SAXReader();
			Document doc = saxReader.read(new File(SysProperty.getConfPath()));

			List comList = doc.getRootElement().element("AppServer").element("components").elements("component");
			IComLoader loader = new JComLoader();
			String defaultBusinessDirectory = SysProperty.getDefaultBusinessDirectory();
			for (int i = 0; i < comList.size(); i++)
			{
				Element ele = (Element)comList.get(i); 

				boolean enable     = getSubElementText(ele, "eanble").equals("false")?false:true;
				String  desc       = getSubElementText(ele, "desc");
				String  location   = getSubElementText(ele, "location");
				String  domainName = getSubElementText(ele, "domain");
				
				System.out.println("name:       " + ele.attributeValue("name"));
				System.out.println("desc:       " + desc);		
				System.out.println("location:   " + location);
				System.out.println("domainName: " + ((domainName.length()>0)?domainName:"未指定") + "\n");

				if (!enable)
				{
					// log ...
					continue;
				}

				if (location.length() == 0)
				{
					// log ...
					continue;
				}

				if (System.getProperty("os.name").equals("Linux"))
				{
					if (!location.substring(0, 1).equals("/"))				
						location = defaultBusinessDirectory + File.separator + location;

				}
				else
				{
					if (location.length() > 1 && location.charAt(1) != ':')
						location = defaultBusinessDirectory + File.separator + location;
				}
               System.out.println("domain location"+ location);
				if (domainName.length() > 0)
				{
					IBusinessDomain domain = loader.LoadBusinessComponent(location, domainName);

					if (domain == null)
					{
						// log ...
						continue;
					}

		
					domains.add(domain);
				}
				else
				{
					ArrayList<IBusinessDomain> domainList = loader.LoadBusinessComponent(location);
					if (domainList.size() == 0)
					{
						// log ...
						continue;
					}

					domains.addAll(domainList);
				}

			}

		}
		catch(Exception e)
		{
			System.out.println("getDomains 读配置文件异常：" + e.getMessage());	
			e.printStackTrace();
		}	

		return domains;		
	}
}




