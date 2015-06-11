package com.hibernate.bean;

/**
 * WebMenutable entity. @author MyEclipse Persistence Tools
 */

public class WebMenutable implements java.io.Serializable,IArrayPoJo {

	// Fields

	private Integer id;
	private String menuId;
	private String menuPid;
	private String menuName;
	private String url;

	public String[] toStringArray() {
 		String[] result = new String[5];
		result[0] = id.toString();
		result[1] = menuId.toString();
		result[2] = menuPid.toString();
		result[3] = menuName.toString();
		result[4] = url.toString();
		return result;
 	}
	
	// Constructors

	/** default constructor */
	public WebMenutable() {
	}

	/** full constructor */
	public WebMenutable(String menuId, String menuPid, String menuName,
			String url) {
		this.menuId = menuId;
		this.menuPid = menuPid;
		this.menuName = menuName;
		this.url = url;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMenuId() {
		return this.menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuPid() {
		return this.menuPid;
	}

	public void setMenuPid(String menuPid) {
		this.menuPid = menuPid;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}