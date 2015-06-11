package mavenweb;

public class TestSplit {
	public static void main(String[] args) {/*
		
		String restURL="asda/{123}/sdalk/{456}";
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
		System.out.println("reverseURL----"+reverseURL);
		System.out.println("fomat----"+fomat);
		
		
	*/
		
//	String str="123|456";
//	String[] list=str.split("\\|");
//	for (String string : list) {
//		System.out.println(string);
//	}
	
	String centerId="{";
	centerId=centerId.substring(0,centerId.length()-1);
	System.out.println("输出:"+centerId);
	}
	

}
