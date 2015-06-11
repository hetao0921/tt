package com.hibernate.bean;

/**
 * WebMenutablesync entity. @author MyEclipse Persistence Tools
 */

public class WebMenutablesync implements java.io.Serializable {

	// Fields

	private Integer id;
	private String menuId;
	private String menuPid;
	private String menuName;
	private String url;

	// Constructors

	/** default constructor */
	public WebMenutablesync() {
	}

	/** full constructor */
	public WebMenutablesync(String menuId, String menuPid, String menuName,
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