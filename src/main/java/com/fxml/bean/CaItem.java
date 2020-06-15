package com.fxml.bean;

import java.io.Serializable;

import com.pojo.Ca;
import com.pojo.Org;


/**
 * The persistent class for the ca database table.
 * 
 */
public class CaItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String admin;
	private String adminpw;
	private String location;
	private Org org;
    private String orgName;
    private Ca ca;
    
	public CaItem() {
	}
	
	public CaItem(Ca c) {
		this.ca = c;
		this.id = c.getId();
		this.name = c.getName();
		this.admin = c.getAdmin();
		this.adminpw = c.getAdminpw();
		this.location = c.getLocation();
		this.org = c.getOrg();
		this.orgName = c.getOrg().getName();
	}

	
	
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Ca getCa() {
		return ca;
	}

	public void setCa(Ca ca) {
		this.ca = ca;
	}

	public String getOrgName() {
		return orgName;
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
		this.orgName=org.getName();
	}

}