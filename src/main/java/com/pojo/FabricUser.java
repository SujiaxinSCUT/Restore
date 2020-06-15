package com.pojo;

import java.io.Serializable;


/**
 * The persistent class for the fabric_user database table.
 * 
 */
public class FabricUser implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String affiliation;
	private String mspId;
	private String name;
	private String objectHex;
	private String secret;
	private String sk;
	private String cert;
	private Account account;
	private Org org;

	public FabricUser() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAffiliation() {
		return this.affiliation;
	}

	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}

	public String getMspId() {
		return this.mspId;
	}

	public void setMspId(String mspId) {
		this.mspId = mspId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getObjectHex() {
		return this.objectHex;
	}

	public void setObjectHex(String objectHex) {
		this.objectHex = objectHex;
	}

	public String getSecret() {
		return this.secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getSk() {
		return sk;
	}
	
	public void setSk(String sk) {
		this.sk = sk;
	}

	public String getCert() {
		return cert;
	}
	
	public void setCert(String cert) {
		this.cert = cert;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Org getOrg() {
		return this.org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

}