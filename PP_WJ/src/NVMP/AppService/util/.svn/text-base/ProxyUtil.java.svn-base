package NVMP.AppService.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.misc.log.*;

/**
 * by zzw ŽŠÀíŽúÀíÀàµÄ¹€ŸßÀà »º³åÇøÓÃÀŽ±£ŽæÔËÐÐÖÐµÄž÷ÖÖÐÅÏ¢
 * 
 * */
public class ProxyUtil {

	// »º³åÇø
	static private HashMap<String, ProxyFile> proxyHp = new HashMap<String, ProxyFile>();

	// ÅÐ¶ÏŽæÔÚ
	static public boolean isExists(String domainName) {

		return proxyHp.containsKey(domainName);

	}

	// 获取生成代理文件的目录
	public static String getGenProxyDireictory() {
		if (System.getProperty("os.name").equals("Linux"))
			return "/tmp/";
		else
			return "d:\\";
	}

	// ÌáÈ¡,Èç¹ûÃ»ÓÐÉú³ÉÒ»žöÐÂµÄ
	static public ProxyFile getProxyFile(String domainName) {

		ProxyFile pf = null;

		if (isExists(domainName)) {
			pf = proxyHp.get(domainName);

		} else {
			pf = new ProxyFile(domainName);
			proxyHp.put(domainName, pf);
		}
		return pf;
	}

	// ÒÆ³ý
	static public boolean removeProxyFile(String domainName) {
		if (isExists(domainName)) {
			proxyHp.remove(domainName);
			return true;
		} else {

			return false;
		}

	}

	// ÅÐ¶Ï±£ŽæÎÄŒþÊýÁ¿
	static public int countProxy() {

		return proxyHp.size();

	}

	// Éú³ÉC#ÎÄŒþ

	static private String typeChange(String typeStr) {
		String str = typeStr;
		if (typeStr.equals("java.lang.String")) {

			str = "string";
		}
		if (typeStr.equals("java.lang.Boolean")) {

			str = "bool";
		}
		if (typeStr.equals("java.lang.Integer")) {

			str = "int";
		}
		return str;

	}

	static private String sourceChange(ParamInfo pi) {
		String str = "";
		if (pi.getType().equals("java.lang.String")) {
			str = " string " + pi.getName() + " =  retValue[\"" + pi.getName()
					+ "\"].ToString(); \n";

		}
		if (pi.getType().equals("java.lang.Boolean")) {
			str = " bool " + pi.getName() + ";\n";
			str = str + "if(retValue[\"" + pi.getName()
					+ "\"].ToString().Equals(\"\")) { \n" + pi.getName()
					+ " = false;  \n } else { \n";
			str = str + pi.getName() + " = retValue[\"" + pi.getName()
					+ "\"].ToString().Equals(\"true\") ? true : false; \n } \n";

		}
		if (pi.getType().equals("java.lang.Integer")) {
			str = " int " + pi.getName() + ";\n";
			str = str + "if(retValue[\"" + pi.getName()
					+ "\"].ToString().Equals(\"\")) { \n" + pi.getName()
					+ " = -1;  \n } else { \n";
			str = str + pi.getName() + " = int.Parse(retValue[\""
					+ pi.getName() + "\"].ToString()); \n } \n";

		}

		return str;
	}

