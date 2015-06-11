package corenet.exchange;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.LinkedList;

import com.sun.tools.xjc.outline.Outline;

public class BigDateSendTest {

	//生成5w条数据集合,并且写入数据
	static public void writeData() throws IOException {
		LinkedList<ClientInfo> list = new LinkedList<ClientInfo>();
		for(int i=0;i<50000;i++){
			String temp = Encoding.getUuid();
			ClientInfo clientInfo = new ClientInfo(String.valueOf(i), temp, temp, temp, temp, false, temp, false, false, i, temp, temp, temp, temp, temp, temp);
			list.add(clientInfo);
		}
		//ByteArrayInputStream
		
		 FileOutputStream fo = new FileOutputStream("D://BigData.dat");   
		  
	        ObjectOutputStream so = new ObjectOutputStream(fo);   
	  
	        try {   
	  
	            so.writeObject(list);   
	  
	            so.close();   
	  
	        } catch (IOException e) {   
	            System.out.println(e);   
	        }   
		
		
	}
	
	static public void readData() throws IOException {
		
		FileInputStream fi = new FileInputStream("D://BigData.dat");  
		 ObjectInputStream si = new ObjectInputStream(fi);   
		  
		   
		    try {   

		    	LinkedList<ClientInfo> list = (LinkedList<ClientInfo>) si.readObject();   

		        si.close();   
		        
		        for(ClientInfo info : list){
		        	System.out.println(info.getCamera1Name());
		        	break;
		        	
		        }

		    } catch (ClassNotFoundException e)   

		    {   
		        System.out.println(e);   
		    }   

		
	}
	
	
	//测试内存的方式进行写入和读出
	public static void  userByteArrayInputStream() throws IOException {
		
		LinkedList<ClientInfo> list = new LinkedList<ClientInfo>();
		for(int i=0;i<50000;i++){
			String temp = Encoding.getUuid();
			ClientInfo clientInfo = new ClientInfo(String.valueOf(i), temp, temp, temp, temp, false, temp, false, false, i, temp, temp, temp, temp, temp, temp);
			list.add(clientInfo);
		}
		//ByteArrayInputStream
		
	    	ByteArrayOutputStream out = new ByteArrayOutputStream(16*1024*1024);

	        ObjectOutputStream so = new ObjectOutputStream(out);   
	  
	        try {   
	            so.writeObject(list);   
	            so.close();   
	  
	            System.out.println(out.size());
	        } catch (IOException e) {   
	            System.out.println(e);   
	        }   
		
	        
	        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
	        
	        ObjectInputStream si = new ObjectInputStream(in);   
	        
		    try {   

		    	LinkedList<ClientInfo> list2 = (LinkedList<ClientInfo>) si.readObject();   

		        si.close();   
		        
		        for(ClientInfo info : list2){
		        	System.out.println(info.getCamera1Name());
		        	break;
		        	
		        }

		    } catch (ClassNotFoundException e)   

		    {   
		        System.out.println(e);   
		    }   

		
	}
	

	
	
	public static void main(String[] args) throws IOException {
//		writeData();
//		readData();
		userByteArrayInputStream();
		
	}
}
