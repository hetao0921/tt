package com.xfire.bean;

public class ReaderBean {
	 private static final long serialVersionUID = 1L;  
	    private String name;  
	    private String password;  
	      
	    public ReaderBean(){}  
	    public ReaderBean(String name,String password) {  
	        this.name = name;  
	        this.password = password;  
	    }  
	    
	    
	        public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		//Get/Set方法省略  
	    public String toString(){  
	        return "Name:"+name+",Password:"+password;  
	    }  
}
