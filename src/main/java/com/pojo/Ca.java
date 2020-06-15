package com.pojo;

import java.io.Serializable;


/**
 * The persistent class for the ca database table.
 * 
 */
public class Ca implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String admin;
	private String adminpw;
	private String location;
	private Org org;

	public Ca() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getAdmin() {
		return this.admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getAdminpw() {
		return this.adminpw;
	}

	public void setAdminpw(String adminpw) {
		this.adminpw = adminpw;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Org getOrg() {
		return this.org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

}