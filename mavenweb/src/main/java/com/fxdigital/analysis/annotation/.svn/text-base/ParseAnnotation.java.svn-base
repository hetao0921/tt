/**
 * 
 */
package com.fxdigital.analysis.annotation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @author lizehua
 * 
 */

public class ParseAnnotation {

	public void parseMethod(String className) throws Exception {
		String relativelyPath=System.getProperty("user.dir");
		String projectName=relativelyPath.substring(relativelyPath.lastIndexOf("\\")+1);
		String ip="http://{ip}/"+projectName;
		List<Message> list=new ArrayList<Message>();
		Class clazz = Class.forName(className);
		StringBuffer cs=new StringBuffer();
		
		
		Controller controller = (Controller) clazz.getAnnotation(Controller.class);
		String classMapping = ip;
		if (controller != null) {
			String ClaName=className.split("\\.")[className.split("\\.").length-1];
			cs.append("class "+ClaName+"{\n");
			cs.append(" public static string ip = \"192.168.1.102\";");
			RequestMapping classRequestMapping = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
			if (classRequestMapping != null) {
				String[] values = classRequestMapping.value();
				classMapping = classMapping+values[0];
			}
			
			for (Method method : clazz.getDeclaredMethods()) {
				Generate generate=method.getAnnotation(Generate.class);
				if(generate!=null){
					Class[] paramClass=generate.params();//获得输入参数类名 为put 和post 用
					for (Class class1 : paramClass) {
						if (!class1.equals(String.class) ) {
							generateEntity(class1.getCanonicalName());
							
						}
					}
					String[] returnType=generate.returnType();//获得返回类名
					if(!returnType[1].equals("")){
						generateEntity(returnType[1]);//生成返回的实体类
					}
					
					//生成rest url
					RequestMapping methodRequestMapping = method.getAnnotation(RequestMapping.class);
					if (methodRequestMapping != null) {
						Message message=new Message();
						RequestMethod[] requestMethod= methodRequestMapping.method();
						String[] urlValues=methodRequestMapping.value();
						String restURL;
						if(urlValues.length==0){
							restURL=classMapping;
						}else{
							 restURL=classMapping+urlValues[0];
						}
						
						String[] st=restURL.split("\\}");
						String methodParam="";//为C#生成方法的参数 delete 和get用
						String reverseURL="";
						String fomat="";
						for (int i = 0; i < st.length; i++) {
							String[] params=st[i].split("\\{");
							if(params.length==2){
								reverseURL=reverseURL+params[0]+"{"+i+"}";
								methodParam=methodParam+"string "+params[1]+",";
								fomat=fomat+params[1]+",";
							}
							
							if(params.length==1){
								reverseURL=reverseURL+params[0];
							}
							
						}
						methodParam=methodParam.substring(0,methodParam.length()-1);
						fomat=fomat.substring(0,fomat.length()-1);
						
						
						
						//获取rest的方式
						String restType="POST";
						if(!(requestMethod.length==0)){
							restType=requestMethod[0].name();
						}
						if("GET".equals(restType)){
							cs.append("public static "+returnType[0] +"  "+method.getName()+"("); //生成方法名
							cs.append(methodParam+"){"+"\n");//生成方法参数;
							cs.append("RestSharp.RestClient client = new RestSharp.RestClient(string.Format(string.Format(\""+reverseURL+"\"),"+fomat+");");
								
						cs.append(" var request = new RestSharp.RestRequest( RestSharp.Method.GET);");
						
						cs.append("     request.RequestFormat = RestSharp.DataFormat.Json;"+
            "var response = client.Execute<List<Resource>>(request);"+
            "if (response.StatusCode != System.Net.HttpStatusCode.OK)"+
            "{"+
                "MessageBox.Show(\"与中心服务器连接失败！\");"+
                "return null;"+
            "}"+
           " return response.Data;");
						}
						
						if("POST".equals(restType)||"PUT".equals(restType)){//POST
							cs.append("public static bool "+method.getName()+"("); //生成方法名
							if(paramClass==null||paramClass.length==0){
								cs.append("){"+"\n");
								cs.append("RestSharp.RestClient client = new RestSharp.RestClient(string.Format(string.Format(\""+reverseURL+"\"),"+fomat+");");
								
								cs.append(" var request = new RestSharp.RestRequest( RestSharp.Method."+restType+");");
								

							}else{
								String[] clazzName=paramClass[0].getName().split("\\.");
								cs.append(clazzName[clazzName.length-1]+" arg");
								cs.append("){"+"\n");
								cs.append("RestSharp.RestClient client = new RestSharp.RestClient(string.Format(string.Format(\""+reverseURL+"\"),"+fomat+");");
										
								cs.append(" var request = new RestSharp.RestRequest( RestSharp.Method."+restType+");");
								cs.append(" request.AddBody(arg);");
							}
							cs.append("request.RequestFormat = RestSharp.DataFormat.Json;"+
						            " var response = client.Execute(request);"+
						            " if (response.StatusCode != System.Net.HttpStatusCode.OK)"+
						            " {"+
						             "    MessageBox.Show(\"与中心服务器连接失败！\");"+
						            "     return false;"+
						           "  }"+
						           "  else"+
						           "  {"+
						           "      MessageBox.Show(\"保存分组成功！\");"+
						           "      return true;"+
						           "  }");
							
							
						}

						if("DELETE".equals(restType)){//POST
							cs.append("public static bool"+method.getName()+"("); //生成方法名
							cs.append(methodParam+"){"+"/n");//生成方法参数;
							cs.append("RestSharp.RestClient client = new RestSharp.RestClient(string.Format(string.Format(\""+reverseURL+"\"),"+fomat+");");
								cs.append(" var request = new RestSharp.RestRequest( RestSharp.Method.DELETE);");
							cs.append("     request.RequestFormat = RestSharp.DataFormat.Json;"+
          "  var response = client.Execute(request);"+

          "  if (response.StatusCode == System.Net.HttpStatusCode.OK)"+
          " {"+
          "      MessageBox.Show(\"删除成功\", \"提示\", MessageBoxButtons.OK, MessageBoxIcon.Asterisk);"+
          "      return true;"+
          "  }"+
          "  else if (response.StatusCode != System.Net.HttpStatusCode.InternalServerError)"+
          "  {"+
          "      MessageBox.Show(\"删除失败\", \"提示\", MessageBoxButtons.OK, MessageBoxIcon.Error);"+
          "      return false;"+
          "  }"+
          "  else if (response.StatusCode != System.Net.HttpStatusCode.NotFound)"+
          "  {"+
          "      MessageBox.Show(\"与中心服务器连接失败！\", \"提示\", MessageBoxButtons.OK, MessageBoxIcon.Error);"+
          "      return false;"+
          "  }"+
          "  else"+
          "  {"+
          "      return false;"+
          "  }");
						}
						
						
				}
				
					cs.append("}");
					
				}
				

				
				
			}

		}
		cs.append("}");
		generateClass(cs,className);//生成cs类文件
	}

	private void generateClass(StringBuffer cs,String className) {
		className=className.split("\\.")[className.split("\\.").length-1];
		String text=cs.toString();
		File file=new File("D:/classes/"+className+".cs");
		FileWriter write;
		try {
			write = new FileWriter(file);
			write.write(text);
			write.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void generateEntity(String string) {
		try {
			Class clazz=Class.forName(string);
			StringBuffer buffer=new StringBuffer();
			buffer.append("public class "+string.split("\\.")[string.split("\\.").length-1]+"{ \n");
			Field[] fields=clazz.getDeclaredFields();
			for (Field field : fields) {
//				field.setAccessible(true);
				buffer.append("public string "+field.getName()+" { get; set; } \n");
			}
			buffer.append("\n }");
			generateClass(buffer,string);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		Class clazz=Class.forName("com.fxdigital.analysis.bean.ResourceGroup");
		Field[] fields=clazz.getDeclaredFields();
		for (Field field : fields) {
//			field.setAccessible(true);
			System.out.println(field.getName());
		}
		
	}

}