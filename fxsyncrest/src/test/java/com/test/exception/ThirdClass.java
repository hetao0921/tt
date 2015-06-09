package com.test.exception;

public class ThirdClass {

	
	public void third(){
		SecondClass secondClass=new SecondClass();
		
		try{
			secondClass.second();
		}catch(Exception e){
			System.out.println(e);
		}
		
	}
	
	
	public static void main(String[] args) {
		ThirdClass thirdClass=new ThirdClass();
		thirdClass.third();
	}
}
