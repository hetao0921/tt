package com.test.exception;

public class OneClass {
	
	
	public void one() throws Exception{
		try{
			int i = 1/0;
		}catch(Exception e){
			throw new Exception("aaa");
		}
		
		
	}

}
