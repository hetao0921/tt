package com.test.exception;

public class SecondClass {
	
	public void second() throws Exception{
		OneClass oneClass=new OneClass();
		try{
			oneClass.one();
		}catch(Exception e){
			throw new Exception(e);
		}
	}

}
