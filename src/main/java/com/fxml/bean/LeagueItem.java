package com.fxml.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.pojo.*;


/**
 * The persistent class for the league database table.
 * 
 */
public class LeagueItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date date;
	private String name;
	private Set<Orderer> orderers = new HashSet<>();
	private Set<Org> orgs = new HashSet<>();
	private Set<Channel> channels = new HashSet<>();

    private int ordererCount;
	private int orgCount;
	private int channelCount;
	private League league;

	public LeagueItem() {
	}
	
	public LeagueItem(League l) {
		this.league = l;
		this.id = l.getId();
		this.date = l.getDate();
		this.name = l.getName();
		this.orderers = l.getOrderers();
		this.orgs = l.getOrgs();
		this.channels =l.getChannels();
		this.ordererCount = l.getOrderers().size();
		this.orgCount = l.getOrgs().size();
		this.channelCount = l.getChannels().size();
	}
	
	

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public int getOrdererCount() {
		return ordererCount;
	}

	public void setOrdererCount(int ordererCount) {
		this.ordererCount = ordererCount;
	}

	public int getOrgCount() {
		return orgCount;
	}

	public void setOrgCount(int orgCount) {
		this.orgCount = orgCount;
	}

	public int getChannelCount() {
		return channelCount;
	}

	public void setChannelCount(int channelCount) {
		this.channelCount = channelCount;
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

	public Set<Orderer> getOrderers() {
		return this.orderers;
	}

	public void setOrderers(Set<Orderer> orderers) {
		this.orderers = orderers;
	}


	public Set<Org> getOrgs() {
		return this.orgs;
	}

	public void setOrgs(Set<Org> orgs) {
		this.orgs = orgs;
	}



	public Set<Channel> getChannels() {
		return this.channels;
	}

	public void setChannels(Set<Channel> channels) {
		this.channels = channels;
	}


}