	static private String sourceChange_J(ParamInfo pi) {
		String str = "";
		if (pi.getType().equals("java.lang.String")) {
			str = " String " + pi.getName() + " =  retValue.get(\""
					+ pi.getName() + "\").toString(); \n";

		}
		if (pi.getType().equals("java.lang.Boolean")) {
			str = " Boolean " + pi.getName() + ";\n";
			str = str + "if(retValue.get(\"" + pi.getName()
					+ "\").toString().equals(\"\")) { \n" + pi.getName()
					+ " = null ; \n } else { \n";

			str = str + pi.getName() + " = Boolean.valueOf(retValue.get(\""
					+ pi.getName() + "\").toString());\n}\n";

		}
		if (pi.getType().equals("java.lang.Integer")) {
			str = " Integer " + pi.getName() + ";\n";
			str = str + "if(retValue.get(\"" + pi.getName()
					+ "\").toString().equals(\"\")) { \n" + pi.getName()
					+ " = null;  \n } else { \n";
			str = str + pi.getName() + " = Integer.valueOf(retValue.get(\""
					+ pi.getName() + "\").toString()); \n }\n";
		}

		return str;
	}
	static public void printCfile(String domainName) {
		if (isExists(domainName)) {
			ProxyFile pf = proxyHp.get(domainName);

			String strFile = "using System.Collections.Generic;\n"
					+ "using NVMP.RunTime;\n"
					+ "using NVMP.AppService.Interface;\n"
					+ "using NVMP.Command.Business;\n"
					+ "using corenet.exchange;\n" + "using corenet.netbase;\n"
					+ "namespace NVMP.Proxy \n { \n";

			// ÉùÃ÷ÊÂŒþ

			// ArrayList tempL = new ArrayList();
			for (ProxyObj obj : pf.getEventList()) {
				// if(tempL.contains(obj)) {
				// break;
				// } else {
				// tempL.add(obj);
				// }

				strFile = strFile + " public delegate void " + obj.getName()
						+ "Event(";
				int i = 0;
				for (ParamInfo pi : obj.getParamInfoArray()) {
					if (i != 0) {
						strFile = strFile + ",";
					} else {
						i++;
					}
					strFile = strFile + typeChange(pi.type) + " " + pi.name;
				}
				strFile = strFile + "); \n";
			}

			// ÓòÃû
			strFile = strFile + " public class " + domainName + "Proxy \n { \n"
					+ "private IRunTime RunTime;\n" + "private static "
					+ domainName + "Proxy Instance = null;\n"
					+ "private   ExchangeClient client;\n";

			strFile = strFile + " public static " + domainName
					+ "Proxy NewInstance(ExchangeClient _client)  \n"
					+ " { \n if (Instance == null) \n { \n Instance = new "
					+ domainName + "Proxy(_client); \n } \n"
					+ "return Instance; \n } \n";

			strFile = strFile + " public static " + domainName
					+ "Proxy GetInstance()  \n" + " { \n"
					+ "return Instance; \n } \n";

			strFile = strFile + " private " + domainName
					+ "Proxy( ExchangeClient _client ) \n"
					+ "{ \n RunTime = new RunTimeImpl(); \n "
					+ "RunTime.Event += EventResult; \n"
					+ "this.client = _client; \n"
					+ "RunTime.setTransChannel(client); \n} \n ";

			strFile = strFile
					+ "public void EventResult(string EventURL, Dictionary<string, object> retValue) \n { \n "
					+ "try { \n";
			// žùŸÝÊÂŒþœøÐÐÐŽÈë
			for (ProxyObj poj : pf.getEventList()) {
				String newName = poj.getName().replaceFirst("^On", "");
				strFile = strFile + "if (EventURL == \"" + poj.getDomainName()
						+ "\") \n { \n ";

				for (ParamInfo pi : poj.getParamInfoArray()) {

					strFile = strFile + sourceChange(pi);
				}

				strFile = strFile + "this." + newName + "Event(";
				int i = 0;
				for (ParamInfo pi : poj.getParamInfoArray()) {
					if (i != 0) {
						strFile = strFile + ",";
					} else {
						i++;
					}
					strFile = strFile + pi.getName();
				}
				strFile = strFile + " ); \n } \n";
			}
			strFile = strFile
					+ "\n } catch (System.Exception e) {\n"
					+ "string ErrorInfo = string.Format(\"中心服务反馈指令处理错误：方法名称为：a{0}\",EventURL); \n"
					+ " NVMP.Util.LogUtil.error(ErrorInfo + e.ToString()); \n"
					+ " NVMP.Util.LogUtil.ErrorMsg(EventURL,retValue); \n"
					+ " \n} \n } \n";

			// ¿ªÊŒÊµÏÖž÷žöÉùÃ÷
			for (ProxyObj poj : pf.getEventList()) {
				String newName = poj.getName().replaceFirst("^On", "");
				strFile = strFile + "public event " + poj.getName() + "Event "
						+ poj.getName() + "; \n " + "public void " + newName
						+ "Event(";
				int i = 0;
				for (ParamInfo pi : poj.getParamInfoArray()) {
					if (i != 0) {
						strFile = strFile + ",";
					} else {
						i++;
					}
					strFile = strFile + typeChange(pi.getType()) + " "
							+ pi.getName();
				}
				strFile = strFile + ") \n { \n if (" + poj.getName()
						+ " != null) \n { \n " + poj.getName() + "(";

				i = 0;
				for (ParamInfo pi : poj.getParamInfoArray()) {
					if (i != 0) {
						strFile = strFile + ",";
					} else {
						i++;
					}
					strFile = strFile + pi.getName();
				}
				strFile = strFile + ");\n } \n }";
			}

			// ¿ªÊŒÊµÏÖž÷žö·œ·š
			for (ProxyObj poj : pf.getFunctionList()) {
				strFile = strFile + "  public virtual void " + poj.getName() + "(";
				int i = 0;
				for (ParamInfo pi : poj.getParamInfoArray()) {
					if (i != 0) {
						strFile = strFile + ",";
					} else {
						i++;
					}
					strFile = strFile + typeChange(pi.getType()) + " "
							+ pi.getName();
				}
				strFile = strFile
						+ ") \n { \n  string url = \""
						+ poj.getDomainName()
						+ "\"; \n"
						+ "Dictionary<string, object> Params = new Dictionary<string, object>(); \n";
				for (ParamInfo pi : poj.getParamInfoArray()) {
					strFile = strFile + "Params.Add(\"" + pi.getName() + "\", "
							+ pi.getName() + "); \n";
				}
				strFile = strFile
						+ "  if(RunTime!=null) RunTime.Invoke(url, Params, null, null); \n } \n ";

			}

			strFile = strFile + "} \n }";

			// System.out.println(strFile);

			File f = new File(getGenProxyDireictory() + domainName + ".txt");
			try {
				FileWriter fw = new FileWriter(f);
				fw.write(strFile);
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

			LogUtil.debug("ÎÄŒþ²»ŽæÔÚž±±Ÿ£¬ÎÄŒþÃû£º" + domainName);

		}

	}
	private static final int MAXPARAM = 8;
	
	private static String getParamCPP(ParamInfo pi)
	{
		String typeStr = pi.type;
		String str = "";
		if (typeStr.equals("java.lang.String")) {
			str =  "rpcMessage->getParamString(\"" + pi.name + "\", \"\")";
		} else if (typeStr.equals("java.lang.Boolean")) {
			str =  "rpcMessage->getParamBool(\"" + pi.name + "\", false)";
		} else if (typeStr.equals("java.lang.Integer")) {
			str =  "rpcMessage->getParamInt(\"" + pi.name + "\", -1)";
		} else {
			str =  "rpcMessage->getParamString(\"" + pi.name + "\", \"\")";
		}
		return str;
	}
	
	static private boolean hasLargeEventParam(ProxyFile pf) {
		for (ProxyObj obj : pf.getEventList()) {
			if (obj.getParamInfoArray().length > MAXPARAM) {
				return true;
			}
		}
		return false;
	}
	static public void printCPPfile(String domainName) {

		if (isExists(domainName)) {
			ProxyFile pf = proxyHp.get(domainName);
			boolean largeEventParam = hasLargeEventParam(pf);
			
			String strFile = "/* DO NOT EDIT THIS FILE - it is machine generated */\n"
						   + "#ifndef H_" + domainName + "Proxy" + "\n"
					       + "#define H_" + domainName + "Proxy" + "\n\n"
					       + "#include <iostream>\n"
					       + "#include <exception>\n"
					       + "#include \"RMASession.h\"\n\n"
					       + "namespace NVMP { namespace Proxy {\n\n";
			if (largeEventParam) {
				strFile = strFile + "struct __Base" + domainName + "Event" + "Param {"
				        + "virtual ~__Base" + domainName + "Event" + "Param(){}" + "};\n\n";
			}
			strFile = strFile + "// signal(event) declare\n";
			for (ProxyObj obj : pf.getEventList()) {

				if (obj.getParamInfoArray().length > MAXPARAM) {
					strFile = strFile + "struct " + obj.getName() + "Event" + "Param : public " 
				            + "__Base" + domainName + "Event" + "Param\n{\n";
					int i = 0;
					
					// fields
					for (ParamInfo pi : obj.getParamInfoArray()) {
						String type = typeChange(pi.type);
						if (type.equals("string"))
							type = "const std::string";
						else
							type = "const " + type;
						strFile = strFile + "\t" + type + " " + pi.name +";\n";
					}
					
					// constructor 
					strFile = strFile + "\t" + obj.getName() + "Event" + "Param(";
					for (ParamInfo pi : obj.getParamInfoArray()) {
						if (i != 0) {
							strFile = strFile + ", ";
						} else {
							i++;
						}
						String type = typeChange(pi.type);
						if (type.equals("string"))
							type = "const std::string";
						strFile = strFile + type + " __" + pi.name;
					}					
					strFile = strFile + "):";
					
					i = 0;
					for (ParamInfo pi : obj.getParamInfoArray()) {
						if (i != 0) {
							strFile = strFile + ", ";
						} else {
							i++;
						}
						strFile = strFile + pi.name + "(__" + pi.name + ")";
					}
					
					strFile = strFile + "{}\n};\n";
					
					strFile = strFile + "typedef boost::signal<void (";
					strFile = strFile + obj.getName() + "Event" + "Param* param)>";
					strFile = strFile + "__" + obj.getName() + "Event;\n";
				} else {
					// typedef boost::signal<void (std::string a, std::string b, bool c)>  DeviceOnlineEvent;
					strFile = strFile + "typedef boost::signal<void (";
					int i = 0;
					for (ParamInfo pi : obj.getParamInfoArray()) {
						if (i != 0) {
							strFile = strFile + ", ";
						} else {
							i++;
						}
						strFile = strFile + typeChange(pi.type) + " " + pi.name;
					}
					strFile = strFile + ")> __" + obj.getName() + "Event;\n";
				}
				
			}
			strFile = strFile + "\nclass " + domainName + "Proxy\n{\n"
			        + "private:\n"
			        + "\tRMASessionPtr _session;\n"
			        + "public:\n";
			
			strFile = strFile + "\t// events\n";
			for (ProxyObj poj : pf.getEventList()) {
				String newName = poj.getName().replaceFirst("^On", "");
				if (newName.equals(poj.getName()))
					newName = newName.replaceFirst("^on", "");
				strFile = strFile + "\t__" + poj.getName() + "Event " + newName + "Event;\n";
			}
			      
			strFile = strFile + "\n\tvoid setRMASession(RMASessionPtr session)\n"
			        + "\t{\n"
			        + "\t\t_session = session;\n"
			        + "\t\t_session->connectRecvMessageEvent(boost::bind(&" + domainName + "Proxy"
			        + "::OnRecvRPCMessage, this, _1));\n"
			        + "\t}\n\n";
			
			strFile = strFile + "\t// event dispatch\n"
					+ "\tvoid OnRecvRPCMessage(BaseMessage *rpcMessage)\n\t{\n ";
			
			if (largeEventParam) {
				strFile = strFile + "\t\t__Base" + domainName + "Event" + "Param *__param = NULL;\n";
			}
			
			strFile = strFile + "\t\tconst std::string& EventURL = rpcMessage->getObjURL();\n";

			strFile = strFile + "\t\ttry {\n";
			
			// žùŸÝÊÂŒþœøÐÐÐŽÈës
			int j = 0;
			for (ProxyObj poj : pf.getEventList()) {
				String newName = poj.getName().replaceFirst("^On", "");
				if (newName.equals(poj.getName()))
					newName = newName.replaceFirst("^on", "");
				if (j == 0) {
					strFile = strFile + "\t\t\tif (EventURL == \"" + poj.getDomainName()
							+ "\")\n\t\t\t{\n ";
				} else {
					strFile = strFile + "\t\t\telse if (EventURL == \"" + poj.getDomainName()
							+ "\")\n\t\t\t{\n ";					
				}

				if (poj.getParamInfoArray().length > MAXPARAM) {
					strFile = strFile + "\t\t\t\t__param = new " + poj.getName() + "Event" + "Param(";
					int i = 0;
					for (ParamInfo pi : poj.getParamInfoArray()) {
						if (i != 0) {
							strFile = strFile + ", ";
						} else {
							i++;
						}
						strFile = strFile + getParamCPP(pi);
					}
					strFile = strFile + " );\n";
					
					strFile = strFile + "\t\t\t\tthis->" + newName + "Event(" 
							+ "(" + poj.getName() + "EventParam*) __param);\n"
							+ "\t\t\t\tdelete (" + poj.getName() + "EventParam*)" + "__param;\n"
							+ "\t\t\t\t__param = NULL;\n";
					
				} else {
					strFile = strFile + "\t\t\t\tthis->" + newName + "Event(";
					int i = 0;
					for (ParamInfo pi : poj.getParamInfoArray()) {
						if (i != 0) {
							strFile = strFile + ", ";
						} else {
							i++;
						}
						strFile = strFile + getParamCPP(pi);// pi.getName();
					}
					strFile = strFile + " );\n";
				}
						
				strFile = strFile + "\t\t\t}\n";
						
				j++;
			}
						
			strFile = strFile + "\n\t\t} catch (const std::exception& e) {\n";
			if (largeEventParam) {
				strFile = strFile + "\t\t\tif(__param != NULL) {delete __param; __param = NULL;}\n";
			}
			strFile = strFile + "\t\t\tstd::cerr << \"event\" <<  EventURL << \"dispatch exception: \" << e.what() << std::endl;\n"
					+ "\t\t}"
					+ " catch (...) {\n";
			if (largeEventParam) {
				strFile = strFile + "\t\t\tif(__param != NULL) {delete __param; __param = NULL;}\n";
			}					
					
			strFile = strFile + "\t\t\tstd::cerr << \"event\" <<  EventURL << \"dispatch exception: unknow\" << std::endl;\n"
					+ "\t\t}\n"
					+ "\t}\n\n";

			strFile = strFile + "\t// rpc method\n";
			// ¿ªÊŒÊµÏÖž÷žö·œ·š
			for (ProxyObj poj : pf.getFunctionList()) {
				strFile = strFile + "\tvoid " + poj.getName() + "(";
				int i = 0;
				for (ParamInfo pi : poj.getParamInfoArray()) {
					if (i != 0) {
						strFile = strFile + ", ";
					} else {
						i++;
					}
					String type = typeChange(pi.getType());
					if (type.equals("string"))
						type = "const std::string&";
					
					strFile = strFile + type + " " + pi.getName();
				}
				strFile = strFile
						+ ") \n\t{\n\t\tconst std::string url = \""
						+ poj.getDomainName()
						+ "\"; \n"
						+ "\t\tRPCRequestMessage message(url);\n";
				for (ParamInfo pi : poj.getParamInfoArray()) {
					strFile = strFile + "\t\tmessage.addParam(\"" + pi.getName() + "\", "
							+ pi.getName() + ");\n";
				}
				strFile = strFile
						+ "\t\t_session->sendRPCMessage(&message); \n\t}\n\n";

			}

			strFile = strFile + "};\n";
			strFile = strFile + "};}; // end namespace NVMP::Proxy\n";
			
			strFile  = strFile + "#endif // end H_" + domainName + "Proxy" + "\n";

			// System.out.println(strFile);

			File f = new File(getGenProxyDireictory() + domainName + "Proxy.hpp");
			try {
				FileWriter fw = new FileWriter(f);
				fw.write(strFile);
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

			LogUtil.debug("ÎÄŒþ²»ŽæÔÚž±±Ÿ£¬ÎÄŒþÃû£º" + domainName);

		}

	}

	/**
	 * ŽòÓ¡java¿Í»§¶ËŽúÀí
	 * 
	 * @author zzw
	 * @param domainName
	 *            ÎÄŒþÃû
	 * 
	 * **/

	static public void printJfile(String domainName) {
		if (isExists(domainName)) {

			ProxyFile pf = proxyHp.get(domainName);

			String strFile = "package NVMP.Proxy; \n"
					+ "import java.util.HashMap; \n"
					+ "import Runtime.IRunTime; \n"
					+ "import Runtime.impl.RunTime; \n"
					+ "import Runtime.ReturnDo;\n";
			strFile = strFile + "public class " + domainName
					+ "  implements ReturnDo { \n";

			// ¿ªÊŒŒÇÂŒÊÂŒþµÄŒò»¯°æ
			// ÊÂŒþÊÇœáÎ²ŒÓÉÏEvent
			strFile = strFile + "static public class EventHandler {";
			for (ProxyObj poj : pf.getEventList()) {
				String newName = poj.getName().replaceFirst("^On", "");
				strFile = strFile + "public Object " + newName + "Event(";
				int i = 0;
				for (ParamInfo pi : poj.getParamInfoArray()) {
					if (i != 0) {
						strFile = strFile + ",";
					} else {
						i++;
					}
					strFile = strFile + pi.getType() + " " + pi.getName();
				}
				strFile = strFile + ") \n {return null; \n}\n";
			}

			strFile = strFile + "}\n";

			// ¿ªÊÇŒÇÂŒ·œ·š·µ»ØµÄŒò»¯°æ

			strFile = strFile + "static public class FunResultHandler {";
			for (ProxyObj poj : pf.getFunctionList()) {
				String newName = poj.getName();
				strFile = strFile + "public Object " + newName + "Result(";
				int i = 0;
				for (ParamInfo pi : poj.getReturnType()) {
					if (i != 0) {
						strFile = strFile + ",";
					} else {
						i++;
					}
					strFile = strFile + pi.getType() + " " + pi.getName();
				}
				strFile = strFile + ") \n {return null; \n}\n";
			}

			strFile = strFile + "}\n";

			// ÆÕÍšÄÇÐ©ÉùÃ÷

			strFile = strFile
					+ "private IRunTime run;\n"
					+ "public "
					+ domainName
					+ "(IRunTime run)\n { \n"
					+ "this.run = run; \n"
					+ "((RunTime)run).registerProxy(this.getClass().getSimpleName(), this);"
					+ "\n}\n";

			strFile = strFile + "public EventHandler eventhandler;\n"
					+ "public FunResultHandler funhandler;\n"
					+ "public void setEventhand(EventHandler eventhandler) {\n"
					+ "this.eventhandler = eventhandler;\n}\n "
					+ "public void setFunhand(FunResultHandler funhandler) {\n"
					+ "this.funhandler = funhandler;\n}\n";

			// ÊÂŒþ»ñÈ¡

			strFile = strFile
					+ "public void ReturnEvent(HashMap<String,Object> retValue,String EventURL) \n { \n";

			for (ProxyObj poj : pf.getEventList()) {
				String newName = poj.getName().replaceFirst("^On", "");
				// strFile = strFile + "if (EventURL.equals( \"" +
				// poj.getDomainName().substring(0,
				// poj.getDomainName().lastIndexOf(".")+1)+newName
				// + "\")) \n { \n ";
				strFile = strFile + "if (EventURL.equals( \""
						+ poj.getDomainName() + "\")) \n { \n ";
				for (ParamInfo pi : poj.getParamInfoArray()) {

					strFile = strFile + sourceChange_J(pi);
				}

				strFile = strFile
						+ "if(this.eventhandler!=null) {\n this.eventhandler."
						+ newName + "Event(";
				int i = 0;
				for (ParamInfo pi : poj.getParamInfoArray()) {
					if (i != 0) {
						strFile = strFile + ",";
					} else {
						i++;
					}
					strFile = strFile + pi.getName();
				}
				strFile = strFile + " ); \n } \n}\n";
			}

			strFile = strFile + "}\n";

			// ·œ·š»ñÈ¡
			strFile = strFile
					+ "public void ReturnFunction(HashMap<String,Object> retValue,String EventURL) \n { \n";

			for (ProxyObj poj : pf.getFunctionList()) {
				String newName = poj.getName();
				strFile = strFile + "if (EventURL.equals( \""
						+ poj.getDomainName() + "\")) \n { \n ";

				for (ParamInfo pi : poj.getReturnType()) {

					strFile = strFile + sourceChange_J(pi);
				}

				strFile = strFile
						+ " if(this.funhandler!=null) { \n this.funhandler."
						+ newName + "Result(";
				int i = 0;
				for (ParamInfo pi : poj.getReturnType()) {
					if (i != 0) {
						strFile = strFile + ",";
					} else {
						i++;
					}
					strFile = strFile + pi.getName();
				}
				strFile = strFile + " ); \n } \n}\n";
			}

			strFile = strFile + "}\n";

			// ¿ªÊŒÊµÏÖž÷žö·œ·š
			for (ProxyObj poj : pf.getFunctionList()) {
				strFile = strFile + "  public void " + poj.getName() + "(";
				int i = 0;
				for (ParamInfo pi : poj.getParamInfoArray()) {
					if (i != 0) {
						strFile = strFile + ",";
					} else {
						i++;
					}
					strFile = strFile + pi.getType() + " " + pi.getName();
				}
				strFile = strFile
						+ ") \n { \n  String url = \""
						+ poj.getDomainName()
						+ "\"; \n"
						+ "HashMap<String,Object> Params = new HashMap<String,Object>(); \n";
				for (ParamInfo pi : poj.getParamInfoArray()) {
					strFile = strFile + "Params.put(\"" + pi.getName() + "\", "
							+ pi.getName() + "); \n";
				}
				strFile = strFile
						+ "run.Invoke(url, Params, null, null); \n } \n ";

			}

			strFile = strFile + "} \n ";

			// System.out.println(strFile);

			File f = new File(getGenProxyDireictory() + domainName + ".java");
			try {
				FileWriter fw = new FileWriter(f);
				fw.write(strFile);
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

			LogUtil.debug("ÎÄŒþ²»ŽæÔÚž±±Ÿ£¬ÎÄŒþÃû£º" + domainName);
		}

	}

	/** ŽòÓ¡ËùÓÐµÄŽúÀíÀàC#°æµÄ */
	static public void pringCfileAll() {

		for (@SuppressWarnings("rawtypes")
		Map.Entry me : proxyHp.entrySet()) {
			printCPPfile(me.getKey().toString());// 生成 C++ 代理
			printCfile(me.getKey().toString());
			printJfile(me.getKey().toString());
			printLocalJFile(me.getKey().toString());

		}
	}

	static public void printLocalJFile(String domainName) {
		if (isExists(domainName)) {
			ProxyFile pf = proxyHp.get(domainName);

			String classname = domainName + "Proxy";
			String str = "package NVMP.Proxy;\n"
					+ "import java.util.HashMap;\n"
					+ "import corenet.rpc.BaseMessage;\n"
					+ "import NVMP.AppService.Interface.IAppRuntime;\n"
					+ "import corenet.rpc.IMessage;\n";

			str = str + "public class " + classname + " { \n"

			+ "private static IAppRuntime _AppRuntime = null; \n"

			+ "public void load(IAppRuntime AppRuntime) { \n"

			+ "	_AppRuntime = AppRuntime; \n } \n";

			// ¿ªÊŒ¶ÁÈ¡·œ·š£¬Í¬Ê±žÄ¶¯ ³ÉÎª·µ»ØÊýŸÝ
			for (ProxyObj po : pf.getFunctionList()) {

				str = str + "  static public IMessage " + po.getName() + "(";
				int i = 0;
				for (ParamInfo pi : po.getParamInfoArray()) {
					if (i != 0) {
						str = str + ",";
					} else {
						i++;
					}
					str = str + pi.getType() + " " + pi.getName();
				}

				str = str
						+ ") \n { \n  String url = \""
						+ po.getDomainName()
						+ "\"; \n"
						+ "HashMap<String, Object> Params = new HashMap<String, Object>(); \n";
				for (ParamInfo pi : po.getParamInfoArray()) {
					str = str + "Params.put(\"" + pi.getName() + "\", "
							+ pi.getName() + "); \n";
				}
				str = str + "BaseMessage Message = new BaseMessage();\n"
						+ "Message.AddParams(Params);\n"
						+ "Message.SetAction(\"Event\");\n"
						+ "Message.SetObjURL(url);\n"
						+ "return _AppRuntime.Dispatch(Message); \n } \n ";
			}

			for (ProxyObj po : pf.getFunctionList()) {

				str = str + "  static public IMessage " + po.getName()
						+ "_Copy(";
				int i = 0;
				for (ParamInfo pi : po.getParamInfoArray()) {
					if (i != 0) {
						str = str + ",";
					} else {
						i++;
					}
					str = str + pi.getType() + " " + pi.getName();
				}

				str = str
						+ ") \n { \n  String url = \""
						+ po.getDomainName()
						+ "\"; \n"
						+ "HashMap<String, Object> Params = new HashMap<String, Object>(); \n";
				for (ParamInfo pi : po.getParamInfoArray()) {
					str = str + "Params.put(\"" + pi.getName() + "\", "
							+ pi.getName() + "); \n";
				}
				str = str + "BaseMessage Message = new BaseMessage();\n"
						+ "Message.AddParams(Params);\n"
						+ "Message.SetAction(\"Event\");\n"
						+ "Message.SetObjURL(url);\n"
						+ "return Message; \n } \n ";
			}

			str = str + "}";

			File f = new File(getGenProxyDireictory() + classname + ".java");
			try {
				FileWriter fw = new FileWriter(f);
				fw.write(str);
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

			LogUtil.debug("Éú³ÉŽúÀíÎÄŒþÊ±ºò²»ŽæÔÚž±±Ÿ£¬ÎÄŒþÃû£º" + domainName);

		}

	}

}
