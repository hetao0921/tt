package com.hibernate.bean;



/**
 * WebGroupconf entity. @author MyEclipse Persistence Tools
 */

public class WebGroupconf  implements java.io.Serializable,IArrayPoJo {


    // Fields    

     private Long id;
     private String name;
     private String url;
     private String descs;

 	@Override
 	public String[] toStringArray() {
 		String[] result = new String[4];
		result[0] = id.toString();
		result[1] = name.toString();
		result[2] = url.toString();
		result[3] = descs.toString();
		return result;
 	}

    // Constructors

    /** default constructor */
    public WebGroupconf() {
    }

    
    /** full constructor */
    public WebGroupconf(String name, String url, String desc) {
        this.name = name;
        this.url = url;
        this.descs = desc;
    }

   
    // Property accessors

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescs() {
        return this.descs;
    }
    
    public void setDescs(String desc) {
        this.descs = desc;
    }


}