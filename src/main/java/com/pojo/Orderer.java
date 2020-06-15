package com.pojo;

import java.io.Serializable;

/**
 * The persistent class for the orderer database table.
 * 
 */

public class Orderer implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String domain_suffix;
	private String location;
	private String name;
	private League league;

	public Orderer() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDomain_suffix() {
		return domain_suffix;
	}

	public void setDomain_suffix(String domain_suffix) {
		this.domain_suffix = domain_suffix;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public League getLeague() {
		return this.league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

}