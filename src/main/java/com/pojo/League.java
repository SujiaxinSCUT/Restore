package com.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * The persistent class for the league database table.
 * 
 */
public class League implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date date;
	private String name;
	private Set<Orderer> orderers = new HashSet<>();
	private Set<Org> orgs = new HashSet<>();
	private Set<Channel> channels = new HashSet<>();

	public League() {
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

	public Orderer addOrderer(Orderer orderer) {
		getOrderers().add(orderer);
		orderer.setLeague(this);

		return orderer;
	}

	public Orderer removeOrderer(Orderer orderer) {
		getOrderers().remove(orderer);
		orderer.setLeague(null);

		return orderer;
	}

	public Set<Org> getOrgs() {
		return this.orgs;
	}

	public void setOrgs(Set<Org> orgs) {
		this.orgs = orgs;
	}

	public Org addOrg(Org org) {
		getOrgs().add(org);
		org.setLeague(this);

		return org;
	}

	public Org removeOrg(Org org) {
		getOrgs().remove(org);
		org.setLeague(null);

		return org;
	}

	public Set<Channel> getChannels() {
		return this.channels;
	}

	public void setChannels(Set<Channel> channels) {
		this.channels = channels;
	}

	public Channel addChannel(Channel channel) {
		getChannels().add(channel);
		channel.setLeague(this);

		return channel;
	}

	public Channel removeChannel(Channel channel) {
		getChannels().remove(channel);
		channel.setLeague(null);

		return channel;
	}
}