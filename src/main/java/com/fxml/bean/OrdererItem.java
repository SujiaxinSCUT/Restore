package com.fxml.bean;

import java.io.Serializable;

import com.pojo.*;

/**
 * The persistent class for the orderer database table.
 * 
 */

public class OrdererItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String domain_suffix;
	private String location;
	private String name;
	private League league;
	
	private String leagueName;
	private Orderer orderer;

	public OrdererItem() {
	}
	
	public OrdererItem(Orderer o) {
		this.orderer = o;
		this.id = o.getId();
		this.domain_suffix = o.getDomain_suffix();
		this.location = o.getLocation();
		this.name = o.getName();
		this.league = o.getLeague();
		this.leagueName = o.getLeague().getName();
	}
	
	
	

	public Orderer getOrderer() {
		return orderer;
	}

	public void setOrderer(Orderer orderer) {
		this.orderer = orderer;
	}

	public String getLeagueName() {
		return leagueName;
	}

    public void setLeagueName(String leagueName) {
    	this.leagueName=leagueName;
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