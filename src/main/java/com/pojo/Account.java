package com.pojo;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the account database table.
 * 
 */
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date date;
	private String name;
	private String password;
	private String permission;
	private boolean verified;
	private FabricUser fabricUser;

	public Account() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPermission() {
		return permission;
	}
	
	public void setPermission(String permission) {
		this.permission = permission;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public FabricUser getFabricUser() {
		return this.fabricUser;
	}

	public void setFabricUser(FabricUser fabricUser) {
		this.fabricUser = fabricUser;
	}

}